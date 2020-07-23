/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParcelamentoContaCapitalDTO;

/**
 * @author Marco.Nascimento
 */
public interface RelParcelamentoContaCapitalServico extends ContaCapitalRelatoriosServico {

	/**
	 * Gera relatorio de parcelamento de conta capital
	 * @param dtoEntrada
	 * @return JasperPrint
	 * @throws BancoobException
	 */
	Object gerarRelatorioParcelamentoContaCapital(RelParcelamentoContaCapitalDTO dtoEntrada) throws BancoobException;
}