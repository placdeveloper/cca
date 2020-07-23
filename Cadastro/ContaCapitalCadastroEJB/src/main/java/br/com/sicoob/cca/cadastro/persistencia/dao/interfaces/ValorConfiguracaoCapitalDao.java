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
	 * Consulta a lista das instituições do sicoob e seus respectivos valores para o idconfiguração informado
	 * @param IdConfiguracaoCapital
	 * @return
	 * @throws BancoobException
	 */
	DOMDocument consultaListaInstituicaoParametro(Integer IdConfiguracaoCapital, Integer codTipoGrauCoop, Integer numCoop) throws BancoobException;
	
	/**
	 * Obtem valor configuração conta capital por parametro e instuição
	 * @param idParametro
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ValorConfiguracaoCapital obterValorConfiguracao(Integer idParametro, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem valor configuração conta capital por parametro e instuiçoes
	 * @param idsInstituicao
	 * @param idParametro
	 * @return
	 * @throws BancoobException
	 */
	List<ValorConfiguracaoCapital> obterValorConfiguracao(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException;
}