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
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ProdutoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ProdutoLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ProdutoLegadoDao;

/**
 * EJB contendo servicos relacionados a informacoes de produto.
 */
@Stateless
@Local(ProdutoLegadoServicoLocal.class)
@Remote(ProdutoLegadoServicoRemote.class)
public class ProdutoLegadoServicoEJB extends ContaCapitalIntegracaoLegadoCrudServicoEJB<ProdutoLegado> implements ProdutoLegadoServicoLocal, ProdutoLegadoServicoRemote {
	
	@SuppressWarnings("unused")
	@PersistenceContext(unitName = "emcca_integracao")
	private EntityManager em;
	
	@Dao(entityManager = "em", fabrica = ContaCapitalIntegracaoLegadoDaoFactory.class)
	private ProdutoLegadoDao produtoLegadoDao; 
	
	@Override
	protected ContaCapitalIntegracaoLegadoCrudDaoIF<ProdutoLegado> getDAO() {
		return produtoLegadoDao;
	}
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instIntegServico; 

	@EJB
	private GenIntIntegracaoServicoLocal genInt;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico#obterDataAtualProduto(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date obterDataAtualProduto(Integer idProduto, Integer idInstituicao) throws BancoobException {
		Integer numCoop = instIntegServico.obterNumeroCooperativa(idInstituicao);
		return produtoLegadoDao.obterDataAtualProduto(idProduto, numCoop);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico#obterDataAtualProdutoDateTimeDB(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DateTimeDB obterDataAtualProdutoDateTimeDB(Integer idProduto, Integer idInstituicao) throws BancoobException {		
		return new DateTimeDB(obterDataAtualProduto(idProduto, idInstituicao).getTime()); 
	}	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico#obterDataAtualProdutoNumCoop(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date obterDataAtualProdutoNumCoop(Integer idProduto, Integer numCoop) throws BancoobException {
		return produtoLegadoDao.obterDataAtualProduto(idProduto, numCoop);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico#obterDataAtualProdutoCCALogado()
	 */
	public Date obterDataAtualProdutoCCALogado() throws BancoobException {
		return obterDataAtualProduto(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, 
				Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico#obterDataAnteriorProduto(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date obterDataAnteriorProduto(Integer idProduto, Integer numCoop) throws BancoobException {
		return produtoLegadoDao.obterDataAnteriorProduto(idProduto, numCoop);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico#obterDatasProduto	(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProdutoDTO obterDatasProduto(Integer idProduto, Integer numCoop) throws BancoobException {
		return produtoLegadoDao.obterDatasProduto(idProduto, numCoop);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ProdutoLegadoServico#rodar	(java.lang.Integer)
	 */
	public void rodar(Integer numCoop) throws BancoobException {
		
		// Necessário para conectar ao datasorce correto da cooperativa
		ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(numCoop);
		
		ProdutoLegado produtoLegado = produtoLegadoDao.obter(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		
		int idInstituicao = instIntegServico.obterIdInstituicao(numCoop);
		
		DateTimeDB dataProxProd = new DateTimeDB(genInt.recuperarProximoDiaUtil(idInstituicao, produtoLegado.getDataProxProd()).getTime());
		
		produtoLegado.setDataAntProd(produtoLegado.getDataAtualProd());
		produtoLegado.setDataUltMovimento(produtoLegado.getDataAtualProd());
		
		produtoLegado.setDataAtualProd(produtoLegado.getDataProxProd());
		produtoLegado.setDataAtualMovimento(produtoLegado.getDataProxProd());
		
		produtoLegado.setDataProxProd(dataProxProd);
		produtoLegado.setDataProxMovimento(dataProxProd);
		
		produtoLegadoDao.alterar(produtoLegado);		
	}
	
	
}