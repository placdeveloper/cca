package br.com.sicoob.sisbr.cca.relatorios.conversor;

import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;

public class ConversorRelatoriosContaCapitalTest {

	private ConversorRelatoriosContaCapital<BancoobEntidade, Object> conversor = 
			new ConversorRelatoriosContaCapital<BancoobEntidade, Object>();
	
	@Test
	public void voParaEntidadeTest() throws BancoobException {
		conversor.voParaEntidade(new Parcelamento(), new Object());
	}
	
	@Test
	public void entidadeParaVOTest() throws BancoobException {
		conversor.entidadeParaVO(new Object(), new Parcelamento());
	}
	
}
