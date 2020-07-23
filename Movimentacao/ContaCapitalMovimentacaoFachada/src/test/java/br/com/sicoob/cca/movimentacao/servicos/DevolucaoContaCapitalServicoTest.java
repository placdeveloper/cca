package br.com.sicoob.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.BloqueioContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.DevolucaoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ParcelamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.DevolucaoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CaptacaoRemuneradaIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.movimentacao.servicos.DevolucaoContaCapitalServico;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DevolucaoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.tipos.DateTime;

public class DevolucaoContaCapitalServicoTest extends AbstractMovimentacaoContaCapitalTest {

	@InjectMocks
	private DevolucaoContaCapitalServico devolucaoContaCapitalServico;
	
	@Mock
	private ProdutoLegadoDelegate prodLegadoDelegate;
	
	@Mock
	private ContaCapitalDelegate contaCapitalDelegate;	
	
	@Mock
	private ValorCotaDelegate valorCotaDelegate;	
	
	@Mock
	private GenIntIntegracaoDelegate genIntIntegracaoDelegate;		
	
	@Mock
	private DevolucaoContaCapitalDelegate devolucaoContaCapitalDelegate;		
	
	@Mock
	private CaptacaoRemuneradaIntegracaoDelegate captacaoRemuneradaIntegracaoDelegate;

	@Mock
	private CadastroContaCapitalRenDelegate cadastroDelegate;
	
	@Mock
	private ValorConfiguracaoCapitalDelegate valorConfiguracaoCapitalDelegate;

	@Mock
	private BloqueioContaCapitalDelegate bloqueioContaCapitalDelegate;
	
	@Mock
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate;
	
	@Mock
	private ParcelamentoContaCapitalDelegate parcelamentoDelegate;
	
	@Test
	public void obterDefinicoesTest() throws BancoobException {
		when(prodLegadoDelegate.obterDataAtualProdutoCCALogado()).thenReturn(new DateTime());
		when(valorConfiguracaoCapitalDelegate.obterValorConfiguracao(anyInt(), anyInt())).thenReturn(new ValorConfiguracaoCapital());
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.obterDefinicoes(new RequisicaoReqDTO());
		Assert.assertTrue(retornoDTO.getDados().containsKey("dataAtualProduto"));
	}
	
	@Test
	public void obterDadosDevolucaoTest() throws BancoobException {
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idContaCapital", "1");
		
		ContaCapital contaCapital = new ContaCapital();
		SituacaoContaCapital situacaoContaCapital = new SituacaoContaCapital();
		situacaoContaCapital.setId(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO.shortValue());
		contaCapital.setSituacaoContaCapital(situacaoContaCapital);
		contaCapital.setValorInteg(BigDecimal.ONE);
		when(contaCapitalDelegate.obter(anyInt())).thenReturn(contaCapital);
		
		when(valorCotaDelegate.obterValorMinimoSubscricao(anyInt(), anyInt())).thenReturn(BigDecimal.ONE);
		when(valorCotaDelegate.obterValorCota(anyInt())).thenReturn(BigDecimal.TEN);
		when(valorCotaDelegate.obterQtdMaxParcela(anyInt())).thenReturn(1);
		
		List<ContaCorrenteIntegracaoRetDTO> lstCco = new ArrayList<ContaCorrenteIntegracaoRetDTO>();
		ContaCorrenteIntegracaoRetDTO cco = new ContaCorrenteIntegracaoRetDTO();
		cco.setNumeroContaCorrente(1L);
		lstCco.add(cco);
		when(contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(any(ContaCorrenteIntegracaoDTO.class))).thenReturn(lstCco);
		
		ValorConfiguracaoCapital valorConfiguracao = new ValorConfiguracaoCapital();
		valorConfiguracao.setValorConfiguracao("0");
		when(valorConfiguracaoCapitalDelegate.obterValorConfiguracao(anyInt(), anyInt())).thenReturn(valorConfiguracao);
		
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.obterDadosDevolucao(reqDTO);
		Assert.assertFalse(retornoDTO.getDados().isEmpty());
	}
	
