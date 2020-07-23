/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cco.api.negocio.enums.AplicativoEnum;

/**
 * A Classe LancamentoContaCorrenteIntegracaoDTO.
 */
public class LancamentoContaCorrenteIntegracaoDTO extends BancoobDto {


	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7950108903620021513L;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo dataLote. */
	private DateTimeDB dataLote;
	
	/** O atributo numLoteLanc. */
	private Integer numLoteLanc;
	
	/** O atributo descNumDocumento. */
	private String descNumDocumento;
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente;
	
	/** O atributo valorLanc. */
	private BigDecimal valorLanc;
	
	/** O atributo idProduto. */
	private Integer idProduto;
	
	/** O atributo idTipoHistoricoLanc. */
	private Integer idTipoHistoricoLanc;
	
	/** O atributo idProdutoEstorno. */
	private Integer idProdutoEstorno;
	
	/** O atributo idTipoHistoricoEstorno. */
	private Integer idTipoHistoricoEstorno;
	
	/** O atributo idUsuarioResp. */
	private String idUsuarioResp;
	
	/** O atributo bolVerificaSaldo. */
	private Boolean bolVerificaSaldo;
	
	/** O atributo codOrigemLote. */
	private Integer codOrigemLote;
	
	/** O atributo numSeqLanc. */
	private Integer numSeqLanc = 0;
	
	/** O atributo bolVerificaContaAnt. */
	private Boolean bolVerificaContaAnt;
	
	/** O atributo bolConsideraLimite. */
	private Boolean bolConsideraLimite;
	
	/** O atributo descInfComplementar. */
	private String descInfComplementar;
	
	/** O atributo bolConsSaldoResgAuto. */
	private Boolean bolConsSaldoResgAuto = false;
	
	/** O atributo idAplicativo. */
	private Integer idAplicativo;
	
	/** O atributo aplicativoEnum. */
	private AplicativoEnum aplicativoEnum;

	/**
	 * Recupera o valor de aplicativoEnum.
	 *
	 * @return o valor de aplicativoEnum
	 */
	public AplicativoEnum getAplicativoEnum() {
		return aplicativoEnum;
	}

	/**
	 * Define o valor de aplicativoEnum.
	 *
	 * @param aplicativoEnum o novo valor de aplicativoEnum
	 */
	public void setAplicativoEnum(AplicativoEnum aplicativoEnum) {
		this.aplicativoEnum = aplicativoEnum;
	}	

	/**
	 * Recupera o valor de idAplicativo.
	 *
	 * @return o valor de idAplicativo
	 */
	public Integer getIdAplicativo() {
		return idAplicativo;
	}

	/**
	 * Define o valor de idAplicativo.
	 *
	 * @param idAplicativo o novo valor de idAplicativo
	 */
	public void setIdAplicativo(Integer idAplicativo) {
		this.idAplicativo = idAplicativo;
	}	

	/**
	 * Recupera o valor de dataLote.
	 *
	 * @return o valor de dataLote
	 */
	public DateTimeDB getDataLote() {
		if(dataLote != null){
			return new DateTimeDB(dataLote.getTime());
		}
		return null;
	}

	/**
	 * Define o valor de dataLote.
	 *
	 * @param dataLote o novo valor de dataLote
	 */
	public void setDataLote(DateTimeDB dataLote) {
		if(dataLote != null){
			this.dataLote = new DateTimeDB(dataLote.getTime());
		}
	}

