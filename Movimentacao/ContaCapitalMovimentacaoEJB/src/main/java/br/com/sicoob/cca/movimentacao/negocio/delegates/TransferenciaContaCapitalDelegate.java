package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.TransferenciaRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.TransferenciaContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe TransferenciaContaCapitalDelegate.
 *
 * @author Antonio.Genaro
 */
public class TransferenciaContaCapitalDelegate extends ContaCapitalMovimentacaoDelegate<TransferenciaContaCapitalServico> {
	/**
	 * Instancia um novo TransferenciaContaCapitalDelegate.
	 */
	TransferenciaContaCapitalDelegate() {
		
	}	
	
	/**
	 * Locator TransferenciaContaCapitalServico.
	 *
	 * @return TransferenciaContaCapitalServico
	 */
	@Override
	protected TransferenciaContaCapitalServico localizarServico() {
		return (TransferenciaContaCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarTransferenciaContaCapitalServico();
	}	
	
	/**
	 * incluir.
	 *
	 * @param transferenciaRenDTO o valor de transferencia ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluir(TransferenciaRenDTO transferenciaRenDTO) throws BancoobException {		
		getServico().incluir(transferenciaRenDTO);
	}	
}
