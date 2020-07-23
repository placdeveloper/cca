package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.FechamentoContaCapitalDao;

public class FechamentoContaCapitalServicoEJBTest extends Mockito {

	private static final Integer UM = 1;
	
	@InjectMocks
	private FechamentoContaCapitalServicoEJB servico;
	
	@Mock
	private EntityManager emCCAEntidades;
	
	@Mock
	private FechamentoContaCapitalDao fechamentoContaCapitalDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void isFechamentoIniciadoTrueTest() throws BancoobException {
		when(fechamentoContaCapitalDao.isFechamentoIniciado(anyInt())).thenReturn(Boolean.TRUE);
		Assert.assertTrue(servico.isFechamentoIniciado(UM));
	}
	
	@Test
	public void isFechamentoIniciadoFalseTest() throws BancoobException {
		when(fechamentoContaCapitalDao.isFechamentoIniciado(anyInt())).thenReturn(Boolean.FALSE);
		Assert.assertFalse(servico.isFechamentoIniciado(UM));
	}
	
	@Test
	public void buscarIdUsuarioTest() throws BancoobException {
		when(fechamentoContaCapitalDao.buscarIdUsuarioFechamento(anyInt())).thenReturn("");
		Assert.assertNotNull(servico.buscarIdUsuarioFechamento(UM));
	}
	
	@Test
	public void isStepFechamentoIniciadoTest() throws BancoobException {
		when(fechamentoContaCapitalDao.isStepFechamentoIniciado(anyObject(), anyInt(), anyInt())).thenReturn(Boolean.TRUE);
		Assert.assertTrue(servico.isStepFechamentoIniciado(new Date(),UM, UM));
	}
	
	@Test
	public void processoConcluidoTest() throws BancoobException {
		when(fechamentoContaCapitalDao.processoConcluido(anyObject(), anyInt(), anyInt())).thenReturn(Boolean.TRUE);
		Assert.assertTrue(servico.processoConcluido(new Date(),UM, UM));
	}
	
	@Test
	public void processoRejeitadoTest() throws BancoobException {
		when(fechamentoContaCapitalDao.processoRejeitado(anyObject(), anyInt(), anyInt(), anyString())).thenReturn(Boolean.TRUE);
		Assert.assertTrue(servico.processoRejeitado(new Date(),UM, UM, ""));
	}
}
