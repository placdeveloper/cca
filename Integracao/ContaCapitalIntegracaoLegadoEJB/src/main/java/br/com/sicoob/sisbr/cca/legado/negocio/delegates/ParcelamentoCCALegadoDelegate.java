/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ParcelamentoCCALegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ParcelamentoCCALegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * A Classe ParcelamentoCCALegadoDelegate.
 */
public class ParcelamentoCCALegadoDelegate extends
		ContaCapitalIntegracaoLegadoCrudDelegate<ParcelamentoCCALegado, ParcelamentoCCALegadoServico> {

	/**
	 * Recupera a unica instancia de ParcelamentoCCALegadoDelegate.
	 *
	 * @return uma instancia de ParcelamentoCCALegadoDelegate
	 */
	public static ParcelamentoCCALegadoDelegate getInstance(){
		return new ParcelamentoCCALegadoDelegate();
	}
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ParcelamentoCCALegadoServico localizarServico() {
		return (ParcelamentoCCALegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarParcelamentoCCALegadoServico();
	}
	
	/**
	 * Obter proximo num parcelamento.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer obterProximoNumParcelamento(Integer numMatricula, Integer codTipoParcelamento) throws BancoobException{
		return getServico().obterProximoNumParcelamento(numMatricula, codTipoParcelamento);
	}

	/**
	 * Verificar parcelamento aberto.
	 *
	 * @param numMatricula o valor de num matricula
	 * @param codTipoParcelamento o valor de cod tipo parcelamento
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer verificarParcelamentoAberto(Integer numMatricula, Integer codTipoParcelamento) throws BancoobException{
		return getServico().verificarParcelamentoAberto(numMatricula, codTipoParcelamento);
	}
	
	/**
	 * Obtem as parcelas abertas pelos canais de atendimento
	 * 
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	public List<ParcelamentoCCALegado> obterParcelasEmAbertoPelosCanais(Integer numeroCooperativa, Integer numMatricula) throws BancoobException {
		return getServico().obterParcelasEmAbertoPelosCanais(numeroCooperativa, numMatricula);
	} 
	
	/**
	 * Verifica se ja existem parcelas em aberto pelos canais de atendimento.
	 * 
	 * @param numeroCooperativa
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	public Boolean verificarParcelasEmAbertoPelosCanais(Integer numeroCooperativa, Integer numMatricula) throws BancoobException {
		return getServico().verificarParcelasEmAbertoPelosCanais(numeroCooperativa, numMatricula);
	}
	
	/**
	 * @param numMatricula
	 * @throws BancoobException
	 */
	public void excluirParcelasDevolucaoAbertoViaCaixa(Integer numMatricula) throws BancoobException {
		getServico().excluirParcelasDevolucaoAbertoViaCaixa(numMatricula);
	}
	
}
