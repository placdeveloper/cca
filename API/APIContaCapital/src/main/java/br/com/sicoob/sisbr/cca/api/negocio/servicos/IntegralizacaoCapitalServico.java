package br.com.sicoob.sisbr.cca.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ConsultaIntegralizacaoCapitalCabalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ConsultaIntegralizacaoCapitalCabalRetornoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.EstornoRateioDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalBoletoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCabalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCabalRetornoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCartaoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalRateioDTO;

/**
 * IntegralizacaoCapitalServico
 */
public interface IntegralizacaoCapitalServico extends APIContaCapitalServico{

	/**
	 * Recebe os dados da Cabal para integraliza��o via pontos do cart�o
	 * @param dtoCabal
	 * @return IntegralizacaoCabalRetornoDTO
	 * @throws BancoobException
	 */
	IntegralizacaoCapitalCabalRetornoDTO integralizarPontosCabal(IntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException;
	
	/**
	 * Retorna os registros de lan�amentos de integraliza��o via pontos do cart�o
	 * @param dtoCabal
	 * @return
	 * @throws BancoobException
	 */
	List<ConsultaIntegralizacaoCapitalCabalRetornoDTO> consultarIntegralizacaoPontosCabal(ConsultaIntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException;
	
	
	/**
	 * Recebe os dados para integraliza��o via boleto
	 * @param dtoBoleto
	 * @return String
	 * @throws BancoobException
	 */
	String integralizarBoletoBancario(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException;

	/**
	 * Recebe os dados para integraliza��o via cartao de cr�dito
	 * @param dtoBoleto
	 * @return String
	 * @throws BancoobException
	 */	
	String integralizarCartaoCredito(IntegralizacaoCapitalCartaoDTO dtoCartao) throws BancoobException;
	
	/**
	 * Recebe os dados para integralizacao do Rateio
	 * @param dtosRateio
	 * @return
	 * @throws BancoobException
	 */
	String integralizarRateio(List<IntegralizacaoCapitalRateioDTO> dtosRateio) throws BancoobException;
	
	/**
	 * Estorna os lan�amentos de rateio
	 * @param idUsuario
	 * @param chavesLancamentos
	 * @return
	 * @throws BancoobException
	 */
	void estornarRateio(EstornoRateioDTO dto) throws BancoobException;
	
}
