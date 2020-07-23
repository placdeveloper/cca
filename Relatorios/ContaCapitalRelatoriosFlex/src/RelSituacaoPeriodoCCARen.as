package
{
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
	import br.com.sicoob.sisbr.cca.relatorios.situacaocontacapitalporperiodo.RelSituacaoPeriodoCCARenView;
	import br.com.sicoob.sisbr.cca.vo.RelSituacaoPeriodoCCARenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelSituacaoPeriodoCCARenVO", RelSituacaoPeriodoCCARenVO);
	public class RelSituacaoPeriodoCCARen extends RelSituacaoPeriodoCCARenView {
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:RelSituacaoPeriodoCCARenVO;
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelSituacaoPeriodoCCARenServico";
		
		public function RelSituacaoPeriodoCCARen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);		
		} 
		
		private function init(event:FlexEvent):void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			btOk.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			servico.addEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			servico.bloquearOperacao = true;
			servico.mensagemEspera = "Obtendo Definições";
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function resultObterDefinicoes(evt:ResultEvent):void {
			cboOrdenacao.dataProvider = evt.result.dados["comboOrdenacao"] as ArrayCollection;
			cboPac.dataProvider = evt.result.dados["comboPac"] as ArrayCollection;
			cboSituacao.dataProvider = evt.result.dados["comboSituacao"] as ArrayCollection;
			
			this.vo = evt.result.dados["vo"] as RelSituacaoPeriodoCCARenVO;
			periodoInicial.selectedDate = this.vo.dataPeriodoInicial.data;
			periodoFinal.selectedDate = this.vo.dataPeriodoFinal.data; 
			
			MostraCursor.removeBusyCursor();
		}
		
		private function emitirRelatorio(evt:MouseEvent): void {	
			if(validar()) {	
				var paramDTO:ParametroDTO = new ParametroDTO();
	
				paramDTO.dados.filtroIdInstituicao = this.vo.idInstituicao;
				paramDTO.dados.filtroDataPeriodoInicial = this.periodoInicial.selectedDate;
				paramDTO.dados.filtroDataPeriodoFinal = this.periodoFinal.selectedDate;
				paramDTO.dados.filtroIdSituacao = this.cboSituacao.selectedItem["codListaItem"];
				paramDTO.dados.filtroCadastrosAprovados = this.chkSituacaoCadastro.selected;
				paramDTO.dados.filtroNumPA = this.cboPac.selectedItem["codListaItem"];
				paramDTO.dados.filtroOrdenacao = this.cboOrdenacao.selectedItem["codListaItem"];
		
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelSituacaoPeriodoCCARenServicoRemote", 
					paramDTO, "CCA_Relatorio_Situacao_Periodo", this.destino, "Emitindo relatório...", null, true);
			}
		}
		
		private function validar():Boolean { 
			if (this.periodoInicial.selectedDate == null){
				periodoInicial.setFocus();
				Alerta.show("O campo Período Inicial é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if (this.periodoFinal.selectedDate == null){
				periodoFinal.setFocus();
				Alerta.show("O campo Período Final é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(this.periodoInicial.selectedDate > this.periodoFinal.selectedDate) {
				periodoInicial.setFocus();
				Alerta.show("O campo Período Final deve ser maior ou igual ao campo Período Inicial.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			return true;
		}
		
		private function fechar(evt:MouseEvent):void {
			this.fecharJanela();
		}

	}
}