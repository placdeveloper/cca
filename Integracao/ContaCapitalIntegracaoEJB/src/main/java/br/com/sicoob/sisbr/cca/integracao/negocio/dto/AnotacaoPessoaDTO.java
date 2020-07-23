/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO AnotacaoPessoaDTO
 */
public class AnotacaoPessoaDTO extends BancoobDto {
	
		/** O atributo id anotacao. */
		private Long idAnotacao;
		
		/** O atributo quantidade. */
		private Short quantidade;
		
		/** O atributo valor. */
		private BigDecimal valor;
		
		/** O atributo observacao. */
		private String observacao;
		
		/** O atributo flexibilidade. */
		private Boolean flexibilidade;
		
		// TIPO ANOTACAO
		/** O atributo codigo tipo anotacao. */
		private Integer codigoTipoAnotacao;
		
		/** O atributo descricao tipo anotacao. */
		private String descricaoTipoAnotacao;
		
		// CATEGORIA TIPO ANOTACAO
		/** O atributo codigo categoria anotacao. */
		private Short codigoCategoriaAnotacao;
		
		/** O atributo descricao categoria anotacao. */
		private String descricaoCategoriaAnotacao;
		
		// ORIGEM INFORMACAO TIPO ANOTACAO
		/** O atributo codigo origem info. */
		private Short codigoOrigemInfo;
		
		/** O atributo descricao origem info. */
		private String descricaoOrigemInfo;

		/** O atributo data hora anotacao. */
		private Date dataHoraAnotacao;
		
		/** O atributo data hora ocorrencia. */
		private Date dataHoraOcorrencia;
		
		/** O atributo data hora baixa. */
		private Date dataHoraBaixa;
		
		/** O atributo id tipo baixa. */
		private Short idTipoBaixa;
		
		/** O atributo desc tipo baixa. */
		private String descricaoTipoBaixa;
		
		/** O Atributo codigo do tipo de consulta **/
		private Integer idTipoConsultaOrigem;
		
		/** O atributo descricao do tipo de consulta **/
		private String descricaoTipoConsultaOrigem;

		/**
		 * @return the idAnotacao
		 */
		public Long getIdAnotacao() {
			return idAnotacao;
		}

		/**
		 * @param idAnotacao the idAnotacao to set
		 */
		public void setIdAnotacao(Long idAnotacao) {
			this.idAnotacao = idAnotacao;
		}

		/**
		 * @return the quantidade
		 */
		public Short getQuantidade() {
			return quantidade;
		}

		/**
		 * @param quantidade the quantidade to set
		 */
		public void setQuantidade(Short quantidade) {
			this.quantidade = quantidade;
		}

		/**
		 * @return the valor
		 */
		public BigDecimal getValor() {
			return valor;
		}

		/**
		 * @param valor the valor to set
		 */
		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		/**
		 * @return the observacao
		 */
		public String getObservacao() {
			return observacao;
		}

		/**
		 * @param observacao the observacao to set
		 */
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		/**
		 * @return the flexibilidade
		 */
		public Boolean getFlexibilidade() {
			return flexibilidade;
		}

		/**
		 * @param flexibilidade the flexibilidade to set
		 */
		public void setFlexibilidade(Boolean flexibilidade) {
			this.flexibilidade = flexibilidade;
		}

		/**
		 * @return the codigoTipoAnotacao
		 */
		public Integer getCodigoTipoAnotacao() {
			return codigoTipoAnotacao;
		}

		/**
		 * @param codigoTipoAnotacao the codigoTipoAnotacao to set
		 */
		public void setCodigoTipoAnotacao(Integer codigoTipoAnotacao) {
			this.codigoTipoAnotacao = codigoTipoAnotacao;
		}

		/**
		 * @return the descricaoTipoAnotacao
		 */
		public String getDescricaoTipoAnotacao() {
			return descricaoTipoAnotacao;
		}

		/**
		 * @param descricaoTipoAnotacao the descricaoTipoAnotacao to set
		 */
		public void setDescricaoTipoAnotacao(String descricaoTipoAnotacao) {
			this.descricaoTipoAnotacao = descricaoTipoAnotacao;
		}

		/**
		 * @return the codigoCategoriaAnotacao
		 */
		public Short getCodigoCategoriaAnotacao() {
			return codigoCategoriaAnotacao;
		}

		/**
		 * @param codigoCategoriaAnotacao the codigoCategoriaAnotacao to set
		 */
		public void setCodigoCategoriaAnotacao(Short codigoCategoriaAnotacao) {
			this.codigoCategoriaAnotacao = codigoCategoriaAnotacao;
		}

		/**
		 * @return the descricaoCategoriaAnotacao
		 */
		public String getDescricaoCategoriaAnotacao() {
			return descricaoCategoriaAnotacao;
		}

