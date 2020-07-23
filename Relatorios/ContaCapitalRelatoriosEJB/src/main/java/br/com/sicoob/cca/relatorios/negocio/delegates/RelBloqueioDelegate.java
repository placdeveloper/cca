package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelBloqueioServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * @author Nairon.Silva
 */
public final class RelBloqueioDelegate extends ContaCapitalRelatoriosDelegate<RelBloqueioServico> {

	/**
	 * Método para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	private RelBloqueioDelegate() {
	}
	
	/**
	 * Método para criar uma instancia do delegate
	 * @return
	 */
	public static RelBloqueioDelegate getInstance() {
		return new RelBloqueioDelegate();
	}
	
	/**
	 * Método para criar uma instancia do localizador de servico
	 */
	@Override
	protected RelBloqueioServico localizarServico() {
		return (RelBloqueioServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelBloqueioServico();
	}
	
	/**
	 * Gera o relatorio de historico do bloqueio
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioHistoricoBloqueio(BloqueioContaCapitalDTO filtro) throws BancoobException {
		return getServico().gerarRelatorioHistoricoBloqueio(filtro);
	}
	
	/**
	 * Gera o relatorio de bloqueios
	 * @param tipoPesquisa o tipo de pesquisa utilizado para ser adicionado no header
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioBloqueios(String tipoPesquisa, BloqueioContaCapitalDTO filtro) throws BancoobException {
		return getServico().gerarRelatorioBloqueios(tipoPesquisa, filtro);
	}

}
