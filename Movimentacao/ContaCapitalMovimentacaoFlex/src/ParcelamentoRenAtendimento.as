package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.ListEvent;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.TelaPlataformaAtendimentoBase;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.sisbr.cca.movimentacao.plataforma.ParcelamentoBotoesOpcoesView;
	import br.com.sicoob.sisbr.cca.movimentacao.plataforma.ParcelamentoEdicao;
	import br.com.sicoob.sisbr.cca.movimentacao.plataforma.ParcelamentoLista;
	import br.com.sicoob.sisbr.cca.vo.ParcelamentoRenVO;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO", ParcelamentoRenVO);
	public class ParcelamentoRenAtendimento extends TelaPlataformaAtendimentoBase {
		
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.movimentacao.servicos.ParcelamentoContaCapitalServico";
		private var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
		private var servicoRel:ServicoJava = new ServicoJava();
		private var telaLista:ParcelamentoLista;
		private var telaEdicao:ParcelamentoEdicao;
		
		public var botoesOpt:ParcelamentoBotoesOpcoesView = new ParcelamentoBotoesOpcoesView();
		
		private var janelaOutrosDados:Janela = new Janela();
		
		public function ParcelamentoRenAtendimento() {
			super();
			this.addEventListener("carregou", init);
		}
		
		private function init(event:Event):void {
			inicializaServico();
			configurarBotoes();
			configurarLabelTela();
		}
		
		private function inicializaServico():void {
			servicoRel.configurarDestino(this.destino);
			servicoRel.showBusyCursor = true;
			servicoRel.bloquearOperacao = true;
			servicoRel.source = SERVICO_REL_SOURCE;
			
			this.telaLista = new ParcelamentoLista();
			this.telaLista.destinoParent = this.destino;
			this.setTelaLista(telaLista);
			
			this.telaEdicao = new ParcelamentoEdicao();
			this.telaEdicao.destinoParent = this.destino;
			this.setTelaEdicao(telaEdicao);
		}
		
		private function configurarLabelTela():void {
			this.textoModulo = "PARCELAMENTO";
			this.iconModulo = "br/com/bancoob/imagens/icos/notes_32.png";
			this.textoOpcoes = "OPÇÕES";
			this.iconOpcoes = "br/com/bancoob/imagens/icos/opts_24.png";			
		}
		
		private function configurarBotoes():void {
			this.setBotoesAdicionais(botoesOpt);
			
			botoesOpt.btCancTodas.visible = false;
			botoesOpt.botManutencaoParcelamento.visible = false;
			botoesOpt.botBaixaViaBanco.visible = false;
			botoesOpt.botCancelamentoParcela.visible = false;
			botoesOpt.btCancTodas.addEventListener(MouseEvent.CLICK, this.telaEdicao.validaCancelarTodasParcelas);
			botoesOpt.botManutencaoParcelamento.addEventListener(MouseEvent.CLICK, this.telaEdicao.editarParcelamento);
			botoesOpt.botCancelamentoParcela.addEventListener(MouseEvent.CLICK, this.telaEdicao.validaCancelarParcela);
			botoesOpt.botBaixaViaBanco.addEventListener(MouseEvent.CLICK, this.telaEdicao.validaBaixarParcela);			
									
			listaBotoes.botNovo.teclaAtalho = null;
			listaBotoes.botVoltar.teclaAtalho = null;
			listaBotoes.botCancelar.teclaAtalho = null;
			listaBotoes.botAlterar.teclaAtalho = null;
			listaBotoes.botOk.teclaAtalho = null;
			
			super.botExcluirOculto = true; 
			super.botNovoOculto = true;
			super.botCancelarOculto = true;
			super.botOkOculto = true;
			super.botVerOculto = true;
		}		
		
		override protected function mostraBotoesMudaGrid(evt:ListEvent = null):void {
			super.mostraBotoesMudaGrid(evt);
			
			if(itemLista != null && itemLista.idContaCapital > 0) {
								
				dtoRel.dados.idPessoa = ProcuraClientePlataformaCAPES.getPessoaSelecionada().idPessoa;
				dtoRel.dados.idInstituicao = ProcuraClientePlataformaCAPES.getPessoaSelecionada().idInstituicao;
				dtoRel.dados.idContaCapital = itemLista.idContaCapital;
			}
		}
		
		override protected function listaCarregada(event:Event):void {
			super.listaCarregada(event);
			
			botoesOpt.botManutencaoParcelamento.visible = false;
			botoesOpt.botBaixaViaBanco.visible = false;
			botoesOpt.botCancelamentoParcela.visible = false;
			botoesOpt.btCancTodas.visible = false;
			
			listaBotoes.botNovo.visible = false;
		}
		
		override protected function alterarClicado(event:Event=null):void {
			
			super.alterarClicado(event);
			
			botoesOpt.botManutencaoParcelamento.visible = false;
			botoesOpt.botBaixaViaBanco.visible = false;
			botoesOpt.botCancelamentoParcela.visible = false;
			botoesOpt.btCancTodas.visible = false;
		}
		
	}
}