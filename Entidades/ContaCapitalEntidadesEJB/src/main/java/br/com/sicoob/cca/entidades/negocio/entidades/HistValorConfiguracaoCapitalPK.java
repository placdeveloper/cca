/*
 * 
 */
package br.com.sicoob.cca.entidades.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe HistValorConfiguracaoCapitalPK.
 */
@Embeddable
public class HistValorConfiguracaoCapitalPK extends BancoobChavePrimaria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8766690530324714057L;

	/**
	 * no args constructor 
	 */
	public HistValorConfiguracaoCapitalPK() {
		
	}
		
	/**
	 * @see HistValorConfiguracaoCapitalPK 
	 * @param idValorConfiguracaoCapital
	 * @param dataHoraAtualizacao
	 */
	public HistValorConfiguracaoCapitalPK(ValorConfiguracaoCapital valorConfiguracaoCapital, DateTimeDB dataHoraAtualizacao) {
		this.valorConfiguracaoCapital = valorConfiguracaoCapital;
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	/** O atributo valorConfiguracaoCapital. */
	@ManyToOne(optional = false)
	@JoinColumn(name = "IDVALORCONFIGURACAOCAPITAL", referencedColumnName = "IDVALORCONFIGURACAOCAPITAL")	
	private ValorConfiguracaoCapital valorConfiguracaoCapital;
	
	/** O atributo dataHoraAtualizacao. */
	@Column(name = "DATAHORAATUALIZACAO", nullable = false)
	private DateTimeDB dataHoraAtualizacao;

	/**
	 * Recupera o valor de valorConfiguracaoCapital.
	 *
	 * @return o valor de valorConfiguracaoCapital
	 */
	public ValorConfiguracaoCapital getValorConfiguracaoCapital() {
		return valorConfiguracaoCapital;
	}

	/**
	 * Define o valor de valorConfiguracaoCapital.
	 *
	 * @param valorConfiguracaoCapital o novo valor de valorConfiguracaoCapital
	 */
	public void setValorConfiguracaoCapital(
			ValorConfiguracaoCapital valorConfiguracaoCapital) {
		this.valorConfiguracaoCapital = valorConfiguracaoCapital;
	}

	/**
	 * Recupera o valor de dataHoraAtualizacao.
	 *
	 * @return o valor de dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * Define o valor de dataHoraAtualizacao.
	 *
	 * @param dataHoraAtualizacao o novo valor de dataHoraAtualizacao
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	
}
