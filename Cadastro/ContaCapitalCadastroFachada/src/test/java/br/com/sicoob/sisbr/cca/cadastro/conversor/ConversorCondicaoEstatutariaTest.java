package br.com.sicoob.sisbr.cca.cadastro.conversor;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.sisbr.cca.cadastro.vo.CondicaoEstatutariaVO;

public class ConversorCondicaoEstatutariaTest {

	private ConversorCondicaoEstatutaria conversor = new ConversorCondicaoEstatutaria();
	
	@Test
	public void dtoParaVoTest() throws BancoobException {
		final int um = 1;
		CondicaoEstatutariaDTO dto = new CondicaoEstatutariaDTO();
		dto.setIdConfiguracaoCapital(um);
		dto.setNomeAgrupadorConfiguracaoCapital("nomeAgrupadorConfiguracaoCapital");
		dto.setNomeConfiguracaoCapital("nomeConfiguracaoCapital");
		dto.setValorConfiguracao("valorConfiguracao");
		CondicaoEstatutariaVO vo = conversor.dtoParaVo(dto);
		Assert.assertEquals(vo.getIdConfiguracaoCapital(), dto.getIdConfiguracaoCapital());
		Assert.assertEquals(vo.getNomeAgrupadorConfiguracaoCapital(), dto.getNomeAgrupadorConfiguracaoCapital());
		Assert.assertEquals(vo.getNomeConfiguracaoCapital(), dto.getNomeConfiguracaoCapital());
		Assert.assertEquals(vo.getValorConfiguracao(), dto.getValorConfiguracao());
	}
	
}
