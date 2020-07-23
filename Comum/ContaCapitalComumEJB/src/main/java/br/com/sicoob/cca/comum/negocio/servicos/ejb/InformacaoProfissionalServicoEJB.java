package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.cca.comum.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDaoFactory;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.InformacaoProfissionalDao;

/**
 * @author Nairon.Silva
 */
@Stateless
@Local(InformacaoProfissionalServico.class)
public class InformacaoProfissionalServicoEJB extends ContaCapitalComumServicoEJB implements InformacaoProfissionalServico {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager emCCAEntidades;
	
	@Dao(entityManager = "emCCAEntidades", fabrica = ContaCapitalComumDaoFactory.class)
	private InformacaoProfissionalDao dao;

	public List<InformacaoProfissionalDTO> consultarInformacaoProfissional(Integer idContaCapital) throws BancoobException {
		return dao.consultarInformacaoProfissional(idContaCapital);
	}

}