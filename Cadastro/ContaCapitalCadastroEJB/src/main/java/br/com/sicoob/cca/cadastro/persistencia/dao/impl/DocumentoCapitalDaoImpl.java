/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.impl;

import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.DocumentoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;

/**
 * @author marco.nascimento
 */
public class DocumentoCapitalDaoImpl extends ContaCapitalCadastroCrudDao<DocumentoCapital> implements DocumentoCapitalDao {

	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public DocumentoCapitalDaoImpl(Class<DocumentoCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
}