package br.com.sicoob.cca.replicacao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Metodos auxiliares para a execucao da replicacao.
 * @author Nairon.Silva
 */
public class ExecucaoReplicacaoUtil {

	public static final String[] EMAIL_DESTINATARIOS_DEFAULT = {"GrupoContaCapital@sicoob.com.br"};
	public static final String MSG_ASSUNTO_ERRO = "TWS - Replicação Conta Capital";
	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private static ResourceBundle config;
	private static SimpleDateFormat sdf = new SimpleDateFormat("[E MMM dd/MM/yyyy HH:mm:ss] ");
	
	/**
	 * Inicializa o arquivo de propriedades config.
	 */
	public static void inicializar() {
		config = ResourceBundle.getBundle("config");
	}
	
	/**
	 * Imprime em Sysout a mensagem.
	 * @param msg
	 */
	public static void log(String msg) {
		System.out.println(sdf.format(new Date())+msg);
	}
	
	/**
	 * Retorna o keySet das propriedades no arquivo de configuracoes.
	 * @return
	 */
	public static Set<String> getConfigKeySet() {
		return config.keySet();
	}
	
	/**
	 * Retorna o valor de uma propriedade no arquivo de configuracoes.
	 * @param key
	 * @return
	 */
	public static String getConfigString(String key) {
		return config.getString(key);
	}
	
	/**
	 * Retorna os destinatarios para envio de e-mail parametrizados no arquivo de configuracoes.
	 * @return
	 */
	public static String[] lerDestinatarios() {
		String destinatarios = config.getString("email_destinatarios");
		if (destinatarios != null) {
			return destinatarios.split(";");
		}
		return EMAIL_DESTINATARIOS_DEFAULT;
	}
	
}
