package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;

public class IntegralizacaoCapitalServicoEJBTest extends ContaCapitalFrontofficeEJBTest {

	@InjectMocks
	private IntegralizacaoCapitalServicoEJB integralizacaoCapitalServicoEJB;
	
	@Mock
	private ContaCorrenteIntegracaoServicoLocal contaCorrenteIntegracaoServico;

	@Mock
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;
	
	@Mock
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;
	
	@Mock
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;
	
	@Mock
	private GenIntIntegracaoServicoLocal genIntIntegracaoServico;
	
	private static final Integer DIA_DEBITO = 10;
	private static final Integer QTD_MESES = 6;
	private static final BigDecimal VALOR_INTEGRALIZACAO = BigDecimal.TEN;
	private static final Double NUMERO_CONTA_CORRENTE = 1234D;
	
	@Override
	protected Transacao getTransacao() {
		return integralizacaoCapitalServicoEJB;
	}
	
	@Override
	protected List<Parametro> criarParametrosTransacao() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.VALOR, VALOR_INTEGRALIZACAO));
		parametros.add(criarParametro(ParametroSRTBCCA.TIPO_AGENDAMENTO, 0));
		parametros.add(criarParametro(ParametroSRTBCCA.MESES, QTD_MESES));
		parametros.add(criarParametro(ParametroSRTBCCA.DIA_DEBITO, DIA_DEBITO));
		parametros.add(criarParametro(ParametroSRTBCCA.NUMERO_CONTA_CORRENTE, NUMERO_CONTA_CORRENTE));
		return parametros;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void prepararTeste() throws BancoobException, ExcecaoTransacao {
		LancamentoContaCorrenteIntegracaoRetDTO lancCcoRet = new LancamentoContaCorrenteIntegracaoRetDTO();
		lancCcoRet.setCodRetorno(1);
		when(contaCorrenteIntegracaoServico.gravarLancamentosIntegracao(any(LancamentoContaCorrenteIntegracaoDTO.class))).thenReturn(lancCcoRet);
		when(contaCorrenteIntegracaoServico.isValorIntegralizacaoMaiorSalcoCco(
				VALOR_INTEGRALIZACAO, ID_PESSOA.longValue(), ID_INSTITUICAO, ID_USUARIO, NUMERO_CONTA_CORRENTE.longValue()))
				.thenReturn(false);
		when(contaCorrenteIntegracaoServico.verificarContaCorrenteBloqueadaEncerrada(any(ContaCorrenteIntegracaoDTO.class))).thenReturn(Boolean.FALSE);
		when(lancamentoIntegralizacaoExternaServico.incluir(any(IntegralizacaoCapitalDTO.class))).thenReturn(null);
		doNothing().when(parcelamentoContaCapitalExternoServico).incluirParcelamento(anyList());
		when(genIntIntegracaoServico.verificarDiaUtil(anyInt(), any(Date.class))).thenReturn(true);
		when(parcelamentoCCALegadoServico.verificarParcelasEmAbertoPelosCanais(anyInt(), anyInt())).thenReturn(Boolean.FALSE);
		when(contaCorrenteIntegracaoServico.gravarLancamentosIntegracao(any(LancamentoContaCorrenteIntegracaoDTO.class))).thenReturn(lancCcoRet);
	}
	
	@Test
	public void executarTransacaoProgramadaTest() throws BancoobException, ExcecaoTransacao {
		prepararTeste();
		Mensagem mensagem = new Mensagem();
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.VALOR, VALOR_INTEGRALIZACAO));
		parametros.add(criarParametro(ParametroSRTBCCA.TIPO_AGENDAMENTO, 1));
		parametros.add(criarParametro(ParametroSRTBCCA.MESES, QTD_MESES));
		parametros.add(criarParametro(ParametroSRTBCCA.DIA_DEBITO, DIA_DEBITO));
		parametros.add(criarParametro(ParametroSRTBCCA.NUMERO_CONTA_CORRENTE, NUMERO_CONTA_CORRENTE));
		adicionarParametrosComuns(parametros);
		mensagem.setParametros(parametros);
		mensagem.setIdInstituicao(ID_INSTITUICAO);
		RetornoMensagem retornoMensagem = getTransacao().executarTransacao(mensagem);
		assertTransacaoSucesso(retornoMensagem);
	}

}
