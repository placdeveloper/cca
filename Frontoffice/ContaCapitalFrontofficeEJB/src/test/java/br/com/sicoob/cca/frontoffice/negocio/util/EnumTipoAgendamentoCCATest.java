package br.com.sicoob.cca.frontoffice.negocio.util;

import org.junit.Assert;
import org.junit.Test;

public class EnumTipoAgendamentoCCATest {

	@Test
	public void deveEncontrarCodigos() {
		EnumTipoAgendamentoCCA[] values = EnumTipoAgendamentoCCA.values();
		for (EnumTipoAgendamentoCCA enumTipoAgendamentoCCA : values) {
			EnumTipoAgendamentoCCA tipoPorCodigo = EnumTipoAgendamentoCCA.getTipoPorCodigo(enumTipoAgendamentoCCA.getCodigo());
			Assert.assertEquals(enumTipoAgendamentoCCA, tipoPorCodigo);
		}
	}
	
	@Test
	public void naoDeveEncontrarCodigo() {
		Assert.assertNull(EnumTipoAgendamentoCCA.getTipoPorCodigo(-1));
	}
	
}
