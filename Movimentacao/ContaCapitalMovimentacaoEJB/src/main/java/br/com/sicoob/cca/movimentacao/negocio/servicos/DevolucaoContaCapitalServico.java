/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;


// TODO: Auto-generated Javadoc
/**
 * A Interface DevolucaoContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
public interface DevolucaoContaCapitalServico extends ContaCapitalMovimentacaoServico {
	
	/**
	 * incluir.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @param lstParcelamentoRenDTO o valor de lst parcelamento ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluir(DevolucaoRenDTO devolucaoRenDTO, List<ParcelamentoRenDTO> lstParcelamentoRenDTO) throws BancoobException;	
	
	/**
	 * incluirCaptacaoRemunerada.
	 *
	 * @param devolucaoRenDTO o valor de devolucao ren dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluirCaptacaoRemunerada(DevolucaoRenDTO devolucaoRenDTO) throws BancoobException;	
	
}
