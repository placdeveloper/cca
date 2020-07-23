package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BancoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao;
import br.com.sicoob.tipos.DateTime;

/**
 * IntegralizacaoOutrosBancosLegadoDaoImpl
 */
public class IntegralizacaoOutrosBancosLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements IntegralizacaoOutrosBancosLegadoDao {

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#obtemListaBancos()
	 */
	public List<BancoLegadoDTO> obtemListaBancos() throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<BancoLegadoDTO> listaRetorno = new ArrayList<BancoLegadoDTO>();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("OBTEM_LISTA_BANCOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				BancoLegadoDTO dto = new BancoLegadoDTO();
				dto.setNumBanco(rs.getInt("NumBanco"));
				dto.setDescBanco(rs.getString("DescBanco"));
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao recuperar lista de bancos.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao recuperar lista de bancos.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#consultarFavorecidosIntegralizacao(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarFavorecidosIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<IntegralizacaoOutrosBancosLegadoDTO> listaRetorno = new ArrayList<IntegralizacaoOutrosBancosLegadoDTO>();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_FAVORECIDOS_INTEG_OUTROS_BANCOS");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				IntegralizacaoOutrosBancosLegadoDTO dto = new IntegralizacaoOutrosBancosLegadoDTO();
				dto.setNumCliente(rs.getInt("NumCliente"));
				dto.setNumMatricula(rs.getInt("NumMatricula"));
				dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
				dto.setNumCpfCnpj(rs.getString("NumCGC_CPF"));
				dto.setNumBanco(rs.getInt("NumBanco"));
				dto.setNumAgencia(rs.getInt("NumAgencia"));
				dto.setNumDVAgencia(rs.getString("NumDVAgencia"));
				dto.setNumContaCorrente(rs.getString("NumContaCorrente"));
				dto.setNumDVContaCorrente(rs.getInt("NumDVContaCorrente"));
				dto.setNumBancoFavorecido(rs.getInt("NumBancoFavorecido"));
				dto.setNumAgenciaFavorecido(rs.getInt("NumAgenciaFavorecido"));
				dto.setNumContaFavorecido(rs.getString("NumContaFavorecido"));
				dto.setNumDVAgenciaFavorecido(rs.getString("NumDVAgenciaFavorecido"));
				dto.setDescBanco(rs.getString("DescBanco"));
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar favorecidos.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar favorecidos.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#enviarRemessa(java.util.List)
	 */
	public void enviarRemessa(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("ENVIAR_OUTROS_BANCOS").getSql());
			for (IntegralizacaoOutrosBancosLegadoDTO dto : dtos) {
				int i=0;
				ps.setInt(++i, dto.getNumCliente());
				ps.setInt(++i, dto.getNumMatricula());
				ps.setInt(++i, dto.getNumBanco());
				ps.setInt(++i, dto.getNumAgencia());
				ps.setString(++i, dto.getNumDVAgencia());
				ps.setString(++i, dto.getNumContaCorrente());
				ps.setInt(++i, dto.getNumDVContaCorrente());
				ps.setInt(++i, dto.getNumAgenciaFavorecido());
				ps.setString(++i, dto.getNumDVAgenciaFavorecido());
				ps.setInt(++i, dto.getNumBancoFavorecido());
				ps.setString(++i, dto.getNumContaFavorecido());
				ps.setBigDecimal(++i, dto.getValorIntegralizacao());
				ps.setBigDecimal(++i, dto.getValorIntegralizacao());
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao enviar remessa para outros bancos.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao enviar remessa para outros bancos.", e);
		} finally {
			fecharStatement(ps);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#consultarContasFavorecidos(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO, java.lang.Integer)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarContasFavorecidos(IntegralizacaoOutrosBancosLegadoDTO filtro, Integer tipoSituacao) throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<IntegralizacaoOutrosBancosLegadoDTO> listaRetorno = new ArrayList<IntegralizacaoOutrosBancosLegadoDTO>();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_CONTAS_FAVORECIDOS");
			comando.adicionarVariavel("filtro", filtro);
			comando.adicionarVariavel("tipoSituacao", tipoSituacao);
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				IntegralizacaoOutrosBancosLegadoDTO dto = new IntegralizacaoOutrosBancosLegadoDTO();
				dto.setId(rs.getInt("IdRateioContaFavorecida"));
				dto.setNumMatricula(rs.getInt("NumMatricula"));
				dto.setNumCliente(rs.getInt("NumCliente"));
				dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
				dto.setNumCpfCnpj(rs.getString("NumCGC_CPF"));
				dto.setNumBancoFavorecido(rs.getInt("NumBancoFavorecido"));
				dto.setNumAgenciaFavorecido(rs.getInt("NumAgenciaFavorecido"));
				dto.setNumDVAgenciaFavorecido(rs.getString("NumDVAgenciaFavorecido"));
				dto.setNumContaFavorecido(rs.getString("NumContaFavorecido"));
				dto.setContaPrincipal(rs.getInt("BolContaPrincipalCapital") == 1);
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar contas de favorecidos.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar contas de favorecidos.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#atualizarContas()
	 */
	public void atualizarContas() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("ATUALIZAR_CONTAS_FAVORECIDOS");
			conexao = estabelecerConexao();
			comando.configurar();
			comando.executarStoredProcedure(conexao);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao verificar novas contas de favorecidos.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#definirPrincipal(java.util.List)
	 */
	public void definirPrincipal(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("DEFINIR_CONTA_FAVORECIDO_PRINCIPAL").getSql());
			String usuario = InformacoesUsuario.getInstance().getLogin();
			for (IntegralizacaoOutrosBancosLegadoDTO dto : dtos) {
				int i=0;
				ps.setInt(++i, dto.getId());
				ps.setInt(++i, dto.getNumCliente());
				ps.setInt(++i, dto.getId());
				ps.setString(++i, usuario);
				ps.setInt(++i, dto.getId());
				ps.setString(++i, usuario);
				ps.setInt(++i, dto.getNumCliente());
				ps.setInt(++i, dto.getId());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao definir conta principal.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao definir conta principal.", e);
		} finally {
			fecharStatement(ps);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	public void enviarIntegBancos() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		String idUsuario = InformacoesUsuario.getInstance().getLogin();
		
		try {
			comando = getComando("ENVIAR_INTEG_BANCOS");
			conexao = estabelecerConexao();
			comando.adicionarVariavel("idUsuario", idUsuario);
			comando.configurar();
			comando.executarStoredProcedure(conexao);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao enviar integralização bancos.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}	
		
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#consultarRemessaIntegralizacaoOutrosBancos(br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaIntegralizacaoOutrosBancos(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<RemessaIntegralizacaoOutrosBancosLegadoDTO> listaRetorno = new ArrayList<RemessaIntegralizacaoOutrosBancosLegadoDTO>();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_REMESSA_INTEG_BANCOS");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				RemessaIntegralizacaoOutrosBancosLegadoDTO dto = new RemessaIntegralizacaoOutrosBancosLegadoDTO();
				dto.setSequencialArquivo(rs.getInt("SequencialArquivo"));
				dto.setNomeArquivo(rs.getString("NomeArquivo"));
				dto.setDataGeracao(new DateTime(rs.getDate("dataGeracao").getTime()));
				dto.setCodSituacaoArquivo(rs.getInt("CodSituacaoArquivo"));
				dto.setNumBanco(rs.getInt("NumBanco"));
				dto.setDescBanco(rs.getString("DescBanco"));
				dto.setNumAgencia(rs.getInt("NumAgencia"));
				dto.setQtdRegistros(rs.getInt("QtdRegistros"));
				dto.setValorTotal(rs.getBigDecimal("ValorTotal"));
				
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar remessas.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar remessas.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#consultarRemessaIntegralizacaoOutrosBancosDetalhe(Integer)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaEnvDetalhe(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<IntegralizacaoOutrosBancosLegadoDTO> listaRetorno = new ArrayList<IntegralizacaoOutrosBancosLegadoDTO>();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_REMESSA_ENV_DETALHE_INTEG_BANCOS");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				IntegralizacaoOutrosBancosLegadoDTO dto = new IntegralizacaoOutrosBancosLegadoDTO();
				dto.setSequencialArquivo(rs.getInt("SequencialArquivo"));
				dto.setNumBanco(rs.getInt("NumBanco"));
				dto.setNumAgencia(rs.getInt("NumAgencia"));
				dto.setNumMatricula(rs.getInt("MatriculaContaCapital"));
				dto.setNumAgenciaFavorecido(rs.getInt("NumAgenciaFavorecido"));
				dto.setNumContaFavorecido(rs.getString("NumContaFavorecido"));
				dto.setValorIntegralizacao(rs.getBigDecimal("ValorLiquido"));
				dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
				dto.setNumCpfCnpj(rs.getString("NumCGC_CPF"));				
				
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar remessas detalhe.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar remessas detalhe.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}	

		
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#consultarRemessaRetorno(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetorno(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<RemessaIntegralizacaoOutrosBancosLegadoDTO> listaRetorno = new ArrayList<RemessaIntegralizacaoOutrosBancosLegadoDTO>();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_RETORNO_REMESSA_INTEG_BANCOS");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				RemessaIntegralizacaoOutrosBancosLegadoDTO dto = new RemessaIntegralizacaoOutrosBancosLegadoDTO();
				dto.setSequencialArquivo(rs.getInt("SequencialArquivo"));				
				dto.setNomeArquivo(rs.getString("NomeArquivo"));
				dto.setDescBanco(rs.getString("DescBanco"));
				dto.setNumBanco(rs.getInt("NumBanco"));
				dto.setNumAgencia(rs.getInt("NumAgencia"));
				dto.setDataGeracao (new DateTime(rs.getDate("dataGeracao").getTime()));
				dto.setValorTotal(rs.getBigDecimal("ValorTotal"));
				dto.setQtdRegistros(rs.getInt("QtdRegistros"));
				
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar retorno remessa.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar retorno remessa.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#consultarRemessaRetornoDetalhe(br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO)
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetornoDetalhe(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<IntegralizacaoOutrosBancosLegadoDTO> listaRetorno = new ArrayList<IntegralizacaoOutrosBancosLegadoDTO>();
		
		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTAR_DETALHE_RETORNO_REMESSA_INTEG_BANCOS");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				IntegralizacaoOutrosBancosLegadoDTO dto = new IntegralizacaoOutrosBancosLegadoDTO();
				dto.setNumCliente(rs.getInt("NumCliente"));
				dto.setNumMatricula(rs.getInt("MatriculaContaCapital"));
				dto.setNumBanco(rs.getInt("NumBanco"));
				dto.setNumAgencia(rs.getInt("NumAgencia"));
				dto.setNumAgenciaFavorecido(rs.getInt("NumAgenciaFavorecido"));
				dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
				dto.setNumCpfCnpj(rs.getString("NumCGC_CPF"));				
				dto.setNumContaFavorecido(rs.getString("NumContaFavorecido"));
				dto.setValorIntegralizacao(rs.getBigDecimal("ValorLiquido"));
				dto.setDescBanco(rs.getString("DescBanco"));
				dto.setBolIntegralizadoCapital(rs.getInt("BolIntegralizadoCapital") == 1);
				dto.setSequencialArquivo(rs.getInt("SequencialArquivo"));
				dto.setSequencialDetalhe(rs.getInt("SequencialDetalhe"));
				dto.setNomeArquivo(rs.getString("NomeArquivo"));
				dto.setNumParcela(rs.getInt("NumParcela"));				
				
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar retorno remessa.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar retorno remessa.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#gravarIntegralizacao(java.util.List)
	 */
	public void gravarIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("UPDATE_INTEG_OUTROS_BANCOS").getSql());
			int i=0;
			ps.setInt(++i, dto.getNumBanco());
			ps.setInt(++i, dto.getNumAgencia());
			ps.setInt(++i, dto.getSequencialArquivo());
			ps.setString(++i, dto.getNomeArquivo());
			ps.setInt(++i, dto.getSequencialDetalhe());
			ps.setInt(++i, dto.getNumParcela());
			ps.setInt(++i, dto.getNumMatricula());
			ps.addBatch();
			ps.executeBatch();
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao gravar integralizacao para outros bancos.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao gravar integralizacao para outros bancos.", e);
		} finally {
			fecharStatement(ps);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	
	
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarFavorecidosIntegralizacaoSWS(Integer numCoop) throws BancoobException {
		Comando comando = null;
		ResultSet rs = null;
		Connection conexao = null;
		List<IntegralizacaoOutrosBancosLegadoDTO> listaRetorno = new ArrayList<IntegralizacaoOutrosBancosLegadoDTO>();
		
		try {
			conexao = estabelecerConexao(numCoop);
			comando = getComando("CONSULTAR_RETORNO_INTEG_OUTROS_BANCOS");
			comando.configurar();
			rs = comando.executarConsulta(conexao);  
			while(rs.next()) {
				IntegralizacaoOutrosBancosLegadoDTO dto = new IntegralizacaoOutrosBancosLegadoDTO();
				dto.setNumBanco(rs.getInt("NumBanco"));
				dto.setNumAgencia(rs.getInt("NumAgencia"));
				dto.setSequencialArquivo(rs.getInt("SequencialArquivo"));
				dto.setNomeArquivo(rs.getString("NomeArquivo"));
				dto.setSequencialDetalhe(rs.getInt("SequencialDetalhe"));
				dto.setNumParcela(rs.getInt("NumParcela"));				
				dto.setNumMatricula(rs.getInt("MatriculaContaCapital"));
				dto.setValorIntegralizacao(rs.getBigDecimal("ValorLiquido"));
				dto.setBolIntegralizadoCapital(rs.getInt("BolIntegralizadoCapital") == 1);
				
				listaRetorno.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar retorno remessa.", e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("Erro ao consultar retorno remessa.", e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}
	
	/**
	 * @throws SQLException 
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.IntegralizacaoOutrosBancosLegadoDao#updateIntegralizacaoSWS(java.util.List)
	 */
	public void updateIntegralizacaoSWS(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		try {
			conexao = estabelecerConexao(dto.getNumCoop());
			ps = conexao.prepareStatement(getComando("UPDATE_INTEG_OUTROS_BANCOS").getSql());
			int i=0;
			ps.setInt(++i, dto.getNumBanco());
			ps.setInt(++i, dto.getNumAgencia());
			ps.setInt(++i, dto.getSequencialArquivo());
			ps.setString(++i, dto.getNomeArquivo());
			ps.setInt(++i, dto.getSequencialDetalhe());
			ps.setInt(++i, dto.getNumParcela());
			ps.setInt(++i, dto.getNumMatricula());
			ps.addBatch();
			ps.executeBatch();
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
		} finally {
			fecharStatement(ps);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}
	

