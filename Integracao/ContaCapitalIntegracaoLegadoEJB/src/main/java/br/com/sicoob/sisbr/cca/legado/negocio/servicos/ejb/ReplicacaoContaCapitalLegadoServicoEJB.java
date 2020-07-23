package br.com.sicoob.sisbr.cca.legado.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.joda.time.DateTime;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoCapesNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.constantes.ReplicacaoLegadoConstantes;
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
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoAtualizaChaveDb2LegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoAtualizaListaReplicacaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoConsultaLegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoConsultaListaReplicacaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoMonitoracaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoMonitoracaoReplicacaoNegocioException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ReplicacaoContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ReplicacaoContaCapitalLegadoServicoRemote;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.ContaCapitalIntegracaoLegadoDaoFactory;
import br.com.sicoob.sisbr.cca.legado.persistencia.dao.interfaces.ReplicacaoContaCapitalLegadoDao;

/**
 * EJB com os serviços de replicacao legado.
 */
@Stateless
@Local(ReplicacaoContaCapitalLegadoServicoLocal.class)
@Remote(ReplicacaoContaCapitalLegadoServicoRemote.class)
public class ReplicacaoContaCapitalLegadoServicoEJB extends ContaCapitalIntegracaoLegadoServicoEJB implements ReplicacaoContaCapitalLegadoServicoRemote, ReplicacaoContaCapitalLegadoServicoLocal {

	/**
	 * ReplicacaoContaCapitalLegadoDao 
	 */
	private ReplicacaoContaCapitalLegadoDao replicacaoContaCapitalLegadoDao = ContaCapitalIntegracaoLegadoDaoFactory.getInstance().criarReplicacaoContaCapitalLegadoDao();

	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaReplicacao()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaReplicacao()throws BancoobException {	
		try {
			List<ReplicacaoConfiguracaoLegadoDTO> listConfig = replicacaoContaCapitalLegadoDao.consultarListaConfiguracaoReplicacao();
			
			if (validarSuspenderReplicacao(consultarItemConfiguracao(listConfig,ReplicacaoLegadoConstantes.PARAM_SUSPENDER_REPLICACAO))){
				return null; 
			}

			Integer delay = validarDelayReplicacao(consultarItemConfiguracao(listConfig, ReplicacaoLegadoConstantes.PARAM_DELAY_REPLICACAO));			
			if (!delay.equals(ReplicacaoLegadoConstantes.VALOR_DEFAULT_DELAY_REPLICACAO)){
				Thread.sleep(delay);
			}

			List<String> listaCoop = validarCooperativasReplicacao(consultarItemConfiguracao(listConfig, ReplicacaoLegadoConstantes.PARAM_COOP_PILOTO_REPLICACAO));			
			Integer qtdRegistros =  validarQuantidadeRegistrosReplicacao(consultarItemConfiguracao(listConfig,ReplicacaoLegadoConstantes.PARAM_QTD_REG_REPLICACAO));
			
			return replicacaoContaCapitalLegadoDao.consultarTabelaReplicacao(qtdRegistros,listaCoop);
			
		}catch (ReplicacaoConsultaLegadoException e) {
			throw new ReplicacaoConsultaListaReplicacaoException(e.getMessage(),e);			
		}catch (ReplicacaoConsultaListaReplicacaoException e){
			throw new ReplicacaoConsultaListaReplicacaoException(e.getMessage(),e);			
		} catch (InterruptedException e) {
			throw new ReplicacaoConsultaListaReplicacaoException(e.getMessage(),e);
		}catch (IllegalStateException e) {
			throw new ReplicacaoConsultaListaReplicacaoException("MSG_ERRO_TIMEOUT_TRANSACAO",e.getMessage(),e);
		}
	}
	
