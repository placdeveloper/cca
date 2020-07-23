/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParticipacaoIndiretaBancoobLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.InformacaoAcumuladaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * Delegate informacao acumulada legado
 * @author Marco.Nascimento
 * @since 06/06/2014
 */
public class InformacaoAcumuladaLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<InformacaoAcumuladaLegadoServico> {
	
	/** O atributo instance. */
	private static InformacaoAcumuladaLegadoDelegate instance;

	/**
	 * Instancia de InformacaoAcumuladaLegadoDelegate
	 */
	public static InformacaoAcumuladaLegadoDelegate getInstance() {
		if(instance == null) {
			instance = new InformacaoAcumuladaLegadoDelegate();
		}
		return instance;
	}
	
	/**
	 * {@link ContaCapitalIntegracaoLegadoServiceLocator#localizarInformacaoAcumuladaLegadoServico()}
	 */
	@Override
	protected InformacaoAcumuladaLegadoServico localizarServico() {
		return (InformacaoAcumuladaLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarInformacaoAcumuladaLegadoServico();
	}
	
}