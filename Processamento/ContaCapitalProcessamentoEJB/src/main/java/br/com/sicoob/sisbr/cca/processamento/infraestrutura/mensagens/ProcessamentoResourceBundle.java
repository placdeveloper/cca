/*
 * 
 */
package br.com.sicoob.sisbr.cca.processamento.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema Processamento
 * 
 */
public class ProcessamentoResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "Processamento". */
	public static final String PROCESSAMENTO_SICOOB_PROPERTIES = "cca_processamento.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static final ProcessamentoResourceBundle bundle = new ProcessamentoResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ProcessamentoResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected ProcessamentoResourceBundle() {
		this(PROCESSAMENTO_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo ProcessamentoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected ProcessamentoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo ProcessamentoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected ProcessamentoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
