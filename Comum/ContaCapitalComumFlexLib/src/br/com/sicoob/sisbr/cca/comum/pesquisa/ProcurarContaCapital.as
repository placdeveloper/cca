package br.com.sicoob.sisbr.cca.comum.pesquisa
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
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.sisbr.cca.comum.util.Constantes;
	
	public class ProcurarContaCapital extends ProcurarContaCapitalView {				
		
		private var servico:ServicoJava = new ServicoJava();
			
		private var jan:Janela;
		
		private var _idInstituicao:Number;
		
		private var _idSituacaoContaCapital:Number = 1;
		
		private var _idSituacaoCadastro:Number = 2;
		
		private var _resultadoPesquisaVO:PesquisaContaCapitalVO = new PesquisaContaCapitalVO();		
		
		private var _exibeCpfCnpj:Boolean = false;
		
		private var _validarObrigatorio:Boolean = false;

		public static const CLASSE_SERVICO:String = "br.com.sicoob.sisbr.cca.comum.servicos.PesquisaContaCapitalServico";
		
		public function ProcurarContaCapital() {
			super();
			
			servico.source = CLASSE_SERVICO;
			servico.showBusyCursor = false;
			servico.bloquearOperacao = false;
			servico.addEventListener(ResultEvent.RESULT, resultProcurarCCA);
			servico.addEventListener(FaultEvent.FAULT, resultFalhaProcurarCCA);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);			
		}
		
		private function init(evt:FlexEvent):void {
			PortalModel.portal.obterDefinicoesDestino(Constantes.SERVICOSJAVACCAREN, configurarServico);
			
			btProcurar.addEventListener(MouseEvent.CLICK, selecionarCCA);
			
			txtNumCCA.addEventListener(KeyboardEvent.KEY_DOWN, txtNumCCAKeyDown);
			txtNumCCA.addEventListener(FocusEvent.KEY_FOCUS_CHANGE,txtNumCCAOnFocusOut);
			txtNumCCA.addEventListener(FocusEvent.MOUSE_FOCUS_CHANGE,txtNumCCAOnFocusOut);
			
			if (exibeCpfCnpj) {
				boxCpfCnpj.visible = true;
				boxCpfCnpj.includeInLayout = true;
			}
			
			if(validarObrigatorio){
				txtNumCCA.validarObrigatorio = true;
			}
		}
		
		private function configurarServico(destinoVO:DestinoVO):void {
			servico.configurarDestino(destinoVO);
		}
		
		private function txtNumCCAKeyDown(evt:KeyboardEvent):void {
			if(evt.keyCode == 13) {
				procurarCCA();
			}
		}		
		
		private  function txtNumCCAOnFocusOut(evt:FocusEvent):void {
			if(resultadoPesquisaVO.numContaCapital != new Number(txtNumCCA.text)) {
				procurarCCA();
			}
		}
	
		private function validarProcurarCCA():Boolean {
			if(StringUtils.trim(txtNumCCA.text) == "" || txtNumCCA.text.length <= 0 || new Number(txtNumCCA.text) <= 0) {
				return false;
			}
			
			if(idInstituicao <= 0) {
				return false;
			}
			
			return true;
		}
		
		/**
		 * Valida se a conta capital foi selecionada 
		 */
		public function isContaCapitalSelecionada():Boolean {
			if(txtNumCCA.text != "" && txtNumCCA.length > 0 && _resultadoPesquisaVO.idContaCapital > 0) {
				return true;
			}
			return false;
		}
		
		private function selecionarCCA(evt:MouseEvent):void {
			var sel:SelecionarContaCapital = new SelecionarContaCapital();
			
			jan = new Janela();
			jan.addEventListener(KeyboardEvent.KEY_DOWN, janelaOnKeyDown);
			jan.addEventListener(Janela.FECHAR_JANELA, janelaOnClose);
			jan.title = "SELEÇÃO DE ASSOCIADO";
			jan.icone = "br/com/bancoob/imagens/icos/srch_16.png";
			jan.addChild(sel as DisplayObject);
			sel.procurarContaCapital = this;

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
			txtNumCCA.setFocus();
		}
		
		public function procurarCCA(evt:Event=null):void {
			if (!validarProcurarCCA()) {
				limparRegistro();
				limparCampos();
				return;
			}
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var pesquisaVO:PesquisaContaCapitalVO = new PesquisaContaCapitalVO();
			
			pesquisaVO.numContaCapital = new Number(txtNumCCA.text);
			pesquisaVO.idInstituicao = idInstituicao;
			
			if(idSituacaoCadastro > 0) {
				pesquisaVO.idSituacaoCadastro = idSituacaoCadastro;
			}
			
			if(idSituacaoContaCapital > 0) {
				pesquisaVO.idSituacaoContaCapital = idSituacaoContaCapital;
			}
			
			dto.dados.pesquisaContaCapitalVO = pesquisaVO;
			
	 		servico.pesquisar(dto);
		}
		
		private function resultProcurarCCA(evt:ResultEvent):void {			
			if(evt.result.dados["lstDadosRetorno"] && evt.result.dados["lstDadosRetorno"].length > 0) {			
				txtNome.text = evt.result.dados["lstDadosRetorno"][0].nome;		
				txtCpfCnpj.text = FormatUtil.formataCPFCNPJ(evt.result.dados["lstDadosRetorno"][0].cpfCnpj);
				preecherRegistro(evt.result.dados["lstDadosRetorno"][0]);
			} else {
				limparRegistro();
				limparCampos();
			}
		}

		public function limparRegistro():void {
			resultadoPesquisaVO = new PesquisaContaCapitalVO();
		}
		
		public function limparCampos():void {
			txtNome.text = "";
			txtNumCCA.text = "";
			txtCpfCnpj.text = "";
			this.dispatchEvent(new SelecionarContaCapitalEvent(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO));
		}
		
		public function exibirTxtNome(valor:Boolean):void {
			txtNome.visible = valor;
		}
		
		public function preecherRegistro(registro:Object):void {
			resultadoPesquisaVO = registro as PesquisaContaCapitalVO;
			this.dispatchEvent(new SelecionarContaCapitalEvent(SelecionarContaCapitalEvent.ITEM_SELECIONADO));
			return;
		}
		
		private function resultFalhaProcurarCCA(evt:FaultEvent):void {
			Alerta.show("Erro ao procurar conta capital.", "Atenção", Alerta.ALERTA_ERRO);
		}				
			
		public function configurarDestinoRecuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
		}				

		public function get idInstituicao():Number {
			return _idInstituicao;
		}

		/**
		 * Obrigatório IdInstituicao DB2
		 */
		public function set idInstituicao(value:Number):void {
			_idInstituicao = value;
		}

		public function get resultadoPesquisaVO():PesquisaContaCapitalVO {
			return _resultadoPesquisaVO;
		}

		public function set resultadoPesquisaVO(value:PesquisaContaCapitalVO):void {
			_resultadoPesquisaVO = value;
		}

		public function get idSituacaoContaCapital():Number {
			return _idSituacaoContaCapital;
		}

		/**
		 * Situações da conta capital: 
		 *  <p>1 - ATIVO (Default) </p>
		 *	<p>2 - DEMITIDO </p>
		 *	<p>3 - EXCLUIDO </p>
		 *	<p>4 - ELIMINADO </p>
		 *  <p>99 - TODOS </p>
		 */
		public function set idSituacaoContaCapital(value:Number):void {
			_idSituacaoContaCapital = value;
		}

		public function get idSituacaoCadastro():Number {
			return _idSituacaoCadastro;
		}

		/**
		 *  Situações do cadastro: 
		 *	<p>1 -	AGUARDANDO APROVAÇÃO </p>
		 *	<p>2 -	APROVADO  (Default) </p>
		 *	<p>3 -	REJEITADO </p>
		 *	<p>4 -	DEVOLVIDO </p>
		 *  <p>99 - TODOS </p>
		 */
		public function set idSituacaoCadastro(value:Number):void {
			_idSituacaoCadastro = value;
		}
		
		/**
		 * Controla a exibição do campo CPF/CNPJ no retorno da consulta
		 */
		public function set exibeCpfCnpj(value:Boolean):void {
			_exibeCpfCnpj = value;
		}
		
		public function get exibeCpfCnpj():Boolean {
			return _exibeCpfCnpj;
		}
		
		/**
		 * Controla a exibição da mensagem de campo obrigatório
		 */
		public function set validarObrigatorio(value:Boolean):void {
			_validarObrigatorio = value;
		}
		
		public function get validarObrigatorio():Boolean {
			return _validarObrigatorio;
		}
		
	}
}