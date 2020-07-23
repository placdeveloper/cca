package br.com.sicoob.cca.comum.persistencia.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumCrudDaoIF;
import br.com.sicoob.cca.entidades.negocio.entidades.OperacaoContaCapital;

/**
 * OperacaoContaCapitalDao
 * @author Nairon.Silva
 */
public interface OperacaoContaCapitalDao extends ContaCapitalComumCrudDaoIF<OperacaoContaCapital> {
	
	void expurgarOperacao(Date dataExpurgoOperacao) throws BancoobException;

	void incluirOperacaoContaCapitalLote(List<OperacaoContaCapital> listOperacaoContaCapital) throws BancoobException;

}
