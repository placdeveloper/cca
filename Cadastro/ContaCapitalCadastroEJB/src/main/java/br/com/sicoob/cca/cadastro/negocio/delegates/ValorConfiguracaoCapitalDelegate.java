/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import java.util.List;

import org.dom4j.dom.DOMDocument;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.cca.cadastro.negocio.servicos.ValorConfiguracaoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;

/**
 * A Classe ValorConfiguracaoCapitalDelegate.
 */
public class ValorConfiguracaoCapitalDelegate extends ContaCapitalCadastroCrudDelegate<ValorConfiguracaoCapital, ValorConfiguracaoCapitalServico> {
	
	/**
	 * Instancia um novo ValorConfiguracaoCapitalDelegate.
	 */
	ValorConfiguracaoCapitalDelegate() {
		
	}

	/**
	 * Locator ValorConfiguracaoCapitalServico
	 */
	@Override
	protected ValorConfiguracaoCapitalServico localizarServico() {
		return (ValorConfiguracaoCapitalServico) ContaCapitalCadastroServiceLocator.getInstance().localizarValorConfiguracaoCapitalServico();
	}		

	/**
	 * Listar valor configuracao capital.
	 *
	 * @param consultaDTO o valor de consulta dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ValorConfiguracaoCapital> listarValorConfiguracaoCapital(ConsultaDto<ValorConfiguracaoCapital> consultaDTO) throws BancoobException {		
		return getServico().listarValorConfiguracaoCapital(consultaDTO);
	}
	
	/**
	 * O método Gravar lista valor configuracao capital.
	 *
	 * @param lstValorConfiguracaoCapital o valor de lst valor configuracao capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void gravarListaValorConfiguracaoCapital(List<ValorConfiguracaoCapital> lstValorConfiguracaoCapital)throws BancoobException {		
		getServico().gravarListaValorConfiguracaoCapital(lstValorConfiguracaoCapital);
	}	
	
	/**
	 * Consulta lista instituicao parametro.
	 *
	 * @param IdConfiguracaoCapital o valor de id configuracao capital
	 * @param codTipoGrauCoop o valor de cod tipo grau coop
	 * @param numCoop o valor de num coop
	 * @return DOMDocument
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DOMDocument consultaListaInstituicaoParametro(Integer idConfiguracaoCapital, Integer codTipoGrauCoop, Integer numCoop) throws BancoobException {	
		return getServico().consultaListaInstituicaoParametro(idConfiguracaoCapital, codTipoGrauCoop, numCoop);
	}		
	
	/**
	 * {@link ValorConfiguracaoCapitalServico#obterValorConfiguracao(Integer, Integer)}
	 */
	public ValorConfiguracaoCapital obterValorConfiguracao(Integer idParametro, Integer idInstituicao) throws BancoobException {
		return getServico().obterValorConfiguracao(idParametro, idInstituicao);
	}
	
	/**
	 * {@link ValorConfiguracaoCapitalServico#obterValorConfiguracao(List, Integer)}
	 */
	public List<ValorConfiguracaoCapital> obterValorConfiguracao(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException {
		return getServico().obterValorConfiguracao(idsInstituicao, idParametro);
	}
}