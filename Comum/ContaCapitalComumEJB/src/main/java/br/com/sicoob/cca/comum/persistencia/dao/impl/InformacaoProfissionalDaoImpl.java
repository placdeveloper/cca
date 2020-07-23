package br.com.sicoob.cca.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.cca.comum.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.InformacaoProfissionalDao;

/**
 * @see InformacaoProfissionalDao
 * @author Nairon.Silva
 */
public class InformacaoProfissionalDaoImpl extends ContaCapitalComumDao implements InformacaoProfissionalDao {

	/**
	 * @see br.com.sicoob.cca.comum.persistencia.dao.interfaces.InformacaoProfissionalDao#consultarInformacaoProfissional(java.lang.Integer)
	 */
	public List<InformacaoProfissionalDTO> consultarInformacaoProfissional(Integer idContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<InformacaoProfissionalDTO> dtos = new ArrayList<InformacaoProfissionalDTO>();
		try {
			comando = getComando("CONSULTARINFORMACAOPROFISSIONAL");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.configurar();
			conexao = this.estabelecerConexaoCorporativa();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				InformacaoProfissionalDTO dto = new InformacaoProfissionalDTO();
				dto.setIdInformacaoProfissional(rs.getInt("IDINFORMACAOPROFISSIONAL"));
				dto.setNumMatricula(rs.getString("NUMMATRICULA"));
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
}
