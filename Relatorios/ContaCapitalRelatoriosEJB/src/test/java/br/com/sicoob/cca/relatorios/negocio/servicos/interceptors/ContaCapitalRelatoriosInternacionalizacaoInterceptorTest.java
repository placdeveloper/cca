package br.com.sicoob.cca.relatorios.negocio.servicos.interceptors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class ContaCapitalRelatoriosInternacionalizacaoInterceptorTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveInstanciarInterceptor() {
		ContaCapitalRelatoriosInternacionalizacaoInterceptor interceptor = 
				new ContaCapitalRelatoriosInternacionalizacaoInterceptor();
		Assert.assertNotNull(interceptor);
		Assert.assertNotNull(interceptor.getResourceBundle());
	}
	
}
