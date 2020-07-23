package br.com.sicoob.cca.replicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Classe utilitaria para exibicao do modo Help.
 * @author Nairon.Silva
 */
public class ExecucaoReplicacaoHelp {

	private static final String HELP_PARAM = "-help";
	
	/**
	 * Verifica a presenca do parametro -help. Em caso positivo, imprime o conteudo.
	 * @param args
	 * @return
	 */
	public static boolean exibeHelp(String[] args) {
		if (args != null && args.length > 0 && HELP_PARAM.equals(args[0])) {
			try {
				imprimirHelp();
				return true;
			} catch (Exception e) {
				ExecucaoReplicacaoUtil.log(e.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * Exibe a ajuda para configuração do serviço, realizando as trocas de tags por conteudo.
	 */
	private static void imprimirHelp() throws Exception {
		Map<String, String> tags = new HashMap<String, String>();
		tags.put("${MSG_CICLO_SUCESSO}", StatusExecucao.CICLO_SUCESSO.getMensagem());
		tags.put("${MSG_ERRO_COMUNICAR_SERVIDOR}", StatusExecucao.ERRO_COMUNICAR_SERVIDOR.getMensagem());
		tags.put("${MSG_ERRO_LOCALIZAR_SERVICO}", StatusExecucao.ERRO_LOCALIZAR_SERVICO.getMensagem());
		tags.put("${MSG_ERRO_NEGOCIAL}", StatusExecucao.ERRO_NEGOCIAL.getMensagem());
		tags.put("${MSG_ERRO_INESPERADO}", StatusExecucao.ERRO_INESPERADO.getMensagem());
		tags.put("${CONFIG.PROPERTIES}", lerConfigProperties());
		System.out.println(lerArquivoHelp(tags));
	}
	
	private static String lerArquivoHelp(Map<String, String> tags) throws Exception {
		InputStream inputStream = ExecucaoReplicacaoCca.class.getClassLoader().getResourceAsStream("help.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ((line = br.readLine()) != null) {
		        sb.append(substituiTag(tags, line));
		        sb.append(ExecucaoReplicacaoUtil.LINE_SEPARATOR);
		    }
		    return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "--";
	}

	private static String substituiTag(Map<String, String> tags, String line) {
		Set<String> tagsSet = tags.keySet();
		for (Iterator<String> iterator = tagsSet.iterator(); iterator.hasNext();) {
			String tag = (String) iterator.next();
			if (line.contains(tag)) {
				String newLine = line.replace(tag, tags.get(tag));
				iterator.remove();
				return newLine;
			}
		}
		return line;
	}

	private static String lerConfigProperties() {
		StringBuilder sb = new StringBuilder();
		Set<String> keySet = ExecucaoReplicacaoUtil.getConfigKeySet();
		for (String key : keySet) {
			sb.append(key).append("=").append(ExecucaoReplicacaoUtil.getConfigString(key)).append(ExecucaoReplicacaoUtil.LINE_SEPARATOR);
		}
		return sb.toString();
	}
	
}
