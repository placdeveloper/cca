package br.com.sicoob.cca.replicacao.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ReplicacaoContaCapitalDb2ExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2Exception(mensagem));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2Exception(mensagem, mensagem));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2Exception(bancoobException));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2Exception(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2Exception(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ReplicacaoContaCapitalDb2Exception(mensagem, mensagem, bancoobException));
	}
	
}
