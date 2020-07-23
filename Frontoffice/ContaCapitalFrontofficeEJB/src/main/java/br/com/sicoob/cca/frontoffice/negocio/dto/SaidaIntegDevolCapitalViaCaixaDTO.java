package br.com.sicoob.cca.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * DTO de saida para integralizacao e devolucao de capital via caixa.
 * @author Nairon.Silva
 */
public class SaidaIntegDevolCapitalViaCaixaDTO extends BancoobDto {

	private static final long serialVersionUID = 1L;
	
	@AtributoRetorno(posicao=1)
	private Integer indice;
	
	@AtributoRetorno(posicao=2)
	private Integer numParcelamento;
	
	@AtributoRetorno(posicao=3)
	private Integer numParcela;
	
	@AtributoRetorno(posicao=4)
	private String valorParcela;
	
	@AtributoRetorno(posicao=5)
	private String dataVencimento;

	public Integer getNumParcelamento() {
		return numParcelamento;
	}

	public void setNumParcelamento(Integer numParcelamento) {
		this.numParcelamento = numParcelamento;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

}
