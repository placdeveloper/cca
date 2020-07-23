package br.com.sicoob.sisbr.cca.movimentacao.conversor;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoArquivoRemessa;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoOutrosBancosDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.IntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RemessaIntegralizacaoOutrosBancosLegadoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.IntegralizacaoOutrosBancosVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.RemessaIntegralizacaoOutrosBancosLegadoVO;

/**
 * ConversorIntegralizacaoOutrosBancos
 */
public class ConversorIntegralizacaoOutrosBancos {

	/**
	 * @param dtos
	 * @return
	 */
	public List<IntegralizacaoOutrosBancosVO> converterListaDTOparaVO(List<IntegralizacaoOutrosBancosLegadoDTO> dtos) {
		List<IntegralizacaoOutrosBancosVO> vos = new ArrayList<IntegralizacaoOutrosBancosVO>();
		for (IntegralizacaoOutrosBancosLegadoDTO dto : dtos) {
			vos.add(converterDTOparaVO(dto));
		}
		return vos;
	}

	/**
	 * @param dto
	 * @return
	 */
	public IntegralizacaoOutrosBancosVO converterDTOparaVO(IntegralizacaoOutrosBancosLegadoDTO dto) {
		IntegralizacaoOutrosBancosVO vo = new IntegralizacaoOutrosBancosVO();
		vo.setId(dto.getId());
		vo.setNumCliente(dto.getNumCliente());
		vo.setNumMatricula(dto.getNumMatricula());
		vo.setDescNomePessoa(dto.getDescNomePessoa());
		vo.setNumCpfCnpj(dto.getNumCpfCnpj());
		vo.setNumBanco(dto.getNumBanco());
		vo.setNumAgencia(dto.getNumAgencia());
		vo.setNumDVAgencia(dto.getNumDVAgencia());
		vo.setNumContaCorrente(dto.getNumContaCorrente());
		vo.setNumDVContaCorrente(dto.getNumDVContaCorrente());
		vo.setNumBancoFavorecido(dto.getNumBancoFavorecido());
		vo.setNumAgenciaFavorecido(dto.getNumAgenciaFavorecido());
		vo.setNumContaFavorecido(dto.getNumContaFavorecido());
		vo.setNumDVAgenciaFavorecido(dto.getNumDVAgenciaFavorecido());
		vo.setDescBanco(dto.getDescBanco());
		vo.setValorIntegralizacao(dto.getValorIntegralizacao());
		vo.setContaPrincipal(dto.getContaPrincipal());
		vo.setBolIntegralizadoCapital(dto.getBolIntegralizadoCapital());
		vo.setSequencialArquivo(dto.getSequencialArquivo());
		vo.setSequencialDetalhe(dto.getSequencialDetalhe());
		vo.setNumParcela(dto.getNumParcela());
		vo.setNomeArquivo(dto.getNomeArquivo());
		
		return vo;
	}
	
	/**
	 * @param vos
	 * @return
	 */
	public List<IntegralizacaoOutrosBancosLegadoDTO> converterListaVOparaDTO(List<IntegralizacaoOutrosBancosVO> vos) {
		List<IntegralizacaoOutrosBancosLegadoDTO> dtos = new ArrayList<IntegralizacaoOutrosBancosLegadoDTO>();
		for (IntegralizacaoOutrosBancosVO vo : vos) {
			dtos.add(converterVOparaDTO(vo));
		}
		return dtos;
	}
	
