package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.OperacaoFinanceiraLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.OperacaoFinanceiraLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.OperacaoFinanceiraLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.OperacaoFinanceiraLegadoDao;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local(OperacaoFinanceiraLegadoServicoLocal.class)
@Remote(OperacaoFinanceiraLegadoServicoRemote.class)
public class OperacaoFinanceiraLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements OperacaoFinanceiraLegadoServicoLocal, OperacaoFinanceiraLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private OperacaoFinanceiraLegadoDao operacaoFinanceiraLegadoDao;

	/**
	 * {@link OperacaoFinanceiraLegadoServicoRemote#incluir(OperacaoFinanceiraLegadoDTO)}
	 */
	public void incluir(OperacaoFinanceiraLegadoDTO of) throws BancoobException {
		this.operacaoFinanceiraLegadoDao.incluir(of);
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoServicoRemote#getUltimoId(Integer)}
	 */
	public Integer getUltimoId(Integer numCoop) throws BancoobException {
		return this.operacaoFinanceiraLegadoDao.getUltimoId(numCoop);
	}

	/**
	 * {@link OperacaoFinanceiraLegadoServicoRemote#consultarValorEstorno(Integer)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal consultarValorEstorno(Integer numCoop) throws BancoobException {
		return this.operacaoFinanceiraLegadoDao.consultarValorEstorno(numCoop);
	}

	/**
	 * {@link OperacaoFinanceiraLegadoServicoRemote#existeOperacaoFinanceiraPI(Integer)}
	 */
	public Boolean existeOperacaoFinanceiraPI(Integer numCoop) throws BancoobException {
		return this.operacaoFinanceiraLegadoDao.existeOperacaoFinanceiraPI(numCoop);
	}

	/**
	 * {@link OperacaoFinanceiraLegadoServicoRemote#valorParticipacaoCentralPorAnoMes(Integer,Integer)}
	 */
	public BigDecimal valorParticipacaoCentralPorAnoMes(Integer numCoop, Integer anoMes) throws BancoobException {
		return this.operacaoFinanceiraLegadoDao.valorParticipacaoCentralPorAnoMes(numCoop, anoMes);
	}
}