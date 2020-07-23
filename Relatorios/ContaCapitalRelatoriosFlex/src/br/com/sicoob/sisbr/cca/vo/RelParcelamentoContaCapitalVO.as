package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelParcelamentoContaCapitalVO", RelParcelamentoContaCapitalVO);

	public class RelParcelamentoContaCapitalVO extends BancoobVO {
		
		private var _idInstituicao:Number;
		private var _numContaCapitalInicial:Number;
		private var _numContaCapitalFinal:Number;
		private var _tipoParcelamento:Number;		
		private var _formaParcelamento:Number;
		private var _situacaoParcela:Number;
		private var _dataInicialVencimento:IDateTime;
		private var _dataFinalVencimento:IDateTime;
		private var _dataSituacao:IDateTime;
		private var _numPA:Number;
		private var _ordenacao:Number;
		
		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get numContaCapitalInicial():Number
		{
			return _numContaCapitalInicial;
		}

		public function set numContaCapitalInicial(value:Number):void
		{
			_numContaCapitalInicial = value;
		}

		public function get numContaCapitalFinal():Number
		{
			return _numContaCapitalFinal;
		}

		public function set numContaCapitalFinal(value:Number):void
		{
			_numContaCapitalFinal = value;
		}

		public function get tipoParcelamento():Number
		{
			return _tipoParcelamento;
		}

		public function set tipoParcelamento(value:Number):void
		{
			_tipoParcelamento = value;
		}

		public function get formaParcelamento():Number
		{
			return _formaParcelamento;
		}

		public function set formaParcelamento(value:Number):void
		{
			_formaParcelamento = value;
		}

		public function get situacaoParcela():Number
		{
			return _situacaoParcela;
		}

		public function set situacaoParcela(value:Number):void
		{
			_situacaoParcela = value;
		}

		public function get dataInicialVencimento():IDateTime
		{
			return _dataInicialVencimento;
		}

		public function set dataInicialVencimento(value:IDateTime):void
		{
			_dataInicialVencimento = value;
		}

		public function get dataFinalVencimento():IDateTime
		{
			return _dataFinalVencimento;
		}

		public function set dataFinalVencimento(value:IDateTime):void
		{
			_dataFinalVencimento = value;
		}

		public function get dataSituacao():IDateTime
		{
			return _dataSituacao;
		}

		public function set dataSituacao(value:IDateTime):void
		{
			_dataSituacao = value;
		}

		public function get numPA():Number
		{
			return _numPA;
		}

		public function set numPA(value:Number):void
		{
			_numPA = value;
		}

		public function get ordenacao():Number
		{
			return _ordenacao;
		}

		public function set ordenacao(value:Number):void
		{
			_ordenacao = value;
		}
	}
}