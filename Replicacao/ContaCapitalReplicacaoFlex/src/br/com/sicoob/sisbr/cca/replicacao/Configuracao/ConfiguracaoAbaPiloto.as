package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;

	public class ConfiguracaoAbaPiloto {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var cooperativasConsulta:Array = [];
		private var posicao:int = 0;
		
		private var resultados:ArrayCollection; // resultados
		
		private var cooperativasPreparacao:Array = [];
		
		public function ConfiguracaoAbaPiloto(configuracao:ConfiguracaoReplicacaoCapital)	{
			this.configuracao = configuracao;

			this.configuracao.servico.obterSingularesPP.addEventListener(ResultEvent.RESULT, retornoObterSingulares);
			this.configuracao.servico.consultarCooperativaPiloto.addEventListener(ResultEvent.RESULT, retornoConsultarCooperativaPiloto);
			this.configuracao.servico.prepararCooperativaPiloto.addEventListener(ResultEvent.RESULT, retornoPrepararCooperativaPiloto);
			
			this.configuracao.btTransferirOrigemDestinoPP.addEventListener(MouseEvent.CLICK, transferirOrigemDestino);
			this.configuracao.btTransferirDestinoOrigemPP.addEventListener(MouseEvent.CLICK, transferirDestinoOrigem);
			this.configuracao.btProcurarPiloto.addEventListener(MouseEvent.CLICK, consultarCooperativaPiloto);
			this.configuracao.btPrepararPiloto.addEventListener(MouseEvent.CLICK, prepararCooperativaPiloto);
			this.configuracao.chkSelecionarTodasPP.addEventListener(MouseEvent.CLICK, selecionarTodas);
		}
		
		public function retornoObterCentrais(event:ResultEvent):void {
			var listaCentral:ArrayCollection = event.result.dados.listaCentral as ArrayCollection;
			// listaCentral.addItemAt("SELECIONE", 0); // nao eh necessario, ja eh adicionado na aba coopPiloto
			this.configuracao.cmbCentralPP.dataProvider = listaCentral;
			this.configuracao.cmbCentralPP.addEventListener(Event.CHANGE, onChangeCmbCentral);
			limparSingulares();
			MostraCursor.removeBusyCursor();
		}
		
		private function onChangeCmbCentral(evt:Event):void {
			limparSingulares();
			if (this.configuracao.cmbCentralPP.selectedIndex > 0) {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.numCoopPai = this.configuracao.cmbCentralPP.selectedItem.codListaItem;
				dto.dados.descCoopPai = this.configuracao.cmbCentralPP.selectedItem.descListaItem;
				this.configuracao.servico.obterSingularesPP(dto);
			}
		}
		
		private function limparSingulares():void {
			this.configuracao.lstSingularesOrigemPP.dataProvider = new ArrayCollection();
			this.configuracao.lstSingularesDestinoPP.dataProvider = new ArrayCollection();
		}
		
		private function retornoObterSingulares(event:ResultEvent):void {
			this.configuracao.lstSingularesOrigemPP.dataProvider = event.result.dados.listaSingular;
			this.configuracao.lstSingularesDestinoPP.dataProvider = new ArrayCollection();
			MostraCursor.removeBusyCursor();
		}
		
		private function transferirOrigemDestino(event:MouseEvent=null):void {
			this.configuracao.abaCoopPiloto.transferirDePara(this.configuracao.lstSingularesOrigemPP, this.configuracao.lstSingularesDestinoPP);
		}
		
		private function transferirDestinoOrigem(event:MouseEvent=null):void {
			this.configuracao.abaCoopPiloto.transferirDePara(this.configuracao.lstSingularesDestinoPP, this.configuracao.lstSingularesOrigemPP);
		}
		
		private function prepararCooperativaPiloto(evt:MouseEvent):void {
			this.cooperativasPreparacao = [];
			if (this.resultados != null) {
				for (var i:int=0; i < this.resultados.length;i++) {
					if (this.resultados[i].selecionado && !this.resultados[i].coopPiloto) {
						this.cooperativasPreparacao.push(this.resultados[i].numCoop);
					}
				}
			}
			if (this.cooperativasPreparacao.length == 0) {
				Alerta.show("Selecione pelo menos uma cooperativa para preparação.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;
			}
			this.posicao = 0;
			Alerta.show("Confirma preparação da(s) cooperativa(s): " + this.cooperativasPreparacao.join(',') + "?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, enviarPreparacao);
		}
		
		private function enviarPreparacao(evt:MouseEvent=null):void {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cooperativa = this.cooperativasPreparacao[this.posicao];
			this.configuracao.servico.prepararCooperativaPiloto(dto);	
		}
		
		private function retornoPrepararCooperativaPiloto(event:ResultEvent):void {
			this.posicao++;
			if (this.posicao < this.cooperativasPreparacao.length) {
				enviarPreparacao();
			} else {
				Alerta.show("Execução realizada com sucesso!", "SUCESSO", Alerta.ALERTA_OK);
				//consultarCooperativaPiloto();
			}
		}
		
		private function consultarCooperativaPiloto(evt:Object=null):void {
			var selecionados:ArrayCollection = this.configuracao.lstSingularesDestinoPP.dataProvider as ArrayCollection;
			if (selecionados.length == 0) {
				Alerta.show("Selecione pelo menos uma cooperativa para consultar.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;
			}
			this.configuracao.chkSelecionarTodasPP.selected = false;
			this.resultados = new ArrayCollection();
			this.cooperativasConsulta = [];
			this.posicao = 0;
			for (var i:int=0; i<selecionados.length; i++) {
				cooperativasConsulta.push({num: selecionados[i].codListaItem, desc: selecionados[i].descListaItem});
			}
			enviarConsulta();
		}
		
		private function enviarConsulta():void {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cooperativa = this.cooperativasConsulta[this.posicao].num;
			this.configuracao.servico.consultarCooperativaPiloto(dto);	
		}
		
		private function retornoConsultarCooperativaPiloto(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			
			// ajustar objeto
			var obj:Object = event.result.dados.consulta;
			for (var keyDB2:String in obj.db2) {
				obj["db2."+keyDB2] = obj.db2[keyDB2];
			}
			for (var keySQL:String in obj.sql) {
				obj["sql."+keySQL] = obj.sql[keySQL];
			}
			obj.selecionado = false;
			obj.numCoop = this.cooperativasConsulta[this.posicao].num;
			obj.descCoop = this.cooperativasConsulta[this.posicao].desc;
			obj.coopPiloto = (this.configuracao.abaCoopPiloto.cooperativasPiloto.indexOf(obj.numCoop) != -1);
			obj.central = event.result.dados.central;
			this.resultados.addItem(obj);
			
			this.posicao++;
			if (this.posicao < this.cooperativasConsulta.length) {
				enviarConsulta();
			} else {
				atualizarGrid(this.configuracao.grdPP0);
				atualizarGrid(this.configuracao.grdPP1);
				atualizarGrid(this.configuracao.grdPP2);
				atualizarGrid(this.configuracao.grdPP3);
				this.configuracao.btCSVPiloto.enabled = (this.configuracao.grdPP0.dataProvider.length > 0);
			}
			
		}
		
		private function atualizarGrid(grid:Grid):void {
			grid.dataProvider = this.resultados;
			grid.dataProvider.refresh();
		}
		
		private function selecionarTodas(evt:MouseEvent=null):void {
			if (this.resultados != null) {
				for (var i:int=0; i < this.resultados.length;i++) {
					if (!this.resultados[i].coopPiloto) {
						this.resultados[i].selecionado = this.configuracao.chkSelecionarTodasPP.selected;								
					}
				} 
			}
		}
		
	}
}