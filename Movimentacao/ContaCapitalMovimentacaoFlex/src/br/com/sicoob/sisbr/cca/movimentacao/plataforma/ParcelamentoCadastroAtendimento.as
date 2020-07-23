package br.com.sicoob.sisbr.cca.movimentacao.plataforma
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.util.DataUtilRelatorios;
	
	public class ParcelamentoCadastroAtendimento extends ParcelamentoCadastroAtendimentoView {		
						
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.SubscricaoContaCapitalServico";
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private const COD_MODO_LANCAMENTO_VIA_CONTA:String = "2";		
		public 	var movimentacaoSubscricaoRen:SubscricaoRenEdicao;
		
		public function ParcelamentoCadastroAtendimento() {
			super();			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(event: FlexEvent):void {
			super.init(event);	
			configurarServico();			
			controlarTela();
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
		}
		
		/**
		 * Retorna os valores da camada de apresentacao para o estado inicial 
		 */
		private function cancelar(evt:MouseEvent):void {	
			dtVencimento.selectedDate = dtVencimento.dataDefault;
			cboTipoIntegParcela.selectedIndex = 0;
			vlrParcela.text = ""; 	
			
			labelCcoParcela.visible = false;
			controleCcoParcela.visible = false;
			
			labelMatriculaParcela.visible = false;
			cboMatriculaParcela.visible = false;
		}		
		
		private function controlarTela():void {
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, verificarDiaUtil);
			cboTipoIntegParcela.addEventListener(Event.CHANGE, onChangeCboTipoInteg);
			servico.verificarDiaUtil.addEventListener(ResultEvent.RESULT, retornoVerificarDiaUtil);
			
			cboTipoIntegParcela.selectedIndex = 0;
			vlrParcela.text = ""; 				
			
			controleCcoParcela.visible = false;
			labelCcoParcela.visible = false;
			
			labelMatriculaParcela.visible = false;
			cboMatriculaParcela.visible = false;		
			
			dtVencimento.selectedDate = DataUtilRelatorios.somarDiasData(DataUtilRelatorios.stringBrToDate(movimentacaoSubscricaoRen.dataUltimaParcela), 30);
		}
		
		private function retornoVerificarDiaUtil(event:ResultEvent):void {			
			var bolDataUtil:String = event.result.dados["bolDataUtil"];
			
			if(bolDataUtil=="1"){
				incluir();
			}else{
				Alerta.show("A data da parcela à vista deve ser um dia útil.", "ATENÇÃO", Alerta.ALERTA_OK, dtVencimento);
				return;
			}
		}
		
		private function fechar(evt:MouseEvent):void {
			fecharJanelaAtual();
		}		

		private function fecharJanelaAtual():void {
			MostraCursor.removeBusyCursor();
			super.fecharJanela();
		}		
		
		private function onChangeCboTipoInteg(evt:Event):void {
			
			if(cboTipoIntegParcela.selectedItem != null){
				
				labelCcoParcela.visible = false;
				controleCcoParcela.visible = false;
				
				labelMatriculaParcela.visible = false;
				cboMatriculaParcela.visible = false;
				
				switch (cboTipoIntegParcela.selectedItem["codListaItem"]){
					case "2":
						controleCcoParcela.visible = true;
						labelCcoParcela.visible = true;
						cboMatriculaParcela.visible = false;	
						labelMatriculaParcela.visible = false;
						break;
					case "3":
						controleCcoParcela.visible = false;
						labelCcoParcela.visible = false;
						cboMatriculaParcela.visible = true;
						labelMatriculaParcela.visible = true;
						break;
					default:
						controleCcoParcela.visible = false;
						labelCcoParcela.visible = false;
						cboMatriculaParcela.visible = false;
						labelMatriculaParcela.visible = false;
				}
				
			}
				
		}			
		
		private function incluir():void {
			if(validar()) {
				var dataVencimentoOrdenacao:String=FormataData.formataData(dtVencimento.selectedDate, "YYYYMMDD");				
				var idTipoInteg:Number=cboTipoIntegParcela.dataProvider[cboTipoIntegParcela.selectedIndex].codListaItem;
				
				movimentacaoSubscricaoRen.listaParcelas.addItem({dataVencimento:FormataData.formataData(dtVencimento.selectedDate, "DD/MM/YYYY"),valorParcela:vlrParcela.valor,idTipoInteg:idTipoInteg,descNumMatriculaFunc:cboMatriculaParcela.text,numContaCorrente:controleCcoParcela.selecaoCCOVO.numContaCorrente,dataVencimentoOrdenacao:dataVencimentoOrdenacao});				
				movimentacaoSubscricaoRen.atualizaValores();				
				fecharJanelaAtual();
			}
		}		
		
		private function verificarDiaUtil(evt:MouseEvent){
			if(dtVencimento.selectedDate == null) {
				Alerta.show("A data de vencimento é obrigatória.", "ATENÇÃO", Alerta.ALERTA_OK, dtVencimento);
				return false;
			}						
			reqDTO.dados.dtParcela = dtVencimento.selectedDate;			
			this.servico.verificarDiaUtil(reqDTO);			
		}
		
		private function validar():Boolean {			
			if(dtVencimento.selectedDate == null) {
				Alerta.show("A data de vencimento é obrigatória.", "ATENÇÃO", Alerta.ALERTA_OK, dtVencimento);
				return false;
			}						
			
			var dataVencimento:String = FormataData.formataData(dtVencimento.selectedDate, "YYYYMMDD");
			var dataSubsc:String = movimentacaoSubscricaoRen.dataSubscricao.text.substr(6,4)+movimentacaoSubscricaoRen.dataSubscricao.text.substr(3,2)+movimentacaoSubscricaoRen.dataSubscricao.text.substr(0,2);			
			
			if(dataVencimento < dataSubsc){
				Alerta.show("A data da parcela deve ser maior do que a da parcela à vista.", "ATENÇÃO", Alerta.ALERTA_OK, vlrParcela);
				return false;
			}						
			
			if(vlrParcela.valor <= 0) {
				Alerta.show("Valor da parcela deve ser maior que 0,00.", "ATENÇÃO", Alerta.ALERTA_OK, vlrParcela);
				return false;
			}			
			
			if(vlrParcela.valor > movimentacaoSubscricaoRen.vlrParcelar.valor) {
				Alerta.show("Valor da parcela deve ser menor ou igual a R$ " + movimentacaoSubscricaoRen.vlrParcelar.text, "ATENÇÃO", Alerta.ALERTA_OK, vlrParcela);
				return false;
			}			
			
			if(cboTipoIntegParcela.text == "") {
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoIntegParcela);
				return false;
			}				
			
			if(cboTipoIntegParcela.selectedItem == null){
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoIntegParcela);
				return false;				
			}				
			
			if(cboTipoIntegParcela.selectedItem.codListaItem == 2){				
				if(controleCcoParcela.selecaoCCOVO.numContaCorrente == 0){
					Alerta.show("O número da conta corrente é obrigatória para a forma de débito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, controleCcoParcela);
					return false;										
				}
			}
			
			if(cboTipoIntegParcela.selectedItem.codListaItem == 3){				
				if(cboMatriculaParcela.text == ""){
					Alerta.show("O número matrícula é obrigatória para a forma de débito folha.", "ATENÇÃO", Alerta.ALERTA_OK, cboMatriculaParcela);
					return false;										
				}
			}			
			
			return true;
		}						

	}
}