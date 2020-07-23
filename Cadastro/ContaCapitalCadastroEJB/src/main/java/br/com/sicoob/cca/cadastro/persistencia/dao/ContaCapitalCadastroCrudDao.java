/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao;

import java.sql.Connection;

import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.cadastro.persistencia.ContaCapitalCadastroDatasource;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ContaCapitalCadastro
 * 
 * @author Jarbasjstefanini
 */
public abstract class ContaCapitalCadastroCrudDao<T extends BancoobEntidade> extends BancoobCrudDao<T> implements
		ContaCapitalCadastroCrudDaoIF<T> {

	/**
	 * Instancia um novo ContaCapitalCadastroCrudDao.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ContaCapitalCadastroCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
		super("jdbc/BancoobCCADS","cca.cadastro.queries.xml", "comandos-cca-cadastro",clazz, nomeComandoPesquisar);
	}	
	
	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public ContaCapitalCadastroCrudDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos,
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
			ContaCapitalCadastroDatasource datasource = new ContaCapitalCadastroDatasource(getNomeDatasource(), System
				.getProperties());

			return datasource.getConnection();
		} catch (Exception excecao) {
			throw new PersistenciaException(excecao);
		}
	}
}