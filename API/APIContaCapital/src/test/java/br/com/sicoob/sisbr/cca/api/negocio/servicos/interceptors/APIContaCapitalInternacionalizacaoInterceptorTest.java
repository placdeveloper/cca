package br.com.sicoob.sisbr.cca.api.negocio.servicos.interceptors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class APIContaCapitalInternacionalizacaoInterceptorTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveInstanciarInterceptor() {
		APIContaCapitalInternacionalizacaoInterceptor interceptor = 
				new APIContaCapitalInternacionalizacaoInterceptor();
		Assert.assertNotNull(interceptor);
		Assert.assertNotNull(interceptor.getResourceBundle());
	}
	
}
