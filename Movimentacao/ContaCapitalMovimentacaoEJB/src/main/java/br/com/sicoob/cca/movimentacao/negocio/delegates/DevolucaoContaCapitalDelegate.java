/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.DevolucaoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe DevolucaoContaCapitalDelegate.
 *
 * @author Antonio.Genaro
 */
public class DevolucaoContaCapitalDelegate extends	ContaCapitalMovimentacaoDelegate<DevolucaoContaCapitalServico>{

	
	/**
	 * Instancia um novo DevolucaoContaCapitalDelegate.
	 */
	DevolucaoContaCapitalDelegate() {
		
	}	
	
	/**
	 * Locator CadastroContaCapitalServico.
	 *
	 * @return DevolucaoContaCapitalServico
	 */
	@Override
	protected DevolucaoContaCapitalServico localizarServico() {
		return (DevolucaoContaCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarDevolucaoContaCapitalServico();
	}	
	
	/**
	 * incluir.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluir(DevolucaoRenDTO devolucaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException {		
		getServico().incluir(devolucaoRenDTO, lstParcelamentoRenDTO);
	}	
	
	/**
	 * incluirCaptacaoRemunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluirCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException {		
		getServico().incluirCaptacaoRemunerada(devolucaoRenDTO);
	}	
	
}
