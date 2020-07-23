package br.com.sicoob.cca.frontoffice.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;

/**
 * Datasource padrao para o sistema.
 */
public class ContaCapitalFrontofficeDatasource extends CorporativoDataSource {
	
	/**
	 * Instancia um novo ContaCapitalFrontofficeDatasource.
	 *
	 * @param nomeJndi o valor de nome jndi
	 * @param propriedades o valor de propriedades
	 */
	public ContaCapitalFrontofficeDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
	}
		
}
