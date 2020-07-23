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
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.PesquisaEmpresaDTO;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.PesquisaEmpresaLegadoDao;

/**
 * @author Marco.Nascimento
 */
public class PesquisaEmpresaLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements PesquisaEmpresaLegadoDao {

	/**
	 * {@link PesquisaEmpresaDao#pesquisar(PesquisaContaCapitalDTO)}
	 */
	public List<PesquisaEmpresaDTO> pesquisar(PesquisaEmpresaDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PesquisaEmpresaDTO dto = null;
		List<PesquisaEmpresaDTO> lstRetorno = new ArrayList<PesquisaEmpresaDTO>(0); 
		
		try {
			comando = getComando("CONSULTA_LANC_FOLHA_PJ");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = this.estabelecerConexao(filtro.getNumCoop());
			
			ResultSet rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				dto = new PesquisaEmpresaDTO();
				dto.setNumCGC_CPF(rs.getString("NumCGC_CPF"));
				dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
				dto.setDiaFolha(rs.getInt("DiaRodaFolha"));
				dto.setQtdeDiasGeraInf(rs.getInt("QtdeDiasGeraInf"));
				dto.setNumPessoaJuridica(rs.getInt("NumPessoaJuridica"));
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