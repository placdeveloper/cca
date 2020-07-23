/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.conversor;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.LancamentoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoContaCapitalVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoLancamentoContaCapitalVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoParcelamentoContaCapitalVO;

/**
 * Conversor ConversorMonitoracaoContaCapital
 */
public class ConversorMonitoracaoContaCapital {

	/**
	 * Converter Entidade ContaCapital para MonitoracaoContaCapitalVO
	 * @param entidade
	 * @return
	 * @throws BancoobException
	 */
	public MonitoracaoContaCapitalVO converterCapitalEntidadeparaVO(ContaCapital entidade)
			throws BancoobException{
		MonitoracaoContaCapitalVO vo  = new MonitoracaoContaCapitalVO();
		vo.setDataMatricula(entidade.getDataMatricula());
		vo.setDataSaida(entidade.getDataSaida());
		vo.setIdContaCapital(entidade.getId());
		vo.setIdInstituicao(entidade.getIdInstituicao());
		vo.setIdPessoa(entidade.getIdPessoa());
		vo.setIdUsuario(entidade.getIdUsuario());
		vo.setNumMatricula(entidade.getNumContaCapital());
		vo.setValorBloq(entidade.getValorBloq());
		vo.setValorDevol(entidade.getValorDevol());
		vo.setValorInteg(entidade.getValorInteg());
		vo.setValorSubs(entidade.getValorSubs());
		return vo;    
	}
	
	/**
	 * Converter Entidade ParcelamentoContaCapital para MonitoracaoParcelamentoContaCapitalVO
	 * @param entidade
	 * @return
	 * @throws BancoobException
	 */
	public MonitoracaoParcelamentoContaCapitalVO converterParcelamentoCapitalEntidadeparaVO(Parcelamento entidade)throws BancoobException{
		return new MonitoracaoParcelamentoContaCapitalVO();
	}	
	
	/**
	 * Converter Entidade LancamentoContaCapital para MonitoracaoLancamentoContaCapitalVO
	 * @param entidade
	 * @return
	 * @throws BancoobException
	 */
	public MonitoracaoLancamentoContaCapitalVO converterLancamentoCapitalEntidadeparaVO(LancamentoContaCapital entidade)throws BancoobException{
		return new MonitoracaoLancamentoContaCapitalVO();
	}	
	
}
