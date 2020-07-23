/*
 * 
 */
package br.com.sicoob.cca.replicacao.negocio.servicos;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.dto.BatimentoSaldoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;

/**
 * Servico ReplicacaoContaCapitalServico
 */
public interface ReplicacaoContaCapitalServico extends ContaCapitalReplicacaoServico {

	/**
	 * 1 - Inclui a conta capital com os dados do legado
	 * 2 - Grava no legado a chave inserida no db2
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */
	Integer incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException;
	
	/**
	 * 1 - Inclui o parcelamento com os dados do legado
	 * 2 - Grava no legado a chave inserida no db2
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */	
	Long incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException;

	/**
	 * 1 - Inclui o lancamento com os dados do legado
	 * 2 - Grava no legado a chave inserida no db2
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */	
	Long incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException;	

	/**
	 * Altera a conta capital com os dados do legado
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */
	void alterarContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException;
	
	/**
	 * Altera o parcelamento com os dados do legado
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */	
	void alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException;
	
	/**
	 * Altera o lancamento com os dados do legado
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */		
	void alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException;	
	
	/**
	 * Exclui no db2 as tabelas que no legado estavam representadas apenas pela tabela ContaCapital. 	
	 * ContaCapital, DocumentoCapital, PropostaSubscricao, DebitoContaCapital
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */
	void excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException;
	
	/**
	 * Exclui o parcelamento, usa somente a ChaveDB2 para a ação
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */	
	void excluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException;
	
	/**
	 * Exclui o lançamento, usa somente a ChaveDB2 para a ação
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */		
	void excluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException;

	/**
	 * Prepara a cooperativa piloto.
	 * @param cooperativa
	 * @throws BancoobException
	 */
	void prepararCooperativaPiloto(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta as quantidades das tabelas DB2 e SQL da cooperativa.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	Map<String, Map<String, Long>> consultarCooperativaPiloto(Integer cooperativa) throws BancoobException;

	/**
	 * Gera o relatorio de batimento das bases SQL x DB2
	 * @param cooperativas
	 * @return
	 * @throws BancoobException
	 */
	Object gerarRelatorioBatimentoSQLxDB2(List<Integer> cooperativas) throws BancoobException;		
	
	/**
	 * Verifica se a cooperativa possui divergencias nos batimentos.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	boolean verificarCooperativaComDivergenciasBatimentos(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta os batimentos de saldos divergentes.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<BatimentoSaldoDTO> consultarBatimentoSaldos(Integer cooperativa) throws BancoobException;
	
	/**
	 * Replica informacoes de debito indeterminado.
	 * @param string 
	 * @param dtoContaCapital
	 */
	void replicarDebitoIndeterminado(ReplicacaoTabelaContaCapitalLegadoDTO obj, String codAcao) throws BancoobException;
	
}
