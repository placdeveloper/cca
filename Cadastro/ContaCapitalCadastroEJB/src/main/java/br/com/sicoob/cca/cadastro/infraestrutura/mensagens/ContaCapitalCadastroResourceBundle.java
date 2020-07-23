/*
 * 
 */
package br.com.sicoob.cca.cadastro.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema ContaCapitalCadastro
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalCadastroResourceBundle extends BancoobResourceBundle {

	/** Nome do arquivo de mensagens do sistema "ContaCapitalCadastro". */
	public static final String CONTACAPITALCADASTRO_SICOOB_PROPERTIES = "cca.cadastro.bancoob.properties";

	/** Resource bundle a ser usada. */
	private static ContaCapitalCadastroResourceBundle bundle = new ContaCapitalCadastroResourceBundle();

	/**
	 * Retorna o bundle a ser usado.
	 * 
	 * @return o bundle a ser usado.
	 */
	public static ContaCapitalCadastroResourceBundle getInstance() {
		return bundle;
	}

	/**
	 * Inicia o bundle com as suas propriedades padrao.
	 */
	protected ContaCapitalCadastroResourceBundle() {
		this(CONTACAPITALCADASTRO_SICOOB_PROPERTIES);
	}
	
	/**
	 * Instancia um novo ContaCapitalCadastroResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 */
	protected ContaCapitalCadastroResourceBundle(String arquivoProperties) {
		this(arquivoProperties, CorporativoResourceBundle.getInstance());
	}

	/**
	 * Instancia um novo ContaCapitalCadastroResourceBundle.
	 *
	 * @param arquivoProperties o valor de arquivo properties
	 * @param resourcePai o valor de resource pai
	 */
	protected ContaCapitalCadastroResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
		super(arquivoProperties, resourcePai);
	}
}
