package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.relatorios.fichapropostamatricula.RelFichaPropostaMatriculaView;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	public class RelFichaPropostaMatricula extends RelFichaPropostaMatriculaView {
		private	var capitalConfig:ContaCapitalConfiguracoes = new ContaCapitalConfiguracoes();			
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelFichaPropostaMatriculaServico";
		
		public function RelFichaPropostaMatricula()
		{			
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);		
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			btOk.addEventListener(MouseEvent.CLICK,emitirRelatorio);
			btFechar.addEventListener(MouseEvent.CLICK,fecharEmitirRelatorio);
			
			compNivelInst.cmbSingular.addEventListener(Event.CHANGE, onChangeCmbSingular);
		}
		
		private function onChangeCmbSingular(evt:Event):void {
			if(evt.currentTarget.selectedIndex > 0) {
				procurarCCA.idInstituicao = evt.currentTarget.selectedItem.codListaItem;
			}
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
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function resultObterDefinicoes(evt:ResultEvent):void {
			servico.removeEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			MostraCursor.removeBusyCursor();
			
			controlarTela();
			
			procurarCCA.idInstituicao = evt.result.dados["idInstituicao"];
		}
		
		private function controlarTela():void {
			compNivelInst.cmbCentral.percentWidth = new Number(100);
			compNivelInst.cmbSingular.percentWidth = new Number(100);
		}
		
		private function fecharEmitirRelatorio(evt:MouseEvent):void{
			this.fecharJanela();
		}
		
		private function emitirRelatorio(evt:MouseEvent): void {
			var dto:ParametroDTO = new ParametroDTO();
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;
			carregarDTO(dto);
			
			if(validar()) {
//				RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirRelatorioFichaPropostaMatricula",
//					SERVICO_SOURCE, dto, "RelFichaPropostaMatricula", destinoVO,"Emitindo relatório",
//					formatoRelatorio, exibirPreImpressao);
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelFichaPropostaMatriculaServicoRemote", 
					dto, "CCA_RelFichaPropostaMatricula", this.destino, "Emitindo relatório...", null, false);
			}
		}
		
		private function carregarDTO(dto:ParametroDTO):void {
			dto.dados.idInstituicao = new Number(procurarCCA.resultadoPesquisaVO.idInstituicao);
			dto.dados.idPessoa = new Number(procurarCCA.resultadoPesquisaVO.idPessoa);
			dto.dados.idContaCapital = new Number(procurarCCA.resultadoPesquisaVO.idContaCapital);
		}
		
		private function validar():Boolean {
			if(!instituicaoCentralPreenchida()) {
				Alerta.show("O campo Instutuição Resp é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbCentral);
				return false;
			}
			
			if(!instituicaoSingularPreenchida()) {
				Alerta.show("O campo Instutuição é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbSingular);
				return false;
			}
			
			if(!procurarCCA.isContaCapitalSelecionada()) {
				focusManager.setFocus(procurarCCA.txtNumCCA);
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			return true;
		}
		
		private function instituicaoSingularPreenchida():Boolean {
			if(compNivelInst.cmbSingular.selectedItem != null) {
				if(compNivelInst.cmbSingular.selectedIndex == 0 && compNivelInst.cmbSingular.value == "SELECIONE") {
					return false;
				}
				if(compNivelInst.cmbSingular.selectedItem.codListaItem > 0) {
					return true;
				}
			}
			return false;
		}
		
		private function instituicaoCentralPreenchida():Boolean {
			if(compNivelInst.cmbCentral.selectedItem != null) {
				if(compNivelInst.cmbCentral.selectedIndex == 0 && compNivelInst.cmbCentral.value == "SELECIONE") {
					return false;
				}
				if(compNivelInst.cmbCentral.selectedItem.codListaItem > 0) {
					return true;
				}
			}
			return false;
		}
	}
}