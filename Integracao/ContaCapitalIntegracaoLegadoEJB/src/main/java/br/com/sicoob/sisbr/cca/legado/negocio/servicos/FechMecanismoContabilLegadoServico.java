package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;

/**
 * @author ricardo.barcante
 */
public interface FechMecanismoContabilLegadoServico extends Fechamento {
	
	/**
	 * Realiza o fechamento de Mecanismo Contabil por número de cooperativa
	 * @param numCoop
	 * @throws BancoobException
	 */
	void rodar(Integer numCoop) throws BancoobException;
}
