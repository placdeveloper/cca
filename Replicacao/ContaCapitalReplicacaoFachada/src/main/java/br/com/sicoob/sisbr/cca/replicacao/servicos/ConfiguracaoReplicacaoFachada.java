/*
 * 
 */
package br.com.sicoob.sisbr.cca.replicacao.servicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.comum.negocio.delegates.ContaCapitalComumFabricaDelegates;
import br.com.sicoob.cca.comum.negocio.delegates.ViewInstituicaoDelegate;
import br.com.sicoob.cca.comum.negocio.dto.InstituicaoCooperativaSCIDTO;
import br.com.sicoob.cca.comum.util.JsonCapital;
import br.com.sicoob.cca.replicacao.negocio.delegates.ContaCapitalReplicacaoFabricaDelegates;
import br.com.sicoob.cca.replicacao.negocio.delegates.ControleReplicacaoDelegate;
import br.com.sicoob.cca.replicacao.negocio.delegates.ReplicacaoContaCapitalDelegate;
import br.com.sicoob.cca.replicacao.negocio.excecao.ContaCapitalReplicacaoException;
import br.com.sicoob.cca.replicacao.negocio.excecao.ContaCapitalReplicacaoNegocioException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ReplicacaoContaCapitalLegadoDelegate;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConsultaMonitoracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ExpurgoReplicacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorCooperativaReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.MonitorReplicacaoCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.replicacao.ReplicacaoContaCapital;
import br.com.sicoob.sisbr.cca.replicacao.chaveacesso.ControleChaveAcesso;
import br.com.sicoob.sisbr.cca.replicacao.conversor.ConversorConfiguracaoReplicacao;
import br.com.sicoob.sisbr.cca.replicacao.vo.BatimentoSaldoVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.ConfiguracaoReplicacaoVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoCapitalVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoCooperativaCapitalVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.ReplicacaoContaCapitalLegadoVO;

/**
 * Fachada de configuracao da replicacao.
 * @author Nairon.Silva
 */
@RemoteService
public class ConfiguracaoReplicacaoFachada extends ReplicacaoContaCapital{
	
	/**
	 * Construtor 
	 */
	public ConfiguracaoReplicacaoFachada(){
		
	}

	//delegate acesso ao servicos de replicacao do legado
	private ReplicacaoContaCapitalLegadoDelegate replicacaoContaCapitalLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarReplicacaoContaCapitalLegadoDelegate();
	
	private ReplicacaoContaCapitalDelegate replicacaoContaCapitalDelegate = ContaCapitalReplicacaoFabricaDelegates.getInstance().criarReplicacaoContaCapitalDelegate();
	
	private InstituicaoIntegracaoDelegate instituicaoIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	
	private ControleReplicacaoDelegate controleReplicacao = ContaCapitalReplicacaoFabricaDelegates.getInstance().criarControleReplicacaoDelegate();
	
