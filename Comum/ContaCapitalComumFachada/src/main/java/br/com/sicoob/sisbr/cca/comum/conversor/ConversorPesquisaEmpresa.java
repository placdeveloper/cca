/*
 * 
 */
package br.com.sicoob.sisbr.cca.comum.conversor;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.cca.comum.vo.PesquisaEmpresaVO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.PesquisaEmpresaDTO;

/**
 * A Classe ConversorPesquisaEmpresa.
 */
public class ConversorPesquisaEmpresa {

	/**
	 * O método Vo para dto.
	 *
	 * @param dto o valor de dto
	 * @param vo o valor de vo
	 */
	public void voParaDto(PesquisaEmpresaDTO dto, PesquisaEmpresaVO vo) {
		dto.setDescNomePessoa(vo.getDescNomePessoa());
		dto.setDiaFolha(vo.getDiaFolha());
		dto.setNumCGC_CPF(vo.getNumCGC_CPF());
		dto.setNumPessoaJuridica(vo.getNumPessoaJuridica());
		dto.setQtdeDiasGeraInf(vo.getQtdeDiasGeraInf());
	}
	
	/**
	 * O método Dto para vo.
	 *
	 * @param dto o valor de dto
	 * @param vo o valor de vo
	 */
	public void dtoParaVo(PesquisaEmpresaDTO dto, PesquisaEmpresaVO vo) {
		vo.setDescNomePessoa(dto.getDescNomePessoa());
		vo.setDiaFolha(dto.getDiaFolha());
		vo.setNumCGC_CPF(dto.getNumCGC_CPF());
		vo.setNumPessoaJuridica(dto.getNumPessoaJuridica());
		vo.setQtdeDiasGeraInf(dto.getQtdeDiasGeraInf());
	}
	
	/**
	 * Dto para vo.
	 *
	 * @param lst o valor de lst
	 * @return List
	 */
	public List<PesquisaEmpresaVO> dtoParaVo(List<PesquisaEmpresaDTO> lst) {
		List<PesquisaEmpresaVO> retorno = new ArrayList<PesquisaEmpresaVO>(0);
		PesquisaEmpresaVO vo = null;
		
		for(PesquisaEmpresaDTO dto : lst) {
			vo = new PesquisaEmpresaVO();
			dtoParaVo(dto, vo);
			retorno.add(vo);
		}
		
		return retorno;
	}
}