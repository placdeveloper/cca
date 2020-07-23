package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.math.BigDecimal;
import java.util.List;

/**
 * Registro de batimento de lancamento
 * @author Nairon.Silva
 */
public class RegistroBatimentoLancamento extends RegistroBatimento<RegistroBatimentoLancamento> {

	private String tabela;
	private Integer idInstituicao;
	private Integer bolAtualizado;
	private Integer total;
	private BigDecimal valorLanc;
	
	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getBolAtualizado() {
		return bolAtualizado;
	}

	public void setBolAtualizado(Integer bolAtualizado) {
		this.bolAtualizado = bolAtualizado;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public BigDecimal getValorLanc() {
		return valorLanc;
	}

	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}

	@Override
	public RegistroBatimentoLancamento procurarCorrespondente(List<RegistroBatimentoLancamento> registros) {
		for (RegistroBatimentoLancamento registro : registros) {
			if (registro.getIdInstituicao().equals(idInstituicao)
					&& registro.getBolAtualizado().equals(bolAtualizado)) {
				return registro;
			}
		}
		return null;
	}

	@Override
	public boolean verificarDivergencia(RegistroBatimentoLancamento registro) {
		registro.setPossuiDivergencia(possuiDiferenca(total, registro.getTotal()) 
				|| possuiDiferencaValor(valorLanc, registro.getValorLanc()));
		return registro.isPossuiDivergencia();
	}

	@Override
	public String toString() {
		return "RegistroBatimentoLancamento [tabela=" + tabela
				+ ", idInstituicao=" + idInstituicao + ", bolAtualizado="
				+ bolAtualizado + ", total=" + total + ", valorLanc="
				+ valorLanc + ", isPossuiDivergencia()="
				+ isPossuiDivergencia() + "]";
	}
	
}
