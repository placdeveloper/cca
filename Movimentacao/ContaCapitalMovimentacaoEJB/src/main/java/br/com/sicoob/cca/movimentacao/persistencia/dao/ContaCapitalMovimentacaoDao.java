/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;

// TODO: Auto-generated Javadoc
/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema .
 *
 * @author Balbi
 */
public abstract class ContaCapitalMovimentacaoDao extends BancoobDao {

	/** A constante DATA_SOURCE. */
	public static final String DATA_SOURCE = "jdbc/BancoobCCADS";

	/** A constante ARQUIVO_QUERIES. */
	public static final String ARQUIVO_QUERIES = "cca.movimentacao.queries.xml";

	/**
	 * Instancia um novo ContaCapitalMovimentacaoDao.
	 *
	 * @param nomeDatasource      o valor de nome datasource
	 * @param arquivoQueries      o valor de arquivo queries
	 * @param nomeColecaoComandos o valor de nome colecao comandos
	 */
	public ContaCapitalMovimentacaoDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {
		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}

	/**
	 * Instancia um novo ContaCapitalMovimentacaoDao.
	 */
	public ContaCapitalMovimentacaoDao() {
		super(DATA_SOURCE, ARQUIVO_QUERIES, null);
	}

	/**
	 * Estabelecer conexao.
	 *
	 * @return Connection
	 * @see br.com.bancoob.persistencia.dao.BancoobDao#estabelecerConexao()
	 */
	@Override
	protected Connection estabelecerConexao() {
		try {
			DataSource dataSource = new CorporativoDataSource(getNomeDatasource(), System.getProperties());
			return dataSource.getConnection();
		} catch (Exception excecao) {
			throw new PersistenciaException(excecao);
		}
	}

}