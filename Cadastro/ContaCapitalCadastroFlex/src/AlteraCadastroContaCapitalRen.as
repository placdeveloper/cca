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
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.HBoxBancoob;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.cadastro.contacapital.AlteraCadastroContaCapitalRenView;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	import br.com.sicoob.sisbr.cca.vo.DocumentoCapitalVO;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.FabricaUploadDocumentoGED;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.IUploadDocumentoGED;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.ParametroUploadDocumentoGED;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO", DocumentoCapitalVO);
	public class AlteraCadastroContaCapitalRen extends AlteraCadastroContaCapitalRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.CadastroContaCapitalRenServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelFichaPropostaMatriculaServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		private var voSemAlteracao:CadastroContaCapitalRenVO;
		private var tabSelecionada:int;
		public var vo:CadastroContaCapitalRenVO;
		private var factory:FabricaUploadDocumentoGED = new FabricaUploadDocumentoGED();
		private var upLoadDocGed:IUploadDocumentoGED;

		public function AlteraCadastroContaCapitalRen(vo:CadastroContaCapitalRenVO) {
			super();
			
			this.vo = vo;
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function init(event:FlexEvent):void {
			configurarServico();
			
			controlarServico();
			
			controlarEventos();
		}
		
		private function configurarServico():void {
			destinoVO = this.destino;

			servico.configurarDestino(destinoVO);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			obterDefinicoesAlterar();
		}
		
		private function obterDefinicoesAlterar():void {
			MostraCursor.setBusyCursor("Recuperando registros...", this);
			reqDTO.dados.vo = this.vo;
			servico.obterDefinicoesAlterar(reqDTO);
		}
		
		private function retornoObterDefinicoesAlterar(event:ResultEvent):void {
			this.voSemAlteracao = event.result.dados["vo"];
			
			this.vo = event.result.dados["vo"];

			//add para atender a abertura da tela de aprovação pela aprovação de documentos
			tabNav.selectedIndex = tabSelecionada; 
			
			dataInclusao.text = this.vo.dataInclusao;
			numContaCapital.text = this.vo.numContaCapital.toString();
			central.text = this.vo.descCentral;
			singular.text = this.vo.descSingular;
			pessoa.text = FormatUtil.formataCPFCNPJ(this.vo.cpfCnpj) + " " + this.vo.nomePessoa;
			
			vlrSubs.valor = this.vo.vlrSubs;
			vlrInteg.valor = this.vo.vlrInteg;
			qtdParcelas.valor = this.vo.qtdParcelas;
			vlrParcelas.valor = this.vo.vlrParcelas;
			diaDebito.valor = this.vo.diaDebito;
			
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			tipoInteg.procuraItemPorNome(this.vo.tipoInteg.toString() ,"codListaItem");
			
			controlarTela();
			
			initDocumento(event.result.dados["documentos"]);
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoesAlterar.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesAlterar);
			servico.obterDefinicoesAlterar.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesAlterarError);
			
			servico.alterar.addEventListener(ResultEvent.RESULT, retornoAlterar);
			servico.alterar.addEventListener(FaultEvent.FAULT, retornoAlterarError);
			
			servico.vincularDocumentos.addEventListener(ResultEvent.RESULT, retornoVincularDocumentos);
			//servico.vincularDocumentos.addEventListener(FaultEvent.FAULT, retornoVincularDocumentosError);
			
			servico.excluirDocumento.addEventListener(ResultEvent.RESULT, retornoExcluirDocumento);
			//servico.excluirDocumento.addEventListener(FaultEvent.FAULT, retornoExcluirDocumentoError);
		}
		
		private function controlarEventos():void {
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, alterar);
			
			vlrSubs.addEventListener(Event.CHANGE, calcularValorParcela);
			vlrInteg.addEventListener(Event.CHANGE, calcularValorParcela);
			qtdParcelas.addEventListener(Event.CHANGE, calcularValorParcela);
		}
		
		private function controlarTela():void {
			//Permite alterar campos da tela quando status da proposta for 'aguardando aprovacao' ou 'devolvida' 
			var permiteAlterar:Boolean = this.vo.idSituacaoCadastro == 1 || this.vo.idSituacaoCadastro == 4;
			vlrSubs.enabled = permiteAlterar;
			vlrInteg.enabled = permiteAlterar;
			qtdParcelas.enabled = permiteAlterar;
			diaDebito.enabled = permiteAlterar;
			tipoInteg.enabled = permiteAlterar;
			
			//Permite incluir/alterar documentos quando status da proposta for 'aprovado', 'rejeitada' ou aprovado c/ pend doc
			//			tabDocumentos.enabled = this.vo.idSituacaoCadastro == 2 || this.vo.idSituacaoCadastro == 3 || this.vo.idSituacaoCadastro == 5;
			//			if(this.vo.idSituacaoContaCapital != 1) {
			//				tabDocumentos.enabled = false;
			//			}
		}
		
		private function retornoObterDefinicoesAlterarError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			this.fecharJanela();
		}
		
		private function alterar(evt:MouseEvent):void {
			if(validar()) {
				reqDTO.dados.isDadosPropostaAlterados = isDadosPropostaAlterados();
				reqDTO.dados.vo = carregarVO();
				reqDTO.dados.atualizarSituacaoCadastro = false;
				if (vo.idSituacaoCadastro == 3 || vo.idSituacaoCadastro == 4) { // 3-rejeitado/4-devolvido
					var descSituacao:String = (vo.idSituacaoCadastro == 3) ? "Rejeitado" : "Devolvido";
					Alerta.show("Esta proposta encontra-se na situação "+descSituacao+". Deseja reencaminhar para aprovação? Caso negativo, as alterações serão gravadas mas manterá a situação atual.",
						"Reencaminhar proposta", Alerta.ALERTA_PERGUNTA, null, confirmarAlterarComSituacao, confirmarAlterarSemSituacao);
				} else {
					this.servico.alterar(reqDTO);
				}
			}
		}
		
		private function confirmarAlterarComSituacao(evt:Event):void {
			reqDTO.dados.atualizarSituacaoCadastro = true;
			this.servico.alterar(reqDTO);
		}
		
		private function confirmarAlterarSemSituacao(evt:Event):void {
			this.servico.alterar(reqDTO);
		}
		
		private function retornoAlterar(evt:ResultEvent):void {
			if(evt.result.dados["erroNegocial"]) {
				Alerta.show(evt.result.dados["msg"], "ALERTA", Alerta.ALERTA_ERRO);
				MostraCursor.removeBusyCursor();
				return;
			}
			
			this.vo = evt.result.dados["vo"];
			
			Alerta.show(evt.result.dados["msg"], "SUCESSO", Alerta.ALERTA_SUCESSO, null, fecharJanelaMouseEvt);
		}
		
		private function retornoAlterarError(evt:FaultEvent):void {
			this.removeAllChildren();
			this.addChild(new CadastroContaCapitalRen() as DisplayObject);
			Alerta.show("Erro ao gravar os dados.", "ALERTA", Alerta.ALERTA_ERRO);
		}
		
		/**
		 * Carrega valores da camada de apresentacao para o VO 
		 */
		private function carregarVO():CadastroContaCapitalRenVO {
			vo.vlrSubs = vlrSubs.valor;
			vo.vlrInteg = vlrInteg.valor;
			vo.qtdParcelas = qtdParcelas.valor;
			vo.vlrParcelas = vlrParcelas.valor;
			vo.diaDebito = diaDebito.valor;
			if (tipoInteg.selectedItem != null) {
				vo.tipoInteg = new Number(tipoInteg.selectedItem.codListaItem);
			}
			return vo;
		}
		
		/**
		 * Valida campos de acordo com regras de negocio
		 */
		private function validar():Boolean {
			
			var permiteAlterar:Boolean = this.vo.idSituacaoCadastro == 1 || this.vo.idSituacaoCadastro == 4;
			
			if(!permiteAlterar) {
				return true;
			}
			
			if(vlrSubs.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("Valor da Subscrição deve ser maior que 0,00.", "ATENÇÃO", Alerta.ALERTA_OK, vlrSubs);
				return false;
			}
			
			if(vlrInteg.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("Valor da Integralização à Vista deve ser maior que 0,00.", "ATENÇÃO", Alerta.ALERTA_OK, vlrInteg);
				return false;
			}
			
			if(vlrSubs.valor != vlrInteg.valor) {
				tabNav.selectedIndex = 1;
				if(qtdParcelas.valor <= 0 || qtdParcelas.valor > 100) {
					Alerta.show("Quant. Parcelas deve estar entre 1 e 100.", "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
					return false;
				}
			}
			
			if(vlrSubs.valor < vlrInteg.valor) {
				tabNav.selectedIndex = 1;
				Alerta.show("O valor da Integralização à Vista deve ser igual ou menor que o Valor da Subscrição.", "ATENÇÃO", Alerta.ALERTA_OK, vlrInteg);
				return false;
			}
			
			if(diaDebito.valor <= 0) {
				tabNav.selectedIndex = 1;
				Alerta.show("Dia para Débito deve ser preenchido.", "ATENÇÃO", Alerta.ALERTA_OK, diaDebito);
				return false;
			}
			
			if(diaDebito.valor > 31) {
				tabNav.selectedIndex = 1;
				Alerta.show("Dia para Débito inválido.", "ATENÇÃO", Alerta.ALERTA_OK, diaDebito);
				return false;
			}
			
			return true;
		}
		
		private function calcularValorParcela(evt:Event):void {
			if(vlrSubs.valor == vlrInteg.valor) {
				qtdParcelas.valor = 0;
				vlrParcelas.valor = 0;
				return;
			}
			
			if(vlrSubs.valor > 0 && vlrInteg.valor > 0 && qtdParcelas.valor > 0) {
				vlrParcelas.valor = (vlrSubs.valor - vlrInteg.valor) / qtdParcelas.valor;
				vlrParcelas.valor.toFixed(2);
				return;
			}
			
			vlrParcelas.valor = 0;
		}
		
		/**
		 * Retorna os valores da camada de apresentacao para o estado inicial 
		 */
		private function cancelar(evt:MouseEvent):void {
			var novo:AlteraCadastroContaCapitalRen = new AlteraCadastroContaCapitalRen(this.vo);
			novo.destino = this.destino;
			this.removeAllChildren();
			this.addChild(novo as DisplayObject);
		}
		
		private function fechar(evt:MouseEvent):void {
			if(!isDadosPropostaAlterados()) {
				super.fecharJanela();
			} else {
				Alerta.show("Informações não foram salvas! Deseja realmente sair?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, confirmarFechar);
			}
		}
		
		private function confirmarFechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		/**
		 * Verifica se os dados originais da proposta de subscricao foram alterados
		 */
		private function isDadosPropostaAlterados():Boolean {
			if(voSemAlteracao.vlrSubs != vlrSubs.valor) {
				return true;
			}
			
			if(voSemAlteracao.vlrInteg != vlrInteg.valor) {
				return true;
			}
			
			if(voSemAlteracao.qtdParcelas != qtdParcelas.valor) {
				return true;
			}
			
			if(voSemAlteracao.vlrParcelas != vlrParcelas.valor) {
				return true;
			}
			
			if(voSemAlteracao.diaDebito != diaDebito.valor) {
				return true;
			}
			
			if(tipoInteg.selectedItem != null) {
				var codTipoInteg:Number = new Number(tipoInteg.selectedItem.codListaItem);
				if(voSemAlteracao.tipoInteg != codTipoInteg) {
					return true;
				}
			}
			
			return false;
		}
		
		private function initDocumento(documentos:ArrayCollection):void {
			initUploadDocumento();
			grid.dataProvider = documentos;
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
		}

		private function verificarExibicaoGrid():void {
			var documentos:ArrayCollection = this.grid.dataProvider as ArrayCollection;
			var parentBox:HBoxBancoob = grid.parent as HBoxBancoob;
			var deveExibir:Boolean = (documentos != null && documentos.length > 0);
			parentBox.visible = deveExibir;
			parentBox.includeInLayout = deveExibir;
			if (deveExibir) {
				grid.height = 25 + (documentos.length * 27);
			}
		}
		
		private function valorChaveGED():ArrayCollection {
			var listValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = new Object();
			//			objChaveDoc.siglaTipoDocumento = "FICHAPROPMATRICULA";
			objChaveDoc.siglaChaveDocumento = "NUM.MAT.CONT.CAPITAL";
			objChaveDoc.valorChave = this.vo.numContaCapital;
			listValoresChaves.addItem(objChaveDoc);
			return listValoresChaves;
		}
		
		private function uploadCompleteGED(event:Event):void {
			var listaDocumentosSalvos:Array = (event as Object).itemsFileSucess as Array;
			
			var docs:Array = new Array();	
			var tiposDeArquivo:Array = (upLoadDocGed['cbTiposDocs'] as Combo).dataProvider.source;
			
			for(var i:uint = 0; i < listaDocumentosSalvos.length; i++) {
				var doc:Object = new Object();
				doc.idDocumento = listaDocumentosSalvos[i].idDocumento;

				for(var j:int = 0; j< tiposDeArquivo.length; j++){
					if(tiposDeArquivo[j].idTipoDocumento == listaDocumentosSalvos[i].idTipoDocumento){
						doc.siglaTipoDocumento = tiposDeArquivo[j].siglaTipoDocumento;
					}
				}
				
				docs.push(doc);
			}
			
			reqDTO.dados.docs = docs;
			reqDTO.dados.vo = this.vo;
			
			servico.vincularDocumentos(reqDTO);
		}
		
		private function retornoVincularDocumentos(evt:ResultEvent):void {
			if (grid.dataProvider == null) {
				grid.dataProvider = evt.result.dados["documentos"];
			} else {
				(grid.dataProvider as ArrayCollection).addAll(evt.result.dados["documentos"]);
			}
			
			MostraCursor.removeBusyCursor();
			initUploadDocumento();
		}
		
		private function retornoVincularDocumentosError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			obterDefinicoesAlterar();
		}
		
		public function excluirDocumento(documentoVO:DocumentoCapitalVO):void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.documentoVO = documentoVO;	
			servico.excluirDocumento(reqDTO);
		}
		
		private function retornoExcluirDocumento(evt:ResultEvent):void {
			var documentos:ArrayCollection = this.grid.dataProvider as ArrayCollection;
			if (documentos != null) {
				for (var i:int = 0; i<documentos.length; i++) {
					if (documentos[i].idDocumento == evt.result.dados['idDocumentoExcluido']) {
						documentos.removeItemAt(i);
					}
				}
			}
			
			MostraCursor.removeBusyCursor();
			
			Alerta.show("Arquivo removido com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO);
		}
		
		private function retornoExcluirDocumentoError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			obterDefinicoesAlterar();
		}
		
		private function fecharJanelaMouseEvt(evt:MouseEvent):void {
			this.fecharJanela();
		}
		
		override public function fecharJanela():void {
			dispatchEvent(new Event("atualizarRegistrosConsulta", true));
			super.fecharJanela();
		}

		public function setTabSelecionada(numTab:int):void{
			this.tabSelecionada = numTab;
		}
	}
}