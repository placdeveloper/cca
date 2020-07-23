/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelExtratoRelatorioDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSolDevolucaoCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelTransferenciaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.ContaCapitalRelatoriosServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelContaCapitalServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * Business delegate a ser usado pelo Sistema ContaCapitalCadastro.
 * 
 * @author Stefanini IT Solutions
 * @param <T>
 */
public class RelContaCapitalDelegate extends ContaCapitalRelatoriosDelegate<RelContaCapitalServico> {

	/**
	 * @return RelContaCapitalDelegate
	 */
	public static RelContaCapitalDelegate getInstance() {
		return new RelContaCapitalDelegate();
	}
	
	/**
	 * @return RelContaCapitalServico
	 */
	@Override
	protected RelContaCapitalServico localizarServico() {
		return (RelContaCapitalServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelContaCapitalServico();
	}
	
	/**
	 * {@link ContaCapitalRelatoriosServico#gerarFichaMatricula(Long)}
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarFichaMatricula(Long numMatricula) throws BancoobException {
		return getServico().gerarFichaMatricula(numMatricula);
	}
	
	/**
	 * {@link RelContaCapitalServico#gerarFichaAdmissao(Integer)}
	 * @param idPessoa
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarFichaAdmissao(Integer idPessoa) throws BancoobException {
		return getServico().gerarFichaAdmissao(idPessoa);
	}
	
	/**
	 * {@link RelContaCapitalServico#gerarExtrato(RelExtratoRelatorioDTO)
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarExtrato(RelExtratoRelatorioDTO relExtratoDTO) throws BancoobException {
		return getServico().gerarExtrato(relExtratoDTO);
	}
	
	/**
	 * {@link RelContaCapitalServico#gerarExtratoHTML(RelExtratoRelatorioDTO)
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	public String gerarExtratoHTML(RelExtratoRelatorioDTO relExtratoDTO) throws BancoobException {
		return getServico().gerarExtratoHTML(relExtratoDTO);
	}
	
	/**
	 * {@link RelContaCapitalServico#gerarSolDevolucaoCapital(relSolDevolucaoCapitalDTO)
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarSolDevolucaoCapital(RelSolDevolucaoCapitalDTO relSolDevolucaoCapitalDTO) throws BancoobException {
		return getServico().gerarSolDevolucaoCapital(relSolDevolucaoCapitalDTO);
	}

	/**
	 * {@link RelContaCapitalServico#gerarReciboTransferenciaCapital(relTransferenciaCapitalDTO)
	 * @param relExtratoDTO
	 * @return
	 * @throws BancoobException
	 */
	public Object gerarReciboTransferenciaCapital(RelTransferenciaCapitalDTO relTransferenciaCapitalDTO) throws BancoobException {
		return getServico().gerarReciboTransferenciaCapital(relTransferenciaCapitalDTO);
	}
	
	
	
	
}