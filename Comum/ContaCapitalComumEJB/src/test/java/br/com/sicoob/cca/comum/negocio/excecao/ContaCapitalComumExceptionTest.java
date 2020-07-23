package br.com.sicoob.cca.comum.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalComumExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalComumException(mensagem));
		Assert.assertNotNull(new ContaCapitalComumException(bancoobException));
		Assert.assertNotNull(new ContaCapitalComumException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalComumException(mensagem, new Object[]{}, bancoobException));
	}
	
}
