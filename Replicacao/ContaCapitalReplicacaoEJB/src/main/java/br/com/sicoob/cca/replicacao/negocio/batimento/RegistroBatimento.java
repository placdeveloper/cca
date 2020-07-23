package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.math.BigDecimal;
import java.util.List;

/**
 * Superclasse dos registros de batimento
 * @author Nairon.Silva
 * @param <E>
 */
@SuppressWarnings("rawtypes")
public abstract class RegistroBatimento<E extends RegistroBatimento> {

	private Integer cooperativa;
	private boolean possuiDivergencia;
	private boolean naoPossuiCorrespondente;
	
	/**
	 * Procura o registro correspondente
	 * @param registros
	 * @return
	 */
	public abstract E procurarCorrespondente(List<E> registros);
	
	/**
	 * Verifica divergencias com outro registro
	 * @param registro
	 * @return
	 */
	public abstract boolean verificarDivergencia(E registro);

	public boolean isPossuiDivergencia() {
		return possuiDivergencia;
	}

	public void setPossuiDivergencia(boolean possuiDivergencia) {
		this.possuiDivergencia = possuiDivergencia;
	}
	
	/**
	 * Verifica se um numero esta diferente do outro
	 * @param numeroUm
	 * @param numeroDois
	 * @return
	 */
	protected boolean possuiDiferenca(Number numeroUm, Number numeroDois) {
		return !numeroUm.equals(numeroDois);
	}
	
	/**
	 * Verifica se um BigDecimal possui valor diferente de outro
	 * @param numeroUm
	 * @param numeroDois
	 * @return
	 */
	protected boolean possuiDiferencaValor(BigDecimal numeroUm, BigDecimal numeroDois) {
		return numeroUm.compareTo(numeroDois) != 0;
	}

	public Integer getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Integer cooperativa) {
		this.cooperativa = cooperativa;
	}

	/**
	 * @return the naoPossuiCorrespondente
	 */
	public boolean isNaoPossuiCorrespondente() {
		return naoPossuiCorrespondente;
	}

	/**
	 * @param naoPossuiCorrespondente the naoPossuiCorrespondente to set
	 */
	public void setNaoPossuiCorrespondente(boolean naoPossuiCorrespondente) {
		this.naoPossuiCorrespondente = naoPossuiCorrespondente;
	}
	
}
