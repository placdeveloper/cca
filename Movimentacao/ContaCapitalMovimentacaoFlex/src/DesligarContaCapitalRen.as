package
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.movimentacao.desligar.DesligarContaCapitalRenView;
	import br.com.sicoob.sisbr.cca.vo.DesligarContaCapitalRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO", DesligarContaCapitalRenVO);
	public class DesligarContaCapitalRen extends DesligarContaCapitalRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DesligarContaCapitalServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelDesligamentoAssociadoServico";
		
		private var destinoVO:DestinoVO;
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		private var vo:DesligarContaCapitalRenVO = new DesligarContaCapitalRenVO();
		
		public function DesligarContaCapitalRen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			configurarServico();
		}
		
		private function configurarServico():void {
			//PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
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
			
			controlarServico();
			controlarEventos();	
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			dataDesligamento.text = event.result.dados["dataAtualProduto"];
			MostraCursor.removeBusyCursor();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			servico.obterInformacoes.addEventListener(ResultEvent.RESULT, retornoObterInformacoes);
			servico.desligar.addEventListener(ResultEvent.RESULT, retornoDesligar);
			servico.desligar.addEventListener(FaultEvent.FAULT, retornoDesligarError);
		}
		
		private function controlarEventos():void {
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, desligar);
			
			optEliminacao.addEventListener(MouseEvent.CLICK, desabilitarCartaDemissao);
			optExclusao.addEventListener(MouseEvent.CLICK, desabilitarCartaDemissao);
			optDemissao.addEventListener(MouseEvent.CLICK, habilitarCartaDemissao);
			
			btDemissao.addEventListener(MouseEvent.CLICK, emitirDemissao);
			
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoObterProcurarCCA);
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.REGISTRO_NAO_ENCONTRADO, contaCapitalNaoEncontrada);
		}
		
		private function retornoObterProcurarCCA(event:SelecionarContaCapitalEvent):void {
			reqDTO.dados.idContaCapital = procurarCCA.resultadoPesquisaVO.idContaCapital;
			servico.obterInformacoes(reqDTO);
		}
		
		private function contaCapitalNaoEncontrada(event:SelecionarContaCapitalEvent):void {
			limpar();
		}
		
		private function limpar():void {
			this.vo = new DesligarContaCapitalRenVO();
			
			this.optDemissao.selected = false;
			this.optEliminacao.selected = false;
			this.optExclusao.selected = false;
			
			this.vlrSubs.valor = 0;
			this.vlrInteg.valor = 0;
			this.vlrAInteg.valor = 0;
			this.vlrBloq.valor = 0;
			this.qtdCotas.valor = 0;
			
			btDemissao.enabled = false;
		}
		
		private function retornoObterInformacoes(event:ResultEvent):void {
			this.vo = event.result.dados["vo"];
			
			this.vlrSubs.valor = this.vo.vlrSubs;
			this.vlrInteg.valor = this.vo.vlrInteg;
			this.vlrAInteg.valor = this.vo.vlrAInteg;
			this.vlrBloq.valor = this.vo.vlrBloq;
			this.qtdCotas.valor = this.vo.qtdCotas;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
		}
		
		private function desligar(evt:MouseEvent):void {
			if(validar()) {
				this.vo.tipoOperacao = getTipoOperacao();
				
				var dataDesligamento:Date = DateUtils.stringToDate(dataDesligamento.text, "DD/MM/YYYY");
				this.vo.dataDesligamento = DateTimeBase.getDateTime(dataDesligamento);
				
				reqDTO.dados.vo = this.vo;
				this.servico.desligar(reqDTO);
			}
		}
		
		private function retornoDesligar(evt:ResultEvent):void {
			if(evt.result.dados["erroNegocial"] && evt.result.dados["impedimentos"]) {
				Alerta.show(evt.result.dados["msg"], "ATENÇÃO", Alerta.ALERTA_OK, null, emitirImpedimentos);
				return;
				
			} else if(evt.result.dados["erroNegocial"]) {
				Alerta.show(evt.result.dados["msg"], "ERRO", Alerta.ALERTA_ERRO);
				MostraCursor.removeBusyCursor();
				return;
			}
			
			MostraCursor.removeBusyCursor();
			
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO);
			
			cancelar(null);
		}
		
		private function retornoDesligarError(evt:FaultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
		}
		
		/**
		 * Valida dados da tela
		 */
		private function validar():Boolean {
			if(!procurarCCA.isContaCapitalSelecionada()) {
				Alerta.show("O campo Conta Capital é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, procurarCCA.txtNumCCA);
				return false;
			}
			
			if(!optDemissao.selected && !optEliminacao.selected && !optExclusao.selected) {
				Alerta.show("O campo Operação é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, optDemissao);
				return false;
			}
			
			return true;
		}
		
		private function getTipoOperacao():int {
			if(optDemissao.selected) {
				return parseInt(optDemissao.value.toString());
			}
			if(optEliminacao.selected) {
				return parseInt(optEliminacao.value.toString());
			}
			if(optExclusao.selected) {
				return parseInt(optExclusao.value.toString());
			}
			return 0;
		}
		
		private function desabilitarCartaDemissao(evt:MouseEvent):void {
			btDemissao.enabled = false;
		}
		
		private function habilitarCartaDemissao(evt:MouseEvent):void {
			btDemissao.enabled = true;
		}
		
		private function emitirDemissao(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;	
			
			if(this.vo.idContaCapital > 0 && this.vo.idPessoa > 0 && this.vo.idInstituicao > 0) {
				
//				var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
				var dtoRel:ParametroDTO = new ParametroDTO();
				dtoRel.dados.idContaCapital = this.vo.idContaCapital;
				dtoRel.dados.idPessoa = this.vo.idPessoa;
				dtoRel.dados.idInstituicao = this.vo.idInstituicao;
				
//				RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirDesligamentoAssociado",
//					SERVICO_REL_SOURCE, dtoRel, "RelDesligamentoAssociado", destinoVO, "Emitindo relatório",
//					formatoRelatorio, exibirPreImpressao);				
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelDesligamentoAssociadoServicoRemote", 
					dtoRel, "CCA_RelDesligamentoAssociado", this.destino, "Emitindo relatório...", null, false);
			}
		}
		
		private function emitirImpedimentos(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;	
			
			if(this.vo.idContaCapital > 0 && this.vo.idPessoa > 0 && this.vo.idInstituicao > 0) {
				
//				var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
				var dtoRel:ParametroDTO = new ParametroDTO();
				dtoRel.dados.idContaCapital = this.vo.idContaCapital;
				dtoRel.dados.idPessoa = this.vo.idPessoa;
				dtoRel.dados.idInstituicao = this.vo.idInstituicao;
				
//				RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirImpedimentos",
//					SERVICO_REL_SOURCE, dtoRel, "RelImpedimentosDesligamento", destinoVO, "Emitindo relatório",
//					formatoRelatorio, exibirPreImpressao);
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelImpedimentosDesligamentoServicoRemote", 
					dtoRel, "CCA_RelImpedimentosDesligamento", this.destino, "Emitindo relatório...", null, false);
			}
		}
			
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		/**
		 * Retorna os valores da camada de apresentacao para o estado inicial 
		 */
		private function cancelar(evt:MouseEvent):void {
			limparCCA();
			zerarValores();
		}
		
		private function limparCCA():void {
			procurarCCA.limparCampos();			
			procurarCCA.limparRegistro();			
		}
		
		private function zerarValores():void {
			this.optDemissao.selected = false;
			this.optExclusao.selected = false;
			this.optEliminacao.selected = false;
			this.vlrSubs.text = "";
			this.vlrInteg.text = "";
			this.vlrAInteg.text = "";
			this.vlrBloq.text = "";
			this.qtdCotas.text = "";			
		}
	}
}