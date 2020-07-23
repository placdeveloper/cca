/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.PLDContaCapitalLegadoDao;

/**
 * Implementacao de PLDContaCapitalLegadoDao
 */
public class PLDContaCapitalLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements PLDContaCapitalLegadoDao {
	
	/**
	 * {@link PLDContaCapitalLegadoDao#gerarPLD(Integer, Date)}
	 */
	public Boolean gerarPLD(Integer numCoop, Date dataLancamento) {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			comando = getComando("GERAR_PLD_CCA");
			comando.adicionarVariavel("dataLancamento", new SimpleDateFormat("yyyy-MM-dd").format(dataLancamento));
			
			comando.configurar();
			
			conexao = estabelecerConexao(numCoop);
			
			comando.executarStoredProcedure(conexao);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return Boolean.TRUE;
	}
}