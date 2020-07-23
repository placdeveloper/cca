package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.PesquisaContaCapitalDao;

public class PesquisaContaCapitalServicoEJBTest extends Mockito {

	@InjectMocks
	private PesquisaContaCapitalServicoEJB servico;
	
	@Mock
	private EntityManager emCCAEntidades;
	
	@Mock
	private PesquisaContaCapitalDao pesquisaContaCapitalDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void pesquisarTest() throws BancoobException {
		List<PesquisaContaCapitalDTO> list = new ArrayList<PesquisaContaCapitalDTO>();
		list.add(new PesquisaContaCapitalDTO());
		when(pesquisaContaCapitalDao.pesquisar(any(PesquisaContaCapitalDTO.class))).thenReturn(list);
		List<PesquisaContaCapitalDTO> result = servico.pesquisar(new PesquisaContaCapitalDTO());
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
	}
	
}
