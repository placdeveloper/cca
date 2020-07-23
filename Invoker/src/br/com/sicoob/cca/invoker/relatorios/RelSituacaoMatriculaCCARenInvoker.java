package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * The Class RelSituacaoMatriculaCCARenInvoker.
 */
public class RelSituacaoMatriculaCCARenInvoker extends ContaCapitalJasperReportInvoker {
	
	/** The Constant LOOKUP_NAME. */
	private static final String LOOKUP_NAME = "cca_relatorios/RelSituacaoMatriculaCCARenServicoRemote";
	
	/** The Constant PARAM_ID_INSTITUICAO. */
	private static final int PARAM_ID_INSTITUICAO = 29;
	
	/** The Constant PARAM_ID_CCA_INICIAL. */
	private static final int PARAM_ID_CCA_INICIAL = 1;
	
	/** The Constant PARAM_ID_CCA_FINAL. */
	private static final int PARAM_ID_CCA_FINAL = 1000;
	
	/** The Constant PARAM_ID_SITUACAO_CONTA. */
	private static final int PARAM_ID_SITUACAO_CONTA = -1;
	
	/** The Constant PARAM_CADASTROS_APROVADOS. */
	private static final boolean PARAM_CADASTROS_APROVADOS = false;

	/** The Constant PARAM_ULTIMA_SITUACAO. */
	private static final boolean PARAM_ULTIMA_SITUACAO = true;

	/** The Constant PARAM_NUM_PA. */
	private static final Integer PARAM_NUM_PA = -1;
	
	/** The Constant PARAM_FILTRO_ORDENACAO. */
	private static final int PARAM_FILTRO_ORDENACAO = 2;
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.invoker.relatorios.ContaCapitalJasperReportInvoker#criarParametros()
	 */
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("filtroIdInstituicao", PARAM_ID_INSTITUICAO);
		dto.getDados().put("filtroNumContaCapitalInicial", PARAM_ID_CCA_INICIAL);
		dto.getDados().put("filtroNumContaCapitalFinal", PARAM_ID_CCA_FINAL);
		dto.getDados().put("filtroIdSituacaoConta", PARAM_ID_SITUACAO_CONTA);
		dto.getDados().put("filtroUltimaSituacao", PARAM_ULTIMA_SITUACAO);
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
		new RelSituacaoMatriculaCCARenInvoker().invoke(LOOKUP_NAME);
	}	
}