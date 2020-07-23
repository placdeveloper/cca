/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe TipoGrauCooperativaDTO.
 */
public class TipoGrauCooperativaDTO extends BancoobDto  {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7128994116353132706L;
	
	/** O atributo codTipoGrauCoop. */
	private Integer codTipoGrauCoop;
	
	/** A constante TIPO_GRAU_INSTITUICAO_CENTRAL. */
	private static final Integer TIPO_GRAU_INSTITUICAO_CENTRAL = 1;
	
	/** A constante TIPO_GRAU_INSTITUICAO_SINGULAR. */
	private static final Integer TIPO_GRAU_INSTITUICAO_SINGULAR = 2;
	
	/** A constante TIPO_GRAU_INSTITUICAO_CONFEDERACAO. */
	private static final Integer TIPO_GRAU_INSTITUICAO_CONFEDERACAO = 3;
	
	/**
	 * Indica se a cooperativa em questao é uma Central
	 * @return
	 */
	public Boolean isCentral(){
		return TIPO_GRAU_INSTITUICAO_CENTRAL.equals(codTipoGrauCoop);
	}
	
	/**
	 * Indica se a cooperativa em questao é uma Singular
	 * @return
	 */
	public Boolean isSingular() {
		return TIPO_GRAU_INSTITUICAO_SINGULAR.equals(codTipoGrauCoop);
	}
	
	/**
	 * Indica se a cooperativa em questao é uma Confederacao
	 * @return
	 */
	public Boolean isConfederacao() {
		return TIPO_GRAU_INSTITUICAO_CONFEDERACAO.equals(codTipoGrauCoop);
	}
	
	/**
	 * Recupera o valor de codTipoGrauCoop.
	 *
	 * @return o valor de codTipoGrauCoop
	 */
	public Integer getCodTipoGrauCoop() {
		return codTipoGrauCoop;
	}
	
	/**
	 * Define o valor de codTipoGrauCoop.
	 *
	 * @param codTipoGrauCoop o novo valor de codTipoGrauCoop
	 */
	public void setCodTipoGrauCoop(Integer codTipoGrauCoop) {
		this.codTipoGrauCoop = codTipoGrauCoop;
	}
	
}