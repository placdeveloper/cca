/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParticipacaoIndiretaBancoobLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.InformacaoAcumuladaLegadoDao;

/**
 * @author Marco.Nascimento
 * @since 08/06/2014
 */
public class InformacaoAcumuladaLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements InformacaoAcumuladaLegadoDao {
	
	/**
	 * {@link InformacaoAcumuladaLegadoDao#obterSaldoCapitalSingulares()}
	 * @param dataParametro
	 * @param numCoopCentral
	 */
	public List<ParticipacaoIndiretaBancoobLegadoDTO> obterSaldoCapitalSingulares(DateTime dataParametro, Integer numCoopCentral, List<Integer> cooperativasSingulares) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<ParticipacaoIndiretaBancoobLegadoDTO> listaRetorno = new ArrayList<ParticipacaoIndiretaBancoobLegadoDTO>();
		try {
			Integer numCoop;
			comando = getComando("OBTERSALDOCAPITALSINGULARES");
			comando.adicionarVariavel("mesReferencia", dataParametro.getMonthOfYear());
			comando.adicionarVariavel("anoReferencia", dataParametro.getYear());
			comando.adicionarVariavel("numCooperativas", ContaCapitalUtil.formatarListaValoresIN(cooperativasSingulares));
			comando.configurar();
			conexao = estabelecerConexao(numCoopCentral);
			
			rs = comando.executarConsulta(conexao);

			while(rs.next()) {
				ParticipacaoIndiretaBancoobLegadoDTO retorno = new ParticipacaoIndiretaBancoobLegadoDTO();
				retorno.setAno(rs.getInt("numAnoref"));
				retorno.setMes(rs.getInt("numMesRef"));
				
				numCoop = rs.getInt("numCoop") == 0 ?null:rs.getInt("numCoop");
				retorno.setNumCooperativa(numCoop);
				retorno.setValor(rs.getBigDecimal("valor") != null ? rs.getBigDecimal("valor") : BigDecimal.ZERO);
				listaRetorno.add(retorno);
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
		} catch(PersistenciaException e){
			this.getLogger().erro(e, e.getMessage());
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.InformacaoAcumuladaLegadoDao#consultarSnapshotCooperativas(java.lang.Integer, org.joda.time.DateTime)
	 */
	public List<ParticipacaoIndiretaBancoobLegadoDTO> consultarSnapshotCooperativas(Integer numCoopCentral, DateTime dataSnapshot) {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<ParticipacaoIndiretaBancoobLegadoDTO> listaRetorno = new ArrayList<ParticipacaoIndiretaBancoobLegadoDTO>();
		try {
			comando = getComando("OBTERSNAPSHOTSINGULARES");
			comando.adicionarVariavel("numCoopCentral", numCoopCentral);
			comando.adicionarVariavel("mesReferencia", dataSnapshot.getMonthOfYear());
			comando.adicionarVariavel("anoReferencia", dataSnapshot.getYear());
			comando.configurar();
			conexao = estabelecerConexao(numCoopCentral);
			rs = comando.executarConsulta(conexao);

			while(rs.next()) {
				ParticipacaoIndiretaBancoobLegadoDTO retorno = new ParticipacaoIndiretaBancoobLegadoDTO();
				retorno.setNumCooperativa(rs.getInt("numCoop") == 0 ? null : rs.getInt("numCoop"));
				listaRetorno.add(retorno);
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
		} catch(PersistenciaException e){
			this.getLogger().erro(e, e.getMessage());
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}
}