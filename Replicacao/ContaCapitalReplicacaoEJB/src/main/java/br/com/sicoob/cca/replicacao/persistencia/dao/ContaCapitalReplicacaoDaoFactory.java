/*
 * 
 */
package br.com.sicoob.cca.replicacao.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;
import br.com.sicoob.cca.replicacao.persistencia.dao.impl.ReplicacaoContaCapitalDaoImpl;
import br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao;

/**
 * Fabrica de objetos da camada de acesso aos dados do sistema
 * 
 * @author Balbi
 */
public final class ContaCapitalReplicacaoDaoFactory extends BancoobDaoFactory {

	/** Instancia do DAOFactory. */
	private static ContaCapitalReplicacaoDaoFactory factory = new ContaCapitalReplicacaoDaoFactory();

	/** 
	 * Retorna a fabrica de DAO's a ser utilizada.
	 * 
	 * @return a fabrica de DAO's a ser utilizada.
	 */
	public static ContaCapitalReplicacaoDaoFactory getInstance() {
		return factory;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalReplicacaoDaoFactory() {	
		
	}
	
	/**
	 * ReplicacaoContaCapital
	 * @return
	 */
	public ReplicacaoContaCapitalDao criarReplicacaoContaCapitalDao(){
		return new ReplicacaoContaCapitalDaoImpl();
	}
	
}