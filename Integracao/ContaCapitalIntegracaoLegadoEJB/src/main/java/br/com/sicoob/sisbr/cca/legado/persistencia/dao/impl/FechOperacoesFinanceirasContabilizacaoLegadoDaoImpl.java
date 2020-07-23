package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechOperacoesFinanceirasContabilizacaoLegadoDao;

/**
* @author Ricardo.Barcante
*/
public class FechOperacoesFinanceirasContabilizacaoLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao
		implements FechOperacoesFinanceirasContabilizacaoLegadoDao {

	public void fechamentoOperacoesFinanceirasContabilizacao(Integer numCoop, String idUsuario) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;

		try {
			comando = getComando("FECHAMENTOPROCESSAROPERACOESFINANCEIRASCONTABILIZACAO");
			comando.adicionarVariavel("idUsuario", idUsuario);
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			comando.executarStoredProcedure(conexao);	
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao fechar mecanismo contábil", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}
