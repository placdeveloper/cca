package br.com.sicoob.cca.frontoffice.negocio.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.TipoParametro;
import br.com.sicoob.cca.frontoffice.negocio.excecao.ContaCapitalFrontofficeNegocioException;

public class ParametroSRTBCCATest {

	@Test
	public void deveExtrairParametro() throws ContaCapitalFrontofficeNegocioException {
		Mensagem mensagem = new Mensagem();
		List<Parametro> parametros = new ArrayList<Parametro>();
		Parametro parametro = new Parametro(ParametroSRTBCCA.CPF_CNPJ.getRotulo(), TipoParametro.ENTRADA, "111.111.111-11", 1);
		parametros.add(parametro);
		mensagem.setParametros(parametros);
		Assert.assertNotNull(ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.CPF_CNPJ, String.class));
	}
	
	@Test
	public void deveExibirNaoNulo() {
		Assert.assertNotNull(ParametroSRTBCCA.CPF_CNPJ.getNomeRotulo());
	}
	
	@Test(expected=ContaCapitalFrontofficeNegocioException.class)
	public void deveLancarExcecaoVazio() throws ContaCapitalFrontofficeNegocioException {
		Mensagem mensagem = new Mensagem();
		mensagem.setParametros(new ArrayList<Parametro>());
		ParametroSRTBCCA.extrairParametro(mensagem, ParametroSRTBCCA.CPF_CNPJ, String.class);
	}
	
	@Test
	public void deveExtrairIdInstituicao() throws ContaCapitalFrontofficeNegocioException {
		Mensagem mensagem = new Mensagem();
		mensagem.setIdInstituicao(1);
		Assert.assertNotNull(ParametroSRTBCCA.extrairIdInstituicao(mensagem));
	}
	
	@Test(expected=ContaCapitalFrontofficeNegocioException.class)
	public void deveLancarExcecaoSemIdInstituicao() throws ContaCapitalFrontofficeNegocioException {
		Mensagem mensagem = new Mensagem();
		ParametroSRTBCCA.extrairIdInstituicao(mensagem);
	}
	
}
