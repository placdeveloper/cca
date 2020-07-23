package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RecolhimentoIrrfDestinacaoJurosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.RecolhimentoIrrfDestinacaoJurosLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.RecolhimentoIrrfDestinacaoJurosLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.RecolhimentoIrrfDestinacaoJurosLegadoDao;

/**
 * EJB contendo servicos relacionados a recolhimento de irrf e destinacao de juros.
 */
@Stateless
@Local(RecolhimentoIrrfDestinacaoJurosLegadoServicoLocal.class)
@Remote(RecolhimentoIrrfDestinacaoJurosLegadoServicoRemote.class)
public class RecolhimentoIrrfDestinacaoJurosLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements RecolhimentoIrrfDestinacaoJurosLegadoServicoLocal, RecolhimentoIrrfDestinacaoJurosLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private RecolhimentoIrrfDestinacaoJurosLegadoDao  recolhimentoIrrfDestinacaoJurosLegadoDao;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> gerarRelatorioRecolhimentoIrrfDestinacaoJuros(RecolhimentoIrrfDestinacaoJurosLegadoDTO dtoEntrada)
			throws BancoobException {
			return recolhimentoIrrfDestinacaoJurosLegadoDao.pesquisarRecolhimentoIrrfDestinacaoJuros(dtoEntrada);
	}
}
