package br.com.sicoob.cca.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.servicos.ControleReplicacaoServico;
import br.com.sicoob.cca.replicacao.negocio.servicos.locator.ContaCapitalReplicacaoServiceLocator;

/**
 * ControleReplicacaoDelegate
 */
public class ControleReplicacaoDelegate extends ContaCapitalReplicacaoDelegate<ControleReplicacaoServico> {

	/**
	 * Recupera a instancia
	 * @return
	 */
	public static ControleReplicacaoDelegate getInstance(){
		return new ControleReplicacaoDelegate();
	}	
	
	@Override
	protected ControleReplicacaoServico localizarServico() {
		return (ControleReplicacaoServico) ContaCapitalReplicacaoServiceLocator.getInstance().localizarControleReplicacaoServico();
	}
	
	/**
	 * Inicia a replicacao.
	 * @throws BancoobException
	 */
	public void iniciarReplicacao() throws BancoobException {
		getServico().iniciarReplicacao();
	}

}
