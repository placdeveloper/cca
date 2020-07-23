package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;

/**
 * Interface de servicos para relatorio de bloqueio
 * @author Nairon.Silva
 */
public interface RelBloqueioServico extends ContaCapitalRelatoriosServico {

	/**
	 * Gera o relatorio de historico de bloqueio
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioHistoricoBloqueio(BloqueioContaCapitalDTO dto) throws BancoobException;
	
	/**
	 * Gera o relatorio de bloqueios
	 * @param tipoPesquisa
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioBloqueios(String tipoPesquisa, BloqueioContaCapitalDTO filtro) throws BancoobException;
	
}
