/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe LancamentosCCapitalLegadoDelegate.
 */
public class LancamentosCCapitalLegadoDelegate extends ContaCapitalIntegracaoLegadoCrudDelegate<LancamentosCCapitalLegado, LancamentosCCapitalLegadoServico> {
	
	/**
	 * Recupera a unica instancia de LancamentosCCapitalLegadoDelegate.
	 *
	 * @return uma instancia de LancamentosCCapitalLegadoDelegate
	 */
	public static LancamentosCCapitalLegadoDelegate getInstance(){
		return new LancamentosCCapitalLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected LancamentosCCapitalLegadoServico localizarServico() {
		return (LancamentosCCapitalLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarLancamentosCCapitalLegadoServico();
	}	

	/**
	 * Obter ultimo num seq lanc.
	 *
	 * @param capaLoteCapitalLegado o valor de capa lote capital legado
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer obterUltimoNumSeqLanc(CapaLoteCapitalLegado capaLoteCapitalLegado) throws BancoobException{
		return getServico().obterUltimoNumSeqLanc(capaLoteCapitalLegado);
	}

	/**
	 * {@link LancamentosCCapitalLegadoServico#incluirEmLote(List)}
	 * 
	 * @param lancamentosLegado
	 * @return
	 * @throws BancoobException
	 */
	public List<LancamentosCCapitalLegado> incluirEmLote(List<LancamentosCCapitalLegado> lancamentosLegado) throws BancoobException {
		return getServico().incluirEmLote(lancamentosLegado);
	}

}
