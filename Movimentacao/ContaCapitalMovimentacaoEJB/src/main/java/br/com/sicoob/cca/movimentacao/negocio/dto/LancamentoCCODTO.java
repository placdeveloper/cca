package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe DevolucaoRenDTO.
 *
 * @author Antonio.Genaro
 */
public class LancamentoCCODTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5877612282468007344L;

	/** O atributo idContaCapital. */
	private Integer idContaCapital;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numContaCapital. */
	private Integer numContaCapital;
	
	/** O atributo vlrDevolucao. */
	private BigDecimal vlrDevolucao;
	
	/** O atributo vlrAVista. */
	private BigDecimal vlrAVista;
	
	/** O atributo qtdParcelas. */
	private Integer qtdParcelas;
	
	/** O atributo vlrParcelas. */
	private BigDecimal vlrParcelas;

	/** O atributo dtInicioParcelamento. */
	private String dtInicioParcelamento;
		
	/** O atributo tipoInteg. */
	private Short tipoInteg;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo dataHoraAtualizacao. */
	private DateTimeDB dataHoraAtualizacao;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;	
	
	/** O atributo idPessoa. */
	private Integer idPessoa;

	/** O atributo idMotivoDevolucao. */
	private Short idMotivoDevolucao;

	/** O atributo qtdAplicacao. */
	private Integer qtdAplicacao;

	/** O atributo vlrPorAplicacao. */
	private Integer vlrPorAplicacao;

	/** O atributo idModalidadeAplicacao. */
	private Integer idModalidadeAplicacao;			
	
	/**
	 * Recupera o valor de idMotivoDevolucao.
	 *
	 * @return o valor de idMotivoDevolucao
	 */
	public Short getIdMotivoDevolucao() {
		return idMotivoDevolucao;
	}

	/**
	 * Define o valor de idMotivoDevolucao.
	 *
	 * @param idMotivoDevolucao o novo valor de idMotivoDevolucao
	 */
	public void setIdMotivoDevolucao(Short idMotivoDevolucao) {
		this.idMotivoDevolucao = idMotivoDevolucao;
	}

	/**
	 * Recupera o valor de qtdAplicacao.
	 *
	 * @return o valor de qtdAplicacao
	 */
	public Integer getQtdAplicacao() {
		return qtdAplicacao;
	}

	/**
	 * Define o valor de qtdAplicacao.
	 *
	 * @param qtdAplicacao o novo valor de qtdAplicacao
	 */
	public void setQtdAplicacao(Integer qtdAplicacao) {
		this.qtdAplicacao = qtdAplicacao;
	}

	/**
	 * Recupera o valor de vlrPorAplicacao.
	 *
	 * @return o valor de vlrPorAplicacao
	 */
	public Integer getVlrPorAplicacao() {
		return vlrPorAplicacao;
	}

	/**
	 * Define o valor de vlrPorAplicacao.
	 *
	 * @param vlrPorAplicacao o novo valor de vlrPorAplicacao
	 */
	public void setVlrPorAplicacao(Integer vlrPorAplicacao) {
		this.vlrPorAplicacao = vlrPorAplicacao;
	}

	/**
	 * Recupera o valor de idModalidadeAplicacao.
	 *
	 * @return o valor de idModalidadeAplicacao
	 */
	public Integer getIdModalidadeAplicacao() {
		return idModalidadeAplicacao;
	}

	/**
	 * Define o valor de idModalidadeAplicacao.
	 *
	 * @param idModalidadeAplicacao o novo valor de idModalidadeAplicacao
	 */
	public void setIdModalidadeAplicacao(Integer idModalidadeAplicacao) {
		this.idModalidadeAplicacao = idModalidadeAplicacao;
	}

	/**
	 * Recupera o valor de idContaCapital.
	 *
	 * @return o valor de idContaCapital
	 */
	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	/**
	 * Define o valor de idContaCapital.
	 *
	 * @param idContaCapital o novo valor de idContaCapital
	 */
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de numContaCapital.
	 *
	 * @return o valor de numContaCapital
	 */
	public Integer getNumContaCapital() {
		return numContaCapital;
	}

	/**
	 * Define o valor de numContaCapital.
	 *
	 * @param numContaCapital o novo valor de numContaCapital
	 */
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}

	/**
	 * Recupera o valor de vlrDevolucao.
	 *
	 * @return o valor de vlrDevolucao
	 */
	public BigDecimal getVlrDevolucao() {
		return vlrDevolucao;
	}

	/**
	 * Define o valor de vlrDevolucao.
	 *
	 * @param vlrDevolucao o novo valor de vlrDevolucao
	 */
	public void setVlrDevolucao(BigDecimal vlrDevolucao) {
		this.vlrDevolucao = vlrDevolucao;
	}

	/**
	 * Recupera o valor de vlrAVista.
	 *
	 * @return o valor de vlrAVista
	 */
	public BigDecimal getVlrAVista() {
		return vlrAVista;
	}

	/**
	 * Define o valor de vlrAVista.
	 *
	 * @param vlrAVista o novo valor de vlrAVista
	 */
	public void setVlrAVista(BigDecimal vlrAVista) {
		this.vlrAVista = vlrAVista;
	}

	/**
	 * Recupera o valor de qtdParcelas.
	 *
	 * @return o valor de qtdParcelas
	 */
	public Integer getQtdParcelas() {
		return qtdParcelas;
	}

	/**
	 * Define o valor de qtdParcelas.
	 *
	 * @param qtdParcelas o novo valor de qtdParcelas
	 */
	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	/**
	 * Recupera o valor de vlrParcelas.
	 *
	 * @return o valor de vlrParcelas
	 */
	public BigDecimal getVlrParcelas() {
		return vlrParcelas;
	}

	/**
	 * Define o valor de vlrParcelas.
	 *
	 * @param vlrParcelas o novo valor de vlrParcelas
	 */
	public void setVlrParcelas(BigDecimal vlrParcelas) {
		this.vlrParcelas = vlrParcelas;
	}

	/**
	 * Recupera o valor de dtInicioParcelamento.
	 *
	 * @return o valor de dtInicioParcelamento
	 */
	public String getDtInicioParcelamento() {
		return dtInicioParcelamento;
	}

	/**
	 * Define o valor de dtInicioParcelamento.
	 *
	 * @param dtInicioParcelamento o novo valor de dtInicioParcelamento
	 */
	public void setDtInicioParcelamento(String dtInicioParcelamento) {
		this.dtInicioParcelamento = dtInicioParcelamento;
	}

	/**
	 * Recupera o valor de tipoInteg.
	 *
	 * @return o valor de tipoInteg
	 */
	public Short getTipoInteg() {
		return tipoInteg;
	}

	/**
	 * Define o valor de tipoInteg.
	 *
	 * @param tipoInteg o novo valor de tipoInteg
	 */
	public void setTipoInteg(Short tipoInteg) {
		this.tipoInteg = tipoInteg;
	}

	/**
	 * Recupera o valor de numContaCorrente.
	 *
	 * @return o valor de numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}

	/**
	 * Define o valor de numContaCorrente.
	 *
	 * @param numContaCorrente o novo valor de numContaCorrente
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}

	/**
	 * Recupera o valor de dataHoraAtualizacao.
	 *
	 * @return o valor de dataHoraAtualizacao
	 */
	public DateTimeDB getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	/**
	 * Define o valor de dataHoraAtualizacao.
	 *
	 * @param dataHoraAtualizacao o novo valor de dataHoraAtualizacao
	 */
	public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	/**
	 * Recupera o valor de idPessoaLegado.
	 *
	 * @return o valor de idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * Define o valor de idPessoaLegado.
	 *
	 * @param idPessoaLegado o novo valor de idPessoaLegado
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return o valor de idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa o novo valor de idPessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
}
