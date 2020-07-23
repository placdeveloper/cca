package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.input.Radio;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.vo.MonitoracaoCapitalVO;
	import br.com.sicoob.sisbr.cca.vo.ReplicacaoContaCapitalLegadoVO;

	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoCapitalVO", MonitoracaoCapitalVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.ReplicacaoContaCapitalLegadoVO", ReplicacaoContaCapitalLegadoVO);
	public class ConfiguracaoAbaMonitoracao {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var SERVICO_MONITORACAO_SOURCE:String = "br.com.sicoob.sisbr.cca.replicacao.servicos.MonitoracaoReplicacaoCapitalFachada";
		private var servicoMonitoracao:ServicoJava = new ServicoJava();
		
		private var listaGeral:ArrayCollection = new ArrayCollection();		
		private var listaCooperativas:ArrayCollection = new ArrayCollection();		
		
		public function ConfiguracaoAbaMonitoracao(configuracao:ConfiguracaoReplicacaoCapital)	{
			this.configuracao = configuracao;
			inicializarServico();
			
			this.configuracao.servico.consultarMonitoracao.addEventListener(ResultEvent.RESULT, retornoConsultar);
			
			this.configuracao.btMonitConsultar.addEventListener(MouseEvent.CLICK, consultar);
			
			this.configuracao.optMonitCooperativaTodas.addEventListener(MouseEvent.CLICK, onChangeCooperativa);
			this.configuracao.optMonitCooperativaPiloto.addEventListener(MouseEvent.CLICK, onChangeCooperativa);
			this.configuracao.optMonitCooperativaNumero.addEventListener(MouseEvent.CLICK, onChangeCooperativa);
			
			this.configuracao.optMonitPeriodoTodas.addEventListener(MouseEvent.CLICK, onChangePeriodo);
			this.configuracao.optMonitPeriodoHoje.addEventListener(MouseEvent.CLICK, onChangePeriodo);
			this.configuracao.optMonitPeriodoIntervalo.addEventListener(MouseEvent.CLICK, onChangePeriodo);
			
			this.configuracao.grdMonitGeral.dataProvider = this.listaGeral;
			this.configuracao.grdMonitCooperativas.dataProvider = this.listaCooperativas;
		}
		
		private function inicializarServico():void {
			servicoMonitoracao.configurarDestino(configuracao.destino);
			servicoMonitoracao.source = SERVICO_MONITORACAO_SOURCE;
			servicoMonitoracao.showBusyCursor = true;
			servicoMonitoracao.bloquearOperacao = true;			
		}
		
		protected function onChangeCooperativa(event:MouseEvent):void {
			this.configuracao.txtMonitCooperativa.text = "";
			this.configuracao.txtMonitCooperativa.visible = this.configuracao.optMonitCooperativaNumero.selected;
		}
		
		protected function onChangePeriodo(event:MouseEvent):void {
			this.configuracao.boxMonitPeriodoDe.visible = this.configuracao.optMonitPeriodoIntervalo.selected;
			this.configuracao.boxMonitPeriodoAte.visible = this.configuracao.optMonitPeriodoIntervalo.selected;
		}
		
		private function consultar(event:MouseEvent=null):void {
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			var i:int = 0;
			var opt:Radio = null;
			
			// COOPERATIVA
			if (this.configuracao.txtMonitCooperativa.enabled && this.configuracao.txtMonitCooperativa.text.length == 4) {
				req.dados.numCoop = this.configuracao.txtMonitCooperativa.text;
			}
			req.dados.apenasPilotos = this.configuracao.optMonitCooperativaPiloto.selected;
			
			// VISAO
			var visoes:Array = this.configuracao.boxMonitVisao.getChildren();
			for (i=0; i<visoes.length; i++) {
				if (visoes[i] is Radio) {
					opt = visoes[i] as Radio;
					if (opt.selected) {
						req.dados.visao = opt.value;
						break;
					}
				}
			}
			
			// SITUACAO
			var situacoes:Array = this.configuracao.boxMonitSituacao.getChildren();
			for (i=0; i<situacoes.length; i++) {
				if (situacoes[i] is Radio) {
					opt = situacoes[i] as Radio;
					if (opt.selected) {
						req.dados.situacao = opt.value;
						break;
					}
				}
			}
			
			// PERIODO
			if (this.configuracao.optMonitPeriodoHoje.selected) {
				req.dados.data = new Date();
			}
			if (this.configuracao.optMonitPeriodoIntervalo.selected) {
				if (this.configuracao.txtMonitPeriodoDe.selectedDate == null || this.configuracao.txtMonitPeriodoAte.selectedDate == null) {
					Alerta.show("Informe o período.", "ATENÇÃO", Alerta.ALERTA_ERRO);
					return;
				}
				req.dados.periodo = [this.configuracao.txtMonitPeriodoDe.selectedDate, this.configuracao.txtMonitPeriodoAte.selectedDate];
			}
			
			this.configuracao.servico.consultarMonitoracao(req);
		}
		
		private function retornoConsultar(event:ResultEvent):void {
			
			this.listaGeral.removeAll();
			this.listaCooperativas.removeAll();
			
			this.configuracao.btMonitCSV1.enabled = false;
			this.configuracao.btMonitCSV2.enabled = false;
			
			if (event.result.dados["lstMonitor"] != null) {
				var lstMonitor:ArrayCollection = event.result.dados["lstMonitor"] as ArrayCollection;
				this.listaGeral.addAll(lstMonitor);					
				this.configuracao.btMonitCSV1.enabled = (lstMonitor.length > 0);
			} 
			if (event.result.dados["lstMonitorCoop"] != null) {
				var lstMonitorCoop:ArrayCollection = event.result.dados["lstMonitorCoop"] as ArrayCollection;
				this.listaCooperativas.addAll(lstMonitorCoop);
				this.configuracao.btMonitCSV2.enabled = (lstMonitorCoop.length > 0);
			} 
			
			MostraCursor.removeBusyCursor();
		}
		
	}
}