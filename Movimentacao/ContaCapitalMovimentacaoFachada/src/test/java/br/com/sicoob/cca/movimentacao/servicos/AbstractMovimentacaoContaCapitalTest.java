package br.com.sicoob.cca.movimentacao.servicos;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.BloqueioContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.LancamentoContaCapitalDelegate;

/**
 * Classe abstrata para testes. Mocka valores de InformacoesUsuario.
 * @author Nairon.Silva
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ContaCapitalMovimentacaoFabricaDelegates.class, LancamentoContaCapitalDelegate.class, BloqueioContaCapitalDelegate.class,
	ContaCapitalCadastroFabricaDelegates.class, ContaCapitalDelegate.class})
public abstract class AbstractMovimentacaoContaCapitalTest extends Mockito {
	
	protected static final String TEST_ID_INSTITUICAO = "1";
	protected static final String TEST_LOGIN = "Test";
	
	public AbstractMovimentacaoContaCapitalTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void inicializarMocksEstaticos() throws BancoobException {
		InformacoesUsuario info = new InformacoesUsuario();
		info.setIdInstituicao(TEST_ID_INSTITUICAO);
		info.setLogin(TEST_LOGIN);
		InformacoesUsuario.INSTANCIA.set(info);
	}
	
	@Before
	public void mockValoresCalculados() throws BancoobException {
		PowerMockito.mockStatic(ContaCapitalMovimentacaoFabricaDelegates.class);
		ContaCapitalMovimentacaoFabricaDelegates contaCapitalMovimentacaoFabricaDelegates = 
				PowerMockito.mock(ContaCapitalMovimentacaoFabricaDelegates.class);
		PowerMockito.when(ContaCapitalMovimentacaoFabricaDelegates.getInstance()).thenReturn(contaCapitalMovimentacaoFabricaDelegates);
		
		LancamentoContaCapitalDelegate lancamentoContaCapitalDelegate = PowerMockito.mock(LancamentoContaCapitalDelegate.class);
		when(contaCapitalMovimentacaoFabricaDelegates.criarLancamentoContaCapitalDelegate()).thenReturn(lancamentoContaCapitalDelegate);
		final double valorIntegralizado = BigDecimal.TEN.doubleValue();
		final double valorDevolucao = BigDecimal.ZERO.doubleValue();
		PowerMockito.when(lancamentoContaCapitalDelegate.calcularValorSubscrito(anyInt())).thenReturn(BigDecimal.valueOf(valorIntegralizado));
		PowerMockito.when(lancamentoContaCapitalDelegate.calcularValorIntegralizado(anyInt())).thenReturn(BigDecimal.valueOf(valorIntegralizado));
		PowerMockito.when(lancamentoContaCapitalDelegate.calcularValorDevolucao(anyInt())).thenReturn(BigDecimal.valueOf(valorDevolucao));
		
		BloqueioContaCapitalDelegate bloqueioContaCapitalDelegate = PowerMockito.mock(BloqueioContaCapitalDelegate.class);
		when(contaCapitalMovimentacaoFabricaDelegates.criarBloqueioContaCapitalDelegate()).thenReturn(bloqueioContaCapitalDelegate);
		PowerMockito.when(bloqueioContaCapitalDelegate.calcularValorBloqueado(anyInt())).thenReturn(BigDecimal.ONE);
	}

}
