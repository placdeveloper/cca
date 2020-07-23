/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.util.ContaCapitalUtil;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelFichaMatriculaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoCrudDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ContaCapitalLegadoDao;
import br.com.sicoob.tipos.DateTime;

/**
 * @author Marco.Nascimento
 */
public class ContaCapitalLegadoDaoImpl extends ContaCapitalIntegracaoLegadoCrudDao<ContaCapitalLegado> implements
		ContaCapitalLegadoDao {

	/** A constante MSG_ERRO_RECUPERAR_DADOS. */
	private static final String MSG_ERRO_RECUPERAR_DADOS = "Erro ao recuperar dados";
	private static final Integer TAMANHO_MIN_CCA = 8;
	
	/** O atributo instituicaoIntegracao. */
	private InstituicaoIntegracaoDelegate instituicaoIntegracao = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	
	/**
	 * Constructor
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public ContaCapitalLegadoDaoImpl(Class<ContaCapitalLegado> clazz,String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
	/**
	 * {@link ContaCapitalLegadoDao#obterUltimaMatricula()}
	 */
	public Integer obterUltimaMatricula() throws BancoobException {

		Integer numMatricula = 0;
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			comando = getComando("OBTERULTIMAMATRICULA");
			comando.configurar();
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			
			if(rs.next()) {
				numMatricula = rs.getInt(1);
			}
			
			if(numMatricula != null && numMatricula.toString().length() > TAMANHO_MIN_CCA){
				numMatricula = obterIntervaloNovaContaCapitalLegado();	
			}			
			

		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}		
		
		return numMatricula;			
	}
	
	/**
	 * Obter o intervalo entre os numeros conta capital
	 * @param idInstituicao
	 * @return intervalo
	 * @throws BancoobException
	 */
	private Integer obterIntervaloNovaContaCapitalLegado() throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Integer novaContaCapital = null;
		
		try {
			comando = getComando("NOVAMATRICULALEGADO");
			comando.configurar();
			conexao = estabelecerConexao();
			
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				novaContaCapital = rs.getInt("MATRICULA");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return novaContaCapital;
	}	
	
	
	/**
	 * {@link ContaCapitalLegadoDao#listarRelFicha(Integer, Integer)}
	 */
	public List<DadosRelFichaMatriculaDTO> listarRelFicha(Integer matricula, Integer iAplicCoopDif)
			throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<DadosRelFichaMatriculaDTO> dados = new ArrayList<DadosRelFichaMatriculaDTO>();
		
		try {
			comando = getComando("GERAR_REL_FICHA_MATRIC");
			comando.adicionarVariavel("matricula", matricula);
			comando.adicionarVariavel("iAplicCoopDif", iAplicCoopDif);
			comando.adicionarVariavel("COD_GRUPO_HIST_INTEGRALIZACAO", ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO);
			comando.adicionarVariavel("COD_GRUPO_HIST_SUBSCRICAO", ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO);
			comando.adicionarVariavel("COD_TIPO_PES_FISICA", ContaCapitalConstantes.COD_TIPO_PES_FISICA);
			comando.adicionarVariavel("COD_TIPO_PES_JURIDICA", ContaCapitalConstantes.COD_TIPO_PES_JURIDICA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_CAIXA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_FOLHA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_COBRANCA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_COBRANCA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_MIGRACAO", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_MIGRACAO);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_RATEIO", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RATEIO);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_RESERVA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RESERVA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_CONTA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA);
			comando.adicionarVariavel("COD_PARCELA_GERADA", ContaCapitalConstantes.COD_PARCELA_GERADA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_CAIXA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CAIXA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_CONTA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CONTA);
			comando.adicionarVariavel("COD_PARCELA_CANCELADA", ContaCapitalConstantes.COD_PARCELA_CANCELADA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_CHADMIN", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CHADMIN);
			comando.adicionarVariavel("COD_PARCELA_EXCLUIDA", ContaCapitalConstantes.COD_PARCELA_EXCLUIDA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_FOLHA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_FOLHA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_COBRANCA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_COBRANCA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_RATEIO", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_RATEIO);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_RESERVA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_RESERVA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_ANTES_MIGRACAO", ContaCapitalConstantes.COD_PARCELA_PAGA_ANTES_MIGRACAO);
			comando.adicionarVariavel("LST_TIPO_PROFISSAO", ContaCapitalConstantes.LST_TIPO_PROFISSAO);
			comando.adicionarVariavel("COD_TIPO_AMBAS", ContaCapitalConstantes.COD_TIPO_AMBAS);			
			comando.adicionarVariavel("LST_TIPO_ESTADO_CIVIL", ContaCapitalConstantes.LST_TIPO_ESTADO_CIVIL);
			
			comando.configurar();
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			DadosRelFichaMatriculaDTO vo;
			while(rs.next()){
				vo = preencherFichaMatric(rs);
				dados.add(vo);				
			}
			return dados;

		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_ERRO_RECUPERAR_DADOS, e);
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());			
			throw new ContaCapitalIntegracaoLegadoException(MSG_ERRO_RECUPERAR_DADOS, e);		
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
	}	
	
	/**
	 * Preenche DTO de ficha matricula
	 * @param rs
	 * @return
	 * @throws BancoobException
	 */
	private DadosRelFichaMatriculaDTO preencherFichaMatric(ResultSet rs) throws BancoobException {
		DadosRelFichaMatriculaDTO dto = new DadosRelFichaMatriculaDTO();
		try {
			dto.setValorSaldoAtuInteg(rs.getBigDecimal("ValorSaldoAtuInteg"));
			dto.setValorLancInteg(rs.getBigDecimal("ValorLancInteg"));
			dto.setValorSaldoAtuSubsc(rs.getBigDecimal("ValorSaldoAtuSubsc"));
			dto.setValorLancSubsc(rs.getBigDecimal("ValorLancSubsc"));
			dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
			dto.setDataNascimento(new DateTime(rs.getDate("DataNascimento").getTime()));
			dto.setDescNacionalidade(rs.getString("DescNacionalidade"));
			dto.setDescNaturalidade(rs.getString("DescNaturalidade"));
			dto.setNumRG(rs.getString("NumRG"));
			dto.setDescOrgaoExpRG(rs.getString("DescOrgaoExpRG"));
			dto.setDescUfOrgExpRG(rs.getString("DescUfOrgExpRG"));
			dto.setDataEmissaoRG(new DateTime(rs.getDate("DataEmissaoRG").getTime()));
			dto.setCodEstadoCivil(rs.getInt("CodEstadoCivil"));
			dto.setCodTipoSexo(rs.getInt("CodTipoSexo"));
			dto.setCodProfissao(rs.getLong("codprofissao"));
			dto.setDesclistaitem(rs.getString("desclistaitem"));
			dto.setCodTipoEndereco(rs.getInt("CodTipoEndereco"));
			dto.setEnderecoResidencial(rs.getString("EnderecoResidencial"));
			dto.setBairroResidencial(rs.getString("BairroResidencial"));
			dto.setCidadeResidencial(rs.getString("CidadeResidencial"));
			dto.setUfResidencial(rs.getString("UFResidencial"));
			dto.setCepResidencial(rs.getString("CepResidencial"));
			dto.setDddResidencial(rs.getString("DDDResidencial"));
			dto.setNumTelefoneResidencial(rs.getString("NumTelefoneResidencial"));
			dto.setRamalResidencial(rs.getString("RamalResidencial"));
			dto.setEnderecoComercial(rs.getString("EnderecoComercial"));
			dto.setBairroComercial(rs.getString("BairroComercial"));
			dto.setCidadeComercial(rs.getString("CidadeComercial"));
			dto.setUfComercial(rs.getString("UFComercial"));
			dto.setCepComercial(rs.getString("CepComercial"));
			dto.setDddComercial(rs.getString("DDDComercial"));
			dto.setNumTelefoneComercial(rs.getString("NumTelefoneComercial"));
			dto.setRamalcomercial(rs.getString("Ramalcomercial"));
			dto.setBolEnvioCorrespondencia(rs.getBoolean("BolEnvioCorrespondencia"));
			dto.setDescApelidoPessoa(rs.getString("DescApelidoPessoa"));
			dto.setNumCgcCpf(rs.getString("NumCGC_CPF"));
			dto.setNumPessoa(rs.getLong("NumPessoa"));
			dto.setsNumPessoa(rs.getString("sNumPessoa"));
			dto.setNumInscEstadual(rs.getString("NumInscEstadual"));			
			dto.setCodPfPj(rs.getInt("CodPF_PJ"));
			dto.setNumParcelamento(rs.getInt("NumParcelamento"));
			dto.setNumParcela(rs.getInt("NumParcela"));			
			dto.setValorParcela(rs.getBigDecimal("ValorParcela"));
			dto.setModoLanc(rs.getString("ModoLanc"));
			dto.setSituacao(rs.getString("Situacao"));
			dto.setDescCondicaoAssociacao(rs.getString("DescCondicaoAssociacao"));
			
			if(rs.getDate("DataConstituicao") != null){
				dto.setDataConstituicao(new DateTime(rs.getDate("DataConstituicao").getTime()));								
			}			
			if(rs.getDate("DataVencParcela") != null){
				dto.setDataVencParcela(new DateTime(rs.getDate("DataVencParcela").getTime()));
			}			
				
		} catch (SQLException e) {
			throw new ContaCapitalIntegracaoLegadoException("Erro ao preencher dados", e);
		} catch (PersistenciaException e) {
			throw new ContaCapitalIntegracaoLegadoException("Erro ao preencher dados", e);
		}
		return dto;
	}
	
	/**
	 * {@link ContaCapitalLegadoDao#listarRelFichaAdmissao(Integer, Integer)}
	 */
	public List<DadosRelFichaMatriculaDTO> listarRelFichaAdmissao(Integer idPessoa, Integer iAplicCoopDif) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<DadosRelFichaMatriculaDTO> dados = new ArrayList<DadosRelFichaMatriculaDTO>();
		
		try {
			comando = getComando("GERAR_REL_FICHA_ADMISSAO");
			comando.adicionarVariavel("numPessoa", idPessoa);
			comando.adicionarVariavel("iAplicCoopDif", iAplicCoopDif);
			comando.adicionarVariavel("COD_GRUPO_HIST_INTEGRALIZACAO", ContaCapitalConstantes.COD_GRUPO_HIST_INTEGRALIZACAO);
			comando.adicionarVariavel("COD_GRUPO_HIST_SUBSCRICAO", ContaCapitalConstantes.COD_GRUPO_HIST_SUBSCRICAO);
			comando.adicionarVariavel("COD_TIPO_PES_FISICA", ContaCapitalConstantes.COD_TIPO_PES_FISICA);
			comando.adicionarVariavel("COD_TIPO_PES_JURIDICA", ContaCapitalConstantes.COD_TIPO_PES_JURIDICA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_CAIXA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_FOLHA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_COBRANCA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_COBRANCA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_MIGRACAO", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_MIGRACAO);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_RATEIO", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RATEIO);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_RESERVA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RESERVA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_CONTA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA);
			comando.adicionarVariavel("COD_PARCELA_GERADA", ContaCapitalConstantes.COD_PARCELA_GERADA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_CAIXA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CAIXA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_CONTA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CONTA);
			comando.adicionarVariavel("COD_PARCELA_CANCELADA", ContaCapitalConstantes.COD_PARCELA_CANCELADA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_CHADMIN", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_CHADMIN);
			comando.adicionarVariavel("COD_PARCELA_EXCLUIDA", ContaCapitalConstantes.COD_PARCELA_EXCLUIDA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_FOLHA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_FOLHA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_COBRANCA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_COBRANCA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_RATEIO", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_RATEIO);
			comando.adicionarVariavel("COD_PARCELA_PAGA_VIA_RESERVA", ContaCapitalConstantes.COD_PARCELA_PAGA_VIA_RESERVA);
			comando.adicionarVariavel("COD_PARCELA_PAGA_ANTES_MIGRACAO", ContaCapitalConstantes.COD_PARCELA_PAGA_ANTES_MIGRACAO);
			comando.adicionarVariavel("LST_TIPO_PROFISSAO", ContaCapitalConstantes.LST_TIPO_PROFISSAO);
			comando.adicionarVariavel("COD_TIPO_AMBAS", ContaCapitalConstantes.COD_TIPO_AMBAS);
			comando.adicionarVariavel("LST_TIPO_ESTADO_CIVIL", ContaCapitalConstantes.LST_TIPO_ESTADO_CIVIL);			
			
			comando.configurar();
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			DadosRelFichaMatriculaDTO dto;
			while(rs.next()){
				dto = preencherFichaAdmissao(rs);
				dados.add(dto);				
			}
			return dados;

		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_ERRO_RECUPERAR_DADOS, e);
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());			
			throw new ContaCapitalIntegracaoLegadoException(MSG_ERRO_RECUPERAR_DADOS, e);		
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
	}
	
	/**
	 * Preenche DTO com dados necessarios para ficha de admissao
	 * @param rs
	 * @return
	 * @throws BancoobException
	 */
	private DadosRelFichaMatriculaDTO preencherFichaAdmissao(ResultSet rs) throws BancoobException {
		DadosRelFichaMatriculaDTO dto = new DadosRelFichaMatriculaDTO();
		try {
			dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
			dto.setDataNascimento(new DateTime(rs.getDate("DataNascimento").getTime()));
			dto.setDescNacionalidade(rs.getString("DescNacionalidade"));
			dto.setDescNaturalidade(rs.getString("DescNaturalidade"));
			dto.setNumRG(rs.getString("NumRG"));
			dto.setDescOrgaoExpRG(rs.getString("DescOrgaoExpRG"));
			dto.setDescUfOrgExpRG(rs.getString("DescUfOrgExpRG"));
			dto.setDataEmissaoRG(new DateTime(rs.getDate("DataEmissaoRG").getTime()));
			dto.setCodEstadoCivil(rs.getInt("CodEstadoCivil"));
			dto.setCodTipoSexo(rs.getInt("CodTipoSexo"));
			dto.setCodProfissao(rs.getLong("codprofissao"));
			dto.setDesclistaitem(rs.getString("desclistaitem"));
			dto.setCodTipoEndereco(rs.getInt("CodTipoEndereco"));
			dto.setEnderecoResidencial(rs.getString("EnderecoResidencial"));
			dto.setBairroResidencial(rs.getString("BairroResidencial"));
			dto.setCidadeResidencial(rs.getString("CidadeResidencial"));
			dto.setUfResidencial(rs.getString("UFResidencial"));
			dto.setCepResidencial(rs.getString("CepResidencial"));
			dto.setDddResidencial(rs.getString("DDDResidencial"));
			dto.setNumTelefoneResidencial(rs.getString("NumTelefoneResidencial"));
			dto.setRamalResidencial(rs.getString("RamalResidencial"));
			dto.setEnderecoComercial(rs.getString("EnderecoComercial"));
			dto.setBairroComercial(rs.getString("BairroComercial"));
			dto.setCidadeComercial(rs.getString("CidadeComercial"));
			dto.setUfComercial(rs.getString("UFComercial"));
			dto.setCepComercial(rs.getString("CepComercial"));
			dto.setDddComercial(rs.getString("DDDComercial"));
			dto.setNumTelefoneComercial(rs.getString("NumTelefoneComercial"));
			dto.setRamalcomercial(rs.getString("Ramalcomercial"));
			dto.setBolEnvioCorrespondencia(rs.getBoolean("BolEnvioCorrespondencia"));
			dto.setDescApelidoPessoa(rs.getString("DescApelidoPessoa"));
			dto.setNumCgcCpf(rs.getString("NumCGC_CPF"));
			dto.setNumPessoa(rs.getLong("NumPessoa"));
			dto.setsNumPessoa(rs.getString("sNumPessoa"));
			dto.setNumInscEstadual(rs.getString("NumInscEstadual"));
			dto.setCodPfPj(rs.getInt("CodPF_PJ"));
			dto.setDescCondicaoAssociacao(rs.getString("DescCondicaoAssociacao"));
			dto.setDescEstadoCivil(rs.getString("DescEstadoCivil"));
			
			if(rs.getDate("DataConstituicao") != null) {
				dto.setDataConstituicao(new DateTime(rs.getDate("DataConstituicao").getTime()));
			}
			
		} catch (SQLException e) {
			throw new ContaCapitalIntegracaoLegadoException("Erro ao preencher dados", e);
		} catch (PersistenciaException e) {
			throw new ContaCapitalIntegracaoLegadoException("Erro ao preencher dados", e);
		}
		return dto;
	}
	
	/**
	 * {@link ContaCapitalLegadoDao#DadosRelExtratoLegadoDTO(RelExtratoLegadoDTO)}
	 */
	public List<DadosRelExtratoLegadoDTO> listarRelExtrato(RelExtratoLegadoDTO relExtratoDTO)
			throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		DadosRelExtratoLegadoDTO dado = null;
		List<DadosRelExtratoLegadoDTO> dados = new ArrayList<DadosRelExtratoLegadoDTO>();
		
		try {
			comando = getComando("GERAR_CCA_EXTRATO");
			
			comando.adicionarVariavel("numMatricula", relExtratoDTO.getNumMatricula());
			getLogger().info("CCA numMatricula:" + relExtratoDTO.getNumMatricula());
			
			comando.adicionarVariavel("dataInicio",  ContaCapitalUtil.formatarDataMascara(relExtratoDTO.getDataInicio(), "yyyy-MM-dd"));
			getLogger().info("CCA dataInicio:" + ContaCapitalUtil.formatarDataMascara(relExtratoDTO.getDataInicio(), "yyyy-MM-dd"));
			
			comando.adicionarVariavel("dataFim",  ContaCapitalUtil.formatarDataMascara(relExtratoDTO.getDataFim(), "yyyy-MM-dd"));
			getLogger().info("CCA dataFim:" + ContaCapitalUtil.formatarDataMascara(relExtratoDTO.getDataFim(), "yyyy-MM-dd"));

			comando.configurar();
			getLogger().info("CCA Comando SQL em uso:" + comando.getSqlEmUso());
			
			conn = estabelecerConexao();
			getLogger().info("CCA CONEXAO DatabaseProductName:" + conn.getMetaData().getDatabaseProductName());
			getLogger().info("CCA CONEXAO URL:" + conn.getMetaData().getURL());
			getLogger().info("CCA CONEXAO STR:" + conn.getMetaData().getConnection().toString());
			
			rs = comando.executarStoredProcedure(conn);
			while(rs.next()) {
				dado = preencherRelExtrato(rs);
				dados.add(dado);	
			}
			return dados;

		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_ERRO_RECUPERAR_DADOS, e);
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());			
			throw new ContaCapitalIntegracaoLegadoException(MSG_ERRO_RECUPERAR_DADOS, e);		
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
	}		
	
	/**
	 * Preenche DTO com dados necessarios para gerar extrato
	 * @param rs
	 * @return
	 * @throws BancoobException
	 */
	private DadosRelExtratoLegadoDTO preencherRelExtrato(ResultSet rs) throws BancoobException {
		DadosRelExtratoLegadoDTO dto = new DadosRelExtratoLegadoDTO();
		try {			
			dto.setNumMatricula(rs.getLong("NumMatricula"));
			dto.setValorSaldoBloqInt(rs.getBigDecimal("ValorSaldoBloqInt"));
			dto.setDescNomePessoa(rs.getString("DescNomePessoa"));
			dto.setCodSituacao(rs.getInt("CodSituacao"));
			dto.setEnderecoResidencial(rs.getString("EnderecoResidencial"));
			dto.setBairroResidencial(rs.getString("BairroResidencial"));
			dto.setCidadeResidencial(rs.getString("CidadeResidencial"));
			dto.setUfResidencial(rs.getString("UFResidencial"));
			dto.setCepResidencial(rs.getString("CepResidencial"));
			dto.setDddResidencial(rs.getString("DDDResidencial"));
			dto.setNumTelefoneResidencial(rs.getString("NumTelefoneResidencial"));
			dto.setNumCGC_CPF(rs.getString("NumCGC_CPF"));
			dto.setDescHistorico(rs.getString("DescHistorico"));
			dto.setCodGrupoHist(rs.getInt("CodGrupoHist"));
			dto.setDataLote(rs.getDate("DataLote"));
			dto.setValorCredito(rs.getBigDecimal("ValorCredito"));
			dto.setValorDebito(rs.getBigDecimal("ValorDebito"));
			dto.setSaldoAtual(rs.getBigDecimal("SaldoAtual"));
			dto.setDescMatriculaFunc(rs.getString("DescMatriculaFunc"));
			dto.setEmpresa(rs.getString("Empresa"));
			dto.setDepartamento(rs.getString("Departamento"));
			
			String numTelCompleto = "";
			if(rs.getString("DDDResidencial") != null && !rs.getString("DDDResidencial").equals("")){
				numTelCompleto = "("+rs.getString("DDDResidencial")+")";
			}
			if(rs.getString("NumTelefoneResidencial") != null && !rs.getString("NumTelefoneResidencial").equals("")){
				numTelCompleto = numTelCompleto + rs.getString("NumTelefoneResidencial");
			}		
			dto.setNumTelefoneFormatado(numTelCompleto);	
			
		} catch (SQLException e) {
			throw new ContaCapitalIntegracaoLegadoException("Erro ao preencher dados", e);
		} catch (PersistenciaException e) {
			throw new ContaCapitalIntegracaoLegadoException("Erro ao preencher dados", e);
		}
		return dto;
	}	
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public ContaCapitalLegado incluir(ContaCapitalLegado objeto) throws BancoobException {
		
		if(objeto.getNumCoop() != null && objeto.getNumCoop().intValue() > 0) {
			
			Comando comando = null;
			Connection conexao = null;
			try {
				
				conexao = estabelecerConexao(objeto.getNumCoop());
				comando = getComando("INCLUIRCONTACAPITALLEGADO");
				
				comando.adicionarVariavel("obj", objeto);
				comando.adicionarVariavel("dataMatriculaFMT", DataUtil.converterDateToString(objeto.getDataMatricula(), "yyyy-MM-dd"));
				
				comando.configurar();
				
				comando.executarAtualizacao(conexao);
				
			} catch (PersistenciaException e) {
				this.getLogger().erro(e, e.getMessage());
				throw new BancoobException(e);
			} finally {
				fecharComando(comando);
				fecharConexao(conexao);
			}
			
			return null;
		}
		
		
		return super.incluir(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean verificarContaCapitalCadastrada(Integer numCoop, Integer numMatricula) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		try {
			
			conexao = estabelecerConexao(numCoop);
			comando = getComando("OBTERCONTACAPITALMATRICULA");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.configurar();
			
			rs = comando.executarConsulta(conexao);
			if(rs.next()) {
				return rs.getInt(1) > 0;
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
		
		return true;
	}

	/**
	 * {@link ContaCapitalLegadoDao#excluir(numMatricula, numCoop)}
	 */
	public void excluir(Integer numMatricula, Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		
		try {
			conexao = estabelecerConexao(numCoop);
			
			comando = getComando("EXCLUIRCADASTROCONTACAPITAL");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.adicionarVariavel("idContaCapitalNaoReplica", ContaCapitalConstantes.ID_CONTACAPITAL_DELETE_NAOREPLICA);
			
			comando.configurar();
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
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ContaCapitalLegadoDao#obterContaCapitalCooperativa(java.lang.Integer, java.lang.Integer)
	 */
	public List<ContaCapitalLegado> obterContaCapitalCooperativaCliente(Integer numCoop, Integer numCliente,Integer situacao) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ContaCapitalLegado> listContaCapitalLegado = new ArrayList<ContaCapitalLegado>();
		
		try {
			conn = estabelecerConexao(numCoop);
			comando = getComando("OBTERCONTACAPITALCLIENTE");
			comando.adicionarVariavel("numCliente", numCliente);
			comando.adicionarVariavel("situacao", situacao);
			comando.configurar();
			
			rs = comando.executarConsulta(conn);
			
			while(rs.next()) {
				ContaCapitalLegado contaCapitalLegado = new ContaCapitalLegado();
				
				contaCapitalLegado.setNumCoop(numCoop);

				if (rs.getObject("idContaCapital") != null){				
					contaCapitalLegado.setIdContaCapital(rs.getInt("idContaCapital"));
				}
				
				if (rs.getDate("dataMatricula")!= null){
					contaCapitalLegado.setDataMatricula(new DateTimeDB(rs.getDate("dataMatricula").getTime()));
				}
				
				if (rs.getDate("dataSaida")!= null){
					contaCapitalLegado.setDataSaida(new DateTimeDB(rs.getDate("dataSaida").getTime()));
				}

				contaCapitalLegado.setBolDebIndeterminado(rs.getBoolean("bolDebIndeterminado"));
				contaCapitalLegado.setBolDireitoVoto(rs.getBoolean("BolDireitoVoto"));
				contaCapitalLegado.setBolParticipaRateio(rs.getBoolean("bolParticipaRateio"));
				
				contaCapitalLegado.setNumMatricula(rs.getInt("numMatricula"));
				contaCapitalLegado.setNumCliente(rs.getInt("numCliente"));
				contaCapitalLegado.setCodSituacao(rs.getInt("codSituacao"));				
				contaCapitalLegado.setValorSaldoAtuSubsc(rs.getBigDecimal("valorSaldoAtuSubsc"));				
				contaCapitalLegado.setValorSaldoAtuInteg(rs.getBigDecimal("valorSaldoAtuInteg"));
				contaCapitalLegado.setValorSaldoAtuDevolver(rs.getBigDecimal("valorSaldoAtuDevolver"));
				contaCapitalLegado.setValorSaldoBloqInt(rs.getBigDecimal("valorSaldoBloqInt"));				
				
				listContaCapitalLegado.add(contaCapitalLegado);	
			}

			return listContaCapitalLegado;
			
		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (PersistenciaException e) {
			getLogger().erro(e, e.getMessage());			
			throw new BancoobException(e);		
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
		
		
	}
	
	/**
	 * {@link CadastroContaCapitalRenDao#obterNovaContaCapital(Integer)}
	 */
	public Integer obterNovaContaCapital(Integer idInstituicao) throws BancoobException {
		
		if(idInstituicao == null || idInstituicao.intValue() == 0) {
			BancoobException ex = new BancoobException("MSG_024");
			getLogger().erro(ex, ex.getMessage());
			throw ex;
		}
		
		/**
		 * Nova Conta Capital DB2
		 * Query q = getEntityManager().createNativeQuery("select max(numContaCapital)+1 from cca.contacapital where idInstituicao = :idInstituicao");
		 * q.setParameter("idInstituicao", idInstituicao);
		 * Integer numContaCapital = (Integer) q.getSingleResult(); 
		 */
		Integer numContaCapital = obterNovaContaCapitalLegado(idInstituicao);
		
		//Caso seja a primeira matricula da instituicao
		if(numContaCapital == null) {
			numContaCapital = 1;
		}
		
		return numContaCapital;
	}
	
	/**
	 * Obtem novo numero de conta capital
	 * Utiliza a instituicao como parametro para os casos de uma central ou confederação estar realizando o cadastro para a singular
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private Integer obterNovaContaCapitalLegado(Integer idInstituicao) throws BancoobException {
		Integer resultado = 1;
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		Integer numCoop = null;
		
		try {
			numCoop = instituicaoIntegracao.obterNumeroCooperativa(idInstituicao);
			
			comando = getComando("OBTER_ULTIMA_MATRICULA");
			comando.configurar();
			conn = estabelecerConexao(numCoop);
			rs = comando.executarConsulta(conn);
			
			if(rs.next()) {
				resultado = rs.getInt("Matricula");
				if(resultado != null) {
					resultado++;
				}
			}
			
			if(resultado != null && resultado.toString().length() > TAMANHO_MIN_CCA){
				resultado = obterIntervaloNovaContaCapitalLegado(numCoop);	
			}
			
		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (BancoobException e) {
			getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conn);
		}
		
		return resultado;
	}
	
	/**
	 * Ontem intervalo entre os numeros conta capital
	 * @param idInstituicao
	 * @return intervalo
	 * @throws BancoobException
	 */
	private Integer obterIntervaloNovaContaCapitalLegado(Integer numCoop) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		Integer novaContaCapital = null;
		
		try {
			comando = getComando("NOVAMATRICULALEGADO");
			comando.configurar();
			conexao = estabelecerConexao(numCoop);
			
			rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				novaContaCapital = rs.getInt("MATRICULA");
			}
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return novaContaCapital;
	}
	
	/**
	 * {@link ContaCapitalLegadoDao#excluirDebIndeterminadoEmLote(numMatriculas)}
	 */
	public void excluirDebIndeterminadoEmLote(List<Integer> numMatriculas) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			
			conexao = estabelecerConexao();
			
			ps = conexao.prepareStatement(getComando("EXCLUIRDEBINDEMLOTE").getSql());
			for(int i = 0; i < numMatriculas.size(); i++) {
				ps.setInt(1, numMatriculas.get(i));
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
	 * {@link ContaCapitalLegadoDao#atualizarDebIndeterminadoEmLote(List)}
	 */
	public void atualizarDebIndeterminadoEmLote(List<ContaCapitalLegado> lstAtulizacao) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		
		try {
			
			conexao = estabelecerConexao();
			
			ps = conexao.prepareStatement(getComando("ALTERARDEBINDEMLOTE").getSql());
			for(int i = 0; i < lstAtulizacao.size(); i++) {
				int ix = 1;
				ps.setBoolean(ix++, lstAtulizacao.get(i).getBolDebIndeterminado());
				ps.setBigDecimal(ix++, lstAtulizacao.get(i).getValorDeb());
				ps.setDate(ix++, new Date(lstAtulizacao.get(i).getDataVencimentoDeb().getTime()));
				ps.setInt(ix++, lstAtulizacao.get(i).getCodFormaDeb());
				ps.setInt(ix++, lstAtulizacao.get(i).getPeriodoProxDeb());
				ps.setInt(ix++, lstAtulizacao.get(i).getTipoPeriodoDeb());
				ps.setInt(ix++, lstAtulizacao.get(i).getCodTipoValorDebito());
				
				if(lstAtulizacao.get(i).getNumContaCorrente() != null && lstAtulizacao.get(i).getNumContaCorrente().longValue() > 0) {
					ps.setInt(ix++, lstAtulizacao.get(i).getNumContaCorrente().intValue());
				} else {
					ps.setNull(ix++, java.sql.Types.INTEGER);
				}
				
				ps.setInt(ix++, lstAtulizacao.get(i).getNumMatricula());
				
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
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ContaCapitalLegadoDao#alterarDebIndeterminadoEmLote(java.util.List, java.lang.Integer, java.math.BigDecimal)
	 */
	public void alterarDebIndeterminadoEmLote(List<ContaCapitalLegado> lst, Integer tipoAlteracao,
			BigDecimal percentual) throws BancoobException {
		if(ContaCapitalConstantes.TIPO_ALTERACAO_VALOR.equals(tipoAlteracao)) {
			alterarEmLotePorValor(lst, percentual);
		} else if (ContaCapitalConstantes.TIPO_ALTERACAO_DATA.equals(tipoAlteracao)) {
			alterarEmLotePorData(lst);
		} else if(ContaCapitalConstantes.TIPO_ALTERACAO_VALOR_DATA.equals(tipoAlteracao)) {
			alterarEmLotePorValorData(lst, percentual);
		}
	}
	
	private void alterarEmLotePorValor(List<ContaCapitalLegado> lst, BigDecimal percentual) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		try {
			conexao = estabelecerConexao();
			boolean isValorPercentual = percentual != null && percentual.compareTo(BigDecimal.ZERO) > 0; 
			if(isValorPercentual) {
				comando = getComando("ALTERARVALORPERCENTUALDEBINDLOTE");
			} else {
				comando = getComando("ALTERARVALORDEBINDLOTE");
			}
			ps = conexao.prepareStatement(comando.getSql());
			Timestamp dataHoraAlteracao = new Timestamp(new java.util.Date().getTime());
			for(int i = 0; i < lst.size(); i++) {
				ContaCapitalLegado contaCapitalLegado = lst.get(i);
				int ix = 1;
				ps.setBigDecimal(ix++, isValorPercentual ? percentual : contaCapitalLegado.getValorDeb());
				ps.setTimestamp(ix++, dataHoraAlteracao);
				ps.setInt(ix++, contaCapitalLegado.getNumMatricula());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			if (e.getNextException() != null) {
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

	private void alterarEmLotePorData(List<ContaCapitalLegado> lst) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		try {
			conexao = estabelecerConexao();
			ps = conexao.prepareStatement(getComando("ALTERARDATADEBINDLOTE").getSql());
			Timestamp dataHoraAlteracao = new Timestamp(new java.util.Date().getTime());
			for(int i = 0; i < lst.size(); i++) {
				ContaCapitalLegado contaCapitalLegado = lst.get(i);
				int ix = 1;
				ps.setDate(ix++, new Date(contaCapitalLegado.getDataVencimentoDeb().getTime()));
				ps.setTimestamp(ix++, dataHoraAlteracao);
				ps.setInt(ix++, contaCapitalLegado.getNumMatricula());
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

	private void alterarEmLotePorValorData(List<ContaCapitalLegado> lst, BigDecimal percentual) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PreparedStatement ps = null;
		try {
			conexao = estabelecerConexao();
			boolean isValorPercentual = percentual != null && percentual.compareTo(BigDecimal.ZERO) > 0;
			if(isValorPercentual) {
				comando = getComando("ALTERARVALORPERCENTUALDATADEBINDLOTE");
			} else {
				comando = getComando("ALTERARVALORDATADEBINDLOTE");
			}
			ps = conexao.prepareStatement(comando.getSql());
			Timestamp dataHoraAlteracao = new Timestamp(new java.util.Date().getTime());
			for(int i = 0; i < lst.size(); i++) {
				ContaCapitalLegado contaCapitalLegado = lst.get(i);
				int ix = 1;
				ps.setBigDecimal(ix++, isValorPercentual ? percentual : contaCapitalLegado.getValorDeb());
				ps.setDate(ix++, new Date(contaCapitalLegado.getDataVencimentoDeb().getTime()));
				ps.setTimestamp(ix++, dataHoraAlteracao);
				ps.setInt(ix++, contaCapitalLegado.getNumMatricula());
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

}