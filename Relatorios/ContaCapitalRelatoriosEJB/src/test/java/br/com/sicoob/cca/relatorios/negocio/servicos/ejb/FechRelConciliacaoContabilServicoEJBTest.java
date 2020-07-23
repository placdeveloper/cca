package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelConciliacaoContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelConciliacaoContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

public class FechRelConciliacaoContabilServicoEJBTest {

	@Mock
	protected FechRelConciliacaoContabilLegadoServicoLocal relatorioConciliacaoService;

	@Mock
	private ProdutoLegadoServicoLocal produtoLegadoService;

	@Mock
	private InstituicaoIntegracaoServicoLocal integracaoService;

	@Mock
	private FechRelatoriosServicoLocal relatorioService;

	@InjectMocks
	private FechRelConciliacaoContabilServicoEJB relatorioLancamentosService;

	private InstituicaoIntegracaoDTO instituicaoIntegracaoDTO;

	@Before
	public void inicializaMocks() {

		MockitoAnnotations.initMocks(this);

		instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setIdInstituicao(29);
		instituicaoIntegracaoDTO.setDescInstituicao("SICOOB CENTRO-SERRANO");
	}

	@Test
	public void testEmissaoRelatorio() throws BancoobException {

		List<RelConciliacaoContabilDTO> retorno = Mockito.spy(new ArrayList<>());
		retorno.add(new RelConciliacaoContabilDTO());

		geraRelatorio(retorno);
	}

	@Test
	public void testEmissaoRelatorioSemDados() throws BancoobException {

		List<RelConciliacaoContabilDTO> retorno = Mockito.spy(new ArrayList<>());
		geraRelatorio(retorno);

	}

	private void geraRelatorio(List<RelConciliacaoContabilDTO> retorno) throws BancoobException {
		
		Mockito.when(integracaoService.obterInstituicaoIntegracaoPorNumCoop(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		Mockito.when(produtoLegadoService.obterDataAtualProdutoNumCoop(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new Date());

		Mockito.when(relatorioConciliacaoService.obtemDadosConcialicaoContabil(Mockito.any(), Mockito.any(), Mockito.anyInt()))
				.thenReturn(retorno);
		relatorioLancamentosService.rodar(3008);
	}

}
