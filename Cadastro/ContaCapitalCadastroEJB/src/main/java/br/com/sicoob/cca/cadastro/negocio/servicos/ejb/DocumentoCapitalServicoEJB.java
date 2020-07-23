package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.DocumentoCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.DocumentoCapitalServicoRemote;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.DocumentoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.DocumentoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoDocumentoException;

/**
 *  @author Marco.Nascimento
 */
@Stateless
@Local (DocumentoCapitalServicoLocal.class)
@Remote(DocumentoCapitalServicoRemote.class) 
public class DocumentoCapitalServicoEJB extends ContaCapitalCadastroCrudServicoEJB<DocumentoCapital> implements DocumentoCapitalServicoLocal, DocumentoCapitalServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalCadastroDaoFactory.class)
	private DocumentoCapitalDao documentoCapitalDao;
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.ejb.ContaCapitalCadastroCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalCadastroCrudDaoIF<DocumentoCapital> getDAO() {
		return documentoCapitalDao;
	}
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#excluir(java.io.Serializable)
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		try {
			DocumentoIntegracaoDelegate documentoIntegDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarDocumentoIntegracaoDelegate();
			documentoIntegDelegate.excluirDocumento((Long) chave);
			
			super.excluir(chave);
			
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoDocumentoException(e); 
		}
	}
}