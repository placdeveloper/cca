package br.com.sicoob.sisbr.cca.comum.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TipoGrauCooperativaDTO;

public class NivelInstituicaoServicoTest extends Mockito {

	private static final Integer UM = 1;

	@InjectMocks
	private NivelInstituicaoServico servico;
	
	@Mock
	private InstituicaoIntegracaoDelegate delegateIntegracao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void obterDefinicoesConfederacaoTest() throws BancoobException {
		TipoGrauCooperativaDTO tipoGrauCooperativaDTO = new TipoGrauCooperativaDTO();
		tipoGrauCooperativaDTO.setCodTipoGrauCoop(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO);
		when(delegateIntegracao.obterTipoGrauCooperativa(anyInt())).thenReturn(tipoGrauCooperativaDTO);
		
		List<ItemListaIntegracaoDTO> list = new ArrayList<ItemListaIntegracaoDTO>();
		list.add(new ItemListaIntegracaoDTO("", ""));
		when(delegateIntegracao.listarCentralInstituicao()).thenReturn(list);
		
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("numCoop", "1");
		RetornoDTO retorno = servico.obterDefinicoes(dto);
		Assert.assertTrue(retorno.getDados().containsKey("listaCentral"));
	}
	
	@Test
	public void obterDefinicoesCentralTest() throws BancoobException {
		TipoGrauCooperativaDTO tipoGrauCooperativaDTO = new TipoGrauCooperativaDTO();
		tipoGrauCooperativaDTO.setCodTipoGrauCoop(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CENTRAL);
		when(delegateIntegracao.obterTipoGrauCooperativa(anyInt())).thenReturn(tipoGrauCooperativaDTO);
		
		CentralCooperativaDTO centralCooperativaDTO = new CentralCooperativaDTO();
		centralCooperativaDTO.setDescCentral("descCentral");
		centralCooperativaDTO.setDescCooperativa("descCooperativa");
		centralCooperativaDTO.setIdInstituicaoCentral(UM);
		centralCooperativaDTO.setIdInstituicaoCooperativa(UM);
		centralCooperativaDTO.setNumCentral(UM.shortValue());
		centralCooperativaDTO.setNumCooperativa(UM.shortValue());
		when(delegateIntegracao.consultarCentralCooperativa(anyInt())).thenReturn(centralCooperativaDTO);
		
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("numCoop", "1");
		dto.getDados().put("bolInstCentral", Boolean.TRUE);
		RetornoDTO retorno = servico.obterDefinicoes(dto);
		Assert.assertTrue(retorno.getDados().containsKey("listaCentral"));
		Assert.assertTrue(retorno.getDados().containsKey("listaSingular"));
	}
	
	@Test
	public void obterDefinicoesSingularTest() throws BancoobException {
		TipoGrauCooperativaDTO tipoGrauCooperativaDTO = new TipoGrauCooperativaDTO();
		tipoGrauCooperativaDTO.setCodTipoGrauCoop(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_SINGULAR);
		when(delegateIntegracao.obterTipoGrauCooperativa(anyInt())).thenReturn(tipoGrauCooperativaDTO);
		
		CentralCooperativaDTO centralCooperativaDTO = new CentralCooperativaDTO();
		centralCooperativaDTO.setDescCentral("descCentral");
		centralCooperativaDTO.setDescCooperativa("descCooperativa");
		centralCooperativaDTO.setIdInstituicaoCentral(UM);
		centralCooperativaDTO.setIdInstituicaoCooperativa(UM);
		centralCooperativaDTO.setNumCentral(UM.shortValue());
		centralCooperativaDTO.setNumCooperativa(UM.shortValue());
		when(delegateIntegracao.consultarCentralCooperativa(anyInt())).thenReturn(centralCooperativaDTO);
		
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("numCoop", "1");
		dto.getDados().put("bolInstCentral", Boolean.TRUE);
		RetornoDTO retorno = servico.obterDefinicoes(dto);
		Assert.assertTrue(retorno.getDados().containsKey("listaCentral"));
		Assert.assertTrue(retorno.getDados().containsKey("listaSingular"));
	}
	
	@Test
	public void listarSingularesCentral300Test() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("idInstituicao", "1");
		dto.getDados().put("bolInstCentral", Boolean.TRUE);
		
		final Integer trezentos = 300;
		when(delegateIntegracao.obterNumeroCooperativa(anyInt())).thenReturn(trezentos);
		
		RetornoDTO retorno = servico.listarSingularesCentral(dto);
		Assert.assertTrue(retorno.getDados().containsKey("listaSingular"));
	}
	
	@Test
	public void listarSingularesCentralTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("idInstituicao", "1");
		dto.getDados().put("bolInstCentral", Boolean.TRUE);
		
		when(delegateIntegracao.obterNumeroCooperativa(anyInt())).thenReturn(UM);
		
		List<ItemListaIntegracaoDTO> itens = new ArrayList<ItemListaIntegracaoDTO>();
		ItemListaIntegracaoDTO item = new ItemListaIntegracaoDTO("", "");
		itens.add(item);
		when(delegateIntegracao.listarSingularesInstituicao(anyInt())).thenReturn(itens);
		
		CentralCooperativaDTO centralCooperativaDTO = new CentralCooperativaDTO();
		centralCooperativaDTO.setDescCentral("descCentral");
		centralCooperativaDTO.setDescCooperativa("descCooperativa");
		centralCooperativaDTO.setIdInstituicaoCentral(UM);
		centralCooperativaDTO.setIdInstituicaoCooperativa(UM);
		centralCooperativaDTO.setNumCentral(UM.shortValue());
		centralCooperativaDTO.setNumCooperativa(UM.shortValue());
		when(delegateIntegracao.consultarCentralCooperativa(anyInt())).thenReturn(centralCooperativaDTO);
		
		RetornoDTO retorno = servico.listarSingularesCentral(dto);
		Assert.assertTrue(retorno.getDados().containsKey("listaSingular"));
	}
	
}
