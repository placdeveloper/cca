package
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.corporativo.componentes.listarAnotacoes.PainelListaAnotacoes;
	import br.com.sicoob.sisbr.cca.cadastro.aprovacao.AprovacaoCadastroContaCapitalView;
	import br.com.sicoob.sisbr.cca.vo.CadastroContaCapitalRenVO;
	import br.com.sicoob.sisbr.cca.vo.DocumentoCapitalVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO", CadastroContaCapitalRenVO);
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO", DocumentoCapitalVO);
	public class AprovacaoCadastroContaCapital extends AprovacaoCadastroContaCapitalView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.AprovacaoContaCapitalServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		
		public var vo:CadastroContaCapitalRenVO;
		
		
		public function AprovacaoCadastroContaCapital(vo:CadastroContaCapitalRenVO) {
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
			
			obterDefinicoes();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.obterDefinicoes.addEventListener(FaultEvent.FAULT, retornoObterDefinicoesError);
			servico.aprovar.addEventListener(ResultEvent.RESULT, retornoAprovar);
		}
		
		private function obterDefinicoes():void {
			reqDTO.dados.vo = this.vo;
			servico.obterDefinicoes(reqDTO);
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			this.vo = event.result.dados["vo"];
			
			dataCadastro.text = this.vo.dataInclusao;
			numContaCapital.text = this.vo.numContaCapital.toString();
			nomePessoa.text = this.vo.nomePessoa;
			cpfCnpj.text = FormatUtil.formataCPFCNPJ(this.vo.cpfCnpj);
			vlrSubs.text = FormataNumero.formata(this.vo.vlrSubs, 2);
			vlrInteg.text = FormataNumero.formata(this.vo.vlrInteg, 2); 
			vlrParcelas.text = FormataNumero.formata(this.vo.vlrParcelas, 2);
			qtdParcelas.text = this.vo.qtdParcelas.toString();
			diaDebito.text = this.vo.diaDebito.toString();
			observacao.text = this.vo.observacao;
			descTipoInteg.text = event.result.dados["descTipoIntegralizacao"];
			
			cboSituacaoCadastro.dataProvider = event.result.dados["cboSituacaoCadastro"] as ArrayCollection;
			
			controlarTela();
			
			initAnotacoesVigente();
			initAnotacoesBaixada();
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoObterDefinicoesError(evt:FaultEvent):void {
			Alerta.show(evt.fault.faultDetail, "ERRO", Alerta.ALERTA_ERRO);
			fecharJanela();
		}
		
		private function controlarEventos():void {
			btOk.addEventListener(MouseEvent.CLICK, aprovar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
		}
		
		private function controlarTela():void {
			numCco.visible = this.vo.tipoInteg == 2;
			numCcoLabel.visible = this.vo.tipoInteg == 2;
		}
		
		private function aprovar(evt:MouseEvent):void {
			
			if(validar()) {
				
				this.vo.observacao = this.observacao.text;

				this.reqDTO.dados.idProcedimento = this.cboSituacaoCadastro.selectedItem.codListaItem;
				this.reqDTO.dados.nomeProcedimento = this.cboSituacaoCadastro.selectedItem.descListaItem;
				this.reqDTO.dados.vo = this.vo;
				this.reqDTO.dados.idDocs = null;
				
				servico.aprovar(reqDTO);
			}
			
		}
		
		private function retornoAprovar(evt:ResultEvent):void {
			
			//Se proposta devolvida
			if(evt.result.dados["idSituacaoAtualProposta"] == 4) {
				Alerta.show("Dados gravados com sucesso. O cadastro foi (" + evt.result.dados["descSituacaoAtualProposta"] + ").", "DECISÃO", Alerta.ALERTA_SUCESSO, null, fechar);
			} else {
				Alerta.show("Dados gravados com sucesso. O cadastro foi (" + evt.result.dados["descSituacaoAtualProposta"] + "), deseja anexar um documento?", "DECISÃO", Alerta.ALERTA_PERGUNTA, null, confirmAnexarDoc, fechar);
			}
		}
		
		private function confirmAnexarDoc(evt:MouseEvent):void {
			/*
			var aprovacaoDoc:AprovacaoDocumento = new AprovacaoDocumento(this.vo, this.destino);
			var telaCadastro:Janela = new Janela();
			telaCadastro.title = "APROVAR CADASTRO CONTA CAPITAL - ANEXAR DOCUMENTAÇÃO";
			telaCadastro.removeAllChildren();
			telaCadastro.x = (this.stage.stageWidth / 100) * 20;
			telaCadastro.y = (this.stage.stageWidth / 100) * 2;
			telaCadastro.addChild(aprovacaoDoc);
			telaCadastro.abrir(this, true, false);
			*/

			var cadastro:AlteraCadastroContaCapitalRen = new AlteraCadastroContaCapitalRen(this.vo);
			cadastro.destino = this.destino;
			cadastro.setTabSelecionada(2);
			
			var telaCadastro:Janela = new Janela();
			telaCadastro.title = "ALTERAR CONTA CAPITAL";
			telaCadastro.removeAllChildren();
			telaCadastro.addChild(cadastro);
			telaCadastro.abrir(this, true, true);
			
			dispatchEvent(new Event("atualizarRegistrosConsulta", true));
			super.fecharJanela();
		}
		
		private function validar():Boolean {
			if(this.cboSituacaoCadastro.selectedItem.codListaItem == -1) {
				Alerta.show("O campo Situação deve ser preenchido.", "ATENÇÃO", Alerta.ALERTA_OK, this.cboSituacaoCadastro);
				return false;
			}
			
			if(this.cboSituacaoCadastro.selectedItem.descListaItem == "APROVADO" || this.cboSituacaoCadastro.selectedItem.descListaItem == "REJEITADO") {
				if(this.observacao.text.length == 0) {
					Alerta.show("O campo Observação deve ser preenchido.", "ATENÇÃO", Alerta.ALERTA_OK, this.observacao);
					return false;
				}
			}
			
			return true;
		}
		
		private function initAnotacoesVigente():void {
			var anotacoes:PainelListaAnotacoes = new PainelListaAnotacoes();
			anotacoes.percentHeight = 100;
			anotacoes.percentWidth = 100;
			anotacoes.listar(this.vo.idPessoa, this.vo.idInstituicao);
			anotVigente.addChild(anotacoes as DisplayObject);
		}
		
		private function initAnotacoesBaixada():void {
			var anotacoes:PainelListaAnotacoes = new PainelListaAnotacoes();
			anotacoes.percentHeight = 100;
			anotacoes.percentWidth = 100;
			anotacoes.baixadas = true;
			anotacoes.listar(this.vo.idPessoa, this.vo.idInstituicao);
			anotBaixada.addChild(anotacoes as DisplayObject);
		}
		
		private function fechar(evt:MouseEvent):void {
			this.fecharJanela();
		}
		
		override public function fecharJanela():void {
			dispatchEvent(new Event("atualizarRegistrosConsulta", true));
			super.fecharJanela();
		}
	}
}