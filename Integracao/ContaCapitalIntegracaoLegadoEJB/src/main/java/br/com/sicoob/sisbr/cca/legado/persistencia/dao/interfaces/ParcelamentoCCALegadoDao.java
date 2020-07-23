/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosFechBaixarParcelasLegadoCCODTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;

/**
 * A Interface ParcelamentoCCALegadoDao.
 */
public interface ParcelamentoCCALegadoDao extends ContaCapitalIntegracaoLegadoCrudDaoIF<ParcelamentoCCALegado> {
	
	/**
	 * Obter proximo num parcelamento.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer obterProximoNumParcelamento(Integer numMatricula, Integer codTipoParcelamento) throws BancoobException;
	
	/**
	 * Verificar parcelamento aberto.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer verificarParcelamentoAberto(Integer numMatricula, Integer codTipoParcelamento) throws BancoobException;
	
	/**
	 * Verifica se ja existem parcelas em aberto pelos canais de atendimento.
	 * @param numeroCooperativa
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarParcelasEmAbertoPelosCanais(Integer numMatricula) throws BancoobException;

	/**
	 * @param numMatricula
	 */
	void excluirParcelasDevolucaoAbertoViaCaixa(Integer numMatricula) throws BancoobException;

	/**
	 * Pesquisar parcelas em aberto via cco.
	 *
	 * @param dataAtualProduto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DadosFechBaixarParcelasLegadoCCODTO> pesquisarFechParcelasEmAbertoViaCCO(DateTimeDB dataAtualProduto) throws BancoobException;

	/**
	 * Realiza a alteração em lote dos parcelamentos legados.
	 * 
	 * @param parcelamentoCCALegado
	 * @return
	 * @throws BancoobException
	 */
	void alterarEmLote(List<ParcelamentoCCALegado> listParcelamentoCCALegado) throws BancoobException;
	
	/**
	 * Pesquisar parcelas débito indeterminado.
	 *
	 * @param dataAtualProduto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DadosFechBaixarParcelasLegadoCCODTO> pesquisarFechParcelasDebIndet(DateTimeDB dataAtualProduto) throws BancoobException;
	
	/**
	 * Realiza a inclusão em lote dos parcelamentos legados.
	 * 
	 * @param parcelamentoCCALegado
	 * @return
	 * @throws BancoobException
	 */
	void incluirEmLote(List<ParcelamentoCCALegado> listParcelamentoCCALegado) throws BancoobException;
	
}
