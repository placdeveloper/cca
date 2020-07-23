package br.com.sicoob.cca.movimentacao.conversor;

import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.sisbr.cca.movimentacao.conversor.ConversorMovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO;

public class ConversorMovimentacaoContaCapitalTest{

	private ConversorMovimentacaoContaCapital<Parcelamento, ParcelamentoRenVO> conversor = 
			new ConversorMovimentacaoContaCapital<Parcelamento, ParcelamentoRenVO>();
	
	@Test
	public void voParaEntidadeTest() throws BancoobException {
		conversor.voParaEntidade(new Parcelamento(), new ParcelamentoRenVO());
	}
	
	@Test
	public void entidadeParaVOTest() throws BancoobException {
		conversor.entidadeParaVO(new ParcelamentoRenVO(), new Parcelamento());
	}
	
}