	/**
	 * Recupera o valor de dataLoteDB.
	 *
	 * @return o valor de dataLoteDB
	 */
	public String getDataLoteDB() {
		return DataUtil.converterDateToString(dataLote, "yyyy-MM-dd");
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
	 * Recupera o valor de descNumDocumento.
	 *
	 * @return o valor de descNumDocumento
	 */
	public String getDescNumDocumento() {
		return descNumDocumento;
	}

	/**
	 * Define o valor de descNumDocumento.
	 *
	 * @param descNumDocumento o novo valor de descNumDocumento
	 */
	public void setDescNumDocumento(String descNumDocumento) {
		this.descNumDocumento = descNumDocumento;
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
	 * Recupera o valor de valorLanc.
	 *
	 * @return o valor de valorLanc
	 */
	public BigDecimal getValorLanc() {
		return valorLanc;
	}

	/**
	 * Define o valor de valorLanc.
	 *
	 * @param valorLanc o novo valor de valorLanc
	 */
	public void setValorLanc(BigDecimal valorLanc) {
		this.valorLanc = valorLanc;
	}

	/**
	 * Recupera o valor de idProduto.
	 *
	 * @return o valor de idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * Define o valor de idProduto.
	 *
	 * @param idProduto o novo valor de idProduto
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
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
	 * Recupera o valor de idProdutoEstorno.
	 *
	 * @return o valor de idProdutoEstorno
	 */
	public Integer getIdProdutoEstorno() {
		return idProdutoEstorno;
	}

	/**
	 * Define o valor de idProdutoEstorno.
	 *
	 * @param idProdutoEstorno o novo valor de idProdutoEstorno
	 */
	public void setIdProdutoEstorno(Integer idProdutoEstorno) {
		this.idProdutoEstorno = idProdutoEstorno;
	}

	/**
	 * Recupera o valor de idTipoHistoricoEstorno.
	 *
	 * @return o valor de idTipoHistoricoEstorno
	 */
	public Integer getIdTipoHistoricoEstorno() {
		return idTipoHistoricoEstorno;
	}

	/**
	 * Define o valor de idTipoHistoricoEstorno.
	 *
	 * @param idTipoHistoricoEstorno o novo valor de idTipoHistoricoEstorno
	 */
	public void setIdTipoHistoricoEstorno(Integer idTipoHistoricoEstorno) {
		this.idTipoHistoricoEstorno = idTipoHistoricoEstorno;
	}

	/**
	 * Recupera o valor de idUsuarioResp.
	 *
	 * @return o valor de idUsuarioResp
	 */
	public String getIdUsuarioResp() {
		return idUsuarioResp;
	}

	/**
	 * Define o valor de idUsuarioResp.
	 *
	 * @param idUsuarioResp o novo valor de idUsuarioResp
	 */
	public void setIdUsuarioResp(String idUsuarioResp) {
		this.idUsuarioResp = idUsuarioResp;
	}

	/**
	 * Recupera o valor de bolVerificaSaldo.
	 *
	 * @return o valor de bolVerificaSaldo
	 */
	public Boolean getBolVerificaSaldo() {
		return bolVerificaSaldo;
	}

	/**
	 * Define o valor de bolVerificaSaldo.
	 *
	 * @param bolVerificaSaldo o novo valor de bolVerificaSaldo
	 */
	public void setBolVerificaSaldo(Boolean bolVerificaSaldo) {
		this.bolVerificaSaldo = bolVerificaSaldo;
	}

	/**
	 * Recupera o valor de bolVerificaSaldoDB.
	 *
	 * @return o valor de bolVerificaSaldoDB
	 */
	public Integer getBolVerificaSaldoDB() {
		return bolVerificaSaldo ? 1 : 0;
	}

	/**
	 * Recupera o valor de codOrigemLote.
	 *
	 * @return o valor de codOrigemLote
	 */
	public Integer getCodOrigemLote() {
		return codOrigemLote;
	}

	/**
	 * Define o valor de codOrigemLote.
	 *
	 * @param codOrigemLote o novo valor de codOrigemLote
	 */
	public void setCodOrigemLote(Integer codOrigemLote) {
		this.codOrigemLote = codOrigemLote;
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

	/**
	 * Recupera o valor de bolVerificaContaAnt.
	 *
	 * @return o valor de bolVerificaContaAnt
	 */
	public Boolean getBolVerificaContaAnt() {
		return bolVerificaContaAnt;
	}

	/**
	 * Define o valor de bolVerificaContaAnt.
	 *
	 * @param bolVerificaContaAnt o novo valor de bolVerificaContaAnt
	 */
	public void setBolVerificaContaAnt(Boolean bolVerificaContaAnt) {
		this.bolVerificaContaAnt = bolVerificaContaAnt;
	}

	/**
	 * Recupera o valor de bolVerificaContaAntDB.
	 *
	 * @return o valor de bolVerificaContaAntDB
	 */
	public Integer getBolVerificaContaAntDB() {
		return bolVerificaContaAnt ? 1 : 0;
	}

	/**
	 * Recupera o valor de bolConsideraLimite.
	 *
	 * @return o valor de bolConsideraLimite
	 */
	public Boolean getBolConsideraLimite() {
		return bolConsideraLimite;
	}

	/**
	 * Define o valor de bolConsideraLimite.
	 *
	 * @param bolConsideraLimite o novo valor de bolConsideraLimite
	 */
	public void setBolConsideraLimite(Boolean bolConsideraLimite) {
		this.bolConsideraLimite = bolConsideraLimite;
	}

	/**
	 * Recupera o valor de bolConsideraLimiteDB.
	 *
	 * @return o valor de bolConsideraLimiteDB
	 */
	public Integer getBolConsideraLimiteDB() {
		return bolConsideraLimite ? 1 : 0;
	}

	/**
	 * Recupera o valor de descInfComplementar.
	 *
	 * @return o valor de descInfComplementar
	 */
	public String getDescInfComplementar() {
		return descInfComplementar;
	}

	/**
	 * Define o valor de descInfComplementar.
	 *
	 * @param descInfComplementar o novo valor de descInfComplementar
	 */
	public void setDescInfComplementar(String descInfComplementar) {
		this.descInfComplementar = descInfComplementar;
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
	 * Recupera o valor de bolConsSaldoResgAuto.
	 *
	 * @return o valor de bolConsSaldoResgAuto
	 */
	public Boolean getBolConsSaldoResgAuto() {
		return bolConsSaldoResgAuto;
	}

	/**
	 * Define o valor de bolConsSaldoResgAuto.
	 *
	 * @param bolConsSaldoResgAuto o novo valor de bolConsSaldoResgAuto
	 */
	public void setBolConsSaldoResgAuto(Boolean bolConsSaldoResgAuto) {
		this.bolConsSaldoResgAuto = bolConsSaldoResgAuto;
	}

	
}
