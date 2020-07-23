/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDao;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ContaCapitalCadastro
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalCadastroDao extends BancoobDao {

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalCadastroDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}
}