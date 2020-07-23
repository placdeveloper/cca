package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.DesligarContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;


/**
 * A Interface DesligarContaCapitalServico.
 *
 * @author Marco.Nascimento
 */
public interface DesligarContaCapitalServico extends ContaCapitalMovimentacaoServico {

	/**
	 * Realiza desligamento de uma conta capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param tipoDesligamento o valor de tipo desligamento
	 * @param dataDesligamento o valor de data desligamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void desligarContaCapital(Integer idContaCapital, Integer tipoDesligamento, Date dataDesligamento) throws BancoobException;
	
	/**
	 * Valida se eh possivel realizar o desligamento com encontro de contas.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	boolean validarDesligamentoEncontroContas(DesligarContaCapitalDTO dto) throws BancoobException;
	
	/**
	 * Realiza desligamento com encontro de contas.
	 * @param desligarDTO
	 * @param devolucaoDTO
	 * @param parcelamentosDTO
	 * @throws BancoobException
	 */
	void desligarEncontroContas(DesligarContaCapitalDTO desligarDTO, DevolucaoRenDTO devolucaoDTO, 
			List<ParcelamentoRenDTO> parcelamentosDTO) throws BancoobException;
	
}