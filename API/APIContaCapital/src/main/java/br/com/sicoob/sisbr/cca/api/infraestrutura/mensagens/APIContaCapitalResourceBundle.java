/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema APIContaCapital
 * 
 */
public final class APIContaCapitalResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "APIContaCapital". */
	public static final String API_SICOOB_PROPERTIES = "cca_api.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static APIContaCapitalResourceBundle bundle = new APIContaCapitalResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static APIContaCapitalResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected APIContaCapitalResourceBundle() {
		this(API_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo APIContaCapitalResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected APIContaCapitalResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo APIContaCapitalResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected APIContaCapitalResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
