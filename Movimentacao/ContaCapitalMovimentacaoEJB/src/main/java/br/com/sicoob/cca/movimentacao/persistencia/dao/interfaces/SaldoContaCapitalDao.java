package br.com.sicoob.cca.movimentacao.persistencia.dao.interfaces;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;

public interface SaldoContaCapitalDao {

	void incluirCarga(Integer idInstituicao, Date dataAnteriorProduto) throws BancoobException;

	Boolean validarDataSaldoSeJaPossuiCarga(Integer idInstituicao, Date dataAnteriorProduto) throws BancoobException;
}
