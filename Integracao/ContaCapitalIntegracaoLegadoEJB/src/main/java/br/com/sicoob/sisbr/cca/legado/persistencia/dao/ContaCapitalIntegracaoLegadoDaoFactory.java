/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ProdutoLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ValorCotaLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.CapaLoteCapitalLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.ClienteCooperativaLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.ConciliacaoContabilLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.ContaCapitalLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.EmprestimoIntegracaoLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechCalculoProvisaoJurosDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechDestinacaoJurosLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechGerarInfoAcumuladasLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechGerarInfoCalculoVarLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechGravacaoSaldoCapSocialIntegralizadoLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechMecanismoContabilLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechOperacoesFinanceirasContabilizacaoLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.FechRelContabilLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.GestaoEmpresarialLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.HistContaCapitalLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.InformacaoAcumuladaLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.IntegralizacaoOutrosBancosLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.LancamentosCCapitalLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.OperacaoFinanceiraLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.PLDContaCapitalLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.ParcelamentoCCALegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.PesquisaEmpresaLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.ProdutoLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.RecolhimentoIrrfDestinacaoJurosLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.ReplicacaoContaCapitalLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.TabelaIRRFLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.TrabalhaLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl.ValorCotasLegadoDaoImpl;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.CapaLoteCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ClienteCooperativaLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ConciliacaoContabilLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ContaCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.EmprestimoIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechCalculoProvisaoJurosDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechDestinacaoJurosLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGerarInfoAcumuladasLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGerarInfoCalculoVarLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGravacaoSaldoCapSocialIntegralizadoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechMecanismoContabilLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechOperacoesFinanceirasContabilizacaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechRelContabilLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.GestaoEmpresarialLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.HistContaCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.InformacaoAcumuladaLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.LancamentosCCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.OperacaoFinanceiraLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.PLDContaCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.PesquisaEmpresaLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ProdutoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.RecolhimentoIrrfDestinacaoJurosLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TabelaIRRFLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ValorCotasLegadoDao;

/**
 * Fabrica de objetos da camada de acesso aos dados do sistema ContaCapitalIntegracaoLegado
 * 
 */
public final class ContaCapitalIntegracaoLegadoDaoFactory extends BancoobDaoFactory {

	/** Instancia do DAOFactory. */
	private static ContaCapitalIntegracaoLegadoDaoFactory factory;

