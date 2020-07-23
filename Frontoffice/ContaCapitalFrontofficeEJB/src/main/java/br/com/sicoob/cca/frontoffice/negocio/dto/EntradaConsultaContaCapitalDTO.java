package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO de entrada para a consulta de conta capital.
 * @author Nairon.Silva
 */
public class EntradaConsultaContaCapitalDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	private static final Integer TIPO_PROCURA_NOME = 1;
	private static final Integer TIPO_PROCURA_CPFCNPJ = 2;
	private static final Integer TIPO_PROCURA_CCA = 3;

	private Integer idInstituicao;
	private Integer tipoProcura;
	private String textoProcura;

	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Integer getTipoProcura() {
		return tipoProcura;
	}
	public void setTipoProcura(Integer tipoProcura) {
		this.tipoProcura = tipoProcura;
	}
	public String getTextoProcura() {
		return textoProcura;
	}
	public void setTextoProcura(String textoProcura) {
		this.textoProcura = textoProcura;
	}
	
	/**
	 * Verifica se o tipo de procura eh por nome.
	 * @return
	 */
	public boolean isTipoProcuraNome() {
		return TIPO_PROCURA_NOME.equals(tipoProcura);
	}
	
	/**
	 * Verifica se o tipo de procura eh por cpf/cnpj.
	 * @return
	 */
	public boolean isTipoProcuraCpfCnpj() {
		return TIPO_PROCURA_CPFCNPJ.equals(tipoProcura);
	}
	
	/**
	 * Verifica se o tipo de procura eh por conta capital.
	 * @return
	 */
	public boolean isTipoProcuraCCA() {
		return TIPO_PROCURA_CCA.equals(tipoProcura);
	}
	
}
