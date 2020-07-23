package
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.relatorios.recolhimentoirrfdestinacaojuros.RelRecolhimentoIrrfDestinacaoJurosView;
	import br.com.sicoob.sisbr.cca.vo.RelRecolhimentoIrrfDestinacaoJurosVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelRecolhimentoIrrfDestinacaoJurosVO", RelRecolhimentoIrrfDestinacaoJurosVO);
	public class RelRecolhimentoIrrfDestinacaoJuros extends RelRecolhimentoIrrfDestinacaoJurosView {
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:RelRecolhimentoIrrfDestinacaoJurosVO;
		private var ATIVO: Number = 1;
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelRecolhimentoIrrfDestinacaoJurosServico";
		
		public function RelRecolhimentoIrrfDestinacaoJuros() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);		
		} 
		
		private function init(event:FlexEvent):void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			chkTodos.addEventListener(MouseEvent.CLICK, todosChange);
			btOk.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelarRelatorio);
			obterDefinicoes();
		}
		
		private function cancelarRelatorio(evt:MouseEvent):void{
			chkTodos.selected = true;
			
			procurarCCA.txtNumCCA.text = "";
			procurarCCA.procurarCCA(null);
			procurarCCA.enabled = false;
			
			anoBase.text = "";
			
			cboPac.selectedIndex = 0;
			chkAgrupadorPA.selected = false;
			cboOrdenacao.selectedIndex = 0;
		}
		
		private function todosChange(evt:Event):void{
			procurarCCA.enabled = !chkTodos.selected;
			procurarCCA.txtNumCCA.text = "";
			procurarCCA.procurarCCA(null);
		}
		
		private function obterDefinicoes():void {
			servico.addEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			servico.bloquearOperacao = true;
			servico.mensagemEspera = "Obtendo Definições";
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function resultObterDefinicoes(evt:ResultEvent):void {
			cboOrdenacao.dataProvider = evt.result.dados["comboOrdenacao"] as ArrayCollection;
			cboPac.dataProvider = evt.result.dados["comboPa"] as ArrayCollection;
			
			this.vo = evt.result.dados["vo"] as RelRecolhimentoIrrfDestinacaoJurosVO;
			
			procurarCCA.enabled = false;
				
			procurarCCA.txtNumCCA.text = this.vo.numContaCapital.toString();
			procurarCCA.idInstituicao = this.vo.idInstituicao;
			procurarCCA.procurarCCA(null);
			
			MostraCursor.removeBusyCursor();
		}
		
		private function emitirRelatorio(evt:MouseEvent): void {	
			if(validar()) {	
				var paramDTO:ParametroDTO = new ParametroDTO();
				
				paramDTO.dados.filtroIdInstituicao = this.vo.idInstituicao;
				
				paramDTO.dados.filtroTodos = this.chkTodos.selected;
				paramDTO.dados.filtroNumContaCapital = new Number(procurarCCA.txtNumCCA.text);
				paramDTO.dados.filtroAnoBase = new Number(this.anoBase.valor);
				
				paramDTO.dados.filtroNumPac = this.cboPac.selectedItem["codListaItem"];
				paramDTO.dados.filtroOrdenacao = this.cboOrdenacao.selectedItem["codListaItem"];
				paramDTO.dados.filtroAgruparPorPA = this.chkAgrupadorPA.selected;
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelRecolhimentoIrrfDestinacaoJurosServicoRemote", 
					paramDTO, "CCA_Relatorio_Recolhimento_Irrf_Destinacao_Juros", this.destino, "Emitindo relatório...", null, true);
			}
		}
		
		private function validar():Boolean {  
			if (!chkTodos.selected && !procurarCCA.isContaCapitalSelecionada()){
				focusManager.setFocus(procurarCCA.txtNumCCA);
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if (anoBase.text == null || anoBase.text.length < 4){
				focusManager.setFocus(anoBase);
				Alerta.show("O campo Ano Base é obrigatório e deve conter 4 números.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			
			
			return true;
		}
		
		private function fechar(evt:MouseEvent):void {
			this.fecharJanela();
		}
		
	}
}