/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author marco.nascimento
 */
public interface ValorCotaServico extends ContaCapitalCadastroServico {
	
	/**
	 * Obtem valor minimo de subscricao.
	 * <p>Caso idPessoa = null, retorna valor minimo de subscricao para pessoa fisica</p>
	 * @param idInstituicao
	 * @param idPessoa
	 * @return valor minimo de subscricao da cooperativa
	 */
	BigDecimal obterValorMinimoSubscricao(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Obtem valor minimo de integralizacao.
	 * @param idInstituicao
	 * @return valor minimo de integralizacao da cooperativa
	 */
	BigDecimal obterValorMinimoIntegralizacao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem quantidade maxima de parcelas da subscricao
	 * @param idInstituicao
	 * @return quantidade maxima de parcelas em que a subscricao pode ser divida 
	 */
	Integer obterQtdMaxParcela(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem valor cota
	 * @param idInstituicao
	 * @return valor cota da cooperativa 
	 */
	BigDecimal obterValorCota(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem quantidade minima de cotas para ingresso na cooperativa
	 * @param idInstituicao
	 * @param idPessoa
	 * @return 
	 * @throws BancoobException
	 */
	Integer obterQtdMinCota(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Obtem quantidade minima de cotas para ingresso na cooperativa para pessoa fisica
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterQtdMinCotaPF(Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtem quantidade minima de cotas para ingresso na cooperativa para pessoa juridica
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterQtdMinCotaPJ(Integer idInstituicao) throws BancoobException;
}