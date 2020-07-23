package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.sisbr.cca.TesteRenView;
	
	registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);
	public class TesteRen extends TesteRenView {
		
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.cadastro.servicos.CCARenServico";
		private var destinoVO:DestinoVO;
		private var servico:ServicoJava = new ServicoJava();
		
		private var dados:Object;
		
		public function TesteRen() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);				
		}
		
		private function init(event:FlexEvent):void {
			var listaClasses:ArrayCollection = new ArrayCollection();
			listaClasses.addItem("br.com.sicoob.sisbr.cca.api.negocio.delegates.ContaCapitalDelegate");
			listaClasses.addItem("br.com.sicoob.sisbr.cca.api.negocio.delegates.IntegralizacaoCapitalDelegate");
			cmbClazz.dataProvider = listaClasses;
			
			configurarServico();
			controlarServico();
			
			btOk.addEventListener(MouseEvent.CLICK, chamarServico);
		}
		
		private function configurarServico():void {
			//PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoRecuperado);
			onDestinoRecuperado(this.destino);

		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
			inicializaServico();
		}
		
		private function controlarServico():void {
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servico.listarMetodos.addEventListener(ResultEvent.RESULT, retornoListarMetodos);
			servico.chamarServico.addEventListener(ResultEvent.RESULT, retornoChamarServico);
		}
		
		private function inicializaServico():void {
			servico.configurarDestino(destinoVO);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Aguarde...", this);
			
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.clazz = cmbClazz.selectedItem.toString();
			
			servico.obterDefinicoes(reqDTO);
		}
		
		private function retornoObterDefinicoes(event:ResultEvent):void {
			
			cmbClazz.addEventListener(Event.CHANGE, listarMetodos);
			
			var listaMtds:ArrayCollection = new ArrayCollection();
			for(var key:String in event.result.dados) {
				listaMtds.addItem(key);
			}
			cmbMtds.dataProvider = listaMtds;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function listarMetodos(evt:Event):void {
			
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			reqDTO.dados.clazz = cmbClazz.selectedItem.toString();
			
			servico.listarMetodos(reqDTO);
		}
		
		private function retornoListarMetodos(event:ResultEvent):void {
			
			var listaMtds:ArrayCollection = new ArrayCollection();
			for(var key:String in event.result.dados) {
				listaMtds.addItem(key);
			}
			cmbMtds.dataProvider = listaMtds;
			
			MostraCursor.removeBusyCursor();
		}
		
		private function chamarServico(evt:MouseEvent):void {
			var reqDTO:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			reqDTO.dados.clazz = cmbClazz.selectedItem.toString();
			reqDTO.dados.metodo = cmbMtds.selectedItem.toString();
			reqDTO.dados.parametros = valorParametros.text;
			
			servico.chamarServico(reqDTO);
		}
		
		private function retornoChamarServico(event:ResultEvent):void {
			Alerta.show(event.result.dados.retorno, "MENSAGEM", Alerta.ALERTA_INFORMACAO);
		}
	}
}