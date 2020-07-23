package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;

public class RecolhimentoIrrfDestinacaoJurosLegadoDTO extends BancoobDto{

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4458490057289490212L;

	private Integer idInstituicao;
	
	private Integer numContaCapital;
	
	private String nomeCliente;

	private Integer numContaCorrente;
	
	private BigDecimal valorContaCorrente;
	
	private BigDecimal valorContaCapital;
	
	private BigDecimal valorTotal;
	
	private BigDecimal valorIrrf;
	
	private String descPA;
	
	private Boolean todos;

	private Integer numPac;
	
	private Integer ordenacao;
	
	private Boolean agruparPorPA;
	
	private Integer anoBase;
	
	private Date dataAtualProduto;

	private String matriculaFuncionario;
	
	private Integer idSituacaoConta;
	
	private BigDecimal totalValorContaCorrente;
	
	private BigDecimal totalValorContaCapital;
	
	private Integer totalTotal;
	
	private Integer totalIrrf;

	
	public BigDecimal getTotalValorContaCorrente() {
		return totalValorContaCorrente;
	}

	public void setTotalValorContaCorrente(BigDecimal totalValorContaCorrente) {
		this.totalValorContaCorrente = totalValorContaCorrente;
	}

	public BigDecimal getTotalValorContaCapital() {
		return totalValorContaCapital;
	}

	public void setTotalValorContaCapital(BigDecimal totalValorContaCapital) {
		this.totalValorContaCapital = totalValorContaCapital;
	}

	public Integer getTotalTotal() {
		return totalTotal;
	}

	public void setTotalTotal(Integer totalTotal) {
		this.totalTotal = totalTotal;
	}

	public Integer getTotalIrrf() {
		return totalIrrf;
	}

	public void setTotalIrrf(Integer totalIrrf) {
		this.totalIrrf = totalIrrf;
	}

	public Integer getIdSituacaoConta() {
		return idSituacaoConta;
	}

	public void setIdSituacaoConta(Integer idSituacaoConta) {
		this.idSituacaoConta = idSituacaoConta;
	}

	public String getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	/** The situacao conta. */
	private String situacaoConta;	
	
	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public Date getDataAtualProduto() {
		return dataAtualProduto;
	}

	public void setDataAtualProduto(Date dataAtualProduto) {
		this.dataAtualProduto = dataAtualProduto;
	}

	private List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> recolhimentos;

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getNumContaCorrente() {
		return numContaCorrente;
	}

	public void setNumContaCorrente(Integer numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	public BigDecimal getValorContaCorrente() {
		return valorContaCorrente;
	}

	public void setValorContaCorrente(BigDecimal valorContaCorrente) {
		this.valorContaCorrente = valorContaCorrente;
	}

	public BigDecimal getValorContaCapital() {
		return valorContaCapital;
	}

	public void setValorContaCapital(BigDecimal valorContaCapital) {
		this.valorContaCapital = valorContaCapital;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	// Valor total gerado a partir de valorContaCorrente e ValorContaCapital
	public void setValorTotal(BigDecimal valorTotal) {
		if(this.valorContaCorrente != null) {
			this.valorTotal = this.valorContaCorrente.add(this.valorContaCapital);	
		}else {
			this.valorTotal = this.valorContaCapital;
		}
		
	}

	public BigDecimal getValorIrrf() {
		return valorIrrf;
	}

	public void setValorIrrf(BigDecimal valorIrrf) {
		this.valorIrrf = valorIrrf;
	}

	public String getDescPA() {
		return descPA;
	}

	public void setDescPA(String descPA) {
		this.descPA = descPA;
	}

	public Boolean getTodos() {
		return todos;
	}

	public void setTodos(Boolean todos) {
		this.todos = todos;
	}
	
	public Integer getNumPac() {
		return numPac;
	}

	public void setNumPac(Integer numPA) {
		this.numPac = numPA;
	}

	public Integer getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}

	public Boolean getAgruparPorPA() {
		return agruparPorPA;
	}

	public void setAgruparPorPA(Boolean agruparPorPA) {
		this.agruparPorPA = agruparPorPA;
	}

	public Integer getAnoBase() {
		return anoBase;
	}

	public void setAnoBase(Integer anoBase) {
		this.anoBase = anoBase;
	}

	public List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> getRecolhimentos() {
		return recolhimentos;
	}

	public void setRecolhimentos(List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> recolhimentos) {
		this.recolhimentos = recolhimentos;
	}	
}
