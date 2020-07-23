/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;

/**
 * Interface para trazer os métodos de operações de crud para gerar o 
 * relatório de participação indireta
 * 
 * @author Guilherme.Nunes
 *
 */
public interface RelParticipacaoIndiretaSingularServico  extends ContaCapitalRelatoriosServico { 
	
	/**
	 * Listar para cada Central todas as singulares com o valor da singular na central, o percentual de participação 
	 * e o valor de participação indireta
	 * @param filtroParticipacaoIndiretaBancoob
	 * @return
	 * @throws BancoobException
	 */
	Object emitirRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO filtroParticipacaoIndiretaBancoob) throws BancoobException;
}
