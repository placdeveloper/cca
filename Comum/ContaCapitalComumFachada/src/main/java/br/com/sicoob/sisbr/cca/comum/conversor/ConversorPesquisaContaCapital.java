/*
 * 
 */
package br.com.sicoob.sisbr.cca.comum.conversor;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.sisbr.cca.comum.vo.PesquisaContaCapitalVO;

/**
 * A Classe ConversorPesquisaContaCapital.
 */
public class ConversorPesquisaContaCapital {

	/**
	 * O método Vo para dto.
	 *
	 * @param dto o valor de dto
	 * @param vo o valor de vo
	 */
	public void voParaDto(PesquisaContaCapitalDTO dto, PesquisaContaCapitalVO vo) {
		dto.setCpfCnpj(vo.getCpfCnpj());
		dto.setDescSituacaoContaCapital(vo.getDescSituacaoContaCapital());
		dto.setIdInstituicao(vo.getIdInstituicao());
		dto.setIdPessoa(vo.getIdPessoa());
		dto.setIdPessoaLegado(vo.getIdPessoaLegado());
		dto.setIdSituacaoContaCapital(vo.getIdSituacaoContaCapital());
		dto.setIdSituacaoCadastro(vo.getIdSituacaoCadastro());
		dto.setNome(vo.getNome());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setDescSituacaoContaCapital(vo.getDescSituacaoContaCapital());
		dto.setIdContaCapital(vo.getIdContaCapital());
	}
	
	/**
	 * O método Dto para vo.
	 *
	 * @param dto o valor de dto
	 * @param vo o valor de vo
	 */
	public void dtoParaVo(PesquisaContaCapitalDTO dto, PesquisaContaCapitalVO vo) {
		vo.setCpfCnpj(dto.getCpfCnpj());
		vo.setDescSituacaoContaCapital(dto.getDescSituacaoContaCapital());
		vo.setIdInstituicao(dto.getIdInstituicao());
		vo.setIdPessoa(dto.getIdPessoa());
		vo.setIdPessoaLegado(dto.getIdPessoaLegado());
		vo.setIdSituacaoContaCapital(dto.getIdSituacaoContaCapital());
		vo.setIdSituacaoCadastro(dto.getIdSituacaoCadastro());
		vo.setNome(dto.getNome());
		vo.setNumContaCapital(dto.getNumContaCapital());
		vo.setDescSituacaoContaCapital(dto.getDescSituacaoContaCapital());
		vo.setIdContaCapital(dto.getIdContaCapital());
	}
	
	/**
	 * Dto para vo.
	 *
	 * @param lst o valor de lst
	 * @return List
	 */
	public List<PesquisaContaCapitalVO> dtoParaVo(List<PesquisaContaCapitalDTO> lst) {
		List<PesquisaContaCapitalVO> retorno = new ArrayList<PesquisaContaCapitalVO>(0);
		PesquisaContaCapitalVO vo = null;
		
		for (PesquisaContaCapitalDTO dto : lst) {
			vo = new PesquisaContaCapitalVO();
			vo.setCpfCnpj(dto.getCpfCnpj());
			vo.setDescSituacaoContaCapital(dto.getDescSituacaoContaCapital());
			vo.setIdInstituicao(dto.getIdInstituicao());
			vo.setIdPessoa(dto.getIdPessoa());
			vo.setIdPessoaLegado(dto.getIdPessoaLegado());
			vo.setIdSituacaoContaCapital(dto.getIdSituacaoContaCapital());
			vo.setNome(dto.getNome());
			vo.setNumContaCapital(dto.getNumContaCapital());
			vo.setIdContaCapital(dto.getIdContaCapital());
			vo.setCodTipoPessoa(dto.getCodTipoPessoa());
			
			retorno.add(vo);
		}
		
		return retorno;
	}
}
