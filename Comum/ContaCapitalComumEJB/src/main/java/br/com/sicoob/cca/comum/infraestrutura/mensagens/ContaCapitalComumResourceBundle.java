/*
 * 
 */
package br.com.sicoob.cca.comum.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ContaCapitalComum
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalComumResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ContaCapitalComum". */
	public static final String CONTACAPITALCOMUM_SICOOB_PROPERTIES = "cca.comum.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static ContaCapitalComumResourceBundle bundle = new ContaCapitalComumResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalComumResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected ContaCapitalComumResourceBundle() {
		this(CONTACAPITALCOMUM_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo ContaCapitalComumResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected ContaCapitalComumResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo ContaCapitalComumResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected ContaCapitalComumResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}