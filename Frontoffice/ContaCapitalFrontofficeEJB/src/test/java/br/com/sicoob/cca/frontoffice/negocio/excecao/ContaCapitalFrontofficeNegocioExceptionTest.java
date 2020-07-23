package br.com.sicoob.cca.frontoffice.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalFrontofficeNegocioExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalFrontofficeNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalFrontofficeNegocioException(bancoobException));
		Assert.assertNotNull(new ContaCapitalFrontofficeNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalFrontofficeNegocioException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalFrontofficeNegocioException(mensagem, new Object[]{}));
	}
	
}
