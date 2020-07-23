/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;
import br.com.sicoob.cca.comum.persistencia.dao.impl.FechamentoContaCapitalDaoImpl;
import br.com.sicoob.cca.comum.persistencia.dao.impl.InformacaoProfissionalDaoImpl;
import br.com.sicoob.cca.comum.persistencia.dao.impl.OperacaoContaCapitalDaoImpl;
import br.com.sicoob.cca.comum.persistencia.dao.impl.PesquisaContaCapitalDaoImpl;
import br.com.sicoob.cca.comum.persistencia.dao.impl.ViewInstituicaoDaoImpl;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.FechamentoContaCapitalDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.InformacaoProfissionalDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.OperacaoContaCapitalDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.PesquisaContaCapitalDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao;
import br.com.sicoob.cca.entidades.negocio.entidades.OperacaoContaCapital;

/**
 * Fabrica de objetos da camada de acesso aos dados do sistema ContaCapitalComum
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalComumDaoFactory extends BancoobDaoFactory {

	/** Instancia do DAOFactory. */
	private static ContaCapitalComumDaoFactory factory = new ContaCapitalComumDaoFactory();

	/**
	 * Retorna a fabrica de DAO's a ser utilizada.
	 * 
	 * @return a fabrica de DAO's a ser utilizada.
	 */
	public static ContaCapitalComumDaoFactory getInstance() {
		return factory;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalComumDaoFactory() {
		
	}
	
	/**
	 * @return FechamentoContaCapitalDao
	 */
	public FechamentoContaCapitalDao criarFechamentoContaCapitalDao() {
		return new FechamentoContaCapitalDaoImpl();
	}
	
	/**
	 * @return FechamentoContaCapitalDao
	 */
	public PesquisaContaCapitalDao criarPesquisaContaCapitalDao() {
		return new PesquisaContaCapitalDaoImpl();
	}
	
	/**
	 * @return ViewInstituicaoDao
	 */
	public ViewInstituicaoDao criarViewInstituicaoDao() {
		return new ViewInstituicaoDaoImpl();
	}	
	
	/**
	 * @return criarOperacaoContaCapitalDao
	 */
	public OperacaoContaCapitalDao criarOperacaoContaCapitalDao() {
		return new OperacaoContaCapitalDaoImpl(OperacaoContaCapital.class, "OBTEROPERACAOCONTACAPITAL");
	}
	
	/**
	 * @return criarInformacaoProfissionalDao
	 */
	public InformacaoProfissionalDao criarInformacaoProfissionalDao() {
		return new InformacaoProfissionalDaoImpl();
	}
}