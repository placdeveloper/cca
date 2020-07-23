/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.GestaoEmpresarialLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.GestaoEmpresarialLegadoDao;

/**
 * Implementacao de GestaoEmpresarialLegadoDao
 */
public class GestaoEmpresarialLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements GestaoEmpresarialLegadoDao {
	
	/**
	 * {@link GestaoEmpresarialLegadoDao#gerarExtratoDIRF(Integer, Date)}
	 */
	public List<GestaoEmpresarialLegadoDTO> gerarExtratoDIRF(Integer numCoop, Date data) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<GestaoEmpresarialLegadoDTO> lst = new ArrayList<GestaoEmpresarialLegadoDTO>();
		
		try {
			
			comando = getComando("EXTRATOIRRF");
			
			if(data != null) {
				comando.adicionarVariavel("dataInicio", DataUtil.converterDateToString(data, "yyyy-MM-dd"));
			}
			
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conexao);
			
			GestaoEmpresarialLegadoDTO dto;
			while(rs.next()) {			
				dto = new GestaoEmpresarialLegadoDTO();
				dto.setIdInstituicao(rs.getInt("Instituicao"));
				dto.setNumPac(rs.getInt("NumPac"));
				dto.setCodPFPJ(rs.getInt("CodPF_PJ"));
				dto.setDataLote(rs.getDate("DataLote"));
				dto.setBaseIRRF(rs.getBigDecimal("BaseIRRF"));
				dto.setValorIRRF(rs.getBigDecimal("ValorIRRF"));
				lst.add(dto);
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new GestaoEmpresarialLegadoException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return lst;
	}
	
	/**
	 * {@link GestaoEmpresarialLegadoDao#novosLancamentosDIRF(Integer, Date)}
	 */
	public Boolean novosLancamentosDIRF(Integer numCoop, Date data) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		try {
			
			comando = getComando("NOVOSLANCAMENTOSIRRF");
			comando.adicionarVariavel("dataInicio", DataUtil.converterDateToString(data, "yyyy-MM-dd"));
			
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conexao);
			rs.next();
			
			int count = rs.getInt(1); 
			
			return count > 0;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new GestaoEmpresarialLegadoException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}