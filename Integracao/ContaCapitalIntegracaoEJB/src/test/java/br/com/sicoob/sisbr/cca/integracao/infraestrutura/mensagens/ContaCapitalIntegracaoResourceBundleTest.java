/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.infraestrutura.mensagens;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalIntegracaoResourceBundleTest {
	
	@BeforeClass
	public static void setUpClass() {
		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
	}
	
	/** A constante SOURCE_PATH. */
	private static final String SOURCE_PATH = "src"+ File.separator +"main"+ File.separator +"mensagens";
	
	/** A constante FILE_PATH. */
	private static final String FILE_PATH = SOURCE_PATH + File.separator + ContaCapitalIntegracaoResourceBundle.INTEGRACAO_SICOOB_PROPERTIES;
	
	/** A constante MSG_FILE_NOT_FOUND. */
	private static final String MSG_FILE_NOT_FOUND = "Arquivo de propriedades nao encontrado: ";
	
	/**
	 * O método Test file exist.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testFileExist() throws Exception {
		assertTrue(MSG_FILE_NOT_FOUND + FILE_PATH, new File(FILE_PATH).exists());
	}
	
	@Test
	public void deveRecuperarInstancia() {
		Assert.assertNotNull(ContaCapitalIntegracaoResourceBundle.getInstance());
	}
	
}