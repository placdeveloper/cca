package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.math.BigDecimal;
import java.util.List;

/**
 * Registro de batimento de conta capital
 * @author Nairon.Silva
 */
public class RegistroBatimentoContaCapital extends RegistroBatimento<RegistroBatimentoContaCapital> {

	private String tabela;
	private Integer idInstituicao;
	private Integer total;
	private BigDecimal valorSaldoAtuSubsc;
	private BigDecimal valorSaldoAtuInteg;
	private BigDecimal valorSaldoAtuDevolver;
	private BigDecimal valorSaldoBloq;
	
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public BigDecimal getValorSaldoAtuSubsc() {
		return valorSaldoAtuSubsc;
	}
	public void setValorSaldoAtuSubsc(BigDecimal valorSaldoAtuSubsc) {
		this.valorSaldoAtuSubsc = valorSaldoAtuSubsc;
	}
	public BigDecimal getValorSaldoAtuInteg() {
		return valorSaldoAtuInteg;
	}
	public void setValorSaldoAtuInteg(BigDecimal valorSaldoAtuInteg) {
		this.valorSaldoAtuInteg = valorSaldoAtuInteg;
	}
	public BigDecimal getValorSaldoAtuDevolver() {
		return valorSaldoAtuDevolver;
	}
	public void setValorSaldoAtuDevolver(BigDecimal valorSaldoAtuDevolver) {
		this.valorSaldoAtuDevolver = valorSaldoAtuDevolver;
	}
	public BigDecimal getValorSaldoBloq() {
		return valorSaldoBloq;
	}
	public void setValorSaldoBloq(BigDecimal valorSaldoBloq) {
		this.valorSaldoBloq = valorSaldoBloq;
	}

	@Override
	public RegistroBatimentoContaCapital procurarCorrespondente(List<RegistroBatimentoContaCapital> registros) {
		for (RegistroBatimentoContaCapital registro : registros) {
			if (registro.getIdInstituicao().equals(idInstituicao)) {
				return registro;
			}
		}
		return null;
	}
	
	@Override
	public boolean verificarDivergencia(RegistroBatimentoContaCapital registro) {
		registro.setPossuiDivergencia(possuiDiferenca(total, registro.getTotal())
			|| possuiDiferencaSaldosAtuais(registro)
			|| possuiDiferencaValor(valorSaldoBloq, registro.getValorSaldoBloq()));
		return registro.isPossuiDivergencia();
	}
	
	private boolean possuiDiferencaSaldosAtuais(RegistroBatimentoContaCapital registro) {
		return possuiDiferencaValor(valorSaldoAtuSubsc, registro.getValorSaldoAtuSubsc()) 
				|| possuiDiferencaValor(valorSaldoAtuInteg, registro.getValorSaldoAtuInteg())
				|| possuiDiferencaValor(valorSaldoAtuDevolver, registro.getValorSaldoAtuDevolver());
	}
	@Override
	public String toString() {
		return "RegistroBatimentoContaCapital [tabela=" + tabela
				+ ", idInstituicao=" + idInstituicao + ", total=" + total
				+ ", valorSaldoAtuSubsc=" + valorSaldoAtuSubsc
				+ ", valorSaldoAtuInteg=" + valorSaldoAtuInteg
				+ ", valorSaldoAtuDevolver=" + valorSaldoAtuDevolver
				+ ", valorSaldoBloq=" + valorSaldoBloq
				+ ", isPossuiDivergencia()=" + isPossuiDivergencia() + "]";
	}
	
}
