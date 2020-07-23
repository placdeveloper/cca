/*
 * 
 */
package br.com.sicoob.cca.comum.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;

/**
 * @author Marco.Nascimento
 */
public interface PesquisaContaCapitalServico extends ContaCapitalComumServico {
	
	/**
	 * Pesquisa por conta capital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<PesquisaContaCapitalDTO> pesquisar(PesquisaContaCapitalDTO dto) throws BancoobException;
}