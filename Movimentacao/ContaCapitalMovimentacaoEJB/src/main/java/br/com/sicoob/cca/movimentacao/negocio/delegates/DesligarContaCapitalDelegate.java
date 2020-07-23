/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.DesligarContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.DesligarContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;

// TODO: Auto-generated Javadoc
/**
 * A Classe DesligarContaCapitalDelegate.
 *
 * @author marco.nascimento
 */
public class DesligarContaCapitalDelegate extends ContaCapitalMovimentacaoDelegate<DesligarContaCapitalServico>{

	/**
	 * Instancia um novo DesligarContaCapitalDelegate.
	 */
	DesligarContaCapitalDelegate() {
		
	}

	/**
	 * Locator DesligarContaCapitalDelegate.
	 *
	 * @return DesligarContaCapitalServico
	 */
	@Override
	protected DesligarContaCapitalServico localizarServico() {
		return (DesligarContaCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarDesligarContaCapitalServico();
	}
	
	/**
	 * {@link DesligarContaCapitalServico#desligarContaCapital(Integer, Integer, Date)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param tipoDesligamento o valor de tipo desligamento
	 * @param dataDesligamento o valor de data desligamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void desligarContaCapital(Integer idContaCapital, Integer tipoDesligamento, Date dataDesligamento) throws BancoobException {
		getServico().desligarContaCapital(idContaCapital, tipoDesligamento, dataDesligamento);
	}
	
	/**
	 * {@link DesligarContaCapitalServico#validarDesligamentoEncontroContas(DesligarContaCapitalDTO)}
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public boolean validarDesligamentoEncontroContas(DesligarContaCapitalDTO dto) throws BancoobException {
		return getServico().validarDesligamentoEncontroContas(dto);
	}
	
	/**
	 * {@link DesligarContaCapitalServico#desligarEncontroContas(DesligarContaCapitalDTO, DevolucaoRenDTO, List, List)}
	 * @param desligarDTO
	 * @param devolucaoDTO
	 * @param parcelamentosDTO
	 * @throws BancoobException
	 */
	public void desligarEncontroContas(DesligarContaCapitalDTO desligarDTO, DevolucaoRenDTO devolucaoDTO, 
			List<ParcelamentoRenDTO> parcelamentosDTO) throws BancoobException {
		getServico().desligarEncontroContas(desligarDTO, devolucaoDTO, parcelamentosDTO);
	}
	
}