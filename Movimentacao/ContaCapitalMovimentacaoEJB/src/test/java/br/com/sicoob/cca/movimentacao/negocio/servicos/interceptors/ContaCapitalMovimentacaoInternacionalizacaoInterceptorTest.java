package br.com.sicoob.cca.movimentacao.negocio.servicos.interceptors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class ContaCapitalMovimentacaoInternacionalizacaoInterceptorTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveInstanciarInterceptor() {
		ContaCapitalMovimentacaoInternacionalizacaoInterceptor interceptor = 
				new ContaCapitalMovimentacaoInternacionalizacaoInterceptor();
		Assert.assertNotNull(interceptor);
		Assert.assertNotNull(interceptor.getResourceBundle());
	}
	
}
