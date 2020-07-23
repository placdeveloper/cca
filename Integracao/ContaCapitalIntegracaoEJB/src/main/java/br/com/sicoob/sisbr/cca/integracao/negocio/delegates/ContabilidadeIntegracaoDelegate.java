/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DadosConciliacaoContabilIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContabilidadeIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * A Classe ContabilidadeIntegracaoDelegate.
 */
public class ContabilidadeIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<ContabilidadeIntegracaoServico> {
	
	/**
	 * Recupera a unica instancia de ContabilidadeIntegracaoDelegate.
	 *
	 * @return uma instancia de ContabilidadeIntegracaoDelegate
	 */
	public static ContabilidadeIntegracaoDelegate getInstance() {
		return new ContabilidadeIntegracaoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ContabilidadeIntegracaoServico localizarServico() {
		return (ContabilidadeIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarContabilidadeIntegracaoServico();
	}

	/**
	 * @see ContabilidadeIntegracaoServico#incluirConciliacaoContabil(List)
	 * @param lst
	 * @throws BancoobException
	 */
	public void incluirConciliacaoContabil(List<DadosConciliacaoContabilIntegracaoDTO> lst) throws BancoobException {
		getServico().incluirConciliacaoContabil(lst) ;		
	}
}