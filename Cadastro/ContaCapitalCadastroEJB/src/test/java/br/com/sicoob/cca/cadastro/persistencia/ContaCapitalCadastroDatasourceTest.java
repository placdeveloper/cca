/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia;

import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Test;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalCadastroDatasourceTest {

	/**
	 * O método Test data source.
	 */
	@Test
	public void testDataSource() {
		ContaCapitalCadastroDatasource dataSource = new ContaCapitalCadastroDatasource("jdbc/BancoobDS", new Properties());
		assertNotNull(dataSource);
	}
}
