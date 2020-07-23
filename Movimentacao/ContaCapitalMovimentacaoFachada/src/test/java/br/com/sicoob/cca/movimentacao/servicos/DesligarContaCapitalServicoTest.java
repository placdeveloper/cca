package br.com.sicoob.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.GrupoHistorico;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.movimentacao.negocio.delegates.DesligarContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.LancamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.movimentacao.servicos.DesligarContaCapitalServico;
import br.com.sicoob.sisbr.cca.movimentacao.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO;
import br.com.sicoob.tipos.DateTime;

public class DesligarContaCapitalServicoTest extends AbstractMovimentacaoContaCapitalTest {

	@Mock	
	private ProdutoLegadoDelegate prodLegadoDelegate;
	
	@Mock
	private DesligarContaCapitalDelegate desligarContaCapitalDelegate;
	
	@Mock
	private CadastroContaCapitalRenDelegate cadastroDelegate;
	
	@Mock
	private ContaCapitalDelegate contaCapitalDelegate;
	
	@Mock
	private LancamentoContaCapitalDelegate lancamentoDelegate;
	
	@Mock
	private ValorCotaDelegate valorCotaDelegate;
	
	@InjectMocks
	private DesligarContaCapitalServico servico = new DesligarContaCapitalServico();
	
	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void obterDefinicoesTest() throws BancoobException {
		when(prodLegadoDelegate.obterDataAtualProdutoCCALogado()).thenReturn(new DateTime());
		RetornoDTO retornoDTO = servico.obterDefinicoes(new RequisicaoReqDTO());
		Assert.assertTrue(retornoDTO.getDados().containsKey("dataAtualProduto"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarTest() throws BancoobException {
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idPessoa", 1);
		reqDTO.getDados().put("idInstituicao", 1);
		List<CadastroContaCapitalRenDTO> listaMock = new ArrayList<CadastroContaCapitalRenDTO>();
		listaMock.add(new CadastroContaCapitalRenDTO());
		Mockito.when(cadastroDelegate.pesquisar(Mockito.any(CadastroContaCapitalRenDTO.class)))
			.thenReturn(listaMock);
		RetornoDTO retorno = servico.consultar(reqDTO);
		Assert.assertNotNull(retorno.getDados().get("registros"));
		List<CadastroContaCapitalRenVO> listaRetorno = (List<CadastroContaCapitalRenVO>) retorno.getDados().get("registros");
		Assert.assertFalse(listaRetorno.isEmpty());
	}
	
	@Test
	public void obterInformacoesTest() throws BancoobException {
		final Integer UM = 1;
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idContaCapital", UM);
		
		ContaCapital contaCapital = criarContaCapital();
		Mockito.when(contaCapitalDelegate.obter(UM)).thenReturn(contaCapital);
		
		List<LancamentoContaCapital> lancamentos = criarLancamentos();
		Mockito.when(lancamentoDelegate.pesquisarLancamentosDoDiaPorContaCapital(UM)).thenReturn(lancamentos);
		
		Mockito.when(valorCotaDelegate.obterValorCota(UM)).thenReturn(BigDecimal.ONE);
		
		RetornoDTO retornoDTO = servico.obterInformacoes(reqDTO);
		Assert.assertTrue(retornoDTO.getDados().containsKey("vo"));
		DesligarContaCapitalRenVO vo = (DesligarContaCapitalRenVO) retornoDTO.getDados().get("vo");
		Assert.assertTrue(BigDecimal.TEN.compareTo(vo.getVlrSubs()) == 0);
		Assert.assertTrue(BigDecimal.TEN.compareTo(vo.getVlrInteg()) == 0);
		Assert.assertTrue(BigDecimal.ZERO.compareTo(vo.getVlrDevol()) == 0);
		Assert.assertTrue(BigDecimal.ZERO.compareTo(vo.getVlrAInteg()) == 0);
		Assert.assertEquals(BigDecimal.TEN.longValue(), vo.getQtdCotas().longValue());
	}

	private ContaCapital criarContaCapital() {
		final Integer UM = 1;
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setId(UM);
		contaCapital.setIdInstituicao(UM);
		contaCapital.setIdPessoa(UM);
		contaCapital.setNumContaCapital(UM);
		contaCapital.setValorBloq(BigDecimal.ZERO);
		contaCapital.setValorSubs(BigDecimal.TEN);
		contaCapital.setValorInteg(BigDecimal.TEN);
		contaCapital.setValorDevol(BigDecimal.ZERO);
		return contaCapital;
	}
	
	private List<LancamentoContaCapital> criarLancamentos() {
		List<LancamentoContaCapital> lancamentos = new ArrayList<LancamentoContaCapital>();
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO, ContaCapitalConstantes.COD_LANC_OPERACAO_CREDITO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO, ContaCapitalConstantes.COD_LANC_OPERACAO_DEBITO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO, ContaCapitalConstantes.COD_LANC_OPERACAO_ESTORNO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO, ContaCapitalConstantes.COD_LANC_OPERACAO_CREDITO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO, ContaCapitalConstantes.COD_LANC_OPERACAO_DEBITO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO, ContaCapitalConstantes.COD_LANC_OPERACAO_ESTORNO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_DEVOLUCAO, ContaCapitalConstantes.COD_LANC_OPERACAO_CREDITO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_DEVOLUCAO, ContaCapitalConstantes.COD_LANC_OPERACAO_DEBITO));
		lancamentos.add(criarLancamento(ContaCapitalConstantes.COD_GRUPO_HIST_DEVOLUCAO, ContaCapitalConstantes.COD_LANC_OPERACAO_ESTORNO));
		return lancamentos;
	}

	private LancamentoContaCapital criarLancamento(Integer codGrupoHistorico, String codLancOperacao) {
		GrupoHistorico grupoHistorico = new GrupoHistorico();
		grupoHistorico.setId(codGrupoHistorico.shortValue());
		TipoHistoricoCCA tipoHistoricoCCA = new TipoHistoricoCCA();
		tipoHistoricoCCA.setGrupoHistorico(grupoHistorico);
		tipoHistoricoCCA.setNaturezaOperacao(codLancOperacao);
		
		LancamentoContaCapital lancamento = new LancamentoContaCapital();
		lancamento.setTipoHistoricoCCA(tipoHistoricoCCA);
		lancamento.setValorLancamento(BigDecimal.ONE);
		
		return lancamento;
	}
	
	@Test
	public void desligarTest() throws BancoobException {
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("vo", new DesligarContaCapitalRenVO());
		Mockito.doNothing().when(desligarContaCapitalDelegate).desligarContaCapital(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Date.class));
		RetornoDTO retorno = servico.desligar(reqDTO);
		Assert.assertNull(retorno.getDados().get("erroNegocial"));
	}
	
}
