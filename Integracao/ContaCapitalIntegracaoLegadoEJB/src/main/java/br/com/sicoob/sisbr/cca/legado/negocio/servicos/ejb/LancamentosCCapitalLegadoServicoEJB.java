package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.LancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.LancamentosCCapitalLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.LancamentosCCapitalLegadoDao;

/**
 * EJB contendo servicos relacionados a LancamentosCCapitalLegado.
 */
@Stateless
@Local (LancamentosCCapitalLegadoServicoLocal.class)
@Remote(LancamentosCCapitalLegadoServicoRemote.class)
public class LancamentosCCapitalLegadoServicoEJB extends ContaCapitalIntegracaoLegadoCrudServicoEJB<LancamentosCCapitalLegado> implements
		LancamentosCCapitalLegadoServicoLocal, LancamentosCCapitalLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private LancamentosCCapitalLegadoDao lancamentosCCapitalLegadoDao;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb.ContaCapitalIntegracaoLegadoCrudServicoEJB#getDAO()
	 */
	@Override
	protected ContaCapitalIntegracaoLegadoCrudDaoIF<LancamentosCCapitalLegado> getDAO() {
		return lancamentosCCapitalLegadoDao;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico#obterUltimoNumSeqLanc(br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado)
	 */
	public Integer obterUltimoNumSeqLanc(CapaLoteCapitalLegado capaLoteCapitalLegado)throws BancoobException {
		Integer numSeqLanc = lancamentosCCapitalLegadoDao.obterUltimoNumSeqLanc(capaLoteCapitalLegado);
		if (numSeqLanc == null){
			numSeqLanc = 0;
		}

		return numSeqLanc;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico#incluirEmLote(java.util.List)
	 */
	public List<LancamentosCCapitalLegado> incluirEmLote(List<LancamentosCCapitalLegado> lancamentosLegado) throws BancoobException {
		return lancamentosCCapitalLegadoDao.incluirEmLote(lancamentosLegado);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico#verificarLancamentoExistente(java.lang.Integer, java.lang.String)
	 */
	public Boolean verificarLancamentoExistente(Integer numCooperativa, Integer numMatricula, String descOperacaoExterna) throws BancoobException {
		return lancamentosCCapitalLegadoDao.verificarLancamentoExistente(numCooperativa, numMatricula, descOperacaoExterna);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico#listarLancViaCCO(DateTimeDB)
	 */
	public List<LancamentosCCapitalLegadoDTO> listarLancViaCCO(DateTimeDB dataAtualProd) throws BancoobException {
		return lancamentosCCapitalLegadoDao.listarLancViaCCO(dataAtualProd);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico#listarLancViaDebIndet(DateTimeDB)
	 */
	public List<LancamentosCCapitalLegadoDTO> listarLancViaDebIndet(DateTimeDB dataAtualProd) throws BancoobException {
		return lancamentosCCapitalLegadoDao.listarLancViaDebIndet(dataAtualProd);
	}	

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico#listarLancamentosDestinacaoJuros(DateTimeDB)
	 */
	public List<LancamentosCCapitalLegadoDTO> listarLancamentosDestinacaoJuros(DateTimeDB dataAtualProd) throws BancoobException {
		return lancamentosCCapitalLegadoDao.listarLancamentosDestinacaoJuros(dataAtualProd);
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.LancamentosCCapitalLegadoServico#rodarSQL(java.lang.Integer)
	 */
	public void rodarSQL(Integer numCoop) throws BancoobException {
		lancamentosCCapitalLegadoDao.atualizaMovimentoLancamentos(numCoop);
	}
	
}
