package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.negocio.servicos.ejb.FechamentoContaCapitalServicoEJB;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.FechCalculoProvisaoJurosDao;

/**
* @author Antonio.Genaro
*/
public class FechCalculoProvisaoJurosDaoImpl extends ContaCapitalIntegracaoLegadoDao implements FechCalculoProvisaoJurosDao {

	public void gerarCalculoProvisaoJuros(Integer numCoop) throws BancoobException {
		SicoobLoggerPadrao.getInstance(FechCalculoProvisaoJurosDaoImpl.class).info("------ Inicio FechCalculoProvisaoJurosDaoImpl.gerarCalculoProvisaoJuros ------ ");
		Comando comando = null;
		Connection conn = null;

		try {
			comando = getComando("FECHAMENTOGERARCALCULOPROVISAOJUROS");
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