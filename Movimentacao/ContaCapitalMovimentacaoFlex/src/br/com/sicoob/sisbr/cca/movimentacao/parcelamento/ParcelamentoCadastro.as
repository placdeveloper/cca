package br.com.sicoob.sisbr.cca.movimentacao.parcelamento
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.util.DataUtilRelatorios;
	
	public class ParcelamentoCadastro extends ParcelamentoCadastroView{		
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.ParcelamentoContaCapitalServico";
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private const COD_MODO_LANCAMENTO_VIA_CONTA:String = "2";		
		public 	var janMovimentacaoParcelamentoRenEditar:MovimentacaoParcelamentoRenEditar;
		private var _dadosDefinicoes:Object = new Object();
		private var dataAtualProduto:String;
		public var vlrTotalParcela:Number;
		public var destinoPai:Object;
		
		
		public function ParcelamentoCadastro(){
			super();			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(event: FlexEvent): void {
			super.init(event);	
			controlarTela();
			configurarServico();			
		}
		
		private function configurarServico():void {
			//PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
			onDestinoRecuperado(janMovimentacaoParcelamentoRenEditar.destinoPai);
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
			
			reqDTO.dados.idContaCapital = janMovimentacaoParcelamentoRenEditar.idContaCapital;
			reqDTO.dados.idPessoaLegado = janMovimentacaoParcelamentoRenEditar.idPessoaLegado;			
			
			servico.obterDefinicoesParcela(reqDTO);
		}
		
		
		private function retornoObterDefinicoesParcela(event:ResultEvent):void {
			
			_dadosDefinicoes = event.result.dados;			
			
			dataAtualProduto = _dadosDefinicoes.dataAtualProduto;
			cboTipoIntegParcela.dataProvider = _dadosDefinicoes["comboTipoInteg"] as ArrayCollection;			
			cboMatriculaParcela.dataProvider = _dadosDefinicoes["comboTrabalha"] as ArrayCollection;	
			cboCcoParcela.dataProvider = _dadosDefinicoes["comboCco"] as ArrayCollection;
			
			cboTipoIntegParcela.dataProvider.removeItemAt(3)
			
			labelCcoParcela.visible = false;
			cboCcoParcela.visible = false;
			
			cboMatriculaParcela.visible = false;
			labelMatriculaParcela.visible = false;
			
			MostraCursor.removeBusyCursor();
		}
		
		
		/**
		 * Retorna os valores da camada de apresentacao para o estado inicial 
		 */
		private function cancelar(evt:MouseEvent):void {	
			dtVencimento.selectedDate = dtVencimento.dataDefault;
			cboTipoIntegParcela.selectedIndex = 0;
			vlrParcela.text = ""; 	
			
			labelCcoParcela.visible = false;
			cboCcoParcela.visible = false;
			
			labelMatriculaParcela.visible = false;
			cboMatriculaParcela.visible = false;
		}		
		
		private function controlarTela():void {
			servico.obterDefinicoesParcela.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesParcela);			
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, verificarDiaUtil);
			cboTipoIntegParcela.addEventListener(Event.CHANGE, onChangeCboTipoInteg);
			servico.verificarDiaUtil.addEventListener(ResultEvent.RESULT, retornoVerificarDiaUtil);
			
			cboTipoIntegParcela.selectedIndex = 0;
			vlrParcela.text = ""; 				
			
			cboCcoParcela.visible = false;
			labelCcoParcela.visible = false;
			
			labelMatriculaParcela.visible = false;
			cboMatriculaParcela.visible = false;		
			
			dtVencimento.selectedDate = DataUtilRelatorios.somarDiasData(DataUtilRelatorios.stringBrToDate(janMovimentacaoParcelamentoRenEditar.dataUltimaParcela), 30);
		}
		
		private function retornoVerificarDiaUtil(event:ResultEvent):void {			
			var bolDataUtil:String = event.result.dados["bolDataUtil"];
			
			if(bolDataUtil=="1"){
				incluir();
			}else{
				Alerta.show("A data de vencimento da nova parcela deve corresponder a um dia útil.", "ATENÇÃO", Alerta.ALERTA_OK, dtVencimento);
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
			
			labelCcoParcela.visible = false;
			cboCcoParcela.visible = false;
			
			labelMatriculaParcela.visible = false;
			cboMatriculaParcela.visible = false;
			
			switch (cboTipoIntegParcela.selectedItem["codListaItem"]){
				case "2":
					cboCcoParcela.visible = true;
					labelCcoParcela.visible = true;
					cboMatriculaParcela.visible = false;	
					labelMatriculaParcela.visible = false;
					break;
				case "3":
					cboCcoParcela.visible = false;
					labelCcoParcela.visible = false;
					cboMatriculaParcela.visible = true;
					labelMatriculaParcela.visible = true;
					break;
				default:
					cboCcoParcela.visible = false;
					labelCcoParcela.visible = false;
					cboMatriculaParcela.visible = false;
					labelMatriculaParcela.visible = false;
			}
		}			
		
		private function incluir():void {
			if(validar()) {
				var dataVencimentoOrdenacao:String=FormataData.formataData(dtVencimento.selectedDate, "YYYYMMDD");				
				var idTipoInteg:Number=cboTipoIntegParcela.dataProvider[cboTipoIntegParcela.selectedIndex].codListaItem;
				janMovimentacaoParcelamentoRenEditar.numUltimaParcela=janMovimentacaoParcelamentoRenEditar.numUltimaParcela+1;
				
				var numContaCorrente:Number = new Number(cboCcoParcela.text);
				
				janMovimentacaoParcelamentoRenEditar.listaParcelas.addItem({numParcela:janMovimentacaoParcelamentoRenEditar.numUltimaParcela,dataVencimento:FormataData.formataData(dtVencimento.selectedDate, "DD/MM/YYYY"),descSituacao:"EM ABERTO",dataSituacao:dataAtualProduto,valorParcela:vlrParcela.valor,idTipoInteg:idTipoInteg,descNumMatriculaFunc:cboMatriculaParcela.text,numContaCorrente:numContaCorrente,dataVencimentoOrdenacao:dataVencimentoOrdenacao,selecionado:false,idSituacaoParcelamento:1});				
				janMovimentacaoParcelamentoRenEditar.atualizaValores();
				fecharJanelaAtual();
			}
		}		
		
		private function verificarDiaUtil(evt:MouseEvent){
			if(dtVencimento.selectedDate == null) {
				Alerta.show("A data de vencimento é obrigatória.", "ATENÇÃO", Alerta.ALERTA_OK, dtVencimento);
				return;
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
			var dataProdFormat:String = dataAtualProduto.substr(6,4)+dataAtualProduto.substr(3,2)+dataAtualProduto.substr(0,2);						
			
			if(dataVencimento < dataProdFormat){
				Alerta.show("A data de vencimento da nova parcela deve ser maior ou igual a data do produto.", "ATENÇÃO", Alerta.ALERTA_OK, vlrParcela);
				return false;
			}						
			
			if(vlrParcela.valor <= 0) {
				Alerta.show("O campo Valor da Parcela é obrigatório", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}			
			
			if(vlrParcela.valor > vlrTotalParcela) {
				Alerta.show("Valor da parcela deve ser menor ou igual a R$ " + FormataNumero.formata(vlrTotalParcela,2), "ATENÇÃO", Alerta.ALERTA_OK, vlrParcela);
				return false;
			}			
			
			if(cboTipoIntegParcela.text == "") {
				Alerta.show("A forma de pagamento é obrigatória.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoIntegParcela);
				return false;
			}						
			
			if(cboTipoIntegParcela.selectedItem.codListaItem == 2){				
				if(cboCcoParcela.text == ""){
					Alerta.show("O número da conta corrente é obrigatória para a forma de débito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, cboCcoParcela);
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


