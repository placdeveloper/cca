/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.cca.comum.negocio.servicos.PesquisaContaCapitalServico;
import br.com.sicoob.cca.comum.negocio.servicos.locator.ContaCapitalComumServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class PesquisaContaCapitalDelegate extends ContaCapitalComumDelegate<PesquisaContaCapitalServico> {
	
	/**
	 * Utilizar a fabrica para criar o delegate
	 * @see ContaCapitalComumFabricaDelegates#criarFechamentoContaCapitalDelegate()
	 */
	PesquisaContaCapitalDelegate() {
		
	}

	/**
	 * {@link FechamentoContaCapitalServico}
	 */
	@Override
	protected PesquisaContaCapitalServico localizarServico() {
		return ContaCapitalComumServiceLocator.getInstance().localizarPesquisaContaCapitalServico();
	}
	
	/**
	 * {@link PesquisaContaCapitalServico#pesquisar(PesquisaContaCapitalDTO)}
	 */
	public List<PesquisaContaCapitalDTO> pesquisar(PesquisaContaCapitalDTO filtro) throws BancoobException {
		return getServico().pesquisar(filtro);
	}
}