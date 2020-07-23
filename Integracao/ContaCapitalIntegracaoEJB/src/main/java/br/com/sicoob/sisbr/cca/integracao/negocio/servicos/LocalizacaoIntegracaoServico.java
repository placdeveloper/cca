/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;

/**
 * A Interface LocalizacaoIntegracaoServico.
 */
public interface LocalizacaoIntegracaoServico extends ContaCapitalIntegracaoServico {
	
	/**
	 * Consultar localidade.
	 *
	 * @param idLoc o valor de id loc
	 * @return LocalizacaoIntegracaoDTO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	LocalizacaoIntegracaoDTO consultarLocalidade (Integer idLoc) throws BancoobException;
}
