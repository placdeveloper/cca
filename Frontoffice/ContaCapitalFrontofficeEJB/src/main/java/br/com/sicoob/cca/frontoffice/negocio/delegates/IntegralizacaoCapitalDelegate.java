/*
 * 
 */
package br.com.sicoob.cca.frontoffice.negocio.delegates;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.sicoob.cca.frontoffice.negocio.servicos.IntegralizacaoCapitalServico;
import br.com.sicoob.cca.frontoffice.negocio.servicos.locator.ContaCapitalFrontofficeServiceLocator;

/**
 * Delegate de integralizacao
 * @author Nairon.Silva
 */
public class IntegralizacaoCapitalDelegate extends ContaCapitalFrontofficeDelegate<IntegralizacaoCapitalServico> {

	@Override
	protected IntegralizacaoCapitalServico localizarServico() {
		return (IntegralizacaoCapitalServico) ContaCapitalFrontofficeServiceLocator.getInstance().localizarIntegralizacaoCapitalServico();
	}

	/**
	 * Executa uma transação a partir das mensagem informada.
	 * 
	 * @param mensagem
	 *            a mensagem usada para executar a transação.
	 * @return a mensagem de retorno.
	 * @throws ExcecaoTransacao
	 *             caso ocorra algum erro durante a execução da transação.
	 */
	public RetornoMensagem executarTransacao(Mensagem mensagem) throws ExcecaoTransacao {
		return getServico().executarTransacao(mensagem);
	}
	
}
