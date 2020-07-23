package br.com.sicoob.sisbr.cca.comum.pesquisaempresa
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
	
	public class SelecionarEmpresa extends SelecionarEmpresaView {
		
		private var mapResultConsulta:Object = new Object();
		private var destinoVO:DestinoVO;		
		private var vetCampos:ArrayCollection = new ArrayCollection();
		private var servico:ServicoJava = new ServicoJava();
		private var source:String;
		
		public var procurarEmpresa:ProcurarEmpresa;
			
		public function SelecionarEmpresa() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
			
		private function init(evt:FlexEvent):void {
			obterDefinicoes();
			
			botFechar.addEventListener(MouseEvent.CLICK, fecharJanelaConsulta);			
			botProcurar.addEventListener(MouseEvent.CLICK, pesquisar);
			botOK.addEventListener(MouseEvent.CLICK, carregarPesquisarMouseEvent);
			
			grdDados.addEventListener(KeyboardEvent.KEY_DOWN, carregarPesquisarKeyDown);
			grdDados.addEventListener(MouseEvent.DOUBLE_CLICK, carregarPesquisarMouseEvent);
			
			txtValor.addEventListener(KeyboardEvent.KEY_DOWN, pesquisarKeyDown);
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

		private function carregarPesquisarKeyDown(event:KeyboardEvent = null):void {
			if(grdDados.selectedItem != null) {
				procurarEmpresa.txtNome.text = grdDados.selectedItem.nome;												
				procurarEmpresa.txtNumCNPJ.text = FormatUtil.formataCPFCNPJ(grdDados.selectedItem.numCGC_CPF);
				procurarEmpresa.preecherRegistro(grdDados.selectedItem);
				this.fecharJanela();
			} else {
				procurarEmpresa.limparRegistro();
				procurarEmpresa.limparCampos();
			}
		}
		
		private function carregarPesquisarMouseEvent(event:MouseEvent=null):void {
			if(grdDados.selectedItem != null) {
				procurarEmpresa.txtNome.text = grdDados.selectedItem.descNomePessoa;
				procurarEmpresa.txtNumCNPJ.text = FormatUtil.formataCPFCNPJ(grdDados.selectedItem.numCGC_CPF);
				procurarEmpresa.preecherRegistro(grdDados.selectedItem);				
				this.fecharJanela();
			} else {
				procurarEmpresa.limparRegistro();
				procurarEmpresa.limparCampos();
			}
		}

		private function obterDefinicoes():void {
			PortalModel.portal.obterDefinicoesDestino(Constantes.SERVICOSJAVACCAREN, configurarServico);			
			carregarCombos();
			
			servico.source = "br.com.sicoob.sisbr.cca.comum.servicos.PesquisaEmpresaServico";	
			servico.mensagemEspera = "Procurando Registros";
			servico.bloquearOperacao = true;
			servico.addEventListener(ResultEvent.RESULT, resultPesquisar);
			servico.addEventListener(FaultEvent.FAULT, resultFalhaPesquisar);
		}		
		
		private function configurarServico(destinoVO:DestinoVO):void {
			servico.configurarDestino(destinoVO);
		}		
			
		private function validarPesquisa():Boolean {
			if(txtValor.length < 3 && cboTipoProcura.selectedItem.data == 1) {
				Alerta.show("Informe ao menos 3 caracteres.", "ATENÇÃO", Alerta.ALERTA_OK, txtValor);
				return false;
			}
			
			if(txtValor.length == 0) {
				Alerta.show("Informe o filtro da pesquisa.", "ATENÇÃO", Alerta.ALERTA_OK, txtValor);
				return false;
			}

			return true;
		}
		
		private function pesquisarKeyDown(evt:KeyboardEvent):void {			
			if (evt.keyCode == 13) {
				pesquisar();
			}
		}
		
		private function configurarFiltros(evt:ListEvent):void {
			txtValor.text = "";
			
			switch (cboTipoProcura.selectedItem.data) {
				case 1:
					txtValor.maxChars = 50;
					txtValor.restrict = "^'";
				break;
				
				case 2:
					txtValor.maxChars = 14;
					txtValor.restrict = "0-9";
				break;
			}
		}
		
		private function carregarCombos():void {
			var arrTipoProcura:ArrayCollection = new ArrayCollection();
			arrTipoProcura.addItem({label: "NOME", data: 1});
			arrTipoProcura.addItem({label: "CNPJ", data: 2});
			cboTipoProcura.dataProvider = arrTipoProcura;
		}
		
		private function pesquisar(evt:MouseEvent=null):void {
			if (!validarPesquisa()) {
				return;
			}
			
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			var pesquisaVO:PesquisaEmpresaVO = new PesquisaEmpresaVO();
			
			switch (cboTipoProcura.selectedItem.data) {
				case 1:
					pesquisaVO.descNomePessoa = txtValor.text;		
				break;
				
				case 2:
					pesquisaVO.numCGC_CPF = txtValor.text;
				break;
			}
			
			reqDTO.dados.pesquisaEmpresaVO = pesquisaVO;
			
	 		servico.pesquisar(reqDTO);
		}
		
		private function resultPesquisar(evt:ResultEvent):void {			
			mapResultConsulta = evt.result.dados;		
			fazerPaginacao();
			MostraCursor.removeBusyCursor();
		}
		
		private function resultFalhaPesquisar(evt:FaultEvent):void {
			Alerta.show("Erro ao pesquisar a empresa.", "Atenção", Alerta.ALERTA_ERRO);
			return;
		}				
		
		private function formataDataGrid(obj:Object, col:ColunaGrid):String {
			var retorno:String = "";
			
			switch(col.dataField) {
				
				case "numCGC_CPF":
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