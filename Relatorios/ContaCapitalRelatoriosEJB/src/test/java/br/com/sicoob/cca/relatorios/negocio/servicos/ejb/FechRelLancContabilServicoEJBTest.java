package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.FechRelLancContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelLancContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

public class FechRelLancContabilServicoEJBTest {

	@Mock
	private FechRelLancContabilLegadoServicoLocal fechRelLancContabilLegadoServicoLocal;
	
	@Mock
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServicoLocal;
	
	@Mock
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	@Mock
	private FechRelatoriosServicoLocal fechRelatoriosServicoLocal;
	
	@InjectMocks
	private FechRelLancContabilServicoEJB ejb;
	
	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGeraLancamentoContabil() throws BancoobException {
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setIdInstituicao(29);
		instituicaoIntegracaoDTO.setDescInstituicao("DESCRICAO INSTITUICAO DE TESTE");
		
		Mockito.when(instituicaoIntegracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		Mockito.when(produtoLegadoServicoLocal.obterDataAtualProdutoNumCoop(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new Date());
		
		ArrayList<FechRelLancContabilDTO> retorno = new ArrayList<FechRelLancContabilDTO>();
		retorno.add(new FechRelLancContabilDTO());
		
		Mockito.when(fechRelLancContabilLegadoServicoLocal.pesquisarLancamentoContabil((FechRelLancContabilDTO) Mockito.anyObject(), Mockito.anyInt())).thenReturn(retorno);
		ejb.rodar(3008);
	}
	
	public void testGeraLancamentoContabilSemRegistro_EsperaException() throws BancoobException {
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setIdInstituicao(29);
		instituicaoIntegracaoDTO.setDescInstituicao("DESCRICAO INSTITUICAO DE TESTE");
		
		Mockito.when(instituicaoIntegracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		
		ejb.rodar(3008);
	}
	
	public void testGeraLancamentoContabilRegistroNulo_EsperaException() throws BancoobException {
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setIdInstituicao(29);
		instituicaoIntegracaoDTO.setDescInstituicao("DESCRICAO INSTITUICAO DE TESTE");
		
		Mockito.when(instituicaoIntegracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		Mockito.when(produtoLegadoServicoLocal.obterDataAtualProdutoNumCoop(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new Date());
		
		ArrayList<FechRelLancContabilDTO> retorno = new ArrayList<FechRelLancContabilDTO>();
		
		Mockito.when(fechRelLancContabilLegadoServicoLocal.pesquisarLancamentoContabil((FechRelLancContabilDTO) Mockito.anyObject(), Mockito.anyInt())).thenReturn(retorno);
		ejb.rodar(3008);
	}

}
