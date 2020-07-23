package br.com.sicoob.sisbr.cca.cadastro.plataforma {
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.HBoxBancoob;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IEdicaoPlataformaAtendimento;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	import br.com.sicoob.sisbr.cca.vo.DocumentoCapitalVO;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.FabricaUploadDocumentoGED;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.IUploadDocumentoGED;
	import br.com.sicoob.sisbr.ged.uploadDocumentos.ParametroUploadDocumentoGED;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO", DocumentoCapitalVO);
	public class CadastroContaCapitalEdicao extends CadastroContaCapitalEdicaoView implements IEdicaoPlataformaAtendimento {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.CadastroContaCapitalRenServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelFichaPropostaMatriculaServico";
		
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		private var factory:FabricaUploadDocumentoGED = new FabricaUploadDocumentoGED();
		private var upLoadDocGed:IUploadDocumentoGED;
		
		private var pessoaSelecionada:PessoaPlataformaVO;
		private var voSemAlteracao:CadastroContaCapitalRenVO;
		private var numContaCapitalGerada:Number;
		private var permiteAlterarMatricula:Boolean = false;
		private var _isEdicao:Boolean = false;
		public var destinoParent:DestinoVO;		
		
		[Bindable]
		public var vo:CadastroContaCapitalRenVO = new CadastroContaCapitalRenVO();
		
		public function CadastroContaCapitalEdicao() {
			super();			
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void {
			
			
			
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			controlarEventos();
			
			
			
			
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(destinoParent);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			controlarServico();
		}
		
		private function controlarServico():void {
			servico.obterNovoNumContaCapital.addEventListener(ResultEvent.RESULT, retornoObterNovoNumContaCapital);
			
			servico.obterDefinicoesAlterar.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesAlterar);
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			
			servico.vincularDocumentos.addEventListener(ResultEvent.RESULT, retornoVincularDocumentos);
			servico.vincularDocumentos.addEventListener(FaultEvent.FAULT, retornoVincularDocumentosError);
			servico.excluirDocumento.addEventListener(ResultEvent.RESULT, retornoExcluirDocumento);
			servico.excluirDocumento.addEventListener(FaultEvent.FAULT, retornoExcluirDocumentoError);
			
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);
			servico.incluir.addEventListener(FaultEvent.FAULT, retornoIncluirError);
			
			servico.alterar.addEventListener(ResultEvent.RESULT, retornoAlterar);
		}
		
		private function controlarEventos():void {
			vlrSubs.addEventListener(Event.CHANGE, calcularValorParcela);
			vlrInteg.addEventListener(Event.CHANGE, calcularValorParcela);
			qtdParcelas.addEventListener(Event.CHANGE, calcularValorParcela);
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Recuperando registros...", this);
			servico.source = SERVICO_SOURCE;
			
			reqDTO.dados.idInstituicao = pessoaSelecionada.idInstituicao;
			reqDTO.dados.idPessoaSelecionada = pessoaSelecionada.idPessoa;
			
			servico.obterDefinicoes(reqDTO);
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			dataInclusao.text = event.result.dados["dataInclusao"];
			permiteAlterarMatricula = event.result.dados["permiteAlterarMatricula"];
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			numContaCapital.text = event.result.dados["numContaCapitalGerada"];
			numContaCapitalGerada = event.result.dados["numContaCapitalGerada"];
			pessoa.text = FormatUtil.formataCPFCNPJ(this.pessoaSelecionada.cpfCnpj) + " " + this.pessoaSelecionada.nomeCompleto;
			
			vlrSubs.valor = event.result.dados["vlrSubs"];
			vlrInteg.valor = event.result.dados["vlrInteg"];
			
			this.isEdicao = false;
			
			numContaCapital.enabled = this.permiteAlterarMatricula;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
		}
		
		private function retornoObterNovoNumContaCapital(evt:ResultEvent):void {
			if(evt.result.dados["numContaCapitalGerada"]) {
				numContaCapital.text = evt.result.dados["numContaCapitalGerada"];
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		public function carregarRegistro(registro:Object):void {
			MostraCursor.setBusyCursor("Obtendo definições... ", Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.vo = registro;
			servico.source = SERVICO_SOURCE;
			servico.obterDefinicoesAlterar(dto);
		}
		
		private function retornoObterDefinicoesAlterar(event:ResultEvent):void {
			this.voSemAlteracao = event.result.dados["vo"];
			
			this.vo = event.result.dados["vo"];
			
			dataInclusao.text = this.vo.dataInclusao;
			numContaCapital.text = this.vo.numContaCapital.toString();
			pessoa.text = FormatUtil.formataCPFCNPJ(this.vo.cpfCnpj) + " " + this.vo.nomePessoa;
			
			vlrSubs.valor = this.vo.vlrSubs;
			vlrInteg.valor = this.vo.vlrInteg;
			qtdParcelas.valor = this.vo.qtdParcelas;
			vlrParcelas.valor = this.vo.vlrParcelas;
			diaDebito.valor = this.vo.diaDebito;
			
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			tipoInteg.procuraItemPorNome(this.vo.tipoInteg.toString(), "codListaItem");
			
			this.isEdicao = true;
			
			controlarTela();
			
			initDocumento(event.result.dados["documentos"]);
			
			MostraCursor.removeBusyCursor();
			
			servico.alterar.addEventListener(ResultEvent.RESULT, retornoAlterar);
			
			numContaCapital.enabled = false;
			tabNav.selectedIndex = 0;
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
		}
		
		private function retornoAlterar(evt:ResultEvent):void {
			if(evt.result.dados["erroNegocial"]) {
				Alerta.show(evt.result.dados["msg"], "ALERTA", Alerta.ALERTA_ERRO);
				MostraCursor.removeBusyCursor();
				return;
			}
			
			this.vo = evt.result.dados["vo"];
			
			Alerta.show(evt.result.dados["msg"], "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}
		
		private function controlarTela():void {
			//Permite alterar campos da tela quando status da proposta for 'aguardando aprovacao' ou 'devolvida' 
			var permiteAlterar:Boolean = this.vo.idSituacaoCadastro == 1 || this.vo.idSituacaoCadastro == 4;
			vlrSubs.enabled = permiteAlterar;
			vlrInteg.enabled = permiteAlterar;
			qtdParcelas.enabled = permiteAlterar;
			diaDebito.enabled = permiteAlterar;
			
			if(!permiteAlterar) {
				var itemSelecionado:Object = tipoInteg.selectedItem;
				tipoInteg.dataProvider.removeAll();
				tipoInteg.dataProvider.addItem(itemSelecionado);
			}
			
			//Permite incluir/alterar documentos quando status da proposta for 'aprovado', 'rejeitada' ou aprovado c/ pend doc
			//			tabDocumentos.enabled = this.vo.idSituacaoCadastro == 2 || this.vo.idSituacaoCadastro == 3 || this.vo.idSituacaoCadastro == 5;
			//			if(this.vo.idSituacaoContaCapital != 1) {
			//				tabDocumentos.enabled = false;
			//			}
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
			reqDTO.dados.siglaTipoDocumento = (upLoadDocGed['cbTiposDocs'] as Combo).selectedItem.siglaTipoDocumento;																											
			
			servico.vincularDocumentos(reqDTO);
		}
		
		private function retornoVincularDocumentos(evt:ResultEvent):void {
			if (grid.dataProvider == null) {
				grid.dataProvider = evt.result.dados["documentos"];
			} else {
				(grid.dataProvider as ArrayCollection).addAll(evt.result.dados["documentos"]);
			}
			
			//			(upLoadDocGed as Object).visible = false;

			initUploadDocumento();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoVincularDocumentosError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
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
			//			grid.dataProvider = null;
			//			grid.parent.visible = false;
			initUploadDocumento();
			
			//			(upLoadDocGed as Object).visible = true;
			
			MostraCursor.removeBusyCursor();
			
			Alerta.show("Arquivo removido com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO);
		}		   
		
		private function retornoExcluirDocumentoError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
		}
		
		public function preencherCampos():void {
			
		}
		
		public function carregarDefinicoes(obj:Object = null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
		}
		
		public function novoRegistro():void	{
			MostraCursor.setBusyCursor("Obtendo definições... ", Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			reqDTO.dados.idInstituicao = pessoaSelecionada.idInstituicao;
			reqDTO.dados.idPessoaSelecionada = pessoaSelecionada.idPessoa;
			servico.obterDefinicoes(reqDTO);
		}
		
		public function gravarRegistro():void {
			if(validar()) {
				
				reqDTO.dados.vo = carregarVO();
				
				if(this.vo.idContaCapital > 0 && this._isEdicao) {
					
					reqDTO.dados.isDadosPropostaAlterados = true; //isDadosPropostaAlterados();
					if (vo.idSituacaoCadastro == 3 || vo.idSituacaoCadastro == 4) { // 3-rejeitado/4-devolvido
						var descSituacao:String = (vo.idSituacaoCadastro == 3) ? "Rejeitado" : "Devolvido";
						Alerta.show("Esta proposta encontra-se na situação "+descSituacao+". Deseja reencaminhar para aprovação? Caso negativo, as alterações serão gravadas mas manterá a situação atual.",
							"Reencaminhar proposta", Alerta.ALERTA_PERGUNTA, null, confirmarAlterarComSituacao, confirmarAlterarSemSituacao);
					} else {
						this.servico.alterar(reqDTO);
					}
					
				} else {
					
					this.servico.incluir(reqDTO);
				}
			}
		}
		
		private function confirmarAlterarComSituacao(evt:Event):void {
			reqDTO.dados.atualizarSituacaoCadastro = true;
			this.servico.alterar(reqDTO);
		}
		
		private function confirmarAlterarSemSituacao(evt:Event):void {
			reqDTO.dados.atualizarSituacaoCadastro = false;
			this.servico.alterar(reqDTO);
		}
		
		private function retornoIncluir(evt:ResultEvent):void {
			if(evt.result.dados["numContaCapitalGerada"]) {
				numContaCapital.text = evt.result.dados["numContaCapitalGerada"];
				numContaCapitalGerada = evt.result.dados["numContaCapitalGerada"];
				tabNav.selectedIndex = 0;
				Alerta.show(evt.result.dados["msg"], "ERRO", Alerta.ALERTA_ERRO, numContaCapital);
				MostraCursor.removeBusyCursor();
				return;
			}
			
			if(evt.result.dados["erroNegocial"]) {
				Alerta.show(evt.result.dados["msg"], "ERRO", Alerta.ALERTA_ERRO);
				tabNav.selectedIndex = 0;
				MostraCursor.removeBusyCursor();
				return;
			}
			
			this.vo = evt.result.dados["vo"];
			
			Alerta.show(evt.result.dados["msg"], "SUCESSO", Alerta.ALERTA_SUCESSO, null, emitirFichaMatricula);
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}
		
		private function retornoIncluirError(evt:FaultEvent):void {
			if(evt.fault.faultCode == "Server.Security.SessionExpired") {
				PortalModel.getInstance().tratarSessaoExpirada();
				return;
			}
			
			Alerta.show("Erro ao gravar os dados.", "ERRO", Alerta.ALERTA_ERRO);
		}
		
		private function emitirFichaMatricula(evt:Event):void {
			Alerta.show("Deseja emitir a ficha proposta de matricula?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, gerarRelatorio);
		}
		
		private function gerarRelatorio(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;
			
			//			var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
			var dtoRel:ParametroDTO = new ParametroDTO();
			dtoRel.dados.idInstituicao = vo.idInstituicao;
			dtoRel.dados.idContaCapital = vo.idContaCapital;
			dtoRel.dados.idPessoa = vo.idPessoa;
			
			//			RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirRelatorioFichaPropostaMatricula",
			//				SERVICO_REL_SOURCE, dtoRel, "RelFichaPropostaMatricula", destinoVO,"Emitindo relatório",
			//				formatoRelatorio, exibirPreImpressao);
			
			RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelFichaPropostaMatriculaServicoRemote", 
				dtoRel, "CCA_RelFichaPropostaMatricula", this.destino, "Emitindo relatório...", null, false);
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
			vo.tipoInteg = new Number(tipoInteg.selectedItem.codListaItem);
			vo.numContaCapital = numContaCapital.valor;
			vo.numContaCapitalGerada = numContaCapitalGerada;
			vo.idInstituicao = pessoaSelecionada.idInstituicao;
			vo.idPessoa = pessoaSelecionada.idPessoa;
			return vo;
		}
		
		/**
		 * Valida campos de acordo com regras de negocio
		 */
		private function validar():Boolean {
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
				//Retirado o qtdParcelas.valor > 100 a pedido do Dr Augusto/Credimep e Vilaça 
				/*
				if(qtdParcelas.valor <= 0 || qtdParcelas.valor > 100) {
				Alerta.show("Quant. Parcelas deve estar entre 1 e 100.", "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
				return false;
				}
				*/
				if(qtdParcelas.valor <= 0) {
					Alerta.show("Quant. Parcelas deve ser maior que 0.", "ATENÇÃO", Alerta.ALERTA_OK, qtdParcelas);
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
			
			var codTipoInteg:Number = new Number(tipoInteg.selectedItem.codListaItem);
			if(voSemAlteracao.tipoInteg != codTipoInteg) {
				return true;
			}
			
			return false;
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
		
		public function atualizarCamposRegistro():void {
		}
		
		public function restaurarRegistro():void {
		}
		
		public function excluirRegistro(obj:Object):void { }
		
		public function verificarAlteracoes():Boolean {
			return false;
		}
		
		public function get isEdicao():Boolean {
			return _isEdicao;
		}
		
		public function set isEdicao(value:Boolean):void {
			_isEdicao = value;
		}
	}
}