/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConciliacaoContabilLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ConciliacaoContabilLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe ConciliacaoContabilLegadoDelegate.
 */
public class ConciliacaoContabilLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<ConciliacaoContabilLegadoServico> {

	/**
	 * Recupera a unica instancia de ConciliacaoContabilLegadoDelegate.
	 *
	 * @return uma instancia de ConciliacaoContabilLegadoDelegate
	 */
	public static ConciliacaoContabilLegadoDelegate getInstance(){
		return new ConciliacaoContabilLegadoDelegate();
	}	
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ConciliacaoContabilLegadoServico localizarServico() {
		return (ConciliacaoContabilLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarConciliacaoContabilLegadoServico();
	}
	
	/**
	 * Obter lista dados conciliacao contabil.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @param dataLote o valor de data lote
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ConciliacaoContabilLegadoDTO> obterListaDadosConciliacaoContabil(Integer numCooperativa, DateTimeDB dataLote) throws BancoobException{
		return getServico().obterListaDadosConciliacaoContabil(numCooperativa, dataLote);
	}
	
	/**
	 * {@link ConciliacaoContabilLegadoServico#atualizarConciliacaoContabil(Integer, ConciliacaoContabilLegadoDTO)}
	 * @param numCooperativa
	 * @param dto
	 * @throws BancoobException
	 */
	public void atualizarConciliacaoContabil(Integer numCooperativa, ConciliacaoContabilLegadoDTO dto) throws BancoobException {
		getServico().atualizarConciliacaoContabil(numCooperativa, dto);
	}
}
