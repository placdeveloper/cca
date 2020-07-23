/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelImpedimentosDesligamentoDTO;

/**
 * Servico RelImpedimentosDesligamentoServico
 */
public interface RelImpedimentosDesligamentoServico extends ContaCapitalRelatoriosServico {

	/**
	 * M�todo que gera relatorio do quadro de pendencias da aprova��o de cadastro de conta capital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioImpedimentosDesligamento(RelImpedimentosDesligamentoDTO dto) throws BancoobException;
	
}
