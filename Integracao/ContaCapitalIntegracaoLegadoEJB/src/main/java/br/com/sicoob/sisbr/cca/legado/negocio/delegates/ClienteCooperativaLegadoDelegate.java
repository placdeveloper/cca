/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ClienteCooperativaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * Delegate ClienteCooperativa (legado)
 * @author Marco.Nascimento
 */
public class ClienteCooperativaLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<ClienteCooperativaLegadoServico> {
	
	/** O atributo instance. */
	private static ClienteCooperativaLegadoDelegate instance;

	/**
	 * Instancia de OperacaoFinanceiraLegadoDelegate
	 */
	public static ClienteCooperativaLegadoDelegate getInstance() {
		if(instance == null) {
			instance = new ClienteCooperativaLegadoDelegate();
		}
		return instance;
	}
	
	/**
	 * {@link ContaCapitalIntegracaoLegadoServiceLocator#localizarClienteCooperativaLegadoServico()}
	 */
	@Override
	protected ClienteCooperativaLegadoServico localizarServico() {
		return (ClienteCooperativaLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarClienteCooperativaLegadoServico();
	}
	
	/**
	 * {@link ClienteCooperativaLegadoServico#consultarClienteCooperativa(Integer, Integer)}
	 * @param numCoop
	 * @throws BancoobException
	 */
	public Integer consultarClienteCooperativa(Integer numCoop, Integer numPac) throws BancoobException {
		return getServico().consultarClienteCooperativa(numCoop, numPac);
	}
}