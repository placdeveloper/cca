package br.com.sicoob.cca.comum.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;

public interface Fechamento {
	public void rodar(Integer numCoop) throws BancoobException;
}
