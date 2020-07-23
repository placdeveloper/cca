package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;

	public class ConfiguracaoAbaAPI {
		
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.replicacao.servicos.TestadorAPIFachada";
		private var servico:ServicoJava = new ServicoJava();
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var lstClasses:ArrayCollection = new ArrayCollection();
		private var lstMetodos:ArrayCollection = new ArrayCollection();
		
		public function ConfiguracaoAbaAPI(configuracao:ConfiguracaoReplicacaoCapital)	{
			this.configuracao = configuracao;
			inicializarServico();
			
			this.configuracao.cmbAPIClasse.dataProvider = lstClasses;
			this.configuracao.cmbAPIMetodo.dataProvider = lstMetodos;
			this.servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			this.servico.executar.addEventListener(ResultEvent.RESULT, retornoExecutar);
			this.servico.executarIntegracao.addEventListener(ResultEvent.RESULT, retornoExecutar);
			this.configuracao.btAPIExec.addEventListener(MouseEvent.CLICK, executar);
			
			this.configuracao.optAPI.addEventListener(MouseEvent.CLICK, onChangeOptAPI);
			this.configuracao.optAPIInteg.addEventListener(MouseEvent.CLICK, onChangeOptAPI);
			
			obterDefinicoes();
		}
		
		private function inicializarServico():void {
			servico.configurarDestino(configuracao.destino);
			servico.source = SERVICO_SOURCE;
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;			
		}
		
		private function obterDefinicoes():void {
			this.servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			lstClasses.removeAll();
			lstClasses.addItem("SELECIONE");
			lstClasses.addAll(event.result.dados.classes as ArrayCollection);
			this.configuracao.cmbAPIClasse.addEventListener(Event.CHANGE, onChangeClasse);
			MostraCursor.removeBusyCursor();
		}
		
		private function onChangeClasse(evt:Event):void {
			var index:int = this.configuracao.cmbAPIClasse.selectedIndex;
			var item:Object = this.configuracao.cmbAPIClasse.selectedItem;
			lstMetodos.removeAll();
			if (index > 0) {
				this.configuracao.cmbAPIMetodo.enabled = true;
				this.configuracao.cmbAPIMetodo.editable = true;
				lstMetodos.addItem("SELECIONE");
				lstMetodos.addAll(item.metodos as ArrayCollection);
				this.configuracao.cmbAPIMetodo.addEventListener(Event.CHANGE, onChangeMetodo);
			} else {
				this.configuracao.cmbAPIMetodo.enabled = false;
				this.configuracao.cmbAPIMetodo.editable = false;
			}
		}
		
		private function onChangeMetodo(evt:Event):void {
			var index:int = this.configuracao.cmbAPIMetodo.selectedIndex;
			var item:Object = this.configuracao.cmbAPIMetodo.selectedItem;
			if (index > 0) {
				this.configuracao.txtAPIParametros.text = item.parametros;
			} else {
				this.configuracao.txtAPIParametros.text = "";
			}
		}
		
		private function executar(event:MouseEvent=null):void {
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			if (this.configuracao.optAPI.selected) {
				req.dados.classe = this.configuracao.cmbAPIClasse.selectedItem.classe;
				req.dados.metodo = this.configuracao.cmbAPIMetodo.selectedItem.metodo;
				req.dados.parametros = this.configuracao.txtAPIParametros.text;
				req.dados.valor = this.configuracao.txtAPIValor.text;
				this.servico.executar(req);
			} else {
				req.dados.valor = this.configuracao.txtAPIValor.text;
				this.servico.executarIntegracao(req);
			}
		}
		
		private function retornoExecutar(event:ResultEvent):void {
			var resultado:String = event.result.dados.resultado;
			this.configuracao.txtAPIResultado.text = resultado;
			MostraCursor.removeBusyCursor();
		}
		
		protected function onChangeOptAPI(event:MouseEvent):void {
			this.configuracao.boxAPI.visible = this.configuracao.optAPI.selected;
			this.configuracao.boxAPI.includeInLayout = this.configuracao.optAPI.selected;
		}
		
	}
}