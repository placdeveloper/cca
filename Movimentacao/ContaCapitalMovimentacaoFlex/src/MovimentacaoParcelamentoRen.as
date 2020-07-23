package
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
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.movimentacao.parcelamento.MovimentacaoParcelamentoRenEditar;
	import br.com.sicoob.sisbr.cca.movimentacao.parcelamento.MovimentacaoParcelamentoRenView;
	import br.com.sicoob.sisbr.cca.vo.ParcelamentoRenVO;
	
	public class MovimentacaoParcelamentoRen extends MovimentacaoParcelamentoRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.ParcelamentoContaCapitalServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var reqDTOParcela:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var _dadosDefinicoes:Object = new Object();
		private var _dadosRetorno:Object = new Object();
		[Bindable]
		public var listaParcelamento:ArrayCollection;
		[Bindable]
		public var listaParcelas:ArrayCollection;
		public var listaParcelasAux:ArrayCollection;
		private var janela:Janela = new Janela();
		private var janParcelamento:MovimentacaoParcelamentoRenEditar = new MovimentacaoParcelamentoRenEditar();	
		private var numUltimaParcela:Number;
		
		public function MovimentacaoParcelamentoRen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();			
			controlarServico();			
		}
		
		private function configurarServico():void {
			//PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
			onDestinoRecuperado(this.destino);
			
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
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.procurar.addEventListener(ResultEvent.RESULT, retornoProcurar);
			servico.listarParcelas.addEventListener(ResultEvent.RESULT, retornoListarParcelas);
			servico.cancelarParcelas.addEventListener(ResultEvent.RESULT, retornoAlterarParcelas);
			servico.cancelarTodasParcelas.addEventListener(ResultEvent.RESULT, retornoAlterarParcelas);
			servico.baixarParcelas.addEventListener(ResultEvent.RESULT, retornoAlterarParcelas);
			
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoObterProcurarCCA);	
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO, retornoObterProcurarCCANaoEcontrado);	
			
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btProcurar.addEventListener(MouseEvent.CLICK, procurar);
			btManutencao.addEventListener(MouseEvent.CLICK, abrirParcelamento);
			btCancParcela.addEventListener(MouseEvent.CLICK, validaCancelarParcela);
			btCancTodas.addEventListener(MouseEvent.CLICK, validaCancelarTodasParcelas);
			btBaixar.addEventListener(MouseEvent.CLICK, validaBaixarParcela);
			
			gridParcelamento.addEventListener(MouseEvent.CLICK, listarParcelas);			
			chkSelecionaTodos.addEventListener(MouseEvent.CLICK,chkSelecionaTodos_Click);
						
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			
			_dadosDefinicoes = event.result.dados;			

			cboTipoParcelamento.dataProvider = _dadosDefinicoes["cboTipoParcelamento"] as ArrayCollection;				
			cboSituacaoParcelamento.dataProvider = _dadosDefinicoes["cboSituacaoParcelamento"] as ArrayCollection;													
			
			MostraCursor.removeBusyCursor();
		}
				
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		private function procurar(evt:MouseEvent=null):void {
			
			if(procurarCCA.txtNumCCA.text == ""){
				Alerta.show("O campo Conta Capital é obrigatório", "ATENÇÃO", Alerta.ALERTA_OK);
				return;
			}
			
			if(cboTipoParcelamento.selectedItem == null) {
				Alerta.show("O campo Tipo Parcelamento é obrigatório", "ATENÇÃO", Alerta.ALERTA_OK);
				return;
			}
			
			if(cboSituacaoParcelamento.selectedItem == null) {
				Alerta.show("O campo Situação Parcelamento é obrigatório", "ATENÇÃO", Alerta.ALERTA_OK);
				return;
			}
										
			reqDTO.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			reqDTO.dados.idTipoParcelamento = new Number(cboTipoParcelamento.selectedItem.codListaItem);
			reqDTO.dados.idSituacaoParcelamento = new Number(cboSituacaoParcelamento.selectedItem.codListaItem);			
			reqDTO.dados.exibirMsgFiltro = null;
				
			limparGridParcelamento();
			
			this.servico.procurar(reqDTO);				
		}		
		
		private function retornoProcurar(event:ResultEvent):void {
			
			if(event.result.dados["msg"]) {
				Alerta.show(event.result.dados["msg"], "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
			}									
						
			this.listaParcelamento = new ArrayCollection();			
			this.listaParcelamento.list = event.result.dados["listaParcelamentos"] as ArrayCollection;		
			configurarBtCancTodas();
			
			gridParcelamento.dataProvider = listaParcelamento.list;				
			MostraCursor.removeBusyCursor();						
		}	
		
		private function configurarBtCancTodas():void {
			btCancTodas.enabled = possuiParcelasEmAberto(); 
		}
		
		private function possuiParcelasEmAberto():Boolean {
			var possuiParcEmAberto:Boolean = false;
			for (var i:int=0; i < listaParcelamento.length; i++) {
				if (listaParcelamento[i].idTipoParcelamento == 1 && listaParcelamento[i].valorAberto > 0) {
					return true;
				}
			}	
			return false;
		}
		
		private function retornoAlterarParcelas(event:ResultEvent):void {
			
			if(event.result.dados["msg"]) {
				Alerta.show(event.result.dados["Erro ao alterar parcela"], "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
			}									
			
			Alerta.show("Dados alterados com sucesso.", "SUCESSO", Alerta.ALERTA_OK);
			
			limparGridParcelamento();
			reqDTO.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			reqDTO.dados.idTipoParcelamento = new Number(cboTipoParcelamento.selectedItem.codListaItem);
			reqDTO.dados.idSituacaoParcelamento = new Number(cboSituacaoParcelamento.selectedItem.codListaItem);
			reqDTO.dados.exibirMsgFiltro = false;
			
			this.servico.procurar(reqDTO);				
		}	
						
		private function retornoListarParcelas(event:ResultEvent):void {
			
			if(event.result.dados["msg"]) {
				Alerta.show(event.result.dados["msg"], "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
			}									
			
			if(gridParcelamento.selectedItem.valorAberto > 0){
				btManutencao.enabled = true;
				btCancParcela.enabled = true;
				btBaixar.enabled = true;
			}else{
				btManutencao.enabled = false;
				btCancParcela.enabled = false;
				btBaixar.enabled = false;					
			}
			
			this.listaParcelas = new ArrayCollection();			
			this.listaParcelas.list = event.result.dados["listaParcelas"] as ArrayCollection;					

			gridParcelas.columns[1].labelFunction = formataParcela;
			gridParcelas.columns[6].labelFunction = formataTipoInteg;
			
			gridParcelas.dataProvider = listaParcelas.list;		
			
			for(var i:int=0;i<listaParcelas.length;i++){
				listaParcelas[i].selecionado = false;
			}				
			chkSelecionaTodos.selected = false;
			
			atualizaValores();
			MostraCursor.removeBusyCursor();						
		}		
		
		private function listarParcelas(evt:MouseEvent):void {
			
			if (gridParcelamento.selectedIndex >= 0) {							
				
				reqDTOParcela.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
				reqDTOParcela.dados.idTipoParcelamento = gridParcelamento.selectedItem.idTipoParcelamento;
				reqDTOParcela.dados.numParcelamento = gridParcelamento.selectedItem.numParcelamento;		
				
				this.servico.listarParcelas(reqDTOParcela);				
				
			}
			
		}
		
		public function chkSelecionaTodos_Click(evt:MouseEvent=null):void{
			
			if(gridParcelas.dataProvider != null){
				gridParcelas.dataProvider.refresh();
				if(listaParcelas != null){
					for(var i:int=0;i<listaParcelas.length;i++){
						if(listaParcelas[i].idSituacaoParcelamento=="1"){
							if(chkSelecionaTodos.selected){								
								listaParcelas[i].selecionado = true;	
							}else{
								listaParcelas[i].selecionado = false;											
							}
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
		
		private function retornoObterProcurarCCANaoEcontrado(event:SelecionarContaCapitalEvent):void {			
			
			limparTela();
		}
		
		private function limparTela():void {
			limparGridParcelamento();
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoObterProcurarCCA(event:SelecionarContaCapitalEvent):void {						
			limparGridParcelamento();
		}
		
		public function limparGridParcelamento():void {						
			gridParcelas.dataProvider = new ArrayCollection();				
			gridParcelamento.dataProvider = new ArrayCollection();		
			
			btManutencao.enabled = false;
			btCancParcela.enabled = false;
			btBaixar.enabled = false;	
			
			btCancTodas.enabled = false;
			
			MostraCursor.removeBusyCursor();		
		}		
		
		private function abrirParcelamento(evt:MouseEvent):void{
			
			if (gridParcelamento.selectedIndex < 0) {					
				Alerta.show("Selecione um parcelamento.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;										
			}
			
			janela = new Janela();
			janela.title = "MANUTENÇÃO PARCELAMENTO";
			janela.removeAllChildren();
			janela.addChild(janParcelamento);
			
			janParcelamento.janMovimentacaoParcelamentoRen = this;			
			
			janParcelamento.numContaCapital.text = gridParcelamento.selectedItem.numContaCapital;
			janParcelamento.qtdParcelas.text = gridParcelamento.selectedItem.qtdParcelas;
			janParcelamento.descTipoParcelamento.text = gridParcelamento.selectedItem.descTipoParcelamento;
			janParcelamento.numParcelamento.text = gridParcelamento.selectedItem.numParcelamento;
			janParcelamento.valorAberto.text = (gridParcelamento.selectedItem.valorAberto).toFixed(2);
			janParcelamento.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			janParcelamento.idPessoaLegado = procurarCCA.resultadoPesquisaVO.idPessoaLegado;	
			
			numUltimaParcela = 0;			
			for(var i:int=0; i < listaParcelas.length; i++){
				numUltimaParcela = listaParcelas[i].numParcela;							
			}				
			
			janParcelamento.numUltimaParcela = numUltimaParcela;
			
			if(janParcelamento.listaParcelas != null){
				janParcelamento.listarParcelas();				
			}
			
			janela.abrir(this,true,true);
			
		}					
		
		private function validaCancelarParcela(evt:MouseEvent):void {
			
			var bolParcSelecionada:Boolean;
			bolParcSelecionada = false;
			gridParcelas.dataProvider.refresh();			
			
			for(var i:int=0;i<listaParcelas.length;i++){
				if(listaParcelas[i].selecionado){
					bolParcSelecionada = true;					
				}
			}
			
			if(bolParcSelecionada){
				Alerta.show("Confirma o cancelamento da parcela?", "Cancelar registro", Alerta.ALERTA_PERGUNTA, null, cancelarParcelas);		
			}else{
				Alerta.show("É obrigatório a seleção de pelo menos uma parcela.","ATENÇÃO",Alerta.ALERTA_OK, null);							
			}				
			
		}
		
		public function cancelarParcelas(evt:Event):void{
			
			var dtoParcela: RequisicaoReqDTO = new RequisicaoReqDTO();
			var vo:ParcelamentoRenVO;
			MostraCursor.setBusyCursor("Gravando alterações de cancelamento na parcela!", Application.application);
			var arrParcelamentoVO:ArrayCollection = new ArrayCollection();									
						
			for(var i:int=0;i<listaParcelas.length;i++){
				if(listaParcelas[i].selecionado){
					vo = new ParcelamentoRenVO();
					
					vo.numContaCapital 		  = gridParcelas.dataProvider[i].numContaCapital;
					vo.numParcela		 	  = gridParcelas.dataProvider[i].numParcela;
					vo.idTipoParcelamento 	  = gridParcelas.dataProvider[i].idTipoParcelamento;					
					vo.numParcelamento 		  = gridParcelamento.selectedItem.numParcelamento;
					vo.valorParcela 		  = gridParcelas.dataProvider[i].valorParcela;
					vo.idParcelamento  		  = gridParcelas.dataProvider[i].idParcelamento;
					vo.idSituacaoParcelamento = gridParcelas.dataProvider[i].idSituacaoParcelamento;
					vo.idContaCapital		  = gridParcelas.dataProvider[i].idContaCapital;
					vo.idMotivoDevolucao      = gridParcelas.dataProvider[i].idMotivoDevolucao;
					
					arrParcelamentoVO.addItem(vo);
				}				
			}		
			
			dtoParcela.dados.listaParcelas = arrParcelamentoVO;		
			
			this.servico.cancelarParcelas(dtoParcela);		
		}			
		
		private function validaBaixarParcela(evt:MouseEvent):void {
			
			var bolParcSelecionada:Boolean;
			bolParcSelecionada = false;
			gridParcelas.dataProvider.refresh();			
			
			for(var i:int=0;i<listaParcelas.length;i++){
				if(listaParcelas[i].selecionado){
					bolParcSelecionada = true;					
				}
			}
			
			if(bolParcSelecionada){
				Alerta.show("Confirma a baixa da parcela?", "Baixar registro", Alerta.ALERTA_PERGUNTA, null, baixarParcelas);		
			}else{
				Alerta.show("É obrigatório a seleção de pelo menos uma parcela.","ATENÇÃO",Alerta.ALERTA_OK, null);							
			}				
			
		}				
		
		public function baixarParcelas(evt:Event):void{
			
			var dtoParcela: RequisicaoReqDTO = new RequisicaoReqDTO();
			var vo:ParcelamentoRenVO;
			MostraCursor.setBusyCursor("Gravando alterações de baixa na parcela!", Application.application);
			var arrParcelamentoVO:ArrayCollection = new ArrayCollection();									
			
			for(var i:int=0;i<listaParcelas.length;i++){
				if(listaParcelas[i].selecionado){
					vo = new ParcelamentoRenVO();
					
					vo.numContaCapital 		  = gridParcelas.dataProvider[i].numContaCapital;
					vo.numParcela		 	  = gridParcelas.dataProvider[i].numParcela;
					vo.idTipoParcelamento 	  = gridParcelas.dataProvider[i].idTipoParcelamento;					
					vo.numParcelamento 		  = gridParcelamento.selectedItem.numParcelamento;
					vo.valorParcela			  = gridParcelas.dataProvider[i].valorParcela;
					vo.idParcelamento		  = gridParcelas.dataProvider[i].idParcelamento;
					vo.idSituacaoParcelamento = gridParcelas.dataProvider[i].idSituacaoParcelamento;
					vo.idContaCapital		  = gridParcelas.dataProvider[i].idContaCapital;
					vo.idMotivoDevolucao      = gridParcelas.dataProvider[i].idMotivoDevolucao;
					
					arrParcelamentoVO.addItem(vo);
				}				
			}		
			
			dtoParcela.dados.listaParcelas = arrParcelamentoVO;		
			
			this.servico.baixarParcelas(dtoParcela);		
		}			
				
		public function atualizaValores():void{
			
			if(listaParcelas.length > 0){
				
				var sort:Sort = new Sort(); 				
				sort.fields = [new SortField("numParcela")];									
				listaParcelas.sort = sort;
				listaParcelas.refresh();				
				listaParcelasAux = new ArrayCollection();			
				
				for(var i:int=0; i < listaParcelas.length; i++){
					listaParcelasAux.addItem(listaParcelas[i]);
				}	
				gridParcelas.dataProvider = listaParcelasAux.list;	
			}
		}
		
		private function validaCancelarTodasParcelas(evt:MouseEvent):void {
			if(possuiParcelasEmAberto()){
				Alerta.show("Esta ação cancelará TODOS os parcelamentos e suas respectivas parcelas de INTEGRALIZAÇÃO. Confirma o cancelamento dos parcelamentos?", "Cancelar parcelamentos", Alerta.ALERTA_PERGUNTA, null, cancelarTodasParcelas);		
			}else{
				Alerta.show("Esta conta não possui parcelas em aberto.","ATENÇÃO",Alerta.ALERTA_OK, null);							
			}
		}
		
		public function cancelarTodasParcelas(evt:Event):void{
			MostraCursor.setBusyCursor("Gravando alterações de cancelamentos nas parcelas!", Application.application);
			var reqDTO: RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			this.servico.cancelarTodasParcelas(reqDTO);	
		}
		
	}	
}