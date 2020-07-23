/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ConfiguracaoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;

/**
 * A Classe ConfiguracaoCapitalDaoImpl.
 */
public class ConfiguracaoCapitalDaoImpl extends ContaCapitalCadastroCrudDao<ConfiguracaoCapital> implements ConfiguracaoCapitalDao {

	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public ConfiguracaoCapitalDaoImpl(Class<ConfiguracaoCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ConfiguracaoCapitalDao#pesquisarProximoSeqConfiguracaoCapital()
	 */
	public Integer pesquisarProximoSeqConfiguracaoCapital() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Integer idConfiguracaoCapital = null;
		try {
			comando = getComando("PESQUISARPROXIMOSEQCONFIGURACAOCAPITAL");
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			if(rs.next()) {
				idConfiguracaoCapital = rs.getInt("idConfiguracaoCapital");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return idConfiguracaoCapital;
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public ConfiguracaoCapital incluir(ConfiguracaoCapital objeto) throws BancoobException {
		ConfiguracaoCapital oCfg = null;
		try {
			oCfg = super.incluir(objeto);
			getEntityManager().flush();
		} catch (PersistenceException pe) {
			this.getLogger().erro(pe, pe.getMessage());
			throw new BancoobException(pe);
		}
		return oCfg;
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#alterar(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public void alterar(ConfiguracaoCapital objeto) throws BancoobException {
		try {
			super.alterar(objeto);
			getEntityManager().flush();
		} catch (PersistenceException pe) {
			this.getLogger().erro(pe, pe.getMessage());
			throw new BancoobException(pe);
		}
	}
	
	/**
	 * {@link ConfiguracaoCapitalDao#consultarConfiguracaoEstatutaria(Integer)}
	 */
	public List<CondicaoEstatutariaDTO> consultarConfiguracaoEstatutaria(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<CondicaoEstatutariaDTO> lst = new ArrayList<CondicaoEstatutariaDTO>();
		CondicaoEstatutariaDTO dto = null;
		
		try {
			comando = getComando("OBTERCONFESTATUTARIA");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				dto = new CondicaoEstatutariaDTO();
				dto.setIdConfiguracaoCapital(rs.getInt("IDCONFIGURACAOCAPITAL"));
				dto.setNomeAgrupadorConfiguracaoCapital(rs.getString("NOMEAGRUPADORCONFIGURACAOCAPITAL"));
				dto.setNomeConfiguracaoCapital(rs.getString("NOMECONFIGURACAOCAPITAL"));
				dto.setValorConfiguracao(rs.getString("VALORCONFIGURACAO"));
				lst.add(dto); 
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}
