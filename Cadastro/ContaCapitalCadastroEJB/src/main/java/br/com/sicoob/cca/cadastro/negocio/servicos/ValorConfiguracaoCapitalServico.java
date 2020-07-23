/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos;

import java.util.List;

import org.dom4j.dom.DOMDocument;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;

/**
 * A Interface ValorConfiguracaoCapitalServico.
 */
public interface ValorConfiguracaoCapitalServico extends ContaCapitalCadastroCrudServico<ValorConfiguracaoCapital> {
	
	/**
	 * Listar valor configuracao capital.
	 *
	 * @param consultaDTO o valor de consulta dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<ValorConfiguracaoCapital> listarValorConfiguracaoCapital(ConsultaDto<ValorConfiguracaoCapital> consultaDTO) throws BancoobException;		
	
	/**
	 * O método Gravar lista valor configuracao capital.
	 *
	 * @param lstValorConfiguracaoCapital o valor de lst valor configuracao capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void gravarListaValorConfiguracaoCapital(List<ValorConfiguracaoCapital> lstValorConfiguracaoCapital)throws BancoobException;

	/**
	 * Consulta a lista das instituições do sicoob e seus respectivos valores para o idconfiguração informado
	 * @param idConfiguracaoCapital
	 * @return
	 * @throws BancoobException
	 */
	DOMDocument consultaListaInstituicaoParametro(Integer idConfiguracaoCapital, Integer codTipoGrauCoop, Integer numCoop) throws BancoobException;;

	/**
	 * Obtem ValorConfiguracao conta capital por idParametro e idInsistuicao
	 * @param idParametro
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ValorConfiguracaoCapital obterValorConfiguracao(Integer idParametro, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem ValorConfiguracao conta capital por idParametro e idInsistuicoes
	 * @param idsInstituicao
	 * @param idParametro
	 * @return
	 * @throws BancoobException
	 */
	List<ValorConfiguracaoCapital> obterValorConfiguracao(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException;
}