	@Test
	public void obterDadosDevolucaoInativoTest() throws BancoobException {
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idContaCapital", "1");
		
		when(parcelamentoDelegate.pesquisarValorParcelasDevolucaoEmAberto(anyInt())).thenReturn(BigDecimal.ZERO);
		
		ContaCapital contaCapital = new ContaCapital();
		SituacaoContaCapital situacaoContaCapital = new SituacaoContaCapital();
		situacaoContaCapital.setId(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_DEMITIDO.shortValue());
		contaCapital.setSituacaoContaCapital(situacaoContaCapital);
		contaCapital.setValorDevol(BigDecimal.ONE);
		when(contaCapitalDelegate.obter(anyInt())).thenReturn(contaCapital);
		
		when(valorCotaDelegate.obterValorMinimoSubscricao(anyInt(), anyInt())).thenReturn(BigDecimal.ONE);
		when(valorCotaDelegate.obterValorCota(anyInt())).thenReturn(BigDecimal.TEN);
		when(valorCotaDelegate.obterQtdMaxParcela(anyInt())).thenReturn(1);
		
		List<ContaCorrenteIntegracaoRetDTO> lstCco = new ArrayList<ContaCorrenteIntegracaoRetDTO>();
		ContaCorrenteIntegracaoRetDTO cco = new ContaCorrenteIntegracaoRetDTO();
		cco.setNumeroContaCorrente(1L);
		lstCco.add(cco);
		when(contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(any(ContaCorrenteIntegracaoDTO.class))).thenReturn(lstCco);
		
		ValorConfiguracaoCapital valorConfiguracao = new ValorConfiguracaoCapital();
		valorConfiguracao.setValorConfiguracao("0");
		when(valorConfiguracaoCapitalDelegate.obterValorConfiguracao(anyInt(), anyInt())).thenReturn(valorConfiguracao);
		
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.obterDadosDevolucao(reqDTO);
		Assert.assertFalse(retornoDTO.getDados().isEmpty());
	}
	
