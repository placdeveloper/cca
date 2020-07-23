package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TabelaIRRFLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.TabelaIRRFLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe TabelaIRRFLegadoDelegate.
 */
public class TabelaIRRFLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<TabelaIRRFLegadoServico> {

	/**
	 * Recupera a unica instancia de TabelaIRRFLegadoDelegate.
	 *
	 * @return uma instancia de TabelaIRRFLegadoDelegate
	 */
	public static TabelaIRRFLegadoDelegate getInstance() {
		return new TabelaIRRFLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected TabelaIRRFLegadoServico localizarServico() {
		return (TabelaIRRFLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarTabelaIRRFLegadoServico();
	}
	
	/**
	 * {@link TabelaIRRFLegadoServico#consultarPorAnoBase(Integer)}
	 */
	public List<TabelaIRRFLegadoDTO> consultarPorAnoBase(Integer anoBase) throws BancoobException {
		return getServico().consultarPorAnoBase(anoBase);
	}
	
	/**
	 * {@link TabelaIRRFLegadoServico#incluir(List)}
	 */
	public void incluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException {
		getServico().incluir(lstTabelaIRRF);
	}
	
	/**
	 * {@link TabelaIRRFLegadoServico#excluir(List)}
	 */
	public void excluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException {
		getServico().excluir(lstTabelaIRRF);
	}
}