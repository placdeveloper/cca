package br.com.sicoob.sisbr.cca.util
{
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.sisbr.relatorios.componentes.preImpressao.FormatoImpressaoVO;

	public class PreImpressaoUtil
	{	
		private static const COD_FORMATO_XLS:String = "XLS";
		private static const DESCRICAO_FORMATO_XLS:String = "Excel (xls)";
		private static const COD_FORMATO_XLSX:String = "XLSX";
		private static const DESCRICAO_FORMATO_XLSX:String = "Excel 2007 (xlsx)";
		private static const COD_FORMATO_CSV:String = "CSV";
		private static const DESCRICAO_FORMATO_CSV:String = "CSV";
		private static const COD_FORMATO_PDF:String = "PDF";
		private static const DESCRICAO_FORMATO_PDF:String = "PDF";
		
		public function PreImpressaoUtil()
		{
		}
		
		public static function obterFormatosImpressao(): ArrayCollection {		
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

			return formatosImpressaoCollection;
		}
	}
}