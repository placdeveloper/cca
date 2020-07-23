package br.com.sicoob.cca.movimentacao.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalImpedimentosNegocioExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalImpedimentosNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalImpedimentosNegocioException(bancoobException));
		Assert.assertNotNull(new ContaCapitalImpedimentosNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalImpedimentosNegocioException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalImpedimentosNegocioException(mensagem, new Object[]{}));
	}
	
}
