package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BancoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.IntegralizacaoOutrosBancosLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * IntegralizacaoOutrosBancosLegadoDelegate
 */
public class IntegralizacaoOutrosBancosLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<IntegralizacaoOutrosBancosLegadoServico> {

	/** O atributo instance. */
	private static IntegralizacaoOutrosBancosLegadoDelegate instance;

	/**
	 * Instancia de InformacaoAcumuladaLegadoDelegate
	 */
	public static IntegralizacaoOutrosBancosLegadoDelegate getInstance() {
		if(instance == null) {
			instance = new IntegralizacaoOutrosBancosLegadoDelegate();
		}
		return instance;
	}
	
	/**
	 * {@link ContaCapitalIntegracaoLegadoServiceLocator#localizarInformacaoAcumuladaLegadoServico()}
	 */
	@Override
	protected IntegralizacaoOutrosBancosLegadoServico localizarServico() {
		return (IntegralizacaoOutrosBancosLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarIntegralizacaoOutrosBancosLegadoServico();
	}
	
	/**
	 * Obtem a lista de bancos.
	 * @return
	 * @throws BancoobException
	 */
	public List<BancoLegadoDTO> obtemListaBancos() throws BancoobException {
		return getServico().obtemListaBancos();
	}

	/**
	 * Consulta os favorecidos para integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarFavorecidosIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return getServico().consultarFavorecidosIntegralizacao(filtro);
	}

	/**
	 * Envia remessa para outros bancos.
	 * @param dtos
	 * @throws BancoobException
	 */
	public void enviarRemessa(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException {
		getServico().enviarRemessa(dtos);
	}

	/**
	 * Consulta as contas de favorecidos para definicao de conta principal.
	 * @param filtro
	 * @param tipoSituacao
	 * @return
	 * @throws BancoobException
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarContasFavorecidos(IntegralizacaoOutrosBancosLegadoDTO filtro, Integer tipoSituacao) throws BancoobException {
		return getServico().consultarContasFavorecidos(filtro, tipoSituacao);
	}

	/**
	 * Verifica novas contas de favorecidos.
	 * @throws BancoobException
	 */
	public void atualizarContas() throws BancoobException {
		getServico().atualizarContas();
	}

	/**
	 * Define as contas principais de favorecidos. 
	 * @param dtos
	 * @throws BancoobException
	 */
	public void definirPrincipal(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException {
		getServico().definirPrincipal(dtos);
	}
	
	/**
	 * Consulta os remessas de integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaIntegralizacaoOutrosBancos(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return getServico().consultarRemessaIntegralizacaoOutrosBancos(filtro);
	}

	/**
	 * Grava a integralizacao para outros bancos.
	 * @param 
	 * @throws BancoobException
	 */
	public void enviarIntegBancos() throws BancoobException {
		getServico().enviarIntegBancos();
	}

	/**
	 * Consulta os remessas de integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaEnvDetalhe(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		return getServico().consultarRemessaEnvDetalhe(dto);
	}
	
	/**
	 * Consulta retorno de remessas de integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetorno(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return getServico().consultarRemessaRetorno(filtro);
	}
	
	/**
	 * Consulta retorno de remessas de integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetornoDetalhe(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException {
		return getServico().consultarRemessaRetornoDetalhe(filtro);
	}

	
	/**
	 * Grava a integralizacao para outros bancos.
	 * @param dtos
	 * @throws BancoobException
	 */
	public void gravarIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException {
		getServico().gravarIntegralizacao(dto);
	}
	
}
