/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DocumentoIntegracaoDTO;

/**
 * A Interface DocumentoIntegracaoServico.
 */
public interface DocumentoIntegracaoServico extends ContaCapitalIntegracaoServico {
	
	/**
	 * Recupera documento em questao
	 * @param idDoc
	 * @return
	 * @throws BancoobException
	 */
	DocumentoIntegracaoDTO recuperarDocumento(Long idDoc) throws BancoobException;
	
	/**
	 * Marca documento para expurgo
	 * @param idDoc
	 * @throws BancoobException
	 */
	void excluirDocumento(Long idDoc) throws BancoobException;
}