/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoAssociadoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoEncontroContasListaDTO;

/**
 * Interface para trazer os m�todos de opera��es de crud para gerar o 
 * relat�rio de desligamento associado
 * 
 * @author Guilherme.Nunes
 *
 */
public interface RelDesligamentoAssociadoServico extends ContaCapitalRelatoriosServico {

	/**
	 * M�todo que gera relatorio do quadro de pendencias da aprova��o de cadastro de conta capital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioDesligamentoAssociado(RelDesligamentoAssociadoDTO dto) throws BancoobException;
	
	/**
	 * Metodo responsavel de gerar o relatorio de desligamento associado com encontro de contas
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioDesligamentoEncontroContas(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Metodo responsavel de gerar o relatorio de desligamento associado com encontro de contas (lista)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioDesligamentoEncontroContasLista(RelDesligamentoEncontroContasListaDTO dto) throws BancoobException;
}
