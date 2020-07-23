/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.delegates;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.BloqueioContaCapitalServico;
import br.com.sicoob.cca.movimentacao.negocio.servicos.locator.ContaCapitalMovimentacaoServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * A Classe BloqueioContaCapitalDelegate.
 *
 * @author Antonio.Genaro
 */
public class BloqueioContaCapitalDelegate extends ContaCapitalMovimentacaoCrudDelegate<BloqueioCapital, BloqueioContaCapitalServico> {

	/**
	 * Instancia um novo BloqueioContaCapitalDelegate.
	 */
	BloqueioContaCapitalDelegate() {
		
	}

	/**
	 * Locator BloqueioContaCapitalDelegate.
	 *
	 * @return BloqueioContaCapitalServico
	 */
	@Override
	protected BloqueioContaCapitalServico localizarServico() {
		return (BloqueioContaCapitalServico) ContaCapitalMovimentacaoServiceLocator.getInstance().localizarBloqueioContaCapitalServico();
	}	
	
	/**
	 * Consultar valor bloqueado via transferencia capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public BigDecimal consultarValorBloqueadoViaTransferenciaCapital(Integer idContaCapital) throws BancoobException {
		return getServico().consultarValorBloqueadoViaTransferenciaCapital(idContaCapital);
	}
	
	/**
	 * Consulta lista de bloqueios para determinada instituicao, sendo idInstituicao unico parametro obrigatorio
	 * no parametro DTO. 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public List<BloqueioContaCapitalDTO> consultarBloqueios(BloqueioContaCapitalDTO dto) throws BancoobException {
		return getServico().consultarBloqueios(dto);
	}
	
	/**
	 * Calcula o valor bloqueado na conta capital considerando parametro de configuracao.
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	public BigDecimal calcularValorBloqueado(Integer idContaCapital) throws BancoobException {
		return getServico().calcularValorBloqueado(idContaCapital);
	}

	/**
	 * Realiza a inclusao de um bloqueio fazendo as devidas validacoes de negocio.
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public BloqueioCapital incluir(BloqueioContaCapitalDTO dto) throws BancoobException {
		return getServico().incluir(dto);
	}

	/**
	 * Realiza o desbloqueio fazendo as devidas validacoes de negocio.
	 * @param dto
	 * @throws BancoobException
	 */
	public void desbloquear(BloqueioContaCapitalDTO dto) throws BancoobException {
		getServico().desbloquear(dto);
	}
	
	/**
	 * Realiza o exclusão do bloqueio de capital.
	 * @param Integer
	 * @throws BancoobException
	 */	
	public void excluirBloqueioCapital(Integer idBloqueioCapital) throws BancoobException {
		getServico().excluirBloqueioCapital(idBloqueioCapital);
	}
		
}
