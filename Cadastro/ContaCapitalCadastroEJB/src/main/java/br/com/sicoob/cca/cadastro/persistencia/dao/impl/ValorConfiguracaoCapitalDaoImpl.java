/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.hibernate.Hibernate;
import org.w3c.dom.DOMException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ValorConfiguracaoCapitalDao;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;

/**
 * A Classe ValorConfiguracaoCapitalDaoImpl.
 */
public class ValorConfiguracaoCapitalDaoImpl extends ContaCapitalCadastroCrudDao<ValorConfiguracaoCapital> implements ValorConfiguracaoCapitalDao {
    	
	private static final Integer PARAMETRO_FILTRO = 1658;
	
	
	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public ValorConfiguracaoCapitalDaoImpl(Class<ValorConfiguracaoCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ValorConfiguracaoCapitalDao#consultaListaInstituicaoParametro(java.lang.Integer)
	 */

	public DOMDocument consultaListaInstituicaoParametro(Integer idConfiguracaoCapital, Integer codTipoGrauCoop, Integer numCoop) throws BancoobException {

		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;	
		DOMDocument xml = new DOMDocument();
		DOMElement root = new DOMElement("ROOT");	
		
		try {
			comando = getComando("CONSULTARLISTAINSTUICOESSCI");
			comando.adicionarVariavel("IDCONFIGURACAOCAPITAL", idConfiguracaoCapital);
			if(codTipoGrauCoop.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CENTRAL)){
				comando.adicionarVariavel("NUMCOOPPAI", numCoop);				
			}else if(codTipoGrauCoop.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_SINGULAR)){
				comando.adicionarVariavel("NUMCOOPERATIVA", numCoop);								
			}
			
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			DOMElement instituicaoConfederacao = new DOMElement("INSTITUICAO");					
			DOMElement instituicaoCentral = new DOMElement("INSTITUICAO");								
			
			while(rs.next()){																
				if(rs.getString("CODTIPOGRAUCOOP").equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO.toString())){					
					instituicaoConfederacao = montarInstituicao(rs, ContaCapitalConstantes.NUM_ZERO);
					instituicaoConfederacao.appendChild(montarInstituicao(rs, null));										
					root.appendChild(instituicaoConfederacao);
				}else if(rs.getString("CODTIPOGRAUCOOP").equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CENTRAL.toString())){										
					instituicaoCentral = montarInstituicao(rs, ContaCapitalConstantes.NUM_ZERO);					
					if(codTipoGrauCoop.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO)){
						instituicaoConfederacao.appendChild(instituicaoCentral);
					}else{						
						root.appendChild(instituicaoCentral);
					}										
					instituicaoCentral.appendChild(montarInstituicao(rs, null));					
				}else if(rs.getString("CODTIPOGRAUCOOP").equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_SINGULAR.toString())){
										
					if(codTipoGrauCoop.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_SINGULAR)){
						
						DOMElement instituicaoSingular = montarInstituicao(rs, null); 
						instituicaoSingular.setAttribute("CHECKED", "1");
						root.appendChild(instituicaoSingular);
						
					}else{						
						instituicaoCentral.appendChild(montarInstituicao(rs, null));
					}
				}								
			}	
			xml.appendChild(root);
			
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		return xml;		
	}	
	
	/**
	 * Montar instituicao.
	 *
	 * @param rs o valor de rs
	 * @param NumSingular o valor de num singular
	 * @return DOMElement
	 * @throws BancoobException lança a exceção BancoobException
	 * @throws SQLException lança a exceção SQLException
	 */
	private DOMElement montarInstituicao(ResultSet rs, Integer NumSingular) throws BancoobException, SQLException {
		DOMElement instituicao = new DOMElement("INSTITUICAO");
		try {
			instituicao.setAttribute("CHECKED", ContaCapitalConstantes.NUM_ZERO.toString());
			instituicao.setAttribute("IDINSTITUICAO", rs.getString("IDINSTITUICAO"));
			instituicao.setAttribute("NUMCOOPPAI", rs.getString("NUMCOOPPAI"));
			instituicao.setAttribute("NUMCOOPERATIVA", rs.getString("NUMCOOPERATIVA"));
			instituicao.setAttribute("IDCONFIGURACAOCAPITAL", rs.getString("IDCONFIGURACAOCAPITAL"));
			instituicao.setAttribute("CODTIPOGRAUCOOP", rs.getString("CODTIPOGRAUCOOP"));
			instituicao.setAttribute("IDVALORCONFIGURACAOCAPITAL", rs.getString("IDVALORCONFIGURACAOCAPITAL"));									
			instituicao.setAttribute("DESCNOMECOOP",  rs.getString("DESCNOMECOOPCOMPLETO"));
			if(NumSingular!=null){
				instituicao.setAttribute("VALORCONFIGURACAO", "");
				instituicao.setAttribute("LINHAPAI", "");
			}else{
				instituicao.setAttribute("LINHAPAI", ContaCapitalConstantes.NUM_ZERO.toString());
				instituicao.setAttribute("VALORCONFIGURACAO", rs.getString("VALORCONFIGURACAO"));
			}									
		} catch (DOMException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}		
		return instituicao;
	}	
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	@Override
	public ValorConfiguracaoCapital incluir(ValorConfiguracaoCapital objeto) throws BancoobException {
		ValorConfiguracaoCapital oCfg = null;
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
	public void alterar(ValorConfiguracaoCapital objeto) throws BancoobException {
		try {
			super.alterar(objeto);
			getEntityManager().flush();
		} catch (PersistenceException pe) {
			this.getLogger().erro(pe, pe.getMessage());
			throw new BancoobException(pe);
		}
	}

	/**
	 * @see br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ValorConfiguracaoCapitalDao#obterValorConfiguracao(java.lang.Integer, java.lang.Integer)
	 */
	public ValorConfiguracaoCapital obterValorConfiguracao(Integer idParametro, Integer idInstituicao) throws BancoobException {
		Query query;
		try {
			query = getEntityManager().createQuery("FROM br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital " +
					" WHERE idInstituicao = :idInstituicao AND configuracaoCapital.id = :idParametro");
			
			query.setParameter("idParametro", idParametro);
			query.setParameter("idInstituicao", idInstituicao);
			
			return (ValorConfiguracaoCapital) query.getSingleResult();
			
		} catch (NoResultException nre) {
			if(idParametro.intValue() == PARAMETRO_FILTRO) {
				this.getLogger().erro(nre, nre.getMessage());
				throw new CadastroContaCapitalNegocioException("MSG_027");
			} else {
				this.getLogger().erro(nre, nre.getMessage());
				throw new CadastroContaCapitalNegocioException("MSG_028", idParametro.toString());
			}
		}
	}
	
	/**
	 * @see br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ValorConfiguracaoCapitalDao#obterValorConfiguracao(List, java.lang.Integer)
	 */
	public List<ValorConfiguracaoCapital> obterValorConfiguracao(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException {
		Query query;
		try {
			query = getEntityManager().createQuery("FROM br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital vlr" +
					" WHERE vlr.idInstituicao IN (:idsInstituicao) AND vlr.configuracaoCapital.id = :idParametro");
					
			query.setParameter("idParametro", idParametro);
			query.setParameter("idsInstituicao", idsInstituicao);
			
			List<ValorConfiguracaoCapital> lst = query.getResultList();
			for (ValorConfiguracaoCapital vlr : lst) {
				Hibernate.initialize(vlr.getHistorico());
			}
			
			return query.getResultList();
			
		} catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		}
	}
}
