/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;

// TODO: Auto-generated Javadoc
/**
 * Datasource padrao para o sistema .
 *
 * @author Balbi
 */
public class ContaCapitalMovimentacaoDatasource extends CorporativoDataSource {

		
	
	/**
	 * Instancia um novo ContaCapitalMovimentacaoDatasource.
	 *
	 * @param nomeJndi o valor de nome jndi
	 * @param propriedades o valor de propriedades
	 */
	public ContaCapitalMovimentacaoDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
				
	}
	
		
}
