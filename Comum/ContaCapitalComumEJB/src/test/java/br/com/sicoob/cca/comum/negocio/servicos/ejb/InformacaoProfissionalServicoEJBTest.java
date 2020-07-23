package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.InformacaoProfissionalDao;

public class InformacaoProfissionalServicoEJBTest extends Mockito{

	@InjectMocks
	private InformacaoProfissionalServicoEJB servico;
	
	@Mock
	private EntityManager emCCAEntidades;
	
	@Mock
	private InformacaoProfissionalDao dao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void consultarInformacaoProfissionalTest() throws BancoobException {
		ArrayList<InformacaoProfissionalDTO> list = new ArrayList<InformacaoProfissionalDTO>();
		list.add(new InformacaoProfissionalDTO());
		when(dao.consultarInformacaoProfissional(anyInt())).thenReturn(list);
		Assert.assertNotNull(servico.consultarInformacaoProfissional(1));
	}

}
