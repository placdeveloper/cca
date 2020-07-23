/*
 * 
 */
package br.com.sicoob.sisbr.cca.api.negocio.delegates;

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
import br.com.sicoob.sisbr.cca.api.negocio.servicos.IntegralizacaoCapitalServico;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.locator.APIContaCapitalServiceLocator;

/**
 * Delegate IntegralizacaoCapitalDelegate
 */
public class IntegralizacaoCapitalDelegate extends APIContaCapitalDelegate<IntegralizacaoCapitalServico> {

	@Override
	protected IntegralizacaoCapitalServico localizarServico() {
		return (IntegralizacaoCapitalServico) APIContaCapitalServiceLocator.getInstance().localizarIntegralizacaoCapitalServico();
	}

	/**
	 * {@link IntegralizacaoCapitalServico#integralizarPontosCabal(IntegralizacaoCapitalCabalDTO) 
	 */
	public IntegralizacaoCapitalCabalRetornoDTO integralizarPontosCabal(IntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException{
		return getServico().integralizarPontosCabal(dtoCabal);
	}

	/**
	 * {@link IntegralizacaoCapitalServico#consultarIntegralizacaoPontosCabal(ConsultaIntegralizacaoCapitalCabalDTO)}
	 */
	public List<ConsultaIntegralizacaoCapitalCabalRetornoDTO> consultarIntegralizacaoPontosCabal(ConsultaIntegralizacaoCapitalCabalDTO dtoCabal) throws BancoobException{
		return getServico().consultarIntegralizacaoPontosCabal(dtoCabal);
	}
	
	
	/**
	{@link IntegralizacaoCapitalServico#integralizarBoletoBancario(IntegralizacaoCapitalBoletoDTO)}
	 */
	public String integralizarBoletoBancario(IntegralizacaoCapitalBoletoDTO dtoBoleto) throws BancoobException{
		return getServico().integralizarBoletoBancario(dtoBoleto);
	}
		
	/**
	{@link IntegralizacaoCapitalServico#integralizarCartaoCredito(IntegralizacaoCapitalCartaoDTO)}
	 */
	public String integralizarCartaoCredito(IntegralizacaoCapitalCartaoDTO dtoCartao) throws BancoobException{
		return getServico().integralizarCartaoCredito(dtoCartao);
	}
	
	/**
	{@link IntegralizacaoCapitalServico#integralizarRateio(List)}
	 */
	public String integralizarRateio(List<IntegralizacaoCapitalRateioDTO> dtosRateio) throws BancoobException {
		return getServico().integralizarRateio(dtosRateio);
	}
	
	/**
	 {@link IntegralizacaoCapitalServico#estornarRateio(EstornoRateioDTO)}
	 */
	public void estornarRateio(EstornoRateioDTO dto) throws BancoobException {
		getServico().estornarRateio(dto);
	}
	
}
