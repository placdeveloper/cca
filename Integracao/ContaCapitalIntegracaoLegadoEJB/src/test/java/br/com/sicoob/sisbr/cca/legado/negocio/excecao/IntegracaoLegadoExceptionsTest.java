package br.com.sicoob.sisbr.cca.legado.negocio.excecao;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class IntegracaoLegadoExceptionsTest {

	private String mensagem = "Teste";
	private BancoobException bancoobException = new BancoobException(mensagem);
	
	@Test
	public void deveInstanciarContaCapitalIntegracaoLegadoException() {
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoException(mensagem));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoException(bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarContaCapitalIntegracaoLegadoNegocioException() {
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoNegocioException(mensagem));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoNegocioException(mensagem, mensagem));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoNegocioException(mensagem, mensagem, mensagem));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoNegocioException(bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoNegocioException(mensagem, new Object[]{}, bancoobException));
		Assert.assertNotNull(new ContaCapitalIntegracaoLegadoNegocioException(mensagem, new Object[]{}));
	}
	
	@Test
	public void deveInstanciarContaCapitalLegadoException() {
		Assert.assertNotNull(new ContaCapitalLegadoException(bancoobException));
		Assert.assertNotNull(new ContaCapitalLegadoException(mensagem, bancoobException));
	}
	
	@Test
	public void deveInstanciarContaCapitalLegadoNegocioException() {
		Assert.assertNotNull(new ContaCapitalLegadoNegocioException(mensagem));
	}
	
	@Test
	public void deveInstanciarGestaoEmpresarialLegadoException() {
		Assert.assertNotNull(new GestaoEmpresarialLegadoException(mensagem));
		Assert.assertNotNull(new GestaoEmpresarialLegadoException(bancoobException));
		Assert.assertNotNull(new GestaoEmpresarialLegadoException(mensagem, bancoobException));
		Assert.assertNotNull(new GestaoEmpresarialLegadoException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new GestaoEmpresarialLegadoException(mensagem, new Object[]{}, bancoobException));
	}
	
	@Test
	public void deveInstanciarPLDContaCapitalLegadoException() {
		Assert.assertNotNull(new PLDContaCapitalLegadoException(mensagem));
		Assert.assertNotNull(new PLDContaCapitalLegadoException(bancoobException));
		Assert.assertNotNull(new PLDContaCapitalLegadoException(mensagem, bancoobException));
		Assert.assertNotNull(new PLDContaCapitalLegadoException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new PLDContaCapitalLegadoException(mensagem, new Object[]{}, bancoobException));
	}
	
	@Test
	public void deveInstanciarReplicacaoAtualizaChaveDb2LegadoException() {
		Assert.assertNotNull(new ReplicacaoAtualizaChaveDb2LegadoException(mensagem));
		Assert.assertNotNull(new ReplicacaoAtualizaChaveDb2LegadoException(bancoobException));
		Assert.assertNotNull(new ReplicacaoAtualizaChaveDb2LegadoException(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoAtualizaChaveDb2LegadoException(mensagem, mensagem));
		Assert.assertNotNull(new ReplicacaoAtualizaChaveDb2LegadoException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoAtualizaChaveDb2LegadoException(mensagem, new Object[]{}, bancoobException));
	}
	
	@Test
	public void deveInstanciarReplicacaoAtualizaListaReplicacaoException() {
		Assert.assertNotNull(new ReplicacaoAtualizaListaReplicacaoException(mensagem));
		Assert.assertNotNull(new ReplicacaoAtualizaListaReplicacaoException(bancoobException));
		Assert.assertNotNull(new ReplicacaoAtualizaListaReplicacaoException(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoAtualizaListaReplicacaoException(mensagem, mensagem));
		Assert.assertNotNull(new ReplicacaoAtualizaListaReplicacaoException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoAtualizaListaReplicacaoException(mensagem, new Object[]{}, bancoobException));
	}
	
	@Test
	public void deveInstanciarReplicacaoConsultaLegadoException() {
		Assert.assertNotNull(new ReplicacaoConsultaLegadoException(mensagem));
		Assert.assertNotNull(new ReplicacaoConsultaLegadoException(bancoobException));
		Assert.assertNotNull(new ReplicacaoConsultaLegadoException(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoConsultaLegadoException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoConsultaLegadoException(mensagem, new Object[]{}, bancoobException));
	}
	
	@Test
	public void deveInstanciarReplicacaoConsultaListaReplicacaoException() {
		Assert.assertNotNull(new ReplicacaoConsultaListaReplicacaoException(mensagem));
		Assert.assertNotNull(new ReplicacaoConsultaListaReplicacaoException(bancoobException));
		Assert.assertNotNull(new ReplicacaoConsultaListaReplicacaoException(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoConsultaListaReplicacaoException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoConsultaListaReplicacaoException(mensagem, new Object[]{}, bancoobException));
	}
	
	@Test
	public void deveInstanciarReplicacaoMonitoracaoException() {
		Assert.assertNotNull(new ReplicacaoMonitoracaoException(mensagem));
		Assert.assertNotNull(new ReplicacaoMonitoracaoException(bancoobException));
		Assert.assertNotNull(new ReplicacaoMonitoracaoException(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoMonitoracaoException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoMonitoracaoException(mensagem, new Object[]{}, bancoobException));
	}
	
	@Test
	public void deveInstanciarReplicacaoMonitoracaoReplicacaoNegocioException() {
		Assert.assertNotNull(new ReplicacaoMonitoracaoReplicacaoNegocioException(mensagem));
		Assert.assertNotNull(new ReplicacaoMonitoracaoReplicacaoNegocioException(bancoobException));
		Assert.assertNotNull(new ReplicacaoMonitoracaoReplicacaoNegocioException(mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoMonitoracaoReplicacaoNegocioException(mensagem, mensagem));
		Assert.assertNotNull(new ReplicacaoMonitoracaoReplicacaoNegocioException(mensagem, mensagem, bancoobException));
		Assert.assertNotNull(new ReplicacaoMonitoracaoReplicacaoNegocioException(mensagem, new Object[]{}, bancoobException));
	}
	
}
