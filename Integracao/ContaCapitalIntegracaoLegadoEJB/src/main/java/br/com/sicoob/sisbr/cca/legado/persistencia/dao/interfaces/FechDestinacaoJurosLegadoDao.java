package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import br.com.bancoob.excecao.BancoobException;

/**
* @author Antonio.Genaro
*/
public interface FechDestinacaoJurosLegadoDao {
	
	void gerarLancamentoProvisaoJuros(Integer numCoop, String idUsuario) throws BancoobException;

	void gerarLancamentoDestinacaoJuros(Integer numCoop, String idUsuario) throws BancoobException;	
	
}