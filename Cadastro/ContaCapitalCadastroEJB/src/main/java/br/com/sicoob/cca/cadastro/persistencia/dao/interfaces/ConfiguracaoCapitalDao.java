/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;

/**
 * A Interface ConfiguracaoCapitalDao.
 */
public interface ConfiguracaoCapitalDao extends ContaCapitalCadastroCrudDaoIF<ConfiguracaoCapital> {
	
	/**
	 * Pesquisar proximo seq configuracao capital.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer pesquisarProximoSeqConfiguracaoCapital() throws BancoobException;
	
	/**
	 * Pesquisa condicoes estatutarias da instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<CondicaoEstatutariaDTO> consultarConfiguracaoEstatutaria(Integer idInstituicao) throws BancoobException;
}