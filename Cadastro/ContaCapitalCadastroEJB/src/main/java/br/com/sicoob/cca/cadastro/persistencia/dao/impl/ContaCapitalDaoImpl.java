/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.DadosDesligamentoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.FiltroContaCapitalDTO;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;

/**
 * @author marco.nascimento
 */
public class ContaCapitalDaoImpl extends ContaCapitalCadastroCrudDao<ContaCapital> implements ContaCapitalDao {

	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public ContaCapitalDaoImpl(Class<ContaCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
	/**
	 * {@link ContaCapitalDao#pesquisarContaCapitalAtiva(Integer, Integer)}
	 */
	public Boolean pesquisarContaCapitalAtiva(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("PESQUISARCONTACAPITALATIVA").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("idPessoa", idPessoa);
		return ((Integer) query.getSingleResult()) > 0;
	}
	
	/**
	 * {@link ContaCapitalDao#pesquisarContaCapitalInativa(Integer, Integer)}
	 */
	public Boolean pesquisarContaCapitalInativa(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("PESQUISARCONTACAPITALINATIVA").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("idPessoa", idPessoa);
		return ((Integer) query.getSingleResult()) > 0;
	}
	
	/**
	 * {@link ContaCapitalDao#pesquisarContaCapital(Integer, Integer)}
	 */
	public Boolean pesquisarContaCapital(Integer idInstituicao, Integer numContaCapital) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("PESQUISARCONTACAPITAL").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("numContaCapital", numContaCapital);
		return ((Integer) query.getSingleResult()) > 0;
	}
	
	/**
	 * {@link ContaCapitalDao#pesquisarContaCapitalPessoa(Integer, Integer)}
	 */
	public Integer pesquisarContaCapitalPessoa(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		Query query;
		
		try {
			query = getEntityManager().createNativeQuery(getComando("PESQUISARCONTACAPITALPESSOA").getSql());
			query.setParameter("idInstituicao", idInstituicao);
			query.setParameter("idPessoa", idPessoa);
			return (Integer) query.getSingleResult();
			
		} catch (NonUniqueResultException e) {
			getLogger().erro(e, "Cliente com mais de uma conta capital cadastrada para a instituicao[" + idInstituicao + "]" + ", idPessoa[" + idPessoa + "]" );
			throw new BancoobException(e);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * {@link ContaCapitalDao#naturezaJuridicaPermitida(Short)}
	 */
	public Boolean naturezaJuridicaPermitida(Short codigoNatJur) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("NATUREZAJURIDICAPERMITIDA").getSql());
		query.setParameter("codigoNatJur", codigoNatJur);
		return ((Integer) query.getSingleResult()) == 0;
	}
	
	/**
	 * {@link ContaCapitalDao#cnaePermitido(String)}
	 */
	public Boolean cnaePermitido(String codigoCnae) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("CNAEPERMITIDO").getSql());
		query.setParameter("codigoCnae", codigoCnae);
		return ((Integer) query.getSingleResult()) == 0;
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#obter(java.io.Serializable)
	 */
	@Override
	public ContaCapital obter(Serializable chave) throws BancoobException {
		ContaCapital cca = super.obter(chave);
		if(cca != null && cca.getId() != null) {
			Hibernate.initialize(cca.getHistorico());
			Hibernate.initialize(cca.getDocumentos());
		}
		return cca;
	}
	
	/**
	 * {@link CadastroContaCapitalRenDao#pesquisar(CadastroContaCapitalRenDTO)}
	 */
	public List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<CadastroContaCapitalRenDTO> lst = new ArrayList<CadastroContaCapitalRenDTO>();
		CadastroContaCapitalRenDTO dto = null;
		
