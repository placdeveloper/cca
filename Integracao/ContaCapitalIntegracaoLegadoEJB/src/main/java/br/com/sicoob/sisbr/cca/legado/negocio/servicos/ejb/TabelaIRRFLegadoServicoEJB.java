package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TabelaIRRFLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.TabelaIRRFLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.TabelaIRRFLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TabelaIRRFLegadoDao;

/**
 * EJB contendo servicos relacionados a tabela progressiva de IRRF
 */
@Stateless
@Local (TabelaIRRFLegadoServicoLocal.class)
@Remote(TabelaIRRFLegadoServicoRemote.class)
public class TabelaIRRFLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements TabelaIRRFLegadoServicoLocal, TabelaIRRFLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private TabelaIRRFLegadoDao tabelaIRRFLegadoDao;

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TabelaIRRFLegadoServico#consultarPorAnoBase(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TabelaIRRFLegadoDTO> consultarPorAnoBase(Integer anoBase) throws BancoobException {
		return tabelaIRRFLegadoDao.consultarPorAnoBase(anoBase);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TabelaIRRFLegadoServico#incluir(java.util.List)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void incluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException {
		tabelaIRRFLegadoDao.incluir(lstTabelaIRRF);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TabelaIRRFLegadoServico#excluir(java.util.List)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void excluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException {
		tabelaIRRFLegadoDao.excluir(lstTabelaIRRF);
	}
}