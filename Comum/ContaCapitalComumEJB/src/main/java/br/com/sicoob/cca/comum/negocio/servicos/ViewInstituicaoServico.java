package br.com.sicoob.cca.comum.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;

/**
 * @author Nairon.Silva
 */
public interface ViewInstituicaoServico extends ContaCapitalComumServico {
	
	/**
	 * {@link br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao#consultarCooperativasAtivas(Integer)}
	 * @param numCoopPai opcional
	 * @return
	 * @throws BancoobException
	 */
	List<InstituicaoCooperativaSCIDTO> consultarCooperativasAtivas(Integer numCoopPai) throws BancoobException;
	
	/**
	 * {@link br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao#listarCentrais()}
	 * @return
	 * @throws BancoobException
	 */
	List<InstituicaoCooperativaSCIDTO> listarCentrais() throws BancoobException;

	/**
	 * {@link br.com.sicoob.cca.comum.persistencia.dao.interfaces.ViewInstituicaoDao#listarCentrais()}
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