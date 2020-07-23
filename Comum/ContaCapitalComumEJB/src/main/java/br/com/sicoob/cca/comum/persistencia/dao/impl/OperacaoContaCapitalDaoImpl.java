package br.com.sicoob.cca.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumCrudDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.OperacaoContaCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.OperacaoContaCapital;

/**
 * OperacaoContaCapitalDaoImpl
 * @author Nairon.Silva
 */
public class OperacaoContaCapitalDaoImpl extends ContaCapitalComumCrudDao<OperacaoContaCapital> implements OperacaoContaCapitalDao {

	/**
	 * Instancia um novo OperacaoContaCapitalDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public OperacaoContaCapitalDaoImpl(Class<OperacaoContaCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
	public void expurgarOperacao(Date dataExpurgoOperacao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("EXPURGAROPERACAO");
			comando.adicionarVariavel("dataExpurgoOperacao", DataUtil.converterDateToString(dataExpurgoOperacao, "yyyy-MM-dd"));
			comando.configurar();
			conexao = estabelecerConexao();
			
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}
	
	public void incluirOperacaoContaCapitalLote(List<OperacaoContaCapital> listOperacaoContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("INCLUIROPERACAOLOTE").getSql());
			for (OperacaoContaCapital op : listOperacaoContaCapital) {
				int i=0;
				ps.setDate(++i, new java.sql.Date(new Date().getTime()));
				ps.setDate(++i, new java.sql.Date(new Date().getTime()));
				ps.setInt(++i, op.getMetodo().getId());
				ps.setString(++i, op.getDescParametros());
				ps.setInt(++i, op.getResultado().getId());
				ps.setString(++i, op.getDescErro());
				ps.setString(++i, op.getIdUsuario());
				ps.setInt(++i, op.getIdInstituicao());
				ps.setShort(++i, op.getIdUnidadeInst());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharStatement(ps);
			fecharComando(comando);
			fecharConexao(conexao);
		}		
	}	

}
