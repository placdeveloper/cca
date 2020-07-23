package br.com.sicoob.cca.movimentacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.FechamentoSQL;

/**
* @author Antonio.Genaro
*/
public interface FechDestinacaoJurosServico extends Fechamento, FechamentoSQL {
	void rodar(Integer numCoop) throws BancoobException;	
	void rodarSQL(Integer numCoop) throws BancoobException;
}