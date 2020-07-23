package br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoCCADTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoEstornoRateioDTO;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;

/**
 * A Interface LancamentoContaCapitalDao.
 *
 * @author Antonio.Genaro
 */
public interface LancamentoContaCapitalDao extends ContaCapitalMovimentacaoCrudDaoIF<LancamentoContaCapital> {

	/**
	 * Realiza pesquisa de lancamentos do dia por conta capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @param dataProduto o valor de data produto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<LancamentoContaCapital> pesquisarLancamentosDoDiaPorContaCapital(Integer idContaCapital, Integer idInstituicao, Date dataProduto) throws BancoobException;
	
	/**
	 * Realiza pesquisa de lancamentos por conta capital e instituicao.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<LancamentoContaCapital> pesquisarLancamentosPorContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza pesquisa da quantidade lancamentos por conta capital e instituicao para subscricao
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer pesquisarCountLancamentosPorContaCapitalSubscricao(Integer idContaCapital, Integer idInstituicao) throws BancoobException;	

	/**
	 * Realiza pesquisa simplificada de lancamentos por conta capital e instituicao.
	 * @param idContaCapital
	 * @param idInstituicao
	 * @param dataProduto
	 * @return
	 * @throws BancoobException
	 */
	List<LancamentoCCADTO> pesquisarLancamentosDoDiaPorContaCapitalSimplificado(Integer idContaCapital, Integer idInstituicao, Date dataProduto) throws BancoobException;

	/**
	 * Realiza pesquisa de lancamentos do dia por tipo historico por conta capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @param idTipoHistorico o valor de id tipo historico
	 * @param dataProduto o valor de data produto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<LancamentoContaCapital> pesquisarLancamentosDoDiaTipoHistContaCapital(Integer idContaCapital, Integer idInstituicao, Integer idTipoHistorico, Date dataProduto) throws BancoobException;

	/**
	 * Realiza a inclusao em lote dos lancamentos, <b>inclusive de seus respectivos historicos</b>.
	 * @param lancamentos
	 * @return Os lancamentos inseridos com os ids preenchidos.
	 * @throws BancoobException
	 */
	List<LancamentoContaCapital> incluirEmLote(List<LancamentoContaCapital> lancamentos) throws BancoobException;

	/**
	 * Consulta as informações para estorno do rateio.
	 * @param lancamento
	 * @return
	 * @throws BancoobException
	 */
	List<LancamentoEstornoRateioDTO> consultarLancamentoEstornoRateio(LancamentoContaCapital lancamento) throws BancoobException;

	/**
	 * Verifica se ja foi realizado estorno para a chave passada.
	 * @param lancamentoChave
	 * @return
	 */
	boolean verificarEstornoRealizado(LancamentoContaCapital lancamentoChave) throws BancoobException;
	
	/**
	 * Valida se existem contas capital sem saldo para estorno do rateio.
	 * @param lancamentoChave
	 * @return os numeros das contas sem saldo por grupo (SUBSC, INTEG e DEVOL)
	 * @throws BancoobException
	 */
	Map<String, List<Integer>> validarCCAsSemSaldoParaEstornoRateio(LancamentoContaCapital lancamentoChave) throws BancoobException;

	/**
	 * Atualiza movimento de lançamentos
	 * @param idInstituicao
	 * @param dataProduto
	 * @throws BancoobException
	 */
	void atualizarMovimentoLancamentos(Integer idInstituicao, DateTimeDB dataAtualProduto) throws BancoobException;
	
	/**
	 * Atualiza lançamentos movimento
	 * @param idInstituicao
	 * @param dataProduto
	 * @throws BancoobException
	 */
	void atualizarLancamentosMovimento(Integer idInstituicao, DateTimeDB dataAtualProduto) throws BancoobException;

	/**
	 * Conta se existem lancamentos de integralização (não usa numseq), geralmente utilizado para validações de dupla chamada
	 * @param IntegralizacaoCapitalDTO
	 * @return Boolean
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean consultarLancamentoIntegralizacaoJaRealizada(IntegralizacaoCapitalDTO dto) throws BancoobException;
}