package br.com.sicoob.sisbr.cca.movimentacao.plataforma {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IEdicaoPlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.cca.movimentacao.parcelamento.MovimentacaoParcelamentoRenEditar;
	import br.com.sicoob.sisbr.cca.vo.ParcelamentoRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO", ParcelamentoRenVO);
	public class ParcelamentoEdicao extends ParcelamentoEdicaoView implements IEdicaoPlataformaAtendimento {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.ParcelamentoContaCapitalServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var pessoaSelecionada:PessoaPlataformaVO;
		private var _dadosDefinicoes:Object = new Object();
		[Bindable]
		public var listaParcelas:ArrayCollection;
		[Bindable]
		public var listaParcelamento:ArrayCollection;
		private var reqDTOParcela:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var janela:Janela = new Janela();
		private var janParcelamento:MovimentacaoParcelamentoRenEditar = new MovimentacaoParcelamentoRenEditar();	
		private var idPessoaLegado:Number;
		private var numUltimaParcela:Number;		
		public var destinoParent:DestinoVO;		
		
		public function ParcelamentoEdicao() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			inicializaServico();			
			obterDefinicoes();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(destinoParent);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			controlarServico();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			
			btProcurar.addEventListener(MouseEvent.CLICK, procurar);
								
			this.servico.procurar.addEventListener(ResultEvent.RESULT, retornoProcurar);
			this.servico.listarParcelas.addEventListener(ResultEvent.RESULT, retornoListarParcelas);
			this.servico.cancelarParcelas.addEventListener(ResultEvent.RESULT, retornoAlterarParcelas);
			this.servico.cancelarTodasParcelas.addEventListener(ResultEvent.RESULT, retornoAlterarParcelas);
			this.servico.baixarParcelas.addEventListener(ResultEvent.RESULT, retornoAlterarParcelas);
			gridParcelamento.addEventListener(MouseEvent.CLICK, listarParcelas);			
			chkSelecionaTodos.addEventListener(MouseEvent.CLICK,chkSelecionaTodos_Click);
			
			btProcurar.addEventListener(MouseEvent.CLICK, procurar);			
		}
		
		private function obterDefinicoes():void {
			servico.source = SERVICO_SOURCE;
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			_dadosDefinicoes = event.result.dados;			
			
			cboTipoParcelamento.dataProvider = _dadosDefinicoes["cboTipoParcelamento"] as ArrayCollection;				
			cboSituacaoParcelamento.dataProvider = _dadosDefinicoes["cboSituacaoParcelamento"] as ArrayCollection;																
			
			MostraCursor.removeBusyCursor();
			
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
		}
		
		public function carregarRegistro(registro:Object):void {
			idPessoaLegado = registro.idPessoaLegado;
			numCCA.text = registro.numContaCapital;		
			idContaCapital.text = registro.idContaCapital;		
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
			MostraCursor.removeBusyCursor();
		}
		
		public function validaCancelarParcela(evt:MouseEvent=null):void {
			
			var bolParcSelecionada:Boolean;
			bolParcSelecionada = false;
			
			if (listaParcelas != null){							
				gridParcelas.dataProvider.refresh();			
				
				for(var i:int=0;i<listaParcelas.length;i++){
					if(listaParcelas[i].selecionado){
						bolParcSelecionada = true;					
					}
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
		
		public function validaBaixarParcela(evt:MouseEvent=null):void {
			
			var bolParcSelecionada:Boolean;
			bolParcSelecionada = false;
			if (listaParcelas != null){				
				gridParcelas.dataProvider.refresh();			
				
				for(var i:int=0;i<listaParcelas.length;i++){
					if(listaParcelas[i].selecionado){
						bolParcSelecionada = true;					
					}
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
		
		public function procurar(evt:MouseEvent=null):void {
			
			if(numCCA.text == ""){
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
			
			reqDTO.dados.idContaCapital = idContaCapital.text;
			reqDTO.dados.idTipoParcelamento = new Number(cboTipoParcelamento.selectedItem.codListaItem);
			reqDTO.dados.idSituacaoParcelamento = new Number(cboSituacaoParcelamento.selectedItem.codListaItem);			
			
			limparGridParcelamento();
			
			this.servico.procurar(reqDTO);				
		}			
		
		public function limparGridParcelamento():void {						
			gridParcelas.dataProvider = new ArrayCollection();				
			gridParcelamento.dataProvider = new ArrayCollection();		
			
			MostraCursor.removeBusyCursor();		
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
			esconderBotoesManutencao();
			
			gridParcelamento.dataProvider = listaParcelamento.list;				
			MostraCursor.removeBusyCursor();						
		}	
		
		private function retornoAlterarParcelas(event:ResultEvent):void {
			
			if(event.result.dados["msg"]) {
				Alerta.show(event.result.dados["Erro ao alterar parcela"], "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
			}									
			
			Alerta.show("Dados alterados com sucesso.", "SUCESSO", Alerta.ALERTA_OK);
			
			limparGridParcelamento();
			reqDTO.dados.idContaCapital = idContaCapital.text;
			reqDTO.dados.idTipoParcelamento = new Number(cboTipoParcelamento.selectedItem.codListaItem);
			reqDTO.dados.idSituacaoParcelamento = new Number(cboSituacaoParcelamento.selectedItem.codListaItem);
			reqDTO.dados.exibirMsgFiltro = false;
			
			esconderBotoesManutencao();
			
			this.servico.procurar(reqDTO);				
		}	
		
		private function esconderBotoesManutencao() {
			(this.parentDocument as ParcelamentoRenAtendimento).botoesOpt.botManutencaoParcelamento.visible = false;
			(this.parentDocument as ParcelamentoRenAtendimento).botoesOpt.botCancelamentoParcela.visible = false;
			(this.parentDocument as ParcelamentoRenAtendimento).botoesOpt.botBaixaViaBanco.visible = false;
		}
		
		private function retornoListarParcelas(event:ResultEvent):void {
			
			if(event.result.dados["msg"]) {
				Alerta.show(event.result.dados["msg"], "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
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
			MostraCursor.removeBusyCursor();						
		}		
		
		private function listarParcelas(evt:MouseEvent):void {
			
			
			if (gridParcelamento.selectedIndex >= 0) {	
				
				//Manutenção apenas em parcelamento que possuem valor em aberto
				var exibirBotoesManutencao:Boolean = (gridParcelamento.selectedItem.valorAberto > 0 && this.parentDocument is ParcelamentoRenAtendimento);
				(this.parentDocument as ParcelamentoRenAtendimento).botoesOpt.botManutencaoParcelamento.visible = exibirBotoesManutencao;
				(this.parentDocument as ParcelamentoRenAtendimento).botoesOpt.botCancelamentoParcela.visible = exibirBotoesManutencao;
				(this.parentDocument as ParcelamentoRenAtendimento).botoesOpt.botBaixaViaBanco.visible = exibirBotoesManutencao;
				
				reqDTOParcela.dados.idContaCapital = idContaCapital.text;
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
		
		public function editarParcelamento(evt:MouseEvent):void{
			
			if (gridParcelamento.selectedIndex < 0) {					
				Alerta.show("Selecione um parcelamento.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;										
			}
			
			janela = new Janela();
			janela.title = "MANUTENÇÃO PARCELAMENTO";
			janela.removeAllChildren();
			janParcelamento.destinoPai = destinoParent;
			janela.addChild(janParcelamento);
			
			janParcelamento.parcelamentoEdicao = this;				
			janParcelamento.bolPlataforma = true;	
			
			janParcelamento.numContaCapital.text = gridParcelamento.selectedItem.numContaCapital;
			janParcelamento.qtdParcelas.text = gridParcelamento.selectedItem.qtdParcelas;
			janParcelamento.descTipoParcelamento.text = gridParcelamento.selectedItem.descTipoParcelamento;
			janParcelamento.numParcelamento.text = gridParcelamento.selectedItem.numParcelamento;
			janParcelamento.valorAberto.text = (gridParcelamento.selectedItem.valorAberto).toFixed(2);			
			janParcelamento.idContaCapital = new Number(idContaCapital.text);
			janParcelamento.idPessoaLegado = idPessoaLegado;	
			
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
		
		public function validaCancelarTodasParcelas(evt:MouseEvent):void {
			if(possuiParcelasEmAberto()){
				Alerta.show("Esta ação cancelará TODOS os parcelamentos e suas respectivas parcelas de INTEGRALIZAÇÃO. Confirma o cancelamento dos parcelamentos?", "Cancelar parcelamentos", Alerta.ALERTA_PERGUNTA, null, cancelarTodasParcelas);		
			}else{
				Alerta.show("Esta conta não possui parcelas em aberto.","ATENÇÃO",Alerta.ALERTA_OK, null);							
			}
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
		
		private function configurarBtCancTodas():void {
			(this.parentDocument as ParcelamentoRenAtendimento).botoesOpt.btCancTodas.visible = possuiParcelasEmAberto();
		}
		
		public function cancelarTodasParcelas(evt:Event):void{
			MostraCursor.setBusyCursor("Gravando alterações de cancelamentos nas parcelas!", Application.application);
			var reqDTO: RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.idContaCapital = idContaCapital.text;
			this.servico.cancelarTodasParcelas(reqDTO);	
		}
		
		public function gravarRegistro():void {}
				
		public function preencherCampos():void { }
		
		public function carregarDefinicoes(obj:Object = null):void { }
		
		public function novoRegistro():void	{ }
		
		public function atualizarCamposRegistro():void { }
		
		public function restaurarRegistro():void { }
		
		public function excluirRegistro(obj:Object):void { }
		
		public function verificarAlteracoes():Boolean {
			return false;
		}
		
	}
}