/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConciliacaoContabilLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ConciliacaoContabilLegadoDao;
import br.com.sicoob.tipos.DateTime;

/**
 * @author Marco.Nascimento
 */
public class ConciliacaoContabilLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements ConciliacaoContabilLegadoDao {

	/**
	 * Instancia um novo ConciliacaoContabilLegadoDaoImpl.
	 */
	public ConciliacaoContabilLegadoDaoImpl(){
		super();
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ConciliacaoContabilLegadoDao#obterListaDadosConciliacaoContabil(java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB)
	 */
	public List<ConciliacaoContabilLegadoDTO> obterListaDadosConciliacaoContabil(Integer numCooperativa, DateTimeDB dataLote) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<ConciliacaoContabilLegadoDTO> listaRetorno = new ArrayList<ConciliacaoContabilLegadoDTO>();
		
		try {
			comando = getComando("OBTERDADOSCONCILIACAOCONTABIL");
			comando.configurar();
			conexao = estabelecerConexao(numCooperativa);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()){
				ConciliacaoContabilLegadoDTO retorno = new ConciliacaoContabilLegadoDTO();
				retorno.setDataContabilizacao(new DateTime(rs.getDate("DataContabilizacao").getTime()));
				retorno.setNumContaBACEN(rs.getString("NumContaBACEN"));
				retorno.setSaldoTotal(rs.getBigDecimal("SaldoTotal"));
				listaRetorno.add(retorno);
			}			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_014",e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_014",e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;		
	}
	
	/**
	 * {@link ConciliacaoContabilLegadoDao#atualizarConciliacaoContabil(Integer, ConciliacaoContabilLegadoDTO)}
	 * @param numCooperativa
	 * @param dto
	 * @throws BancoobException
	 */
	public void atualizarConciliacaoContabil(Integer numCooperativa, ConciliacaoContabilLegadoDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("ATUALIZARCONCILIACAOCONTABIL");
			comando.adicionarVariavel("dataContabilizacao", DataUtil.converterDateToString(dto.getDataContabilizacao(), "yyyy-MM-dd"));
			comando.adicionarVariavel("numContaBACEN", dto.getNumContaBACEN());
			comando.configurar();
			conexao = estabelecerConexao(numCooperativa);
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}
