/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.GestaoEmpresarialDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.GestaoEmpresarialServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe GestaoEmpresarialDelegate.
 *
 * @author marco.nascimento
 */
public class GestaoEmpresarialDelegate extends ContaCapitalMovimentacaoDelegate<GestaoEmpresarialServico>{

	/**
	 * Instancia um novo GestaoEmpresarialDelegate.
	 */
	GestaoEmpresarialDelegate() {
		
	}

	/**
	 * Locator DesligarContaCapitalDelegate.
	 *
	 * @return GestaoEmpresarialServico
	 */
	@Override
	protected GestaoEmpresarialServico localizarServico() {
		return (GestaoEmpresarialServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarGestaoEmpresarialServico();
	}
	
	/**
	 * {@link GestaoEmpresarialServico#iniciarProcessamento(Integer)}.
	 *
	 * @param numCoop o valor de num coop
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean iniciarProcessamento(Integer numCoop) throws BancoobException {
		return getServico().iniciarProcessamento(numCoop);
	}
	
	/**
	 * {@link GestaoEmpresarialServico#isPrimeiraCarga(Integer)}.
	 *
	 * @param numCoop o valor de num coop
	 * @return {@code true}, se for primeira carga
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean isPrimeiraCarga(Integer numCoop) throws BancoobException {
		return getServico().isPrimeiraCarga(numCoop);
	}
	
	/**
	 * {@link GestaoEmpresarialServico#realizarCarga(Integer)}.
	 *
	 * @param numCoop o valor de num coop
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void realizarCarga(Integer numCoop) throws BancoobException {
		getServico().realizarCarga(numCoop);
	}
	
	/**
	 * {@link GestaoEmpresarialServico#gerarExtratoDIRF(Integer, Date, Date)}.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param dataInicio o valor de data inicio
	 * @param dataFim o valor de data fim
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<GestaoEmpresarialDTO> gerarExtratoDIRF(List<Integer> idInstituicao, Date dataInicio, Date dataFim) throws BancoobException {
		return getServico().gerarExtratoDIRF(idInstituicao, dataInicio, dataFim);
	}
}