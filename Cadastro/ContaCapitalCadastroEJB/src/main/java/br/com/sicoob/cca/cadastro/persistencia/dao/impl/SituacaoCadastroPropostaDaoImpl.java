/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.impl;

import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.SituacaoCadastroPropostaDao;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;

/**
 * @author marco.nascimento
 */
public class SituacaoCadastroPropostaDaoImpl extends ContaCapitalCadastroCrudDao<SituacaoCadastroProposta> implements SituacaoCadastroPropostaDao {

	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public SituacaoCadastroPropostaDaoImpl(Class<SituacaoCadastroProposta> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}

}