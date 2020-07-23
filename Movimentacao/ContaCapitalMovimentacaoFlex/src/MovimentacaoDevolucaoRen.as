package
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.comum.util.NumeroUtil;
	import br.com.sicoob.sisbr.cca.movimentacao.devolucao.MovimentacaoDevolucaoRenView;
	import br.com.sicoob.sisbr.cca.movimentacao.devolucao.ParcelamentoCadastro;
	import br.com.sicoob.sisbr.cca.util.DataUtilRelatorios;
	import br.com.sicoob.sisbr.cca.vo.DevolucaoRenVO;
	import br.com.sicoob.sisbr.cca.vo.ParcelamentoRenVO;
	
	public class MovimentacaoDevolucaoRen extends MovimentacaoDevolucaoRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DevolucaoContaCapitalServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelContaCapitalServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var _dadosDefinicoes:Object = new Object();
		private var _dadosRetorno:Object = new Object();
		private var janela:Janela = new Janela();
		private var parc:ParcelamentoCadastro = new ParcelamentoCadastro();			
		[Bindable]
		public var listaParcelas:ArrayCollection;
		public var listaParcelasAux:ArrayCollection;
		private var voParcela:ParcelamentoRenVO = new ParcelamentoRenVO();		
		public var dataUltimaParcela:String;
		private var dataAtualProduto:String;
		private var vo:DevolucaoRenVO = new DevolucaoRenVO();
		private var vlrCota:Number = new Number();
		private var numMaxParcelas:int;
		
		[Bindable]
		public var listaTipoInteg:ArrayCollection = new ArrayCollection();
		[Bindable]
		public var listaTipoIntegParcelas:ArrayCollection = new ArrayCollection();
		[Bindable]
		public var listaMotivoDevolucao:ArrayCollection = new ArrayCollection();		
		
		public var listaCco:ArrayCollection = new ArrayCollection();
		
		public function MovimentacaoDevolucaoRen() {
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
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			
			_dadosDefinicoes = event.result.dados;		
			
			cboTipoInteg.dataProvider = listaTipoInteg.list;			
			cboTipoIntegParcela.dataProvider =  listaTipoIntegParcelas.list;										
			cboMotivoDevolucao.dataProvider = listaMotivoDevolucao.list;
			cboModalidade.dataProvider = _dadosDefinicoes["cboModalidade"] as ArrayCollection;
			
			dataAtualProduto = _dadosDefinicoes["dataAtualProduto"];
			procurarCCA.idInstituicao = _dadosDefinicoes.idInstituicao;					
			procurarCCA.txtNumCCA.validarObrigatorio = true;			
			numMaxParcelas = _dadosDefinicoes.numMaxParcelas;		
			
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);			
			servico.gerarParcelas.addEventListener(ResultEvent.RESULT, retornoGerarParcelas);			
			servico.obterDadosDevolucao.addEventListener(ResultEvent.RESULT, retornoObterDadosDevolucao);
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);			
			
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoObterProcurarCCA);	
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO, retornoObterProcurarCCANaoEcontrado);	
			cboTipoInteg.addEventListener(Event.CHANGE, onChangeCboTipoInteg);
			cboTipoIntegParcela.addEventListener(Event.CHANGE, onChangeCboTipoIntegParcela);
			vlrDevolucao.addEventListener(FocusEvent.FOCUS_OUT, calcularValorParcela);				
			vlrAVista.addEventListener(FocusEvent.FOCUS_OUT, calcularValorParcela);	
			qtdAplicacoes.addEventListener(FocusEvent.FOCUS_OUT, atualizarCaptacaoRemunerada);	
			
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, validarIncluir);
			btGerarParcelas.addEventListener(MouseEvent.CLICK, gerarParcelas);						
			btIncluir.addEventListener(MouseEvent.CLICK, abrirParcelamento);
			btExcluir.addEventListener(MouseEvent.CLICK, excluirParcela);	
			btImprimirSolResgate.addEventListener(MouseEvent.CLICK, emitirImpedimentos);			
			
		}				
		
		private function gerarParcelas(evt:MouseEvent):void {
			if(!validarParcelas()) {
				return;
			}				
			
			vo = new DevolucaoRenVO();
			vo.vlrDevolucao = vlrDevolucao.valor;
			vo.vlrAVista = vlrAVista.valor;				
			vo.vlrParcelas = vlrParcelamento.valor;
			vo.qtdParcelas = qtdParcelas.valor;								
			vo.dtInicioParcelamento = FormataData.formataData(dtInicioParcelamento.selectedDate, "DD/MM/YYYY");												
			vo.tipoInteg = new Number(cboTipoIntegParcela.selectedItem.codListaItem);
			vo.numContaCorrente = new Number(cboCcoParcela.text); 
			
			reqDTO.dados.vo = vo;				
			this.servico.gerarParcelas(reqDTO);
		}			
		
		/**
		 * Valida campos de acordo com regras de negocio
		 */
		private function validarParcelas():Boolean {	
			
			if(cboTipoInteg.selectedItem == null){
				tabNav.selectedIndex = 0;
				Alerta.show("O campo Forma de Crédito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoInteg);
				return false;				
			}	
			
			if(cboTipoInteg.selectedItem.codListaItem == 2){				
				if(cboCco.text == ""){
					tabNav.selectedIndex = 0;
					Alerta.show("O número da conta corrente é obrigatória para a forma de crédito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, cboCco);
					return false;										
				}
			}			
			
			if(vlrParcelamento.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("Valor do parcelamento deve ser maior que 0,00.", "ATENÇÃO", Alerta.ALERTA_OK, vlrParcelamento);
				return false;
			}			
			
			if(qtdParcelas.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("Quant. Parcelas deve ser maior que 0", "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
				return false;
			}
			
			if(qtdParcelas.valor > numMaxParcelas) {
				tabNav.selectedIndex = 1;
				Alerta.show("Quant. Parcelas não deve ser maior que " + numMaxParcelas, "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
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
			
			if(dtInicioParcelamento.selectedDate == null){
				tabNav.selectedIndex = 1;
				Alerta.show("O campo Data Início Parcelamento é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, dtInicioParcelamento);
				return false;				
			}				
			
			if(cboTipoIntegParcela.selectedItem == null){
				tabNav.selectedIndex = 1;
				Alerta.show("O campo Forma de Crédito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoIntegParcela);
				return false;				
			}				
			
			if(cboTipoIntegParcela.selectedItem.codListaItem == 2){				
				if(cboCcoParcela.text == ""){
					tabNav.selectedIndex = 1;
					Alerta.show("O número da conta corrente é obrigatória para a forma de crédito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, cboCcoParcela);
					return false;										
				}
			}
			
			return true;
		}			
		
		private function retornoGerarParcelas(evt:ResultEvent):void {	
			
			this.listaParcelas = new ArrayCollection();			
			this.listaParcelas.list = evt.result.dados["listaParcelas"] as ArrayCollection;					
			
			if(vlrAVista.valor > 0){
				this.listaParcelas.addItemAt({numParcela:0,
					dataVencimento:FormataData.formataData(dtInicioParcelamento.selectedDate, "DD/MM/YYYY"),
					valorParcela:vlrAVista.valor,
					idTipoInteg:cboTipoInteg.selectedItem.codListaItem,
					numContaCorrente:cboCco.text,
					dataVencimentoOrdenacao:"00000000"},0);											
			}			
			
			grid.columns[0].labelFunction = formataParcela;
			grid.columns[3].labelFunction = formataTipoInteg;
			
			grid.dataProvider = listaParcelas.list;	
			atualizaValores();
			btIncluir.enabled = true;
			btExcluir.enabled = true;
			
			MostraCursor.removeBusyCursor();						
			
		}
		
		private function retornoObterProcurarCCA(event:SelecionarContaCapitalEvent):void {			
			reqDTO.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			reqDTO.dados.idPessoa = procurarCCA.resultadoPesquisaVO.idPessoaLegado;
			limparComboDevolucao();			
			this.servico.obterDadosDevolucao(reqDTO);
		}		
		
		private function validarIncluir(evt:MouseEvent):void {			
			if(!validarCampos()) {
				return;
			}
			incluir(evt);
		}
		
		private function validarCampos():Boolean {		
			
			if(procurarCCA.txtNumCCA.text == "") {
				tabNav.selectedIndex = 0;
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCA.txtNumCCA);
				return false;
			}		
			
			if(cboMotivoDevolucao.selectedItem == null){
				tabNav.selectedIndex = 0;
				Alerta.show("O campo motivo da devolução é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboMotivoDevolucao);
				return false;										
			}			
			
			if(vlrDevolucao.valor <= 0){
				tabNav.selectedIndex = 0;
				Alerta.show("O valor da devolução deve ser maior que 0.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDevolucao);
				return false;										
			}
			
			if(vlrDisponivel.valor < vlrDevolucao.valor){
				tabNav.selectedIndex = 0;
				Alerta.show("O valor da devolução não pode ser maior que o valor disponível.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDevolucao);
				return false;										
			}			
			
			if(vlrAVista.valor != vlrDevolucao.valor) {				
				if(vlrParcelar.valor > 0){
					tabNav.selectedIndex = 1;
					Alerta.show("A soma dos valores parcelados acrescidos da parcela à vista, deve corresponder ao valor total de parcelamento, não podendo a soma ser inferior ou superior ao total", "ATENÇÃO", Alerta.ALERTA_OK, vlrParcelar);
					return false;					
				}
			}						
			
			if(cboTipoInteg.selectedItem == null){
				tabNav.selectedIndex = 0;
				Alerta.show("O campo forma de crédito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoInteg);
				return false;										
			}			
			
			if(cboTipoInteg.selectedItem.codListaItem == "10") {
				
				if(vlrAVista.valor != vlrDevolucao.valor){
					tabNav.selectedIndex = 0;
					Alerta.show("Para devolução de capital via captação remunerada o valor da devolução é igual o valor à vista.", "ATENÇÃO", Alerta.ALERTA_OK, vlrAVista);
					return false;										
				}
				
				if(new Number(qtdAplicacoes.text) <= 0){
					tabNav.selectedIndex = 0;
					Alerta.show("A quantidade de aplicações deve maior que 0.", "ATENÇÃO", Alerta.ALERTA_OK, qtdAplicacoes);
					return false;										
				}
				
				if (cboMotivoDevolucao.selectedItem.codListaItem != "6"){
					tabNav.selectedIndex = 0;
					Alerta.show("Essa forma de devolução só pode ser realizada em Função de Tempo de Associação e/ou Idade.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoInteg);
					return false;					
				}
				if(cboCco.text == ""){
					tabNav.selectedIndex = 0;
					Alerta.show("O número da conta corrente é obrigatória para a forma de crédito captação remunerada.", "ATENÇÃO", Alerta.ALERTA_OK, cboCco);
					return false;										
				}				
				
			}		
			
			if(cboTipoInteg.selectedItem.codListaItem == "2"){				
				if(cboCco.text == ""){
					tabNav.selectedIndex = 0;
					Alerta.show("O número da conta corrente é obrigatória para a forma de crédito conta corrente.", "ATENÇÃO", Alerta.ALERTA_OK, cboCco);
					return false;										
				}
			}					
						
			return true;
		}	
		
		private function abrirParcelamento(evt:MouseEvent):void{
			
			if(vlrParcelar.valor <= 0){				
				Alerta.show("O valor a parcelar deve ser maior que 0.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;										
			}
			
			janela = new Janela();
			janela.title = "NOVA PARCELA";
			janela.removeAllChildren();
			janela.addChild(parc);
			
			parc.movimentacaoDevolucaoRen = this;
			parc.cboTipoIntegParcela.dataProvider = _dadosRetorno["cboTipoIntegParcela"] as ArrayCollection;										
			parc.cboCcoParcela.dataProvider = _dadosRetorno["comboCco"] as ArrayCollection;
			parc.cboTipoIntegParcela.selectedIndex = 0;
			parc.vlrParcela.text = ""; 				
			
			parc.dtVencimento.selectedDate = DataUtilRelatorios.somarDiasData(DataUtilRelatorios.stringBrToDate(dataUltimaParcela), 30);
			
			parc.cboCcoParcela.visible = false;
			parc.labelCcoParcela.visible = false;
			
			janela.abrir(this,true,true);
			
		}	
		
		private function incluir(evt:MouseEvent=null):void {		
			
			btOk.enabled = false;
			procurarCCA.setFocus();
			
			reqDTO = new RequisicaoReqDTO();			
			reqDTO.dados.vo = carregarVO();							
			
			if(cboTipoInteg.selectedItem["codListaItem"] != "10"){
				
				var arrParcelamentoVO:ArrayCollection = new ArrayCollection();																
				
				if(listaParcelas!=null){
					for(var i:int=0;i<listaParcelas.length;i++){
						voParcela = new ParcelamentoRenVO();
						
						voParcela.numParcela = grid.dataProvider[i].numParcela;
						voParcela.dataVencimento = grid.dataProvider[i].dataVencimento;
						voParcela.valorParcela = grid.dataProvider[i].valorParcela;
						voParcela.idTipoInteg = grid.dataProvider[i].idTipoInteg;
						voParcela.numContaCorrente = grid.dataProvider[i].numContaCorrente;
						voParcela.dataVencimentoOrdenacao = grid.dataProvider[i].dataVencimentoOrdenacao;
						voParcela.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
						voParcela.idMotivoDevolucao = new Number(cboMotivoDevolucao.selectedItem.codListaItem);
						
						arrParcelamentoVO.addItem(voParcela);
					}					 				
				}else{
					voParcela = new ParcelamentoRenVO();
					
					voParcela.numParcela = 0;
					voParcela.dataVencimento = FormataData.formataData(dtInicioParcelamento.selectedDate, "DD/MM/YYYY");				
					voParcela.valorParcela = vlrDevolucao.valor;
					voParcela.idTipoInteg = new Number(cboTipoInteg.selectedItem.codListaItem);
					voParcela.numContaCorrente = new Number(cboCco.text);
					voParcela.dataVencimentoOrdenacao = grid.dataProvider[i].dataVencimentoOrdenacao;
					voParcela.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
					voParcela.idMotivoDevolucao = new Number(cboMotivoDevolucao.selectedItem.codListaItem);
					
					arrParcelamentoVO.addItem(voParcela);				
				}
				
				reqDTO.dados.listaParcelasVO = arrParcelamentoVO;					
			}
			this.servico.incluir(reqDTO);			
			btOk.enabled = true;			
			
		}		
		
		/**
		 * Carrega valores da camada de apresentacao para o VO
		 */
		private function carregarVO():DevolucaoRenVO{
			
			vo = new DevolucaoRenVO();
			
			vo.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			vo.idInstituicao = procurarCCA.resultadoPesquisaVO.idInstituicao;
			vo.numContaCapital = procurarCCA.resultadoPesquisaVO.numContaCapital;
			vo.qtdParcelas = qtdParcelas.valor;
			vo.vlrParcelas = vlrParcelamento.valor;
			vo.tipoInteg = new Number(cboTipoInteg.selectedItem.codListaItem);
			vo.idMotivoDevolucao = new Number(cboMotivoDevolucao.selectedItem.codListaItem);
			
			//Captacao Remunerada
			vo.qtdAplicacao = qtdAplicacoes.valor;
			vo.vlrPorAplicacao = vlrPorAplicacao.valor;
			if(cboModalidade.selectedItem != null){
				vo.idModalidadeAplicacao = Number(cboModalidade.selectedItem.codListaItem);			
			}
			
			vo.numContaCorrente = new Number(cboCco.text);
			vo.idPessoaLegado = procurarCCA.resultadoPesquisaVO.idPessoaLegado;
			vo.idPessoa = procurarCCA.resultadoPesquisaVO.idPessoa;
			vo.vlrAVista = vlrAVista.valor;
			vo.dtInicioParcelamento = FormataData.formataData(dtInicioParcelamento.selectedDate, "DD/MM/YYYY");
			vo.vlrDevolucao = vlrDevolucao.valor;
			
			if(vlrAVista.valor == 0){
				vo.tipoInteg = grid.dataProvider[0].idTipoInteg;	
				vo.vlrAVista = grid.dataProvider[0].valorParcela;
			}			
			
			return vo;
			
		}
		
		private function retornoObterProcurarCCANaoEcontrado(event:SelecionarContaCapitalEvent):void {			
			iniciarTelaCancelar();
		}
		
		private function retornoObterDadosDevolucao(event:ResultEvent):void {
			
			iniciarTela();			
			limparComboDevolucao();
			
			_dadosRetorno = event.result.dados;
			
			vlrDevolver.valor = _dadosRetorno["vlrDevolver"];			
			vlrMinimoExigido.valor = _dadosRetorno["vlrMinimoExigido"];			
			vlrBloqueado.valor = _dadosRetorno["vlrBloqueado"];			
			
			vlrDisponivel.valor = NumeroUtil.ajustarArredondamento(vlrDevolver.valor - (vlrMinimoExigido.valor + vlrBloqueado.valor));			
			
			if(vlrDisponivel.valor <= 0){		
				vlrDisponivel.valor = 0;
				btOk.enabled = false;				
			}else{
				btOk.enabled = true;				
			}
			
			cboMotivoDevolucao.enabled = true;						
			vlrDevolucao.enabled = true;						
			cboTipoInteg.enabled = true;						
			vlrAVista.enabled = true;		
			
			listaTipoInteg.addAll(_dadosRetorno["cboTipoInteg"] as ArrayCollection);			
			listaTipoIntegParcelas.addAll(_dadosRetorno["cboTipoIntegParcela"] as ArrayCollection);										
			listaMotivoDevolucao.addAll(_dadosRetorno["cboMotivoDevolucao"] as ArrayCollection);	
			
			if(_dadosRetorno["idSituacaoContaCapital"] == "1"){
				descSituacaoContaCapital.text = "Ativo";
				lblDevolver.text = "Valor Integralizado (R$):";
				btImprimirSolResgate.enabled = true;
				cboMotivoDevolucao.enabled = true;
			}else{
				descSituacaoContaCapital.text = "Desligado";			
				lblDevolver.text = "Valor a Devolver (R$):";
				btImprimirSolResgate.enabled = false;
				cboMotivoDevolucao.procuraItemPorNome(_dadosRetorno["idSituacaoContaCapital"], "codListaItem");		
				cboMotivoDevolucao.enabled = false;
			}			
			
			qtdCotas.valor = 0;							
			vlrCota = _dadosRetorno["vlrCota"];
			
			listaCco.addAll(_dadosRetorno["comboCco"] as ArrayCollection);
			cboCco.dataProvider = listaCco.list;
			cboCco.selectedIndex = 0;
			cboCcoParcela.dataProvider = listaCco.list;
			cboCcoParcela.selectedIndex = 0;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function iniciarTela():void {
			
			descSituacaoContaCapital.text = "";			
			vlrDevolver.valor = 0;			
			vlrMinimoExigido.valor = 0;			
			vlrBloqueado.valor = 0;			
			qtdCotas.valor = 0;							
			vlrDisponivel.valor = 0;			
			vlrDevolucao.valor = 0;
			vlrAVista.valor = 0;
			qtdAplicacoes.valor = 0;
			qtdParcelas.valor = 0;
			
			cboCco.visible = false;
			labelCco.visible = false;				
			labelModalidade.visible = false;
			cboModalidade.visible = false;
			labelQtdAplicacoes.visible = false;
			qtdAplicacoes.visible = false;
			labelVlrAplicacoes.visible = false;
			vlrPorAplicacao.visible = false;
			
			cboMotivoDevolucao.enabled = false;						
			vlrDevolucao.enabled = false;						
			cboTipoInteg.enabled = false;						
			vlrAVista.enabled = false;			
			
			cboCcoParcela.visible = false;
			labelCcoParcela.visible = false;				
			
			btImprimirSolResgate.enabled = false;
			abaParcelamento.enabled = false;
			tabNav.selectedIndex = 0;
			
			btOk.enabled = false;
			
		}		
		
		
		private function iniciarTelaCancelar():void {
			
			limparComboDevolucao();
			
			procurarCCA.idInstituicao = _dadosDefinicoes.idInstituicao;					
			
			procurarCCA.txtNumCCA.text = "";
			procurarCCA.txtNome.text = "";				
			procurarCCA.resultadoPesquisaVO.numContaCapital = 0;			
			procurarCCA.resultadoPesquisaVO.idContaCapital = 0;		
			
			iniciarTela();
			
		}			
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			if(validarFechar()) {
				super.fecharJanela();
			} else {
				Alerta.show("Informações não foram salvas! Deseja realmente sair?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, confirmarFechar);
			}
		}
		
		private function confirmarFechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		/**
		 * Verifica se os dados originais foram alterados
		 */
		private function validarFechar():Boolean {			
			
			if(procurarCCA.txtNumCCA.text != "") {
				return false;
			}
			
			return true;
		}
		
		/**
		 * Retorna os valores da camada de apresentacao para o estado inicial 
		 */
		private function cancelar(evt:MouseEvent):void {
			iniciarTelaCancelar();
		}
		
		private function onChangeCboTipoInteg(evt:Event):void {
			if(cboTipoInteg.selectedItem != null){
				
				cboCco.visible = false;
				labelCco.visible = false;				
				labelModalidade.visible = false;
				cboModalidade.visible = false;
				labelQtdAplicacoes.visible = false;
				qtdAplicacoes.visible = false;
				labelVlrAplicacoes.visible = false;
				vlrPorAplicacao.visible = false;
				vlrAVista.valor = 0;
				vlrAVista.enabled = true;
				abaParcelamento.enabled = true;
				
				switch (cboTipoInteg.selectedItem["codListaItem"]){
					case "2":
						cboCco.visible = true;
						labelCco.visible = true;
						break;
					case "10":
						cboCco.visible = true;
						labelCco.visible = true;
						labelModalidade.visible = true;
						cboModalidade.visible = true;
						labelQtdAplicacoes.visible = true;
						qtdAplicacoes.visible = true;
						labelVlrAplicacoes.visible = true;
						vlrPorAplicacao.visible = true;	
						vlrAVista.valor = vlrDevolucao.valor;
						vlrAVista.enabled = false;
						abaParcelamento.enabled = false;
						break;
				}
				
				calcularValorParcela();
			}
		}	
		
		private function onChangeCboTipoIntegParcela(evt:Event):void {
			if(cboTipoIntegParcela.selectedItem != null){				
				cboCcoParcela.visible = false;
				labelCcoParcela.visible = false;
				if(cboTipoIntegParcela.selectedItem["codListaItem"] =="2"){					
					cboCcoParcela.visible = true;
					labelCcoParcela.visible = true;
				}
			}
		}			
		
		private function atualizarCaptacaoRemunerada(evt:FocusEvent=null):void {
			if(cboTipoInteg.selectedItem["codListaItem"] == "10"){
				vlrAVista.valor = vlrDevolucao.valor;
				if (qtdAplicacoes.valor != 0){
					vlrPorAplicacao.valor = (vlrDevolucao.valor / qtdAplicacoes.valor);					
				}else{
					vlrPorAplicacao.valor = 0;					
				}
				abaParcelamento.enabled = false;
			}else{
				vlrAVista.valor = 0;
				vlrPorAplicacao.valor = 0;
			}
			if(vlrDevolucao.valor > 0) {
				qtdCotas.text = int(vlrDevolucao.valor / vlrCota).toFixed(0);
			} else {
				qtdCotas.text = "0";
			}
		}
		
		private function excluirParcela(event:MouseEvent=null):void
		{			
			if (grid.selectedIndex >= 0) {							
				Alerta.show("Deseja realmente excluir a parcela " + grid.selectedItem.numParcela + "?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, confirmarExcluirParcela);			
			}else{
				Alerta.show("Escolha a parcela que deseja exlcuir.", "ATENÇÃO", Alerta.ALERTA_OK);				
			}
		}		
		
		private function confirmarExcluirParcela(event:MouseEvent=null):void
		{						
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
		
		public function atualizaValores():void{
			var totalParcelado:Number = 0;
			
			if(listaParcelas.length > 0){
				
				var sort:Sort = new Sort(); 				
				sort.fields = [new SortField("dataVencimentoOrdenacao")];									
				listaParcelas.sort = sort;
				listaParcelas.refresh();				
				listaParcelasAux = new ArrayCollection();			
				
				var numParcela:int=0;
				
				if(vlrAVista.valor > 0){
					numParcela = 1;
				}				
				
				for(var i:int=0; i < listaParcelas.length; i++){
					if(listaParcelas[i].numParcela != 0 || vlrAVista.valor == 0){
						listaParcelas[i].numParcela = numParcela;
						totalParcelado = totalParcelado+listaParcelas[i].valorParcela;
						numParcela++;
					}
					listaParcelasAux.addItem(listaParcelas[i]);
					dataUltimaParcela = listaParcelas[i].dataVencimento;
				}	
				
				vlrParcelar.valor = NumeroUtil.ajustarArredondamento(vlrParcelamento.valor - new Number(totalParcelado.toFixed(2)));						
				grid.dataProvider = listaParcelasAux.list;	
			}
		}
		
		/*======================================= ======== ======== 
		Função auxiliar: Formata Parcela
		======================================= ======== ======== */		
		private function formataParcela(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";	
			
			if(vlr.numParcela == 0 && FormataData.formataData(DateUtils.stringToDate(dataAtualProduto, "DD/MM/YYYY"), "DDMMYYYY") == FormataData.formataData(dtInicioParcelamento.selectedDate, "DDMMYYYY")){
				retorno = vlr.numParcela + " (à vista)";				
			}else{
				retorno = vlr.numParcela;				
			}
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
		
		private function calcularValorParcela(evt:FocusEvent=null):void {		
			
			if(procurarCCA.resultadoPesquisaVO.idContaCapital == 0) {
				abaParcelamento.enabled = false;
				return;
			}			
			
			if(vlrDisponivel.valor < 0){
				tabNav.selectedIndex = 0;
				Alerta.show("O valor diponível para devolução não pode ser menor negativo.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDisponivel);
				return;										
			}			
			
			if(vlrDisponivel.valor < vlrDevolucao.valor){
				tabNav.selectedIndex = 0;
				vlrDevolucao.valor = 0;
				Alerta.show("O valor da devolução não pode ser maior que o valor disponível.", "ATENÇÃO", Alerta.ALERTA_OK, vlrDevolucao);
				return;										
			}			
			
			if(cboTipoInteg.selectedItem != null && cboTipoInteg.selectedItem["codListaItem"] == "10"){			
				atualizarCaptacaoRemunerada();
				abaParcelamento.enabled = false;
				return;
			}
			
			if(vlrDevolucao.valor > 0 && vlrAVista.valor >= 0 && vlrDevolucao.valor != vlrAVista.valor && cboTipoInteg.selectedItem != null) {				
				
				if(vlrDevolucao.valor < vlrAVista.valor){
					this.listaParcelas = null;			
					abaParcelamento.enabled = false;				
					vlrParcelamento.valor = 0;
					vlrParcelar.valor = 0;
					vlrAVista.valor = 0;
					
					Alerta.show("O valor à vista não pode ser maior que o campo valor da devolução.", "ATENÇÃO", Alerta.ALERTA_OK, vlrAVista);
					return;
				}
				
				abaParcelamento.enabled = true;
				vlrParcelamento.text = (vlrDevolucao.valor - vlrAVista.valor).toFixed(2).replace(".",",");
				vlrParcelar.text = (vlrDevolucao.valor - vlrAVista.valor).toFixed(2).replace(".",",");
				qtdCotas.text = int(vlrDevolucao.valor / vlrCota).toFixed(0);
				
				if(vlrAVista.valor > 0){
					dtInicioParcelamento.enabled = false;
					dtInicioParcelamento.selectedDate = DateUtils.stringToDate(dataAtualProduto, "DD/MM/YYYY");					
				}else{
					dtInicioParcelamento.enabled = true;
					dtInicioParcelamento.selectedDate = DateUtils.stringToDate(dataAtualProduto, "DD/MM/YYYY");										
				}
				
				limparDadosParcelamento();
				
				var vlrNumContaCorrente:Number = 0;
				if(cboCco.text != ""){
					vlrNumContaCorrente = new Number(cboCco.text);					
				}				
				
				this.listaParcelas = new ArrayCollection();			
				this.listaParcelas.addItemAt({numParcela:0,dataVencimento:FormataData.formataData(dtInicioParcelamento.selectedDate, "DD/MM/YYYY"),valorParcela:vlrAVista.valor,idTipoInteg:cboTipoInteg.selectedItem.codListaItem,numContaCorrente:vlrNumContaCorrente,dataVencimentoOrdenacao:"00000000"},0);														
				grid.columns[0].labelFunction = formataParcela;
				grid.columns[3].labelFunction = formataTipoInteg;
				
				grid.dataProvider = listaParcelas.list;					
			}else{
				this.listaParcelas = null;			
				abaParcelamento.enabled = false;				
				vlrParcelamento.valor = 0;
				vlrParcelar.valor = 0;
				qtdCotas.text = "0";
			}	
		}
		
		private function limparDadosParcelamento():void{			
			qtdParcelas.enabled = true;
			cboTipoIntegParcela.enabled = true;
			cboCcoParcela.enabled = true;
			btGerarParcelas.enabled = true;
			btIncluir.enabled = false;
			btExcluir.enabled = false;			
		}
		
		private function retornoIncluir(evt:ResultEvent):void {
			
			if(abaParcelamento.enabled) {				
				tabNav.selectedIndex = 1;					
			}else{
				tabNav.selectedIndex = 0;
			}
			
			MostraCursor.removeBusyCursor();
			btOk.enabled = true;			
			
			Alerta.show(evt.result.dados["msg"], "SUCESSO", Alerta.ALERTA_SUCESSO, null, null);
			
			iniciarTelaCancelar();
		}		
		
		private function emitirImpedimentos(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;	
			
			if(procurarCCA.resultadoPesquisaVO.idContaCapital > 0) {
				
//				var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
				var dtoRel:ParametroDTO = new ParametroDTO();
				dtoRel.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
				dtoRel.dados.idInstituicao = procurarCCA.resultadoPesquisaVO.idInstituicao;
				dtoRel.dados.idPessoa = procurarCCA.resultadoPesquisaVO.idPessoa;				
				
//				RelatorioUtil.getRelatorioUtil().emitirRelatorio("gerarSolDevolucaoCapital",
//					SERVICO_REL_SOURCE, dtoRel, "RelSolDevolucaoCapital", destinoVO, "Emitindo relatório",
//					formatoRelatorio, exibirPreImpressao);
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelSolDevolucaoCapitalServicoRemote", 
					dtoRel, "CCA_RelSolDevolucaoCapital", this.destino, "Emitindo relatório...", null, false);
			}
		}		
		
		private function limparComboDevolucao():void {
			listaTipoInteg.removeAll();
			listaTipoIntegParcelas.removeAll();
			listaMotivoDevolucao.removeAll();
			listaCco.removeAll();
		}		
		
	}	
}