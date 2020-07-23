package
{
	import flash.display.DisplayObject;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.cadastro.participacaoindireta.CadastroInvestimentoCentralBancoobEdicao;
	import br.com.sicoob.sisbr.cca.cadastro.participacaoindireta.CadastroInvestimentoCentralBancoobView;
	import br.com.sicoob.sisbr.cca.constantes.Constantes;
	
	public class CadastroInvestimentoCentralBancoob extends CadastroInvestimentoCentralBancoobView
	{
		
		private const dtBaseNum:Number = Constantes.ANO_MES_BASE_CAD_PARTICIP_CENTRAL_BANCOOB;
		private var destinoVO:DestinoVO;
		private var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var _dadosDefinicoes:Object = new Object();
		private var servico:ServicoJava = new ServicoJava();		
		private var listaVO:ArrayCollection = new ArrayCollection();		
		private var listaCentral:ArrayCollection = new ArrayCollection();				
		private var jan:Janela;			
		public var indiceListaSelecionado:int=-1; //ITEM DA GRID SELECIONADO PARA ALGUMA AÇAO
		public var numCooperativaLogado:int=parseInt(DadosLogin.coop);		
		public var strIncluir:String;
		private var bCentral:Boolean;
		
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.ParticipacaoCentralBancoobServico";		
		
		public function CadastroInvestimentoCentralBancoob()
		{
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE,init);				
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			btFechar.addEventListener(MouseEvent.CLICK, botaoFecharPressionado);
			btnProcurar.addEventListener(MouseEvent.CLICK, btnProcurarPressionado);
			btIncluir.addEventListener(MouseEvent.CLICK,abrirJanelaInclusao);
			btAlterar.addEventListener(MouseEvent.CLICK,abrirJanelaEdicao);
			servico.obterDados.addEventListener(ResultEvent.RESULT, retornoListaParticipacaoCentral);	
			grdParticipacaoCentral.addEventListener(KeyboardEvent.KEY_DOWN,definirIndiceListaSelecionadoKeyDown);			
			grdParticipacaoCentral.addEventListener(MouseEvent.CLICK,definirIndiceListaSelecionadoMouseEvent);
			grdParticipacaoCentral.addEventListener(MouseEvent.DOUBLE_CLICK,definirIndiceListaSelecionadoMouseEvent);	
		}	
		
		private function definirIndiceListaSelecionadoKeyDown(evt:KeyboardEvent):void{
			if (evt.keyCode == 13 && grdParticipacaoCentral.selectedItem != null){ 
				indiceListaSelecionado = grdParticipacaoCentral.selectedIndex;
			}	
		}
		
		private function definirIndiceListaSelecionadoMouseEvent(evt:MouseEvent):void{
			if(grdParticipacaoCentral.selectedItem != null){ 
				indiceListaSelecionado = grdParticipacaoCentral.selectedIndex;
			}	
		}
		
		private function configurarTela():void{
			preencherCmbAnoMes();			
			obterDefinicoes();				
		}
		
		private function configurarServico():void{
			onDestinoRecuperado(this.destino);

		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
			inicializaServico();
		}
		
		private function inicializaServico():void{
			servico.configurarDestino(destinoVO);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			configurarTela();				
		}		
		
		private function obterDefinicoes():void{
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
				
		private function retornoObterDefinicoes(event:ResultEvent):void {			
			_dadosDefinicoes = event.result.dados;			
			
			if(numCooperativaLogado!=1){
				bCentral = false;
				listaCentral = _dadosDefinicoes["listaCentral"] as ArrayCollection;
				
				for(var i:int=0; i < listaCentral.length; i++){
					if(listaCentral[i].codListaItem == numCooperativaLogado){
						bCentral = true;
						break;
					}
				}
				if(bCentral){				
					cmbCentral.text = numCooperativaLogado.toString();
					cmbCentral.enabled = false;
				}else{
					Alerta.show("O cadastro é utilizado apenas para centrais e bancoob.","Atenção",Alerta.ALERTA_OK);		
					fecharJanela();				
				}				
			}else{
				cmbCentral.inserirItemOpcional = true;
				cmbCentral.labelItemOpcional = "TODOS";					
				cmbCentral.dataProvider = _dadosDefinicoes["listaCentral"] as ArrayCollection;				
			}
			MostraCursor.removeBusyCursor();
			
		}
		
		private function botaoFecharPressionado(event:MouseEvent):void {
			fecharJanela();
		}
		
		private function btnProcurarPressionado(event:MouseEvent):void {	
			indiceListaSelecionado = -1;			
			if(cmbCentral.text=="" || cmbCentral.text =="TODOS"){
				dto.dados["cmbCentral"] = "";								
			}else{
				dto.dados["cmbCentral"] = cmbCentral.text;				
			}
			if(cmbAnoMesBase.text==""||cmbAnoMesBase.text=="TODOS"){
				dto.dados["cmbAnoMesBase"] = "";
			}else{
				dto.dados["cmbAnoMesBase"] = cmbAnoMesBase.selectedItem.toString().substr(3,4)+cmbAnoMesBase.selectedItem.toString().substr(0,2);
			}
			listarParticipacaoCentral(dto);			 			 				 			
		}

		public function preencherGrid():void {			
			indiceListaSelecionado = -1;			
			if(cmbCentral.text=="" || cmbCentral.text =="TODOS"){
				dto.dados["cmbCentral"] = "";								
			}else{
				dto.dados["cmbCentral"] = cmbCentral.text;				
			}
			if(cmbAnoMesBase.text==""||cmbAnoMesBase.text=="TODOS"){
				dto.dados["cmbAnoMesBase"] = "";
			}else{
				dto.dados["cmbAnoMesBase"] = cmbAnoMesBase.selectedItem.toString().substr(3,4)+cmbAnoMesBase.selectedItem.toString().substr(0,2);
			}
			listarParticipacaoCentral(dto);			 			 				 
		}
		
		private function validaListar():Boolean{
			if(cmbAnoMesBase.text==""){
				Alerta.show("O campo Mês/Ano é obrigatório.","Atenção",Alerta.ALERTA_OK);		
				return false;				
			}				
			return true;
		}		
		
		private function listarParticipacaoCentral(evt:RequisicaoReqDTO):void {
			servico.obterDados(evt);			 
		}
		
		private function retornoListaParticipacaoCentral(evt:ResultEvent):void{
			listaVO = evt.result.dados["registros"] as ArrayCollection;
			grdParticipacaoCentral.dataProvider = listaVO ;
			grdParticipacaoCentral.labelFunction = formataDataGrid;                   
			MostraCursor.removeBusyCursor();	
		}		
		
		/** Preenche combo com mes e ano de 05/2014 até a data do mes anterior.**/
		private function preencherCmbAnoMes():void{
			var vetData:ArrayCollection = new ArrayCollection();
			var dtAtual:Date = new Date();			
			var dtAtualNum:Number;
			var dtAtualStr:String;
			var cont:int = new int(dtBaseNum.toString().substr(4,2));	
			var dtBaseNumCmb:Number = dtBaseNum;
			
			
			var anoAtual:String = dtAtual.getFullYear().toString();
			var mesAtual:String = (dtAtual.getMonth()).toString();
			var anoBase:String = dtBaseNumCmb.toString().substr(0,4);
			
			mesAtual = (mesAtual.length == 1 ? '0'+mesAtual:mesAtual);
			dtAtualStr = anoAtual+''+mesAtual; 
			dtAtualNum = new Number(dtAtualStr);			
			
			vetData.addItem("TODOS");				
			vetData.addItem(formataMesAno(dtBaseNumCmb));				
			while (dtAtualNum > dtBaseNumCmb){				
				dtBaseNumCmb = new Number((dtBaseNumCmb+1));
				if (dtBaseNumCmb.toString().substr(4,2)=="13"){					
					dtBaseNumCmb = new Number(dtBaseNumCmb.toString().substr(0,4))+1;
					dtBaseNumCmb =  new Number(dtBaseNumCmb.toString()+'01');
				}								
				vetData.addItem(formataMesAno(dtBaseNumCmb));				
			}
			cmbAnoMesBase.dataProvider = vetData; 
		}
				
		/** Os valores que sao atribuidos pelo metodo acima não contém separadores, abaixo
		 * eles são colocados para que o usuario visualize uma data normal **/
		private function formataMesAno(dtBase:Number):String{
			var dtBaseStr:String = dtBase.toString();
			return dtBaseStr.substr(4,2)+'/'+dtBaseStr.substr(0,4);
		}				
		
		private function formataDataGrid(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";
			
			switch(cln.dataField){
				case "numAnoMesBase":
					if (vlr[cln.dataField] != null){
						retorno = vlr[cln.dataField].toString().substr(4,2) + "/" + vlr[cln.dataField].toString().substr(0,4);
					}
					break;
				case "valorParticipacao":
					if (vlr[cln.dataField] != null){
						retorno = FormataNumero.formata(vlr[cln.dataField],2,true).toString();
					}
					break;
				default:
					if(vlr[cln.dataField] == null)
						retorno = "";
					else
						retorno = vlr[cln.dataField].toString();
					
					break;

			}                                       
			return retorno;
		}
		
		private function janelaOnKeyDown(evt:KeyboardEvent):void{
			switch(evt.keyCode){
				case Keyboard.ESCAPE:
					evt.currentTarget.fecharJanela();					
					break;
			}
		}	
		
		private function abrirJanelaInclusao(evt:MouseEvent):void{			
			indiceListaSelecionado = -1;			
			strIncluir = "1";
			abrirJanela();			
		}
		
		private function abrirJanelaEdicao(evt:MouseEvent):void{
			strIncluir = "0";
			if(indiceListaSelecionado==-1){
				Alerta.show("É obrigatório a seleção de um registro para alteração.","Atenção",Alerta.ALERTA_OK);	
			}else{
				abrirJanela();
			}			
			return;			
		}			
		
		private function abrirJanela():void{
			var app:CadastroInvestimentoCentralBancoobEdicao = new CadastroInvestimentoCentralBancoobEdicao();
			
			jan = new Janela();
			jan.addEventListener(KeyboardEvent.KEY_DOWN,janelaOnKeyDown);
			jan.title = "Investimento da Central no Bancoob";
			jan.icone = "br/com/bancoob/imagens/icos/listaAplicativos/contacapital_16.png";									
			jan.addChild(DisplayObject(app));	
			jan.height = 285;
			jan.width = 505;
			app.cadastroInvestimentoCentralBancoob = this;
			jan.abrir(DisplayObject(Application.application), true, true);
			
			jan.setFocus();			
			
		}	
	}
}