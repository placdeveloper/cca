package br.com.sicoob.cca.comum.negocio.delegates;

import org.junit.Assert;
import org.junit.Test;

import br.com.sicoob.cca.comum.negocio.entidades.ContaCapitalComumEntidade;
import br.com.sicoob.cca.comum.negocio.servicos.ContaCapitalComumCrudServico;

public class ContaCapitalComumCrudDelegateTest {

	@Test
	public void deveCriarInstancia() {
		Assert.assertNotNull(new ContaCapitalComumCrudDelegate<ContaCapitalComumEntidade, ContaCapitalComumCrudServico<ContaCapitalComumEntidade>>() {
			@Override
			protected ContaCapitalComumCrudServico<ContaCapitalComumEntidade> localizarServico() {
				return null;
			}
		});
	}
	
}
