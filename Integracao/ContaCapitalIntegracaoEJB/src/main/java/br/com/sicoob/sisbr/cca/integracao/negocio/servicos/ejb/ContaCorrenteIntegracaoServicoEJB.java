package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cco.api.negocio.delegates.ContaCorrenteApiFabricaDelegates;
import br.com.sicoob.cco.api.negocio.delegates.ContaCorrenteDelegate;
import br.com.sicoob.cco.api.negocio.delegates.SaldoContaCorrenteDelegate;
import br.com.sicoob.cco.api.negocio.dto.ContaCorrenteDTO;
import br.com.sicoob.cco.api.negocio.dto.ParticipanteContaDTO;
import br.com.sicoob.cco.api.negocio.dto.SaldoContaCorrenteDTO;
import br.com.sicoob.cco.api.negocio.dto.SaldoContaCorrenteFiltroDTO;
import br.com.sicoob.cco.api.negocio.dto.filtros.ContaCorrenteFiltroDTO;
import br.com.sicoob.cco.api.negocio.enums.AplicativoEnum;
import br.com.sicoob.cco.api.negocio.enums.ModalidadeContaCorrenteEnum;
import br.com.sicoob.cco.api.negocio.enums.SituacaoContaCorrenteEnum;
import br.com.sicoob.cco.api.negocio.excecao.ContaCorrenteApiException;
import br.com.sicoob.cco.api.negocio.excecao.ContaCorrenteApiNegocioException;
import br.com.sicoob.cco.movimentacao.api.delegates.LancamentoIntegracaoDelegate;
import br.com.sicoob.cco.movimentacao.api.delegates.MovimentacaoApiFabricaDelegates;
import br.com.sicoob.cco.movimentacao.api.dto.LancamentoIntegracaoLoteRetDTO;
import br.com.sicoob.cco.movimentacao.api.dto.LancamentoIntegracaoRetDTO;
import br.com.sicoob.cco.movimentacao.api.dto.filtros.LancamentoIntegracaoDTO;
import br.com.sicoob.cco.movimentacao.api.dto.filtros.LancamentoIntegracaoLoteDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoLoteRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ParticipanteContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.SaldoContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.SaldoContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoContaCorrenteException;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoContaCorrenteNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCorrenteIntegracaoServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoRemote;
import br.com.sicoob.tipos.DateTime;

/**
 * EJB contendo servicos relacionados a ContaCorrenteIntegracao.
 */
@Stateless
@Local (ContaCorrenteIntegracaoServicoLocal.class)
@Remote(ContaCorrenteIntegracaoServicoRemote.class)
public class ContaCorrenteIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements ContaCorrenteIntegracaoServicoLocal, ContaCorrenteIntegracaoServicoRemote {
	
	/** A constante ERRO_LANCAMENTO_CC. */
	private static final String ERRO_LANCAMENTO_CC = "MSG_014";
	
