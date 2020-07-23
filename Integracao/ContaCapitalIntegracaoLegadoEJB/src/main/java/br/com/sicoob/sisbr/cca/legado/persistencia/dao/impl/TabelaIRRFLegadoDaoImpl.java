package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TabelaIRRFLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TabelaIRRFLegadoDao;

/**
 * A Classe TabelaIRRFLegadoDaoImpl.
 */
public class TabelaIRRFLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements TabelaIRRFLegadoDao {
	
	public static final Integer COOP_BDSICOOBINTEGRACAO = 0;
	
	/**
	 * {@link TabelaIRRFLegadoDao#incluir(List<TabelaIRRFLegadoDTO>)}
	 */
	public void incluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			if(!lstTabelaIRRF.isEmpty()) {
				
				excluirPorAnoBase(lstTabelaIRRF.get(0).getAnoBase());
				
				conexao = estabelecerConexao(COOP_BDSICOOBINTEGRACAO);
				for(TabelaIRRFLegadoDTO dto : lstTabelaIRRF) {
					comando = getComando("INCLUIRTABELAIRRF");
					comando.adicionarVariavel("dto", dto);
					comando.configurar();
					comando.executarAtualizacao(conexao);
				}
			}
				
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link TabelaIRRFLegadoDao#consultarPorAnoBase(Integer)}
	 */
	public List<TabelaIRRFLegadoDTO> consultarPorAnoBase(Integer anoBase) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<TabelaIRRFLegadoDTO> listaRetorno = new ArrayList<TabelaIRRFLegadoDTO>();
		
		try {
			comando = getComando("OBTERIRRFANOBASE");
			
			if(anoBase != null) {
				comando.adicionarVariavel("anoBase", anoBase);
			}
			
			comando.configurar();
			
			conexao = estabelecerConexao(COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			TabelaIRRFLegadoDTO dto = null;
			while(rs.next()) {
				dto = new TabelaIRRFLegadoDTO();
				dto.setAnoBase(rs.getInt("ANOBASE"));
				dto.setPercAliquota(rs.getBigDecimal("PERCALIQUOTA"));
				dto.setValorBaseInicial(rs.getBigDecimal("VALORBASEINICIAL"));
				dto.setValorBaseFinal(rs.getBigDecimal("VALORBASEFINAL"));
				dto.setValorParcelaDeducao(rs.getBigDecimal("VALORPARCELADEDUCAO"));
				listaRetorno.add(dto);
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}
	
	private void excluirPorAnoBase(Integer anoBase) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			conexao = estabelecerConexao(COOP_BDSICOOBINTEGRACAO);
			comando = getComando("EXCLUIRTABELAIRRFANO");
			comando.adicionarVariavel("anoBase", anoBase);
			comando.configurar();
			comando.executarAtualizacao(conexao);
				
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * {@link TabelaIRRFLegadoDao#incluir(List<TabelaIRRFLegadoDTO>)}
	 */
	public void excluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			if(!lstTabelaIRRF.isEmpty()) {
				
				conexao = estabelecerConexao(COOP_BDSICOOBINTEGRACAO);
				for(TabelaIRRFLegadoDTO dto : lstTabelaIRRF) {
					comando = getComando("EXCLUIRTABELAIRRF");
					comando.adicionarVariavel("dto", dto);
					comando.configurar();
					comando.executarAtualizacao(conexao);
				}
			}
				
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}