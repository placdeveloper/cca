package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * RelDesligamentoAssociadoInvoker
 */
public class RelDesligamentoAssociadoInvoker extends ContaCapitalJasperReportInvoker {

	private static final String LOOKUP_NAME = "cca_relatorios/RelDesligamentoAssociadoServicoRemote";
	
	private static final int PARAM_ID_INSTITUICAO = 29;
	private static final int PARAM_ID_CCA = 1089786;
	private static final int PARAM_ID_PESSOA = 737382;
	
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("idInstituicao", PARAM_ID_INSTITUICAO);
		dto.getDados().put("idContaCapital", PARAM_ID_CCA);
		dto.getDados().put("idPessoa", PARAM_ID_PESSOA);
		return dto;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) throws JRException, IOException {
		new RelDesligamentoAssociadoInvoker().invoke(LOOKUP_NAME);
	}

}
