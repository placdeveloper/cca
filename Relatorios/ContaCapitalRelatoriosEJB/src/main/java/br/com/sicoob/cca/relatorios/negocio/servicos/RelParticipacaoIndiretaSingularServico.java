/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;

/**
 * Interface para trazer os m�todos de opera��es de crud para gerar o 
 * relat�rio de participa��o indireta
 * 
 * @author Guilherme.Nunes
 *
 */
public interface RelParticipacaoIndiretaSingularServico  extends ContaCapitalRelatoriosServico { 
	
	/**
	 * Listar para cada Central todas as singulares com o valor da singular na central, o percentual de participa��o 
	 * e o valor de participa��o indireta
	 * @param filtroParticipacaoIndiretaBancoob
	 * @return
	 * @throws BancoobException
	 */
	Object emitirRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO filtroParticipacaoIndiretaBancoob) throws BancoobException;
}
