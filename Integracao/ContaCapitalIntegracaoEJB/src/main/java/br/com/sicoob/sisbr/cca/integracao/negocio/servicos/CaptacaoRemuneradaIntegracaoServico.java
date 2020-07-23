/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CaptacaoRemuneradaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
/**
 * A Interface CaptacaoRemuneradaIntegracaoServico.
 */
public interface CaptacaoRemuneradaIntegracaoServico extends ContaCapitalIntegracaoServico {


	/**
	 * listarModalidadeCaptacaoRemunerada
	 * @return
	 * @throws BancoobException
	 */
	List<ItemListaIntegracaoDTO> listarModalidadeCaptacaoRemunerada() throws BancoobException;
	
	/**
	 * incluirCaptacaoRemuneradaIntegracao
	 * @param captacaoRemuneradaIntegracaoDTO
	 * @throws BancoobException
	 */
	void incluirCaptacaoRemuneradaIntegracao(CaptacaoRemuneradaIntegracaoDTO captacaoRemuneradaIntegracaoDTO) throws BancoobException;
	

}
