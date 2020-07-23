package
{
	import flash.display.DisplayObject;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.ui.Keyboard;
	
	import mx.containers.HBox;
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.titulo.SubtituloView;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.cadastro.condestatutaria.CondEstatutariaView;
	import br.com.sicoob.sisbr.cca.vo.CondicaoEstatutariaVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CondicaoEstatutariaVO", CondicaoEstatutariaVO);
	public class CondEstatutaria extends CondEstatutariaView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.CondEstatutariaServico";
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		public function CondEstatutaria() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			controlarServico();
			this.addEventListener(KeyboardEvent.KEY_DOWN, onKeyDown);
		}
		
		private function onKeyDown(evt:KeyboardEvent):void {
			if(evt.keyCode == Keyboard.TAB) {
				evt.preventDefault();
			}
		}
		
		private function configurarServico():void {
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
			
			var lstRegistros:Object = event.result.dados["registros"];
			
			for(var o:Object in lstRegistros) {
				
				var hboxTitulo:HBox = new HBox();
				var sv:SubtituloView  = new SubtituloView();
				sv.width = 750;
				sv.texto = o.toString();
				hboxTitulo.addChild(sv as DisplayObject);
				
				var hboxGrid:HBox = new HBox();
				var dg:DataGrid = new DataGrid();
				dg.enabled = false;
				dg.selectable = false;
				dg.verticalScrollPolicy = "off";
				dg.focusEnabled = false;
				dg.mouseEnabled = false;
				dg.height = (lstRegistros[o].length * 22) + dg.headerHeight; 
				dg.width = 750;
				var colunas:Array = new Array();
				
				var colunaCondicao:DataGridColumn = new DataGridColumn();
				colunaCondicao.headerText = "Condição";
				colunaCondicao.dataField = "condicao";
				colunaCondicao.width = 400;
				colunas.push(colunaCondicao);
				
				var colunaValor:DataGridColumn = new DataGridColumn();
				colunaValor.headerText = "Valor";
				colunaValor.dataField = "valorConfiguracao";
				colunaValor.width = 100;
				colunas.push(colunaValor);
				
				dg.columns = colunas;
				dg.dataProvider = lstRegistros[o];
				hboxGrid.addChild(dg as DisplayObject);
				
				switch(o.toString()) {
					case "COTA": {
						lstCota.addChild(hboxTitulo as DisplayObject);
						lstCota.addChild(hboxGrid as DisplayObject);	
						break;
					}
						
					case "CADASTRO": {
						lstCadastro.addChild(hboxTitulo as DisplayObject);
						lstCadastro.addChild(hboxGrid as DisplayObject);	
						break;
					}
						
					case "SUBSCRIÇÃO": {
						lstSubscricao.addChild(hboxTitulo as DisplayObject);
						lstSubscricao.addChild(hboxGrid as DisplayObject);
						break;
					}
						
					case "INTEGRALIZAÇÃO": {
						lstIntegralizacao.addChild(hboxTitulo as DisplayObject);
						lstIntegralizacao.addChild(hboxGrid as DisplayObject);
						break;
					}
						
					case "DEVOLUÇÃO": {
						lstDevolucao.addChild(hboxTitulo as DisplayObject);
						lstDevolucao.addChild(hboxGrid as DisplayObject);
						break;
					}
						
					case "INTEGRALIZAÇÃO": {
						lstIntegralizacao.addChild(hboxTitulo as DisplayObject);
						lstIntegralizacao.addChild(hboxGrid as DisplayObject);
						break;
					}
						
					case "READMISSÃO": {
						lstReadmissao.addChild(hboxTitulo as DisplayObject);
						lstReadmissao.addChild(hboxGrid as DisplayObject);
						break;
					}
						
					default: {
						lstRelatorio.addChild(hboxTitulo as DisplayObject);
						lstRelatorio.addChild(hboxGrid as DisplayObject);	
						break;
					}
				}
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();			
		}
	}
}