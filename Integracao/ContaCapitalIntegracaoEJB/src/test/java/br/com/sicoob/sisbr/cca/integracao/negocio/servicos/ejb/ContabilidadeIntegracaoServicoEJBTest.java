package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.ctb.api.integracao.negocio.delegates.ConciliacaoContabilIntegracaoDelegate;
import br.com.sicoob.ctb.api.integracao.negocio.delegates.ContabilidadeIntegracaoFabricaDelegates;
import br.com.sicoob.ctb.api.integracao.negocio.vo.DadosConciliacaoContabilVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DadosConciliacaoContabilIntegracaoDTO;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ContabilidadeIntegracaoFabricaDelegates.class, ConciliacaoContabilIntegracaoDelegate.class})
public class ContabilidadeIntegracaoServicoEJBTest {

	private ContabilidadeIntegracaoServicoEJB servico = new ContabilidadeIntegracaoServicoEJB();

	@BeforeClass
	public static void setUpClass() throws BancoobException {
		PowerMockito.mockStatic(ContabilidadeIntegracaoFabricaDelegates.class);
		
		ContabilidadeIntegracaoFabricaDelegates contabilidadeIntegracaoFabricaDelegates = PowerMockito.mock(ContabilidadeIntegracaoFabricaDelegates.class);
		PowerMockito.when(ContabilidadeIntegracaoFabricaDelegates.getInstance()).thenReturn(contabilidadeIntegracaoFabricaDelegates);
		
		ConciliacaoContabilIntegracaoDelegate conciliacaoContabilIntegracaoDelegate = PowerMockito.mock(ConciliacaoContabilIntegracaoDelegate.class);
		PowerMockito.when(contabilidadeIntegracaoFabricaDelegates.criarConciliacaoContabilIntegracaoDelegate()).thenReturn(conciliacaoContabilIntegracaoDelegate);

		PowerMockito.doNothing().when(conciliacaoContabilIntegracaoDelegate).incluirDadosConciliacaoContabil(Matchers.anyListOf(DadosConciliacaoContabilVO.class));
	}

	@Test
	public void incluirConciliacaoContabilTest() throws BancoobException {
		List<DadosConciliacaoContabilIntegracaoDTO> lst = new ArrayList<DadosConciliacaoContabilIntegracaoDTO>();
		DadosConciliacaoContabilIntegracaoDTO dto = new DadosConciliacaoContabilIntegracaoDTO();
		dto.setDataContabilizacao(new DateTimeDB());
		lst.add(dto);
		servico.incluirConciliacaoContabil(lst);
	}

}
