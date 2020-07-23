package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelSituacaoMatriculaCCARenVO", RelSituacaoMatriculaCCARenVO);
	public class RelSituacaoMatriculaCCARenVO extends BancoobVO { 
		
		private var _idInstituicao: Number;

		private var _numContaCapitalInicial;

		private var _numContaCapitalFinal;
		
		private var _nomeCliente: String;
		
		private var _dataMatricula: Date;
		
		private var _dataSaida: Date;
		
		private var _dataSituacao: Date;
		
		private var _situacao: String;
		
		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}
		
		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}
		
		public function get nomeCliente():String
		{
			return _nomeCliente;
		}
		
		public function set nomeCliente(value:String):void
		{
			_nomeCliente = value;
		}
		
		public function get dataMatricula():Date
		{
			return _dataMatricula;
		}
		
		public function set dataMatricula(value:Date):void
		{
			_dataMatricula = value;
		}
		
		public function get dataSaida():Date
		{
			return _dataSaida;
		}
		
		public function set dataSaida(value:Date):void
		{
			_dataSaida = value;
		}
		
		public function get dataSituacao():Date
		{
			return _dataSituacao;
		}
		
		public function set dataSituacao(value:Date):void
		{
			_dataSituacao = value;
		}
		
		public function get situacao():String
		{
			return _situacao;
		}
		
		public function set situacao(value:String):void
		{
			_situacao = value;
		}

		public function get numContaCapitalInicial()
		{
			return _numContaCapitalInicial;
		}

		public function set numContaCapitalInicial(value):void
		{
			_numContaCapitalInicial = value;
		}

		public function get numContaCapitalFinal()
		{
			return _numContaCapitalFinal;
		}

		public function set numContaCapitalFinal(value):void
		{
			_numContaCapitalFinal = value;
		}


	}
}
