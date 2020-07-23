package br.com.sicoob.cca.invoker.servicos.frontoffice;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.invoker.ContaCapitalFrontofficeInvoker;

/**
 * Invoker da transacao CCACANCPARC
 * @author Nairon.Silva
 */
public class CancelamentoIntegralizacaoAgendadaInvoker extends ContaCapitalFrontofficeInvoker {

	private static final String LOOKUP_NAME = "CancelamentoIntegralizacaoAgendadaServicoRemote";
	
	private static final String		NUMERO_CPF = "45180806887";
	private static final Double 	NUMERO_CONTA_CORRENTE = 19D;
	private static final String		IDENTIFICADOR = "1-2-0-1"; // Utilizar a consulta para preencher este campo

	@Override
	protected List<Parametro> criarParametros() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.CPF_CNPJ, NUMERO_CPF, Types.VARCHAR));
		parametros.add(criarParametro(ParametroSRTBCCA.NUMERO_CONTA_CORRENTE, NUMERO_CONTA_CORRENTE, Types.DECIMAL));
		parametros.add(criarParametro(ParametroSRTBCCA.IDENTIFICADOR, IDENTIFICADOR, Types.VARCHAR));
		return parametros;
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new CancelamentoIntegralizacaoAgendadaInvoker().invoke(LOOKUP_NAME);
	}
	
}
