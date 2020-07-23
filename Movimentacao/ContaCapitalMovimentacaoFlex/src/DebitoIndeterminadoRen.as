package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.formatters.CurrencyFormatter;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.vo.DebitoIndeterminadoRenVO;
	import br.com.sicoob.sisbr.cca.movimentacao.debitoindeterminado.AlteraDebitoIndeterminadoLoteRen;
	import br.com.sicoob.sisbr.cca.movimentacao.debitoindeterminado.AlteraDebitoIndeterminadoRen;
	import br.com.sicoob.sisbr.cca.movimentacao.debitoindeterminado.CadastroDebitoIndeterminadoRen;
	import br.com.sicoob.sisbr.cca.movimentacao.debitoindeterminado.DebitoIndeterminadoRenView;
	import br.com.sicoob.sisbr.cca.vo.ConsultaDebitoIndeterminadoRenVO;
	import br.com.sicoob.sisbr.cca.vo.QuadroGeralAssociadoVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO", DebitoIndeterminadoRenVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.ConsultaDebitoIndeterminadoRenVO", ConsultaDebitoIndeterminadoRenVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.QuadroGeralAssociadoVO", QuadroGeralAssociadoVO);
	public class DebitoIndeterminadoRen extends DebitoIndeterminadoRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DebitoIndeterminadoServico";
		private const SERVICO_SOURCE_REL:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelDebitoIndeterminadoServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:DebitoIndeterminadoRenVO = new DebitoIndeterminadoRenVO();
		private var cf:CurrencyFormatter;
		private var filtro:String;
		private var telaCadastro:Janela;
		[Bindable]
		public var listaValorCombo:ArrayCollection = new ArrayCollection();
		
		public function DebitoIndeterminadoRen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			
			subTituloCCODiaFixo.texto = "DÉBITOS VIA CCO POR \"DIA FIXO\"";
			subTituloCCOIntervalo.texto = "DÉBITOS VIA CCO POR \"INTERVALO\"";
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
			
			controlarServico();
			controlarEventos();
			
			obterDefinicoes();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			
			servico.excluir.addEventListener(ResultEvent.RESULT, retornoExcluir);
			servico.consultar.addEventListener(ResultEvent.RESULT, retornoConsultar);
		}
		
		private function consultar(evt:Event):void {
			
			if(validar()) {
				this.reqDTO.dados.vo = carregarVO();
				this.servico.consultar(reqDTO);
			}
		}
		
		private function validar():Boolean {
			
			if (cboTipoPesquisa.selectedItem == null) {
				Alerta.show("O campo Tipo Pesquisa é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
				return false;
			}
			
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 2 || cboTipoPesquisa.selectedItem["codListaItem"] == 3) {
				if (comboTipoPesquisa.selectedItem == null) {
					Alerta.show("O campo Valor do filtro é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
					return false;
				}
			}
			
			//Valor
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 4 && valorTipoPeqsuisa.valor == 0) {
				Alerta.show("O campo Valor é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
				return false;
			}
			
			//Dia Debito
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 5) {
				if(valorTipoPeqsuisa.valor < 1 || valorTipoPeqsuisa.valor > 31) {
					Alerta.show("O dia para débito deve ser maior que 0 e menor ou igual a 31.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
					return false;
				}
			}
			
			//Conta Capital
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 6 && valorTipoPeqsuisa.valor == 0) {
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
				return false;
			}
			
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 7) {
				if (valorTipoPeqsuisa.text == "") {
					Alerta.show("O campo Nome é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
					return false;
				}
				if (valorTipoPeqsuisa.length < 3) {
					Alerta.show("Informe pelo menos 3 letras para o Nome", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
					return false;
				}
			}
			
			//CPF/CNPJ
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 8 && valorTipoPeqsuisa.text == "") {
				Alerta.show("O campo CPF/CNPJ é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
				return false;
			}
			
			return true;
		}
		
		private function retornoConsultar(event:ResultEvent):void {
			gridDebitos.dataProvider = event.result.dados["registros"] as ArrayCollection;
			gridDebitos.labelFunction = formataDataGridDebito;
			
			filtro = "" + cboTipoPesquisa.selectedItem["descListaItem"] + " ";
			if(valorTipoPeqsuisa.visible) {
				filtro += valorTipoPeqsuisa.text;
			} else if(comboTipoPesquisa.selectedItem != null) {
				filtro += comboTipoPesquisa.selectedItem["label"];
			}
			
			btAlterar.enabled = false;
			btExcluir.enabled = false;
			btRelatorio.enabled = gridDebitos.dataProvider.length > 0;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btConsultar.addEventListener(MouseEvent.CLICK, consultar);
			valorTipoPeqsuisa.addEventListener(FlexEvent.ENTER, consultar);
			btAlterar.addEventListener(MouseEvent.CLICK, alterar);
			btIncluir.addEventListener(MouseEvent.CLICK, incluir);
			btExcluir.addEventListener(MouseEvent.CLICK, confirmaExcluir);
			cboTipoPesquisa.addEventListener(Event.CHANGE, onChangeTipoPesquisa);
			gridDebitos.addEventListener(MouseEvent.DOUBLE_CLICK, abrirAlterar);
			btRelatorio.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			tabNav.addEventListener(Event.CHANGE, atualizarAbaResumo);
		}
		
		private function atualizarAbaResumo(evt:Event):void {
			if((evt as IndexChangedEvent).newIndex == 0) {
				obterDefinicoes();
			}
		}
		
		private function alterar(evt:MouseEvent):void {
			
			var arrIdsDeb:ArrayCollection = new ArrayCollection();
			var arrIdsNumMatricula:ArrayCollection = new ArrayCollection();
			
			var integralizacaoSemContaCorrente:Boolean = false;
			
			for(var i:int = 0; i < gridDebitos.dataProvider.length; i++) {
				if(gridDebitos.dataProvider[i].selecionado) {
					if(gridDebitos.dataProvider[i].idTipoIntegralizacao != 2) {
						integralizacaoSemContaCorrente = true;
					}
					arrIdsDeb.addItem(gridDebitos.dataProvider[i].idDebitoContaCapital);
					arrIdsNumMatricula.addItem(gridDebitos.dataProvider[i].numContaCapital);
				}
			}
			
			var altera:*;
			if(arrIdsDeb.length > 0) {
				if(arrIdsDeb.length == 1) {
					altera = new AlteraDebitoIndeterminadoRen(arrIdsDeb);
				}
				if(arrIdsDeb.length > 1) {
					
					if(integralizacaoSemContaCorrente) {
						Alerta.show("Apenas débitos por tempo indeterminado via conta corrente podem ser alterados em lote.", "ATENÇÃO", Alerta.ALERTA_OK, null);
						return;
					}
					
					altera = new AlteraDebitoIndeterminadoLoteRen(arrIdsDeb, arrIdsNumMatricula);
				}
				
				altera.destinoPai = this.destino;
				var telaAltera:Janela = new Janela();
				telaAltera.title = "ALTERAR DÉBITO POR TEMPO INDETERMINADO";
				telaAltera.removeAllChildren();
				telaAltera.addChild(altera);
				telaAltera.abrir(this, true, true);
			}
		}
		
		private function abrirAlterar(evt:MouseEvent):void {
			if(gridDebitos.selectedItem != null) {
				var arrIdsDeb:ArrayCollection = new ArrayCollection();
				arrIdsDeb.addItem(gridDebitos.selectedItem.idDebitoContaCapital);
				var altera:AlteraDebitoIndeterminadoRen = new AlteraDebitoIndeterminadoRen(arrIdsDeb);
				altera.destinoPai = this.destino;
				var telaAltera:Janela = new Janela();
				telaAltera.title = "ALTERAR DÉBITO POR TEMPO INDETERMINADO";
				telaAltera.removeAllChildren();
				telaAltera.addChild(altera);
				telaAltera.abrir(this, true, true);
			}
		}
		
		private function obterDefinicoes():void {
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			fomatoMonetario();
			
			gridQuadroGeral.dataProvider = event.result.dados["lstQuadroGeral"] as ArrayCollection;
			
			gridDebCCODia.dataProvider = event.result.dados["lstDebCCODia"] as ArrayCollection;
			gridDebCCODia.labelFunction = formataGridDebCCODia;
			
			gridDebCCOIntervalo.dataProvider = event.result.dados["lstDebCCOIntervalo"] as ArrayCollection;
			gridDebCCOIntervalo.labelFunction = formataGridDebCCOIntervalo;
			
			gridDebFolhaBanco.dataProvider = event.result.dados["lstDebFolhaBanco"] as ArrayCollection;
			
			//default: CPF/CNPJ
			if (cboTipoPesquisa.dataProvider == null || cboTipoPesquisa.dataProvider.length == 0) {
				cboTipoPesquisa.dataProvider = event.result.dados["cboTipoPesquisa"] as ArrayCollection;
				cboTipoPesquisa.selectedIndex = 8;
				onChangeTipoPesquisa(null);
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function fomatoMonetario():void {
			cf = new CurrencyFormatter();
			cf.precision="2";
			cf.rounding="none";
			cf.decimalSeparatorTo=",";
			cf.thousandsSeparatorTo=".";
			cf.useThousandsSeparator="true";
			cf.useNegativeSign="true";
			cf.currencySymbol="R$ ";
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
		}
		
		private function formataDataGridDebito(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			switch(col.dataField) {
				
				case "valor":
					if (obj[col.dataField]) {
						if(obj["idTipoValorDebito"] == 0) {
							retorno = cf.format(obj[col.dataField]);	
						}
						if(obj["idTipoValorDebito"] == 1) {
							retorno = obj[col.dataField] + "% Sal. Assoc." ;	
						}
						if(obj["idTipoValorDebito"] == 2) {
							retorno = obj[col.dataField] + "% Sal. Base" ;		
						}
						if(obj["idTipoValorDebito"] == 3) {
							retorno = obj[col.dataField] + " Cotas";	
						}
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
			return retorno;
		}
		
		private function formataGridDebCCODia(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			switch(col.dataField) {
				
				case "valorTotalPorDiaFixo":
					if (obj[col.dataField]) {
						retorno = cf.format(obj[col.dataField]);
					} else {
						retorno = "";						
					}
				break;
				
				case "dia":
					if (obj[col.dataField]) {
						retorno = obj[col.dataField].toString().length == 1 ? "0" + obj[col.dataField].toString() : obj[col.dataField].toString();
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
			return retorno;
		}
		
		private function formataGridDebCCOIntervalo(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			switch(col.dataField) {
				
				case "valorTotalIntervalo":
					if (obj[col.dataField]) {
						retorno = cf.format(obj[col.dataField]);
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
			return retorno;
		}
		
		private function emitirRelatorio(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;
			
			if(gridDebitos.dataProvider != null && gridDebitos.dataProvider.length > 0) {
				
				var reqDTORel:ParametroDTO = new ParametroDTO();
				reqDTORel.dados.filtro = filtro;
				reqDTORel.dados.idTipoPessoa = this.reqDTO.dados.vo.idTipoPessoa;
				reqDTORel.dados.idFormaDebito = this.reqDTO.dados.vo.idFormaDebito;
				reqDTORel.dados.valor = this.reqDTO.dados.vo.valor;
				reqDTORel.dados.diaDebito = this.reqDTO.dados.vo.diaDebito;
				reqDTORel.dados.numContaCapital = this.reqDTO.dados.vo.numContaCapital;
				reqDTORel.dados.nome = this.reqDTO.dados.vo.nome;
				reqDTORel.dados.cpfCnpj = this.reqDTO.dados.vo.cpfCnpj;
				
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelDebitoIndeterminadoServicoRemote", 
					reqDTORel, "CCA_RelDebitoIndeterminado", this.destino, "Emitindo relatório...", null, false);
			}
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		private function carregarVO():ConsultaDebitoIndeterminadoRenVO {
			var vo:ConsultaDebitoIndeterminadoRenVO = new ConsultaDebitoIndeterminadoRenVO();
			
			//Em Branco
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 1) {
				
			}
			
			//Tipo Pessoa
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 2) {
				vo.idTipoPessoa = comboTipoPesquisa.selectedItem.data;
			}
			
			//Forma Debito
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 3) {
				vo.idFormaDebito = comboTipoPesquisa.selectedItem.data;
			}
			
			//Valor
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 4) {
				vo.valor = valorTipoPeqsuisa.valor;
			}
			
			//Dia Debito
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 5) {
				vo.diaDebito = valorTipoPeqsuisa.valor; 
			}
			
			//Conta Capital
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 6) {
				vo.numContaCapital = valorTipoPeqsuisa.valor; 
			}
			
			//Nome
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 7) {
				vo.nome = valorTipoPeqsuisa.text; 
			}
			
			//CPF/CNPJ
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 8) {
				vo.cpfCnpj = valorTipoPeqsuisa.text; 
			}
			
			return vo;
		}
		
		private function onChangeTipoPesquisa(evt:Event):void {
			var tipoPesquisa:int = 1;
			if (cboTipoPesquisa.selectedItem != null) {
				tipoPesquisa = cboTipoPesquisa.selectedItem["codListaItem"];
			}
			
			listaValorCombo.removeAll();
			
			valorTipoPeqsuisa.text = "";
			comboTipoPesquisa.dataProvider = listaValorCombo.list;
			
			valorTipoPeqsuisa.visible = false;
			valorTipoPeqsuisa.includeInLayout = false;
			comboTipoPesquisa.visible = false;
			comboTipoPesquisa.includeInLayout = false;
			
			//Em Branco
			if(tipoPesquisa == 1) {
				valorTipoPeqsuisa.includeInLayout = true;
				valorTipoPeqsuisa.visible = false;
				return;
			}
			
			//Tipo Pessoa
			if(tipoPesquisa == 2) {
				comboTipoPesquisa.includeInLayout = true;
				comboTipoPesquisa.visible = true;
				
				listaValorCombo.addItem({label: "PESSOA FÍSICA", data: 0});
				listaValorCombo.addItem({label: "PESSOA JURÍDICA", data: 1});
				
				comboTipoPesquisa.dataProvider = listaValorCombo.list;
				return;
			}
			
			//Forma Debito
			if(tipoPesquisa == 3) {
				comboTipoPesquisa.includeInLayout = true;
				comboTipoPesquisa.visible = true;
				
				listaValorCombo.addItem({label: "VIA CCO", data: 2});
				listaValorCombo.addItem({label: "VIA FOLHA", data: 3});
				//listaValorCombo.addItem({label: "VIA BANCO", data: 6});
				
				comboTipoPesquisa.dataProvider = listaValorCombo.list;
				return;
			}
			
			//Valor
			if(tipoPesquisa == 4) {
				valorTipoPeqsuisa.includeInLayout = true;
				valorTipoPeqsuisa.visible = true;
				valorTipoPeqsuisa.restrict = "0-9";
				valorTipoPeqsuisa.maxChars = 13;
				valorTipoPeqsuisa.tipoEntrada = 1;
				valorTipoPeqsuisa.casasDecimais = 2;
				valorTipoPeqsuisa.permitirValoreNegativos = false;
				return;
			}
			
			//Dia Debito
			if(tipoPesquisa == 5) {
				valorTipoPeqsuisa.includeInLayout = true;
				valorTipoPeqsuisa.visible = true;
				valorTipoPeqsuisa.restrict = "0-9";
				valorTipoPeqsuisa.maxChars = 2;
				valorTipoPeqsuisa.tipoEntrada = 1;
				valorTipoPeqsuisa.casasDecimais = 0;
				valorTipoPeqsuisa.permitirValoreNegativos = false;
				return;
			}
			
			//Conta Capital
			if(tipoPesquisa == 6) {
				valorTipoPeqsuisa.includeInLayout = true;
				valorTipoPeqsuisa.visible = true;
				valorTipoPeqsuisa.restrict = "0-9";
				valorTipoPeqsuisa.maxChars = 8;
				valorTipoPeqsuisa.tipoEntrada = 0;
				valorTipoPeqsuisa.casasDecimais = 0;
				valorTipoPeqsuisa.permitirValoreNegativos = false;
				return;
			}
			
			//Nome
			if(tipoPesquisa == 7) {
				valorTipoPeqsuisa.includeInLayout = true;
				valorTipoPeqsuisa.visible = true;
				valorTipoPeqsuisa.restrict = "";
				valorTipoPeqsuisa.maxChars = 50;
				valorTipoPeqsuisa.tipoEntrada = 0;
				return;
			}
			
			//CPF/CNPJ
			if(tipoPesquisa == 8) {
				valorTipoPeqsuisa.includeInLayout = true;
				valorTipoPeqsuisa.visible = true;
				valorTipoPeqsuisa.restrict = "0-9";
				valorTipoPeqsuisa.maxChars = 14;
				valorTipoPeqsuisa.tipoEntrada = 0;
				valorTipoPeqsuisa.permitirValoreNegativos = false;
				return;
			}
			
		}
		
		private function incluir(evt:MouseEvent = null):void {
			var cadastro:CadastroDebitoIndeterminadoRen = new CadastroDebitoIndeterminadoRen();
			
			cadastro.destinoPai = this.destino;
			this.telaCadastro = new Janela();
			telaCadastro.title = "INCLUIR DÉBITO POR TEMPO INDETERMINADO";
			telaCadastro.removeAllChildren();
			telaCadastro.addChild(cadastro);
			telaCadastro.abrir(this, true, true);
		}
		
		public function centralizarTelaCadastro():void {
			this.telaCadastro.centralizarJanela();
		}
		
		private function confirmaExcluir(evt:MouseEvent = null):void {
			Alerta.show("Deseja realmente excluir o(s) débito(s) por tempo indeterminado?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, excluir);
		}
		
		private function excluir(evt:MouseEvent = null):void {
			var arrIdsDeb:ArrayCollection = new ArrayCollection();
			var arrNumMatricula:ArrayCollection = new ArrayCollection();
			for(var i:int = 0; i < gridDebitos.dataProvider.length; i++) {
				if(gridDebitos.dataProvider[i].selecionado) {
					arrIdsDeb.addItem(gridDebitos.dataProvider[i].idDebitoContaCapital);
					arrNumMatricula.addItem(gridDebitos.dataProvider[i].numContaCapital);
				}
			}
			
			if(arrIdsDeb.length > 0) {
				var reqDTODel:RequisicaoReqDTO = new RequisicaoReqDTO();
				reqDTODel.dados.arrIdsDeb = arrIdsDeb;
				reqDTODel.dados.arrNumMatricula = arrNumMatricula;
				servico.excluir(reqDTODel);
				
			} else {
				
				Alerta.show("Marque pelo menos um registro para exclusão.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
			}
		}
		
		private function retornoExcluir(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show("Dados excluídos com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null, recarregarConsulta);
		}
		
		public function recarregarConsulta(me:MouseEvent):void {
			var isConsultaValida:Boolean = true;
			
			//Valor
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 4 && valorTipoPeqsuisa.valor == 0) {
				isConsultaValida = false;
			}
			//Dia Debito
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 5) {
				if(valorTipoPeqsuisa.valor > 31 || valorTipoPeqsuisa.valor == 0) {
					isConsultaValida = false;
				}
			}
			//Conta Capital
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 6 && valorTipoPeqsuisa.valor == 0) {
				isConsultaValida = false;
			}
			//Nome
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 7 && valorTipoPeqsuisa.length < 3) {
				isConsultaValida = false;
			}
			//CPF/CNPJ
			if(cboTipoPesquisa.selectedItem["codListaItem"] == 8 && valorTipoPeqsuisa.text == "") {
				isConsultaValida = false;
			}
			
			if(isConsultaValida) {
				this.reqDTO.dados.vo = carregarVO();
				this.servico.consultar(reqDTO);
			} else {
				gridDebitos.dataProvider = null;
			}
		}
	}
}