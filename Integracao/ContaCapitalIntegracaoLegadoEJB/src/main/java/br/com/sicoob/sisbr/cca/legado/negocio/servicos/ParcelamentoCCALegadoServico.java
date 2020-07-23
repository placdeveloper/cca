/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosFechBaixarParcelasLegadoCCODTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;

/**
 * A Interface ParcelamentoCCALegadoServico.
 */
public interface ParcelamentoCCALegadoServico extends ContaCapitalIntegracaoLegadoCrudServico<ParcelamentoCCALegado> {
	
	/**
	 * Obter proximo num parcelamento.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer obterProximoNumParcelamento(Integer numMatricula, Integer codTipoParcelamento)throws BancoobException;
	
	/**
	 * Verificar parcelamento aberto.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer verificarParcelamentoAberto(Integer numMatricula, Integer codTipoParcelamento)throws BancoobException;

	/**
	 * Obtem as parcelas abertas pelos canais de atendimento
	 * @param numeroCooperativa o numero da cooperativa
	 * @param numMatricula o numMatricula da contaCapitalLegado
	 * @return lista de parcelas em aberto pelos canais
	 * @throws BancoobException
	 */
	List<ParcelamentoCCALegado> obterParcelasEmAbertoPelosCanais(Integer numeroCooperativa, Integer numMatricula) throws BancoobException;
	
	/**
	 * Verifica se ja existem parcelas em aberto pelos canais de atendimento.
	 * @param numeroCooperativa
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarParcelasEmAbertoPelosCanais(Integer numeroCooperativa, Integer numMatricula) throws BancoobException;

	/**
	 * @param numMatricula
	 */
	void excluirParcelasDevolucaoAbertoViaCaixa(Integer numMatricula) throws BancoobException;
	
	/**
	 * Pesquisa parcelas em aberto de uma via cco.
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
	 * Pesquisa parcelas débito indeterminado.
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
