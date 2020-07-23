/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.SaldoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.SaldoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCorrenteIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

/**
 * A Classe ContaCorrenteIntegracaoDelegate.
 */
public class ContaCorrenteIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<ContaCorrenteIntegracaoServico> {

	/**
	 * Recupera a unica instancia de ContaCorrenteIntegracaoDelegate.
	 *
	 * @return uma instancia de ContaCorrenteIntegracaoDelegate
	 */
	public static ContaCorrenteIntegracaoDelegate getInstance() {
		return new ContaCorrenteIntegracaoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ContaCorrenteIntegracaoServico localizarServico() {
		return (ContaCorrenteIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarContaCorrenteIntegracaoServico();
	}

	/**
	 * @see ContaCorrenteIntegracaoServico#obterSaldoContaCorrente(SaldoContaCorrenteIntegracaoDTO)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public SaldoContaCorrenteIntegracaoRetDTO obterSaldoContaCorrente(SaldoContaCorrenteIntegracaoDTO dto) throws BancoobException{
		return getServico().obterSaldoContaCorrente(dto);
	}

	/**
	 * @see ContaCorrenteIntegracaoServico#gravarLancamentosIntegracao(LancamentoContaCorrenteIntegracaoDTO)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public LancamentoContaCorrenteIntegracaoRetDTO gravarLancamentosIntegracao(LancamentoContaCorrenteIntegracaoDTO dto) throws BancoobException{
		return getServico().gravarLancamentosIntegracao(dto);
	}

	/**
	 * @see ContaCorrenteIntegracaoServico#verificarContaCorrenteAtiva(ContaCorrenteIntegracaoDTO)
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public Boolean verificarContaCorrenteAtiva(ContaCorrenteIntegracaoDTO dto) throws BancoobException{
		return getServico().verificarContaCorrenteAtiva(dto);
	}
	
	/**
	 *@see ContaCorrenteIntegracaoServico#verificarContaCorrentePorIdPessoa(ContaCorrenteIntegracaoDTO) 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public Boolean verificarContaCorrentePorIdPessoa(ContaCorrenteIntegracaoDTO dto) throws BancoobException{
		return getServico().verificarContaCorrentePorIdPessoa(dto);
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#validarTitularidade(Integer, Integer, Long)}
	 * @param idPessoa
	 * @param idInstituicao
	 * @param numContaCorrente
	 * @return
	 * @throws BancoobException
	 */
	public Boolean validarTitularidade(Integer idPessoa, Integer idInstituicao, Long numContaCorrente) throws BancoobException{
		return getServico().validarTitularidade(idPessoa, idInstituicao, numContaCorrente);
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#consultarContaCorrentePorNumeroCliente(ContaCorrenteIntegracaoDTO)}
	 */
	public List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrentePorNumeroCliente(ContaCorrenteIntegracaoDTO dto) throws BancoobException {
		return getServico().consultarContaCorrentePorNumeroCliente(dto);
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#consultarContaCorrenteAtivaPorNumeroCliente(ContaCorrenteIntegracaoDTO)}
	 */
	public List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrenteAtivaPorNumeroCliente(ContaCorrenteIntegracaoDTO dto) throws BancoobException {
		return getServico().consultarContaCorrenteAtivaPorNumeroCliente(dto);
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#isValorIntegralizacaoMaiorSalcoCco(BigDecimal, Integer, Integer, String, Integer)}
	 */
	public Boolean isValorIntegralizacaoMaiorSalcoCco(BigDecimal valor, Long idPessoa, Integer idInstituicao, String idUsuario, Long numContaCorrente) throws BancoobException{
		return getServico().isValorIntegralizacaoMaiorSalcoCco(valor, idPessoa, idInstituicao, idUsuario, numContaCorrente);		
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#verificarContaCorrenteBloqueadaEncerrada(ContaCorrenteIntegracaoDTO)}
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public Boolean verificarContaCorrenteBloqueadaEncerrada(ContaCorrenteIntegracaoDTO dto)throws BancoobException{
		return getServico().verificarContaCorrenteBloqueadaEncerrada(dto);
	}

	/**
	 * {@link ContaCorrenteIntegracaoServico#verificarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO)}
	 * @param ccoDTO
	 * @return
	 * @throws BancoobException
	 */
	public boolean verificarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO ccoDTO) throws BancoobException {
		return getServico().verificarContaCorrenteImpeditivaDesligamento(ccoDTO);
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#consultarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO)}
	 * @param ccoDTO
	 * @return
	 * @throws BancoobException
	 */
	public List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO ccoDTO) throws BancoobException {
		return getServico().consultarContaCorrenteImpeditivaDesligamento(ccoDTO);
	}	
	
	/**
	 * @see ContaCorrenteIntegracaoServico#gravarLancamentosIntegracaoLote(List<LancamentoContaCorrenteIntegracaoLoteDTO>, Integer)
	 * @param listLancamentoContaCorrenteIntegracaoLoteDTO
	 * @param numCooperativa
	 * @return List<LancamentoContaCorrenteIntegracaoLoteRetDTO>
	 * @throws BancoobException
	 */
	public List<LancamentoContaCorrenteIntegracaoLoteRetDTO> gravarLancamentosIntegracaoLote(List<LancamentoContaCorrenteIntegracaoLoteDTO> listLancamentoContaCorrenteIntegracaoLoteDTO, Integer numCooperativa) throws BancoobException{
		return getServico().gravarLancamentosIntegracaoLote(listLancamentoContaCorrenteIntegracaoLoteDTO, numCooperativa);
	}
	
}