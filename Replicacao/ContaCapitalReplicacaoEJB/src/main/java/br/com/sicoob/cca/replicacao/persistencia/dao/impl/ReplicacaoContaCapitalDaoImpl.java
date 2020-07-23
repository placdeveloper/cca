/*
 * 
 */
package br.com.sicoob.cca.replicacao.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoContaCapital;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoLancamento;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoParcelamento;
import br.com.sicoob.cca.replicacao.negocio.constantes.ReplicacaoContaCapitalConstantes;
import br.com.sicoob.cca.replicacao.negocio.dto.BatimentoSaldoDTO;
import br.com.sicoob.cca.replicacao.negocio.excecao.ReplicacaoContaCapitalDb2Exception;
import br.com.sicoob.cca.replicacao.negocio.excecao.ReplicacaoContaCapitalDb2NegocioException;
import br.com.sicoob.cca.replicacao.persistencia.dao.ContaCapitalReplicacaoDao;
import br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;
import br.com.sicoob.tipos.DateTime;

/**
 * DAO de replicacao.
 */
public class ReplicacaoContaCapitalDaoImpl extends ContaCapitalReplicacaoDao implements ReplicacaoContaCapitalDao {

	
	private String sqlExecucao = null; //comando sql utilizado para log
	private String identificacao = null; //indentifica o comando utilizado no queries
	
	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */
	public Integer incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		Integer saida = null;
		ResultSet rs = null;
		
		try {
			identificacao = "INCLUIRCONTACAPITALREPLICACAO";
			comando = getComando(identificacao);
			comando.adicionarVariavel("objContaCapital", obj);
			comando.adicionarVariavel("dataHoraAtualizacao", new DateTime(new DateTime().getTime()));			
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			rs = comando.executarConsulta(conexao);

			if (rs.next()){
				saida = rs.getInt("IDCONTACAPITAL");
			}else{
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_OBTER_CHAVE_CONTACAPITAL",sqlExecucao);
			}			
		}catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharResultSetReplicacao(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return saida;
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO)
	 */
	public Long incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		Long saida = null;
		ResultSet rs  = null;
		
		try {
			identificacao = "INCLUIRPARCELAMENTOCONTACAPITALREPLICACAO";				
			comando = getComando(identificacao);
			comando.adicionarVariavel("objParcelamentoContaCapital", obj);			
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			rs = comando.executarConsulta(conexao);
				
			if (rs.next()){
				saida = rs.getLong("IDPARCELAMENTOCONTACAPITAL");
			}else{
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_OBTER_CHAVE_PARCELAMENTO", sqlExecucao);
			}
			
		}catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharResultSetReplicacao(rs);			
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return saida;		
		
		
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 */	
	public Long incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		Long saida = null;	
		ResultSet rs = null;
		
