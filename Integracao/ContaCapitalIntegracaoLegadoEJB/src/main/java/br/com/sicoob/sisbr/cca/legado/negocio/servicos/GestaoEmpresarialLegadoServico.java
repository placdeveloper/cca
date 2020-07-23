/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;


/**
 * A Interface GestaoEmpresarialLegadoServico.
 */
public interface GestaoEmpresarialLegadoServico extends ContaCapitalIntegracaoLegadoServico {

	/**
	 * Gera extrato DIRF por cooperativa
	 * @param numCoop
	 * @param dataInicio
	 * @return 
	 * @throws BancoobException
	 */
	List<GestaoEmpresarialLegadoDTO> gerarExtratoDIRF(Integer numCoop, Date dataInicio) throws BancoobException;
	
	/**
	 * Verifica se ha novos lancamentos de DIRF apartir da data
	 * @param numCoop
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	Boolean novosLancamentosDIRF(Integer numCoop, Date data) throws BancoobException;
}