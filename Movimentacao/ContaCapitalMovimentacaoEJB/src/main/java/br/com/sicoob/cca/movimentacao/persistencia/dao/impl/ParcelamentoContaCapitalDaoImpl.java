package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

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
import br.com.bancoob.persistencia.excecao.ViolacaoChavePrimariaException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoParcela;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParcelamentoContaCapitalDao;

/**
 * A Classe ParcelamentoContaCapitalDaoImpl.
 *
 * @author Antonio.Genaro
 */
public class ParcelamentoContaCapitalDaoImpl extends ContaCapitalMovimentacaoCrudDao<Parcelamento> implements ParcelamentoContaCapitalDao {
	
	/** A constante SQL_PARCELAS_EM_ABERTO. */
	private static final String SQL_PARCELAS_EM_ABERTO = "FROM br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento WHERE contaCapital.id = :idContaCapital AND situacaoParcelamento.id = :idSituacaoParcelamento";

	/**
	 * Instancia um novo ParcelamentoContaCapitalDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public ParcelamentoContaCapitalDaoImpl(Class<Parcelamento> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ParcelamentoRenDTO> pesquisarParcelamentos(Integer idContaCapital, Integer idTipoParcelamento) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<ParcelamentoRenDTO> lst = new ArrayList<ParcelamentoRenDTO>();
		ParcelamentoRenDTO dto = null;
		
		try {
			comando = getComando("PESQUISARPARCELAMENTOCONTACAPITAL");
			comando.adicionarVariavel("IDCONTACAPITAL", idContaCapital);
			if(!idTipoParcelamento.equals(ContaCapitalConstantes.NUM_ZERO)){
				comando.adicionarVariavel("IDTIPOPARCELAMENTO", idTipoParcelamento);				
			}
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				dto = new ParcelamentoRenDTO();
				dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				dto.setNumParcelamento(rs.getShort("NUMPARCELAMENTO"));
				dto.setDescTipoParcelamento(rs.getString("DESCTIPOPARCELAMENTO"));
				dto.setQtdParcelas(rs.getInt("QTDPARCELAS"));
				dto.setValorTotal(rs.getBigDecimal("VLRTOTAL"));
				dto.setValorAberto(rs.getBigDecimal("VLRTOTALABERTO"));
				dto.setIdTipoParcelamento(rs.getShort("IDTIPOPARCELAMENTO"));
				
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
	 * {@link ParcelamentoContaCapitalDao#pesquisarParcelasEmAberto(Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public List<Parcelamento> pesquisarParcelasEmAberto(Integer idContaCapital) throws BancoobException {
		return getEntityManager().createQuery(SQL_PARCELAS_EM_ABERTO)
				.setParameter("idContaCapital", idContaCapital)
				.setParameter("idSituacaoParcelamento", EnumSituacaoParcela.COD_ABERTO.getCodigo().shortValue())
				.getResultList();
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParcelamentoContaCapitalDao#pesquisarParcelamentosEmAbertoViaCaixa(br.com.sicoob.cca.movimentacao.negocio.dto.ParcelamentoCapitalDTO)
	 */
	public List<ParcelamentoRenDTO> pesquisarParcelamentosEmAbertoViaCaixa(ParcelamentoCapitalDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<ParcelamentoRenDTO> lst = new ArrayList<ParcelamentoRenDTO>();
		try {
			comando = getComando("CONSULTAPARCABERTASVIACAIXA");
			comando.adicionarVariavel("dto", dto);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				ParcelamentoRenDTO parcDto = new ParcelamentoRenDTO();
				parcDto.setIdParcelamento(rs.getLong("IDPARCELAMENTOCONTACAPITAL"));
				parcDto.setNumParcelamento(rs.getShort("NUMPARCELAMENTO"));
				parcDto.setNumParcela(rs.getShort("NUMPARCELA"));
				parcDto.setValorParcela(rs.getBigDecimal("VALORPARCELA"));
				parcDto.setDataVencimento(new DateTimeDB(rs.getDate("DATAVENCPARCELA").getTime()));
				parcDto.setIdSituacaoParcelamento(rs.getShort("IDSITUACAOPARCELAMENTO"));
				parcDto.setIdTipoInteg(rs.getShort("IDTIPOINTEGRALIZACAO"));
				parcDto.setIdTipoParcelamento(rs.getShort("IDTIPOPARCELAMENTO"));
				parcDto.setNumContaCapital(dto.getNumContaCapital());
				lst.add(parcDto); 
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
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.ParcelamentoContaCapitalDao#atualizarParcelamentos(java.util.List, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer)
	 */
	public void atualizarParcelamentos(List<Long> idsParcelamento, DateTimeDB dataSituacaoParcela, Integer idSituacaoParcelamento) throws BancoobException {
		Connection conexao = null;
		Comando comando = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("ATUALIZARPARCELAMENTOS");
			comando.adicionarVariavel("idSituacaoParcelamento", idSituacaoParcelamento);
			comando.adicionarVariavel("dataSituacaoParcela", ContaCapitalUtil.formatarDataUS(dataSituacaoParcela));
			comando.adicionarVariavel("idsParcelamento", ContaCapitalUtil.formatarListaValoresIN(idsParcelamento));
			comando.configurar();
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}	
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public Parcelamento incluir(Parcelamento objeto) throws BancoobException {
		try {
			return super.incluir(objeto);
		} catch (ViolacaoChavePrimariaException ve) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_PARCELAMENTO_REPLICACAO_EXECUCAO", ve);
		} catch (ViolacaoIntegridadeException ve) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_PARCELAMENTO_REPLICACAO_EXECUCAO", ve);
		} 
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#alterarEmLote(java.util.List)
	 */
	public void alterarEmLote(List<Parcelamento> listParcelamento) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("ALTERARPARCELAMENTOLOTE").getSql());
			for (Parcelamento parc : listParcelamento) {
				int i=0;
				ps.setInt(++i, parc.getSituacaoParcelamento().getId());				
				ps.setDate(++i, new java.sql.Date(parc.getDataSituacao().getTime()));
				ps.setInt(++i, parc.getContaCapital().getId());
				ps.setInt(++i, parc.getNumParcelamento());
				ps.setInt(++i, parc.getNumParcela());
				ps.setInt(++i, parc.getTipoParcelamento().getId());	
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
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ParcelamentoCCALegadoDao#incluirEmLote(java.util.List)
	 */
	public void incluirEmLote(List<Parcelamento> listParcelamento) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("INCLUIRPARCELAMENTOLOTE").getSql());
			for (Parcelamento parc : listParcelamento) {
				int i=0;
				ps.setInt(++i, parc.getContaCapital().getId());
				ps.setInt(++i, parc.getNumParcelamento());
				ps.setInt(++i, parc.getNumParcela());
				ps.setInt(++i, parc.getTipoParcelamento().getId());	
				if(parc.getMotivoDevolucao()== null) {
					ps.setNull(++i, Types.SMALLINT);
				}else {
					ps.setInt(++i, parc.getMotivoDevolucao().getId());						
				}
				ps.setInt(++i, parc.getSituacaoParcelamento().getId());				
				ps.setInt(++i, parc.getTipoIntegralizacao().getId());									
				ps.setDate(++i, new java.sql.Date(parc.getDataVencimento().getTime()));
				ps.setDate(++i, new java.sql.Date(parc.getDataSituacao().getTime()));				
				ps.setBigDecimal(++i, parc.getValor());
				ps.setLong(++i, parc.getNumContaCorrente());
				ps.setString(++i, parc.getMatriculaFuncionario());
				ps.setString(++i, parc.getObservacao());				
				if(parc.getCodCanal()== null) {
					ps.setNull(++i, Types.SMALLINT);
				}else {
					ps.setInt(++i, parc.getCodCanal());						
				}
				ps.setInt(++i, parc.getIdInstituicao());								
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
