package br.com.sicoob.cca.invoker.relatorios;

import java.io.IOException;

import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import net.sf.jasperreports.engine.JRException;

/**
 * Relatorio debito indeterminado.
 * @author Nairon.Silva
 */
public class RelDebitoIndeterminadoInvoker extends ContaCapitalJasperReportInvoker {

	private static final String LOOKUP_NAME = "cca_relatorios/RelDebitoIndeterminadoServicoRemote";
	
	@Override
	protected ParametroDTO criarParametros() {
		ParametroDTO dto = new ParametroDTO();
		dto.getDados().put("filtro", "Nenhum");
		return dto;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) throws JRException, IOException {
		new RelDebitoIndeterminadoInvoker().invoke(LOOKUP_NAME);
	}
	
}
