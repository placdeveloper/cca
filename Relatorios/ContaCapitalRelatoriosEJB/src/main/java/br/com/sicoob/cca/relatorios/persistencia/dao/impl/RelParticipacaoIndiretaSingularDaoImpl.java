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
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelParticipacaoIndiretaSingularDao;

/**
 * DAO de participação indireta
 * @author Sron.Cruz
 */
public class RelParticipacaoIndiretaSingularDaoImpl extends ContaCapitalRelatoriosDao implements RelParticipacaoIndiretaSingularDao {
	
	/**
	 * {@link RelParticipacaoIndiretaSingularDao#listarRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO))
	 */
	public List<RelParticipacaoIndiretaSingularDTO> listarRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<RelParticipacaoIndiretaSingularDTO> listaRetorno = new ArrayList<RelParticipacaoIndiretaSingularDTO>();
		
		try {
			comando = getComando("LISTARRELPARTICIPACAOINDIRETA");
			
			//Pesquisa todos os meses do ano
			if(filtro.getMes().intValue() == 0) {
				comando.adicionarVariavel("anoMesInicio", filtro.getAno() + "01");
				comando.adicionarVariavel("anoMesFim", filtro.getAno() + "12");
			} else {
				comando.adicionarVariavel("numAnoMesBase", filtro.getAnoMesBase());
			}
			
			comando.adicionarVariavel("idInstituicaoCentral", filtro.getNumCentral());
			comando.adicionarVariavel("idInstituicaoSingular", filtro.getNumCooperativa());			
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()){
				RelParticipacaoIndiretaSingularDTO retorno = new RelParticipacaoIndiretaSingularDTO();
				retorno.setIdInstituicaoCentral(rs.getInt("IDINSTITUICAOCENTRAL"));
				retorno.setNumInstituicaoCentral(rs.getInt("NUMINSTITUICAOCENTRAL"));
				retorno.setNomeInstituicaoCentral(rs.getString("NOMEINSTITUICAOCENTRAL"));
				retorno.setIdInstituicaoSingular(rs.getInt("IDINSTITUICAOSINGULAR"));
				retorno.setNumInstituicaoSingular(rs.getInt("NUMINSTITUICAOSINGULAR"));
				retorno.setNomeInstituicaoSingular(rs.getString("NOMEINSTITUICAOSINGULAR"));
				retorno.setAnoMesBase(rs.getInt("ANOMESBASE"));
				retorno.setValorSaldoInteg(rs.getBigDecimal("VALORSALDOINTEG"));
				retorno.setPercParticipacao(rs.getBigDecimal("PERCPARTICIPACAO"));
				retorno.setValorParticipacaoBancoob(rs.getBigDecimal("VALORPARTICIPACAOBANCOOB"));
				retorno.setValorParticipacao(rs.getBigDecimal("VALORPARTICIPACAO"));
				listaRetorno.add(retorno);
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
		
		return listaRetorno;
	}
}