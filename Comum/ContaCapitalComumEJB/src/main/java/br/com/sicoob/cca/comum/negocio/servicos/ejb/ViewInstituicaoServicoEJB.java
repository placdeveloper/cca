package br.com.sicoob.cca.comum.negocio.servicos.ejb;

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
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.comum.negocio.servicos.ViewInstituicaoServico;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.ViewInstituicaoServicoLocal;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.ViewInstituicaoServicoRemote;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDaoFactory;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao;

/**
 * @author Nairon.Silva
 */
@Stateless
@Local(ViewInstituicaoServicoLocal.class)
@Remote(ViewInstituicaoServicoRemote.class)
public class ViewInstituicaoServicoEJB extends ContaCapitalComumServicoEJB implements ViewInstituicaoServicoLocal, ViewInstituicaoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager emCCAEntidades;
	
	@Dao(entityManager = "emCCAEntidades", fabrica = ContaCapitalComumDaoFactory.class)
	private ViewInstituicaoDao viewInstituicaoDao;

	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.ViewInstituicaoServico#consultarCooperativasAtivas(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<InstituicaoCooperativaSCIDTO> consultarCooperativasAtivas(Integer numCoopPai) throws BancoobException {
		return viewInstituicaoDao.consultarCooperativasAtivas(numCoopPai);
	}

	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.ViewInstituicaoServico#listarCentrais()
	 */
	public List<InstituicaoCooperativaSCIDTO> listarCentrais() throws BancoobException {
		return viewInstituicaoDao.listarCentrais();
	}
	
	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.ViewInstituicaoServico#listarCentrais()
	 */
	public List<InstituicaoCooperativaSCIDTO> listarCentraisEConfederacao() throws BancoobException {
		return viewInstituicaoDao.listarCentraisEConfederacao();
	}	

	/**
	 * {@link ViewInstituicaoServico#consultarPacPorCooperativa(Integer)}
	 */
	public List<InstituicaoCooperativaSCIDTO> consultarPacPorCooperativa(Integer idInstituicao) throws BancoobException {
		return viewInstituicaoDao.consultarPacPorCooperativa(idInstituicao);
	}
}