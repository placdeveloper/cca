package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ProcessamentoObjetoReplicacaoInvalidoExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoInvalidoException(mensagem));
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoInvalidoException(bancoobException));
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoInvalidoException(mensagem, bancoobException));
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoInvalidoException(mensagem, new Object[]{}, bancoobException));
	}
	
}
