package
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.cadastro.cadastroconfiguracao.CadastroConfiguracaoCapitalEdicao;
	import br.com.sicoob.sisbr.cca.cadastro.cadastroconfiguracao.CadastroConfiguracaoCapitalView;
	
	public class CadastroConfiguracaoCapital extends CadastroConfiguracaoCapitalView
	{		
		private var _numCooperativa:int=parseInt(DadosLogin.coop);
		private var destinoVO:DestinoVO;
		private var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();		
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.ConfiguracaoCapitalServico";		
		public var indiceListaSelecionado:int=-1; //ITEM DA GRID SELECIONADO PARA ALGUMA AÇAO
		public var strIncluir:String;
		private var listaVO:ArrayCollection = new ArrayCollection();		
		private var jan:Janela;			
		
		public function CadastroConfiguracaoCapital()
		{			
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE,validarConfederacao);				
		}
		
		
		private function validarConfederacao(event:FlexEvent):void {
			if(_numCooperativa != 300){
				Alerta.show("Somente o usuário do Sicoob Confederação tem permissão para acessar a funcionalidade.","Atenção",Alerta.ALERTA_OK);					
				fecharJanela();
				return;								
			}else{
				init();				
			}
		}		
		
		private function init():void {
			configurarServico();
			btFechar.addEventListener(MouseEvent.CLICK, botaoFecharPressionado);
			btIncluir.addEventListener(MouseEvent.CLICK,abrirJanelaInclusao);
			btAlterar.addEventListener(MouseEvent.CLICK,abrirJanelaEdicao);
			btCancelar.addEventListener(MouseEvent.CLICK,botaoCancelarPressionado);
			grdParametro.addEventListener(KeyboardEvent.KEY_DOWN,definirIndiceListaSelecionadoKeyDown);			
			grdParametro.addEventListener(MouseEvent.CLICK,definirIndiceListaSelecionadoMouseEvent);
			grdParametro.addEventListener(MouseEvent.DOUBLE_CLICK,definirIndiceListaSelecionadoMouseEvent);					
			cmbTipoValor.addEventListener(ListEvent.CHANGE,definirTipoPesquisa);
			btnConsultar.addEventListener(MouseEvent.CLICK, btnConsultarPressionado);
			servico.obterDados.addEventListener(ResultEvent.RESULT, retornoListaDados);	
			
		}	
		
		private function configurarAtalho(event:KeyboardEvent):void {
			if(event.charCode == 13) {
				btnConsultarPressionado(null);
			}
		}
		
		private function definirIndiceListaSelecionadoKeyDown(evt:KeyboardEvent):void{
			if (evt.keyCode == 13 && grdParametro.selectedItem != null){ 
				indiceListaSelecionado = grdParametro.selectedIndex;
				btAlterar.enabled = true;
			}	
		}
		
		private function definirIndiceListaSelecionadoMouseEvent(evt:MouseEvent):void{
			if(grdParametro.selectedItem != null){ 
				indiceListaSelecionado = grdParametro.selectedIndex;
				btAlterar.enabled = true;
			}	
		}
		
		private function configurarTela():void{						
			btAlterar.enabled = false;
			txtPesquisa.restrict = "^'"; //retira restrição para tipo de entrada como texto
			txtPesquisa.text = "";
			txtPesquisa.maxChars = 200;
			montarComboPesquisa();
			
			focusManager.setFocus(txtPesquisa);
			txtPesquisa.addEventListener(KeyboardEvent.KEY_DOWN, configurarAtalho);
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
				
		private function botaoFecharPressionado(event:MouseEvent):void {
			fecharJanela();
		}	
		
		private function botaoCancelarPressionado(event:MouseEvent):void {
			cmbTipoValor.selectedIndex = 0;
			txtPesquisa.text = "";			
			grdParametro.dataProvider = null;
			indiceListaSelecionado = -1;			
			btAlterar.enabled = false;
		}	
		
		private function abrirJanelaInclusao(evt:MouseEvent):void{			
			btAlterar.enabled = false;
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

		private function janelaOnKeyDown(evt:KeyboardEvent):void{
			switch(evt.keyCode){
				case Keyboard.ESCAPE:
					evt.currentTarget.fecharJanela();					
					break;
			}
		}	
		
		private function abrirJanela():void{
			
			var app:CadastroConfiguracaoCapitalEdicao = new CadastroConfiguracaoCapitalEdicao();
			
			jan = new Janela();
			jan.addEventListener(KeyboardEvent.KEY_DOWN,janelaOnKeyDown);
			jan.title = "Cadastrar Parâmetro";
			jan.icone = "br/com/bancoob/imagens/icos/listaAplicativos/contacapital_16.png";									
			jan.addChild(DisplayObject(app));	
			jan.height = 445;
			jan.width = 516;
			app.cadastroConfiguracaoCapital = this;
			jan.abrir(DisplayObject(Application.application), true, true);
			
			jan.setFocus();		
		}			
		private function montarComboPesquisa():void{
			var vetCombo:ArrayCollection = new ArrayCollection();			
			vetCombo.addItem("NOME PARÂMETRO");				
			vetCombo.addItem("CÓDIGO PARÂMETRO");				
			vetCombo.addItem("DESCRIÇÃO PARÂMETRO");							
			cmbTipoValor.dataProvider = vetCombo;			
		}
		
		private function definirTipoPesquisa(ListEvent:Event):void {						
			if(cmbTipoValor.selectedIndex == 1){
				txtPesquisa.restrict = "0123456789.,\\"; //restringe números, vírgula, ponto e traço (para valores negativos)
				txtPesquisa.text = "";
				txtPesquisa.maxChars = 5;
			}else{				
				txtPesquisa.restrict = "^'"; //retira restrição para tipo de entrada como texto
				txtPesquisa.text = "";
				txtPesquisa.tipoEntrada = 0;
				txtPesquisa.maxChars = 200;
			}			
		}				
		
		private function btnConsultarPressionado(event:MouseEvent):void {	
			
			//if(txtPesquisa.text==""){
			//	Alerta.show("O campo pesquisa é obrigatório.","Atenção",Alerta.ALERTA_OK);	
			//	return;
			//}			
			
			//if(cmbTipoValor.selectedIndex != 0 && txtPesquisa.text.length < 3 ){
			//	Alerta.show("No campo pesquisa deve ser informado no mínimo três (3) caracteres.","Atenção",Alerta.ALERTA_OK);	
			//	return;
			//}			
			
			btAlterar.enabled = false;
			indiceListaSelecionado = -1;			
			dto.dados["tipoPesquisa"] = cmbTipoValor.selectedIndex;
			dto.dados["valorPesquisa"] = txtPesquisa.text;
			servico.obterDados(dto);			 
		}
				
		private function retornoListaDados(evt:ResultEvent):void{			
			var msg:String;			
			msg = evt.result.dados["msg"] as String;
			if(msg!=null){
				Alerta.show(msg,"Atenção",Alerta.ALERTA_OK);								
				MostraCursor.removeBusyCursor();	
				grdParametro.dataProvider = null;
				return;
			}
			
			listaVO = evt.result.dados["registros"] as ArrayCollection;
			grdParametro.dataProvider = listaVO;
			grdParametro.labelFunction = formataDataGrid;                   
			MostraCursor.removeBusyCursor();	
		}	

		private function formataDataGrid(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";
			
			switch(cln.dataField){
				case "bolAtivo":
					if (vlr[cln.dataField]){
						retorno = "ATIVO";
					}else{
						retorno = "INATIVO";						
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
		public function preencherGrid():void {					
			dto.dados["tipoPesquisa"] = cmbTipoValor.selectedIndex;
			dto.dados["valorPesquisa"] = txtPesquisa.text;
			servico.obterDados(dto);			 						
		}			
	}
}