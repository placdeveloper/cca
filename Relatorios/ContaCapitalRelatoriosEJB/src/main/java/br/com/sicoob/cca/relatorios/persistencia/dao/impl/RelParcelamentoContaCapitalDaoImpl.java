/*
 * 
 */
package br.com.sicoob.cca.relatorios.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParcelaContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParcelamentoContaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelParcelamentoContaCapitalDao;

/**
 * DAO Relatorio de Parcelamento Conta Capital
 * @author Marco.Nascimento
 */
public class RelParcelamentoContaCapitalDaoImpl extends ContaCapitalRelatoriosDao implements RelParcelamentoContaCapitalDao {
	
	/**
	 * {@link RelParcelamentoContaCapitalDao#pesquisarParcelamentos(RelParcelamentoContaCapitalDTO)}
	 */
	public List<RelParcelamentoContaCapitalDTO> pesquisarParcelamentos(RelParcelamentoContaCapitalDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<RelParcelamentoContaCapitalDTO> lstDTO = new ArrayList<RelParcelamentoContaCapitalDTO>();
		
		try {
			
			comando = getComando("PESQUISARELATORIOPARCELAMENTOCONTACAPITAL");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			List<RelParcelaContaCapitalDTO> listaParcelas = new ArrayList<RelParcelaContaCapitalDTO>();
			while(rs.next()) {
				RelParcelaContaCapitalDTO retorno = new RelParcelaContaCapitalDTO();
				retorno.setNomePessoa(rs.getString("NOMECOMPLETO"));
				retorno.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				retorno.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
				retorno.setNumParcelamento(rs.getInt("NUMPARCELAMENTO"));
				retorno.setNumParcela(rs.getInt("NUMPARCELA"));
				retorno.setValorParcela(rs.getBigDecimal("VALORPARCELA"));
				retorno.setDescSituacaoParcelamento(rs.getString("DESCSITUACAOPARCELAMENTO"));
				retorno.setDescTipoIntegralizacao(rs.getString("DESCTIPOINTEGRALIZACAO"));
				retorno.setDataVencimentoParcela(new Date(rs.getDate("DATAVENCPARCELA").getTime()));
				retorno.setDataSituacao(new Date(rs.getDate("DATASITUACAOPARCELA").getTime()));
				retorno.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				retorno.setNumContaCorrente(rs.getLong("NUMCONTACORRENTE"));
				listaParcelas.add(retorno);
			}
			
			if(!listaParcelas.isEmpty()) {
				RelParcelamentoContaCapitalDTO dtoRelatorio = new RelParcelamentoContaCapitalDTO();
				dtoRelatorio.setParcelas(listaParcelas);
				lstDTO.add(dtoRelatorio);
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
		
		return lstDTO;
	}
}