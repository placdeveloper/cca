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
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TrabalhaLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ContaCapitalIntegracaoLegadoException;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDao;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao;

/**
 * A Classe TrabalhaLegadoDaoImpl.
 */
public class TrabalhaLegadoDaoImpl extends ContaCapitalIntegracaoLegadoDao implements TrabalhaLegadoDao {

	/** A constante MSG_016. */
	private static final String MSG_016 = "MSG_016";
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao#obterDadosTrabalha(java.lang.Integer)
	 */
	public List<TrabalhaLegadoDTO> obterDadosTrabalha(Integer numPessoa) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		List<TrabalhaLegadoDTO> listaRetorno = new ArrayList<TrabalhaLegadoDTO>();
		
		try {
			comando = getComando("OBTERDADOSTRABALHA");
			comando.adicionarVariavel("numPessoa", numPessoa);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()){				
				TrabalhaLegadoDTO retorno = new TrabalhaLegadoDTO();
				if (rs.getDate("dataAdmissao") != null) {
					retorno.setDataAdmissao(new DateTimeDB(rs.getDate("dataAdmissao").getTime()));
				}
				retorno.setDescEmpresaTrabalha(rs.getString("descEmpresaTrabalha"));
				retorno.setDescMatriculaFunc(rs.getString("descMatriculaFunc"));
				retorno.setDescOcupacaoProfissional(rs.getString("descOcupacaoProfissional"));
				retorno.setNumPessoaFisica(rs.getInt("numPessoaFisica"));
				retorno.setNumPessoaJuridica(rs.getInt("numPessoaJuridica"));
				retorno.setUIDTrabalha(rs.getString("uIDTrabalha"));	
				
				listaRetorno.add(retorno);
			}			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_004",e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_004",e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
		
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao#verificaSeDebIndFolhaCliente(java.lang.Integer, java.lang.Integer)
	 */
	public Boolean verificaSeDebIndFolhaCliente(Integer numMatricula, Integer numCliente) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Boolean bolPossuiDebito = false;
		
		try {
			comando = getComando("VERIFICASEDEBINDFOLHACLIENTE");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.adicionarVariavel("numCliente", numCliente);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_FOLHA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			bolPossuiDebito = rs.next();
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return bolPossuiDebito;		
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao#verificarParcelaViaFolhaCliente(java.lang.Integer, java.lang.Integer)
	 */
	public Boolean verificarParcelaViaFolhaCliente(Integer numMatricula, Integer numCliente) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Boolean bolPossuiParcela = false;
		
		try {
			comando = getComando("VERIFICARPARCELAVIAFOLHACLIENTE");
			comando.adicionarVariavel("numMatricula", numMatricula);
			comando.adicionarVariavel("numCliente", numCliente);
			comando.adicionarVariavel("COD_PARCELA_GERADA", ContaCapitalConstantes.COD_PARCELA_GERADA);
			comando.adicionarVariavel("COD_PARCELA_AGUARDA_RETORNO", ContaCapitalConstantes.COD_PARCELA_AGUARDA_RETORNO);
			comando.adicionarVariavel("COD_MODO_LANCAMENTO_VIA_FOLHA", ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA);
			comando.adicionarVariavel("COD_TIPO_PARCELAMENTO_INTEGRAL", ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			bolPossuiParcela = rs.next();	
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return bolPossuiParcela;		
	}	
			
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao#verificarSePrepRemessa(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public Boolean verificarSePrepRemessa(String strUIDTrabalha, Integer numAnoRef, Integer numMesRef) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Boolean bolPossuiParcela = false;
		
		try {
			comando = getComando("VERIFICARSEPREPREMESSA");
			comando.adicionarVariavel("UIDTrabalha", strUIDTrabalha);
			comando.adicionarVariavel("AnoRef", numAnoRef);
			comando.adicionarVariavel("MesRef", numMesRef);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
				
			bolPossuiParcela = rs.next();
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return bolPossuiParcela;		
	}


	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao#verificarSeMatriculaTrabalhaValida(java.lang.String, java.lang.Integer)
	 */
	public Boolean verificarSeMatriculaTrabalhaValida(String strUIDTrabalha,Integer numCliente) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		Boolean bolMatriculaTrabalha = false;	
		
		try {
			comando = getComando("VERIFICARSEMATRICULATRABALHAVALIDA");
			comando.adicionarVariavel("UIDTrabalha", strUIDTrabalha);
			comando.adicionarVariavel("numCliente", numCliente);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
								
			bolMatriculaTrabalha = rs.next();

		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException(MSG_016,e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return bolMatriculaTrabalha;	
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.TrabalhaLegadoDao#obterDadosTrabalhaPorMatricula(java.lang.String)
	 */
	public TrabalhaLegadoDTO obterDadosTrabalhaPorMatricula(String descMatriculaFunc) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		TrabalhaLegadoDTO retorno = new TrabalhaLegadoDTO();
		
		try {
			comando = getComando("OBTERDADOSTRABALHAMATRICULA");
			comando.adicionarVariavel("descMatriculaFunc", descMatriculaFunc);
			comando.configurar();

			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){				
				retorno.setDataAdmissao(new DateTimeDB(rs.getDate("dataAdmissao").getTime()));
				retorno.setDescEmpresaTrabalha(rs.getString("descEmpresaTrabalha"));
				retorno.setDescMatriculaFunc(rs.getString("descMatriculaFunc"));
				retorno.setDescOcupacaoProfissional(rs.getString("descOcupacaoProfissional"));
				retorno.setNumPessoaFisica(rs.getInt("numPessoaFisica"));
				retorno.setNumPessoaJuridica(rs.getInt("numPessoaJuridica"));
				retorno.setUIDTrabalha(rs.getString("uIDTrabalha"));	
			}
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_004",e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalIntegracaoLegadoException("MSG_004",e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return retorno;
		
	}	
	
}