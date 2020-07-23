/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ContaCapitalIntegracaoLegado
 * 
 */
public final class ContaCapitalIntegracaoLegadoResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ContaCapitalIntegracaoLegado". */
	public static final String INTEGRACAO_SICOOB_PROPERTIES = "cca_legado_integracao.bancoob.properties";																

	/** Resource bundle a ser usada. */
	private static ContaCapitalIntegracaoLegadoResourceBundle bundle;

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalIntegracaoLegadoResourceBundle getInstance() {
		if (bundle == null) {
			synchronized (ContaCapitalIntegracaoLegadoResourceBundle.class) {
				if (bundle == null) {
					bundle = new ContaCapitalIntegracaoLegadoResourceBundle();
				}
			}
		}
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	private ContaCapitalIntegracaoLegadoResourceBundle() {
		this(INTEGRACAO_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo ContaCapitalIntegracaoLegadoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected ContaCapitalIntegracaoLegadoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo ContaCapitalIntegracaoLegadoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected ContaCapitalIntegracaoLegadoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
