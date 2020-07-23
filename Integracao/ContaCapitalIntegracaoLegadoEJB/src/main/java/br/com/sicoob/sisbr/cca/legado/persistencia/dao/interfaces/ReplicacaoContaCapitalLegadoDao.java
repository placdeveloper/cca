package br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.BatimentoSaldoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConfiguracaoConciliacaoDTO;
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
 * DAO ReplicacaoContaCapitalLegadoDao
 */
public interface ReplicacaoContaCapitalLegadoDao {

	/**
	 * Consulta lista de parametros para replicacao
	 * @throws BancoobException
	 * @author Marcos.Balbi
	 */	
	List<ReplicacaoConfiguracaoLegadoDTO> consultarListaConfiguracaoReplicacao() throws BancoobException;		
	
	/**
	 * Consulta os parametros para replicacao
	 * @throws BancoobException
	 * @author Marcos.Balbi
	 */	
	String consultarConfiguracaoReplicacao(Integer obj) throws BancoobException;	

	
	/**
	 * Consulta os valores para serem replicados do BDSicoobIntegracao
	 * Força uma quantidade especifica de registros e de cooperativas
	 * @throws BancoobException
	 * @author Marcos.Balbi
	 */	
	List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaReplicacao(Integer quantidade,List<String> cooperativas) throws BancoobException;		
	
	/**
	 * Consulta os valores para serem replicados do BDSicoobIntegracao
	 * Força uma quantidade especifica de registros 
	 * @throws BancoobException
	 * @author Marcos.Balbi
	 */	
	List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaReplicacao(Integer quantidade) throws BancoobException;	
	
	/**
	 * Consulta o registro da Tabela ContaCapital para replicacao
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	ReplicacaoTabelaContaCapitalLegadoDTO consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException;

	/**
	 * Consulta o registro da Tabela ParcelamentoCCA para replicacao
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	ReplicacaoTabelaParcelamentoCCALegadoDTO consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException;
	
	/**
	 * Consulta o registro da Tabela LancamentosCCapital para replicacao
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	ReplicacaoTabelaLancamentosCCapitalLegadoDTO consultarTabelaLancamentosCCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException;

	/**
	 * Altera o registro na tabela de Replicação do BDSicoobIntegração
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
	 * Consulta os valores de replicacao para monitoracao
	 * @throws BancoobException
	 * @author Marcos.Balbi
	 */	
	List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaMonitoracaoReplicacao() throws BancoobException;
	
	/**
	 * Recebe a operacao de bloqueio ou liberacao da execução
	 * 0 - Liberada
	 * 1 - Bloqueada 
	 * @param tipoAcao
	 * @throws BancoobException
	 */
	void liberarBloquearExecucao(Integer operacao) throws BancoobException;
	
	/**
	 * Alterar uma configuração para a replicacao da conta capital
	 * @param obj
	 * @throws BancoobException
	 */
	void alterarConfiguracaoReplicacaoCCA(ReplicacaoConfiguracaoLegadoDTO obj) throws BancoobException;
	
	
	/**
	 * Realiza consulta do monitor de replicacao   
	 * @param ConsultaMonitoracaoDTO
	 * @param cooperativasPiloto 
	 * @return
	 * @throws BancoobException
	 */
	List<MonitorReplicacaoCapitalLegadoDTO> consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO consultaDTO, List<Integer> cooperativasPiloto) throws BancoobException;
	
	/**
	 * Realiza consulta do monitor de replicacao por cooperativas
	 * @param consultaDTO
	 * @param cooperativasPiloto
	 * @return
	 * @throws BancoobException
	 */
	List<MonitorCooperativaReplicacaoCapitalLegadoDTO> consultarMonitoracaoCooperativasReplicacao(ConsultaMonitoracaoDTO consultaDTO, List<Integer> cooperativasPiloto) throws BancoobException;
	
	/**
	 * Realiza consulta de erros da replicacao de conta capital
	 * @param consultaDTO
	 * @param cooperativasPiloto
	 * @return
	 * @throws BancoobException
	 */
	List<ReplicacaoContaCapitalLegadoDTO> consultarErrosReplicacao(ConsultaMonitoracaoDTO consultaDTO, List<Integer> cooperativasPiloto) throws BancoobException;

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
	 * Expurga registro de replicacao conta capital por situacao e(ou) numero da cooperativa
	 * @param idSituacaoReplicacaoCCA
	 * @param numCooperativa
	 * @throws BancoobException
	 */
	void expurgarReplicacao(Integer idSituacaoReplicacaoCCA, Integer numCooperativa) throws BancoobException;
	
	/**
	 * Consulta Quantidade de registro na replicacao conta capital
	 * @return
	 * @throws BancoobException
	 */
	Long consultarQuantRegReplicacao() throws BancoobException;
	/**
	 * Expurga registro de replicacao conta capital replicados com sucesso
	 * @throws BancoobException
	 */
	void expurgarReplicacaoSucesso(Integer qdtDiasNaoExpurgar) throws BancoobException;

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
	 * @return
	 * @throws BancoobException
	 */
	List<Integer> consultarCooperativasConciliacao() throws BancoobException;

	/**
	 * Consulta os lancamentos nao atualizados.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoLancamentosNaoAtualizados(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta as parcelas sem subscricao.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoParcelaSemSubscricao(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta as subcricoes sem parcela.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoSubscricaoSemParcela(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta as cooperativas para expurgo.
	 * @param cooperativa
	 * @param cooperativasPiloto
	 * @return
	 * @throws BancoobException
	 */
	List<ExpurgoReplicacaoDTO> consultarExpurgo(Integer cooperativa, List<Integer> cooperativasPiloto) throws BancoobException;
	
	/**
	 * Consulta as duplicidades - clientes com mais de uma matricula ativa.
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoDuplicidade(Integer cooperativa) throws BancoobException;

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
	 * Consulta divergencias entre capa lote e lancamentos 
	 * @param coop
	 * @return
	 * @throws BancoobException
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoCapaLote(Integer coop) throws BancoobException;

	/**
	 * Consulta os saldos para batimento do legado.
	 * @param cooperativa
	 * @return
	 */
	List<BatimentoSaldoLegadoDTO> consultarBatimentosSaldosLegado(Integer cooperativa) throws BancoobException;

	/**
	 * Consulta os saldos negativos.
	 * @param coop
	 * @return
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoSaldosNegativos(Integer coop) throws BancoobException;
	
	/**
	 * Consulta parcelamento de devolucao sem saldo.
	 * @param coop
	 * @return
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoParcelamentoDevolucaoSemSaldo(Integer coop) throws BancoobException;
	
	/**
	 * Consulta devolucao sem parcelamento para ativos.
	 * @param coop
	 * @return
	 */
	List<ConfiguracaoConciliacaoDTO> consultarConciliacaoDevolucaoSemParcelamentoParaAtivos(Integer coop) throws BancoobException;
	
}