package br.com.sicoob.cca.movimentacao.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCorrenteView;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ContaCorrenteViewServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ContaCorrenteViewServicoRemote;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDaoFactory;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ContaCorrenteViewDao;

/**
 * Servico ContaCorrenteView
 * @author Nairon.Silva
 */
@Stateless
@Local (ContaCorrenteViewServicoLocal.class)
@Remote(ContaCorrenteViewServicoRemote.class) 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ContaCorrenteViewServicoEJB extends ContaCapitalMovimentacaoCrudServicoEJB<ContaCorrenteView> implements ContaCorrenteViewServicoLocal, ContaCorrenteViewServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emCCAEntidades")
	private EntityManager em;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalMovimentacaoDaoFactory.class)
	private ContaCorrenteViewDao contaCorrenteViewDao;

	@Override
	protected ContaCapitalMovimentacaoCrudDaoIF<ContaCorrenteView> getDAO() {
		return contaCorrenteViewDao;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ContaCorrenteViewServico#verificarContaCorrenteBloqueadaEncerrada(java.lang.Integer, java.lang.Integer)
	 */
	public boolean verificarContaCorrenteBloqueadaEncerrada(Integer idInstituicao, Integer numContaCorrente) throws BancoobException {
		List<ContaCorrenteView> ccos = listar(montarCriterios(idInstituicao, numContaCorrente));
		if (ccos == null || ccos.isEmpty()){
			throw new ContaCapitalMovimentacaoNegocioException("MSG_CCO_NAO_ENCONTRADA");
		}
		for (ContaCorrenteView cco : ccos) {
			if (cco.isBloqueadaOuEncerrada()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.negocio.servicos.ContaCorrenteViewServico#verificarContaCorrentePessoa(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public boolean verificarContaCorrentePessoa(Integer idInstituicao,Integer idPessoa, Integer numContaCorrente) throws BancoobException {
		List<ContaCorrenteView> ccos = listar(montarCriterios(idInstituicao,idPessoa, numContaCorrente));
		if (ccos == null || ccos.isEmpty()){
			return false;
		}
		return true;
	}	
	
	private ConsultaDto<ContaCorrenteView> montarCriterios(Integer idInstituicao, Integer numContaCorrente) {
		ConsultaDto<ContaCorrenteView> criterios = new ConsultaDto<ContaCorrenteView>();
		ContaCorrenteView filtro = new ContaCorrenteView();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setNumContaCorrente(numContaCorrente);
		criterios.setFiltro(filtro);
		return criterios;
	}	
	
	private ConsultaDto<ContaCorrenteView> montarCriterios(Integer idInstituicao,Integer idPessoa, Integer numContaCorrente) {
		ConsultaDto<ContaCorrenteView> criterios = new ConsultaDto<ContaCorrenteView>();
		ContaCorrenteView filtro = new ContaCorrenteView();
		filtro.setIdInstituicao(idInstituicao);
		filtro.setNumContaCorrente(numContaCorrente);
		filtro.setIdPessoa(idPessoa);
		criterios.setFiltro(filtro);
		return criterios;
	}		
	
	
	
}