	/**
	 * Obtem as configuracoes de replicacao.
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO req)throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		
		List<ConfiguracaoReplicacaoVO> lstConfiguracaoReplicacao = montarListaConfiguracaoReplicacao();
		
		dto.getDados().put("lstConfiguracaoReplicacao", lstConfiguracaoReplicacao);
		return dto;
	}	
	
	
	private List<ConfiguracaoReplicacaoVO> montarListaConfiguracaoReplicacao() throws BancoobException {				
		
		List<ReplicacaoConfiguracaoLegadoDTO> lstReplicacaoConfiguracaoLegadoDTO = replicacaoContaCapitalLegadoDelegate.consultarListaConfiguracaoReplicacao();	
		List<ConfiguracaoReplicacaoVO> lstConfiguracaoReplicacaoVo = new ArrayList<ConfiguracaoReplicacaoVO>(0);
		
		for (ReplicacaoConfiguracaoLegadoDTO item: lstReplicacaoConfiguracaoLegadoDTO){	
			
			ConfiguracaoReplicacaoVO itemSaida = new ConfiguracaoReplicacaoVO();						
			itemSaida.setIdConfiguracaoReplicacaoCCA(item.getIdConfiguracaoReplicacaoCCA());
			itemSaida.setNomeConfiguracaoReplicacao(item.getNomeConfiguracaoReplicacao());
			itemSaida.setDescConfiguracaoReplicacao(item.getDescConfiguracaoReplicacao());

			lstConfiguracaoReplicacaoVo.add(itemSaida);									
		}
		
		return lstConfiguracaoReplicacaoVo;		
		
	}		
	
	/**
	 * Grava os dados de configuracao de replicacao.
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO gravarDados(RequisicaoReqDTO req)throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		
		Integer idConfiguracaoReplicacaoCCA = Integer.valueOf(req.getDados().get("idConfiguracaoReplicacaoCCA").toString()); 
		String descConfiguracaoReplicacao = req.getDados().get("descConfiguracaoReplicacao").toString();
		
		try{
			ReplicacaoConfiguracaoLegadoDTO replicacaoConfiguracaoLegadoDTO = new ReplicacaoConfiguracaoLegadoDTO();
			replicacaoConfiguracaoLegadoDTO.setIdConfiguracaoReplicacaoCCA(idConfiguracaoReplicacaoCCA);
			replicacaoConfiguracaoLegadoDTO.setDescConfiguracaoReplicacao(descConfiguracaoReplicacao);
			
			replicacaoContaCapitalLegadoDelegate.alterarConfiguracaoReplicacaoCCA(replicacaoConfiguracaoLegadoDTO);
			
			return dto;			
		
		}catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new ContaCapitalReplicacaoException("MSG_ERRO_GRAVAR_DADOS",e);
		}			
	}
	
	/**
	 * Consulta a cooperativa piloto.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarCooperativaPiloto(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO dto = new RetornoDTO();
		
		Integer cooperativa = Integer.valueOf(req.getDados().get("cooperativa").toString());
		Integer idInstituicao = instituicaoIntegracaoDelegate.obterIdInstituicao(cooperativa);
		try {
			CentralCooperativaDTO central = instituicaoIntegracaoDelegate.consultarCentralCooperativa(idInstituicao);
			dto.getDados().put("central", central.getNumCentral() + " - " + central.getDescCentral());
		} catch (NegocioException ne) {
			dto.getDados().put("central", "Central inativa");
		}
		dto.getDados().put("consulta", replicacaoContaCapitalDelegate.consultarCooperativaPiloto(cooperativa));
		
		return dto;
	}
	
	/**
	 * Prepara a cooperativa piloto.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO prepararCooperativaPiloto(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO dto = new RetornoDTO();
		
		Integer cooperativa = Integer.valueOf(req.getDados().get("cooperativa").toString());
		replicacaoContaCapitalDelegate.prepararCooperativaPiloto(cooperativa);
		
		return dto;
	}
	
	/**
	 * Emite relatorio batimentos sql x db2.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorioBatimentoSQLxDB2(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String coopParam = req.getDados().get("cooperativa").toString();
		List<Integer> cooperativas = null;
		if (coopParam.length() > 0) {
			cooperativas = new ArrayList<Integer>();
			String[] coops = coopParam.split(",");
			for (String coop : coops) {
				cooperativas.add(Integer.valueOf(coop));	
			}
		}
		ContextoHttp.getInstance().adicionarContexto("RelBatimentoSQLxDB2", replicacaoContaCapitalDelegate.gerarRelatorioBatimentoSQLxDB2(cooperativas));
		return retorno;
	}
	
	/**
	 * Consulta as cooperativas para execucao da conciliacao.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarCooperativasConciliacao(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("cooperativas", replicacaoContaCapitalLegadoDelegate.consultarCooperativasConciliacao());
		return retorno;
	}
	
	/**
	 * Consulta as cooperativas para batimento.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarCooperativasBatimento(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("cooperativas", replicacaoContaCapitalLegadoDelegate.consultarCooperativasPiloto());
		return retorno;
	}
	
	/**
	 * Verifica se a cooperativa possui divergencias para o relatorio final.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO verificarCooperativaBatimento(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Integer cooperativa = Integer.valueOf(req.getDados().get("cooperativa").toString());
		if (replicacaoContaCapitalDelegate.verificarCooperativaComDivergenciasBatimentos(cooperativa)) {
			retorno.getDados().put("cooperativa", cooperativa);
		}
		return retorno;
	}
	
	/**
	 * Executa a conciliacao para geracao de script.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO executarConciliacao(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		String coopParam = req.getDados().get("cooperativa").toString();
		Integer cooperativa = null;
		if (coopParam != null && coopParam.length() > 0) {
			cooperativa = Integer.valueOf(req.getDados().get("cooperativa").toString());	
		}
		
		Map<String, Object> map = replicacaoContaCapitalLegadoDelegate.executarConciliacao(cooperativa);
		ConversorConfiguracaoReplicacao conversor = new ConversorConfiguracaoReplicacao();
		conversor.transformarDTOConciliacaoParaVO(map, "lancamentosNaoAtualizados");
		conversor.transformarDTOConciliacaoParaVO(map, "parcelaSemSubscricao");
		conversor.transformarDTOConciliacaoParaVO(map, "subscricaoSemParcela");
		conversor.transformarDTOConciliacaoParaVO(map, "duplicidade");
		conversor.transformarDTOConciliacaoParaVO(map, "erros");
		conversor.transformarDTOConciliacaoParaVO(map, "capaLote");
		conversor.transformarDTOConciliacaoParaVO(map, "saldosNegativos");
		conversor.transformarDTOConciliacaoParaVO(map, "parcDevolSemSaldo");
		conversor.transformarDTOConciliacaoParaVO(map, "devolSemParcParaAtivos");
		retorno.getDados().put("conciliacao", map);
		
		return retorno;
	}
	
	/**
	 * Recupera o template do script de conciliacao.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO recuperarScriptConciliacao(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("script", recuperarConteudoArquivo("script-conciliacao.txt"));
		return retorno;
	}
	
	/**
	 * Recupera o template do script de conciliacao para capa lote.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO recuperarScriptConciliacaoCapaLote(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("script", recuperarConteudoArquivo("script-capalote.txt"));
		return retorno;
	}
	
	/**
	 * Recupera o conteudo de um arquivo no pacote .../arquivo/
	 * @param arquivo
	 * @return
	 * @throws ContaCapitalReplicacaoException
	 */
	private String recuperarConteudoArquivo(String arquivo) throws ContaCapitalReplicacaoException {
		final String caminho = "br/com/sicoob/sisbr/cca/replicacao/arquivo/"+arquivo;
		StringBuilder sb = new StringBuilder();
		URL resource = Thread.currentThread().getContextClassLoader().getResource(caminho);
	    String line = null;
	    try {
	    	BufferedReader in = new BufferedReader(new InputStreamReader(resource.openStream(), "UTF-8"));
	    	while ((line = in.readLine()) != null) {
	    		sb.append(line).append("\n");
	    	}
			in.close();
		} catch (IOException e) {
			throw new ContaCapitalReplicacaoException("Erro ao recuperar conteudo de arquivo");
		}
		return sb.toString();
	}

