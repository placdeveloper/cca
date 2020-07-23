/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.GestaoEmpresarialLegadoDTO;

/**
 * A Interface GestaoEmpresarialLegadoDao.
 */
public interface GestaoEmpresarialLegadoDao {

	/**
	 * Extrato com dados brutos, agrupados por instituicao, pac e tipo pessoa (PJ e PF)
	 * @param idInstituicao
	 * @param dataInicio
	 * @return
	 * @throws BancoobException
	 */
	List<GestaoEmpresarialLegadoDTO> gerarExtratoDIRF(Integer idInstituicao, Date dataInicio) throws BancoobException;
	
	/**
	 * Verifica se ha novos lancamentos (DIRF) apartir da data
	 * @param numCoop
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	Boolean novosLancamentosDIRF(Integer numCoop, Date data) throws BancoobException;
}