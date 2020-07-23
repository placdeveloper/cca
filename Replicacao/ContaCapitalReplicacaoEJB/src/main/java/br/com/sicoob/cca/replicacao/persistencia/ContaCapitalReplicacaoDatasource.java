/*
 * 
 */
package br.com.sicoob.cca.replicacao.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;

/**
 * Datasource padrao para o sistema 
 * 
 * @author Balbi
 */
public class ContaCapitalReplicacaoDatasource extends CorporativoDataSource {

		
	
	/**
	 * @param nomeJndi
	 * @param propriedades
	 */
	public ContaCapitalReplicacaoDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
				
	}
	
		
}
