package
{	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.nivelinstituicao.NivelInstituicao;
	import br.com.sicoob.sisbr.cca.relatorios.participacaoindireta.RelParticipacaoIndiretaSingularView;
	
	public class RelParticipacaoIndiretaSingular extends RelParticipacaoIndiretaSingularView {
		
		private	var capitalConfig:ContaCapitalConfiguracoes = new ContaCapitalConfiguracoes();			
		private var destinoVO:DestinoVO;
		private var servico:ServicoJava = new ServicoJava();
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelParticipacaoIndiretaServico";
		
		
		
		public function RelParticipacaoIndiretaSingular() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			btOk.addEventListener(MouseEvent.CLICK,emitirRelatorio);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelarEmitirRelatorio);
			btFechar.addEventListener(MouseEvent.CLICK,fecharEmitirRelatorio);
		}
		
		private function configurarServico():void {
//			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
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
			servico.addEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			servico.bloquearOperacao = true;
			servico.mensagemEspera = "Obtendo Definições";
			servico.obterDefinicoes(new RequisicaoReqDTO()); // transferir objeto para servico e alterar comportamento
		}
		
		private function resultObterDefinicoes(evt:ResultEvent):void {		
			servico.removeEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			MostraCursor.removeBusyCursor();
			//capitalConfig.obterConfiguracoesCentralSingular(procurarCentral,procurarSingular,evt.result.dados);
			
			compNivelInst.cmbCentral.percentWidth = new Number(80);
			compNivelInst.rtlCentral.width = new Number(80);
			
			compNivelInst.cmbSingular.percentWidth = new Number(80);
			compNivelInst.rtlSingular.width = new Number(80);
			
			//procurarCentral.txtValor.maxChars = 4;
			//procurarSingular.txtValor.maxChars = 4;	
			preencherCboAno();
			preencherCboMes();
			//exibirChkAgruparBancoob();
		}

		private function emitirRelatorio(evt:MouseEvent): void {
			var dto:ParametroDTO = new ParametroDTO();
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;
			
			if(chkArquivoExcel.selected){
				formatoRelatorio = PreImpressao.FORMATO_XLS_SEM_FORMATACAO;
			}
			
			var selectedCentral:* = compNivelInst.cmbCentral.selectedItem;
			var selectedSingular:* = compNivelInst.cmbSingular.selectedItem;
			
			// Retorna sem ação no caso de não seleção de central nem singular
			if( (selectedCentral == null || selectedCentral is String )
				|| 
				(selectedSingular == null || selectedSingular is String)){
				return;
			}

			carregarDTO(dto);

			RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelParticipacaoIndiretaSingularServicoRemote", 
				dto, "CCA_RelParticipacaoIndiretaSingular", this.destino, "Emitindo relatório...", formatoRelatorio, exibirPreImpressao);
		}
		
		private function carregarDTO(dto:ParametroDTO):void {
			dto.dados.mes = (cboMes.selectedIndex as Number).toString();
			dto.dados.ano = (cboAno.selectedItem as Number).toString();			
			dto.dados.agruparPorCentral = chkArquivoExcel.selected;			
			
			dto.dados.numCentral = new Number(compNivelInst.cmbCentral.selectedItem.codListaItem);
			dto.dados.numCooperativa = new Number(compNivelInst.cmbSingular.selectedItem.codListaItem);			
		}
		
		private function cancelarEmitirRelatorio(evt:MouseEvent):void{
			obterDefinicoes();
			compNivelInst.resetInstituicao();
			cboAno.selectedIndex = 0;
			cboMes.selectedIndex = 0;
			chkArquivoExcel.selected = false;
		}
		
		private function fecharEmitirRelatorio(evt:MouseEvent):void{
			this.fecharJanela();			
		}
		
		private function preencherCboAno():void {
			var dados:ArrayCollection = new ArrayCollection();
			for(var i:int = 2014; i <= new Date().fullYear; i++) {
				dados.addItem(i);				
			}
		
			cboAno.dataProvider = dados;	
		}
		
		private function preencherCboMes():void {
			var dados:ArrayCollection = new ArrayCollection();
			dados.addItemAt("Todos", 0);
			dados.addItemAt("Janeiro", 1);				
			dados.addItemAt("Fevereiro", 2);
			dados.addItemAt("Março", 3);
			dados.addItemAt("Abril", 4);
			dados.addItemAt("Maio", 5);
			dados.addItemAt("Junho", 6);
			dados.addItemAt("Julho", 7);
			dados.addItemAt("Agosto", 8);
			dados.addItemAt("Setembro", 9);
			dados.addItemAt("Outubro", 10);
			dados.addItemAt("Novembro", 11);
			dados.addItemAt("Dezembro", 12);
			
			cboMes.dataProvider = dados;
		}
		/*
		private function exibirChkAgruparBancoob():void {
			if(DadosLogin.coop == "0001"){
				chkAgruparPorCentral.visible = true;
				rotuloAgruparPorCentral.visible = true;	
			}
		}*/
	}
}