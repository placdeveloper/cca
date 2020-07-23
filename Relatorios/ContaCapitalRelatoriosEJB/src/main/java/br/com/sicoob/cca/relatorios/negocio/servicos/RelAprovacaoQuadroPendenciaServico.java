/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelAprovacaoQuadroPendenciaDTO;


/**
 * Interface para trazer os métodos de operações de crud para gerar o 
 * relatório de aprovacao quadro pendencia
 * 
 * @author Guilherme.Nunes
 *
 */
public interface RelAprovacaoQuadroPendenciaServico extends ContaCapitalRelatoriosServico {
	
	/**
	 * Método que gera relatorio do quadro de pendencias da aprovação de cadastro de conta capital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioAprovacaoQuadroPendencia(RelAprovacaoQuadroPendenciaDTO dto) throws BancoobException;

}
