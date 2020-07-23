/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DadosConciliacaoContabilIntegracaoDTO;

/**
 * A Interface ContabilidadeIntegracaoServico.
 */
public interface ContabilidadeIntegracaoServico extends ContaCapitalIntegracaoServico {
	
	/**
	 * Realiza inclusão de conciliação contabil
	 * @param lst
	 * @throws BancoobException
	 */
	void incluirConciliacaoContabil(List<DadosConciliacaoContabilIntegracaoDTO> lst) throws BancoobException;
}