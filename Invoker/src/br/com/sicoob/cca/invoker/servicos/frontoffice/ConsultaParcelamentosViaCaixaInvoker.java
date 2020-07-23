package br.com.sicoob.cca.invoker.servicos.frontoffice;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.invoker.ContaCapitalFrontofficeInvoker;

/**
 * Invoker da transacao CCACONSPARCCAIXA
 * @author Nairon.Silva
 */
public class ConsultaParcelamentosViaCaixaInvoker extends ContaCapitalFrontofficeInvoker {

	private static final String LOOKUP_NAME = "cca-frontoffice/ConsultaParcelamentosViaCaixaServicoRemote";
	
	private static final Integer TIPO_PARCELAMENTO = 1;
	private static final Integer NUM_CONTA_CAPITAL = 21856;
	
	@Override
	protected List<Parametro> criarParametros() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.TIPO_PARCELAMENTO, TIPO_PARCELAMENTO, Types.TINYINT));
		parametros.add(criarParametro(ParametroSRTBCCA.MATRICULA, NUM_CONTA_CAPITAL, Types.TINYINT));
		return parametros;
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new ConsultaParcelamentosViaCaixaInvoker().invoke(LOOKUP_NAME);
	}

}
