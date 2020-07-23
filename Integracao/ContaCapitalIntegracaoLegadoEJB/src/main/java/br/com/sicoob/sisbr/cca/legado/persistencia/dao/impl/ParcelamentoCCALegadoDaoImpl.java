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

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosFechBaixarParcelasLegadoCCODTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao;

/**
 * A Classe ParcelamentoCCALegadoDaoImpl.
 */
public class ParcelamentoCCALegadoDaoImpl extends ContaCapitalIntegracaoLegadoCrudDao<ParcelamentoCCALegado>implements ParcelamentoCCALegadoDao {

	/**
	 * Instancia um novo ParcelamentoCCALegadoDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ParcelamentoCCALegadoDaoImpl(Class<ParcelamentoCCALegado> clazz,String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#obterProximoNumParcelamento(java.lang.Integer, java.lang.Integer)
	 */
	public Integer obterProximoNumParcelamento(Integer numMatricula, Integer codTipoParcelamento) throws BancoobException {
		
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Integer numParcelamento = null;	
		
		try {
			comando = getComando("OBTERPROXIMOPARCELAMENTO");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.adicionarVariavel("codTipoParcelamento", codTipoParcelamento);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if(rs.next()) {			
				numParcelamento = rs.getInt("numParcelamento");
			}else{
				numParcelamento = 1;				
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_019",e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_019",e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		
		
		return numParcelamento;
		
	}
		
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#verificarParcelamentoAberto(java.lang.Integer, java.lang.Integer)
	 */
	public Integer verificarParcelamentoAberto(Integer numMatricula, Integer codTipoParcelamento) throws BancoobException {
		
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Integer qtdParcelas = 0;	
		
		try {
			comando = getComando("VERIFICARPARCELASEMABERTO");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.adicionarVariavel("codTipoParcelamento", codTipoParcelamento);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if(rs.next()) {			
				qtdParcelas = rs.getInt("qtdParcelas");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_019",e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_019",e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		
		
		return qtdParcelas;
		
	}		
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#verificarParcelasEmAbertoPelosCanais(java.lang.Integer)
	 */
	public Boolean verificarParcelasEmAbertoPelosCanais(Integer numMatricula) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		
		final int tipoParcelamentoIntegral = 1;
		final int codModoLancViaConta = 2;
		
		try {
			comando = getComando("VERIFICARPARCELASEMABERTOPELOSCANAIS");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.adicionarVariavel("codTipoParcelamento", tipoParcelamentoIntegral);
			comando.adicionarVariavel("codModoLanc", codModoLancViaConta);
			comando.adicionarVariavel("codSituacaoParcela", ContaCapitalConstantes.COD_SITUACAO_EM_ABERTO);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if(rs.next()) {			
				return rs.getInt(1) > 0;
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_020",e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_020",e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		
		
		return Boolean.TRUE;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#excluirParcelasDevolucaoAbertoViaCaixa(java.lang.Integer)
	 */
	public void excluirParcelasDevolucaoAbertoViaCaixa(Integer numMatricula) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("EXCLUIR_PARC_DEVOL_CAIXA_CCA_ATIVA");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.configurar();
			conexao = estabelecerConexao();
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao excluir parc. de devolução abertas via caixa", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		
	}
	
	
	/**
	 * {@link ParcelamentoCCALegadoDao#pesquisarFechParcelasEmAbertoViaCCO(DateTimeDB)}.
	 *
	 * @param DateTimeDB
	 * @return Lista DadosFechBaixarParcelasLegadoCCODTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<DadosFechBaixarParcelasLegadoCCODTO> pesquisarFechParcelasEmAbertoViaCCO(DateTimeDB dataAtualProduto)  throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<DadosFechBaixarParcelasLegadoCCODTO> lst = new ArrayList<DadosFechBaixarParcelasLegadoCCODTO>();
		try {
			comando = getComando("CONSULTAPARCABERTASVIACCO");
			comando.adicionarVariavel("dataAtualProduto", DataUtil.converterDateToString(dataAtualProduto, "yyyyMMdd"));
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				DadosFechBaixarParcelasLegadoCCODTO dto = new DadosFechBaixarParcelasLegadoCCODTO();
				
				dto.setNumMatricula(rs.getInt("NumMatricula"));
				dto.setNumParcelamento(rs.getInt("NumParcelamento"));
				dto.setNumParcela(rs.getInt("NumParcela"));
				dto.setCodTipoParcelamento(rs.getInt("CodTipoParcelamento"));
				dto.setDataVencParcela(new DateTimeDB(rs.getDate("DataVencParcela").getTime()));
				dto.setValorParcela(rs.getBigDecimal("ValorParcela"));
				dto.setCodModoLanc(rs.getInt("CodModoLanc"));
				dto.setCodSituacaoParcela(rs.getInt("CodSituacaoParcela"));
				dto.setNumContaCorrente(rs.getLong("NumContaCorrente"));
				dto.setTipoHistoricoCCO(rs.getInt("TipoHistoricoCCO"));				
				dto.setDescNumDocumento(rs.getString("DescNumDocumento"));
				
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
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#alterarEmLote(java.util.List)
	 */
	public void alterarEmLote(List<ParcelamentoCCALegado> listParcelamentoCCALegado) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("ALTERARPARCELAMENTOLEGADOLOTE").getSql());
			for (ParcelamentoCCALegado parc : listParcelamentoCCALegado) {
				int i=0;
				ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = parc.getParcelamentoCCALegadoPK();
				ps.setInt(++i, parc.getCodSituacaoParcela());				
				ps.setDate(++i, new java.sql.Date(parc.getDataSituacaoParcela().getTime()));
				ps.setLong(++i, parc.getIdParcelamentoContaCapital());				
				ps.setInt(++i, parcelamentoCCALegadoPK.getContaCapitalLegado().getNumMatricula());
				ps.setInt(++i, parcelamentoCCALegadoPK.getNumParcelamento());
				ps.setInt(++i, parcelamentoCCALegadoPK.getNumParcela());
				ps.setInt(++i, parcelamentoCCALegadoPK.getCodTipoParcelamento());	
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
	}		
	
	/**
	 * {@link ParcelamentoCCALegadoDao#pesquisarFechParcelasDebIndet(DateTimeDB)}.
	 *
	 * @param DateTimeDB
	 * @return Lista DadosFechBaixarParcelasLegadoCCODTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<DadosFechBaixarParcelasLegadoCCODTO> pesquisarFechParcelasDebIndet(DateTimeDB dataAtualProduto)  throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<DadosFechBaixarParcelasLegadoCCODTO> lst = new ArrayList<DadosFechBaixarParcelasLegadoCCODTO>();
		try {
			comando = getComando("CONSULTADEBINDET");
//			comando.adicionarVariavel("dataAtualProduto", DataUtil.converterDateToString(dataAtualProduto, "yyyyMMdd"));
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				DadosFechBaixarParcelasLegadoCCODTO dto = new DadosFechBaixarParcelasLegadoCCODTO();
				
				dto.setNumMatricula(rs.getInt("NumMatricula"));
				dto.setNumParcelamento(rs.getInt("NumParcelamento"));
				dto.setNumParcela(rs.getInt("NumParcela"));
				dto.setCodTipoParcelamento(rs.getInt("CodTipoParcelamento"));
				dto.setDataVencParcela(new DateTimeDB(rs.getDate("DataParcela").getTime()));
				dto.setDataSituacaoParcela(new DateTimeDB(rs.getDate("DataSituacaoParcela").getTime()));
				dto.setValorParcela(rs.getBigDecimal("ValorParcela"));
				dto.setCodModoLanc(rs.getInt("CodModoLanc"));
				dto.setCodSituacaoParcela(rs.getInt("CodSituacaoParcela"));
				dto.setNumContaCorrente(rs.getLong("NumContaCorrente"));
				dto.setTipoHistoricoCCO(rs.getInt("TipoHistoricoCCO"));				
				dto.setDescNumDocumento(rs.getString("DescNumDocumento"));
				
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
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#incluirEmLote(java.util.List)
	 */
	public void incluirEmLote(List<ParcelamentoCCALegado> listParcelamentoCCALegado) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("INCLUIR_PARCELAMENTO_LOTE").getSql());
			for (ParcelamentoCCALegado parc : listParcelamentoCCALegado) {
				int i=0;
				ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = parc.getParcelamentoCCALegadoPK();
				ps.setInt(++i, parcelamentoCCALegadoPK.getContaCapitalLegado().getNumMatricula());
				ps.setInt(++i, parcelamentoCCALegadoPK.getNumParcelamento());
				ps.setInt(++i, parcelamentoCCALegadoPK.getNumParcela());
				ps.setInt(++i, parcelamentoCCALegadoPK.getCodTipoParcelamento());	
				ps.setDate(++i, new java.sql.Date(parc.getDataVencParcela().getTime()));
				ps.setDate(++i, new java.sql.Date(parc.getDataSituacaoParcela().getTime()));
				ps.setBigDecimal(++i, parc.getValorParcela());
				ps.setInt(++i, parc.getCodModoLanc());
				ps.setInt(++i, parc.getCodSituacaoParcela());				
				ps.setLong(++i, parc.getNumContaCorrente());
				if (parc.getDataEnvioCob() == null) {
					ps.setNull(++i, Types.DATE);
				} else {
					ps.setDate(++i, new java.sql.Date(parc.getDataEnvioCob().getTime()));					
				}				
				ps.setString(++i, parc.getuIDTrabalha());	
				if (parc.getCodMotivoDevolucao() == null) {
					ps.setNull(++i, Types.SMALLINT);
				} else {
					ps.setInt(++i, parc.getCodMotivoDevolucao());					
				}
				ps.setString(++i, parc.getDescObservacao());	
				ps.setLong(++i, parc.getIdParcelamentoContaCapital());				
				if (parc.getCodCanal() == null) {
					ps.setNull(++i, Types.SMALLINT);
				} else {
					ps.setInt(++i, parc.getCodCanal());				
				}
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
	}			
}
