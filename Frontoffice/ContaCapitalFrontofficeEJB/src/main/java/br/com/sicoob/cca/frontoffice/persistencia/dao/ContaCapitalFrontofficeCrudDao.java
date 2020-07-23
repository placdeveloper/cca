package br.com.sicoob.cca.frontoffice.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.frontoffice.persistencia.ContaCapitalFrontofficeDatasource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema.
 *
 * @param <T> o tipo generico
 */
public abstract class ContaCapitalFrontofficeCrudDao<T extends BancoobEntidade> extends BancoobCrudDao<T> implements
		ContaCapitalFrontofficeCrudDaoIF<T> {

	/**
	 * Instancia um novo ContaCapitalFrontofficeCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalFrontofficeCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super("jdbc/BancoobCCADS","cca.frontoffice.queries.xml", "comandos-cca-frontoffice",clazz, nomeComandoPesquisar);
	}	
	
	/**
	 * Instancia um novo ContaCapitalFrontofficeCrudDao.
	 *
	 * @param nomeDatasource o valor de nome datasource
	 * @param arquivoQueries o valor de arquivo queries
	 * @param nomeColecaoComandos o valor de nome colecao comandos
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalFrontofficeCrudDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos,
			Class<T> clazz, String nomeComandoPesquisar) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar);
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	protected Connection estabelecerConexao() {
		try {
			ContaCapitalFrontofficeDatasource datasource = new ContaCapitalFrontofficeDatasource(getNomeDatasource(), 
					System.getProperties());

			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
}