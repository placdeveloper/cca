/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.tipos.DateTime;

/**
 * A Interface TrabalhaLegadoServico.
 */
public interface TrabalhaLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Obter dados trabalha.
	 *
	 * @param numPessoa o valor de num pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<TrabalhaLegadoDTO> obterDadosTrabalha(Integer numPessoa) throws BancoobException;
	
	/**
	 * Obter dados trabalha por matricula.
	 *
	 * @param descMatriculaFunc o valor de desc matricula func
	 * @return TrabalhaLegadoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	TrabalhaLegadoDTO obterDadosTrabalhaPorMatricula(String descMatriculaFunc) throws BancoobException;
	
	/**
	 * Verifica se deb ind folha cliente.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param numCliente o valor de num cliente
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean verificaSeDebIndFolhaCliente(Integer numMatricula, Integer numCliente) throws BancoobException;
	
	/**
	 * Verificar parcela via folha cliente.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param numCliente o valor de num cliente
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean verificarParcelaViaFolhaCliente(Integer numMatricula, Integer numCliente) throws BancoobException;
	
	/**
	 * Verificar se prep remessa.
	 *
	 * @param strUIDTrabalha o valor de str uid trabalha
	 * @param dataReferencia o valor de data referencia
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean verificarSePrepRemessa(String strUIDTrabalha, DateTime dataReferencia) throws BancoobException;		
	
	/**
	 * Verificar se matricula trabalha valida.
	 *
	 * @param strUIDTrabalha o valor de str uid trabalha
	 * @param numCliente o valor de num cliente
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean verificarSeMatriculaTrabalhaValida(String strUIDTrabalha, Integer numCliente) throws BancoobException;	
}
