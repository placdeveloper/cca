package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;

public interface FechGravacaoSaldoCapSocialIntegralizadoLegadoDao {

	void realizarCarga(Integer numCoop, Date date) throws BancoobException;

}