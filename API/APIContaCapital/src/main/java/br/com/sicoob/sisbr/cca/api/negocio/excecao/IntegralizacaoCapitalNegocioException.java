/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * Excecao de negocio para integralizacao de capital.
 */
@ApplicationException(rollback=true)
public class IntegralizacaoCapitalNegocioException extends NegocioException{

		/** A constante serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * @param chave
		 */
		public IntegralizacaoCapitalNegocioException(String chave) {
			super(chave);
		}
		
		/**
		 * @param excecao
		 */
		public IntegralizacaoCapitalNegocioException(Throwable excecao) {
			super(excecao);
		}	

		/**
		 * @param chave
		 * @param excecao
		 */
		public IntegralizacaoCapitalNegocioException(String chave, Throwable excecao) {
			super(chave, excecao);
		}
		
		/**
		 * @param chave
		 * @param parametros
		 * @param excecao
		 */
		public IntegralizacaoCapitalNegocioException(String chave, Object[] parametros, Throwable excecao) {
			super(chave, parametros, excecao);
		}
		
		/**
		 *  @param mensagem
		 * @param parametros
		 */
		public IntegralizacaoCapitalNegocioException(String chave, Object... parametros) {
			super(chave, parametros);
		}

	
	
}
