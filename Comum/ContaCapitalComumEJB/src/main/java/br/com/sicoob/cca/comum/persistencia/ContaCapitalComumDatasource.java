/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;

/**
 * Datasource padrao para o sistema ContaCapitalComum
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalComumDatasource extends BancoobDataSource {

		
	
	/**
	 * @param nomeJndi
	 * @param propriedades
	 */
	public ContaCapitalComumDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
				
	}
	
		
}
