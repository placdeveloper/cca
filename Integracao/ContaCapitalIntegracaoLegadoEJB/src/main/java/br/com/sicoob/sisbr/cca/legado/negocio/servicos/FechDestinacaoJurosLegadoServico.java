package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
* @author Antonio.Genaro
*/
public interface FechDestinacaoJurosLegadoServico {

	void gerarLancamentoProvisaoJuros(Integer numCoop, String idUsuario) throws BancoobException;

	void gerarLancamentoDestinacaoJuros(Integer numCoop, String idUsuario) throws BancoobException;
	
}