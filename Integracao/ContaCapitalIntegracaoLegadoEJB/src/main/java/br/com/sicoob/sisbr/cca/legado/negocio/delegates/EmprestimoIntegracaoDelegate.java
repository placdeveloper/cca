/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.EmprestimoIntegracaoLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class EmprestimoIntegracaoDelegate extends ContaCapitalIntegracaoLegadoDelegate<EmprestimoIntegracaoLegadoServico> {
	
	/**
	 * Instancia um novo EmprestimoIntegracaoDelegate.
	 */
	EmprestimoIntegracaoDelegate() {
		
	}

	/**
	 * Recupera a unica instancia de EmprestimoIntegracaoDelegate.
	 *
	 * @return uma instancia de EmprestimoIntegracaoDelegate
	 */
	public static EmprestimoIntegracaoDelegate getInstance() {
		return new EmprestimoIntegracaoDelegate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmprestimoIntegracaoLegadoServico localizarServico() {
		return (EmprestimoIntegracaoLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarEmprestimoIntegracaoServico();
	}
	
	/**
	 * {@link EmprestimoIntegracaoLegadoServico#consultarEmprestimos(Integer)} 
	 */
	public List<EmprestimoIntegracaoDTO> consultarEmprestimos(Integer numCliente, Long numContaCorrente) throws BancoobException {
		return getServico().consultarEmprestimos(numCliente, numContaCorrente);
	}
}