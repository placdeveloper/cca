/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParticipacaoIndiretaSingularDTO;

/**
 * Obtem informa��es de participa��o indireta da singular no bancoob
 * @author Sron.Cruz
 */
public interface RelParticipacaoIndiretaSingularDao{
	/**
	 * Listar para cada Central todas as singulares com o valor da singular na central, o percentual de participa��o 
	 * e o valor de participa��o indireta
	 * @param filtroParticipacaoIndiretaBancoob
	 * @return
	 * @throws BancoobException
	 */
	 List<RelParticipacaoIndiretaSingularDTO> listarRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO filtroParticipacaoIndiretaBancoob)throws BancoobException;

}
