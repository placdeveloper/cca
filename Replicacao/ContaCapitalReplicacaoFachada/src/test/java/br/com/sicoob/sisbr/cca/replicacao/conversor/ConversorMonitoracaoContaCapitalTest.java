package br.com.sicoob.sisbr.cca.replicacao.conversor;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoContaCapitalVO;

public class ConversorMonitoracaoContaCapitalTest {

	private ConversorMonitoracaoContaCapital conversor = new ConversorMonitoracaoContaCapital();
	
	private static final Integer UM = 1;
	
	@Test
	public void converterCapitalEntidadeparaVOTest() throws BancoobException {
		ContaCapital contaCapital = new ContaCapital();
		contaCapital.setDataMatricula(new DateTimeDB());
		contaCapital.setDataSaida(new DateTimeDB());
		contaCapital.setId(UM);
		contaCapital.setIdInstituicao(UM);
		contaCapital.setIdPessoa(UM);
		contaCapital.setIdUsuario(UM.toString());
		contaCapital.setNumContaCapital(UM);
		contaCapital.setValorBloq(BigDecimal.ONE);
		contaCapital.setValorDevol(BigDecimal.ONE);
		contaCapital.setValorInteg(BigDecimal.ONE);
		contaCapital.setValorSubs(BigDecimal.ONE);
		MonitoracaoContaCapitalVO vo = conversor.converterCapitalEntidadeparaVO(contaCapital);
		Assert.assertEquals(vo.getDataMatricula(), contaCapital.getDataMatricula());
		Assert.assertEquals(vo.getDataSaida(), contaCapital.getDataSaida());
		Assert.assertEquals(vo.getIdContaCapital(), contaCapital.getId());
		Assert.assertEquals(vo.getIdInstituicao(), contaCapital.getIdInstituicao());
		Assert.assertEquals(vo.getIdPessoa(), contaCapital.getIdPessoa());
		Assert.assertEquals(vo.getIdUsuario(), contaCapital.getIdUsuario());
		Assert.assertEquals(vo.getNumMatricula(), contaCapital.getNumContaCapital());
		Assert.assertEquals(vo.getValorBloq(), contaCapital.getValorBloq());
		Assert.assertEquals(vo.getValorDevol(), contaCapital.getValorDevol());
		Assert.assertEquals(vo.getValorInteg(), contaCapital.getValorInteg());
		Assert.assertEquals(vo.getValorSubs(), contaCapital.getValorSubs());
	}
	
	@Test
	public void converterParcelamentoCapitalEntidadeparaVOTest() throws BancoobException {
		conversor.converterParcelamentoCapitalEntidadeparaVO(new Parcelamento());
	}
	
	@Test
	public void converterLancamentoCapitalEntidadeparaVOTest() throws BancoobException {
		conversor.converterLancamentoCapitalEntidadeparaVO(new LancamentoContaCapital());
	}
	
}
