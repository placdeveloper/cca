package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoEstornoRateioDTO;

/**
 * A Interface LancamentoIntegralizacaoExternaServico.
 *
 * @author Antonio.Genaro
 */
public interface LancamentoIntegralizacaoExternaServico extends ContaCapitalMovimentacaoCrudServico<LancamentoContaCapital> {
	
	/**
	 * Integraliza capital de uma requisição extra sisbr 2.0
	 * Grava no DB2(LancamentoContaCapital/HistLancamentoContacapital) e no SQL(CapaLoteCapital/LancamentosCCapital) respectivamente
	 * No DB2 so e realizada a gravacao caso ja exista conta capital replicada 
	 * No SQl grava sempre
	 * Inclui um registro default para a subcrição e o registro com o historico de lancamento informado 			
	 *
	 * @param dto o valor de dto
	 * @return LancamentoContaCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	LancamentoContaCapital incluir(IntegralizacaoCapitalDTO dto) throws BancoobException;
	
	/**
	 * Consultar Integralização de Capital .
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<LancamentoContaCapital> consultarIntegralizacao(IntegralizacaoCapitalDTO dto) throws BancoobException;	
	
	/**
	 * Integraliza capital do Rateio em lote.
	 * @param dtos
	 * @return
	 * @throws BancoobException
	 */
	List<LancamentoContaCapital> incluirRateioEmLote(List<IntegralizacaoCapitalDTO> dtos)throws BancoobException;
	
	/**
	 * Verifica se existe um lancamento pelo numMatricula e descOperacaoExterna
	 * @param numMatricula
	 * @param descOperacaoExterna
	 * @return TRUE se existir, FALSE se nao existir
	 * @throws BancoobException
	 */
	Boolean verificarLancamentoExistente(Integer numCooperativa, Integer numMatricula, String descOperacaoExterna) throws BancoobException;
	
	/**
	 * Consulta as informações para estorno do rateio.
	 * @param lancamentoChave
	 * @return
	 * @throws BancoobException
	 */
	List<LancamentoEstornoRateioDTO> consultarLancamentoEstornoRateio(LancamentoContaCapital lancamentoChave) throws BancoobException;
	
	/**
	 * Realiza o estorno dos lançamentos de rateio.
	 * @param idUsuario
	 * @param dtos
	 * @return
	 * @throws BancoobException
	 */
	List<LancamentoContaCapital> estornarRateio(String idUsuario, List<LancamentoEstornoRateioDTO> dtos) throws BancoobException;
	
	/**
	 * Verifica se ja foi realizado estorno para a chave passada.
	 * @param lancamentoChave
	 * @return
	 * @throws BancoobException
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
	 * Conta se existem lancamentos de integralização (não usa numseq), geralmente utilizado para validações de dupla chamada
	 * @param IntegralizacaoCapitalDTO
	 * @return Boolean
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean consultarLancamentoIntegralizacaoJaRealizada(IntegralizacaoCapitalDTO dto) throws BancoobException;
}