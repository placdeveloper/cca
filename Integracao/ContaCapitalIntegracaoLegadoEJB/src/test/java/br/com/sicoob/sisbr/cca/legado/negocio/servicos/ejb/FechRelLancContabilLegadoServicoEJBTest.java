package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.FechRelLancContabilDTO;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechRelContabilLegadoDao;

public class FechRelLancContabilLegadoServicoEJBTest {

	@Mock
	private EntityManager em;
	
	@Mock
	private FechRelContabilLegadoDao fechRelLancContabilLegadoDao;

	@InjectMocks
	private FechRelLancContabilLegadoServicoEJB ejb;
	
	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPesquisarLancamentoContabil() throws BancoobException {
		ArrayList<FechRelLancContabilDTO> retorno = new ArrayList<FechRelLancContabilDTO>();
		retorno.add(new FechRelLancContabilDTO());
		
		Mockito.when(fechRelLancContabilLegadoDao.pesquisarLancamentoContabil((FechRelLancContabilDTO) Mockito.anyObject(), Mockito.anyInt())).thenReturn(retorno);
		List<FechRelLancContabilDTO> resposta = ejb.pesquisarLancamentoContabil(new FechRelLancContabilDTO(), 3008);
		assertNotNull(resposta);
	}

}
