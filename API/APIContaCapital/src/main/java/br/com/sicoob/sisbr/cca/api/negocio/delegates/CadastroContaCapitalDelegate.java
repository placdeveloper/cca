/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.api.negocio.dto.CadastroContaCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.CadastroContaCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.locator.APIContaCapitalServiceLocator;

/**
 * Delegate do cadastro conta capital
 */
public class CadastroContaCapitalDelegate extends APIContaCapitalDelegate<CadastroContaCapitalServico> {
	
	/**
	 * Instancia um novo CadastroContaCapitalDelegate.
	 */
	CadastroContaCapitalDelegate() {
		
	}	
	
	/**
	 * Locator CadastroContaCapitalServico
	 */
	@Override
	protected CadastroContaCapitalServico localizarServico() {
		return (CadastroContaCapitalServico) APIContaCapitalServiceLocator.getInstance().localizarCadastroContaCapitalServico();
	}

	/**
	 * {@link CadastroContaCapitalServico#cadastrarContaCapital(CadastroContaCapitalDTO)}
	 * @param cadastroContaCapitalDTO
	 * @return
	 * @throws BancoobException
	 */
	public Integer cadastrarContaCapital(CadastroContaCapitalDTO cadastroContaCapitalDTO) throws BancoobException {
		return getServico().cadastrarContaCapital(cadastroContaCapitalDTO);
	}	
	
}