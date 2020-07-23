package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelatorioDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.FechRelatoriosServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
* @author Ricardo.Barcante
*/
public class FechRelatoriosDelegate extends ContaCapitalRelatoriosDelegate<FechRelatoriosServico>{
	/**
	 * Método para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	private FechRelatoriosDelegate() {
	}
	
	/**
	 * Método para criar uma instancia do delegate
	 * @return
	 */
	public static FechRelatoriosDelegate getInstance() {
		return new FechRelatoriosDelegate();
	}
	
	/**
	 * Método para criar uma instancia do localizador de servico
	 */
	@Override
	protected FechRelatoriosServico localizarServico() {
		return (FechRelatoriosServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarFechRelatoriosServico();
	}
	
	public FechRelatorioDTO exportaRelatorioParaFormato(String formato, String caminhoArquivoJasper) throws BancoobException {
		return getServico().exportaRelatorioParaFormato(formato, caminhoArquivoJasper);
	}

	public Object listaRelatoriosPorData(FechRelatorioDTO dto) throws BancoobException {
		return getServico().listaRelatoriosPorData(dto);
	}
}
