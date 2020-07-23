package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGravacaoSaldoCapSocialIntegralizadoLegadoDao;

@Stateless
@Local(FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoLocal.class)
@Remote(FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoRemote.class)
public class FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoLocal, FechGravacaoSaldoCapSocialIntegralizadoLegadoServicoRemote {

	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private FechGravacaoSaldoCapSocialIntegralizadoLegadoDao fechamentoGravacaoSaldoCapSocialIntegralizadoLegadoDao;

	@EJB
	private ProdutoLegadoServicoLocal produtoLegadoServicoLocal;
	
	public void rodar(Integer numCoop) throws BancoobException {
		fechamentoGravacaoSaldoCapSocialIntegralizadoLegadoDao.realizarCarga(numCoop, produtoLegadoServicoLocal.obterDataAtualProdutoNumCoop(2, numCoop));
	}
	
}