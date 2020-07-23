package br.com.sicoob.sisbr.cca.relatorios.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class RelRecolhimentoIrrfDestinacaoJurosVO extends BancoobVo{
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1205652103265461L;
	
	/** The id instituicao. */
	private Integer idInstituicao;
	
	/** The todos. */
	private Boolean todos;
	
	/** The num conta capital. */
	private Integer numContaCapital;
	
	/** The situacao conta. */
	private Integer anoBase;	
	
	/** The num PA. */
	private Integer numPA;

	/** The agrupar por PA. */
	private Boolean agruparPorPA;
	
	/** The ordenacao. */
	private Integer ordenarPor;

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Boolean getTodos() {
		return todos;
	}

	public void setTodos(Boolean todos) {
		this.todos = todos;
	}

	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	public Integer getAnoBase() {
		return anoBase;
	}

	public void setAnoBase(Integer anoBase) {
		this.anoBase = anoBase;
	}

	public Integer getNumPA() {
		return numPA;
	}

	public void setNumPA(Integer numPA) {
		this.numPA = numPA;
	}

	public Boolean getAgruparPorPA() {
		return agruparPorPA;
	}

	public void setAgruparPorPA(Boolean agruparPorPA) {
		this.agruparPorPA = agruparPorPA;
	}

	public Integer getOrdenarPor() {
		return ordenarPor;
	}

	public void setOrdenarPor(Integer ordenarPor) {
		this.ordenarPor = ordenarPor;
	}
	
	
}
