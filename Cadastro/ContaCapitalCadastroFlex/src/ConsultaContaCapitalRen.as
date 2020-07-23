package
{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.cadastro.contacapital.ConsultaContaCapitalRenView;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	public class ConsultaContaCapitalRen extends ConsultaContaCapitalRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.ConsultaContaCapitalRenServico";
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		private var vo:CadastroContaCapitalRenVO;
		private var voSelecionado:CadastroContaCapitalRenVO;
		
		private var listaVO:ArrayCollection = new ArrayCollection();
		
		public function ConsultaContaCapitalRen() {
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
			cboSituacaoCadastro.dataProvider = event.result.dados["cboSituacaoCadastro"] as ArrayCollection;
			cboTipoPesquisa.dataProvider = event.result.dados["cboTipoPesquisa"] as ArrayCollection;
			cboTipoPesquisa.selectedIndex = 2;
			
			controlarTela();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			servico.consultar.addEventListener(ResultEvent.RESULT, retornoConsultar);
			servico.consultar.addEventListener(FaultEvent.FAULT, retornoConsultarError);
			servico.excluir.addEventListener(ResultEvent.RESULT, retornoExcluir);
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btConsultar.addEventListener(MouseEvent.CLICK, consultar);
			btIncluir.addEventListener(MouseEvent.CLICK, incluir);
			btAlterar.addEventListener(MouseEvent.CLICK, alterar);
			btVisualizar.addEventListener(MouseEvent.CLICK, visualizar);
			btExcluir.addEventListener(MouseEvent.CLICK, confirmExcluir);
			
			cboTipoPesquisa.addEventListener(Event.CHANGE, onChangeTipoPesquisa);
			
			grid.addEventListener(KeyboardEvent.KEY_DOWN, definirSelecionadoKeyDown);			
			grid.addEventListener(MouseEvent.CLICK, definirSelecionadoMouseEvent);
			grid.addEventListener(MouseEvent.DOUBLE_CLICK, definirSelecionadoMouseEvent);
			
			//Default da pesquisa: CPF/CNPJ
			valorTipoPeqsuisa.restrict = "0-9";
			valorTipoPeqsuisa.maxChars = 14;
			focusManager.setFocus(valorTipoPeqsuisa);
			valorTipoPeqsuisa.addEventListener(KeyboardEvent.KEY_DOWN, configurarAtalho);
		}
		
		private function definirSelecionadoKeyDown(evt:KeyboardEvent):void {
			if(evt.keyCode == 13 && grid.selectedItem != null) {
				voSelecionado = grid.selectedItem as CadastroContaCapitalRenVO;
				btAlterar.enabled = true;
				btVisualizar.enabled = true;
			}	
		}
		
		private function definirSelecionadoMouseEvent(evt:MouseEvent):void {
			if(grid.selectedItem != null) {
				voSelecionado = grid.selectedItem as CadastroContaCapitalRenVO;
				btAlterar.enabled = true;
				btVisualizar.enabled = true;
				btExcluir.visible = voSelecionado.permissaoExcluir as Boolean;
				btExcluir.includeInLayout = voSelecionado.permissaoExcluir as Boolean;
			}
		}
		
		private function retornoConsultar(evt:ResultEvent):void {
			listaVO = evt.result.dados["registros"] as ArrayCollection;
			grid.dataProvider = listaVO;
			grid.labelFunction = formataDataGrid;
			
			btAlterar.enabled = false;
			btVisualizar.enabled = false;
			
			MostraCursor.removeBusyCursor();
			
			if(!evt.result.dados["registros"]) {
				Alerta.show("Não existem dados cadastrados para os filtros informados.", "ATENÇÃO", Alerta.ALERTA_OK);
			}
		}
		
		private function retornoConsultarError(evt:FaultEvent):void {
			if(evt.fault.faultCode == "Server.Security.SessionExpired") {
				PortalModel.getInstance().tratarSessaoExpirada();
				return;
			}
				
			Alerta.show("Erro ao consultar os dados", "ERRO", Alerta.ALERTA_ERRO);
		}
		
		private function incluir(evt:MouseEvent = null):void {
			var cadastro:CadastroContaCapitalRen = new CadastroContaCapitalRen();
			cadastro.destino = this.destino;
			var telaCadastro:Janela = new Janela();
			telaCadastro.title = "CADASTRAR CONTA CAPITAL";
			telaCadastro.removeAllChildren();
			telaCadastro.addChild(cadastro);
			telaCadastro.abrir(this, true, true);
		}
		
		private function alterar(evt:MouseEvent):void {
			var cadastro:AlteraCadastroContaCapitalRen = new AlteraCadastroContaCapitalRen(voSelecionado);
			cadastro.destino = this.destino;
			var telaCadastro:Janela = new Janela();
			telaCadastro.title = "ALTERAR CONTA CAPITAL";
			telaCadastro.removeAllChildren();
			telaCadastro.addChild(cadastro);
			telaCadastro.abrir(this, true, true);
			
			telaCadastro.addEventListener("atualizarRegistrosConsulta", atualizarRegistrosConsulta);
		}
		
		private function atualizarRegistrosConsulta(evt:Event):void {
			this.servico.consultar(reqDTO);
		}
		
		private function confirmExcluir(evt:MouseEvent):void {
			Alerta.show("Deseja excluir o registro? Esta ação não poderá ser desfeita.", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, excluir);
		}
		
		private function excluir(evt:MouseEvent):void {
			reqDTO.dados.idContaCapital = (grid.selectedItem as CadastroContaCapitalRenVO).idContaCapital;
			this.servico.excluir(reqDTO);
		}
		
		private function retornoExcluir(event:ResultEvent):void {
			Alerta.show("Exclusão realizada com sucesso!", "SUCESSO", Alerta.ALERTA_SUCESSO, null, consultar);
			
			btExcluir.visible = false;
			btExcluir.includeInLayout = false;
		}
		
		private function visualizar(evt:MouseEvent):void {
			var cadastro:VisualizaCadastroContaCapitalRen = new VisualizaCadastroContaCapitalRen(voSelecionado);
			cadastro.destino = this.destino;
			var telaCadastro:Janela = new Janela();
			telaCadastro.title = "VISUALIZAR CONTA CAPITAL";
			telaCadastro.removeAllChildren();
			telaCadastro.addChild(cadastro);
			telaCadastro.abrir(this, true, true);
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			switch(col.dataField) {
				
				case "cpfCnpj":
					if (obj[col.dataField]) {
						retorno = FormatUtil.formataCPFCNPJ(obj[col.dataField]);
					} else {
						retorno = "";						
					}
				break;
				
				default:
					if(obj[col.dataField] == null) {
						retorno = "";
					} else {
						retorno = obj[col.dataField].toString();
					}
				break;
			
			}                                       
			return retorno;
		}
		
		private function controlarTela():void {
			compNivelInst.cmbCentral.percentWidth = new Number(100);
			compNivelInst.rtlCentral.width = new Number(100);
			
			compNivelInst.cmbSingular.percentWidth = new Number(100);
			compNivelInst.rtlSingular.width = new Number(100);
			
			btAlterar.enabled = false;
			btVisualizar.enabled = false;
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
		}
		
		private function consultar(evt:MouseEvent):void {
			if(validar()) {
				this.reqDTO.dados.vo = carregarVO();
				this.servico.consultar(reqDTO);
			}
		}
		
		/**
		 * Carrega valores da camada de apresentacao para o VO
		 */
		private function carregarVO():CadastroContaCapitalRenVO {
			this.vo = new CadastroContaCapitalRenVO(); 
			
			this.vo.idInstituicao = new Number(compNivelInst.cmbSingular.selectedItem.codListaItem);
			this.vo.idSituacaoCadastro = cboSituacaoCadastro.selectedItem.codListaItem;
			
			if(cboTipoPesquisa.selectedItem.codListaItem == 2) {
				this.vo.numContaCapital = new Number(valorTipoPeqsuisa.text);
			}
			
			if(cboTipoPesquisa.selectedItem.codListaItem == 3) {
				this.vo.cpfCnpj = valorTipoPeqsuisa.text;
			}
			
			if(cboTipoPesquisa.selectedItem.codListaItem == 4) {
				this.vo.nomeCompleto = valorTipoPeqsuisa.text;
			}
			
			return vo;
		}
		
		private function onChangeTipoPesquisa(evt:Event):void {
			var tipoPesquisa:int = (evt.currentTarget as Combo).selectedItem["codListaItem"];
			
			valorTipoPeqsuisa.text = "";
			
			if(tipoPesquisa > 1) {
				valorTipoPeqsuisa.visible = true;
			}
			
			if(tipoPesquisa == 2) {
				valorTipoPeqsuisa.restrict = "0-9";
				valorTipoPeqsuisa.maxChars = 8;
			}
			
			if(tipoPesquisa == 3) {
				valorTipoPeqsuisa.restrict = "0-9";
				valorTipoPeqsuisa.maxChars = 14;
			}
			
			if(tipoPesquisa == 4) {
				valorTipoPeqsuisa.restrict = "^'";
				valorTipoPeqsuisa.maxChars = 50;
			}
		}
		
		/**
		 * Valida campos de acordo com regras de negocio
		 */
		private function validar():Boolean {
			if(!instituicaoCentralPreenchida()) {
				Alerta.show("Instituição Resp deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbCentral);
				return false;
			}
			
			if(!instituicaoSingularPreenchida()) {
				Alerta.show("Instituição deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbSingular);
				return false;
			}
			
			if(cboSituacaoCadastro.selectedItem["codListaItem"] == "2" || cboSituacaoCadastro.selectedItem["codListaItem"] == "-1") {
				
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "1") {
					Alerta.show("Tipo Pesquisa deve ser preenchido.", "ATENÇÃO", Alerta.ALERTA_OK, cboTipoPesquisa);
					return false;
				}
				
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "2" && valorTipoPeqsuisa.text == "") {
					Alerta.show("Conta Capital deve ser preenchida.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
					return false;
				}
				
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "3" && valorTipoPeqsuisa.text == "") {
					Alerta.show("CPF/CNPJ deve ser preenchido.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
					return false;
				}
			}
			
			if(cboTipoPesquisa.selectedItem["codListaItem"] == "4" && valorTipoPeqsuisa.length < 3) {
				Alerta.show("Informe pelo menos 3 letras para o Nome", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPeqsuisa);
				return false;
			}
			
			return true;
		}
		
		private function configurarAtalho(event:KeyboardEvent):void {
			if(event.charCode == 13) {
				consultar(null);
			}
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();			
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