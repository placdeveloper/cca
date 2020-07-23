/*
 * 
 */
package br.com.sicoob.cca.replicacao.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.replicacao.persistencia.ContaCapitalReplicacaoDatasource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema
 * 
 * @author Balbi
 */
public abstract class ContaCapitalReplicacaoCrudDao<T extends BancoobEntidade> extends BancoobCrudDao<T> implements
		ContaCapitalReplicacaoCrudDaoIF<T> {

	/**
	 * Instancia um novo ContaCapitalReplicacaoCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalReplicacaoCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super("jdbc/BancoobCCADS","cca.replicacao.queries.xml", "comandos-cca-replicacao",clazz, nomeComandoPesquisar);
	}	
	
	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalReplicacaoCrudDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos,
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
			ContaCapitalReplicacaoDatasource datasource = new ContaCapitalReplicacaoDatasource(getNomeDatasource(), System
				.getProperties());

			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
}