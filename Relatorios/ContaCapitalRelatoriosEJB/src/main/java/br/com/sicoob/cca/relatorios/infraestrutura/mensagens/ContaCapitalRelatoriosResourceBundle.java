/*
 * 
 */
package br.com.sicoob.cca.relatorios.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ContaCapitalRelatorios
 * 
 * @author Balbi
 */
public final class ContaCapitalRelatoriosResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ContaCapitalRelatorios". */
	public static final String CONTACAPITALRELATORIOS_SICOOB_PROPERTIES = "cca.relatorios.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static ContaCapitalRelatoriosResourceBundle bundle = new ContaCapitalRelatoriosResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalRelatoriosResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected ContaCapitalRelatoriosResourceBundle() {
		this(CONTACAPITALRELATORIOS_SICOOB_PROPERTIES);
	}
	
	/**
	 * @param arquivoProperties
	 */
	protected ContaCapitalRelatoriosResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * BancoobResourceBundle
	 * @param arquivoProperties
	 * @param resourcePai
	 */
	protected ContaCapitalRelatoriosResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}