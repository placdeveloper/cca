package br.com.sicoob.sisbr.cca.cadastro.plataforma{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IEdicaoPlataformaAtendimento;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	public class OutrosDadosNovo extends OutrosDadosNovoView implements IEdicaoPlataformaAtendimento{
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.OutrosDadosContaCapitalServico";
		
		private var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servico:ServicoJava = new ServicoJava();
		public var pessoaSelecionada:PessoaPlataformaVO;
		public var idDebitoContaCapital:Number;						
		public var destinoParent:DestinoVO;		
		private var _dadosDefinicoes:Object = new Object();
		
		public function OutrosDadosNovo() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			inicializaServico();
			obterDefinicoes();
			controlarEventos();
		}
	
		private function obterDefinicoes():void {
			reqDTO.dados.numMatricula = new Number(numMatricula.text);
			reqDTO.dados.idPessoa = pessoaSelecionada.idPessoa;
			reqDTO.dados.idInstituicao = pessoaSelecionada.idInstituicao;
			servico.obterDefinicoes(reqDTO);
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
			servico.alterar.addEventListener(ResultEvent.RESULT, retornoAlterar);
		}
		
		private function controlarEventos():void {
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			btOk.addEventListener(MouseEvent.CLICK, alterar);
		}
			
		private function retornoObterDefinicoes(event:ResultEvent):void {
			
			_dadosDefinicoes = event.result.dados;		
			
			cboCco.dataProvider = _dadosDefinicoes["comboCco"] as ArrayCollection;
			cboCco.procuraItemPorNome(_dadosDefinicoes["numContaCorrente"], "codListaItem");							
			chkParticipaRateio.selected  = _dadosDefinicoes["bolParticipaRateio"];			
			chkRestricaoRateio.selected  = _dadosDefinicoes["bolRestricaoRateio"];			
			chkDireitoVoto.selected  = _dadosDefinicoes["bolDireitoVoto"];						
			nome.text = StringUtils.trim(pessoaSelecionada.nomeCompleto);			
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoAlterar(event:ResultEvent):void {
			Alerta.show("Dados gravados com sucesso.", "SUCESSO", Alerta.ALERTA_SUCESSO, null);
			MostraCursor.removeBusyCursor();
			fechar(null);
		}
		
		/**
		 * Encerra funcionalidade
		 */
		private function fechar(evt:MouseEvent):void {
			super.fecharJanela();
		}
		
		private function alterar(me:MouseEvent):void {
			reqDTO = new RequisicaoReqDTO();
			
			reqDTO.dados.numMatricula = new Number(numMatricula.text);
			reqDTO.dados.numContaCorrente = cboCco.text;
			reqDTO.dados.bolParticipaRateio = chkParticipaRateio.selected;
			reqDTO.dados.bolRestricaoRateio = chkRestricaoRateio.selected;
			reqDTO.dados.bolDireitoVoto = chkDireitoVoto.selected;
			
			servico.alterar(reqDTO);
		}
			
		public function gravarRegistro():void{}
		
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
}}