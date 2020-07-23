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
	 * M�todo para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelImpedimentosDesligamentoDelegate() {
	}
	
	/**
	 * M�todo para criar uma instancia do delegate do relat�rio de impedimentos de desligamento.
	 * @return
	 */
	public static RelImpedimentosDesligamentoDelegate getInstance() {
		return new RelImpedimentosDesligamentoDelegate();
	}
	
	/**
	 * M�todo para criar uma instancia do localizador de servico do relat�rio de impedimentos de desligamento.
	 */
	@Override
	protected RelImpedimentosDesligamentoServico localizarServico() {
		return (RelImpedimentosDesligamentoServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelImpedimentosDesligamentoServico();
	}
	
	/**
	 * M�todo respons�vel para gerar relat�rio de impedimentos de desligamento
	 * @param RelImpedimentosDesligamentoDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioImpedimentosDesligamento(RelImpedimentosDesligamentoDTO dto) throws BancoobException {
		return getServico().gerarRelatorioImpedimentosDesligamento(dto);
	}
	
	
}
