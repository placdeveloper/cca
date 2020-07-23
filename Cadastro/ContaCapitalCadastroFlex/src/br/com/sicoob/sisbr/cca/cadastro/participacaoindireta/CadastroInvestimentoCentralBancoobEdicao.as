package br.com.sicoob.sisbr.cca.cadastro.participacaoindireta
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;

	public class CadastroInvestimentoCentralBancoobEdicao extends CadastroInvestimentoCentralBancoobEdicaoView
	{
				
		private var destinoVO:DestinoVO;
		private var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var _dadosDefinicoes:Object = new Object();
		private var servico:ServicoJava = new ServicoJava();		
		private var voLista:ArrayCollection = new ArrayCollection();		
		public 	var cadastroInvestimentoCentralBancoob:CadastroInvestimentoCentralBancoob;
		private var voParticipacaoCentralBancoob:ArrayCollection = new ArrayCollection();		
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.ParticipacaoCentralBancoobServico";		
		
		public function CadastroInvestimentoCentralBancoobEdicao()
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
			servico.obterDados.addEventListener(ResultEvent.RESULT, retornoListaParticipacaoCentral);		
			servico.gravarParticipacaoCentral.addEventListener(ResultEvent.RESULT, retornoParticipacaoCentral);					
		}	
		
		private function configurarTela():void{
			if(cadastroInvestimentoCentralBancoob.indiceListaSelecionado !=-1){
				configurarTelaEditar();								
			}else{
				configurarTelaInserir();			
			}
		}
		private function configurarTelaInserir():void{
			preencherCmbAnoMes();
			if(cadastroInvestimentoCentralBancoob.numCooperativaLogado==1){
				cmbCentral.enabled = true;
				valorParticipacao.text = "";																	
				obterDefinicoes();											
			}else{
				cmbCentral.text = cadastroInvestimentoCentralBancoob.numCooperativaLogado.toString();
				cmbCentral.enabled = false;
				valorParticipacao.text = "";																	
			}
		}
			
		private function configurarTelaEditar():void{
			cmbCentral.text = cadastroInvestimentoCentralBancoob.grdParticipacaoCentral.selectedItem["idInstituicaoCentral"];										
			cmbAnoMesBase.text = formataMesAno(cadastroInvestimentoCentralBancoob.grdParticipacaoCentral.selectedItem["numAnoMesBase"]);										
			valorParticipacao.text = FormataNumero.formata(cadastroInvestimentoCentralBancoob.grdParticipacaoCentral.selectedItem["valorParticipacao"],2,true).toString();													
		}		
		private function configurarServico():void{
			onDestinoRecuperado(cadastroInvestimentoCentralBancoob.destino);
			
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
			cmbCentral.dataProvider = _dadosDefinicoes["listaCentral"] as ArrayCollection;
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
				dto.dados["strIncluir"] = cadastroInvestimentoCentralBancoob.strIncluir;
				dto.dados["cmbCentral"] = cmbCentral.text;
				dto.dados["cmbAnoMesBase"] = cmbAnoMesBase.text.toString().substr(3,4)+cmbAnoMesBase.text.toString().substr(0,2);
				dto.dados["valorParticipacao"] = valorParticipacao.valor;
				gravarParticipacaoCentral(dto);			 			 				
			}
			return;
		}
		private function validarGravar():Boolean{
			
			if(cmbCentral.text==""){
				Alerta.show("O campo Central é obrigatório.","Atenção",Alerta.ALERTA_OK);		
				return false;				
			}				
			
			if(cmbAnoMesBase.text==""){
				Alerta.show("O campo Mês/Ano é obrigatório.","Atenção",Alerta.ALERTA_OK);		
				return false;				
			}				
			
			if(valorParticipacao.text == ""){
				Alerta.show("O campo valor é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;
			}
			return true;
		}
		
		private function gravarParticipacaoCentral(evt:RequisicaoReqDTO):void {
			servico.gravarParticipacaoCentral(evt);			 
		}
		private function retornoListaParticipacaoCentral(evt:ResultEvent):void{
			voLista = evt.result.dados["registros"] as ArrayCollection;
			MostraCursor.removeBusyCursor();	
		}		
		private function retornoParticipacaoCentral(evt:ResultEvent):void{
			var msg:String;			
			msg = evt.result.dados["msg"] as String;
			Alerta.show(msg, "Sucesso", Alerta.ALERTA_SUCESSO);								
			MostraCursor.removeBusyCursor();	
			
			cadastroInvestimentoCentralBancoob.preencherGrid();				
			fecharJanela();
		}		
		
		/** Preenche combo com mes e ano de 05/2014 até a data atual.**/
		private function preencherCmbAnoMes():void{
			var vetData:ArrayCollection = new ArrayCollection();
			var dtAtual:Date = new Date();			
			var dtAtualNum:Number;
			var dtAtualStr:String;
						
			var anoAtual:String;
			var mesAtual:String;
			
			if(dtAtual.getMonth() == 0){				
				anoAtual = (dtAtual.getFullYear()-1).toString();
				mesAtual = "12";				
			}else{
				anoAtual = dtAtual.getFullYear().toString();
				mesAtual = (dtAtual.getMonth()).toString();
			}			
			
			mesAtual = (mesAtual.length == 1 ? '0'+mesAtual:mesAtual);						
			dtAtualStr = anoAtual+''+mesAtual; 
			dtAtualNum = new Number(dtAtualStr);						
			vetData.addItem(formataMesAno(dtAtualNum));				
			cmbAnoMesBase.dataProvider = vetData; 
		}
		
		/** Os valores que sao atribuidos pelo metodo acima não contém separadores, abaixo
		 * eles são colocados para que o usuario visualize uma data normal **/
		private function formataMesAno(dtBase:Number):String{
			var dtBaseStr:String = dtBase.toString();
			return dtBaseStr.substr(4,2)+'/'+dtBaseStr.substr(0,4);
		}				
		
	}
}