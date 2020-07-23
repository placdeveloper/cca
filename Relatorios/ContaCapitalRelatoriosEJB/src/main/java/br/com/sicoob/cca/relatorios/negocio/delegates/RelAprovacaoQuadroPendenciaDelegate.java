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
	 * M�todo para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelAprovacaoQuadroPendenciaDelegate() {
	}
	
	/**
	 * M�todo para criar uma instancia do delegate do relat�rio de aprovacao de quadro de pendencia.
	 * @return
	 */
	public static RelAprovacaoQuadroPendenciaDelegate getInstance() {
		return new RelAprovacaoQuadroPendenciaDelegate();
	}
	
	/**
	 * M�todo para criar uma instancia do localizador de servico do relat�rio de aprovacao de quadro de pendencia.
	 */
	@Override
	protected RelAprovacaoQuadroPendenciaServico localizarServico() {
		return (RelAprovacaoQuadroPendenciaServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelAprovacaoQuadroPendenciaServico();
	}
	
	/**
	 * M�todo respons�vel para gerar relat�rio de aprovacao de quadro pendencia
	 * @param RelAprovacaoQuadroPendenciaDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioAprovacaoQuadroPendencia(RelAprovacaoQuadroPendenciaDTO dto) throws BancoobException {
		return getServico().gerarRelatorioAprovacaoQuadroPendencia(dto);
	}
}
