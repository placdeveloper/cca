package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.List;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;

	public class ConfiguracaoAbaCoopPiloto {
		
		private var configuracao:ConfiguracaoReplicacaoCapital;
		public var cooperativasPiloto:Array;
		
		public function ConfiguracaoAbaCoopPiloto(configuracao:ConfiguracaoReplicacaoCapital) {
			this.configuracao = configuracao;
			
			this.configuracao.servico.obterCentrais.addEventListener(ResultEvent.RESULT, retornoObterCentrais);
			this.configuracao.servico.obterSingulares.addEventListener(ResultEvent.RESULT, retornoObterSingulares);
			
			this.configuracao.btTransferirOrigemDestino.addEventListener(MouseEvent.CLICK, transferirOrigemDestino);
			this.configuracao.btTransferirDestinoOrigem.addEventListener(MouseEvent.CLICK, transferirDestinoOrigem);
			this.configuracao.btSalvarCoopPiloto.addEventListener(MouseEvent.CLICK, salvarCoopPiloto);
			obterCentrais();
		}
		
		public function init(valor:String):void {
			this.cooperativasPiloto = valor.split(',');
			this.configuracao.txtDescConfigCoopPiloto.text = valor;
		}
		
		private function obterCentrais():void {
			this.configuracao.servico.obterCentrais(new RequisicaoReqDTO());
		}
		
		private function retornoObterCentrais(event:ResultEvent):void {
			var listaCentral:ArrayCollection = event.result.dados.listaCentral as ArrayCollection;
			listaCentral.addItemAt("SELECIONE", 0);
			this.configuracao.cmbCentral.dataProvider = listaCentral;
			this.configuracao.cmbCentral.addEventListener(Event.CHANGE, onChangeCmbCentral);
			limparSingulares();
			MostraCursor.removeBusyCursor();
			this.configuracao.abaPiloto.retornoObterCentrais(event);			
		}
		
		private function onChangeCmbCentral(evt:Event):void {
			limparSingulares();
			if (this.configuracao.cmbCentral.selectedIndex > 0) {
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.numCoopPai = this.configuracao.cmbCentral.selectedItem.codListaItem;
				dto.dados.descCoopPai = this.configuracao.cmbCentral.selectedItem.descListaItem;
				this.configuracao.servico.obterSingulares(dto);
			}
		}
		
		private function limparSingulares():void {
			this.configuracao.lstSingularesOrigem.dataProvider = new ArrayCollection();
			this.configuracao.lstSingularesDestino.dataProvider = new ArrayCollection();
		}
		
		private function retornoObterSingulares(event:ResultEvent):void {
			this.configuracao.lstSingularesOrigem.dataProvider = event.result.dados.listaSingular;
			this.configuracao.lstSingularesDestino.dataProvider = new ArrayCollection();
			selecionarOrigensConfiguradas();
			MostraCursor.removeBusyCursor();
		}
		
		private function selecionarOrigensConfiguradas():void {
			var indices:Array = [];
			var dados:ArrayCollection = this.configuracao.lstSingularesOrigem.dataProvider as ArrayCollection;
			for (var i:int=0; i<dados.length; i++) {
				for (var j:int=0; j<this.cooperativasPiloto.length; j++) {
					if (dados[i].codListaItem == this.cooperativasPiloto[j]) {
						indices.push(i);
					}
				}
			}
			if (indices.length > 0) {
				this.configuracao.lstSingularesOrigem.selectedIndices = indices;
				transferirDePara(this.configuracao.lstSingularesOrigem, this.configuracao.lstSingularesDestino);
			}
		}
		
		private function transferirOrigemDestino(event:MouseEvent=null):void {
			transferirDePara(this.configuracao.lstSingularesOrigem, this.configuracao.lstSingularesDestino);
		}
		
		private function transferirDestinoOrigem(event:MouseEvent=null):void {
			transferirDePara(this.configuracao.lstSingularesDestino, this.configuracao.lstSingularesOrigem);
		}
		
		public function transferirDePara(listDe:List, listPara:List):void {
			var dadosDe:ArrayCollection = listDe.dataProvider as ArrayCollection;
			var dadosPara:ArrayCollection = listPara.dataProvider as ArrayCollection;
			while (listDe.selectedIndices.length > 0) {
				var index:int = listDe.selectedIndices[0];
				dadosPara.addItem(dadosDe.getItemAt(index));
				dadosDe.removeItemAt(index);
			}
			ordenarList(listPara);
		}
		
		public function ordenarList(list:List):void {
			var dados:ArrayCollection = list.dataProvider as ArrayCollection;
			var dataSortField:SortField = new SortField();
			dataSortField.name = 'descListaItem';
			dataSortField.numeric = false;
			var dataSort:Sort = new Sort();
			dataSort.fields = [dataSortField];
			dados.sort = dataSort;
			dados.refresh();
		}
		
		private function salvarCoopPiloto(event:MouseEvent=null):void {
			var cooperativasConfiguradas:Array = [];
			var todasSingulares:ArrayCollection = new ArrayCollection();
			todasSingulares.addAll(this.configuracao.lstSingularesOrigem.dataProvider as ArrayCollection);
			todasSingulares.addAll(this.configuracao.lstSingularesDestino.dataProvider as ArrayCollection);
			// remover todos da edicao corrente
			for (var i:int=0; i<this.cooperativasPiloto.length; i++) {
				var deveAdicionar:Boolean = true;
				for (var j:int=0; j<todasSingulares.length; j++) {
					if (this.cooperativasPiloto[i] == todasSingulares[j].codListaItem) {
						deveAdicionar = false;
						break;
					}
				}
				if (deveAdicionar) {
					cooperativasConfiguradas.push(this.cooperativasPiloto[i]);
				}
			}
			// adicionar os da lista destino
			var selecionados:ArrayCollection = this.configuracao.lstSingularesDestino.dataProvider as ArrayCollection;
			for (i=0; i<selecionados.length; i++) {
				cooperativasConfiguradas.push(selecionados[i].codListaItem);
			}
			// atualizar
			cooperativasConfiguradas.sort();
			this.cooperativasPiloto = cooperativasConfiguradas;
			var valor:String = cooperativasConfiguradas.join(',');
			this.configuracao.txtDescConfigCoopPiloto.text = valor;
			// salvar
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idConfiguracaoReplicacaoCCA = 4;
			dto.dados.descConfiguracaoReplicacao = valor;
			this.configuracao.servico.gravarDados(dto);
		}
		
	}
	
}