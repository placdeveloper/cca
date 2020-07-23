package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.Date;
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
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.GestaoEmpresarialLegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.GestaoEmpresarialLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.GestaoEmpresarialLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.GestaoEmpresarialLegadoDao;

/**
 * EJB contendo servicos relacionados a gestao empresial (IRRF).
 */
@Stateless
@Local (GestaoEmpresarialLegadoServicoLocal.class)
@Remote(GestaoEmpresarialLegadoServicoRemote.class)
public class GestaoEmpresarialLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements GestaoEmpresarialLegadoServicoLocal, GestaoEmpresarialLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;

	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private GestaoEmpresarialLegadoDao gestaoEmpresarialLegadoDao;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.GestaoEmpresarialLegadoServico#gerarExtratoDIRF(java.lang.Integer, java.util.Date)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<GestaoEmpresarialLegadoDTO> gerarExtratoDIRF(Integer numCoop, Date dataInicio) throws BancoobException {
		
		try {
			
			return gestaoEmpresarialLegadoDao.gerarExtratoDIRF(numCoop, dataInicio);
			
		} catch (GestaoEmpresarialLegadoException e) {
			getLogger().erro(e, e.getMessage());
			throw e;
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.GestaoEmpresarialLegadoServico#novosLancamentosDIRF(java.lang.Integer, java.util.Date)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean novosLancamentosDIRF(Integer numCoop, Date data) throws BancoobException {
		
		try {
			
			return gestaoEmpresarialLegadoDao.novosLancamentosDIRF(numCoop, data);
			
		} catch (GestaoEmpresarialLegadoException e) {
			getLogger().erro(e, e.getMessage());
			throw e;
		}
	}
}