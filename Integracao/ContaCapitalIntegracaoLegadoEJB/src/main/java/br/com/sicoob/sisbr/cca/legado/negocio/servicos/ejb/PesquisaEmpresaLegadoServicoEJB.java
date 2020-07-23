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
import br.com.sicoob.sisbr.cca.legado.negocio.dto.PesquisaEmpresaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.PesquisaEmpresaLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.PesquisaEmpresaLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.PesquisaEmpresaLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.PesquisaEmpresaLegadoDao;

/**
 * @author Marco.Nascimento
 */
@Stateless
@Local(PesquisaEmpresaLegadoServicoLocal.class)
@Remote(PesquisaEmpresaLegadoServicoRemote.class)
public class PesquisaEmpresaLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements PesquisaEmpresaLegadoServico {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;

	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private PesquisaEmpresaLegadoDao pesquisaEmpresaLegadoDao;
	
	/**
	 * {@link PesquisaEmpresaLegadoServico#pesquisar(PesquisaEmpresaDTO)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PesquisaEmpresaDTO> pesquisar(PesquisaEmpresaDTO dto) throws BancoobException {
		return pesquisaEmpresaLegadoDao.pesquisar(dto);
	}
}