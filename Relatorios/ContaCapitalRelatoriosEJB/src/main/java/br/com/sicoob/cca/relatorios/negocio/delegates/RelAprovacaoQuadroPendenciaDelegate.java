/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelAprovacaoQuadroPendenciaDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelAprovacaoQuadroPendenciaServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * Delegate RelAprovacaoQuadroPendenciaDelegate
 */
public class RelAprovacaoQuadroPendenciaDelegate extends ContaCapitalRelatoriosDelegate<RelAprovacaoQuadroPendenciaServico> {

	
	/**
	 * Método para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelAprovacaoQuadroPendenciaDelegate() {
	}
	
	/**
	 * Método para criar uma instancia do delegate do relatório de aprovacao de quadro de pendencia.
	 * @return
	 */
	public static RelAprovacaoQuadroPendenciaDelegate getInstance() {
		return new RelAprovacaoQuadroPendenciaDelegate();
	}
	
	/**
	 * Método para criar uma instancia do localizador de servico do relatório de aprovacao de quadro de pendencia.
	 */
	@Override
	protected RelAprovacaoQuadroPendenciaServico localizarServico() {
		return (RelAprovacaoQuadroPendenciaServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelAprovacaoQuadroPendenciaServico();
	}
	
	/**
	 * Método responsável para gerar relatório de aprovacao de quadro pendencia
	 * @param RelAprovacaoQuadroPendenciaDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioAprovacaoQuadroPendencia(RelAprovacaoQuadroPendenciaDTO dto) throws BancoobException {
		return getServico().gerarRelatorioAprovacaoQuadroPendencia(dto);
	}
}
