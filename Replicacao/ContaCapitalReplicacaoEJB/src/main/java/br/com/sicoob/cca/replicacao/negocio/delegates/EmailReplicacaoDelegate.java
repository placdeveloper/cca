/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.servicos.EmailReplicacaoServico;
import br.com.sicoob.cca.replicacao.negocio.servicos.locator.ContaCapitalReplicacaoServiceLocator;

/**
 * Delegate EmailReplicacaoDelegate
 */
public class EmailReplicacaoDelegate  extends ContaCapitalReplicacaoDelegate<EmailReplicacaoServico> {

	/**
	 * Recupera a instancia
	 * @return
	 */
	public static EmailReplicacaoDelegate getInstance(){
		return new EmailReplicacaoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected EmailReplicacaoServico localizarServico() {
		return (EmailReplicacaoServico) ContaCapitalReplicacaoServiceLocator.getInstance().localizarEmailReplicacaoServico();	
	}

	
	/**
	 * @see br.com.sicoob.cca.replicacao.negocio.servicos.EmailReplicacaoServico#enviar(String[], String, String)
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	public void enviar(String[] destinatarios, String assunto, String texto) throws BancoobException{
		getServico().enviar(destinatarios, assunto, texto);
	}

}
