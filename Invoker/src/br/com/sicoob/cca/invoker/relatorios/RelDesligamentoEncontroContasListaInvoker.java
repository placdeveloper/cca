package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * RelDesligamentoEncontroContasListaInvoker
 */
public class RelDesligamentoEncontroContasListaInvoker extends ContaCapitalJasperReportInvoker {

	private static final String LOOKUP_NAME = "cca_relatorios/RelDesligamentoEncontroContasListaServicoRemote";
	
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("numContaCapitalInicial", 1);
		dto.getDados().put("numContaCapitalFinal", 999999);
		return dto;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) throws JRException, IOException {
		new RelDesligamentoEncontroContasListaInvoker().invoke(LOOKUP_NAME);
	}

}
