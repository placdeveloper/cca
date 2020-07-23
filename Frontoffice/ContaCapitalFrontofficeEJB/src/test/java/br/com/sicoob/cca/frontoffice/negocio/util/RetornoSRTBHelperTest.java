package br.com.sicoob.cca.frontoffice.negocio.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;

public class RetornoSRTBHelperTest {

	private static final int ZERO = 0;
	private static final int UM = 1;
	private static final int ANO_2000 = 2000;
	private static final int HORA_12 = 12;
	private static final int MINUTO_30 = 30;
	
	private LocalDateTime dataHora = LocalDateTime.now().withDayOfMonth(UM).withMonthOfYear(UM).withYear(ANO_2000)
										.withHourOfDay(HORA_12).withMinuteOfHour(MINUTO_30).withSecondOfMinute(ZERO).withMillisOfSecond(ZERO);

	@Test
	public void testConstrutorPrivado() throws Exception {
		Constructor<RetornoSRTBHelper> constructor = RetornoSRTBHelper.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void formatarValorTest() {
		Assert.assertEquals("10.00", RetornoSRTBHelper.formatarValor(BigDecimal.TEN));
	}

	@Test
	public void formatarHoraTest() {
		Assert.assertEquals("12:30", RetornoSRTBHelper.formatarHora(dataHora.toDate()));
	}
	
	@Test
	public void formatarDataTest() {
		Assert.assertEquals("2000-01-01 12:30:00.000", RetornoSRTBHelper.formatarData(dataHora.toDate()));
	}
	
	@Test
	public void formatarDataNullTest() {
		Assert.assertNull(RetornoSRTBHelper.formatarData(null));
	}

}
