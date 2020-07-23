package br.com.sicoob.cca.relatorios.negocio.dto;

public class RelLancamentosAnaliticoDTO extends RelLancamentosDTO {

	private static final long serialVersionUID = 8171921653424697570L;

	private Integer numeroContaCapital;
	private Integer numeroCliente;
	private Integer tipoLote;
	private Integer sequencialLancamento;
	private String descricaoHistorico;
	private String numeroDocumento;
	private String nomePessoa;
	private String naturezaOperacao;

	public Integer getNumeroContaCapital() {
		return numeroContaCapital;
	}

	public void setNumeroContaCapital(Integer numeroContaCapital) {
		this.numeroContaCapital = numeroContaCapital;
	}

	public Integer getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(Integer numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public Integer getTipoLote() {
		return tipoLote;
	}

	public void setTipoLote(Integer tipoLote) {
		this.tipoLote = tipoLote;
	}

	public Integer getSequencialLancamento() {
		return sequencialLancamento;
	}

	public void setSequencialLancamento(Integer sequencialLancamento) {
		this.sequencialLancamento = sequencialLancamento;
	}

	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = descricaoHistorico;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

}
