package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import org.junit.Assert;
import org.junit.Test;

import br.com.sicoob.cca.comum.negocio.entidades.ContaCapitalComumEntidade;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumCrudDaoIF;

public class ContaCapitalComumCrudServicoEJBTest {

	@Test
	public void deveCriarInstancia() {
		Assert.assertNotNull(new ContaCapitalComumCrudServicoEJB<ContaCapitalComumEntidade>() {
			@Override
			protected ContaCapitalComumCrudDaoIF<ContaCapitalComumEntidade> getDAO() {
				return null;
			}
		});
	}
	
}
