/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.interfaces;

import java.util.List;

import org.dom4j.dom.DOMDocument;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;

/**
 * A Interface ValorConfiguracaoCapitalDao.
 */
public interface ValorConfiguracaoCapitalDao extends ContaCapitalCadastroCrudDaoIF<ValorConfiguracaoCapital> {

	/**
	 * Consulta a lista das institui��es do sicoob e seus respectivos valores para o idconfigura��o informado
	 * @param IdConfiguracaoCapital
	 * @return
	 * @throws BancoobException
	 */
	DOMDocument consultaListaInstituicaoParametro(Integer IdConfiguracaoCapital, Integer codTipoGrauCoop, Integer numCoop) throws BancoobException;
	
	/**
	 * Obtem valor configura��o conta capital por parametro e instui��o
	 * @param idParametro
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ValorConfiguracaoCapital obterValorConfiguracao(Integer idParametro, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem valor configura��o conta capital por parametro e instui�oes
	 * @param idsInstituicao
	 * @param idParametro
	 * @return
	 * @throws BancoobException
	 */
	List<ValorConfiguracaoCapital> obterValorConfiguracao(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException;
}