package br.com.sicoob.sisbr.cca.comum.pesquisaempresa
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.sisbr.cca.comum.util.Constantes;
	
	public class ProcurarEmpresa extends ProcurarEmpresaView {				
		
		private var servico:ServicoJava = new ServicoJava();
		private var jan:Janela;
		private var _resultadoPesquisaVO:PesquisaEmpresaVO = new PesquisaEmpresaVO();		
		
		public static const CLASSE_SERVICO:String = "br.com.sicoob.sisbr.cca.comum.servicos.PesquisaEmpresaServico";
		
		public function ProcurarEmpresa() {
			super();
			
			servico.source = CLASSE_SERVICO;
			servico.addEventListener(ResultEvent.RESULT, resultProcurar);
			servico.addEventListener(FaultEvent.FAULT, resultFalhaProcurar);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);			
		}
		
		private function init(evt:FlexEvent):void {
			PortalModel.portal.obterDefinicoesDestino(Constantes.SERVICOSJAVACCAREN, configurarServico);
			
			btProcurar.addEventListener(MouseEvent.CLICK, selecionar);
			
			txtNumCNPJ.addEventListener(KeyboardEvent.KEY_DOWN, txtNumKeyDown);
			txtNumCNPJ.addEventListener(FocusEvent.KEY_FOCUS_CHANGE,txtNumOnFocusOut);
			txtNumCNPJ.addEventListener(FocusEvent.MOUSE_FOCUS_CHANGE,txtNumOnFocusOut);
			
		}
		
		private function configurarServico(destinoVO:DestinoVO):void {
			servico.configurarDestino(destinoVO);
		}
		
		private function txtNumKeyDown(evt:KeyboardEvent):void {
			if(evt.keyCode == 13) {
				procurar();
			}
		}		
		
		private function txtNumOnFocusOut(evt:FocusEvent):void {
			var pattern:RegExp = /[^0-9]/gi;
			var cnpjSemMascara:String = txtNumCNPJ.text.replace(pattern, "");
			if(resultadoPesquisaVO.numCGC_CPF != cnpjSemMascara) {
				procurar();
			}
		}
		private function validarProcurar():Boolean {
			if(StringUtils.trim(txtNumCNPJ.text) == "") {
				return false;
			}
			return true;
		}
		
		/**
		 * Valida se a empresa foi selecionada 
		 */
		public function isEmpresaSelecionada():Boolean {
			if(txtNumCNPJ.text != "") {
				return true;
			}
			return false;
		}
		
		private function selecionar(evt:MouseEvent):void {
			var sel:SelecionarEmpresa = new SelecionarEmpresa();
			
			jan = new Janela();
			jan.addEventListener(KeyboardEvent.KEY_DOWN, janelaOnKeyDown);
			jan.addEventListener(Janela.FECHAR_JANELA, janelaOnClose);
			jan.title = "SELEÇÃO DE EMPRESA";
			jan.icone = "br/com/bancoob/imagens/icos/srch_16.png";
			jan.addChild(sel as DisplayObject);
			sel.procurarEmpresa = this;

			jan.height = 489;
			jan.abrir(DisplayObject(Application.application), true, true);
			jan.setFocus();
		}
		
		private function janelaOnKeyDown(evt:KeyboardEvent):void {
			switch(evt.keyCode) {
				case Keyboard.ESCAPE:
					evt.currentTarget.fecharJanela();					
				break;
			}
		}
		
		private function janelaOnClose(event:Event):void {
			txtNumCNPJ.setFocus();
		}
		
		public function procurar(evt:Event=null):void {
			if (!validarProcurar()) {
				limparRegistro();
				limparCampos();
				return;
			}
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var pesquisaVO:PesquisaEmpresaVO = new PesquisaEmpresaVO();
			
			pesquisaVO.numCGC_CPF = txtNumCNPJ.text;
			
			dto.dados.pesquisaEmpresaVO = pesquisaVO;
			
	 		servico.pesquisar(dto);
			
	 		MostraCursor.setBusyCursor("Procurando Empresa...", this);
		}
		
		private function resultProcurar(evt:ResultEvent):void {			
			if(evt.result.dados["lstDadosRetorno"] && evt.result.dados["lstDadosRetorno"].length > 0) {			
				txtNome.text = evt.result.dados["lstDadosRetorno"][0].descNomePessoa;		
				txtNumCNPJ.text = FormatUtil.formataCPFCNPJ(evt.result.dados["lstDadosRetorno"][0].numCGC_CPF);
				preecherRegistro(evt.result.dados["lstDadosRetorno"][0]);
			} else {
				limparRegistro();
				limparCampos();
			}
			MostraCursor.removeBusyCursor();
		}

		public function limparRegistro():void {
			resultadoPesquisaVO = new PesquisaEmpresaVO();
		}
		
		public function limparCampos():void {
			txtNome.text = "";
			txtNumCNPJ.text = "";
			this.dispatchEvent(new SelecionarEmpresaEvent(SelecionarEmpresaEvent.REGISTRO_NAO_ENCONTRADO));
		}
		
		public function exibirTxtNome(valor:Boolean):void {
			txtNome.visible = valor;
		}
		
		public function preecherRegistro(registro:Object):void {
			resultadoPesquisaVO = registro as PesquisaEmpresaVO;
			this.dispatchEvent(new SelecionarEmpresaEvent(SelecionarEmpresaEvent.ITEM_SELECIONADO));
			return;
		}
		
		private function resultFalhaProcurar(evt:FaultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show("Erro ao procurar empresa.", "Atenção", Alerta.ALERTA_ERRO);
		}				
			
		public function get resultadoPesquisaVO():PesquisaEmpresaVO {
			return _resultadoPesquisaVO;
		}

		public function set resultadoPesquisaVO(value:PesquisaEmpresaVO):void {
			_resultadoPesquisaVO = value;
		}
	}
}