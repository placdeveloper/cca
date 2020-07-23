/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.delegates;

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
import br.com.sicoob.sisbr.cca.api.negocio.servicos.ContaCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.locator.APIContaCapitalServiceLocator;

/**
 * Delegate do conta capital
 * @author gesin1
 */
public class ContaCapitalDelegate extends APIContaCapitalDelegate<ContaCapitalServico> {
	
	public ContaCapitalDelegate() {
		
	}
	
	/**
	 * Locator conta capital
	 * @return ContaCapitalServico
	 */
	@Override
	protected ContaCapitalServico localizarServico() {
		return (ContaCapitalServico) APIContaCapitalServiceLocator.getInstance().localizarContaCapitalServico();
	}

	/**
	 * Consulta se o cliente é um associado ativo de um cooperativa de crédito,
	 * Para Central e Confederacao sempre retorna ativo (true)
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	public Boolean verificarAssociadoContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {
		return getServico().verificarAssociadoContaCapital(idPessoa,idInstituicao);
	}

	/**
	 * Consulta o saldo integralizado do cliente em conta capital para uma determinada instituicao
	 * Cliente com mais de uma conta capital, o saldo sera consolidado
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public BigDecimal obterSaldoContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		return getServico().obterSaldoContaCapital(idPessoa, idInstituicao);
	}
	
	
	/**
	 * Consulta a Matricula(Conta Capital) do cliente 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterMatriculaContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {	
		return getServico().obterMatriculaContaCapital(idPessoa, idInstituicao);
	}
	
	/**
	 * Gera ficha matricula do associado 
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarFichaMatricula(Integer numMatricula) throws BancoobException {	
		return getServico().gerarFichaMatricula(numMatricula);
	}
	
	/**
	 * {@link ContaCapitalServico#gerarFichaAdmissao(Integer, Integer)}
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarFichaAdmissao(Integer idPessoa, Integer idInstituicao) throws BancoobException {	
		return getServico().gerarFichaAdmissao(idPessoa, idInstituicao);
	}
	
	/**
	 * {@link ContaCapitalServico#obterSaldosContaCapital(Integer, Integer)}
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public SaldoContaCapitalDTO obterSaldosContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {
		return getServico().obterSaldosContaCapital(idPessoa, idInstituicao);
	}
	
	/**
	 * {@link ContaCapitalServico#gerarExtrato(RelExtratoDTO)}
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarExtrato(RelExtratoDTO relExtratoDTO) throws BancoobException {	
		return getServico().gerarExtrato(relExtratoDTO);
	}
	
	/**
	 * {@link ContaCapitalServico#gerarExtratoHTML(RelExtratoDTO)}
	 * @return
	 * @throws BancoobException
	 */
	public String gerarExtratoHTML(RelExtratoDTO relExtratoDTO) throws BancoobException {	
		return getServico().gerarExtratoHTML(relExtratoDTO);
	}
	
	/**
	 * Consulta dados da conta capital
	 * @param Integer idPessoa,Integer idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public Object obterDadosContaCapital(Integer idPessoa,Integer idInstituicao) throws BancoobException {	
		return getServico().obterDadosContaCapital(idPessoa, idInstituicao);
	}
	
	/**
	 * Consulta dados da conta capital
	 * @param Integer idPessoa,Integer idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public DadosContaCapitalDTO obterDadosContaCapitalDTO(Integer idPessoa,Integer idInstituicao) throws BancoobException {	
		return getServico().obterDadosContaCapital(idPessoa, idInstituicao);
	}	
	
	/**
	 * {@link ContaCapitalServico#verificarFechamentoContaCapital(Integer)}
	 */
	public Boolean verificarFechamentoContaCapital(Integer numCoop) throws BancoobException {
		return getServico().verificarFechamentoContaCapital(numCoop);
	}
	
	/**
	 * {@link ContaCapitalServico#gerarExtratoDIRF(List, Date, Date)}
	 */
	public List<ContaCapitalDIRFDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio, Date dataFim) throws BancoobException {
		return getServico().gerarExtratoDIRF(idInstituicao, dataInicio, dataFim);
	}
	
	/**
	 * {@link ContaCapitalServico#obterValorIngressoCapital(Integer)}
	 */
	public ValorIngressoCapitalDTO obterValorIngressoCapital(Integer idInstituicao) throws BancoobException {
		return getServico().obterValorIngressoCapital(idInstituicao);
	}
	
	/**
	 * {@link ContaCapitalServico#obterContaCapitalPorCpfCnpj(String)}
	 */
	public List<ContaCapitalInstituicaoDTO> obterContaCapitalPorCpfCnpj(String cpfCnpj) throws BancoobException {	
		return getServico().obterContaCapitalPorCpfCnpj(cpfCnpj);
	}
	
	/**
	 * {@link ContaCapitalServico#obterValorBloqueado(Integer, Integer)}
	 */
	public BigDecimal obterValorBloqueado(Integer numCooperativa, Integer numMatricula) throws BancoobException {
		return getServico().obterValorBloqueado(numCooperativa, numMatricula);
	}
	
}