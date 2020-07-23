package br.com.sicoob.sisbr.cca.integracao.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class IntegracaoExceptionsTest {

	private String mensagem = "Teste";
	private BancoobException bancoobException = new BancoobException(mensagem);
	
	@Test
	public void deveInstanciarContaCapitalIntegracaoException() {
		Assert.assertNotNull(new ContaCapitalIntegracaoException(mensagem));
		Assert.assertNotNull(new ContaCapitalIntegracaoException(bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoException(mensagem, new Object[]{}));
	}
	
	@Test
	public void deveInstanciarContaCapitalIntegracaoNegocioException() {
		Assert.assertNotNull(new ContaCapitalIntegracaoNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalIntegracaoNegocioException(bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoNegocioException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoNegocioException(mensagem, new Object[]{}));
	}
	
	@Test
	public void deveInstanciarIntegracaoCapesException() {
		Assert.assertNotNull(new IntegracaoCapesException(bancoobException));
	}

	@Test
	public void deveInstanciarIntegracaoCapesNegocioException() {
		Assert.assertNotNull(new IntegracaoCapesNegocioException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoCaptacaoRemuneradaException() {
		Assert.assertNotNull(new IntegracaoCaptacaoRemuneradaException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoCaptacaoRemuneradaNegocioException() {
		Assert.assertNotNull(new IntegracaoCaptacaoRemuneradaNegocioException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoContabilidadeException() {
		Assert.assertNotNull(new IntegracaoContabilidadeException());
		Assert.assertNotNull(new IntegracaoContabilidadeException(bancoobException));
		Assert.assertNotNull(new IntegracaoContabilidadeException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoContabilidadeNegocioException() {
		Assert.assertNotNull(new IntegracaoContabilidadeNegocioException(mensagem));
		Assert.assertNotNull(new IntegracaoContabilidadeNegocioException(mensagem, mensagem));
		Assert.assertNotNull(new IntegracaoContabilidadeNegocioException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoContaCorrenteException() {
		Assert.assertNotNull(new IntegracaoContaCorrenteException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoContaCorrenteNegocioException() {
		Assert.assertNotNull(new IntegracaoContaCorrenteNegocioException(mensagem));
		Assert.assertNotNull(new IntegracaoContaCorrenteNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new IntegracaoContaCorrenteNegocioException(mensagem, mensagem));
	}
	
	@Test
	public void deveInstanciarIntegracaoCorporativoException() {
		Assert.assertNotNull(new IntegracaoCorporativoException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoCorporativoNegocioException() {
		Assert.assertNotNull(new IntegracaoCorporativoNegocioException(mensagem, mensagem));
		Assert.assertNotNull(new IntegracaoCorporativoNegocioException(mensagem, mensagem, mensagem));
		Assert.assertNotNull(new IntegracaoCorporativoNegocioException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoDocumentoException() {
		Assert.assertNotNull(new IntegracaoDocumentoException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoDocumentoNegocioException() {
		Assert.assertNotNull(new IntegracaoDocumentoNegocioException(mensagem));
	}
	
	@Test
	public void deveInstanciarIntegracaoGenIntException() {
		Assert.assertNotNull(new IntegracaoGenIntException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoGenIntNegocioException() {
		Assert.assertNotNull(new IntegracaoGenIntNegocioException(mensagem));
	}
	
	@Test
	public void deveInstanciarIntegracaoGftException() {
		Assert.assertNotNull(new IntegracaoGftException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoGftNegocioException() {
		Assert.assertNotNull(new IntegracaoGftNegocioException(mensagem));
	}
	
	@Test
	public void deveInstanciarIntegracaoInstituicaoCooperativaException() {
		Assert.assertNotNull(new IntegracaoInstituicaoCooperativaException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoInstituicaoException() {
		Assert.assertNotNull(new IntegracaoInstituicaoException(bancoobException));
	}
	
	@Test
	public void deveInstanciarIntegracaoInstituicaoNegocioException() {
		Assert.assertNotNull(new IntegracaoInstituicaoNegocioException(mensagem));
	}
	
}
