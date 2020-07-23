package br.com.sicoob.cca.movimentacao.negocio.servicos;

import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.dto.IntegralizacaoOutrosBancosDTO;

public interface IntegralizacaoOutrosBancosServico extends ContaCapitalMovimentacaoServico {

	/**
	 * Realiza integralização entre outros bancos.
	 * @param IntegralizacaoOutrosBancosDTO
	 * @throws BancoobException
	 */	
	void incluirIntegralizacao(IntegralizacaoOutrosBancosDTO dto) throws BancoobException;
	
	void IntegralizacaoOutrosBancosSWS(Integer numCoop) throws BancoobException;
	
}
