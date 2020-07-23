/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao;

import java.sql.Connection;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.persistencia.ContaCapitalComumDatasource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ContaCapitalComum
 * 
 * @author Jarbasjstefanini
 */
public abstract class ContaCapitalComumCrudDao<T extends BancoobEntidade> extends BancoobCrudDao<T> implements
		ContaCapitalComumCrudDaoIF<T> {

	/** A constante DATA_SOURCE. */
	public static final String DATA_SOURCE = "jdbc/BancoobCCADS";
	
	/** A constante ARQUIVO_QUERIES. */
	public static final String ARQUIVO_QUERIES = "cca.comum.queries.xml";
	
	/** A constante NOME_COLECAO_COMANDOS. */
	public static final String NOME_COLECAO_COMANDOS = "comandos-cca-comum";
	
	/**
	 * Instancia um novo ContaCapitalComumCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalComumCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super(DATA_SOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, clazz, nomeComandoPesquisar);
	}
	
	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalComumCrudDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos,
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
			ContaCapitalComumDatasource datasource = new ContaCapitalComumDatasource(getNomeDatasource(), System
				.getProperties());

			return datasource.getConnection();
		} catch (Exception excecao) {
			throw new PersistenciaException(excecao);
		}
	}
}