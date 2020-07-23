/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao;

import java.sql.Connection;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.movimentacao.persistencia.ContaCapitalMovimentacaoDatasource;

// TODO: Auto-generated Javadoc
/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema.
 *
 * @author Balbi
 * @param <T> o tipo generico
 */
public abstract class ContaCapitalMovimentacaoCrudDao<T extends BancoobEntidade> extends BancoobCrudDao<T> implements
		ContaCapitalMovimentacaoCrudDaoIF<T> {

	/**
	 * Instancia um novo ContaCapitalMovimentacaoCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalMovimentacaoCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super("jdbc/BancoobCCADS","cca.movimentacao.queries.xml", "comandos-cca-movimentacao",clazz, nomeComandoPesquisar);
	}	
	
	/**
	 * Instancia um novo ContaCapitalMovimentacaoCrudDao.
	 *
	 * @param nomeDatasource o valor de nome datasource
	 * @param arquivoQueries o valor de arquivo queries
	 * @param nomeColecaoComandos o valor de nome colecao comandos
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalMovimentacaoCrudDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos,
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
			ContaCapitalMovimentacaoDatasource datasource = new ContaCapitalMovimentacaoDatasource(getNomeDatasource(), System
				.getProperties());

			return datasource.getConnection();
		} catch (Exception excecao) {
			throw new PersistenciaException(excecao);
		}
	}
}