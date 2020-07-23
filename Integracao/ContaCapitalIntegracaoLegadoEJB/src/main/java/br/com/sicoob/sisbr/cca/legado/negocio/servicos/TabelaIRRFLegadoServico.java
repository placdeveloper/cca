package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.TabelaIRRFLegadoDTO;

/**
 * A Interface TabelaIRRFLegadoServico.
 */
public interface TabelaIRRFLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	
	/**
	 * Obter tabela IRRF por ano base
	 * @param anoBase
	 * @return List
	 * @throws BancoobException
	 */
	List<TabelaIRRFLegadoDTO> consultarPorAnoBase(Integer anoBase) throws BancoobException;
	
	/**
	 * Realiza inclusao da tabela progressiva de IRRF
	 */
	void incluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException;
	
	/**
	 * Realiza exclusao da tabela progressiva de IRRF
	 */
	void excluir(List<TabelaIRRFLegadoDTO> lstTabelaIRRF) throws BancoobException;
}