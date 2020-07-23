/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.servicos;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author Marco.Nascimento
 */
public interface FechamentoContaCapitalServico extends ContaCapitalComumServico {
	
	/**
	 * Verifica se o fechamento do produto conta capital foi iniciado
	 * @return se o produto conta capital iniciou fechamento na data corrente
	 * @param numCoop
	 * @throws BancoobException
	 */
	boolean isFechamentoIniciado(Integer numCoop) throws BancoobException;
	
	String buscarIdUsuarioFechamento(Integer numCoop) throws BancoobException;
	
	/**
	 * Verifica se o step de fechamento foi iniciado
	 * @param dataAtualProduto 
	 * @return se o step de fechamento esta rodando na data corrente
	 * @param idInstituicao
	 * @param idProcesso
	 * @throws BancoobException
	 */
	public boolean isStepFechamentoIniciado(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso) throws BancoobException;

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