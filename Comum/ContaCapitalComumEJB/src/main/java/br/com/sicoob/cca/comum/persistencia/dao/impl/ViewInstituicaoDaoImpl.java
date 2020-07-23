package br.com.sicoob.cca.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao;

/**
 * @see ViewInstituicaoDao
 * @author Nairon.Silva
 */
public class ViewInstituicaoDaoImpl extends ContaCapitalComumDao implements ViewInstituicaoDao {

	/**
	 * @see br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao#consultarCooperativasAtivas(java.lang.Integer)
	 */
	public List<InstituicaoCooperativaSCIDTO> consultarCooperativasAtivas(Integer numCoopPai) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<InstituicaoCooperativaSCIDTO> lstRetorno = new ArrayList<InstituicaoCooperativaSCIDTO>(0); 
		try {
			comando = getComando("CONSULTA_COOPERATIVAS_ATIVAS_SCI");
			if (numCoopPai != null) {
				comando.adicionarVariavel("numCoopPai", numCoopPai);
			}
			comando.configurar();
			conexao = this.estabelecerConexaoCorporativa();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				InstituicaoCooperativaSCIDTO dto = new InstituicaoCooperativaSCIDTO();
				dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				dto.setNumCooperativa(rs.getInt("NUMCOOPERATIVA"));
				dto.setNome(rs.getString("DESCSIGLACOOP"));
				lstRetorno.add(dto);
			}
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lstRetorno;
	}
	
	/**
	 * @see br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao#listarCentrais()
	 */
	public List<InstituicaoCooperativaSCIDTO> listarCentrais() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<InstituicaoCooperativaSCIDTO> lstRetorno = new ArrayList<InstituicaoCooperativaSCIDTO>(0); 
		try {
			comando = getComando("SCI_LISTAR_CENTRAL");
			comando.configurar();
			conexao = this.estabelecerConexaoCorporativa();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				InstituicaoCooperativaSCIDTO dto = new InstituicaoCooperativaSCIDTO();
				dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				dto.setNumCooperativa(rs.getInt("NUMCOOPERATIVA"));
				dto.setNome(rs.getString("DESCSIGLACOOP"));
				lstRetorno.add(dto);
			}
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lstRetorno;
	}
	
	/**
	 * @see br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao#listarCentrais()
	 */
	public List<InstituicaoCooperativaSCIDTO> listarCentraisEConfederacao() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<InstituicaoCooperativaSCIDTO> lstRetorno = new ArrayList<InstituicaoCooperativaSCIDTO>(0); 
		try {
			comando = getComando("SCI_LISTAR_CENTRAL_E_CONFEDERACAO");
			comando.configurar();
			conexao = this.estabelecerConexaoCorporativa();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				InstituicaoCooperativaSCIDTO dto = new InstituicaoCooperativaSCIDTO();
				dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				dto.setNumCooperativa(rs.getInt("NUMCOOPERATIVA"));
				dto.setNome(rs.getString("DESCSIGLACOOP"));
				lstRetorno.add(dto);
			}
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lstRetorno;
	}
	
	/**
	 * {@link ViewInstituicaoDao#consultarPacPorCooperativa(Integer)}
	 */
	public List<InstituicaoCooperativaSCIDTO> consultarPacPorCooperativa(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<InstituicaoCooperativaSCIDTO> lstRetorno = new ArrayList<InstituicaoCooperativaSCIDTO>(0); 
		try {
			comando = getComando("CONSULTA_PAC_POR_COOPERATIVA");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			conexao = this.estabelecerConexaoCorporativa();
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				InstituicaoCooperativaSCIDTO dto = new InstituicaoCooperativaSCIDTO();
				dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				dto.setNumCooperativa(rs.getInt("NUMCOOPERATIVA"));
				dto.setNome(rs.getString("DESCNOMECOOP"));
				dto.setNumPAC(rs.getInt("NUMPAC"));
				lstRetorno.add(dto);
			}
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lstRetorno;
	}
}
