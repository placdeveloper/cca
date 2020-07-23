/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelExtratoRelatorioDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSolDevolucaoCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelTransferenciaCapitalDTO;


/**
 * Servicos de relatorio (conta capital) 
 * @author Marco.Nascimento
 */
public interface RelContaCapitalServico extends ContaCapitalRelatoriosServico {
	
	/**
	 * Gera ficha matricula
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	Object gerarFichaMatricula(Long numMatricula) throws BancoobException;
	
	/**
	 * Gera ficha admissao
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	Object gerarFichaAdmissao(Integer idPessoa) throws BancoobException;

	/**
	 * Gera extrato
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	Object gerarExtrato(RelExtratoRelatorioDTO relExtratoDTO) throws BancoobException;
	
	/**
	 * Gera extrato html
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	String gerarExtratoHTML(RelExtratoRelatorioDTO relExtratoDTO) throws BancoobException;
	
	/**
	 * SOLICITAÇÃO DE DEVOLUÇÃO EVENTUAL DE CAPITAL
	 * @param relSolDevolucaoCapitalDTO
	 * @return
	 * @throws BancoobException
	 */
	Object gerarSolDevolucaoCapital(RelSolDevolucaoCapitalDTO relSolDevolucaoCapitalDTO) throws BancoobException;
	
	/**
	 * RECIBO DE TRANSFERENCIA CAPITAL
	 * @param relTransferenciaCapitalDTO
	 * @return
	 * @throws BancoobException
	 */
	Object gerarReciboTransferenciaCapital(RelTransferenciaCapitalDTO relTransferenciaCapitalDTO) throws BancoobException;
	
		
	
}