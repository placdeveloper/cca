/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Servico EmailReplicacaoServico
 */
public interface EmailReplicacaoServico extends ContaCapitalReplicacaoServico{

	/**
	 * Dispara e-mail utilizando o servidor do Sicoob
	 * @param destinatarios
	 * @param assunto
	 * @param texto
	 * @throws BancoobException
	 */
	void enviar(String[] destinatarios, String assunto, String texto) throws BancoobException;
	
	/**
	 * Dispara e-mail utilizando o servidor do Sicoob, considerando se eh processo bloqueado para controle de tempo 
	 * @param destinatarios
	 * @param assunto
	 * @param texto
	 * @throws BancoobException
	 */
	void enviar(String[] destinatarios, String assunto, String texto, boolean processoBloqueado) throws BancoobException;
}
