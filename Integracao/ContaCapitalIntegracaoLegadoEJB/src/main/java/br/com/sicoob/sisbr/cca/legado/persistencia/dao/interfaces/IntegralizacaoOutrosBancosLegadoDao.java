package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BancoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO;

/**
 * IntegralizacaoOutrosBancosLegadoDao
 */
public interface IntegralizacaoOutrosBancosLegadoDao {

	/**
	 * Recupera lista de bancos.
	 * @return
	 * @throws BancoobException
	 */
	List<BancoLegadoDTO> obtemListaBancos() throws BancoobException;

	/**
	 * Consulta os favorecidos para integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 */
	List<IntegralizacaoOutrosBancosLegadoDTO> consultarFavorecidosIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException;

	/**
	 * Envia remessa para outros bancos.
	 * @param dtos
	 * @throws BancoobException
	 */
	void enviarRemessa(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException;

	/**
	 * Consulta as contas de favorecidos para definicao de conta principal.
	 * @param filtro
	 * @param tipoSituacao
	 * @return
	 * @throws BancoobException
	 */
	List<IntegralizacaoOutrosBancosLegadoDTO> consultarContasFavorecidos(IntegralizacaoOutrosBancosLegadoDTO filtro, Integer tipoSituacao) throws BancoobException;

	/**
	 * Verifica novas contas de favorecidos.
	 * @throws BancoobException
	 */
	void atualizarContas() throws BancoobException;

	/**
	 * Define as contas principais de favorecidos.
	 * @param dtos
	 */
	void definirPrincipal(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) throws BancoobException;
	
	
	/**
	 * Consulta os remessa de integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 */	
	List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaIntegralizacaoOutrosBancos(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException;
	
	/**
	 * Envia integralização para outros bancos.
	 * @throws BancoobException
	 */
	void enviarIntegBancos() throws BancoobException;
	
	/**
	 * Consulta os remessa de integralizacao em outros bancos detalhe.
	 * @param filtro
	 * @return
	 */	
	List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaEnvDetalhe(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException;

	/**
	 * Consulta retorno de remessas para integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 */
	List<IntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetornoDetalhe(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException;
	
	/**
	 * Consulta retorno de remessas para integralizacao em outros bancos.
	 * @param filtro
	 * @return
	 */
	List<RemessaIntegralizacaoOutrosBancosLegadoDTO> consultarRemessaRetorno(IntegralizacaoOutrosBancosLegadoDTO filtro) throws BancoobException;
	
	
	/**
	 * Grava a integralizacao para outros bancos.
	 * @param dtos
	 * @throws BancoobException
	 */
	void gravarIntegralizacao(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException;
	
	/**
	 * Consulta os remessa de integralizacao em outros bancos sws.
	 * @param numCoop 
	 * @param filtro
	 * @return
	 */	
	List<IntegralizacaoOutrosBancosLegadoDTO> consultarFavorecidosIntegralizacaoSWS(Integer numCoop) throws BancoobException;

	/**
	 * Consulta os remessa de integralizacao em outros bancos sws.
	 * @param numCoop 
	 * @param filtro
	 * @return
	 * @throws BancoobException 
	 * @throws SQLException 
	 */	
	void updateIntegralizacaoSWS(IntegralizacaoOutrosBancosLegadoDTO dto) throws BancoobException;
	
}
