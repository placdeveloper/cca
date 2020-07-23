/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;

/**
 * A Interface ConfiguracaoCapitalServico.
 */
public interface ConfiguracaoCapitalServico extends ContaCapitalCadastroCrudServico<ConfiguracaoCapital> {
	
	/**
	 * O m�todo Incluir configuracao capital.
	 *
	 * @param configuracaoCapital o valor de configuracao capital
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void incluirConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital) throws BancoobException;
	
	/**
	 * O m�todo Alterar configuracao capital.
	 *
	 * @param configuracaoCapital o valor de configuracao capital
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void alterarConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital) throws BancoobException;
	
	/**
	 * Pesquisar proximo seq configuracao capital.
	 *
	 * @return Integer
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Integer pesquisarProximoSeqConfiguracaoCapital() throws BancoobException;
	
	/**
	 * Listar configuracao capital.
	 *
	 * @param consultaDTO o valor de consulta dto
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<ConfiguracaoCapital> listarConfiguracaoCapital(ConsultaDto<ConfiguracaoCapital> consultaDTO) throws BancoobException;		
	
	/**
	 * Obter configuracao capital.
	 *
	 * @param idConfiguracaoCapital o valor de id configuracao capital
	 * @return ConfiguracaoCapital
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConfiguracaoCapital obterConfiguracaoCapital(Integer idConfiguracaoCapital) throws BancoobException;		
	
	/**
	 * Pesquisa condicoes estatutarias da instituicao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<CondicaoEstatutariaDTO> consultarConfiguracaoEstatutaria(Integer idInstituicao) throws BancoobException;
}