package br.com.sicoob.sisbr.cca.vo
{
	public class RelFechamentoVO
	{
		public function RelFechamentoVO() {			
		}
		
		private var _idInstituicao:Number;
		private var _numProduto:Number;
		private var _data:Date;
		
		private var _codRelatorio:String;
		private var _descricao:String;
		

		public function get descricao():String
		{
			return _descricao;
		}

		public function set descricao(value:String):void
		{
			_descricao = value;
		}

		public function get codRelatorio():String
		{
			return _codRelatorio;
		}

		public function set codRelatorio(value:String):void
		{
			_codRelatorio = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}
		
		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}
		
		public function get numProduto():Number
		{
			return _numProduto;
		}
		
		public function set numProduto(value:Number):void
		{
			_numProduto = value;
		}
		
		public function get data():Date
		{
			return _data;
		}
		
		public function set data(value:Date):void
		{
			_data = value;
		}
		
		
	}
}