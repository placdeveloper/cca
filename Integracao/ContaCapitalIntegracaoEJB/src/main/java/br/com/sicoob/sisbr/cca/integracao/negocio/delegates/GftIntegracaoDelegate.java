/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GftIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class GftIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<GftIntegracaoServico> {
	
	/**
	 * Instancia um novo GftIntegracaoDelegate.
	 */
	GftIntegracaoDelegate() {
		
	}

	/**
	 * Recupera a unica instancia de GftIntegracaoDelegate.
	 *
	 * @return uma instancia de GftIntegracaoDelegate
	 */
	public static GftIntegracaoDelegate getInstance() {
		return new GftIntegracaoDelegate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GftIntegracaoServico localizarServico() {
		return (GftIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarGftIntegracaoServico();
	}

	/**
	 * {@link GftIntegracaoServico#instanciarFluxoAprovacao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)} 
	 */
	public void instanciarFluxoAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		getServico().instanciarFluxoAprovacao(gftIntegracaoDTO);
	}
	
	/**
	 * {@link GftIntegracaoServico#listaProcedimentosAprovacao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)} 
	 */
	public List<ItemListaIntegracaoDTO> listaProcedimentosAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		return getServico().listaProcedimentosAprovacao(gftIntegracaoDTO);
	}
	
	/**
	 * {@link GftIntegracaoServico#executarAtividadeAprovacao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)} 
	 */
	public void executarAtividadeAprovacao(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		getServico().executarAtividadeAprovacao(gftIntegracaoDTO);
	}
	
	/**
	 * {@link GftIntegracaoServico#listarAtividadesPendentes(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)} 
	 */
	public List<GftIntegracaoDTO> listarAtividadesPendentes(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		return getServico().listarAtividadesPendentes(gftIntegracaoDTO);
	}
	
	/**
	 * {@link GftIntegracaoServico#recuperarNomeProcedimento(br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO)} 
	 */
	public String recuperarNomeProcedimento(GftIntegracaoDTO gftIntegracaoDTO) throws BancoobException {
		return getServico().recuperarNomeProcedimento(gftIntegracaoDTO);
	}
	
	/**
	 * {@link GftIntegracaoServico#isProcessoCompleto(Integer, Integer)} 
	 */
	public Boolean isProcessoCompleto(Integer idAtividade, Integer idRegistroControlado) throws BancoobException {
		return getServico().isProcessoCompleto(idAtividade, idRegistroControlado);
	}
}