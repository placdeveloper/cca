/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelAprovacaoQuadroPendenciaDTO;


/**
 * Interface para trazer os m�todos de opera��es de crud para gerar o 
 * relat�rio de aprovacao quadro pendencia
 * 
 * @author Guilherme.Nunes
 *
 */
public interface RelAprovacaoQuadroPendenciaServico extends ContaCapitalRelatoriosServico {
	
	/**
	 * M�todo que gera relatorio do quadro de pendencias da aprova��o de cadastro de conta capital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioAprovacaoQuadroPendencia(RelAprovacaoQuadroPendenciaDTO dto) throws BancoobException;

}
