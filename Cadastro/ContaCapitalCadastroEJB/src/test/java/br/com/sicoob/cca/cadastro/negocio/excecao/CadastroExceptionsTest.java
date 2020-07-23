package br.com.sicoob.cca.cadastro.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class CadastroExceptionsTest {

	private String mensagem = "Teste";
	private BancoobException bancoobException = new BancoobException(mensagem);
	
	@Test
	public void deveInstanciarCadastroContaCapitalException() {
		Assert.assertNotNull(new CadastroContaCapitalException(mensagem));
		Assert.assertNotNull(new CadastroContaCapitalException(bancoobException));
		Assert.assertNotNull(new CadastroContaCapitalException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarCadastroContaCapitalNegocioException() {
		Assert.assertNotNull(new CadastroContaCapitalNegocioException(mensagem));
		Assert.assertNotNull(new CadastroContaCapitalNegocioException(mensagem, mensagem));
		Assert.assertNotNull(new CadastroContaCapitalNegocioException(mensagem, mensagem, mensagem));
		Assert.assertNotNull(new CadastroContaCapitalNegocioException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarCadastroContaCapitalTransacaoException() {
		Assert.assertNotNull(new CadastroContaCapitalTransacaoException(mensagem));
		Assert.assertNotNull(new CadastroContaCapitalTransacaoException(mensagem, mensagem));
		Assert.assertNotNull(new CadastroContaCapitalTransacaoException(mensagem, mensagem, mensagem));
		Assert.assertNotNull(new CadastroContaCapitalTransacaoException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarContaCapitalCadastradaNegocioException() {
		Assert.assertNotNull(new ContaCapitalCadastradaNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalCadastradaNegocioException(mensagem, mensagem));
		Assert.assertNotNull(new ContaCapitalCadastradaNegocioException(mensagem, mensagem, mensagem));
		Assert.assertNotNull(new ContaCapitalCadastradaNegocioException(mensagem, new Object(){}));
	}
	
	@Test
	public void deveInstanciarContaCapitalCadastroException() {
		Assert.assertNotNull(new ContaCapitalCadastroException(mensagem));
		Assert.assertNotNull(new ContaCapitalCadastroException(bancoobException));
		Assert.assertNotNull(new ContaCapitalCadastroException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalCadastroException(mensagem, new Object(){}, bancoobException));
	}
	
	@Test
	public void deveInstanciarContaCapitalCadastroNegocioException() {
		Assert.assertNotNull(new ContaCapitalCadastroNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalCadastroNegocioException(bancoobException));
		Assert.assertNotNull(new ContaCapitalCadastroNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalCadastroNegocioException(mensagem, new Object(){}, bancoobException));
	}
	
}
