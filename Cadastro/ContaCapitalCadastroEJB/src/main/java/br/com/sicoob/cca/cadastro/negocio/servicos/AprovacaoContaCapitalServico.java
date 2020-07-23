/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;

/**
 * A Interface AprovacaoContaCapitalServico.
 */
public interface AprovacaoContaCapitalServico extends ContaCapitalCadastroServico {

	/**
	 * Da andamento ao fluxo de aprovacao da conta capital
	 * 
	 * @throws BancoobException
	 */
	void aprovar(ContaCapital contaCapital, Integer idAtividade, Integer idOcorrenciaAtividade, Integer idProcedimento, String nomeProcedimento) throws BancoobException;
}