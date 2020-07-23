package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;

public class ConsultaParcelamentosViaCaixaServicoEJBTest extends ContaCapitalFrontofficeEJBTest {

	@InjectMocks
	private ConsultaParcelamentosViaCaixaServicoEJB servico;
	
	@Mock
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoServico;
	
	@Override
	protected Transacao getTransacao() {
		return servico;
	}

	@Override
	protected List<Parametro> criarParametrosTransacao() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.TIPO_PARCELAMENTO, 1));
		return parametros;
	}

	@Override
	protected void prepararTeste() throws BancoobException, ExcecaoTransacao {
		when(parcelamentoServico.pesquisarParcelamentosEmAbertoViaCaixa(any(ParcelamentoCapitalDTO.class))).thenReturn(lstMock());
	}

	private List<ParcelamentoRenDTO> lstMock() {
		short um = (short) 1;
		List<ParcelamentoRenDTO> lst = new ArrayList<ParcelamentoRenDTO>();
		ParcelamentoRenDTO parc = new ParcelamentoRenDTO();
		parc.setNumParcela(um);
		parc.setNumParcelamento(um);
		parc.setValorParcela(BigDecimal.ONE);
		lst.add(parc);
		return lst;
	}

}
