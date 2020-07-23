package br.com.sicoob.sisbr.cca.processamento.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

public class ProcessamentoUtilTest {

	@Test
	public void testConstrutorPrivado() throws Exception {
		Constructor<ProcessamentoUtil> constructor = ProcessamentoUtil.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void getAnoMesFormatadoTest() {
		Assert.assertEquals(200001L, ProcessamentoUtil.getAnoMesFormatado(DateTime.now().withYear(2000).withMonthOfYear(1)).longValue());
	}
	
	@Test
	public void getUltimoDiaMesAnteriorTest() throws BancoobException {
		Assert.assertNotNull(ProcessamentoUtil.getUltimoDiaMesAnterior());
	}
	
	@Test
	public void getAnoMesFormatadoAnteriorTest() {
		Assert.assertEquals(200001L, ProcessamentoUtil.getAnoMesFormatadoAnterior(DateTime.now().withYear(2000).withMonthOfYear(3)).longValue());
	}
	
	@Test
	public void getAnoMesReplicacaoFormatadoAnteriorTest() {
		Assert.assertEquals(200001L, ProcessamentoUtil.getAnoMesReplicacaoFormatadoAnterior(DateTime.now().withYear(2000).withMonthOfYear(4)).longValue());
	}
	
	
}