		try {
			comando = getComando("PESQUISARCONTACAPITALREN");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				dto = new CadastroContaCapitalRenDTO();
				dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
				dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				dto.setDescSituacaoAprovacaoCapital(rs.getString("DESCSITUACAOAPROVACAOCAPITAL"));
				dto.setDescSituacaoContaCapital(rs.getString("DESCSITUACAOCONTACAPITAL"));
				dto.setNomePessoa(rs.getString("NOMEPESSOA"));
				dto.setNomeCompleto(rs.getString("NOMECOMPLETO"));
				dto.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				dto.setDataHoraAtualizacao(new DateTimeDB(rs.getDate("DATAHORAATUALIZACAO").getTime()));
				dto.setDataMatricula(new DateTimeDB(rs.getDate("DATAMATRICULA").getTime()));
				dto.setIdSituacaoContaCapital(rs.getInt("IDSITUACAOCONTACAPITAL"));
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
	
	/**
	 * {@link CadastroContaCapitalRenDao#pesquisarLancamentosContaCapital(Integer, Integer))}
	 */
	public Integer pesquisarLancamentosContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Integer qtd = null;
		
		try {
			comando = getComando("PESQUISARLANCAMENTOSCONTACAPITALREN");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				qtd = rs.getInt("QTD");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return qtd;
	}
	
	/**
	 * {@link CadastroContaCapitalRenDao#pesquisarParcelamentosContaCapital(Integer))}
	 */
	public Integer pesquisarParcelamentosContaCapital(Integer idContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Integer qtd = null;
		
		try {
			comando = getComando("PESQUISARPARCELAMENTOSCONTACAPITALREN");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				qtd = rs.getInt("QTD");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return qtd;
	}

	/**
	 * {@link ContaCapitalDao#excluir(Integer)}
	 */
	public void excluir(Integer idContaCapital) throws BancoobException {
		excluirDocumento(idContaCapital);
		excluirProposta(idContaCapital);
		excluirDebIndeterminado(idContaCapital);
		excluirCCA(idContaCapital);
	}
	
	/**
	 * {@link ContaCapitalDao#pesquisarClienteCadastrado(Integer, Integer)}
	 */
	public Boolean pesquisarClienteCadastrado(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("PESQUISARCLIENTECADASTRADO").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		query.setParameter("idPessoa", idPessoa);
		return ((Integer) query.getSingleResult()) > 0;
	}
	
	private void excluirDebIndeterminado(Integer idContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("EXCLUIRDEBCCA");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.configurar();
			conexao = estabelecerConexao();
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	private void excluirDocumento(Integer idContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("EXCLUIRDOCCCA");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.configurar();
			conexao = estabelecerConexao();
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	private void excluirProposta(Integer idContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("EXCLUIRPROPOSTA");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.configurar();
			conexao = estabelecerConexao();
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}
	
	private void excluirCCA(Integer idContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		try {
			comando = getComando("EXCLUIRCCA");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.configurar();
			conexao = estabelecerConexao();
			comando.executarAtualizacao(conexao);
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
	}

	/**
	 * {@link ContaCapitalDao#obterResumo(Integer)}
	 */
	public ContaCapitalResumoDTO obterResumo(Integer idContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		ContaCapitalResumoDTO dto = null;
		
		try {
			comando = getComando("OBTERRESUMOCONTACAPITAL");
			comando.adicionarVariavel("idContaCapital", idContaCapital);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				dto = criarResumoDTO(rs);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dto;
	}
	
	/**
	 * {@link ContaCapitalDao#obterResumo(Integer, Integer)}
	 */
	public ContaCapitalResumoDTO obterResumo(Integer idInstituicao, Integer numContaCapital) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		ContaCapitalResumoDTO dto = null;
		
		try {
			comando = getComando("OBTERRESUMOCONTACAPITAL");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("numContaCapital", numContaCapital);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			if (rs.next()) {
				dto = criarResumoDTO(rs);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dto;
	}

	private ContaCapitalResumoDTO criarResumoDTO(ResultSet rs) throws SQLException {
		ContaCapitalResumoDTO dto;
		dto = new ContaCapitalResumoDTO();
		dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
		dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
		dto.setIdSituacaoContaCapital(rs.getInt("IDSITUACAOCONTACAPITAL"));
		dto.setIdPessoa(rs.getInt("IDPESSOA"));
		dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
		dto.setValorInteg(rs.getBigDecimal("VALORSALDOINTEG"));
		dto.setValorSubs(rs.getBigDecimal("VALORSALDOSUBSC"));
		dto.setValorDevol(rs.getBigDecimal("VALORSALDODEVOL"));
		dto.setValorBloq(rs.getBigDecimal("VALORSALDOBLOQ"));
		return dto;
	}
	
	/**
	 * {@link ContaCapitalDao#obterResumos(List)}
	 */
	@SuppressWarnings("unchecked")
	public List<ContaCapitalResumoDTO> obterResumos(List<Integer> idsContaCapital) throws BancoobException {
		Comando comando = null;
		List<ContaCapitalResumoDTO> dtos = new ArrayList<ContaCapitalResumoDTO>();
		final int tamMaxLote = 1000;
		int qtdTotal = idsContaCapital.size();
		int inicio = 0;
		int fim = (qtdTotal > tamMaxLote) ? tamMaxLote : qtdTotal;
		
		try {
			comando = getComando("OBTERRESUMOSCONTACAPITAL");
			comando.configurar();
			do {
				List<Integer> subListaIds = idsContaCapital.subList(inicio, fim);
				List<Object[]> resultList = comando.criarQuery(getEntityManager()).setParameter("idsContaCapital", subListaIds).getResultList();
				if (resultList != null) {
					for (Object[] obj : resultList) {
						ContaCapitalResumoDTO dto = new ContaCapitalResumoDTO();
						int i=0;
						dto.setIdContaCapital(((Number) obj[i++]).intValue());
						dto.setIdInstituicao(((Number) obj[i++]).intValue());
						dto.setIdSituacaoContaCapital(((Number) obj[i++]).intValue());
						dto.setNumContaCapital(((Number) obj[i++]).intValue());
						dtos.add(dto);
					}
				}
				inicio = fim;
				fim += ((qtdTotal - fim) > tamMaxLote) ? tamMaxLote : (qtdTotal - fim);
			} while (inicio < qtdTotal);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
		}
		return dtos;
	}
	
	/**
	 * {@link ContaCapitalDao#obterContaCapitalPorPessoa(List)}
	 */
	public List<CadastroContaCapitalRenDTO> obterContaCapitalPorPessoa(List<PessoaIntegracaoDTO> lstPessoas) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<CadastroContaCapitalRenDTO> lstRetorno = new ArrayList<CadastroContaCapitalRenDTO>();
		
		try {
		
			/** Verificar performance em producao, em caso de lentidao utilizar idInstituicao na query */
			Integer idPessoa = lstPessoas.get(0).getIdPessoa();
			
			if(idPessoa != null) {
				comando = getComando("OBTERCONTACAPITALPORPESSOA");
				comando.adicionarVariavel("idPessoa", idPessoa);
				
				comando.configurar();
				conexao = estabelecerConexao();
				conexao.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				rs = comando.executarConsulta(conexao);
				
				CadastroContaCapitalRenDTO dto = null;
				while(rs.next()) {
					dto = new CadastroContaCapitalRenDTO();
					dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
					dto.setIdPessoa(rs.getInt("IDPESSOA"));
					dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
					dto.setDataMatricula(new DateTimeDB(rs.getDate("DATAMATRICULA").getTime()));
					dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
					dto.setNumCoop(rs.getInt("NUMCOOPERATIVA"));
					
					lstRetorno.add(dto);
				}
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return lstRetorno;
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao#obterIdInstituicaoPorIdContaCapital(java.lang.Integer)
	 */
	public Integer obterIdInstituicaoPorIdContaCapital(Integer idContaCapital) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("OBTERIDINSTITUICAOPORIDCONTACAPITAL").getSql());
		query.setParameter("idContaCapital", idContaCapital);
		return (Integer) query.getSingleResult();
	}
	
	/**
	 * Obtem maior numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterMaiorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("OBTERMAIORCONTACAPITALATIVA").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		return (Integer) query.getSingleResult();
	}
	
	/**
	 *
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterMenorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("OBTERMENORCONTACAPITALATIVA").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		return (Integer) query.getSingleResult();
	}
	
	/**
	 * Obter maior conta capital por instituicao.
	 *
	 * @param idInstituicao the id instituicao
	 * @return the integer
	 * @throws BancoobException the bancoob exception
	 */
	public Integer obterMaiorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("OBTERMAIORCONTACAPITAL").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		return (Integer) query.getSingleResult();
	}
	
	/**
	 * Obter menor conta capital por instituicao.
	 *
	 * @param idInstituicao the id instituicao
	 * @return the integer
	 * @throws BancoobException the bancoob exception
	 */
	public Integer obterMenorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException {
		Query query = getEntityManager().createNativeQuery(getComando("OBTERMENORCONTACAPITAL").getSql());
		query.setParameter("idInstituicao", idInstituicao);
		return (Integer) query.getSingleResult();
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao#obterDadosDesligamento(br.com.sicoob.cca.cadastro.negocio.dto.FiltroContaCapitalDTO)
	 */
	public List<DadosDesligamentoDTO> obterDadosDesligamento(FiltroContaCapitalDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<DadosDesligamentoDTO> dados = new ArrayList<DadosDesligamentoDTO>();
		try {
			comando = getComando("OBTERDADOSDESLIGAMENTO");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			while (rs.next()) {
				DadosDesligamentoDTO dto = new DadosDesligamentoDTO();
				dto.setDataSaida(rs.getDate("DATASAIDA"));
				dto.setDescSituacaoContaCapital(rs.getString("DESCSITUACAOCONTACAPITAL"));
				dto.setDescDadosDesligamento(rs.getString("DESCDADOSDESLIGAMENTO"));
				dados.add(dto);
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return dados;
	}
}