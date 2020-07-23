/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BatimentoSaldoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConsultaMonitoracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ExpurgoReplicacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorCooperativaReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoContaCapitalDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoLancamentoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoParcelamentoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;

/**
 * Servico ReplicacaoContaCapitalLegadoServico
 */
public interface ReplicacaoContaCapitalLegadoServico extends ContaCapitalIntegracaoLegadoServico {

	/**
	 * Consulta os valores para serem replicados do BDSicoobIntegracao
	 * Realiza validações dos parametros de replicacao
	 * @throws BancoobException
	 * @author Marcos.Balbi
	 */
	List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaReplicacao() throws BancoobException;

	/**
	 * Consulta na tabela ContaCapital da cooperativa a linha obtida na Replicacao
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	ReplicacaoTabelaContaCapitalLegadoDTO consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException;
	
	/**
	 * Consulta na tabela ParcelamentoCCA da cooperativa a linha obtida na Replicacao
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	ReplicacaoTabelaParcelamentoCCALegadoDTO consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException;
	
	/**
	 * Consulta na tabela LancamentosCCapital da cooperativa a linha obtida na Replicacao
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	ReplicacaoTabelaLancamentosCCapitalLegadoDTO consultarTabelaLancamentosCCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException;	

	/**
	 * Atualiza os valores da tabela de replicação do BDSicoobIntegracao
	 * @author Marcos.Balbi
	 * @param obj
	 * @throws BancoobException
	 */
	void alterarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException;
	
