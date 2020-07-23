package br.com.sicoob.sisbr.cca.relatorios.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * The Class RelSituacaoPeriodoCCARenVO.
 */
public class RelSituacaoPeriodoCCARenVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -120565210505268621L;
	
	/** The id instituicao. */
	private Integer idInstituicao;
	
	/** The data Periodo. */
	private DateTimeDB dataPeriodoInicial;
	
	/** The data Periodo. */
	private DateTimeDB dataPeriodoFinal;
	
	/**
	 * Gets the id instituicao.
	 *
	 * @return the id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Sets the id instituicao.
	 *
	 * @param idInstituicao the new id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	
	/**
	 * Gets the data periodo inicial.
	 *
	 * @return the data periodo inicial
	 */
	public DateTimeDB getDataPeriodoInicial() {
		return dataPeriodoInicial;
	}

	/**
	 * Sets the data periodo inicial.
	 *
	 * @param dataPeriodoInicial the new data periodo inicial
	 */
	public void setDataPeriodoInicial(DateTimeDB dataPeriodoInicial) {
		this.dataPeriodoInicial = dataPeriodoInicial;
	}

	/**
	 * Gets the data periodo final.
	 *
	 * @return the data periodo final
	 */
	public DateTimeDB getDataPeriodoFinal() {
		return dataPeriodoFinal;
	}

	/**
	 * Sets the periodo final.
	 *
	 * @param dataSaidaFinal the new periodo final
	 */
	public void setDataPeriodoFinal(DateTimeDB dataPeriodoFinal) {
		this.dataPeriodoFinal = dataPeriodoFinal;
	}
}