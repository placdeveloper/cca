package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.negocio.entidades.BancoobEntidade;

/**
 * ResultadoOperacaoContaCapital
 * @author Nairon.Silva
 */
@Entity
@Table(name="RESULTADOOPERACAOCONTACAPITAL", schema="CCA")
public class ResultadoOperacaoContaCapital extends BancoobEntidade {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor padrao
	 */
	public ResultadoOperacaoContaCapital() {
		
	}
	
	/**
	 * Construtor com ID
	 * @param id
	 */
	public ResultadoOperacaoContaCapital(Integer id) {
		this.id = id;
	}
	
	@Id
	@Column(name="IDRESULTADOOPERACAO")
	private Integer id;
	
	@Column(name="NOMERESULTADOOPERACAO")
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
		return "ResultadoOperacaoContaCapital [id=" + id + ", nome=" + nome + "]";
	}

}
