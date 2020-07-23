/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CaptacaoRemuneradaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CaptacaoRemuneradaIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;
/**
 * A Classe CaptacaoRemuneradaIntegracaoDelegate.
 */
public class CaptacaoRemuneradaIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<CaptacaoRemuneradaIntegracaoServico> {
	/**
	 * Recupera a unica instancia de CaptacaoRemuneradaIntegracaoDelegate.
	 *
	 * @return uma instancia de CaptacaoRemuneradaIntegracaoDelegate
	 */
	public static CaptacaoRemuneradaIntegracaoDelegate getInstance() {
		return new CaptacaoRemuneradaIntegracaoDelegate();
	}	
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected CaptacaoRemuneradaIntegracaoServico localizarServico() {
		return (CaptacaoRemuneradaIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarCaptacaoRemuneradaIntegracaoServico();
	}
		
	/**
	 * listarModalidadeCaptacaoRemunerada
	 * @return
	 * @throws BancoobException
	 */
	public List<ItemListaIntegracaoDTO> listarModalidadeCaptacaoRemunerada() throws BancoobException{
		return getServico().listarModalidadeCaptacaoRemunerada();
	}
	
	/**
	 * incluirCaptacaoRemuneradaIntegracao
	 * @param captacaoRemuneradaIntegracaoDTO
	 * @throws BancoobException
	 */
	public void incluirCaptacaoRemuneradaIntegracao(CaptacaoRemuneradaIntegracaoDTO captacaoRemuneradaIntegracaoDTO) throws BancoobException{
		getServico().incluirCaptacaoRemuneradaIntegracao(captacaoRemuneradaIntegracaoDTO);
		
	}
	
}
