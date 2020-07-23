package br.com.sicoob.cca.invoker.servicos.frontoffice;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.invoker.ContaCapitalFrontofficeInvoker;

/**
 * Invoker da transacao CCACONSPARC
 * @author Nairon.Silva
 */
public class ConsultaIntegralizacaoAgendadaInvoker extends ContaCapitalFrontofficeInvoker {

	private static final String LOOKUP_NAME = "ConsultaIntegralizacaoAgendadaServicoRemote";
	
	private static final String	NUMERO_CPF = "45180806887";
	
	@Override
	protected List<Parametro> criarParametros() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.CPF_CNPJ, NUMERO_CPF, Types.VARCHAR));
		return parametros;
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new ConsultaIntegralizacaoAgendadaInvoker().invoke(LOOKUP_NAME);
	}

}
