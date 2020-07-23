/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;

/**
 * @author Marco.Nascimento
 */
public interface RelValorParametroServico extends ContaCapitalRelatoriosServico {

	/**
	 * Gera historico de valor parametro
	 * @param idsInstituicao
	 * @param idParametro
	 * @return
	 * @throws BancoobException
	 */
	Object gerarHistorico(List<Integer> idsInstituicao, Integer idParametro) throws BancoobException;
}