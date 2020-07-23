package br.com.sicoob.sisbr.cca.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ResultadoLiquidacaoDTO;

/**
 * CreditoIntegracaoServico
 * @author Nairon.Silva
 */
public interface CreditoIntegracaoServico extends ContaCapitalIntegracaoServico {

	/**
	 * Recupera os emprestimos do cliente.
	 * @param numCooperativa
	 * @param numCliente
	 * @return
	 * @throws BancoobException
	 */
	List<ContratoLiquidacaoDTO> consultarContratosLiquidacao(Integer numCooperativa, Integer numCliente) throws BancoobException;
	
	/**
	 * Realiza a liquidacao dos emprestimos do cliente.
	 * @param numCooperativa
	 * @param idUsuario
	 * @param contratos
	 * @return
	 * @throws BancoobException
	 */
	List<ResultadoLiquidacaoDTO> gravarLiquidacao(Integer numCooperativa, String idUsuario, List<ContratoLiquidacaoDTO> contratos) throws BancoobException;
	
}
