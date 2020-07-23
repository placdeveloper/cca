package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;

	public class ConfiguracaoAbaReplicacao {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		
		private var listaConfiguracao:ArrayCollection = new ArrayCollection();		
		private var _dadosDefinicoes:Object = new Object();
		
		public function ConfiguracaoAbaReplicacao(configuracao:ConfiguracaoReplicacaoCapital) {
			this.configuracao = configuracao;
			
			this.configuracao.servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			this.configuracao.servico.gravarDados.addEventListener(ResultEvent.RESULT, retornoGravarDados);
			
			this.configuracao.btRecarregar.addEventListener(MouseEvent.CLICK, recarregar);
			this.configuracao.btOK.addEventListener(MouseEvent.CLICK, gravarDados);
			
			this.configuracao.grdConfigucacao.addEventListener(MouseEvent.CLICK, preencherTxt);
		}
		
		private function recarregar(evt:MouseEvent):void {
			this.configuracao.txtDescConfiguracaoReplicacao.text = "";
			obterDefinicoes();
		}
		
		public function obterDefinicoes():void {
			this.configuracao.servico.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			_dadosDefinicoes = event.result.dados;			
			
			this.listaConfiguracao = new ArrayCollection();			
			this.listaConfiguracao.list = event.result.dados["lstConfiguracaoReplicacao"] as ArrayCollection;					
			
			this.configuracao.grdConfigucacao.dataProvider = listaConfiguracao.list;				
			MostraCursor.removeBusyCursor();		
			
			// aba configuracao coop piloto
			this.configuracao.abaCoopPiloto.init(recuperarValorCoopPiloto());
		}
		
		private function recuperarValorCoopPiloto():String {
			for (var i:int=0; i<this.listaConfiguracao.length; i++) {
				if (this.listaConfiguracao[i].idConfiguracaoReplicacaoCCA == 4) {
					return this.listaConfiguracao[i].descConfiguracaoReplicacao;
				}
			}
			return 'Parâmetro Cooperativas Piloto não encontrado';
		}
		
		private function gravarDados(evt:MouseEvent):void {
			if(validarCampos()){
				var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
				
				dto.dados.idConfiguracaoReplicacaoCCA = this.configuracao.grdConfigucacao.selectedItem.idConfiguracaoReplicacaoCCA;
				dto.dados.descConfiguracaoReplicacao = this.configuracao.txtDescConfiguracaoReplicacao.text;
				
				this.configuracao.servico.gravarDados(dto);					
				this.configuracao.txtDescConfiguracaoReplicacao.text = "";
			}
		}	
		
		private function validarCampos():Boolean {		
			if(this.configuracao.grdConfigucacao.selectedItem == null){
				Alerta.show("Selecione um registro para alteração.", "ATENÇÃO", Alerta.ALERTA_OK);
				return false;
			}
			if(this.configuracao.grdConfigucacao.selectedItem.idConfiguracaoReplicacaoCCA != 4){
				if(this.configuracao.txtDescConfiguracaoReplicacao.text == ""){
					Alerta.show("O campo valor é obrigatório.", "ATENÇÃO", Alerta.ALERTA_OK);
					return false;
				}				
			}
			return true;
		}
		
		private function retornoGravarDados(event:ResultEvent):void {
			if(event.result.dados["msg"]) {
				Alerta.show('Erro ao gravar parâmetro.', "ATENÇÃO", Alerta.ALERTA_OK);
				MostraCursor.removeBusyCursor();
				return;
			}									
			Alerta.show("Dados alterados com sucesso!", "SUCESSO", Alerta.ALERTA_OK);
			this.configuracao.servico.obterDefinicoes(new RequisicaoReqDTO());			
		}
		
		private function preencherTxt(evt:MouseEvent):void {
			if (this.configuracao.grdConfigucacao.selectedItem != null) {
				this.configuracao.txtDescConfiguracaoReplicacao.text = this.configuracao.grdConfigucacao.selectedItem.descConfiguracaoReplicacao;
			}
		}
		
	}
}