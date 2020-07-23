package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.Date;

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
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.PLDContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.PLDContaCapitalLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.PLDContaCapitalLegadoDao;

/**
 * EJB contendo servicos relacionados a prevencao de lavagem de dinheiro (PLD)
 * @author Marco.Nascimento
 */
@Stateless
@Local(PLDContaCapitalLegadoServicoLocal.class)
@Remote(PLDContaCapitalLegadoServicoRemote.class)
public class PLDContaCapitalLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements PLDContaCapitalLegadoServicoLocal, PLDContaCapitalLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;

	@Dao (entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private PLDContaCapitalLegadoDao pldContaCapitalLegadoDao;
	
	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServico; 
	
	@EJB
	private GenIntIntegracaoServicoLocal genIntServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instIntServico;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.PLDContaCapitalLegadoServico#gerarPLD(java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean gerarPLD(Integer numCoop) throws BancoobException {
		
		Integer idInst = instIntServico.obterIdInstituicao(numCoop);
		
		if(genIntServico.verificarDiaUtil(idInst, new Date())) {
			
			Date dtAntProd = produtoLegadoServico.obterDataAnteriorProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, numCoop);
			
			return pldContaCapitalLegadoDao.gerarPLD(numCoop, dtAntProd);	
		}
		
		return Boolean.FALSE;
	}
}