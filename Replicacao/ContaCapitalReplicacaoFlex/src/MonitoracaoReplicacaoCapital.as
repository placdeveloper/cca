package
{
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.net.registerClassAlias;
	import flash.utils.Timer;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ResizeEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.plataformas.IModuloPlataformaMonitoracao;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.replicacao.monitoracao.ConfirmacaoOperacaoView;
	import br.com.sicoob.sisbr.cca.replicacao.monitoracao.MonitoracaoDetalhe;
	import br.com.sicoob.sisbr.cca.replicacao.monitoracao.MonitoracaoReplicacaoCapitalView;
	import br.com.sicoob.sisbr.cca.vo.MonitoracaoCapitalVO;
	import br.com.sicoob.sisbr.cca.vo.ReplicacaoContaCapitalLegadoVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoCapitalVO", MonitoracaoCapitalVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.ReplicacaoContaCapitalLegadoVO", ReplicacaoContaCapitalLegadoVO);
	public class MonitoracaoReplicacaoCapital extends MonitoracaoReplicacaoCapitalView implements IModuloPlataformaMonitoracao {
		
		private var destinoVO:DestinoVO;
		private var servico:ServicoJava = new ServicoJava();
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.replicacao.servicos.MonitoracaoReplicacaoCapitalFachada";		
		private var lista:ArrayCollection = new ArrayCollection();		
		private var listaErros:ArrayCollection = new ArrayCollection();
		private var timer:Timer;
		private var telaWidget:Object;
		
		private var janelaConfirmacao:Janela;
		private var telaConfirmacao:ConfirmacaoOperacaoView;
		
		private var numCoopPesquisa:int = 0;
		
		public function MonitoracaoReplicacaoCapital() {
			super();
			addEventListener(ResizeEvent.RESIZE, resizeTelaMonitoramentoFecharLoadingHandler);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();			
			controlarServico();		
			controlarEventos();
			
			timer = new Timer(60000 * 10); //10 Minutos
			timer.addEventListener(TimerEvent.TIMER, atualizar);
		}
		
		private function configurarServico():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(destinoVO);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			if(numCoop.text.length == 4) {
				numCoopPesquisa = parseInt(numCoop.text);
				reqDTO.dados.numCoop = numCoopPesquisa;
			}
			reqDTO.dados.apenasPilotos = chkApenasPilotos.selected;
			
			servico.obterDefinicoes(reqDTO);
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			
			servico.reprocessar.addEventListener(ResultEvent.RESULT, retornoReprocessar);
			servico.invalidar.addEventListener(ResultEvent.RESULT, retornoInvalidar);
		}
		
		private function controlarEventos():void {
			btReprocessar.addEventListener(MouseEvent.CLICK, confirmReprocessar);
			btInvalidar.addEventListener(MouseEvent.CLICK, confirmInvalidar);
			
			numCoop.addEventListener(KeyboardEvent.KEY_UP, buscarPorCooperativa);
			chkApenasPilotos.addEventListener(MouseEvent.CLICK, onClickApenasPilotos);
		}
		
		private function onClickApenasPilotos(evt:MouseEvent):void {
			numCoop.text = "";
			numCoop.enabled = !chkApenasPilotos.selected;
			obterDefinicoes();
		}
		
		private function buscarPorCooperativa(ke:KeyboardEvent):void {
			var reqDTO:RequisicaoReqDTO;
			
			if(numCoop.text.length == 4 && numCoopPesquisa != parseInt(numCoop.text)) {
				reqDTO = new RequisicaoReqDTO();
				numCoopPesquisa = parseInt(numCoop.text);
				reqDTO.dados.numCoop = numCoopPesquisa;
				reqDTO.dados.apenasPilotos = chkApenasPilotos.selected;
				servico.obterDefinicoes(reqDTO);
			} else if(numCoop.text.length == 0 && (ke.keyCode == 8 || ke.keyCode == 46)) {
				reqDTO = new RequisicaoReqDTO();
				reqDTO.dados.apenasPilotos = chkApenasPilotos.selected;
				servico.obterDefinicoes(reqDTO);
			}
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			this.lista = new ArrayCollection();
			this.lista.list = event.result.dados["lstMonitor"] as ArrayCollection;					
			grdVisaoGeral.dataProvider = lista.list;
			
			this.listaErros = new ArrayCollection();			
			this.listaErros.list = event.result.dados["lstErros"] as ArrayCollection;
			grdErros.dataProvider = listaErros.list;
			grdErros.labelFunction = formataDataGrid;
			grdErros.addEventListener(MouseEvent.DOUBLE_CLICK, exibirDetalhe);
			
			MostraCursor.removeBusyCursor();
		}
		
		private function exibirDetalhe(evt:MouseEvent):void {
			if (grdErros.selectedItem != null) {
				var detalheMensagem:MonitoracaoDetalhe = new MonitoracaoDetalhe(grdErros.selectedItem);
				
				var telaMensagem:Janela = new Janela();
				telaMensagem.title = "MONITORAÇÃO";
				telaMensagem.removeAllChildren();
				
				telaMensagem.x = (this.stage.stageWidth / 100) * 20;
				telaMensagem.y = (this.stage.stageWidth / 100) * 5;
				
				telaMensagem.addChild(detalheMensagem);
				telaMensagem.abrir(this, true, false);
			}
		}
		
		private function confirmReprocessar(evt:MouseEvent):void {
			if(!registroSelecionado()) {
				Alerta.show("Selecione o registro.", "Alerta", Alerta.ALERTA_INFORMACAO);
				return;
			}
			
			telaConfirmacao = new ConfirmacaoOperacaoView();
			telaConfirmacao.addEventListener(FlexEvent.CREATION_COMPLETE, initReprocessar);
			
			janelaConfirmacao = new Janela();
			janelaConfirmacao.title = "CONFIRMAR OPERAÇÃO";
			janelaConfirmacao.x = (this.stage.stageWidth / 100) * 30;
			janelaConfirmacao.y = (this.stage.stageWidth / 100) * 10;
			janelaConfirmacao.addChild(telaConfirmacao);
			janelaConfirmacao.abrir(this, true, false);
			
			pararTempo();
		}
		
		private function initReprocessar(fe:FlexEvent):void {
			telaConfirmacao.btOk.addEventListener(MouseEvent.CLICK, reprocessar);
			telaConfirmacao.btCancelar.addEventListener(MouseEvent.CLICK, fecharJanelaConfirmacao);
		}
		
		private function reprocessar(evt:MouseEvent):void {
			if(telaConfirmacao.justificativa.length == 0) {
				Alerta.show("Justificativa deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, telaConfirmacao.justificativa);
				return;
			}
			
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var listaReprocessar:ArrayCollection = new ArrayCollection();
			
			for(var i:uint = 0; i < grdErros.dataProvider.length; i++) {
				if(grdErros.dataProvider[i].selecionado) {
					listaReprocessar.addItem(grdErros.dataProvider[i]);
				}
			}
			
			req.dados.justificativa = telaConfirmacao.justificativa.text;
			req.dados.listaReprocessar = listaReprocessar;
			
			servico.reprocessar(req);
		}
		
		private function retornoReprocessar(event:ResultEvent):void {
			Alerta.show("Operação realizada com sucesso!", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			
			telaConfirmacao.fecharJanela();
			
			obterDefinicoes();
			
			iniciarTempo();
		}
		
		private function confirmInvalidar(evt:MouseEvent):void {
			if(!registroSelecionado()) {
				Alerta.show("Selecione o registro.", "Alerta", Alerta.ALERTA_INFORMACAO);
				return;
			}
			
			telaConfirmacao = new ConfirmacaoOperacaoView();
			telaConfirmacao.addEventListener(FlexEvent.CREATION_COMPLETE, initInvalidar);
			
			janelaConfirmacao = new Janela();
			janelaConfirmacao.title = "CONFIRMAR OPERAÇÃO";
			janelaConfirmacao.x = (this.stage.stageWidth / 100) * 30;
			janelaConfirmacao.y = (this.stage.stageWidth / 100) * 10;
			janelaConfirmacao.addChild(telaConfirmacao);
			janelaConfirmacao.abrir(this, true, false);
			
			pararTempo();
		}
		
		private function initInvalidar(fe:FlexEvent):void {
			telaConfirmacao.btOk.addEventListener(MouseEvent.CLICK, invalidar);
			telaConfirmacao.btCancelar.addEventListener(MouseEvent.CLICK, fecharJanelaConfirmacao);
		}
		
		private function invalidar(evt:MouseEvent):void {
			if(telaConfirmacao.justificativa.length == 0) {
				Alerta.show("Justificativa deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, telaConfirmacao);
				return;
			}
			
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var listaInvalidar:ArrayCollection = new ArrayCollection();
			
			for(var i:uint = 0; i < grdErros.dataProvider.length; i++) {
				if(grdErros.dataProvider[i].selecionado) {
					listaInvalidar.addItem(grdErros.dataProvider[i]);
				}
			}
			
			req.dados.justificativa = telaConfirmacao.justificativa.text;
			req.dados.listaInvalidar = listaInvalidar;
			
			servico.invalidar(req);
		}
		
		private function retornoInvalidar(event:ResultEvent):void {
			Alerta.show("Operação realizada com sucesso!", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			
			telaConfirmacao.fecharJanela();
			
			obterDefinicoes();
			
			iniciarTempo();
		}
		
		private function registroSelecionado():Boolean {
			var registroSelecionado:Boolean = false;
			for(var i:uint = 0; i < grdErros.dataProvider.length; i++) {
				if(grdErros.dataProvider[i].selecionado) {
					registroSelecionado = true;
					break;
				}
			}
			return registroSelecionado;
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			var format:DateFormatter = new DateFormatter();
			format.formatString = "DD/MM/YYYY J:NN:SS";
			
			switch(col.dataField) {
				case "dataHoraCadastro":
					if (obj[col.dataField]) {
						return format.format(obj[col.dataField].data);
					}
				break;
				
				case "dataHoraReplicacao":
					if (obj[col.dataField]) {
						return format.format(obj[col.dataField].data);
					}
				break;
				
				default:
					if(obj[col.dataField] == null) {
						retorno = "";
					} else {
						retorno = obj[col.dataField].toString();
					}
				break;
			}
			return retorno;
		}
		
		private function resizeTelaMonitoramentoFecharLoadingHandler(evento:ResizeEvent):void {
			pararCarregamento();
		}
		
		private function pararCarregamento():void {
			telaWidget.imgCarregando.visible = false;
		}
		
		private function fecharJanelaConfirmacao(evt:MouseEvent):void {
			timer.start();
		}
		
		public function iniciarTempo():void {
			timer.start();
		}
		
		public function pararTempo():void {
			timer.stop();
		}
		
		public function setWidget(tela:Object):void {
			telaWidget = tela;
		}
		
		private function atualizar(evt:TimerEvent):void {
			obterDefinicoes();
		}
		
		public function mostraResumo():void	{}
		
		public function someResumo():void {} 
		
		public function mostraFiltro():void	{}
		
		public function someFiltro():void {}
	}
}