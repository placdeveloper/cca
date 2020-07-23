/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.SaldoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.SaldoContaCorrenteIntegracaoRetDTO;

/**
 * A Interface ContaCorrenteIntegracaoServico.
 */
public interface ContaCorrenteIntegracaoServico extends ContaCapitalIntegracaoServico {

	/**
	 * Obtem saldo da conta corrente
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	SaldoContaCorrenteIntegracaoRetDTO obterSaldoContaCorrente(SaldoContaCorrenteIntegracaoDTO dto) throws BancoobException;
	
	/**
	 * Persistência nas Tabelas do Conta Corrente
	 * A Chamada e feita lá no CCAIntegracao
	 * @param contaCapitalLegado
	 * @param dtoCadastro
	 * @throws BancoobException
	 */
	LancamentoContaCorrenteIntegracaoRetDTO gravarLancamentosIntegracao(LancamentoContaCorrenteIntegracaoDTO dto) throws BancoobException;
	
	/**
	 * Verifica se a conta corrente informada é ativa, desde que existente
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarContaCorrenteAtiva(ContaCorrenteIntegracaoDTO dto) throws BancoobException;
			
	/**
	 * Verifica se a conta corrente informada é do cliente informado
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarContaCorrentePorIdPessoa(ContaCorrenteIntegracaoDTO dto) throws BancoobException;
	
	/**
	 * Verifica se a pessoa é primeira ou segundo titular da conta corrente
	 * @param idPessoa
	 * @param idInstituicao
	 * @param numContaCorrente
	 * @return 
	 */
	Boolean validarTitularidade(Integer idPessoa, Integer idInstituicao, Long numContaCorrente) throws BancoobException;
	
	/**
	 * Contas corrente de um associado
	 * @param dto
	 * @return 
	 */
	List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrentePorNumeroCliente(ContaCorrenteIntegracaoDTO dto) throws BancoobException;
	
	/**
	 * Contas corrente ATIVAS de um associado
	 * @param dto
	 * @return 
	 */
	List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrenteAtivaPorNumeroCliente(ContaCorrenteIntegracaoDTO dto) throws BancoobException;
	
	/**
	 * Consulta o saldo em conta corrente na data corrente
	 * @param idInstituicao
	 * @param idUsuario
	 * @param numContaCorrente
	 * @return
	 * @throws BancoobException
	 */	
	Boolean isValorIntegralizacaoMaiorSalcoCco(BigDecimal valor, Long idPessoa, Integer idInstituicao, String idUsuario, Long numContaCorrente) throws BancoobException;
	

	/**
	 * Retorna true se tiver a contacorrente estiver encerrada ou bloqueada
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarContaCorrenteBloqueadaEncerrada(ContaCorrenteIntegracaoDTO dto)throws BancoobException;

	/**
	 * Verifica se existe conta corrente impeditiva de desligamento.
	 * @param ccoDTO
	 * @return
	 */
	Boolean verificarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO ccoDTO) throws BancoobException;
	
	/**
	 * Consulta as contas correntes impeditivas de desligamento.
	 * @param ccoDTO
	 * @return
	 * @throws BancoobException
	 */
	List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO ccoDTO) throws BancoobException;
	
	/**
	 * Persistência nas Tabelas do Conta Corrente em lote
	 * A Chamada e feita lá no CCAIntegracao
	 * @param listLancamentoContaCorrenteIntegracaoLoteDTO
	 * @param numCooperativa
	 * @throws BancoobException
	 */	
	List<LancamentoContaCorrenteIntegracaoLoteRetDTO> gravarLancamentosIntegracaoLote(List<LancamentoContaCorrenteIntegracaoLoteDTO> listLancamentoContaCorrenteIntegracaoLoteDTO, Integer numCooperativa) throws BancoobException;
	
}