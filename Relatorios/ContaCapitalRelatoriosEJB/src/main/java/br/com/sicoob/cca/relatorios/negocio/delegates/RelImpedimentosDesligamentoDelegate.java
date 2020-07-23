/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelImpedimentosDesligamentoDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelImpedimentosDesligamentoServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * Delegate para relatorio de impedimentos de desligamento.
 */
public class RelImpedimentosDesligamentoDelegate extends ContaCapitalRelatoriosDelegate<RelImpedimentosDesligamentoServico> {

	
	/**
	 * Método para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelImpedimentosDesligamentoDelegate() {
	}
	
	/**
	 * Método para criar uma instancia do delegate do relatório de impedimentos de desligamento.
	 * @return
	 */
	public static RelImpedimentosDesligamentoDelegate getInstance() {
		return new RelImpedimentosDesligamentoDelegate();
	}
	
	/**
	 * Método para criar uma instancia do localizador de servico do relatório de impedimentos de desligamento.
	 */
	@Override
	protected RelImpedimentosDesligamentoServico localizarServico() {
		return (RelImpedimentosDesligamentoServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelImpedimentosDesligamentoServico();
	}
	
	/**
	 * Método responsável para gerar relatório de impedimentos de desligamento
	 * @param RelImpedimentosDesligamentoDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioImpedimentosDesligamento(RelImpedimentosDesligamentoDTO dto) throws BancoobException {
		return getServico().gerarRelatorioImpedimentosDesligamento(dto);
	}
	
	
}
