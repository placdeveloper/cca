package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * RelDesligamentoEncontroContasInvoker
 */
public class RelDesligamentoEncontroContasInvoker extends ContaCapitalJasperReportInvoker {

private static final String LOOKUP_NAME = "cca_relatorios/RelDesligamentoEncontroContasServicoRemote";
	
	private static final int PARAM_ID_CCA = 1528487;
	
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("idContaCapital", PARAM_ID_CCA);
		return dto;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) throws JRException, IOException {
		new RelDesligamentoEncontroContasInvoker().invoke(LOOKUP_NAME);
	}

}
