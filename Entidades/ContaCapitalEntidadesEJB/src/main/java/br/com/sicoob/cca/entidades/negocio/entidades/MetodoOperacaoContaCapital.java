package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.negocio.entidades.BancoobEntidade;

@Entity
@Table(name="METODOOPERACAOCONTACAPITAL", schema="CCA")
public class MetodoOperacaoContaCapital extends BancoobEntidade {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrao
	 */
	public MetodoOperacaoContaCapital() {
		
	}
	
	/**
	 * Construtor com ID
	 * @param id
	 */
	public MetodoOperacaoContaCapital(Integer id) {
		this.id = id;
	}
	
	@Id
	@Column(name="IDMETODOOPERACAO")
	private Integer id;
	
	@Column(name="NOMEMETODOOPERACAO")
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "MetodoOperacaoContaCapital [id=" + id + ", nome=" + nome + "]";
	}

}
