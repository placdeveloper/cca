package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelConciliacaoContabilDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelConciliacaoContabilLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechRelConciliacaoContabilLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechRelContabilLegadoDao;

/**
 * @author Kleber Alves
 */
@Stateless
@Local(FechRelConciliacaoContabilLegadoServicoLocal.class)
@Remote(FechRelConciliacaoContabilLegadoServicoRemote.class)
public class FechRelConciliacaoContabilLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB
		implements FechRelConciliacaoContabilLegadoServicoLocal, FechRelConciliacaoContabilLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;

	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechRelContabilLegadoDao relatorioContabilLegadoDao;

	public List<RelConciliacaoContabilDTO> obtemDadosConcialicaoContabil(Date dataInicial, Date dataFinal, Integer numeroCooperativa)
			throws BancoobException {

		return relatorioContabilLegadoDao.obtemDadosConcialicaoContabil(dataInicial, dataFinal, numeroCooperativa);
	}

}