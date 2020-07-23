package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * The Class RelSituacaoPeriodoCCARenInvoker.
 */
public class RelSituacaoPeriodoCCARenInvoker extends ContaCapitalJasperReportInvoker {
	
	/** The Constant LOOKUP_NAME. */
	private static final String LOOKUP_NAME = "cca_relatorios/RelSituacaoPeriodoCCARenServicoRemote";
	
	/** The Constant PARAM_ID_INSTITUICAO. */
	private static final int PARAM_ID_INSTITUICAO = 29;
	
	/** The Constant PARAM_ID_SITUACAO_CONTA. */
	private static final int PARAM_ID_SITUACAO_CONTA = 1;
	
	/** The Constant PARAM_CADASTROS_APROVADOS. */
	private static final boolean PARAM_CADASTROS_APROVADOS = true;

	/** The Constant PARAM_NUM_PA. */
	private static final Integer PARAM_NUM_PA = -1;
	
	/** The Constant PARAM_FILTRO_ORDENACAO. */
	private static final int PARAM_FILTRO_ORDENACAO = 2;
	
	private static final Date DT_INICIAL = new Date(DateTime.parse("2017-08-01").getMillis());
	
	private static final Date DT_FINAL = new Date(DateTime.parse("2018-12-31").getMillis());
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.invoker.relatorios.ContaCapitalJasperReportInvoker#criarParametros()
	 */
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("filtroIdInstituicao", PARAM_ID_INSTITUICAO);
		dto.getDados().put("filtroDataPeriodoInicial", DT_INICIAL);
		dto.getDados().put("filtroDataPeriodoFinal", DT_FINAL);
		dto.getDados().put("filtroIdSituacao", PARAM_ID_SITUACAO_CONTA);
		dto.getDados().put("filtroCadastrosAprovados", PARAM_CADASTROS_APROVADOS);
		dto.getDados().put("filtroNumPA", PARAM_NUM_PA);
		dto.getDados().put("filtroOrdenacao", PARAM_FILTRO_ORDENACAO);
		return dto;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws JRException the JR exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws JRException, IOException {
		new RelSituacaoPeriodoCCARenInvoker().invoke(LOOKUP_NAME);
	}	
}