/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelParticipacaoIndiretaSingularServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * Delegate participação indireta 
 * @author Sron.Cruz
 *
 */
public class RelParticipacaoIndiretaSingularDelegate extends ContaCapitalRelatoriosDelegate<RelParticipacaoIndiretaSingularServico>{
	/**
	 * Instancia da RelParticipacaoIndiretaSingularDelegate
	 */
	public static RelParticipacaoIndiretaSingularDelegate getInstance() {
		return new RelParticipacaoIndiretaSingularDelegate();
	}
	/**
	 * {@link ContaCapitalRelatoriosServiceLocator#localizarRelParticipacaoIndiretaSingularServico()
	 */
	@Override
	protected RelParticipacaoIndiretaSingularServico localizarServico() {
		return(RelParticipacaoIndiretaSingularServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelParticipacaoIndiretaSingularServico();
	}
	/**
	 * {@link RelParticipacaoIndiretaSingularServico#emitirRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO)
	 * @param filtroParticipacaoIndiretaBancoob
	 * @return
	 * @throws BancoobException
	 */
	public Object emitirRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO filtroParticipacaoIndiretaBancoob) throws BancoobException{
		return getServico().emitirRelParticipacaoIndireta(filtroParticipacaoIndiretaBancoob);
	}
}