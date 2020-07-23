/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;

/**
 * Datasource padrao para o sistema ContaCapitalCadastro
 * 
 * @author Stefanini IT Solutions
 */
public class ContaCapitalCadastroDatasource extends CorporativoDataSource {

	/**
	 * @param nomeJndi
	 * @param propriedades
	 */
	public ContaCapitalCadastroDatasource(String nomeJndi, Properties propriedades) {
		super(nomeJndi, propriedades);
				
	}
}