	@Test
	public void criarComboTipoIntegralizacaoAtivoTest() throws BancoobException {
		List<ItemListaVO> itens = devolucaoContaCapitalServico
				.criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO, false);
		Assert.assertFalse(itens.isEmpty());
	}
	
	@Test
	public void criarComboTipoIntegralizacaoOutrosTest() throws BancoobException {
		List<ItemListaVO> itens = devolucaoContaCapitalServico
				.criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_DEMITIDO, false);
		Assert.assertFalse(itens.isEmpty());
	}
	
	@Test
	public void criarComboMotivoDevolucaoAtivoTest() throws BancoobException {
		List<ItemListaVO> itens = devolucaoContaCapitalServico
				.criarComboMotivoDevolucao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_ATIVO);
		Assert.assertFalse(itens.isEmpty());
	}
	
	@Test
	public void criarComboMotivoDevolucaoOutrosTest() throws BancoobException {
		List<ItemListaVO> itens = devolucaoContaCapitalServico
				.criarComboMotivoDevolucao(ContaCapitalConstantes.COD_SITUACAO_COOPERADO_DEMITIDO);
		Assert.assertFalse(itens.isEmpty());
	}
	
	@Test
	public void gerarParcelasTest() throws BancoobException {
		when(genIntIntegracaoDelegate.verificarDiaUtil(anyInt(), any(Date.class))).thenReturn(false);
		when(genIntIntegracaoDelegate.recuperarProximoDiaUtil(anyInt(), any(Date.class))).thenReturn(new Date());
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		DevolucaoRenVO vo = new DevolucaoRenVO();
		vo.setDtInicioParcelamento("01/01/2000");
		vo.setQtdParcelas(3);
		vo.setVlrParcelas(BigDecimal.TEN);
		vo.setVlrAVista(BigDecimal.ZERO);
		dto.getDados().put("vo", vo);
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.gerarParcelas(dto);
		Assert.assertTrue(retornoDTO.getDados().containsKey("listaParcelas"));
	}
	
	@Test(expected=ContaCapitalMovimentacaoException.class)
	public void gerarParcelasExceptionTest() throws BancoobException {
		when(genIntIntegracaoDelegate.verificarDiaUtil(anyInt(), any(Date.class))).thenThrow(new BancoobException(""));
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		DevolucaoRenVO vo = new DevolucaoRenVO();
		vo.setDtInicioParcelamento("01/01/2000");
		vo.setQtdParcelas(3);
		vo.setVlrParcelas(BigDecimal.TEN);
		vo.setVlrAVista(BigDecimal.ZERO);
		dto.getDados().put("vo", vo);
		devolucaoContaCapitalServico.gerarParcelas(dto);
	}
	
	@Test
	public void incluirCaptacaoTest() throws BancoobException {
		doNothing().when(devolucaoContaCapitalDelegate).incluirCaptacaoRemunerada(any(DevolucaoRenDTO.class));
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		DevolucaoRenVO devolucaoRenVO = new DevolucaoRenVO();
		devolucaoRenVO.setTipoInteg(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA.shortValue());
		reqDTO.getDados().put("vo", devolucaoRenVO);
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.incluir(reqDTO);
		Assert.assertTrue(retornoDTO.getDados().containsKey("msg"));
	}
	
	@Test(expected=ContaCapitalMovimentacaoNegocioException.class)
	public void incluirMovimentacaoNegocioExceptionTest() throws BancoobException {
		fluxoIncluirException(new ContaCapitalMovimentacaoNegocioException("Teste"));
	}
	
	@Test(expected=ContaCapitalMovimentacaoNegocioException.class)
	public void incluirCadastroExceptionTest() throws BancoobException {
		fluxoIncluirException(new ContaCapitalCadastroNegocioException("Teste"));
	}
	
	@Test(expected=ContaCapitalMovimentacaoException.class)
	public void incluirMovimentacaoExceptionTest() throws BancoobException {
		fluxoIncluirException(new ContaCapitalMovimentacaoException("Teste"));
	}
	
	@Test(expected=ContaCapitalMovimentacaoException.class)
	public void incluirBancoobExceptionTest() throws BancoobException {
		fluxoIncluirException(new BancoobException("Teste"));
	}

	private void fluxoIncluirException(Exception exception) throws BancoobException {
		doThrow(exception).when(devolucaoContaCapitalDelegate).incluirCaptacaoRemunerada(any(DevolucaoRenDTO.class));
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		DevolucaoRenVO devolucaoRenVO = new DevolucaoRenVO();
		devolucaoRenVO.setTipoInteg(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAPTACAO_REMUNERADA.shortValue());
		reqDTO.getDados().put("vo", devolucaoRenVO);
		devolucaoContaCapitalServico.incluir(reqDTO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void incluirNaoCaptacaoTest() throws BancoobException {
		doNothing().when(devolucaoContaCapitalDelegate).incluir(any(DevolucaoRenDTO.class), anyList());
		when(prodLegadoDelegate.obterDataAtualProdutoCCALogado()).thenReturn(new Date());
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		
		DevolucaoRenVO devolucaoRenVO = new DevolucaoRenVO();
		devolucaoRenVO.setTipoInteg(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.shortValue());
		
		List<ParcelamentoRenVO> lstParcelamentoRenVO = new ArrayList<ParcelamentoRenVO>();
		lstParcelamentoRenVO.add(criarParcelamento(ContaCapitalConstantes.NUM_ZERO, ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA));
		lstParcelamentoRenVO.add(criarParcelamento(ContaCapitalConstantes.NUM_ZERO, ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN));
		lstParcelamentoRenVO.add(criarParcelamento(ContaCapitalConstantes.NUM_ZERO, ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA));
		lstParcelamentoRenVO.add(criarParcelamento(1, 0));
		
		reqDTO.getDados().put("vo", devolucaoRenVO);
		reqDTO.getDados().put("listaParcelasVO", lstParcelamentoRenVO);
		
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.incluir(reqDTO);
		Assert.assertTrue(retornoDTO.getDados().containsKey("msg"));
	}

	private ParcelamentoRenVO criarParcelamento(Integer numParcela, Integer codModoLancamento) {
		ParcelamentoRenVO parcelamentoRenVO = new ParcelamentoRenVO();
		parcelamentoRenVO.setDataVencimento("01/01/2000");
		parcelamentoRenVO.setNumParcela(numParcela.shortValue());
		parcelamentoRenVO.setIdTipoInteg(codModoLancamento.shortValue());
		return parcelamentoRenVO;
	}
	
	@Test
	public void verificarDiaUtilPositivoTest() throws BancoobException {
		when(genIntIntegracaoDelegate.verificarDiaUtil(anyInt(), any(Date.class))).thenReturn(true);
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("dtParcela", new Date());
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.verificarDiaUtil(dto);
		Assert.assertTrue(retornoDTO.getDados().containsKey("bolDataUtil"));
	}
	
	@Test
	public void verificarDiaUtilNegativoTest() throws BancoobException {
		when(genIntIntegracaoDelegate.verificarDiaUtil(anyInt(), any(Date.class))).thenReturn(false);
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("dtParcela", new Date());
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.verificarDiaUtil(dto);
		Assert.assertTrue(retornoDTO.getDados().containsKey("bolDataUtil"));
	}
	
	@Test
	public void criarComboModalidadeCaptacaoRemuneradaTest() throws BancoobException {
		List<ItemListaIntegracaoDTO> listaModalidade = new ArrayList<ItemListaIntegracaoDTO>();
		listaModalidade.add(new ItemListaIntegracaoDTO("1", "A"));
		when(captacaoRemuneradaIntegracaoDelegate.listarModalidadeCaptacaoRemunerada()).thenReturn(listaModalidade);
		List<ItemListaVO> list = devolucaoContaCapitalServico.criarComboModalidadeCaptacaoRemunerada();
		Assert.assertFalse(list.isEmpty());
	}
	
	@Test
	public void consultarTest() throws BancoobException {
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idPessoa", 1);
		reqDTO.getDados().put("idInstituicao", 1);
		List<CadastroContaCapitalRenDTO> listRet = new ArrayList<CadastroContaCapitalRenDTO>();
		listRet.add(new CadastroContaCapitalRenDTO());
		when(cadastroDelegate.pesquisar(any(CadastroContaCapitalRenDTO.class))).thenReturn(listRet);
		RetornoDTO retornoDTO = devolucaoContaCapitalServico.consultar(reqDTO);
		Assert.assertTrue(retornoDTO.getDados().containsKey("registros"));
	}
	
}
