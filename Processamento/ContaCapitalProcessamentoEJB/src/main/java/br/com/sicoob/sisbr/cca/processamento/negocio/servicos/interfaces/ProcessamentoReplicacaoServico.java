/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.processamento.negocio.servicos.ProcessamentoServico;

/**
 * Servico ProcessamentoReplicacaoServico
 */
public interface ProcessamentoReplicacaoServico extends ProcessamentoServico {

	/**
	 * Replica as informacoes das bases de dados SQL para a base unificada DB2
	 * @throws BancoobException
	 * @author Marcos.Balbi
	 */
	void iniciarReplicacao() throws BancoobException;
	
}
