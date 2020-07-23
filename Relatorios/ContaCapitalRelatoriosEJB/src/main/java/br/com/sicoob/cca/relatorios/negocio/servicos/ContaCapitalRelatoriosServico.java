/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.BancoobServico;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;

/**
 * Contrato padrao de servicos dos relatorios
 * @author gesin1
 */
public interface ContaCapitalRelatoriosServico extends BancoobServico {

	/**
	 * Configura os parametros comuns para nova API.
	 * @param rDto
	 * @throws BancoobException
	 */
	void configurarParametrosComuns(RelatorioDadosDTO rDto) throws BancoobException;
	
}
