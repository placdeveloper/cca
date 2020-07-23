package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.ListEvent;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.TelaPlataformaAtendimentoBase;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.BotoesOpcoes;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.CadastroContaCapitalEdicao;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.CadastroContaCapitalLista;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.DebitoPorTempoIndeterminado;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.DesligarContaCapitalEdicao;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.DevolucaoRenEdicao;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.OutrosDadosNovo;
	import br.com.sicoob.sisbr.cca.cadastro.plataforma.SubscricaoRenEdicao;
	
	public class CadastroContaCapitalAtendimento extends TelaPlataformaAtendimentoBase {
		
		private const SERVICO_REL_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelFichaPropostaMatriculaServico";
		private var dtoRel:ParametroDTO = new ParametroDTO();
		private var servicoRel:ServicoJava = new ServicoJava();
		private var telaLista:CadastroContaCapitalLista;
		private var telaEdicao:CadastroContaCapitalEdicao;
		
		private var botoesOpt:BotoesOpcoes = new BotoesOpcoes();
		
		private var janelaOutrosDados:Janela = new Janela();
		private var telaSubscricaoRenEdicao:SubscricaoRenEdicao = new SubscricaoRenEdicao();
		private var telaDevolucaoRenEdicao:DevolucaoRenEdicao = new DevolucaoRenEdicao();
		private var telaDesligarContaCapitalEdicao:DesligarContaCapitalEdicao = new DesligarContaCapitalEdicao();
		private var debitoPorTempoIndeterminado:DebitoPorTempoIndeterminado = new DebitoPorTempoIndeterminado();
		private var outrosDadosNovo:OutrosDadosNovo = new OutrosDadosNovo();
		
		public function CadastroContaCapitalAtendimento() {
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
			
			this.telaLista = new CadastroContaCapitalLista();
			this.telaLista.destinoParent = this.destino;
			this.telaLista.botaoNovo = listaBotoes.botNovo;
			this.setTelaLista(telaLista);
			
			this.telaEdicao = new CadastroContaCapitalEdicao();
			this.telaEdicao.destinoParent = this.destino;			
			this.setTelaEdicao(telaEdicao);
		}	
		
		override protected function abrirInclusao():void {
			super.abrirInclusao();
		}
		
		private function configurarLabelTela():void {
			this.textoModulo = "CADASTRO CONTA CAPITAL";
			this.iconModulo = "br/com/bancoob/imagens/icos/LimiteCredito.png";
			this.textoOpcoes = "OPÇÕES";
			this.iconOpcoes = "br/com/bancoob/imagens/icos/opts_24.png";			
		}
		
		private function configurarBotoes():void {
			this.setBotoesAdicionais(botoesOpt);
			
			botoesOpt.btFichaCadastral.visible = false;
			botoesOpt.btFichaCadastral.includeInLayout = false;
			botoesOpt.btFichaCadastral.addEventListener(MouseEvent.CLICK, gerarRelatorio);
			
			botoesOpt.btOutrosDados.visible = false;
			botoesOpt.btOutrosDados.includeInLayout = false;
			botoesOpt.btOutrosDados.addEventListener(MouseEvent.CLICK, abrirOutrosDadosNovo);
			
			botoesOpt.btSubscricao.visible = false;
			botoesOpt.btSubscricao.addEventListener(MouseEvent.CLICK, abrirSubscricao);
			
			botoesOpt.btDevolucao.visible = false;
			botoesOpt.btDevolucao.addEventListener(MouseEvent.CLICK, abrirDevolucao);
			
			botoesOpt.btDesligamento.visible = false;
			botoesOpt.btDesligamento.addEventListener(MouseEvent.CLICK, abrirDesligar);
			
			botoesOpt.btDebitoPorTempoIndeterminado.visible = false;
			botoesOpt.btDebitoPorTempoIndeterminado.addEventListener(MouseEvent.CLICK, abrirDebitoPorTempoIndeterminado);
			
			listaBotoes.botNovo.teclaAtalho = null;
			listaBotoes.botVoltar.teclaAtalho = null;
			listaBotoes.botCancelar.teclaAtalho = null;
			listaBotoes.botAlterar.teclaAtalho = null;
			listaBotoes.botOk.teclaAtalho = null;
			
			super.botExcluirOculto = true; 
		}
		
		override protected function mostraBotoesMudaGrid(evt:ListEvent = null):void {
			super.mostraBotoesMudaGrid(evt);
			
			if(itemLista != null && itemLista.idContaCapital > 0) {
				botoesOpt.btFichaCadastral.visible = true;
				botoesOpt.btFichaCadastral.includeInLayout = true;
				botoesOpt.btOutrosDados.visible = true;
				botoesOpt.btOutrosDados.includeInLayout = true;				
				botoesOpt.btDevolucao.visible = true;
				
				if (itemLista.idSituacaoContaCapital == 1) { // ativo
					botoesOpt.btSubscricao.visible = true;
					botoesOpt.btDesligamento.visible = true;	
					botoesOpt.btDevolucao.y = 180;
					botoesOpt.btDebitoPorTempoIndeterminado.visible = true;    //Validar essa regra
					botoesOpt.btDebitoPorTempoIndeterminado.includeInLayout = true;
					
				} else {
					botoesOpt.btDevolucao.y = 120;
				}
				
				dtoRel.dados.idPessoa = ProcuraClientePlataformaCAPES.getPessoaSelecionada().idPessoa;
				dtoRel.dados.idInstituicao = ProcuraClientePlataformaCAPES.getPessoaSelecionada().idInstituicao;
				dtoRel.dados.idContaCapital = itemLista.idContaCapital;
			}
		}
		
		private function gerarRelatorio(evt:MouseEvent):void {
			var formatoRelatorio:String = null;
			var exibirPreImpressao:Boolean = false;
			
//			RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirRelatorioFichaPropostaMatricula",
//				SERVICO_REL_SOURCE, dtoRel, "RelFichaPropostaMatricula", destinoVO, "Emitindo relatório",
//				formatoRelatorio, exibirPreImpressao);
			
			RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelFichaPropostaMatriculaServicoRemote", 
				dtoRel, "CCA_RelFichaPropostaMatricula", this.destino, "Emitindo relatório...", null, false);
		}
		
	
		private function abrirSubscricao (evt:MouseEvent):void {
			if(itemLista == null || itemLista.idContaCapital < 1) {
				return;
			}

			janelaOutrosDados = new Janela();
			janelaOutrosDados.title = "SUBSCRIÇÃO DE CONTA CAPITAL";
			janelaOutrosDados.icone = "br/com/bancoob/imagens/icos/listaAplicativos/contacapital_16.png";	
			
			telaSubscricaoRenEdicao = new SubscricaoRenEdicao();
			telaSubscricaoRenEdicao.destinoParent = this.destino;
			janelaOutrosDados.addChild(telaSubscricaoRenEdicao);	
			
			telaSubscricaoRenEdicao.pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			telaSubscricaoRenEdicao.numContaCapital = itemLista.numContaCapital;
			telaSubscricaoRenEdicao.idContaCapital = itemLista.idContaCapital;
			
			janelaOutrosDados.abrir(this, true, true);

		}
		
		private function abrirDevolucao (evt:MouseEvent):void {
			if(itemLista == null || itemLista.idContaCapital < 1) {
				return;
			}
			
			janelaOutrosDados = new Janela();
			janelaOutrosDados.title = "DEVOLUÇÃO DE CONTA CAPITAL";
			janelaOutrosDados.icone = "br/com/bancoob/imagens/icos/listaAplicativos/contacapital_16.png";	
						
			telaDevolucaoRenEdicao = new DevolucaoRenEdicao();
			telaDevolucaoRenEdicao.destinoParent = this.destino;			
			janelaOutrosDados.addChild(telaDevolucaoRenEdicao);	
			
			telaDevolucaoRenEdicao.pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			telaDevolucaoRenEdicao.numContaCapital = itemLista.numContaCapital;
			telaDevolucaoRenEdicao.idContaCapital = itemLista.idContaCapital;
			telaDevolucaoRenEdicao.descSituacaoContaCapital.text = itemLista.descSituacaoContaCapital;
			
			janelaOutrosDados.abrir(this, true, true);
			
		}
		
		private function abrirDesligar (evt:MouseEvent):void {
			if(itemLista == null || itemLista.idContaCapital < 1) {
				return;
			}
			
			janelaOutrosDados = new Janela();
			janelaOutrosDados.title = "DESLIGAR ASSOCIADO";
			janelaOutrosDados.icone = "br/com/bancoob/imagens/icos/listaAplicativos/contacapital_16.png";		
			
			telaDesligarContaCapitalEdicao = new DesligarContaCapitalEdicao();		
			telaDesligarContaCapitalEdicao.destinoParent = this.destino;
			janelaOutrosDados.addChild(telaDesligarContaCapitalEdicao);			
			
			telaDesligarContaCapitalEdicao.pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			telaDesligarContaCapitalEdicao.numContaCapital = itemLista.numContaCapital;
			telaDesligarContaCapitalEdicao.idContaCapital = itemLista.idContaCapital;
			telaDesligarContaCapitalEdicao.telaCadastroContaCapitalAtendimento = this;
			
			janelaOutrosDados.abrir(this, true, true);
			
		}	
		
		private function abrirDebitoPorTempoIndeterminado (evt:MouseEvent):void {
			if(itemLista == null || itemLista.idContaCapital < 1) {
				return;
			}
			
			janelaOutrosDados = new Janela();
			janelaOutrosDados.title = "DÉBITO POR TEMPO INDETERMINADO";
			janelaOutrosDados.icone = "br/com/bancoob/imagens/icos/listaAplicativos/contacapital_16.png";		
			
			debitoPorTempoIndeterminado = new DebitoPorTempoIndeterminado();	
			debitoPorTempoIndeterminado.destinoParent = this.destino;			
			janelaOutrosDados.addChild(debitoPorTempoIndeterminado);			
			
			debitoPorTempoIndeterminado.pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			debitoPorTempoIndeterminado.numContaCapital.text = itemLista.numContaCapital;
			debitoPorTempoIndeterminado.idContaCapital = itemLista.idContaCapital;
			debitoPorTempoIndeterminado.telaCadastroContaCapitalAtendimento = this;
			
			janelaOutrosDados.abrir(this, true, true);
			
		}	
		
		private function abrirOutrosDadosNovo(evt:MouseEvent):void {
			if(itemLista == null || itemLista.idContaCapital < 1) {
				return;
			}
			
			janelaOutrosDados = new Janela();
			
			janelaOutrosDados.title = "OUTROS DADOS";
			janelaOutrosDados.icone = "br/com/bancoob/imagens/icos/listaAplicativos/contacapital_16.png";		
			
			outrosDadosNovo = new OutrosDadosNovo();	
			outrosDadosNovo.destinoParent = this.destino;			
			janelaOutrosDados.addChild(outrosDadosNovo);			
			
			outrosDadosNovo.pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			outrosDadosNovo.numMatricula.text = itemLista.numContaCapital;
				
			janelaOutrosDados.abrir(this, true, true);
	
		}
		
		override protected function listaCarregada(event:Event):void {
			super.listaCarregada(event);
			
			if(_telaLista.obterGrid().dataProvider.length > 0) {
				listaBotoes.botNovo.enabled = false;
			}
		}
		
		override protected function cancelarClicado(event:MouseEvent = null):void {
			super.cancelarClicado(event);
			
			if(telaEdicao.isEdicao) {
				telaEdicao.carregarRegistro(telaEdicao.vo);				
			} else {
				telaEdicao.novoRegistro();
			}
		}
		
		override protected function adicionarClicado(event:MouseEvent=null):void {
			super.adicionarClicado(event);
		}
		
		override protected function alterarClicado(event:Event=null):void {
			super.alterarClicado(event);
		}
		
		public function recarregarLista():void {
			botoesOpt.btFichaCadastral.visible = false;
			botoesOpt.btOutrosDados.visible = false;
			botoesOpt.btSubscricao.visible = false;
			botoesOpt.btDevolucao.visible = false;
			botoesOpt.btDesligamento.visible = false;
			botoesOpt.btDebitoPorTempoIndeterminado.visible = false;
			this.telaLista.consultar();
		}
		
	}
}