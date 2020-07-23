/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.HistContaCapitalLegadoDao;

/**
 * A Classe HistContaCapitalLegadoDaoImpl.
 */
public class HistContaCapitalLegadoDaoImpl extends ContaCapitalIntegracaoLegadoCrudDao<HistContaCapitalLegado> implements
		HistContaCapitalLegadoDao {

	/**
	 * Instancia um novo HistContaCapitalLegadoDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public HistContaCapitalLegadoDaoImpl(Class<HistContaCapitalLegado> clazz,
			String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
		// TODO Auto-generated constructor stub
	}
	
	
	/** (non-Javadoc)
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public HistContaCapitalLegado incluir(HistContaCapitalLegado objeto) throws BancoobException {
		
		if(objeto.getNumCoop() != null && objeto.getNumCoop().intValue() > 0) {
			
			Comando comando = null;
			Connection conexao = null;
			
			try {
				conexao = estabelecerConexao(objeto.getNumCoop());
				
				comando = getComando("INCLUIRHISTCONTACAPITALLEGADO");
				comando.adicionarVariavel("obj", objeto);
				comando.configurar();
				comando.executarAtualizacao(conexao);
				
			} catch (PersistenciaException e) {
				this.getLogger().erro(e, e.getMessage());
				throw new BancoobException(e);
			} finally {
				fecharComando(comando);
				fecharConexao(conexao);
			}
			
			return null;
		}
		
		return super.incluir(objeto);
	}
	
	/**
	 * {@link HistContaCapitalLegadoDao#qtdInativacaoCCA(Integer)}
	 */
	public Integer qtdInativacaoCCA(Integer numMatricula) throws BancoobException {
		
		Query query = getEntityManager().createNativeQuery(getComando("QTDINATIVACAOCCA").getSql());
		query.setParameter("numMatricula", numMatricula);
		return (Integer) query.getSingleResult();
		
	}
}
