/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.ConfiguracaoCapitalServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;

/**
 * A Classe ConfiguracaoCapitalDelegate.
 */
public final class ConfiguracaoCapitalDelegate extends ContaCapitalCadastroCrudDelegate<ConfiguracaoCapital, ConfiguracaoCapitalServico> {
	
	/**
	 * Instancia um novo ConfiguracaoCapitalDelegate.
	 */
	ConfiguracaoCapitalDelegate() {
	
	}

	/**
	 * Locator ConfiguracaoCapitalServico
	 */
	@Override
	protected ConfiguracaoCapitalServico localizarServico() {
		return (ConfiguracaoCapitalServico) ContaCapitalCadastroServiceLocator.getInstance().localizarConfiguracaoCapitalServico();
	}		
	
	/**
	 * O método Incluir configuracao capital.
	 *
	 * @param configuracaoCapital o valor de configuracao capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluirConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital)throws BancoobException {		
		getServico().incluirConfiguracaoCapital(configuracaoCapital);
	}
	
	/**
	 * O método Alterar configuracao capital.
	 *
	 * @param configuracaoCapital o valor de configuracao capital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void alterarConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital)throws BancoobException {		
		getServico().alterarConfiguracaoCapital(configuracaoCapital);
	}
	
	/**
	 * Pesquisar proximo seq configuracao capital.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer pesquisarProximoSeqConfiguracaoCapital() throws BancoobException {		
		return getServico().pesquisarProximoSeqConfiguracaoCapital();
	}
	
	/**
	 * Listar configuracao capital.
	 *
	 * @param consultaDTO o valor de consulta dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ConfiguracaoCapital> listarConfiguracaoCapital(ConsultaDto<ConfiguracaoCapital> consultaDTO) throws BancoobException {		
		return getServico().listarConfiguracaoCapital(consultaDTO);
	}
	
	/**
	 * Obter configuracao capital.
	 *
	 * @param idConfiguracaoCapital o valor de id configuracao capital
	 * @return ConfiguracaoCapital
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConfiguracaoCapital obterConfiguracaoCapital(Integer idConfiguracaoCapital) throws BancoobException {		
		return getServico().obterConfiguracaoCapital(idConfiguracaoCapital);
	}
	
	/**
	 * {@link ConfiguracaoCapitalServico#consultarConfiguracaoEstatutaria(Integer)}
	 */
	public List<CondicaoEstatutariaDTO> consultarConfiguracaoEstatutaria(Integer idInstituicao) throws BancoobException {		
		return getServico().consultarConfiguracaoEstatutaria(idInstituicao);
	}
}
