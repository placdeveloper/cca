/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;

/**
 * A Interface CapaLoteCapitalLegadoServico.
 */
public interface CapaLoteCapitalLegadoServico extends ContaCapitalIntegracaoLegadoCrudServico<CapaLoteCapitalLegado> {

	/**
	 * Atualiza a capa lote de acordo com o total de lançamentos do dia
	 * @param numCooperativa
	 * @param dataLote
	 * @param numLote
	 * @throws BancoobException
	 */
	void atualizarCapaLote(Integer numCooperativa, DateTimeDB dataLote, Integer numLote) throws BancoobException;
	
}
