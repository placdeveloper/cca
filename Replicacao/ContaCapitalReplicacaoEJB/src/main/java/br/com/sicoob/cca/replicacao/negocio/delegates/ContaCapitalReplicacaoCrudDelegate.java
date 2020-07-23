/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.sicoob.cca.replicacao.negocio.servicos.ContaCapitalReplicacaoCrudServico;

/**
 * @author Balbi
 */
public abstract class ContaCapitalReplicacaoCrudDelegate<T extends BancoobEntidade, S extends ContaCapitalReplicacaoCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

}