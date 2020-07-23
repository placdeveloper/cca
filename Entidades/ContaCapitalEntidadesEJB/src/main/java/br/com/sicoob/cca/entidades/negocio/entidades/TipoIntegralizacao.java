/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Tipo integralização
 * @author marco.nascimento
 * @since 22/05/2014
 */
@Entity
@Table(name = "TIPOINTEGRALIZACAO", schema = "cca")
public class TipoIntegralizacao extends ContaCapitalEntidade<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8215987354485469743L;
	
	
	/**
	 * Construtor Padrão
	 */
	public TipoIntegralizacao() {

	}
	
	/**
	 * Construtor com o ID
	 * @param id
	 */
	public TipoIntegralizacao(Short id) {
		this.id = id;
	}	
	
	/**
	 * Identificador do tipo de integralização.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDTIPOINTEGRALIZACAO")
	private Short id;
	
	/**
	 * Descrição do tipo de integralização.
	 */
	@Column(name = "DESCTIPOINTEGRALIZACAO", length = 100, nullable = false)
	private String descricao;
	
	/**
	 * Identificador da origem de integralizacao de capital
	 */
	@ManyToOne
	@JoinColumn(name = "IDORIGEMINTEGRALIZACAO")
	private OrigemIntegralizacao origemIntegralizacao;

	/**
	 * @return the id
	 */
	public Short getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Short id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the origemIntegralizacao
	 */
	public OrigemIntegralizacao getOrigemIntegralizacao() {
		return origemIntegralizacao;
	}

	/**
	 * @param origemIntegralizacao the origemIntegralizacao to set
	 */
	public void setOrigemIntegralizacao(OrigemIntegralizacao origemIntegralizacao) {
		this.origemIntegralizacao = origemIntegralizacao;
	}
}