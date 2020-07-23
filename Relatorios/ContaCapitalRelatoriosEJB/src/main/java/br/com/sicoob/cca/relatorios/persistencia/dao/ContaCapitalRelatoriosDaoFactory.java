/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.FechRelPosicaoDiariaCarteiraDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelAprovacaoQuadroPendenciaDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelDesligamentoAssociadoDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelImpedimentosDesligamentoDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelLancamentosDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelParcelamentoContaCapitalDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelParticipacaoIndiretaSingularDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelSaldoAtualDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelSituacaoMatriculaCCARenDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.impl.RelSituacaoPeriodoCCARenDaoImpl;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.FechRelPosicaoDiariaCarteiraDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelAprovacaoQuadroPendenciaDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelDesligamentoAssociadoDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelImpedimentosDesligamentoDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelLancamentosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelParcelamentoContaCapitalDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelParticipacaoIndiretaSingularDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSaldoAtualDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSituacaoMatriculaCCARenDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSituacaoPeriodoCCARenDao;

/**
 * Fabrica de objetos da camada de acesso aos dados do sistema ContaCapitalRelatorios
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalRelatoriosDaoFactory extends BancoobDaoFactory {

	/** Instancia do DAOFactory. */
	private static ContaCapitalRelatoriosDaoFactory factory = new ContaCapitalRelatoriosDaoFactory();

	/** 
	 * Retorna a fabrica de DAO's a ser utilizada.
	 * 
	 * @return a fabrica de DAO's a ser utilizada.
	 */
	public static ContaCapitalRelatoriosDaoFactory getInstance() {
		return factory;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalRelatoriosDaoFactory() {
		
	}
	
	/**
	 * @return RelParticipacaoIndiretaSingularDao
	 */
	public RelParticipacaoIndiretaSingularDao criarRelParticipacaoIndiretaSingularDao(){
		return new RelParticipacaoIndiretaSingularDaoImpl();
	}	
	
	/**
	 * @return RelAprovacaoQuadroPendenciaDao
	 */
	public RelAprovacaoQuadroPendenciaDao criarRelAprovacaoQuadroPendenciaDao() {
		return new RelAprovacaoQuadroPendenciaDaoImpl();
	}
	
	/**
	 * @return RelDesligamentoAssociadoDao
	 */
	public RelDesligamentoAssociadoDao criarRelDesligamentoAssociadoDao() {
		return new RelDesligamentoAssociadoDaoImpl();
	}
	
	/**
	 * @return RelImpedimentosDesligamentoDao
	 */
	public RelImpedimentosDesligamentoDao criarRelImpedimentosDesligamentoDao() {
		return new RelImpedimentosDesligamentoDaoImpl();
	}

	/**
	 * @return RelParcelamentoContaCapitalDao
	 */
	public RelParcelamentoContaCapitalDao criarRelParcelamentoContaCapitalDao() {
		return new RelParcelamentoContaCapitalDaoImpl();
	}
	
	/**
	 * @return RelSaldoAtualDaoImpl
	 */
	public RelSaldoAtualDao criarRelSaldoAtualDao() {
		return new RelSaldoAtualDaoImpl();
	}
	
	/**
	 * Criar RelSituacaoMatriculaCCARenDaoImpl.
	 *
	 * @return RelSituacaoMatriculaCCARenDaoImpl
	 */
	public RelSituacaoMatriculaCCARenDao criarRelSituacaoMatriculaCCARenDao() {
		return new RelSituacaoMatriculaCCARenDaoImpl();
	}
	
	/**
	 * Criar rel situacao periodo CCA ren dao.
	 *
	 * @return the rel situacao periodo CCA ren dao
	 */
	public RelSituacaoPeriodoCCARenDao criarRelSituacaoPeriodoCCARenDao() {
		return new RelSituacaoPeriodoCCARenDaoImpl();
	}
	
	/**
	 * Criar FechRelPosicaoDiariaCarteiraDao.
	 *
	 * @return FechRelPosicaoDiariaCarteiraDao
	 */
	public FechRelPosicaoDiariaCarteiraDao criarFechRelPosicaoDiariaCarteiraDao() {
		return new FechRelPosicaoDiariaCarteiraDaoImpl();
	}
	
	/**
	 * Criar RelLancamentosDao
	 * @return RelLancamentosDao
	 */
	public RelLancamentosDao criarRelLancamentosDao() {
		return new RelLancamentosDaoImpl();
	}
	
}