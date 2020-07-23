/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao;

import java.sql.Connection;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ContaCapitalRelatorios
 * 
 * @author Balbi
 */
public abstract class ContaCapitalRelatoriosCrudDao<T extends BancoobEntidade> extends BancoobCrudDao<T> implements
		ContaCapitalRelatoriosCrudDaoIF<T> {

	/**
	 * Instancia um novo ContaCapitalRelatoriosCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalRelatoriosCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super("jdbc/BancoobCCADS","cca.relatorios.queries.xml", "comandos-cca-relatorios",clazz, nomeComandoPesquisar);
	}	
	
	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalRelatoriosCrudDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos,
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
			CorporativoDataSource datasource = new CorporativoDataSource(getNomeDatasource(), System
				.getProperties());

			return datasource.getConnection();
		} catch (Exception excecao) {
			throw new PersistenciaException(excecao);
		}
	}
}