package
{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.formatters.CurrencyFormatter;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.DateTimeEntity;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.movimentacao.bloqueio.DesbloquearCapital;
	import br.com.sicoob.sisbr.cca.movimentacao.bloqueio.IncluirBloqueio;
	import br.com.sicoob.sisbr.cca.movimentacao.bloqueio.MovimentacaoBloqueioView;
	import br.com.sicoob.sisbr.cca.util.DataUtilRelatorios;
	import br.com.sicoob.sisbr.cca.vo.BloqueioContaCapitalVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO", BloqueioContaCapitalVO);
	public class MovimentacaoBloqueio extends MovimentacaoBloqueioView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.BloqueioContaCapitalServico";
		
		private var servico:ServicoJava = new ServicoJava();
		private var cf:CurrencyFormatter;
		
		private var vo:BloqueioContaCapitalVO;
		private var voSelecionado:BloqueioContaCapitalVO;
		
		private var listaVO:ArrayCollection = new ArrayCollection();
		
		private var bolRecarregarConsulta:Boolean = false;
		
		public function MovimentacaoBloqueio() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			inicializarServico();
			controlarServico();
			controlarEventos();
			obterDefinicoes();
		}
		
		private function inicializarServico():void {
			servico.configurarDestino(this.destino);
			servico.source = SERVICO_SOURCE;
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;			
		}
		
		private function obterDefinicoes():void {
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			formatoMonetario();
			cboTipoPesquisa.dataProvider = event.result.dados["cboTipoPesquisa"] as ArrayCollection;
			cboTipoPesquisa.selectedIndex = 0;
			cboSituacaoBloqueio.dataProvider = event.result.dados["cboSituacaoBloqueio"] as ArrayCollection;
			cboTipoBloqueio.dataProvider = event.result.dados["cboTipoBloqueio"] as ArrayCollection;
			
			controlarTela();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function formatoMonetario():void {
			cf = new CurrencyFormatter();
			cf.precision="2";
			cf.rounding="none";
			cf.decimalSeparatorTo=",";
			cf.thousandsSeparatorTo=".";
			cf.useThousandsSeparator="true";
			cf.useNegativeSign="true";
			cf.currencySymbol="R$ ";
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			servico.consultar.addEventListener(ResultEvent.RESULT, retornoConsultar);
			servico.consultar.addEventListener(FaultEvent.FAULT, retornoConsultarError);
		}
		
		private function controlarEventos():void {
			btConsultar.addEventListener(MouseEvent.CLICK, consultar);
			
			cboTipoPesquisa.addEventListener(Event.CHANGE, onChangeTipoPesquisa);
			
			grid.addEventListener(KeyboardEvent.KEY_DOWN, definirSelecionadoKeyDown);			
			grid.addEventListener(MouseEvent.CLICK, definirSelecionadoMouseEvent);
			grid.addEventListener(MouseEvent.DOUBLE_CLICK, definirSelecionadoMouseEvent);
			
			valorTipoPesquisa.addEventListener(KeyboardEvent.KEY_DOWN, configurarAtalho);
			
			btHistorico.addEventListener(MouseEvent.CLICK, emitirRelHistoricoBloqueio);
			btRelatorio.addEventListener(MouseEvent.CLICK, emitirRelBloqueios);
			
			btIncluir.addEventListener(MouseEvent.CLICK, incluir);
			btDesbloquear.addEventListener(MouseEvent.CLICK, desbloquear);
		}
		
		private function definirSelecionadoKeyDown(evt:KeyboardEvent):void {
			if(evt.keyCode == 13 && grid.selectedItem != null) {
				voSelecionado = grid.selectedItem as BloqueioContaCapitalVO;
				btHistorico.enabled = true;
				if (voSelecionado.ativo) {
					btDesbloquear.enabled = true;
				}
			}	
		}
		
		private function definirSelecionadoMouseEvent(evt:MouseEvent):void {
			if(grid.selectedItem != null) {
				voSelecionado = grid.selectedItem as BloqueioContaCapitalVO;
				btHistorico.enabled = true;
				btDesbloquear.enabled = false;
				if (voSelecionado.ativo) {
					btDesbloquear.enabled = true;
				} 
			}
		}
		
		private function retornoConsultar(evt:ResultEvent):void {
			listaVO = evt.result.dados["registros"] as ArrayCollection;
			grid.dataProvider = listaVO;
			grid.labelFunction = formataDataGrid;
			grid.dataProvider.refresh();
			
			btHistorico.enabled = false;
			btDesbloquear.enabled = false;
			if (listaVO != null && listaVO.length > 0) {
				btRelatorio.enabled = true;
			} else {
				btRelatorio.enabled = false;
			}
			
			MostraCursor.removeBusyCursor();
			
			if(!bolRecarregarConsulta && !evt.result.dados["registros"]) {
				Alerta.show("Não existem dados cadastrados para os filtros informados.", "ATENÇÃO", Alerta.ALERTA_OK);
			}
			bolRecarregarConsulta = false;
		}
		
		private function retornoConsultarError(evt:FaultEvent):void {
			if(evt.fault.faultCode == "Server.Security.SessionExpired") {
				PortalModel.getInstance().tratarSessaoExpirada();
				return;
			}
			
			Alerta.show("Erro ao consultar os dados", "ERRO", Alerta.ALERTA_ERRO);
		}
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			if (col.dataField != null) {
				switch(col.dataField) {
					case "cpfCnpj":
						if (obj[col.dataField]) {
							retorno = FormatUtil.formataCPFCNPJ(obj[col.dataField]);
						} else {
							retorno = "";						
						}
						break;
					case "dataBloqueio":
					case "dataProtocolo":
						if (obj[col.dataField]) {
							retorno = DataUtilRelatorios.dateToString((obj[col.dataField] as DateTimeEntity).data);
						} else {
							retorno = "";						
						}
						break;
					case "valorBloqueado":
						if (obj[col.dataField] != null) {
							retorno = cf.format(obj[col.dataField]);	
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
			}
			return retorno;
		}
		
		private function controlarTela():void {
			compNivelInst.cmbCentral.percentWidth = new Number(100);
			compNivelInst.rtlCentral.width = new Number(100);
			
			compNivelInst.cmbSingular.percentWidth = new Number(100);
			compNivelInst.rtlSingular.width = new Number(100);
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
		}
		
		private function consultar(evt:MouseEvent=null):void {
			if(validar()) {
				var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
				reqDTO.dados.vo = carregarVO();
				this.servico.consultar(reqDTO);
			}
		}
		
		public function recarregarConsulta():void {
			this.bolRecarregarConsulta = true;
			consultar();
		}
		
		private function carregarVO():BloqueioContaCapitalVO {
			this.vo = new BloqueioContaCapitalVO(); 
			this.vo.idInstituicao = new Number(compNivelInst.cmbSingular.selectedItem.codListaItem);
			var tipoPesquisa:String = cboTipoPesquisa.selectedItem["codListaItem"];
			if (tipoPesquisa == "1") {
				this.vo.nomePessoa = valorTipoPesquisa.text;
			} else if (tipoPesquisa == "2") {
				this.vo.cpfCnpj = valorTipoPesquisa.text;
			} else if (tipoPesquisa == "3") {
				this.vo.codTipoBloqueio = new Number(cboTipoBloqueio.selectedItem["codListaItem"]);
			} else if (tipoPesquisa == "4") {
				this.vo.numContaCapital = new Number(valorTipoPesquisa.text);
			} else if (tipoPesquisa == "5") {
				this.vo.numProtocolo = valorTipoPesquisa.text;
			} else if (tipoPesquisa == "6") {
				this.vo.numProcesso = valorTipoPesquisa.text;
			}
			if (cboSituacaoBloqueio.selectedItem["codListaItem"] != "") {
				this.vo.codSituacaoBloqueio = new Number(cboSituacaoBloqueio.selectedItem["codListaItem"]); 
			}
			return this.vo;
		}
		
		private function onChangeTipoPesquisa(evt:Event):void {
			var tipoPesquisa:int = (evt.currentTarget as Combo).selectedItem["codListaItem"];
			
			valorTipoPesquisa.text = "";
			valorTipoPesquisa.visible = true;
			cboTipoBloqueio.visible = false;
			cboTipoBloqueio.selectedIndex = 0;
			
			switch(tipoPesquisa) { // EnumTipoPesquisaBloqueio
				case 0: // TODOS
				{
					valorTipoPesquisa.visible = false;
					break;
				}
				case 1: // NOME
				{
					valorTipoPesquisa.restrict = "^'";
					valorTipoPesquisa.maxChars = 50;
					break;
				}
				case 2: // CPF_CNPJ
				{
					valorTipoPesquisa.restrict = "0-9";
					valorTipoPesquisa.maxChars = 14;
					break;
				}
				case 3: // TIPO_BLOQUEIO
				{
					cboTipoBloqueio.visible = true;
					valorTipoPesquisa.visible = false;
					break;					
				}
				case 4: // CONTA_CAPITAL
				{
					valorTipoPesquisa.restrict = "0-9";
					valorTipoPesquisa.maxChars = 8;
					break;
				}
				case 5: // NUM_PROTOCOLO
				{
					valorTipoPesquisa.restrict = "^'";
					valorTipoPesquisa.maxChars = 30;
					break;
				}
					
			}
			
		}
		
		/**
		 * Valida campos de acordo com regras de negocio
		 */
		private function validar():Boolean {
			if(!instituicaoCentralPreenchida()) {
				Alerta.show("O campo Instituição Resp é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbCentral);
				return false;
			}
			
			if(!instituicaoSingularPreenchida()) {
				Alerta.show("O campo Instituição é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, compNivelInst.cmbSingular);
				return false;
			}
			
			if(cboTipoPesquisa.selectedItem != null){//tratamento error em produção nullpoint
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "1" && valorTipoPesquisa.length < 3) {
					Alerta.show("Informe pelo menos 3 letras para o Nome", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPesquisa);
					return false;
				}
				
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "2" && valorTipoPesquisa.text == "") {
					Alerta.show("O campo CPF/CNPJ é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPesquisa);
					return false;
				}
				
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "4" && valorTipoPesquisa.text == "") {
					Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPesquisa);
					return false;
				}
				
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "5" && valorTipoPesquisa.text == "") {
					Alerta.show("O campo Nº Protocolo é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPesquisa);
					return false;
				}
				
				if(cboTipoPesquisa.selectedItem["codListaItem"] == "6" && valorTipoPesquisa.text == "") {
					Alerta.show("O campo Nº Processo é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, valorTipoPesquisa);
					return false;
				}
			}
			
			return true;
		}
		
		private function configurarAtalho(event:KeyboardEvent):void {
			if(event.charCode == 13) {
				consultar(null);
			}
		}
		
		private function instituicaoSingularPreenchida():Boolean {
			if(compNivelInst.cmbSingular.selectedItem != null) {//tratamento error em produção nullpoint
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
			if(compNivelInst.cmbCentral.selectedItem != null) {//tratamento error em produção nullpoint
				if(compNivelInst.cmbCentral.selectedIndex == 0 && compNivelInst.cmbCentral.value == "SELECIONE") {
					return false;
				}
				if(compNivelInst.cmbCentral.selectedItem.codListaItem > 0) {
					return true;
				}
			}
			return false;
		}
		
		private function emitirRelHistoricoBloqueio(evt:MouseEvent):void {
			if (this.voSelecionado != null) {
				var param:ParametroDTO = new ParametroDTO();
				param.dados.idBloqueio = this.voSelecionado.idBloqueio; 
				param.dados.idInstituicao = this.voSelecionado.idInstituicao; 
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelHistoricoBloqueioServicoRemote", 
					param, "CCA_RelHistoricoBloqueio", this.destino, "Emitindo relatório...", null, false);
			}
		}
		
		private function emitirRelBloqueios(evt:MouseEvent):void {
			if (this.vo != null && this.listaVO.length > 0) {
				var param:ParametroDTO = new ParametroDTO();
				param.dados.tipoPesquisa = cboTipoPesquisa.selectedItem["descListaItem"];
				param.dados.idInstituicao = this.vo.idInstituicao;
				param.dados.nomePessoa = this.vo.nomePessoa;
				param.dados.cpfCnpj = this.vo.cpfCnpj;
				param.dados.codTipoBloqueio = this.vo.codTipoBloqueio;
				if (cboTipoPesquisa.selectedItem["codListaItem"] == "3") {
					param.dados.nomeTipoBloqueio = cboTipoBloqueio.selectedItem["descListaItem"];
				}
				param.dados.numContaCapital = this.vo.numContaCapital;
				param.dados.numProtocolo = this.vo.numProtocolo;
				param.dados.codSituacaoBloqueio = this.vo.codSituacaoBloqueio; 
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelBloqueiosServicoRemote", 
					param, "CCA_RelBloqueios", this.destino, "Emitindo relatório...", null, false);
			}
		}
		
		private function incluir(evt:MouseEvent = null):void {
			var incluirBloqueio:IncluirBloqueio = new IncluirBloqueio();
			incluirBloqueio.destino = this.destino;
			var telaIncluirBloqueio:Janela = new Janela();
			telaIncluirBloqueio.title = "INCLUIR BLOQUEIO DE CAPITAL";
			telaIncluirBloqueio.removeAllChildren();
			telaIncluirBloqueio.addChild(incluirBloqueio);
			telaIncluirBloqueio.abrir(this, true, true);
		}
		
		private function desbloquear(evt:MouseEvent = null):void {
			var desbloquearCapital:DesbloquearCapital = new DesbloquearCapital();
			desbloquearCapital.vo = voSelecionado;
			desbloquearCapital.cf = cf;
			desbloquearCapital.destino = this.destino;
			var telaDesbloquearCapital:Janela = new Janela();
			telaDesbloquearCapital.title = "DESBLOQUEAR CAPITAL";
			telaDesbloquearCapital.removeAllChildren();
			telaDesbloquearCapital.addChild(desbloquearCapital);
			telaDesbloquearCapital.abrir(this, true, true);
		}
		
	}
}