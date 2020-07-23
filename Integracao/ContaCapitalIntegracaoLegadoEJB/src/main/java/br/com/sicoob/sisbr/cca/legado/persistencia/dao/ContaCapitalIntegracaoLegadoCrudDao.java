/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalIntegracaoLegadoEntidade;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema
 * ContaCapitalIntegracaoLegado
 * 
 */
public abstract class ContaCapitalIntegracaoLegadoCrudDao<T extends ContaCapitalIntegracaoLegadoEntidade>
		extends BancoobCrudDao<T> implements ContaCapitalIntegracaoLegadoCrudDaoIF<T> {

	/**
	 * Instancia um novo ContaCapitalIntegracaoLegadoCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalIntegracaoLegadoCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super("jdbc/BancoobDS","cca_legado_integracao.queries.xml", "comandos-cca-integracao",clazz, nomeComandoPesquisar);
	}

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalIntegracaoLegadoCrudDao(String nomeDatasource,
			String arquivoQueries, String nomeColecaoComandos, Class<T> clazz,
			String nomeComandoPesquisar) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos, clazz,
				nomeComandoPesquisar);
	}

	/**
	 * Estabelece a conexao com o banco de dados.
	 * 
	 * @return a conexao com o banco de dados.
	 */
	protected Connection estabelecerConexao() {
		try {
			ContaCapitalIntegracaoLegadoDataSource datasource = new ContaCapitalIntegracaoLegadoDataSource(
					getNomeDatasource(), System.getProperties());

			return datasource.getConnection();
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		}
	}

}