	/** A constante ERRO_INTEG_CCO. */
	private static final String ERRO_INTEG_CCO = "MSG_018";
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCorrenteIntegracaoServico#obterSaldoContaCorrente(br.com.sicoob.sisbr.cca.integracao.negocio.dto.SaldoContaCorrenteIntegracaoDTO)
	 */
	public SaldoContaCorrenteIntegracaoRetDTO obterSaldoContaCorrente(
			SaldoContaCorrenteIntegracaoDTO dto) throws BancoobException {
		this.getLogger().info("CCA.obterSaldoContaCorrente");
		SaldoContaCorrenteIntegracaoRetDTO dtoRetCca= null;
		
		try{
			SaldoContaCorrenteDelegate saldoContaCorrenteDelegate = ContaCorrenteApiFabricaDelegates.getInstance().criarSaldoContaCorrenteDelegate();
			SaldoContaCorrenteDTO dtoRetCco = saldoContaCorrenteDelegate.obterSaldo(montarSaldoDTO(dto));
			dtoRetCca = montarSaldoRetDTO(dtoRetCco);
		}catch (ContaCorrenteApiNegocioException e) {
			throw new IntegracaoContaCorrenteNegocioException(ERRO_LANCAMENTO_CC, e.getMessage());			
		}catch (ContaCorrenteApiException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);
		}catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e); 
		}
		
		return dtoRetCca;		
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCorrenteIntegracaoServico#gravarLancamentosIntegracao(br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO)
	 */
	public LancamentoContaCorrenteIntegracaoRetDTO gravarLancamentosIntegracao(LancamentoContaCorrenteIntegracaoDTO dtoCca) throws BancoobException {
		this.getLogger().info("CCA.gravarLancamentosIntegracao");
		LancamentoContaCorrenteIntegracaoRetDTO dtoRetCca= null;
		
		try{
			LancamentoIntegracaoDelegate lancamentoIntegracaoDelegate = MovimentacaoApiFabricaDelegates.getInstance().criarLancamentoIntegracaoDelegate();
			LancamentoIntegracaoRetDTO dtoRetCco = lancamentoIntegracaoDelegate.gravarLancamentoIntegracao(montarLancamentoDTO(dtoCca));
			
			dtoRetCca = montarLancamentoRetDTO(dtoRetCco);
		}catch (ContaCorrenteApiNegocioException e) {
			throw new IntegracaoContaCorrenteNegocioException(ERRO_LANCAMENTO_CC, e.getMessage());			
		}catch (ContaCorrenteApiException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);
		}catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e); 
		}
		
		return dtoRetCca;
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#verificarContaCorrenteAtiva(ContaCorrenteIntegracaoDTO)}
	 */
	public Boolean verificarContaCorrenteAtiva(ContaCorrenteIntegracaoDTO dto)throws BancoobException {
		List<ContaCorrenteIntegracaoRetDTO> lstConta = verificarContaCorrentePorIdPessoaRet(dto);
		if (lstConta == null){
			throw new IntegracaoContaCorrenteNegocioException("MSG_CCO_NAO_ENCONTRADA");
		}
		removerContasCorrenteInativas(lstConta);
		return !lstConta.isEmpty();		
	}
	
	/**
	 * {@link ContaCorrenteIntegracaoServico#verificarContaCorrenteBloqueadaEncerrada(ContaCorrenteIntegracaoDTO)}
	 */	
	
	public Boolean verificarContaCorrenteBloqueadaEncerrada(ContaCorrenteIntegracaoDTO dto)throws BancoobException {
		List<ContaCorrenteIntegracaoRetDTO> lstConta = verificarContaCorrentePorIdPessoaRet(dto);
		if (lstConta == null){
			throw new IntegracaoContaCorrenteNegocioException("MSG_CCO_NAO_ENCONTRADA");
		}
		for (ContaCorrenteIntegracaoRetDTO item : lstConta){
			if (item.getDataBloqueio() != null || item.getDataEncerramento() != null){
				return Boolean.TRUE;				
			}
		}
		return Boolean.FALSE;
	}	

	/**
	 * {@link ContaCorrenteIntegracaoServico#verificarContaCorrentePorIdPessoa(ContaCorrenteIntegracaoDTO)}
	 */	
	public Boolean verificarContaCorrentePorIdPessoa(ContaCorrenteIntegracaoDTO dto) throws BancoobException {
		Boolean saida = false;
		
		if (dto.getNumContaCorrente() == null || dto.getIdPessoa() == null || dto.getIdInstituicao() == null){
			throw new IntegracaoContaCorrenteNegocioException("MSG_FALTA_PARAM");	
		}
		
		List<ContaCorrenteIntegracaoRetDTO> lstConta = consultarContaCorrentePorNumeroCliente(dto);
		
		for (ContaCorrenteIntegracaoRetDTO item:lstConta) {
			if (dto.getNumContaCorrente().equals(item.getNumeroContaCorrente())) {
				saida = true;
				break;
			}
		}
		
		return saida;
	}
	
	private List<ContaCorrenteIntegracaoRetDTO> verificarContaCorrentePorIdPessoaRet(ContaCorrenteIntegracaoDTO dto) throws BancoobException {
		if (dto.getNumContaCorrente() == null || dto.getIdPessoa() == null || dto.getIdInstituicao() == null){
			throw new IntegracaoContaCorrenteNegocioException("MSG_FALTA_PARAM");	
		}
		
		List<ContaCorrenteIntegracaoRetDTO> lstConta = consultarContaCorrentePorNumeroCliente(dto);
		
		for (ContaCorrenteIntegracaoRetDTO item:lstConta) {
			if (dto.getNumContaCorrente().equals(item.getNumeroContaCorrente())) {
				return lstConta;
			}
		}
		
		return null;
	}
	
	/**
	 * Consultar conta corrente por numero cliente.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrentePorNumeroCliente(ContaCorrenteIntegracaoDTO dto) throws BancoobException {
		this.getLogger().info("CCA.consultarContaCorrentePorNumeroCliente");
		
		List<ContaCorrenteIntegracaoRetDTO> lstRetorno = new ArrayList<ContaCorrenteIntegracaoRetDTO>();
		
		try{
			ContaCorrenteDelegate contaCorrenteDelegate = ContaCorrenteApiFabricaDelegates.getInstance().criarContaCorrenteDelegate();			
			ContaCorrenteFiltroDTO filtroDto = montarContaCorrenteFiltroDTO(dto);
			List<ContaCorrenteDTO> lstContaCorrenteDTO = contaCorrenteDelegate.listarContasCorrentesPorNumeroCliente(filtroDto);	
			
						
			if (!lstContaCorrenteDTO.isEmpty()){
				for (ContaCorrenteDTO item:lstContaCorrenteDTO){
					ContaCorrenteIntegracaoRetDTO dtoRetorno = new ContaCorrenteIntegracaoRetDTO();
					
					dtoRetorno.setCodSituacaoCCO(item.getCodSituacaoCCO());
					dtoRetorno.setIdContaCorrente(item.getIdContaCorrente());
					dtoRetorno.setDataAbertura(montarDateTime(item.getDataAbertura()));
					dtoRetorno.setDataBloqueio(montarDateTime(item.getDataBloqueio()));
					dtoRetorno.setDataEncerramento(montarDateTime(item.getDataEncerramento()));
					dtoRetorno.setDescricaoSituacaoCCO(item.getDescricaoSituacaoCCO());
					dtoRetorno.setDescTipoContaCor(item.getDescTipoContaCor());
					dtoRetorno.setNumeroContaCorrente(item.getNumeroContaCorrente());
					dtoRetorno.setIdInstituicao(item.getIdInstituicao());
					dtoRetorno.setNumeroCooperativa(item.getNumeroCooperativa());
					dtoRetorno.setIdModalidadeProdutoCCO(item.getIdModalidadeProdutoCCO());
					if (!item.getListaParticipante().isEmpty()){
						dtoRetorno.setLstParticipanteContaCorrenteIntegracaoRetDTO(montarListaParticipanteContaCorrenteIntegracaoRetDTO(item.getListaParticipante()));
					}
					lstRetorno.add(dtoRetorno);
				}
			}

		}catch (ContaCorrenteApiNegocioException e) {
			throw new IntegracaoContaCorrenteNegocioException(ERRO_INTEG_CCO, e.getMessage());			
		}catch (ContaCorrenteApiException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);
		}catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e); 
		}	
		
		return lstRetorno;
	}
	
	/**
	 * Consultar contas correntes ativas por numero cliente.
	 * A conta corrente ativa eh aquela com situacao ATIVA e INATIVA.
	 *
	 * @param dto o valor de dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrenteAtivaPorNumeroCliente(ContaCorrenteIntegracaoDTO dto) throws BancoobException {
		this.getLogger().info("CCA.consultarContaCorrenteAtivaPorNumeroCliente");
		List<ContaCorrenteIntegracaoRetDTO> contas = consultarContaCorrentePorNumeroCliente(dto);
		removerContasCorrenteInativas(contas);
		return contas;
	}
	
	private void removerContasCorrenteInativas(List<ContaCorrenteIntegracaoRetDTO> contas) {
		List<Integer> codsSituacaoAtiva = Arrays.asList(SituacaoContaCorrenteEnum.ATIVA.getCodSituacao(),
				SituacaoContaCorrenteEnum.INATIVA.getCodSituacao());
		if (contas != null) {
			for (Iterator<ContaCorrenteIntegracaoRetDTO> iterator = contas.iterator(); iterator.hasNext();) {
				ContaCorrenteIntegracaoRetDTO item = iterator.next();
				if (!codsSituacaoAtiva.contains(item.getCodSituacaoCCO())){
					iterator.remove();
				}
			}
		}
	}
	
	/**
	 * Montar lista participante conta corrente integracao ret dto.
	 *
	 * @param lstDtoCco o valor de lst dto cco
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ParticipanteContaCorrenteIntegracaoRetDTO> montarListaParticipanteContaCorrenteIntegracaoRetDTO(List<ParticipanteContaDTO> lstDtoCco) throws BancoobException{
		List<ParticipanteContaCorrenteIntegracaoRetDTO> lstDtoCca = new ArrayList<ParticipanteContaCorrenteIntegracaoRetDTO>();
				
		for(ParticipanteContaDTO dtoCco :lstDtoCco){
			ParticipanteContaCorrenteIntegracaoRetDTO dtoCca = new ParticipanteContaCorrenteIntegracaoRetDTO();
			dtoCca.setBolAtivo(dtoCco.getBolAtivo());
			dtoCca.setDescricaoResponsabilidade(dtoCco.getDescricaoResponsabilidade());
			dtoCca.setIdParticipanteConta(dtoCco.getIdParticipanteConta());
			dtoCca.setIdPessoa(dtoCco.getIdPessoa());
			dtoCca.setIdResponsabilidade(dtoCco.getIdResponsabilidade());
			dtoCca.setNomeEmbossamento(dtoCco.getNomeEmbossamento());
			dtoCca.setNumeroCliente(dtoCco.getNumeroCliente());
			dtoCca.setNumeroClienteLegado(dtoCco.getNumeroClienteLegado());
			dtoCca.setNumOrdemTitularidade(dtoCco.getNumOrdemTitularidade());			
			lstDtoCca.add(dtoCca);
		}
		return lstDtoCca;
		
	}
	
	/**
	 * Montar conta corrente filtro dto.
	 *
	 * @param dto o valor de dto
	 * @return ContaCorrenteFiltroDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("deprecation")
	private ContaCorrenteFiltroDTO montarContaCorrenteFiltroDTO(ContaCorrenteIntegracaoDTO dto) throws BancoobException{
		ContaCorrenteFiltroDTO filtroDTO = new ContaCorrenteFiltroDTO();	

		filtroDTO.setAplicativoEnum(dto.getAplicativoEnum());
		filtroDTO.setIdInstituicao(dto.getIdInstituicao());
		filtroDTO.setListaModalidade(Arrays.asList(ModalidadeContaCorrenteEnum.CONTA_CORRENTE_DEPOSITOS_A_VISTA.getValor(),
				ModalidadeContaCorrenteEnum.CENTRALIZACAO_FINANCEIRA.getValor()));
		
		List<Long> listaNumeroCliente = new ArrayList<Long>();
		listaNumeroCliente.add(dto.getIdPessoa().longValue());
		filtroDTO.setListaNumeroCliente(listaNumeroCliente);
		
		if (dto.getNumContaCorrente() != null){
			List<Long> listaNumeroContaCorrente = new ArrayList<Long>();
			listaNumeroContaCorrente.add(dto.getNumContaCorrente());
			filtroDTO.setListaNumeroContaCorrente(listaNumeroContaCorrente);
		}		
		
		return filtroDTO;
	}

	/**
	 * Montar lancamento ret dto.
	 *
	 * @param dtoRetCco o valor de dto ret cco
	 * @return LancamentoContaCorrenteIntegracaoRetDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCorrenteIntegracaoRetDTO montarLancamentoRetDTO(LancamentoIntegracaoRetDTO dtoRetCco) throws BancoobException{
		LancamentoContaCorrenteIntegracaoRetDTO dtoRetCca = new LancamentoContaCorrenteIntegracaoRetDTO();		
		dtoRetCca.setCampoErro(dtoRetCco.getCampoErro());
		dtoRetCca.setCodErroRetorno(dtoRetCco.getCodErroRetorno());
		dtoRetCca.setCodRetorno(dtoRetCco.getCodRetorno());
		dtoRetCca.setMensagem(dtoRetCco.getMensagem());
		dtoRetCca.setNumSeqLanc(dtoRetCco.getNumSeqLanc());				
		return dtoRetCca;
	}
	
	/**
	 * Montar lancamento dto.
	 *
	 * @param dto o valor de dto
	 * @return LancamentoIntegracaoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoIntegracaoDTO montarLancamentoDTO(LancamentoContaCorrenteIntegracaoDTO dto) throws BancoobException{
		LancamentoIntegracaoDTO dtoCco = new LancamentoIntegracaoDTO();
		dtoCco.setBolConsideraLimite(dto.getBolConsideraLimite());
		dtoCco.setBolConsSaldoResgAuto(false); 
		dtoCco.setBolVerificaContaAnt(dto.getBolVerificaContaAnt());
		dtoCco.setBolVerificaSaldo(dto.getBolVerificaSaldo());
		dtoCco.setCodOrigemLote(dto.getCodOrigemLote());
		dtoCco.setDataLote(new Date(dto.getDataLote().getTime()));
		dtoCco.setDescInfComplementar(dto.getDescInfComplementar());
		dtoCco.setDescNumDocumento(dto.getDescNumDocumento());
		dtoCco.setIdAplicativo(dto.getIdAplicativo());
		dtoCco.setIdInstituicao(dto.getIdInstituicao());
		dtoCco.setIdProduto(dto.getIdProduto());
		dtoCco.setIdProdutoEstorno(dto.getIdProdutoEstorno());
		dtoCco.setIdTipoHistoricoEstorno(dto.getIdTipoHistoricoEstorno());
		dtoCco.setIdTipoHistoricoLanc(dto.getIdTipoHistoricoLanc());
		dtoCco.setIdUsuarioResp(dto.getIdUsuarioResp());
		dtoCco.setNumContaCorrente(dto.getNumContaCorrente());
		dtoCco.setNumLoteLanc(dto.getNumLoteLanc());
		dtoCco.setValorLanc(dto.getValorLanc());
		return dtoCco;
	}
	
	/**
	 * Montar saldo dto.
	 *
	 * @param dto o valor de dto
	 * @return SaldoContaCorrenteFiltroDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private SaldoContaCorrenteFiltroDTO montarSaldoDTO(SaldoContaCorrenteIntegracaoDTO dto) throws BancoobException{
		SaldoContaCorrenteFiltroDTO dtoCco = new SaldoContaCorrenteFiltroDTO();
		dtoCco.setAplicativoEnum(AplicativoEnum.COBRANCA_ADMINISTRATIVA);
		dtoCco.setDataReferencia(new Date(dto.getDataReferencia().getTime()));
		dtoCco.setIdAplicativo(dto.getIdAplicativo());
		dtoCco.setIdInstituicao(dto.getIdInstituicao());
		dtoCco.setIdUsuario(dto.getIdUsuario());
		dtoCco.setNumAno(dto.getNumAno());
		dtoCco.setNumClienteCapes(dto.getNumClienteCapes());
		dtoCco.setNumContaCorrente(dto.getNumContaCorrente());
		dtoCco.setNumMes(dto.getNumMes());
		dtoCco.setNumMesesRetro(dto.getNumMesesRetro());
		return dtoCco;
	}	

	/**
	 * Montar saldo ret dto.
	 *
	 * @param dtoRetCco o valor de dto ret cco
	 * @return SaldoContaCorrenteIntegracaoRetDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private SaldoContaCorrenteIntegracaoRetDTO montarSaldoRetDTO(SaldoContaCorrenteDTO dtoRetCco) throws BancoobException{
		SaldoContaCorrenteIntegracaoRetDTO dtoRetCca = new SaldoContaCorrenteIntegracaoRetDTO();		
		dtoRetCca.setSaldoAplicacaoResgate(dtoRetCco.getSaldoAplicacaoResgate());
		dtoRetCca.setValorLimite(dtoRetCco.getValorLimite());
		dtoRetCca.setValorLimiteUtilizado(dtoRetCco.getValorLimiteUtilizado());
		dtoRetCca.setValorSaldoBloqAtual(dtoRetCco.getValorSaldoBloqAtual());
		dtoRetCca.setValorSaldoBloqJudicialAtual(dtoRetCco.getValorSaldoBloqJudicialAtual());
		dtoRetCca.setValorSaldoChequeEspecial(dtoRetCco.getValorSaldoChequeEspecial());
		dtoRetCca.setValorSaldoDispAtual(dtoRetCco.getValorSaldoDispAtual());
		dtoRetCca.setValorSaldoDispAtualSemProvisionamento(dtoRetCco.getValorSaldoDispAtualSemProvisionamento());
		dtoRetCca.setValorSaldoRespIndireta(dtoRetCco.getValorSaldoRespIndireta());
		return dtoRetCca;
	}
	
	/**
	 * 
	 */
	public Boolean validarTitularidade(Integer idPessoa, Integer idInstituicao, Long numContaCorrente) throws BancoobException {
		
		try {
			ContaCorrenteDelegate ccoDelegate = ContaCorrenteApiFabricaDelegates.getInstance().criarContaCorrenteDelegate();
			
			ContaCorrenteFiltroDTO filtro = montarFiltroTitularidade(idPessoa, idInstituicao, numContaCorrente);
			List<ContaCorrenteDTO> lst = ccoDelegate.listarContasCorrentesPorNumeroCliente(filtro);
			
			if(lst != null && !lst.isEmpty()) {
				for (ContaCorrenteDTO dto : lst) {
					if(dto.getListaParticipante() != null && !dto.getListaParticipante().isEmpty()) {
						
						for (ParticipanteContaDTO parDTO : dto.getListaParticipante()) {
							if(parDTO.getNumeroCliente().equals(Long.valueOf(idPessoa))) {
								
								//Primeiro ou segundo titular
								if(parDTO.getIdResponsabilidade().equals(1) || parDTO.getIdResponsabilidade().equals(2)) {
									return true;
								}
							}
						}
					}
				}
			}
			
		} catch (ContaCorrenteApiNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteNegocioException(ERRO_INTEG_CCO, e.getMessage());			
		}catch (ContaCorrenteApiException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);
		}catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e); 
		}	
		
		return false;
	}
	
	/**
	 * Montar filtro titularidade.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param numContaCorrente o valor de num conta corrente
	 * @return ContaCorrenteFiltroDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ContaCorrenteFiltroDTO montarFiltroTitularidade(Integer idPessoa, Integer idInstituicao, Long numContaCorrente) throws BancoobException {
		ContaCorrenteFiltroDTO filtro = new ContaCorrenteFiltroDTO();
		filtro.setIdInstituicao(idInstituicao);
		
		List<Long> lstNumCli = new ArrayList<Long>(0);
		lstNumCli.add(idPessoa.longValue());
		filtro.setListaNumeroCliente(lstNumCli);
		
		List<Long> lstNumCco = new ArrayList<Long>(0);
		lstNumCco.add(numContaCorrente);
		filtro.setListaNumeroContaCorrente(lstNumCco);
		
		return filtro;
	}
	
	/**
	 * Consulta o saldo em conta corrente na data corrente
	 * @param idInstituicao
	 * @param idUsuario
	 * @param numContaCorrente
	 * @return
	 * @throws BancoobException
	 */
	public Boolean isValorIntegralizacaoMaiorSalcoCco(BigDecimal valor, Long idPessoa, Integer idInstituicao, String idUsuario, Long numContaCorrente) throws BancoobException{
			SaldoContaCorrenteIntegracaoDTO saldoDtoCco = montarConsultaSaldoCco(idPessoa,idInstituicao, idUsuario,numContaCorrente);
			BigDecimal salcoCco =  obterSaldoContaCorrente(saldoDtoCco).getValorSaldoDispAtual();
			return valor.compareTo(salcoCco) == 1;
	}	
	
	/**
	 * Monta DTO de consulta de saldo conta corrente
	 * @param idPessoa
	 * @param idInstituicao
	 * @param idUsuario
	 * @param numContaCorrente
	 * @return
	 * @throws BancoobException
	 */
	private SaldoContaCorrenteIntegracaoDTO montarConsultaSaldoCco(Long idPessoa,Integer idInstituicao, String idUsuario, Long numContaCorrente) throws BancoobException {
		Date dataAtual = new Date();
		SaldoContaCorrenteIntegracaoDTO dtoSaldo = new SaldoContaCorrenteIntegracaoDTO();
		dtoSaldo.setDataReferencia(new DateTimeDB(formatarDataSemHora(dataAtual).getTime()));
		dtoSaldo.setIdAplicativo(1);
		dtoSaldo.setIdInstituicao(idInstituicao);
		dtoSaldo.setIdUsuario(idUsuario);
		dtoSaldo.setNumContaCorrente(numContaCorrente);
		dtoSaldo.setNumClienteCapes(idPessoa);
		return dtoSaldo;
	}		

	/**
	 * Recebe a Data no formato Date e converte para DateTime, trata os nulls
	 * @param obj
	 * @return
	 * @throws BancoobException
	 */
	private DateTime montarDateTime(Date obj) throws BancoobException{
		return (obj == null? null:new DateTime(obj.getTime()));
	}
	
	/**
	 * Formata data sem hora
	 * @param data
	 * @return
	 * @throws BancoobException
	 */
	private Date formatarDataSemHora(Date data) throws BancoobException{
		String dataFormatada = null;
		Date dataSaida = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada = formatter.format(data);
			dataSaida =  formatter.parse(dataFormatada);						
		} catch (ParseException e) {
			throw new BancoobException("Erro ao Formatar a Data", e);
		}
		return dataSaida;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean verificarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO ccoDTO) throws BancoobException {
		return !consultarContaCorrenteImpeditivaDesligamento(ccoDTO).isEmpty();
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCorrenteIntegracaoServico#consultarContaCorrenteImpeditivaDesligamento(br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO)
	 */
	public List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrenteImpeditivaDesligamento(ContaCorrenteIntegracaoDTO ccoDTO) throws BancoobException {
		List<ContaCorrenteIntegracaoRetDTO> contasImpeditivas = new ArrayList<ContaCorrenteIntegracaoRetDTO>();
		List<Integer> situacoesCco = Arrays.asList(
				SituacaoContaCorrenteEnum.ATIVA.getCodSituacao(),
				SituacaoContaCorrenteEnum.INATIVA.getCodSituacao(),
				SituacaoContaCorrenteEnum.BLOQUEADA.getCodSituacao());
		List<Integer> modalidadesImpeditivas = Arrays.asList(ModalidadeContaCorrenteEnum.CONTA_CORRENTE_DEPOSITOS_A_VISTA.getValor());
		List<ContaCorrenteIntegracaoRetDTO> lstCco = consultarContaCorrentePorNumeroClienteComSituacoes(ccoDTO, situacoesCco);
		if (lstCco != null) {
			for (ContaCorrenteIntegracaoRetDTO cco : lstCco) {
				if (cco.getDataEncerramento() == null && modalidadesImpeditivas.contains(cco.getIdModalidadeProdutoCCO())) {
					contasImpeditivas.add(cco);
				}
			}
		}
		return contasImpeditivas;
	}

	private List<ContaCorrenteIntegracaoRetDTO> consultarContaCorrentePorNumeroClienteComSituacoes(
			ContaCorrenteIntegracaoDTO ccoDTO, List<Integer> codsSituacaoCco) throws BancoobException {
		this.getLogger().info("CCA.consultarContaCorrentePorNumeroClienteComSituacoes");
		List<ContaCorrenteIntegracaoRetDTO> contas = consultarContaCorrentePorNumeroCliente(ccoDTO);
		if (contas != null) {
			for (Iterator<ContaCorrenteIntegracaoRetDTO> iterator = contas.iterator(); iterator.hasNext();) {
				ContaCorrenteIntegracaoRetDTO item = iterator.next();
				if (!codsSituacaoCco.contains(item.getCodSituacaoCCO())) {
					iterator.remove();
				}
			}
		}
		return contas;
	}
	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ContaCorrenteIntegracaoServico#gravarLancamentosIntegracaoLote(br.com.sicoob.sisbr.cca.integracao.negocio.dto.LancamentoContaCorrenteIntegracaoDTO)
	 */
	public List<LancamentoContaCorrenteIntegracaoLoteRetDTO> gravarLancamentosIntegracaoLote(List<LancamentoContaCorrenteIntegracaoLoteDTO> listLancamentoContaCorrenteIntegracaoLoteDTO, Integer numCooperativa) throws BancoobException {
		this.getLogger().info("CCA.gravarLancamentosIntegracaoLote CCO");
		List<LancamentoContaCorrenteIntegracaoLoteRetDTO> dtoRetCca= null;
		
		try{
			LancamentoIntegracaoDelegate lancamentoIntegracaoDelegate = MovimentacaoApiFabricaDelegates.getInstance().criarLancamentoIntegracaoDelegate();
			List<LancamentoIntegracaoLoteDTO> listaLancamentoIntegracaoLoteDTO = montarDadosListaLancamentoIntegracaoLoteDTO(listLancamentoContaCorrenteIntegracaoLoteDTO);						
			List<LancamentoIntegracaoLoteRetDTO> dtoRetCco = lancamentoIntegracaoDelegate.gravarLancamentoIntegracaoLote(listaLancamentoIntegracaoLoteDTO, numCooperativa);			
			dtoRetCca = montarListLancamentoLoteRetDTO(dtoRetCco);			
		}catch (ContaCorrenteApiNegocioException e) {
			throw new IntegracaoContaCorrenteNegocioException(ERRO_LANCAMENTO_CC, e.getMessage());			
		}catch (ContaCorrenteApiException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e);
		}catch (BancoobRuntimeException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new IntegracaoContaCorrenteException(e); 
		}
		
		return dtoRetCca;
	}	
	
	/**
	 * Monta dados da lista de lancamentos em lote.
	 * @param lstLancamentoContaCorrenteIntegracaoDTO - Passa a lista de lancamentos.
	 * @return - Retorna lista.
	 */
	private List<LancamentoIntegracaoLoteDTO> montarDadosListaLancamentoIntegracaoLoteDTO(List<LancamentoContaCorrenteIntegracaoLoteDTO> lstLancamentoContaCorrenteIntegracaoDTO) throws BancoobException {
		List<LancamentoIntegracaoLoteDTO> listaLancamentos = new ArrayList<LancamentoIntegracaoLoteDTO>();
		
		for (LancamentoContaCorrenteIntegracaoLoteDTO lancamentoContaCorrenteIntegracaoLoteDTO : lstLancamentoContaCorrenteIntegracaoDTO) {
			if(lancamentoContaCorrenteIntegracaoLoteDTO.getNumContaCorrente() != null){
				LancamentoIntegracaoLoteDTO lancamentoIntegracaoLoteDTO = preencheDadosLancamentosEmLote(lancamentoContaCorrenteIntegracaoLoteDTO);
				listaLancamentos.add(lancamentoIntegracaoLoteDTO);
			}
		}		
		
		return listaLancamentos;
	}
	
	/**
	 * Metodo que preenche os dados de lancamentos em lote.
	 * 
	 * @param lancamentoContaCorrenteIntegracaoDTO
	 * @return - Retorna DTO.
	 * @throws BancoobException 
	 */
	private LancamentoIntegracaoLoteDTO preencheDadosLancamentosEmLote(LancamentoContaCorrenteIntegracaoLoteDTO lancamentoContaCorrenteIntegracaoLoteDTO) throws BancoobException {
		LancamentoIntegracaoLoteDTO lancamentoIntegracaoLoteDTO = new LancamentoIntegracaoLoteDTO();
		lancamentoIntegracaoLoteDTO.setDataLancamento(lancamentoContaCorrenteIntegracaoLoteDTO.getDataLancamento());
		lancamentoIntegracaoLoteDTO.setNumLoteLanc(lancamentoContaCorrenteIntegracaoLoteDTO.getNumLoteLanc());
		lancamentoIntegracaoLoteDTO.setDescNumDocumento(lancamentoContaCorrenteIntegracaoLoteDTO.getDescNumDocumento());
		lancamentoIntegracaoLoteDTO.setNumContaCorrente(lancamentoContaCorrenteIntegracaoLoteDTO.getNumContaCorrente());
		lancamentoIntegracaoLoteDTO.setValorLanc(lancamentoContaCorrenteIntegracaoLoteDTO.getValorLanc());
		lancamentoIntegracaoLoteDTO.setIdTipoHistoricoLanc(lancamentoContaCorrenteIntegracaoLoteDTO.getIdTipoHistoricoLanc());
		lancamentoIntegracaoLoteDTO.setBolVerificaSaldo(lancamentoContaCorrenteIntegracaoLoteDTO.getBolVerificaSaldo());	
		lancamentoIntegracaoLoteDTO.setHashIdentificacaoLancamento(lancamentoContaCorrenteIntegracaoLoteDTO.getHashIdentificacaoLancamento());
		return lancamentoIntegracaoLoteDTO;
	}
	
	/**
	 * Montar lancamento ret dto.
	 *
	 * @param dtoRetCco o valor de dto ret cco
	 * @return LancamentoContaCorrenteIntegracaoRetDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private LancamentoContaCorrenteIntegracaoLoteRetDTO montarLancamentoLoteRetDTO(LancamentoIntegracaoLoteRetDTO dtoRetCco) throws BancoobException{
		LancamentoContaCorrenteIntegracaoLoteRetDTO dtoRetCca = new LancamentoContaCorrenteIntegracaoLoteRetDTO();				
		dtoRetCca.setCodErroRetorno(dtoRetCco.getCodErroRetorno());
		dtoRetCca.setCodRetorno(dtoRetCco.getCodRetorno());
		dtoRetCca.setMensagem(dtoRetCco.getMensagem());
		dtoRetCca.setNumSeqLanc(dtoRetCco.getNumSeqLanc());				
		dtoRetCca.setDataLote(dtoRetCco.getDataLote());
		dtoRetCca.setValorSaldoAnteriorLanc(dtoRetCco.getValorSaldoAnteriorLanc());
		dtoRetCca.setHashIdentificacaoLancamento(dtoRetCco.getHashIdentificacaoLancamento());				
		return dtoRetCca;				
	}

	/**
	 * Montar lista de lancamento lote ret dto.
	 *
	 * @param listDtoRetCco o valor de dto ret cco
	 * @return listDtoRetCca
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<LancamentoContaCorrenteIntegracaoLoteRetDTO> montarListLancamentoLoteRetDTO(List<LancamentoIntegracaoLoteRetDTO> listDtoRetCco) throws BancoobException{
		List<LancamentoContaCorrenteIntegracaoLoteRetDTO> listDtoRetCca = new ArrayList<LancamentoContaCorrenteIntegracaoLoteRetDTO>();				
		
		for (LancamentoIntegracaoLoteRetDTO lancamentoIntegracaoLoteRetDTO : listDtoRetCco) {
			listDtoRetCca.add(montarLancamentoLoteRetDTO(lancamentoIntegracaoLoteRetDTO));
		}		
		return listDtoRetCca;				
	}	
	
}