/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.vo;

import br.com.bancoob.negocio.vo.BancoobVo;


/**
 * A Classe DocumentoCapitalVO.
 */
public class DocumentoCapitalVO extends BancoobVo {
	
	private static final long serialVersionUID = 3756325662817142401L;

	/** O atributo idDocumento. */
	private Long idDocumento;
	
	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo idUsuario. */
	private String idUsuario;
	
	/** O atributo dataHoraAtualizacao. */
	private String dataHoraAtualizacao;
	
	/**
	 * @return the idDocumento
	 */
	public Long getIdDocumento() {
		return idDocumento;
	}
	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	/**
	 * @return the idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	/**
	 * @param idContaCapital the idContaCapital to set
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the dataHoraAtualizacao
	 */
	public String getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	/**
	 * @param dataHoraAtualizacao the dataHoraAtualizacao to set
	 */
	public void setDataHoraAtualizacao(String dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
}
