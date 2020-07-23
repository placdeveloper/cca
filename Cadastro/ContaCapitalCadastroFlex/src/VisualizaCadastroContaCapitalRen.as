package
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.cadastro.contacapital.VisualizaCadastroContaCapitalRenView;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	import br.com.sicoob.sisbr.cca.vo.DocumentoCapitalVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO", DocumentoCapitalVO);
	public class VisualizaCadastroContaCapitalRen extends VisualizaCadastroContaCapitalRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.CadastroContaCapitalRenServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelFichaPropostaMatriculaServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		public var vo:CadastroContaCapitalRenVO;
		
		public function VisualizaCadastroContaCapitalRen(vo:CadastroContaCapitalRenVO) {
			super();
			
			this.vo = vo;
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function init(event:FlexEvent):void {
			controlarServico();
			controlarEventos();
			
			inicializaServico();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			obterDefinicoesAlterar();
		}
		
		private function obterDefinicoesAlterar():void {
			reqDTO.dados.vo = this.vo;
			servico.obterDefinicoesAlterar(reqDTO);
		}
		
		private function retornoObterDefinicoesAlterar(event:ResultEvent):void {
			
			this.vo = event.result.dados["vo"];
			
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
			
			grid.dataProvider = event.result.dados["documentos"] as ArrayCollection;
			
			controlarTela();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoesAlterar.addEventListener(ResultEvent.RESULT, retornoObterDefinicoesAlterar);
			servico.obterDefinicoesAlterar.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesAlterarError);
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		private function controlarTela():void {
			
		}
		
		private function retornoObterDefinicoesAlterarError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
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
			return vo;
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
	}	
}