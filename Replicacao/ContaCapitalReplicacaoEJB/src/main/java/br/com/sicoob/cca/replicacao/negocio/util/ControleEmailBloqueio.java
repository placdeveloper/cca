package br.com.sicoob.cca.replicacao.negocio.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe para controlar envio de emails de bloqueio, para impedir spam
 * @author Nairon.Silva
 */
public final class ControleEmailBloqueio {

	private ControleEmailBloqueio() {}
	
	private static final int TEMPO_ESPERA_MINUTOS = 30;
	private static Date ultimaExecucao;
	
	/**
	 * Verifica se deve enviar e-mail (se ja tiver passado o tempo determinado na constante desde a ultima execucao)
	 * @return
	 */
	public static synchronized boolean deveEnviarEmail() {
		Date agora = new Date();
		if (ultimaExecucao == null || agora.after(tempoCalculado())) {
			ultimaExecucao = agora;
			return true;
		}
		return false;
	}

	/**
	 * Calcula o tempo da ultima execucao somado com a constante
	 * @return
	 */
	private static Date tempoCalculado() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ultimaExecucao);
		calendar.add(Calendar.MINUTE, TEMPO_ESPERA_MINUTOS);
		return calendar.getTime();
	}
	
}
