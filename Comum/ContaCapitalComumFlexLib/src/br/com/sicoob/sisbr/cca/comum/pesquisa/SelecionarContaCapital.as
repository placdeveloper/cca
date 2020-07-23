package br.com.sicoob.sisbr.cca.comum.pesquisa
{
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.eventos.EventNavegacao;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.paginacao.BarraPaginacao;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.util.Constantes;
	
	public class SelecionarContaCapital extends SelecionarContaCapitalView {
		
		private var mapResultConsulta:Object = new Object();
		private var vetCampos:ArrayCollection = new ArrayCollection();
		private var servico:ServicoJava = new ServicoJava();
		private var source:String;
				
		public var procurarContaCapital:ProcurarContaCapital;
			
		public function SelecionarContaCapital() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
			
		private function init(evt:FlexEvent):void {
			obterDefinicoes();
			
			botFechar.addEventListener(MouseEvent.CLICK, fecharJanelaConsulta);			
			botProcurar.addEventListener(MouseEvent.CLICK, pesquisarCCA);
			botOK.addEventListener(MouseEvent.CLICK, carregarPesquisarCCAMouseEvent);
			
			grdDados.addEventListener(KeyboardEvent.KEY_DOWN, carregarPesquisarCCAKeyDown);
			grdDados.addEventListener(MouseEvent.DOUBLE_CLICK, carregarPesquisarCCAMouseEvent);
			
			txtValor.addEventListener(KeyboardEvent.KEY_DOWN, pesquisarCCAKeyDown);
			cboTipoProcura.addEventListener(ListEvent.CHANGE, configurarFiltros);
			
			barraPaginacao.addEventListener(BarraPaginacao.EVENT_NAVEGACAO, navegar);
			
			configurarFiltros(null);
			cboTipoProcura.setFocus();
		}
		
		private function navegar(evt:EventNavegacao):void {
			fazerPaginacao(evt.pagina);
		}
		
		private function fazerPaginacao(pgn:int=1):void {
			var numRegistroPagina:int = mapResultConsulta["numRegistroPagina"]; 
			var numTotalRegistro:int = mapResultConsulta["numTotalRegistro"];
			var numTotalPaginas:int = mapResultConsulta["numTotalPaginas"];
			var lstDadosRetorno:ArrayCollection = mapResultConsulta["lstDadosRetorno"];
			var lstDadosGrid:ArrayCollection = new ArrayCollection();			
			var ini:int;
			var fim:int;
		
			if(lstDadosRetorno != null && lstDadosRetorno.length > 0) {

				fim = (pgn == 1 ? (numRegistroPagina-1):((pgn*numRegistroPagina)-1));
				ini = (pgn == 1 ? 0:(fim-numRegistroPagina+1));
				
				if (fim >= numTotalRegistro){
					fim = numTotalRegistro-1;
				}					
				
				for (ini;ini <= fim;ini++){
					lstDadosGrid.addItem(lstDadosRetorno.getItemAt(ini));
				}
				grdDados.dataProvider = lstDadosGrid;
				grdDados.labelFunction = formataDataGrid;
			} else {
				grdDados.dataProvider = null;								
			}
			
			
			
			barraPaginacao.totalPaginas = numTotalPaginas;
			barraPaginacao.pagina = pgn;
		}

		private function carregarPesquisarCCAKeyDown(event:KeyboardEvent=null):void {
			if(grdDados.selectedItem != null) {
				procurarContaCapital.txtNome.text = grdDados.selectedItem.nome;												
				procurarContaCapital.txtNumCCA.text = grdDados.selectedItem.numContaCapital;
				procurarContaCapital.txtCpfCnpj.text = FormatUtil.formataCPFCNPJ(grdDados.selectedItem.cpfCnpj);
				procurarContaCapital.preecherRegistro(grdDados.selectedItem);
				this.fecharJanela();
			} else {
				procurarContaCapital.limparRegistro();
				procurarContaCapital.limparCampos();
			}
		}
		
		private function carregarPesquisarCCAMouseEvent(event:MouseEvent=null):void {
			if(grdDados.selectedItem != null) {
				procurarContaCapital.txtNome.text = grdDados.selectedItem.nome;												
				procurarContaCapital.txtNumCCA.text = grdDados.selectedItem.numContaCapital.toString();
				procurarContaCapital.txtCpfCnpj.text = FormatUtil.formataCPFCNPJ(grdDados.selectedItem.cpfCnpj);
				procurarContaCapital.preecherRegistro(grdDados.selectedItem);				
				this.fecharJanela();
			} else {
				procurarContaCapital.limparRegistro();
				procurarContaCapital.limparCampos();
			}
		}

		private function obterDefinicoes():void {
			PortalModel.portal.obterDefinicoesDestino(Constantes.SERVICOSJAVACCAREN, configurarServico);			
			carregarCombos();
			
			servico.source = "br.com.sicoob.sisbr.cca.comum.servicos.PesquisaContaCapitalServico";	
			servico.mensagemEspera = "Procurando Registros";
			servico.bloquearOperacao = true;
			servico.addEventListener(ResultEvent.RESULT, resultPesquisarCCA);
			servico.addEventListener(FaultEvent.FAULT, resultFalhaPesquisarCCA);
		}		
		
		private function configurarServico(destinoVO:DestinoVO):void {
			servico.configurarDestino(destinoVO);
		}		
		
		private function validarPesquisa():Boolean {
			if(procurarContaCapital == null || procurarContaCapital.idInstituicao == 0) {
				return false;
			}
			
			if(txtValor.length < 3 && cboTipoProcura.selectedItem.data == 3) {
				Alerta.show("Informe ao menos 3 caracteres.", "ATENÇÃO", Alerta.ALERTA_OK, txtValor);
				return false;
			}
			
			if(txtValor.length == 0) {
				Alerta.show("Informe o filtro da pesquisa.", "ATENÇÃO", Alerta.ALERTA_OK, txtValor);
				return false;
			}

			return true;
		}
		
		private function pesquisarCCAKeyDown(evt:KeyboardEvent):void {			
			if (evt.keyCode == 13) {
				pesquisarCCA();
			}
		}
		
		private function configurarFiltros(evt:ListEvent):void {
			txtValor.text = "";
			
			switch (cboTipoProcura.selectedItem.data) {
				case 1:
					txtValor.maxChars = 8;
					txtValor.restrict = "0-9";
					break;
				
				case 2:
					txtValor.maxChars = 14;
					txtValor.restrict = "0-9";
					break;
				
				case 3:
					txtValor.maxChars = 50;
					txtValor.restrict = "^'";
				break;				
			}
		}
		
		private function carregarCombos():void {
			var arrTipoProcura:ArrayCollection = new ArrayCollection();
			arrTipoProcura.addItem({label: "CPF/CNPJ", data: 2});
			arrTipoProcura.addItem({label: "NOME", data: 3});
			arrTipoProcura.addItem({label: "CONTA CAPITAL", data: 1});
			
			cboTipoProcura.dataProvider = arrTipoProcura;
		}
		
		private function pesquisarCCA(evt:MouseEvent=null):void {
			if (!validarPesquisa()) {
				return;
			}
			
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var pesquisaVO:PesquisaContaCapitalVO = new PesquisaContaCapitalVO();
			
			pesquisaVO.idInstituicao = procurarContaCapital.idInstituicao;
			pesquisaVO.idSituacaoCadastro = procurarContaCapital.idSituacaoCadastro;
			pesquisaVO.idSituacaoContaCapital = procurarContaCapital.idSituacaoContaCapital;
			
			switch (cboTipoProcura.selectedItem.data) {
				case 1:
					pesquisaVO.numContaCapital = new Number(txtValor.text);
				break;
				
				case 2:
					pesquisaVO.cpfCnpj = txtValor.text;
				break;
				
				case 3:
					pesquisaVO.nome = txtValor.text;		
				break;				
			}
			
			reqDTO.dados.pesquisaContaCapitalVO = pesquisaVO;
			
	 		servico.pesquisar(reqDTO);
		}
		
		private function resultPesquisarCCA(evt:ResultEvent):void {			
			mapResultConsulta = evt.result.dados;		
			fazerPaginacao();
			MostraCursor.removeBusyCursor();
		}
		
		private function resultFalhaPesquisarCCA(evt:FaultEvent):void {
			Alerta.show("Erro ao pesquisar a conta capital.", "Atenção", Alerta.ALERTA_ERRO);
			return;
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
		

		private function fecharJanelaConsulta(evt:MouseEvent):void {
			this.fecharJanela();
		}
	}
}