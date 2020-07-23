package br.com.sicoob.cca.frontoffice.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;

/**
 * Fabrica de objetos da camada de acesso aos dados do sistema.
 */
public final class ContaCapitalFrontofficeDaoFactory extends BancoobDaoFactory {

	/** Instancia do DAOFactory. */
	private static ContaCapitalFrontofficeDaoFactory factory = new ContaCapitalFrontofficeDaoFactory();

	/** 
	 * Retorna a fabrica de DAO's a ser utilizada.
	 * 
	 * @return a fabrica de DAO's a ser utilizada.
	 */
	public static ContaCapitalFrontofficeDaoFactory getInstance() {
		return factory;
	}

	/**
	 * Construtor privado no-args da classe.
	 */
	protected ContaCapitalFrontofficeDaoFactory() {	
		
	}

}