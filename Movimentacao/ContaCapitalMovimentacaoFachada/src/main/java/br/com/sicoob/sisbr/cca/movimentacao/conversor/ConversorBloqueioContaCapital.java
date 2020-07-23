package br.com.sicoob.sisbr.cca.movimentacao.conversor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO;

/**
 * Conversor de DTO e VO para funcionalidade de bloqueio de saldo de capital
 * @author Nairon.Silva
 */
public class ConversorBloqueioContaCapital implements Serializable {

	private static final long serialVersionUID = -3727930233502615365L;
	
	/**
	 * Converte BloqueioContaCapitalVO para BloqueioContaCapitalDTO 
	 * @param vo
	 * @return
	 */
	public BloqueioContaCapitalDTO converterVOParaDTO(BloqueioContaCapitalVO vo) {
		BloqueioContaCapitalDTO dto = new BloqueioContaCapitalDTO();
		dto.setIdBloqueio(vo.getIdBloqueio());
		dto.setIdContaCapital(vo.getIdContaCapital());
		dto.setIdInstituicao(vo.getIdInstituicao());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setNomePessoa(vo.getNomePessoa());
		dto.setCpfCnpj(vo.getCpfCnpj());
		dto.setCodTipoBloqueio(vo.getCodTipoBloqueio());
		dto.setIdTipoBloqueio(vo.getIdTipoBloqueio());
		dto.setNomeTipoBloqueio(vo.getNomeTipoBloqueio());
		dto.setNumProtocolo(vo.getNumProtocolo());
		dto.setNumProcesso(vo.getNumProcesso());
		dto.setCodSituacaoBloqueio(vo.getCodSituacaoBloqueio());
		dto.setDataBloqueio(vo.getDataBloqueio());
		dto.setDataDesbloqueio(vo.getDataDesbloqueio());
		dto.setValorBloqueado(vo.getValorBloqueado());
		dto.setValorDesbloqueio(vo.getValorDesbloqueio());
		dto.setDataProtocolo(vo.getDataProtocolo());
		dto.setAtivo(vo.getAtivo());
		return dto;
	}
	
	/**
	 * Converte BloqueioContaCapitalDTO para BloqueioContaCapitalVO
	 * @param dto
	 * @return
	 */
	public BloqueioContaCapitalVO converterDTOParaVO(BloqueioContaCapitalDTO dto) {
		BloqueioContaCapitalVO vo = new BloqueioContaCapitalVO();
		vo.setIdBloqueio(dto.getIdBloqueio());
		vo.setIdContaCapital(dto.getIdContaCapital());
		vo.setIdInstituicao(dto.getIdInstituicao());
		vo.setNumContaCapital(dto.getNumContaCapital());
		vo.setNomePessoa(dto.getNomePessoa());
		vo.setCpfCnpj(dto.getCpfCnpj());
		vo.setCodTipoBloqueio(dto.getCodTipoBloqueio());
		vo.setIdTipoBloqueio(dto.getIdTipoBloqueio());
		vo.setNomeTipoBloqueio(dto.getNomeTipoBloqueio());
		vo.setNumProtocolo(dto.getNumProtocolo());
		vo.setNumProcesso(dto.getNumProcesso());
		vo.setCodSituacaoBloqueio(dto.getCodSituacaoBloqueio());
		vo.setDataBloqueio(dto.getDataBloqueio());
		vo.setDataDesbloqueio(dto.getDataDesbloqueio());
		vo.setValorBloqueado(dto.getValorBloqueado());
		vo.setValorDesbloqueio(dto.getValorDesbloqueio());
		vo.setDataProtocolo(dto.getDataProtocolo());
		vo.setAtivo(dto.getAtivo());
		return vo;
	}
	
	/**
	 * Converte lista de BloqueioContaCapitalDTO para lista de BloqueioContaCapitalVO
	 * @param dtos
	 * @return
	 */
	public List<BloqueioContaCapitalVO> converterListaDTOParaVO(List<BloqueioContaCapitalDTO> dtos) {
		List<BloqueioContaCapitalVO> vos = new ArrayList<BloqueioContaCapitalVO>();
		for (BloqueioContaCapitalDTO dto : dtos) {
			vos.add(converterDTOParaVO(dto));
		}
		return vos;
	}
	
	/**
	 * Converte lista de BloqueioContaCapitalVO para lista de BloqueioContaCapitalDTO
	 * @param vos
	 * @return
	 */
	public List<BloqueioContaCapitalDTO> converterListaVOParaDTO(List<BloqueioContaCapitalVO> vos) {
		List<BloqueioContaCapitalDTO> dtos = new ArrayList<BloqueioContaCapitalDTO>();
		for (BloqueioContaCapitalVO vo : vos) {
			dtos.add(converterVOParaDTO(vo));
		}
		return dtos;
	}

}
