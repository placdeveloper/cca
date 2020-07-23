/*
 * 
 */
package br.com.sicoob.cca.movimentacao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * A Classe ContaCapitalMovimentacaoResourceBundle.
 *
 * @author Balbi
 */
public final class ContaCapitalMovimentacaoResourceBundle extends BancoobResourceBundle {

	/** A constante CONTACAPITALMOVIMENTACAO_SICOOB_PROPERTIES. */
	public static final String CONTACAPITALMOVIMENTACAO_SICOOB_PROPERTIES = "cca.movimentacao.bancoob.properties";

	/** O atributo bundle. */
	private static ContaCapitalMovimentacaoResourceBundle bundle = new ContaCapitalMovimentacaoResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalMovimentacaoResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected ContaCapitalMovimentacaoResourceBundle() {
		this(CONTACAPITALMOVIMENTACAO_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo ContaCapitalMovimentacaoResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected ContaCapitalMovimentacaoResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * BancoobResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected ContaCapitalMovimentacaoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}