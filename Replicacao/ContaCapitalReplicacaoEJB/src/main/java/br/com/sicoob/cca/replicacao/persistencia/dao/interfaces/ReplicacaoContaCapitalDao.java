/*
 * 
 */
package br.com.sicoob.cca.replicacao.persistencia.dao.interfaces;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoContaCapital;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoLancamento;
import br.com.sicoob.cca.replicacao.negocio.batimento.RegistroBatimentoParcelamento;
import br.com.sicoob.cca.replicacao.negocio.dto.BatimentoSaldoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;

/**
 * Dao ReplicacaoContaCapitalDao
 * @author Marcos.Balbi
 *
 */
public interface ReplicacaoContaCapitalDao {
	
	/**
	 * Insere a conta capital no db2 com os dados do sql
	 * @param obj
	 * @throws BancoobException
	 */
	Integer incluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException;
	
	/**
	 * Insere o parcelamento no db2 com os dados do sql
	 * @param obj
	 * @throws BancoobException
	 */	
	Long incluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException;
	
	/**
	 * Insere o lancamento no db2 com os dados do sql
	 * @param obj
	 * @throws BancoobException
	 */
	Long incluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException;
	
	/**
	 * Altera a conta capital no db2 com os dados do sql
	 * @param obj
	 * @throws BancoobException
	 */	
	void alterarContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException;
	
	/**
	 * Altera o parcelamento no db2 com os dados do sql
	 * @param obj
	 * @throws BancoobException
	 */		
	void alterarParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException ;
	
	/**
	 * Altera o lancamento no db2 com os dados do sql
	 * @param obj
	 * @throws BancoobException
	 */			
	void alterarLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException ;
	
	/**
	 * Exclui conta capital no db2 com a chave do sql	
	 * @param obj
	 * @throws BancoobException
	 */
	void excluirContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException;
	
	/**
	 * Exclui parcelamento no db2 com a chave do sql	
	 * @param obj
	 * @throws BancoobException
	 */	
	void excluirParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO obj)throws BancoobException;
	
	/**
	 * Exclui lancamento no db2 com a chave do sql	
	 * @param obj
	 * @throws BancoobException
	 */		
	void excluirLancamentoContaCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj)throws BancoobException;
	
	/**
	 * Exclui o documento conta capital no db2 com a chave do sql	
	 * @param obj
	 * @throws BancoobException
	 */
	void excluirDocumentoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException;
	
	/**
	 * Exclui a proposta de subscricao no db2 com a chave do sql	
	 * @param obj
	 * @throws BancoobException
	 */
	void excluirPropostaContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException;
	
	/**
	 * Exclui o débito conta capital no db2 com a chave do sql	
	 * @param obj
	 * @throws BancoobException
	 */
	void excluirDebitoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO obj)throws BancoobException;

	/**
	 * Prepara a cooperativa piloto.
	 * @param instituicao
	 * @throws BancoobException
	 */
	void prepararCooperativaPiloto(Integer instituicao) throws BancoobException;

	/**
	 * Consulta as propriedades para preparacao da piloto.
	 * @param instituicao
	 * @return
	 * @throws BancoobException
	 */
	Map<String, Long> consultarCooperativaPiloto(Integer instituicao) throws BancoobException;

	/**
	 * Consulta os batimentos de contacapital.
	 * @param idInstituicao 
	 * @return
	 * @throws BancoobException
	 */
	List<RegistroBatimentoContaCapital> consultarBatimentosContaCapital(Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta os batimentos de lancamento.
	 * @return
	 * @throws BancoobException
	 */
	List<RegistroBatimentoLancamento> consultarBatimentosLancamento(Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta os batimentos de parcelamento.
	 * @return
	 * @throws BancoobException
	 */
	List<RegistroBatimentoParcelamento> consultarBatimentosParcelamento(Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta as informações de saldo das contas.
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BatimentoSaldoDTO> consultarBatimentoSaldos(Integer idInstituicao) throws BancoobException;

	/**
	 * Replica informacoes de debito indeterminado.
	 * @param obj
	 * @param codAcao 
	 * @return
	 */
	void replicarDebitoIndeterminado(ReplicacaoTabelaContaCapitalLegadoDTO obj, String codAcao) throws BancoobException;
	
}
