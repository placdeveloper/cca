package
{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.DateTimeEntity;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.cadastro.aprovacao.ConsultaAprovacaoView;
	import br.com.sicoob.sisbr.cca.util.DataUtil;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	public class ConsultaAprovacao extends ConsultaAprovacaoView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.ConsultaAprovacaoServico";
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		private var vo:CadastroContaCapitalRenVO;
		private var voSelecionado:CadastroContaCapitalRenVO;
		
		private var listaVO:ArrayCollection = new ArrayCollection();
		
		public function ConsultaAprovacao() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			controlarServico();
			controlarEventos();
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(evt:ResultEvent):void {
			listaVO = evt.result.dados["registros"] as ArrayCollection;
			grid.dataProvider = listaVO;
			grid.labelFunction = formataDataGrid;
			
			MostraCursor.removeBusyCursor();
			
			if(listaVO == null) {
				Alerta.show("Não existem cadastros disponíveis para aprovação.", "ATENÇÃO", Alerta.ALERTA_OK, btFechar);
				btOK.enabled = false;
			}
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOK.addEventListener(MouseEvent.CLICK, aprovar);
			
			grid.addEventListener(KeyboardEvent.KEY_DOWN, definirSelecionadoKeyDown);			
			grid.addEventListener(MouseEvent.CLICK, definirSelecionadoMouseEvent);
			grid.addEventListener(MouseEvent.DOUBLE_CLICK, definirSelecionadoMouseEvent);
		}
		
		private function definirSelecionadoKeyDown(evt:KeyboardEvent):void {
			if(evt.keyCode == 13 && grid.selectedItem != null) {
				voSelecionado = grid.selectedItem as CadastroContaCapitalRenVO;
			}
		}
		
		private function definirSelecionadoMouseEvent(evt:MouseEvent):void {
			if(grid.selectedItem != null) { 
				voSelecionado = grid.selectedItem as CadastroContaCapitalRenVO;
				
				if(evt.type == MouseEvent.DOUBLE_CLICK) {
					aprovar(evt);
				}
			}
		}
		
		private function aprovar(evt:MouseEvent):void {
			if(voSelecionado == null) {
				return;
			}
			
			var cadastro:AprovacaoCadastroContaCapital = new AprovacaoCadastroContaCapital(voSelecionado);
			cadastro.destino = this.destino;
			var telaCadastro:Janela = new Janela();
			telaCadastro.title = "APROVAR CADASTRO CONTA CAPITAL";
			telaCadastro.removeAllChildren();
			
			telaCadastro.x = (this.stage.stageWidth / 100) * 20;
			telaCadastro.y = (this.stage.stageWidth / 100) * 5;
			
			telaCadastro.addEventListener("atualizarRegistrosConsulta", atualizarRegistrosConsulta);
			
			telaCadastro.addChild(cadastro);
			telaCadastro.abrir(this, true, false);
		}
		
		private function atualizarRegistrosConsulta(evt:Event):void {
			obterDefinicoes();
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			switch(col.dataField) {
				
				case "cpfCnpj":
					if (obj[col.dataField]) {
						retorno = FormatUtil.formataCPFCNPJ(obj[col.dataField]);
					} else {
						retorno = "";						
					}
				break;
				
				case "dataHoraAtualizacao":
					if (obj[col.dataField]) {
						retorno = DataUtil.dateToString((obj[col.dataField] as DateTimeEntity).data);
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
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();			
		}
	}
}