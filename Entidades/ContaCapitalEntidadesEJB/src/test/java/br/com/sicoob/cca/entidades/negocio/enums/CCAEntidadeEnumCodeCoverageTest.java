package br.com.sicoob.cca.entidades.negocio.enums;

import org.junit.Assert;
import org.junit.Test;

public class CCAEntidadeEnumCodeCoverageTest {

	@Test
	public void verificaEnumsNaoVazios() {
		Assert.assertTrue(EnumGrupoHistorico.values().length > 0);
		Assert.assertTrue(EnumMotivoDevolucao.values().length > 0);
		Assert.assertTrue(EnumPerfilConfiguracaoCapital.values().length > 0);
		Assert.assertTrue(EnumSituacaoCadastroProposta.values().length > 0);
		Assert.assertTrue(EnumSituacaoContaCapital.values().length > 0);
		Assert.assertTrue(EnumSituacaoParcela.values().length > 0);
		Assert.assertTrue(EnumSituacaoParcelamento.values().length > 0);
		Assert.assertTrue(EnumTipoCooperativa.values().length > 0);
		Assert.assertTrue(EnumTipoDocumento.values().length > 0);
		Assert.assertTrue(EnumTipoHistoricoCCA.values().length > 0);
		Assert.assertTrue(EnumTipoIntegralizacao.values().length > 0);
		Assert.assertTrue(EnumTipoLote.values().length > 0);
		Assert.assertTrue(EnumTipoParcelamento.values().length > 0);
		Assert.assertTrue(EnumTipoPeriodoDebito.values().length > 0);
		Assert.assertTrue(EnumTipoPesquisa.values().length > 0);
		Assert.assertTrue(EnumTipoPesquisaDebitoIndeterminado.values().length > 0);
		Assert.assertTrue(EnumTipoPessoa.values().length > 0);
		Assert.assertTrue(EnumTipoSubscricaoCapital.values().length > 0);
		Assert.assertTrue(EnumTipoValorConfiguracaoCapital.values().length > 0);
		Assert.assertTrue(EnumTipoValorDebito.values().length > 0);
	}
	
}
