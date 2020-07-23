/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * Excecao ValorIngressoNaoCadastradoNegocioException
 */
@ApplicationException(rollback = true)
public class ValorIngressoNaoCadastradoNegocioException extends NegocioException {

		/** A constante serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * @param chave
		 */
		public ValorIngressoNaoCadastradoNegocioException() {
			super("MSG_VALOR_INGRESSO_NAO_CAD");
		}
		
		/**
		 * @param excecao
		 */
		public ValorIngressoNaoCadastradoNegocioException(Throwable excecao) {
			super(excecao);
		}	

		/**
		 * @param chave
		 * @param excecao
		 */
		public ValorIngressoNaoCadastradoNegocioException(String chave, Throwable excecao) {
			super(chave, excecao);
		}
		
		/**
		 * @param chave
		 * @param parametros
		 * @param excecao
		 */
		public ValorIngressoNaoCadastradoNegocioException(String chave, Object[] parametros, Throwable excecao) {
			super(chave, parametros, excecao);
		}
		
		/**
		 *  @param mensagem
		 * @param parametros
		 */
		public ValorIngressoNaoCadastradoNegocioException(String chave, Object... parametros) {
			super(chave, parametros);
		}
}