package br.com.sicoob.sisbr.cca.processamento.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ProcessamentoObjetoReplicacaoNaoEncontradoExceptionTest {

	@Test
	public void deveInstanciarExcecao() {
		String mensagem = "Teste";
		BancoobException bancoobException = new BancoobException(mensagem);
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoNaoEncontradoException(mensagem));
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoNaoEncontradoException(bancoobException));
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoNaoEncontradoException(mensagem, bancoobException));
		Assert.assertNotNull(new ProcessamentoObjetoReplicacaoNaoEncontradoException(mensagem, new Object[]{}, bancoobException));
	}
	
}
