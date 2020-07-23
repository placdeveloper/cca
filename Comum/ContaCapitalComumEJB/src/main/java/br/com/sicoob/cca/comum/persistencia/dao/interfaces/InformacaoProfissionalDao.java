package br.com.sicoob.cca.comum.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.InformacaoProfissionalDTO;

/**
 * InformacaoProfissionalDao
 * @author Nairon.Silva
 */
public interface InformacaoProfissionalDao {

	/**
	 * Consulta por idContaCapital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	List<InformacaoProfissionalDTO> consultarInformacaoProfissional(Integer idContaCapital) throws BancoobException;
}