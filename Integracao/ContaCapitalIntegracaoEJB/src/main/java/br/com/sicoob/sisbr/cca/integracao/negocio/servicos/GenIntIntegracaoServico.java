/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ProdutoInstituicaoIntegracaoDTO;

/**
 * A Interface GenIntIntegracaoServico.
 */
public interface GenIntIntegracaoServico extends ContaCapitalIntegracaoServico {
	
	/**
	 * Obtem produto por instituição
	 * @param idProduto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ProdutoInstituicaoIntegracaoDTO obterProdutoInstituicao(Integer idProduto,Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem data atual do produto (DB2)
	 * @param idProduto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Date obterDataAtualProdutoDB2(Integer idProduto, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Verifica no admapi se é dia util
	 * @param idInstituicao
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarDiaUtil(Integer idInstituicao,Date data) throws BancoobException;

	/**
	 * consulta no admapi o proximo dia util a partir da data
	 * @param idInstituicao
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	Date recuperarProximoDiaUtil(Integer idInstituicao,Date data) throws BancoobException;	
	
}