	/**
	 * Retorna a fabrica de DAOs a ser utilizada.
	 * 
	 * @return a fabrica de DAOs a ser utilizada.
	 */
	public static ContaCapitalIntegracaoLegadoDaoFactory getInstance() {
		if (factory == null) {
			synchronized (ContaCapitalIntegracaoLegadoDaoFactory.class) {
				if (factory == null) {
					factory = new ContaCapitalIntegracaoLegadoDaoFactory();
				}
			}
		}
		return factory;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	private ContaCapitalIntegracaoLegadoDaoFactory() {
	}
	
	/**
	 * Cria uma instancia de ContaCapitalLegadoDao.
	 *
	 * @return O Dao solicitado
	 * @see ContaCapitalLegadoDao
	 */
	public ContaCapitalLegadoDao criarContaCapitalLegadoDao(){
		return new ContaCapitalLegadoDaoImpl(ContaCapitalLegado.class, "OBTERCONTACAPITAL");
	}
	
	/**
	 * Cria uma instancia de HistContaCapitalLegadoDao.
	 *
	 * @return O Dao solicitado
	 * @see HistContaCapitalLegadoDao
	 */
	public HistContaCapitalLegadoDao criarHistContaCapitalLegadoDao(){
		return new HistContaCapitalLegadoDaoImpl(HistContaCapitalLegado.class, "OBTERHISTCONTACAPITAL");
	}
	
	/**
	 * Cria uma instancia de LancamentosCCapitalLegadoDao.
	 *
	 * @return O Dao solicitado
	 * @see LancamentosCCapitalLegadoDao
	 */
	public LancamentosCCapitalLegadoDao criarLancamentosCCapitalLegadoDao(){
		return new LancamentosCCapitalLegadoDaoImpl(LancamentosCCapitalLegado.class, "OBTERLANCAMENTOSCCAPITAL");
	}
	
	/**
	 * Cria uma instancia de CapaLoteCapitalLegadoDao.
	 *
	 * @return O Dao solicitado
	 * @see CapaLoteCapitalLegadoDao
	 */
	public CapaLoteCapitalLegadoDao criarCapaLoteCapitalLegadoDao(){
		return new CapaLoteCapitalLegadoDaoImpl(CapaLoteCapitalLegado.class, "OBTERCAPALOTECAPITAL");
	}	
	
	/**
	 * Cria uma instancia de ParcelamentoCCALegadoDao.
	 *
	 * @return O Dao solicitado
	 * @see ParcelamentoCCALegadoDao
	 */
	public ParcelamentoCCALegadoDao criarParcelamentoCCALegadoDao(){
		return new ParcelamentoCCALegadoDaoImpl(ParcelamentoCCALegado.class, "OBTERPARCELAMENTOCCA");
	}

	/**
	 * Cria uma instancia de TrabalhaLegadoDao.
	 *
	 * @return O Dao solicitado
	 * @see TrabalhaLegadoDao
	 */
	public TrabalhaLegadoDao criarTrabalhaLegadoDao(){
		return new TrabalhaLegadoDaoImpl();		
	}

	/**
	 * {@link ValorCotasLegadoDao}
	 * @return
	 */
	public ValorCotasLegadoDao criarValorCotasLegadoDao(){
		return new ValorCotasLegadoDaoImpl(ValorCotaLegado.class, "OBTERVALORCOTALEGADO");
	}	
	
	/**
	 * Cria uma instancia de ConciliacaoContabilLegadoDao.
	 *
	 * @return O Dao solicitado
	 * @see ConciliacaoContabilLegadoDao
	 */
	public ConciliacaoContabilLegadoDao criarConciliacaoContabilLegadoDao(){
		return new ConciliacaoContabilLegadoDaoImpl();		
	}
	
	/**
	 * {@link InformacaoAcumuladaLegadoDao}
	 */
	public InformacaoAcumuladaLegadoDao criarInformacaoAcumuladaLegadoDao(){
		return new InformacaoAcumuladaLegadoDaoImpl();		
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoDao}
	 */
	public OperacaoFinanceiraLegadoDao criarOperacaoFinanceiraLegadoDao(){
		return new OperacaoFinanceiraLegadoDaoImpl();		
	}
	
	/**
	 * {@link ClienteCooperativaLegadoDao}
	 */
	public ClienteCooperativaLegadoDao criarClienteCooperativaLegadoDao(){
		return new ClienteCooperativaLegadoDaoImpl();		
	}
	
	/**
	 * {@link EmprestimoIntegracaoLegadoDao}
	 */
	public EmprestimoIntegracaoLegadoDao criarEmprestimoIntegracaoLegadoDao(){
		return new EmprestimoIntegracaoLegadoDaoImpl();		
	}
	
	/**
	 * {@link ReplicacaoContaCapitalLegadoDao}
	 */
	public ReplicacaoContaCapitalLegadoDao criarReplicacaoContaCapitalLegadoDao(){		
		return new ReplicacaoContaCapitalLegadoDaoImpl();		
	}
	
	/**
	 * {@link GestaoEmpresarialLegadoDao}
	 */
	public GestaoEmpresarialLegadoDao criarGestaoEmpresarialLegadoDao(){		
		return new GestaoEmpresarialLegadoDaoImpl();		
	}
	
	/**
	 * {@link ProdutoLegadoDao}
	 */
	public ProdutoLegadoDao criarProdutoLegadoDao(){		
		return new ProdutoLegadoDaoImpl(ProdutoLegado.class, "OBTERPRODUTOLEGADO");		
	}
	
	/**
	 * {@link PLDContaCapitalLegadoDao}
	 */
	public PLDContaCapitalLegadoDao criarPLDContaCapitalLegadoDao(){		
		return new PLDContaCapitalLegadoDaoImpl();		
	}
	
	/**
	 * {@link TabelaIRRFLegadoDao}
	 */
	public TabelaIRRFLegadoDao criarTabelaIRRFLegadoDao(){		
		return new TabelaIRRFLegadoDaoImpl();		
	}
	
	/**
	 * {@link PesquisaEmpresaLegadoDao}
	 */
	public PesquisaEmpresaLegadoDao criarPesquisaEmpresaDao() {		
		return new PesquisaEmpresaLegadoDaoImpl();		
	}
	
	/**
	 * {@link IntegralizacaoOutrosBancosLegadoDao}
	 */
	public IntegralizacaoOutrosBancosLegadoDao criarIntegralizacaoOutrosBancosLegadoDao() {
		return new IntegralizacaoOutrosBancosLegadoDaoImpl();
	}
	
	/**
	 * {@link RecolhimentoIrrfDestinacaoJurosLegadoDao}
	 */
	public RecolhimentoIrrfDestinacaoJurosLegadoDao criarRecolhimentoIrrfDestinacaoJurosLegadoDao() {
		return new RecolhimentoIrrfDestinacaoJurosLegadoDaoImpl();
	}
	
	/**
	 * {@link FechMecanismoContabilLegadoDao}
	 */
	public FechMecanismoContabilLegadoDao criarFechamentoMecanismoContabilLegadoDao() {
		return new FechMecanismoContabilLegadoDaoImpl();
	}
	
	/**
	 * {@link FechOperacoesFinanceirasContabilizacaoLegadoDao}
	 */
	public FechOperacoesFinanceirasContabilizacaoLegadoDao criarFechamentoOperacoesFinanceirasContabilizacaoLegadoDao() {
		return new FechOperacoesFinanceirasContabilizacaoLegadoDaoImpl();
	}
	
	/**
	 * {@link FechGerarInfoCalculoVarLegadoDao}
	 */
	public FechGerarInfoCalculoVarLegadoDao criarFechamentoGerarInfoCalculoVarLegadoDao() {
		return new FechGerarInfoCalculoVarLegadoDaoImpl();
	}
	
	/**
	 * {@link FechGravacaoSaldoCapSocialIntegralizadoLegadoDao}
	 */
	public FechGravacaoSaldoCapSocialIntegralizadoLegadoDao criarFechamentoGravacaoSaldoCapSocialIntegralizadoLegadoDao() {
		return new FechGravacaoSaldoCapSocialIntegralizadoLegadoDaoImpl();
	}
	
	/**
	 * {@link FechGerarInfoAcumuladasLegadoDao}
	 */
	public FechGerarInfoAcumuladasLegadoDao criarFechGerarInfoAcumuladasLegadoDao() {
		return new FechGerarInfoAcumuladasLegadoDaoImpl();
	}
	
	/**
	 * {@link FechCalculoProvisaoJurosDao}
	 */
	public FechCalculoProvisaoJurosDao criarFechCalculoProvisaoJurosDao() {
		return new FechCalculoProvisaoJurosDaoImpl();
	}
	
	/**
	 * {@link FechDestinacaoJurosLegadoDao}
	 */
	public FechDestinacaoJurosLegadoDao criarFechDestinacaoJurosLegadoDao() {
		return new FechDestinacaoJurosLegadoDaoImpl();
	}	
	
	/**
	 * {@link FechRelContabilLegadoDao}
	 */
	public FechRelContabilLegadoDao criarFechRelLancContabilLegadoDao() {
		return new FechRelContabilLegadoDaoImpl();
	}	
	
}