package br.com.sicoob.cca.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Servico ReplicacaoContaCapitalServico
 */
public interface ControleReplicacaoServico extends ContaCapitalReplicacaoServico {

	void iniciarReplicacao() throws BancoobException;
	
}
