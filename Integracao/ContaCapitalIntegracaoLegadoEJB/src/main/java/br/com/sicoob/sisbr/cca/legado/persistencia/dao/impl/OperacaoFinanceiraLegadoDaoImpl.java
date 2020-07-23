/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.OperacaoFinanceiraLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.OperacaoFinanceiraLegadoDao;

/**
 * @author Marco.Nascimento
 * @since 19/06/2014
 */
public class OperacaoFinanceiraLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements OperacaoFinanceiraLegadoDao {
	
	/**
	 * {@link OperacaoFinanceiraLegadoDao#incluir(OperacaoFinanceiraLegadoDTO)}
	 */
	public void incluir(OperacaoFinanceiraLegadoDTO of) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			conexao = estabelecerConexao(of.getNumCoopSingular());
			comando = getComando("INCLUIROPERACAOFINANCEIRA");
			comando.adicionarVariavel("operacaoFinanceira", of);
			comando.configurar();
			comando.executarAtualizacao(conexao);
			
			incluirValor(of);
			
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * Inclui valor da operacao financeira ValorOpFin
	 * @param of
	 */
	private void incluirValor(OperacaoFinanceiraLegadoDTO of) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			conexao = estabelecerConexao(of.getNumCoopSingular());
			comando = getComando("INCLUIRVALOROPERACAOFINANCEIRA");
			comando.adicionarVariavel("operacaoFinanceira", of);
			comando.configurar();
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoDao#getUltimoId(Integer)}
	 */
	public Integer getUltimoId(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Integer retorno = null;
		try {
			conexao = estabelecerConexao(numCoop);
			comando = getComando("ULTIMOIDOPERACAOFINANCEIRA");
			comando.configurar();
			comando.executarConsulta(conexao);
			rs = comando.executarConsulta(conexao);
			if(rs.next()) {
				retorno = rs.getInt("IDOpFinanceira");
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return retorno;
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoDao#consultarValorEstorno(Integer)}
	 */
	public BigDecimal consultarValorEstorno(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			conexao = estabelecerConexao(numCoop);
			comando = getComando("VALORESTORNOPARTINDIRETA");
			comando.adicionarVariavel("dataPrimeiroFechamento", "2014-09-09");
			comando.adicionarVariavel("idProduto", ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
			comando.adicionarVariavel("idOperacao", ContaCapitalConstantes.ID_OPERACAO_PARTICIPACAO_INDIRETA);
			comando.adicionarVariavel("codStatusOpFin", ContaCapitalConstantes.COD_STATUS_CONTABILIZADO);
			comando.adicionarVariavel("idTipoValorCont", ContaCapitalConstantes.VALOR_REF_PART_INDIRETA);					
			
			comando.configurar();
			comando.executarConsulta(conexao);
			rs = comando.executarConsulta(conexao);
			if(rs.next()) {
				retorno = rs.getBigDecimal("ValorEstorno") != null ? rs.getBigDecimal("ValorEstorno") : BigDecimal.ZERO;
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return retorno;
	}

	/**
	 * {@link OperacaoFinanceiraLegadoDao#existeOperacaoFinanceiraPI(Integer)
	 */
	public Boolean existeOperacaoFinanceiraPI(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Integer retorno = 0;
		try {
			conexao = estabelecerConexao(numCoop);
			comando = getComando("CONSULTA_OPERACAO_FINANCEIRA_PI");
			comando.adicionarVariavel("idProduto", ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
			comando.adicionarVariavel("idOperacao", ContaCapitalConstantes.ID_OPERACAO_PARTICIPACAO_INDIRETA);
			comando.configurar();
			comando.executarConsulta(conexao);
			rs = comando.executarConsulta(conexao);
			if(rs.next()){
				retorno = rs.getInt(1);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return retorno.intValue() > 0;
	}
	
	/**
	 * {@link OperacaoFinanceiraLegadoDao#valorParticipacaoCentralPorAnoMes(Integer, Integer)}
	 */
	public BigDecimal valorParticipacaoCentralPorAnoMes(Integer numCoop, Integer anoMes) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		BigDecimal retorno = null;
		try {
			conexao = estabelecerConexao(numCoop);
			comando = getComando("VALORPARTICIPACAOCENTRALPORANOMES");
			comando.adicionarVariavel("numConta", ContaCapitalConstantes.NUM_CONTA_PI);
			comando.adicionarVariavel("anoMes", new StringBuffer().append(anoMes).append("01").toString());
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			if(rs.next()){
				retorno = rs.getBigDecimal("SALDOCONTA");
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return retorno;
	}
}