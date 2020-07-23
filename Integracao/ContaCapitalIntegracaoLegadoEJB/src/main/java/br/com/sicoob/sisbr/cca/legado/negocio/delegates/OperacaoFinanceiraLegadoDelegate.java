/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.OperacaoFinanceiraLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.OperacaoFinanceiraLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * Delegate operacao financeira (legado)
 * @author Marco.Nascimento
 */
public class OperacaoFinanceiraLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<OperacaoFinanceiraLegadoServico> {
	
	/** O atributo instance. */
	private static OperacaoFinanceiraLegadoDelegate instance;

	/**
	 * Instancia de OperacaoFinanceiraLegadoDelegate
	 */
	public static OperacaoFinanceiraLegadoDelegate getInstance() {
		if(instance == null) {
			instance = new OperacaoFinanceiraLegadoDelegate();
		}
		return instance;
	}
	
	/**
	 * {@link ContaCapitalIntegracaoLegadoServiceLocator#localizarOperacaoFinanceiraLegadoServico()}
	 */
	@Override
	protected OperacaoFinanceiraLegadoServico localizarServico() {
		return (OperacaoFinanceiraLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarOperacaoFinanceiraLegadoServico();
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoServico#incluir(OperacaoFinanceiraLegadoDTO)}
	 * @throws BancoobException
	 */
	public void incluir(OperacaoFinanceiraLegadoDTO of) throws BancoobException {
		getServico().incluir(of);
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoServico#getUltimoId()}
	 * @return
	 * @throws BancoobException
	 */
	public Integer getUltimoId(Integer numCoop) throws BancoobException {
		return getServico().getUltimoId(numCoop);
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoServico#consultarValorEstorno()}
	 * @return
	 * @throws BancoobException
	 */
	public BigDecimal consultarValorEstorno(Integer numCoop) throws BancoobException {
		return getServico().consultarValorEstorno(numCoop);
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoServico#existeOperacaoFinanceiraPI(Integer)}
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	public Boolean existeOperacaoFinanceiraPI(Integer numCoop) throws BancoobException {
		return getServico().existeOperacaoFinanceiraPI(numCoop);
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoServico#valorParticipacaoCentralPorAnoMes(Integer,Integer)}
	 * @param numCoop
	 * @param anoMes
	 * @return
	 */
	public BigDecimal consultarValorParticipacaoCentralPorAnoMes(Integer numCoop, Integer anoMes) throws BancoobException {
		return getServico().valorParticipacaoCentralPorAnoMes(numCoop, anoMes);
	}
}