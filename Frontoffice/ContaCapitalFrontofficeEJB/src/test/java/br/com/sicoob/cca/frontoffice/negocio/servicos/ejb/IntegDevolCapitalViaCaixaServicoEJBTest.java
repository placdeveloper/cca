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
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoIntegralizacaoExternaServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalExternoServicoLocal;
import br.com.sicoob.tipos.DateTime;

public class IntegDevolCapitalViaCaixaServicoEJBTest extends ContaCapitalFrontofficeEJBTest {

	@InjectMocks
	private IntegDevolCapitalViaCaixaServicoEJB servico;
	
	@Mock
	private ParcelamentoContaCapitalExternoServicoLocal parcelamentoServico;
	
	@Mock
	private LancamentoIntegralizacaoExternaServicoLocal lancamentoServico;
	
	@Mock
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@Override
	protected Transacao getTransacao() {
		return servico;
	}

	@Override
	protected List<Parametro> criarParametrosTransacao() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.IDENTIFICADOR, "1-21856-33-1-10.00"));
		return parametros;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void prepararTeste() throws BancoobException, ExcecaoTransacao {
		doNothing().when(parcelamentoServico).atualizarParcelamentos(anyInt(), anyList(), anyInt());
		when(parcelamentoServico.pesquisarParcelamentosEmAbertoViaCaixa(any(ParcelamentoCapitalDTO.class))).thenReturn(lstMock());
		when(contaCapitalServico.obterResumo(anyInt(), anyInt())).thenReturn(new ContaCapitalResumoDTO());
	}
	
	private List<ParcelamentoRenDTO> lstMock() {
		short um = (short) 1;
		List<ParcelamentoRenDTO> lst = new ArrayList<ParcelamentoRenDTO>();
		ParcelamentoRenDTO parc = new ParcelamentoRenDTO();
		parc.setNumParcela(um);
		parc.setNumParcelamento(um);
		parc.setValorParcela(BigDecimal.TEN);
		parc.setIdSituacaoParcelamento(um);
		parc.setIdTipoInteg(um);
		parc.setDataVencimento(new DateTime());
		lst.add(parc);
		return lst;
	}

}
