/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.ParcelamentoContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe ParcelamentoContaCapitalDelegate.
 *
 * @author Antonio.Genaro
 */
public class ParcelamentoContaCapitalDelegate extends ContaCapitalMovimentacaoCrudDelegate<Parcelamento, ParcelamentoContaCapitalServico>{

	/**
	 * Instancia um novo ParcelamentoContaCapitalDelegate.
	 */
	ParcelamentoContaCapitalDelegate(){
		
	}

	/**
	 * Locator CadastroContaCapitalServico.
	 *
	 * @return ParcelamentoContaCapitalServico
	 */
	@Override
	protected ParcelamentoContaCapitalServico localizarServico() {
		return (ParcelamentoContaCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarParcelamentoContaCapitalServico();
	}	
		
	/**
	 * Pesquisar parcelamentos.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idTipoParcelamento o valor de id tipo parcelamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ParcelamentoRenDTO> pesquisarParcelamentos(Integer idContaCapital, Integer idTipoParcelamento)  throws BancoobException {
		return getServico().pesquisarParcelamentos(idContaCapital, idTipoParcelamento);
	}	

	/**
	 * O método Cancelar parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void cancelarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO)  throws BancoobException {
		getServico().cancelarParcelas(listaParcelasDTO);
	}	
	
	/**
	 * O método Baixar parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void baixarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO)  throws BancoobException {
		getServico().baixarParcelas(listaParcelasDTO);
	}	
	
	/**
	 * O método Gravar parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void gravarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO)  throws BancoobException {
		getServico().gravarParcelas(listaParcelasDTO);
	}	
	
	/**
	 * {@link ParcelamentoContaCapitalServico#pesquisarParcelasEmAberto(Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<Parcelamento> pesquisarParcelasEmAberto(Integer idContaCapital) throws BancoobException {
		return getServico().pesquisarParcelasEmAberto(idContaCapital);
	}
	
	/**
	 * Incluir parcelas.
	 *
	 * @param numContaCapital o valor de num conta capital
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @return Parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Parcelamento incluirParcelas(Integer numContaCapital, Integer codTipoParcelamento, List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException {
		return getServico().incluirParcelas(numContaCapital, codTipoParcelamento, listaParcelasDTO);
	}
	
	/**
	 * {@link ParcelamentoContaCapitalServico#pesquisarValorParcelasDevolucaoEmAberto(Integer)}
	 * 
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public BigDecimal pesquisarValorParcelasDevolucaoEmAberto(Integer idContaCapital) throws BancoobException {
		return getServico().pesquisarValorParcelasDevolucaoEmAberto(idContaCapital);
	}
	
}
