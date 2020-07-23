package br.com.sicoob.cca.comum.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ContaCapitalUtilTest {

	private static final int ZERO = 0;
	
	@Test
	public void testConstrutorPrivado() throws Exception {
		Constructor<ContaCapitalUtil> constructor = ContaCapitalUtil.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void formatarDataSemHoraTest() throws BancoobException {
		Date data = new Date();
		Date dataSemHora = ContaCapitalUtil.formatarDataSemHora(data);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataSemHora);
		Assert.assertEquals(ZERO, calendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(ZERO, calendar.get(Calendar.MINUTE));
		Assert.assertEquals(ZERO, calendar.get(Calendar.SECOND));
	}
	
	@Test
	public void formatarStringToDateTest() throws BancoobException {
		Assert.assertNotNull(ContaCapitalUtil.formatarStringToDate("01/01/2000"));
	}
	
	@Test(expected=BancoobException.class)
	public void formatarStringToDateExceptionTest() throws BancoobException {
		Assert.assertNotNull(ContaCapitalUtil.formatarStringToDate("2000-01-01"));
	}
	
	@Test
	public void formatarDataUSTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 2000);
		Assert.assertEquals("2000-01-01", ContaCapitalUtil.formatarDataUS(calendar.getTime()));
	}
	
	@Test
	public void formatarDataUSNullTest() {
		Assert.assertNull(null, ContaCapitalUtil.formatarDataUS(null));
	}
	
	@Test
	public void formatarDataBRTest() throws BancoobException {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 2000);
		Assert.assertEquals("01/01/2000", ContaCapitalUtil.formatarDataBR(calendar.getTime()));
	}
	
	@Test
	public void formatarDataMascaraTest() throws BancoobException {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 2000);
		Assert.assertEquals("01-01-2000", ContaCapitalUtil.formatarDataMascara(calendar.getTime(), "dd-MM-yyyy"));
	}
	
	@Test
	public void montarDateTimeTest() throws BancoobException {
		Assert.assertNotNull(ContaCapitalUtil.montarDateTime(new Date()));
	}
	
	@Test
	public void formatarValorTest() {
		Assert.assertEquals("R$ 10.000,00", ContaCapitalUtil.formatarValor(BigDecimal.valueOf(10000)));
	}
	
	@Test
	public void retirarMascaraCpfCnpjTest() {
		Assert.assertEquals("12345678900", ContaCapitalUtil.retirarMascaraCpfCnpj("123.456.789-00"));
		Assert.assertEquals("12345789000199", ContaCapitalUtil.retirarMascaraCpfCnpj("12.345.789/0001-99"));
	}
	
	@Test
	public void formatarStringToDateUSTest() throws BancoobException {
		Assert.assertNotNull(ContaCapitalUtil.formatarStringToDateUS("2012-10-10"));
	}
	
	@Test(expected = BancoobException.class)
	public void formatarStringToDateUS_Exception_Test() throws BancoobException {
		ContaCapitalUtil.formatarStringToDateUS("70/70/1010");
	}
	
	@Test
	public void formatarStringToDateUS_NullData_Test() throws BancoobException {
		Assert.assertNull(ContaCapitalUtil.formatarStringToDateUS(null));
	}	
	
	@Test
	public void formatarStringCpfTest() {
		Assert.assertNotNull(ContaCapitalUtil.formatarCpfCnpj("00000000000"));		
	}
	
	@Test
	public void formatarStringCnpjTest() {
		Assert.assertNotNull(ContaCapitalUtil.formatarCpfCnpj("0000000000000"));		
	}
	
	@Test
	public void formatarStringCpfCnpj_NULL_Test() {
		Assert.assertNotNull(ContaCapitalUtil.formatarCpfCnpj((null)));		
	}
	
	@Test
	public void completaComCaracterADireitaTest() {
		Assert.assertEquals("TESTE", ContaCapitalUtil.completaComCaracterADireita("TEST", "E", 5));
	}
	
	@Test
	public void completaComCaracterAEsquerdaTest() {
		Assert.assertEquals("ETEST", ContaCapitalUtil.completaComCaracterAEsquerda("TEST", "E", 5));
	}
	
	@Test
	public void formatarListaValoresEmptyTest() {
		List<Integer> valores = new ArrayList<>();
		Assert.assertEquals("", ContaCapitalUtil.formatarListaValores(valores));
	}
	
	@Test
	public void formatarListaValoresTest() {
		List<Integer> valores = new ArrayList<>();
		valores.add(1);
		valores.add(2);
		valores.add(3);
		valores.add(4);
		
		Assert.assertEquals("1, 2, 3 e 4", ContaCapitalUtil.formatarListaValores(valores));
	}
	
	@Test
	public void formatarListaValoresIN_Null_Test() throws BancoobException {
		Assert.assertEquals("", ContaCapitalUtil.formatarListaValoresIN(null));
	}
	
	@Test
	public void formatarListaValoresINTest() throws BancoobException {
		List<Object> valores = new ArrayList<>();
		valores.add(1);
		valores.add("teste");
		valores.add(45.6);
		valores.add(true);
		
		
		Assert.assertEquals("1,teste,45.6,true", ContaCapitalUtil.formatarListaValoresIN(valores));
	}
	
	@Test
	public void concatenarValores_Null_Test() {
		Assert.assertEquals("", ContaCapitalUtil.concatenarValores(",", null));
	}
	
	@Test
	public void concatenarValoresTest() {
		List<Integer> valores = new ArrayList<>();
		valores.add(1);
		valores.add(2);
		valores.add(3);
		valores.add(4);
		
		Assert.assertEquals("1,2,3,4", ContaCapitalUtil.concatenarValores(",", 1,2,3,4));
	}
	
	@Test
	public void getAnoMesFormatadoTest() {
		DateTime data = new DateTime(2010,8,1,20,20);
		Integer in = ContaCapitalUtil.getAnoMesFormatado(data);
		Assert.assertEquals(201008,  in.intValue());
	}
	
	@Test
	public void formatarStringToLocalDateTest() throws BancoobException {
		Assert.assertNotNull(ContaCapitalUtil.formatarStringToLocalDate("2018-01-01"));
	}
	
	@Test(expected = BancoobException.class)
	public void formatarStringToLocalDate_Exception_Test() throws BancoobException {
		ContaCapitalUtil.formatarStringToLocalDate("208/50/50");
	}
	
	@Test
	public void formatarLocalDateToStringTest() throws BancoobException {
		LocalDate date = LocalDate.of(2018, 1 ,1);
		Assert.assertEquals("2018-01-01", ContaCapitalUtil.formatarLocalDateToString(date));
	}
	
	@Test
	public void formatarLocalDateToString_Null_Test() throws BancoobException {
		Assert.assertNull(ContaCapitalUtil.formatarLocalDateToString(null));
	}
	
	@Test
	public void formatarLocalDateDate_Null_Test() throws BancoobException {
		Assert.assertNull(ContaCapitalUtil.formatarLocalDateToDate(null));
	}
	
}
