/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.servicos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalDIRFDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalInstituicaoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.DadosContaCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.RelExtratoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.SaldoContaCapitalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ValorIngressoCapitalDTO;

/**
 * Define contrato de serviço do conta capital api
 */
public interface ContaCapitalServico extends APIContaCapitalServico {

	/**
	 * Verifica se o cliente é um associado ativo de um cooperativa de crédito
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	Boolean verificarAssociadoContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta o saldo integralizado do cliente em conta capital para uma determinada cooperativa
	 * Cliente com mais de uma conta capital, o saldo sera consolidado
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal obterSaldoContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consulta os saldos da Conta Capital (subscrito,integralizado,a devolver,a realizar, bloqueado) do cliente em conta capital para uma determinada cooperativa
	 * Cliente com mais de uma conta capital, o saldo sera consolidado
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	SaldoContaCapitalDTO obterSaldosContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException;	
	
	/**
	 * Consulta a matricula(Conta Capital) pelo Cliente Capes Informado e Instituicao
	 * Clientes com mais de uma matrícula ativa, será considerada apenas a matrícula mais antiga 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterMatriculaContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException;
	
	/**
	 * Gera ficha matricula do associado 
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	Object gerarFichaMatricula(Integer numMatricula) throws BancoobException;
	
	/**
	 * Gera ficha admissao do associado 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Object gerarFichaAdmissao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Gera extrato do associado 
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	Object gerarExtrato(RelExtratoDTO relExtratoDTO) throws BancoobException;
	
	/**
	 * Gera extrato do associado 
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	String gerarExtratoHTML(RelExtratoDTO relExtratoDTO) throws BancoobException;
	
	/**
	 * Consultar os dados da conta capital
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	DadosContaCapitalDTO obterDadosContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException;
	
	/**
	 * Verifica se o fechamento do conta capital foi iniciado para a cooperativa em questao
	 * @param numCoop
	 * @return 
	 * @throws BancoobException
	 */
	Boolean verificarFechamentoContaCapital(Integer numCoop) throws BancoobException;
	
	/**
	 * Gera extrato DIRF (Declaração do Imposto de Renda Retido na Fonte)
	 * @param idInstituicao (default: todas instituicoes)
	 * @param dataInicio (default: 01/01/2014)
	 * @param dataFim (default: Dia -1)
	 * @return extrato DIRF das instituicoes filtradas por periodo
	 * @throws BancoobException
	 */
	List<ContaCapitalDIRFDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio, Date dataFim) throws BancoobException;
	
	/**
	 * Consulta valor de ingresso na cooperativa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ValorIngressoCapitalDTO obterValorIngressoCapital(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consulta conta capital por CPF/CNPJ (todas as instituicoes)
	 * @param cpfCnpj
	 * @return 
	 * @throws BancoobException
	 */
	List<ContaCapitalInstituicaoDTO> obterContaCapitalPorCpfCnpj(String cpfCnpj) throws BancoobException;
	
	/**
	 * Recupera o valor bloqueado.
	 * @param numCooperativa
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal obterValorBloqueado(Integer numCooperativa, Integer numMatricula) throws BancoobException;
}