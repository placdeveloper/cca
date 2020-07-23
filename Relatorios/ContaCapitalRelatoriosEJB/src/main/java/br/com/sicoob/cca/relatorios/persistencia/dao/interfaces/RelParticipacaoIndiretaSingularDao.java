/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParticipacaoIndiretaSingularDTO;

/**
 * Obtem informações de participação indireta da singular no bancoob
 * @author Sron.Cruz
 */
public interface RelParticipacaoIndiretaSingularDao{
	/**
	 * Listar para cada Central todas as singulares com o valor da singular na central, o percentual de participação 
	 * e o valor de participação indireta
	 * @param filtroParticipacaoIndiretaBancoob
	 * @return
	 * @throws BancoobException
	 */
	 List<RelParticipacaoIndiretaSingularDTO> listarRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO filtroParticipacaoIndiretaBancoob)throws BancoobException;

}
