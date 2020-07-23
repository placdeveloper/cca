package br.com.sicoob.sisbr.cca.processamento.infraestrutura.mensagens;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class ProcessamentoResourceBundleTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveRecuperarInstancia() {
		Assert.assertNotNull(ProcessamentoResourceBundle.getInstance());
	}
	
}
