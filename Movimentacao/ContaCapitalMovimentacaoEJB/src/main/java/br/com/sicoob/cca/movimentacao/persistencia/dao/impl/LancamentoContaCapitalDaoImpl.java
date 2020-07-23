package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.excecao.ViolacaoChavePrimariaException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoCCADTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.LancamentoEstornoRateioDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao;

/**
 * A Classe LancamentoContaCapitalDaoImpl.
 *
 * @author Antonio.Genaro
 */
public class LancamentoContaCapitalDaoImpl extends ContaCapitalMovimentacaoCrudDao<LancamentoContaCapital> implements LancamentoContaCapitalDao {
	
	/**
	 * Instancia um novo LancamentoContaCapitalDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public LancamentoContaCapitalDaoImpl(Class<LancamentoContaCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}

	/**
	 * {@link LancamentoContaCapitalDao#pesquisarLancamentosDoDiaPorContaCapital(Integer, Integer, Date)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @param dataProduto o valor de data produto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public List<LancamentoContaCapital> pesquisarLancamentosDoDiaPorContaCapital(Integer idContaCapital, Integer idInstituicao, Date dataProduto) throws BancoobException {
		
		Query query = getEntityManager().createQuery("FROM br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital WHERE " +
				" IDINSTITUICAO = :idInstituicao AND DATALANCAMENTO = :dataLancamento AND IDCONTACAPITAL = :idContaCapital AND BOLPROCESSADO = 0");
		
		query.setParameter("idContaCapital", idContaCapital);
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("dataLancamento", ContaCapitalUtil.formatarDataUS(dataProduto));
		
		return query.getResultList();
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao#pesquisarLancamentosDoDiaPorContaCapitalSimplificado(java.lang.Integer, java.lang.Integer, java.util.Date)
	 */
	public List<LancamentoCCADTO> pesquisarLancamentosDoDiaPorContaCapitalSimplificado(Integer idContaCapital, Integer idInstituicao, Date dataProduto) throws BancoobException {
		List<LancamentoCCADTO> dtos = new ArrayList<LancamentoCCADTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTARLANCAMENTOSDIACCA");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("data", ContaCapitalUtil.formatarDataUS(dataProduto));
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				int ix = 0;
				LancamentoCCADTO dto = new LancamentoCCADTO();
				dto.setIdLancamentoContaCapital(rs.getInt(++ix));
				dto.setIdTipoHistorico(rs.getInt(++ix));
				dto.setIdTipoLote(rs.getInt(++ix));
				dto.setIdContaCapital(rs.getInt(++ix));
				dto.setIdInstituicao(rs.getInt(++ix));
				dto.setValorLancamento(rs.getBigDecimal(++ix));
				dto.setNumSeqLanc(rs.getInt(++ix));
				dto.setCodNaturezaOperacao(rs.getString(++ix));
				dto.setIdGrupoHistorico(rs.getInt(++ix));
				dto.setIdTipoHistoricoEstorno(rs.getInt(++ix));
				dto.setCodNaturezaOperacaoEstorno(rs.getString(++ix));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}
	
