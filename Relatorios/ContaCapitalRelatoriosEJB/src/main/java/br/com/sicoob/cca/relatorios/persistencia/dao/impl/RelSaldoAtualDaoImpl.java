/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSaldoAtualDao;

/**
 * The Class RelSaldoAtualDaoImpl.
 */
public class RelSaldoAtualDaoImpl extends ContaCapitalRelatoriosDao implements RelSaldoAtualDao {
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSaldoAtualDao#pesquisarSaldoAtual(br.com.sicoob.cca.relatorios.negocio.dto.RelSaldoAtualDTO)
	 */
	public List<RelSaldoAtualDTO> pesquisarSaldoAtual(RelSaldoAtualDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<RelSaldoAtualDTO> lstRetorno = new ArrayList<RelSaldoAtualDTO>();
		
		try {
			
			RelSaldoAtualDTO saldo = null;
			
			comando = getComando("PESQUISASALDOATUAL");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				saldo = new RelSaldoAtualDTO();
				saldo.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				saldo.setDescPA(rs.getString("NOMEPAC"));
				saldo.setNumPA(rs.getInt("NUMPAC"));
				saldo.setValorARealizar(rs.getBigDecimal("VALORSALDOAREALIZAR"));
				saldo.setValorADevolver(rs.getBigDecimal("VALORSALDODEVOL"));
				saldo.setSubscricao(rs.getBigDecimal("VALORSALDOSUBSC"));
				saldo.setIntegralizacao(rs.getBigDecimal("VALORSALDOINTEG"));
				saldo.setNomeCliente(rs.getString("NOMECOMPLETO"));
				saldo.setSituacaoConta(rs.getString("DESCSITUACAOCONTACAPITAL"));
				saldo.setSituacaoAprovacao(rs.getString("DESCSITUACAOAPROVACAOCAPITAL"));
				saldo.setMatriculaFuncionario(rs.getString("NUMMATRICULA"));
				lstRetorno.add(saldo);
			}
		
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return lstRetorno;
	}
}