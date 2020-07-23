package br.com.sicoob.cca.replicacao.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ReplicacaoContaCapitalDb2NegocioExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2NegocioException(mensagem));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2NegocioException(mensagem, mensagem));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2NegocioException(bancoobException));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2NegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2NegocioException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2NegocioException(mensagem, mensagem, bancoobException));
	}
	
}
