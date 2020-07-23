package br.com.sicoob.cca.comum.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;

public interface FechamentoSQL {
	public void rodarSQL(Integer numCoop) throws BancoobException;
}
