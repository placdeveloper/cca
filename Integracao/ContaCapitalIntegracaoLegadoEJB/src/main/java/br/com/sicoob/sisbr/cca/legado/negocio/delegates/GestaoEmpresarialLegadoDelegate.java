/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.GestaoEmpresarialLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe GestaoEmpresarialLegadoDelegate.
 */
public class GestaoEmpresarialLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<GestaoEmpresarialLegadoServico> {

	/**
	 * Recupera a unica instancia de GestaoEmpresarialLegadoDelegate.
	 *
	 * @return uma instancia de GestaoEmpresarialLegadoDelegate
	 */
	public static GestaoEmpresarialLegadoDelegate getInstance() {
		return new GestaoEmpresarialLegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected GestaoEmpresarialLegadoServico localizarServico() {
		return (GestaoEmpresarialLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarGestaoEmpresarialLegadoServico();
	}
	
	/**
	 * {@link GestaoEmpresarialLegadoServico#gerarExtratoDIRF(Integer, java.util.Date)}
	 */
	public List<GestaoEmpresarialLegadoDTO> gerarExtratoDIRF(Integer numCoop, Date dataInicio) throws BancoobException {
		return getServico().gerarExtratoDIRF(numCoop, dataInicio);
	}
	
	/**
	 * {@link GestaoEmpresarialLegadoServico#novosLancamentosDIRF(Integer, Date)}
	 */
	public Boolean novosLancamentosDIRF(Integer numCoop, Date data) throws BancoobException {
		return getServico().novosLancamentosDIRF(numCoop, data);
	}
}