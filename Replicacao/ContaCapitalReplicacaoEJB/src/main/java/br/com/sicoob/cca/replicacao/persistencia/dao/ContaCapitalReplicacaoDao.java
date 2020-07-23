/*
 * 
 */
package br.com.sicoob.cca.replicacao.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema 
 * 
 * @author Balbi
 */
public abstract class ContaCapitalReplicacaoDao extends BancoobDao {

	/** A constante DATA_SOURCE. */
	public static final String DATA_SOURCE = "jdbc/BancoobCCADS";
	
	/** A constante ARQUIVO_QUERIES. */
	public static final String ARQUIVO_QUERIES = "cca_replicacao.queries.xml";

	/** A constante NOME_COLECAO_COMANDOS. */
	public static final String NOME_COLECAO_COMANDOS = "comandos-cca-replicacao";	
	
	/**
	 * Instancia um novo ContaCapitalReplicacaoDao.
	 *
	 * @param nomeDatasource o valor de nome datasource
	 * @param arquivoQueries o valor de arquivo queries
	 * @param nomeColecaoComandos o valor de nome colecao comandos
	 */
	public ContaCapitalReplicacaoDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {
		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}

	/**
	 * Instancia um novo ContaCapitalReplicacaoDao.
	 */
	public ContaCapitalReplicacaoDao() {
		super(DATA_SOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobDao#estabelecerConexao()
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			BancoobDataSource datasource = new BancoobDataSource(getNomeDatasource(), System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}	
	
	protected Connection estabelecerConexaoCorporativa() {
		try {
			DataSource datasource = new CorporativoDataSource(getNomeDatasource(),System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
	
	/**
	 * Fecha o resultSet
	 * @param resultSet
	 */
	protected void fecharResultSetReplicacao(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException excecao) {
				throw new PersistenciaException(excecao);				
			}
		}
	}	
	
	
	
	
	
}