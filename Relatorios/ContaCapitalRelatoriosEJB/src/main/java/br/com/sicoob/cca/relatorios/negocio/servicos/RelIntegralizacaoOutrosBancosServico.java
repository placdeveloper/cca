package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelRemessaIntegralizacaoOutrosBancosDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;

/**
 * Servico RelIntegralizacaoOutrosBancosServico
 */
public interface RelIntegralizacaoOutrosBancosServico extends ContaCapitalRelatoriosServico {

	
	/**
	 * Método que gera relatorio do quadro de pendencias da aprovação de cadastro de conta capital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioRemessaIntegralizacaoOutrosBancosDetalhe(IntegralizacaoOutrosBancosLegadoDTO dtoEntrada) throws BancoobException;
	
}
