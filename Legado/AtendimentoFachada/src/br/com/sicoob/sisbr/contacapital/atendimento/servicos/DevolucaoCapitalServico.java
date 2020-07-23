package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.contacapital.comum.negocio.entidades.ValorCotas;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.mensagens.AtendimentoBOResourceBundle;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.FechamentoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.SaldoContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ValorCotasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosParcelamentoCCAProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DevolucaoCapitalVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.ParcelaVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class DevolucaoCapitalServico extends AtendimentoFachada {

	private ValorCotasDelegate valorCotasDelegate=
			AtendimentoFabricaDelegates.getInstance().criarValorCotasDelegate();
	
	private FechamentoContaCapitalDelegate fechCcaDelegate =
			AtendimentoFabricaDelegates.getInstance().criarFechamentoContaCapitalDelegate();
	
	private SaldoContaCapitalDelegate saldoDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarSaldoContaCapitalDelegate();
	
	private ContaCapitalDelegate ccaDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	private ParcelamentoCCADelegate parcelDelegate =
			AtendimentoFabricaDelegates.getInstance().criarParcelamentoCCADelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RetornoDTO retorno = new RetornoDTO();
		
		if(validarInicializacao(retorno)){
		
			retorno.getDados().put("dtProduto", obterDataAtualProduto());
			
			List<ItemListaVO> listaMotivo = obterLista(
					ContaCapitalConstantes.LST_MOTIVOS_DEVOLUCAO_CAPITAL);
			
			retorno.getDados().put("listaMotivo", listaMotivo);
			
			Integer numPai = obterNumPai();
			
			retorno.getDados().put("numPai", numPai);
		}
		
		return retorno;	
	}
	
	public RetornoDTO apurar(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RetornoDTO retorno = new RetornoDTO();
		Long numMatricula = Long.valueOf(dto.getDados().get("numMatricula").toString());
		Integer statusCooperado = (Integer) dto.getDados().get("statusCooperado");
		
		if(statusCooperado.equals(ContaCapitalConstantes.COOPERADO_ATIVO)){
			
			BigDecimal saldoSubscricao = 
					saldoDelegate.recuperarSaldoAtualSubscricao(numMatricula);
			
			BigDecimal saldoIntegralizacao = 
				saldoDelegate.recuperarSaldoAtualIntegralizacao(numMatricula);
			
			BigDecimal valorIntegralizar =
					ccaDelegate.recuperarValorIntegralizar(
							saldoSubscricao, saldoIntegralizacao);
			
			BigDecimal valorBloqueado = 
					ccaDelegate.recuperarValorBloqueado(numMatricula);
			
			Integer quantidadeCotas = 
					valorCotasDelegate.obterQuantidadeCotas(saldoSubscricao);
			
			retorno.getDados().put("saldoSubscricao", saldoSubscricao);
			retorno.getDados().put("saldoIntegralizacao", saldoIntegralizacao);
			retorno.getDados().put("valorIntegralizar", valorIntegralizar);
			retorno.getDados().put("valorBloqueado", valorBloqueado);
			retorno.getDados().put("quantidadeCotas", quantidadeCotas);
			
		}else if(statusCooperado.equals(ContaCapitalConstantes.COOPERADO_DESLIGADO)){
			
			BigDecimal valorRestituir =
					saldoDelegate.verificarSaldoRestituir(numMatricula);
			retorno.getDados().put("valorRestituir", valorRestituir);
			
		}
		
		retorno.getDados().put("statusCooperado", statusCooperado);
		
		return retorno;
	}
	
	public RetornoDTO validar(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RetornoDTO retorno = new RetornoDTO();
		
		DevolucaoCapitalVO vo = obterVO(dto);
		
		parcelDelegate.validarParcelasDevAberto(
				vo.getNumMatricula() != null ? vo.getNumMatricula() : 0);
		
		DadosContaCapitalProxy proxy = converter(vo);
		
		ccaDelegate.validarDevolucao(proxy);
		
		ccaDelegate.validarRestituicaoAVista(proxy);
		
		ccaDelegate.ValidarSaldoBloqProcapcred(
				proxy.getNumMatricula(), proxy.getTotalDevolver());
		
		return retorno;
		
	}
	
	public RetornoDTO obterDefinicoesParcSel(RequisicaoReqDTO dto)
			throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		
		List<ItemListaVO> lista = obterListaModoInteg();
		retorno.getDados().put("listaFormaPagamento", lista);
		
		DevolucaoCapitalVO vo = obterVO(dto);
		
		List<ParcelaVO> parcelas = gerarParcelas(vo);
		
		retorno.getDados().put("parcelas", parcelas);
		
		return retorno;
	}
	
	public RetornoDTO gerarMaisParcelas(RequisicaoReqDTO dto)
			throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		
		DevolucaoCapitalVO vo = obterVO(dto);
		ParcelaVO pvo = obterParcelaVO(dto);
		
		List<ParcelaVO> parcelas = gerarMaisParcelas(vo, pvo);
		
		retorno.getDados().put("parcelas", parcelas);
		
		return retorno;
		
	}
	
	public RetornoDTO validarDataVencParcela(RequisicaoReqDTO dto)
			throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();		
		DateTime dtVenc = (DateTime) dto.getDados().get("dataVencimento");
		retorno.getDados().put("stDataVencimento", validarDataVencimentoParcela(dtVenc));
		retorno.getDados().put("codTipoParcelamento", 
				ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO);
		return retorno;		
	}
	
	public RetornoDTO atualizar(RequisicaoReqDTO dto)
			throws BancoobException{
		
		RetornoDTO retorno = new RetornoDTO();
		
		DevolucaoCapitalVO vo = obterVO(dto);
		
		List<ParcelaVO> parcelas = obterParcelasVO(dto);
		if(parcelas == null || parcelas.isEmpty()){
			parcelas = gerarParcelas(vo);
		}
		
		Boolean flag = ccaDelegate.restituirCapital(vo, parcelas);
		
		retorno.getDados().put("flag", flag);
		
		return retorno;		
	}

	private List<ParcelaVO> gerarParcelas(DevolucaoCapitalVO vo) throws BancoobException {
		
		DadosParcelamentoCCAProxy proxy = new DadosParcelamentoCCAProxy();
		proxy.setValorTotal(vo.getValorDevolucao());
		proxy.setValorAvista(vo.getValorAvista());
		proxy.setDataMatricula(vo.getDataDevolucao());
		proxy.setNumContaCorrente(vo.getNumContaCorrente());
		proxy.setCodTipoParcelamento(
				ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO);
		proxy.setCodModoLanc(vo.getTipoModoDevolucao());
		proxy.setQuantParcelas(0);
		
		return parcelDelegate.gerarParcelas(proxy);
	}
	
	private List<ParcelaVO> gerarMaisParcelas(
			DevolucaoCapitalVO vo, ParcelaVO pvo) throws BancoobException {
		
		DadosParcelamentoCCAProxy proxy = new DadosParcelamentoCCAProxy();
		proxy.setValorTotal(pvo.getValorRestante());
		proxy.setDataMatricula(vo.getDataDevolucao());
		proxy.setNumContaCorrente(pvo.getNumContaCorrente());
		proxy.setCodTipoParcelamento(
				ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_DEVOLUCAO);
		proxy.setCodModoLanc(pvo.getCodModoLanc());
		proxy.setQuantParcelas(pvo.getQtdParcelas());
		return parcelDelegate.gerarMaisParcelas(proxy);
	}

	private DadosContaCapitalProxy converter(DevolucaoCapitalVO vo) {
		DadosContaCapitalProxy proxy = new DadosContaCapitalProxy();
		
		proxy.setNumCliente(vo.getNumCliente());
		proxy.setNumMatricula(vo.getNumMatricula());
		proxy.setStAtivo(ContaCapitalConstantes.COOPERADO_ATIVO.equals(
				vo.getStatusCooperado()));
		proxy.setStDesligado(ContaCapitalConstantes.COOPERADO_DESLIGADO.equals(
				vo.getStatusCooperado()));
		proxy.setStViaConta(vo.getTipoModoDevolucao() == 2);
		proxy.setNumContaCorrente(vo.getNumContaCorrente());
		proxy.setDataDevolucao(vo.getDataDevolucao());
		proxy.setMotivo(vo.getCodigoMotivo());
		proxy.setTotalDevolver(vo.getValorDevolucao());
		proxy.setValorAVista(vo.getValorAvista());
		proxy.setValorSubscricao(vo.getValorSuscricao());
		
		return proxy;
	}

	private DevolucaoCapitalVO obterVO(RequisicaoReqDTO dto) {
		DevolucaoCapitalVO vo = (DevolucaoCapitalVO) dto.getDados().get("devolucaoCapitalVO");
		return vo;
	}
	
	private ParcelaVO obterParcelaVO(RequisicaoReqDTO dto) {
		ParcelaVO vo = (ParcelaVO) dto.getDados().get("parcelaVO");
		return vo;
	}
	
	private List<ParcelaVO> obterParcelasVO(RequisicaoReqDTO dto) {
		List<ParcelaVO> vos = (List<ParcelaVO>) dto.getDados().get("listaParcelasVO");
		
		if(vos != null){
			Iterator<ParcelaVO> it = vos.iterator();
			while (it.hasNext()) {
				ParcelaVO vo = (ParcelaVO) it.next();
				System.out.println(vo.getNumParcela());
			}
		}
		
		return vos;
	}
	
	private Boolean validarInicializacao(RetornoDTO retorno) throws BancoobException{
		
		if(fechCcaDelegate.verificarFechCapitalIniciado()){
			retorno.getDados().put("flagInicializacao", false);
			retorno.getDados().put("msgInicializacao"
					, AtendimentoBOResourceBundle.getInstance().getString(
							"msg.erro.verificaFechamentoCapitalIniciado"));
			return false;
		}
		
		List<ValorCotas> listaValorCotas = valorCotasDelegate.listar();
		if(listaValorCotas == null || listaValorCotas.isEmpty()){
			retorno.getDados().put("flagInicializacao", false);
			retorno.getDados().put("msgInicializacao"
					, AtendimentoBOResourceBundle.getInstance().getString(
							"msg.erro.informeValorCota"));
			return false;		
		}
		
		retorno.getDados().put("flagInicializacao", true);
		return true;		
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
			if(!cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_FOLHA) &&
					!cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_COBRANCA) &&
					!cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RATEIO) &&
					!cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RESERVA) &&
					!(cod.equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA) 
							&& numPai ==0 )){
				retorno.add(itemVO);
			}
		}		
		
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
	
}
