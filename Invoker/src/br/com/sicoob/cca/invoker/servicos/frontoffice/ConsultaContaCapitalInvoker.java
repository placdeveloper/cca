package br.com.sicoob.cca.invoker.servicos.frontoffice;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.invoker.ContaCapitalFrontofficeInvoker;

/**
 * Invoker da transacao CCACONSULTACCA
 * @author Nairon.Silva
 */
public class ConsultaContaCapitalInvoker extends ContaCapitalFrontofficeInvoker {

	private static final String LOOKUP_NAME = "cca-frontoffice/ConsultaContaCapitalServicoRemote";
	
	private static final Integer TIPO_PESQUISA = 3;
//	private static final String  VALOR_PESQUISA = "Roberto de"; // 1 - nome
//	private static final String  VALOR_PESQUISA = "32528088035"; // 2 - cpfcnpj
	private static final String  VALOR_PESQUISA = "87"; // 3 - cca
	
	@Override
	protected List<Parametro> criarParametros() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.TIPO_PESQUISA, TIPO_PESQUISA, Types.TINYINT));
		parametros.add(criarParametro(ParametroSRTBCCA.CHAVE_PESQUISA, VALOR_PESQUISA, Types.VARCHAR));
		return parametros;
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new ConsultaContaCapitalInvoker().invoke(LOOKUP_NAME);
	}

}
