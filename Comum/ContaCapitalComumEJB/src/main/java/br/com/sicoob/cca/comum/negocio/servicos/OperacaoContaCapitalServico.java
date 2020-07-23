package br.com.sicoob.cca.comum.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.entidades.negocio.entidades.OperacaoContaCapital;

/**
 * OperacaoContaCapitalServico
 * @author Nairon.Silva
 */
public interface OperacaoContaCapitalServico extends ContaCapitalComumCrudServico<OperacaoContaCapital> {

	/**
	 * Expurga os registros de opecacao.
	 * @throws BancoobException
	 */
	void expurgarOperacao(Date dataExpurgoOperacao) throws BancoobException;
	
	/**
	 * Incluir opecacao em lote.
	 * @throws BancoobException
	 */
	void incluirOperacaoContaCapitalLote(List<OperacaoContaCapital> listOperacaoContaCapital) throws BancoobException;
	
}
