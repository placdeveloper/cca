/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.delegates;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.ValorCotaServico;
import br.com.sicoob.cca.cadastro.negocio.servicos.locator.ContaCapitalCadastroServiceLocator;

/**
 * @author Marco.Nascimento
 */
public class ValorCotaDelegate extends ContaCapitalCadastroDelegate<ValorCotaServico> {

	/**
	 * Construtor
	 */
	ValorCotaDelegate() {
		
	}

	/**
	 * Locator ValorCotaServico
	 */
	@Override
	protected ValorCotaServico localizarServico() {
		return (ValorCotaServico) ContaCapitalCadastroServiceLocator.getInstance().localizarValorCotaServico();
	}
	
	/**
	 * {@link ValorCotaServico#obterValorMinimoSubscricao(Integer, Integer)}
	 */
	public BigDecimal obterValorMinimoSubscricao(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		return getServico().obterValorMinimoSubscricao(idInstituicao, idPessoa);
	}
	
	/**
	 * {@link ValorCotaServico#obterValorMinimoIntegralizacao(Integer)}
	 */
	public BigDecimal obterValorMinimoIntegralizacao(Integer idInstituicao) throws BancoobException {
		return getServico().obterValorMinimoIntegralizacao(idInstituicao);
	}
	
	/**
	 * {@link ValorCotaServico#obterQtdMaxParcela(Integer)}
	 */
	public Integer obterQtdMaxParcela(Integer idInstituicao) throws BancoobException {
		return getServico().obterQtdMaxParcela(idInstituicao);
	}
	
	/**
	 * {@link ValorCotaServico#obterQtdMinCota(Integer, Integer)}
	 */
	public Integer obterQtdMinCota(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		return getServico().obterQtdMinCota(idInstituicao, idPessoa);
	}
	
	/**
	 * {@link ValorCotaServico#obterValorCota(Integer, Integer)}
	 */
	public BigDecimal obterValorCota(Integer idInstituicao) throws BancoobException {
		return getServico().obterValorCota(idInstituicao);
	}
}