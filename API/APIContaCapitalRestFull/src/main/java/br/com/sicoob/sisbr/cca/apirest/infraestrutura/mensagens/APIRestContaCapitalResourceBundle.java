/*
 * 
 */
package br.com.sicoob.sisbr.cca.apirest.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema APIRestContaCapital
 * 
 */
public final class APIRestContaCapitalResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "APIContaCapital". */
	public static final String API_REST_SICOOB_PROPERTIES = "cca_apirest.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static APIRestContaCapitalResourceBundle bundle = new APIRestContaCapitalResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static APIRestContaCapitalResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected APIRestContaCapitalResourceBundle() {
		this(API_REST_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo APIContaCapitalResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected APIRestContaCapitalResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo APIContaCapitalResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected APIRestContaCapitalResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