	/**
	 * Consulta se a execução da replicacao esta liberada
	 * @return
	 * @throws BancoobException
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean consultarExecucaoLiberada() throws BancoobException{
		List<ReplicacaoConfiguracaoLegadoDTO> listConfig = replicacaoContaCapitalLegadoDao.consultarListaConfiguracaoReplicacao();
		ReplicacaoConfiguracaoLegadoDTO dto = consultarItemConfiguracao(listConfig,ReplicacaoLegadoConstantes.PARAM_REPLICACAO_INICIADA);
		return (dto != null && dto.getDescConfiguracaoReplicacao().equals("0"));
	}
	
	/**
	 * Liberar ou Bloquear a execução da replicacao
	 * 0 - Liberar
	 * 1 - Bloquear
	 * @param operacao
	 * @throws BancoobException
	 */
	public void liberarBloquearExecucao(Integer operacao) throws BancoobException{
		replicacaoContaCapitalLegadoDao.liberarBloquearExecucao(operacao);
	}
	
	/**
	 * Consulta a lista completa dos itens de configuração para replicação
	 * @return
	 * @throws BancoobException
	 */
	public List<ReplicacaoConfiguracaoLegadoDTO> consultarListaConfiguracaoReplicacao() throws BancoobException{
		return replicacaoContaCapitalLegadoDao.consultarListaConfiguracaoReplicacao();
	}	
	
	/**
	 * Altera um item de configuracao para a replicacao
	 */
	public void alterarConfiguracaoReplicacaoCCA(ReplicacaoConfiguracaoLegadoDTO obj) throws BancoobException {
		replicacaoContaCapitalLegadoDao.alterarConfiguracaoReplicacaoCCA(obj);		
	}	
	
	/**
	 * Consulta na Lista o item de configuracao da replicacao desejado
	 * @return
	 * @throws BancoobException
	 */
	private ReplicacaoConfiguracaoLegadoDTO consultarItemConfiguracao(List<ReplicacaoConfiguracaoLegadoDTO> listConfig, Integer parametro) throws BancoobException{
		ReplicacaoConfiguracaoLegadoDTO dto = null;
		for (ReplicacaoConfiguracaoLegadoDTO item :listConfig){
			if (item.getIdConfiguracaoReplicacaoCCA().equals(parametro)){
				dto = item;
				break;
			}
		}
		return dto;
	}
	
	/**
	 * Valida e retorna o item de configuracao "Quantidade de Registros"
	 * @author Marcos.Balbi
	 */
	private Integer validarQuantidadeRegistrosReplicacao(ReplicacaoConfiguracaoLegadoDTO dto) throws BancoobException{
		String quantidade = dto.getDescConfiguracaoReplicacao();
		if (quantidade != null && quantidade.matches("[0-9]+")){
			return Integer.valueOf(quantidade);		
		}else{
			return ReplicacaoLegadoConstantes.VALOR_DEFAULT_QTD_REG_REPLICACAO;
		}
	}

	/**
	 * Valida o item de configuracao "Suspender Replicacao no proximo agendamento"
	 * @author Marcos.Balbi
	 */
	private Boolean validarSuspenderReplicacao(ReplicacaoConfiguracaoLegadoDTO dto) throws BancoobException{
		return (dto.getDescConfiguracaoReplicacao() != null && dto.getDescConfiguracaoReplicacao().equals("1"));
	}	
	
	/**
	 * Valida e retorna o item de configuracao "Delay da Thread de replicacao"
	 * @author Marcos.Balbi
	 */
	private Integer validarDelayReplicacao(ReplicacaoConfiguracaoLegadoDTO dto) throws BancoobException{
		String quantidade = dto.getDescConfiguracaoReplicacao();
		if (quantidade != null && quantidade.matches("[0-9]+")){
			return Integer.valueOf(quantidade);		
		}else{
			return ReplicacaoLegadoConstantes.VALOR_DEFAULT_DELAY_REPLICACAO;
		}
	}
	
