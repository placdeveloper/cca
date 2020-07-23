/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import br.com.sicoob.cca.cadastro.negocio.servicos.DocumentoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;

/**
 * @author Marco.Nascimento
 */
public class DocumentoCapitalDelegate extends ContaCapitalCadastroCrudDelegate<DocumentoCapital, DocumentoCapitalServico> {

	/**
	 * Instancia um novo DocumentoCapitalDelegate.
	 */
	DocumentoCapitalDelegate() {
		
	}

	/**
	 * Locator DocumentoCapitalServico
	 */
	@Override
	protected DocumentoCapitalServico localizarServico() {
		return (DocumentoCapitalServico) ContaCapitalCadastroServiceLocator.getInstance().localizarDocumentoCapitalServico();
	}
}