/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.api.negocio.dto.CadastroContaCapitalDTO;

/**
 * A Interface CadastroContaCapitalServico.
 */
public interface CadastroContaCapitalServico extends APIContaCapitalServico {

	/**
	 * Realiza cadastro de conta capital na base da dados legado
	 * @param cadastroContaCapitalDTO
	 * @return
	 * @throws BancoobException
	 */
	Integer cadastrarContaCapital(CadastroContaCapitalDTO cadastroContaCapitalDTO) throws BancoobException;
	

}