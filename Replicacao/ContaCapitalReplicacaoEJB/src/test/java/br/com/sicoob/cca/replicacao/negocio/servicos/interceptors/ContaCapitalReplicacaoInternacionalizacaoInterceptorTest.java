package br.com.sicoob.cca.replicacao.negocio.servicos.interceptors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class ContaCapitalReplicacaoInternacionalizacaoInterceptorTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveInstanciarInterceptor() {
		ContaCapitalReplicacaoInternacionalizacaoInterceptor interceptor = 
				new ContaCapitalReplicacaoInternacionalizacaoInterceptor();
		Assert.assertNotNull(interceptor);
		Assert.assertNotNull(interceptor.getResourceBundle());
	}
	
}
