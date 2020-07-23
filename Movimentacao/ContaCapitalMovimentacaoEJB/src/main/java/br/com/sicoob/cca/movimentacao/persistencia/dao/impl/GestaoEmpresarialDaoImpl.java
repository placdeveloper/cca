package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.movimentacao.negocio.dto.GestaoEmpresarialDTO;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.GestaoEmpresarialDao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;

/**
 * A Classe GestaoEmpresarialDao.
 *
 * @author marco.nascimento
 */
public class GestaoEmpresarialDaoImpl extends ContaCapitalMovimentacaoDao implements GestaoEmpresarialDao {
	
	/**
	 * {@link GestaoEmpresarialDao#isPrimeiraCarga(Integer)}.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return {@code true}, se for primeira carga
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean isPrimeiraCarga(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		try {
			comando = getComando("PESQUISARCARGAGESTAOEMPRESARIAL");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			
			rs.next();
			
			int count = rs.getInt(1);
			
			return count == 0;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * {@link GestaoEmpresarialDao#incluir(List)}
	 */
	public void incluir(List<GestaoEmpresarialLegadoDTO> lst) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			conexao = estabelecerConexao();
			
			for (GestaoEmpresarialLegadoDTO dto : lst) {
				
				if(dto.getBaseIRRF() != null && dto.getValorIRRF() != null) {
					
					comando = getComando("INCLUIRGESTAOEMPRESARIAL");
					comando.adicionarVariavel("IDINSTITUICAO", dto.getIdInstituicao());
					comando.adicionarVariavel("NUMPAC", dto.getNumPac());
					comando.adicionarVariavel("CODTIPOPESSOA", dto.getCodPFPJ());
					comando.adicionarVariavel("DATALOTE", dto.getDataLote());
					comando.adicionarVariavel("VALORBASEIRRF", dto.getBaseIRRF());
					comando.adicionarVariavel("VALORIRRF", dto.getValorIRRF());
					
					comando.configurar();
					comando.executarAtualizacao(conexao);
				}
			}
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link GestaoEmpresarialDao#gerarExtratoDIRF(Integer, Date, Date)}.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param dataInicio o valor de data inicio
	 * @param dataFim o valor de data fim
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<GestaoEmpresarialDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio, Date dataFim) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<GestaoEmpresarialDTO> lst = new ArrayList<GestaoEmpresarialDTO>(0);
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARGESTAOEMPRESARIAL");
			
			if(idInstituicao != null && !idInstituicao.isEmpty()) {
				comando.adicionarVariavel("idInstituicao", idInstituicao);
			}
			
			comando.adicionarVariavel("dataInicio", DataUtil.converterDateToString(dataInicio, "yyyy-MM-dd"));
			comando.adicionarVariavel("dataFim", DataUtil.converterDateToString(dataFim, "yyyy-MM-dd"));
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			GestaoEmpresarialDTO dto;
			while(rs.next()) {			
				dto = new GestaoEmpresarialDTO();
				dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				dto.setNumPac(rs.getInt("NUMPAC"));
				dto.setCodPFPJ(rs.getInt("CODTIPOPESSOA"));
				dto.setBaseIRRF(rs.getBigDecimal("VALORBASEIRRF"));
				dto.setValorIRRF(rs.getBigDecimal("VALORIRRF"));
				dto.setDataInicio(dataInicio);
				dto.setDataFim(dataFim);
				dto.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
				dto.setNomeProduto("Conta Capital");
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link GestaoEmpresarialDao#pesquisarDataLoteRecente(Integer)}
	 */
	public Date pesquisarDataLoteRecente(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		Date dataLote = null;
		
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARDATALOTEEXTRATOIRRF");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {			
				dataLote = rs.getDate("DATALOTE");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return dataLote;
	}
}