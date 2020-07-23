package br.com.sicoob.sisbr.cca.movimentacao.conversor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContratoLiquidacaoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ContratoLiquidacaoVO;

/**
 * ConversorContratoLiquidacao
 */
public class ConversorContratoLiquidacao {

	/**
	 * @param dtos
	 * @return
	 */
	public List<ContratoLiquidacaoVO> converterListaDTOparaVO(List<ContratoLiquidacaoDTO> dtos) {
		List<ContratoLiquidacaoVO> vos = new ArrayList<ContratoLiquidacaoVO>();
		for (ContratoLiquidacaoDTO dto : dtos) {
			vos.add(converterDTOparaVO(dto));
		}
		return vos;
	}
	
	/**
	 * @param vos
	 * @return
	 */
	public List<ContratoLiquidacaoDTO> converterListaVOparaDTO(List<ContratoLiquidacaoVO> vos) {
		List<ContratoLiquidacaoDTO> dtos = new ArrayList<ContratoLiquidacaoDTO>();
		for (ContratoLiquidacaoVO vo : vos) {
			dtos.add(converterVOparaDTO(vo));
		}
		return dtos;
	}
	
	/**
	 * @param dto
	 * @return
	 */
	public ContratoLiquidacaoVO converterDTOparaVO(ContratoLiquidacaoDTO dto) {
		ContratoLiquidacaoVO vo = new ContratoLiquidacaoVO();
		vo.setIdProduto(dto.getIdProduto());              
		vo.setIdModalidadeProduto(dto.getIdModalidadeProduto());    
		vo.setIdOrigemOperacaoCredito(dto.getIdOrigemOperacaoCredito());
		vo.setNumDiasAtraso(dto.getNumDiasAtraso());         
		vo.setNumOperacaoCredito(dto.getNumOperacaoCredito());     
		vo.setQtdParcelasOperacao(dto.getQtdParcelasOperacao());    
		vo.setQtdParcelasAtraso(dto.getQtdParcelasAtraso());   
		vo.setQtdParcelasAberto(dto.getQtdParcelasAberto());
		vo.setDescOperacaoCredito(dto.getDescOperacaoCredito());
		vo.setDescProduto(dto.getDescProduto());        
		vo.setDescLinha(dto.getDescLinha());
		vo.setDescTaxaJuros(dto.getDescTaxaJuros()); 
		vo.setIdNivelRisco(dto.getIdNivelRisco());       
		vo.setDescErro(dto.getDescErro());
		if (dto.getDataVencimento() != null) {
			vo.setDataVencimento(new DateTimeDB(dto.getDataVencimento().getTime()));       
		}
		if (dto.getDataUltimaLiquidacao() != null) {
			vo.setDataUltimaLiquidacao(new DateTimeDB(dto.getDataUltimaLiquidacao().getTime())); 
		}
		if (dto.getDataEntradaOperacao() != null) {
			vo.setDataEntradaOperacao(new DateTimeDB(dto.getDataEntradaOperacao().getTime()));  
		}
		vo.setIdOperacaoSISBR20(dto.getIdOperacaoSISBR20());
		vo.setBolLegado(dto.getBolLegado());   
		vo.setBolErro(dto.getBolErro());     
		vo.setBolRotativo(dto.getBolRotativo()); 
		vo.setValorPrincipal(dto.getValorPrincipal());          
		vo.setValorRendas(dto.getValorRendas());             
		vo.setValorMulta(dto.getValorMulta());              
		vo.setValorMora(dto.getValorMora());               
		vo.setValorOutrosEncargos(dto.getValorOutrosEncargos());     
		vo.setValorTotalEncargosAtraso(dto.getValorTotalEncargosAtraso());
		vo.setValorAtrasoRenegociado(dto.getValorAtrasoRenegociado());  
		vo.setValorContratado(dto.getValorContratado());         
		vo.setValorSaldoContabil(dto.getValorSaldoContabil());      
		vo.setValorSaldoGanhoAAuferir(dto.getValorSaldoGanhoAAuferir()); 
		vo.setValorCreditoIOF(dto.getValorCreditoIOF());         
		vo.setPercAliquotaDiarioIOF(dto.getPercAliquotaDiarioIOF());   
		vo.setPercAliquotaRelativaIOF(dto.getPercAliquotaRelativaIOF()); 
		vo.setValorQuitacao(dto.getValorQuitacao());
		return vo;
	}
	
	/**
	 * @param vo
	 * @return
	 */
	public ContratoLiquidacaoDTO converterVOparaDTO(ContratoLiquidacaoVO vo) {
		ContratoLiquidacaoDTO dto = new ContratoLiquidacaoDTO();
		dto.setIdProduto(vo.getIdProduto());              
		dto.setIdModalidadeProduto(vo.getIdModalidadeProduto());    
		dto.setIdOrigemOperacaoCredito(vo.getIdOrigemOperacaoCredito());
		dto.setNumDiasAtraso(vo.getNumDiasAtraso());         
		dto.setNumOperacaoCredito(vo.getNumOperacaoCredito());     
		dto.setQtdParcelasOperacao(vo.getQtdParcelasOperacao());    
		dto.setQtdParcelasAtraso(vo.getQtdParcelasAtraso());   
		dto.setQtdParcelasAberto(vo.getQtdParcelasAberto());
		dto.setDescOperacaoCredito(vo.getDescOperacaoCredito());
		dto.setDescProduto(vo.getDescProduto());        
		dto.setDescLinha(vo.getDescLinha());
		dto.setDescTaxaJuros(vo.getDescTaxaJuros()); 
		dto.setIdNivelRisco(vo.getIdNivelRisco());       
		dto.setDescErro(vo.getDescErro());
		if (vo.getDataVencimento() != null) {
			dto.setDataVencimento(new Date(vo.getDataVencimento().getTime()));       
		}
		if (vo.getDataUltimaLiquidacao() != null) {
			dto.setDataUltimaLiquidacao(new Date(vo.getDataUltimaLiquidacao().getTime())); 
		}
		if (vo.getDataEntradaOperacao() != null) {
			dto.setDataEntradaOperacao(new Date(vo.getDataEntradaOperacao().getTime()));  
		}
		dto.setIdOperacaoSISBR20(vo.getIdOperacaoSISBR20());
		dto.setBolLegado(vo.getBolLegado());   
		dto.setBolErro(vo.getBolErro());     
		dto.setBolRotativo(vo.getBolRotativo()); 
		dto.setValorPrincipal(vo.getValorPrincipal());          
		dto.setValorRendas(vo.getValorRendas());             
		dto.setValorMulta(vo.getValorMulta());              
		dto.setValorMora(vo.getValorMora());               
		dto.setValorOutrosEncargos(vo.getValorOutrosEncargos());     
		dto.setValorTotalEncargosAtraso(vo.getValorTotalEncargosAtraso());
		dto.setValorAtrasoRenegociado(vo.getValorAtrasoRenegociado());  
		dto.setValorContratado(vo.getValorContratado());         
		dto.setValorSaldoContabil(vo.getValorSaldoContabil());      
		dto.setValorSaldoGanhoAAuferir(vo.getValorSaldoGanhoAAuferir()); 
		dto.setValorCreditoIOF(vo.getValorCreditoIOF());         
		dto.setPercAliquotaDiarioIOF(vo.getPercAliquotaDiarioIOF());   
		dto.setPercAliquotaRelativaIOF(vo.getPercAliquotaRelativaIOF()); 
		dto.setValorQuitacao(vo.getValorQuitacao());
		return dto;
	}
	
}
