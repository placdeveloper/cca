package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.FechRelLancContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelLancContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelLancContabilLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechRelContabilLegadoDao;

/**
* @author Ricardo.Barcante
*/
@Stateless
@Local(FechRelLancContabilLegadoServicoLocal.class)
@Remote(FechRelLancContabilLegadoServicoRemote.class)
public class FechRelLancContabilLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechRelLancContabilLegadoServicoLocal, FechRelLancContabilLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechRelContabilLegadoDao fechRelLancContabilLegadoDao;

	public List<FechRelLancContabilDTO> pesquisarLancamentoContabil(FechRelLancContabilDTO filtro, Integer numCoop) throws BancoobException {
		return fechRelLancContabilLegadoDao.pesquisarLancamentoContabil(filtro, numCoop);
	}
	
}