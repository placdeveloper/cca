package br.com.sicoob.cca.invoker.servicos.frontoffice;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.invoker.ContaCapitalFrontofficeInvoker;

/**
 * Invoker da transacao CCAINTEGDEVOLCAIXA
 * @author Nairon.Silva
 */
public class IntegDevolCapitalViaCaixaInvoker extends ContaCapitalFrontofficeInvoker {

	private static final String LOOKUP_NAME = "cca-frontoffice/IntegDevolCapitalViaCaixaServicoRemote";

	private static final String  USUARIO = "TesteFrontoffice5";
	private static final String  IDENTIFICADOR = "2-50176-2-0-20.00";
	
	@Override
	protected List<Parametro> criarParametros() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.ID_USUARIO, USUARIO, Types.VARCHAR));
		parametros.add(criarParametro(ParametroSRTBCCA.IDENTIFICADOR, IDENTIFICADOR, Types.VARCHAR));
		return parametros;
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new IntegDevolCapitalViaCaixaInvoker().invoke(LOOKUP_NAME);
	}

}
