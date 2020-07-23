/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ContaCapitalComum
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalComumDao extends BancoobDao {
	
	/** A constante DATA_SOURCE. */
	public static final String DATA_SOURCE = "jdbc/BancoobCCADS";
	
	/** A constante ARQUIVO_QUERIES. */
	public static final String ARQUIVO_QUERIES = "cca.comum.queries.xml";
	
	/** A constante NOME_COLECAO_COMANDOS. */
	public static final String NOME_COLECAO_COMANDOS = "comandos-cca-comum";

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalComumDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {
		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}
	
	/**
	 * Instancia um novo ContaCapitalComumDao.
	 */
	public ContaCapitalComumDao() {
		super(DATA_SOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}
	
	protected Connection estabelecerConexaoCorporativa() {
		try {
			DataSource datasource = new CorporativoDataSource(getNomeDatasource(),System.getProperties());
			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}
	
}