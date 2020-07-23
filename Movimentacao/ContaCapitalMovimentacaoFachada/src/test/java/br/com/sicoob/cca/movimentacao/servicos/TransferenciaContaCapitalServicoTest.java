package br.com.sicoob.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.GrupoHistorico;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoHistoricoCCA;
import br.com.sicoob.cca.movimentacao.negocio.delegates.LancamentoContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.TransferenciaContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.movimentacao.servicos.TransferenciaContaCapitalServico;

public class TransferenciaContaCapitalServicoTest extends AbstractMovimentacaoContaCapitalTest {

	@Mock
	private ContaCapitalDelegate contaCapitalDelegate;	
	
	@Mock
	private ValorCotaDelegate valorCotaDelegate;	
	
	@Mock
	private LancamentoContaCapitalDelegate lancamentoDelegate;
	
	@Mock
	private TransferenciaContaCapitalDelegate transferenciaContaCapitalDelegate ;
	
	@Mock
	private ValorConfiguracaoCapitalDelegate valorConfiguracaoCapitalDelegate;
	
	@InjectMocks
	private TransferenciaContaCapitalServico servico =  new TransferenciaContaCapitalServico();
	
	@Before
	public void inicializaMocks() throws BancoobException {
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
	public void obterDadosTransferenciaTest() throws BancoobException{
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put("idContaCapital", Integer.valueOf(1));
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setIdInstituicao(Integer.valueOf(1));
		contaCapital.setIdPessoa(Integer.valueOf(1));
		contaCapital.setValorInteg(BigDecimal.valueOf(12.0));
		SituacaoContaCapital situacaoContaCapital = new SituacaoContaCapital();
		contaCapital.setSituacaoContaCapital(situacaoContaCapital);
		List<LancamentoContaCapital> listaLancamento = new ArrayList<LancamentoContaCapital>();
		LancamentoContaCapital lancamento = new LancamentoContaCapital();
		TipoHistoricoCCA tipoCCA = new TipoHistoricoCCA();
		tipoCCA.setNaturezaOperacao("C");
		GrupoHistorico grupoHist = new GrupoHistorico();
		grupoHist.setId(Short.valueOf("2"));
		tipoCCA.setGrupoHistorico(grupoHist);
		lancamento.setTipoHistoricoCCA(tipoCCA);
		lancamento.setValorLancamento(BigDecimal.valueOf(12.0));
		listaLancamento.add(lancamento);
		when(contaCapitalDelegate.obter(anyInt())).thenReturn(contaCapital);
		when(lancamentoDelegate.pesquisarLancamentosDoDiaPorContaCapital(anyInt())).thenReturn(listaLancamento);
		dto.setDados(dados);
		RetornoDTO retorno = servico.obterDadosTransferencia(dto);
		Assert.assertTrue(retorno.getDados().containsKey("vlrBloqueado"));
	}
	
	/**
	 * Obter dados transferencia.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void obterDadosTransferenciaTest2() throws BancoobException{
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put("idContaCapital", Integer.valueOf(1));
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setIdInstituicao(Integer.valueOf(1));
		contaCapital.setIdPessoa(Integer.valueOf(1));
		contaCapital.setValorInteg(BigDecimal.valueOf(12.0));
		SituacaoContaCapital situacaoContaCapital = new SituacaoContaCapital();
		contaCapital.setSituacaoContaCapital(situacaoContaCapital);
		List<LancamentoContaCapital> listaLancamento = new ArrayList<LancamentoContaCapital>();
		LancamentoContaCapital lancamento = new LancamentoContaCapital();
		TipoHistoricoCCA tipoCCA = new TipoHistoricoCCA();
		tipoCCA.setNaturezaOperacao("D");
		GrupoHistorico grupoHist = new GrupoHistorico();
		grupoHist.setId(Short.valueOf("2"));
		tipoCCA.setGrupoHistorico(grupoHist);
		lancamento.setTipoHistoricoCCA(tipoCCA);
		lancamento.setValorLancamento(BigDecimal.valueOf(12.0));
		listaLancamento.add(lancamento);
		when(contaCapitalDelegate.obter(anyInt())).thenReturn(contaCapital);
		when(lancamentoDelegate.pesquisarLancamentosDoDiaPorContaCapital(anyInt())).thenReturn(listaLancamento);
		dto.setDados(dados);
		RetornoDTO retorno = servico.obterDadosTransferencia(dto);
		Assert.assertTrue(retorno.getDados().containsKey("vlrBloqueado"));
	}
	
	/**
	 * Obter dados transferencia.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void obterDadosTransferenciaTest3() throws BancoobException{
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put("idContaCapital", Integer.valueOf(1));
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setIdInstituicao(Integer.valueOf(1));
		contaCapital.setIdPessoa(Integer.valueOf(1));
		contaCapital.setValorInteg(BigDecimal.valueOf(12.0));
		SituacaoContaCapital situacaoContaCapital = new SituacaoContaCapital();
		contaCapital.setSituacaoContaCapital(situacaoContaCapital);
		List<LancamentoContaCapital> listaLancamento = new ArrayList<LancamentoContaCapital>();
		LancamentoContaCapital lancamento = new LancamentoContaCapital();
		TipoHistoricoCCA tipoCCA = new TipoHistoricoCCA();
		tipoCCA.setNaturezaOperacao("E");
		GrupoHistorico grupoHist = new GrupoHistorico();
		grupoHist.setId(Short.valueOf("2"));
		tipoCCA.setGrupoHistorico(grupoHist);
		lancamento.setTipoHistoricoCCA(tipoCCA);
		lancamento.setValorLancamento(BigDecimal.valueOf(12.0));
		listaLancamento.add(lancamento);
		when(contaCapitalDelegate.obter(anyInt())).thenReturn(contaCapital);
		when(lancamentoDelegate.pesquisarLancamentosDoDiaPorContaCapital(anyInt())).thenReturn(listaLancamento);
		dto.setDados(dados);
		RetornoDTO retorno = servico.obterDadosTransferencia(dto);
		Assert.assertTrue(retorno.getDados().containsKey("vlrBloqueado"));
	}
	
	/**
	 * Obter dados transferencia.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void incluirTestException() throws BancoobException{
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put("idContaCapitalDebito", Integer.valueOf(1));
		dados.put("idInstituicaoDebito", Integer.valueOf(1));
		dados.put("numContaCapitalDebito", Integer.valueOf(1));
		dados.put("idContaCapitalCredito", Integer.valueOf(1));
		dados.put("idInstituicaoCredito", Integer.valueOf(1));
		dados.put("numContaCapitalCredito", Integer.valueOf(1));
		dados.put("vlrTransferir", Integer.valueOf(1));
		
		dto.setDados(dados);
		RetornoDTO retornoDTO = servico.incluir(dto);
		Assert.assertNotNull(retornoDTO.getDados().get("msg"));
	}
	/**
	 * Obter dados transferencia.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	
	@Test
	public void incluirTest() throws BancoobException{
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put("idContaCapitalDebito", Integer.valueOf(1));
		dados.put("idInstituicaoDebito", Integer.valueOf(1));
		dados.put("numContaCapitalDebito", Integer.valueOf(1));
		dados.put("idContaCapitalCredito", Integer.valueOf(1));
		dados.put("idInstituicaoCredito", Integer.valueOf(1));
		dados.put("numContaCapitalCredito", Integer.valueOf(1));
		dados.put("vlrTransferir", Integer.valueOf(1));
		
		dto.setDados(dados);
		RetornoDTO retorno = servico.incluir(dto);
		Assert.assertNotNull(retorno.getDados().get("msg"));
	}
}
