/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.PesquisaEmpresaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.PesquisaEmpresaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe PesquisaEmpresaLegadoDelegate.
 */
public class PesquisaEmpresaLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<PesquisaEmpresaLegadoServico> {

	/**
	 * Recupera a unica instancia de PesquisaEmpresaDelegate.
	 *
	 * @return uma instancia de PesquisaEmpresaDelegate
	 */
	public static PesquisaEmpresaLegadoDelegate getInstance() {
		return new PesquisaEmpresaLegadoDelegate();
	}	
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected PesquisaEmpresaLegadoServico localizarServico() {
		return (PesquisaEmpresaLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarPesquisaEmpresaServico();
	}
	
	/**
	 * {@link PesquisaEmpresaLegadoServico#pesquisar(PesquisaEmpresaDTO)}
	 */
	public List<PesquisaEmpresaDTO> pesquisar(PesquisaEmpresaDTO dto) throws BancoobException {
		return getServico().pesquisar(dto);
	}
}