/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.LancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.LancamentosCCapitalLegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.LancamentosCCapitalLegadoDao;

/**
 * A Classe LancamentosCCapitalLegadoDaoImpl.
 */
public class LancamentosCCapitalLegadoDaoImpl extends ContaCapitalIntegracaoLegadoCrudDao<LancamentosCCapitalLegado> implements
		LancamentosCCapitalLegadoDao {

	/**
	 * Instancia um novo LancamentosCCapitalLegadoDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public LancamentosCCapitalLegadoDaoImpl(Class<LancamentosCCapitalLegado> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.LancamentosCCapitalLegadoDao#obterUltimoNumSeqLanc(br.com.sicoob.sisbr.cca.legado.negocio.entidades.CapaLoteCapitalLegado)
	 */
	public Integer obterUltimoNumSeqLanc(CapaLoteCapitalLegado capaLoteCapitalLegado) throws BancoobException {
		
		Comando comando = getComando("OBTERULTIMONUMSEQLANC");
		comando.configurar();
		Integer numSeqlanc = null;		
		try {
			Query query = comando.criarQuery(getEntityManager());
			query.setParameter("dataLote", capaLoteCapitalLegado.getCapaLoteCapitalLegadoPK().getDataLote());
			query.setParameter("numLoteLanc", capaLoteCapitalLegado.getCapaLoteCapitalLegadoPK().getNumLoteLanc());			
			numSeqlanc = (Integer) query.getSingleResult();
		} catch (NoResultException nre) {
			return 0;
		} catch (Exception e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_005", e);			
		} finally {
			fecharComando(comando);
		}		
		
		return numSeqlanc;
		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.LancamentosCCapitalLegadoDao#incluirEmLote(java.util.List)
	 */
	public List<LancamentosCCapitalLegado> incluirEmLote(List<LancamentosCCapitalLegado> lancamentosLegado) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		Integer ultimoNumSeqLAnc = 1;
		
		if(!lancamentosLegado.isEmpty()) {
			if(!lancamentosLegado.get(0).getLancamentosCCapitalLegadoPK().getCapaLoteCapitalLegado().getBolNovo()) {
				ultimoNumSeqLAnc = obterUltimoNumSeqLanc(lancamentosLegado.get(0).getLancamentosCCapitalLegadoPK().getCapaLoteCapitalLegado())+1;							
			}
		}		
		
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("INCLUIRLANCAMENTOLEGADOLOTE").getSql());
			for (LancamentosCCapitalLegado lanc : lancamentosLegado) {
				int i=0;
				LancamentosCCapitalLegadoPK lancamentosCCapitalLegadoPK = lanc.getLancamentosCCapitalLegadoPK();
				CapaLoteCapitalLegado capaLote = lancamentosCCapitalLegadoPK.getCapaLoteCapitalLegado();
				CapaLoteCapitalLegadoPK capaLotePK = capaLote.getCapaLoteCapitalLegadoPK();
				ps.setDate(++i, new java.sql.Date(capaLotePK.getDataLote().getTime()));
				ps.setInt(++i, capaLotePK.getNumLoteLanc());
				ps.setInt(++i, ultimoNumSeqLAnc++);
				ps.setString(++i, lanc.getDescNumDocumento());
				ps.setBigDecimal(++i, lanc.getValorLanc());
				ps.setInt(++i, Boolean.TRUE.equals(lanc.getBolAtualizado()) ? 1 : 0);
				ps.setInt(++i, lanc.getContaCapitalLegado().getNumMatricula());
				ps.setInt(++i, lanc.getiDProduto());
				ps.setInt(++i, lanc.getiDTipoHistoricoLanc());
				if (lanc.getiDTipoHistoricoEstorno() == null) {
					ps.setNull(++i, Types.SMALLINT);
				} else {
					ps.setInt(++i, lanc.getiDTipoHistoricoEstorno());
				}
				ps.setString(++i, lanc.getiDUsuarioResp());
				ps.setTimestamp(++i, new java.sql.Timestamp(lanc.getDataHoraInclusao().getTime()));
				ps.setString(++i, lanc.getDescObsDevolucao());
				ps.setLong(++i, lanc.getIdLancamentoContaCapital());
				ps.setString(++i, lanc.getDescOperacaoExterna());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharStatement(ps);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return lancamentosLegado;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.LancamentosCCapitalLegadoDao#verificarLancamentoExistente(java.lang.Integer, java.lang.String)
	 */
	public Boolean verificarLancamentoExistente(Integer numCooperativa, Integer numMatricula, String descOperacaoExterna) throws BancoobException {
		Connection conexao = null;
		Comando comando = null;
		ResultSet rs = null;
		Boolean flag = Boolean.FALSE;		
		try {
			conexao = estabelecerConexao(numCooperativa);
			comando = getComando("VERIFICARLANCAMENTOEXISTENTE");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.adicionarVariavel("descOperacaoExterna", descOperacaoExterna);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				flag = Boolean.TRUE;
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}	
		return flag;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.listarLancBaxadosViaCCO#listarLancViaCCO(br.com.bancoob.persistencia.types.DateTimeDB)
	 */
	public List<LancamentosCCapitalLegadoDTO> listarLancViaCCO(DateTimeDB dataAtualProduto)  throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<LancamentosCCapitalLegadoDTO> lst = new ArrayList<LancamentosCCapitalLegadoDTO>();
		try {
			comando = getComando("CONSULTALANCVIACCO");
			comando.adicionarVariavel("dataAtualProduto", DataUtil.converterDateToString(dataAtualProduto, "yyyyMMdd"));
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				LancamentosCCapitalLegadoDTO dto = new LancamentosCCapitalLegadoDTO();
				
				dto.setDataLote(new DateTimeDB(rs.getDate("DATALOTE").getTime()));
				dto.setNumLoteLanc(rs.getInt("NUMLOTELANC"));
				dto.setNumSeqLanc(rs.getInt("NUMSEQLANC"));
				dto.setDescNumDocumento(rs.getString("DESCNUMDOCUMENTO"));
				dto.setValorLanc(rs.getBigDecimal("VALORLANC"));
				dto.setBolAtualizado(rs.getBoolean("BOLATUALIZADO"));
				dto.setNumMatricula(rs.getInt("NUMMATRICULA"));
				dto.setIdTipoHistoricoLanc(rs.getInt("IDTIPOHISTORICOLANC"));
				dto.setIdUsuarioResp(rs.getString("IDUSUARIORESP"));
				dto.setDataHoraInclusao((new DateTimeDB(rs.getDate("DATAHORAINCLUSAO").getTime())));
				dto.setCodMotivoDevolucao(rs.getInt("CODMOTIVODEVOLUCAO"));
				dto.setDescObsDevolucao(rs.getString("DESCOBSDEVOLUCAO"));
				dto.setIdTipoSubscricao(rs.getInt("IDTIPOSUBSCRICAO"));
				dto.setIdLancamentoContaCapital(rs.getInt("IDLANCAMENTOCONTACAPITAL"));
				dto.setDescOperacaoExterna(rs.getString("DESCOPERACAOEXTERNA"));				
				
				lst.add(dto); 
			}			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lst;
	}			
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.listarLancBaxadosViaCCO#listarLancViaDebIndet(br.com.bancoob.persistencia.types.DateTimeDB)
	 */
	public List<LancamentosCCapitalLegadoDTO> listarLancViaDebIndet(DateTimeDB dataAtualProduto)  throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<LancamentosCCapitalLegadoDTO> lst = new ArrayList<LancamentosCCapitalLegadoDTO>();
		try {
			comando = getComando("CONSULTALANCDEBINDET");
			comando.adicionarVariavel("dataAtualProduto", DataUtil.converterDateToString(dataAtualProduto, "yyyyMMdd"));
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				LancamentosCCapitalLegadoDTO dto = new LancamentosCCapitalLegadoDTO();
				
				dto.setDataLote(new DateTimeDB(rs.getDate("DATALOTE").getTime()));
				dto.setNumLoteLanc(rs.getInt("NUMLOTELANC"));
				dto.setNumSeqLanc(rs.getInt("NUMSEQLANC"));
				dto.setDescNumDocumento(rs.getString("DESCNUMDOCUMENTO"));
				dto.setValorLanc(rs.getBigDecimal("VALORLANC"));
				dto.setBolAtualizado(rs.getBoolean("BOLATUALIZADO"));
				dto.setNumMatricula(rs.getInt("NUMMATRICULA"));
				dto.setIdTipoHistoricoLanc(rs.getInt("IDTIPOHISTORICOLANC"));
				dto.setIdUsuarioResp(rs.getString("IDUSUARIORESP"));
				dto.setDataHoraInclusao((new DateTimeDB(rs.getDate("DATAHORAINCLUSAO").getTime())));
				dto.setCodMotivoDevolucao(rs.getInt("CODMOTIVODEVOLUCAO"));
				dto.setDescObsDevolucao(rs.getString("DESCOBSDEVOLUCAO"));
				dto.setIdTipoSubscricao(rs.getInt("IDTIPOSUBSCRICAO"));
				dto.setIdLancamentoContaCapital(rs.getInt("IDLANCAMENTOCONTACAPITAL"));
				dto.setDescOperacaoExterna(rs.getString("DESCOPERACAOEXTERNA"));				
				
				lst.add(dto); 
			}			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lst;
	}			
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.listarLancBaxadosViaCCO#listarLancamentosDestinacaoJuros(br.com.bancoob.persistencia.types.DateTimeDB)
	 */
	public List<LancamentosCCapitalLegadoDTO> listarLancamentosDestinacaoJuros(DateTimeDB dataAtualProduto)  throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<LancamentosCCapitalLegadoDTO> lst = new ArrayList<LancamentosCCapitalLegadoDTO>();
		try {
			comando = getComando("CONSULTALANCDESTINACAOJUROS");
			comando.adicionarVariavel("dataAtualProduto", DataUtil.converterDateToString(dataAtualProduto, "yyyyMMdd"));
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				LancamentosCCapitalLegadoDTO dto = new LancamentosCCapitalLegadoDTO();
				
				dto.setDataLote(new DateTimeDB(rs.getDate("DATALOTE").getTime()));
				dto.setNumLoteLanc(rs.getInt("NUMLOTELANC"));
				dto.setNumSeqLanc(rs.getInt("NUMSEQLANC"));
				dto.setDescNumDocumento(rs.getString("DESCNUMDOCUMENTO"));
				dto.setValorLanc(rs.getBigDecimal("VALORLANC"));
				dto.setBolAtualizado(rs.getBoolean("BOLATUALIZADO"));
				dto.setNumMatricula(rs.getInt("NUMMATRICULA"));
				dto.setIdTipoHistoricoLanc(rs.getInt("IDTIPOHISTORICOLANC"));
				dto.setIdUsuarioResp(rs.getString("IDUSUARIORESP"));
				dto.setDataHoraInclusao((new DateTimeDB(rs.getDate("DATAHORAINCLUSAO").getTime())));
				dto.setCodMotivoDevolucao(rs.getInt("CODMOTIVODEVOLUCAO"));
				dto.setDescObsDevolucao(rs.getString("DESCOBSDEVOLUCAO"));
				dto.setIdTipoSubscricao(rs.getInt("IDTIPOSUBSCRICAO"));
				dto.setIdLancamentoContaCapital(rs.getInt("IDLANCAMENTOCONTACAPITAL"));
				dto.setDescOperacaoExterna(rs.getString("DESCOPERACAOEXTERNA"));				
				
				lst.add(dto); 
			}			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lst;
	}			
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.listarLancBaxadosViaCCO#atualizaMovimentoLancamentos(java.lang.Integer)
	 */
	public void atualizaMovimentoLancamentos(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("ATUALIZARMOVIMENTOLANCAMENTOS");
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao realizar movimento de lançamentos", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}	
	}	
				
}
