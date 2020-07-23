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
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.cca.comum.negocio.servicos.PesquisaContaCapitalServico;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.PesquisaContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.PesquisaContaCapitalServicoRemote;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDaoFactory;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.PesquisaContaCapitalDao;

/**
 * Responsavel por fornecer informacoes sobre o fechamento do produto conta capital
 * 
 * @author Marco.Nascimento
 */
@Stateless
@Local(PesquisaContaCapitalServicoLocal.class)
@Remote(PesquisaContaCapitalServicoRemote.class)
public class PesquisaContaCapitalServicoEJB extends ContaCapitalComumServicoEJB implements PesquisaContaCapitalServico {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager emCCAEntidades;
	
	@Dao(entityManager = "emCCAEntidades", fabrica = ContaCapitalComumDaoFactory.class)
	private PesquisaContaCapitalDao pesquisaContaCapitalDao;
	
	/**
	 * {@link PesquisaContaCapitalServico#pesquisar(PesquisaContaCapitalDTO)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PesquisaContaCapitalDTO> pesquisar(PesquisaContaCapitalDTO dto) throws BancoobException {
		return pesquisaContaCapitalDao.pesquisar(dto);
	}
}