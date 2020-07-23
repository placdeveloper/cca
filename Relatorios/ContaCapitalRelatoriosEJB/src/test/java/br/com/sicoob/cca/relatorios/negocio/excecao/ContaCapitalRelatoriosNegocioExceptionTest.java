package br.com.sicoob.cca.relatorios.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalRelatoriosNegocioExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalRelatoriosNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalRelatoriosNegocioException(bancoobException));
		Assert.assertNotNull(new ContaCapitalRelatoriosNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalRelatoriosNegocioException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalRelatoriosNegocioException(mensagem, new Object[]{}));
	}
	
}
