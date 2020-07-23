/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.DocumentoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoDocumentoException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.DocumentoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.DocumentoIntegracaoServicoRemote;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiDocumentoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiFabricaDelegates;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.GedApiFabricaFiltros;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.IFiltroMarcarDocumentoExpurgo;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoDocumento;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local (DocumentoIntegracaoServicoLocal.class)
@Remote(DocumentoIntegracaoServicoRemote.class)
public class DocumentoIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements DocumentoIntegracaoServicoLocal, DocumentoIntegracaoServicoRemote {
	
	/**
	 * Delegate API Documento GED
	 */
	private GedApiDocumentoDelegate apiDocDelegate = GedApiFabricaDelegates.getInstance().criarGedApiDocumentoDelegate();

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.DocumentoIntegracaoServico#recuperarDocumento(java.lang.Long)
	 */
	public DocumentoIntegracaoDTO recuperarDocumento(Long idDoc) throws BancoobException {
		
		DocumentoIntegracaoDTO dto = null;
		try {
			
			List<IRetornoDocumento> lst = apiDocDelegate.obterListaArquivosGed(idDoc);
			
			if(lst != null && !lst.isEmpty()) {
				
				dto = new DocumentoIntegracaoDTO();
				for(IRetornoDocumento doc : lst) {
					dto.setDescTipoDoc(doc.getNomeTipoDocumento());
					dto.setIdDocumento(doc.getIdDocumento());
					break;
				}
			}
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoDocumentoException(e);
		}
		
		return dto;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.DocumentoIntegracaoServico#excluirDocumento(java.lang.Long)
	 */
	public void excluirDocumento(Long idDoc) throws BancoobException {
		
		try {
			
			List<Long> idsDocumentos = new ArrayList<Long>(0);
			idsDocumentos.add(idDoc);
			
			IFiltroMarcarDocumentoExpurgo filtro = GedApiFabricaFiltros.getInstance().criarFiltroMarcarDocumentoExpurgo(null, "CAPITALWEB", idsDocumentos, null);
			
			apiDocDelegate.marcaDocumentosExpurgo(filtro);
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoDocumentoException(e);
		}
	}
}