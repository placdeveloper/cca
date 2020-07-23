/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.EmprestimoIntegracaoLegadoDao;

/**
 * @author Marco.Nascimento
 */
public class EmprestimoIntegracaoLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements EmprestimoIntegracaoLegadoDao {

	/**
	 * {@link EmprestimoIntegracaoLegadoDao#consultarEmprestimos(Integer)}
	 */
	public List<EmprestimoIntegracaoDTO> consultarEmprestimos(Integer numCliente, Long numContaCorrente) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		EmprestimoIntegracaoDTO dto = null;
		List<EmprestimoIntegracaoDTO> lstRetorno = new ArrayList<EmprestimoIntegracaoDTO>(0); 
		
		try {
			comando = getComando("PESQUISA_EMPRESTIMO");
			comando.adicionarVariavel("numCliente", numCliente);
			comando.adicionarVariavel("numCco", numContaCorrente);
			comando.configurar();
			conexao = this.estabelecerConexao();
			
			ResultSet rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				dto = new EmprestimoIntegracaoDTO();
				dto.setNumContrato(rs.getInt("NUMCONTRATOCREDITO"));
				dto.setNome(rs.getString("DESCMODALIDADEPRODUTO"));
				dto.setDataOperacao(rs.getDate("DataOperacao"));
				dto.setDataVencimento(rs.getDate("DATAVENCOPCRED"));
				dto.setValor(rs.getBigDecimal("ValorContrato"));
				dto.setSaldoDevedor(rs.getBigDecimal("SaldoDevedorContabil"));
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