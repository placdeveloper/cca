package br.com.sicoob.sisbr.cca.legado.infraestrutura.mensagens;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class ContaCapitalIntegracaoLegadoResourceBundleTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveRecuperarInstancia() {
		Assert.assertNotNull(ContaCapitalIntegracaoLegadoResourceBundle.getInstance());
	}
	
}
