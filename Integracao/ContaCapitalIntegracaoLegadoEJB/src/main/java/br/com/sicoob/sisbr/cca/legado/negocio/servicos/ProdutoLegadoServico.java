/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.negocio.servicos.interfaces.Fechamento;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ProdutoDTO;

/**
 * A Interface ProdutoLegadoServico.
 * @author Marco.Nascimento
 */
public interface ProdutoLegadoServico extends ContaCapitalIntegracaoLegadoServico, Fechamento {
	
	/**
	 * Obtem data do produto (SQL Server) 
	 * @param idProduto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Date obterDataAtualProduto(Integer idProduto, Integer idInstituicao) throws BancoobException;	
	
	/**
	 * Obtem data do produto (SQl Server) no formato DateTimeDB  
	 * @param idProduto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */	
	DateTimeDB obterDataAtualProdutoDateTimeDB(Integer idProduto, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem data do produto CCA (SQL Server) para o usuario logado. 
	 * @param idProduto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Date obterDataAtualProdutoCCALogado() throws BancoobException;
	
	/**
	 * Obtem data do produto (SQL Server) 
	 * @param idProduto
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Date obterDataAnteriorProduto(Integer idProduto, Integer idInstituicao) throws BancoobException;
	
	
	/**
	 * Obtem data do produto (SQL Server) 
	 * @param idProduto
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	Date obterDataAtualProdutoNumCoop(Integer idProduto, Integer numCoop) throws BancoobException;
	
	/**
	 * Obtem as datas para os produtos (SQL Server)
	 * @param idProduto
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	ProdutoDTO obterDatasProduto(Integer idProduto, Integer numCoop) throws BancoobException;	
	
	
	/**
	 * Atualiza as datas de movimentacao para as cooperativas(SQL Server)
	 * @param numCoop
	 * @return
	 * @throws BancoobException
	 */
	void rodar(Integer numCoop) throws BancoobException;	
	
	
}