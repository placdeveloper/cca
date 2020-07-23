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
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoParcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcela;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ParcelamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.GenIntIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.TrabalhaLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.movimentacao.servicos.ParcelamentoContaCapitalServico;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;
import br.com.sicoob.tipos.DateTime;

public class ParcelamentoContaCapitalServicoTest extends AbstractMovimentacaoContaCapitalTest {

	@InjectMocks
	private ParcelamentoContaCapitalServico parcelamentoContaCapitalServico;
	
	@Mock
	private ProdutoLegadoDelegate prodLegadoDelegate;
	
	@Mock
	private ParcelamentoContaCapitalDelegate parcelamentoContaCapitalDelegate;
	
	@Mock
	private GenIntIntegracaoDelegate genIntIntegracaoDelegate;	
	
	@Mock
	private TrabalhaLegadoDelegate trabalhaLegadoDelegate;
	
	@Mock
	private CadastroContaCapitalRenDelegate cadastroDelegate;
	
	@Mock
	private ContaCapitalLegadoDelegate contaCapitalLegadoDelegate;
	
	@Mock
	private ContaCapitalDelegate contaCapitalDelegate;
	
	@Mock
	private ContaCorrenteIntegracaoDelegate contaCorrenteIntegracaoDelegate;
	
	@Test
	public void obterDefinicoesTest() throws BancoobException {
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.obterDefinicoes(new RequisicaoReqDTO());
		Assert.assertFalse(retornoDTO.getDados().isEmpty());
	}
	
