/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import br.com.bancoob.negocio.servicos.BancoobCrudServico;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalIntegracaoLegadoEntidade;

/**
 * Interface que define um contrato padrao para as operacoes de CRUD de todo o
 * sistema ContaCapitalIntegracaoLegado
 * 
 */
public interface ContaCapitalIntegracaoLegadoCrudServico<T extends ContaCapitalIntegracaoLegadoEntidade>
		extends ContaCapitalIntegracaoLegadoServico, BancoobCrudServico<T> {

}
