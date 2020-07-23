package br.com.sicoob.sisbr.cca.relatorios.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
* @author Ricardo.Barcante
*/
public class FechRelatoriosVO extends BancoobVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 164202461L;
	
	private Integer codRelatorio;
	private String descricao;
	private String finalidade;
	private String complemento;
	public Integer getCodRelatorio() {
		return codRelatorio;
	}
	public void setCodRelatorio(Integer codRelatorio) {
		this.codRelatorio = codRelatorio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFinalidade() {
		return finalidade;
	}
	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	

}
