package br.com.sicoob.sisbr.cca.integracao.negocio.enumintegracao;

import org.junit.Assert;
import org.junit.Test;

public class TipoReferenciaEnumTest {

	@Test
	public void deveEncontrarCodigos() {
		TipoReferenciaEnum[] values = TipoReferenciaEnum.values();
		for (TipoReferenciaEnum enumTipoReferenciaEnum : values) {
			TipoReferenciaEnum tipoPorCodigo = TipoReferenciaEnum.find(enumTipoReferenciaEnum.getCodigo());
			Assert.assertEquals(enumTipoReferenciaEnum, tipoPorCodigo);
		}
	}
	
	@Test
	public void naoDeveEncontrarCodigo() {
		Assert.assertNull(TipoReferenciaEnum.find((short)-1));
	}
	
}
