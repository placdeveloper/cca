package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;

public interface FechGerarInfoCalculoVarLegadoDao {
	void gerarInfoCalculoVar(Integer numCoop) throws BancoobException;

	Integer obterValorParametro(Integer numCoop, int idParametro) throws BancoobException;

	boolean fechOrdemProcessoRowCountIsZero(Integer numCoop) throws BancoobException;
}