/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ProdutoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ProdutoLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ProdutoLegadoDao;

/**
 * A Classe ProdutoLegadoDaoImpl.
 */
public class ProdutoLegadoDaoImpl extends ContaCapitalIntegracaoLegadoCrudDao<ProdutoLegado> implements ProdutoLegadoDao {

	public ProdutoLegadoDaoImpl(Class<ProdutoLegado> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}

	/**
	 * {@link ProdutoLegadoDao#obterDataAtualProduto(Integer, Integer)}
	 */
	public Date obterDataAtualProduto(Integer idProduto, Integer numCoop) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Date dataProduto = null;
		
		try {
			
			comando = getComando("OBTER_DATA_PRODUTO_SQL");
			comando.adicionarVariavel("idProduto", idProduto);
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()) {
				dataProduto = rs.getDate("DataAtualProd");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return dataProduto;		
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ProdutoLegadoDao#obterDataAnteriorProduto(java.lang.Integer, java.lang.Integer)
	 */
	public Date obterDataAnteriorProduto(Integer idProduto, Integer numCoop) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Date dataAntProduto = null;
		
		try {
			
			comando = getComando("OBTER_DATA_ANT_PRODUTO_SQL");
			comando.adicionarVariavel("idProduto", idProduto);
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()) {
				dataAntProduto = rs.getDate("DataAntProd");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return dataAntProduto;		
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ProdutoLegadoDao#obterDataAnteriorProduto(java.lang.Integer, java.lang.Integer)
	 */
	public ProdutoDTO obterDatasProduto(Integer idProduto, Integer numCoop) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		ProdutoDTO produtoDTO = null;
		
		try {
			
			comando = getComando("OBTER_DATAS_PRODUTO_SQL");
			comando.adicionarVariavel("idProduto", idProduto);
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()) {
				produtoDTO = new ProdutoDTO();
				produtoDTO.setIDProduto(rs.getInt("IDProduto"));
				produtoDTO.setDataAntProd(rs.getDate("DataAntProd"));
				produtoDTO.setDataAtualProd(rs.getDate("DataAtualProd"));
				produtoDTO.setDataProxProd(rs.getDate("DataProxProd"));
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return produtoDTO;		
	}	
	
}