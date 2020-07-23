package br.com.sicoob.cca.comum.negocio.dto;

import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;

public class InformacaoProfissionalDTOTest {

	@Test
	public void getIdInformacaoProfissionalTest() {
		InformacaoProfissionalDTO dto = new InformacaoProfissionalDTO();
		assertNull(dto.getIdInformacaoProfissional());
	}
	
	@Test
	public void setIdInformacaoProfissionalTest() {
		InformacaoProfissionalDTO dto = new InformacaoProfissionalDTO();
		dto.setIdInformacaoProfissional(1);
		Assert.assertNotNull(dto.getIdInformacaoProfissional());
	}
	
	@Test
	public void getNumMatriculaTest() {
		InformacaoProfissionalDTO dto = new InformacaoProfissionalDTO();
		assertNull(dto.getNumMatricula());
	}
	
	@Test
	public void setNumMatriculaTest() {
		InformacaoProfissionalDTO dto = new InformacaoProfissionalDTO();
		dto.setNumMatricula("teste");
		Assert.assertEquals("teste", dto.getNumMatricula());
	}

}
