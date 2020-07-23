package br.com.sicoob.cca.movimentacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoOutrosBancosDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.IntegralizacaoOutrosBancosServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

public class IntegralizacaoOutrosBancosDelegate extends ContaCapitalMovimentacaoDelegate<IntegralizacaoOutrosBancosServico> {

	/**
	 * Instancia um novo IntegralizacaoOutrosBancosDelegate.
	 */
	IntegralizacaoOutrosBancosDelegate() {
		
	}

	/**
	 * Locator IntegralizacaoOutrosBancosDelegate.
	 *
	 * @return IntegralizacaoOutrosBancosServico
	 */
	@Override
	protected IntegralizacaoOutrosBancosServico localizarServico() {
		return (IntegralizacaoOutrosBancosServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarIntegralizacaoOutrosBancosServico();
	}
	
	
	
	/**
	 * {@link IntegralizacaoOutrosBancosServico#incluirIntegralizacao(IntegralizacaoOutrosBancosDTO)}
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public void incluirIntegralizacao(IntegralizacaoOutrosBancosDTO dto) throws BancoobException {
		getServico().incluirIntegralizacao(dto);
	}	
	
}
