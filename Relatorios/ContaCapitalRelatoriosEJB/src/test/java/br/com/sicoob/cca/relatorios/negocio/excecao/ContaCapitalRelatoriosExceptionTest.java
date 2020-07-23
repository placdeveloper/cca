package br.com.sicoob.cca.relatorios.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalRelatoriosExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalRelatoriosException(mensagem));
		Assert.assertNotNull(new ContaCapitalRelatoriosException(bancoobException));
		Assert.assertNotNull(new ContaCapitalRelatoriosException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalRelatoriosException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalRelatoriosException(mensagem, new Object[]{}));
	}
	
}
