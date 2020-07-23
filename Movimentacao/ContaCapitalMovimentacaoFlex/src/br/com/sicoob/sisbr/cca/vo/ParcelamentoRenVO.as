package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: antonio.genaro
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.ParcelamentoRenVO", ParcelamentoRenVO);
	public class ParcelamentoRenVO extends BancoobVO {
		
		private var _numParcela:Number;
		private var _dataVencimento:String;	
		private var _valorParcela:Number;
		private var _idTipoInteg:Number;
		private var _descNumMatriculaFunc:String;
		private var _numContaCorrente:Number;		
		private var _dataVencimentoOrdenacao:String;
		
		private var _dataSituacao:String;
		private var _descSituacao:String;
		private var _descFormaPagamento:String;		
		
		private var _numContaCapital:Number;		
		private var _descTipoParcelamento:String;		
		private var _numParcelamento:Number;		
		private var _qtdParcelas:Number;		
		private var _valorTotal:Number;		
		private var _valorAberto:Number;		
		private var _idTipoParcelamento:Number;		
		private var _idSituacaoParcelamento:Number;		
		private var _idParcelamento:Number;		
		private var _idContaCapital;
		private var _selecionado:Boolean=false;
		private var _habilitado:Boolean=true;
		private var _idMotivoDevolucao:Number;		
		
		public function get idMotivoDevolucao():Number
		{
			return _idMotivoDevolucao;
		}

		public function set idMotivoDevolucao(value:Number):void
		{
			_idMotivoDevolucao = value;
		}

		public function get idContaCapital()
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value):void
		{
			_idContaCapital = value;
		}

		public function get selecionado():Boolean
		{
			return _selecionado;
		}

		public function set selecionado(value:Boolean):void
		{
			_selecionado = value;
		}
		
		public function get habilitado():Boolean
		{
			return _habilitado;
		}
		
		public function set habilitado(value:Boolean):void
		{
			_habilitado = value;
		}

		public function get idParcelamento():Number
		{
			return _idParcelamento;
		}

		public function set idParcelamento(value:Number):void
		{
			_idParcelamento = value;
		}

		public function get idSituacaoParcelamento():Number
		{
			return _idSituacaoParcelamento;
		}

		public function set idSituacaoParcelamento(value:Number):void
		{
			_idSituacaoParcelamento = value;
		}

		public function get idTipoParcelamento():Number
		{
			return _idTipoParcelamento;
		}

		public function set idTipoParcelamento(value:Number):void
		{
			_idTipoParcelamento = value;
		}

		public function get valorAberto():Number
		{
			return _valorAberto;
		}

		public function set valorAberto(value:Number):void
		{
			_valorAberto = value;
		}

		public function get valorTotal():Number
		{
			return _valorTotal;
		}

		public function set valorTotal(value:Number):void
		{
			_valorTotal = value;
		}

		public function get qtdParcelas():Number
		{
			return _qtdParcelas;
		}

		public function set qtdParcelas(value:Number):void
		{
			_qtdParcelas = value;
		}

		public function get numParcelamento():Number
		{
			return _numParcelamento;
		}

		public function set numParcelamento(value:Number):void
		{
			_numParcelamento = value;
		}

		public function get descTipoParcelamento():String
		{
			return _descTipoParcelamento;
		}

		public function set descTipoParcelamento(value:String):void
		{
			_descTipoParcelamento = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get descFormaPagamento():String
		{
			return _descFormaPagamento;
		}

		public function set descFormaPagamento(value:String):void
		{
			_descFormaPagamento = value;
		}

		public function get descSituacao():String
		{
			return _descSituacao;
		}

		public function set descSituacao(value:String):void
		{
			_descSituacao = value;
		}

		public function get dataSituacao():String
		{
			return _dataSituacao;
		}

		public function set dataSituacao(value:String):void
		{
			_dataSituacao = value;
		}

		public function get dataVencimento():String
		{
			return _dataVencimento;
		}

		public function set dataVencimento(value:String):void
		{
			_dataVencimento = value;
		}

		public function get dataVencimentoOrdenacao():String
		{
			return _dataVencimentoOrdenacao;
		}

		public function set dataVencimentoOrdenacao(value:String):void
		{
			_dataVencimentoOrdenacao = value;
		}

		public function get numContaCorrente():Number
		{
			return _numContaCorrente;
		}

		public function set numContaCorrente(value:Number):void
		{
			_numContaCorrente = value;
		}

		public function get descNumMatriculaFunc():String
		{
			return _descNumMatriculaFunc;
		}

		public function set descNumMatriculaFunc(value:String):void
		{
			_descNumMatriculaFunc = value;
		}

		public function get idTipoInteg():Number
		{
			return _idTipoInteg;
		}

		public function set idTipoInteg(value:Number):void
		{
			_idTipoInteg = value;
		}

		public function get valorParcela():Number
		{
			return _valorParcela;
		}

		public function set valorParcela(value:Number):void
		{
			_valorParcela = value;
		}

		public function get numParcela():Number
		{
			return _numParcela;
		}

		public function set numParcela(value:Number):void
		{
			_numParcela = value;
		}

	}
}