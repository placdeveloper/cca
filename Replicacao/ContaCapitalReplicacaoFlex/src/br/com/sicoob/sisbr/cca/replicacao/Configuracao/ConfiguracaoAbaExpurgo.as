package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.formatters.NumberFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	
	public class ConfiguracaoAbaExpurgo {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var nf:NumberFormatter = new NumberFormatter();
		
		private var cooperativas:Array = [];
		private var posicao:int = 0;
		
		public function ConfiguracaoAbaExpurgo(configuracao:ConfiguracaoReplicacaoCapital)	{
			this.configuracao = configuracao;
			
			this.configuracao.servico.consultarExpurgo.addEventListener(ResultEvent.RESULT, retornoConsultarExpurgo);
			this.configuracao.servico.expurgarCooperativa.addEventListener(ResultEvent.RESULT, retornoExpurgarCooperativa);
			
			this.configuracao.txtCooperativaExpurgo.addEventListener(FlexEvent.ENTER, consultarExpurgo);
			this.configuracao.btConsultarExpurgo.addEventListener(MouseEvent.CLICK, consultarExpurgo);
			this.configuracao.chkSelecionarTodas.addEventListener(MouseEvent.CLICK, selecionarTodas);
			this.configuracao.btExpurgar.addEventListener(MouseEvent.CLICK, expurgar);
		}
		
		private function initNumberFormatter():void {
			nf.thousandsSeparatorFrom = '.';
			nf.thousandsSeparatorTo = '.';
		}
		
		private function consultarExpurgo(evt:Object=null):void {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cooperativa = this.configuracao.txtCooperativaExpurgo.text;
			this.configuracao.servico.consultarExpurgo(dto);			
		}
		
		private function retornoConsultarExpurgo(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			var dados:ArrayCollection = event.result.dados.vos;
			this.configuracao.grdExpurgo.dataProvider = dados;
			this.configuracao.grdExpurgo.labelFunction = formataDataGrid;
			this.configuracao.grdExpurgo.dataProvider.refresh();
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			if (col.dataField != null) {
				switch(col.dataField) {
					case "quantidade":
						if (obj[col.dataField] != null) {
							retorno = nf.format(obj[col.dataField]);	
						} else {
							retorno = "";						
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
			}
			return retorno;
		}
		
		private function selecionarTodas(evt:MouseEvent=null):void {
			var dados:Object = this.configuracao.grdExpurgo.dataProvider;
			if (dados != null) {
				for (var i:int=0; i < dados.length;i++) {
					dados[i].selecionado = this.configuracao.chkSelecionarTodas.selected;								
				}
				dados.refresh();
			}
		}
		
		private function expurgar(evt:MouseEvent=null):void {
			this.cooperativas = [];
			this.posicao = 0;
			var dados:Object = this.configuracao.grdExpurgo.dataProvider;
			if (dados != null) {
				for (var i:int=0; i < dados.length;i++) {
					if (dados[i].selecionado) {
						cooperativas.push(dados[i].numCooperativa);
					}
				}
			}
			if (cooperativas.length > 0) {
				Alerta.show("Confirma expurgo das cooperativas selecionadas?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, expurgarCooperativa);
			} else {
				Alerta.show("Selecione as cooperativas que devem ser expurgadas da replicação.", "ATENÇÃO", Alerta.ALERTA_OK);				
			}
		}
		
		private function expurgarCooperativa(evt:MouseEvent=null):void {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cooperativa = new Number(this.cooperativas[this.posicao]);
			this.configuracao.servico.expurgarCooperativa(dto);			
		}
		
		private function retornoExpurgarCooperativa(event:ResultEvent):void {
			this.posicao++;
			if (this.posicao < this.cooperativas.length) {
				expurgarCooperativa();
			} else {
				consultarExpurgo();
				Alerta.show("Cooperativas expurgadas com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO);
			}
		}
		
	}
}