package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.constantes.TemplatesRelatorio;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelLancamentosDao;

public class FechRelLancamentosAnaliticoServicoEJBTest {
	
	@Mock
	protected RelLancamentosDao relatorioLancamentosDAO;

	@InjectMocks
	private FechRelLancamentosAnaliticoServicoEJB relatorioLancamentosService;

	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCodigoRelatorio() {

		assertEquals(relatorioLancamentosService.obtemCodigoRelatorio(),
				CodigoRelatorio.RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_ANALITICO);
	}

	@Test
	public void testTemplateRelatorio() {

		assertEquals(relatorioLancamentosService.obtemTemplateRelatorio(),
				TemplatesRelatorio.RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_ANALITICO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testConsultaDadosAnaliticos() throws BancoobException {

		Mockito.when(relatorioLancamentosDAO.obtemLancamentosNaoContabilizadosAnalitico(new Date(), new Date(), Integer.valueOf(29)))
				.thenReturn(Mockito.anyList());

		assertNotNull(relatorioLancamentosService.obtemLancamentos(Mockito.anyInt(), Mockito.any()));
	}

}
