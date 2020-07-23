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
	import br.com.sicoob.sisbr.cca.relatorios.saldoatual.RelSaldoAtualView;
	import br.com.sicoob.sisbr.cca.util.PreImpressaoUtil;
	import br.com.sicoob.sisbr.cca.vo.RelSaldoAtualVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelSaldoAtualVO", RelSaldoAtualVO);
	public class RelSaldoAtual extends RelSaldoAtualView {
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:RelSaldoAtualVO;
		private var ATIVO: Number = 1;
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelSaldoAtualServico";
		
		public function RelSaldoAtual() {
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
			cboSituacaoConta.dataProvider = evt.result.dados["comboSituacaoConta"] as ArrayCollection;
			cboSituacaoConta.selectedIndex = ATIVO;
			
			this.vo = evt.result.dados["vo"] as RelSaldoAtualVO;
			
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
				paramDTO.dados.filtroIdSituacaoConta = this.cboSituacaoConta.selectedItem["codListaItem"];
				paramDTO.dados.filtroCadastrosAprovados = this.chkSituacaoCadastro.selected;
				paramDTO.dados.filtroDescEmpresa = this.empresa.txtNome.text;
				paramDTO.dados.filtroNumPessoaJuridica = this.empresa.resultadoPesquisaVO.numPessoaJuridica;
				paramDTO.dados.filtroNumPA = this.cboPac.selectedItem["codListaItem"];
				paramDTO.dados.filtroOrdenacao = this.cboOrdenacao.selectedItem["codListaItem"];
				paramDTO.dados.filtroAgruparPorPA = this.chkAgrupadorPA.selected;
				
				var pattern:RegExp = /[^0-9]/gi;
				var cnpjSemMascara:String = this.empresa.txtNumCNPJ.text.replace(pattern, "");
				paramDTO.dados.filtroCnpjEmpresa = cnpjSemMascara;

				var relatorioUtil:RelatorioUtil = RelatorioUtil.create();
				relatorioUtil.especificarFormatosDeImpressao(PreImpressaoUtil.obterFormatosImpressao());
				relatorioUtil.emitirRelatorio("cca_relatorios/RelSaldoAtualServicoRemote", 
					paramDTO, "CCA_Relatorio_Saldo_Atual", this.destino, "Emitindo relatório...", null, true);
			}
		}
		
		private function validar():Boolean {  
			if (!procurarCCAInicial.isContaCapitalSelecionada()){
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
			
			return true;
		}
		
		private function fechar(evt:MouseEvent):void {
			this.fecharJanela();
		}

	}
}