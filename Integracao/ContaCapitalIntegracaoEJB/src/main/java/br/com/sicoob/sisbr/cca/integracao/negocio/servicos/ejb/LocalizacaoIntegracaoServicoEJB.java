/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.LocalizacaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.LocalizacaoIntegracaoServicoRemote;
import br.com.sicoob.sisbr.localidade.api.filtro.retorno.ILocApiLocalidadeRet;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiFabricaDelegates;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiLocalidadeDelegate;

/**
 * EJB contendo servicos relacionados a LocalizacaoIntegracao.
 */
@Stateless
@Local (LocalizacaoIntegracaoServicoLocal.class)
@Remote(LocalizacaoIntegracaoServicoRemote.class)
public class LocalizacaoIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements LocalizacaoIntegracaoServicoLocal, LocalizacaoIntegracaoServicoRemote {

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.LocalizacaoIntegracaoServico#consultarLocalidade(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public LocalizacaoIntegracaoDTO consultarLocalidade(Integer idLoc) throws BancoobException {
		
		LocApiLocalidadeDelegate loc = LocApiFabricaDelegates.getInstancia().criarLocApiLocalidadeDelegate();
		LocalizacaoIntegracaoDTO dto = new LocalizacaoIntegracaoDTO();
		
		ILocApiLocalidadeRet locRet = loc.obterLocalidade(idLoc);
		
		dto.setUf(locRet.getSiglaUF());
		dto.setMunicipio(locRet.getNomeLocalidade());
		
		return dto;
	}
}
