/*
 * 
 */
package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;

// TODO: Auto-generated Javadoc
/**
 * A Interface BloqueioContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
public interface BloqueioContaCapitalServico extends ContaCapitalMovimentacaoCrudServico<BloqueioCapital> {
	
	/**
	 * Retorna o soma do valor bloqueado via transferencia de capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	BigDecimal consultarValorBloqueadoViaTransferenciaCapital(Integer idContaCapital) throws BancoobException;

	/**
	 * Consulta os bloqueios de conta capital.
	 * 
	 * @param dto
	 * @return
	 */
	List<BloqueioContaCapitalDTO> consultarBloqueios(BloqueioContaCapitalDTO dto) throws BancoobException;

	/**
	 * Calcula o valor bloqueado da conta capital considerando parametro de configuracao.
	 * @param idContaCapital
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal calcularValorBloqueado(Integer idContaCapital) throws BancoobException;
	
	/**
	 * Inclui um BloqueioCapital
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	BloqueioCapital incluir(BloqueioContaCapitalDTO dto) throws BancoobException;

	/**
	 * Desbloqueia capital.
	 * @param dto
	 */
	void desbloquear(BloqueioContaCapitalDTO dto) throws BancoobException;
	
	/**
	 * Exclui Bloqueio de capital.
	 * @param dto
	 */
	void excluirBloqueioCapital(Integer IdBloqueioCapital) throws BancoobException;
	
	

}
