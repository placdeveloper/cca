/*
 * 
 */
package br.com.sicoob.cca.comum.util;

import java.awt.Image;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe utilitaria conta capital
 */
public final class ContaCapitalUtil {
	

	private static final String PATTERN_DATA_US = "yyyy-MM-dd";
	private static final String PATTERN_DATA_BR = "dd/MM/yyyy";		
	private static final ZoneId BRAZIL_ZONE_ID = ZoneId.of("America/Sao_Paulo");	
	
	/**
	 * Construtor
	 */
	private ContaCapitalUtil() {
		
	}
	
	/**
	 * Formata data sem hora
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	public static Date formatarDataSemHora(Date data) throws BancoobException{
		String dataFormatada = null;
		Date dataSaida = null;
		try {
			DateFormat formatter = new SimpleDateFormat(PATTERN_DATA_BR);
			dataFormatada = formatter.format(data);
			dataSaida =  formatter.parse(dataFormatada);						
		} catch (ParseException e) {
			throw new BancoobException("MSG_ERRO_CONVERTER_DATA", e);
		}
		return dataSaida;
	}

	/**
	 * Formata data strind to data
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	public static Date formatarStringToDate(String data) throws BancoobException{
		Date dataSaida = null;
		try {
			DateFormat formatter = new SimpleDateFormat(PATTERN_DATA_BR);
			dataSaida =  formatter.parse(data);						
		} catch (ParseException e) {
			throw new BancoobException("MSG_ERRO_CONVERTER_DATA", e);
		}
		return dataSaida;
	}
	
	/**
	 * Formata data string to data em formato US
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	public static Date formatarStringToDateUS(String data) throws BancoobException{
		if(Objects.isNull(data)){
			return null;
		}
		
		try {
			DateFormat formatter = new SimpleDateFormat(PATTERN_DATA_US);
			return formatter.parse(data);						
		} catch (ParseException e) {
			throw new BancoobException("MSG_ERRO_CONVERTER_DATA", e);
		}

	}
		
	
	/**
	 * formatarDataUS (SQL Server)
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	public static String formatarDataUS(Date data) {
		if(data != null) {
			String dataSaida = null;
			DateFormat formatter = new SimpleDateFormat(PATTERN_DATA_US);
			dataSaida = formatter.format(data);				
			return dataSaida;
		}
		return null;
	}
	
	
	/**
	 * Formata data para o padrão pt BR
	 * @param data
	 * @return	
	 * @throws BancoobException
	 */
	public static String formatarDataBR(Date data) throws BancoobException{
		String dataSaida = null;
		DateFormat formatter = new SimpleDateFormat(PATTERN_DATA_BR);
		dataSaida = formatter.format(data);				
		return dataSaida;
	}	
	
	/**
	 * Formata data para o padrão da mascara passada e retorna String
	 * @param data
	 * @return	
	 * @throws BancoobException
	 */
	public static String formatarDataMascara(Date data,String mascara) throws BancoobException{
		String dataSaida = null;
		DateFormat formatter = new SimpleDateFormat(mascara);
		dataSaida = formatter.format(data);				
		return dataSaida;
	}	
		

	/**
	 * Recupera imagem logo SICOOB
	 * @param imagemPacote
	 * @return	
	 * @throws BancoobException
	 */
	public static Image recuperarImagemRelatorio(String imagemPacote) throws BancoobException{
		URL urlImagem = Thread.currentThread().getContextClassLoader().getResource(imagemPacote);
		ImageIcon imagem = new ImageIcon(urlImagem);
		return imagem.getImage();
	}
	
	/**
	 * Recebe a Data no formato Date e converte para DateTime, trata os nulls
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	public static DateTime montarDateTime(Date obj) throws BancoobException{
		return (obj == null? null:new DateTime(obj.getTime()));
	}
	
	/**
	 * Formata valor (sem arredondamento) para R$ 
	 * @param valor
	 * @return valor formatado (R$ #.###,##)  
	 */
	public static String formatarValor(BigDecimal valor) {
		NumberFormat format = DecimalFormat.getCurrencyInstance(new Locale("pt","BR"));  
	    format.setMinimumFractionDigits(2);
	    return format.format(valor);
	}
	
	/**
	 * Retira mascara de cpf/cnpj
	 * @param valor
	 * @return
	 */
	public static String retirarMascaraCpfCnpj(String valor) {
		return valor.replaceAll("\\D", "");
	}
	
	/**
	 * Formata a string com a mascara de cpf/cnpj
	 * @param valor
	 * @return
	 */
	public static String formatarCpfCnpj(String valor) {
		if (valor == null || valor.trim().length() == 0) {
			return "";
		}
		String formatado = null;
		try {
			final int tamanhoCpf = 11;
			final String mascaraCpf = "###.###.###-##";
			final String mascaraCnpj = "##.###.###/####-##";
			MaskFormatter formatter = null;
			if (valor.length() > tamanhoCpf) {
				formatter = new MaskFormatter(mascaraCnpj);
			} else {
				formatter = new MaskFormatter(mascaraCpf);
			}
			JFormattedTextField field = new JFormattedTextField(formatter);
			field.setText(valor);
			formatado = field.getText();
		} catch (ParseException e) {
			formatado = valor;
		}
		
		return formatado;
	}
	
