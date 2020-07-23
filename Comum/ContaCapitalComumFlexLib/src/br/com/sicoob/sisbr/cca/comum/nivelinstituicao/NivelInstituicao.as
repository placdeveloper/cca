package br.com.sicoob.sisbr.cca.comum.nivelinstituicao
{

	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.util.Constantes;


	public class NivelInstituicao extends NivelInstituicaoView
	{
		private var _numCooperativa:int=parseInt(DadosLogin.coop);
		private var _dadosDefinicoes:Object = new Object();
		public var _bolVisivel:Boolean = true;
		private var servico:ServicoJava = new ServicoJava();		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.comum.servicos.NivelInstituicaoServico";
		private var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
		
		/**
		 * Adiciona instituicao central selecionada ao combo de singulares 
		 */
		public var _bolInstCentral:Boolean = false;
		
		public function NivelInstituicao()
		{
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
		}
		
		private function configurarServico():void {
			PortalModel.portal.obterDefinicoesDestino(Constantes.SERVICOSJAVACCAREN, inicializaServico);			
		}
		
		private function inicializaServico(destinoVO:DestinoVO):void {
			servico.configurarDestino(destinoVO);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;				
			obterDefinicoes();
			mostrarComponente();
		}
		
		private function obterDefinicoes():void {
			servico.addEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			servico.bloquearOperacao = true;
			servico.mensagemEspera = "Obtendo Definições";
			dto.dados.numCoop = _numCooperativa;
			dto.dados.bolInstCentral = this._bolInstCentral;
			servico.obterDefinicoes(dto); // transferir objeto para servico e alterar comportamento
			servico.listarSingularesCentral.addEventListener(ResultEvent.RESULT, retornolistarSingularesCentral);
		}
		
		private function resultObterDefinicoes(evt:ResultEvent):void {		
			servico.removeEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			MostraCursor.removeBusyCursor();
			_dadosDefinicoes = evt.result.dados;
			var tipoGrauCoopetativa:int = _dadosDefinicoes["tipoGrauCoopetativa"];
			var listaCentral:ArrayCollection = _dadosDefinicoes["listaCentral"] as ArrayCollection;			
			var listaSingular:ArrayCollection = _dadosDefinicoes["listaSingular"] as ArrayCollection;			
			
			if(tipoGrauCoopetativa == 1){
				cmbCentral.enabled = false;
				cmbSingular.enabled = true;								
				listaSingular.addItemAt("SELECIONE",0);							
			}else if(tipoGrauCoopetativa == 2){
				cmbCentral.enabled = false;
				cmbSingular.enabled = false;								
			}else if(tipoGrauCoopetativa == 3){
				cmbCentral.enabled = true;
				cmbSingular.enabled = false;				
				listaCentral.addItemAt("SELECIONE",0);							
			}			
						
			cmbCentral.dataProvider = listaCentral as ArrayCollection;				
			cmbSingular.dataProvider = listaSingular as ArrayCollection;				
			cmbCentral.addEventListener(Event.CHANGE, onChangeCmbCentral);		
		}		
		
		public function onChangeCmbCentral(evt:Event):void {
			this.dispatchEvent(new NivelInstituicaoEvent(NivelInstituicaoEvent.NIVEL_INSTITUICAO_SELECIONADO));
			if(evt.currentTarget.selectedIndex==0){
				cmbSingular.selectedIndex = -1;					
				cmbSingular.enabled = false;					
				return;
			}
			if(evt.currentTarget.selectedItem !=null){
				var dtoCentral:RequisicaoReqDTO = new RequisicaoReqDTO();				

				dtoCentral.dados.bolInstCentral = this._bolInstCentral;
				dtoCentral.dados.idInstituicao = evt.currentTarget.selectedItem.codListaItem;		
				servico.bloquearOperacao = true;
				servico.mensagemEspera = "Listando Singulares";				
				servico.listarSingularesCentral(dtoCentral); 			
			}
		}
				
		private function retornolistarSingularesCentral(evt:ResultEvent):void {
			var _dadosSingular:Object = evt.result.dados;
			cmbSingular.selectedIndex = -1;					
			cmbSingular.enabled = false;					
			
			var listaSingular:ArrayCollection = _dadosSingular["listaSingular"] as ArrayCollection;			
			listaSingular.addItemAt("SELECIONE",0);							
			cmbSingular.dataProvider = listaSingular as ArrayCollection;				
			
			cmbSingular.dropdown.dataProvider = _dadosSingular["listaSingular"] as ArrayCollection;	
			if(_dadosSingular["listaSingular"].length > 0){
				cmbSingular.selectedIndex = 0;
				cmbSingular.enabled = true;					
			}			
			cmbSingular.addEventListener(Event.CHANGE, onChangeCmbSingular);
			MostraCursor.removeBusyCursor();
		}
		
		public function onChangeCmbSingular(evt:Event):void {
			this.dispatchEvent(new NivelInstituicaoEvent(NivelInstituicaoEvent.NIVEL_INSTITUICAO_SELECIONADO));
		}
				
		private function mostrarComponente():void{
			rtlCentral.visible = _bolVisivel;
			rtlSingular.visible = _bolVisivel;
			cmbCentral.visible = _bolVisivel;
			cmbSingular.visible = _bolVisivel;						
		}
		
		public function resetInstituicao():void {
			obterDefinicoes();
		}
	}
}
