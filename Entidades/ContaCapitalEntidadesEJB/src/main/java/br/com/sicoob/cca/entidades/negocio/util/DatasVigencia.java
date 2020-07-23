package br.com.sicoob.cca.entidades.negocio.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Datas de vigencia para alteracoes em regras de codigo.
 * @author Nairon.Silva
 */
public final class DatasVigencia {

	private DatasVigencia() {
		// construtor vazio
	}
	
	private static final int ANO_2018 = 2018;
	
	/**
	 * Novos numeros de lotes para integralizacao via Cabal, FAP e Canais de Atendimento.
	 */
	public static final Date LOTES_CABAL_FAP_CANAIS = new GregorianCalendar(ANO_2018, Calendar.JUNE, 1).getTime();
	
}
