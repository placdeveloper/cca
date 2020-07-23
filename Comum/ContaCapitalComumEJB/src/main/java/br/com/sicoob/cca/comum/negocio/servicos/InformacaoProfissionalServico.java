package br.com.sicoob.cca.comum.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.InformacaoProfissionalDTO;

/**
 * InformacaoProfissionalServico
 * @author Nairon.Silva
 */
public interface InformacaoProfissionalServico extends ContaCapitalComumServico {

	/**
	 * Consulta por idContaCapital
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	List<InformacaoProfissionalDTO> consultarInformacaoProfissional(Integer idContaCapital) throws BancoobException;
	
}
