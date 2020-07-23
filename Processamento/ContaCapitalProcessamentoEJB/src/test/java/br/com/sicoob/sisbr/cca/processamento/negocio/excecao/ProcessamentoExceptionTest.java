package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ProcessamentoExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ProcessamentoException(mensagem));
		Assert.assertNotNull(new ProcessamentoException(bancoobException));
		Assert.assertNotNull(new ProcessamentoException(mensagem, bancoobException));
		Assert.assertNotNull(new ProcessamentoException(mensagem, new Object[]{}, bancoobException));
	}
	
}