	/**
	 * Valida e retorna o item de configuracao "Cooperativas Piloto"
	 * @author Marcos.Balbi
	 */
	private List<String> validarCooperativasReplicacao(ReplicacaoConfiguracaoLegadoDTO dto) throws BancoobException{
		String coop = dto.getDescConfiguracaoReplicacao();
		List<String> listCoop = new ArrayList<String>();
		
		if (coop != null && coop.length() > 0){
			String[] vetorCoop = coop.split(",");
			
			for (int x=0; x < vetorCoop.length; x++){
				if (vetorCoop[x].matches("[0-9]+")){
					listCoop.add(vetorCoop[x]);
				}else{
					listCoop = null;
					break;
				}
			}
		}
		return listCoop;		
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public ReplicacaoTabelaContaCapitalLegadoDTO consultarTabelaContaCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {
		try{
			return replicacaoContaCapitalLegadoDao.consultarTabelaContaCapitalReplicacao(obj);
		}catch (ReplicacaoConsultaLegadoException e) {
			throw new ReplicacaoConsultaLegadoException("MSG_ERRO_CONSULTA_TABELA_CONTACAPITAL",e.getMessage(),e);			
		}catch (IntegracaoCapesNegocioException e){
			throw new ReplicacaoConsultaLegadoException("MSG_ERRO_CONSULTA_TABELA_CONTACAPITAL",e.getMessage(),e);			
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public ReplicacaoTabelaParcelamentoCCALegadoDTO consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {
		try{
			return replicacaoContaCapitalLegadoDao.consultarTabelaParcelamentoCCAReplicacao(obj);
		}catch (ReplicacaoConsultaLegadoException e) {
			throw new ReplicacaoConsultaLegadoException("MSG_ERRO_CONSULTA_TABELA_PARCELAMENTOCCA",e.getMessage(),e);			
		}
	}


	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaParcelamentoCCAReplicacao(ReplicacaoContaCapitalLegadoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public ReplicacaoTabelaLancamentosCCapitalLegadoDTO consultarTabelaLancamentosCCapitalReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {
		try{
			return replicacaoContaCapitalLegadoDao.consultarTabelaLancamentosCCapitalReplicacao(obj);
		}catch (ReplicacaoConsultaLegadoException e) {
			throw new ReplicacaoConsultaLegadoException("MSG_ERRO_CONSULTA_TABELA_LANCAMENTOSCCAPITAL",e.getMessage(),e);			
		}		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaContaCapitalReplicacao(Integer) 
	 */
	public void alterarTabelaContaCapitalReplicacao(ReplicacaoTabelaContaCapitalLegadoDTO obj) throws BancoobException {
		try {
			replicacaoContaCapitalLegadoDao.alterarTabelaContaCapitalReplicacao(obj);
		} catch (ReplicacaoAtualizaChaveDb2LegadoException e) {
			throw new ReplicacaoAtualizaChaveDb2LegadoException("MSG_ERRO_ATUALIZACAO_CONTACAPITAL",e.getMessage(),e);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaParcelamentoCCAReplicacao(Long) 
	 */	
	public void alterarTabelaParcelamentoCCAReplicacao(ReplicacaoTabelaParcelamentoCCALegadoDTO obj) throws BancoobException {
		try {
			replicacaoContaCapitalLegadoDao.alterarTabelaParcelamentoCCAReplicacao(obj);
		} catch (ReplicacaoAtualizaChaveDb2LegadoException e) {
			throw new ReplicacaoAtualizaChaveDb2LegadoException("MSG_ERRO_ATUALIZACAO_PARCELAMENTOCCA",e.getMessage(),e);
		}				
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaLancamentosCCapitalReplicacao(Long) 
	 */	
	public void alterarTabelaLancamentosCCapitalReplicacao(ReplicacaoTabelaLancamentosCCapitalLegadoDTO obj) throws BancoobException {
		try {
			replicacaoContaCapitalLegadoDao.alterarTabelaLancamentosCCapitalReplicacao(obj);
		} catch (ReplicacaoAtualizaChaveDb2LegadoException e) {
			throw new ReplicacaoAtualizaChaveDb2LegadoException("MSG_ERRO_ATUALIZACAO_LANCAMENTOSCCAPITAL",e.getMessage(),e);
		}		
	}

	/**
	 * Altera a tabela de replicacao com as informações da replicacao
	 * E requiresnew por rodar em momento posterior a replicacao e deve acontecer mesmo sem o sucesso dos crud's
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO) 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException {
		try {
			replicacaoContaCapitalLegadoDao.alterarTabelaReplicacao(obj);
		} catch (ReplicacaoAtualizaListaReplicacaoException e) {
			throw new ReplicacaoAtualizaListaReplicacaoException("MSG_ERRO_ATUALIZA_TABELA_REPLICACAO",e.getMessage(),e);
		} catch (BancoobException e) {
			throw new ReplicacaoAtualizaListaReplicacaoException("MSG_ERRO_ATUALIZA_TABELA_REPLICACAO",e.getMessage(),e);
		}		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarTabelaMonitoracaoReplicacao() 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public List<ReplicacaoContaCapitalLegadoDTO> consultarTabelaMonitoracaoReplicacao() throws BancoobException {
		try{
			return replicacaoContaCapitalLegadoDao.consultarTabelaMonitoracaoReplicacao();	
		}catch (ReplicacaoMonitoracaoReplicacaoNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException("MSG_MONITORACAO_CONSULTA_TOTAL", e);
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException("MSG_MONITORACAO_CONSULTA_TOTAL", e);
		}		
	}

	/**
	 * {@link br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MonitorReplicacaoCapitalLegadoDTO> consultarMonitoracaoReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException {
		try {
			List<Integer> cooperativasPiloto = null;
			if (Boolean.TRUE.equals(consultaDTO.getApenasPilotos())) {
				cooperativasPiloto = consultarCooperativasPiloto();
				consultaDTO.setNumCooperativa(null);
			}
			return replicacaoContaCapitalLegadoDao.consultarMonitoracaoReplicacao(consultaDTO, cooperativasPiloto);
		} catch (ReplicacaoConsultaLegadoException e) {
			getLogger().erro(e, e.getMessage());
			throw new ReplicacaoConsultaListaReplicacaoException(e.getMessage(), e);
		}
	}
	
	/**
	 * {@link br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarMonitoracaoCooperativasReplicacao(ConsultaMonitoracaoDTO)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MonitorCooperativaReplicacaoCapitalLegadoDTO> consultarMonitoracaoCooperativasReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException {
		try {
			List<Integer> cooperativasPiloto = null;
			if (Boolean.TRUE.equals(consultaDTO.getApenasPilotos())) {
				cooperativasPiloto = consultarCooperativasPiloto();
				consultaDTO.setNumCooperativa(null);
			}
			return replicacaoContaCapitalLegadoDao.consultarMonitoracaoCooperativasReplicacao(consultaDTO, cooperativasPiloto);
		} catch (ReplicacaoConsultaLegadoException e) {
			getLogger().erro(e, e.getMessage());
			throw new ReplicacaoConsultaListaReplicacaoException(e.getMessage(), e);
		}
	}

	/**
	 * {@link br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarErrosReplicacao(Integer, Boolean)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ReplicacaoContaCapitalLegadoDTO> consultarErrosReplicacao(ConsultaMonitoracaoDTO consultaDTO) throws BancoobException {
		try {
			List<Integer> cooperativasPiloto = null;
			if (Boolean.TRUE.equals(consultaDTO.getApenasPilotos())) {
				cooperativasPiloto = consultarCooperativasPiloto();
				consultaDTO.setNumCooperativa(null);
			}
			return replicacaoContaCapitalLegadoDao.consultarErrosReplicacao(consultaDTO, cooperativasPiloto);
		} catch (ReplicacaoConsultaLegadoException e) {
			getLogger().erro(e, e.getMessage());
			throw new ReplicacaoConsultaListaReplicacaoException(e.getMessage(), e);
		}
	}

	/**
	 * {@link br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#reprocessar(List, String, String)}
	 */
	public void reprocessar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException {
		try {
			replicacaoContaCapitalLegadoDao.reprocessar(lst, justificativa, idUsuario);	
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e.getMessage(), e);
		}
	}
	
	/**
	 * {@link br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#invalidar(List, String, String)}
	 */
	public void invalidar(List<ReplicacaoContaCapitalLegadoDTO> lst, String justificativa, String idUsuario) throws BancoobException {
		try {
			replicacaoContaCapitalLegadoDao.invalidar(lst, justificativa, idUsuario);	
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException(e.getMessage(), e);
		}
	}

	/*Expurgo dos registros já replicados
	 * (non-Javadoc)
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#expurgarReplicacaoSucesso()
	 */
	public void expurgarReplicacaoSucesso() throws BancoobException{
			List<ReplicacaoConfiguracaoLegadoDTO> listConfig = replicacaoContaCapitalLegadoDao.consultarListaConfiguracaoReplicacao();
			
			if (!validarSuspenderReplicacao(consultarItemConfiguracao(listConfig,ReplicacaoLegadoConstantes.PARAM_SUSPENDER_EXPURGO))){				
				replicacaoContaCapitalLegadoDao.expurgarReplicacaoSucesso(consultarQtdDiasNaoExpurgar(consultarItemConfiguracao(listConfig,ReplicacaoLegadoConstantes.PARAM_QTD_DIAS_NAO_EXPURGAR_REPLICACAO)));				 					
			}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#prepararCooperativaPiloto(Integer)
	 */
	public void prepararCooperativaPiloto(Integer cooperativa) throws BancoobException {
		verificarUsuarioLogado();
		replicacaoContaCapitalLegadoDao.prepararCooperativaPiloto(cooperativa);
	}

	private void verificarUsuarioLogado() throws ReplicacaoMonitoracaoException {
		if (InformacoesUsuario.getInstance().getLogin() == null) {
			throw new ReplicacaoMonitoracaoException("Acesso negado.");
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarCooperativaPiloto(Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<String, Long> consultarCooperativaPiloto(Integer cooperativa) throws BancoobException {
		return replicacaoContaCapitalLegadoDao.consultarCooperativaPiloto(cooperativa);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarBatimentosContaCapital(Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RegistroBatimentoContaCapitalDTO> consultarBatimentosContaCapital(Integer cooperativa) throws BancoobException {
		return replicacaoContaCapitalLegadoDao.consultarBatimentosContaCapital(cooperativa);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarBatimentosLancamento(Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RegistroBatimentoLancamentoDTO> consultarBatimentosLancamento(Integer cooperativa) throws BancoobException {
		return replicacaoContaCapitalLegadoDao.consultarBatimentosLancamento(cooperativa);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarBatimentosParcelamento(Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RegistroBatimentoParcelamentoDTO> consultarBatimentosParcelamento(Integer cooperativa) throws BancoobException {
		return replicacaoContaCapitalLegadoDao.consultarBatimentosParcelamento(cooperativa);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#executarConciliacao(Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<String, Object> executarConciliacao(Integer cooperativa) throws BancoobException {
		List<Integer> cooperativas = inicializarCooperativasConciliacao(cooperativa);
		Map<String, Object> map = new HashMap<String, Object>();
		List<ConfiguracaoConciliacaoDTO> dtosLanc = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosParcSemSub = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosSubSemParc = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosErro = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosDuplicidade = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosCapaLote = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosSaldosNegativos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosParcDevolSemSaldo = new ArrayList<ConfiguracaoConciliacaoDTO>();
		List<ConfiguracaoConciliacaoDTO> dtosDevolSemParcParaAtivos = new ArrayList<ConfiguracaoConciliacaoDTO>();
		for (Integer coop : cooperativas) {
			try {
				dtosLanc.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoLancamentosNaoAtualizados(coop));
				dtosParcSemSub.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoParcelaSemSubscricao(coop));
				dtosSubSemParc.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoSubscricaoSemParcela(coop));
				dtosDuplicidade.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoDuplicidade(coop));
				dtosCapaLote.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoCapaLote(coop));
				dtosSaldosNegativos.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoSaldosNegativos(coop));
				dtosParcDevolSemSaldo.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoParcelamentoDevolucaoSemSaldo(coop));
				dtosDevolSemParcParaAtivos.addAll(replicacaoContaCapitalLegadoDao.consultarConciliacaoDevolucaoSemParcelamentoParaAtivos(coop));
			} catch (BancoobException be) {
				ConfiguracaoConciliacaoDTO dtoErro = new ConfiguracaoConciliacaoDTO();
				dtoErro.setScript("-- ERRO "+coop+" > "+be.getMessage());
				dtosErro.add(dtoErro);
			}
		}
		map.put("lancamentosNaoAtualizados", dtosLanc);
		map.put("parcelaSemSubscricao", dtosParcSemSub);
		map.put("subscricaoSemParcela", dtosSubSemParc);
		map.put("erros", dtosErro);
		map.put("duplicidade", dtosDuplicidade);
		map.put("capaLote", dtosCapaLote);
		map.put("saldosNegativos", dtosSaldosNegativos);
		map.put("parcDevolSemSaldo", dtosParcDevolSemSaldo);
		map.put("devolSemParcParaAtivos", dtosDevolSemParcParaAtivos);
		return map;
	}

	private List<Integer> inicializarCooperativasConciliacao(Integer cooperativa) throws BancoobException {
		List<Integer> cooperativas = null;
		if (cooperativa == null) {
			cooperativas = consultarCooperativasConciliacao();
		} else {
			cooperativas = new ArrayList<Integer>();
			cooperativas.add(cooperativa);
		}
		return cooperativas;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarCooperativasConciliacao()
	 */
	public List<Integer> consultarCooperativasConciliacao() throws BancoobException {
		return replicacaoContaCapitalLegadoDao.consultarCooperativasConciliacao();
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarExpurgo(Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ExpurgoReplicacaoDTO> consultarExpurgo(Integer cooperativa) throws BancoobException {
		List<Integer> cooperativasPiloto = consultarCooperativasPiloto();
		if (cooperativa != null && cooperativasPiloto.contains(cooperativa)) {
			throw new ReplicacaoMonitoracaoReplicacaoNegocioException("Esta cooperativa está configurada como piloto e não pode ser expurgada.");
		}
		List<ExpurgoReplicacaoDTO> dtos = replicacaoContaCapitalLegadoDao.consultarExpurgo(cooperativa, cooperativasPiloto);
		for (ExpurgoReplicacaoDTO dto : dtos) {
			try {
				InstituicaoIntegracaoDTO instituicaoDTO = instituicaoIntegracaoServico.obterInstituicaoIntegracao(dto.getIdInstituicao());
				dto.setNomeCooperativa(instituicaoDTO.getSiglaInstituicao());
			} catch (BancoobException be) {
				dto.setNomeCooperativa("");
			}
		}
		return dtos;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarCooperativasPiloto()
	 */
	public List<Integer> consultarCooperativasPiloto() throws BancoobException {
		List<ReplicacaoConfiguracaoLegadoDTO> listConfig = replicacaoContaCapitalLegadoDao.consultarListaConfiguracaoReplicacao();
		ReplicacaoConfiguracaoLegadoDTO item = consultarItemConfiguracao(listConfig, ReplicacaoLegadoConstantes.PARAM_COOP_PILOTO_REPLICACAO);
		List<Integer> cooperativas = new ArrayList<Integer>();
		if (item.getDescConfiguracaoReplicacao() != null && item.getDescConfiguracaoReplicacao().length() > 0) {
			String[] valores = item.getDescConfiguracaoReplicacao().split(",");
			for (String coop : valores) {
				try {
					cooperativas.add(Integer.valueOf(coop));
				} catch (NumberFormatException nfe) {
					throw new ReplicacaoMonitoracaoReplicacaoNegocioException("Erro ao interpretar parâmetro de cooperativas piloto.");
				}
			}
		}
		return cooperativas;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#expurgarCooperativaReplicacao(Integer)
	 */
	public void expurgarCooperativaReplicacao(Integer cooperativa) throws BancoobException {
		verificarUsuarioLogado();
		replicacaoContaCapitalLegadoDao.expurgarReplicacao(0, cooperativa);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#alterarJSONReplicacao(java.lang.Integer, java.lang.String)
	 */
	public void alterarJSONReplicacao(Integer idReplicacaoCCA, String json) throws BancoobException {
		replicacaoContaCapitalLegadoDao.alterarJSONReplicacao(idReplicacaoCCA, json);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarReplicacaoPorIds(java.lang.Long, java.lang.Long)
	 */
	public List<ReplicacaoContaCapitalLegadoDTO> consultarReplicacaoPorIds(Long idInicial, Long idFinal) throws BancoobException {
		return replicacaoContaCapitalLegadoDao.consultarReplicacaoPorIds(idInicial, idFinal);
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarBatimentoSaldosLegado(java.lang.Integer)
	 */
	public List<BatimentoSaldoLegadoDTO> consultarBatimentoSaldosLegado(Integer cooperativa) throws BancoobException {
		return replicacaoContaCapitalLegadoDao.consultarBatimentosSaldosLegado(cooperativa);
	}
		
	public Integer consultarQtdDiasNaoExpurgar(ReplicacaoConfiguracaoLegadoDTO replicacaoConfiguracaoLegadoDTO) throws BancoobException {
		Integer qtdDiasNaoExpurgar = 0;		
		
		if(replicacaoConfiguracaoLegadoDTO.getDescConfiguracaoReplicacao() != null) {
			qtdDiasNaoExpurgar = Integer.valueOf(replicacaoConfiguracaoLegadoDTO.getDescConfiguracaoReplicacao());
		}
		return qtdDiasNaoExpurgar;
	}
	
	/* Consulta quantos dias para tras o expurgo deve ser realizado
	 * (non-Javadoc)
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#consultarDataExpurgoOperacao()
	 */
	public Date consultarDataExpurgoOperacao() throws BancoobException {
		List<ReplicacaoConfiguracaoLegadoDTO> listConfig = replicacaoContaCapitalLegadoDao.consultarListaConfiguracaoReplicacao();
		return new DateTime().minusDays(consultarQtdDiasNaoExpurgar(consultarItemConfiguracao(listConfig,ReplicacaoLegadoConstantes.PARAM_QTD_DIAS_NAO_EXPURGAR_OPERACAO_CCA))).toDate();					
	}
	
	/*Consulta se o parametro para suspender o expurgo de operação esta marcado
	 * (non-Javadoc)
	 * @see br.com.sicoob.sisbr.cca.legado.negocio.servicos.ReplicacaoContaCapitalLegadoServico#validarSuspensaoExpurgoOperacao()
	 */
	public Boolean validarSuspensaoExpurgoOperacao() throws BancoobException {
			List<ReplicacaoConfiguracaoLegadoDTO> listConfig = replicacaoContaCapitalLegadoDao.consultarListaConfiguracaoReplicacao();
			if (validarSuspenderReplicacao(consultarItemConfiguracao(listConfig,ReplicacaoLegadoConstantes.PARAM_SUSPENDER_EXPURGO_OPERACAO_CCA))){
				return true;
			}
			return false;			
	}
	
}