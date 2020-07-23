/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

// TODO: Auto-generated Javadoc
/**
 * A Classe IntegralizacaoCapitalDTO.
 *
 * @author Antonio.Genaro
 */
public class IntegralizacaoCapitalDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7361048319888603235L;

	//Dados Cooperativa/Instituicao
	/** O atributo idInstituicao. */
	private Integer idInstituicao;

	//Dados Pessoa/Capes
	/** O atributo numCpfCnpj. */
	private String numCpfCnpj;	
	
	/** O atributo idPessoa. */
	private Integer idPessoa;

	//Dados Capital Legado/Renovacao
	/** O atributo numMatricula. */
	private Integer numMatricula;

	//Dados Integralizacao
	/** O atributo idTipoHistoricoLanc. */
	private Integer idTipoHistoricoLanc;
	
	private Integer idTipoHistoricoEstorno;
	
	/** O atributo valorLancamento. */
	private BigDecimal valorLancamento;	
	
	//Dados do Emissor - DescNumDocumento
	/** O atributo idOperacaoOrigem. */
	private String idOperacaoOrigem;
	
	//Dados Gerados pela Aplicação (nao atribuir)
	/** O atributo idLancamentoContaCapital. */
	private Long idLancamentoContaCapital;
	
	/** O atributo numCooperativa. */
	private Integer numCooperativa;	
	
	/** O atributo dataLancamento. */
	private DateTimeDB dataLancamento;	
	
	/** O atributo numLoteLanc. */
	private Integer numLoteLanc;
	
	/** O atributo numSeqLanc. */
	private Integer numSeqLanc;
	
	/** Atributos adicionados para inclusao em lote do rateio **/
	/** O atributo idContaCapital */
	private Integer idContaCapital;
	
	/** O atributo idUsuario */
	private String idUsuario;
	
	/** O atributo descNumDocumento */
	private String descNumDocumento;
	
	private boolean ignoraValidacaoContaCapitalAtiva;
	
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
	 * Recupera o valor de numCpfCnpj.
	 *
	 * @return o valor de numCpfCnpj
	 */
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}
	
	/**
	 * Define o valor de numCpfCnpj.
	 *
	 * @param numCpfCnpj o novo valor de numCpfCnpj
	 */
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
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
	
	/**
	 * Recupera o valor de numMatricula.
	 *
	 * @return o valor de numMatricula
	 */
	public Integer getNumMatricula() {
		return numMatricula;
	}
	
	/**
	 * Define o valor de numMatricula.
	 *
	 * @param numMatricula o novo valor de numMatricula
	 */
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}
	
	/**
	 * Recupera o valor de idTipoHistoricoLanc.
	 *
	 * @return o valor de idTipoHistoricoLanc
	 */
	public Integer getIdTipoHistoricoLanc() {
		return idTipoHistoricoLanc;
	}
	
	/**
	 * Define o valor de idTipoHistoricoLanc.
	 *
	 * @param idTipoHistoricoLanc o novo valor de idTipoHistoricoLanc
	 */
	public void setIdTipoHistoricoLanc(Integer idTipoHistoricoLanc) {
		this.idTipoHistoricoLanc = idTipoHistoricoLanc;
	}
	
	/**
	 * Recupera o valor de valorLancamento.
	 *
	 * @return o valor de valorLancamento
	 */
	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}
	
	/**
	 * Define o valor de valorLancamento.
	 *
	 * @param valorLancamento o novo valor de valorLancamento
	 */
	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}
	
	/**
	 * Recupera o valor de idOperacaoOrigem.
	 *
	 * @return o valor de idOperacaoOrigem
	 */
	public String getIdOperacaoOrigem() {
		return idOperacaoOrigem;
	}
	
	/**
	 * Define o valor de idOperacaoOrigem.
	 *
	 * @param idOperacaoOrigem o novo valor de idOperacaoOrigem
	 */
	public void setIdOperacaoOrigem(String idOperacaoOrigem) {
		this.idOperacaoOrigem = idOperacaoOrigem;
	}
	
	/**
	 * Recupera o valor de idLancamentoContaCapital.
	 *
	 * @return o valor de idLancamentoContaCapital
	 */
	public Long getIdLancamentoContaCapital() {
		return idLancamentoContaCapital;
	}
	
	/**
	 * Define o valor de idLancamentoContaCapital.
	 *
	 * @param idLancamentoContaCapital o novo valor de idLancamentoContaCapital
	 */
	public void setIdLancamentoContaCapital(Long idLancamentoContaCapital) {
		this.idLancamentoContaCapital = idLancamentoContaCapital;
	}
	
	/**
	 * Recupera o valor de numCooperativa.
	 *
	 * @return o valor de numCooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	
	/**
	 * Define o valor de numCooperativa.
	 *
	 * @param numCooperativa o novo valor de numCooperativa
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	
	/**
	 * Recupera o valor de dataLancamento.
	 *
	 * @return o valor de dataLancamento
	 */
	public DateTimeDB getDataLancamento() {
		return dataLancamento;
	}
	
	/**
	 * Define o valor de dataLancamento.
	 *
	 * @param dataLancamento o novo valor de dataLancamento
	 */
	public void setDataLancamento(DateTimeDB dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	/**
	 * Recupera o valor de numLoteLanc.
	 *
	 * @return o valor de numLoteLanc
	 */
	public Integer getNumLoteLanc() {
		return numLoteLanc;
	}
	
	/**
	 * Define o valor de numLoteLanc.
	 *
	 * @param numLoteLanc o novo valor de numLoteLanc
	 */
	public void setNumLoteLanc(Integer numLoteLanc) {
		this.numLoteLanc = numLoteLanc;
	}
	
	/**
	 * Recupera o valor de numSeqLanc.
	 *
	 * @return o valor de numSeqLanc
	 */
	public Integer getNumSeqLanc() {
		return numSeqLanc;
	}
	
	/**
	 * Define o valor de numSeqLanc.
	 *
	 * @param numSeqLanc o novo valor de numSeqLanc
	 */
	public void setNumSeqLanc(Integer numSeqLanc) {
		this.numSeqLanc = numSeqLanc;
	}

	public Integer getIdContaCapital() {
		return idContaCapital;
	}

	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescNumDocumento() {
		return descNumDocumento;
	}

	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
	}

	public Integer getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}

	public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}

	public boolean isIgnoraValidacaoContaCapitalAtiva() {
		return ignoraValidacaoContaCapitalAtiva;
	}

	public void setIgnoraValidacaoContaCapitalAtiva(boolean ignoraValidacaoContaCapitalAtiva) {
		this.ignoraValidacaoContaCapitalAtiva = ignoraValidacaoContaCapitalAtiva;
	}

}
