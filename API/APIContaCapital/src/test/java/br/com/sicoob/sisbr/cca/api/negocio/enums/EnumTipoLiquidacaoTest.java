package br.com.sicoob.sisbr.cca.api.negocio.enums;

import org.junit.Assert;
import org.junit.Test;

public class EnumTipoLiquidacaoTest {

	@Test
	public void deveConterCodigos() {
		final Integer zero = 0;
		EnumTipoLiquidacao[] values = EnumTipoLiquidacao.values();
		for (EnumTipoLiquidacao enumTipoLiquidacao : values) {
			Assert.assertFalse(zero.equals(enumTipoLiquidacao.getCodigo()));
		}
	}
	
}
