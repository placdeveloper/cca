/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ClienteCooperativaLegadoDao;

/**
 * @author Marco.Nascimento
 */
public class ClienteCooperativaLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements ClienteCooperativaLegadoDao {
	
	/**
	 * {@link ClienteCooperativaLegadoDao#consultaClienteCooperativa(Integer, Integer)}
	 */
	public Integer consultarClienteCooperativa(Integer numCoop, Integer numPac) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Integer retorno = null;
		try {
			conexao = estabelecerConexao(numCoop);
			comando = getComando("CONSULTARCLIENTECOOPERATIVA");
			comando.adicionarVariavel("numCoop", numCoop);
			comando.adicionarVariavel("numPac", numPac);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				retorno = rs.getInt("numcliente");
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
		return retorno;
	}
}