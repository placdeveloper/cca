/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.CadastroContaCapitalRenServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;

/**
 * Delegate do cadastro conta capital (renovacao)
 */
public class CadastroContaCapitalRenDelegate extends ContaCapitalCadastroDelegate<CadastroContaCapitalRenServico> {
	
	/**
	 * Instancia um novo CadastroContaCapitalRenDelegate.
	 */
	CadastroContaCapitalRenDelegate() {
		
	}	
	
	/**
	 * Locator CadastroContaCapitalServico
	 */
	@Override
	protected CadastroContaCapitalRenServico localizarServico() {
		return (CadastroContaCapitalRenServico) ContaCapitalCadastroServiceLocator.getInstance().localizarCadastroContaCapitalRenServico();
	}

	/**
	 * {@link CadastroContaCapitalRenServico#obterNovaContaCapital(Integer)}
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException {
		return getServico().obterNovaContaCapital(idInstituicao);
	}
	
	/**
	 * {@link CadastroContaCapitalRenServico#pesquisar(CadastroContaCapitalRenDTO)}
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO dto) throws BancoobException {
		return getServico().pesquisar(dto);
	}
	
	/**
	 * {@link CadastroContaCapitalRenServico#pesquisarAprovacaoPendente(CadastroContaCapitalRenDTO)}
	 * @param dto 
	 * @return
	 * @throws BancoobException
	 */
	public List<CadastroContaCapitalRenDTO> pesquisarAprovacaoPendente(CadastroContaCapitalRenDTO dto) throws BancoobException {
		return getServico().pesquisarAprovacaoPendente(dto);
	}
	
	/**
	 * {@link CadastroContaCapitalRenServico#incluir(CadastroContaCapitalRenDTO)}
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public CadastroContaCapitalRenDTO incluir(CadastroContaCapitalRenDTO dto) throws BancoobException {
		return getServico().incluir(dto);
	}
	
	/**
	 * {@link CadastroContaCapitalRenServico#alterar(CadastroContaCapitalRenDTO)}
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public CadastroContaCapitalRenDTO alterar(CadastroContaCapitalRenDTO dto) throws BancoobException {
		return getServico().alterar(dto);
	}
	
	/**
	 * {@link CadastroContaCapitalRenServico#excluir(Integer)}
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public void excluir(Integer idContaCapital) throws BancoobException {
		getServico().excluir(idContaCapital);
	}
}