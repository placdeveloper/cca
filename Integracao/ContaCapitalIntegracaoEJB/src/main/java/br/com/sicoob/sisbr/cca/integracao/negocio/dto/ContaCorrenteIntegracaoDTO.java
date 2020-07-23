/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.cco.api.negocio.enums.AplicativoEnum;

/**
 * A Classe ContaCorrenteIntegracaoDTO.
 */
@SuppressWarnings("deprecation")
public class ContaCorrenteIntegracaoDTO extends BancoobDto {
		
	
		/**
	 * 
	 */
		private static final long serialVersionUID = -3640184043616900837L;
		
		/** O atributo numContaCorrente. */
		private Long numContaCorrente;
		
		/** O atributo idInstituicao. */
		private Integer idInstituicao;
		
		/** O atributo idUsuario. */
		private String idUsuario;
		
		/** O atributo idPessoa. */
		private Integer idPessoa;
		
		/** O atributo aplicativoEnum. */
		private AplicativoEnum aplicativoEnum = AplicativoEnum.COBRANCA_ADMINISTRATIVA;
		
		/**
		 * Recupera o valor de numContaCorrente.
		 *
		 * @return o valor de numContaCorrente
		 */
		public Long getNumContaCorrente() {
			return numContaCorrente;
		}
		
		/**
		 * Define o valor de numContaCorrente.
		 *
		 * @param numContaCorrente o novo valor de numContaCorrente
		 */
		public void setNumContaCorrente(Long numContaCorrente) {
			this.numContaCorrente = numContaCorrente;
		}
		
		/**
		 * Recupera o valor de idInstituicao.
		 *
		 * @return o valor de idInstituicao
		 */
		public Integer getIdInstituicao() {
			return idInstituicao;
		}
		
		/**
		 * Define o valor de idInstituicao.
		 *
		 * @param idInstituicao o novo valor de idInstituicao
		 */
		public void setIdInstituicao(Integer idInstituicao) {
			this.idInstituicao = idInstituicao;
		}
		
		/**
		 * Recupera o valor de idUsuario.
		 *
		 * @return o valor de idUsuario
		 */
		public String getIdUsuario() {
			return idUsuario;
		}
		
		/**
		 * Define o valor de idUsuario.
		 *
		 * @param idUsuario o novo valor de idUsuario
		 */
		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}
		
		/**
		 * Recupera o valor de idPessoa.
		 *
		 * @return o valor de idPessoa
		 */
		public Integer getIdPessoa() {
			return idPessoa;
		}
		
		/**
		 * Define o valor de idPessoa.
		 *
		 * @param idPessoa o novo valor de idPessoa
		 */
		public void setIdPessoa(Integer idPessoa) {
			this.idPessoa = idPessoa;
		}
		
		/**
		 * Recupera o valor de aplicativoEnum.
		 *
		 * @return o valor de aplicativoEnum
		 */
		public AplicativoEnum getAplicativoEnum() {
			return aplicativoEnum;
		}
		
		/**
		 * Define o valor de aplicativoEnum.
		 *
		 * @param aplicativoEnum o novo valor de aplicativoEnum
		 */
		public void setAplicativoEnum(AplicativoEnum aplicativoEnum) {
			this.aplicativoEnum = aplicativoEnum;
		}
		
	
		
		
}