package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechDestinacaoJurosLegadoDao;

/**
* @author Antonio.Genaro
*/
public class FechDestinacaoJurosLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements FechDestinacaoJurosLegadoDao {

	public void gerarLancamentoProvisaoJuros(Integer numCoop, String idUsuario) throws BancoobException {
		Comando comando = null;
		Connection conn = null;

		try {
			comando = getComando("FECHAMENTOGERARLANCPROVISAOJUROS");
			comando.adicionarVariavel("idUsuario", idUsuario);
			comando.configurar();
			conn = estabelecerConexao(numCoop);
			comando.executarStoredProcedure(conn);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}		
	}

	public void gerarLancamentoDestinacaoJuros(Integer numCoop, String idUsuario) throws BancoobException {
		Comando comando = null;
		Connection conn = null;

		try {
			comando = getComando("FECHAMENTOGERARLANCDESTINACAOJUROS");
			comando.adicionarVariavel("idUsuario", idUsuario);
			comando.configurar();
			conn = estabelecerConexao(numCoop);
			comando.executarStoredProcedure(conn);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}		
	}

}