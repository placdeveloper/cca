/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCorrenteView;
import br.com.sicoob.cca.entidades.negocio.entidades.HistBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.HistParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.HistoricoLancamentoCCA;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.OrigemBloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoIndiretaBancoob;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.BloqueioContaCapitalDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.ContaCorrenteViewDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.DebitoIndeterminadoDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.GestaoEmpresarialDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.HistBloqueioContaCapitalDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.HistParticipacaoCentralBancoobDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.HistoricoLancamentoContaCapitalDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.LancamentoContaCapitalDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.OrigemBloqueioCapitalDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.ParcelamentoContaCapitalDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.ParticipacaoCentralBancoobDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.ParticipacaoIndiretaBancoobDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.impl.SaldoContaCapitalDaoImpl;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.BloqueioContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ContaCorrenteViewDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.DebitoIndeterminadoDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.GestaoEmpresarialDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistBloqueioContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistParticipacaoCentralBancoobDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.HistoricoLancamentoContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.OrigemBloqueioCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParcelamentoContaCapitalDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoCentralBancoobDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParticipacaoIndiretaBancoobDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.SaldoContaCapitalDao;

// TODO: Auto-generated Javadoc
/**
 * Fabrica de objetos da camada de acesso aos dados do sistema.
 *
 * @author Balbi
 */
public final class ContaCapitalMovimentacaoDaoFactory extends BancoobDaoFactory {

	/** Instancia do DAOFactory. */
	private static ContaCapitalMovimentacaoDaoFactory factory = new ContaCapitalMovimentacaoDaoFactory();

	/** 
	 * Retorna a fabrica de DAO's a ser utilizada.
	 * 
	 * @return a fabrica de DAO's a ser utilizada.
	 */
	public static ContaCapitalMovimentacaoDaoFactory getInstance() {
		return factory;
	}

	/**
	 * Construtor privado no-args da classe.
	 */
	protected ContaCapitalMovimentacaoDaoFactory() {	
		
	}
	

	/**
	 * Cria uma instancia de ParcelamentoContaCapitalDao.
	 *
	 * @return LancamentoContaCapitalDao
	 * @see ParcelamentoContaCapitalDao
	 */
	public ParcelamentoContaCapitalDao criarParcelamentoContaCapitalDao() {
		return new ParcelamentoContaCapitalDaoImpl(Parcelamento.class, "OBTERPARCELAMENTOCONTACAPITAL");
	}		

	/**
	 * Cria uma instancia de LancamentoContaCapitalDao.
	 *
	 * @return LancamentoContaCapitalDao
	 * @see LancamentoContaCapitalDao
	 */
	public LancamentoContaCapitalDao criarLancamentoContaCapitalDao() {
		return new LancamentoContaCapitalDaoImpl(LancamentoContaCapital.class, "OBTERLANCAMENTOCONTACAPITAL");
	}		

	/**
	 * Cria uma instancia de HistoricoLancamentoContaCapitalDao.
	 *
	 * @return HistoricoLancamentoContaCapitalDao
	 * @see HistoricoLancamentoContaCapitalDao
	 */
	public HistoricoLancamentoContaCapitalDao criarHistoricoLancamentoContaCapitalDao() {
		return new HistoricoLancamentoContaCapitalDaoImpl(HistoricoLancamentoCCA.class, "OBTERHISTORICOLANCAMENTOCONTACAPITAL");
	}		
	
	/**
	 * Cria uma instancia de GestaoEmpresarialDao.
	 *
	 * @return GestaoEmpresarialDao
	 * @see GestaoEmpresarialDao
	 */
	public GestaoEmpresarialDao criarGestaoEmpresarialDao() {
		return new GestaoEmpresarialDaoImpl();
	}
	
	/**
	 * Cria uma instancia de BloqueioContaCapitalDao.
	 *
	 * @return BloqueioContaCapitalDao
	 * @see BloqueioContaCapitalDao
	 */
	public BloqueioContaCapitalDao criarBloqueioContaCapitalDao() {
		return new BloqueioContaCapitalDaoImpl(BloqueioCapital.class, "OBTERBLOQUEIOCAPITAL");
	}		
	
	/**
	 * Cria uma instancia de HistBloqueioContaCapitalDao.
	 *
	 * @return HistBloqueioContaCapitalDao
	 * @see HistBloqueioContaCapitalDao
	 */
	public HistBloqueioContaCapitalDao criarHistBloqueioContaCapitalDao() {
		return new HistBloqueioContaCapitalDaoImpl(HistBloqueioCapital.class, "OBTERHISTBLOQUEIOCAPITAL");
	}
	
	/**
	 * Cria uma instancia de DebitoIndeterminadoDao.
	 *
	 * @return DebitoIndeterminadoDao
	 * @see DebitoIndeterminadoDao
	 */
	public DebitoIndeterminadoDao criarDebitoIndeterminadoDao() {
		return new DebitoIndeterminadoDaoImpl();
	}

	/**
	 * Cria uma instancia de OrigemBloqueioCapitalDao.
	 *
	 * @return OrigemBloqueioCapitalDao
	 * @see OrigemBloqueioCapitalDao
	 */
	public OrigemBloqueioCapitalDao criarOrigemBloqueioCapitalDao() {
		return new OrigemBloqueioCapitalDaoImpl(OrigemBloqueioCapital.class, "OBTERORIGEMBLOQUEIOCAPITAL");
	}
	
	/**
	 * Cria uma instancia de ContaCorrenteViewDao.
	 *
	 * @return ContaCorrenteViewDao
	 * @see ContaCorrenteViewDao
	 */
	public ContaCorrenteViewDao criarContaCorrenteViewDao() {
		return new ContaCorrenteViewDaoImpl(ContaCorrenteView.class, "OBTERCONTACORRENTEVIEW");
	}
	
	/**
	 * {@link ParticipacaoCentralBancoobDao}
	 * @return
	 */
	public ParticipacaoCentralBancoobDao criarParticipacaoCentralBancoobDao(){
		return new ParticipacaoCentralBancoobDaoImpl(ParticipacaoCentralBancoob.class, "OBTERPARTICIPACAOCENTRALBANCOOB");
	}
	
	/**
	 * 
	 * @return
	 */
	public HistParticipacaoCentralBancoobDao criarHistParticipacaoCentralBancoobDao(){
		return new HistParticipacaoCentralBancoobDaoImpl(HistParticipacaoCentralBancoob.class, "OBTERHISTPARTICIPACAOCENTRALBANCOOB");
	}
	
	/**
	 * 
	 * @return
	 */
	public ParticipacaoIndiretaBancoobDao criarParticipacaoIndiretaBancoobDao(){
		return new ParticipacaoIndiretaBancoobDaoImpl(ParticipacaoIndiretaBancoob.class, "OBTERPARTICIPACAOINDIRETABANCOOB");
	}
	
	/**
	 * {@link SaldoContaCapitalDao}
	 */
	public SaldoContaCapitalDao criarSaldoContaCapitalDao() {
		return new SaldoContaCapitalDaoImpl();
	}
	
}