/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.impl;

import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.AgrupadorConfiguracaoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.AgrupadorConfiguracaoCapital;

/**
 * A Classe AgrupadorConfiguracaoCapitalDaoImpl
 */
public class AgrupadorConfiguracaoCapitalDaoImpl extends ContaCapitalCadastroCrudDao<AgrupadorConfiguracaoCapital> implements AgrupadorConfiguracaoCapitalDao {
    	
	/**
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public AgrupadorConfiguracaoCapitalDaoImpl(Class<AgrupadorConfiguracaoCapital> clazz, String nomeComandoPesquisar) {
		super(clazz, nomeComandoPesquisar);
	}
}