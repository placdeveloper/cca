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
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.ProcurarPessoaExternoCAPES;
	import br.com.sicoob.sisbr.cca.cadastro.contacapital.CadastroContaCapitalRenView;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	public class CadastroContaCapitalRen extends CadastroContaCapitalRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.CadastroContaCapitalRenServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelFichaPropostaMatriculaServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var numContaCapitalGerada:Number;
		private var permiteAlterarMatricula:Boolean = false;
		private var vo:CadastroContaCapitalRenVO = new CadastroContaCapitalRenVO();
		private var tipoGrauCoopetativa:int;
		
		public function CadastroContaCapitalRen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
			
			controlarServico();
			
			controlarEventos();	
		}
		
		private function configurarServico():void {
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
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			dataInclusao.text = event.result.dados["dataInclusao"];
			permiteAlterarMatricula = event.result.dados["permiteAlterarMatricula"];
			tipoInteg.dataProvider = event.result.dados["comboTipoInteg"] as ArrayCollection;
			numContaCapital.text = event.result.dados["numContaCapitalGerada"];
			numContaCapitalGerada = event.result.dados["numContaCapitalGerada"];
			
			vlrSubs.valor = event.result.dados["vlrSubs"];
			vlrInteg.valor = event.result.dados["vlrInteg"];
			
			
			tipoGrauCoopetativa = event.result.dados["tipoGrauCoopetativa"];
			
			controlarTela();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			
			servico.incluir.addEventListener(ResultEvent.RESULT, retornoIncluir);
			servico.incluir.addEventListener(FaultEvent.FAULT, retornoIncluirError);
			
			servico.obterNumeroCooperativa.addEventListener(ResultEvent.RESULT, retornoObterNumeroCooperativa);
			servico.obterNumeroCooperativa.addEventListener(FaultEvent.FAULT, retornoObterNumeroCooperativaError);
			
			servico.obterNovoNumContaCapital.addEventListener(ResultEvent.RESULT, retornoObterNovoNumContaCapital);
		}
		
		private function controlarEventos():void {
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, incluir);
			
			compNivelInst.cmbSingular.addEventListener(Event.CHANGE, onChangeCmbSingular);
			compNivelInst.cmbCentral.addEventListener(Event.CHANGE, onChangeCmbCentral);
			
			vlrSubs.addEventListener(Event.CHANGE, calcularValorParcela);
			vlrInteg.addEventListener(Event.CHANGE, calcularValorParcela);
			qtdParcelas.addEventListener(Event.CHANGE, calcularValorParcela);
			
			compCapes.addEventListener(ProcurarPessoaExternoCAPES.EVENTO_REGISTRO_PREENCHIDO, carregarDadosProposta);
		}
		
		private function carregarDadosProposta(evt:Event):void {
			var capes:ProcurarPessoaExternoCAPES = evt.currentTarget as ProcurarPessoaExternoCAPES;
			if(compNivelInst.cmbSingular.selectedItem != null && capes.obterPessoa().idPessoa > 0) {
				var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
				reqDTO.dados.idPessoaSelecionada = capes.obterPessoa().idPessoa; 
				reqDTO.dados.idInstituicao = new Number(compNivelInst.cmbSingular.selectedItem.codListaItem);
				servico.obterDefinicoes(reqDTO);
			}
		} 
		
		private function controlarTela():void {
			compNivelInst.cmbCentral.percentWidth = new Number(100);
			compNivelInst.cmbCentral.editable = false;
			
			compNivelInst.cmbSingular.percentWidth = new Number(100);
			compNivelInst.cmbSingular.editable = false;
			
			numContaCapital.enabled = this.permiteAlterarMatricula;
			
			compCapes.enabled = instituicaoSingularPreenchida();
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
		}
		
		private function incluir(evt:MouseEvent):void {
			if(validar()) {
				reqDTO.dados.vo = carregarVO();
				this.servico.incluir(reqDTO);
			}
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
		}
		
		private function retornoIncluirError(evt:FaultEvent):void {
			if(evt.fault.faultCode == "Server.Security.SessionExpired") {
				PortalModel.getInstance().tratarSessaoExpirada();
				return;
			}
			
			Alerta.show("Erro ao gravar os dados.", "ERRO", Alerta.ALERTA_ERRO);
		}
		
		private function emitirFichaMatricula(evt:Event):void {
			Alerta.show("Deseja emitir a ficha proposta de matricula?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, gerarRelatorio, naoGerarRelatorio);
		}
		
		private function gerarRelatorio(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;
			var dtoRel:ParametroDTO = new ParametroDTO();
			
			dtoRel.dados.idInstituicao = vo.idInstituicao;
			dtoRel.dados.idContaCapital = vo.idContaCapital;
			dtoRel.dados.idPessoa = vo.idPessoa;
			
			RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelFichaPropostaMatriculaServicoRemote", 
				dtoRel, "CCA_RelFichaPropostaMatricula", this.destino, "Emitindo relatório...", null, false);
			
			cancelar(evt);
		}
		
		private function naoGerarRelatorio(evt:MouseEvent):void {
			cancelar(evt);
		}
		
		/**
		 * Carrega valores da camada de apresentacao para o VO
		 */
		private function carregarVO():CadastroContaCapitalRenVO {
			vo.idInstituicao = new Number(compNivelInst.cmbSingular.selectedItem.codListaItem);
			vo.idPessoa = compCapes.obterPessoa().idPessoa;
			vo.vlrSubs = vlrSubs.valor;
			vo.vlrInteg = vlrInteg.valor;
			vo.qtdParcelas = qtdParcelas.valor;
			vo.vlrParcelas = vlrParcelas.valor;
			vo.diaDebito = diaDebito.valor;
			vo.tipoInteg = new Number(tipoInteg.selectedItem.codListaItem);
			vo.numContaCapital = numContaCapital.valor;
			vo.numContaCapitalGerada = numContaCapitalGerada;
			return vo;
		}
		
		/**
		 * Valida campos de acordo com regras de negocio
		 */
		private function validar():Boolean {
			if(!instituicaoCentralPreenchida()) {
				tabNav.selectedIndex = 0;
				Alerta.show("Instituição Resp deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbCentral);
				return false;
			}
			
			if(!instituicaoSingularPreenchida()) {
				tabNav.selectedIndex = 0;
				Alerta.show("Instituição deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbSingular);
				return false;
			}
			
			if(!compCapes.realizarValidacao().valido) {
				tabNav.selectedIndex = 0;
				Alerta.show(compCapes.realizarValidacao().mensagens[0], "ATENÇÃO", Alerta.ALERTA_OK, compCapes.botaoProcurar);
				return false;
			}
			
			if(numContaCapital.valor <= 0) {
				tabNav.selectedIndex = 0;
				Alerta.show("Conta Capital deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, numContaCapital);
				return false;
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
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			if(validarFechar()) {
				super.fecharJanela();
			} else {
				Alerta.show("Informações não foram salvas! Deseja realmente sair?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, confirmarFechar);
			}
		}
		
		private function confirmarFechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		/**
		 * Verifica se os dados originais foram alterados
		 */
		private function validarFechar():Boolean {
			if(vlrSubs.valor > 0) {
				return false;
			}
			
			if(vlrInteg.valor > 0) {
				return false;
			}
			
			if(qtdParcelas.valor > 0) {
				return false;
			}
			
			if(vlrParcelas.valor > 0) {
				return false;
			}
			
			if(diaDebito.valor > 0) {
				return false;
			}
			
			var codTipoInteg:Number = new Number(tipoInteg.selectedItem.codListaItem);
			if(codTipoInteg > 1) {
				return false;
			}
			
			if(compCapes.campoCodigo.valor > 0) {
				return false;
			}
			
			return true;
		}
		
		/**
		 * Retorna os valores da camada de apresentacao para o estado inicial 
		 */
		private function cancelar(evt:MouseEvent):void {
			var novoCadastro:CadastroContaCapitalRen = new CadastroContaCapitalRen();
			novoCadastro.destino = this.destino;
			this.removeAllChildren();
			this.addChild(novoCadastro as DisplayObject);
		}
		
		private function onChangeCmbSingular(evt:Event):void {
			vlrSubs.valor = 0;
			vlrInteg.valor = 0;
			
			if(evt.currentTarget.selectedIndex > 0) {
				reqDTO.dados.idInstituicao = evt.currentTarget.selectedItem.codListaItem;
				servico.obterNumeroCooperativa(reqDTO);
			} else {
				compCapes.enabled = instituicaoSingularPreenchida();
			}
			
			controlarTela();
		}
		
		private function onChangeCmbCentral(evt:Event):void {
			controlarTela();
			compCapes.enabled = false;
		}
		
		private function retornoObterNumeroCooperativa(evt:ResultEvent):void {
			
			if(!evt.result.dados["numCoop"]) {
				Alerta.show("Erro ao obter numero da cooperativa: " + evt.result.dados["numCoop"], "ERRO", Alerta.ALERTA_ERRO);
				return;
			}
			
			compCapes.limpar();
			compCapes.numeroCooperativa = new Number(evt.result.dados["numCoop"]);
			
			this.numContaCapital.text = "";
			this.numContaCapitalGerada = null;
			
			this.permiteAlterarMatricula = evt.result.dados["permiteAlterarMatricula"];
			
			controlarTela();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoObterNumeroCooperativaError(evt:FaultEvent):void {
			if(evt.fault.faultCode == "Server.Security.SessionExpired") {
				PortalModel.getInstance().tratarSessaoExpirada();
				return;
			}
			
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
			
			controlarTela();
			
			this.permiteAlterarMatricula = false;
			
			compCapes.limpar();
		}
		
		private function retornoObterNovoNumContaCapital(evt:ResultEvent):void {
			if(evt.result.dados["numContaCapitalGerada"]) {
				numContaCapital.text = evt.result.dados["numContaCapitalGerada"];
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function instituicaoSingularPreenchida():Boolean {
			if(tipoGrauCoopetativa == 2) {
				return true;
			}
			
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