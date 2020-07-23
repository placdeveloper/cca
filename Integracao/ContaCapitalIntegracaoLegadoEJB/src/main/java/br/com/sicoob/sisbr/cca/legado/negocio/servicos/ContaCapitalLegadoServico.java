/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelFichaMatriculaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;

/**
 * A Interface ContaCapitalLegadoServico.
 */
public interface ContaCapitalLegadoServico extends ContaCapitalIntegracaoLegadoCrudServico<ContaCapitalLegado> {	
	
	/**
	 * Obter conta capital.
	 *
	 * @param numCliente o valor de num cliente
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<ContaCapitalLegado> obterContaCapital(Integer numCliente) throws BancoobException;
	
	/**
	 * Obter ultima matricula.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer obterUltimaMatricula() throws BancoobException;
	
	/**
	 * Verificar cliente cadastrado.
	 *
	 * @param numCliente o valor de num cliente
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean verificarClienteCadastrado(Integer numCliente) throws BancoobException;
	
	/**
	 * Listar rel ficha admissao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param iAplicCoopDif o valor de i aplic coop dif
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DadosRelFichaMatriculaDTO> listarRelFichaAdmissao(Integer idPessoa, Integer iAplicCoopDif) throws BancoobException;
	
	/**
	 * Listar rel ficha.
	 *
	 * @param matricula o valor de matricula
	 * @param iAplicCoopDif o valor de i aplic coop dif
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DadosRelFichaMatriculaDTO> listarRelFicha(Integer matricula, Integer iAplicCoopDif) throws BancoobException;
	
	/**
	 * Listar rel extrato.
	 *
	 * @param relExtratoDTO o valor de rel extrato dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DadosRelExtratoLegadoDTO> listarRelExtrato(RelExtratoLegadoDTO relExtratoDTO) throws BancoobException;

	/**
	 * Verifica se a conta capital ja esta cadastrada
	 * @param numCoop 
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarContaCapitalCadastrada(Integer numCoop, Integer numMatricula) throws BancoobException;
	
	/**
	 * Exclui conta capital e historico
	 * @param numMatricula
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	void excluir(Integer numMatricula, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consulta a ContaCapital através do numero da Pessoa no Legado e da Cooperativa 
	 * @param numCooperativa
	 * @param numCliente
	 * @param situacao (1 = ativo, 2 = desligados, null = todos)
	 * @return
	 * @throws BancoobException
	 */
	List<ContaCapitalLegado> obterContaCapitalCooperativaCliente(Integer numCooperativa,Integer numCliente, Integer situacao) throws BancoobException;
	
	/**
	 * Obtem nova matricula de conta capital
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Exclui debito indeterminado por matricula
	 * @param numMatriculas
	 * @throws BancoobException
	 */
	void excluirDebIndeterminadoEmLote(List<Integer> numMatriculas) throws BancoobException;
	
	/**
	 * Atualiza informacoes sobre debito indeterminado em lote
	 * @param lst
	 * @throws BancoobException
	 */
	void atualizarDebIndeterminadoEmLote(List<ContaCapitalLegado> lst) throws BancoobException;
	
	/**
	 * Atualiza informacoes sobre debito indeterminado em lote
	 * @param lstAtuaDebIndLegado
	 * @param tipoAlteracao 
	 * @param percentual
	 */
	void alterarDebIndeterminadoEmLote(List<ContaCapitalLegado> lst, Integer tipoAlteracao, BigDecimal percentual) throws BancoobException;
}