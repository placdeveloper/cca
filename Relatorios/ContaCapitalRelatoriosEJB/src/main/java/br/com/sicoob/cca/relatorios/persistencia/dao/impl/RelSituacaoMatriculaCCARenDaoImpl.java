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
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoMatriculaCCARenDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSituacaoMatriculaCCARenDao;


/**
 * The Class RelSituacaoMatriculaCCARenDaoImpl.
 */
public class RelSituacaoMatriculaCCARenDaoImpl extends ContaCapitalRelatoriosDao implements RelSituacaoMatriculaCCARenDao {
	
	private static final String PESQUISA_SITUACAO = "PESQUISASITUACAOMATRICULACCAREN";
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSituacaoMatriculaCCARenDao#pesquisarSituacaoCCAPorMatricula(br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoMatriculaCCARenDTO)
	 */
	public List<RelSituacaoMatriculaCCARenDTO> pesquisarSituacaoCCAPorMatricula(RelSituacaoMatriculaCCARenDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<RelSituacaoMatriculaCCARenDTO> dadosRelatorio = new ArrayList<RelSituacaoMatriculaCCARenDTO>();
		try {	
			comando = getComando(PESQUISA_SITUACAO);
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				RelSituacaoMatriculaCCARenDTO relSituacaoMatriculaCCARenDTO = new RelSituacaoMatriculaCCARenDTO();
				relSituacaoMatriculaCCARenDTO.setNumMatricula(rs.getInt("MATRICULA"));
				relSituacaoMatriculaCCARenDTO.setNomeCliente(rs.getString("NOMECOMPLETO"));
				relSituacaoMatriculaCCARenDTO.setSituacao(rs.getString("DESCSITUACAOCONTACAPITAL"));

				if(rs.getDate("DATAMATRICULA") != null) {
					relSituacaoMatriculaCCARenDTO.setDataMatricula(new DateTimeDB(rs.getDate("DATAMATRICULA").getTime()));
				}
				if(rs.getDate("DATASAIDA") != null) {
					relSituacaoMatriculaCCARenDTO.setDataSaida(new DateTimeDB(rs.getDate("DATASAIDA").getTime()));
				}
				if(rs.getDate("DATAHORAATUALIZACAO") != null) {
					relSituacaoMatriculaCCARenDTO.setDataSituacao(new DateTimeDB(rs.getDate("DATAHORAATUALIZACAO").getTime()));
				}
				
				dadosRelatorio.add(relSituacaoMatriculaCCARenDTO);
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
		
		return dadosRelatorio;
	}
}