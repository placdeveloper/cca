package br.com.sicoob.sisbr.contacapital.atendimento.fachada.vo;

import java.io.Serializable;

public class OrdenacaoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4326426727348218217L;
	private Integer idOrdenacao;
	private String descOrdenacao;
	public Integer getIdOrdenacao() {
		return idOrdenacao;
	}
	public void setIdOrdenacao(Integer idOrdenacao) {
		this.idOrdenacao = idOrdenacao;
	}
	public String getDescOrdenacao() {
		return descOrdenacao;
	}
	public void setDescOrdenacao(String descOrdenacao) {
		this.descOrdenacao = descOrdenacao;
	}
	
	
}
