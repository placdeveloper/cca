package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechGerarInfoCalculoVarLegadoDao;

/**
 * @author Ricardo.Barcante
 */
public class FechGerarInfoCalculoVarLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements FechGerarInfoCalculoVarLegadoDao {

	public void gerarInfoCalculoVar(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conn = null;

		try {
			comando = getComando("FECHAMENTOGERARINFOCALCULOVARLEGADO");
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

	public Integer obterValorParametro(Integer numCoop, int idParametro) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			comando = getComando("OBTERVALORPARAMETRO");
			comando.adicionarVariavel("idParametro", idParametro);
			comando.configurar();
			conn = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conn);

			if(rs.next()) {
				return rs.getInt("ValorPar");
			}
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}	
		
		return null;
	}

	public boolean fechOrdemProcessoRowCountIsZero(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			comando = getComando("OBTERCOUNTFECHORDEMPROCESSO");
			
			comando.adicionarVariavel("idParametro", ContaCapitalConstantes.PAR_PROCESSO_FECHREMNOITE);
			comando.adicionarVariavel("idProduto", ContaCapitalConstantes.PRODUTO_CAPTACAO_REMUNERADA);
			
			comando.configurar();
			conn = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conn);

			return rs.getFetchSize() == 0;
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}	
	}
}