	/**
	 * Verifica se a string esta vazia. <br>
	 * Para tanto, verifica se esta nula ou esta vazia apos ignorar \n, \t e espacos.
	 * @param string
	 * @return
	 */
	public static boolean isStringVazia(String string) {
		return string == null || string.replace("\n", "").replace("\t", "").replace(" ", "").length() == 0;
	}
	
	/**
	 * Completa com caracteres a direita
	 * @param string
	 * @param caracter
	 * @param tamanho
	 * @return
	 */
	public static String completaComCaracterADireita(String string, String caracter, int tamanho) {
		String valor = (string == null) ? "" : string;
		StringBuilder sb = new StringBuilder(valor);
		while (sb.length() < tamanho) {
			sb.append(caracter);
		}
		return sb.toString();
	}
	
	/**
	 * Completa com caracteres a esquerda
	 * @param string
	 * @param caracter
	 * @param tamanho
	 * @return
	 */
	public static String completaComCaracterAEsquerda(String string, String caracter, int tamanho) {
		String valor = (string == null) ? "" : string;
		int qtdCaracteres = tamanho - valor.length();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < qtdCaracteres; i++) {
			sb.append(caracter);
		}
		sb.append(valor);
		return sb.toString();
	}
	
	/**
	 * Formata uma lista de valores. Exemplo: [1,2,3] -> "1, 2 e 3".
	 * @param valores
	 * @return
	 */
	public static String formatarListaValores(List<Integer> valores) {
		if (valores == null || valores.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int size = valores.size();
		for (int i = 0; i < size; i++) {
			Integer valor = valores.get(i);
			if (i == 0) {
				sb.append(valor);
			} else if (i == (size-1)) {
				sb.append(" e ").append(valor);
			} else {
				sb.append(", ").append(valor);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Formata uma lista de valores para clausulas IN
	 * @param valores
	 * @return
	 * @throws BancoobException
	 */
	public static String formatarListaValoresIN(List<?> valores) throws BancoobException{
		StringBuilder sb = new StringBuilder();
		if (valores != null){
			for (Iterator<?> iterator = valores.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				sb.append(obj);
				if (iterator.hasNext()) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * Concatena um conjunto de valores por um determinado separador.
	 * <br>
	 * Exemplo: concatenarValores("-", "A", "B", "C") -> "A-B-C"
	 * @param separador o que deve ficar entre os valores
	 * @param valores o conjunto de valores
	 * @return
	 */
	public static String concatenarValores(String separador, Object... valores) {
		if (valores == null || valores.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		List<Object> list = Arrays.asList(valores);
		for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
			Object valor = iterator.next();
			sb.append(valor);
			if (iterator.hasNext()) {
				sb.append(separador);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Formata para yyyyMM
	 * @param data
	 * @return Integer no formato yyyyMM 
	 */
	public static Integer getAnoMesFormatado(org.joda.time.DateTime data) {
		return Integer.valueOf(data.getYear() + StringUtils.leftPad(String.valueOf(data.getMonthOfYear()), 2, "0"));
	}
		
    /**
     * formatarLocalDatetimeToString
     * @param LocalDate
     * @return
     * @throws BancoobException 
     */
    public static LocalDate formatarStringToLocalDate(String str) throws BancoobException {
        LocalDate ld = null;
    	try {
    		DateFormat df = new SimpleDateFormat(PATTERN_DATA_US);
    		ld = formatarDateToLocalDate(df.parse(str));    		
    	} catch (ParseException e) {
			throw new BancoobException("MSG_012", e);
		}
    	return ld;
    }		
	
    /**
     * formatarDateToLocalDate
     * @param date
     * @return
     */
	public static LocalDate formatarDateToLocalDate(Date date) {
		if(Objects.isNull(date)){
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(BRAZIL_ZONE_ID).toLocalDate();
	}    
    
    /**
     * formatarLocalDateToString
     * @param LocalDate
     * @return
     */
    public static String formatarLocalDateToString(LocalDate ldt) {
    	if(Objects.isNull(ldt)){
        	return null;
        }
        
        Date dt = formatarLocalDateToDate(ldt);
		DateFormat df = new SimpleDateFormat(PATTERN_DATA_US);
        return df.format(dt);
    }	

    /**
     * formatarLocalDateToDate
     * @param localDate
     * @return
     */
	public static Date formatarLocalDateToDate(LocalDate localDate) {
		if(Objects.isNull(localDate)){
			return null;
		}
		return Date.from(localDate.atStartOfDay().atZone(BRAZIL_ZONE_ID).toInstant());
	}	    	
	
}