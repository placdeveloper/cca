/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.SubscricaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.SubscricaoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe SubscricaoContaCapitalDelegate.
 *
 * @author Antonio.Genaro
 */
public class SubscricaoContaCapitalDelegate extends	ContaCapitalMovimentacaoDelegate<SubscricaoContaCapitalServico>{

	
	/**
	 * Instancia um novo SubscricaoContaCapitalDelegate.
	 */
	SubscricaoContaCapitalDelegate() {
		
	}	
	
	/**
	 * Locator CadastroContaCapitalServico.
	 *
	 * @return SubscricaoContaCapitalServico
	 */
	@Override
	protected SubscricaoContaCapitalServico localizarServico() {
		return (SubscricaoContaCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarSubscricaoContaCapitalServico();
	}	
	
	/**
	 * O método Incluir.
	 *
	 * @param subscricaoRenDTO o valor de subscricao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluir(SubscricaoRenDTO subscricaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException {		
		getServico().incluir(subscricaoRenDTO, lstParcelamentoRenDTO);
	}	
	
}
