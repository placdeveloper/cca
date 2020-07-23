/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;

/**
 * @author marco.nascimento
 */
public interface PropostaSubscricaoServico extends ContaCapitalCadastroCrudServico<PropostaSubscricao> {
	 									   
	
	/**
	 * Incluir proposta utilizando usuario fora do Sisbr 2.0 
	 * @param proposta
	 * @return
	 * @throws BancoobException
	 */
	PropostaSubscricao incluirExterno(PropostaSubscricao proposta) throws BancoobException;
	
	
}