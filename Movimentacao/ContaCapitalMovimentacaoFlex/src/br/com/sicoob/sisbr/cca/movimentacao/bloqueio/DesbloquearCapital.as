package br.com.sicoob.sisbr.cca.movimentacao.bloqueio
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.formatters.CurrencyFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.util.DataUtilRelatorios;
	import br.com.sicoob.sisbr.cca.vo.BloqueioContaCapitalVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO", BloqueioContaCapitalVO);
	public class DesbloquearCapital extends DesbloquearCapitalView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.BloqueioContaCapitalServico";
		
		private var servico:ServicoJava = new ServicoJava();
		
		public var vo:BloqueioContaCapitalVO;
		public var cf:CurrencyFormatter;
		
		private var listaVO:ArrayCollection = new ArrayCollection();
		
		public function DesbloquearCapital() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			controlarServico();
			controlarEventos();
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(this.destino);
			servico.source = SERVICO_SOURCE;
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			obterInformacoes();
		}
		
		private function controlarServico():void {
			servico.obterInformacoesDesbloqueio.addEventListener(ResultEvent.RESULT, retornoObterInformacoes);
			servico.desbloquear.addEventListener(ResultEvent.RESULT, retornoDesbloquear);
		}
		
		private function obterInformacoes():void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.idInstituicao = vo.idInstituicao;
			servico.obterInformacoesDesbloqueio(reqDTO);
		}
		
		private function retornoObterInformacoes(event:ResultEvent):void {
			txtInstituicao.text = event.result.dados["instituicao"];
			txtInstituicaoResp.text = event.result.dados["central"];
			txtNumCCA.text = vo.numContaCapital.toString();
			txtNome.text = vo.nomePessoa;
			txtCpfCnpj.text = FormatUtil.formataCPFCNPJ(vo.cpfCnpj);
			txtTipoBloqueio.text = vo.nomeTipoBloqueio;
			txtDtBloqueio.text = DataUtilRelatorios.dateToString(vo.dataBloqueio.data);
			txtValorBloqueio.valor = vo.valorBloqueado;
			txtValorDesbloqueio.valor = vo.valorBloqueado;
			if (vo.dataProtocolo != null) {
				txtDtProtocolo.text = DataUtilRelatorios.dateToString(vo.dataProtocolo.data);
			}
			if (vo.numProtocolo != null) {
				txtNumProtocolo.text = vo.numProtocolo;
			}
			if (vo.numProcesso != null) {
				txtNumProcesso.text = vo.numProcesso;
			}
			if (vo.idTipoBloqueio == 2) { // judicial
				rtDtProtocolo.visible = true;
				rtDtProtocolo.includeInLayout = true;
				txtDtProtocolo.visible = true;
				txtDtProtocolo.includeInLayout = true;
				hboxNumerosJudiciais.visible = true;
				hboxNumerosJudiciais.includeInLayout = true;
				this.height = 370;
			}
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarEventos():void {
			optTipoDesbloqueioTotal.addEventListener(MouseEvent.CLICK, onClickOptDesbloqueio);
			optTipoDesbloqueioParcial.addEventListener(MouseEvent.CLICK, onClickOptDesbloqueio);
			
			btOk.addEventListener(MouseEvent.CLICK, desbloquear);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		private function limpar():void {
			txtValorDesbloqueio.valor = vo.valorBloqueado;
			txtValorDesbloqueio.enabled = false;
			optTipoDesbloqueioTotal.selected = true;
		}
		
		private function cancelar(evt:MouseEvent):void {
			limpar();
		}
		
		private function fechar(evt:MouseEvent):void {
			fecharJanela();
		}
		
		private function desbloquear(evt:MouseEvent):void {
			if (!validarCampos()) {
				return;
			}
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			vo.valorDesbloqueio = txtValorDesbloqueio.valor;
			reqDTO.dados.vo = vo;
			this.servico.desbloquear(reqDTO);
		}
		
		private function validarCampos():Boolean {
			if (optTipoDesbloqueioParcial.selected) {
				if (txtValorDesbloqueio.text == "") {
					Alerta.show("O campo Valor do Desbloqueio é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, txtValorDesbloqueio);
					return false;
				}
				if (txtValorDesbloqueio.valor <= 0) {
					Alerta.show("O valor do desbloqueio deve ser maior que zero (R$ 0,00).", "ATENÇÃO", Alerta.ALERTA_OK, txtValorDesbloqueio);
					return false;
				}
				if (txtValorDesbloqueio.valor > txtValorBloqueio.valor) {
					Alerta.show("O valor do desbloqueio não pode ser maior que o valor do bloqueio.", "ATENÇÃO", Alerta.ALERTA_OK, txtValorDesbloqueio);
					return false;
				}
			}
			return true;
		}
		
		private function retornoDesbloquear(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			if (evt.result.dados["msg"]) {
				Alerta.show(evt.result.dados["msg"], "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
				this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
				(this.parentDocument as MovimentacaoBloqueio).recarregarConsulta();
				fecharJanela();
			}
		}
		
		private function onClickOptDesbloqueio(evt:MouseEvent):void {
			if (optTipoDesbloqueioTotal.selected) {
				txtValorDesbloqueio.enabled = false;
				txtValorDesbloqueio.valor = vo.valorBloqueado;
			} else {
				txtValorDesbloqueio.enabled = true;
			}
		}
		
	}
}