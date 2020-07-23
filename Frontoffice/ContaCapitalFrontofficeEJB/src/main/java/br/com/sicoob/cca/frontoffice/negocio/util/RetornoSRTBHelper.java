package br.com.sicoob.cca.frontoffice.negocio.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe auxiliar pra construir os retornos das transacoes para o SRTB.
 * @author Nairon.Silva
 */
public final class RetornoSRTBHelper {

	private RetornoSRTBHelper() {
	}
	
	private static final String FORMATO_SRTB = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String FORMATO_HORA_MINUTO = "HH:mm";

	private static final String STRING_VAZIA = "";

	/**
	 * Formata o número de acordo com as casas decimais informada no padrão do SRTB
	 * @param numero Número a ser formatado
	 * @param casasDecimais Número de casas decimais
	 * @return O número formatado
	 */
	public static String formatarValor(Number numero, int casasDecimais) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(casasDecimais);
		nf.setMinimumFractionDigits(casasDecimais);
		return nf.format(numero).replaceAll(",", STRING_VAZIA);
	}

	/**
	 * Formata um valor com 2 casas decimais
	 * @param numero
	 * @return
	 */
	public static String formatarValor(Number numero) {
		return formatarValor(numero, 2);
	}
	
	/**
	 * Recupera o valor formatado.
	 * @param numero
	 * @param casasDecimais
	 * @return
	 */
	public static Number recuperarValor(String numero, int casasDecimais) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(casasDecimais);
		nf.setMinimumFractionDigits(casasDecimais);
		try {
			return nf.parse(numero);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Recupera o valor formatado a partir da formatacao de 2 casas.
	 * @param numero
	 * @return
	 */
	public static Number recuperarValor(String numero) {
		return recuperarValor(numero, 2);
	}

	/**
	 * Formata a hora para o padrão "HH:mm".
	 */
	public static String formatarHora(Date data) {
		return formatar(data, FORMATO_HORA_MINUTO);
	}

	/**
	 * Formata a data para o padrão do SRTB, "yyyy-MM-dd HH:mm:ss.SSS".
	 */
	public static String formatarData(Date data) {
		return formatar(data, FORMATO_SRTB);
	}

	/**
	 * Formata uma data, baseada na máscara fornecida no parâmetro
	 */
	private static String formatar(Date data, String mascara) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(mascara);
		return dateFormat.format(data);
	}

}
