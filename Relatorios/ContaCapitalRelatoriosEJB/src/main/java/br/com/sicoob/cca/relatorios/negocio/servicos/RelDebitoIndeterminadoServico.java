/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDebitoIndeterminadoRenDTO;

/**
 * Relatorio de debito indeterminado 
 * @author marco.nascimento
 */
public interface RelDebitoIndeterminadoServico extends ContaCapitalRelatoriosServico {

	/**
	 * Relatorio de debito indeterminado
	 * @param dto
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioDebitoIndeterminado(List<RelDebitoIndeterminadoRenDTO> lstDTOEntrada, String filtro) throws BancoobException;
}