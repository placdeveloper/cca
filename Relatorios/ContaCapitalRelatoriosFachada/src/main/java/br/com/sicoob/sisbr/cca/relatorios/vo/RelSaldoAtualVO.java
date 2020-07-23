package br.com.sicoob.sisbr.cca.relatorios.vo;

import br.com.bancoob.negocio.vo.BancoobVo;


/**
 * The Class RelSaldoAtualVO.
 */
public class RelSaldoAtualVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	/** The id instituicao. */
	private Integer idInstituicao;
	
	/** The num conta capital inicial. */
	private Integer numContaCapitalInicial;
	
	/** The num conta capital final. */
	private Integer numContaCapitalFinal;
	
	/** The situacao conta. */
	private Integer situacaoConta;	
	
	/** The cadastros aprovados. */
	private Boolean cadastrosAprovados;
	
	/** The num PA. */
	private Integer numPA;
	
	/** The ordenacao. */
	private Integer ordenacao;
	
	/** The empresa. */
	private Integer empresa;
	
	/** The agrupar por PA. */
	private Boolean agruparPorPA;
	
	/**
	 * Gets the id instituicao.
	 *
	 * @return the id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Sets the id instituicao.
	 *
	 * @param idInstituicao the new id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Gets the num conta capital inicial.
	 *
	 * @return the num conta capital inicial
	 */
	public Integer getNumContaCapitalInicial() {
		return numContaCapitalInicial;
	}
	
	
	/**
	 * Sets the num conta capital inicial.
	 *
	 * @param numContaCapitalInicial the new num conta capital inicial
	 */
	public void setNumContaCapitalInicial(Integer numContaCapitalInicial) {
		this.numContaCapitalInicial = numContaCapitalInicial;
	}
	
	/**
	 * Gets the num conta capital final.
	 *
	 * @return the num conta capital final
	 */
	public Integer getNumContaCapitalFinal() {
		return numContaCapitalFinal;
	}
	
	/**
	 * Sets the num conta capital final.
	 *
	 * @param numContaCapitalFinal the new num conta capital final
	 */
	public void setNumContaCapitalFinal(Integer numContaCapitalFinal) {
		this.numContaCapitalFinal = numContaCapitalFinal;
	}
	
	/**
	 * Gets the situacao conta.
	 *
	 * @return the situacao conta
	 */
	public Integer getSituacaoConta() {
		return situacaoConta;
	}
	
	/**
	 * Sets the situacao conta.
	 *
	 * @param situacaoConta the new situacao conta
	 */
	public void setSituacaoConta(Integer situacaoConta) {
		this.situacaoConta = situacaoConta;
	}
	
	/**
	 * Gets the cadastros aprovados.
	 *
	 * @return the cadastros aprovados
	 */
	public Boolean getCadastrosAprovados() {
		return cadastrosAprovados;
	}
	
	/**
	 * Sets the cadastros aprovados.
	 *
	 * @param cadastrosAprovados the new cadastros aprovados
	 */
	public void setCadastrosAprovados(Boolean cadastrosAprovados) {
		this.cadastrosAprovados = cadastrosAprovados;
	}
	
	/**
	 * Gets the num PA.
	 *
	 * @return the num PA
	 */
	public Integer getNumPA() {
		return numPA;
	}
	
	/**
	 * Sets the num PA.
	 *
	 * @param numPA the new num PA
	 */
	public void setNumPA(Integer numPA) {
		this.numPA = numPA;
	}
	
	/**
	 * Gets the ordenacao.
	 *
	 * @return the ordenacao
	 */
	public Integer getOrdenacao() {
		return ordenacao;
	}
	
	/**
	 * Sets the ordenacao.
	 *
	 * @param ordenacao the new ordenacao
	 */
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}
	
	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public Integer getEmpresa() {
		return empresa;
	}
	
	/**
	 * Sets the empresa.
	 *
	 * @param empresa the new empresa
	 */
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	
	/**
	 * Gets the agrupar por PA.
	 *
	 * @return the agrupar por PA
	 */
	public Boolean getAgruparPorPA() {
		return agruparPorPA;
	}
	
	/**
	 * Sets the agrupar por PA.
	 *
	 * @param agruparPorPA the new agrupar por PA
	 */
	public void setAgruparPorPA(Boolean agruparPorPA) {
		this.agruparPorPA = agruparPorPA;
	}
}