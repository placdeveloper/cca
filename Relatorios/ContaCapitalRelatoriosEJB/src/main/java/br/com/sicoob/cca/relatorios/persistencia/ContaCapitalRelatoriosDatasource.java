/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;

/**
 * Datasource padrao para o sistema ContaCapitalRElatorios
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalRelatoriosDatasource extends BancoobDataSource {

		
	
	/**
	 * @param nomeJndi
	 * @param propriedades
	 */
	public ContaCapitalRelatoriosDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
				
	}
	
		
}
