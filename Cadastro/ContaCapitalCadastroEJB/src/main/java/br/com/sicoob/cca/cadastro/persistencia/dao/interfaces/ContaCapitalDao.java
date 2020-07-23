/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.ContaCapitalResumoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.DadosDesligamentoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.FiltroContaCapitalDTO;
import br.com.sicoob.cca.cadastro.persistencia.dao.ContaCapitalCadastroCrudDaoIF;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;

/**
 * DAO Conta Capital
 * @author marco.nascimento
 */
public interface ContaCapitalDao extends ContaCapitalCadastroCrudDaoIF<ContaCapital> {

	/**
	 * Verifica se a conta capital está ativa
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	Boolean pesquisarContaCapitalAtiva(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Verifica se a conta capital está inativa
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	Boolean pesquisarContaCapitalInativa(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Verifica se existe conta capital para instituicao em questão
	 * @param idInstituicao
	 * @param numContaCapital
	 * @return
	 * @throws BancoobException
	 */
	Boolean pesquisarContaCapital(Integer idInstituicao, Integer numContaCapital) throws BancoobException;

	/**
	 * Verifica se a pessoa tem conta capital para a instituição em questão
	 * @param idInstituicao
	 * @param idPessoa
	 * @return numContaCapital se existente
	 * @throws BancoobException
	 */
	Integer pesquisarContaCapitalPessoa(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Verifica se a natureza jurídica é permitida para cadastro da conta capital  
	 * @param codigoNatJur
	 * @return 
	 * @throws BancoobException
	 */
	Boolean naturezaJuridicaPermitida(Short codigoNatJur) throws BancoobException;
	
	/**
	 * Verifica se o CNAE é permitido para cadastro da conta capital  
	 * @param codigoNatJur
	 * @return 
	 * @throws BancoobException
	 */
	Boolean cnaePermitido(String codigoCNAE) throws BancoobException;
	
	/**
	 * Pesquisa de acordo com o filtro informado
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<CadastroContaCapitalRenDTO> pesquisar(CadastroContaCapitalRenDTO dto) throws BancoobException;
	
	/**
	 * Pesquisa quantidade de lancamentos existentes para a conta capital
	 * @param idContaCapital
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer pesquisarLancamentosContaCapital(Integer idContaCapital, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Pesquisa quantidade de parcelamentos existentes para a conta capital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	Integer pesquisarParcelamentosContaCapital(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Exclui cadastro de conta capital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	void excluir(Integer idContaCapital) throws BancoobException;	

	/**
	 * Verifica se o cliente está cadastrado na VIW_PESSOA
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	Boolean pesquisarClienteCadastrado(Integer idInstituicao, Integer idPessoa) throws BancoobException;

	/**
	 * Obtem informacoes basicas a respeito de conta capital, para evitar objetos pesados ou joins desnecessarios
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	ContaCapitalResumoDTO obterResumo(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Obtem informacoes basicas a respeito de conta capital, para evitar objetos pesados ou joins desnecessarios
	 * @param idInstituicao
	 * @param numContaCapital
	 * @return
	 * @throws BancoobException
	 */
	ContaCapitalResumoDTO obterResumo(Integer idInstituicao, Integer numContaCapital) throws BancoobException;

	/**
	 * Obtem informacoes basicas a respeito de contas capital, para evitar objetos pesados ou joins desnecessarios
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	List<ContaCapitalResumoDTO> obterResumos(List<Integer> idsContaCapital) throws BancoobException;
	
	/**
	 * Consulta conta capital por pessoa (apenas ativas)
	 * @param lstPessoas
	 * @return
	 * @throws BancoobException
	 */
	List<CadastroContaCapitalRenDTO> obterContaCapitalPorPessoa(List<PessoaIntegracaoDTO> lstPessoas) throws BancoobException;

	/**
	 * Recupera o idInstituicao pelo idContaCapital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	Integer obterIdInstituicaoPorIdContaCapital(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Obtem maior numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return numContacapital
	 * @throws BancoobException
	 */
	Integer obterMaiorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem menor numero de conta capital ativa por instituicao
	 * @param idInstituicao
	 * @return numContacapital
	 * @throws BancoobException
	 */
	Integer obterMenorContaCapitalAtivaPorInstituicao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter maior conta capital por instituicao.
	 *
	 * @param idInstituicao the id instituicao
	 * @return the integer
	 * @throws BancoobException the bancoob exception
	 */
	Integer obterMaiorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter menor conta capital por instituicao.
	 *
	 * @param idInstituicao the id instituicao
	 * @return the integer
	 * @throws BancoobException the bancoob exception
	 */
	Integer obterMenorContaCapitalPorInstituicao(Integer idInstituicao) throws BancoobException;

	/**
	 * Recupera os JSONs com os dados de desligamento
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<DadosDesligamentoDTO> obterDadosDesligamento(FiltroContaCapitalDTO filtro) throws BancoobException;
}