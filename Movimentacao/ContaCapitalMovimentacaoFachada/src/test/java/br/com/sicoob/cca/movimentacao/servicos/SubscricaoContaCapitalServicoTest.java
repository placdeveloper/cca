package br.com.sicoob.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.PropostaSubscricaoDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.SubscricaoContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.TrabalhaLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.servicos.SubscricaoContaCapitalServico;
import br.com.sicoob.sisbr.cca.movimentacao.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.SubscricaoRenVO;
import br.com.sicoob.tipos.DateTime;

public class SubscricaoContaCapitalServicoTest extends AbstractMovimentacaoContaCapitalTest {
	
	@Mock	
	private GenIntIntegracaoDelegate genIntIntegracaoDelegate;	
	
	@Mock	
	private ProdutoLegadoDelegate prodLegadoDelegate;
	
	@Mock	
	private PropostaSubscricaoDelegate propostaDelegate;
	
	@Mock	
	private ValorCotaDelegate valorCotaDelegate;
	
	@Mock	
	private TrabalhaLegadoDelegate trabalhaLegadoDelegate;
	
	@Mock	
	private SubscricaoContaCapitalDelegate subscricaoContaCapitalDelegate;	
	
	@Mock	
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate;
	
	@Mock	
	private CadastroContaCapitalRenDelegate cadastroDelegate;
	
	@InjectMocks
	private SubscricaoContaCapitalServico servico =  new SubscricaoContaCapitalServico();
	
	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Obter dados transferencia.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void obterPropostaSubscricaoTest() throws BancoobException{
		
		PowerMockito.mockStatic(ContaCapitalCadastroFabricaDelegates.class);
		ContaCapitalCadastroFabricaDelegates ccaCadastroFabricaMock = PowerMockito.mock(ContaCapitalCadastroFabricaDelegates.class);
		PowerMockito.when(ContaCapitalCadastroFabricaDelegates.getInstance()).thenReturn(ccaCadastroFabricaMock);
		ContaCapitalDelegate ccaDelegateMock = PowerMockito.mock(ContaCapitalDelegate.class);
		PowerMockito.when(ccaCadastroFabricaMock.criarContaCapitalDelegate()).thenReturn(ccaDelegateMock);
		ContaCapital cca = new ContaCapital();
		cca.setIdInstituicao(1);
		cca.setIdPessoa(1);
		PowerMockito.when(ccaDelegateMock.obter(anyInt())).thenReturn(cca);
		
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put("idPessoaLegado", Integer.valueOf(1));
		dados.put("idContaCapital", Integer.valueOf(1));
		List<TrabalhaLegadoDTO> listTrabalhaLegadoDTO = new ArrayList<TrabalhaLegadoDTO>();
		TrabalhaLegadoDTO trabalhaLegadoDTO = new TrabalhaLegadoDTO();
		trabalhaLegadoDTO.setDescMatriculaFunc("teste");
		listTrabalhaLegadoDTO.add(trabalhaLegadoDTO);
		
		List<ContaCorrenteIntegracaoRetDTO> lstCco = new ArrayList<ContaCorrenteIntegracaoRetDTO>();
		ContaCorrenteIntegracaoRetDTO cco = new ContaCorrenteIntegracaoRetDTO();
		cco.setNumeroContaCorrente(1L);
		lstCco.add(cco);
		when(contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(any(ContaCorrenteIntegracaoDTO.class))).thenReturn(lstCco);
		
		when(trabalhaLegadoDelegate.obterDadosTrabalha(anyInt())).thenReturn(listTrabalhaLegadoDTO);
		
		dto.setDados(dados);
		RetornoDTO retornoDTO = servico.obterPropostaSubscricao(dto);
		Assert.assertNotNull(retornoDTO.getDados().get("comboTrabalha"));
		Assert.assertNotNull(retornoDTO.getDados().get("comboTipoSubscricao"));
		Assert.assertNotNull(retornoDTO.getDados().get("propostaSubscricao"));
	}	
	
