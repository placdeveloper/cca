/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ContaCapitalIntegracao
 * 
 */
public final class ContaCapitalIntegracaoResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ContaCapitalIntegracao". */
	public static final String INTEGRACAO_SICOOB_PROPERTIES = "cca_integracao.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static ContaCapitalIntegracaoResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalIntegracaoResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (ContaCapitalIntegracaoResourceBundle.class) {
				if (bundle == null) {
					bundle = new ContaCapitalIntegracaoResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private ContaCapitalIntegracaoResourceBundle() {
		this(INTEGRACAO_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo ContaCapitalIntegracaoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected ContaCapitalIntegracaoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo ContaCapitalIntegracaoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected ContaCapitalIntegracaoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
