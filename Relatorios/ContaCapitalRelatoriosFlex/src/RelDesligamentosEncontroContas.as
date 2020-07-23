package
{
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.PesquisaContaCapitalVO;
	import br.com.sicoob.sisbr.cca.comum.pesquisa.SelecionarContaCapitalEvent;
	import br.com.sicoob.sisbr.cca.relatorios.desligamentosencontrocontas.RelDesligamentosEncontroContasView;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	public class RelDesligamentosEncontroContas extends RelDesligamentosEncontroContasView {
		
		private var servico:ServicoJava = new ServicoJava();
		private var cca:PesquisaContaCapitalVO;
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.RelDesligamentoAssociadoServico";
		
		public function RelDesligamentosEncontroContas() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);		
		} 
		
		private function init(event:FlexEvent):void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			servico.obterDefinicoesEncontroContas.addEventListener(ResultEvent.RESULT, resultObterDefinicoes);
			
			optTermoDesligamento.addEventListener(MouseEvent.CLICK, onClickOptTipoRelatorio);
			optRelatorioDesligamento.addEventListener(MouseEvent.CLICK, onClickOptTipoRelatorio);
			
			procurarCCA.addEventListener(SelecionarContaCapitalEvent.ITEM_SELECIONADO, retornoProcurarCCA);
			
			btOk.addEventListener(MouseEvent.CLICK, emitirRelatorio);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fechar);
			
			obterDefinicoes();
			inicializarTela();
		}
		
		private function retornoProcurarCCA(event:SelecionarContaCapitalEvent):void {
			cca = procurarCCA.resultadoPesquisaVO;
		}
		
		private function cancelar(evt:MouseEvent): void{
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			servico.obterDefinicoesEncontroContas(new RequisicaoReqDTO());
		}
		
		private function resultObterDefinicoes(evt:ResultEvent):void {
			var dados:Object = evt.result.dados;
			
			procurarCCAInicial.txtNumCCA.text = dados.numContaCapitalInicial.toString();
			procurarCCAInicial.idInstituicao = dados.idInstituicao;
			procurarCCAInicial.procurarCCA(null);
			
			procurarCCAFinal.txtNumCCA.text = dados.numContaCapitalFinal.toString();
			procurarCCAFinal.idInstituicao = dados.idInstituicao;
			procurarCCAFinal.procurarCCA(null);
			
			procurarCCA.txtNumCCA.text = dados.numContaCapitalInicial.toString();
			procurarCCA.idInstituicao = dados.idInstituicao;
			procurarCCA.procurarCCA(null);
			
			MostraCursor.removeBusyCursor();
		}
		
		private function emitirRelatorio(evt:MouseEvent): void {
			if(optRelatorioDesligamento.selected){
				emitirRelatorioDesligamento();
			} else {
				emitirTermoDesligamento();
			}
		}
		
		private function emitirTermoDesligamento() : void{
			if(validarTermoDesligamento()){
				//jboss
				//var dtoRel:RequisicaoReqDTO = new RequisicaoReqDTO();
				//was
				var dtoRel:ParametroDTO = new ParametroDTO();
				
				//comum
				dtoRel.dados.idContaCapital = cca.idContaCapital;
				
				//jboss
				//RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirDesligamentoEncontroContas",
				//	SERVICO_SOURCE, dtoRel, "RelDesligamentoEncontroContas", destino, "Emitindo relatório...",
				//	null, false);
				
				//was
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelDesligamentoEncontroContasServicoRemote", 
					dtoRel, "CCA_RelDesligamentoEncontroContas", destino, "Emitindo relatório...", null, false);
			}
		}
		
		private function emitirRelatorioDesligamento() : void{
			if(validarRelatorioDesligamento()){
				//was
				var dto:ParametroDTO = new ParametroDTO();
				
				//jboss
				//var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				
				//comum
				dto.dados.numContaCapitalInicial = new Number(procurarCCAInicial.txtNumCCA.text);
				dto.dados.numContaCapitalFinal = new Number(procurarCCAFinal.txtNumCCA.text);
				
				//jboss
				//RelatorioUtil.getRelatorioUtil().emitirRelatorio("emitirRelatorioDesligamentoEncontroContasLista",
				//						SERVICO_SOURCE, dto, "RelDesligamentoEncontroContasLista", this.destino, "Emitindo relatório...",
				//						null, true);
				
				RelatorioUtil.create().emitirRelatorio("cca_relatorios/RelDesligamentoEncontroContasListaServicoRemote", 
					dto, "CCA_RelDesligamentoEncontroContasLista", this.destino, "Emitindo relatório...", null, true);
				
			}
		}
		
		private function validarRelatorioDesligamento():Boolean {
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
			
			return true;
		}
		
		private function validarTermoDesligamento() : Boolean {
			if(!procurarCCA.isContaCapitalSelecionada()) {
				focusManager.setFocus(procurarCCA.txtNumCCA);
				Alerta.show("O campo Conta Capital é obrigatório", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			
			return true;
		}
		
		private function fechar(evt:MouseEvent):void {
			this.fecharJanela();
		}
		
		private function onClickOptTipoRelatorio(evt:MouseEvent): void {
			//obterDefinicoes();
			inicializarTela();
		}
		
		private function inicializarTela(): void{
			if (optTermoDesligamento.selected) {
				relatorioDesligamento.includeInLayout = false;
				relatorioDesligamento.visible = false;
				termoDesligamento.visible = true;
				termoDesligamento.includeInLayout = true;
			} else {
				relatorioDesligamento.includeInLayout = true;
				relatorioDesligamento.visible = true;
				termoDesligamento.visible = false;
				termoDesligamento.includeInLayout = false;
			}
		}
	}
}