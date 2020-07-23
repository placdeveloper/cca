/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiFabricaDelegates;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiProdutoInstituicao;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ProdutoInstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoGenIntException;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoGenIntNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GenIntIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GenIntIntegracaoServicoRemote;

/**
 * EJB contendo servicos relacionados a GenIntIntegracao.
 */
@Stateless
@Local(GenIntIntegracaoServicoLocal.class)
@Remote(GenIntIntegracaoServicoRemote.class)
public class GenIntIntegracaoServicoEJB extends
		ContaCapitalIntegracaoServicoEJB implements
		GenIntIntegracaoServicoLocal, GenIntIntegracaoServicoRemote {
	
	/**
	 * @see GenIntIntegracaoServico#obterProdutoInstituicao(Integer idProduto,Integer idInstituicao)
	 */
	public ProdutoInstituicaoIntegracaoDTO obterProdutoInstituicao(Integer idProduto, Integer idInstituicao) throws BancoobException {
		
		this.getLogger().info("CCA.obterProdutoInstituicao");

		IAdmApiProdutoInstituicao admApi = null;
		try {
			List<IAdmApiProdutoInstituicao> lst = AdmApiFabricaDelegates.getInstance().createAdmApiProdutoInstituicaoDelegate().pesquisarProdutoInstituicao(
					idInstituicao, idProduto.shortValue());
			
			if(lst != null && !lst.isEmpty()) {
				admApi = lst.get(lst.size()-1);
			}
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoGenIntException(e);
		}

		if (admApi == null) {
			throw new IntegracaoGenIntNegocioException("MSG_011");
		}

		return montarProdutoInstituicaoIntegracaoDTO(admApi);
	}

	/**
	 * Montar produto instituicao integracao dto.
	 *
	 * @param produtoInstituicao o valor de produto instituicao
	 * @return ProdutoInstituicaoIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ProdutoInstituicaoIntegracaoDTO montarProdutoInstituicaoIntegracaoDTO(IAdmApiProdutoInstituicao produtoInstituicao) throws BancoobException {
		ProdutoInstituicaoIntegracaoDTO produtoInstituicaoIntegracaoDTO = new ProdutoInstituicaoIntegracaoDTO();
		produtoInstituicaoIntegracaoDTO.setDataAntigoProduto(new DateTimeDB(produtoInstituicao.getDataAntigoProduto().getTime()));
		produtoInstituicaoIntegracaoDTO.setDataAtualMovimento(new DateTimeDB(produtoInstituicao.getDataAtualMovimento().getTime()));
		produtoInstituicaoIntegracaoDTO.setDataAtualProduto(new DateTimeDB(produtoInstituicao.getDataAtualProduto().getTime()));
		produtoInstituicaoIntegracaoDTO.setDataProximoMovimento(new DateTimeDB(produtoInstituicao.getDataProximoMovimento().getTime()));
		produtoInstituicaoIntegracaoDTO.setDataProximoProduto(new DateTimeDB(produtoInstituicao.getDataProximoProduto().getTime()));
		produtoInstituicaoIntegracaoDTO.setDataUltimoMovimento(new DateTimeDB(produtoInstituicao.getDataUltimoMovimento().getTime()));
		return produtoInstituicaoIntegracaoDTO;
	}

	/**
	 * @see GenIntIntegracaoServico#obterDataAtualProdutoDB2(Integer idProduto, Integer idInstituicao)
	 */
	public Date obterDataAtualProdutoDB2(Integer idProduto, Integer idInstituicao) throws BancoobException {
		return this.obterProdutoInstituicao(idProduto, idInstituicao).getDataAtualProduto();
	}
	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GenIntIntegracaoServico#verificarDiaUtil(java.lang.Integer, java.util.Date)
	 */
	public Boolean verificarDiaUtil(Integer idInstituicao,Date data) throws BancoobException {

		try{
			return AdmApiFabricaDelegates.getInstance().createAdmApiFeriadoDelegate().verificarDiaUtil(idInstituicao, data);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoGenIntException(e);
		}
		
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.GenIntIntegracaoServico#recuperarProximoDiaUtil(java.lang.Integer, java.util.Date)
	 */
	public Date recuperarProximoDiaUtil(Integer idInstituicao,Date data) throws BancoobException {

		try{
			return AdmApiFabricaDelegates.getInstance().createAdmApiFeriadoDelegate().recuperarProximoDiaUtil(idInstituicao, data);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoGenIntException(e);
		}		
	}		
}
