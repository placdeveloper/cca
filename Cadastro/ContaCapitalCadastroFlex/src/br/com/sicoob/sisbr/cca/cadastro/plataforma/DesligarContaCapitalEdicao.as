package br.com.sicoob.sisbr.cca.cadastro.plataforma {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IEdicaoPlataformaAtendimento;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.cca.vo.DesligarContaCapitalRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO", DesligarContaCapitalRenVO);
	public class DesligarContaCapitalEdicao extends DesligarContaCapitalEdicaoView implements IEdicaoPlataformaAtendimento {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.DesligarContaCapitalServico";
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelDesligamentoAssociadoServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		public var pessoaSelecionada:PessoaPlataformaVO;
		public var numContaCapital:Number;
		public var idContaCapital:Number;
		public var destinoParent:DestinoVO;		
		
		[Bindable]
		public var vo:DesligarContaCapitalRenVO = new DesligarContaCapitalRenVO();
		
		public var telaCadastroContaCapitalAtendimento:CadastroContaCapitalAtendimento;
		
		public function DesligarContaCapitalEdicao() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			inicializaServico();		
			obterDefinicoes();
			controlarEventos();
			carregarDados();
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(destinoParent);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			controlarServico();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			
			servico.obterInformacoes.addEventListener(ResultEvent.RESULT, retornoObterInformacoes);
			servico.obterInformacoes.addEventListener(FaultEvent.FAULT, retornoObterInformacoesError);
			
			this.servico.desligar.addEventListener(ResultEvent.RESULT, retornoDesligar);
		}
		
		private function obterDefinicoes():void {
			servico.source = SERVICO_SOURCE;
			servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			dataDesligamento.text = event.result.dados["dataAtualProduto"];
			MostraCursor.removeBusyCursor();
		}
		
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
		}
		
		public function carregarDados():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idContaCapital = idContaCapital;
			servico.source = SERVICO_SOURCE;
			servico.obterInformacoes(dto);
		}
		
		private function retornoObterInformacoes(event:ResultEvent):void {
			this.vo = event.result.dados["vo"];
			
			this.numCCA.text = this.vo.numContaCapital.toString();
			
			this.vlrSubs.valor = this.vo.vlrSubs;
			this.vlrInteg.valor = this.vo.vlrInteg;
			this.vlrAInteg.valor = this.vo.vlrAInteg;
			this.vlrBloq.valor = this.vo.vlrBloq;
			this.qtdCotas.valor = this.vo.qtdCotas;
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoObterInformacoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultString, "ERRO", Alerta.ALERTA_ERRO);
		}
		
		public function gravarRegistro():void {
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
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
			telaCadastroContaCapitalAtendimento.recarregarLista();
			super.fecharJanela();
		}
		
		private function validar():Boolean {
			if(!optDemissao.selected && !optEliminacao.selected && !optExclusao.selected) {
				Alerta.show("O campo OPERAÇÃO é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK, optDemissao);
				return false;
			}
			
			return true;
		}
		
		/**
		 * Carrega valores da camada de apresentacao para o VO 
		 */
		private function carregarVO():DesligarContaCapitalRenVO {
			vo.idInstituicao = pessoaSelecionada.idInstituicao;
			vo.idPessoa = pessoaSelecionada.idPessoa;
			return vo;
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
		
		private function controlarEventos():void {
			btCanc.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, desligar);
			
			optEliminacao.addEventListener(MouseEvent.CLICK, desabilitarCartaDemissao);
			optExclusao.addEventListener(MouseEvent.CLICK, desabilitarCartaDemissao);
			optDemissao.addEventListener(MouseEvent.CLICK, habilitarCartaDemissao);
			
			btDemissao.addEventListener(MouseEvent.CLICK, emitirDemissao);
			
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
					dtoRel, "CCA_RelDesligamentoAssociado", destinoParent, "Emitindo relatório...", null, false);
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
					dtoRel, "CCA_RelImpedimentosDesligamento", destinoParent, "Emitindo relatório...", null, false);
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
			btDemissao.enabled = false;
		}		
		
		public function carregarRegistro(registro:Object):void {}
		
		public function preencherCampos():void { }
		
		public function carregarDefinicoes(obj:Object = null):void { }
		
		public function novoRegistro():void	{ }
		
		public function atualizarCamposRegistro():void { }
		
		public function restaurarRegistro():void { }
		
		public function excluirRegistro(obj:Object):void { }
		
		public function verificarAlteracoes():Boolean {
			return false;
		}
	}
}