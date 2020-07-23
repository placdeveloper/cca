package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelRemessaIntegralizacaoOutrosBancosDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelIntegralizacaoOutrosBancosServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;

public class RelIntegralizacaoOutrosBancosDelegate extends ContaCapitalRelatoriosDelegate<RelIntegralizacaoOutrosBancosServico> {

	/**
	 * Método para garantir que o delegate seja criado apenas pela fabrica de delegates
	 */
	RelIntegralizacaoOutrosBancosDelegate() {
	}
	
	/**
	 * Método para criar uma instancia do delegate do relatório de impedimentos de desligamento.
	 * @return
	 */
	public static RelIntegralizacaoOutrosBancosDelegate getInstance() {
		return new RelIntegralizacaoOutrosBancosDelegate();
	}
	
	/**
	 * Método para criar uma instancia do localizador de servico do relatório de impedimentos de desligamento.
	 */
	@Override
	protected RelIntegralizacaoOutrosBancosServico localizarServico() {
		return (RelIntegralizacaoOutrosBancosServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelIntegralizacaoOutrosBancosServico();
	}
	
	/**
	 * Método responsável para gerar relatório de impedimentos de desligamento
	 * @param RelIntegralizacaoOutrosBancosDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarRelatorioRemessaIntegralizacaoOutrosBancosDetalhe(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		return getServico().gerarRelatorioRemessaIntegralizacaoOutrosBancosDetalhe(dto);
	}
	
}