	/**
	 * Consulta as cooperativas para expurgo.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarExpurgo(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		String coopParam = req.getDados().get("cooperativa").toString();
		Integer cooperativa = null;
		if (coopParam != null && coopParam.length() > 0) {
			cooperativa = Integer.valueOf(req.getDados().get("cooperativa").toString());	
		}
		List<ExpurgoReplicacaoDTO> dtos = replicacaoContaCapitalLegadoDelegate.consultarExpurgo(cooperativa);
		retorno.getDados().put("vos", new ConversorConfiguracaoReplicacao().transformarDTOExpurgoParaVO(dtos));
		
		return retorno;
	}
	
	/**
	 * Expurga os dados de replicacoa de uma cooperativa.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO expurgarCooperativa(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		Integer cooperativa = Integer.valueOf(req.getDados().get("cooperativa").toString());
		replicacaoContaCapitalLegadoDelegate.expurgarCooperativaReplicacao(cooperativa);
		
		return retorno;
	}

	/**
	 * Gera a chave de acesso, enviando-a por e-mail.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO gerarChaveAcesso(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		ControleChaveAcesso controleChaveAcesso = new ControleChaveAcesso();
		controleChaveAcesso.gerarChaveAcesso();
		return retorno;
	}
	
	/**
	 * Valida a chave de acesso.
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO validarChaveAcesso(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String chave = req.getDados().get("chave").toString();
		ControleChaveAcesso controleChaveAcesso = new ControleChaveAcesso();
		retorno.getDados().put("valido", controleChaveAcesso.isChaveValida(chave));
		return retorno;
	}

	/**
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterCentrais(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		InstituicaoIntegracaoDelegate instituicaoDelegate  = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
		List<ItemListaIntegracaoDTO> listaCentral = instituicaoDelegate.listarCentral();
		listaCentral.add(new ItemListaIntegracaoDTO("", "-- TODAS --"));
		retorno.getDados().put("listaCentral", montarListaComboCentral(listaCentral));
		return retorno;
	}
	
	/**
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterSingularesPP(RequisicaoReqDTO dto) throws BancoobException {
		return obterSingulares(dto);
	}
	
	/**
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterSingularesRel(RequisicaoReqDTO dto) throws BancoobException {
		return obterSingulares(dto);
	}
	
	/**
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterSingulares(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		ViewInstituicaoDelegate viewInstituicaoDelegate = ContaCapitalComumFabricaDelegates.getInstance().criarViewInstituicaoDelegate();
		String numCoopPaiParam = dto.getDados().get("numCoopPai").toString();
		Integer numCoopPai = null;
		List<ItemListaVO> listaVO = new ArrayList<ItemListaVO>();
		if (numCoopPaiParam.length() > 0) {
			listaVO.add(new ItemListaVO(numCoopPaiParam, dto.getDados().get("descCoopPai").toString()));
			numCoopPai = Integer.valueOf(numCoopPaiParam);
		}
		List<InstituicaoCooperativaSCIDTO> listaSingular = viewInstituicaoDelegate.consultarCooperativasAtivas(numCoopPai);
		listaVO.addAll(montarListaComboSingular(listaSingular));
		retorno.getDados().put("listaSingular", listaVO);
		return retorno;
	}
	
	private List<ItemListaVO> montarListaComboCentral(List<ItemListaIntegracaoDTO> listaDTO) throws BancoobException {		
		List<ItemListaVO> listaVO = new ArrayList<ItemListaVO>();		
		for(ItemListaIntegracaoDTO itemListaIntegracaoDTO : listaDTO){
			ItemListaVO item = new ItemListaVO(itemListaIntegracaoDTO.getCodListaItem(), itemListaIntegracaoDTO.getDescListaItem());
			listaVO.add(item);
		}		
		Collections.sort(listaVO);
		return listaVO;
	}
	
	private List<ItemListaVO> montarListaComboSingular(List<InstituicaoCooperativaSCIDTO> listaDTO) throws BancoobException {		
		List<ItemListaVO> listaVO = new ArrayList<ItemListaVO>();		
		for(InstituicaoCooperativaSCIDTO dto : listaDTO){
			ItemListaVO item = new ItemListaVO(dto.getNumCooperativa().toString(), dto.getNumCooperativa() + " - " + dto.getNome());
			listaVO.add(item);
		}		
		Collections.sort(listaVO);
		return listaVO;
	}
	
	/**
	 * Consulta a monitoracao
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarMonitoracao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		ConsultaMonitoracaoDTO consultaDTO = extrairConsultaMonitoracaoDTO(dto);
		
		if (consultaDTO.possuiVisaoGeral()) {
			retorno.getDados().put("lstMonitor", converterParaVo(replicacaoContaCapitalLegadoDelegate.consultarMonitoracaoReplicacao(consultaDTO)));
		}
		if (consultaDTO.possuiVisaoCooperativas()) {
			retorno.getDados().put("lstMonitorCoop", converterParaVoCooperativas(replicacaoContaCapitalLegadoDelegate.consultarMonitoracaoCooperativasReplicacao(consultaDTO)));
		}
		
		return retorno;
	}
	
	private ConsultaMonitoracaoDTO extrairConsultaMonitoracaoDTO(RequisicaoReqDTO req) {
		ConsultaMonitoracaoDTO dto = new ConsultaMonitoracaoDTO();
		if(req.getDados().get("numCoop") != null) {
			dto.setNumCooperativa(Integer.valueOf(req.getDados().get("numCoop").toString()));
		}
		if (req.getDados().get("apenasPilotos") != null) {
			dto.setApenasPilotos((Boolean) req.getDados().get("apenasPilotos"));
		}
		if (req.getDados().get("visao") != null) {
			dto.setVisao(req.getDados().get("visao").toString());
		}
		if (req.getDados().get("situacao") != null) {
			dto.setSituacao(req.getDados().get("situacao").toString());
		}
		if (req.getDados().get("data") != null) {
			dto.setData((Date) req.getDados().get("data"));
		}
		if (req.getDados().get("periodo") != null) {
			Object[] periodo = (Object[]) req.getDados().get("periodo");
			dto.setPeriodoDe((Date) periodo[0]);
			dto.setPeriodoAte((Date) periodo[1]);
		}
		if (req.getDados().get("dataCadastro") != null) {
			dto.setDataCadastro(req.getDados().get("dataCadastro").toString());
		}
		if (req.getDados().get("dataReplicacao") != null) {
			dto.setDataReplicacao(req.getDados().get("dataReplicacao").toString());
		}
		if (req.getDados().get("tabela") != null) {
			dto.setTabela(req.getDados().get("tabela").toString());
		}
		if (req.getDados().get("acao") != null) {
			dto.setAcao(req.getDados().get("acao").toString());
		}
		return dto;
	}


	private List<MonitoracaoCapitalVO> converterParaVo(List<MonitorReplicacaoCapitalLegadoDTO> lst) {
		List<MonitoracaoCapitalVO> lstRetorno = new ArrayList<MonitoracaoCapitalVO>();
		for(MonitorReplicacaoCapitalLegadoDTO dto : lst) {
			lstRetorno.add(new MonitoracaoCapitalVO(dto));
		}
		return lstRetorno;
	}
	
	private List<MonitoracaoCooperativaCapitalVO> converterParaVoCooperativas(List<MonitorCooperativaReplicacaoCapitalLegadoDTO> lst) {
		List<MonitoracaoCooperativaCapitalVO> lstRetorno = new ArrayList<MonitoracaoCooperativaCapitalVO>();
		for(MonitorCooperativaReplicacaoCapitalLegadoDTO dto : lst) {
			lstRetorno.add(new MonitoracaoCooperativaCapitalVO(dto));
		}
		return lstRetorno;
	}
	
	private List<ReplicacaoContaCapitalLegadoVO> converterParaVoErros(List<ReplicacaoContaCapitalLegadoDTO> lst) throws BancoobException {
		List<ReplicacaoContaCapitalLegadoVO> lstRetorno = new ArrayList<ReplicacaoContaCapitalLegadoVO>();
		
		JsonCapital jsonCapital = new JsonCapital();
		
		ReplicacaoContaCapitalLegadoVO vo;
		for(ReplicacaoContaCapitalLegadoDTO dto : lst) {
			
			if (dto.getDescChaveReplicacaoSQL() == null) {
				vo = new ReplicacaoContaCapitalLegadoVO();
			} else {
				vo = jsonCapital.converterJSon(dto.getDescChaveReplicacaoSQL(), ReplicacaoContaCapitalLegadoVO.class);
			}
			
			vo.setIdReplicacaoCCA(dto.getIdReplicacaoCCA());
			vo.setIdTabelaReplicadaCCA(dto.getIdTabelaReplicadaCCA());
			vo.setIdSituacaoReplicacaoCCA(dto.getIdSituacaoReplicacaoCCA());
			vo.setCodAcao(dto.getCodAcao());
			vo.setDescChaveReplicacaoSQL(dto.getDescChaveReplicacaoSQL());
			vo.setDataHoraCadastro(dto.getDataHoraCadastro());
			vo.setDataHoraReplicacao(dto.getDataHoraReplicacao());
			vo.setNumCooperativa(dto.getNumCooperativa());
			vo.setIdInstituicao(dto.getIdInstituicao());
			vo.setDescMensagemReplicacao(dto.getDescMensagemReplicacao());
			
			if(dto.getDescChaveReplicacaoDB2() != null) {
				vo.setDescChaveReplicacaoDB2(dto.getDescChaveReplicacaoDB2());
			}
			
			lstRetorno.add(vo);
		}
		
		return lstRetorno;
	}

	/**
	 * Salva alteracao JSON
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO salvarJSON(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Integer idReplicacaoCCA = Integer.valueOf(req.getDados().get("idReplicacaoCCA").toString());
		String json = req.getDados().get("json").toString();
		validarJSON(json);
		replicacaoContaCapitalLegadoDelegate.alterarJSONReplicacao(idReplicacaoCCA, json);
		return retorno;
	}

	private void validarJSON(String json) throws BancoobException {
		JsonCapital jsonCapital = new JsonCapital();
		jsonCapital.converterJSon(json, ReplicacaoContaCapitalLegadoVO.class);
	}
	
	/**
	 * Consulta a tabela de acoes
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarAcoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String tipo = dto.getDados().get("tipo").toString();
		
		if ("3".equals(tipo)) { // 3 - busca por ids
			Long idInicial = Long.valueOf(dto.getDados().get("idInicial").toString());
			Long idFinal = Long.valueOf(dto.getDados().get("idFinal").toString());
			retorno.getDados().put("lst", converterParaVoErros(replicacaoContaCapitalLegadoDelegate.consultarReplicacaoPorIds(idInicial, idFinal)));
		} else {
			ConsultaMonitoracaoDTO consultaDTO = extrairConsultaMonitoracaoDTO(dto);
			if ("2".equals(tipo)) { // 2 - busca por erros e invalidos
				consultaDTO.setSituacao("2,4");
			} else if ("4".equals(tipo)) { // 4 - busca por sucesso
				consultaDTO.setSituacao("1");
			}  
			if (dto.getDados().get("qtdRegistros") != null) {
				consultaDTO.setQtdRegistros(Integer.valueOf(dto.getDados().get("qtdRegistros").toString()));
			} 
			retorno.getDados().put("lst", converterParaVoErros(replicacaoContaCapitalLegadoDelegate.consultarErrosReplicacao(consultaDTO)));
		}
		
		return retorno;
	}
	
	/**
	 * Consulta os batimentos de saldo
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO consultarBatimentoSaldos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		List<BatimentoSaldoVO> dtos = new ArrayList<BatimentoSaldoVO>();
		ConversorConfiguracaoReplicacao conversor = new ConversorConfiguracaoReplicacao();
		if (dto.getDados().get("cooperativa") != null) {
			Integer cooperativa = Integer.valueOf(dto.getDados().get("cooperativa").toString());
			dtos.addAll(conversor.transformarBatimentoSaldoDTOparaVO(replicacaoContaCapitalDelegate.consultarBatimentoSaldos(cooperativa)));
		}
		retorno.getDados().put("lista", dtos);
		return retorno;
	}
	
	/**
	 * Inicia a replicacao forcada
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO iniciarReplicacao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		if (!replicacaoContaCapitalLegadoDelegate.consultarExecucaoLiberada()) {
			throw new ContaCapitalReplicacaoNegocioException("Replicação bloqueada."); 
		}
		replicacaoContaCapitalLegadoDelegate.liberarBloquearExecucao(1);
		controleReplicacao.iniciarReplicacao();
		replicacaoContaCapitalLegadoDelegate.liberarBloquearExecucao(0);
		return retorno;
	}
	
}
