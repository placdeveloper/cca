/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ValorCotaLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ValorCotasLegadoDao;

/**
 * A Classe ValorCotasLegadoDaoImpl.
 */
public class ValorCotasLegadoDaoImpl extends ContaCapitalIntegracaoLegadoCrudDao<ValorCotaLegado> implements ValorCotasLegadoDao {
	
	/**
	 * Instancia um novo ValorCotasLegadoDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ValorCotasLegadoDaoImpl(Class<ValorCotaLegado> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ValorCotasLegadoDao#obterNumeroMinimoCota()
	 */
	public Integer obterNumeroMinimoCota() throws BancoobException {
		
		Integer numeroMininoCota = 0;
		Map<String,Object> mapaCotas = obterDadosValorCotas();		
		
		if (!mapaCotas.isEmpty()){
			numeroMininoCota =  Integer.valueOf(mapaCotas.get("numMinCotasInteg").toString());
		}

		return numeroMininoCota;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ValorCotasLegadoDao#obterValorCota()
	 */
	public BigDecimal obterValorCota() throws BancoobException {
		BigDecimal valorCota = BigDecimal.ZERO;
		Map<String,Object> mapaCotas = obterDadosValorCotas();		
		
		if (!mapaCotas.isEmpty()){
			valorCota =  new BigDecimal(mapaCotas.get("valorCota").toString());
		}

		return valorCota;
	}	
	
	/**
	 * Obter dados valor cotas.
	 *
	 * @return Map
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Map<String,Object> obterDadosValorCotas() throws BancoobException {
		
		
		Map<String,Object> mapaCotas = new HashMap<String, Object>();
		
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		
		try {			
			comando = getComando("OBTERDADOSVALORCOTAS");	
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if(rs.next()){				
				mapaCotas.put("valorCota", rs.getBigDecimal("ValorCota"));
				mapaCotas.put("numMinCotasInteg", rs.getInt("NumMinCotasInteg"));
			}
						
		} catch (Exception e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_006",e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return mapaCotas;		
		
	}
	
	/**
	 * {@link ValorCotasLegadoDao#obterValorCotaVigente(Integer)}
	 */
	public ValorCotaLegado obterValorCotaVigente(Integer numCoop) throws BancoobException {
			Comando comando = null;
			Connection conexao = null;
			ResultSet rs = null;
			
			ValorCotaLegado vcl = null;
			try {			
				comando = getComando("OBTERVALORCOTAVIGENTE");	
				comando.configurar();

				conexao = estabelecerConexao(numCoop);
				rs = comando.executarConsulta(conexao);
				
				vcl = new ValorCotaLegado();
				if(rs.next()) {
					vcl.setId(rs.getDate("DataInicialCota"));
					vcl.setDataCadastroCota(rs.getDate("DataCadastroCota"));
					vcl.setValorCota(rs.getBigDecimal("ValorCota"));
					vcl.setNumMinCotasInteg(rs.getInt("NumMinCotasInteg"));
					vcl.setPercMinIntegralizacao(rs.getBigDecimal("PercMinIntegralizacao"));
					vcl.setValorSalarioBase(rs.getBigDecimal("ValorSalarioBase"));
					vcl.setBolLimIntegralCapConsignado(rs.getBoolean("BolLimIntegralCapConsignado"));
					vcl.setValorLimiteIntegralMinimo(rs.getBigDecimal("ValorLimiteIntegralMinimo"));
					vcl.setValorLimiteIntegralMaximo(rs.getBigDecimal("ValorLimiteIntegralMaximo"));
				}
				return vcl;
							
			} catch (SQLException e) {
				this.getLogger().erro(e, e.getMessage());
				throw new BancoobException(e);
			} finally {
				fecharComando(comando);
				fecharConexao(conexao);
			}
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public ValorCotaLegado incluir(ValorCotaLegado objeto) throws BancoobException {
		
		if(objeto.getNumCoop() != null && objeto.getNumCoop().intValue() > 0) {
			
			Comando comando = null;
			Connection conexao = null;
			try {
				
				conexao = estabelecerConexao(objeto.getNumCoop());
				comando = getComando("INCLUIRVALORCOTA");
				comando.adicionarVariavel("vc", objeto);
				comando.configurar();
				comando.executarAtualizacao(conexao);
				
			} catch (PersistenciaException e) {
				this.getLogger().erro(e, e.getMessage());
				throw new BancoobException(e);
			} finally {
				fecharComando(comando);
				fecharConexao(conexao);
			}
			
			return null;
		}
		
		return super.incluir(objeto);
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#alterar(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public void alterar(ValorCotaLegado objeto) throws BancoobException {
		if(objeto.getNumCoop() != null && objeto.getNumCoop().intValue() > 0) {
			
			Comando comando = null;
			Connection conexao = null;
			try {
				
				conexao = estabelecerConexao(objeto.getNumCoop());
				comando = getComando("ALTERARVALORCOTA");
				comando.adicionarVariavel("vc", objeto);
				comando.adicionarVariavel("dataInicioCota", ContaCapitalUtil.formatarDataUS(objeto.getId()));
				comando.configurar();
				comando.executarAtualizacao(conexao);
				
			} catch (PersistenciaException e) {
				this.getLogger().erro(e, e.getMessage());
				throw new BancoobException(e);
			} finally {
				fecharComando(comando);
				fecharConexao(conexao);
			}
			
			return;
		}
		
		super.alterar(objeto);
	}

	/**
	 * {@link ValorCotasLegadoDao#obterValorSalarioBase(Integer)}
	 */
	public BigDecimal obterValorSalarioBase(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		BigDecimal valorSalarioBase = null;
		try {			
			comando = getComando("OBTERVALORSALARIOBASE");	
			comando.configurar();

			conexao = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()) {
				valorSalarioBase = rs.getBigDecimal("ValorSalarioBase");
			}
			
			return valorSalarioBase;
						
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}