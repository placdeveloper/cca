package br.com.sicoob.cca.comum.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.comum.negocio.servicos.OperacaoContaCapitalServico;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumCrudDaoIF;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDaoFactory;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.OperacaoContaCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.OperacaoContaCapital;

/**
 * OperacaoContaCapitalServicoEJB
 * @author Nairon.Silva
 */
@Stateless
@Local(OperacaoContaCapitalServico.class)
public class OperacaoContaCapitalServicoEJB extends ContaCapitalComumCrudServicoEJB<OperacaoContaCapital> implements OperacaoContaCapitalServico {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager emCCAEntidades;
	
	@Dao(entityManager = "emCCAEntidades", fabrica = ContaCapitalComumDaoFactory.class)
	private OperacaoContaCapitalDao dao;

	@Override
	protected ContaCapitalComumCrudDaoIF<OperacaoContaCapital> getDAO() {
		return dao;
	}
	
	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.OperacaoContaCapitalServico#expurgarOperacao(java.util.Date)
	 */
	public void expurgarOperacao(Date dataExpurgoOperacao) throws BancoobException{
		dao.expurgarOperacao(dataExpurgoOperacao);		
	}
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public OperacaoContaCapital incluir(OperacaoContaCapital objeto) throws BancoobException {
		return super.incluir(objeto);
	}
	
	/**
	 * @see br.com.sicoob.cca.comum.negocio.servicos.OperacaoContaCapitalServico#incluirOperacaoContaCapitalLote(java.util.List)
	 */
	public void incluirOperacaoContaCapitalLote(List<OperacaoContaCapital> listOperacaoContaCapital) throws BancoobException{
		dao.incluirOperacaoContaCapitalLote(listOperacaoContaCapital);		
	}

}
