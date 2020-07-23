package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.sisbr.cca.legado.negocio.constantes.ReplicacaoLegadoConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BatimentoSaldoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConfiguracaoConciliacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConsultaMonitoracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ExpurgoReplicacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorCooperativaReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoContaCapitalDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoLancamentoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoParcelamentoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalLegadoNegocioException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoAtualizaChaveDb2LegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoAtualizaListaReplicacaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoConsultaLegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoConsultaListaReplicacaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoMonitoracaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoMonitoracaoReplicacaoNegocioException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao;
import br.com.sicoob.tipos.DateTime;

/**
 * DAO para replicacao legado.
 */
public class ReplicacaoContaCapitalLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements ReplicacaoContaCapitalLegadoDao {

	//constantes para representar as colunas do banco de dados???????
	private static final String DATAHORAREPLICACAO = "dataHoraReplicacao";
	private static final String NUMMATRICULA = "numMatricula";
	private static final String NUMPARCELAMENTO = "numParcelamento";
	private static final String NUMPARCELA = "numParcela";
	private static final String CODTIPOPARCELAMENTO = "codTipoParcelamento";
	private static final String CODMOTIVODEVOLUCAO = "codMotivoDevolucao";
	private static final String DATALOTE = "dataLote";
	private static final String NUMLOTELANC = "numLoteLanc";
	private static final String NUMSEQLANC = "numSeqLanc";
	
	//Mensagem MSG_ERRO_REGISTRO_NAO_ATUALIZADO
	private static final String MSG_ERRO_REGISTRO_NAO_ATUALIZADO = "MSG_ERRO_REGISTRO_NAO_ATUALIZADO";
	
	//Variaveis para log do erro
	private String sqlExecucao = "";
	private String identificacao= "";	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConfiguracaoReplicacao(Integer)
	 */		
	public String consultarConfiguracaoReplicacao(Integer obj) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		String saida = null;
		
