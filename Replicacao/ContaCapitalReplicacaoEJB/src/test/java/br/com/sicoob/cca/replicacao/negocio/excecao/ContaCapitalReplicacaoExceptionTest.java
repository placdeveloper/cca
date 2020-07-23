package br.com.sicoob.cca.replicacao.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalReplicacaoExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalReplicacaoException(mensagem));
		Assert.assertNotNull(new ContaCapitalReplicacaoException(bancoobException));
		Assert.assertNotNull(new ContaCapitalReplicacaoException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalReplicacaoException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalReplicacaoException(mensagem, new Object[]{}));
	}
	
}
