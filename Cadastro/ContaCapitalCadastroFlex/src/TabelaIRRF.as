package
{
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.formatters.CurrencyFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.cadastro.tabelairrf.CadastroTabelaIRRF;
	import br.com.sicoob.sisbr.cca.cadastro.tabelairrf.TabelaIRRFView;
	import br.com.sicoob.sisbr.cca.vo.TabelaIRRFVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.TabelaIRRFVO", TabelaIRRFVO);
	public class TabelaIRRF extends TabelaIRRFView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.TabelaIRRFServico";
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var listaVO:ArrayCollection = new ArrayCollection();
		private var numCooperativa:int=parseInt(DadosLogin.coop);
		
		public function TabelaIRRF() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			if(numCooperativa != 300) {
				Alerta.show("Somente o usuário do Sicoob Confederação tem permissão para acessar a funcionalidade.", "Atenção", Alerta.ALERTA_OK);					
				fecharJanela();
			}
			
			configurarServico();
			controlarServico();
			controlarEventos();
		}
		
		private function configurarServico():void {
			onDestinoRecuperado(this.destino);

		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			//destino.servidor = "http://localhost:8080/sisbr/";
			//destino.endPoint = "http://localhost:8080/sisbr/graniteamf/amf";
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
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.consultar.addEventListener(ResultEvent.RESULT, retornoConsultar);
			servico.excluir.addEventListener(ResultEvent.RESULT, retornoExcluir);
		}
		
		private function controlarEventos():void {
			btConsultar.addEventListener(MouseEvent.CLICK, consultar);
			btIncluir.addEventListener(MouseEvent.CLICK, incluir);
			btExcluir.addEventListener(MouseEvent.CLICK, confirmExcluir);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			this.addEventListener(KeyboardEvent.KEY_DOWN, configurarAtalho);
		}
		
		public function consultar(evt:MouseEvent):void {
			reqDTO.dados.anoBase = filtroAnoBase.text;
			this.servico.consultar(reqDTO);
		}
		
		private function retornoConsultar(evt:ResultEvent):void {
			listaVO = evt.result.dados["registros"] as ArrayCollection;
			grid.dataProvider = listaVO;
			grid.labelFunction = formataDataGrid;
			
			btExcluir.enabled = false;
			
			MostraCursor.removeBusyCursor();
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
		
		private function incluir(evt:MouseEvent):void {
			var cadastro:CadastroTabelaIRRF = new CadastroTabelaIRRF();
			cadastro.destinoPai = this.destino;
			var telaCadastro:Janela = new Janela();
			telaCadastro.title = "TABELA IRRF - JUROS AO CAPITAL";
			telaCadastro.removeAllChildren();
			telaCadastro.addChild(cadastro);
			telaCadastro.abrir(this, true, true);
		}
		
		private function confirmExcluir(evt:MouseEvent):void {
			if(grid.dataProvider != null && grid.dataProvider.length > 0) {
				
				var registroSelecionado:Boolean = false;
				for(var i:int = 0; i < grid.dataProvider.length; i++) {
					if(grid.dataProvider.getItemAt(i).selecionado) {
						registroSelecionado = true;
						break;
					}
				}
				
				if(!registroSelecionado) {
					Alerta.show("Selecione ao menos um registro para exclusão.", "ATENÇÃO", Alerta.ALERTA_OK);
					return;
				}
				
				Alerta.show("Deseja realmente excluir o(s) registros(s) da tabela de irrf?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, excluir);	
			}
		}
		
		private function excluir(evt:MouseEvent):void {
			
			if(grid.dataProvider != null && grid.dataProvider.length > 0) {
				
				listaVO = grid.dataProvider as ArrayCollection;
				
				var listaExclusao:ArrayCollection = new ArrayCollection();
				for(var x:int = 0; x < listaVO.length; x++) {
					if(listaVO.getItemAt(x).selecionado) {
						listaExclusao.addItem(listaVO.getItemAt(x));
					}
				}
				
				reqDTO.dados.listaVO = listaExclusao;
				
				this.servico.excluir(reqDTO);
			}
		}
		
		private function retornoExcluir(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show("Dados excluídos com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null, consultar);
		}
		
		private function configurarAtalho(event:KeyboardEvent):void {
			if(event.charCode == 13) {
				consultar(null);
			}
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
	}
}