		try {	
			comando = getComando("OBTERCONFIGURACAOREPLICACAOCCA");
			comando.adicionarVariavel("idConfiguracaoReplicacaoCCA", obj);			
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){				
				saida = rs.getString("DescConfiguracaoReplicacao");
			}		
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return saida;		
		
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarListaConfiguracaoReplicacao()
	 */		
	public List<ReplicacaoConfiguracaoLegadoDTO> consultarListaConfiguracaoReplicacao() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<ReplicacaoConfiguracaoLegadoDTO> listaRetorno = new ArrayList<ReplicacaoConfiguracaoLegadoDTO>();		
		try {
			comando = getComando("OBTERLISTACONFIGURACAOREPLICACAOCCA");
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()){
				ReplicacaoConfiguracaoLegadoDTO dto = new ReplicacaoConfiguracaoLegadoDTO();				
				dto.setIdConfiguracaoReplicacaoCCA(rs.getInt("idConfiguracaoReplicacaoCCA"));
				dto.setNomeConfiguracaoReplicacao(rs.getString("nomeConfiguracaoReplicacao"));
				dto.setDescConfiguracaoReplicacao(rs.getString("descConfiguracaoReplicacao"));				
				listaRetorno.add(dto);
			}	
			
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;		
		
	}	
		
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarTabelaReplicacao(Integer, List)
	 */	
	public List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaReplicacao(Integer quantidade,List<String> lstCooperativas) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<ReplicacaoContaCapitalLegadoDTO> listaRetorno = new ArrayList<ReplicacaoContaCapitalLegadoDTO>();
		
		try {
			comando = getComando("OBTERDADOSREPLICACAOCCAPORCOOP");
			comando.adicionarVariavel("quantidadeRegistros", quantidade);	
			comando.adicionarVariavel("cooperativas", ContaCapitalUtil.formatarListaValoresIN(lstCooperativas).toString());			
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()){				

				ReplicacaoContaCapitalLegadoDTO dto = new ReplicacaoContaCapitalLegadoDTO();
				
				dto.setIdReplicacaoCCA(rs.getLong("idReplicacaoCCA"));
				dto.setIdTabelaReplicadaCCA(rs.getInt("IdTabelaReplicadaCCA"));
				dto.setIdSituacaoReplicacaoCCA(rs.getInt("idSituacaoReplicacaoCCA"));
				dto.setCodAcao(rs.getString("codAcao"));
				dto.setDescChaveReplicacaoSQL(rs.getString("descchaveReplicacaoSQL"));
				if (rs.getString("descchaveReplicacaoDB2")!= null){
					dto.setDescChaveReplicacaoDB2(rs.getString("descchaveReplicacaoDB2"));
				}
				if (rs.getDate(DATAHORAREPLICACAO)!= null){
					dto.setDataHoraReplicacao(new DateTime(rs.getDate(DATAHORAREPLICACAO).getTime()));
				}
				dto.setNumCooperativa(rs.getInt("numCooperativa"));
				dto.setIdInstituicao(rs.getInt("idInstituicao"));
				
				listaRetorno.add(dto);
			}			
		} catch (SQLException e) {
			throw new ReplicacaoConsultaListaReplicacaoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaListaReplicacaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;		
		
	}		
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarTabelaReplicacao()
	 */	
	public List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaReplicacao(Integer quantidade) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<ReplicacaoContaCapitalLegadoDTO> listaRetorno = new ArrayList<ReplicacaoContaCapitalLegadoDTO>();
		
		try {
			comando = getComando("OBTERDADOSREPLICACAOCCA");
			comando.adicionarVariavel("quantidadeRegistros", quantidade);			
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()){				

				ReplicacaoContaCapitalLegadoDTO dto = new ReplicacaoContaCapitalLegadoDTO();
				
				dto.setIdReplicacaoCCA(rs.getLong("idReplicacaoCCA"));
				dto.setIdTabelaReplicadaCCA(rs.getInt("IdTabelaReplicadaCCA"));
				dto.setIdSituacaoReplicacaoCCA(rs.getInt("idSituacaoReplicacaoCCA"));
				dto.setCodAcao(rs.getString("codAcao"));
				dto.setDescChaveReplicacaoSQL(rs.getString("descchaveReplicacaoSQL"));
				dto.setDescChaveReplicacaoDB2(rs.getString("descchaveReplicacaoDB2"));
				if (rs.getDate(DATAHORAREPLICACAO)!= null){
					dto.setDataHoraReplicacao(new DateTime(rs.getDate(DATAHORAREPLICACAO).getTime()));
				}
				dto.setNumCooperativa(rs.getInt("numCooperativa"));
				dto.setIdInstituicao(rs.getInt("idInstituicao"));
				
				listaRetorno.add(dto);
			}			
		} catch (SQLException e) {
			throw new ReplicacaoConsultaListaReplicacaoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaListaReplicacaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;		
		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO)()
	 */		
	public ReplicacaoTabelaContaCapitalLegadoDTO consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		ReplicacaoTabelaContaCapitalLegadoDTO dto = null;
		
		try {
			comando = getComando("OBTERDADOSTABELACONTACAPITALREPLICACAO");
			comando.adicionarVariavel(NUMMATRICULA, obj.getDescChaveReplicacaoSQL());
			comando.configurar();

			conexao = estabelecerConexao(obj.getNumCooperativa());
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){				
				dto = new ReplicacaoTabelaContaCapitalLegadoDTO();
				
				dto.setNumCooperativa(obj.getNumCooperativa());
				dto.setIdInstituicao(obj.getIdInstituicao());

				if (rs.getObject("idContaCapital") != null){				
					dto.setIdContaCapital(rs.getInt("idContaCapital"));
				}
				
				if (rs.getDate("dataMatricula")!= null){
					dto.setDataMatricula(new DateTime(rs.getDate("dataMatricula").getTime()));
				}
				
				if (rs.getDate("dataSaida")!= null){
					dto.setDataSaida(new DateTime(rs.getDate("dataSaida").getTime()));
				}

				dto.setNumMatricula(rs.getInt(NUMMATRICULA));
				dto.setNumCliente(rs.getInt("numCliente"));
				dto.setCodSituacao(rs.getInt("codSituacao"));				
				dto.setValorSaldoAtuSubsc(rs.getBigDecimal("valorSaldoAtuSubsc"));				
				dto.setValorSaldoAtuInteg(rs.getBigDecimal("valorSaldoAtuInteg"));
				dto.setValorSaldoAtuDevolver(rs.getBigDecimal("valorSaldoAtuDevolver"));
				dto.setValorSaldoBloqInt(rs.getBigDecimal("valorSaldoBloqInt"));
				
			}			
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return dto;			

	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 */		
	public ReplicacaoTabelaParcelamentoCCALegadoDTO consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		ReplicacaoTabelaParcelamentoCCALegadoDTO dto = null;
		
		try {
			comando = getComando("OBTERDADOSTABELAPARCELAMENTOCCAREPLICACAO");
			Map<String, String> mapaChave = desmontarChaveSql(obj);			
			comando.adicionarVariavel(NUMMATRICULA, mapaChave.get(NUMMATRICULA));	
			comando.adicionarVariavel(NUMPARCELAMENTO, mapaChave.get(NUMPARCELAMENTO));	
			comando.adicionarVariavel(NUMPARCELA, mapaChave.get(NUMPARCELA));	
			comando.adicionarVariavel(CODTIPOPARCELAMENTO, mapaChave.get(CODTIPOPARCELAMENTO));			
			comando.configurar();

			conexao = estabelecerConexao(obj.getNumCooperativa());
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){				
				
				dto = new ReplicacaoTabelaParcelamentoCCALegadoDTO();
				
				dto.setNumCooperativa(obj.getNumCooperativa());
				dto.setIdInstituicao(obj.getIdInstituicao());
				
				if (rs.getObject("idParcelamentoContaCapital") != null){				
					dto.setIdParcelamentoContaCapital(rs.getLong("idParcelamentoContaCapital"));
				}
				if (rs.getObject(CODMOTIVODEVOLUCAO) != null){				
					dto.setCodMotivoDevolucao(rs.getInt(CODMOTIVODEVOLUCAO));
				}
				if (rs.getObject("numContaCorrente") != null){				
					dto.setNumContaCorrente(rs.getLong("numContaCorrente"));
				}
				if (rs.getDate("dataEnvioCob")!= null){
					dto.setDataEnvioCob(new DateTime(rs.getDate("dataEnvioCob").getTime()));
				}					
				dto.setCodModoLanc(rs.getInt("codModoLanc"));
				dto.setCodSituacaoParcela(rs.getInt("codSituacaoParcela"));
				dto.setCodTipoParcelamento(rs.getInt(CODTIPOPARCELAMENTO));
				dto.setDataSituacaoParcela(new DateTime(rs.getDate("dataSituacaoParcela").getTime()));
				dto.setDataVencParcela(new DateTime(rs.getDate("dataVencParcela").getTime()));
				dto.setDescObservacao(rs.getString("descObservacao"));
				dto.setNumMatricula(rs.getInt(NUMMATRICULA));
				dto.setNumParcela(rs.getInt(NUMPARCELA));
				dto.setNumParcelamento(rs.getInt(NUMPARCELAMENTO));
				dto.setuIDTrabalha(rs.getString("uIDTrabalha"));
				dto.setValorParcela(rs.getBigDecimal("valorParcela"));
			}			
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		}catch (BancoobException e) {
			throw new ReplicacaoConsultaLegadoException(e);			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return dto;			

	}

	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarTabelaLancamentosCCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 */		
	public ReplicacaoTabelaLancamentosCCapitalLegadoDTO consultarTabelaLancamentosCCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		ReplicacaoTabelaLancamentosCCapitalLegadoDTO dto = null;
		
		try {
			
			comando = getComando("OBTERDADOSTABELALANCAMENTOSCCAPITALREPLICACAO");
			Map<String, String> mapaChave = desmontarChaveSql(obj);			
			comando.adicionarVariavel(DATALOTE, mapaChave.get(DATALOTE));	
			comando.adicionarVariavel(NUMLOTELANC, mapaChave.get(NUMLOTELANC));	
			comando.adicionarVariavel(NUMSEQLANC, mapaChave.get(NUMSEQLANC));	
			comando.configurar();

			conexao = estabelecerConexao(obj.getNumCooperativa());
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){				
				dto = new ReplicacaoTabelaLancamentosCCapitalLegadoDTO();	
				
				dto.setNumCooperativa(obj.getNumCooperativa());				
				dto.setIdInstituicao(obj.getIdInstituicao());				
				
				if (rs.getObject("idLancamentoContaCapital") != null){				
					dto.setIdLancamentoContaCapital(rs.getLong("idLancamentoContaCapital"));
				}
				if (rs.getObject(CODMOTIVODEVOLUCAO) != null){				
					dto.setCodMotivoDevolucao(rs.getInt(CODMOTIVODEVOLUCAO));
				}				
				if (rs.getObject("idProdutoEst") != null){				
					dto.setIdProdutoEst(rs.getInt("idProdutoEst"));
				}				
				if (rs.getObject("idTipoHistoricoEstorno") != null){				
					dto.setIdTipoHistoricoEstorno(rs.getInt("idTipoHistoricoEstorno"));
				}				
				if (rs.getDate("dataHoraInclusao")!= null){
					dto.setDataHoraInclusao(new DateTime(rs.getTimestamp("dataHoraInclusao").getTime()));
				}					
				
				dto.setBolAtualizado(rs.getBoolean("bolAtualizado"));
				dto.setDataLote(new DateTime(rs.getDate(DATALOTE).getTime()));
				dto.setDescNumDocumento(rs.getString("descNumDocumento"));
				dto.setDescObsDevolucao(rs.getString("descObsDevolucao"));
				dto.setIdProduto(rs.getInt("idProduto"));
				dto.setIdTipoHistoricoLanc(rs.getInt("idTipoHistoricoLanc"));
				dto.setIdUsuarioResp(rs.getString("idUsuarioResp"));
				dto.setNumLoteLanc(rs.getInt(NUMLOTELANC));
				dto.setNumMatricula(rs.getInt(NUMMATRICULA));
				dto.setNumSeqLanc(rs.getInt(NUMSEQLANC));
				dto.setValorLanc(rs.getBigDecimal("valorLanc"));		
			}			
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		}catch (BancoobException e) {
			throw new ReplicacaoConsultaLegadoException(e);					
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return dto;				
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#alterarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 */		
	public void alterarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ATUALIZARDADOSREPLICACAOCCA"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("objDadosReplicacao", obj);
			comando.configurar();			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoLegadoConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoAtualizaListaReplicacaoException(MSG_ERRO_REGISTRO_NAO_ATUALIZADO,sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoAtualizaListaReplicacaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#alterarTabelaContaCapitalReplicacao(Integer)
	 */			
	public void alterarTabelaContaCapitalReplicacao(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ATUALIZARCHAVECONTACAPITALREPLICACAOCCA"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("objDadosContaCapital", obj);
			comando.configurar();			
			conexao = estabelecerConexao(obj.getNumCooperativa());
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoLegadoConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoAtualizaChaveDb2LegadoException(MSG_ERRO_REGISTRO_NAO_ATUALIZADO, sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoAtualizaChaveDb2LegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#alterarTabelaParcelamentoCCAReplicacao(Long)
	 */	
	public void alterarTabelaParcelamentoCCAReplicacao(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ATUALIZARCHAVEPARCELAMENTOCCAREPLICACAOCCA"; 			
			comando = getComando(identificacao);
			comando.adicionarVariavel("objDadosParcelamento", obj);
			comando.configurar();			
			conexao = estabelecerConexao(obj.getNumCooperativa());
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoLegadoConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoAtualizaChaveDb2LegadoException(MSG_ERRO_REGISTRO_NAO_ATUALIZADO, sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoAtualizaChaveDb2LegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#alterarTabelaLancamentosCCapitalReplicacao(Long)
	 */	
	public void alterarTabelaLancamentosCCapitalReplicacao(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ATUALIZARCHAVELANCAMENTOSCCAPITALREPLICACAOCCA"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("objDadosLancamento", obj);
			comando.configurar();			
			conexao = estabelecerConexao(obj.getNumCooperativa());
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoLegadoConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoAtualizaChaveDb2LegadoException(MSG_ERRO_REGISTRO_NAO_ATUALIZADO,sqlExecucao);
			}		
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoAtualizaChaveDb2LegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}	
	
	/**
	 * Desmonta a chave string recebida na tabela de replicacao
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	private Map<String, String> desmontarChaveSql(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException{
		
		Map<String, String> mapaChave = new HashMap<String, String>();
		String[] chaveSql = obj.getDescChaveReplicacaoSQL().split("\\|");
		int pos = ReplicacaoLegadoConstantes.POS_INI_CHAVESQL;
		
		if(obj.getIdTabelaReplicadaCCA().equals(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_CONTACAPITAL)){
			mapaChave.put(NUMMATRICULA, chaveSql[pos]);	
		}else if(obj.getIdTabelaReplicadaCCA().equals(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_PARCELAMENTOCCA)){
			mapaChave.put(NUMMATRICULA, chaveSql[pos++]);	
			mapaChave.put(NUMPARCELAMENTO, chaveSql[pos++]);	
			mapaChave.put(NUMPARCELA, chaveSql[pos++]);	
			mapaChave.put(CODTIPOPARCELAMENTO, chaveSql[pos]);	
		}else if(obj.getIdTabelaReplicadaCCA().equals(ReplicacaoLegadoConstantes.IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL)){
			mapaChave.put(DATALOTE, chaveSql[pos++]);	
			mapaChave.put(NUMLOTELANC, chaveSql[pos++]);	
			mapaChave.put(NUMSEQLANC, chaveSql[pos]);	
		}
		return mapaChave;
	}

	/**
	 * Monta string com comando sql + parametros
	 * @param comando
	 * @return
	 * @throws BancoobException
	 */
	private String montarStringSqlParametros(String identificacao,Comando comando) throws BancoobException{
		String saida = identificacao;
		if (comando != null && comando.getParametros() != null){
			saida = saida + ". Parametros" + comando.getParametros().toString();
		}
		return saida;
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarTabelaMonitoracaoReplicacao()
	 */		
	public List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaMonitoracaoReplicacao()throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<ReplicacaoContaCapitalLegadoDTO> listaRetorno = new ArrayList<ReplicacaoContaCapitalLegadoDTO>();
		
		try {
			comando = getComando("OBTERDADOSMONITORACAOREPLICACAOCCA");
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()){				

				ReplicacaoContaCapitalLegadoDTO dto = new ReplicacaoContaCapitalLegadoDTO();

				dto.setNumCooperativa(rs.getInt("numCooperativa"));				
				dto.setIdTabelaReplicadaCCA(rs.getInt("IdTabelaReplicadaCCA"));
				dto.setIdSituacaoReplicacaoCCA(rs.getInt("idSituacaoReplicacaoCCA"));
				dto.setCountMonitoracao(rs.getLong("Total"));
				listaRetorno.add(dto);
			}			
		} catch (SQLException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO, List)
	 */		
	public List<MonitorReplicacaoCapitalLegadoDTO> consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO consultaDTO, List<Integer> cooperativasPiloto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<MonitorReplicacaoCapitalLegadoDTO> listaRetorno = new ArrayList<MonitorReplicacaoCapitalLegadoDTO>();
		
		try {
			
			comando = getComando("OBTERDADOSMONITORCCA");
			comando.adicionarVariavel("numCoop", consultaDTO.getNumCooperativa());
			comando.adicionarVariavel("cooperativas", ContaCapitalUtil.formatarListaValoresIN(cooperativasPiloto)); 
			comando.adicionarVariavel("situacao", consultaDTO.getSituacao());
			if (consultaDTO.getData() != null) {
				comando.adicionarVariavel("data", DataUtil.converterDateToString(consultaDTO.getData(), "yyyyMMdd"));
			}
			if (consultaDTO.getPeriodoDe() != null && consultaDTO.getPeriodoAte() != null) {
				comando.adicionarVariavel("periodoDe", DataUtil.converterDateToString(consultaDTO.getPeriodoDe(), "yyyyMMdd"));
				comando.adicionarVariavel("periodoAte", DataUtil.converterDateToString(consultaDTO.getPeriodoAte(), "yyyyMMdd"));
			}
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {				

				MonitorReplicacaoCapitalLegadoDTO dto = new MonitorReplicacaoCapitalLegadoDTO();

				dto.setTabela(rs.getString("NomeTabelaReplicada"));
				dto.setQtdAguardando(rs.getInt("QTD_AGUAR"));				
				dto.setQtdInvalido(rs.getInt("QTD_INVAL"));
				dto.setQtdReplicado(rs.getInt("QTD_REPL"));
				dto.setQtdErro(rs.getInt("QTD_ERRO"));
				listaRetorno.add(dto);
			}			
			
		} catch (SQLException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarMonitoracaoCooperativasReplicacao(br.com.sicoob.sisbr.cca.legado.negocio.dto.ConsultaMonitoracaoDTO, java.util.List)
	 */
	public List<MonitorCooperativaReplicacaoCapitalLegadoDTO> consultarMonitoracaoCooperativasReplicacao(ConsultaMonitoracaoDTO consultaDTO, List<Integer> cooperativasPiloto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<MonitorCooperativaReplicacaoCapitalLegadoDTO> listaRetorno = new ArrayList<MonitorCooperativaReplicacaoCapitalLegadoDTO>();
		
		try {
			
			comando = getComando("OBTERDADOSMONITORCOOPERATIVASCCA");
			comando.adicionarVariavel("numCoop", consultaDTO.getNumCooperativa());
			comando.adicionarVariavel("cooperativas", ContaCapitalUtil.formatarListaValoresIN(cooperativasPiloto));
			comando.adicionarVariavel("situacao", consultaDTO.getSituacao());
			if (consultaDTO.getData() != null) {
				comando.adicionarVariavel("data", DataUtil.converterDateToString(consultaDTO.getData(), "yyyyMMdd"));
			}
			if (consultaDTO.getPeriodoDe() != null && consultaDTO.getPeriodoAte() != null) {
				comando.adicionarVariavel("periodoDe", DataUtil.converterDateToString(consultaDTO.getPeriodoDe(), "yyyyMMdd"));
				comando.adicionarVariavel("periodoAte", DataUtil.converterDateToString(consultaDTO.getPeriodoAte(), "yyyyMMdd"));
			}
			comando.configurar();
			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {				
				
				MonitorCooperativaReplicacaoCapitalLegadoDTO dto = new MonitorCooperativaReplicacaoCapitalLegadoDTO();
				
				dto.setNumCooperativa(rs.getInt("NumCooperativa"));
				dto.setQtdAguardandoCCA(rs.getInt("QTD_AGUAR_CCA"));				
				dto.setQtdInvalidoCCA(rs.getInt("QTD_INVAL_CCA"));
				dto.setQtdReplicadoCCA(rs.getInt("QTD_REPL_CCA"));
				dto.setQtdErroCCA(rs.getInt("QTD_ERRO_CCA"));
				dto.setQtdAguardandoParc(rs.getInt("QTD_AGUAR_PARC"));				
				dto.setQtdInvalidoParc(rs.getInt("QTD_INVAL_PARC"));
				dto.setQtdReplicadoParc(rs.getInt("QTD_REPL_PARC"));
				dto.setQtdErroParc(rs.getInt("QTD_ERRO_PARC"));
				dto.setQtdAguardandoLanc(rs.getInt("QTD_AGUAR_LANC"));				
				dto.setQtdInvalidoLanc(rs.getInt("QTD_INVAL_LANC"));
				dto.setQtdReplicadoLanc(rs.getInt("QTD_REPL_LANC"));
				dto.setQtdErroLanc(rs.getInt("QTD_ERRO_LANC"));
				listaRetorno.add(dto);
			}			
			
		} catch (SQLException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarErrosReplicacao(Integer, List)
	 */		
	public List<ReplicacaoContaCapitalLegadoDTO> consultarErrosReplicacao(ConsultaMonitoracaoDTO consultaDTO, List<Integer> cooperativasPiloto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<ReplicacaoContaCapitalLegadoDTO> listaRetorno = new ArrayList<ReplicacaoContaCapitalLegadoDTO>();
		
		try {
			
			comando = getComando("OBTERERROSMONITORCCA");
			comando.adicionarVariavel("obj", consultaDTO);
			comando.adicionarVariavel("cooperativas", ContaCapitalUtil.formatarListaValoresIN(cooperativasPiloto));
			if (consultaDTO.getData() != null) {
				comando.adicionarVariavel("data", DataUtil.converterDateToString(consultaDTO.getData(), "yyyyMMdd"));
			}
			if (consultaDTO.getPeriodoDe() != null && consultaDTO.getPeriodoAte() != null) {
				comando.adicionarVariavel("periodoDe", DataUtil.converterDateToString(consultaDTO.getPeriodoDe(), "yyyyMMdd"));
				comando.adicionarVariavel("periodoAte", DataUtil.converterDateToString(consultaDTO.getPeriodoAte(), "yyyyMMdd"));
			}
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {				
				ReplicacaoContaCapitalLegadoDTO dto = new ReplicacaoContaCapitalLegadoDTO();

				dto.setIdReplicacaoCCA(rs.getLong("IdReplicacaoCCA"));
				dto.setIdTabelaReplicadaCCA(rs.getInt("IdTabelaReplicadaCCA"));
				dto.setIdSituacaoReplicacaoCCA(rs.getInt("IdSituacaoReplicacaoCCA"));
				dto.setCodAcao(rs.getString("CodAcao"));
				dto.setDescChaveReplicacaoSQL(rs.getString("DescChaveReplicacaoSQL"));
				dto.setDataHoraCadastro(new DateTimeDB(rs.getTimestamp("DataHoraCadastro").getTime()));
				dto.setDataHoraReplicacao(new DateTimeDB(rs.getTimestamp("DataHoraReplicacao").getTime()));
				dto.setNumCooperativa(rs.getInt("NumCooperativa"));
				dto.setIdInstituicao(rs.getInt("IdInstituicao"));
				dto.setDescMensagemReplicacao(rs.getString("DescMensagemReplicacao"));
				
				if(rs.getString("DescChaveReplicacaoDB2") != null) {
					dto.setDescChaveReplicacaoDB2(rs.getString("DescChaveReplicacaoDB2"));
				}
				
				listaRetorno.add(dto);
			}			
			
		} catch (SQLException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#liberarBloquearExecucao(Integer)
	 */	
	public void liberarBloquearExecucao(Integer operacao)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "LIBERARBLOQUEAREXECUCAO"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("operacao", operacao);
			comando.configurar();			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoLegadoConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoMonitoracaoReplicacaoNegocioException(MSG_ERRO_REGISTRO_NAO_ATUALIZADO,sqlExecucao);
			}		
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#alterarConfiguracaoReplicacaoCCA(ReplicacaoConfiguracaoLegadoDTO)
	 */		
	public void alterarConfiguracaoReplicacaoCCA(ReplicacaoConfiguracaoLegadoDTO obj) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ALTERARCONFIGURACAOREPLICACAOCCA"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("objReplicacaoConfiguracaoLegadoDTO", obj);
			comando.configurar();			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoLegadoConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoMonitoracaoReplicacaoNegocioException(MSG_ERRO_REGISTRO_NAO_ATUALIZADO,sqlExecucao);
			}		
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#reprocessar(List, String, String)
	 */
	public void reprocessar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			List<Long> ids = new ArrayList<Long>();
			for(ReplicacaoContaCapitalLegadoDTO dto : lst) {
				if(dto.getIdReplicacaoCCA() != null) {
					ids.add(dto.getIdReplicacaoCCA());
				}
			}
			
			if(!ids.isEmpty()) {
				gerarLogReplicacao(ids, justificativa, idUsuario);
				
				identificacao = "REPROCESSARREPLICACAO"; 				
				comando = getComando(identificacao);
				comando.adicionarVariavel("ids", ids);
				comando.configurar();
				conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
				comando.executarAtualizacao(conexao);
			}
			
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw new ReplicacaoMonitoracaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		
	}
	
	private void gerarLogReplicacao(List<Long> ids, String justificativa, String idUsuario) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			identificacao = "LOGREPLICACAOCCA"; 	
			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			
			for(Long id : ids) {
				comando = getComando(identificacao);
				comando.adicionarVariavel("DescJustificativa", justificativa);
				comando.adicionarVariavel("IDUsuarioResp", idUsuario);
				comando.adicionarVariavel("IdReplicacaoCCA", id);
				comando.configurar();
				comando.executarAtualizacao(conexao);
			}
			
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw new ReplicacaoMonitoracaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#invalidar(List, String, String)
	 */
	public void invalidar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			
			List<Long> ids = new ArrayList<Long>();
			for(ReplicacaoContaCapitalLegadoDTO dto : lst) {
				if(dto.getIdReplicacaoCCA() != null) {
					ids.add(dto.getIdReplicacaoCCA());
				}
			}
			
			if (!ids.isEmpty()) {
				gerarLogReplicacao(ids, justificativa, idUsuario);
				
				identificacao = "INVALIDARREPLICACAO"; 				
				comando = getComando(identificacao);
				comando.adicionarVariavel("ids", ids);
				comando.configurar();
				conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
				comando.executarAtualizacao(conexao);
			}
			
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());
			throw new ReplicacaoMonitoracaoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}	
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#expurgarReplicacao(Integer, Integer)
	 */	
	public void expurgarReplicacao(Integer idSituacaoReplicacaoCCA, Integer numCooperativa) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "EXPURGARREPLICACAO"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("idSituacaoReplicacaoCCA", idSituacaoReplicacaoCCA);
			comando.adicionarVariavel("numCooperativa", numCooperativa);
			comando.configurar();			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarQuantRegReplicacao()
	 */	
	public Long consultarQuantRegReplicacao() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Long saida = Long.valueOf(0);
		
		try {	
			comando = getComando("CONSULTARQUANTREGREPLICACAOCCA");
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){				
				saida = rs.getLong("qtdTotal");
			}		
			
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return saida;		
	}		
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#expurgarReplicacaoSucesso()
	 */	
	public void expurgarReplicacaoSucesso(Integer qdtDiasNaoExpurgar) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "EXPURGARREPLICACAO"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("idSituacaoReplicacaoCCA", ReplicacaoLegadoConstantes.SITUACAO_REPLICACAO_COM_SUCESSO);			
			comando.adicionarVariavel("dataExpurgo", DataUtil.converterDateToString(new org.joda.time.DateTime().minusDays(qdtDiasNaoExpurgar).toDate(), "yyyyMMdd"));
			comando.configurar();			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#prepararCooperativaPiloto(Integer)
	 */
	public void prepararCooperativaPiloto(Integer cooperativa) throws BancoobException {
		prepararCooperativaReplicacao(cooperativa);
		prepararCooperativaIds(cooperativa);
	}
	
	private void prepararCooperativaReplicacao(Integer cooperativa) throws BancoobException { 
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("PREPARARCOOPERATIVAPILOTO_REPLICACAO");
			comando.adicionarVariavel("numCooperativa", cooperativa);
			comando.configurar();
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			throw new ContaCapitalLegadoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	private void prepararCooperativaIds(Integer cooperativa) throws BancoobException { 
		Connection conexao = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			Comando comando = getComando("PREPARARCOOPERATIVAPILOTO_IDS");
			String sqlText = comando.getSql();
			String[] sqls = sqlText.split(";");
			for (String sql : sqls) {
				if (!ContaCapitalUtil.isStringVazia(sql)) {
					Comando comandoIndividual = new Comando();
					comandoIndividual.setSql(sql);
					comandoIndividual.configurar();
					comandoIndividual.executarAtualizacao(conexao);
					fecharComando(comandoIndividual);
				}
			}
		} catch (PersistenciaException e) {
			throw new ContaCapitalLegadoNegocioException(e);
		} finally {
			fecharConexao(conexao);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarCooperativaPiloto(Integer)
	 */
	public Map<String, Long> consultarCooperativaPiloto(Integer cooperativa) throws BancoobException {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("ReplicacaoCCA", consultarQuantidadeCooperativaPilotoIntegracao(cooperativa));
		map.putAll(consultarQuantidadesCooperativaPilotoIds(cooperativa));
		return map;
	}

	private Long consultarQuantidadeCooperativaPilotoIntegracao(Integer cooperativa) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			comando = getComando("CONSULTARCOOPERATIVAPILOTO_REPLICACAO");
			comando.adicionarVariavel("numCooperativa", cooperativa);
			comando.configurar();
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return 0L;
	}
	
	private Map<String, Long> consultarQuantidadesCooperativaPilotoIds(Integer cooperativa) throws BancoobException {
		Map<String, Long> map = new HashMap<String, Long>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			comando = getComando("CONSULTARCOOPERATIVAPILOTO_IDS");
			comando.adicionarVariavel("numCooperativa", cooperativa);
			comando.configurar();
			conexao = estabelecerConexao(cooperativa);
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				map.put(rs.getString(1), rs.getLong(2));
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return map;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarBatimentosContaCapital(Integer)
	 */
	public List<RegistroBatimentoContaCapitalDTO> consultarBatimentosContaCapital(Integer cooperativa) throws BancoobException {
		List<RegistroBatimentoContaCapitalDTO> registros = new ArrayList<RegistroBatimentoContaCapitalDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARBATIMENTOSSQL_CONTACAPITAL");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				RegistroBatimentoContaCapitalDTO registro = new RegistroBatimentoContaCapitalDTO();
				int i=0;
				registro.setTabela(rs.getString(++i));
				registro.setTotal(rs.getInt(++i));
				registro.setValorSaldoAtuSubsc(rs.getBigDecimal(++i));
				registro.setValorSaldoAtuInteg(rs.getBigDecimal(++i));
				registro.setValorSaldoAtuDevolver(rs.getBigDecimal(++i));
				registro.setValorSaldoBloq(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarBatimentosLancamento(Integer)
	 */
	public List<RegistroBatimentoLancamentoDTO> consultarBatimentosLancamento(Integer cooperativa) throws BancoobException {
		List<RegistroBatimentoLancamentoDTO> registros = new ArrayList<RegistroBatimentoLancamentoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARBATIMENTOSSQL_LANCAMENTOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				RegistroBatimentoLancamentoDTO registro = new RegistroBatimentoLancamentoDTO();
				int i=0;
				registro.setTabela(rs.getString(++i));
				registro.setBolAtualizado(rs.getInt(++i));
				registro.setTotal(rs.getInt(++i));
				registro.setValorLanc(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarBatimentosParcelamento(Integer)
	 */
	public List<RegistroBatimentoParcelamentoDTO> consultarBatimentosParcelamento(Integer cooperativa) throws BancoobException {
		List<RegistroBatimentoParcelamentoDTO> registros = new ArrayList<RegistroBatimentoParcelamentoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARBATIMENTOSSQL_PARCELAMENTOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				RegistroBatimentoParcelamentoDTO registro = new RegistroBatimentoParcelamentoDTO();
				int i=0;
				registro.setTabela(rs.getString(++i));
				registro.setTipoParcelamento(rs.getString(++i));
				registro.setSituacao(rs.getString(++i));
				registro.setTotal(rs.getInt(++i));
				registro.setValor(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarCooperativasConciliacao()
	 */
	public List<Integer> consultarCooperativasConciliacao()	throws BancoobException {
		List<Integer> cooperativas = new ArrayList<Integer>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBPRODLAB);
			comando = getComando("CONSULTARCOOPERATIVAS_CONCILIACAO");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				cooperativas.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return cooperativas;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoLancamentosNaoAtualizados(Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoLancamentosNaoAtualizados(Integer cooperativa) throws BancoobException {
		List<ConfiguracaoConciliacaoDTO> dtos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARCONCILIACAO_LANCAMENTOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ConfiguracaoConciliacaoDTO dto = new ConfiguracaoConciliacaoDTO();
				int i=0;
				dto.setCooperativa(rs.getInt(++i));
				Date dataLote = rs.getDate(++i);
				if (dataLote != null){
					dto.setDataLote(new DateTime(dataLote.getTime()));
				}
				dto.setNumLoteLanc(rs.getInt(++i));
				dto.setNumSeqLanc(rs.getInt(++i));
				dto.setValorLanc(rs.getBigDecimal(++i));
				dto.setNumMatricula(rs.getInt(++i));
				dto.setIdHistorico(rs.getInt(++i));
				Date dataHoraInclusao = rs.getDate(++i);
				if (dataHoraInclusao != null){
					dto.setDataHoraInclusao(new DateTime(dataHoraInclusao.getTime()));
				}
				dtos.add(dto);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoParcelaSemSubscricao(Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoParcelaSemSubscricao(Integer cooperativa) throws BancoobException {
		return consultarConciliacaoParcSubs(cooperativa, "CONSULTARCONCILIACAO_PARCSEMSUBS");
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoSubscricaoSemParcela(Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoSubscricaoSemParcela(Integer cooperativa) throws BancoobException {
		return consultarConciliacaoParcSubs(cooperativa, "CONSULTARCONCILIACAO_SUBSSEMPARC");
	}
	
	private List<ConfiguracaoConciliacaoDTO> consultarConciliacaoParcSubs(Integer cooperativa, String nomeComando) throws ReplicacaoConsultaLegadoException {
		List<ConfiguracaoConciliacaoDTO> dtos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando(nomeComando);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ConfiguracaoConciliacaoDTO dto = new ConfiguracaoConciliacaoDTO();
				int i=0;
				dto.setCooperativa(rs.getInt(++i));
				dto.setNumMatricula(rs.getInt(++i));
				dto.setValorSaldoAtuSubsc(rs.getBigDecimal(++i));
				dto.setValorLancSubscDoDia(rs.getBigDecimal(++i));
				dto.setValorSaldoAtuInteg(rs.getBigDecimal(++i));
				dto.setValorParcEmAberto(rs.getBigDecimal(++i));
				dto.setValorLancIntegDoDia(rs.getBigDecimal(++i));
				dto.setValorDiferenca(rs.getBigDecimal(++i));
				dto.setScript(rs.getString(++i));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarExpurgo(Integer, List)
	 */
	public List<ExpurgoReplicacaoDTO> consultarExpurgo(Integer cooperativa, List<Integer> cooperativasPiloto) throws BancoobException {
		List<ExpurgoReplicacaoDTO> dtos = new ArrayList<ExpurgoReplicacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			comando = getComando("CONSULTARREPLICACAOEXPURGO");
			comando.adicionarVariavel("numCooperativa", cooperativa);
			comando.adicionarVariavel("cooperativasPiloto", ContaCapitalUtil.formatarListaValoresIN(cooperativasPiloto));
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ExpurgoReplicacaoDTO dto = new ExpurgoReplicacaoDTO();
				int i=0;
				dto.setNumCooperativa(rs.getInt(++i));
				dto.setIdInstituicao(rs.getInt(++i));
				dto.setQuantidade(rs.getLong(++i));
				dtos.add(dto);
			}
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoDuplicidade(java.lang.Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoDuplicidade(Integer cooperativa) throws BancoobException {
		List<ConfiguracaoConciliacaoDTO> dtos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARCONCILIACAO_DUPLICIDADE");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ConfiguracaoConciliacaoDTO dto = new ConfiguracaoConciliacaoDTO();
				int i=0;
				dto.setCentral(rs.getInt(++i));
				dto.setCooperativa(rs.getInt(++i));
				dto.setNumCliente(rs.getInt(++i));
				dto.setNumMatricula(rs.getInt(++i));
				dto.setDataMatricula(rs.getString(++i));
				dto.setValorSaldoAtuInteg(rs.getBigDecimal(++i));
				dto.setValorSaldoAtuSubsc(rs.getBigDecimal(++i));
				dto.setValorSaldoAtuDevol(rs.getBigDecimal(++i));
				dto.setValorSaldoBloqInt(rs.getBigDecimal(++i));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#alterarJSONReplicacao(java.lang.Integer, java.lang.String)
	 */
	public void alterarJSONReplicacao(Integer idReplicacaoCCA, String json) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ALTERARJSONREPLICACAO"; 
			comando = getComando(identificacao);
			comando.adicionarVariavel("idReplicacaoCCA", idReplicacaoCCA);
			comando.adicionarVariavel("json", json);
			comando.configurar();			
			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			
			if (comando.executarAtualizacao(conexao) != ReplicacaoLegadoConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoAtualizaListaReplicacaoException(MSG_ERRO_REGISTRO_NAO_ATUALIZADO, sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarReplicacaoPorIds(java.lang.Long, java.lang.Long)
	 */
	public List<ReplicacaoContaCapitalLegadoDTO> consultarReplicacaoPorIds(Long idInicial, Long idFinal) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<ReplicacaoContaCapitalLegadoDTO> listaRetorno = new ArrayList<ReplicacaoContaCapitalLegadoDTO>();
		
		try {
			
			comando = getComando("OBTERREPLICACAOIDSCCA");
			comando.adicionarVariavel("idInicial", idInicial);
			comando.adicionarVariavel("idFinal", idFinal);
			comando.configurar();

			conexao = estabelecerConexao(ReplicacaoLegadoConstantes.COOP_BDSICOOBINTEGRACAO);
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {				
				ReplicacaoContaCapitalLegadoDTO dto = new ReplicacaoContaCapitalLegadoDTO();

				dto.setIdReplicacaoCCA(rs.getLong("IdReplicacaoCCA"));
				dto.setIdTabelaReplicadaCCA(rs.getInt("IdTabelaReplicadaCCA"));
				dto.setIdSituacaoReplicacaoCCA(rs.getInt("IdSituacaoReplicacaoCCA"));
				dto.setCodAcao(rs.getString("CodAcao"));
				dto.setDescChaveReplicacaoSQL(rs.getString("DescChaveReplicacaoSQL"));
				if (rs.getTimestamp("DataHoraCadastro") != null) {
					dto.setDataHoraCadastro(new DateTimeDB(rs.getTimestamp("DataHoraCadastro").getTime()));
				}
				if (rs.getTimestamp("DataHoraReplicacao") != null) {
					dto.setDataHoraReplicacao(new DateTimeDB(rs.getTimestamp("DataHoraReplicacao").getTime()));
				}
				dto.setNumCooperativa(rs.getInt("NumCooperativa"));
				dto.setIdInstituicao(rs.getInt("IdInstituicao"));
				dto.setDescMensagemReplicacao(rs.getString("DescMensagemReplicacao"));
				
				if (rs.getString("DescChaveReplicacaoDB2") != null) {
					dto.setDescChaveReplicacaoDB2(rs.getString("DescChaveReplicacaoDB2"));
				}
				
				listaRetorno.add(dto);
			}			
			
		} catch (SQLException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoCapaLote(java.lang.Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoCapaLote(Integer cooperativa) throws BancoobException {
		List<ConfiguracaoConciliacaoDTO> dtos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARCONCILIACAO_CAPALOTE");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ConfiguracaoConciliacaoDTO dto = new ConfiguracaoConciliacaoDTO();
				int i=0;
				dto.setCooperativa(cooperativa);
				dto.setNumLoteLanc(rs.getInt(++i));
				dto.setValorCapaLote(rs.getBigDecimal(++i));
				dto.setQtdCapaLote(rs.getLong(++i));
				dto.setValorLancamentos(rs.getBigDecimal(++i));
				dto.setQtdLancamentos(rs.getLong(++i));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarBatimentosSaldosLegado(java.lang.Integer)
	 */
	public List<BatimentoSaldoLegadoDTO> consultarBatimentosSaldosLegado(Integer cooperativa) throws BancoobException {
		List<BatimentoSaldoLegadoDTO> registros = new ArrayList<BatimentoSaldoLegadoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARBATIMENTOSSQL_SALDOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				BatimentoSaldoLegadoDTO registro = new BatimentoSaldoLegadoDTO();
				int i=0;
				registro.setNumMatricula(rs.getInt(++i));
				registro.setValorSaldoInteg(rs.getBigDecimal(++i));
				registro.setValorSaldoSubsc(rs.getBigDecimal(++i));
				registro.setValorSaldoDevol(rs.getBigDecimal(++i));
				registro.setValorSaldoBloq(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoSaldosNegativos(java.lang.Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoSaldosNegativos(Integer cooperativa) throws BancoobException {
		List<ConfiguracaoConciliacaoDTO> dtos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARCONCILIACAO_SALDOSNEGATIVOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ConfiguracaoConciliacaoDTO dto = new ConfiguracaoConciliacaoDTO();
				int i=0;
				dto.setCooperativa(cooperativa);
				dto.setNumMatricula(rs.getInt(++i));
				dto.setCodGrupoHist(rs.getInt(++i));
				dto.setValorLanc(rs.getBigDecimal(++i));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoParcelamentoDevolucaoSemSaldo(java.lang.Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoParcelamentoDevolucaoSemSaldo(Integer cooperativa) throws BancoobException {
		List<ConfiguracaoConciliacaoDTO> dtos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARCONCILIACAO_PARCELAMENTO_DEVOLUCAO_SEM_SALDO");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ConfiguracaoConciliacaoDTO dto = new ConfiguracaoConciliacaoDTO();
				int i=0;
				dto.setCooperativa(cooperativa);
				dto.setNumMatricula(rs.getInt(++i));
				dto.setValorParcEmAberto(rs.getBigDecimal(++i));
				dto.setAuxiliar1(rs.getString(++i));
				dto.setAuxiliar2(rs.getString(++i));
				dto.setValorSaldoAtuDevol(rs.getBigDecimal(++i));
				dto.setAuxiliar3(rs.getString(++i));
				dto.setValorLanc(rs.getBigDecimal(++i));
				dto.setAuxiliar4(rs.getString(++i));
				dto.setAuxiliar5(rs.getString(++i));
				dto.setValorDiferenca(rs.getBigDecimal(++i));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao#consultarConciliacaoDevolucaoSemParcelamentoParaAtivos(java.lang.Integer)
	 */
	public List<ConfiguracaoConciliacaoDTO> consultarConciliacaoDevolucaoSemParcelamentoParaAtivos(Integer cooperativa) throws BancoobException {
		List<ConfiguracaoConciliacaoDTO> dtos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexao(cooperativa);
			comando = getComando("CONSULTARCONCILIACAO_DEVOLUCAO_SEM_PARC_ATIVOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				ConfiguracaoConciliacaoDTO dto = new ConfiguracaoConciliacaoDTO();
				int i=0;
				dto.setCooperativa(cooperativa);
				dto.setNumMatricula(rs.getInt(++i));
				dto.setAuxiliar1(rs.getString(++i));
				dto.setValorSaldoAtuDevol(rs.getBigDecimal(++i));
				dto.setAuxiliar2(rs.getString(++i));
				dto.setValorLanc(rs.getBigDecimal(++i));
				dto.setAuxiliar3(rs.getString(++i));
				dto.setAuxiliar4(rs.getString(++i));
				dto.setValorParcEmAberto(rs.getBigDecimal(++i));
				dto.setAuxiliar5(rs.getString(++i));
				dto.setValorDiferenca(rs.getBigDecimal(++i));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoConsultaLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dtos;
	}
	
	
}