package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;

/**
* @author Ricardo.Barcante
*/
public interface FechOperacoesFinanceirasContabilizacaoLegadoServico extends Fechamento {
	void rodar(Integer numCoop) throws BancoobException;
}
