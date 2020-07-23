/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.CapaLoteCapitalLegadoDao;

/**
 * A Classe CapaLoteCapitalLegadoDaoImpl.
 */
public class CapaLoteCapitalLegadoDaoImpl extends ContaCapitalIntegracaoLegadoCrudDao<CapaLoteCapitalLegado> implements
		CapaLoteCapitalLegadoDao {

	/**
	 * Instancia um novo CapaLoteCapitalLegadoDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public CapaLoteCapitalLegadoDaoImpl(Class<CapaLoteCapitalLegado> clazz,
			String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.CapaLoteCapitalLegadoDao#atualizarCapaLote(java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer)
	 */
	public void atualizarCapaLote(Integer numCooperativa, DateTimeDB dataLote, Integer numLote) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			
			conexao = estabelecerConexao(numCooperativa);
			comando = getComando("ATUALIZARCAPALOTE");
			
			comando.adicionarVariavel("dataLote", dataLote);
			comando.adicionarVariavel("numLote", numLote);
			
			comando.configurar();
			
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

}
