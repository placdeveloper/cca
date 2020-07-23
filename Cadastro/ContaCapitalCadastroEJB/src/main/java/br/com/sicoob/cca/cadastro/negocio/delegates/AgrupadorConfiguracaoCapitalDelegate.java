/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.sicoob.cca.cadastro.negocio.servicos.AgrupadorConfiguracaoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.AgrupadorConfiguracaoCapital;

/**
 * @author Marco.Nascimento
 */
public class AgrupadorConfiguracaoCapitalDelegate extends ContaCapitalCadastroCrudDelegate<AgrupadorConfiguracaoCapital, AgrupadorConfiguracaoCapitalServico> {

	/**
	 * Construtor
	 */
	AgrupadorConfiguracaoCapitalDelegate() {
		
	}

	/**
	 * Locator AgrupadorConfiguracaoCapitalServico
	 */
	@Override
	protected AgrupadorConfiguracaoCapitalServico localizarServico() {
		return (AgrupadorConfiguracaoCapitalServico) ContaCapitalCadastroServiceLocator.getInstance().localizarAgrupadorConfiguracaoCapitalServico();
	}
}