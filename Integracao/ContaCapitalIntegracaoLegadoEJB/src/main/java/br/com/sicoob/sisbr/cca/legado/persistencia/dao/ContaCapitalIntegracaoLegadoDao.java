/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDao;

/**
 * A Classe ContaCapitalIntegracaoLegadoDao.
 */
public abstract class ContaCapitalIntegracaoLegadoDao extends BancoobDao {

	/** A constante DATA_SOURCE. */
	public static final String DATA_SOURCE = "jdbc/BancoobDS";
	
	/** A constante ARQUIVO_QUERIES. */
	public static final String ARQUIVO_QUERIES = "cca_legado_integracao.queries.xml";
	
	/** A constante NOME_COLECAO_COMANDOS. */
	public static final String NOME_COLECAO_COMANDOS = "comandos-cca-integracao";
	
	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalIntegracaoLegadoDao(String nomeDatasource, String arquivoQueries,
			String nomeColecaoComandos) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}

	/**
	 * Instancia um novo ContaCapitalIntegracaoLegadoDao.
	 */
	public ContaCapitalIntegracaoLegadoDao() {
		super(DATA_SOURCE, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}
	
}