	@Test
	public void procurarTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("idContaCapital", "1");
		dto.getDados().put("idTipoParcelamento", "1");
		dto.getDados().put("idSituacaoParcelamento", EnumSituacaoParcela.COD_ABERTO.getCodigo().toString());
		List<ParcelamentoRenDTO> lstParcelamento = new ArrayList<ParcelamentoRenDTO>();
		ParcelamentoRenDTO parcelamentoDTO = new ParcelamentoRenDTO();
		parcelamentoDTO.setValorAberto(BigDecimal.ONE);
		lstParcelamento.add(parcelamentoDTO);
		when(parcelamentoContaCapitalDelegate.pesquisarParcelamentos(anyInt(), anyInt())).thenReturn(lstParcelamento);
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.procurar(dto);
		Assert.assertTrue(retornoDTO.getDados().containsKey("listaParcelamentos"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void listarParcelasTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("numMatricula", "1");
		dto.getDados().put("idContaCapital", "1");
		dto.getDados().put("idTipoParcelamento", "1");
		dto.getDados().put("idSituacaoParcelamento", "1");
		dto.getDados().put("numParcelamento", "1");
		
		ContaCapitalLegado  contaCapitalLegado = new ContaCapitalLegado();
		when(contaCapitalLegadoDelegate.obter(anyInt())).thenReturn(contaCapitalLegado);
		
		List<Parcelamento> lstParcelas = new ArrayList<Parcelamento>();
		Parcelamento item = new Parcelamento();
		item.setDataVencimento(new DateTimeDB());
		item.setDataSituacao(new DateTimeDB());
		item.setSituacaoParcelamento(new SituacaoParcelamento());
		item.setTipoIntegralizacao(new TipoIntegralizacao());
		item.setContaCapital(new ContaCapital());
		lstParcelas.add(item);
		when(parcelamentoContaCapitalDelegate.listar(any(ConsultaDto.class))).thenReturn(lstParcelas);
		
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.listarParcelas(dto);
		Assert.assertTrue(retornoDTO.getDados().containsKey("listaParcelas"));
		Assert.assertTrue(retornoDTO.getDados().containsKey("idPessoaLegado"));
	}
	
	@Test
	public void criarComboTipoParcelamentoTest() throws BancoobException {
		List<ItemListaVO> list = parcelamentoContaCapitalServico.criarComboTipoParcelamento();
		Assert.assertFalse(list.isEmpty());
	}
	
	@Test
	public void criarComboSituacaoParcelamentoTest() throws BancoobException {
		List<ItemListaVO> list = parcelamentoContaCapitalServico.criarComboSituacaoParcelamento();
		Assert.assertFalse(list.isEmpty());
	}
	
	@Test
	public void criarComboTipoIntegralizacaoTest() throws BancoobException {
		List<ItemListaVO> list = parcelamentoContaCapitalServico.criarComboTipoIntegralizacao(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO);
		Assert.assertFalse(list.isEmpty());
	}
	
	@Test
	public void criarComboTrabalhaTest() throws BancoobException {
		List<TrabalhaLegadoDTO> listTrabalhaLegadoDTO = new ArrayList<TrabalhaLegadoDTO>();
		TrabalhaLegadoDTO dto = new TrabalhaLegadoDTO();
		dto.setDescMatriculaFunc("Test");
		listTrabalhaLegadoDTO.add(dto);
		List<ItemListaVO> list = parcelamentoContaCapitalServico.criarComboTrabalha(listTrabalhaLegadoDTO);
		Assert.assertFalse(list.isEmpty());
	}
	
	@Test
	public void obterDefinicoesParcelaTest() throws BancoobException {
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idContaCapital", "1");
		reqDTO.getDados().put("idPessoaLegado", "1");
		
		when(prodLegadoDelegate.obterDataAtualProdutoCCALogado()).thenReturn(new DateTime());
		when(contaCapitalDelegate.obter(anyInt())).thenReturn(new ContaCapital());
		when(contaCorrenteIntegracaoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(any(ContaCorrenteIntegracaoDTO.class)))
			.thenReturn(new ArrayList<ContaCorrenteIntegracaoRetDTO>());
		
		List<TrabalhaLegadoDTO> listTrabalhaLegadoDTO = new ArrayList<TrabalhaLegadoDTO>();
		TrabalhaLegadoDTO dto = new TrabalhaLegadoDTO();
		dto.setDescMatriculaFunc("Test");
		listTrabalhaLegadoDTO.add(dto);
		when(trabalhaLegadoDelegate.obterDadosTrabalha(anyInt())).thenReturn(listTrabalhaLegadoDTO);
		
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.obterDefinicoesParcela(reqDTO);
		Assert.assertFalse(retornoDTO.getDados().isEmpty());
	}
	
	@Test
	public void verificarDiaUtilTest() throws BancoobException {
		when(genIntIntegracaoDelegate.verificarDiaUtil(anyInt(), any(Date.class))).thenReturn(true);
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("dtParcela", new Date());
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.verificarDiaUtil(dto);
		Assert.assertTrue(retornoDTO.getDados().containsKey("bolDataUtil"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void cancelarParcelasTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		List<ParcelamentoRenVO> listaParcelasVO = new ArrayList<ParcelamentoRenVO>();
		dto.getDados().put("listaParcelas", listaParcelasVO);
		doNothing().when(parcelamentoContaCapitalDelegate).cancelarParcelas(anyList());
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.cancelarParcelas(dto);
		Assert.assertNotNull(retornoDTO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void baixarParcelasTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		List<ParcelamentoRenVO> listaParcelasVO = new ArrayList<ParcelamentoRenVO>();
		dto.getDados().put("listaParcelas", listaParcelasVO);
		doNothing().when(parcelamentoContaCapitalDelegate).baixarParcelas(anyList());
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.baixarParcelas(dto);
		Assert.assertNotNull(retornoDTO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void gravarParcelasTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		List<ParcelamentoRenVO> listaParcelasVO = new ArrayList<ParcelamentoRenVO>();
		dto.getDados().put("listaParcelas", listaParcelasVO);
		doNothing().when(parcelamentoContaCapitalDelegate).gravarParcelas(anyList());
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.gravarParcelas(dto);
		Assert.assertNotNull(retornoDTO);
	}
	
	@Test
	public void montarListaParcelaDTOTest() throws BancoobException {
		List<ParcelamentoRenVO> listaParcelasVO = new ArrayList<ParcelamentoRenVO>();
		ParcelamentoRenVO item = new ParcelamentoRenVO();
		item.setDataVencimento("01/01/2000");
		listaParcelasVO.add(item);
		List<ParcelamentoRenDTO> listaParcelasDTO = parcelamentoContaCapitalServico.montarListaParcelaDTO(listaParcelasVO);
		Assert.assertFalse(listaParcelasDTO.isEmpty());
	}
	
	@Test
	public void consultarTest() throws BancoobException {
		RequisicaoReqDTO reqDTO = new RequisicaoReqDTO();
		reqDTO.getDados().put("idPessoa", 1);
		reqDTO.getDados().put("idInstituicao", 1);
		List<CadastroContaCapitalRenDTO> listRet = new ArrayList<CadastroContaCapitalRenDTO>();
		listRet.add(new CadastroContaCapitalRenDTO());
		when(cadastroDelegate.pesquisar(any(CadastroContaCapitalRenDTO.class))).thenReturn(listRet);
		RetornoDTO retornoDTO = parcelamentoContaCapitalServico.consultar(reqDTO);
		Assert.assertTrue(retornoDTO.getDados().containsKey("registros"));
	}
	
}
