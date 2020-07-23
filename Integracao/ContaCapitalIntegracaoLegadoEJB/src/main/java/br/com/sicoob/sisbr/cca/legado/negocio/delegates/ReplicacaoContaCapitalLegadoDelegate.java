/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.delegates;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
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
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.locator.ContaCapitalIntegracaoLegadoServiceLocator;

/**
 * Delegate ReplicacaoContaCapitalLegadoDelegate
 */
public class ReplicacaoContaCapitalLegadoDelegate extends ContaCapitalIntegracaoLegadoDelegate<ReplicacaoContaCapitalLegadoServico> {

	/**
	 * Recupera a instancia
	 * @return
	 */
	public static ReplicacaoContaCapitalLegadoDelegate getInstance(){
		return new ReplicacaoContaCapitalLegadoDelegate();
	}		
	
	/**
	 * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
	 */
	@Override
	protected ReplicacaoContaCapitalLegadoServico localizarServico() {
		return (ReplicacaoContaCapitalLegadoServico) ContaCapitalIntegracaoLegadoServiceLocator.getInstance().localizarReplicacaoContaCapitalLegadoServico();	
	}

	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaReplicacao()
	 * @return
	 * @throws BancoobException
	 */
	public List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaReplicacao() throws BancoobException{
		return getServico().consultarTabelaReplicacao();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 * @return
	 * @throws BancoobException
	 */
	public ReplicacaoTabelaContaCapitalLegadoDTO consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException{
		return getServico().consultarTabelaContaCapitalReplicacao(obj);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 * @return
	 * @throws BancoobException
	 */
	
	public ReplicacaoTabelaParcelamentoCCALegadoDTO consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException{
		return getServico().consultarTabelaParcelamentoCCAReplicacao(obj);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaLancamentosCCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 * @return
	 * @throws BancoobException
	 */
	public ReplicacaoTabelaLancamentosCCapitalLegadoDTO consultarTabelaLancamentosCCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException{
		return getServico().consultarTabelaLancamentosCCapitalReplicacao(obj);
	}			
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 * @param obj
	 * @throws BancoobException
	 */
	public void alterarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException{
		getServico().alterarTabelaReplicacao(obj);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaContaCapitalReplicacao(Integer)
	 * @param obj
	 * @throws BancoobException
	 */
	public void alterarTabelaContaCapitalReplicacao(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException{
		getServico().alterarTabelaContaCapitalReplicacao(obj);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaParcelamentoCCAReplicacao(Long)
	 * @param obj
	 * @throws BancoobException
	 */
	public void alterarTabelaParcelamentoCCAReplicacao(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException{
		getServico().alterarTabelaParcelamentoCCAReplicacao(obj);
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaLancamentosCCapitalReplicacao(Long)
	 * @param obj
	 * @throws BancoobException
	 */
	public void alterarTabelaLancamentosCCapitalReplicacao(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException{
		getServico().alterarTabelaLancamentosCCapitalReplicacao(obj);
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaMonitoracaoReplicacao()
	 * @return
	 * @throws BancoobException
	 */
	public List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaMonitoracaoReplicacao() throws BancoobException {
		return getServico().consultarTabelaMonitoracaoReplicacao();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarExecucaoLiberada()
	 */
	public Boolean consultarExecucaoLiberada() throws BancoobException{
		return getServico().consultarExecucaoLiberada();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#liberarBloquearExecucao(Integer)
	 */	
	public void liberarBloquearExecucao(Integer operacao) throws BancoobException{
		getServico().liberarBloquearExecucao(operacao);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarListaConfiguracaoReplicacao()
	 */	
	public List<ReplicacaoConfiguracaoLegadoDTO> consultarListaConfiguracaoReplicacao() throws BancoobException{
		return getServico().consultarListaConfiguracaoReplicacao();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarConfiguracaoReplicacaoCCA(ReplicacaoConfiguracaoLegadoDTO)
	 */	
	public void alterarConfiguracaoReplicacaoCCA(ReplicacaoConfiguracaoLegadoDTO obj) throws BancoobException{
		getServico().alterarConfiguracaoReplicacaoCCA(obj);
	}
	
	/**
	 * @param ConsultaMonitoracaoDTO
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO)
	 * @return
	 * @throws BancoobException
	 */
	public List<MonitorReplicacaoCapitalLegadoDTO> consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException {
		return getServico().consultarMonitoracaoReplicacao(consultaDTO);
	}
	
	/**
	 * @param ConsultaMonitoracaoDTO
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarMonitoracaoCooperativasReplicacao(ConsultaMonitoracaoDTO)
	 * @return
	 * @throws BancoobException
	 */
	public List<MonitorCooperativaReplicacaoCapitalLegadoDTO> consultarMonitoracaoCooperativasReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException {
		return getServico().consultarMonitoracaoCooperativasReplicacao(consultaDTO);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarErrosReplicacao(Integer, Boolean)
	 * @param ConsultaMonitoracaoDTO
	 * @return
	 * @throws BancoobException
	 */
	public List<ReplicacaoContaCapitalLegadoDTO> consultarErrosReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException {
		return getServico().consultarErrosReplicacao(consultaDTO);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#reprocessar(List, String, String)
	 */
	public void reprocessar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException {
		getServico().reprocessar(lst, justificativa, idUsuario);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#invalidar(lst)
	 */
	public void invalidar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException {
		getServico().invalidar(lst, justificativa, idUsuario);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#expurgarReplicacaoSucesso()
	 */
	public void expurgarReplicacaoSucesso() throws BancoobException {
		getServico().expurgarReplicacaoSucesso();
	}

	/**
	 * @see ReplicacaoContaCapitalLegadoServico#prepararCooperativaPiloto(Integer)
	 * @param cooperativa
	 * @throws BancoobException
	 */
	public void prepararCooperativaPiloto(Integer cooperativa) throws BancoobException {
		getServico().prepararCooperativaPiloto(cooperativa);
	}

	/**
	 * @see ReplicacaoContaCapitalLegadoServico#consultarCooperativaPiloto(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public Map<String, Long> consultarCooperativaPiloto(Integer cooperativa) throws BancoobException {
		return getServico().consultarCooperativaPiloto(cooperativa);
	}

	/**
	 * @see ReplicacaoContaCapitalLegadoServico#consultarBatimentosContaCapital(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public List<RegistroBatimentoContaCapitalDTO> consultarBatimentosContaCapital(Integer cooperativa) throws BancoobException {
		return getServico().consultarBatimentosContaCapital(cooperativa);
	}

	/**
	 * @see ReplicacaoContaCapitalLegadoServico#consultarBatimentosLancamento(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public List<RegistroBatimentoLancamentoDTO> consultarBatimentosLancamento(Integer cooperativa) throws BancoobException {
		return getServico().consultarBatimentosLancamento(cooperativa);
	}

	/**
	 * @see ReplicacaoContaCapitalLegadoServico#consultarBatimentosParcelamento(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public List<RegistroBatimentoParcelamentoDTO> consultarBatimentosParcelamento(Integer cooperativa) throws BancoobException {
		return getServico().consultarBatimentosParcelamento(cooperativa);
	}
	
	/**
	 * @see ReplicacaoContaCapitalLegadoServico#executarConciliacao(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public Map<String, Object> executarConciliacao(Integer cooperativa) throws BancoobException {
		return getServico().executarConciliacao(cooperativa);
	}
	
	/**
	 * @see ReplicacaoContaCapitalLegadoServico#consultarCooperativasConciliacao()
	 * @return
	 * @throws BancoobException
	 */
	public List<Integer> consultarCooperativasConciliacao() throws BancoobException {
		return getServico().consultarCooperativasConciliacao();
	}
	
	/**
	 * @see ReplicacaoContaCapitalLegadoServico#consultarExpurgo(Integer)
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	public List<ExpurgoReplicacaoDTO> consultarExpurgo(Integer cooperativa) throws BancoobException {
		return getServico().consultarExpurgo(cooperativa);
	}
	
	/**
	 * @see ReplicacaoContaCapitalLegadoServico#expurgarCooperativaReplicacao(Integer)
	 * @param cooperativa
	 * @throws BancoobException
	 */
	public void expurgarCooperativaReplicacao(Integer cooperativa) throws BancoobException {
		getServico().expurgarCooperativaReplicacao(cooperativa);
	}

	/**
	 * Altera o JSON de um registro de replicacao.
	 * @param idReplicacaoCCA
	 * @param json
	 */
	public void alterarJSONReplicacao(Integer idReplicacaoCCA, String json) throws BancoobException {
		getServico().alterarJSONReplicacao(idReplicacaoCCA, json);
	}

	/**
	 * Consulta replicacao pelos IDs
	 * @param idInicial
	 * @param idFinal
	 * @return
	 */
	public List<ReplicacaoContaCapitalLegadoDTO> consultarReplicacaoPorIds(Long idInicial, Long idFinal) throws BancoobException {
		return getServico().consultarReplicacaoPorIds(idInicial, idFinal);
	}
	
	/**
	 * Consulta as cooperativas piloto.
	 * @return
	 * @throws BancoobException
	 */
	public List<Integer> consultarCooperativasPiloto() throws BancoobException {
		return getServico().consultarCooperativasPiloto();
	}
	
}