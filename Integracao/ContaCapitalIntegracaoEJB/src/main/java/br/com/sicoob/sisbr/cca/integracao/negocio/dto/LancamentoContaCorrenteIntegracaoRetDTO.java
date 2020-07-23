/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe LancamentoContaCorrenteIntegracaoRetDTO.
 */
public class LancamentoContaCorrenteIntegracaoRetDTO extends BancoobDto {

	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8084829621551941234L;

	/** O atributo numSeqLanc. */
	private Integer numSeqLanc;

	/** O atributo codRetorno. */
	private Integer codRetorno;
	
	/** O atributo mensagem. */
	private String mensagem;
	
	/** O atributo campoErro. */
	private String campoErro;
	
	/** O atributo codErroRetorno. */
	private String codErroRetorno;

	/**
	 * Recupera o valor de numSeqLanc.
	 *
	 * @return o valor de numSeqLanc
	 */
	public Integer getNumSeqLanc() {
		return numSeqLanc;
	}

	/**
	 * Define o valor de numSeqLanc.
	 *
	 * @param numSeqLanc o novo valor de numSeqLanc
	 */
	public void setNumSeqLanc(Integer numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}

	/**
	 * Recupera o valor de codRetorno.
	 *
	 * @return o valor de codRetorno
	 */
	public Integer getCodRetorno() {
		return codRetorno;
	}

	/**
	 * Define o valor de codRetorno.
	 *
	 * @param codRetorno o novo valor de codRetorno
	 */
	public void setCodRetorno(Integer codRetorno) {
		this.codRetorno = codRetorno;
	}

	/**
	 * Recupera o valor de mensagem.
	 *
	 * @return o valor de mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * Define o valor de mensagem.
	 *
	 * @param mensagem o novo valor de mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * Recupera o valor de campoErro.
	 *
	 * @return o valor de campoErro
	 */
	public String getCampoErro() {
		return campoErro;
	}

	/**
	 * Define o valor de campoErro.
	 *
	 * @param campoErro o novo valor de campoErro
	 */
	public void setCampoErro(String campoErro) {
		this.campoErro = campoErro;
	}

	/**
	 * Recupera o valor de codErroRetorno.
	 *
	 * @return o valor de codErroRetorno
	 */
	public String getCodErroRetorno() {
		return codErroRetorno;
	}

	/**
	 * Define o valor de codErroRetorno.
	 *
	 * @param codErroRetorno o novo valor de codErroRetorno
	 */
	public void setCodErroRetorno(String codErroRetorno) {
		this.codErroRetorno = codErroRetorno;
	}
	
}
