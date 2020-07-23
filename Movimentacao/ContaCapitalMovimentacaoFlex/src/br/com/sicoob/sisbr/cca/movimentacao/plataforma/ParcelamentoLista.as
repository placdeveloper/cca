package br.com.sicoob.sisbr.cca.movimentacao.plataforma
{
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IListaPlataformaAtendimento;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.cca.vo.DesligarContaCapitalRenVO;

	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO", DesligarContaCapitalRenVO);
	public class ParcelamentoLista extends ParcelamentoListaView implements IListaPlataformaAtendimento	{
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.ParcelamentoContaCapitalServico";

		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		public var destinoParent:DestinoVO;		
		
		public function ParcelamentoLista() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
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
			servico.consultar.addEventListener(ResultEvent.RESULT, retornoConsultar);
			
			consultar();
		}
		
		private function consultar():void {
			
			var pessoaSelecionada:PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.idPessoa = pessoaSelecionada.idPessoa;
			reqDTO.dados.idInstituicao = pessoaSelecionada.idInstituicao;
			
			servico.consultar(reqDTO);
		}
		
		private function retornoConsultar(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			gridCCA.dataProvider = event.result.dados["registros"];
			gridCCA.labelFunction = formataDataGrid;      
			
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));
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
		
		public function obterGrid():Grid {
			return this.gridCCA;
		}
		
		public function obterLista():void {
			consultar();
		}
	}
}