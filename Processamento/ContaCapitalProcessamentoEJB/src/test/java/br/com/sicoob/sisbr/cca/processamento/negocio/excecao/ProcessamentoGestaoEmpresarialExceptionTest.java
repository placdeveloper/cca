package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ProcessamentoGestaoEmpresarialExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ProcessamentoGestaoEmpresarialException(mensagem));
		Assert.assertNotNull(new ProcessamentoGestaoEmpresarialException(bancoobException));
		Assert.assertNotNull(new ProcessamentoGestaoEmpresarialException(mensagem, bancoobException));
		Assert.assertNotNull(new ProcessamentoGestaoEmpresarialException(mensagem, new Object[]{}, bancoobException));
	}
	
}
