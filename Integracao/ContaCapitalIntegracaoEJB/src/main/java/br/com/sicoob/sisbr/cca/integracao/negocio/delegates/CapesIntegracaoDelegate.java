/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.AnotacaoPessoaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.BemPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.EnderecoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.FonteRendaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaJuridicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ReferenciaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.RelacionamentoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TelefonePessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * A Classe CapesIntegracaoDelegate.
 */
public class CapesIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<CapesIntegracaoServico> {

	
	/**
	 * Recupera a unica instancia de CapesIntegracaoDelegate.
	 *
	 * @return uma instancia de CapesIntegracaoDelegate
	 */
	public static CapesIntegracaoDelegate getInstance() {
		return new CapesIntegracaoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected CapesIntegracaoServico localizarServico() {
		return (CapesIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarCapesIntegracaoServico();
	}
	
	/**
	 * Obtem um DTO com informações sobre o cpf\cnpj  
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */	
	public PessoaIntegracaoDTO obterPorCpfCnpjInstituicao(String cpfCnpj,Integer idInstituicao) throws BancoobException{
		return getServico().obterPorCpfCnpjInstituicao(cpfCnpj, idInstituicao);
	}	
	
	/**
	 * Obtem um DTO com informações sobre o idPessoa Informado
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public PessoaIntegracaoDTO obterPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterPessoaInstituicao(idPessoa, idInstituicao);
		
	}
	
	/**
	 * Obtem um DTO com informações sobre a pessoa juridica em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public PessoaIntegracaoDTO obterPessoaJuridicaFormaConstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterPessoaJuridicaFormaConstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Verifica se é pessoa juridica
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Boolean isPessoaJuridica(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().isPessoaJuridica(idPessoa, idInstituicao);
	}
	
	/**
	 * Verifica cadastro do cliente
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Boolean isClienteCadastrado(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().isClienteCadastrado(idPessoa, idInstituicao);
	}

	/**
	 * Obtem um DTO com informações sobre a pessoa fisica em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public PessoaFisicaIntegracaoDTO obterPessoaFisicaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterPessoaFisicaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações sobre o endereço da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public EnderecoPessoaIntegracaoDTO obterEnderecoPessoaInstituicao (Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterEnderecoPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações sobre os telefones da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public TelefonePessoaIntegracaoDTO obterTelefonePessoaInstituicao (Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterTelefonePessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações sobre a fonte de renda da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public FonteRendaPessoaIntegracaoDTO obterFonteRendaPessoaInstituicao (Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterFonteRendaPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações sobre referencias da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public ReferenciaPessoaIntegracaoDTO obterReferenciaPessoaInstituicao (Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterReferenciaPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações da pessoa juridica em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public PessoaJuridicaIntegracaoDTO obterPessoaJuridicaInstituicao (Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterPessoaJuridicaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações do conjuge da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<RelacionamentoPessoaIntegracaoDTO> obterConjugePessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterConjugePessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações dos representantes legais da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<RelacionamentoPessoaIntegracaoDTO> obterRepresentantesLegaisPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterRepresentantesLegaisPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações dos responsáveis legais da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<RelacionamentoPessoaIntegracaoDTO> obterResponsavelLegalPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterResponsavelLegalPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem um DTO com informações dos bens da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public BemPessoaIntegracaoDTO obterBemPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterBemPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem anotacoes baixadas de uma pessoa
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<AnotacaoPessoaDTO> obterAnotacoesBaixadas(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAnotacoesBaixadas(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem anotacoes vigentes de uma pessoa
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<AnotacaoPessoaDTO> obterAnotacoesVigentes(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAnotacoesVigentes(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtem o idPessoa atraves do idPessoaLegado e o idInstituicao
	 * @param idPessoaLegado
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterIdPessoaPorIdPessoaLegado(Integer idPessoaLegado,Integer idInstituicao) throws BancoobException {
		return getServico().obterIdPessoaPorIdPessoaLegado(idPessoaLegado, idInstituicao);		
	}
	
	/**
	 * Consulta pessoa por cpfCnpj (todas as instituicoes) <br /> Obs: O associado pode estar em mais de uma instituicao
	 * @param cpfCnpj
	 * @return
	 * @throws BancoobException
	 */
	public List<PessoaIntegracaoDTO> obterPessoaPorCpfCnpj(String cpfCnpj) throws BancoobException {
		return getServico().obterPessoaPorCpfCnpj(cpfCnpj);
	}
}