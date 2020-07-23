package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.formatters.CurrencyFormatter;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.DateTimeEntity;
	import br.com.bancoob.dto.RequisicaoReqDTO;

	public class ConfiguracaoAbaConciliacao {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var cf:CurrencyFormatter;
		private var df:DateFormatter = new DateFormatter();
		
		private var cooperativas:String;
		private var script:String;
		
		private var lstCooperativas:ArrayCollection;
		private var lstLancamentosNaoAtualizados:ArrayCollection;
		private var lstParcelaSemSubscricao:ArrayCollection;
		private var lstSubscricaoSemParcela:ArrayCollection;
		private var lstDuplicidade:ArrayCollection;
		private var lstErros:ArrayCollection;
		private var lstCapaLote:ArrayCollection;
		private var lstSaldosNegativos:ArrayCollection;
		private var lstParcDevolSemSaldo:ArrayCollection;
		private var lstDevolSemParcParaAtivos:ArrayCollection;
		
		private var cooperativasConc:Array = [];
		private var posicao:int = 0;
		
		public function ConfiguracaoAbaConciliacao(configuracao:ConfiguracaoReplicacaoCapital)	{
			this.configuracao = configuracao;
			
			this.configuracao.servico.consultarCooperativasConciliacao.addEventListener(ResultEvent.RESULT, retornoConsultarCooperativasConciliacao);
			this.configuracao.servico.executarConciliacao.addEventListener(ResultEvent.RESULT, retornoExecutarConciliacao);
			this.configuracao.servico.recuperarScriptConciliacao.addEventListener(ResultEvent.RESULT, retornoRecuperarScript);
			this.configuracao.servico.recuperarScriptConciliacaoCapaLote.addEventListener(ResultEvent.RESULT, retornoRecuperarScriptCapaLote);
			
			this.configuracao.btExecConciliacao.addEventListener(MouseEvent.CLICK, executarConciliacao);
			this.configuracao.txtCooperativaConciliacao.addEventListener(FlexEvent.ENTER, executarConciliacao);
			
			this.configuracao.btGerarScript1.addEventListener(MouseEvent.CLICK, gerarScript);
			this.configuracao.btGerarScript2.addEventListener(MouseEvent.CLICK, gerarScript);
			this.configuracao.btGerarScriptCapaLote.addEventListener(MouseEvent.CLICK, gerarScriptCapaLote);
			this.configuracao.btConcCSV1.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btConcCSV2.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btConcCSV3.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btConcCSV4.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btConcCSV5.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btConcCSV6.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btConcCSV7.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btConcCSV8.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btMonitCSV1.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btMonitCSV2.addEventListener(MouseEvent.CLICK, gerarCSV);
			this.configuracao.btAcoesCSV.addEventListener(MouseEvent.CLICK, gerarCSV);
			// aba piloto
			this.configuracao.btCSVPiloto.addEventListener(MouseEvent.CLICK, gerarCSV);
			// aba Bat. SQL x DB2
			this.configuracao.btCSVSaldo.addEventListener(MouseEvent.CLICK, gerarCSV);
			
			initDateFormatter();
			initCurrencyFormatter();
		}
		
		private function initDateFormatter():void {
			df = new DateFormatter();
			df.formatString = "DD/MM/YYYY";
		}
		
		private function initCurrencyFormatter():void {
			cf = new CurrencyFormatter();
			cf.precision="2";
			cf.rounding="none";
			cf.decimalSeparatorTo=",";
			cf.thousandsSeparatorTo=".";
			cf.useThousandsSeparator="true";
			cf.useNegativeSign="true";
			cf.currencySymbol="R$ ";
		}
		
		private function executarConciliacao(evt:Object):void {
			atualizarProgresso(0,0);
			reiniciarEstados();
			
			if (this.configuracao.txtCooperativaConciliacao.text == "") {
				this.configuracao.servico.consultarCooperativasConciliacao(new RequisicaoReqDTO());							
			} else {
				atualizarProgresso(0,1);
				this.cooperativasConc.push(new Number(this.configuracao.txtCooperativaConciliacao.text));
				enviarExecucao();			
			}
			
		}
		
		private function reiniciarEstados():void {
			this.configuracao.btGerarScript1.enabled = false;
			this.configuracao.btGerarScript2.enabled = false;
			this.configuracao.btGerarScriptCapaLote.enabled = false;
			this.configuracao.btConcCSV1.enabled = false;
			this.configuracao.btConcCSV2.enabled = false;
			this.configuracao.btConcCSV3.enabled = false;
			this.configuracao.btConcCSV4.enabled = false;
			this.configuracao.btConcCSV5.enabled = false;
			this.configuracao.btConcCSV6.enabled = false;
			this.cooperativasConc = [];
			this.posicao = 0;
			this.lstCooperativas = new ArrayCollection();
			this.lstLancamentosNaoAtualizados = new ArrayCollection();
			this.lstParcelaSemSubscricao = new ArrayCollection();
			this.lstSubscricaoSemParcela = new ArrayCollection();
			this.lstErros = new ArrayCollection();
			this.lstDuplicidade = new ArrayCollection();
			this.lstCapaLote = new ArrayCollection();
			this.lstSaldosNegativos = new ArrayCollection();
			this.lstParcDevolSemSaldo = new ArrayCollection();
			this.lstDevolSemParcParaAtivos = new ArrayCollection();
		}
		
		private function enviarExecucao():void {
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cooperativa = this.cooperativasConc[this.posicao];
			this.configuracao.servico.executarConciliacao(dto);	
		}
		
		private function retornoConsultarCooperativasConciliacao(event:ResultEvent):void {
			var lstCooperativas:ArrayCollection = event.result.dados.cooperativas;
			atualizarProgresso(0,lstCooperativas.length);
			for(var i:int=0; i < lstCooperativas.length; i++){
				this.cooperativasConc.push(lstCooperativas[i]);
			}
			enviarExecucao();
		}
		
		private function retornoExecutarConciliacao(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			var map:Object = event.result.dados.conciliacao;
			this.lstLancamentosNaoAtualizados.addAll(map.lancamentosNaoAtualizados);
			this.lstParcelaSemSubscricao.addAll(map.parcelaSemSubscricao);
			this.lstSubscricaoSemParcela.addAll(map.subscricaoSemParcela);
			this.lstErros.addAll(map.erros);
			this.lstDuplicidade.addAll(map.duplicidade);
			this.lstCapaLote.addAll(map.capaLote);
			this.lstSaldosNegativos.addAll(map.saldosNegativos);
			this.lstParcDevolSemSaldo.addAll(map.parcDevolSemSaldo);
			this.lstDevolSemParcParaAtivos.addAll(map.devolSemParcParaAtivos);

			if (this.lstParcelaSemSubscricao.length > 0 || this.lstSubscricaoSemParcela.length > 0) {
				this.lstCooperativas.addItem(this.cooperativasConc[this.posicao]);
			}
			
			atualizarProgresso(this.posicao+1, this.cooperativasConc.length);
			
			this.posicao++;
			if (this.posicao < this.cooperativasConc.length) {
				enviarExecucao();
			} else {
				atualizarTabelas();
				atualizarScript();
			}
			
		}
		
		private function atualizarTabelas():void {
			this.configuracao.grdConcLanc.dataProvider = this.lstLancamentosNaoAtualizados;
			this.configuracao.grdConcLanc.labelFunction = formataDataGrid;
			this.configuracao.grdConcLanc.dataProvider.refresh();
			if (this.lstLancamentosNaoAtualizados.length > 0) {
				this.configuracao.btConcCSV1.enabled = true;
			}
			
			this.configuracao.grdConcParcSemSubs.dataProvider = this.lstParcelaSemSubscricao;
			this.configuracao.grdConcParcSemSubs.labelFunction = formataDataGrid;
			this.configuracao.grdConcParcSemSubs.dataProvider.refresh();
			if (this.lstParcelaSemSubscricao.length > 0) {
				this.configuracao.btConcCSV2.enabled = true;
			}
			
			this.configuracao.grdConcSubsSemParc.dataProvider = this.lstSubscricaoSemParcela;
			this.configuracao.grdConcSubsSemParc.labelFunction = formataDataGrid;
			this.configuracao.grdConcSubsSemParc.dataProvider.refresh();
			if (this.lstSubscricaoSemParcela.length > 0) {
				this.configuracao.btConcCSV3.enabled = true;
			}
			
			this.configuracao.grdConcDuplicidade.dataProvider = this.lstDuplicidade;
			this.configuracao.grdConcDuplicidade.labelFunction = formataDataGrid;
			this.configuracao.grdConcDuplicidade.dataProvider.refresh();
			if (this.lstDuplicidade.length > 0) {
				this.configuracao.btConcCSV4.enabled = true;
			}
			
			this.configuracao.grdConcCapaLote.dataProvider = this.lstCapaLote;
			this.configuracao.grdConcCapaLote.labelFunction = formataDataGrid;
			this.configuracao.grdConcCapaLote.dataProvider.refresh();
			if (this.lstCapaLote.length > 0) {
				this.configuracao.btConcCSV5.enabled = true;
				this.configuracao.btGerarScriptCapaLote.enabled = true;
			}
			
			this.configuracao.grdConcSaldosNegativos.dataProvider = this.lstSaldosNegativos;
			this.configuracao.grdConcSaldosNegativos.labelFunction = formataDataGrid;
			this.configuracao.grdConcSaldosNegativos.dataProvider.refresh();
			if (this.lstSaldosNegativos.length > 0) {
				this.configuracao.btConcCSV6.enabled = true;
			}
			
			this.configuracao.grdConcParcDevSemSaldo.dataProvider = this.lstParcDevolSemSaldo;
			this.configuracao.grdConcParcDevSemSaldo.labelFunction = formataDataGrid;
			this.configuracao.grdConcParcDevSemSaldo.dataProvider.refresh();
			if (this.lstParcDevolSemSaldo.length > 0) {
				this.configuracao.btConcCSV7.enabled = true;
			}
			
			this.configuracao.grdConcDevSemParcAtivos.dataProvider = this.lstDevolSemParcParaAtivos;
			this.configuracao.grdConcDevSemParcAtivos.labelFunction = formataDataGrid;
			this.configuracao.grdConcDevSemParcAtivos.dataProvider.refresh();
			if (this.lstDevolSemParcParaAtivos.length > 0) {
				this.configuracao.btConcCSV8.enabled = true;
			}
		}
		
		private function atualizarScript():void {
			if (this.lstParcelaSemSubscricao.length > 0 || this.lstSubscricaoSemParcela.length > 0) {
				this.configuracao.btGerarScript1.enabled = true;
				this.configuracao.btGerarScript2.enabled = true;
				
				this.cooperativas = '';
				this.script = '';
				for(var i:int=0; i < this.lstCooperativas.length; i++){
					this.cooperativas += this.lstCooperativas[i];
					if (i < (this.lstCooperativas.length-1)) {
						this.cooperativas += ', ';
					}
				}
				for(var j:int=0; j < this.lstParcelaSemSubscricao.length; j++){
					this.script += this.lstParcelaSemSubscricao[j].script + '\n';
				}
				for(var k:int=0; k < this.lstSubscricaoSemParcela.length; k++){
					this.script += this.lstSubscricaoSemParcela[k].script + '\n';
				}
				for(var l:int=0; l < this.lstErros.length; l++){
					this.script += this.lstErros[l].script + '\n';
				}
			}
		}
		
		private function atualizarProgresso(atual:int, total:int):void {
			this.configuracao.progressoConciliacao.label = 'Progresso: '+atual+'/'+total;
			this.configuracao.progressoConciliacao.setProgress(atual, total);
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			if (col.dataField != null) {
				switch(col.dataField) {
					case "dataLote":
					case "dataHoraInclusao":
						if (obj[col.dataField]) {
							retorno = df.format((obj[col.dataField] as DateTimeEntity).data);
						} else {
							retorno = "";						
						}
						break;
					case "valorLanc":
					case "valorSaldoAtuSubsc":
					case "valorLancSubscDoDia":
					case "valorSaldoAtuInteg":
					case "valorParcEmAberto":
					case "valorLancIntegDoDia":
					case "valorDiferenca":
					case "valorSaldoAtuDevol":
					case "valorSaldoBloqInt":
					case "valorCapaLote":
					case "valorLancamentos":
						if (obj[col.dataField] != null) {
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
			}
			return retorno;
		}
		
		private function gerarScript(event:MouseEvent=null):void {
			this.configuracao.servico.recuperarScriptConciliacao(new RequisicaoReqDTO());
		}
		
		private function retornoRecuperarScript(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			var script:String = event.result.dados.script.replace('{script}',script).replace('{cooperativas}',cooperativas);
			var janelaCopia:JanelaCopiaConfiguracao = new JanelaCopiaConfiguracao(script);
			var telaCopia:Janela = new Janela();
			telaCopia.title = "SCRIPT";
			telaCopia.removeAllChildren();
			telaCopia.addChild(janelaCopia);
			telaCopia.abrir(this.configuracao, true, true);
		}
		
		private function gerarScriptCapaLote(event:MouseEvent=null):void {
			this.configuracao.servico.recuperarScriptConciliacaoCapaLote(new RequisicaoReqDTO());
		}
		
		private function retornoRecuperarScriptCapaLote(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			var script:String = event.result.dados.script;
			var janelaCopia:JanelaCopiaConfiguracao = new JanelaCopiaConfiguracao(script);
			var telaCopia:Janela = new Janela();
			telaCopia.title = "SCRIPT";
			telaCopia.removeAllChildren();
			telaCopia.addChild(janelaCopia);
			telaCopia.abrir(this.configuracao, true, true);
		}
		
		public function gerarCSV(event:MouseEvent=null):void {
			var csv:String = montarCSV(event.target.name.split('-')[1]);
			var janelaCopia:JanelaCopiaConfiguracao = new JanelaCopiaConfiguracao(csv);
			var telaCopia:Janela = new Janela();
			telaCopia.title = "CSV";
			telaCopia.removeAllChildren();
			telaCopia.addChild(janelaCopia);
			telaCopia.abrir(this.configuracao, true, true);
		}
		
		public function montarCSV(nomeGrid:String):String {
			var i:int = 0;
			var csv:String = '';
			var grid:Grid = this.configuracao[nomeGrid];
			var colunas:Array = grid.columns;
			for (i=0; i<colunas.length; i++) {
				if (colunas[i].dataField == "selecionado") {
					continue;
				}
				csv += colunas[i].headerText;
				if (i < (colunas.length-1)) {
					csv += ';';
				}
			}
			csv += '\n';
			for (i=0; i<grid.dataProvider.length; i++) {
				for (var j:int=0; j<colunas.length; j++) {
					if (colunas[j].dataField == "selecionado") {
						continue;
					}
					csv += formataDataGrid(grid.dataProvider[i], colunas[j]);
					if (j < (colunas.length-1)) {
						csv += ';';
					}
				}
				csv += '\n';
			}
			return csv;
		}
		
	}
}