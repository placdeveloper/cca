package br.com.sicoob.cca.movimentacao.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.QuadroGeralAssociadoDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.DebitoIndeterminadoException;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoDao;
import br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces.DebitoIndeterminadoDao;

/**
 * DAO de debito indeterminado
 * 
 * @author marco.nascimento
 */
public class DebitoIndeterminadoDaoImpl extends ContaCapitalMovimentacaoDao implements DebitoIndeterminadoDao {
	
	/**
	 * {@link DebitoIndeterminadoDao#incluir(DebitoContaCapital)}
	 */
	public DebitoContaCapital incluir(DebitoContaCapital debCCA) throws BancoobException {
		getEntityManager().persist(debCCA);
		getEntityManager().flush();
		return debCCA;
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#alterar(DebitoContaCapital)}
	 */
	public DebitoContaCapital alterar(DebitoContaCapital debCCA) throws BancoobException {
		getEntityManager().merge(debCCA);
		getEntityManager().flush();
		return debCCA;
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#incluirEmLote(DebitoIndeterminadoRenDTO)}
	 */
	public void incluirEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement psIncluir = null;
		PreparedStatement psHistorico = null;
		PreparedStatement psUltimoDebito = null;
		
		try {
			
			conexao = estabelecerConexao();
			
			psIncluir = conexao.prepareStatement(getComando("INCLUIRDEBINDLOTE").getSql());
			psHistorico = conexao.prepareStatement(getComando("INCLUIRHISTDEBINDLOTE").getSql());
			Date dataInicialDeb = new Date(dto.getDataInicialDeb().getTime());
			Timestamp timestampAgora = new Timestamp(new java.util.Date().getTime());
			for(int i = 0; i < dto.getIdsContaCapital().size(); i++) {
				incluirBatch(psIncluir, dto, i, dataInicialDeb, timestampAgora);
			}
			
			int qtdInserts[] = psIncluir.executeBatch();
			
			if(qtdInserts.length > 0) {
				psUltimoDebito = conexao.prepareStatement(getComando("PESQUISAIDULTIMODEBIND").getSql());
				psUltimoDebito.setInt(1, dto.getIdInstituicao());
				rs = psUltimoDebito.executeQuery();
				rs.next();
				Integer ultimoIDDebito = rs.getInt(1);
				
				for(int i = 0; i < qtdInserts.length; i++) {
					psHistorico.setInt(1, ultimoIDDebito - i);
					psHistorico.addBatch();
				}
			}
			
			psHistorico.executeBatch();
			
		} catch (BancoobRuntimeException e) {
			
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (SQLException e) {
			
			if(e.getNextException() != null) {
				this.getLogger().erro(e.getNextException(), e.getNextException().getMessage());
				throw new BancoobException(e.getNextException().getMessage());
			}
			
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharStatement(psUltimoDebito);
			fecharStatement(psIncluir);
			fecharStatement(psHistorico);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * Prepara inclusao batch
	 * @param psIncluir
	 * @param dto
	 * @param index
	 * @param timestampAgora 
	 * @param dataInicialDeb 
	 * @throws BancoobException
	 * @throws SQLException
	 */
	private void incluirBatch(PreparedStatement psIncluir, DebitoIndeterminadoRenDTO dto, Integer index, 
			Date dataInicialDeb, Timestamp timestampAgora) throws BancoobException, SQLException {
		int ix = 1;
		psIncluir.setInt(ix++, dto.getIdsContaCapital().get(index));
		psIncluir.setInt(ix++, dto.getIdInstituicao());
		psIncluir.setInt(ix++, dto.getTipoInteg());
		psIncluir.setInt(ix++, dto.getFormaDebito());
		psIncluir.setInt(ix++, dto.getPeriodoDebito());
		psIncluir.setString(ix++, dto.getIdUsuario());
		psIncluir.setInt(ix++, dto.getNumPeriodo());
		psIncluir.setBigDecimal(ix++, dto.getVlrDebito());
		psIncluir.setDate(ix++, dataInicialDeb);
		psIncluir.setTimestamp(ix++, timestampAgora);
		
		if(dto.getContasCorrente().size() > 0) {
			psIncluir.setLong(ix++,  dto.getContasCorrente().get(index));
		} else {
			psIncluir.setNull(ix++, java.sql.Types.DECIMAL);
		}
		
		psIncluir.setString(ix++, dto.getDescMatriculaFunc());
		psIncluir.addBatch();
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#pesquisar(Integer)}
	 */
	public DebitoContaCapital pesquisar(Long idDebitoContaContaCapital) throws BancoobException {
		DebitoContaCapital debCCA = getEntityManager().find(DebitoContaCapital.class, idDebitoContaContaCapital);
		if(debCCA != null) {
			Hibernate.initialize(debCCA.getContaCapital());
		}
		return debCCA;
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#pesquisar(Integer)}
	 */
	public DebitoContaCapital pesquisarPorNumMatricula(Integer numMatricula, Integer idInstituicao) throws BancoobException {
		DebitoContaCapital deb = null;
		try {
			
			Query query = getEntityManager().createNativeQuery(getComando("PESQUISARDEBITOINDETERMINADOPORNUMMATRICULA").getSql(), DebitoContaCapital.class);
			query.setParameter("numMatricula", numMatricula);
			query.setParameter("idInstituicao", idInstituicao);
			
			deb = (DebitoContaCapital) query.getSingleResult();
			
		} catch (NoResultException nre) {
			return null;
		}
		return deb;
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#pesquisarDebitoContaCapital(Integer, Integer)}
	 */
	public DebitoContaCapital pesquisarDebitoContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException {
		DebitoContaCapital deb = null;
		try {
			
			Query query = getEntityManager().createNativeQuery(getComando("PESQUISARDEBITOINDETERMINADO").getSql(), DebitoContaCapital.class);
			query.setParameter("idContaCapital", idContaCapital);
			query.setParameter("idInstituicao", idInstituicao);
			
			deb = (DebitoContaCapital) query.getSingleResult();
			
		} catch (NoResultException nre) {
			return null;
		} catch (NonUniqueResultException noe) {
			throw new DebitoIndeterminadoException("MSG_DEB_JACADASTRADO", noe);
		}
		
		return deb;
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#pesquisarQuadroGeralAssociados(Integer)}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQuadroGeralAssociados(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<QuadroGeralAssociadoDTO> lst = new ArrayList<QuadroGeralAssociadoDTO>(1);
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARQUADRODEBINDETERMINADO");
			
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			QuadroGeralAssociadoDTO dto;
			while(rs.next()) {
				dto = new QuadroGeralAssociadoDTO();
				dto.setTipoPessoa(rs.getString("TIPO_PESSOA"));
				dto.setQtdPessoasComDebito(rs.getInt("COM_DEB_IND"));
				dto.setQtdPessoasSemDebito(rs.getInt("SEM_DEB_IND"));
				dto.setTotalAssociados(dto.getQtdPessoasComDebito() + dto.getQtdPessoasSemDebito());
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link DebitoIndeterminadoDao#pesquisarQtdDebCCODiaFixo(Integer)}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCODiaFixo(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<QuadroGeralAssociadoDTO> lst = new ArrayList<QuadroGeralAssociadoDTO>(1);
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARDEBDIACCO");
			
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			QuadroGeralAssociadoDTO dto;
			while(rs.next()) {
				dto = new QuadroGeralAssociadoDTO();
				dto.setDia(rs.getInt("DIA"));
				dto.setQtdPorDiaFixo(rs.getInt("QTD"));
				dto.setValorTotalPorDiaFixo(rs.getBigDecimal("SOMA"));
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link DebitoIndeterminadoDao#pesquisarQtdDebCCOIntervalo(Integer)}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebCCOIntervalo(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<QuadroGeralAssociadoDTO> lst = new ArrayList<QuadroGeralAssociadoDTO>(1);
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARDEBINTERVALOCCO");
			
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			QuadroGeralAssociadoDTO dto;
			while(rs.next()) {
				dto = new QuadroGeralAssociadoDTO();
				dto.setIntervaloDias(rs.getInt("NUMPERIODO"));
				dto.setQtdIntervalo(rs.getInt("QTD"));
				dto.setValorTotalIntervalo(rs.getBigDecimal("SOMA"));
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link DebitoIndeterminadoDao#pesquisarQtdDebFolhaBanco(Integer)}
	 */
	public List<QuadroGeralAssociadoDTO> pesquisarQtdDebFolhaBanco(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<QuadroGeralAssociadoDTO> lst = new ArrayList<QuadroGeralAssociadoDTO>(1);
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARDEBFOLHABANCO");
			
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			QuadroGeralAssociadoDTO dto;
			while(rs.next()) {
				dto = new QuadroGeralAssociadoDTO();
				dto.setDescFormaCalculo(rs.getString("FORMA_CALCULO"));
				dto.setQtdDebitos(rs.getInt("QTD"));
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#pesquisar(ConsultaDebitoIndeterminadoRenDTO)}
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> pesquisar(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<ConsultaDebitoIndeterminadoRenDTO> lst = new ArrayList<ConsultaDebitoIndeterminadoRenDTO>();
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARDEBINDETERMINADO");
			comando.adicionarVariavel("filtro", filtro);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			ConsultaDebitoIndeterminadoRenDTO dto;
			while(rs.next()) {
				dto = new ConsultaDebitoIndeterminadoRenDTO();
				dto.setIdDebitoContaCapital(rs.getInt("IDDEBITOCONTACAPITAL"));
				dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
				dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				dto.setNome(rs.getString("NOMECOMPLETO"));
				dto.setTipoPessoa(rs.getString("TIPOPESSOA"));
				dto.setFormaDebito(rs.getString("FORMADEBITO"));
				dto.setValor(rs.getBigDecimal("VALOR"));
				dto.setDataPeriodoDeb(rs.getString("DATAPERIODODEB"));
				dto.setIdTipoValorDebito(rs.getInt("IDTIPOVALORDEBITO"));
				dto.setIdTipoInteg(rs.getInt("IDTIPOINTEGRALIZACAO"));
				dto.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#pesquisarAssociadosSemDebitoCCO(ConsultaDebitoIndeterminadoRenDTO)}
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> pesquisarAssociadosSemDebitoCCO(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<ConsultaDebitoIndeterminadoRenDTO> lst = new ArrayList<ConsultaDebitoIndeterminadoRenDTO>();
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARCCASEMDEBITOINDETERMINADO");
			comando.adicionarVariavel("filtro", filtro);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			ConsultaDebitoIndeterminadoRenDTO dto;
			while(rs.next()) {
				dto = new ConsultaDebitoIndeterminadoRenDTO();
				dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
				dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				dto.setNumContaCorrente(rs.getLong("NUMCONTACORRENTE"));
				dto.setNome(rs.getString("NOMECOMPLETO"));
				dto.setTipoPessoa(rs.getString("TIPOPESSOA"));
				dto.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#pesquisarAssociadosSemDebitoFolhaBanco(ConsultaDebitoIndeterminadoRenDTO)}
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> pesquisarAssociadosSemDebitoFolhaBanco(ConsultaDebitoIndeterminadoRenDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		List<ConsultaDebitoIndeterminadoRenDTO> lst = new ArrayList<ConsultaDebitoIndeterminadoRenDTO>();
		try {
			
			conexao = estabelecerConexao();
			comando = getComando("PESQUISARCCASEMDEBITOINDETERMINADOFOLHABANCO");
			comando.adicionarVariavel("filtro", filtro);
			
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			ConsultaDebitoIndeterminadoRenDTO dto;
			while(rs.next()) {
				dto = new ConsultaDebitoIndeterminadoRenDTO();
				dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
				dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				dto.setNome(rs.getString("NOMECOMPLETO"));
				dto.setCpfCnpj(rs.getString("CNPJ"));
				dto.setNomeEmpresa(rs.getString("RAZAOSOCIAL"));
				dto.setNumMatriculaFunc(rs.getString("NUMMATRICULAFUNC"));
				lst.add(dto);
			}
			
			return lst;
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
			
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * {@link DebitoIndeterminadoDao#excluirEmLote(DebitoIndeterminadoRenDTO)}
	 */
	public void excluirEmLote(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			conexao = estabelecerConexao();
			
			ps = conexao.prepareStatement(getComando("EXCLUIRDEBINDLOTE").getSql());
			for(int i = 0; i < dto.getIdsDebitoContaCapital().size(); i++) {
				ps.setInt(1, dto.getIdsDebitoContaCapital().get(i));
				ps.addBatch();
			}
			
			ps.executeBatch();
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			if(e.getNextException() != null) {
				this.getLogger().erro(e.getNextException(), e.getNextException().getMessage());
				throw new BancoobException(e.getNextException().getMessage());
			}
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharStatement(ps);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link DebitoIndeterminadoDao#alterarEmLote(DebitoIndeterminadoRenDTO, Integer)}
	 */
	public void alterarEmLote(DebitoIndeterminadoRenDTO dto, Integer tipoAlteracao) throws BancoobException {
		if(ContaCapitalConstantes.TIPO_ALTERACAO_VALOR.equals(tipoAlteracao)) {
			alterarEmLotePorValor(dto);
		} else if (ContaCapitalConstantes.TIPO_ALTERACAO_DATA.equals(tipoAlteracao)) {
			alterarEmLotePorData(dto);
		} else if(ContaCapitalConstantes.TIPO_ALTERACAO_VALOR_DATA.equals(tipoAlteracao)) {
			alterarEmLotePorValorData(dto);
		}
	}
	
	/**
	 * Alteracao em lote de valor do debito
	 * @param dto
	 * @throws BancoobException
	 */
	private void alterarEmLotePorValor(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		PreparedStatement psHistorico = null;
		
		try {
			conexao = estabelecerConexao();
			
			boolean isValorPercentual = dto.getPercentual() != null && dto.getPercentual().compareTo(BigDecimal.ZERO) > 0; 
			
			if(isValorPercentual) {
				comando = getComando("ALTERARVALORPERCENTUALDEBINDLOTE");
			} else {
				comando = getComando("ALTERARVALORDEBINDLOTE");
			}
			ps = conexao.prepareStatement(comando.getSql());
			psHistorico = conexao.prepareStatement(getComando("INCLUIRHISTDEBINDLOTE").getSql());
			
			Timestamp dataHoraAlteracao = new Timestamp(new java.util.Date().getTime());
			for(int i = 0; i < dto.getIdsDebitoContaCapital().size(); i++) {
				int ix = 1;
				ps.setBigDecimal(ix++, isValorPercentual ? dto.getPercentual() : dto.getVlrDebito());
				ps.setTimestamp(ix++, dataHoraAlteracao);
				ps.setString(ix++, dto.getIdUsuario());
				ps.setInt(ix++, dto.getIdsDebitoContaCapital().get(i));
				
				ps.addBatch();
				
				psHistorico.setInt(1, dto.getIdsDebitoContaCapital().get(i));
				psHistorico.addBatch();
			}
			
			ps.executeBatch();
			psHistorico.executeBatch();
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			if(e.getNextException() != null) {
				this.getLogger().erro(e.getNextException(), e.getNextException().getMessage());
				throw new BancoobException(e.getNextException().getMessage());
			}
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharStatement(ps);
			fecharStatement(psHistorico);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * Alteracao em lote de data do debito
	 * @param dto
	 * @throws BancoobException
	 */
	private void alterarEmLotePorData(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		PreparedStatement psHistorico = null;
		
		try {
			conexao = estabelecerConexao();
			
			ps = conexao.prepareStatement(getComando("ALTERARDATADEBINDLOTE").getSql());
			psHistorico = conexao.prepareStatement(getComando("INCLUIRHISTDEBINDLOTE").getSql());
			
			Timestamp dataHoraAlteracao = new Timestamp(new java.util.Date().getTime());
			for(int i = 0; i < dto.getIdsDebitoContaCapital().size(); i++) {
				int ix = 1;
				ps.setDate(ix++, new Date(dto.getDataInicialDeb().getTime()));
				ps.setTimestamp(ix++, dataHoraAlteracao);
				ps.setString(ix++, dto.getIdUsuario());
				ps.setInt(ix++, dto.getIdsDebitoContaCapital().get(i));
				
				ps.addBatch();
				
				psHistorico.setInt(1, dto.getIdsDebitoContaCapital().get(i));
				psHistorico.addBatch();
			}
			
			ps.executeBatch();
			psHistorico.executeBatch();
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			if(e.getNextException() != null) {
				this.getLogger().erro(e.getNextException(), e.getNextException().getMessage());
				throw new BancoobException(e.getNextException().getMessage());
			}
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharStatement(ps);
			fecharStatement(psHistorico);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	/**
	 * Alteracao em lote de valor e data do debito
	 * @param dto
	 * @throws BancoobException
	 */
	private void alterarEmLotePorValorData(DebitoIndeterminadoRenDTO dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		PreparedStatement psHistorico = null;
		
		try {
			conexao = estabelecerConexao();
			
			boolean isValorPercentual = dto.getPercentual() != null && dto.getPercentual().compareTo(BigDecimal.ZERO) > 0; 
			
			if(isValorPercentual) {
				comando = getComando("ALTERARVALORPERCENTUALDATADEBINDLOTE");
			} else {
				comando = getComando("ALTERARVALORDATADEBINDLOTE");
			}
			ps = conexao.prepareStatement(comando.getSql());
			psHistorico = conexao.prepareStatement(getComando("INCLUIRHISTDEBINDLOTE").getSql());
			
			Timestamp dataHoraAlteracao = new Timestamp(new java.util.Date().getTime());
			for(int i = 0; i < dto.getIdsDebitoContaCapital().size(); i++) {
				int ix = 1;
				ps.setBigDecimal(ix++, isValorPercentual ? dto.getPercentual() : dto.getVlrDebito());
				ps.setDate(ix++, new Date(dto.getDataInicialDeb().getTime()));
				ps.setTimestamp(ix++, dataHoraAlteracao);
				ps.setString(ix++, dto.getIdUsuario());
				ps.setInt(ix++, dto.getIdsDebitoContaCapital().get(i));
				
				ps.addBatch();
				
				psHistorico.setInt(1, dto.getIdsDebitoContaCapital().get(i));
				psHistorico.addBatch();
			}
			
			ps.executeBatch();
			psHistorico.executeBatch();
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			if(e.getNextException() != null) {
				this.getLogger().erro(e.getNextException(), e.getNextException().getMessage());
				throw new BancoobException(e.getNextException().getMessage());
			}
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharStatement(ps);
			fecharStatement(psHistorico);
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
}