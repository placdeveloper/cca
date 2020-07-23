package br.com.sicoob.cca.frontoffice.persistencia.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ContaCapitalFrontofficeDaoFactoryTest {

	@Test
	public void testGetInstance() {
		assertNotNull(ContaCapitalFrontofficeDaoFactory.getInstance());
	}
	
}
