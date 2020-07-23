package br.com.sicoob.sisbr.cca.cadastro.tabelairrf
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.events.FlexEvent;
	import mx.formatters.CurrencyFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.vo.TabelaIRRFVO;

	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.TabelaIRRFVO", TabelaIRRFVO);
	public class CadastroTabelaIRRF extends CadastroTabelaIRRFView {
				
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();		
		private var listaVO:ArrayCollection = new ArrayCollection();
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.TabelaIRRFServico";		
		public var destinoPai:DestinoVO;
		
		public function CadastroTabelaIRRF() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);						
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			controlarServico();
			controlarEventos();
		}
		
		private function configurarServico():void {
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
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			grid.dataProvider = listaVO;
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.consultar.addEventListener(ResultEvent.RESULT, retornoConsultar);
			servico.consultarAnoAnterior.addEventListener(ResultEvent.RESULT, retornoConsultarAnoAnterior);
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);
		}
		
		private function controlarEventos():void {
			ckRepetirAnoAnterior.addEventListener(MouseEvent.CLICK, consultarAnoAnterior);
			btOk.addEventListener(MouseEvent.CLICK, confirmIncluir);
			btAddItem.addEventListener(MouseEvent.CLICK, adicionarItem);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		private function incluir(evt:MouseEvent):void {
			
			if(grid.dataProvider != null && grid.dataProvider.length > 0) {
				
				listaVO = grid.dataProvider as ArrayCollection;
				
				reqDTO.dados.listaVO = listaVO;
				
				this.servico.incluir(reqDTO);
			}
				
		}
		
		private function confirmIncluir(evt:MouseEvent):void {
			if(validaAnoBase() && validar()) {
				reqDTO.dados.anoBase = grid.dataProvider.getItemAt(0).anoBase;
				this.servico.consultar(reqDTO);
			}
		}
		
		private function retornoConsultar(evt:ResultEvent):void {
			
			var tabelaIRRF:ArrayCollection = evt.result.dados["registros"] as ArrayCollection;
			
			if(tabelaIRRF != null && tabelaIRRF.length > 0) {
				
				Alerta.show("Consta Tabela IRRF para o ano em questão, deseja substituir?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, incluir);
				
			} else {
				
				incluir(null);
			}
		}
				
		private function retornoIncluir(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null, fechar);
		}
		
		private function consultarAnoAnterior(evt:MouseEvent):void {
			if(ano.length != 4 || ano.valor > 2200 || ano.valor < 2000) {
				Alerta.show("Verifique se o Ano Base foi preenchido corretamente.", "ATENÇÃO", Alerta.ALERTA_OK, ano);
				return;
			}
				
			var anoBase:int = ano.valor;
			
			if(ckRepetirAnoAnterior.selected) {
				anoBase = anoBase -1;
				
				reqDTO.dados.anoBase = anoBase;
				this.servico.consultarAnoAnterior(reqDTO);
				
			} else {
				
				grid.dataProvider = null;
			}
		}
		
		private function retornoConsultarAnoAnterior(evt:ResultEvent):void {
			listaVO = evt.result.dados["registros"] as ArrayCollection;
			grid.dataProvider = listaVO;
			grid.labelFunction = formataDataGrid;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function validar():Boolean {
			if(grid.dataProvider == null || grid.dataProvider.length == 0) {
				Alerta.show("Verifique se a Tabela IRRF foi preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, ano);
				return false;
			}
			
			return true;
		}
		
		private function adicionarItem(evt:MouseEvent):void {
			if(validaAnoBase() && validarIncluirItem()) {
				var item:TabelaIRRFVO = new TabelaIRRFVO();
				item.anoBase = ano.valor;
				item.percAliquota = aliquota.valor;
				item.valorBaseInicial = valorInicial.valor;
				item.valorBaseFinal = valorFinal.valor;
				item.valorDeducao = deducao.valor;
				grid.dataProvider.addItem(item);
				grid.labelFunction = formataDataGrid;
				
				var srt:Sort = new Sort();
				srt.fields = [new SortField("percAliquota", true, false, true)];
				grid.dataProvider.sort = srt;
				grid.dataProvider.refresh();
				
				//ano.text = ""; 
				aliquota.text = "";
				valorInicial.text = "";
				valorFinal.text = "";
				deducao.text = "";
			}
		}
		
		private function validarIncluirItem():Boolean {
			
			if(aliquota.text == "" || aliquota.valor > 50) {
				Alerta.show("O Valor da Alíquota deve ser maior ou igual a zero (0%) e menor ou igual a cinquenta (50%).", "ATENÇÃO", Alerta.ALERTA_OK, deducao);
				return false;
			}
			
			if(valorInicial.text == "" || (valorInicial.valor > valorFinal.valor && valorFinal.text != "")) {
				Alerta.show("O valor inicial deve ser maior ou igual a zero(0) e menor que o valor final", "ATENÇÃO", Alerta.ALERTA_OK, valorInicial);
				return false;
			}
			
			if(valorFinal.valor == 0) {
				Alerta.show("O valor final deve ser maior que zero (0).", "ATENÇÃO", Alerta.ALERTA_OK, valorFinal);
				return false;
			}
			
			if(deducao.text == "" || (deducao.valor < 0 || (deducao.valor != 0 && deducao.valor >= valorInicial.valor))) {
				Alerta.show("O valor de dedução deve ser maior ou igual a zero (0) ou menor que o valor inicial. ", "ATENÇÃO", Alerta.ALERTA_OK, deducao);
				return false;
			}
			
			if(grid.dataProvider != null && grid.dataProvider.length > 0) {
				var item:TabelaIRRFVO;
				for(var i:int = 0; i < grid.dataProvider.length; i++) {
					item = grid.dataProvider.getItemAt(i);
					if(item.anoBase != ano.valor) {
						Alerta.show("O Ano Base deve ser o mesmo da tabela de IRRF.", "ATENÇÃO", Alerta.ALERTA_OK, ano);
						return false;
					}
					if(item.percAliquota == aliquota.valor) {
						Alerta.show("Alíquota informada já consta na tabela de IRRF.", "ATENÇÃO", Alerta.ALERTA_OK, aliquota);
						return false;
					}
				}
			}
			
			return true;
		}
		
		private function validaAnoBase(): Boolean {
			if(ano.length != 4 || ano.valor > 2200 || ano.valor < new Date().fullYear) {
				Alerta.show("Verifique se o Ano Base foi preenchido corretamente.", "ATENÇÃO", Alerta.ALERTA_OK, ano);
				return false;
			}
			
			return true;
		}
		
		public function excluirItem(vo:TabelaIRRFVO):void {
			if(grid.dataProvider != null && grid.dataProvider.length > 0) {
				var item:TabelaIRRFVO;
				for(var i:int = 0; i < grid.dataProvider.length; i++) {
					item = grid.dataProvider.getItemAt(i);
					if(item.percAliquota == vo.percAliquota && item.anoBase == vo.anoBase) {
						grid.dataProvider.removeItemAt(i);	
					}
				}
			}
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			var cf:CurrencyFormatter = new CurrencyFormatter();
			cf.precision="2";
			cf.rounding="none";
			cf.decimalSeparatorTo=".";
			cf.thousandsSeparatorTo="";
			cf.useThousandsSeparator="true";
			cf.useNegativeSign="true";
			cf.currencySymbol="";
			
			switch(col.dataField) {
				
				case "anoBase":
					if (obj[col.dataField]) {
						retorno = obj[col.dataField].toString();
					} else {
						retorno = "";						
					}
					break;
				
				case "percAliquota":
					if (obj[col.dataField]) {
						retorno = cf.format(obj[col.dataField]);
					} else {
						retorno = "0.00";						
					}
					break;
				
				case "valorBaseInicial":
					if (obj[col.dataField]) {
						retorno = cf.format(obj[col.dataField]);
					} else {
						retorno = "0.00";						
					}
					break;
				
				case "valorBaseFinal":
					if (obj[col.dataField]) {
						retorno = cf.format(obj[col.dataField]);
					} else {
						retorno = "0.00";						
					}
					break;
				
				case "valorDeducao":
					if (obj[col.dataField]) {
						retorno = cf.format(obj[col.dataField]);
					} else {
						retorno = "0.00";						
					}
					break;
			}                                       
			return retorno;
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			(this.parentDocument as TabelaIRRF).consultar(evt);
			super.fecharJanela();
		}
	}
}