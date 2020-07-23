package br.com.sicoob.cca.frontoffice.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * A Classe ContaCapitalFrontofficeResourceBundle.
 */
public final class ContaCapitalFrontofficeResourceBundle extends BancoobResourceBundle {

	/** A constante CONTACAPITALFRONTOFFICE_SICOOB_PROPERTIES. */
	public static final String CONTACAPITALFRONTOFFICE_SICOOB_PROPERTIES = "cca.frontoffice.bancoob.properties";

	/** O atributo bundle. */
	private static ContaCapitalFrontofficeResourceBundle bundle = new ContaCapitalFrontofficeResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalFrontofficeResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected ContaCapitalFrontofficeResourceBundle() {
		this(CONTACAPITALFRONTOFFICE_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo ContaCapitalFrontofficeResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected ContaCapitalFrontofficeResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * BancoobResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected ContaCapitalFrontofficeResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}