package
{
	import flash.events.MouseEvent;
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.input.Data;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.spool.SpoolTemporariosFactory;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.FormatoImpressaoVO;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressao;
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.PreImpressaoEvent;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.sisbr.cca.relatorios.fechamento.RelFechamentoView;
	import br.com.sicoob.sisbr.cca.vo.RelFechamentoVO;
	
	
	public class RelFechamento extends RelFechamentoView
	{
		private const COD_FORMATO_XLS:String = "XLS";
		private const DESCRICAO_FORMATO_XLS:String = "Excel (xls)";
		private const COD_FORMATO_XLSX:String = "XLSX";
		private const DESCRICAO_FORMATO_XLSX:String = "Excel 2007 (xlsx)";
		private const COD_FORMATO_CSV:String = "CSV";
		private const DESCRICAO_FORMATO_CSV:String = "CSV";
		private const COD_FORMATO_PDF:String = "PDF";
		private const DESCRICAO_FORMATO_PDF:String = "PDF";
		private const SERVICO_SOURCE:String = "br.com.sicoob.sisbr.cca.relatorios.servicos.FechRelatoriosServico";

		private var servico:ServicoJava = new ServicoJava();
		private var preImpressao:PreImpressao;
		private var formatoPreImpressao:String;
		
		private var relFechamentoVO:RelFechamentoVO;

		public function RelFechamento() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function init(event:FlexEvent):void {
			configurarServico();		
			configurarPreImpressao();
			configurarListeners();
			
			gridRelatorios.labelFunction = formataDataGrid;
		}
		
		private function configurarServico():void {
			servico.configurarDestino(this.destino);
			servico.showBusyCursor = true;
			servico.bloquearOperacao = true;
			servico.source = SERVICO_SOURCE;
		}
		
		private function configurarPreImpressao():void {		
			var formatosImpressaoCollection:ArrayCollection = new ArrayCollection();
			var formatoImpressao:FormatoImpressaoVO = new FormatoImpressaoVO();
			
			formatoImpressao.codFormato = COD_FORMATO_PDF;
			formatoImpressao.descricao = DESCRICAO_FORMATO_PDF;
			
			formatosImpressaoCollection.addItem(formatoImpressao);
			
			formatoImpressao = new FormatoImpressaoVO();
			formatoImpressao.codFormato = COD_FORMATO_XLS;
			formatoImpressao.descricao = DESCRICAO_FORMATO_XLS;
			
			formatosImpressaoCollection.addItem(formatoImpressao);
			
			formatoImpressao = new FormatoImpressaoVO();
			formatoImpressao.codFormato = COD_FORMATO_XLSX;
			formatoImpressao.descricao = DESCRICAO_FORMATO_XLSX;
			
			formatosImpressaoCollection.addItem(formatoImpressao);
			
			formatoImpressao = new FormatoImpressaoVO();
			formatoImpressao.codFormato = COD_FORMATO_CSV;
			formatoImpressao.descricao = DESCRICAO_FORMATO_CSV;
			
			formatosImpressaoCollection.addItem(formatoImpressao);	
			
			preImpressao = new PreImpressao(formatosImpressaoCollection);
		}
		
		private function configurarListeners():void {
			btVisualizar.addEventListener(MouseEvent.CLICK, escolherFormatoDoRelatorio);			
			btConsultar.addEventListener(MouseEvent.CLICK, consultarRelatorios);
			btFechar.addEventListener(MouseEvent.CLICK, function(){
				fecharJanela();
			});
			
			servico.buscarListaRelatorios.addEventListener(ResultEvent.RESULT, retornoBuscarListaRelatorios);
			servico.buscarListaRelatorios.addEventListener(FaultEvent.FAULT, retornoBuscarListaRelatoriosErro);	
			
			gridRelatorios.addEventListener(MouseEvent.CLICK, escolherRelatorio);
			gridRelatorios.addEventListener(MouseEvent.DOUBLE_CLICK, escolherFormatoDoRelatorio);
			
			preImpressao.addEventListener(PreImpressaoEvent.EVENTO_CONFIRMAR_OPCOES, emitirRelatorio);
		}
		
		private function escolherRelatorio(event : MouseEvent): void{
			btVisualizar.enabled = true;	
		}
		
		private function consultarRelatorios(event: MouseEvent):void {
			relFechamentoVO = new RelFechamentoVO();

			relFechamentoVO.data = (data as Data).selectedDate;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();			
			dto.dados.relFechamentoVO = relFechamentoVO;
			
			servico.buscarListaRelatorios(dto);
		}
		
		private function retornoBuscarListaRelatoriosErro(event:FaultEvent):void {
			gridRelatorios.dataProvider = null;	
			btVisualizar.enabled = false;
			MostraCursor.removeBusyCursor();
			Alerta.show(event.fault.faultString, "ATENÇÃO", Alerta.ALERTA_OK);
			return;
		}
		
		private function retornoBuscarListaRelatorios(event: ResultEvent):void {
			gridRelatorios.dataProvider = event.result.dados["lstDadosRetorno"];
			btVisualizar.enabled = false;
			MostraCursor.removeBusyCursor();
		}
		
		private function escolherFormatoDoRelatorio(event: MouseEvent):void {
			if(gridRelatorios.selectedItem == null){
				Alerta.show("Escolha um relatorio para exportar.", "ATENÇÃO", Alerta.ALERTA_OK);
				return;
			}
			
			preImpressao.abrirJanelaPreImpressao();
		}
		
		private function emitirRelatorio(event:PreImpressaoEvent):void {	
			formatoPreImpressao = event.formato.codFormato;
			var selected:Object = gridRelatorios.selectedItem;			
			var caminhoJasper:String = selected.caminho;
	
			// Espera resposta assincrona da fachada
			var token:AsyncToken = servico.emitirRelatorio(formatoPreImpressao, caminhoJasper);
			token.addResponder(new AsyncResponder(onResult,onFault));
		}
		
		private function onResult(event:ResultEvent,what:*):void {
			var nomeArquivo:String = event.result.dados["reportName"] + "." + formatoPreImpressao.toLowerCase();
			var arquivoStream:ByteArray = event.result.dados["reportStream"] as ByteArray;
			
			if(formatoPreImpressao == "CSV" || formatoPreImpressao == "TXT"){
				SpoolTemporariosFactory.getSpool("relAssinc").gravarItem(arquivoStream, nomeArquivo,true);
				return;
			}

			writeBytesToFile(nomeArquivo, arquivoStream);
		}
		
		private function onFault(response:FaultEvent, what:*):void {
			Alerta.show("Ocorreu um erro ao buscar o relatório." + response.fault.faultString, "ATENÇÃO", Alerta.ALERTA_OK);
		}
		
		private function writeBytesToFile(fileName:String, data:ByteArray):void 
		{ 
			// cria pasta temporaria 
			var outFile:File = File.createTempDirectory();
			
			// Arquivo para escrita
			outFile = outFile.resolvePath(fileName);  
			var outStream:FileStream = new FileStream();
			
			// Abre o output file stream em modo WRITE 
			outStream.open(outFile, FileMode.WRITE); 
			outStream.writeBytes(data, 0, data.length); 

			outStream.close(); 
			
			abrirArquivo(outFile.url);
		} 
		
		private function abrirArquivo(arquivo:String):void {
			SpoolTemporariosFactory.getSpool("relAssinc").abrirArquivo(arquivo);
		}
		
		private static function formataDataGrid(vlr:Object, cln:ColunaGrid):String{
			var retorno:String = "";
			
			switch(cln.dataField){
				case "data":
					if (vlr[cln.dataField] != null){
						retorno = FormataData.formataData(vlr[cln.dataField]);
						//retorno = vlr[cln.dataField];
					}
					break;
				default:
					if(vlr[cln.dataField] == null)
						retorno = "";
					else
						retorno = vlr[cln.dataField].toString();
					
			}
			
			return retorno;
		}
		
//		private function fechar(evt:MouseEvent):void {
//			this.fecharJanela();
//		}
	}
}