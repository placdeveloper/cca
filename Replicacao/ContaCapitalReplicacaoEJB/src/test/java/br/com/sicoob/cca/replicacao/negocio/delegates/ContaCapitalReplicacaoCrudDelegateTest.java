package br.com.sicoob.cca.replicacao.negocio.delegates;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.sicoob.cca.replicacao.negocio.servicos.ContaCapitalReplicacaoCrudServico;

public class ContaCapitalReplicacaoCrudDelegateTest {

	@Test
	public void deveCriarInstancia() {
		Assert.assertNotNull(new ContaCapitalReplicacaoCrudDelegate<BancoobEntidade, ContaCapitalReplicacaoCrudServico<BancoobEntidade>>() {
			@Override
			protected ContaCapitalReplicacaoCrudServico<BancoobEntidade> localizarServico() {
				return null;
			}
		});
	}
	
}
