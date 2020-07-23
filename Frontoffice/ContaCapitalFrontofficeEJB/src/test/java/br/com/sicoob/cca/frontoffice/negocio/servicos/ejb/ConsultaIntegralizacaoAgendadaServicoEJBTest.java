package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoParcelamento;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ParcelamentoCCALegadoServicoLocal;

public class ConsultaIntegralizacaoAgendadaServicoEJBTest extends ContaCapitalFrontofficeEJBTest {
	
	@InjectMocks
	private ConsultaIntegralizacaoAgendadaServicoEJB consultaIntegralizacaoAgendadaEJB;
	
	@Mock
	private ParcelamentoCCALegadoServicoLocal parcelamentoCCALegadoServico;

	@Override
	protected Transacao getTransacao() {
		return consultaIntegralizacaoAgendadaEJB;
	}

	@Override
	protected List<Parametro> criarParametrosTransacao() {
		return new ArrayList<Parametro>();
	}

	@Override
	protected void prepararTeste() throws BancoobException, ExcecaoTransacao {
		when(parcelamentoCCALegadoServico.obterParcelasEmAbertoPelosCanais(anyInt(), anyInt())).thenReturn(lstParcelamentosMock());
	}

	private List<ParcelamentoCCALegado> lstParcelamentosMock() {
		List<ParcelamentoCCALegado> lst = new ArrayList<ParcelamentoCCALegado>();
		for (int i=0; i<5; i++) {
			lst.add(criarParcelamento(i));
		}
		return lst;
	}

	private ParcelamentoCCALegado criarParcelamento(int parcela) {
		ParcelamentoCCALegado parcelamento = new ParcelamentoCCALegado();
		ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = new ParcelamentoCCALegadoPK();
		ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();
		contaCapitalLegado.setNumMatricula(NUM_MATRICULA);
		parcelamentoCCALegadoPK.setContaCapitalLegado(contaCapitalLegado);
		parcelamentoCCALegadoPK.setNumParcela(parcela);
		parcelamentoCCALegadoPK.setNumParcelamento(parcela);
		parcelamentoCCALegadoPK.setCodTipoParcelamento(EnumTipoParcelamento.COD_TIPO_PARCELAMENTO_INTEGRAL.getCodigo());
		parcelamento.setParcelamentoCCALegadoPK(parcelamentoCCALegadoPK);
		parcelamento.setCodCanal(1);
		parcelamento.setDataSituacaoParcela(new DateTimeDB(LocalDate.now().toDate().getTime()));
		parcelamento.setDataVencParcela(new DateTimeDB(LocalDate.now().plusMonths(1 + parcela).toDate().getTime()));
		parcelamento.setValorParcela(BigDecimal.TEN);
		return parcelamento;
	}

}
