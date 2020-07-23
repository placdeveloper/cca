package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.frontoffice.negocio.util.ParcelamentoCCAPKHelper;
import br.com.sicoob.cca.movimentacao.negocio.dto.CancelamentoParcelamentoDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;

public class CancelamentoIntegralizacaoAgendadaServicoEJBTest extends ContaCapitalFrontofficeEJBTest {

	@InjectMocks
	private CancelamentoIntegralizacaoAgendadaServicoEJB cancelamentoIntegralizacaoAgendadaEJB;
	
	@Mock
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoContaCapitalExternoServico;
	
	@Mock
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoIntegralizacaoExternaServico;
	
	private static final Double NUMERO_CONTA_CORRENTE = 1234D;
	
	@Override
	protected Transacao getTransacao() {
		return cancelamentoIntegralizacaoAgendadaEJB;
	}

	@Override
	protected List<Parametro> criarParametrosTransacao() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		String identificadores = criarIdentificadoresMock();
		parametros.add(criarParametro(ParametroSRTBCCA.IDENTIFICADOR, identificadores));
		parametros.add(criarParametro(ParametroSRTBCCA.NUMERO_CONTA_CORRENTE, NUMERO_CONTA_CORRENTE));
		return parametros;
	}

	private String criarIdentificadoresMock() {
		List<String> pks = new ArrayList<String>();
		for (int i=0; i<3; i++) {
			ParcelamentoCCALegadoPK pkObj = criarPKObjMock(i);
			pks.add(ParcelamentoCCAPKHelper.toStringPK(pkObj));
		}
		return StringUtils.join(pks.toArray(), "|");
	}

	private ParcelamentoCCALegadoPK criarPKObjMock(int i) {
		ParcelamentoCCALegadoPK pkObj = new ParcelamentoCCALegadoPK();
		pkObj.setNumParcela(i);
		pkObj.setNumParcelamento(1);
		pkObj.setCodTipoParcelamento(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getCodigo());
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();
		contaCapitalLegado.setNumMatricula(NUM_MATRICULA);
		pkObj.setContaCapitalLegado(contaCapitalLegado);
		return pkObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void prepararTeste() throws BancoobException, ExcecaoTransacao {
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				List<CancelamentoParcelamentoDTO> dtos = (List<CancelamentoParcelamentoDTO>) args[0];
				for (CancelamentoParcelamentoDTO dto : dtos) {
					DateTimeDB dateTimeDB = new DateTimeDB();
					dto.setDataSituacao(dateTimeDB);
					dto.setDataVencimento(dateTimeDB);
					dto.setValorParcela(BigDecimal.TEN);
				}
				return null;
			}
		}).when(parcelamentoContaCapitalExternoServico).cancelarParcelamentos(anyList());
		when(lancamentoIntegralizacaoExternaServico.incluir(any(IntegralizacaoCapitalDTO.class))).thenReturn(null);
	}

}
