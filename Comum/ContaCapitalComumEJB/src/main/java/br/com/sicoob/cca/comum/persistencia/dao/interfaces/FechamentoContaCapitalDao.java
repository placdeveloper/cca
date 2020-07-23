/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao.interfaces;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author Marco.Nascimento
 */
public interface FechamentoContaCapitalDao {

	/**
	 * Verifica se o fechamento do produto conta capital foi iniciado
	 * @param numCoop
	 * @return se o produto conta capital iniciou fechamento na data corrente
	 * @throws BancoobException
	 */
	boolean isFechamentoIniciado(Integer numCoop) throws BancoobException;

	String buscarIdUsuarioFechamento(Integer numCoop) throws BancoobException;

	/**
	 * Verifica se o step de fechamento do produto conta capital foi iniciado
	 * @param dataAtualProduto 
	 * @param idInstituicao
	 * @param idProcesso
	 * @return se o produto conta capital iniciou step de fechamento na data corrente
	 * @throws BancoobException
	 */
	boolean isStepFechamentoIniciado(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso) throws BancoobException;

	/**
	 * Atualiza o controle de processamento como concluido para processamento informado
	 * @param dataAtualProduto
	 * @param idInstituicao
	 * @param idProcesso
	 * @return
	 * @throws BancoobException
	 */
	boolean processoConcluido(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso) throws BancoobException;
	
	/**
	 * Atualiza o controle de processamento como erro para processamento informado
	 * @param dataAtualProduto
	 * @param idInstituicao
	 * @param idProcesso
	 * @param descErroProcesso
	 * @return
	 * @throws BancoobException
	 */
	boolean processoRejeitado(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso, String descErroProcesso) throws BancoobException;

}
