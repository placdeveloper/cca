/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.conversor;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.sisbr.cca.cadastro.vo.CondicaoEstatutariaVO;

/**
 * @author Marco.Nascimento
 */
public class ConversorCondicaoEstatutaria implements Serializable {
	
	/**
	 * @param dto
	 * @param vo
	 * @throws BancoobException
	 */
	public CondicaoEstatutariaVO dtoParaVo(CondicaoEstatutariaDTO dto) throws BancoobException {
		CondicaoEstatutariaVO vo = new CondicaoEstatutariaVO();
		vo.setIdConfiguracaoCapital(dto.getIdConfiguracaoCapital());
		vo.setNomeAgrupadorConfiguracaoCapital(dto.getNomeAgrupadorConfiguracaoCapital());
		vo.setNomeConfiguracaoCapital(dto.getNomeConfiguracaoCapital());
		vo.setValorConfiguracao(dto.getValorConfiguracao());
		return vo;
	}
}