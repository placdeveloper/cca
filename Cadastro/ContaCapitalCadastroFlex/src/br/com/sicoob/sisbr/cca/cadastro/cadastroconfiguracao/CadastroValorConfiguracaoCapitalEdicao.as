package br.com.sicoob.sisbr.cca.cadastro.cadastroconfiguracao
{
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.vo.ValorConfiguracaoCapitalVO;

	public class CadastroValorConfiguracaoCapitalEdicao extends CadastroValorConfiguracaoCapitalEdicaoView
	{
		
		private var _numCooperativa:int=parseInt(DadosLogin.coop);
		private var destinoVO:DestinoVO;
		private var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var _dadosDefinicoes:Object = new Object();
		private var servico:ServicoJava = new ServicoJava();		
		public 	var cadastroValorConfiguracaoCapital:CadastroValorConfiguracaoCapital;		
		private var SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.ValorConfiguracaoCapitalServico";		
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelValorParametroServico";
		
		private var _validarMaiorZero:Boolean = false;
		
		[Bindable]
		private var instituicaoXML:XML = new XML();

		public function CadastroValorConfiguracaoCapitalEdicao()
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
			btRelatorio.addEventListener(MouseEvent.CLICK, botaoRelatorioPressionado);
			servico.gravarListaValorConfiguracaoCapital.addEventListener(ResultEvent.RESULT, retornoGravarValorConfiguracaoCapital);
		}			
		
		private function configurarServico():void{
			onDestinoRecuperado(cadastroValorConfiguracaoCapital.destino);
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

		private function configurarTela():void{
			var dtoConsulta:RequisicaoReqDTO = new RequisicaoReqDTO();
			if(cadastroValorConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"]=="" || cadastroValorConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"]=="0"){				
				Alerta.show("Parâmetro não identificado.","Atenção",Alerta.ALERTA_OK);					
				MostraCursor.removeBusyCursor();	
				fecharJanela();
				return;
			}
			btOK.enabled = false;
			txtValorParametro.enabled = false;
			cmbValorParametroLogico.enabled = false;
			dtValorParametroData.enabled = false;						
			
			dtoConsulta.dados.numCoop = _numCooperativa;
			dtoConsulta.dados["idConfiguracaoCapital"] = cadastroValorConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"];
			obterDefinicoes(dtoConsulta);							
		}		
		
		private function obterDefinicoes(dto:RequisicaoReqDTO):void{
			servico.obterDefinicoes(dto);
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {	
			MostraCursor.removeBusyCursor();			
			_dadosDefinicoes = event.result.dados;
			idConfiguracaoCapital.text = cadastroValorConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"];			
			nomeConfiguracaoCapital.text = _dadosDefinicoes["dadosConfiguracaoCapital"].nomeConfiguracaoCapital;																	
			descConfiguracaoCapital.text = _dadosDefinicoes["dadosConfiguracaoCapital"].descConfiguracaoCapital;	
			descTipoValorConfiguracaoCapital.text = _dadosDefinicoes["dadosConfiguracaoCapital"].descTipoValorConfiguracaoCapital;	
			
			
			if(_dadosDefinicoes["dadosConfiguracaoCapital"].idTipoValorConfiguracaoCapital == 1 && _dadosDefinicoes["dadosConfiguracaoCapital"].bolMaiorZero) {
				_validarMaiorZero = true;
			}
			
			if(_dadosDefinicoes["dadosConfiguracaoCapital"].bolAtivo){
				bolAtivo.text = "SIM";					
			}else{
				bolAtivo.text = "NÃO";									
			}
			descPerfilConfiguracaoCapital.text = _dadosDefinicoes["dadosConfiguracaoCapital"].descPerfilConfiguracaoCapital;	
			
			var tipoGrauCoopetativa:String = _dadosDefinicoes["tipoGrauCoopetativa"];
			var idPerfilConfiguracaoCapital:String = _dadosDefinicoes["dadosConfiguracaoCapital"].idPerfilConfiguracaoCapital;
			preencherCmbSituacao();
			
			if (tipoGrauCoopetativa == "3"){						
				btOK.enabled = true;				
				txtValorParametro.enabled = true;
				cmbValorParametroLogico.enabled = true;
				dtValorParametroData.enabled = true;										
			}else if (tipoGrauCoopetativa == "1"){
				if (idPerfilConfiguracaoCapital != "1"){
					btOK.enabled = true;										
					txtValorParametro.enabled = true;
					cmbValorParametroLogico.enabled = true;
					dtValorParametroData.enabled = true;										
				}				
			}else{
				if (idPerfilConfiguracaoCapital == "3"){
					btOK.enabled = true;										
					txtValorParametro.enabled = true;
					cmbValorParametroLogico.enabled = true;
					dtValorParametroData.enabled = true;										
				}								
			}
			
			if(_dadosDefinicoes["dadosConfiguracaoCapital"].idTipoValorConfiguracaoCapital == "1"){
				txtValorParametro.addEventListener(KeyboardEvent.KEY_UP, validarNumero);
				txtValorParametro.restrict = "0123456789.\\"; //restringe números, vírgula, ponto e traço (para valores negativos)
				txtValorParametro.text = "";
				txtValorParametro.maxChars = 9;				
				txtValorParametro.visible = true;
				cmbValorParametroLogico.visible = false;
				dtValorParametroData.visible = false;						
			}else if(_dadosDefinicoes["dadosConfiguracaoCapital"].idTipoValorConfiguracaoCapital == "2"){
				txtValorParametro.visible = false;
				cmbValorParametroLogico.visible = false;
				dtValorParametroData.visible = true;						
			}else if(_dadosDefinicoes["dadosConfiguracaoCapital"].idTipoValorConfiguracaoCapital == "3"){
				txtValorParametro.restrict = "^'"; //retira restrição para tipo de entrada como texto
				txtValorParametro.text = "";
				txtValorParametro.tipoEntrada = 0;
				txtValorParametro.maxChars = 200;
				txtValorParametro.visible = true;
				cmbValorParametroLogico.visible = false;			
				dtValorParametroData.visible = false;						
			}else if(_dadosDefinicoes["dadosConfiguracaoCapital"].idTipoValorConfiguracaoCapital == "4"){
				txtValorParametro.visible = false;
				dtValorParametroData.visible = false;						
				cmbValorParametroLogico.visible = true;		
			}			
							
			instituicaoXML = XML (_dadosDefinicoes["listaValorConfiguracaoCapital"].xmlString);
			trPermissions.dataProvider = instituicaoXML;	
			
			MostraCursor.removeBusyCursor();			
		}
		
		private function botaoFecharPressionado(event:MouseEvent):void {
			fecharJanela();
		}
		
		private function botaoCancelarPressionado(event:MouseEvent):void {
			txtValorParametro.text = "";
		}
		
		private function botaoRelatorioPressionado(event:MouseEvent):void {
			var idsInstituicao:ArrayCollection = new ArrayCollection();	
			var vo:ValorConfiguracaoCapitalVO;
			
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;
//			var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
			var dtoRel:ParametroDTO = new ParametroDTO();
			
			var xList:XMLList = instituicaoXML.descendants().(@CHECKED == "1");
			for each(var xml:XML in xList) {
				idsInstituicao.addItem(new Number(xml.@IDINSTITUICAO));
			}
			
			if(idsInstituicao.length == 0) {
				Alerta.show("Selecione a Instituição.","Atenção", Alerta.ALERTA_OK);	
				return;
			}
			
			dtoRel.dados.idConfiguracao = cadastroValorConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"];
			dtoRel.dados.idsInstituicao = idsInstituicao;
			
//			RelatorioUtil.getRelatorioUtil().emitirRelatorio(
//				"gerarRelatorio", SERVICO_REL_SOURCE, dtoRel, 
//				"CCA_Relatorio_HistValorParametro", destinoVO, "Emitindo relatório",
//				formatoRelatorio, exibirPreImpressao);
			RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelValorParametroServicoRemote", 
				dtoRel, "CCA_RelValorParametro", this.destino, "Emitindo relatório...", null, false);
		}
		
		private function botaoOKPressionado(event:MouseEvent):void {
			
			if(validarGravar()){
				dto.dados["txtValorParametro"] = txtValorParametro.text;
				
				var arrParametrosVO:ArrayCollection = new ArrayCollection();	
				var vo:ValorConfiguracaoCapitalVO;
				var idInstituicaoAnt:Number;
								
				var xList:XMLList = instituicaoXML.descendants().(@CHECKED == "1");
				for (var x:uint=0; x < xList.length(); x++){
					if(xList[x].@LINHAPAI == "0"){	
						if (idInstituicaoAnt!=xList[x].@IDINSTITUICAO){							
							vo = new ValorConfiguracaoCapitalVO;
							
							vo.idInstituicao = xList[x].@IDINSTITUICAO;						
							vo.idConfiguracaoCapital = cadastroValorConfiguracaoCapital.grdParametro.selectedItem["idConfiguracaoCapital"];
							vo.idValorConfiguracaoCapital = xList[x].@IDVALORCONFIGURACAOCAPITAL;
							if(txtValorParametro.visible){							
								vo.valorConfiguracao = txtValorParametro.text;																	
							}else if(cmbValorParametroLogico.visible){
								if(cmbValorParametroLogico.selectedIndex==2){
									vo.valorConfiguracao = "1";																										
								}else{
									vo.valorConfiguracao = "0";																																			
								}
							}else if(dtValorParametroData.visible){
								var valorParametroData:Date;
								valorParametroData = dtValorParametroData.selectedDate;
								var format:DateFormatter = new DateFormatter();
								format.formatString = "DD/MM/YYYY";
								vo.valorConfiguracao = format.format(valorParametroData);
							}
							
							vo.idUsuario = DadosLogin.usr;
							
							arrParametrosVO.addItem(vo);							
						}
						idInstituicaoAnt= Number(xList[x].@IDINSTITUICAO);					
					}
					
				}
						 
				dto.dados["listaValorConfiguracaoCapital"] = arrParametrosVO;
				
				gravarValorConfiguracaoCapital(dto);			 			 				
			}
			
		}		
		
		private function gravarValorConfiguracaoCapital(evt:RequisicaoReqDTO):void {
			servico.gravarListaValorConfiguracaoCapital(evt);			 
		}
		
		private function validarGravar():Boolean{
			
			if(txtValorParametro.visible && txtValorParametro.text == ""){
				Alerta.show("O campo valor parâmetro é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;
			}else if(dtValorParametroData.visible && dtValorParametroData.selectedDate == null){
				Alerta.show("O campo valor parâmetro é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;				
			}else if(cmbValorParametroLogico.visible && cmbValorParametroLogico.text == ""){
				Alerta.show("O campo valor parâmetro é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;				
			}
			
			var xList:XMLList = instituicaoXML.descendants().(@CHECKED == "1");
			if(xList.length() < 1){
				Alerta.show("O campo instituição é obrigatório.","Atenção",Alerta.ALERTA_OK);					
				return false;				
			}
			
			if(_validarMaiorZero && new Number(txtValorParametro.text) <= 0) { 
				Alerta.show("O campo valor parâmetro deve ser maior que zero.", "Atenção",Alerta.ALERTA_OK);					
				return false;		
			}
			
			if(_dadosDefinicoes["dadosConfiguracaoCapital"].idTipoValorConfiguracaoCapital == "1" && isNaN(new Number(txtValorParametro.text))) {
				Alerta.show("Campo valor parâmetro inválido. Formato deve ser: 9999.99", "Atenção", Alerta.ALERTA_OK);					
				return false;
			}
			
			return true;
		}		
		
		private function retornoGravarValorConfiguracaoCapital(evt:ResultEvent):void{
			var msg:String;			
			msg = evt.result.dados["msg"] as String;
			Alerta.show(msg, "Sucesso", Alerta.ALERTA_SUCESSO);								
			MostraCursor.removeBusyCursor();	
			
			cadastroValorConfiguracaoCapital.preencherGrid();		
			fecharJanela();
		}	
		
		private function validarNumero(evt:KeyboardEvent):void {
			if(isNaN(new Number(txtValorParametro.text))) {
				txtValorParametro.text = txtValorParametro.text.substr(0, txtValorParametro.length -1);
			}
		}
		
		private function preencherCmbSituacao():void{
			var vetSituacao:ArrayCollection = new ArrayCollection();
			vetSituacao.addItem("");					
			vetSituacao.addItem("FALSO");					
			vetSituacao.addItem("VERDADEIRO");		
			cmbValorParametroLogico.dataProvider = vetSituacao; 
		}				
	}
}