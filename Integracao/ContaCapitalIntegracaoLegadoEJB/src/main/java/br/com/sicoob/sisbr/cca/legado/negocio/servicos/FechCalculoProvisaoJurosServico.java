package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;

/**
* @author Antonio.Genaro
*/
public interface FechCalculoProvisaoJurosServico extends Fechamento{

	void rodar(Integer numCoop) throws BancoobException;

}