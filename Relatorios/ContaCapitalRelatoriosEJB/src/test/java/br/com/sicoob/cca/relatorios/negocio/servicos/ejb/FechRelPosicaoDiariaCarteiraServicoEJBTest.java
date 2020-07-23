package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelPosicaoDiariaCarteiraDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelatoriosServicoLocal;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.FechRelPosicaoDiariaCarteiraDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

/**
* @author Ricardo.Barcante
*/
public class FechRelPosicaoDiariaCarteiraServicoEJBTest {
	@Mock
	private FechRelPosicaoDiariaCarteiraDao fechRelPosicaoDiariaCarteiraDao;
	
	@Mock
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	@Mock
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServicoLocal;
	
	@Mock
	private FechRelatoriosServicoLocal fechRelatoriosServicoLocal;
	
	@InjectMocks
	private FechRelPosicaoDiariaCarteiraServicoEJB fechRelPosicaoDiariaCarteiraServicoEJB;
	
	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGeraRelatorioSaldoPorInstituicao() throws BancoobException {
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setIdInstituicao(29);
		instituicaoIntegracaoDTO.setDescInstituicao("DESCRICAO INSTITUICAO DE TESTE");
		
		Mockito.when(instituicaoIntegracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		Mockito.when(produtoLegadoServicoLocal.obterDataAtualProdutoNumCoop(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new Date());
		Mockito.when(fechRelPosicaoDiariaCarteiraDao.pesquisarPosicaoDiariaCarteira((FechRelPosicaoDiariaCarteiraDTO) Mockito.anyObject())).thenReturn(new FechRelPosicaoDiariaCarteiraDTO());
		fechRelPosicaoDiariaCarteiraServicoEJB.rodar(3008);
	}
	
	@Test(expected = BancoobException.class)
	public void testGeraRelatorioSaldoPorInstituicao_RelatorioVazio() throws BancoobException {
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		instituicaoIntegracaoDTO.setIdInstituicao(29);
		instituicaoIntegracaoDTO.setDescInstituicao("DESCRICAO INSTITUICAO DE TESTE");
		
		Mockito.when(instituicaoIntegracaoServicoLocal.obterInstituicaoIntegracaoPorNumCoop(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		Mockito.when(produtoLegadoServicoLocal.obterDataAtualProdutoNumCoop(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new Date());
		Mockito.when(fechRelPosicaoDiariaCarteiraDao.pesquisarPosicaoDiariaCarteira((FechRelPosicaoDiariaCarteiraDTO) Mockito.anyObject())).thenReturn(null);
		fechRelPosicaoDiariaCarteiraServicoEJB.rodar(3008);
	}

}
