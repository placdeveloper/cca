/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import org.hibernate.Hibernate;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.PropostaSubscricaoDao;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;

/**
 * @author marco.nascimento
 */
public class PropostaSubscricaoDaoImpl extends ContaCapitalCadastroCrudDao<PropostaSubscricao> implements PropostaSubscricaoDao {

	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public PropostaSubscricaoDaoImpl(Class<PropostaSubscricao> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#obter(java.io.Serializable)
	 */
	@Override
	public PropostaSubscricao obter(Serializable chave) throws BancoobException {
		PropostaSubscricao proposta = super.obter(chave);
		
		if(proposta != null) {
			getLogger().info("Lazy load...");
			Hibernate.initialize(proposta.getTipoIntegralizacao());
		}
		
		return proposta;
	}
}