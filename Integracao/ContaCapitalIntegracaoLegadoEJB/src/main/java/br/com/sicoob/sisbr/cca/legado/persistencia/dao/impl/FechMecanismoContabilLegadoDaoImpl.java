package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechMecanismoContabilLegadoDao;

/**
 * @author ricardo.barcante
 */
public class FechMecanismoContabilLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements FechMecanismoContabilLegadoDao {

	/**
	 * {@link FechMecanismoContabilLegadoDao#fechamentoMecanismoContabil(Integer, Integer)}
	 */
	public void fechamentoMecanismoContabil(Integer idProduto, Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;

		try {
			comando = getComando("FECHAMENTOMECANISMOCONTABIL");
			comando.adicionarVariavel("idProduto", idProduto);
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
