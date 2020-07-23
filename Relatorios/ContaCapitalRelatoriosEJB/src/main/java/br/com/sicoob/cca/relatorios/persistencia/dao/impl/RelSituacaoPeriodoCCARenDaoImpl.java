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
import br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoPeriodoCCARenDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSituacaoPeriodoCCARenDao;


/**
 * The Class RelSituacaoPeriodoCCARenDaoImpl.
 */
public class RelSituacaoPeriodoCCARenDaoImpl extends ContaCapitalRelatoriosDao implements RelSituacaoPeriodoCCARenDao {
	
	/** The Constant PESQUISA_SITUACAO. */
	private static final String PESQUISA_SITUACAO = "PESQUISASITUACAOPERIODOCCAREN";
	
	/* (non-Javadoc)
	 * @see br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelSituacaoPeriodoCCARenDao#pesquisarSituacaoCCAPorPeriodo(br.com.sicoob.cca.relatorios.negocio.dto.RelSituacaoPeriodoCCARenDTO)
	 */
	public List<RelSituacaoPeriodoCCARenDTO> pesquisarSituacaoCCAPorPeriodo(RelSituacaoPeriodoCCARenDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<RelSituacaoPeriodoCCARenDTO> dadosRelatorio = new ArrayList<RelSituacaoPeriodoCCARenDTO>();
		try {	
			comando = getComando(PESQUISA_SITUACAO);
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				RelSituacaoPeriodoCCARenDTO relSituacaoPeriodoCCARenDTO = new RelSituacaoPeriodoCCARenDTO();
				relSituacaoPeriodoCCARenDTO.setNumMatricula(rs.getInt("MATRICULA"));
				relSituacaoPeriodoCCARenDTO.setNomeCliente(rs.getString("NOMECOMPLETO"));
				relSituacaoPeriodoCCARenDTO.setSituacao(rs.getString("SITUACAO"));

				if(rs.getDate("DATAMATRICULA") != null) {
					relSituacaoPeriodoCCARenDTO.setDataMatricula(new DateTimeDB(rs.getDate("DATAMATRICULA").getTime()));
				}
				if(rs.getDate("DATASAIDA") != null) {
					relSituacaoPeriodoCCARenDTO.setDataSaida(new DateTimeDB(rs.getDate("DATASAIDA").getTime()));
				}
				if(rs.getDate("DATAHORAATUALIZACAO") != null) {
					relSituacaoPeriodoCCARenDTO.setDataSituacao(new DateTimeDB(rs.getDate("DATAHORAATUALIZACAO").getTime()));
				}
				
				dadosRelatorio.add(relSituacaoPeriodoCCARenDTO);
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