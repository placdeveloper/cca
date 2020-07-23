package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ProcessamentoPLDContaCapitalExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ProcessamentoPLDContaCapitalException(mensagem));
		Assert.assertNotNull(new ProcessamentoPLDContaCapitalException(bancoobException));
		Assert.assertNotNull(new ProcessamentoPLDContaCapitalException(mensagem, bancoobException));
		Assert.assertNotNull(new ProcessamentoPLDContaCapitalException(mensagem, new Object[]{}, bancoobException));
	}
	
}
