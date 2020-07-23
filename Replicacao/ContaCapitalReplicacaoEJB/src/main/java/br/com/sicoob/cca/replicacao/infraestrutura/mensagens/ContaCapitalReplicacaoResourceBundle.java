/*
 * 
 */
package br.com.sicoob.cca.replicacao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * @author Balbi
 */
public final class ContaCapitalReplicacaoResourceBundle extends BancoobResourceBundle {

	/** A constante CONTACAPITALREPLICACAO_SICOOB_PROPERTIES. */
	public static final String CONTACAPITALREPLICACAO_SICOOB_PROPERTIES = "cca.replicacao.bancoob.properties";

	/** O atributo bundle. */
	private static ContaCapitalReplicacaoResourceBundle bundle = new ContaCapitalReplicacaoResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalReplicacaoResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected ContaCapitalReplicacaoResourceBundle() {
		this(CONTACAPITALREPLICACAO_SICOOB_PROPERTIES);
	}
	
	/**
	 * @param arquivoProperties
	 */
	protected ContaCapitalReplicacaoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * BancoobResourceBundle
	 * @param arquivoProperties
	 * @param resourcePai
	 */
	protected ContaCapitalReplicacaoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}