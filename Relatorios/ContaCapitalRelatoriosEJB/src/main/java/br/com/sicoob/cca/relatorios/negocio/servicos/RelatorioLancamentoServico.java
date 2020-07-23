package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;

/**
 * @author Kleber Alves
 */
public interface RelatorioLancamentoServico extends ContaCapitalRelatoriosServico, Fechamento {

	void rodar(Integer numeroCooperativa) throws BancoobException;
}