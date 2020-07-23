/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.PesquisaEmpresaDTO;

/**
 * @author Marco.Nascimento
 */
public interface PesquisaEmpresaLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Pesquisa por Empresa
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<PesquisaEmpresaDTO> pesquisar(PesquisaEmpresaDTO dto) throws BancoobException;
}