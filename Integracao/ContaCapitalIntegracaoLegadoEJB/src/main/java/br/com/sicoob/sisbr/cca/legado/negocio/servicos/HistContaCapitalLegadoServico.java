/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.HistContaCapitalLegado;

/**
 * A Interface HistContaCapitalLegadoServico.
 */
public interface HistContaCapitalLegadoServico extends ContaCapitalIntegracaoLegadoCrudServico<HistContaCapitalLegado> {

	/**
	 * Quantidade de vezes que a conta capital foi inativada 
	 * @param numMatricula
	 * @return
	 * @throws BancoobException
	 */
	Integer qtdInativacaoCCA(Integer numMatricula) throws BancoobException;
}