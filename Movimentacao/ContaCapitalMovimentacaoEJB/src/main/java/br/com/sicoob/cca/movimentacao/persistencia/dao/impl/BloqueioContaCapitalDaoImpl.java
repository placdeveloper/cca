/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrigemBloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.BloqueioContaCapitalDao;

// TODO: Auto-generated Javadoc
/**
 * A Classe BloqueioContaCapitalDaoImpl.
 *
 * @author Antonio.Genaro
 */
public class BloqueioContaCapitalDaoImpl extends ContaCapitalMovimentacaoCrudDao<BloqueioCapital> implements BloqueioContaCapitalDao {
	
	/**
	 * Instancia um novo BloqueioContaCapitalDaoImpl.
	 *
	 * @param clazz o valor de clazz
	 * @param nomeComandoPesquisar o valor de nome comando pesquisar
	 */
	public BloqueioContaCapitalDaoImpl(Class<BloqueioCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
	/**
	 * {@link BloqueioContaCapitalDao#consultarValorBloqueadoViaTransferenciaCapital(Integer, Date)}.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param dataProduto o valor de data produto
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal consultarValorBloqueadoViaTransferenciaCapital(Integer idContaCapital, Date dataProduto) throws BancoobException {
		BigDecimal valorBloqueado = BigDecimal.ZERO;
		
		Query query = getEntityManager().createQuery("FROM br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital " +
				" WHERE IDORIGEMBLOQUEIOCAPITAL = 1 AND BOLATIVO = 1  " +
				" AND VARCHAR_FORMAT(DATAFIMBLOQUEIO, 'YYYYMMDD') > VARCHAR_FORMAT(:dataProduto, 'YYYYMMDD') " + 
				" AND IDCONTACAPITAL = :idContaCapital ");
		
		query.setParameter("idContaCapital", idContaCapital);
		query.setParameter("dataProduto", ContaCapitalUtil.formatarDataUS(dataProduto));
		
		List<BloqueioCapital> lista = query.getResultList();
		for (BloqueioCapital item:lista){
			valorBloqueado = valorBloqueado.add(item.getValorBloqueio());			
		}
		
		return valorBloqueado;
	}

	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.BloqueioContaCapitalDao#consultarValorBloqueadoPorTipo(java.lang.Integer, java.util.Date, br.com.sicoob.cca.entidades.negocio.enums.EnumOrigemBloqueioCapital)
	 */
	public BigDecimal consultarValorBloqueadoPorTipo(Integer idContaCapital, Date dataProduto, EnumOrigemBloqueioCapital tipoBloqueio) throws BancoobException {
		BigDecimal valorBloqueado = BigDecimal.ZERO;
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		try {
			comando = getComando("CONSULTARVALORBLOQUEADO");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.adicionarVariavel("dataProduto", ContaCapitalUtil.formatarDataUS(dataProduto));
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				int idOrigemBloqueioCapital = rs.getInt("IDORIGEMBLOQUEIOCAPITAL");
				if (tipoBloqueio == null || tipoBloqueio.getCodigo().intValue() == idOrigemBloqueioCapital) {
					BigDecimal valorBloqueio = rs.getBigDecimal("VALORBLOQUEIO");
					valorBloqueado = valorBloqueado.add(valorBloqueio);
				}
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return valorBloqueado;
	}
	
	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.BloqueioContaCapitalDao#consultarValorBloqueado(java.lang.Integer, java.util.Date)
	 */
	public BigDecimal consultarValorBloqueado(Integer idContaCapital, Date dataProduto) throws BancoobException {
		return consultarValorBloqueadoPorTipo(idContaCapital, dataProduto, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BloqueioContaCapitalDTO> consultarBloqueios(BloqueioContaCapitalDTO filtro, Date dataProduto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<BloqueioContaCapitalDTO> lst = new ArrayList<BloqueioContaCapitalDTO>();
		
		try {
			comando = getComando("CONSULTARBLOQUEIOS");
			comando.adicionarVariavel("filtro", filtro);
			comando.adicionarVariavel("dataProduto", ContaCapitalUtil.formatarDataUS(dataProduto));
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				BloqueioContaCapitalDTO dto = new BloqueioContaCapitalDTO();
				dto.setIdBloqueio(rs.getInt("IDBLOQUEIOCAPITAL"));
				dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
				dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				dto.setNomePessoa(rs.getString("NOMECOMPLETO"));
				dto.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				dto.setDataBloqueio(new DateTimeDB(rs.getDate("DATAINICIOBLOQUEIO").getTime()));
				dto.setValorBloqueado(rs.getBigDecimal("VALORBLOQUEIO"));
				dto.setIdTipoBloqueio(rs.getInt("IDORIGEMBLOQUEIOCAPITAL"));
				dto.setNomeTipoBloqueio(rs.getString("NOMEORIGEMBLOQUEIO"));
				java.sql.Date dataProtocolo = rs.getDate("DATAPROTOCOLO");
				if (dataProtocolo != null) {
					dto.setDataProtocolo(new DateTimeDB(dataProtocolo.getTime()));
				}
				dto.setNumProtocolo(rs.getString("NUMPROTOCOLO"));
				dto.setNumProcesso(rs.getString("NUMPROCESSO"));
				verificaAtivo(dto, rs, dataProduto);
				
				lst.add(dto); 
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return lst;
	}

	private void verificaAtivo(BloqueioContaCapitalDTO dto, ResultSet rs, Date dataProduto) throws SQLException {
		final Integer bolAtivoConstant = 1;
		java.sql.Date sqlDataFimTermino = rs.getDate("DATAFIMBLOQUEIO");
		Integer bolAtivo = rs.getInt("BOLATIVO");
		if (sqlDataFimTermino != null) {
			DateTimeDB dataFimBloqueio = new DateTimeDB(sqlDataFimTermino.getTime());
			dto.setDataDesbloqueio(dataFimBloqueio);
			dto.setAtivo(bolAtivoConstant.equals(bolAtivo) && dataFimBloqueio.after(dataProduto));
		} else {
			dto.setAtivo(bolAtivoConstant.equals(bolAtivo));
		}
	}	
	/**
	 * @see br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.BloqueioContaCapitalDao#excluirBloqueioCapital(java.lang.Integer)
	 */

	public void excluirBloqueioCapital(Integer idBloqueioCapital) throws BancoobException {
		Connection conexao = null;
		try {
			conexao = estabelecerConexao();
			Comando comando = getComando("EXCLUIRBLOQUEIO");
			
			String sqlText = comando.getSql();
			String[] sqls = sqlText.split(";");
			for (String sql : sqls) {
				if (!ContaCapitalUtil.isStringVazia(sql)) {
					Comando comandoInvidual = new Comando();
					comandoInvidual.setSql(sql);
					comandoInvidual.adicionarVariavel("idBloqueioCapital", idBloqueioCapital);
					comandoInvidual.configurar();
					comandoInvidual.executarAtualizacao(conexao);
					fecharComando(comandoInvidual);
				}
			}
		} catch (PersistenciaException e) {
			throw new BancoobException(e);
		} finally {
			fecharConexao(conexao);
		}
	}	
	
}
