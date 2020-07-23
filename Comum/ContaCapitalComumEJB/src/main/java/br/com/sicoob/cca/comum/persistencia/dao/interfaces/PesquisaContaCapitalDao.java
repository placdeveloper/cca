/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;

/**
 * @author Marco.Nascimento
 */
public interface PesquisaContaCapitalDao {

	/**
	 * Pesquisar.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PesquisaContaCapitalDTO> pesquisar(PesquisaContaCapitalDTO dto) throws BancoobException;
}
