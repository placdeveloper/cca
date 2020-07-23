package br.com.sicoob.cca.frontoffice.negocio.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegadoPK;

public class ParcelamentoCCAPKHelperTest {

	@Test
	public void testConstrutorPrivado() throws Exception {
		Constructor<ParcelamentoCCAPKHelper> constructor = ParcelamentoCCAPKHelper.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void deveRetornarNull() {
		Assert.assertNull(ParcelamentoCCAPKHelper.toStringPK(null));
		Assert.assertNull(ParcelamentoCCAPKHelper.toParcelamentoCCALegadoPK(null));
	}
	
	@Test
	public void deveTransformarCorretamente() {
		String pkStr = "123-10-0-1";
		ParcelamentoCCALegadoPK parcelamentoCCALegadoPK = ParcelamentoCCAPKHelper.toParcelamentoCCALegadoPK(pkStr);
		String pkStrRet = ParcelamentoCCAPKHelper.toStringPK(parcelamentoCCALegadoPK);
		Assert.assertEquals(pkStr, pkStrRet);
	}
	
}
