package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelRecolhimentoIrrfDestinacaoJurosDTO;

public interface RelRecolhimentoIrrfDestinacaoJurosServico extends ContaCapitalRelatoriosServico {

	/**
	 * Gerar relatorio recolhimento irrf destinacao juros.
	 *
	 * @param dtoEntrada the dto entrada
	 * @return the object
	 * @throws BancoobException the bancoob exception
	 */
	Object gerarRelatorioRecolhimentoIrrfDestinacaoJuros(RelRecolhimentoIrrfDestinacaoJurosDTO dtoEntrada) throws BancoobException;
}