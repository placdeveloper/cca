package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ParticipacaoIndiretaBancoobLegadoDTO;

/**
 * A Interface InformacaoAcumuladaLegadoServico.
 */
public interface InformacaoAcumuladaLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Calcula a participacao indireta das singulares no Bancoob, utilizando data base de dois meses atras.
	 * @param cooperativasSingulares 
	 * @param qtdMesesDataBase 
	 * @return
	 * @throws BancoobException
	 */
	List<ParticipacaoIndiretaBancoobLegadoDTO> calcularParticipacaoIndiretaBancoob(Integer numCoopCentral, List<Integer> cooperativasSingulares, int qtdMesesDataBase) throws BancoobException;
	
	/**
	 * Consulta snaphshot das cooperativas
	 * @param numCoopCentral
	 * @param dataSnapshot 
	 * @return
	 * @throws BancoobException
	 */
	List<ParticipacaoIndiretaBancoobLegadoDTO> consultarSnapshotCooperativas(Integer numCoopCentral, DateTime dataSnapshot) throws BancoobException;
	
}