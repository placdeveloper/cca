package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;

/**
 * A Interface ParcelamentoContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
public interface ParcelamentoContaCapitalServico extends ContaCapitalMovimentacaoCrudServico<Parcelamento> {

	/**
	 * Pesquisar parcelamentos.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idTipoParcelamento o valor de id tipo parcelamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<ParcelamentoRenDTO> pesquisarParcelamentos(Integer idContaCapital, Integer idTipoParcelamento)  throws BancoobException;
	
	/**
	 * O método Cancelar parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void cancelarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO)  throws BancoobException;
	
	/**
	 * O método Baixar parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void baixarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO)  throws BancoobException;
	
	/**
	 * O método Gravar parcelas.
	 *
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void gravarParcelas(List<ParcelamentoRenDTO> listaParcelasDTO)  throws BancoobException;
	
	/**
	 * Incluir parcelas.
	 *
	 * @param numContaCapital o valor de num conta capital
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @param listaParcelasDTO o valor de lista parcelas dto
	 * @return Parcelamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Parcelamento incluirParcelas(Integer numContaCapital, Integer codTipoParcelamento, List<ParcelamentoRenDTO> listaParcelasDTO) throws BancoobException;
	

	/**
	 * Pesquisa parcelas em aberto de uma conta capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Parcelamento> pesquisarParcelasEmAberto(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Pesquisa o valor das parcelas em aberto de devolucao em aberto.
	 * 
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal pesquisarValorParcelasDevolucaoEmAberto(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Realiza a alteração em lote dos parcelamentos.
	 * 
	 * @param Parcelamento
	 * @return
	 * @throws BancoobException
	 */
	void alterarEmLote(List<Parcelamento> listParcelamento) throws BancoobException;
	
	/**
	 * Realiza a cadastro em lote dos parcelamentos.
	 * 
	 * @param Parcelamento
	 * @return
	 * @throws BancoobException
	 */
	void incluirEmLote(List<Parcelamento> listParcelamento) throws BancoobException;
}