		/**
		 * @param descricaoCategoriaAnotacao the descricaoCategoriaAnotacao to set
		 */
		public void setDescricaoCategoriaAnotacao(String descricaoCategoriaAnotacao) {
			this.descricaoCategoriaAnotacao = descricaoCategoriaAnotacao;
		}

		/**
		 * @return the codigoOrigemInfo
		 */
		public Short getCodigoOrigemInfo() {
			return codigoOrigemInfo;
		}

		/**
		 * @param codigoOrigemInfo the codigoOrigemInfo to set
		 */
		public void setCodigoOrigemInfo(Short codigoOrigemInfo) {
			this.codigoOrigemInfo = codigoOrigemInfo;
		}

		/**
		 * @return the descricaoOrigemInfo
		 */
		public String getDescricaoOrigemInfo() {
			return descricaoOrigemInfo;
		}

		/**
		 * @param descricaoOrigemInfo the descricaoOrigemInfo to set
		 */
		public void setDescricaoOrigemInfo(String descricaoOrigemInfo) {
			this.descricaoOrigemInfo = descricaoOrigemInfo;
		}

		/**
		 * @return the dataHoraAnotacao
		 */
		public Date getDataHoraAnotacao() {
			if(dataHoraAnotacao != null){
				return new Date(dataHoraAnotacao.getTime());
			}
			return null;
		}

		/**
		 * @param dataHoraAnotacao the dataHoraAnotacao to set
		 */
		public void setDataHoraAnotacao(Date dataHoraAnotacao) {
			if(dataHoraAnotacao != null){
				this.dataHoraAnotacao = new Date(dataHoraAnotacao.getTime());
			} else{
				this.dataHoraAnotacao = null;
			}
		}
		
		/**
		 * @return the dataHoraOcorrencia
		 */
		public Date getDataHoraOcorrencia() {
			if(dataHoraOcorrencia != null){
				return new Date(dataHoraOcorrencia.getTime());
			}
			return null;
		}

		/**
		 * @param dataHoraOcorrencia the dataHoraOcorrencia to set
		 */
		public void setDataHoraOcorrencia(Date dataHoraOcorrencia) {
			if(dataHoraOcorrencia != null){
				this.dataHoraOcorrencia = new Date(dataHoraOcorrencia.getTime());
			} else{
				this.dataHoraOcorrencia = null;
			}
		}

		/**
		 * @return the dataHoraBaixa
		 */
		public Date getDataHoraBaixa() {
			if(dataHoraBaixa != null){
				return new Date(dataHoraBaixa.getTime());
			}
			return null;
		}

		/**
		 * @param dataHoraBaixa the dataHoraBaixa to set
		 */
		public void setDataHoraBaixa(Date dataHoraBaixa) {
			if(dataHoraBaixa != null){
				this.dataHoraBaixa = new Date(dataHoraBaixa.getTime());
			} else{
				this.dataHoraBaixa = null;
			}
		}

		/**
		 * @return the idTipoBaixa
		 */
		public Short getIdTipoBaixa() {
			return idTipoBaixa;
		}

		/**
		 * @param idTipoBaixa the idTipoBaixa to set
		 */
		public void setIdTipoBaixa(Short idTipoBaixa) {
			this.idTipoBaixa = idTipoBaixa;
		}

		/**
		 * @return the descricaoTipoBaixa
		 */
		public String getDescricaoTipoBaixa() {
			return descricaoTipoBaixa;
		}

		/**
		 * @param descricaoTipoBaixa the descricaoTipoBaixa to set
		 */
		public void setDescricaoTipoBaixa(String descricaoTipoBaixa) {
			this.descricaoTipoBaixa = descricaoTipoBaixa;
		}

		/**
		 * @return the idTipoConsultaOrigem
		 */
		public Integer getIdTipoConsultaOrigem() {
			return idTipoConsultaOrigem;
		}

		/**
		 * @param idTipoConsultaOrigem the idTipoConsultaOrigem to set
		 */
		public void setIdTipoConsultaOrigem(Integer idTipoConsultaOrigem) {
			this.idTipoConsultaOrigem = idTipoConsultaOrigem;
		}

		/**
		 * @return the descricaoTipoConsultaOrigem
		 */
		public String getDescricaoTipoConsultaOrigem() {
			return descricaoTipoConsultaOrigem;
		}

		/**
		 * @param descricaoTipoConsultaOrigem the descricaoTipoConsultaOrigem to set
		 */
		public void setDescricaoTipoConsultaOrigem(String descricaoTipoConsultaOrigem) {
			this.descricaoTipoConsultaOrigem = descricaoTipoConsultaOrigem;
		}
	
	
	
}