package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoLancamentoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoLote;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoEstornoRateioDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistoricoLancamentoContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoInstituicaoNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.CapaLoteCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import junit.framework.Assert;

public class LancamentoIntegralizacaoExternaServicoEJBTest extends Mockito{

	@InjectMocks
	private LancamentoIntegralizacaoExternaServicoEJB servico;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private CapaLoteCapitalLegadoServicoLocal capaLoteCapitalLegadoServico;
	
	@Mock
	private LancamentosCCapitalLegadoServicoLocal lancamentosCCapitalLegadoServico;	
	
	@Mock
	private ContaCapitalLegadoServicoLocal contaCapitalLegadoServico;	
	
	@Mock
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@Mock
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@Mock
	private CapesIntegracaoServicoLocal capesIntegracaoServico;
	
	@Mock
	private FechamentoContaCapitalServicoLocal fechamentoServico;
	
	@Mock
	private ProdutoLegadoServicoLocal prodLegadoServico;
	
	@Mock
	private LancamentoContaCapitalDao lancamentoContaCapitalDao;
	
	@Mock
	private HistoricoLancamentoContaCapitalDao historicoLancamentoContaCapitalDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void incluirTest() throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();

		// Validar integralizacao obrigatorios
		dto.setIdInstituicao(1);
		dto.setNumMatricula(1);
		dto.setIdTipoHistoricoLanc(1);
		dto.setIdOperacaoOrigem("stringTeste");
		
		//Validar valores obrigatorios
		dto.setNumLoteLanc(1);
		dto.setValorLancamento(new BigDecimal(1));
		when(instituicaoIntegracaoServico.obterNumeroCooperativa(anyInt())).thenReturn(1);
		ContaCapitalLegado ccaLegado = new ContaCapitalLegado();
		ccaLegado.setCodSituacao(1);
		ccaLegado.setNumMatricula(1);
		
		when(contaCapitalServico.obterPorInstituicaoMatricula(anyInt(), anyInt())).thenReturn(new ContaCapital());
		
		LancamentoContaCapital lancCCA = new LancamentoContaCapital();
		lancCCA.setContaCapital(new ContaCapital());
		lancCCA.setTipoHistoricoCCA(new TipoHistoricoCCA());
		lancCCA.setTipoLote(new TipoLote());
		
		when(lancamentoContaCapitalDao.incluir(anyObject())).thenReturn(lancCCA);
		
		when(historicoLancamentoContaCapitalDao.incluir(anyObject())).thenReturn(new HistoricoLancamentoCCA());
		
		when(contaCapitalLegadoServico.obter(anyInt())).thenReturn(ccaLegado);
		when(prodLegadoServico.obterDataAtualProduto(anyInt(),anyInt())).thenReturn(new Date());
		when(capaLoteCapitalLegadoServico.obter(anyInt())).thenReturn(new CapaLoteCapitalLegado());
		
		LancamentosCCapitalLegado lancCCALegado = new LancamentosCCapitalLegado();
		lancCCALegado.setBolAtualizado(true);
		lancCCALegado.setiDTipoHistoricoLanc(1);
		LancamentosCCapitalLegadoPK lancCCALegadoPK = new LancamentosCCapitalLegadoPK();
		
		CapaLoteCapitalLegado capaLoteLegado = new CapaLoteCapitalLegado();
		CapaLoteCapitalLegadoPK capaLoteLegadoPK = new CapaLoteCapitalLegadoPK();
		capaLoteLegadoPK.setNumLoteLanc(1);
		
		capaLoteLegado.setCapaLoteCapitalLegadoPK(capaLoteLegadoPK);
		
		lancCCALegadoPK.setCapaLoteCapitalLegado(capaLoteLegado);
		lancCCALegado.setLancamentosCCapitalLegadoPK(lancCCALegadoPK);
		lancCCALegado.setContaCapitalLegado(ccaLegado);
		
		when(lancamentosCCapitalLegadoServico.incluir(anyObject())).thenReturn(lancCCALegado);
		
		doNothing().when(capaLoteCapitalLegadoServico).atualizarCapaLote(anyInt(), anyObject(), anyInt());
		
