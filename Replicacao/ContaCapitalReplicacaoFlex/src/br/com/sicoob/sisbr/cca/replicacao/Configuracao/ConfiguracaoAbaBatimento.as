package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;

	public class ConfiguracaoAbaBatimento {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var cooperativasBat:Array = [];
		private var cooperativasBatRet:Array = [];
		private var posicao:int = 0;
		
		public function ConfiguracaoAbaBatimento(configuracao:ConfiguracaoReplicacaoCapital)	{
			this.configuracao = configuracao;
			
			this.configuracao.servico.consultarCooperativasBatimento.addEventListener(ResultEvent.RESULT, retornoConsultarCooperativasBatimento);
			this.configuracao.servico.verificarCooperativaBatimento.addEventListener(ResultEvent.RESULT, retornoVerificarCooperativaBatimento);
			this.configuracao.servico.obterSingularesRel.addEventListener(ResultEvent.RESULT, retornoObterSingulares);
			
			this.configuracao.btTransferirOrigemDestinoRel.addEventListener(MouseEvent.CLICK, transferirOrigemDestino);
			this.configuracao.btTransferirDestinoOrigemRel.addEventListener(MouseEvent.CLICK, transferirDestinoOrigem);
			
			this.configuracao.optRelBatSingular.addEventListener(MouseEvent.CLICK, onChangeOptRel);
			this.configuracao.optRelBatLista.addEventListener(MouseEvent.CLICK, onChangeOptRel);
			
			this.configuracao.txtCooperativaDivergencias.addEventListener(FlexEvent.ENTER, emitirRelatorio);
			this.configuracao.btRelDivergencias.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			
			this.configuracao.servico.consultarBatimentoSaldos.addEventListener(ResultEvent.RESULT, retornoConsultarBatimentoSaldos);
			this.configuracao.txtCooperativaBatimentoSaldos.addEventListener(FlexEvent.ENTER, consultarBatimentoSaldos);
			this.configuracao.btExecBatimentoSaldos.addEventListener(MouseEvent.CLICK, consultarBatimentoSaldos);
			
			obterCooperativas();
		}
		
		private function emitirRelatorio(evt:Object):void {
			var dto:ParametroDTO = new ParametroDTO();
			reiniciarEstados();
			
			if (this.configuracao.optRelBatSingular.selected) {
				
				if (this.configuracao.txtCooperativaDivergencias.text == "") {
					this.configuracao.boxProgressBatimento.visible = true;
					this.configuracao.boxProgressBatimento.includeInLayout = true;
					atualizarProgresso(0,0,0);
					this.configuracao.servico.consultarCooperativasBatimento(new RequisicaoReqDTO());	
					return;
				}
				
				dto.dados.cooperativa = this.configuracao.txtCooperativaDivergencias.text;
			} else {
				var cooperativas:Array = [];
				var selecionados:ArrayCollection = this.configuracao.lstCoopRelDestino.dataProvider as ArrayCollection;
				for (var i:int=0; i<selecionados.length; i++) {
					cooperativas.push(selecionados[i].codListaItem);
				}
				cooperativas.sort();
				dto.dados.cooperativa = cooperativas.join(',');
			}
			
			RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelBatimentoSQLxDB2ServicoRemote", 
				dto, "CCA_RelBatimentoSQLxDB2", this.configuracao.servico.destino, "Emitindo relatório...", null, false);
		}
		
		private function reiniciarEstados():void {
			this.configuracao.boxProgressBatimento.visible = false;
			this.configuracao.boxProgressBatimento.includeInLayout = false;
			this.posicao = 0;
			this.cooperativasBat = [];
			this.cooperativasBatRet = [];
			this.configuracao.txtCooperativasComDivergencias.text = "";
		}
		
		private function obterCooperativas():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.numCoopPai = "";
			this.configuracao.servico.obterSingularesRel(dto);
		}
		
		private function transferirOrigemDestino(event:MouseEvent=null):void {
			this.configuracao.abaCoopPiloto.transferirDePara(this.configuracao.lstCoopRelOrigem, this.configuracao.lstCoopRelDestino);
		}
		
		private function transferirDestinoOrigem(event:MouseEvent=null):void {
			this.configuracao.abaCoopPiloto.transferirDePara(this.configuracao.lstCoopRelDestino, this.configuracao.lstCoopRelOrigem);
		}
		
		private function retornoObterSingulares(event:ResultEvent):void {
			this.configuracao.lstCoopRelOrigem.dataProvider = event.result.dados.listaSingular;
			this.configuracao.lstCoopRelDestino.dataProvider = new ArrayCollection();
		}
		
		protected function onChangeOptRel(event:MouseEvent):void {
			// visible
			this.configuracao.txtCooperativaDivergencias.visible = this.configuracao.optRelBatSingular.selected;
			this.configuracao.txtCooperativaDivergencias.includeInLayout = this.configuracao.optRelBatSingular.selected;
			this.configuracao.lstCoopRel.visible = this.configuracao.optRelBatLista.selected;
			this.configuracao.lstCoopRel.includeInLayout = this.configuracao.optRelBatLista.selected;
			// reset
			this.configuracao.txtCooperativaDivergencias.text = "";
			(this.configuracao.lstCoopRelOrigem.dataProvider as ArrayCollection).addAll(this.configuracao.lstCoopRelDestino.dataProvider as ArrayCollection);
			(this.configuracao.lstCoopRelDestino.dataProvider as ArrayCollection).removeAll();
			this.configuracao.abaCoopPiloto.ordenarList(this.configuracao.lstCoopRelOrigem);
			// /reset
			//this.configuracao.boxAcoesIntervalo.visible = this.configuracao.optAcoesIntervalo.selected;
		}
		
		private function atualizarProgresso(atual:int, total:int, totalRet:int):void {
			this.configuracao.progressoBatimento.label = 'Progresso: '+atual+'/'+total+' ('+totalRet+')';
			this.configuracao.progressoBatimento.setProgress(atual, total);
		}
		
		private function retornoConsultarCooperativasBatimento(event:ResultEvent):void {
			var lstCooperativas:ArrayCollection = event.result.dados.cooperativas;
			atualizarProgresso(0, lstCooperativas.length, 0);
			for(var i:int=0; i < lstCooperativas.length; i++){
				this.cooperativasBat.push(lstCooperativas[i]);
			}
			if (lstCooperativas.length > 0) {
				enviarExecucao();
			}
		}
		
		private function enviarExecucao():void {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cooperativa = this.cooperativasBat[this.posicao];
			this.configuracao.servico.verificarCooperativaBatimento(dto);	
		}
		
		private function retornoVerificarCooperativaBatimento(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			var coop:Object = event.result.dados.cooperativa;
			if (coop != null) {
				this.cooperativasBatRet.push(coop);
			}
			
			atualizarProgresso(this.posicao+1, this.cooperativasBat.length, this.cooperativasBatRet.length);
			this.configuracao.txtCooperativasComDivergencias.text = this.cooperativasBatRet.join(',');
			this.posicao++;
			
			if (this.posicao < this.cooperativasBat.length) {
				enviarExecucao();
			} else {
				if (this.cooperativasBatRet.length == 0) {
					Alerta.show("Nenhuma divergência encontrada!", "ATENÇÃO", Alerta.ALERTA_INFORMACAO);
					return;
				}
				var dto:ParametroDTO = new ParametroDTO();
				dto.dados.cooperativa = this.cooperativasBatRet.join(',');
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelBatimentoSQLxDB2ServicoRemote", 
					dto, "CCA_RelBatimentoSQLxDB2", this.configuracao.servico.destino, "Emitindo relatório...", null, false);
			}
			
		}
		
		private function consultarBatimentoSaldos(event:Object):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			if (this.configuracao.txtCooperativaBatimentoSaldos.text == "") {
				Alerta.show("Informe uma cooperativa.", "ATENÇÃO", Alerta.ALERTA_ERRO);
				return;
			}
			dto.dados.cooperativa = this.configuracao.txtCooperativaBatimentoSaldos.text;
			this.configuracao.servico.consultarBatimentoSaldos(dto);	
		}
		
		private function retornoConsultarBatimentoSaldos(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			this.configuracao.grdBatimentoSaldos.dataProvider = event.result.dados.lista as ArrayCollection;
			this.configuracao.grdBatimentoSaldos.dataProvider.refresh();
			this.configuracao.btCSVSaldo.enabled = (this.configuracao.grdBatimentoSaldos.dataProvider.length > 0);
		}
		
	}
}