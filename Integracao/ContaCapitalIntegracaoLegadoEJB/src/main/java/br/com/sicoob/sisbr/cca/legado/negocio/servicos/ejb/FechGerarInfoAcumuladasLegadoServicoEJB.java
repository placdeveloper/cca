package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechGerarInfoAcumuladasLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechGerarInfoAcumuladasLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGerarInfoAcumuladasLegadoDao;

@Stateless
@Local(FechGerarInfoAcumuladasLegadoServicoLocal.class)
@Remote(FechGerarInfoAcumuladasLegadoServicoRemote.class)
public class FechGerarInfoAcumuladasLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechGerarInfoAcumuladasLegadoServicoLocal, FechGerarInfoAcumuladasLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechGerarInfoAcumuladasLegadoDao fechGerarInfoAcumuladasLegadoDao;

	public void rodar(Integer numCoop) throws BancoobException {
		fechGerarInfoAcumuladasLegadoDao.gerarInfoAcumulada(numCoop);
	}
	
}