/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

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

/**
 * A Interface CapesIntegracaoServico.
 */
public interface CapesIntegracaoServico extends ContaCapitalIntegracaoServico {

	/**
	 * Obtem um DTO com informações sobre o cpf\cnpj  
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaIntegracaoDTO obterPorCpfCnpjInstituicao(String cpfCnpj,Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem o idPessoa atraves do idPessoaLegado e o idInstituicao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterIdPessoaPorIdPessoaLegado(Integer idPessoaLegado,Integer idInstituicao) throws BancoobException;		
	
	/**
	 * Obtem um DTO com informações sobre o idPessoa Informado
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaIntegracaoDTO obterPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException;
	
	
	/**
	 * Obtem um DTO com informações sobre a pessoa juridica em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaIntegracaoDTO obterPessoaJuridicaFormaConstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Verifica se é pessoa juridica
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Boolean isPessoaJuridica(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	
	/**
	 * Verifica cadastro do cliente
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Boolean isClienteCadastrado(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações sobre a pessoa fisica em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaFisicaIntegracaoDTO obterPessoaFisicaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações sobre o endereço da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	EnderecoPessoaIntegracaoDTO obterEnderecoPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações sobre os telefones da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	TelefonePessoaIntegracaoDTO obterTelefonePessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações sobre a fonte de renda da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	FonteRendaPessoaIntegracaoDTO obterFonteRendaPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações sobre referencias da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ReferenciaPessoaIntegracaoDTO obterReferenciaPessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações da pessoa juridica em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaJuridicaIntegracaoDTO obterPessoaJuridicaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações do conjuge da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoaIntegracaoDTO> obterConjugePessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações dos representantes legais da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoaIntegracaoDTO> obterRepresentantesLegaisPessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações dos responsáveis legais da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoaIntegracaoDTO> obterResponsavelLegalPessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem um DTO com informações dos bens da pessoa em questão
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	BemPessoaIntegracaoDTO obterBemPessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem anotacoes vigentes da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPessoaDTO> obterAnotacoesVigentes(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem anotacoes baixadas da pessoa em questao
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPessoaDTO> obterAnotacoesBaixadas(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consulta pessoa por cpfCnpj (todas as instituicoes) <br /> Obs: O associado pode estar em mais de uma instituicao
	 * @param cpfCnpj
	 * @return
	 * @throws BancoobException
	 */
	List<PessoaIntegracaoDTO> obterPessoaPorCpfCnpj(String cpfCnpj) throws BancoobException;
}