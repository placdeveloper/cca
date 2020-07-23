/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;

/**
 * @author Marco.Nascimento
 */
public final class ProcessamentoUtil {
	
	/**
	 * Instancia um novo ProcessamentoUtil.
	 */
	private ProcessamentoUtil() {
		
	}
	
	/**
	 * Obtem IdInstituicao pelo numero da cooperativa (DB2)
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	public static Integer getIdInstituicao(Integer numCoop) throws BancoobException {
		Integer idInstituicao = null;
		try {
			idInstituicao = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterIdInstituicao(numCoop);
		} catch (BancoobException e) {
			getLooger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		return idInstituicao;
	}
	
	/**
	 * Verifica se a instituicao é uma Central 
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	public static boolean isCentral(Integer idInstituicao) throws BancoobException {
		try {
			return ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterTipoGrauCooperativa(idInstituicao).isCentral();
		} catch (BancoobException e) {
			getLooger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
	}
	
	/**
	 * Verifica se a instituicao é uma Singular
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	public static boolean isSingular(Integer idInstituicao) throws BancoobException {
		try {
			return ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterTipoGrauCooperativa(idInstituicao).isSingular();
		} catch (BancoobException e) {
			getLooger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
	}
	
	/**
	 * Verifica se a instituicao esta ativa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	public static boolean isInstituicaosAtiva(Integer idInstituicao) throws BancoobException {
		try {
			return ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().isInstituicaoAtiva(idInstituicao);
		} catch (BancoobException e) {
			getLooger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
	}
	
	/**
	 * Formata para yyyyMM
	 * @param data
	 * @return Integer no formato yyyyMM 
	 */
	public static Integer getAnoMesFormatado(DateTime data) {
		return Integer.valueOf(data.getYear() + StringUtils.leftPad(String.valueOf(data.getMonthOfYear()), 2, "0"));
	}
	

	/**
	 * Ultimo dia do mês anterior, desconsiderando hora e minuto
	 * @return DateTimeDB
	 * @throws BancoobException 
	 */
	public static DateTimeDB getUltimoDiaMesAnterior() throws BancoobException {
		DateTime ultimoDiaMesAnterior = new DateTime().plusMonths(-1).dayOfMonth().withMaximumValue();
		Date dataSemHora = ContaCapitalUtil.formatarDataSemHora(ultimoDiaMesAnterior.toDate());
		return new DateTimeDB(dataSemHora.getTime());
	}
	
	/**
	 * Ultimo dia util do mês anterior
	 * @return DateTimeDB
	 * @throws BancoobException 
	 */
	public static DateTimeDB getUltimoDiaUtilMesAnterior(Integer idInstituicao) throws BancoobException {
		GenIntIntegracaoDelegate delegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGenIntIntegracaoDelegate();
		DateTime ultimoDia = new DateTime().plusMonths(-1).dayOfMonth().withMaximumValue();
		while(!delegate.verificarDiaUtil(idInstituicao, ultimoDia.toDate())) {
			ultimoDia = ultimoDia.plusDays(-1);
		}
		Date dataSemHora = ContaCapitalUtil.formatarDataSemHora(ultimoDia.toDate());
		return new DateTimeDB(dataSemHora.getTime());
	}
	
	/**
	 * Formata para yyyyMM
	 * @param data
	 * @return Integer no formato yyyyMM 
	 */
	public static Integer getAnoMesFormatadoAnterior(DateTime data) {
		return getAnoMesFormatado(data.plusMonths(-2));
	}
	
	/**
	 * Formata para yyyyMM
	 * @param data
	 * @return Integer no formato yyyyMM 
	 */
	public static Integer getAnoMesReplicacaoFormatadoAnterior(DateTime data) {
		return getAnoMesFormatado(data.plusMonths(-3));
	}
	
	/**
	 * Recupera o valor de looger.
	 *
	 * @return o valor de looger
	 */
	private static ISicoobLogger getLooger() {
		return SicoobLoggerPadrao.getInstance(ProcessamentoUtil.class);
	}
	
	/**
	 * Consulta a data de processamento para gravacao nas tabelas sql. A data esta tratada para pegar o prox. dia util se feriado ou fds
	 * @param idInstituicao
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	public static DateTimeDB getDataProcessamento(Integer idInstituicao,Date data) throws BancoobException {
				
		DateTimeDB dataSaida = null;
		try {
			GenIntIntegracaoDelegate delegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarGenIntIntegracaoDelegate();						
			if (!delegate.verificarDiaUtil(idInstituicao, data)){
				dataSaida = new DateTimeDB(delegate.recuperarProximoDiaUtil(idInstituicao, data).getTime());
			}else{
				dataSaida = new DateTimeDB(data.getTime());
			}
		} catch (BancoobException e) {
			getLooger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		
		return dataSaida;
	}	
	
}