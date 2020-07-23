package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * RelImpedimentosDesligamentoInvoker
 */
public class RelImpedimentosDesligamentoInvoker extends ContaCapitalJasperReportInvoker {

	private static final String LOOKUP_NAME = "cca_relatorios/RelImpedimentosDesligamentoServicoRemote";
	
	private static final int PARAM_ID_INST = 29;
	private static final int PARAM_ID_CCA = 1528488;
	private static final int PARAM_ID_PES = 743910;
	private static final boolean PARAM_ESCONDER_EMPRESTIMOS = true;
	
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("idInstituicao", PARAM_ID_INST);
		dto.getDados().put("idContaCapital", PARAM_ID_CCA);
		dto.getDados().put("idPessoa", PARAM_ID_PES);
		dto.getDados().put("esconderEmprestimos", PARAM_ESCONDER_EMPRESTIMOS);
		return dto;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) throws JRException, IOException {
		new RelImpedimentosDesligamentoInvoker().invoke(LOOKUP_NAME);
	}

}
