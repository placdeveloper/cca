package br.com.sicoob.cca.invoker.servicos.frontoffice;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.invoker.ContaCapitalFrontofficeInvoker;

/**
 * Invoker para a transacao CCAINT
 * @author Nairon.Silva
 */
public class IntegralizacaoCapitalServicoInvoker extends ContaCapitalFrontofficeInvoker {

	private static final String LOOKUP_NAME = "IntegralizacaoCapitalServicoRemote";
	
	private static final String 	NUMERO_CPF = "45180806887";
	private static final BigDecimal VALOR_INTEGRALIZACAO = BigDecimal.valueOf(50);
	private static final Byte 		TIPO_AGENDAMENTO = 0; // 0 - nesta data / 1 - programada
	private static final Integer 	QTD_MESES = 1;
	private static final Integer 	DIA_DEBITO = 10;
	private static final Double 	NUMERO_CONTA_CORRENTE = 19D;
	
	@Override
	protected List<Parametro> criarParametros() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.CPF_CNPJ, NUMERO_CPF, Types.VARCHAR));
		parametros.add(criarParametro(ParametroSRTBCCA.VALOR, VALOR_INTEGRALIZACAO, Types.DECIMAL));
		parametros.add(criarParametro(ParametroSRTBCCA.TIPO_AGENDAMENTO, TIPO_AGENDAMENTO, Types.TINYINT));
		parametros.add(criarParametro(ParametroSRTBCCA.MESES, QTD_MESES, Types.TINYINT));
		parametros.add(criarParametro(ParametroSRTBCCA.DIA_DEBITO, DIA_DEBITO, Types.SMALLINT));
		parametros.add(criarParametro(ParametroSRTBCCA.NUMERO_CONTA_CORRENTE, NUMERO_CONTA_CORRENTE, Types.DECIMAL));
		return parametros;
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new IntegralizacaoCapitalServicoInvoker().invoke(LOOKUP_NAME);
	}
	
}
