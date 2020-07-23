/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.GestaoEmpresarialDTO;

// TODO: Auto-generated Javadoc
/**
 * A Interface GestaoEmpresarialServico.
 *
 * @author Antonio.Genaro
 */
public interface GestaoEmpresarialServico extends ContaCapitalMovimentacaoServico {

	/**
	 * Verifica se ha novos lancamentos de IRRF na data do produto.
	 *
	 * @param numCoop o valor de num coop
	 * @return true se tiver novos lancamentos
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean iniciarProcessamento(Integer numCoop) throws BancoobException;
	
	/**
	 * Verifica se e a primeira carga de IRRF .
	 *
	 * @param numCoop o valor de num coop
	 * @return true se nao houver registros para a instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean isPrimeiraCarga(Integer numCoop) throws BancoobException;
	
	/**
	 * Realiza carga de informacoes (IRRF) da cooperativa .
	 *
	 * @param numCoop o valor de num coop
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void realizarCarga(Integer numCoop) throws BancoobException;
	
	/**
	 * Gera extrato DIRF (Declaração do Imposto de Renda Retido na Fonte).
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param dataInicio o valor de data inicio
	 * @param dataFim o valor de data fim
	 * @return extrato DIRF das instituicoes filtradas por periodo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<GestaoEmpresarialDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio, Date dataFim) throws BancoobException;
}