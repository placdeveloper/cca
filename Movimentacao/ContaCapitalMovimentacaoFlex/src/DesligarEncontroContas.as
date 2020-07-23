package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.DateField;
	import mx.events.FlexEvent;
	import mx.formatters.CurrencyFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.DateTimeEntity;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.PesquisaContaCapitalVO;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.comum.util.NumeroUtil;
	import br.com.sicoob.sisbr.cca.movimentacao.desligarencontrocontas.DesligarEncontroContasConfirmacao;
	import br.com.sicoob.sisbr.cca.movimentacao.desligarencontrocontas.DesligarEncontroContasView;
	import br.com.sicoob.sisbr.cca.vo.DesligarContaCapitalRenVO;
	import br.com.sicoob.sisbr.cca.vo.DevolucaoRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	public class DesligarEncontroContas extends DesligarEncontroContasView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DesligarEncontroContasServico";
		
		private var servico:ServicoJava = new ServicoJava();
		private var cf:CurrencyFormatter;
		private var dataProduto:String;
		private var cca:PesquisaContaCapitalVO; 
		
		public function DesligarEncontroContas() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			controlarServico();
			controlarEventos();	
			obterDefinicoes();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterInformacoesDesligamento.addEventListener(ResultEvent.RESULT, retornoObterInformacoes);
			servico.validarDesligamento.addEventListener(ResultEvent.RESULT, retornoValidarDesligamento);
			servico.gerarParcelas.addEventListener(ResultEvent.RESULT, retornoGerarParcelas);
		}
		
		private function controlarEventos():void {
			btGerarParcelas.addEventListener(MouseEvent.CLICK, gerarParcelas);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btEfetivar.addEventListener(MouseEvent.CLICK, efetivar);
			btLimpar.addEventListener(MouseEvent.CLICK, limpar);
			optGroupFormaDevolucao.addEventListener(Event.CHANGE, onChangeFormaDevolucao);
			dtDataInicioDevolucao.addEventListener(Event.CHANGE, onChangeDataInicioDevolucao);
			
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoProcurarCCA);
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO, retornoContaCapitalNaoEncontrada);
		}
		
		private function obterDefinicoes():void {
			servico.obterDefinicoes(new RequisicaoReqDTO());
			formatoMonetario();
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			var dados:Object = event.result.dados;
			dataProduto = dados["dataAtualProduto"];
			rtDataDesligamento.text = dataProduto;
			cboMotivoDesligamento.dataProvider = dados["motivosDesligamento"];
			cboFormaCredito.dataProvider = dados["formasCredito"];
			MostraCursor.removeBusyCursor();
		}
		
		private function formatoMonetario():void {
			cf = new CurrencyFormatter();
			cf.precision="2";
			cf.rounding="none";
			cf.decimalSeparatorTo=",";
			cf.thousandsSeparatorTo=".";
			cf.useThousandsSeparator="true";
			cf.useNegativeSign="true";
			cf.currencySymbol="";
		}
		
		private function retornoProcurarCCA(event:SelecionarContaCapitalEvent):void {
			cca = procurarCCA.resultadoPesquisaVO;
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.idContaCapital = cca.idContaCapital;
			reqDTO.dados.numMatricula = cca.numContaCapital;
			servico.obterInformacoesDesligamento(reqDTO);
		}
		
		private function retornoContaCapitalNaoEncontrada(event:SelecionarContaCapitalEvent):void {
			cca = null;
			zerarValores();
			verificarValorDisponivel();
		}
		
		private function retornoObterInformacoes(event:ResultEvent):void {
			zerarValores();
			var dados:Object = event.result.dados;
			txtIntegralizado.valor = dados["valorIntegralizado"];
			txtDisponivel.valor = txtIntegralizado.valor - txtEmprestimosAPagar.valor;
			verificarValorDisponivel();
			gridEmprestimos.dataProvider = dados["contratos"];
			gridEmprestimos.columns[2].labelFunction = formataData;
			gridEmprestimos.columns[4].labelFunction = formataValorMonetario;
			MostraCursor.removeBusyCursor();
		}
		
		private function efetivar(evt:Event):void {
			if(validar()) {
				var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
				var vo:DesligarContaCapitalRenVO = new DesligarContaCapitalRenVO();
				vo.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
				vo.vlrInteg = txtIntegralizado.valor;
				reqDTO.dados.vo = vo;
				servico.validarDesligamento(reqDTO);
			}
		}
		
		private function retornoValidarDesligamento(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			if(!event.result.dados["valido"] && event.result.dados["impedimentos"]) {
				Alerta.show(event.result.dados["msg"], "ATENÇÃO", Alerta.ALERTA_OK, null, emitirImpedimentos);
				return;
			} 
			if (event.result.dados["valido"]) {
				var confirmacao:DesligarEncontroContasConfirmacao = 
					new DesligarEncontroContasConfirmacao({
						idContaCapital: cca.idContaCapital,
						descCCA: cca.numContaCapital + ' - ' + cca.nome,
						idMotivoDesligamento: cboMotivoDesligamento.selectedItem.codListaItem,
						descMotivoDesligamento: cboMotivoDesligamento.selectedItem.descListaItem,
						dataDesligamento: dataProduto,
						valores: [{'valorIntegralizado': txtIntegralizado.valor, 'sinal1': '-', 
							'valorEmprestimos': txtEmprestimosAPagar.valor, 'sinal2': '=',
							'valorDisponivelDevolucao': txtDisponivel.valor}],
						emprestimosLiquidar: getEmprestimosSelecionados(),
						emprestimosAbertos: gridEmprestimos.dataProvider,
						parcelas: gridParcelas.dataProvider,
						idInstituicao: cca.idInstituicao,
						numContaCapital: cca.numContaCapital,
						tipoInteg: cboFormaCredito.selectedItem.codListaItem,
						idPessoaLegado: cca.idPessoaLegado,
						idPessoa: cca.idPessoa,
						vlrDevolucao: txtDisponivel.valor,
						vlrInteg: txtIntegralizado.valor,
						vlrEmprestimos: getValorEmprestimos()
					});
				confirmacao.destino = this.destino;
				var janela:Janela = new Janela();
				janela.title = "CONFIRMAÇÃO";
				janela.removeAllChildren();
				janela.addChild(confirmacao);
				janela.abrir(this, true, true);
			}
		}
		
		private function getEmprestimosSelecionados():ArrayCollection {
			var emprestimosSelecionados:ArrayCollection = new ArrayCollection();
			var dados:Object = this.gridEmprestimos.dataProvider;
			if (dados != null) {
				for (var i:int=0; i < dados.length; i++) {
					if (dados[i].selecionado) {
						emprestimosSelecionados.addItem(dados[i]);
					}
				}
			}
			return emprestimosSelecionados;
		}
		
		private function getValorEmprestimos():Number {
			var valor:Number = 0;
			var dados:Object = this.gridEmprestimos.dataProvider;
			if (dados != null) {
				for (var i:int=0; i < dados.length; i++) {
					valor += dados[i].valorQuitacao;
				}
			}
			return valor;
		}
		
		private function validar():Boolean {
			if (!procurarCCA.isContaCapitalSelecionada()) {
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCA.txtNumCCA);
				return false;
			}
			if (cboMotivoDesligamento.selectedItem == null) {
				Alerta.show("O campo Motivo Desligamento é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCA.txtNumCCA);
				return false;
			}
			if (txtDisponivel.valor < 0) {
				Alerta.show("O valor do empréstimo a pagar não pode ser maior que o valor integralizado.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			if (tabDevolucao.enabled) {
				if (dtDataInicioDevolucao.selectedDate == null) {
					Alerta.show("O campo Data Início Devolução é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, dtDataInicioDevolucao);
					return false;
				}
				if (optParcelado.selected) {
					var hoje:Date = new Date();
					hoje.setHours(0,0,0,0);
					if (dtDataInicioDevolucao.selectedDate.getTime() < hoje.getTime()) {
						Alerta.show("A Data Início Devolução deve ser igual ou superior a data atual.", "ATENÇÃO", Alerta.ALERTA_OK, dtDataInicioDevolucao);
						return false;
					}
				}
				if (cboFormaCredito.selectedItem == null) {
					Alerta.show("O campo Forma de Crédito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboFormaCredito);
					return false;
				}
				if (txtQtdParcelas.text == "") {
					Alerta.show("O campo Qtde Parcelas é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, txtQtdParcelas);
					return false;
				}
				if (txtQtdParcelas.valor <= 0) {
					Alerta.show("Para a forma de devolução parcelada a quantidade de parcelas deve ser maior que 0.", "ATENÇÃO", Alerta.ALERTA_OK, txtQtdParcelas);
					return false;
				}
				if (gridParcelas.dataProvider == null || gridParcelas.dataProvider.length == 0) {
					Alerta.show("É necessário gerar parcela(s) de devolução para efetuar o desligamento.", "ATENÇÃO", Alerta.ALERTA_OK);
					return false;
				}
			}
			return true;
		}
		
		private function emitirImpedimentos(evt:MouseEvent):void {
			if (cca != null) {
				
				//jboss
//				var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
				//was
				var dtoRel:ParametroDTO = new ParametroDTO();
				
				//comum
				dtoRel.dados.idContaCapital = cca.idContaCapital;
				dtoRel.dados.idPessoa = cca.idPessoa;
				dtoRel.dados.idInstituicao = cca.idInstituicao;
				dtoRel.dados.esconderEmprestimos = true;
				
				//jboss
//				RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirImpedimentos",
//					SERVICO_REL_SOURCE, dtoRel, "RelImpedimentosDesligamento", destinoVO, "Emitindo relatório",
//					null, false);
				
				//was
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelImpedimentosDesligamentoServicoRemote", 
					dtoRel, "CCA_RelImpedimentosDesligamento", this.destino, "Emitindo relatório...", null, false);
			}
		}
			
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		public function limpar(evt:Event):void {
			limparCCA();
			zerarValores();
		}
		
		private function limparCCA():void {
			procurarCCA.limparCampos();			
			procurarCCA.limparRegistro();			
		}
		
		private function zerarValores():void {
			zerarValoresCabecalho();
			zerarAbaEmprestimos();
			zerarAbaDevolucao();
		}
		
		private function zerarValoresCabecalho():void {
			cboMotivoDesligamento.selectedIndex = 0;
			this.txtIntegralizado.valor = 0;			
			this.txtEmprestimosAPagar.valor = 0;			
			this.txtDisponivel.valor = 0;
		}
		
		private function zerarAbaEmprestimos():void {
			this.gridEmprestimos.dataProvider = new ArrayCollection();
		}
		
		private function zerarAbaDevolucao():void {
			this.optAVista.selected = true;
			this.cboFormaCredito.selectedIndex = 0;
			verificarFormaDevolucao();
			this.gridParcelas.dataProvider = new ArrayCollection();	
		}
		
		private function onChangeFormaDevolucao(event:Event=null):void {
			verificarFormaDevolucao();
			this.gridParcelas.dataProvider = new ArrayCollection();
		}
		
		private function onChangeDataInicioDevolucao(event:Event=null):void {
			this.gridParcelas.dataProvider = new ArrayCollection();
		}
		
		private function verificarValorDisponivel():void {
			if (txtDisponivel.valor > 0) {
				tabDevolucao.enabled = true;
			} else {
				tabDevolucao.enabled = false;
				tabNav.selectedIndex = 0;
			}
		}
		
		private function verificarFormaDevolucao():void {
			if (optAVista.selected) {
				this.dtDataInicioDevolucao.selectedDate = DateField.stringToDate(dataProduto, "DD/MM/YYYY");
				this.dtDataInicioDevolucao.enabled = false;
				this.txtQtdParcelas.valor = 1;
				this.txtQtdParcelas.enabled = false;
			} else {
				this.dtDataInicioDevolucao.enabled = true;
				this.txtQtdParcelas.enabled = true;
			}
		}
		
		private function gerarParcelas(event:Event=null):void {
			if(!validarParcelas()) {
				return;
			}				
			
			var vo:DevolucaoRenVO = new DevolucaoRenVO();
			vo.vlrParcelas = txtDisponivel.valor;
			vo.qtdParcelas = txtQtdParcelas.valor;								
			vo.dtInicioParcelamento = FormataData.formataData(dtDataInicioDevolucao.selectedDate, "DD/MM/YYYY");												
			vo.tipoInteg = new Number(cboFormaCredito.selectedItem.codListaItem);
			
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.vo = vo;				
			this.servico.gerarParcelas(reqDTO);
		}
		
		private function validarParcelas():Boolean {
			if(dtDataInicioDevolucao.selectedDate == null){
				Alerta.show("O campo Data Início Devolução é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, dtDataInicioDevolucao);
				return false;				
			}	
			if (cboFormaCredito.selectedItem == null) {
				Alerta.show("O campo Forma de Crédito é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, cboFormaCredito);
				return false;				
			}
			if (txtQtdParcelas.text == "") {
				Alerta.show("O campo Qtde Parcelas é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, txtQtdParcelas);
				return false;				
			}
			if (txtQtdParcelas.valor <= 0) {
				Alerta.show("Para a forma de devolução parcelada a quantidade de parcelas deve ser maior que 0.", "ATENÇÃO", Alerta.ALERTA_OK, txtQtdParcelas);
				return false;
			}
			return true;
		}
		
		private function retornoGerarParcelas(evt:ResultEvent):void {	
			gridParcelas.dataProvider = evt.result.dados["listaParcelas"] as ArrayCollection;	
			gridParcelas.columns[0].labelFunction = formataParcela;
			gridParcelas.columns[2].labelFunction = formataValorMonetario;
			gridParcelas.columns[3].labelFunction = formataTipoInteg;
			MostraCursor.removeBusyCursor();						
		}
		
		public function formataParcela(vlr:Object, cln:ColunaGrid):String {
			var retorno:String = "";	
			if (vlr.numParcela == 0	&& dataProduto == FormataData.formataData(dtDataInicioDevolucao.selectedDate, "DD/MM/YYYY")) {
				retorno = vlr.numParcela + " (à vista)";				
			} else {
				retorno = vlr.numParcela;				
			}
			return retorno;
		}
		
		public function formataData(vlr:Object, cln:ColunaGrid):String {
			var retorno:String = "";	
			if (vlr[cln.dataField] != null) {
				retorno = FormataData.formata((vlr[cln.dataField] as DateTimeEntity).data, "DD/MM/YYYY");
			}
			return retorno;
		}		
		
		public function formataValorMonetario(vlr:Object, cln:ColunaGrid):String {
			var retorno:String = "";
			if (vlr[cln.dataField] != null) {
				retorno = cf.format(vlr[cln.dataField]);	
			} 
			return retorno;
		}
		
		public function formataTipoInteg(vlr:Object, cln:ColunaGrid):String {
			var retorno:String = "";		
			switch(vlr.idTipoInteg.toString()) {
				case "1":
					retorno = "CAIXA";
					break;
				case "6":
					retorno = "BANCO";
					break;
			}
			return retorno;
		}
		
		public function onClickEmprestimo():void {
			zerarAbaDevolucao();
			var emprestimosSelecionados:ArrayCollection = getEmprestimosSelecionados();
			var totalEmprestimos:Number = 0;
			for (var i:int = 0; i < emprestimosSelecionados.length; i++) {
				totalEmprestimos += emprestimosSelecionados[i].valorQuitacao;
			}
			
			txtEmprestimosAPagar.valor = NumeroUtil.ajustarArredondamento(totalEmprestimos);
			txtDisponivel.valor = NumeroUtil.ajustarArredondamento(txtIntegralizado.valor - txtEmprestimosAPagar.valor);
			verificarValorDisponivel();
		}
		
	}
}