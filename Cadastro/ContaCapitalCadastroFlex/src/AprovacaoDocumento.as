package
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.cadastro.aprovacao.AprovacaoDocumentoView;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	import br.com.sicoob.sisbr.cca.vo.DocumentoCapitalVO;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.FabricaUploadDocumentoGED;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.IUploadDocumentoGED;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.ParametroUploadDocumentoGED;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO", DocumentoCapitalVO);
	public class AprovacaoDocumento extends AprovacaoDocumentoView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.AprovacaoContaCapitalServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		public var vo:CadastroContaCapitalRenVO;
		
		private var factory:FabricaUploadDocumentoGED = new FabricaUploadDocumentoGED();
		private var upLoadDocGed:IUploadDocumentoGED;
		private var existeDocumento:Boolean = false; 
		
		public function AprovacaoDocumento(vo:CadastroContaCapitalRenVO, destinoVO:DestinoVO) {
			super();
			
			this.destino = destinoVO;
			
			this.vo = vo;
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function init(event:FlexEvent):void {
			controlarServico();
			inicializaServico();
		}
		
		private function controlarServico():void {
			servico.excluirDocumento.addEventListener(ResultEvent.RESULT, retornoExcluirDocumento);
			servico.excluirDocumento.addEventListener(FaultEvent.FAULT, retornoExcluirDocumentoError);
			servico.vincularDocumentos.addEventListener(ResultEvent.RESULT, retornoVincularDocumentos);
			servico.obterDefinicoesDocumentos.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesDocumentos);
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			obterDefinicoesDocumentos();
		}
		
		private function obterDefinicoesDocumentos():void {
			reqDTO.dados.vo = this.vo;
			servico.obterDefinicoesDocumentos(reqDTO);
		}
		
		private function retornoObterDefinicoesDocumentos(event:ResultEvent):void {
			initDocumento(event.result.dados["documentos"]);
			
			this.vo = event.result.dados["vo"];
			
			MostraCursor.removeBusyCursor();
		}
				
		private function initDocumento(documentos:ArrayCollection):void {
			initUploadDocumento();
			grid.dataProvider = documentos;
			
			if(documentos != null && documentos.length > 0) {
				existeDocumento = true;
			} else {
				existeDocumento = false;
			}						
			
			var botaoFechar:Botao = new Botao();
			botaoFechar.label = "FECHAR";
			botaoFechar.addEventListener(MouseEvent.CLICK, fecharJanelaMouseEvt);
			//tabDocumentos.getChildren()[0].btRemover.parent.addChild(botaoFechar as DisplayObject);
		}
		
		private function initUploadDocumento():void{
			var parametro:ParametroUploadDocumentoGED = new ParametroUploadDocumentoGED();
			
			var idDocs:ArrayCollection = new ArrayCollection();
			parametro.listaIdDocumentos = idDocs;
			
			var idFases:ArrayCollection = new ArrayCollection();
			idFases.addItem(1);
			
			parametro.listaIdFases = idFases.toArray();
			parametro.siglaAssunto = "CAD.CONT.CAPITAL";
			parametro.idModulo = "0";
			parametro.siglaSistema = "CAPITALWEB";
			parametro.valoresChaves = valorChaveGED();
			parametro.maxFileSelect = 5;
			
			var factory:FabricaUploadDocumentoGED = new FabricaUploadDocumentoGED();
			factory.carregar(parametro, onCarregarModulo);		
		}
		
		private function onCarregarModulo(uploadDocGED:IUploadDocumentoGED):void {
			uploadDocGED.addEventListener("uploadComplete", uploadCompleteGED);
			(uploadDocGED as Object).height = 250;
			upLoadDocGed = uploadDocGED;
			tabDocumentos.addChild(uploadDocGED as DisplayObject);
			
			if(existeDocumento) {
				(upLoadDocGed as Object).visible = false;
				grid.parent.visible = true;
			} else {
				(upLoadDocGed as Object).visible = true;
				grid.parent.visible = false;
			}
						
		}		
		
		private function valorChaveGED():ArrayCollection {
			var listValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = new Object();
			objChaveDoc.siglaTipoDocumento = "FICHAPROPMATRICULA";
			objChaveDoc.siglaChaveDocumento = "NUM.MAT.CONT.CAPITAL";
			objChaveDoc.valorChave = this.vo.numContaCapital;
			listValoresChaves.addItem(objChaveDoc);
			return listValoresChaves;
		}
		
		private function uploadCompleteGED(event:Event):void {
			var listaDocumentosSalvos:Array = (event as Object).itemsFileSucess as Array;
			
			var idDocs:Array = new Array();
			
			for(var i:uint = 0; i < listaDocumentosSalvos.length; i++) {
				idDocs.push(listaDocumentosSalvos[i].idDocumento);
			}
			
			reqDTO.dados.idDocs = idDocs;
			reqDTO.dados.vo = this.vo;

			this.reqDTO.dados.vo = this.vo;
			
			servico.vincularDocumentos(reqDTO);
		}
		
		private function retornoVincularDocumentos(evt:ResultEvent):void {
			this.fecharJanelaMouseEvt(null);
		}
		
		public function excluirDocumento(documentoVO:DocumentoCapitalVO):void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.documentoVO = documentoVO;
			servico.excluirDocumento(reqDTO);
		}
		
		private function retornoExcluirDocumento(evt:ResultEvent):void {
			grid.dataProvider = null;
			grid.parent.visible = false;
			
			
			(upLoadDocGed as Object).visible = true;
			
			MostraCursor.removeBusyCursor();
			
			Alerta.show("Arquivo removido com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO);
		}
		
		private function retornoExcluirDocumentoError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			obterDefinicoesDocumentos();
		}
		
		private function fecharJanelaMouseEvt(evt:MouseEvent):void {
			this.fecharJanela();
			(this.parentDocument as AprovacaoCadastroContaCapital).fecharJanela();
		}
		
		override public function fecharJanela():void {
			dispatchEvent(new Event("atualizarRegistrosConsulta", true));
			super.fecharJanela();
		}
	}
}