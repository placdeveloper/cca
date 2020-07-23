package br.com.sicoob.sisbr.cca.movimentacao.parcelamento
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.movimentacao.plataforma.ParcelamentoEdicao;
	import br.com.sicoob.sisbr.cca.util.DataUtilRelatorios;
	import br.com.sicoob.sisbr.cca.vo.ParcelamentoRenVO;
	
	public class MovimentacaoParcelamentoRenEditar extends MovimentacaoParcelamentoRenEditarView{		
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.ParcelamentoContaCapitalServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var _dadosDefinicoes:Object = new Object();
		private var _dadosRetorno:Object = new Object();
		[Bindable]
		public var listaParcelas:ArrayCollection;
		public 	var janMovimentacaoParcelamentoRen:MovimentacaoParcelamentoRen;
		private var janela:Janela = new Janela();
		private var parc:ParcelamentoCadastro = new ParcelamentoCadastro();			
		public var dataUltimaParcela:String;
		public var idContaCapital:Number;
		public var idPessoaLegado:Number;
		public var idTipoParcelamento:Number;		
		public var numUltimaParcela:Number;		
		public var listaParcelasAux:ArrayCollection;
		public var registroAlterado:Boolean;
		private var vlrTotalParcelado:Number;
		private var vlrTotalParcela:Number;		
		private var zeroSelecionado:Boolean;
		public 	var parcelamentoEdicao:ParcelamentoEdicao;
		public var bolPlataforma:Boolean=false;
		public var destinoPai:DestinoVO;
		
		public function MovimentacaoParcelamentoRenEditar(){
			super();			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event: FlexEvent): void {
			configurarServico();			
			controlarTela();
		}
		
		private function configurarServico():void {
			if (destinoPai == null){
				destinoPai = janMovimentacaoParcelamentoRen.destino;				
			}

			onDestinoRecuperado(destinoPai);			
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
			
			listarParcelas();				
		}
		
		private function fechar(evt:MouseEvent):void {
			
			if(registroAlterado) {
				Alerta.show("Informações não foram salvas! Deseja realmente sair?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, confirmarFechar);
			} else {
				fecharJanelaAtual();
			}						
		}		
		
		private function confirmarFechar(evt:MouseEvent):void {
			MostraCursor.removeBusyCursor();
			super.fecharJanela();
		}
		
		private function fecharJanelaAtual():void {
			MostraCursor.removeBusyCursor();
			super.fecharJanela();
		}		
		
		private function controlarTela():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOK.addEventListener(MouseEvent.CLICK, gravarParcelas);
			btIncluir.addEventListener(MouseEvent.CLICK, incluirParcela);
			btEcluir.addEventListener(MouseEvent.CLICK, validaExcluirParcela);			
			
			chkSelecionaTodos.addEventListener(MouseEvent.CLICK,chkSelecionaTodos_Click);
			servico.listarParcelas.addEventListener(ResultEvent.RESULT, retornoListarParcelas);
			servico.gravarParcelas.addEventListener(ResultEvent.RESULT, retornoGravarParcelas);
			
			registroAlterado = false;
		}
		
		public function listarParcelas():void {			
			if(bolPlataforma){
				
				reqDTO.dados.numMatricula = this.numContaCapital.text;
				reqDTO.dados.idContaCapital = parcelamentoEdicao.idContaCapital.text;
				reqDTO.dados.idTipoParcelamento = parcelamentoEdicao.gridParcelamento.selectedItem.idTipoParcelamento;
				reqDTO.dados.numParcelamento = parcelamentoEdicao.gridParcelamento.selectedItem.numParcelamento;			
				reqDTO.dados.idSituacaoParcelamento = 1;											
			}else{
				reqDTO.dados.idContaCapital = janMovimentacaoParcelamentoRen.procurarCCA.resultadoPesquisaVO.idContaCapital;
				reqDTO.dados.idTipoParcelamento = janMovimentacaoParcelamentoRen.gridParcelamento.selectedItem.idTipoParcelamento;
				reqDTO.dados.numParcelamento = janMovimentacaoParcelamentoRen.gridParcelamento.selectedItem.numParcelamento;			
				reqDTO.dados.idSituacaoParcelamento = 1;											
			}
			
			this.servico.listarParcelas(reqDTO);							
		}
		
		private function retornoListarParcelas(event:ResultEvent):void {
			
			if(event.result.dados["msg"]) {
				Alerta.show(event.result.dados["msg"], "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
			}									
			
			if(idPessoaLegado==0){
				idPessoaLegado = event.result.dados["idPessoaLegado"];							
			}
			
			this.listaParcelas = new ArrayCollection();			
			this.listaParcelas.list = event.result.dados["listaParcelas"] as ArrayCollection;					
			
			gridParcelas.columns[1].labelFunction = formataParcela;
			gridParcelas.columns[6].labelFunction = formataTipoInteg;
			
			gridParcelas.dataProvider = listaParcelas.list;		
			
			for(var i:int=0;i<listaParcelas.length;i++){
				listaParcelas[i].selecionado = false;	
			}				
			
			atualizaValores();
			chkSelecionaTodos.selected = false;				
			MostraCursor.removeBusyCursor();						
		}	
		
		private function incluirParcela(evt:MouseEvent):void{					
			
			vlrTotalParcelado = 0;
			
			for(var i:int=0; i < listaParcelas.length; i++){
				if(listaParcelas[i].idSituacaoParcelamento == 1){
					vlrTotalParcelado = vlrTotalParcelado+listaParcelas[i].valorParcela;							
					dataUltimaParcela = listaParcelas[i].dataVencimento;
				}
			}	
			
			vlrTotalParcela = new Number(valorAberto.text) - vlrTotalParcelado;
			
			if(new Number(vlrTotalParcela.toFixed(2)) <= 0){
				Alerta.show("Não há valor a parcelar.","ATENÇÃO",Alerta.ALERTA_OK, null);		
				return;
			}
			
			janela = new Janela();
			janela.title = "INCLUIR PARCELA";
			janela.removeAllChildren();
			janela.addChild(parc);
			
			parc.janMovimentacaoParcelamentoRenEditar = this;			
			parc.vlrParcela.text = ""; 			
			parc.cboTipoIntegParcela.selectedIndex = 0;
			
			parc.dtVencimento.selectedDate = DataUtilRelatorios.somarDiasData(DataUtilRelatorios.stringBrToDate(dataUltimaParcela), 30);
			
			parc.vlrTotalParcela = new Number(vlrTotalParcela.toFixed(2));
			
			parc.cboCcoParcela.visible = false;
			parc.labelCcoParcela.visible = false;
			
			parc.labelMatriculaParcela.visible = false;
			parc.cboMatriculaParcela.visible = false;						
			
			janela.abrir(this,true,true);
			
		}				
		
		public function chkSelecionaTodos_Click(evt:MouseEvent=null):void{
			
			gridParcelas.dataProvider.refresh();		
			
			if(listaParcelas != null){
				for(var i:int=0;i<listaParcelas.length;i++){
					if(listaParcelas[i].idSituacaoParcelamento=="1"){
						if(chkSelecionaTodos.selected){
							if(i == 0){
								listaParcelas[i].selecionado = true;
							}else{
								listaParcelas[i].selecionado = true;
							}
						}else{
							listaParcelas[i].selecionado = false;
						}
					}					
				}			
			}
		}		
		
		private function formataParcela(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";	
			
			if(vlr.numParcela == 0){
				retorno = vlr.numParcela + " (à vista)";				
			}else{
				retorno = vlr.numParcela;				
			}			
			return retorno;
		}		
		
		private function formataTipoInteg(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";		
			
			switch(vlr.idTipoInteg.toString())
			{
				case "1":
				{
					retorno = "CAIXA";
					break;
				}			
				case "2":
				{
					retorno = "CONTA CORRENTE - " + vlr.numContaCorrente;
					break;
				}			
				case "3":
				{
					retorno = "FOLHA - " + vlr.descNumMatriculaFunc;
					break;
				}			
				case "4":
				{
					retorno = "COBRANÇA";
					break;
				}			
				case "5":
				{
					retorno = "MIGRAÇÃO";
					break;
				}			
				case "6":
				{
					retorno = "BANCO";
					break;
				}			
				case "7":
				{
					retorno = "RATEIO";
					break;
				}			
				case "8":
				{
					retorno = "RESERVA";
					break;
				}			
				case "9":
				{
					retorno = "ESTORNO DEVOLUÇÃO";
					break;
				}										
			}
			return retorno;
		}
		
		private function validaExcluirParcela(evt:MouseEvent):void {
			
			var bolParcSelecionada:Boolean;
			bolParcSelecionada = false;
			
			for(var i:int=0;i<listaParcelas.length;i++){
				if(listaParcelas[i].selecionado && listaParcelas[i].idSituacaoParcelamento == 1){
					bolParcSelecionada = true;					
				}
			}
			
			if(bolParcSelecionada){
				Alerta.show("Confirma a exclusão das parcelas selecionadas?", "Excluir registros", Alerta.ALERTA_PERGUNTA, null, excluirParcelas);		
			}else{
				Alerta.show("É obrigatório a seleção de pelo menos uma parcela.","ATENÇÃO", Alerta.ALERTA_OK, null);							
			}				
			
		}		
		
		public function excluirParcelas(evt:Event):void{
			
			for(var i:int=0;i<listaParcelas.length;i++){
				if(listaParcelas[i].selecionado){			
					listaParcelas[i].idSituacaoParcelamento = 6;
					listaParcelas[i].descSituacao = "EXCLUÍDA";					
					listaParcelas[i].selecionado = false;
				}				
			}					
			listaParcelas.refresh();
			gridParcelas.dataProvider = listaParcelas.list;		
			registroAlterado = true;
			atualizaValores();
		}			
		
		public function atualizaValores():void{
			vlrTotalParcelado = 0;
			var numParcelaAberto:int=0;				
			listaParcelasAux = new ArrayCollection();
			
			var sort:Sort = new Sort(); 				
			sort.fields = [new SortField("numParcela")];									
			listaParcelas.sort = sort;
			listaParcelas.refresh();				
			listaParcelasAux = new ArrayCollection();			
			
			if(listaParcelas.length > 0){				
				var numParcela:int=1;				
				for(var i:int=0; i < listaParcelas.length; i++){
					if(listaParcelas[i].idSituacaoParcelamento == 1){
						vlrTotalParcelado = vlrTotalParcelado+listaParcelas[i].valorParcela;
						numParcelaAberto++;
					}
					listaParcelasAux.addItem(listaParcelas[i]);
					dataUltimaParcela = listaParcelas[i].dataVencimento;
				}	
				
				vlrTotalParcelado = new Number(vlrTotalParcelado.toFixed(2));
				vlrTotalParcela = new Number(valorAberto.text) - vlrTotalParcelado;
				valorParcelar.text = (new Number(valorAberto.text) - vlrTotalParcelado).toFixed(2);
			} 
			gridParcelas.dataProvider = listaParcelasAux.list;	
			
			qtdParcelas.text = numParcelaAberto.toFixed(0);			
			
		}		
		
		public function gravarParcelas(evt:Event):void{
			
			var voParcela:ParcelamentoRenVO;
			MostraCursor.setBusyCursor("Gravando alterações nas parcelas!", Application.application);
			var arrParcelamentoVO:ArrayCollection = new ArrayCollection();									
			var reqDTOGravar:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			
			if(bolPlataforma){
				reqDTOGravar.dados.idContaCapital = parcelamentoEdicao.idContaCapital.text;
				reqDTOGravar.dados.numParcelamento = parcelamentoEdicao.gridParcelamento.selectedItem.numParcelamento;
				reqDTOGravar.dados.idTipoParcelamento = parcelamentoEdicao.gridParcelamento.selectedItem.idTipoParcelamento;
				reqDTOGravar.dados.numContaCapital = new Number(parcelamentoEdicao.numCCA.text);								
			}else{
				reqDTOGravar.dados.idContaCapital = janMovimentacaoParcelamentoRen.procurarCCA.resultadoPesquisaVO.idContaCapital;
				reqDTOGravar.dados.numParcelamento = janMovimentacaoParcelamentoRen.gridParcelamento.selectedItem.numParcelamento;
				reqDTOGravar.dados.idTipoParcelamento = janMovimentacaoParcelamentoRen.gridParcelamento.selectedItem.idTipoParcelamento;
				reqDTOGravar.dados.numContaCapital = janMovimentacaoParcelamentoRen.procurarCCA.resultadoPesquisaVO.numContaCapital;				
			}
			
			for(var i:int=0;i<listaParcelas.length;i++){
				voParcela = new ParcelamentoRenVO();
				
				voParcela.numParcela = gridParcelas.dataProvider[i].numParcela;
				voParcela.dataVencimento = gridParcelas.dataProvider[i].dataVencimento;
				voParcela.valorParcela = gridParcelas.dataProvider[i].valorParcela;
				voParcela.idTipoInteg = gridParcelas.dataProvider[i].idTipoInteg;
				voParcela.numContaCorrente = gridParcelas.dataProvider[i].numContaCorrente;
				voParcela.descNumMatriculaFunc = gridParcelas.dataProvider[i].descNumMatriculaFunc;
				voParcela.dataVencimentoOrdenacao = gridParcelas.dataProvider[i].dataVencimentoOrdenacao;
				voParcela.idSituacaoParcelamento = gridParcelas.dataProvider[i].idSituacaoParcelamento;
				voParcela.idMotivoDevolucao = gridParcelas.dataProvider[i].idMotivoDevolucao;
				
				if(bolPlataforma){
					voParcela.idTipoParcelamento = parcelamentoEdicao.gridParcelamento.selectedItem.idTipoParcelamento;
					voParcela.numParcelamento = parcelamentoEdicao.gridParcelamento.selectedItem.numParcelamento;
					voParcela.idContaCapital = new Number(parcelamentoEdicao.idContaCapital.text);
					voParcela.numContaCapital = new Number(parcelamentoEdicao.numCCA.text);
					
				}else{
					voParcela.idTipoParcelamento = janMovimentacaoParcelamentoRen.gridParcelamento.selectedItem.idTipoParcelamento;
					voParcela.numParcelamento = janMovimentacaoParcelamentoRen.gridParcelamento.selectedItem.numParcelamento;
					voParcela.idContaCapital = janMovimentacaoParcelamentoRen.procurarCCA.resultadoPesquisaVO.idContaCapital;
					voParcela.numContaCapital = janMovimentacaoParcelamentoRen.procurarCCA.resultadoPesquisaVO.numContaCapital;					
				}
				
				voParcela.idParcelamento = gridParcelas.dataProvider[i].idParcelamento
				
				arrParcelamentoVO.addItem(voParcela);new Number(valorAberto.text) - vlrTotalParcelado;
			}		
			
			reqDTOGravar.dados.listaParcelas = arrParcelamentoVO;	
			
			this.servico.gravarParcelas(reqDTOGravar);		
		}					
		
		private function retornoGravarParcelas(event:ResultEvent):void {
			
			if(event.result.dados["msg"]) {
				Alerta.show(event.result.dados["Erro ao alterar parcela"], "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
			}									
			
			Alerta.show("Dados alterados com sucesso.", "SUCESSO", Alerta.ALERTA_OK);	
			
			if(bolPlataforma){
				parcelamentoEdicao.limparGridParcelamento();				
			}else{
				janMovimentacaoParcelamentoRen.limparGridParcelamento();				
			}
						
			fecharJanelaAtual();
		}			
		
	}
}


