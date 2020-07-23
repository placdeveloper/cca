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
	import br.com.sicoob.sisbr.cca.relatorios.parcelamento.RelParcelamentoContaCapitalView;
	import br.com.sicoob.sisbr.cca.vo.RelParcelamentoContaCapitalVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelParcelamentoContaCapitalVO", RelParcelamentoContaCapitalVO);
	public class RelParcelamentoContaCapital extends RelParcelamentoContaCapitalView {
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:RelParcelamentoContaCapitalVO;
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelParcelamentoContaCapitalServico";
		
		public function RelParcelamentoContaCapital() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);		
		} 
		
		private function init(event:FlexEvent):void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			btOk.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			obterDefinicoes();
		}
		
		private function cancelar(evt:MouseEvent): void{
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			servico.addEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			servico.bloquearOperacao = true;
			servico.mensagemEspera = "Obtendo Definições";
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function resultObterDefinicoes(evt:ResultEvent):void {
			cboTipoParcelamento.dataProvider = evt.result.dados["comboTipoParcelamento"] as ArrayCollection;
			cboFormaParcelamento.dataProvider = evt.result.dados["comboFormaParcelamento"] as ArrayCollection;
			cboOrdenacao.dataProvider = evt.result.dados["comboOrdenacao"] as ArrayCollection;
			cboPac.dataProvider = evt.result.dados["comboPac"] as ArrayCollection;
			cboSituacaoParcela.dataProvider = evt.result.dados["comboSituacaoParcela"] as ArrayCollection;
			
			//Parcela em aberto
			cboSituacaoParcela.procuraItemPorNome("1", "codListaItem");
			
			this.vo = evt.result.dados["vo"] as RelParcelamentoContaCapitalVO;
			
			dtInicioVencimento.selectedDate = this.vo.dataInicialVencimento.data;
			dtFinalVencimento.selectedDate = this.vo.dataFinalVencimento.data;
			dtSituacao.selectedDate = null;
			
			procurarCCAInicial.txtNumCCA.text = this.vo.numContaCapitalInicial.toString();
			procurarCCAInicial.idInstituicao = this.vo.idInstituicao;
			procurarCCAInicial.procurarCCA(null);
			
			procurarCCAFinal.txtNumCCA.text = this.vo.numContaCapitalFinal.toString();
			procurarCCAFinal.idInstituicao = this.vo.idInstituicao;
			procurarCCAFinal.procurarCCA(null);
			
			MostraCursor.removeBusyCursor();
		}
		
		private function emitirRelatorio(evt:MouseEvent): void {
			
			if(validar()) {
				
				var paramDTO:ParametroDTO = new ParametroDTO();
				paramDTO.dados.filtroIdInstituicao = this.vo.idInstituicao;
				paramDTO.dados.filtroNumContaCapitalInicial = new Number(procurarCCAInicial.txtNumCCA.text);
				paramDTO.dados.filtroNumContaCapitalFinal = new Number(procurarCCAFinal.txtNumCCA.text);
				paramDTO.dados.filtroTipoParcelameto = this.cboTipoParcelamento.selectedItem["codListaItem"];
				paramDTO.dados.filtroFormaParcelamento = this.cboFormaParcelamento.selectedItem["codListaItem"];
				paramDTO.dados.filtroSituacaoParcela = this.cboSituacaoParcela.selectedItem["codListaItem"];
				paramDTO.dados.filtroPeriodoInicial = this.dtInicioVencimento.selectedDate;
				paramDTO.dados.filtroPeriodoFinal = this.dtFinalVencimento.selectedDate;
				paramDTO.dados.filtroDataSituacao = this.dtSituacao.selectedDate;
				
				paramDTO.dados.filtroOrdenacao = this.cboOrdenacao.selectedItem["codListaItem"];
				paramDTO.dados.filtroNumPA = this.cboPac.selectedItem["codListaItem"];
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelParcelamentoContaCapitalServicoRemote", 
					paramDTO, "CCA_RelParcelamentoContaCapital", this.destino, "Emitindo relatório...", null, true);
			}
		}
		
		private function validar():Boolean {
			if(!procurarCCAInicial.isContaCapitalSelecionada()) {
				focusManager.setFocus(procurarCCAInicial.txtNumCCA);
				Alerta.show("O campo Conta Capital Inicial é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(!procurarCCAFinal.isContaCapitalSelecionada()) {
				focusManager.setFocus(procurarCCAFinal.txtNumCCA);
				Alerta.show("O campo Conta Capital Final é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(procurarCCAInicial.txtNumCCA.valor > procurarCCAFinal.txtNumCCA.valor) {
				focusManager.setFocus(procurarCCAFinal.txtNumCCA);
				Alerta.show("O campo Conta Capital Final deve ser maior ou igual ao campo Conta Capital Inicial.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(dtInicioVencimento.selectedDate == null) {
				focusManager.setFocus(dtInicioVencimento.compMask);
				Alerta.show("O campo Data Inicial de Vencimento é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(dtFinalVencimento.selectedDate == null) {
				focusManager.setFocus(dtInicioVencimento.compMask);
				Alerta.show("O campo Data Final de Vencimento é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(dtInicioVencimento.selectedDate.getTime() > dtFinalVencimento.selectedDate.getTime()) {
				focusManager.setFocus(dtInicioVencimento.compMask);
				Alerta.show("O campo Data Inicial de Vencimento deve ser anterior ao campo Data Final de Vencimento.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(cboTipoParcelamento.selectedItem == null){
				focusManager.setFocus(cboTipoParcelamento);
				Alerta.show("O campo Tipo Parcelamento é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(cboFormaParcelamento.selectedItem == null){
				focusManager.setFocus(cboFormaParcelamento);
				Alerta.show("O campo Forma Parcelamento é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(cboSituacaoParcela.selectedItem == null){
				focusManager.setFocus(cboSituacaoParcela);
				Alerta.show("O campo Situação Parcela é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(cboPac.selectedItem == null){
				focusManager.setFocus(cboPac);
				Alerta.show("O campo PA é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			if(cboOrdenacao.selectedItem == null){
				focusManager.setFocus(cboOrdenacao);
				Alerta.show("O campo Ordenar por é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			return true;
		}
		
		private function fechar(evt:MouseEvent):void {
			this.fecharJanela();
		}
	}
}