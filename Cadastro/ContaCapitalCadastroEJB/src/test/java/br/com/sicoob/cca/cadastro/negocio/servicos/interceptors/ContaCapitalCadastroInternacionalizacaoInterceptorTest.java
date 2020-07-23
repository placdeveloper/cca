package br.com.sicoob.cca.cadastro.negocio.servicos.interceptors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class ContaCapitalCadastroInternacionalizacaoInterceptorTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveInstanciarInterceptor() {
		ContaCapitalCadastroInternacionalizacaoInterceptor interceptor = 
				new ContaCapitalCadastroInternacionalizacaoInterceptor();
		Assert.assertNotNull(interceptor);
		Assert.assertNotNull(interceptor.getResourceBundle());
	}
	
}
