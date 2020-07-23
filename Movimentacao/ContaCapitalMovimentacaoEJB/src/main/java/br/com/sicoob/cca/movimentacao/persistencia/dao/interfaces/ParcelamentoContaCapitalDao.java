package br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;

/**
 * A Interface ParcelamentoContaCapitalDao.
 *
 * @author Antonio.Genaro
 */
public interface ParcelamentoContaCapitalDao extends ContaCapitalMovimentacaoCrudDaoIF<Parcelamento> {
	
	/**
	 * Pesquisar parcelamentos.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idTipoParcelamento o valor de id tipo parcelamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<ParcelamentoRenDTO> pesquisarParcelamentos(Integer idContaCapital, Integer idTipoParcelamento) throws BancoobException;

	/**
	 * Pesquisar parcelas em aberto.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Parcelamento> pesquisarParcelasEmAberto(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Pesquisa parcelamentos em aberto via caixa.
	 * Parametros dto: numContaCapital, idInstituicao e idTipoParcelamento.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<ParcelamentoRenDTO> pesquisarParcelamentosEmAbertoViaCaixa(ParcelamentoCapitalDTO dto) throws BancoobException;

	/**
	 * Realiza o update nos parcelamentos atualizando a dataSituacaoParcela e o idSituacaoParcelamento
	 * @param idsParcelamento
	 * @param dataSituacaoParcela
	 * @param idSituacaoParcelamento
	 * @throws BancoobException
	 */
	void atualizarParcelamentos(List<Long> idsParcelamento, DateTimeDB dataSituacaoParcela, Integer idSituacaoParcelamento) throws BancoobException;

	/**
	 * Realiza o update em lote nos parcelamentos atualizando a dataSituacaoParcela e o idSituacaoParcelamento
	 * @param listParcelamento
	 * @throws BancoobException
	 */
	void alterarEmLote(List<Parcelamento> listParcelamento) throws BancoobException;

	/**
	 * Realiza o cadastro em lote dos parcelamentos 
	 * @param listParcelamento
	 * @throws BancoobException
	 */
	void incluirEmLote(List<Parcelamento> listParcelamento) throws BancoobException;
}