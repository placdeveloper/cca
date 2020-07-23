/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ProdutoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ProdutoLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDaoIF;

/**
 * A Interface ProdutoLegadoDao.
 * @author Marco.Nascimento
 */
public interface ProdutoLegadoDao extends ContaCapitalIntegracaoLegadoCrudDaoIF<ProdutoLegado>{
	
	/**
	 * Obtem data do produto (SQL Server)
	 * @param idProduto
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	Date obterDataAtualProduto(Integer idProduto, Integer numCoop) throws BancoobException;
	
	/**
	 * Obtem data anterior do produto (SQL Server)
	 * @param idProduto
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	Date obterDataAnteriorProduto(Integer idProduto, Integer numCoop) throws BancoobException;

	/**
	 * Obtem as datas para os produtos (SQL Server)
	 * @param idProduto
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	ProdutoDTO obterDatasProduto(Integer idProduto, Integer numCoop) throws BancoobException;
	
	
	
}