/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cco.api.negocio.enums.AplicativoEnum;

/**
 * A Classe SaldoContaCorrenteIntegracaoDTO.
 */
public class SaldoContaCorrenteIntegracaoDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6572288871658887452L;
	
	/** O atributo dataReferencia. */
	private DateTimeDB dataReferencia;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idUsuario. */
	private String idUsuario;
	
	/** O atributo numClienteCapes. */
	private Long numClienteCapes;
	
	/** O atributo numMesesRetro. */
	private Integer numMesesRetro;
	
	/** O atributo numAno. */
	private Integer numAno;
	
	/** O atributo numMes. */
	private Integer numMes;
	
	/** O atributo idAplicativo. */
	private Integer idAplicativo;
	
	/** O atributo aplicativoEnum. */
	private AplicativoEnum aplicativoEnum;	
	
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

	/**
	 * Recupera o valor de idAplicativo.
	 *
	 * @return o valor de idAplicativo
	 */
	public Integer getIdAplicativo() {
		return idAplicativo;
	}

	/**
	 * Define o valor de idAplicativo.
	 *
	 * @param idAplicativo o novo valor de idAplicativo
	 */
	public void setIdAplicativo(Integer idAplicativo) {
		this.idAplicativo = idAplicativo;
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
	 * Recupera o valor de dataReferencia.
	 *
	 * @return o valor de dataReferencia
	 */
	public DateTimeDB getDataReferencia() {
		if(dataReferencia != null){
			return new DateTimeDB(dataReferencia.getTime());
		}
		return null;
	}
	
	/**
	 * Define o valor de dataReferencia.
	 *
	 * @param dataReferencia o novo valor de dataReferencia
	 */
	public void setDataReferencia(DateTimeDB dataReferencia) {
		if(dataReferencia != null){
			this.dataReferencia = new DateTimeDB(dataReferencia.getTime());
		}
	}
	
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
	 * Recupera o valor de numClienteCapes.
	 *
	 * @return o valor de numClienteCapes
	 */
	public Long getNumClienteCapes() {
		return numClienteCapes;
	}
	
	/**
	 * Define o valor de numClienteCapes.
	 *
	 * @param numClienteCapes o novo valor de numClienteCapes
	 */
	public void setNumClienteCapes(Long numClienteCapes) {
		this.numClienteCapes = numClienteCapes;
	}
	
	/**
	 * Recupera o valor de numMesesRetro.
	 *
	 * @return o valor de numMesesRetro
	 */
	public Integer getNumMesesRetro() {
		return numMesesRetro;
	}
	
	/**
	 * Define o valor de numMesesRetro.
	 *
	 * @param numMesesRetro o novo valor de numMesesRetro
	 */
	public void setNumMesesRetro(Integer numMesesRetro) {
		this.numMesesRetro = numMesesRetro;
	}
	
	/**
	 * Recupera o valor de numAno.
	 *
	 * @return o valor de numAno
	 */
	public Integer getNumAno() {
		return numAno;
	}
	
	/**
	 * Define o valor de numAno.
	 *
	 * @param numAno o novo valor de numAno
	 */
	public void setNumAno(Integer numAno) {
		this.numAno = numAno;
	}
	
	/**
	 * Recupera o valor de numMes.
	 *
	 * @return o valor de numMes
	 */
	public Integer getNumMes() {
		return numMes;
	}
	
	/**
	 * Define o valor de numMes.
	 *
	 * @param numMes o novo valor de numMes
	 */
	public void setNumMes(Integer numMes) {
		this.numMes = numMes;
	}

	
}
