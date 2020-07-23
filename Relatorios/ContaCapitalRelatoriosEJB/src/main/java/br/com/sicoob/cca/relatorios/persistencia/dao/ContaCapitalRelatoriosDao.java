/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ContaCapitalRelatorios
 * 
 * @author Stefanini IT Solutions
 */
public abstract class ContaCapitalRelatoriosDao extends BancoobDao {

	/** A constante DATA_SOURCE. */
	public static final String DATA_SOURCE = "jdbc/BancoobCCADS";
	
	/** A constante ARQUIVO_QUERIES. */
	public static final String ARQUIVO_QUERIES = "cca_relatorios.queries.xml";
	
	/** A constante NOME_COLECAO_COMANDOS. */
	public static final String NOME_COLECAO_COMANDOS = "comandos-cca-relatorios";
		
	/**
	 * @param nomeDatasource
	 * @param arquivoQueries
	 * @param nomeColecaoComandos
	 */
	public ContaCapitalRelatoriosDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {
		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}

	/**
	 * Construtor
	 */
	public ContaCapitalRelatoriosDao() {
		super(DATA_SOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}
	
	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			DataSource dataSource = new CorporativoDataSource(getNomeDatasource(),System.getProperties());
			return dataSource.getConnection();
		} catch (Exception excecao) {
			throw new PersistenciaException(excecao);
		}
	}		
	
}