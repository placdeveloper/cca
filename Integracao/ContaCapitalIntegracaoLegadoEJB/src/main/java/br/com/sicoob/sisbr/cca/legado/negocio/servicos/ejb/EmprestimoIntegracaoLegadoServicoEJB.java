package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb.ContaCapitalIntegracaoServicoEJB;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.EmprestimoIntegracaoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.EmprestimoIntegracaoLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.EmprestimoIntegracaoLegadoDao;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local (EmprestimoIntegracaoLegadoServicoLocal.class)
@Remote(EmprestimoIntegracaoLegadoServicoRemote.class)
public class EmprestimoIntegracaoLegadoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements EmprestimoIntegracaoLegadoServicoLocal, EmprestimoIntegracaoLegadoServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager emCCAIntegracao;
	
	@Dao(entityManager = "emCCAIntegracao", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private EmprestimoIntegracaoLegadoDao emprestimoIntegracaoDao;

	/**
	 * {@link EmprestimoIntegracaoLegadoServico#consultarEmprestimos(Integer)}
	 */
	public List<EmprestimoIntegracaoDTO> consultarEmprestimos(Integer numCliente, Long numContaCorrente) throws BancoobException {
		return emprestimoIntegracaoDao.consultarEmprestimos(numCliente, numContaCorrente);
	}
}