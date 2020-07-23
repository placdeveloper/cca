package br.com.sicoob.sisbr.cca.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ResultadoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CreditoIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.locator.ContaCapitalIntegracaoServiceLocator;

public class CreditoIntegracaoDelegate extends ContaCapitalIntegracaoDelegate<CreditoIntegracaoServico> {

	@Override
	protected CreditoIntegracaoServico localizarServico() {
		return (CreditoIntegracaoServico) ContaCapitalIntegracaoServiceLocator.getInstance().localizarCreditoIntegracaoServico();
	}
	
	private CreditoIntegracaoDelegate() {
		// privado
	}
	
	public static CreditoIntegracaoDelegate getInstance() {
		return new CreditoIntegracaoDelegate();
	}
	
	/**
	 * consultarContratosLiquidacao
	 * @param numCooperativa
	 * @param numCliente
	 * @return
	 * @throws BancoobException
	 */
	public List<ContratoLiquidacaoDTO> consultarContratosLiquidacao(Integer numCooperativa, Integer numCliente) throws BancoobException {
		return getServico().consultarContratosLiquidacao(numCooperativa, numCliente);
	}
	
	/**
	 * gravarLiquidacao
	 * @param numCooperativa
	 * @param idUsuario
	 * @param contratos
	 * @return
	 * @throws BancoobException
	 */
	public List<ResultadoLiquidacaoDTO> gravarLiquidacao(Integer numCooperativa, String idUsuario, List<ContratoLiquidacaoDTO> contratos) throws BancoobException {
		return getServico().gravarLiquidacao(numCooperativa, idUsuario, contratos);
	}

}
