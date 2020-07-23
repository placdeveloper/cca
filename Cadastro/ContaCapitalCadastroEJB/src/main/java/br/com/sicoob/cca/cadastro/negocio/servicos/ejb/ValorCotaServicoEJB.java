package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorConfiguracaoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorCotaServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ValorCotaServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local(ValorCotaServicoLocal.class)
@Remote(ValorCotaServicoRemote.class)
public class ValorCotaServicoEJB extends ContaCapitalCadastroServicoEJB implements ValorCotaServicoLocal, ValorCotaServicoRemote {
	
	/**
	 * 1575 [COTA] VALOR DE CADA COTA DE CAPITAL
	 */
	private static final Integer VALOR_COTA = 1575;
	
	/**
	 * 1576 [COTA] QUANTIDADE MÍNIMA DE COTAS - PF
	 */
	private static final Integer QTD_MIN_COTA_PF = 1576;
	
	/**
	 * 1577 [COTA] QUANTIDADE MÍNIMA DE COTAS - PJ
	 */
	private static final Integer QTD_MIN_COTA_PJ = 1577;
	
	/**
	 * 1578 [SUBSCRIÇÃO] QUANTIDADE MÁXIMA DE PARCELAS
	 */
	private static final Integer QTD_MAX_PAR = 1578;
	
	/**
	 * 1581 [INTEGRALIZAÇÃO] VALOR MÍNIMO DE INTEGRALIZAÇÃO À VISTA
	 */
	private static final Integer VLR_MIN_INTEG = 1581;
	
	@EJB
	private CapesIntegracaoServicoLocal capesInt;
	
	@EJB
	private ValorConfiguracaoCapitalServicoLocal vlrConfServico;

	/**
	 * {@link ValorCotaServicoRemote#obterValorMinimoSubscricao(Integer, Integer)}
	 */
	public BigDecimal obterValorMinimoSubscricao(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		
		String vlrCota =  vlrConfServico.obterValorConfiguracao(VALOR_COTA, idInstituicao).getValorConfiguracao();
		
		String qtdMinCota = null;
		
		if(idPessoa != null && capesInt.isPessoaJuridica(idPessoa, idInstituicao)) {
			qtdMinCota = vlrConfServico.obterValorConfiguracao(QTD_MIN_COTA_PJ, idInstituicao).getValorConfiguracao();
		} else {
			qtdMinCota = vlrConfServico.obterValorConfiguracao(QTD_MIN_COTA_PF, idInstituicao).getValorConfiguracao();
		}
		
		return new BigDecimal(qtdMinCota).multiply(new BigDecimal(vlrCota));
	}
	
	/**
	 * {@link ValorCotaServicoRemote#obterValorMinimoIntegralizacao(Integer)}
	 */
	public BigDecimal obterValorMinimoIntegralizacao(Integer idInstituicao) throws BancoobException {
		
		String vlrMinInteg =  vlrConfServico.obterValorConfiguracao(VLR_MIN_INTEG, idInstituicao).getValorConfiguracao();
		
		return new BigDecimal(vlrMinInteg);
	}

	/**
	 * {@link ValorCotaServicoRemote#obterQtdMaxParcela(Integer)}
	 */
	public Integer obterQtdMaxParcela(Integer idInstituicao) throws BancoobException {
		String qtdMaxPar =  vlrConfServico.obterValorConfiguracao(QTD_MAX_PAR, idInstituicao).getValorConfiguracao();
		
		return Integer.valueOf(qtdMaxPar);
	}
	
	/**
	 * {@link ValorCotaServicoRemote#obterValorCota(Integer)}
	 */
	public BigDecimal obterValorCota(Integer idInstituicao) throws BancoobException {
		return new BigDecimal(vlrConfServico.obterValorConfiguracao(VALOR_COTA, idInstituicao).getValorConfiguracao());
	}
	
	/**
	 * {@link ValorCotaServicoRemote#obterQtdMinCota(Integer)}
	 */
	public Integer obterQtdMinCota(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		String qtdMinCota = null;
		
		if(idPessoa != null && capesInt.isPessoaJuridica(idPessoa, idInstituicao)) {
			qtdMinCota = vlrConfServico.obterValorConfiguracao(QTD_MIN_COTA_PJ, idInstituicao).getValorConfiguracao();
		} else {
			qtdMinCota = vlrConfServico.obterValorConfiguracao(QTD_MIN_COTA_PF, idInstituicao).getValorConfiguracao();
		}
		
		return Integer.valueOf(qtdMinCota);
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ValorCotaServico#obterQtdMinCotaPF(java.lang.Integer)
	 */
	public Integer obterQtdMinCotaPF(Integer idInstituicao) throws BancoobException {
		return Integer.valueOf(vlrConfServico.obterValorConfiguracao(QTD_MIN_COTA_PF, idInstituicao).getValorConfiguracao());
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ValorCotaServico#obterQtdMinCotaPJ(java.lang.Integer)
	 */
	public Integer obterQtdMinCotaPJ(Integer idInstituicao) throws BancoobException {
		return Integer.valueOf(vlrConfServico.obterValorConfiguracao(QTD_MIN_COTA_PJ, idInstituicao).getValorConfiguracao());
	}
	
}