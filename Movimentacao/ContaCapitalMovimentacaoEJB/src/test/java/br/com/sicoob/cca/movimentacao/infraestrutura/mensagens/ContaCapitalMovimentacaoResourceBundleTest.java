package br.com.sicoob.cca.movimentacao.infraestrutura.mensagens;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

public class ContaCapitalMovimentacaoResourceBundleTest {

	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	@Test
	public void deveRecuperarInstancia() {
		Assert.assertNotNull(ContaCapitalMovimentacaoResourceBundle.getInstance());
	}
	
}
