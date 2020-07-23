/*
 * 
 */
package br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.BloqueioCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumOrigemBloqueioCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.persistencia.dao.ContaCapitalMovimentacaoCrudDaoIF;
// TODO: Auto-generated Javadoc

/**
 * A Interface BloqueioContaCapitalDao.
 *
 * @author Antonio.Genaro
 */
public interface BloqueioContaCapitalDao extends ContaCapitalMovimentacaoCrudDaoIF<BloqueioCapital> {

	/**
	 * Consultar valor bloqueado via transferencia capital.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param dataProduto o valor de data produto
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	BigDecimal consultarValorBloqueadoViaTransferenciaCapital(Integer idContaCapital, Date dataProduto) throws BancoobException;

	/**
	 * Consultar valor bloqueado.
	 *
	 * @param idContaCapital o valor de id conta capital
	 * @param dataProduto o valor de data produto
	 * @return BigDecimal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	BigDecimal consultarValorBloqueado(Integer idContaCapital, Date dataProduto) throws BancoobException;
	
	/**
	 * Consultar valor bloqueado por tipo.
	 * 
	 * @param idContaCapital
	 * @param dataProduto
	 * @param tipoBloqueio
	 * @return
	 * @throws BancoobException
	 */
	BigDecimal consultarValorBloqueadoPorTipo(Integer idContaCapital, Date dataProduto, EnumOrigemBloqueioCapital tipoBloqueio) throws BancoobException;
	
	/**
	 * Consulta os bloqueios de conta capital.
	 * 
	 * @param dto
	 * @param date 
	 * @return
	 * @throws BancoobException
	 */
	List<BloqueioContaCapitalDTO> consultarBloqueios(BloqueioContaCapitalDTO dto, Date dataProduto) throws BancoobException; 	
	
	
	void excluirBloqueioCapital(Integer idBloqueioCapital) throws BancoobException;
	
}
