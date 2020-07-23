package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.SaldoContaCapitalDao;

public class SaldoContaCapitalDaoImpl  extends ContaCapitalMovimentacaoDao implements SaldoContaCapitalDao {

	/**
	 * Grava a carga da saldo conta capital com o saldo da data informada
	 */
	public void incluirCarga(Integer idInstituicao, Date dataAnteriorProduto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			comando = getComando("INCLUICARGASALDOCONTACAPITAL");						
			conexao = estabelecerConexao();
			comando.adicionarVariavel("IDINSTITUICAO", idInstituicao);
			comando.adicionarVariavel("DATAANTERIORPRODUTO", ContaCapitalUtil.formatarDataUS(dataAnteriorProduto));
			comando.configurar();
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}

	}
	
	/**
	 * Consulta a data para verificar a carga
	 */
	public Boolean validarDataSaldoSeJaPossuiCarga(Integer idInstituicao, Date dataAnteriorProduto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
	
		try {
			comando = getComando("VALIDARDATAJADADOCARGA");
			comando.adicionarVariavel("IDINSTITUICAO", idInstituicao);
			comando.adicionarVariavel("DATAANTERIORPRODUTO", ContaCapitalUtil.formatarDataUS(dataAnteriorProduto));
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			
			rs.next();

			return (rs.getInt(1) >0 ? Boolean.TRUE:Boolean.FALSE);
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}

	
}