	/**
	 * {@link LancamentoContaCapitalDao#pesquisarLancamentosDoDiaTipoHistContaCapital(Integer, Integer, Integer,Date)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @param idTipoHistorico o valor de id tipo historico
	 * @param dataProduto o valor de data produto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public List<LancamentoContaCapital> pesquisarLancamentosDoDiaTipoHistContaCapital(Integer idContaCapital, Integer idInstituicao, Integer idTipoHistorico, Date dataProduto) throws BancoobException {
		
		Query query = getEntityManager().createQuery("FROM br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital WHERE " +
				" IDINSTITUICAO = :idInstituicao AND DATALANCAMENTO = :dataLancamento AND IDCONTACAPITAL = :idContaCapital AND IDTIPOHISTORICO = :idTipoHistorico AND BOLPROCESSADO = 0");
		
		query.setParameter("idContaCapital", idContaCapital);
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("idTipoHistorico", idTipoHistorico);
		query.setParameter("dataLancamento", ContaCapitalUtil.formatarDataUS(dataProduto));
		
		return query.getResultList();
	}

	
	/**
	 * {@link LancamentoContaCapitalDao#pesquisarLancamentosPorContaCapital(Integer, Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public List<LancamentoContaCapital> pesquisarLancamentosPorContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException {
		
		Query query = getEntityManager().createQuery("FROM br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital WHERE " +
				" IDINSTITUICAO = :idInstituicao AND IDCONTACAPITAL = :idContaCapital");
		
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("idContaCapital", idContaCapital);
		
		return query.getResultList();
	}

	/**
	 * {@link LancamentoContaCapitalDao#pesquisarCountLancamentosPorContaCapitalSubscricao(Integer, Integer)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer pesquisarCountLancamentosPorContaCapitalSubscricao(Integer idContaCapital, Integer idInstituicao) throws BancoobException {
		
		Query query = getEntityManager().createNativeQuery("SELECT COUNT(*) FROM CCA.LANCAMENTOCONTACAPITAL WHERE IDINSTITUICAO = :idInstituicao AND IDCONTACAPITAL = :idContaCapital AND IDTIPOHISTORICO = 1");
		
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("idContaCapital", idContaCapital);		

		return (Integer) query.getSingleResult();
	}
	
	
	/**
	 * @see LancamentoContaCapitalDao#consultarLancamentoIntegralizacaoJaRealizada(IntegralizacaoCapitalDTO)
	 */
	public Boolean consultarLancamentoIntegralizacaoJaRealizada(IntegralizacaoCapitalDTO dto) throws BancoobException {

		Boolean lancamentoJaRealizado = Boolean.FALSE;
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTARLANCAMENTOINTEGRALIZACAOJAREALIZADA");
			comando.adicionarVariavel("IDINSTITUICAO", dto.getIdInstituicao());
			comando.adicionarVariavel("DATALANCAMENTO", ContaCapitalUtil.formatarDataUS(dto.getDataLancamento()));
			comando.adicionarVariavel("IDCONTACAPITAL", dto.getIdContaCapital());
			comando.adicionarVariavel("IDTIPOLOTE", dto.getNumLoteLanc());
			comando.adicionarVariavel("IDTIPOHISTORICO", dto.getIdTipoHistoricoLanc());
			comando.adicionarVariavel("DESCOPERACAOEXTERNA", dto.getIdOperacaoOrigem());
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				lancamentoJaRealizado = (rs.getInt("QTD") > 0);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lancamentoJaRealizado;		
		
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao#incluirEmLote(java.util.List)
	 */
	public List<LancamentoContaCapital> incluirEmLote(List<LancamentoContaCapital> lancamentos) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PreparedStatement psUltimoLancamento = null;
		PreparedStatement psHistorico = null;
		
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("INCLUIRLANCAMENTOLOTE").getSql());
			for (LancamentoContaCapital lanc : lancamentos) {
				int i=0;
				ps.setShort(++i, lanc.getTipoHistoricoCCA().getId());
				if (lanc.getTipoHistoricoEstorno() == null) {
					ps.setNull(++i, Types.SMALLINT);
				} else {
					ps.setShort(++i, lanc.getTipoHistoricoEstorno().getId());
				}
				ps.setShort(++i, lanc.getTipoLote().getId());
				ps.setInt(++i, lanc.getContaCapital().getId());
				ps.setInt(++i, lanc.getIdInstituicao());
				ps.setString(++i, lanc.getIdUsuario());
				ps.setDate(++i, new java.sql.Date(lanc.getDataLancamento().getTime()));
				ps.setString(++i, lanc.getDescNumDocumento());
				ps.setBigDecimal(++i, lanc.getValorLancamento());
				ps.setDate(++i, new java.sql.Date(lanc.getDataHoraAtualizacao().getTime()));
				ps.setShort(++i, lanc.getBolProcessado());
				ps.setInt(++i, lanc.getNumSeqLanc());
				ps.setString(++i, lanc.getDescOperacaoExterna());
				ps.addBatch();
			}
			int[] qtdInserts = ps.executeBatch();
			
