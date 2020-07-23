package br.com.sicoob.sisbr.cca.api.negocio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.APIContaCapitalNegocioException;

/**
 * Helper para fazer a tradução da chave de negócio nos serviços de rateio.
 * @author Nairon.Silva
 */
public final class ChaveLancamentoRateioHelper {

	private ChaveLancamentoRateioHelper() {
	}
	
	private static final String FORMATO_SDF = "yyyyMMdd";
	private static final String DELIMITADOR_ESCRITA = "|";
	private static final String DELIMITADOR_LEITURA = "\\|";
	
	/**
	 * Monta a chave String.
	 * @param lancamento
	 * @return
	 */
	public static String toString(LancamentoContaCapital lancamento) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_SDF);
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(lancamento.getDataLancamento()));
		sb.append(DELIMITADOR_ESCRITA);
		sb.append(lancamento.getIdInstituicao());
		sb.append(DELIMITADOR_ESCRITA);
		sb.append(lancamento.getDescOperacaoExterna());
		return sb.toString();
	}

	/**
	 * Monta a chave como objeto Lancamento.
	 * @param chave
	 * @return
	 * @throws APIContaCapitalNegocioException
	 */
	public static LancamentoContaCapital toLancamento(String chave) throws APIContaCapitalNegocioException {
		if (chave == null) {
			throw new APIContaCapitalNegocioException("MSG_ESTORNO_RATEIO_CHAVE_NULA");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_SDF);
		final int qtdPartes = 3;
		LancamentoContaCapital lancamento = new LancamentoContaCapital();
		String[] partesChave = chave.split(DELIMITADOR_LEITURA);
		if (partesChave.length != qtdPartes) {
			throw new APIContaCapitalNegocioException("MSG_ESTORNO_RATEIO_CHAVE_QTD_PARTES", chave);
		}
		int ix = 0;
		String data = partesChave[ix++];
		String idInstituicao = partesChave[ix++];
		String descOperacaoExterna = partesChave[ix++];
		try {
			lancamento.setDataLancamento(new DateTimeDB(sdf.parse(data).getTime()));
		} catch (ParseException e) {
			throw new APIContaCapitalNegocioException("MSG_ESTORNO_RATEIO_CHAVE_DATA", chave);
		}
		lancamento.setIdInstituicao(Integer.valueOf(idInstituicao));
		lancamento.setDescOperacaoExterna(descOperacaoExterna);
		return lancamento;
	}
	
}
