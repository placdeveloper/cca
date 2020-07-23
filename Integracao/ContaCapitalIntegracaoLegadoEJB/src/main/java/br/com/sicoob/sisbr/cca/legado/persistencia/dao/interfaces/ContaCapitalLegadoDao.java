/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelFichaMatriculaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;

/**
 * @author Marco.Nascimento
 */
public interface ContaCapitalLegadoDao extends ContaCapitalIntegracaoLegadoCrudDaoIF<ContaCapitalLegado> {
	
	/**
	 * Obtem ultima matricula
	 * @return
	 * @throws BancoobException
	 */
	Integer obterUltimaMatricula() throws BancoobException;
	
	/**
	 * Listagem com dados necessarios para relatorio de ficha matricula
	 * @param matricula
	 * @param iAplicCoopDif
	 * @return
	 * @throws BancoobException
	 */
	List<DadosRelFichaMatriculaDTO> listarRelFicha(Integer matricula, Integer iAplicCoopDif) throws BancoobException;
	
	/**
	 * Listagem com dados necessarios para relatorio de ficha admissao
	 * @param idPessoa
	 * @param iAplicCoopDif
	 * @return
	 * @throws BancoobException
	 */
	List<DadosRelFichaMatriculaDTO> listarRelFichaAdmissao(Integer idPessoa, Integer iAplicCoopDif) throws BancoobException;
	
	/**
	 * Listagem com dados necessarios para relatorio de extrato
	 * @param RelExtratoLegadoDTO
	 * @return
	 * @throws BancoobException
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
	 * Realiza exclusao da conta capital e historico
	 * @param numMatricula
	 * @param numCoop
	 * @throws BancoobException
	 */
	void excluir(Integer numMatricula, Integer numCoop) throws BancoobException;
	
	/**
	 * Consulta a Conta Capital para uma instituicao informada 
	 * @param numCoop
	 * @param numCliente
	 * @param situacao (1 = ativo, 2 = desligados, null = todos)
	 * @return
	 * @throws BancoobException
	 */
	List<ContaCapitalLegado> obterContaCapitalCooperativaCliente(Integer numCoop, Integer numCliente,Integer situacao) throws BancoobException;	
	
	/**
	 * Obtem nova conta capital (matricula) para a instituição em questão
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
	 * Altera informacoes sobre debito indeterminado em lote
	 * @param lst
	 * @throws BancoobException
	 */
	void alterarDebIndeterminadoEmLote(List<ContaCapitalLegado> lst, Integer tipoAlteracao, BigDecimal percentual) throws BancoobException;
}