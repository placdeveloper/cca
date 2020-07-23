package br.com.sicoob.cca.cadastro.negocio.delegates;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ContaCapitalCadastroFabricaDelegatesTest {

	@Test
	public void testGetInstance() {
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance());
	}
	
	@Test
	public void testCriarConfiguracaoCapitalDelegate(){
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance().criarConfiguracaoCapitalDelegate());
	}
	
	@Test
	public void testCriarValorConfiguracaoCapitalDelegate(){
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate());
	}
	
	@Test
	public void testCriarSituacaoCadastroPropostaDelegate(){
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance().criarSituacaoCadastroPropostaDelegate());
	}
	
	@Test
	public void testCriarPropostaSubscricaoDelegate(){
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance().criarPropostaSubscricaoDelegate());
	}
	
	@Test
	public void testCriarContaCapitalDelegate(){
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate());
	}
	
	@Test
	public void testCriarDocumentoCapitalDelegate(){
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance().criarDocumentoCapitalDelegate());
	}
	
	@Test
	public void testCriarAgrupadorConfiguracaoCapitalDelegate(){
		assertNotNull(ContaCapitalCadastroFabricaDelegates.getInstance().criarAgrupadorConfiguracaoCapitalDelegate());
	}
	
}
