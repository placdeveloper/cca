/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * Documentos (GED) referentes a conta capital
 * @author marco.nascimento
 * @since 25/11/2015
 */
@Entity
@Table(name = "DOCUMENTOCAPITAL", schema = "cca")
public class DocumentoCapital extends ContaCapitalEntidade<Long> {
	
	/** O atributo id. */
	@Id
	@Column(name = "IDDOCUMENTO", nullable = false)
	private Long id;
	
	/** O atributo contaCapital. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCONTACAPITAL", referencedColumnName = "IDCONTACAPITAL")
	private ContaCapital contaCapital;
	
	/** O atributo tipoDocumento. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDTIPODOCUMENTOCAPITAL", referencedColumnName = "IDTIPODOCUMENTOCAPITAL")
	private TipoDocumentoCapital tipoDocumento;
	
	/** O atributo nome. */
	@Column(name = "NOMEDOCUMENTOCAPITAL", length = 100, nullable = false)
	private String nome;
	
	/** O atributo idUsuario. */
	@Column(name = "IDUSUARIO", length = 40, nullable = false)
	private String idUsuario;
	
	/** O atributo dataHoraAtualizacao. */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the contaCapital
	 */
	public ContaCapital getContaCapital() {
		return contaCapital;
	}

	/**
	 * @param contaCapital the contaCapital to set
	 */
	public void setContaCapital(ContaCapital contaCapital) {
		this.contaCapital = contaCapital;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumentoCapital getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoCapital tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * @param dataHoraAtualizacao the dataHoraAtualizacao to set
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
}