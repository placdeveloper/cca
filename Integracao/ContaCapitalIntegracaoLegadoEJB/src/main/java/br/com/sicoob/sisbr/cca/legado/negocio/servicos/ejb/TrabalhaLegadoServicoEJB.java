package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.TrabalhaLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.TrabalhaLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao;
import br.com.sicoob.tipos.DateTime;

/**
 * EJB contendo servicos relacionados a TrabalhaLegado.
 */
@Stateless
@Local (TrabalhaLegadoServicoLocal.class)
@Remote(TrabalhaLegadoServicoRemote.class)
public class TrabalhaLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements TrabalhaLegadoServicoLocal, TrabalhaLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private TrabalhaLegadoDao trabalhaLegadoDao;

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico#obterDadosTrabalha(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TrabalhaLegadoDTO> obterDadosTrabalha(Integer numPessoa) throws BancoobException {
 		return trabalhaLegadoDao.obterDadosTrabalha(numPessoa);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico#obterDadosTrabalhaPorMatricula(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TrabalhaLegadoDTO obterDadosTrabalhaPorMatricula(String descMatriculaFunc) throws BancoobException {
 		return trabalhaLegadoDao.obterDadosTrabalhaPorMatricula(descMatriculaFunc);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico#verificaSeDebIndFolhaCliente(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean verificaSeDebIndFolhaCliente(Integer numMatricula, Integer numCliente) throws BancoobException {
 		return trabalhaLegadoDao.verificaSeDebIndFolhaCliente(numMatricula, numCliente);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico#verificarParcelaViaFolhaCliente(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean verificarParcelaViaFolhaCliente(Integer numMatricula, Integer numCliente) throws BancoobException {
 		return trabalhaLegadoDao.verificarParcelaViaFolhaCliente(numMatricula, numCliente);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico#verificarSePrepRemessa(java.lang.String, br.com.sicoob.tipos.DateTime)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean verificarSePrepRemessa(String strUIDTrabalha,DateTime dataReferencia) throws BancoobException {		
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(dataReferencia.getTime());
 		return trabalhaLegadoDao.verificarSePrepRemessa(strUIDTrabalha, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.TrabalhaLegadoServico#verificarSeMatriculaTrabalhaValida(java.lang.String, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean verificarSeMatriculaTrabalhaValida(String strUIDTrabalha, Integer numCliente) throws BancoobException {
 		return trabalhaLegadoDao.verificarSeMatriculaTrabalhaValida(strUIDTrabalha,numCliente);
	}	
	
}
