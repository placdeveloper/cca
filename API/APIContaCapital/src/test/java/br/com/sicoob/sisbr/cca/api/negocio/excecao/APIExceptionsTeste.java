package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class APIExceptionsTeste {

	private String mensagem = "Teste";
	private BancoobException bancoobException = new BancoobException(mensagem);
	
	@Test
	public void deveInstanciarAPIContaCapitalException() {
		Assert.assertNotNull(new APIContaCapitalException(mensagem));
		Assert.assertNotNull(new APIContaCapitalException(bancoobException));
		Assert.assertNotNull(new APIContaCapitalException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarAPIContaCapitalNegocioException() {
		Assert.assertNotNull(new APIContaCapitalNegocioException(mensagem));
		Assert.assertNotNull(new APIContaCapitalNegocioException(bancoobException));
		Assert.assertNotNull(new APIContaCapitalNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new APIContaCapitalNegocioException(mensagem, mensagem));
	}
	
	@Test
	public void deveInstanciarContaCapitalNaoEncontradaNegocioException() {
		Assert.assertNotNull(new ContaCapitalNaoEncontradaNegocioException(mensagem, mensagem));
		Assert.assertNotNull(new ContaCapitalNaoEncontradaNegocioException(mensagem, mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarDebIndeterminadoContaCapitalNegocioException() {
		Assert.assertNotNull(new DebIndeterminadoContaCapitalNegocioException(mensagem));
		Assert.assertNotNull(new DebIndeterminadoContaCapitalNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new DebIndeterminadoContaCapitalNegocioException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegralizacaoCapitalNegocioException() {
		Assert.assertNotNull(new IntegralizacaoCapitalNegocioException(mensagem));
		Assert.assertNotNull(new IntegralizacaoCapitalNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new IntegralizacaoCapitalNegocioException(bancoobException));
	}
	
	@Test
	public void deveInstanciarValorIngressoNaoCadastradoNegocioException() {
		Assert.assertNotNull(new ValorIngressoNaoCadastradoNegocioException(mensagem));
		Assert.assertNotNull(new ValorIngressoNaoCadastradoNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ValorIngressoNaoCadastradoNegocioException(bancoobException));
	}
	
}
