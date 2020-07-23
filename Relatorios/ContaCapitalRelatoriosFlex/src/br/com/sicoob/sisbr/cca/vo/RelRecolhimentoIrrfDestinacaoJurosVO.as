package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelRecolhimentoIrrfDestinacaoJurosVO", RelRecolhimentoIrrfDestinacaoJurosVO);
	
	public class RelRecolhimentoIrrfDestinacaoJurosVO extends BancoobVO	{
		private var _idInstituicao: Number;
		
		private var _todos: Boolean;
		
		private var _numContaCapital: Number;
		
		private var _anoBase: Number;	
		
		private var _numPac: Number;

		private var _agruparPorPA: Boolean;
		
		private var _ordenarPor: Number;

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get todos():Boolean
		{
			return _todos;
		}

		public function set todos(value:Boolean):void
		{
			_todos = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get anoBase():Number
		{
			return _anoBase;
		}

		public function set anoBase(value:Number):void
		{
			_anoBase = value;
		}

		public function get numPac():Number
		{
			return _numPac;
		}

		public function set numPac(value:Number):void
		{
			_numPac = value;
		}

		public function get agruparPorPA():Boolean
		{
			return _agruparPorPA;
		}

		public function set agruparPorPA(value:Boolean):void
		{
			_agruparPorPA = value;
		}

		public function get ordenarPor():Number
		{
			return _ordenarPor;
		}

		public function set ordenarPor(value:Number):void
		{
			_ordenarPor = value;
		}
	}
}