package br.com.sicoob.cca.frontoffice.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.PesquisaContaCapitalServicoLocal;
import br.com.sicoob.cca.frontoffice.negocio.util.ParametroSRTBCCA;

public class ConsultaContaCapitalServicoEJBTest extends ContaCapitalFrontofficeEJBTest {

	@InjectMocks
	private ConsultaContaCapitalServicoEJB servico;
	
	@Mock
	private PesquisaContaCapitalServicoLocal pesquisaServico;
	
	@Override
	protected Transacao getTransacao() {
		return servico;
	}

	@Override
	protected List<Parametro> criarParametrosTransacao() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(criarParametro(ParametroSRTBCCA.TIPO_PESQUISA, 1));
		parametros.add(criarParametro(ParametroSRTBCCA.CHAVE_PESQUISA, "Teste"));
		return parametros;
	}

	@Override
	protected void prepararTeste() throws BancoobException, ExcecaoTransacao {
		when(pesquisaServico.pesquisar(any(PesquisaContaCapitalDTO.class))).thenReturn(lstMock());
	}

	private List<PesquisaContaCapitalDTO> lstMock() {
		List<PesquisaContaCapitalDTO> lst = new ArrayList<PesquisaContaCapitalDTO>();
		PesquisaContaCapitalDTO mock = new PesquisaContaCapitalDTO();
		lst.add(mock);
		return lst;
	}

}