	/**
	 * @param vo
	 * @return
	 */
	public IntegralizacaoOutrosBancosLegadoDTO converterVOparaDTO(IntegralizacaoOutrosBancosVO vo) {
		IntegralizacaoOutrosBancosLegadoDTO dto = new IntegralizacaoOutrosBancosLegadoDTO();
		dto.setId(vo.getId());
		dto.setNumCliente(vo.getNumCliente());
		dto.setNumMatricula(vo.getNumMatricula());
		dto.setDescNomePessoa(vo.getDescNomePessoa());
		dto.setNumCpfCnpj(vo.getNumCpfCnpj());
		dto.setNumBanco(vo.getNumBanco());
		dto.setNumAgencia(vo.getNumAgencia());
		dto.setNumDVAgencia(vo.getNumDVAgencia());
		dto.setNumContaCorrente(vo.getNumContaCorrente());
		dto.setNumDVContaCorrente(vo.getNumDVContaCorrente());
		dto.setNumBancoFavorecido(vo.getNumBancoFavorecido());
		dto.setNumAgenciaFavorecido(vo.getNumAgenciaFavorecido());
		dto.setNumContaFavorecido(vo.getNumContaFavorecido());
		dto.setNumDVAgenciaFavorecido(vo.getNumDVAgenciaFavorecido());
		dto.setDescBanco(vo.getDescBanco());
		dto.setValorIntegralizacao(vo.getValorIntegralizacao());
		dto.setContaPrincipal(vo.getContaPrincipal());
		dto.setBolIntegralizadoCapital(vo.getBolIntegralizadoCapital());
		dto.setSequencialArquivo(vo.getSequencialArquivo());
		dto.setSequencialDetalhe(vo.getSequencialDetalhe());
		dto.setNomeArquivo(vo.getNomeArquivo());
		dto.setNumParcela(vo.getNumParcela());
		
		return dto;
	}
	
	
	/**
	 * @param vo
	 * @return
	 */
	public IntegralizacaoOutrosBancosDTO converterVOparaDTOMov(IntegralizacaoOutrosBancosVO vo) {
		IntegralizacaoOutrosBancosDTO dto = new IntegralizacaoOutrosBancosDTO();
		dto.setId(vo.getId());
		dto.setNumCliente(vo.getNumCliente());
		dto.setNumMatricula(vo.getNumMatricula());
		dto.setDescNomePessoa(vo.getDescNomePessoa());
		dto.setNumCpfCnpj(vo.getNumCpfCnpj());
		dto.setNumBanco(vo.getNumBanco());
		dto.setNumAgencia(vo.getNumAgencia());
		dto.setNumDVAgencia(vo.getNumDVAgencia());
		dto.setNumContaCorrente(vo.getNumContaCorrente());
		dto.setNumDVContaCorrente(vo.getNumDVContaCorrente());
		dto.setNumBancoFavorecido(vo.getNumBancoFavorecido());
		dto.setNumAgenciaFavorecido(vo.getNumAgenciaFavorecido());
		dto.setNumContaFavorecido(vo.getNumContaFavorecido());
		dto.setNumDVAgenciaFavorecido(vo.getNumDVAgenciaFavorecido());
		dto.setDescBanco(vo.getDescBanco());
		dto.setValorIntegralizacao(vo.getValorIntegralizacao());
		dto.setContaPrincipal(vo.getContaPrincipal());
		dto.setBolIntegralizadoCapital(vo.getBolIntegralizadoCapital());
		dto.setSequencialArquivo(vo.getSequencialArquivo());
		dto.setSequencialDetalhe(vo.getSequencialDetalhe());
		dto.setNomeArquivo(vo.getNomeArquivo());
		dto.setNumParcela(vo.getNumParcela());
		
		return dto;
	}
		
	
	/**
	 * @param dto
	 * @return
	 */
	public RemessaIntegralizacaoOutrosBancosLegadoVO converterRemessaDTOparaVO(RemessaIntegralizacaoOutrosBancosLegadoDTO dto) {
		RemessaIntegralizacaoOutrosBancosLegadoVO vo = new RemessaIntegralizacaoOutrosBancosLegadoVO();
		
		vo.setSequencialArquivo(dto.getSequencialArquivo());
		vo.setNomeArquivo(dto.getNomeArquivo());
		vo.setDataGeracao(dto.getDataGeracao());		
		for (EnumSituacaoArquivoRemessa tipoPesquisa : EnumSituacaoArquivoRemessa.values()) {
			if(tipoPesquisa.getCodigo().equals(dto.getCodSituacaoArquivo())) {
				vo.setDescSituacaoArquivo(tipoPesquisa.getDescricao());						
			}
		}				
		vo.setNumBanco(dto.getNumBanco());
		vo.setDescBanco(dto.getDescBanco());
		vo.setNumAgencia(dto.getNumAgencia());
		vo.setQtdRegistros(dto.getQtdRegistros());
		vo.setValorTotal(dto.getValorTotal());
		vo.setNumAgenciaFavorecido(dto.getNumAgenciaFavorecido());
		vo.setNumContaFavorecido(dto.getNumContaFavorecido());
		vo.setIdNegocio(dto.getIdNegocio());
		vo.setValorLiquido(dto.getValorLiquido());
		vo.setCodSituacaoArquivo(dto.getCodSituacaoArquivo());
		
		return vo;
	}	
	
	/**
	 * @param dtos
	 * @return
	 */
	public List<RemessaIntegralizacaoOutrosBancosLegadoVO> converterRemessaListaDTOparaVO(List<RemessaIntegralizacaoOutrosBancosLegadoDTO> dtos) {
		List<RemessaIntegralizacaoOutrosBancosLegadoVO> vos = new ArrayList<RemessaIntegralizacaoOutrosBancosLegadoVO>();
		for (RemessaIntegralizacaoOutrosBancosLegadoDTO dto : dtos) {
			vos.add(converterRemessaDTOparaVO(dto));
		}
		return vos;
	}
	
}
