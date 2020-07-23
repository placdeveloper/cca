package br.com.sicoob.cca.movimentacao.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalMovimentacaoExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ContaCapitalMovimentacaoException(mensagem));
		Assert.assertNotNull(new ContaCapitalMovimentacaoException(bancoobException));
		Assert.assertNotNull(new ContaCapitalMovimentacaoException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalMovimentacaoException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalMovimentacaoException(mensagem, new Object[]{}));
	}
	
}
