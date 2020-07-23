package br.com.sicoob.sisbr.cca.cadastro.plataforma {
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IEdicaoPlataformaAtendimento;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.cca.comum.util.NumeroUtil;
	import br.com.sicoob.sisbr.cca.util.DataUtilRelatorios;
	import br.com.sicoob.sisbr.cca.vo.ParcelamentoRenVO;
	import br.com.sicoob.sisbr.cca.vo.SubscricaoRenVO;
	
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.SubscricaoRenVO", SubscricaoRenVO);
	public class SubscricaoRenEdicao extends SubscricaoRenEdicaoView implements IEdicaoPlataformaAtendimento {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.SubscricaoContaCapitalServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		public var pessoaSelecionada:PessoaPlataformaVO;
		public var listaParcelasAux:ArrayCollection;
		private var voParcela:ParcelamentoRenVO = new ParcelamentoRenVO();
		private var percMinInteg:Number;
		private var valorCota:Number;
		private var numMaxParcelas:int;
		private var numMinCota:int;		
		private var janela:Janela = new Janela();
		private var parc:ParcelamentoCadastroAtendimento = new ParcelamentoCadastroAtendimento();			
		private var listaTipoInteg:ArrayCollection = new ArrayCollection();
		private var _dadosDefinicoes:Object = new Object();
		private var _dadosRetorno:Object = new Object();
		public var arrListaParcelas:ArrayCollection;
		public var subscricaoRenVO:SubscricaoRenVO;
		private var bolSubscricaoProposta:int;
		public var dataUltimaParcela:String;
		private var dataAtualProduto:String;
		private var listaTipoSubscricao:ArrayCollection;	
		public var numContaCapital:Number;
		public var idContaCapital:Number;
		public var destinoParent:DestinoVO;
		
		[Bindable]
		public var listaParcelas:ArrayCollection;
		
		[Bindable]
		public var vo:SubscricaoRenVO = new SubscricaoRenVO();
		
		public var listaCco:ArrayCollection = new ArrayCollection();
		
		public function SubscricaoRenEdicao() {
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
			
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);
			servico.gerarParcelas.addEventListener(ResultEvent.RESULT, retornoGerarParcelas);			
			servico.obterPropostaSubscricao.addEventListener(ResultEvent.RESULT, retornoObterPropostaSubscricao);
			servico.isValorIntegralizacaoMaiorSalcoCco.addEventListener(ResultEvent.RESULT, retornoIsValorIntegralizacaoMaiorSalcoCco);
			cboTipoInteg.addEventListener(Event.CHANGE, onChangeCboTipoInteg);
			cboTipoIntegParcela.addEventListener(Event.CHANGE, onChangeCboTipoIntegParcela);
			
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, validarIncluir);
			
			
		}
		
		private function onChangeCboTipoIntegParcela(evt:Event):void {
			if(cboTipoIntegParcela.selectedItem != null){				
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
		}
		
		private function retornoIncluir(evt:ResultEvent):void {
			
			if(evt.result.dados["erroNegocial"]) {
				Alerta.show(evt.result.dados["msg"], "ERRO", Alerta.ALERTA_ERRO);
				tabNav.selectedIndex = 0;
				MostraCursor.removeBusyCursor();
				return;
			}
			
			this.vo = evt.result.dados["vo"];
			
			Alerta.show(evt.result.dados["msg"], "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
					
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
			super.fecharJanela();

		}
		
		private function onChangeCboTipoInteg(evt:Event):void {
			if(cboTipoInteg.selectedItem != null){				
				switch (cboTipoInteg.selectedItem["codListaItem"]){
					case "2":
						cboCco.visible = true;
						labelCco.visible = true;
						cboMatricula.visible = false;	
						labelMatricula.visible = false;
						break;
					case "3":
						cboCco.visible = false;
						labelCco.visible = false;
						cboMatricula.visible = true;
						labelMatricula.visible = true;
						break;
					default:
						cboCco.visible = false;
						labelCco.visible = false;
						cboMatricula.visible = false;
						labelMatricula.visible = false;
				}
			}
		}
		
		private function retornoIsValorIntegralizacaoMaiorSalcoCco(event:ResultEvent):void {
			
			_dadosRetorno = event.result.dados;
			
			if(_dadosRetorno["isValorIntegralizacaoMaiorSalcoCco"]){
				Alerta.show("Saldo em Conta Corrente insuficiente para integralização, deseja continuar com a gravação?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, incluir, naoIncluirPorSaldoCCO);
				return;				
			}else{
				incluir();				
			}
			
		}
		
		private function naoIncluirPorSaldoCCO(evt:MouseEvent):void {			
			Alerta.show("Dados não gravados, saldo em conta corrente insuficiente.", "ATENÇÃO", Alerta.ALERTA_OK);
			MostraCursor.removeBusyCursor();			
			return;							
		}	
		
		private function obterDefinicoes():void {
			servico.source = SERVICO_SOURCE;
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			_dadosDefinicoes = event.result.dados;			
			dataAtualProduto = _dadosDefinicoes.dataAtualProduto;
			dataSubscricao.text = _dadosDefinicoes.dataAtualProduto;			
			cboTipoSubscricao.dataProvider = _dadosDefinicoes["comboTipoSubscricao"] as ArrayCollection;				
			listaTipoInteg.list = _dadosDefinicoes["comboTipoInteg"] as ArrayCollection;	
			cboTipoInteg.dataProvider = _dadosDefinicoes["comboTipoInteg"] as ArrayCollection;			
			cboTipoIntegParcela.dataProvider = _dadosDefinicoes["comboTipoInteg"] as ArrayCollection;				
			
			percMinInteg = _dadosDefinicoes.percMinInteg;
			valorCota = _dadosDefinicoes.valorCota;					
			numMaxParcelas = _dadosDefinicoes.numMaxParcelas;		
			numMinCota = _dadosDefinicoes.numMinCota;
			
			cboCco.visible = false;
			labelCco.visible = false;
			
			cboMatricula.visible = false;
			labelMatricula.visible = false;
			
			cboCcoParcela.visible = false;
			labelCcoParcela.visible = false;
			
			cboMatriculaParcela.visible = false;
			labelMatriculaParcela.visible = false;
			
			controlarEventos();
			carregarDados();
		}
		
		private function controlarEventos():void {
			btGerarParcelas.addEventListener(MouseEvent.CLICK, gerarParcelas);						
			btIncluir.addEventListener(MouseEvent.CLICK, abrirParcelamento);
			btExcluir.addEventListener(MouseEvent.CLICK, excluirParcela);			
			cboCco.addEventListener(FocusEvent.FOCUS_OUT, calcularValorParcela);
			cboTipoInteg.addEventListener(FocusEvent.FOCUS_OUT, calcularValorParcela);
			cboMatricula.addEventListener(FocusEvent.FOCUS_OUT, calcularValorParcela);
			vlrSubs.addEventListener(FocusEvent.FOCUS_OUT, calcularValorParcela);
			vlrInteg.addEventListener(FocusEvent.FOCUS_OUT, calcularValorParcela);	
			vlrSubs.addEventListener(FocusEvent.FOCUS_OUT, validarValorSubscricaoIntegralizacao);
			vlrInteg.addEventListener(FocusEvent.FOCUS_OUT, validarValorSubscricaoIntegralizacao);			
		}
		
		private function validarValorSubscricaoIntegralizacao(evt:FocusEvent=null):void {
			if(vlrSubs.valor > 0 && vlrInteg.valor > 0 && vlrSubs.valor != vlrInteg.valor) {				
				
				if(vlrSubs.valor < vlrInteg.valor){
					Alerta.show("O valor da integralização deve ser menor ou igual a subscrição.", "ATENÇÃO", Alerta.ALERTA_OK, evt.currentTarget);		
					evt.currentTarget.text = "";
					return;
				}
				return;
			}
		}
		
		private function excluirParcela(event:MouseEvent=null):void {			
			if (grid.selectedIndex >= 0) {							
				Alerta.show("Deseja realmente excluir a parcela " + grid.selectedItem.numParcela + "?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, confirmarExcluirParcela);			
			}else{
				Alerta.show("Escolha a parcela que deseja excluir.", "ATENÇÃO", Alerta.ALERTA_OK);				
			}
		}		
		
		private function confirmarExcluirParcela(event:MouseEvent=null):void {						
			if (grid.selectedIndex >= 0) {							
				if(grid.selectedIndex == 0){
					Alerta.show("A parcela a vista não pode ser excluída.", "ERRO", Alerta.ALERTA_ERRO);
					return;
				}else{
					listaParcelas.removeItemAt(grid.selectedIndex);
					listaParcelas.refresh();
					atualizaValores();
					grid.selectedIndex = 0;		
				}
			}
		}	
		
		private function abrirParcelamento(evt:MouseEvent):void{
			
			if(vlrParcelar.valor <= 0){				
				Alerta.show("Não há valor a parcelar.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;										
			}
			
			janela = new Janela();
			janela.title = "NOVA PARCELA";
			janela.removeAllChildren();
			janela.addChild(parc);
			
			parc.movimentacaoSubscricaoRen = this;
			parc.cboTipoIntegParcela.dataProvider = listaTipoInteg.list;
			parc.cboCcoParcela.dataProvider = _dadosRetorno["comboCco"] as ArrayCollection; 	
			parc.cboMatriculaParcela.dataProvider = _dadosRetorno["comboTrabalha"] as ArrayCollection;	
			parc.cboTipoIntegParcela.selectedIndex = 0;
			parc.vlrParcela.text = ""; 				
			
			dataUltimaParcela = listaParcelas[listaParcelas.length-1].dataVencimento;
			parc.dtVencimento.selectedDate = DataUtilRelatorios.somarDiasData(DataUtilRelatorios.stringBrToDate(dataUltimaParcela), 30);
			
			parc.cboCcoParcela.visible = false;
			parc.labelCcoParcela.visible = false;
			
			parc.labelMatriculaParcela.visible = false;
			parc.cboMatriculaParcela.visible = false;			
			
			
			janela.abrir(this,true,true);
			
		}			
		
		private function validarIncluir(evt:MouseEvent):void {			
			if(validarCampos()) {
				if(cboTipoInteg.selectedItem.codListaItem == 2){
					reqDTO.dados.idPessoa = pessoaSelecionada.idPessoaLegado;
					reqDTO.dados.numContaCorrente = cboCco.text;
					reqDTO.dados.valor = vlrInteg.valor;
					this.servico.isValorIntegralizacaoMaiorSalcoCco(reqDTO);
				}else{
					incluir(evt);
				}
			}
		}
		
		private function validarCampos():Boolean {		
			
			if (!validarCboTipoInteg()) {
				return false;
			}
			
			if(dataSubscricao.text != dataAtualProduto) {
				tabNav.selectedIndex = 0;
				Alerta.show("A Data da Subscrição deve ser igual a Data do Produto.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(cboTipoSubscricao.selectedItem == null){
				tabNav.selectedIndex = 0;
				Alerta.show("O campo Tipo da Subscrição é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoSubscricao);
				return false;				
			}			
			
			if(bolSubscricaoProposta != 0 && cboTipoSubscricao.selectedItem.codListaItem == 1){
				tabNav.selectedIndex = 0;
				Alerta.show("O tipo de subscrição Ingresso só pode ser escolhido se o associado ainda não tiver realizado a subscrição da proposta", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoSubscricao);
				return false;				
			}
			
			if(vlrSubs.valor <= 0) {
				tabNav.selectedIndex = 0;
				Alerta.show("O Valor de Subscrição deve ser maior que zero (R$ 0,00).", "ATENÇÃO", Alerta.ALERTA_OK, vlrSubs);
				return false;
			}
			
			if(vlrInteg.valor <= 0) {
				tabNav.selectedIndex = 0;
				Alerta.show("O Valor de Integralização à Vista deve ser maior que zero (R$ 0,00).", "ATENÇÃO", Alerta.ALERTA_OK, vlrInteg);
				return false;
			}
			
			if(vlrSubs.valor != vlrInteg.valor) {
				tabNav.selectedIndex = 1;
				//Retirado o qtdParcelas.valor > 100 a pedido do Dr Augusto/Credimep e Vilaça 
				/*
				if(qtdParcelas.valor <= 0 || qtdParcelas.valor > 100) {
					Alerta.show("Quant. Parcelas deve estar entre 1 e 100.", "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
					return false;
				}
				*/
				
				if(qtdParcelas.valor <= 0) {
					Alerta.show("A Quant. Parcelas deve ser maior que zero(0).", "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
					return false;
				}
				
				if(vlrParcelar.valor > 0){
					Alerta.show("A soma dos valores parcelados acrescidos da parcela à vista, deve corresponder ao valor total de parcelamento, não podendo a soma ser inferior ou superior ao total", "ATENÇÃO", Alerta.ALERTA_OK, vlrParcelar);
					return false;					
				}
			}
			
			if(vlrSubs.valor < vlrInteg.valor) {
				tabNav.selectedIndex = 0;
				Alerta.show("O valor da integralização deve ser menor ou igual a subscrição.", "ATENÇÃO", Alerta.ALERTA_OK, vlrInteg);
				return false;
			}
			
			if(cboTipoInteg.selectedItem == null){
				tabNav.selectedIndex = 0;
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoInteg);
				return false;				
			}						
			
			if(diaDebito.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("O campo Dia do Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, diaDebito);
				return false;
			}
			
			if(diaDebito.valor > 31) {
				tabNav.selectedIndex = 1;
				Alerta.show("Dia para Débito inválido.", "ATENÇÃO", Alerta.ALERTA_OK, diaDebito);
				return false;
			}
			
			if(cboTipoIntegParcela.selectedItem == null){
				tabNav.selectedIndex = 1;
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoIntegParcela);
				return false;				
			}									
			
			if(cboTipoSubscricao.selectedItem.codListaItem == 1){				
				if(vlrSubs.valor < (valorCota*numMinCota)){
					tabNav.selectedIndex = 0;
					Alerta.show("O valor mínimo de subscrição é: " + FormataNumero.formata(valorCota*numMinCota,2), "ATENÇÃO", Alerta.ALERTA_OK, vlrSubs);
					return false;					 
				}
				
				var valorMinimoInteg:Number;
				valorMinimoInteg = valorCota*percMinInteg/100;
				
				
				if(vlrInteg.valor < valorMinimoInteg){
					tabNav.selectedIndex = 0;
					Alerta.show("O valor mínimo de integralização é: " + FormataNumero.formata(valorMinimoInteg, 2), "ATENÇÃO", Alerta.ALERTA_OK, vlrInteg);
					return false;					
				}				
				
			}else{
				if(vlrSubs.valor < (valorCota)){
					tabNav.selectedIndex = 0;
					Alerta.show("O valor mínimo de subscrição é: " + FormataNumero.formata(valorCota,2), "ATENÇÃO", Alerta.ALERTA_OK, vlrSubs);
					return false;					
				}								
			}
			
			var dataVencimentoOrdenacaoAnt:int;
			dataVencimentoOrdenacaoAnt = 0;
			if(listaParcelas!=null){
				for(var i:int=0;i<listaParcelas.length;i++){
					if(dataVencimentoOrdenacaoAnt>grid.dataProvider[i].dataVencimentoOrdenacao){
						tabNav.selectedIndex = 1;
						Alerta.show("A data de vencimento da parcela "+i+" não pode ser menor que data da parcela " + (i-1), "ATENÇÃO", Alerta.ALERTA_OK);
						return false;										
					}				
					dataVencimentoOrdenacaoAnt = grid.dataProvider[i].dataVencimentoOrdenacao;
				}							
			}			
			
			return true;
		}
		
		private function validarCboTipoInteg():Boolean {
			if(cboTipoInteg.selectedItem == null){
				tabNav.selectedIndex = 0;
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoInteg);
				return false;				
			}	
			
			if(cboTipoInteg.selectedItem.codListaItem == 2){				
				if(cboCco.text == ""){
					tabNav.selectedIndex = 0;
					Alerta.show("O número da conta corrente é obrigatória para a forma de débito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, cboCco);
					return false;										
				}
			}
			
			if(cboTipoInteg.selectedItem.codListaItem == 3){				
				if(cboMatricula.text == ""){
					tabNav.selectedIndex = 0;
					Alerta.show("O número matrícula é obrigatória para a forma de débito folha.", "ATENÇÃO", Alerta.ALERTA_OK, cboMatricula);
					return false;										
				}
			}
			
			return true;
		}
		
		private function incluir(evt:MouseEvent=null):void {		
			
			reqDTO = new RequisicaoReqDTO();			
			reqDTO.dados.vo = carregarVO();								
			
			var arrParcelamentoVO:ArrayCollection = new ArrayCollection();																
			
			if(listaParcelas!=null){
				for(var i:int=0;i<listaParcelas.length;i++){
					voParcela = new ParcelamentoRenVO();
					
					voParcela.numParcela = grid.dataProvider[i].numParcela;
					voParcela.dataVencimento = grid.dataProvider[i].dataVencimento;
					voParcela.valorParcela = grid.dataProvider[i].valorParcela;
					voParcela.idTipoInteg = grid.dataProvider[i].idTipoInteg;
					voParcela.numContaCorrente = grid.dataProvider[i].numContaCorrente;
					voParcela.descNumMatriculaFunc = grid.dataProvider[i].descNumMatriculaFunc;
					voParcela.dataVencimentoOrdenacao = grid.dataProvider[i].dataVencimentoOrdenacao;
					
					arrParcelamentoVO.addItem(voParcela);
				}					 				
			}else{
				voParcela = new ParcelamentoRenVO();
				
				voParcela.numParcela = 0;
				voParcela.dataVencimento = dataSubscricao.text;
				voParcela.valorParcela = vlrSubs.valor;
				voParcela.idTipoInteg = new Number(cboTipoInteg.selectedItem.codListaItem);
				voParcela.numContaCorrente = new Number(cboCco.text);
				voParcela.descNumMatriculaFunc = cboMatricula.text;
				
				arrParcelamentoVO.addItem(voParcela);				
			}
			
			reqDTO.dados.listaParcelasVO = arrParcelamentoVO;					
			
			this.servico.incluir(reqDTO);
		}
		
		private function gerarParcelas(evt:MouseEvent):void {
			if(validarParcelas()) {
				
				vo.vlrSubs = vlrSubs.valor;
				vo.vlrInteg = vlrInteg.valor;				
				vo.vlrParcelas = vlrParcelamento.valor;
				vo.qtdParcelas = new Number(qtdParcelas.text);
				vo.diaDebito = new Number(diaDebito.text);
				vo.tipoInteg = new Number(cboTipoIntegParcela.selectedItem.codListaItem);
				vo.numContaCorrente = new Number(cboCcoParcela.text); 
				vo.descNumMatriculaFunc = cboMatriculaParcela.text; 
				
				reqDTO.dados.vo = vo;				
				this.servico.gerarParcelas(reqDTO);				
			}
		}		
		
		/**
		 * Valida campos de acordo com regras de negocio
		 */
		private function validarParcelas():Boolean {						
			
			if(cboTipoInteg.selectedItem == null){
				tabNav.selectedIndex = 0;
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoInteg);
				return false;				
			}	
			
			if(cboTipoInteg.selectedItem.codListaItem == 2){				
				if(cboCco.text == ""){
					tabNav.selectedIndex = 0;
					Alerta.show("O número da conta corrente é obrigatória para a forma de débito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, cboCco);
					return false;										
				}
			}
			
			if(cboTipoInteg.selectedItem.codListaItem == 3){				
				if(cboMatricula.text == ""){
					tabNav.selectedIndex = 0;
					Alerta.show("O número matrícula é obrigatória para a forma de débito folha.", "ATENÇÃO", Alerta.ALERTA_OK, cboMatricula);
					return false;										
				}
			}
			
			if(vlrParcelamento.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("Valor do parcelamento deve ser maior que 0,00.", "ATENÇÃO", Alerta.ALERTA_OK, vlrParcelamento);
				return false;
			}			
			
			if(qtdParcelas.valor <= 0 || qtdParcelas.valor > numMaxParcelas) {
				tabNav.selectedIndex = 1;
				Alerta.show("Quant. Parcelas deve estar entre 1 e " + numMaxParcelas, "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
				return false;
			}
			
			var arrValor:Array = new String(vlrParcelamento.valor / qtdParcelas.valor).split(".");
			
			if((arrValor[0] < 1) && (arrValor.length > 1) && (arrValor[1].length > 2) && (arrValor[1].substr(0,2) == 0)){
				for(var i:int=qtdParcelas.valor;i>0;i--){
					var arrValorAux:Array = new String(vlrParcelamento.valor / i).split(".");
					if(arrValorAux.length == 1 || arrValorAux[1].length <= 2){
						tabNav.selectedIndex = 1;
						Alerta.show("O valor do parcelamento somente poderá ser dividido em no máximo "+i+" parcelas.", "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
						return false;																							
					}
				}
			}				
			
			if(diaDebito.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("O campo Dia do Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, diaDebito);
				return false;
			}
			
			if(diaDebito.valor > 31) {
				tabNav.selectedIndex = 1;
				Alerta.show("Dia para Débito inválido.", "ATENÇÃO", Alerta.ALERTA_OK, diaDebito);
				return false;
			}
			
			if(cboTipoIntegParcela.selectedItem == null){
				tabNav.selectedIndex = 1;
				Alerta.show("O campo Forma de Débito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoIntegParcela);
				return false;				
			}				
			
			if(cboTipoIntegParcela.selectedItem.codListaItem == 2){				
				if(cboCcoParcela.text == ""){
					tabNav.selectedIndex = 1;
					Alerta.show("O número da conta corrente é obrigatória para a forma de débito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, cboCcoParcela);
					return false;										
				}
			}
			
			if(cboTipoIntegParcela.selectedItem.codListaItem == 3){				
				if(cboMatriculaParcela.text == ""){
					tabNav.selectedIndex = 1;
					Alerta.show("O número matrícula é obrigatória para a forma de débito folha.", "ATENÇÃO", Alerta.ALERTA_OK, cboMatriculaParcela);
					return false;										
				}
			}
			
			return true;
		}
		
		public function atualizaValores():void{
			var totalParcelado:Number = 0;
			
			if(listaParcelas.length > 0){
				
				var sort:Sort = new Sort(); 				
				sort.fields = [new SortField("dataVencimentoOrdenacao")];									
				listaParcelas.sort = sort;
				listaParcelas.refresh();				
				listaParcelasAux = new ArrayCollection();			
				
				var numParcela:int=1;				
				for(var i:int=0; i < listaParcelas.length; i++){
					if(listaParcelas[i].numParcela != 0){
						listaParcelas[i].numParcela = numParcela;
						totalParcelado = totalParcelado+listaParcelas[i].valorParcela;
						numParcela++;
					}
					listaParcelasAux.addItem(listaParcelas[i]);
					dataUltimaParcela = listaParcelas[i].dataVencimento;
				}	
				
				vlrParcelar.valor = (vlrParcelamento.valor - new Number(totalParcelado.toFixed(2)));					
				grid.dataProvider = listaParcelasAux.list;	
			}
		}
		
		private function retornoGerarParcelas(evt:ResultEvent):void {	
			
			this.listaParcelas = new ArrayCollection();			
			this.listaParcelas.list = evt.result.dados["listaParcelas"] as ArrayCollection;					
			this.listaParcelas.addItemAt({
				numParcela:0,
				dataVencimento:dataSubscricao.text,
				valorParcela:vlrInteg.valor,
				idTipoInteg:cboTipoInteg.selectedItem.codListaItem,
				descNumMatriculaFunc:cboMatricula.text,
				numContaCorrente:cboCco.text,
				dataVencimentoOrdenacao:"00000000"},0);							
			
			grid.columns[0].labelFunction = formataParcela;
			grid.columns[3].labelFunction = formataTipoInteg;
			
			grid.dataProvider = listaParcelas.list;	
			atualizaValores();
			btIncluir.enabled = true;
			btExcluir.enabled = true;
			
			MostraCursor.removeBusyCursor();						
			
		}
		
		private function retornoObterPropostaSubscricao(event:ResultEvent):void {
			listaCco.removeAll();
			_dadosRetorno = event.result.dados;
			subscricaoRenVO = _dadosRetorno["propostaSubscricao"];
			
			//Aba subscrição
			vlrSubs.valor = subscricaoRenVO.vlrSubs;			
			vlrInteg.valor = subscricaoRenVO.vlrInteg;
			cboMatricula.dataProvider = _dadosRetorno["comboTrabalha"] as ArrayCollection;	
			cboMatriculaParcela.dataProvider = _dadosRetorno["comboTrabalha"] as ArrayCollection;						
			listaCco.addAll(_dadosRetorno["comboCco"] as ArrayCollection);
			cboCco.dataProvider = listaCco.list;
			cboCco.selectedIndex = 0;
			//aba parcelamento						
			vlrParcelamento.valor = subscricaoRenVO.vlrParcelas;
			vlrParcelar.valor = subscricaoRenVO.vlrParcelas;
			qtdParcelas.valor = subscricaoRenVO.qtdParcelas;
			diaDebito.valor = subscricaoRenVO.diaDebito;
			cboCcoParcela.dataProvider = listaCco.list;
			cboCcoParcela.selectedIndex = 0;			
			cboTipoInteg.procuraItemPorNome(subscricaoRenVO.tipoInteg, "codListaItem");
			cboTipoIntegParcela.procuraItemPorNome(subscricaoRenVO.tipoInteg, "codListaItem");
			bolSubscricaoProposta = subscricaoRenVO.bolSubscricaoProposta;
			
			cboCco.visible = false;
			cboCcoParcela.visible = false;
			labelCco.visible = false;
			labelCcoParcela.visible = false;
			cboMatricula.visible = false;
			cboMatriculaParcela.visible = false;
			labelMatricula.visible = false;
			labelMatriculaParcela.visible = false;							
			
			if(subscricaoRenVO.tipoInteg == 2){
				cboCco.visible = true;
				cboCcoParcela.visible = true;
				labelCco.visible = true;
				labelCcoParcela.visible = true;
			}else if(subscricaoRenVO.tipoInteg == 3){
				cboMatricula.visible = true;
				cboMatriculaParcela.visible = true;
				labelMatricula.visible = true;
				labelMatriculaParcela.visible = true;				
			}
			
			//regras tela
			if(bolSubscricaoProposta == 0){
				
				listaTipoSubscricao = new ArrayCollection();			
				listaTipoSubscricao.addItemAt({codListaItem:"1",descListaItem:"INGRESSO"},0);											
				cboTipoSubscricao.dataProvider = listaTipoSubscricao.list;								
				cboTipoSubscricao.enabled = false;									
				
				vlrSubs.enabled = false;
				vlrInteg.enabled = false;				
				qtdParcelas.enabled = false;
				
				if(vlrParcelamento.valor <=0){				
					vlrParcelar.enabled = false;
					qtdParcelas.enabled = false;
					diaDebito.enabled = false;
					cboTipoIntegParcela.enabled = false;
					cboCcoParcela.enabled = false;
					cboMatriculaParcela.enabled = false;
					btGerarParcelas.enabled = false;
					btIncluir.enabled = false;
					btExcluir.enabled = false;
				}
				
			}else{

				listaTipoSubscricao = new ArrayCollection();			
				listaTipoSubscricao.addItemAt({codListaItem:"2",descListaItem:"ESTATUTÁRIA"},0);											
				listaTipoSubscricao.addItemAt({codListaItem:"3",descListaItem:"VOLUNTÁRIA"},1);											
				listaTipoSubscricao.addItemAt({codListaItem:"4",descListaItem:"CAMPANHA"},2);											
				cboTipoSubscricao.dataProvider = listaTipoSubscricao.list;								

				cboTipoSubscricao.enabled = true;
				vlrSubs.enabled = true;
				vlrSubs.text = "";
				vlrInteg.enabled = true;
				vlrInteg.text = "";
				qtdParcelas.enabled = true;
				diaDebito.enabled = true;
				qtdParcelas.text = "";
				
				limparDadosParcelamento();
			}
			qtdCotas.enabled = false;
			vlrParcelamento.enabled = false;
			vlrParcelar.enabled = false;			
			
			if(vlrSubs.valor<=vlrInteg.valor){
				abaParcelamento.enabled = false;
			}else{
				abaParcelamento.enabled = true;
			}
			
			calcularValorParcela();
			tabNav.selectedIndex = 0;
			MostraCursor.removeBusyCursor();
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
			MostraCursor.removeBusyCursor();
		}
		
		private function limparDadosParcelamento():void{			
			qtdCotas.valor = 0;
			qtdParcelas.enabled = true;
			diaDebito.enabled = true;
			cboTipoIntegParcela.enabled = true;
			cboCcoParcela.enabled = true;
			cboMatriculaParcela.enabled = true;
			btGerarParcelas.enabled = true;
			btIncluir.enabled = false;
			btExcluir.enabled = false;			
		}
		
		private function calcularValorParcela(evt:FocusEvent=null):void {
			if(vlrSubs.valor > 0 && vlrInteg.valor > 0 && vlrSubs.valor != vlrInteg.valor && cboTipoInteg.selectedItem != null) {				
				
				if(vlrSubs.valor < vlrInteg.valor){
					this.listaParcelas = null;			
					abaParcelamento.enabled = false;				
					vlrParcelamento.valor = 0;
					vlrParcelar.valor = 0;
					return;
				}
				
				abaParcelamento.enabled = true;
				vlrParcelamento.valor = NumeroUtil.ajustarArredondamento(vlrSubs.valor - vlrInteg.valor);
				vlrParcelar.valor = NumeroUtil.ajustarArredondamento(vlrSubs.valor - vlrInteg.valor);
				
				limparDadosParcelamento();
				
				this.listaParcelas = new ArrayCollection();			
				this.listaParcelas.addItemAt({
					numParcela:0,
					dataVencimento:dataSubscricao.text,
					valorParcela:vlrInteg.valor,
					idTipoInteg:cboTipoInteg.selectedItem.codListaItem,
					descNumMatriculaFunc:cboMatricula.text,
					numContaCorrente:cboCco.text,
					dataVencimentoOrdenacao:"00000000"},0);										
				grid.columns[0].labelFunction = formataParcela;
				grid.columns[3].labelFunction = formataTipoInteg;
				
				grid.dataProvider = listaParcelas.list;					
			}else{
				this.listaParcelas = null;			
				abaParcelamento.enabled = false;				
				vlrParcelamento.valor = 0;
				vlrParcelar.valor = 0;
			}			
			qtdCotas.valor = int(vlrSubs.valor / valorCota);
		}
		

		
		public function carregarDados():void {
			lblNumContaCapital.text = numContaCapital.toString();
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idContaCapital = idContaCapital;
			dto.dados.idPessoa = pessoaSelecionada.idPessoa;
			dto.dados.idPessoaLegado = pessoaSelecionada.idPessoaLegado;
			servico.source = SERVICO_SOURCE;
			servico.obterPropostaSubscricao(dto);
		}		
		
		
		public function gravarRegistro():void {
			validarIncluir(null);
		}
	
		
		/*======================================= ======== ======== 
		Função auxiliar: Formata Parcela
		======================================= ======== ======== */		
		private function formataParcela(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";	
			
			if(vlr.numParcela == 0){
				retorno = vlr.numParcela + " (à vista)";				
			}else{
				retorno = vlr.numParcela;				
			}
			return retorno;
		}
		
		/*======================================= ======== ======== 
		Função auxiliar: Formata Data
		======================================= ======== ======== */		
		private function formataDataGrid(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";			
			retorno = FormataData.formataData(vlr[cln.dataField].data);
			return retorno;
		}
		
		/*======================================= ======== ======== 
		Função auxiliar: Formata tipo integ
		======================================= ======== ======== */		
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
		
		private function validar():Boolean {
			
			return true;
		}
		
		/**
		 * Carrega valores da camada de apresentacao para o VO 
		 */
		private function carregarVO():SubscricaoRenVO {
			
			vo.vlrSubs = vlrSubs.valor;
			vo.vlrInteg = vlrInteg.valor;
			vo.qtdParcelas = qtdParcelas.valor;
			vo.vlrParcelas = vlrParcelamento.valor;
			vo.diaDebito = diaDebito.valor;
			vo.tipoInteg = new Number(cboTipoInteg.selectedItem.codListaItem);
			vo.numContaCorrente = new Number(cboCco.text);
			vo.descNumMatriculaFunc = cboMatricula.text;
			vo.idTipoSubscricao = new Number(cboTipoSubscricao.selectedItem.codListaItem);
			vo.bolSubscricaoProposta = bolSubscricaoProposta;
			vo.idPessoaLegado = pessoaSelecionada.idPessoaLegado;
			vo.idPessoa = pessoaSelecionada.idPessoa;
			vo.idInstituicao = pessoaSelecionada.idInstituicao;
			vo.idContaCapital = idContaCapital;
			vo.numContaCapital = numContaCapital;
			
			return vo;
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}		
		
		private function cancelar(evt:MouseEvent):void {			
			
			cboTipoInteg.selectedIndex = 0;
			cboTipoSubscricao.selectedIndex = 0;
			
			vlrSubs.text = "";
			vlrInteg.text = "";			
			tabNav.selectedIndex = 0;
			
			cboTipoSubscricao.enabled = true;
			
			vlrSubs.enabled = true;
			vlrInteg.enabled = true;	
			
			cboCco.visible = false;
			labelCco.visible = false;
			
			cboMatricula.visible = false;
			labelMatricula.visible = false;
			
			cboCcoParcela.visible = false;
			labelCcoParcela.visible = false;
			
			cboMatriculaParcela.visible = false;
			labelMatriculaParcela.visible = false;
			
			this.listaParcelas = null;			
			abaParcelamento.enabled = false;				
			vlrParcelamento.valor = 0;
			vlrParcelar.valor = 0;			
			
			limparDadosParcelamento();
			
			MostraCursor.removeBusyCursor();			
		}		
				
		public function carregarRegistro(obj:Object):void{ }
		
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