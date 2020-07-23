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
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao;

public class ViewInstituicaoServicoEJBTest extends Mockito{

	@InjectMocks
	private ViewInstituicaoServicoEJB servico;
	
	@Mock
	private EntityManager emCCAEntidades;
	
	@Mock
	private ViewInstituicaoDao viewInstituicaoDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void consultarCooperativasAtivasTest() throws BancoobException {
		when(viewInstituicaoDao.consultarCooperativasAtivas(anyInt())).thenReturn(new ArrayList<InstituicaoCooperativaSCIDTO>());
		Assert.assertNotNull(servico.consultarCooperativasAtivas(1));
	}
	
	@Test
	public void listarCentraisTest() throws BancoobException {
		when(viewInstituicaoDao.listarCentrais()).thenReturn(new ArrayList<InstituicaoCooperativaSCIDTO>());
		Assert.assertNotNull(servico.listarCentrais());
	}
	
	@Test
	public void consultarPacPorCooperativaTest() throws BancoobException {
		when(viewInstituicaoDao.consultarPacPorCooperativa(anyInt())).thenReturn(new ArrayList<InstituicaoCooperativaSCIDTO>());
		Assert.assertNotNull(servico.consultarPacPorCooperativa(1));
	}
	

}