		try {
			identificacao = "INCLUIRLANCAMENTOCONTACAPITALREPLICACAO";					
			comando = getComando(identificacao);
			comando.adicionarVariavel("objLancamentoContaCapital", obj);	
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			rs = comando.executarConsulta(conexao);

			if (rs.next()){
				saida = rs.getLong("IDLANCAMENTOCONTACAPITAL");
			}else{
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_OBTER_CHAVE_LANCAMENTO", sqlExecucao);
			}
		}catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {			
			fecharResultSetReplicacao(rs);			
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return saida;	

	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 */
	public void alterarContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException {
			
		Comando comando = null;
		Connection conexao = null;		
		
		try {
			identificacao = "ALTERARCONTACAPITALREPLICACAO";			
			comando = getComando(identificacao);
			comando.adicionarVariavel("objContaCapital", obj);
			comando.adicionarVariavel("dataHoraAtualizacao", new DateTime(new DateTime().getTime()));
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoContaCapitalConstantes.QTDE_MIN_REGISTRO_ALTERADO){				
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_REGISTRO_ALTERADO", sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
					
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO)
	 */
	public void alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ALTERARPARCELAMENTOCONTACAPITALREPLICACAO";				
			comando = getComando(identificacao);
			comando.adicionarVariavel("objParcelamentoContaCapital", obj);			
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoContaCapitalConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_REGISTRO_ALTERADO", sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
				
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 */
	public void alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException {		
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "ALTERARLANCAMENTOCONTACAPITALREPLICACAO";			
			comando = getComando(identificacao);
			comando.adicionarVariavel("objLancamentoContaCapital", obj);			
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoContaCapitalConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_REGISTRO_ALTERADO", sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		

	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */	
	public void excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;	
		
		try {
			identificacao = "EXCLUIRCONTACAPITALREPLICACAO"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("obj", obj);
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoContaCapitalConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_REGISTRO_EXCLUSAO", sqlExecucao);
			}			
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
			

	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#excluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO)
	 */	
	public void excluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "EXCLUIRPARCELAMENTOCONTACAPITALREPLICACAO"; 			
			comando = getComando(identificacao);
			comando.adicionarVariavel("obj", obj);			
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoContaCapitalConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_REGISTRO_EXCLUSAO", sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
			

	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#excluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO)
	 */	
	public void excluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			identificacao = "EXCLUIRLANCAMENTOCONTACAPITALREPLICACAO"; 
			comando = getComando(identificacao);
			comando.adicionarVariavel("obj", obj);			
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);			
			
			if (comando.executarAtualizacao(conexao)!= ReplicacaoContaCapitalConstantes.QTDE_MIN_REGISTRO_ALTERADO){
				throw new ReplicacaoContaCapitalDb2Exception("MSG_ERRO_REGISTRO_EXCLUSAO", sqlExecucao);
			}
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}		

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
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#excluirDocumentoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */	
	public void excluirDocumentoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;	
		
		try {
			identificacao = "EXCLUIRDOCUMENTOCONTACAPITALREPLICACAO"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("obj", obj);
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#excluirPropostaContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */
	public void excluirPropostaContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;	
		
		try {
			identificacao = "EXCLUIRPROPOSTACONTACAPITALREPLICACAO"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("obj", obj);
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#excluirDebitoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO)
	 */
	public void excluirDebitoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;	
		
		try {
			identificacao = "EXCLUIRDEBITOCONTACAPITALREPLICACAO"; 				
			comando = getComando(identificacao);
			comando.adicionarVariavel("obj", obj);
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao,comando);
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#prepararCooperativaPiloto(Integer)
	 */
	public void prepararCooperativaPiloto(Integer instituicao) throws BancoobException {
		Connection conexao = null;
		try {
			conexao = estabelecerConexaoCorporativa();
			Comando comando = getComando("PREPARARCOOPERATIVAPILOTO");
			String sqlText = comando.getSql();
			String[] sqls = sqlText.split(";");
			for (String sql : sqls) {
				if (!ContaCapitalUtil.isStringVazia(sql)) {
					Comando comandoInvidual = new Comando();
					comandoInvidual.setSql(sql);
					comandoInvidual.adicionarVariavel("idInstituicao", instituicao);
					comandoInvidual.configurar();
					comandoInvidual.executarAtualizacao(conexao);
					fecharComando(comandoInvidual);
				}
			}
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2NegocioException(e);
		} finally {
			fecharConexao(conexao);
		}
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#consultarCooperativaPiloto(Integer)
	 */
	public Map<String, Long> consultarCooperativaPiloto(Integer instituicao) throws BancoobException {
		Map<String, Long> map = new HashMap<String, Long>(); 
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		try {
			conexao = estabelecerConexaoCorporativa();
			comando = getComando("CONSULTARCOOPERATIVAPILOTO");
			comando.adicionarVariavel("idInstituicao", instituicao);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				map.put(rs.getString(1), rs.getLong(2));
			}
		} catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return map;
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#consultarBatimentosContaCapital()
	 */
	public List<RegistroBatimentoContaCapital> consultarBatimentosContaCapital(Integer idInstituicao) throws BancoobException {
		List<RegistroBatimentoContaCapital> registros = new ArrayList<RegistroBatimentoContaCapital>();
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		try {
			conexao = estabelecerConexaoCorporativa();
			comando = getComando("CONSULTARBATIMENTOSDB2_CONTACAPITAL");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				RegistroBatimentoContaCapital registro = new RegistroBatimentoContaCapital();
				int i=0;
				registro.setTabela(rs.getString(++i));
				registro.setIdInstituicao(rs.getInt(++i));
				registro.setTotal(rs.getInt(++i));
				registro.setValorSaldoAtuSubsc(rs.getBigDecimal(++i));
				registro.setValorSaldoAtuInteg(rs.getBigDecimal(++i));
				registro.setValorSaldoAtuDevolver(rs.getBigDecimal(++i));
				registro.setValorSaldoBloq(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#consultarBatimentosLancamento()
	 */
	public List<RegistroBatimentoLancamento> consultarBatimentosLancamento(Integer idInstituicao) throws BancoobException {
		List<RegistroBatimentoLancamento> registros = new ArrayList<RegistroBatimentoLancamento>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexaoCorporativa();
			comando = getComando("CONSULTARBATIMENTOSDB2_LANCAMENTOS");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				RegistroBatimentoLancamento registro = new RegistroBatimentoLancamento();
				int i=0;
				registro.setTabela(rs.getString(++i));
				registro.setIdInstituicao(rs.getInt(++i));
				registro.setBolAtualizado(rs.getInt(++i));
				registro.setTotal(rs.getInt(++i));
				registro.setValorLanc(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#consultarBatimentosParcelamento()
	 */
	public List<RegistroBatimentoParcelamento> consultarBatimentosParcelamento(Integer idInstituicao) throws BancoobException {
		List<RegistroBatimentoParcelamento> registros = new ArrayList<RegistroBatimentoParcelamento>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexaoCorporativa();
			comando = getComando("CONSULTARBATIMENTOSDB2_PARCELAMENTOS");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				RegistroBatimentoParcelamento registro = new RegistroBatimentoParcelamento();
				int i=0;
				registro.setTabela(rs.getString(++i));
				registro.setIdInstituicao(rs.getInt(++i));
				registro.setTipoParcelamento(rs.getString(++i));
				registro.setSituacao(rs.getString(++i));
				registro.setTotal(rs.getInt(++i));
				registro.setValor(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	/**
	 * @see br.com.sicoob.cca.replicacao.persistencia.dao.interfaces.ReplicacaoContaCapitalDao#consultarBatimentoSaldos(java.lang.Integer)
	 */
	public List<BatimentoSaldoDTO> consultarBatimentoSaldos(Integer idInstituicao) throws BancoobException {
		List<BatimentoSaldoDTO> registros = new ArrayList<BatimentoSaldoDTO>();
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			conexao = estabelecerConexaoCorporativa();
			comando = getComando("CONSULTARBATIMENTOSDB2_SALDOS");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				BatimentoSaldoDTO registro = new BatimentoSaldoDTO();
				int i=0;
				registro.setNumMatricula(rs.getInt(++i));
				registro.setValorSaldoInteg(rs.getBigDecimal(++i));
				registro.setValorSaldoSubsc(rs.getBigDecimal(++i));
				registro.setValorSaldoDevol(rs.getBigDecimal(++i));
				registro.setValorSaldoBloq(rs.getBigDecimal(++i));
				registros.add(registro);
			}
		} catch (SQLException e) {
			throw new ReplicacaoContaCapitalDb2Exception(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return registros;
	}

	public void replicarDebitoIndeterminado(ReplicacaoTabelaContaCapitalLegadoDTO obj, String codAcao) throws BancoobException {
		replicarTabelaDebitoIndeterminado(obj, codAcao);
		replicarTabelaHistDebitoIndeterminado(obj, codAcao);
	}

	private void replicarTabelaDebitoIndeterminado(ReplicacaoTabelaContaCapitalLegadoDTO obj, String codAcao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;		
		try {
			identificacao = "REPLICARDEBITOINDETERMINADO";			
			comando = getComando(identificacao);
			comando.adicionarVariavel("objContaCapital", obj);
			comando.adicionarVariavel("dataHoraAtualizacao", new DateTime());
			comando.adicionarVariavel("codAcao", codAcao);
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao, comando);
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	private void replicarTabelaHistDebitoIndeterminado(ReplicacaoTabelaContaCapitalLegadoDTO obj, String codAcao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;		
		try {
			identificacao = "REPLICARHISTDEBITOINDETERMINADO";			
			comando = getComando(identificacao);
			comando.adicionarVariavel("objContaCapital", obj);
			comando.configurar();
			conexao = estabelecerConexaoCorporativa();
			sqlExecucao = montarStringSqlParametros(identificacao, comando);
			comando.executarAtualizacao(conexao);
		} catch (PersistenciaException e) {
			throw new ReplicacaoContaCapitalDb2Exception(sqlExecucao+e.getMessage(),e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
}
