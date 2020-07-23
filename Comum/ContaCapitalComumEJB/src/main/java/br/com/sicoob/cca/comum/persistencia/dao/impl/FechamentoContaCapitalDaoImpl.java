/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.FechamentoContaCapitalDao;

/**
 * @author Marco.Nascimento
 */
public class FechamentoContaCapitalDaoImpl extends ContaCapitalComumDao implements FechamentoContaCapitalDao {

	/**
	 * {@link FechamentoContaCapitalDao#isFechamentoIniciado(Integer)}
	 */
	public boolean isFechamentoIniciado(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("VERIFICA_FECH_CAPITAL_INICIADO");
			comando.adicionarVariavel("PRODUTO_CONTA_CAPITAL", ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			
			ResultSet rs = comando.executarConsulta(conexao);
			if(rs.next()) {
				return true;
			}
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return false;
	}

	public String buscarIdUsuarioFechamento(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			comando = getComando("FECHAMENTOQUERYUSERID");
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			
			ResultSet rs  = comando.executarConsulta(conexao);
			
			if(rs.next()) {
				return rs.getString("IDUsuario");				
			}
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException("Erro ao recuperar usuário fechamento", e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException("Erro ao recuperar usuário fechamento", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return "";
	}

	/**
	 * {@link FechamentoContaCapitalDao#isStepFechamentoIniciado(Integer)}
	 */
	public boolean isStepFechamentoIniciado(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("VERIFICASTEPFECHCAPITALINICIADO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("dataAtualProduto", dataAtualProduto);
			
			comando.adicionarVariavel("idProcesso", idProcesso);			
			
			comando.adicionarVariavel("idTipoStatusProcessoIniciado", ContaCapitalConstantes.COD_FECHAMENTO_INICIADO);
			comando.adicionarVariavel("idTipoStatusProcessoConcluido", ContaCapitalConstantes.COD_FECHAMENTO_CONCLUIDO);
			comando.configurar();
			conexao = estabelecerConexao();
			
			ResultSet rs = comando.executarConsulta(conexao);
			
			if( rs.next() ) {
				return false;
			}
			
			return true;
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}

	}

	/**
	 * {@link FechamentoContaCapitalDao#processoConcluido(Integer)}
	 */
	public boolean processoConcluido(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("STEPFECHCAPITALCONCLUIDOREJEITADO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("dataAtualProduto", dataAtualProduto);
			
			comando.adicionarVariavel("idProcesso", idProcesso);
			
			comando.adicionarVariavel("idTipoStatusProcessoIniciado", ContaCapitalConstantes.COD_FECHAMENTO_INICIADO);
			comando.adicionarVariavel("idTipoStatusProcesso", ContaCapitalConstantes.COD_FECHAMENTO_CONCLUIDO);
			
			comando.configurar();
			conexao = estabelecerConexao();
			
			int rs = comando.executarAtualizacao(conexao);
			return rs == 1;
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}

	}
	
	/**
	 * {@link FechamentoContaCapitalDao#processoRejeitado(Integer)}
	 */
	public boolean processoRejeitado(Date dataAtualProduto, Integer idInstituicao, Integer idProcesso, String descErroProcesso) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("STEPFECHCAPITALCONCLUIDOREJEITADO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("dataAtualProduto", dataAtualProduto);
			
			comando.adicionarVariavel("idProcesso", idProcesso);
			comando.adicionarVariavel("descErroProcesso", descErroProcesso);
			
			comando.adicionarVariavel("idTipoStatusProcessoIniciado", ContaCapitalConstantes.COD_FECHAMENTO_INICIADO);
			comando.adicionarVariavel("idTipoStatusProcesso", ContaCapitalConstantes.COD_FECHAMENTO_REJEITADO);
			
			comando.configurar();
			conexao = estabelecerConexao();
			
			int rs = comando.executarAtualizacao(conexao);
			return rs == 1;
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}

	}
}