	/**
	 * Atualiza a chave do db2 na tabela do sql, tabela ContaCapital
	 * @param ReplicacaoTabelaContaCapitalLegadoDTO
	 * @throws BancoobException
	 */
	void alterarTabelaContaCapitalReplicacao(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException;
	
		/**
	 * Atualiza a chave do db2 na tabela do sql, tabela ParcelamentoCCA
	 * @param ReplicacaoTabelaParcelamentoCCALegadoDTO
	 * @throws BancoobException
	 */	
	void alterarTabelaParcelamentoCCAReplicacao(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException;
	
	/**
	 * Atualiza a chave do db2 na tabela do sql, tabela LancamentosCCapital
	 * @param ReplicacaoTabelaLancamentosCCapitalLegadoDTO
	 * @throws BancoobException
	 */	
	void alterarTabelaLancamentosCCapitalReplicacao(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException;		
	
	/**
	 * Consulta os totais da tabela de replicacao para monitoracao
	 * @return
	 * @throws BancoobException
	 */
	List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaMonitoracaoReplicacao()throws BancoobException;
	
	/**
	 * Consulta se a execução está liberada ou bloqueada
	 * 0 - Liberada
	 * 1 - Bloqueada
	 * @return
	 * @throws BancoobException
	 */
	Boolean consultarExecucaoLiberada() throws BancoobException;

	/**
	 * Liberar ou Bloquear a execução 
	 * 0 - Liberada
	 * 1 - Bloqueada
	 * @param operacao
	 * @throws BancoobException
	 */
	void liberarBloquearExecucao(Integer operacao) throws BancoobException;	
	
	/**
	 * Consulta a lista completa das configurações de replicação	
	 * @return
	 * @throws BancoobException
	 */
	List<ReplicacaoConfiguracaoLegadoDTO> consultarListaConfiguracaoReplicacao() throws BancoobException;
	
	/**
	 * Alterar uma configuração para a replicacao da conta capital
	 * @param obj
	 * @throws BancoobException
	 */
	void alterarConfiguracaoReplicacaoCCA(ReplicacaoConfiguracaoLegadoDTO obj) throws BancoobException; 
	
	/**
	 * Consulta relativa a monitoracao de replicacao (conta capital)
	 * @param ConsultaMonitoracaoDTO
	 * @return
	 * @throws BancoobException
	 */
	List<MonitorReplicacaoCapitalLegadoDTO> consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException;
	
	/**
	 * Consulta relativa a monitoracao de replicacao (conta capital)
	 * @param ConsultaMonitoracaoDTO
	 * @return
	 * @throws BancoobException
	 */
	List<MonitorCooperativaReplicacaoCapitalLegadoDTO> consultarMonitoracaoCooperativasReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException;
	
	/**
	 * Realiza consulta de erros da replicacao de conta capital
	 * @param ConsultaMonitoracaoDTO
	 * @return
	 * @throws BancoobException
	 */
	List<ReplicacaoContaCapitalLegadoDTO> consultarErrosReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException;
	
	/**
	 * Marca registro de replicacao conta capital para reprocessamento
	 * @param lst
	 * @param justificativa
	 * @param idUsuario
	 * @throws BancoobException
	 */
	void reprocessar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException;
	
	/**
	 * Marca registro de replicacao conta capital como invalido (nao se aplica a ser reprocessado)
	 * @param lst
	 * @param justificativa
	 * @param idUsuario
	 * @throws BancoobException
	 */
	void invalidar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException;
	
	/**
	 * Expurga replicacao de conta capital replicados com sucesso
	 * @throws BancoobException
	 */
	
	/**
	 * Expurga os registros de replicacao ja realizados.
	 * @throws BancoobException
	 */
	void expurgarReplicacaoSucesso() throws BancoobException;

	/**
	 * Prepara a cooperativa piloto.
	 * @param cooperativa
	 * @throws BancoobException
	 */
	void prepararCooperativaPiloto(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta as propriedades para preparacao de piloto.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	Map<String, Long> consultarCooperativaPiloto(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta os batimentos de contacapital.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<RegistroBatimentoContaCapitalDTO> consultarBatimentosContaCapital(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta os batimentos de lancamentos.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<RegistroBatimentoLancamentoDTO> consultarBatimentosLancamento(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta os batimentos de parcelamento.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<RegistroBatimentoParcelamentoDTO> consultarBatimentosParcelamento(Integer cooperativa) throws BancoobException;
	
	/**
	 * Executa as consultas de conciliacao da cooperativa.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	Map<String, Object> executarConciliacao(Integer cooperativa) throws BancoobException;
	
	/**
	 * Consulta as cooperativas para geracao dos scripts de conciliacao.
	 * @return
	 * @throws BancoobException
	 */
	List<Integer> consultarCooperativasConciliacao() throws BancoobException;
	
	/**
	 * Consulta as cooperativas para expurgo.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<ExpurgoReplicacaoDTO> consultarExpurgo(Integer cooperativa) throws BancoobException;

	/**
	 * Expurga os registros de replicacao para a cooperativa.
	 * @param cooperativa
	 * @throws BancoobException
	 */
	void expurgarCooperativaReplicacao(Integer cooperativa) throws BancoobException;

	/**
	 * Altera o JSON de um registro de replicacao.
	 * @param idReplicacaoCCA
	 * @param json
	 */
	void alterarJSONReplicacao(Integer idReplicacaoCCA, String json) throws BancoobException;

	/**
	 * Consulta replicacao por ids.
	 * @param idInicial
	 * @param idFinal
	 * @return
	 * @throws BancoobException
	 */
	List<ReplicacaoContaCapitalLegadoDTO> consultarReplicacaoPorIds(Long idInicial, Long idFinal) throws BancoobException;
	
	/**
	 * Consulta as cooperativas piloto.
	 * @return
	 * @throws BancoobException
	 */
	List<Integer> consultarCooperativasPiloto() throws BancoobException;
	
	/**
	 * Consulta os saldos para batimento do legado.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<BatimentoSaldoLegadoDTO> consultarBatimentoSaldosLegado(Integer cooperativa) throws BancoobException;

	/**
	 * valida suspenção para expurgo operação.
	 * @throws BancoobException
	 */
	Boolean validarSuspensaoExpurgoOperacao() throws BancoobException;	
	
	/**
	 * Consulta data de corte para expurgo operação.
	 * @throws BancoobException
	 */
	Date consultarDataExpurgoOperacao() throws BancoobException;
	
}