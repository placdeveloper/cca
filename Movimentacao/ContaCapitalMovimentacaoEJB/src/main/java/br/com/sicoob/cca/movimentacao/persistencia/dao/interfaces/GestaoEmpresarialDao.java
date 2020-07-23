/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.GestaoEmpresarialDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;

/**
 * A Interface GestaoEmpresarialDao.
 *
 * @author Antonio.Genaro
 */
public interface GestaoEmpresarialDao {
	
	/**
	 * Verifica se ha registros para a instituicao, caso idInstituicao null pesquisa para todas instituicoes.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return {@code true}, se for primeira carga
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean isPrimeiraCarga(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Realiza inclusao de lancamentos IRRF no DB2.
	 *
	 * @param lst o valor de lst
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluir(List<GestaoEmpresarialLegadoDTO> lst) throws BancoobException;
	
	/**
	 * Gera extrato DIRF (Declaração do Imposto de Renda Retido na Fonte).
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param dataInicio o valor de data inicio
	 * @param dataFim o valor de data fim
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<GestaoEmpresarialDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio,Date dataFim) throws BancoobException;	
	
	/**
	 * Pesquisa dataLote mais recente da instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Date pesquisarDataLoteRecente(Integer idInstituicao) throws BancoobException;	
}