			if(qtdInserts.length > 0) {
				psUltimoLancamento = conexao.prepareStatement(getComando("PESQUISAIDULTIMOLANCAMENTOLOTE").getSql());
				psUltimoLancamento.setInt(1, lancamentos.get(0).getTipoLote().getId());
				rs = psUltimoLancamento.executeQuery();
				rs.next();
				Integer ultimoIDLanc = rs.getInt(1);
				
				int qtdLancs = lancamentos.size();
				psHistorico = conexao.prepareStatement(getComando("INCLUIRHISTLANCAMENTOLOTE").getSql());
				for (int i = 0; i < qtdInserts.length; i++) {
					long id = ultimoIDLanc - i;
					lancamentos.get(qtdLancs - (i+1)).setId(id);
					psHistorico.setLong(1, id);
					psHistorico.addBatch();
				}
				
				psHistorico.executeBatch();
			}
			
		} catch (SQLException e) {
			int cont= 0;
			while(e!= null && cont <= 100) {
				this.getLogger().erro(e, e.getMessage());
				e = e.getNextException();
				cont++;
			}
			
			throw new BancoobException("MSG_INTEG_RATEIO_ERRO_BATCH_DB2");
		} finally {
			fecharStatement(ps);
			fecharStatement(psUltimoLancamento);
			fecharStatement(psHistorico);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return lancamentos;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao#consultarLancamentoEstornoRateio(br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital)
	 */
	public List<LancamentoEstornoRateioDTO> consultarLancamentoEstornoRateio(LancamentoContaCapital lancamentoChave) throws BancoobException {
		List<LancamentoEstornoRateioDTO> dtos = new ArrayList<LancamentoEstornoRateioDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTARESTORNORATEIO");
			comando.adicionarVariavel("lancamento", lancamentoChave);
			comando.adicionarVariavel("data", ContaCapitalUtil.formatarDataUS(lancamentoChave.getDataLancamento()));
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				int ix = 0;
				LancamentoEstornoRateioDTO dto = new LancamentoEstornoRateioDTO();
				dto.setIdContaCapital(rs.getInt(++ix));
				dto.setIdInstituicao(rs.getInt(++ix));
				dto.setIdSituacaoContaCapital(rs.getInt(++ix));
				dto.setNumContaCapital(rs.getInt(++ix));
				dto.setIdTipoHistorico(rs.getInt(++ix));
				dto.setValorLanc(rs.getBigDecimal(++ix));
				dto.setDescNumDocumento(rs.getString(++ix));
				dto.setDescOperacaoExterna(rs.getString(++ix));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao#verificarEstornoRealizado(br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital)
	 */
	public boolean verificarEstornoRealizado(LancamentoContaCapital lancamentoChave) throws BancoobException {
		boolean estornoRealizado = false;
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("VERIFICARESTORNOREALIZADO");
			comando.adicionarVariavel("lancamento", lancamentoChave);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				estornoRealizado = (rs.getInt("QTD") > 0);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return estornoRealizado;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao#validarCCAsSemSaldoParaEstornoRateio(br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital)
	 */
	public Map<String, List<Integer>> validarCCAsSemSaldoParaEstornoRateio(LancamentoContaCapital lancamentoChave) throws BancoobException {
		Map<String, List<Integer>> ccasPorGrupo = new HashMap<String, List<Integer>>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("VALIDARCCASEMSALDOESTORNO");
			comando.adicionarVariavel("lancamento", lancamentoChave);
			comando.adicionarVariavel("data", ContaCapitalUtil.formatarDataUS(lancamentoChave.getDataLancamento()));
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				String grupoHistorico = rs.getString("GRUPOHISTORICO");
				List<Integer> ccas = ccasPorGrupo.get(grupoHistorico);
				if (ccas == null) {
					ccas = new ArrayList<Integer>();
				}
				ccas.add(rs.getInt("NUMCONTACAPITAL"));
				ccasPorGrupo.put(grupoHistorico, ccas);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return ccasPorGrupo;
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public LancamentoContaCapital incluir(LancamentoContaCapital objeto) throws BancoobException {
		try {
			return super.incluir(objeto);
		} catch (ViolacaoChavePrimariaException ve) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_LANCAMENTO_REPLICACAO_EXECUCAO", ve);
		} catch (ViolacaoIntegridadeException ve) {
			throw new ContaCapitalMovimentacaoNegocioException("MSG_LANCAMENTO_REPLICACAO_EXECUCAO", ve);
		} 
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao##atualizarMovimentoLancamentos(java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB)
	 */	
	public void atualizarMovimentoLancamentos(Integer idInstituicao, DateTimeDB dataAtualProduto) throws BancoobException {
		Connection conexao = null;
		Comando comando = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("ATUALIZARMOVIMENTOLANCAMENTOS");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("dataAtualProduto", ContaCapitalUtil.formatarDataUS(dataAtualProduto));
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
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.LancamentoContaCapitalDao##atualizarLancamentosMovimento(java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB)
	 */	
	public void atualizarLancamentosMovimento(Integer idInstituicao, DateTimeDB dataAtualProduto) throws BancoobException {
		Connection conexao = null;
		Comando comando = null;
		try {
			conexao = estabelecerConexao();
			comando = getComando("ATUALIZARLANCAMENTOSMOVIMENTO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("dataAtualProduto", ContaCapitalUtil.formatarDataUS(dataAtualProduto));
			comando.configurar();
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}		
	
}