/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParticipacaoIndiretaBancoobLegadoDTO;

/**
 * Obtem informações consolidadas sobre um determinado produto 
 * @author Marco.Nascimento
 * @since 03/06/2014
 */
public interface InformacaoAcumuladaLegadoDao {
	
	/**
	 * Saldo capital de cada Singular nas Centrais
	 * @param cooperativasSingulares 
	 * @return
	 * @throws BancoobException
	 */
	List<ParticipacaoIndiretaBancoobLegadoDTO> obterSaldoCapitalSingulares(DateTime dataParametro, Integer numCoop, List<Integer> cooperativasSingulares) throws BancoobException;

	/**
	 * Consulta uma snapshot das cooperativas singulares pela central
	 * @param numCoopCentral
	 * @param dataSnapshot 
	 * @return
	 */
	List<ParticipacaoIndiretaBancoobLegadoDTO> consultarSnapshotCooperativas(Integer numCoopCentral, DateTime dataSnapshot);

}