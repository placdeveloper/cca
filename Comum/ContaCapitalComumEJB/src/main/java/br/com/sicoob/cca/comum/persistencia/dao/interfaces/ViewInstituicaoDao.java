package br.com.sicoob.cca.comum.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;

/**
 * DAO de consultas a SCI.VIW_INSTITUICAO
 * @author Nairon.Silva
 */
public interface ViewInstituicaoDao {

	/**
	 * Consulta as cooperativas ativas.
	 * @param numCoopPai opcional
	 * @return
	 */
	List<InstituicaoCooperativaSCIDTO> consultarCooperativasAtivas(Integer numCoopPai) throws BancoobException;
	
	/**
	 * Lista as centrais.
	 * @return
	 * @throws BancoobException
	 */
	List<InstituicaoCooperativaSCIDTO> listarCentrais() throws BancoobException;
	
	/**
	 * Lista as centrais.
	 * @return
	 * @throws BancoobException
	 */
	List<InstituicaoCooperativaSCIDTO> listarCentraisEConfederacao() throws BancoobException;
	
	/**
	 * Consulta PACs por cooperativa
	 * @param idInstituicao
	 * @return PACs
	 * @throws BancoobException
	 */
	List<InstituicaoCooperativaSCIDTO> consultarPacPorCooperativa(Integer idInstituicao) throws BancoobException;
}