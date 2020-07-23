package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelSaldoAtualVO", RelSaldoAtualVO);

	public class RelSaldoAtualVO extends BancoobVO {
		
		private var _idInstituicao: Number;
		
		private var _numContaCapitalInicial: Number;
		
		private var _numContaCapitalFinal: Number;
		
		private var _situacaoConta:Number;	
		
		private var _cadastrosAprovados: Boolean;
		
		private var _numPA: Number;
		
		private var _ordenacao: Number;
		
		private var _empresa: Number;
		
		private var _agruparPorPA: Boolean;
		
		public function get agruparPorPA():Boolean
		{
			return _agruparPorPA;
		}

		public function set agruparPorPA(value:Boolean):void
		{
			_agruparPorPA = value;
		}

		public function get empresa():Number
		{
			return _empresa;
		}

		public function set empresa(value:Number):void
		{
			_empresa = value;
		}

		/** The ordenacao. */
		public function get ordenacao():Number
		{
			return _ordenacao;
		}

		public function set ordenacao(value:Number):void
		{
			_ordenacao = value;
		}

		public function get numPA():Number
		{
			return _numPA;
		}

		public function set numPA(value:Number):void
		{
			_numPA = value;
		}

		public function get cadastrosAprovados():Boolean
		{
			return _cadastrosAprovados;
		}

		public function set cadastrosAprovados(value:Boolean):void
		{
			_cadastrosAprovados = value;
		}

		public function get numContaCapitalFinal():Number
		{
			return _numContaCapitalFinal;
		}

		public function set numContaCapitalFinal(value:Number):void
		{
			_numContaCapitalFinal = value;
		}

		public function get numContaCapitalInicial():Number
		{
			return _numContaCapitalInicial;
		}

		public function set numContaCapitalInicial(value:Number):void
		{
			_numContaCapitalInicial = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get situacaoConta():Number
		{
			return _situacaoConta;
		}

		public function set situacaoConta(value:Number):void
		{
			_situacaoConta = value;
		}


	}
}