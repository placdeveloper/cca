/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DocumentoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoDocumentoException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.DocumentoIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * A Classe DocumentoIntegracaoDelegate.
 */
public class DocumentoIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<DocumentoIntegracaoServico> {

	/**
	 * Recupera a unica instancia de DocumentoIntegracaoDelegate.
	 *
	 * @return uma instancia de DocumentoIntegracaoDelegate
	 */
	public static DocumentoIntegracaoDelegate getInstance() {
		return new DocumentoIntegracaoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected DocumentoIntegracaoServico localizarServico() {
		return (DocumentoIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarDocumentoIntegracaoServico();
	}
	
	/**
	 * Recuperar documento.
	 *
	 * @param idDoc o valor de id doc
	 * @return DocumentoIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DocumentoIntegracaoDTO recuperarDocumento(Long idDoc) throws BancoobException {
		return getServico().recuperarDocumento(idDoc);
	}
	
	/**
	 * O método Excluir documento.
	 *
	 * @param idDoc o valor de id doc
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void excluirDocumento(Long idDoc) throws BancoobException {
		try {
			getServico().excluirDocumento(idDoc);
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoDocumentoException(e);
		}
	}
}
