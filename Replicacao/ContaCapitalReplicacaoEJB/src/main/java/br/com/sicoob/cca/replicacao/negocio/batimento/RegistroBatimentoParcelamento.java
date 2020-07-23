package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.math.BigDecimal;
import java.util.List;

/**
 * Registro de batimento de parcelamento.
 * @author Nairon.Silva
 */
public class RegistroBatimentoParcelamento extends RegistroBatimento<RegistroBatimentoParcelamento> {

	private String tabela;
	private Integer idInstituicao;
	private String tipoParcelamento;
	private String situacao;
	private Integer total;
	private BigDecimal valor;
	
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

	public String getTipoParcelamento() {
		return tipoParcelamento;
	}

	public void setTipoParcelamento(String tipoParcelamento) {
		this.tipoParcelamento = tipoParcelamento;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public RegistroBatimentoParcelamento procurarCorrespondente(List<RegistroBatimentoParcelamento> registros) {
		for (RegistroBatimentoParcelamento registro : registros) {
			if (registro.getIdInstituicao().equals(idInstituicao)
					&& registro.getTipoParcelamento().equals(tipoParcelamento)
					&& registro.getSituacao().equals(situacao)) {
				return registro;
			}
		}
		return null;
	}

	@Override
	public boolean verificarDivergencia(RegistroBatimentoParcelamento registro) {
		registro.setPossuiDivergencia(possuiDiferenca(total, registro.getTotal()) 
				|| possuiDiferencaValor(valor, registro.getValor()));
		return registro.isPossuiDivergencia();
	}

	@Override
	public String toString() {
		return "RegistroBatimentoParcelamento [tabela=" + tabela
				+ ", idInstituicao=" + idInstituicao + ", tipoParcelamento="
				+ tipoParcelamento + ", situacao=" + situacao + ", total="
				+ total + ", valor=" + valor + ", isPossuiDivergencia()="
				+ isPossuiDivergencia() + "]";
	}

}
