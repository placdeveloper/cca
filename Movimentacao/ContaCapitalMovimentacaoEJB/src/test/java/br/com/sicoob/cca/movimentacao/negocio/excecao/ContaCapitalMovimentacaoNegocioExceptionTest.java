package br.com.sicoob.cca.movimentacao.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalMovimentacaoNegocioExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalMovimentacaoNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalMovimentacaoNegocioException(bancoobException));
		Assert.assertNotNull(new ContaCapitalMovimentacaoNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalMovimentacaoNegocioException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalMovimentacaoNegocioException(mensagem, new Object[]{}));
	}
	
}
