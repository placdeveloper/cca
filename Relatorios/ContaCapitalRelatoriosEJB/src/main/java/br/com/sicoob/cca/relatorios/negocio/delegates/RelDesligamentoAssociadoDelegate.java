/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoAssociadoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoEncontroContasListaDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelDesligamentoAssociadoServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * Delegate RelDesligamentoAssociadoDelegate
 */
public class RelDesligamentoAssociadoDelegate extends ContaCapitalRelatoriosDelegate<RelDesligamentoAssociadoServico> {

	/**
	 * M�todo para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelDesligamentoAssociadoDelegate() {
	}
	
	/**
	 * M�todo para criar uma instancia do delegate do relat�rio de desligamento associado.
	 * @return
	 */
	public static RelDesligamentoAssociadoDelegate getInstance() {
		return new RelDesligamentoAssociadoDelegate();
	}
	
	/**
	 * M�todo para criar uma instancia do localizador de servico do relat�rio de desligamento de associado.
	 */
	@Override
	protected RelDesligamentoAssociadoServico localizarServico() {
		return (RelDesligamentoAssociadoServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelDesligamentoAssociadoServico();
	}

	/**
	 * M�todo respons�vel para gerar relat�rio de desligamento associado
	 * @param RelAprovacaoQuadroPendenciaDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioDesligamentoAssociado(RelDesligamentoAssociadoDTO dto) throws BancoobException {
		return getServico().gerarRelatorioDesligamentoAssociado(dto);
	}
	
	/**
	 * Metodo responsavel de gerar o relatorio de desligamento associado com encontro de contas
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioDesligamentoEncontroContas(Integer idContaCapital) throws BancoobException {
		return getServico().gerarRelatorioDesligamentoEncontroContas(idContaCapital);
	}
	
	/**
	 * Metodo responsavel de gerar o relatorio de desligamento associado com encontro de contas (lista)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioDesligamentoEncontroContasLista(RelDesligamentoEncontroContasListaDTO dto) throws BancoobException {
		return getServico().gerarRelatorioDesligamentoEncontroContasLista(dto);
	}
	
}