	/**
	 * Obter definicoes.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void gerarParcelasTest() throws BancoobException {
		DateTime dateTimeHoje = new DateTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTimeHoje);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		DateTime dateTimeAmanha = new DateTime(cal.getTimeInMillis());
		
		when(prodLegadoDelegate.obterDataAtualProdutoCCALogado()).thenReturn(dateTimeHoje);
		when(genIntIntegracaoDelegate.verificarDiaUtil(1, dateTimeHoje)).thenReturn(true);
		when(genIntIntegracaoDelegate.recuperarProximoDiaUtil(Mockito.anyInt(), Mockito.any(Date.class))).thenReturn(dateTimeAmanha);
		
		RequisicaoReqDTO dto = new RequisicaoReqDTO();				
		Map<String, Object> dados = new HashMap<String, Object>();		
		SubscricaoRenVO vo = new SubscricaoRenVO();
		
		final int qtdParcelas = 10;
		
		vo.setVlrSubs(BigDecimal.valueOf(500));
		vo.setVlrInteg(BigDecimal.valueOf(50));				
		vo.setVlrParcelas(BigDecimal.valueOf(10));
		vo.setQtdParcelas(Integer.valueOf(qtdParcelas));
		vo.setDiaDebito(Integer.valueOf(10));
		vo.setTipoInteg(Short.valueOf("0"));
		vo.setNumContaCorrente(Long.valueOf(10)); 
		vo.setDescNumMatriculaFunc("teste"); 

		dados.put("vo", vo);		
		
		dto.setDados(dados);
		
		RetornoDTO retornoDTO = servico.gerarParcelas(dto);
		
		Object parcelasObj = retornoDTO.getDados().get("listaParcelas");
		Assert.assertNotNull(parcelasObj);
		List<ParcelamentoRenVO> parcelas = (List<ParcelamentoRenVO>) parcelasObj;
		Assert.assertFalse(parcelas.isEmpty());
		Assert.assertEquals(parcelas.size(), qtdParcelas);
	}
	
	/**
	 * Inclusao SQL e DB2.
	 *
	 * @param reqDTO o valor de req dto
	 * @return msg
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void incluirTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();				
		Map<String, Object> dados = new HashMap<String, Object>();		
		SubscricaoRenVO vo = new SubscricaoRenVO();
		
		vo.setVlrSubs(BigDecimal.valueOf(500));
		vo.setVlrInteg(BigDecimal.valueOf(50));				
		vo.setVlrParcelas(BigDecimal.valueOf(10));
		vo.setQtdParcelas(Integer.valueOf(10));
		vo.setDiaDebito(Integer.valueOf(10));
		vo.setTipoInteg(Short.valueOf("1"));
		vo.setNumContaCorrente(Long.valueOf(10)); 
		vo.setDescNumMatriculaFunc("teste"); 
		vo.setIdTipoSubscricao(Integer.valueOf(10));
		vo.setIdPessoaLegado(Integer.valueOf(10));
		vo.setIdPessoa(Integer.valueOf(10));		
		
		dados.put("vo", vo);				
		
		List<ParcelamentoRenVO> lstParcelamentoRenVO = new ArrayList<ParcelamentoRenVO>();		
		ParcelamentoRenVO parcelamentoRenVO = new ParcelamentoRenVO();		
		
		parcelamentoRenVO.setDataVencimento("17/08/2017");
		parcelamentoRenVO.setDataVencimentoOrdenacao("2017-08-17");
		parcelamentoRenVO.setDescNumMatriculaFunc("10");
		parcelamentoRenVO.setIdTipoInteg(Short.valueOf("1"));
		parcelamentoRenVO.setNumContaCorrente(Long.valueOf(10));
		parcelamentoRenVO.setNumParcela(Short.valueOf("1"));
		parcelamentoRenVO.setValorParcela(BigDecimal.valueOf(10));
		
		lstParcelamentoRenVO.add(parcelamentoRenVO);
		lstParcelamentoRenVO.add(parcelamentoRenVO);
		lstParcelamentoRenVO.add(parcelamentoRenVO);
		dados.put("listaParcelasVO", lstParcelamentoRenVO);		

		dto.setDados(dados);

		RetornoDTO retornoDTO = servico.incluir(dto);
		Assert.assertEquals(retornoDTO.getDados().get("erroNegocial"), null);
	}
		
	/**
	 * Consulta usada na plataforma de atendimento.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void consultarTest() throws BancoobException {	
		RequisicaoReqDTO dto = new RequisicaoReqDTO();				
		Map<String, Object> dados = new HashMap<String, Object>();		

		dados.put("idInstituicao", Integer.valueOf(10));				
		dados.put("idPessoa", Integer.valueOf(10));				
		
		dto.setDados(dados);
		
		List<CadastroContaCapitalRenDTO> lst = new ArrayList<CadastroContaCapitalRenDTO>();
		CadastroContaCapitalRenDTO cadastroContaCapitalRenDTO = new CadastroContaCapitalRenDTO();
		lst.add(cadastroContaCapitalRenDTO);
		when(cadastroDelegate.pesquisar(Mockito.any(CadastroContaCapitalRenDTO.class))).thenReturn(lst);
		
		RetornoDTO retornoDTO = servico.consultar(dto);		
		Object registrosObj = retornoDTO.getDados().get("registros");
		Assert.assertNotNull(registrosObj);
		List<CadastroContaCapitalRenVO> lstVO = (List<CadastroContaCapitalRenVO>) registrosObj;
		Assert.assertFalse(lstVO.isEmpty());
	}

	/**
	 * Montar proposta subscricao vo.
	 *
	 * @param proposta o valor de proposta
	 * @return SubscricaoRenVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void montarPropostaSubscricaoVOTest() throws BancoobException {		
		SubscricaoRenVO vo = servico.montarPropostaSubscricaoVO(null);
		Assert.assertEquals(ContaCapitalConstantes.ST_BOL_ATIVO, vo.getBolSubscricaoProposta());
		Assert.assertEquals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA, Integer.valueOf(vo.getTipoInteg()));
		Assert.assertEquals(ContaCapitalConstantes.ST_BOL_ATIVO, vo.getDiaDebito());
	}	
	
	/**
	 * Monta combo de acordo com o tipo de operacao de subscrição (novo cooperado, nova subscricao e reativar cooperado).
	 *
	 * @param tipoOpInteg o valor de tipo op integ
	 * @return tipo de integralizacao
	 * @throws BancoobException lança a exceção BancoobException
	 * @see ContaCapitalConstantes#COD_INCLUIR_COOPERADO
	 * @see ContaCapitalConstantes#COD_NOVA_SUBSCRICAO
	 * @see ContaCapitalConstantes#COD_REATIVAR_COOPERADO
	 * @see ContaCapitalConstantes#COD_PROPOSTA_CADASTRO
	 */
	@Test
	public void criarComboTipoIntegralizacaoTest() throws BancoobException {
		List<ItemListaVO> lista = servico.criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO);
		Assert.assertNotNull(lista);
		Assert.assertFalse(lista.isEmpty());
	}	
	
	/**
	 * Monta combo de acordo com o tipo de subscrição.
	 *
	 * @return tipo de subscrição
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void criarComboTipoSubscricaoTest() throws BancoobException {
		List<ItemListaVO> lista = servico.criarComboTipoSubscricao();
		Assert.assertNotNull(lista);
		Assert.assertFalse(lista.isEmpty());
	}	
	
	@Test
	public void obterDefinicoesTest() throws BancoobException {
		when(prodLegadoDelegate.obterDataAtualProdutoCCALogado()).thenReturn(new DateTime());
		RetornoDTO retornoDTO = servico.obterDefinicoes(new RequisicaoReqDTO());
		Assert.assertTrue(retornoDTO.getDados().containsKey("dataAtualProduto"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("comboTipoInteg"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("comboTipoSubscricao"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("idInstituicao"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("numMinCota"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("percMinInteg"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("valorCota"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("numMaxParcelas"));
	}
	
	@Test
	public void isValorIntegralizacaoMaiorSalcoCcoTest() throws BancoobException {
		final long idPessoa = 1;
		final long numContaCorrente = 123456;
		when(contaCorrenteIntegracaoDelegate.isValorIntegralizacaoMaiorSalcoCco(
				BigDecimal.TEN, idPessoa, Integer.valueOf(TEST_ID_INSTITUICAO), TEST_LOGIN, numContaCorrente))
				.thenReturn(Boolean.TRUE);
		when(contaCorrenteIntegracaoDelegate.isValorIntegralizacaoMaiorSalcoCco(
				BigDecimal.ZERO, idPessoa, Integer.valueOf(TEST_ID_INSTITUICAO), TEST_LOGIN, numContaCorrente))
				.thenReturn(Boolean.FALSE);
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idPessoa", String.valueOf(idPessoa));
		reqDTO.getDados().put("numContaCorrente", String.valueOf(numContaCorrente));
		reqDTO.getDados().put("valor", "10");
		RetornoDTO retorno = servico.isValorIntegralizacaoMaiorSalcoCco(reqDTO);
		Assert.assertTrue((Boolean) retorno.getDados().get("isValorIntegralizacaoMaiorSalcoCco"));
		reqDTO.getDados().put("valor", "0");
		retorno = servico.isValorIntegralizacaoMaiorSalcoCco(reqDTO);
		Assert.assertFalse((Boolean) retorno.getDados().get("isValorIntegralizacaoMaiorSalcoCco"));
	}
	
	@Test
	public void criarComboTrabalhaTest() throws BancoobException {
		List<TrabalhaLegadoDTO> listTrabalhaLegadoDTO = new ArrayList<TrabalhaLegadoDTO>();
		TrabalhaLegadoDTO dto = new TrabalhaLegadoDTO();
		dto.setDescMatriculaFunc("teste");
		listTrabalhaLegadoDTO.add(dto);
		List<ItemListaVO> lista = servico.criarComboTrabalha(listTrabalhaLegadoDTO);
		Assert.assertNotNull(lista);
		Assert.assertEquals(lista.size(), 1);
	}
	
}
