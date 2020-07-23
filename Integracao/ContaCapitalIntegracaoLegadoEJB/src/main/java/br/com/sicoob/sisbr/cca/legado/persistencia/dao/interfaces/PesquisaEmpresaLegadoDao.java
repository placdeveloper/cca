/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.PesquisaEmpresaDTO;

/**
 * @author Marco.Nascimento
 */
public interface PesquisaEmpresaLegadoDao {

	/**
	 * Pesquisar.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PesquisaEmpresaDTO> pesquisar(PesquisaEmpresaDTO dto) throws BancoobException;
}