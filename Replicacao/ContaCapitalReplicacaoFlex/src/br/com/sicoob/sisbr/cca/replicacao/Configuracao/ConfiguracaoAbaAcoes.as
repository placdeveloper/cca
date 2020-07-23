package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.replicacao.monitoracao.ConfirmacaoOperacaoView;
	import br.com.sicoob.sisbr.cca.replicacao.monitoracao.MonitoracaoDetalhe;
	import br.com.sicoob.sisbr.cca.vo.MonitoracaoCapitalVO;
	import br.com.sicoob.sisbr.cca.vo.ReplicacaoContaCapitalLegadoVO;

	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoCapitalVO", MonitoracaoCapitalVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.ReplicacaoContaCapitalLegadoVO", ReplicacaoContaCapitalLegadoVO);
	public class ConfiguracaoAbaAcoes {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var SERVICO_MONITORACAO_SOURCE:String = "br.com.sicoob.sisbr.cca.replicacao.servicos.MonitoracaoReplicacaoCapitalFachada";
		private var servicoMonitoracao:ServicoJava = new ServicoJava();
		
		private var lista:ArrayCollection = new ArrayCollection();
		private var format:DateFormatter = new DateFormatter();
		
		private var janelaConfirmacao:Janela;
		private var telaConfirmacao:ConfirmacaoOperacaoView;
		
		private var janelaEdicaoJSON:Janela;
		private var telaEdicaoJSON:JanelaEdicaoJSONView;
		
		public function ConfiguracaoAbaAcoes(configuracao:ConfiguracaoReplicacaoCapital) {
			this.configuracao = configuracao;
			inicializarServico();
			inicializarCombos();
			
			this.configuracao.servico.consultarAcoes.addEventListener(ResultEvent.RESULT, retornoConsultar);
			this.configuracao.servico.salvarJSON.addEventListener(ResultEvent.RESULT, retornoSalvarJSON);
			this.configuracao.servico.iniciarReplicacao.addEventListener(ResultEvent.RESULT, retornoIniciarReplicacao);
			
			this.configuracao.chkAcoesIntervalo.addEventListener(MouseEvent.CLICK, onChangeOptAcoes);
			
			this.configuracao.txtAcoesQtdRegistros.addEventListener(FlexEvent.ENTER, consultar);
			this.configuracao.txtAcoesCoop.addEventListener(FlexEvent.ENTER, consultar);
			this.configuracao.txtAcoesDtCad.addEventListener(FlexEvent.ENTER, consultar);
			this.configuracao.txtAcoesDtRep.addEventListener(FlexEvent.ENTER, consultar);
			this.configuracao.btAcoesConsultar.addEventListener(MouseEvent.CLICK, consultar);
			
			this.configuracao.grdAcoes.dataProvider = this.lista;
			this.format.formatString = "DD/MM/YYYY J:NN:SS";
			
			this.configuracao.btAcoesReprocessar.addEventListener(MouseEvent.CLICK, confirmReprocessar);
			this.configuracao.btAcoesInvalidar.addEventListener(MouseEvent.CLICK, confirmInvalidar);
			this.configuracao.btAcoesEditarJSON.addEventListener(MouseEvent.CLICK, abrirJSON);
			this.configuracao.btAcoesModelosJSON.addEventListener(MouseEvent.CLICK, abrirModelosJSON);
			this.configuracao.btAcoesExecutarReplicacao.addEventListener(MouseEvent.CLICK, iniciarReplicacao);
			
			this.servicoMonitoracao.reprocessar.addEventListener(ResultEvent.RESULT, retornoReprocessar);
			this.servicoMonitoracao.invalidar.addEventListener(ResultEvent.RESULT, retornoInvalidar);
			
		}
		
		private function inicializarCombos():void {
			var listaSituacao:ArrayCollection = new ArrayCollection();
			listaSituacao.addItem({codListaItem: "1", descListaItem: "Erros"});
			listaSituacao.addItem({codListaItem: "2", descListaItem: "Erros e Inválidos"});
			listaSituacao.addItem({codListaItem: "4", descListaItem: "Sucesso"});
			this.configuracao.cmbAcoesSituacao.dataProvider = listaSituacao;
			
			var listaTabela:ArrayCollection = new ArrayCollection();
			listaTabela.addItem({codListaItem: "", descListaItem: "Todas"});
			listaTabela.addItem({codListaItem: "1", descListaItem: "Conta Capital"});
			listaTabela.addItem({codListaItem: "2", descListaItem: "Parcelamento"});
			listaTabela.addItem({codListaItem: "3", descListaItem: "Lançamento"});
			this.configuracao.cmbAcoesTabela.dataProvider = listaTabela;
			
			var listaAcao:ArrayCollection = new ArrayCollection();
			listaAcao.addItem({codListaItem: "", descListaItem: "Todas"});
			listaAcao.addItem({codListaItem: "I", descListaItem: "Insert"});
			listaAcao.addItem({codListaItem: "U", descListaItem: "Update"});
			listaAcao.addItem({codListaItem: "D", descListaItem: "Delete"});
			this.configuracao.cmbAcoesAcao.dataProvider = listaAcao;
		}
		
		private function inicializarServico():void {
			servicoMonitoracao.configurarDestino(configuracao.destino);
			servicoMonitoracao.source = SERVICO_MONITORACAO_SOURCE;
			servicoMonitoracao.showBusyCursor = true;
			servicoMonitoracao.bloquearOperacao = true;			
		}
		
		protected function onChangeOptAcoes(event:MouseEvent):void {
			this.configuracao.txtAcoesIntervaloDe.text = "";
			this.configuracao.txtAcoesIntervaloAte.text = "";
			this.configuracao.boxAcoesIntervalo.includeInLayout = this.configuracao.chkAcoesIntervalo.selected;
			this.configuracao.boxAcoesIntervalo.visible = this.configuracao.chkAcoesIntervalo.selected;
			
			this.configuracao.boxAcoesFiltros1.includeInLayout = !this.configuracao.chkAcoesIntervalo.selected;
			this.configuracao.boxAcoesFiltros1.visible = !this.configuracao.chkAcoesIntervalo.selected;
			this.configuracao.boxAcoesFiltros2.includeInLayout = !this.configuracao.chkAcoesIntervalo.selected;
			this.configuracao.boxAcoesFiltros2.visible = !this.configuracao.chkAcoesIntervalo.selected;
		}
		
		private function consultar(evt:Object=null):void {
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			if (this.configuracao.txtAcoesQtdRegistros.text != "") {
				req.dados.qtdRegistros = this.configuracao.txtAcoesQtdRegistros.text;
			}
			req.dados.tipo = this.configuracao.chkAcoesIntervalo.selected ? "3" : this.configuracao.cmbAcoesSituacao.selectedItem.codListaItem;
			if (this.configuracao.chkAcoesIntervalo.selected) {
				if (this.configuracao.txtAcoesIntervaloDe.text == "" || this.configuracao.txtAcoesIntervaloAte.text == "") {
					Alerta.show("Informe o intervalo de ids.", "Alerta", Alerta.ALERTA_INFORMACAO);
					return;
				}
			}
			req.dados.idInicial = this.configuracao.txtAcoesIntervaloDe.text;
			req.dados.idFinal = this.configuracao.txtAcoesIntervaloAte.text;
			req.dados.tabela = this.configuracao.cmbAcoesTabela.selectedItem.codListaItem == "" ? null : this.configuracao.cmbAcoesTabela.selectedItem.codListaItem;
			req.dados.numCoop = this.configuracao.txtAcoesCoop.text == "" ? null: this.configuracao.txtAcoesCoop.text;
			req.dados.dataCadastro = this.configuracao.txtAcoesDtCad.text == "" ? null : this.configuracao.txtAcoesDtCad.text;
			req.dados.dataReplicacao = this.configuracao.txtAcoesDtRep.text == "" ? null : this.configuracao.txtAcoesDtRep.text;
			req.dados.acao = this.configuracao.cmbAcoesAcao.selectedItem.codListaItem == "" ? null : this.configuracao.cmbAcoesAcao.selectedItem.codListaItem;
			this.configuracao.servico.consultarAcoes(req);
		}
		
		private function retornoConsultar(event:ResultEvent):void {
			
			this.configuracao.btAcoesCSV.enabled = false;
			this.lista.removeAll();
			var lst:ArrayCollection = event.result.dados["lst"] as ArrayCollection;
			this.lista.addAll(lst);
			this.configuracao.btAcoesCSV.enabled = (lst.length > 0);
			this.configuracao.grdAcoes.labelFunction = formataDataGrid;
			this.configuracao.grdAcoes.addEventListener(MouseEvent.DOUBLE_CLICK, exibirDetalhe);
			
			MostraCursor.removeBusyCursor();
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			switch(col.dataField) {
				case "dataHoraCadastro":
				case "dataHoraReplicacao":
					if (obj[col.dataField]) {
						return this.format.format(obj[col.dataField].data);
					}
					break;
				
				default:
					if(obj[col.dataField] != null) {
						retorno = obj[col.dataField].toString();
					}
					break;
			}
			return retorno;
		}
		
		private function exibirDetalhe(evt:MouseEvent):void {
			if (this.configuracao.grdAcoes.selectedItem != null) {
				var detalheMensagem:MonitoracaoDetalhe = new MonitoracaoDetalhe(this.configuracao.grdAcoes.selectedItem);
				var telaMensagem:Janela = new Janela();
				telaMensagem.title = "MONITORAÇÃO";
				telaMensagem.removeAllChildren();
				telaMensagem.addChild(detalheMensagem);
				telaMensagem.abrir(this.configuracao, true, true);
			}
		}
		
		private function confirmReprocessar(evt:MouseEvent):void {
			if(!registroSelecionado()) {
				Alerta.show("Selecione o registro.", "Alerta", Alerta.ALERTA_INFORMACAO);
				return;
			}
			
			telaConfirmacao = new ConfirmacaoOperacaoView();
			telaConfirmacao.addEventListener(FlexEvent.CREATION_COMPLETE, initReprocessar);
			
			janelaConfirmacao = new Janela();
			janelaConfirmacao.title = "CONFIRMAR OPERAÇÃO";
			janelaConfirmacao.addChild(telaConfirmacao);
			janelaConfirmacao.abrir(this.configuracao, true, true);
		}
		
		private function initReprocessar(fe:FlexEvent):void {
			telaConfirmacao.btOk.addEventListener(MouseEvent.CLICK, reprocessar);
		}
		
		private function reprocessar(evt:MouseEvent):void {
			if(telaConfirmacao.justificativa.length == 0) {
				Alerta.show("Justificativa deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, telaConfirmacao.justificativa);
				return;
			}
			
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			var listaReprocessar:ArrayCollection = new ArrayCollection();
			
			for(var i:uint = 0; i < this.configuracao.grdAcoes.dataProvider.length; i++) {
				if(this.configuracao.grdAcoes.dataProvider[i].selecionado) {
					listaReprocessar.addItem(this.configuracao.grdAcoes.dataProvider[i]);
				}
			}
			
			req.dados.justificativa = telaConfirmacao.justificativa.text;
			req.dados.listaReprocessar = listaReprocessar;
			
			this.servicoMonitoracao.reprocessar(req);
		}
		
		private function retornoReprocessar(event:ResultEvent):void {
			Alerta.show("Operação realizada com sucesso!", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			telaConfirmacao.fecharJanela();
			consultar();
		}
		
		private function confirmInvalidar(evt:MouseEvent):void {
			if(!registroSelecionado()) {
				Alerta.show("Selecione o registro.", "Alerta", Alerta.ALERTA_INFORMACAO);
				return;
			}
			
			telaConfirmacao = new ConfirmacaoOperacaoView();
			telaConfirmacao.addEventListener(FlexEvent.CREATION_COMPLETE, initInvalidar);
			
			janelaConfirmacao = new Janela();
			janelaConfirmacao.title = "CONFIRMAR OPERAÇÃO";
			janelaConfirmacao.addChild(telaConfirmacao);
			janelaConfirmacao.abrir(this.configuracao, true, true);
		}
		
		private function initInvalidar(fe:FlexEvent):void {
			telaConfirmacao.btOk.addEventListener(MouseEvent.CLICK, invalidar);
		}
		
		private function invalidar(evt:MouseEvent):void {
			if(telaConfirmacao.justificativa.length == 0) {
				Alerta.show("Justificativa deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, telaConfirmacao);
				return;
			}
			
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			var listaInvalidar:ArrayCollection = new ArrayCollection();
			
			for(var i:uint = 0; i < this.configuracao.grdAcoes.dataProvider.length; i++) {
				if(this.configuracao.grdAcoes.dataProvider[i].selecionado) {
					listaInvalidar.addItem(this.configuracao.grdAcoes.dataProvider[i]);
				}
			}
			
			req.dados.justificativa = telaConfirmacao.justificativa.text;
			req.dados.listaInvalidar = listaInvalidar;
			
			this.servicoMonitoracao.invalidar(req);
		}
		
		private function retornoInvalidar(event:ResultEvent):void {
			Alerta.show("Operação realizada com sucesso!", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			telaConfirmacao.fecharJanela();
			consultar();
		}
		
		private function registroSelecionado():Boolean {
			var registroSelecionado:Boolean = false;
			for(var i:uint = 0; i < this.configuracao.grdAcoes.dataProvider.length; i++) {
				if(this.configuracao.grdAcoes.dataProvider[i].selecionado) {
					registroSelecionado = true;
					break;
				}
			}
			return registroSelecionado;
		}
		
		private function abrirJSON(event:MouseEvent=null):void {
			if (this.configuracao.grdAcoes.selectedItem != null) {
				var json:String = this.configuracao.grdAcoes.selectedItem.descChaveReplicacaoSQL;
				
				telaEdicaoJSON = new JanelaEdicaoJSONView();
				telaEdicaoJSON.init(json);
				telaEdicaoJSON.addEventListener(FlexEvent.CREATION_COMPLETE, initEdicaoJSON);
				
				janelaEdicaoJSON = new Janela();
				janelaEdicaoJSON.title = "EDITAR JSON";
				janelaEdicaoJSON.addChild(telaEdicaoJSON);
				janelaEdicaoJSON.abrir(this.configuracao, false, true);
			}			
		}
		
		private function initEdicaoJSON(fe:FlexEvent):void {
			telaEdicaoJSON.btSalvarJSON.addEventListener(MouseEvent.CLICK, salvarJSON);
		}
		
		private function salvarJSON(event:MouseEvent):void {
			var req:RequisicaoReqDTO = new RequisicaoReqDTO();
			req.dados.idReplicacaoCCA = this.configuracao.grdAcoes.selectedItem.idReplicacaoCCA;
			req.dados.json = telaEdicaoJSON.txtJSON.text;
			this.configuracao.servico.salvarJSON(req);			
		}
		
		private function retornoSalvarJSON(event:ResultEvent):void {
			Alerta.show("Operação realizada com sucesso!", "SUCESSO", Alerta.ALERTA_SUCESSO);
			telaEdicaoJSON.fecharJanela();
			consultar();			
		}
		
		private function abrirModelosJSON(event:MouseEvent=null):void {
			var janela:Janela = new Janela();
			janela.title = "MODELOS JSON";
			janela.addChild(new ModeloJSONView());
			janela.abrir(this.configuracao, false, true);
		}
		
		private function iniciarReplicacao(event:MouseEvent):void	{
			this.configuracao.servico.iniciarReplicacao(new RequisicaoReqDTO());	
		}
		
		private function retornoIniciarReplicacao(event:ResultEvent):void {
			Alerta.show("Replicação executada.", "SUCESSO", Alerta.ALERTA_SUCESSO);
		}
		
	}
}