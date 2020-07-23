package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.contacapital.comum.negocio.entidades.ValorCotas;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.mensagens.AtendimentoBOResourceBundle;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ReativarCooperadoDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ValorCotasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosParcelamentoCCAProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.ParcelaVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioParcelamentoCCAVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class ReativarCooperadoServico extends AtendimentoFachada {

	private static final String KEY_DATA_INCLUSAO = "dataInclusao";
	private static final String KEY_FLAG_INICIALIZACAO = "flagInicializacao";
	private static final String KEY_MSG_INICIALIZACAO = "msgInicializacao";
	private static final String KEY_MATRICULA = "numMatricula";
	private static final String KEY_GERAR_MATRICULA = "gerarMatricula";
	private static final String KEY_TIPO_OPERACAO = "tipoOperacao";
	private static final String KEY_ALTERA_MATRICULA = "alteraMatricula";
	private static final String KEY_REATIVA_MATRICULA = "reativaNovaMatricula";
	private Integer tipoOperacao = ContaCapitalConstantes.COD_REATIVAR_COOPERADO;
	
	private ValorCotasDelegate valorCotasDelegate = AtendimentoFabricaDelegates
			.getInstance().criarValorCotasDelegate();
	private ContaCapitalDelegate contaCapitalDelegate =  AtendimentoFabricaDelegates
			.getInstance().criarContaCapitalDelegate();
	private ParcelamentoCCADelegate parcelamentoCCADelegate = AtendimentoFabricaDelegates.getInstance().criarParcelamentoCCADelegate();

	private FechamentoContaCapitalDelegate fechamentoDelegate = AtendimentoFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate(); 
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {		
		RetornoDTO retornoDTO = new RetornoDTO();
		if(validarInicializacao(retornoDTO)){
			retornoDTO.getDados().put(KEY_DATA_INCLUSAO,obterDataAtualProduto() );
			Integer matricula = contaCapitalDelegate.obterNumeroContaCapital();
			Boolean gerarMatricula=false;
			if(!matricula.equals(Integer.valueOf(-1))){
				gerarMatricula=true;
			}
			retornoDTO.getDados().put(KEY_GERAR_MATRICULA,gerarMatricula);
			retornoDTO.getDados().put(KEY_MATRICULA,matricula);
			retornoDTO.getDados().put(KEY_TIPO_OPERACAO,tipoOperacao);
			String alteraMatricula = obterParametro(ContaCapitalConstantes.PAR_PERMITE_ALTERAR_MATRICULA);
			String reativaNovaMatricula = obterParametro(ContaCapitalConstantes.PAR_CCA_REATIVA_COOP_NOVA_MATRICULA);
			if(alteraMatricula!=null){
				if(alteraMatricula.equals("1")){
					retornoDTO.getDados().put(KEY_ALTERA_MATRICULA,true);
				}
				else{
					retornoDTO.getDados().put(KEY_ALTERA_MATRICULA,false);
				}
			}				
			if(reativaNovaMatricula!=null){
				if(reativaNovaMatricula.equals("1")){
					retornoDTO.getDados().put(KEY_REATIVA_MATRICULA,true);
				}
				else{
					retornoDTO.getDados().put(KEY_REATIVA_MATRICULA,false);
				}
			}	  
		}
		retornoDTO.getDados().put("listaModoInteg", preencherComboModoInteg(ContaCapitalConstantes.LST_FORMA_DEBITO_INTEGRALIZACAO, tipoOperacao));
		return retornoDTO;
	}

	
	private List<ItemListaVO> preencherComboModoInteg(Integer parametro, Integer tipoOperacao ) throws BancoobException {

		List<ItemListaVO> itemLista = obterLista(parametro);	
		
		if(tipoOperacao.equals(ContaCapitalConstantes.COD_INCLUIR_COOPERADO) || 
				tipoOperacao.equals(ContaCapitalConstantes.COD_REATIVAR_COOPERADO)){
			for (int i=0;i< itemLista.size();i++) {
				ItemListaVO itemListaVO  = itemLista.get(i);
				if(itemListaVO.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RATEIO.toString())
						|| itemListaVO.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RESERVA.toString())
						|| itemListaVO.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_COBRANCA.toString())){
					itemLista.remove(itemListaVO);
				}
				else{
					if(itemListaVO.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.toString()) && 
							obterNumPai()==0){
						itemLista.remove(itemListaVO);
					}
				}
			}			
		}
		else if(tipoOperacao.equals(ContaCapitalConstantes.COD_NOVA_SUBSCRICAO)){
			for (ItemListaVO itemListaVO : itemLista) {
				if(itemListaVO.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_COBRANCA.toString())){
					itemLista.remove(itemListaVO);
				}
				else if(itemListaVO.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.toString()) && 
							obterNumPai()==0){
						itemLista.remove(itemListaVO);
					}
				}
			}
		return itemLista;		
	}
	
	private Boolean validarInicializacao(RetornoDTO retorno) throws BancoobException{
		
		if(fechamentoDelegate.verificarFechCapitalIniciado()){
			retorno.getDados().put(KEY_FLAG_INICIALIZACAO, false);
			retorno.getDados().put(KEY_MSG_INICIALIZACAO
					, AtendimentoBOResourceBundle.getInstance().getString(
							"msg.erro.verificaFechamentoCapitalIniciado"));
			return false;
		}
		
		List<ValorCotas> listaValorCotas = valorCotasDelegate.listar();
		if( listaValorCotas == null || listaValorCotas.isEmpty()){
			retorno.getDados().put(KEY_FLAG_INICIALIZACAO, false);
			retorno.getDados().put(KEY_MSG_INICIALIZACAO
					, MensagemUtil.getString("msg.erro.informeValorCota"));
			return false;		
		}		
		retorno.getDados().put(KEY_FLAG_INICIALIZACAO, true);
		return true;		
	}


	public RetornoDTO obterQuantidadeCotas(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();		
		DadosContaCapitalProxy  dadosContaCapital =  obterVO(dto);
		Integer quantidadeCotas = valorCotasDelegate.obterQuantidadeCotas(dadosContaCapital.getValorSubscricao());
		retornoDTO.getDados().put("quantidadeCotas", quantidadeCotas);
		return retornoDTO;
	}
	
	private DadosContaCapitalProxy obterVO(RequisicaoReqDTO dto) {
		DadosContaCapitalProxy vo = (DadosContaCapitalProxy) dto.getDados().get("dadosContaCapital");
		return vo;
	}
	
	public RetornoDTO obterListaTrabalhaPorMatricula(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		DadosContaCapitalProxy  dadosContaCapital =  obterVO(dto);
		
		List<DadosContaCapitalProxy> listaMatricula = contaCapitalDelegate.obterListaTrabalhaPorMatricula(dadosContaCapital.getNumCliente().intValue());//23612,36480
		retornoDTO.getDados().put("listaMatricula", listaMatricula);
		return retornoDTO;
	}


	public RetornoDTO atualizarRegistro(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		DadosContaCapitalProxy  vo =  obterVO(dto);	
		List<ParcelaVO> parcelas = obterParcelasVO(dto);
		if(parcelas == null || parcelas.size() == 0){
			parcelas = gerarParcelas(vo);
		}		
		Boolean atualizar=false;
		try {
			atualizar = contaCapitalDelegate.gravarCooperado(vo, parcelas);
		} catch (Exception e) {
			if(e.getMessage().contains("Saldo em conta corrente insuficiente")){
				retornoDTO.getDados().put("bolIntegralizacao", true);
			} else {
				throw new AtendimentoCadastroException(e.getMessage(), e);
			}
		}
		
		retornoDTO.getDados().put("atualizar", atualizar);
		return retornoDTO;
	}
	
	public Boolean validarTotalSubscritoIntegralizado(DadosContaCapitalProxy vo) throws BancoobException{		
		BigDecimal valorIntegralizar = contaCapitalDelegate.recuperarValorIntegralizar(vo.getValorSubscricao(), vo.getValorAVista());
		if(valorIntegralizar.compareTo(new BigDecimal(0.0))==0){
			return true;
		}
		return false;
	}


	public RetornoDTO validar(RequisicaoReqDTO dto) throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		DadosContaCapitalProxy  dadosContaCapital =  obterVO(dto);
		contaCapitalDelegate.validarCamposCadastro(dadosContaCapital);
		retornoDTO.getDados().put("validarTotalSubscritoIntegralizado", validarTotalSubscritoIntegralizado(dadosContaCapital));
		return retornoDTO;
	}
	
	private List<ParcelaVO> gerarParcelas(
			DadosContaCapitalProxy vo) throws BancoobException {
		DadosParcelamentoCCAProxy proxy = montarProxyParcelamento(vo);		
		return parcelamentoCCADelegate.gerarParcelas(proxy);
	}
	private DadosParcelamentoCCAProxy montarProxyParcelamento(DadosContaCapitalProxy vo){
		DadosParcelamentoCCAProxy proxy = new DadosParcelamentoCCAProxy();
		proxy.setValorTotal(vo.getValorSubscricao());
		proxy.setValorAvista(vo.getValorAVista());
		proxy.setDataMatricula(vo.getDataMatricula());
		proxy.setNumContaCorrente(vo.getNumContaCorrente());
		proxy.setNumParcela(Integer.valueOf(0));
		proxy.setCodTipoParcelamento(ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL);
		proxy.setCodModoLanc(Integer.valueOf(vo.getModoInteg()));
		proxy.setuIDTrabalha(vo.getUidTrabalha());
		proxy.setQuantParcelas(0);
		proxy.setMatriculaTrabalha(vo.getDescMatriculaFunc());
		return proxy;
		
	}
	public RetornoDTO obterDefinicoesParcSel(RequisicaoReqDTO dto)
			throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		
		List<ItemListaVO> lista = obterListaModoInteg();
		retorno.getDados().put("listaFormaPagamento", lista);
		
		DadosContaCapitalProxy vo= (DadosContaCapitalProxy)dto.getDados().get("dadosContaCapital");	
		List<ParcelaVO> parcelas = gerarParcelas(vo);		
		retorno.getDados().put("parcelas", parcelas);		
		return retorno;
	}
	
	private List<ItemListaVO> obterListaModoInteg() throws BancoobException{
		
		List<ItemListaVO> retorno = new ArrayList<ItemListaVO>();
		
		List<ItemListaVO> lista = obterLista(
				ContaCapitalConstantes.LST_FORMA_DEBITO_INTEGRALIZACAO);
		
		Iterator<ItemListaVO> it = lista.iterator();
		while (it.hasNext()) {
			ItemListaVO itemVO = (ItemListaVO) it.next();
			
			Integer cod = Integer.valueOf(itemVO.getCodigoItem());
			Integer numPai = obterNumPai();
			if(	!cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_COBRANCA) &&
					!cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RATEIO) &&
					!cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RESERVA) &&
					!(cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA) 
							&& numPai ==0 )){
				retorno.add(itemVO);
			}
		}		
		
		return retorno;
	}
	
	public RetornoDTO gerarMaisParcelas(RequisicaoReqDTO dto)
			throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();		
		DadosContaCapitalProxy  vo =  obterVO(dto);
		ParcelaVO pvo = obterParcelaVO(dto);		
		List<ParcelaVO> parcelas = gerarMaisParcelas(vo, pvo);		
		retorno.getDados().put("parcelas", parcelas);		
		return retorno;		
	}
	
	private ParcelaVO obterParcelaVO(RequisicaoReqDTO dto) {
		ParcelaVO vo = (ParcelaVO) dto.getDados().get("parcelaVO");
		return vo;
	}
	
	private List<ParcelaVO> gerarMaisParcelas(
			DadosContaCapitalProxy vo, ParcelaVO pvo) throws BancoobException {
		
		DadosParcelamentoCCAProxy proxy = new DadosParcelamentoCCAProxy();
		proxy.setValorTotal(vo.getValorSubscricao().subtract(vo.getValorAVista()));
		proxy.setDataMatricula(vo.getDataMatricula());
		proxy.setNumContaCorrente(pvo.getNumContaCorrente());
		proxy.setCodTipoParcelamento(
				ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL);
		proxy.setCodModoLanc(pvo.getCodModoLanc());
		proxy.setQuantParcelas(pvo.getQtdParcelas());
		
		if(StringUtils.isNotBlank(pvo.getUidTrabalha()) && StringUtils.isNotBlank(pvo.getMatriculaTrabalha())) {
			proxy.setuIDTrabalha(pvo.getUidTrabalha());
			proxy.setMatriculaTrabalha(pvo.getMatriculaTrabalha());
		} else {
			proxy.setuIDTrabalha(vo.getUidTrabalha());
			proxy.setMatriculaTrabalha(vo.getDescMatriculaFunc());
		}
		
		return parcelamentoCCADelegate.gerarMaisParcelas(proxy);
	}
	
	public RetornoDTO atualizarParcela(RequisicaoReqDTO dto)
			throws BancoobException{
		
		RetornoDTO retorno = new RetornoDTO();		
		retorno.getDados().put("flag", true);		
		return retorno;		
	}
	
	private List<ParcelaVO> obterParcelasVO(RequisicaoReqDTO dto) {
		List<ParcelaVO> vos = (List<ParcelaVO>) dto.getDados().get("listaParcelasVO");			
		return vos;
	}
	
	public RetornoDTO validarDataVencParcela(RequisicaoReqDTO dto) throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();		
		DateTime dtVenc = (DateTime) dto.getDados().get("dataVencimento");
		retorno.getDados().put("stDataVencimento", validarDataVencimentoParcela(dtVenc));
		retorno.getDados().put("codTipoParcelamento", 
				ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL);
		return retorno;		
	}
	
	private Boolean validarDataVencimentoParcela(
			DateTime dtVenc) throws BancoobException{
		
		boolean stDiaUtil = DataUtil.isDiaUtil(
				dtVenc, Integer.valueOf(obterCooperativa()));
		
		if( !stDiaUtil || dtVenc.compareTo(obterDataAtualProduto()) <= 0){
			return false;
		}
		
		return true;
	}
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		
		SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.emitirRelatorio...");
		
		//testar com a matricula 5874 para gerar relatorio parcelamentocca
		RetornoDTO retornoDTO = new RetornoDTO();
		DadosContaCapitalProxy  dados = (DadosContaCapitalProxy)dto.getDados().get("dadosContaCapital");
		
		
		SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.emitirRelatorio dadosContaCapital: " + dados);
		
		Object relatorio = null;
		
		if(dados.getModoInteg().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.toString())){
			relatorio = gerarRelatorioParcelamentoCCA(dados);
		}
		if(dados.getModoInteg().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA.toString())){
			relatorio = gerarRelatorioReciboDebitoCCO(dados);
		}
		
		SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.emitirRelatorio relatorio: " + relatorio);
		
		ContextoHttp.getInstance().adicionarContexto(
				"Relatorio",
				relatorio);				
		retornoDTO.getDados().put("Relatorio", "Relatorio");
				
		return retornoDTO;
	}

	private Object gerarRelatorioParcelamentoCCA(DadosContaCapitalProxy  dados) throws BancoobException{
		SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.gerarRelatorioParcelamentoCCA...");
		
		RelatorioParcelamentoCCAVO vo = new RelatorioParcelamentoCCAVO();
        vo.setDtInicial(dados.getDataMatricula());
        vo.setDtFinal(dados.getDataMatricula());  
        
        if (obterParametro(ContaCapitalConstantes.PAR_CCA_REATIVA_COOP_NOVA_MATRICULA).equals("1")){
        	SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.gerarRelatorioParcelamentoCCA  PAR_PERMITE_ALTERAR_MATRICULA = 1");
            vo.setNumMatriculaInicial(dados.getNumMatricula());
            vo.setNumMatriculaFinal(dados.getNumMatricula());        	
            SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.gerarRelatorioParcelamentoCCA NumMatriculaControle:" + dados.getNumMatricula().longValue());
        }else{
        	SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.gerarRelatorioParcelamentoCCA  PAR_PERMITE_ALTERAR_MATRICULA != 1");
        	SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.gerarRelatorioParcelamentoCCA NumMatriculaControle:" + dados.getNumMatriculaControle().longValue());
            vo.setNumMatriculaInicial(dados.getNumMatriculaControle().longValue());
            vo.setNumMatriculaFinal(dados.getNumMatriculaControle().longValue());        	
        }        
        
        vo.setTpParcelamento(ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL);
        vo.setNumPac(0);
        Object relatorio = 
                    ReativarCooperadoDelegate.getInstance().gerarRelatorioFrmParcelamentoCCA(vo);
        
        SicoobLoggerPadrao.getInstance(getClass()).info("CCA ReativarCooperadoServico.gerarRelatorioParcelamentoCCA  relatorio: " + relatorio);
        
        return relatorio;
	}
	
	private Object gerarRelatorioReciboDebitoCCO(DadosContaCapitalProxy  dados) throws BancoobException{		
		Integer iParcelamento = parcelamentoCCADelegate.obterParcelamentoAtual(dados.getNumMatricula(), ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL, null);
      	Object relatorio = 
                    ReativarCooperadoDelegate.getInstance().gerarRelatorioReciboDebitoCCO(dados.getNumMatricula(), iParcelamento, false);
        return relatorio;
	}

	
}
