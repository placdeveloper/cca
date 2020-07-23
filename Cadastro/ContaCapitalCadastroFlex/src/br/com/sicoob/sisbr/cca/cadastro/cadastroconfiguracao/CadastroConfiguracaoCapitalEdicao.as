package br.com.sicoob.sisbr.cca.cadastro.cadastroconfiguracao
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;

	public class CadastroConfiguracaoCapitalEdicao extends CadastroConfiguracaoCapitalEdicaoView
	{
		
		private var destinoVO:DestinoVO;
		private var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var _dadosDefinicoes:Object = new Object();
		private var servico:ServicoJava = new ServicoJava();		
		public 	var cadastroConfiguracaoCapital:CadastroConfiguracaoCapital;
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.ConfiguracaoCapitalServico";		
		private var listaTipoValorConfiguracaoCapital:ArrayCollection = new ArrayCollection;			
		private var listaPerfilConfiguracaoCapital:ArrayCollection =  new ArrayCollection;					
		private var listaAgrupador:ArrayCollection = new ArrayCollection;

		public function CadastroConfiguracaoCapitalEdicao()
		{
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE,init);									
		}		
		
		private function init(event:FlexEvent):void {
			configurarServico();
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			btFechar.addEventListener(MouseEvent.CLICK, botaoFecharPressionado);
			btCancelar.addEventListener(MouseEvent.CLICK, botaoCancelarPressionado);			
			btOK.addEventListener(MouseEvent.CLICK, botaoOKPressionado);
			servico.gravarConfiguracaoCapital.addEventListener(ResultEvent.RESULT, retornoGravarConfiguracaoCapital);					
			
		}			
		
		private function configurarServico():void{
			onDestinoRecuperado(cadastroConfiguracaoCapital.destino);

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
		
		private function obterDefinicoes(dto:RequisicaoReqDTO):void{
			servico.obterDefinicoes(dto);
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {			
			_dadosDefinicoes = event.result.dados;
			
			listaTipoValorConfiguracaoCapital = _dadosDefinicoes["listaTipoValorConfiguracaoCapital"] as ArrayCollection;			
			listaPerfilConfiguracaoCapital = _dadosDefinicoes["listaPerfilConfiguracaoCapital"] as ArrayCollection;			
			listaAgrupador = _dadosDefinicoes["listaAgrupador"] as ArrayCollection;
			
			cmbTipoValorConfiguracaoCapital.dataProvider = listaTipoValorConfiguracaoCapital as ArrayCollection;
			cmbPerfilConfiguracaoCapital.dataProvider = listaPerfilConfiguracaoCapital as ArrayCollection;
			cmbAgrupador.dataProvider = listaAgrupador as ArrayCollection;
			preencherCmbSituacao();
			
			if(cadastroConfiguracaoCapital.indiceListaSelecionado ==-1){
				idConfiguracaoCapital.enabled = true;
				idConfiguracaoCapital.text = _dadosDefinicoes["idConfiguracaoCapital"];
				if(idConfiguracaoCapital.text == "0"){
					idConfiguracaoCapital.text = "1";
				}
				nomeConfiguracaoCapital.text = "";																	
				descConfiguracaoCapital.text = "";																	
				cmbTipoValorConfiguracaoCapital.selectedIndex = 0;
				cmbPerfilConfiguracaoCapital.selectedIndex = 0;
				cmbSituacao.selectedIndex = 1;			
			}else{
				idConfiguracaoCapital.enabled = false;			
				idConfiguracaoCapital.text = cadastroConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"];			
				nomeConfiguracaoCapital.text = _dadosDefinicoes["dadosConfiguracaoCapital"].nomeConfiguracaoCapital;																	
				descConfiguracaoCapital.text = _dadosDefinicoes["dadosConfiguracaoCapital"].descConfiguracaoCapital;																	
				
				cmbPerfilConfiguracaoCapital.procuraItemPorNome(_dadosDefinicoes["dadosConfiguracaoCapital"].idPerfilConfiguracaoCapital, "codListaItem");
				cmbTipoValorConfiguracaoCapital.procuraItemPorNome(_dadosDefinicoes["dadosConfiguracaoCapital"].idTipoValorConfiguracaoCapital, "codListaItem");
				
				if(_dadosDefinicoes["dadosConfiguracaoCapital"].idAgrupador != null) {
					cmbAgrupador.procuraItemPorNome(_dadosDefinicoes["dadosConfiguracaoCapital"].idAgrupador, "codListaItem");
				}
				
				bolMaiorZero.selected = _dadosDefinicoes["dadosConfiguracaoCapital"].bolMaiorZero;
				bolMostrarRelatorio.selected = _dadosDefinicoes["dadosConfiguracaoCapital"].bolMostrarRelatorio;
															
				if( _dadosDefinicoes["dadosConfiguracaoCapital"].bolAtivo){
					cmbSituacao.selectedIndex = 1;								
				}else{
					cmbSituacao.selectedIndex = 0;													
				}
				
				if(_dadosDefinicoes["possuiValorConfiguracaoCapital"]!="0"){
					cmbTipoValorConfiguracaoCapital.enabled = false;													
				}				
				
			}								
			
			MostraCursor.removeBusyCursor();
		}
		
		private function botaoFecharPressionado(event:MouseEvent):void {
			fecharJanela();
		}
		
		private function botaoCancelarPressionado(event:MouseEvent):void {
			configurarTela();
		}
		
		private function botaoOKPressionado(event:MouseEvent):void {
			if(validarGravar()){
				dto.dados["strIncluir"] = cadastroConfiguracaoCapital.strIncluir;
				dto.dados["cmbTipoValorConfiguracaoCapital"] = cmbTipoValorConfiguracaoCapital.selectedItem.codListaItem;
				dto.dados["cmbPerfilConfiguracaoCapital"] = cmbPerfilConfiguracaoCapital.selectedItem.codListaItem;
				dto.dados["cmbAgrupador"] = cmbAgrupador.selectedItem.codListaItem;
				dto.dados["cmbSituacao"] = cmbSituacao.selectedIndex;
				dto.dados["idConfiguracaoCapital"] = idConfiguracaoCapital.valor;
				dto.dados["nomeConfiguracaoCapital"] = nomeConfiguracaoCapital.text;
				dto.dados["descConfiguracaoCapital"] = descConfiguracaoCapital.text;
				dto.dados["bolMaiorZero"] = bolMaiorZero.selected;
				dto.dados["bolMostrarRelatorio"] = bolMostrarRelatorio.selected;
				
				gravarConfiguracaoCapital(dto);			 			 				
			}
			return;
		}		
		
		private function validarGravar():Boolean{
			
			if(idConfiguracaoCapital.text == ""){
				Alerta.show("O campo Código é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;
			}
			if(nomeConfiguracaoCapital.text == ""){
				Alerta.show("O campo Nome é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;
			}
			if(descConfiguracaoCapital.text == ""){
				Alerta.show("O campo Descrição é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;
			}
			if(cmbTipoValorConfiguracaoCapital.text==""){
				Alerta.show("O campo Tipo Valor é obrigatório.","Atenção",Alerta.ALERTA_OK);		
				return false;				
			}							
			if(cmbSituacao.text == ""){
				Alerta.show("O campo Ativo é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;
			}
			if(cmbPerfilConfiguracaoCapital.text==""){
				Alerta.show("O campo Administrado por é obrigatório.","Atenção",Alerta.ALERTA_OK);		
				return false;				
			}				
			
			return true;
		}

		private function configurarTela():void{
			var dtoConsulta:RequisicaoReqDTO = new RequisicaoReqDTO();
			if(cadastroConfiguracaoCapital.indiceListaSelecionado !=-1){
				dtoConsulta.dados["idConfiguracaoCapital"] = cadastroConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"];
			}else{
				dtoConsulta.dados["idConfiguracaoCapital"] = 0;				
			}
			obterDefinicoes(dtoConsulta);							
		}
		
		private function preencherCmbSituacao():void{
			var vetSituacao:ArrayCollection = new ArrayCollection();
			vetSituacao.addItem("NÃO");					
			vetSituacao.addItem("SIM");		
			cmbSituacao.dataProvider = vetSituacao; 
		}		
		
		private function gravarConfiguracaoCapital(evt:RequisicaoReqDTO):void {
			servico.gravarConfiguracaoCapital(evt);			 
		}
		
		private function retornoGravarConfiguracaoCapital(evt:ResultEvent):void{
			var msg:String;			
			msg = evt.result.dados["msg"] as String;
			Alerta.show(msg, "Sucesso", Alerta.ALERTA_SUCESSO);								
			MostraCursor.removeBusyCursor();	
			cadastroConfiguracaoCapital.preencherGrid();								
			fecharJanela();
		}					
	}
}