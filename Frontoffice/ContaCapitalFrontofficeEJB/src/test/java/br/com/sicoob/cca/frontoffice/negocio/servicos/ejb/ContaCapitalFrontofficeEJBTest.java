package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.dto.TipoParametro;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;

public abstract class ContaCapitalFrontofficeEJBTest extends Mockito {

	protected static final String NUMERO_CPF = "11122233344";
	protected static final Integer ID_PESSOA = 1;
	protected static final Integer ID_INSTITUICAO = 1;
	protected static final Integer COD_COOPERATIVA = 1;
	protected static final Integer NUM_MATRICULA = 1;
	protected static final String ID_USUARIO = "Teste";
	
	@Mock
	private CapesIntegracaoServicoLocal capesIntegracaoServico;
	
	@Mock
	private ContaCapitalLegadoServicoLocal contaCapitalLegadoServico;
	
	@Mock
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@Mock
	private ProdutoLegadoServicoLocal prodLegadoServico;
	
	public ContaCapitalFrontofficeEJBTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void prepararCondicoesGenericas() throws BancoobException {
		when(capesIntegracaoServico.obterPorCpfCnpjInstituicao(anyString(), anyInt())).thenReturn(montarPessoaIntegracaoMock());
		when(capesIntegracaoServico.obterPessoaInstituicao(anyInt(), anyInt())).thenReturn(montarPessoaIntegracaoMock());
		when(contaCapitalLegadoServico.obterContaCapitalCooperativaCliente(anyInt(), anyInt(), anyInt())).thenReturn(montarContaCapitalLegadoMock());
		when(instituicaoIntegracaoServico.obterNumeroCooperativa(anyInt())).thenReturn(COD_COOPERATIVA);
		when(instituicaoIntegracaoServico.obterInstituicaoIntegracao(anyInt())).thenReturn(montarInstituicaoMock());
		when(prodLegadoServico.obterDataAtualProduto(anyInt(), anyInt())).thenReturn(new DateTimeDB());
	}
	
	@Test
	public void executarTransacaoTest() throws BancoobException, ExcecaoTransacao {
		prepararTeste();
		RetornoMensagem retornoMensagem = getTransacao().executarTransacao(criarMensagem());
		assertTransacaoSucesso(retornoMensagem);
	}
	
	/**
	 * Testa se o retorno da transação está de acordo com uma execução bem
	 * sucedida.
	 * 
	 * @param retorno
	 */
	protected void assertTransacaoSucesso(RetornoMensagem retorno) {
		Assert.assertNotNull("Retorno não pode ser nulo", retorno);
		Assert.assertNull("Mensagem de erro deve ser nula", retorno.getMensagem());
		Assert.assertTrue("O retorno deveria estar definido como sucesso", retorno.isSucesso());
		Assert.assertEquals("Código retorno deveria ser 1", Integer.valueOf(1), retorno.getCodRetorno());
		Assert.assertNotNull("Deve ser informado um conteúdo de retorno", retorno.getConteudoRetorno());
	}

	/**
	 * Testa se o retorno da transação está de acordo com uma execução com erro.
	 * 
	 * @param retorno
	 */
	protected void assertTransacaoErro(RetornoMensagem retorno) {
		Assert.assertNotNull("Retorno não pode ser nulo", retorno);
		Assert.assertFalse("O retorno não deveria estar definido como sucesso", retorno.isSucesso());
		Assert.assertEquals("Código retorno deveria ser 0", Integer.valueOf(0), retorno.getCodRetorno());
		Assert.assertNotNull("Deve ser informada uma mensagem de erro", retorno.getMensagem());
	}
	
	private List<ContaCapitalLegado> montarContaCapitalLegadoMock() {
		List<ContaCapitalLegado> contas = new ArrayList<ContaCapitalLegado>();
		ContaCapitalLegado conta = new ContaCapitalLegado();
		conta.setNumMatricula(NUM_MATRICULA);
		conta.setCodSituacao(1);
		contas.add(conta);
		return contas;
	}

	private PessoaIntegracaoDTO montarPessoaIntegracaoMock() {
		PessoaIntegracaoDTO dto = new PessoaIntegracaoDTO();
		dto.setIdPessoa(ID_PESSOA);
		dto.setIdPessoaLegado(ID_PESSOA);
		dto.setNomeApelido("Teste Apelido");
		dto.setNomeCompleto("Teste Completo");
		dto.setNomePessoa("Teste Pessoa");
		return dto;
	}
	
	private InstituicaoIntegracaoDTO montarInstituicaoMock() {
		InstituicaoIntegracaoDTO dto = new InstituicaoIntegracaoDTO();
		dto.setNumero("3008");
		dto.setNomeInstituicao("Teste Instituicao");
		return dto;
	}

	protected final Mensagem criarMensagem() {
		Mensagem mensagem = new Mensagem();
		List<Parametro> parametros = criarParametrosTransacao();
		adicionarParametrosComuns(parametros);
		mensagem.setParametros(parametros);
		mensagem.setIdInstituicao(ID_INSTITUICAO);
		return mensagem;
	}

	protected void adicionarParametrosComuns(List<Parametro> parametros) {
		parametros.add(criarParametro(ParametroSRTBCCA.CPF_CNPJ, NUMERO_CPF));
		parametros.add(criarParametro(ParametroSRTBCCA.ID_USUARIO, ID_USUARIO));
		parametros.add(criarParametro(ParametroSRTBCCA.COOPERATIVA, COD_COOPERATIVA));
		parametros.add(criarParametro(ParametroSRTBCCA.MATRICULA, NUM_MATRICULA));
	}

	protected Parametro criarParametro(ParametroSRTBCCA parametro, Object valor) {
		return new Parametro(parametro.getRotulo(), TipoParametro.ENTRADA, valor, 1);
	}

	protected abstract Transacao getTransacao();
	
	protected abstract List<Parametro> criarParametrosTransacao();
	
	protected abstract void prepararTeste() throws BancoobException, ExcecaoTransacao;
	
}