		Assert.assertNotNull(servico.incluir(dto));
	}
	
	@Test(expected = ContaCapitalMovimentacaoNegocioException.class)
	public void incluir_MovimentacaoNegocioException_Test() throws BancoobException {
		servico.incluir(new IntegralizacaoCapitalDTO());
	}
	
	@Test(expected = ContaCapitalMovimentacaoNegocioException.class)
	public void incluir_IntegracaoInstituicaoNegocioException_Test() throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();

		// Validar integralizacao obrigatorios
		dto.setIdInstituicao(1);
		dto.setNumMatricula(1);
		dto.setIdTipoHistoricoLanc(1);
		dto.setIdOperacaoOrigem("stringTeste");
		
		when(instituicaoIntegracaoServico.obterNumeroCooperativa(anyInt())).thenThrow(IntegracaoInstituicaoNegocioException.class);
		servico.incluir(dto);
	}
	
	@Test(expected = ContaCapitalMovimentacaoException.class)
	public void incluir_BancoobException_Test() throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();

		// Validar integralizacao obrigatorios
		dto.setIdInstituicao(1);
		dto.setNumMatricula(1);
		dto.setIdTipoHistoricoLanc(1);
		dto.setIdOperacaoOrigem("stringTeste");
		
		when(instituicaoIntegracaoServico.obterNumeroCooperativa(anyInt())).thenThrow(BancoobException.class);
		servico.incluir(dto);
	}
	
	@Test
	public void consultarIntegralizacao_ConsultaSQL_Test() throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();
		ContaCapitalLegado ccaLegado = new ContaCapitalLegado();
		ccaLegado.setCodSituacao(1);
		ccaLegado.setNumMatricula(1);
		
		// Validar integralizacao obrigatorios
		dto.setIdInstituicao(1);
		dto.setNumMatricula(1);
		dto.setIdTipoHistoricoLanc(1);
		dto.setIdOperacaoOrigem("stringTeste");
		dto.setDataLancamento(new DateTimeDB(new Date().getTime()));
		
		List<LancamentosCCapitalLegado> lista = new ArrayList<>();
		LancamentosCCapitalLegado lancamentos = new LancamentosCCapitalLegado();
		lancamentos.setBolAtualizado(true);
		lancamentos.setContaCapitalLegado(ccaLegado);
		lancamentos.setiDTipoHistoricoLanc(1);
		
		LancamentosCCapitalLegadoPK lancamentosCCapitalLegadoPK = new LancamentosCCapitalLegadoPK();
		
		CapaLoteCapitalLegado capaLoteCapitalLegado = new CapaLoteCapitalLegado();
		CapaLoteCapitalLegadoPK capaLoteCapitalLegadoPK = new CapaLoteCapitalLegadoPK();
		capaLoteCapitalLegadoPK.setNumLoteLanc(1);
		
		capaLoteCapitalLegado.setCapaLoteCapitalLegadoPK(capaLoteCapitalLegadoPK);
		lancamentosCCapitalLegadoPK.setCapaLoteCapitalLegado(capaLoteCapitalLegado);
		lancamentos.setLancamentosCCapitalLegadoPK(lancamentosCCapitalLegadoPK);
		lista.add(lancamentos);
		
		when(contaCapitalLegadoServico.obter(anyInt())).thenReturn(ccaLegado);
		when(lancamentosCCapitalLegadoServico.listar(anyObject())).thenReturn(lista);
		Assert.assertNotNull(servico.consultarIntegralizacao(dto));
	}
	
	@Test
	public void consultarIntegralizacao_ConsultaDB2_Test() throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();

		// Validar integralizacao obrigatorios
		dto.setIdInstituicao(1);
		dto.setNumMatricula(1);
		dto.setIdTipoHistoricoLanc(1);
		dto.setIdOperacaoOrigem("stringTeste");
		dto.setDataLancamento(new DateTimeDB(new Date().getTime()));
		
		when(contaCapitalServico.obterPorInstituicaoMatricula(anyInt(), anyInt())).thenReturn(new ContaCapital());
		when(lancamentoContaCapitalDao.listar(anyObject())).thenReturn(new ArrayList<LancamentoContaCapital>());
		Assert.assertNotNull(servico.consultarIntegralizacao(dto));
	}
	
	@Test(expected = ContaCapitalMovimentacaoNegocioException.class)
	public void consultarIntegralizacao_ConsultaDB2_ContaCapitalMovimentacaoNegocioException_Test() throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();

		// Validar integralizacao obrigatorios
		dto.setIdInstituicao(1);
		dto.setNumMatricula(1);
		dto.setIdTipoHistoricoLanc(1);
		dto.setIdOperacaoOrigem("stringTeste");
		dto.setDataLancamento(new DateTimeDB(new Date().getTime()));
		
		when(contaCapitalServico.obterPorInstituicaoMatricula(anyInt(), anyInt())).thenThrow(ContaCapitalMovimentacaoNegocioException.class);
		
		servico.consultarIntegralizacao(dto);
	}
	
	@Test(expected = ContaCapitalMovimentacaoException.class)
	public void consultarIntegralizacao_ConsultaDB2_BancoobException_Test() throws BancoobException {
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();

		// Validar integralizacao obrigatorios
		dto.setIdInstituicao(1);
		dto.setNumMatricula(1);
		dto.setIdTipoHistoricoLanc(1);
		dto.setIdOperacaoOrigem("stringTeste");
		dto.setDataLancamento(new DateTimeDB(new Date().getTime()));
		
		when(contaCapitalServico.obterPorInstituicaoMatricula(anyInt(), anyInt())).thenThrow(BancoobException.class);
		
		servico.consultarIntegralizacao(dto);
	}
	
	@Test
	public void incluirRateioEmLoteTest() throws BancoobException {
		List<IntegralizacaoCapitalDTO> dtoList = new ArrayList<>();
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();
		dto.setIdInstituicao(1);
		dto.setIdContaCapital(1);
		dto.setIdTipoHistoricoLanc(1);
		
		dto.setNumLoteLanc(1);
		dto.setValorLancamento(new BigDecimal(1));
		dto.setNumMatricula(1);
		
		dtoList.add(dto);
		
		when(prodLegadoServico.obterDataAtualProduto(anyInt(),anyInt())).thenReturn(new Date());
		
		Assert.assertNotNull(servico.incluirRateioEmLote(dtoList));
	}
	
	@Test(expected = ContaCapitalMovimentacaoNegocioException.class)
	public void incluirRateioEmLote_NegocioException_Validacao_Test() throws BancoobException {
		List<IntegralizacaoCapitalDTO> dtoList = new ArrayList<>();
		servico.incluirRateioEmLote(dtoList);
	}
	
	@Test(expected = ContaCapitalMovimentacaoNegocioException.class)
	public void incluirRateioEmLote_ContaCapitalNegocioException_Test() throws BancoobException {
		List<IntegralizacaoCapitalDTO> dtoList = new ArrayList<>();
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();
		dto.setIdInstituicao(1);
		dto.setIdContaCapital(1);
		dto.setIdTipoHistoricoLanc(1);
		
		dto.setNumLoteLanc(1);
		dto.setValorLancamento(new BigDecimal(1));
		dto.setNumMatricula(1);
		
		dtoList.add(dto);
		
		when(prodLegadoServico.obterDataAtualProduto(anyInt(),anyInt())).thenReturn(new Date());
		when(instituicaoIntegracaoServico.obterNumeroCooperativa(anyInt())).thenThrow(ContaCapitalMovimentacaoNegocioException.class);
		servico.incluirRateioEmLote(dtoList);
	}
	
	@Test(expected = ContaCapitalMovimentacaoNegocioException.class)
	public void incluirRateioEmLote_IntegracaoInstituicaoNegocioException_Test() throws BancoobException {
		List<IntegralizacaoCapitalDTO> dtoList = new ArrayList<>();
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();
		dto.setIdInstituicao(1);
		dto.setIdContaCapital(1);
		dto.setIdTipoHistoricoLanc(1);
		
		dto.setNumLoteLanc(1);
		dto.setValorLancamento(new BigDecimal(1));
		dto.setNumMatricula(1);
		
		dtoList.add(dto);
		
		when(prodLegadoServico.obterDataAtualProduto(anyInt(),anyInt())).thenReturn(new Date());
		when(instituicaoIntegracaoServico.obterNumeroCooperativa(anyInt())).thenThrow(IntegracaoInstituicaoNegocioException.class);
		servico.incluirRateioEmLote(dtoList);
	}
	
	@Test(expected = ContaCapitalMovimentacaoException.class)
	public void incluirRateioEmLote_BancoobException_Test() throws BancoobException {
		List<IntegralizacaoCapitalDTO> dtoList = new ArrayList<>();
		IntegralizacaoCapitalDTO dto = new IntegralizacaoCapitalDTO();
		dto.setIdInstituicao(1);
		dto.setIdContaCapital(1);
		dto.setIdTipoHistoricoLanc(1);
		
		dto.setNumLoteLanc(1);
		dto.setValorLancamento(new BigDecimal(1));
		dto.setNumMatricula(1);
		
		dtoList.add(dto);
		
		when(prodLegadoServico.obterDataAtualProduto(anyInt(),anyInt())).thenReturn(new Date());
		when(instituicaoIntegracaoServico.obterNumeroCooperativa(anyInt())).thenThrow(BancoobException.class);
		servico.incluirRateioEmLote(dtoList);
	}
	
	@Test
	public void estornarRateioTest() throws BancoobException {
		List<LancamentoEstornoRateioDTO> dtoList = new ArrayList<>();
		LancamentoEstornoRateioDTO dtoAtivo = new LancamentoEstornoRateioDTO();
		dtoAtivo.setIdInstituicao(1);
		dtoAtivo.setIdContaCapital(1);
		
		dtoAtivo.setDescNumDocumento("descNumDocumento");
		dtoAtivo.setIdSituacaoContaCapital(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo());
		
		LancamentoEstornoRateioDTO dtoDesligado = new LancamentoEstornoRateioDTO();
		dtoDesligado.setIdInstituicao(1);
		dtoDesligado.setIdContaCapital(1);
		
		dtoDesligado.setDescNumDocumento("descNumDocumento");
		dtoDesligado.setIdSituacaoContaCapital(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_DEMITIDO.getCodigo());
		
		dtoList.add(dtoAtivo);
		dtoList.add(dtoDesligado);
		
		when(prodLegadoServico.obterDataAtualProduto(anyInt(),anyInt())).thenReturn(new Date());
		
		Assert.assertNotNull(servico.estornarRateio("idUsuario", dtoList));
	}

}
