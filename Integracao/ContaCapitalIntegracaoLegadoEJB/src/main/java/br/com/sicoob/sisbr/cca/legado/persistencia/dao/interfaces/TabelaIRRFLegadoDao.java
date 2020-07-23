package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TabelaIRRFLegadoDTO;

/**
 * A Interface TabelaIRRFLegadoDao.
 */
public interface TabelaIRRFLegadoDao {
	
	/**
	 * Retorna tabela de imposto do ano em questao
	 * @param anoBase
	 * @return List
	 * @throws BancoobException
	 */
	List<TabelaIRRFLegadoDTO> consultarPorAnoBase(Integer anoBase) throws BancoobException;
	
	/**
	 * Realiza inclusao de tabela progressiva de IRRF
	 * @param lstTabelaIRRF
	 * @throws BancoobException
	 */
	void incluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException;
	
	/**
	 * Realiza exclusao de tabela progressiva de IRRF
	 * @param lstTabelaIRRF
	 * @throws BancoobException
	 */
	void excluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException;
}