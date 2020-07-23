package br.com.sicoob.sisbr.cca.comum.vo
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;

	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO", DebitoIndeterminadoRenVO);
	public class DebitoIndeterminadoRenVO extends BancoobVO {
		
		private var _idDebitoContaCapital:Number;
		private var _tipoInclusao:Number;
		private var _idContaCapital:Number;
		private var _idInstituicao:Number;
		private var _numContaCapital:Number;
		private var _idPessoaLegado:Number;
		private var _idPessoa:Number;
		private var _tipoInteg:Number;
		private var _numContaCorrente:Number;
		private var _formaDebito:Number;
		private var _qtdCotas:Number;
		private var _percentual:Number;
		private var _vlrDebito:Number;
		private var _periodoDebito:Number;
		private var _dataInicialDeb:IDateTime;
		private var _numPeriodo:Number;
		private var _nome:String;
		private var _codTipoPessoa:Number;
		private var _descMatriculaFunc:String;
		
		private var _idsContaCapital:ArrayCollection;
		private var _idsDebitoContaCapital:ArrayCollection;
		private var _idsNumMatricula:ArrayCollection;
		private var _contasCorrente:ArrayCollection;
		
		public function get idPessoa():Number
		{
			return _idPessoa;
		}

		public function set idPessoa(value:Number):void
		{
			_idPessoa = value;
		}

		public function get idPessoaLegado():Number
		{
			return _idPessoaLegado;
		}

		public function set idPessoaLegado(value:Number):void
		{
			_idPessoaLegado = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

		public function get tipoInclusao():Number
		{
			return _tipoInclusao;
		}

		public function set tipoInclusao(value:Number):void
		{
			_tipoInclusao = value;
		}

		public function get tipoInteg():Number
		{
			return _tipoInteg;
		}

		public function set tipoInteg(value:Number):void
		{
			_tipoInteg = value;
		}

		public function get numContaCorrente():Number
		{
			return _numContaCorrente;
		}

		public function set numContaCorrente(value:Number):void
		{
			_numContaCorrente = value;
		}

		public function get formaDebito():Number
		{
			return _formaDebito;
		}

		public function set formaDebito(value:Number):void
		{
			_formaDebito = value;
		}

		public function get qtdCotas():Number
		{
			return _qtdCotas;
		}

		public function set qtdCotas(value:Number):void
		{
			_qtdCotas = value;
		}

		public function get percentual():Number
		{
			return _percentual;
		}

		public function set percentual(value:Number):void
		{
			_percentual = value;
		}

		public function get vlrDebito():Number
		{
			return _vlrDebito;
		}

		public function set vlrDebito(value:Number):void
		{
			_vlrDebito = value;
		}

		public function get periodoDebito():Number
		{
			return _periodoDebito;
		}

		public function set periodoDebito(value:Number):void
		{
			_periodoDebito = value;
		}

		public function get numPeriodo():Number
		{
			return _numPeriodo;
		}

		public function set numPeriodo(value:Number):void
		{
			_numPeriodo = value;
		}

		public function get idDebitoContaCapital():Number
		{
			return _idDebitoContaCapital;
		}

		public function set idDebitoContaCapital(value:Number):void
		{
			_idDebitoContaCapital = value;
		}

		public function get nome():String
		{
			return _nome;
		}

		public function set nome(value:String):void
		{
			_nome = value;
		}

		public function get codTipoPessoa():Number
		{
			return _codTipoPessoa;
		}

		public function set codTipoPessoa(value:Number):void
		{
			_codTipoPessoa = value;
		}

		public function get dataInicialDeb():IDateTime
		{
			return _dataInicialDeb;
		}

		public function set dataInicialDeb(value:IDateTime):void
		{
			_dataInicialDeb = value;
		}

		public function get idsContaCapital():ArrayCollection
		{
			return _idsContaCapital;
		}

		public function set idsContaCapital(value:ArrayCollection):void
		{
			_idsContaCapital = value;
		}

		public function get descMatriculaFunc():String
		{
			return _descMatriculaFunc;
		}

		public function set descMatriculaFunc(value:String):void
		{
			_descMatriculaFunc = value;
		}

		public function get idsNumMatricula():ArrayCollection
		{
			return _idsNumMatricula;
		}

		public function set idsNumMatricula(value:ArrayCollection):void
		{
			_idsNumMatricula = value;
		}

		public function get contasCorrente():ArrayCollection
		{
			return _contasCorrente;
		}

		public function set contasCorrente(value:ArrayCollection):void
		{
			_contasCorrente = value;
		}

		public function get idsDebitoContaCapital():ArrayCollection
		{
			return _idsDebitoContaCapital;
		}

		public function set idsDebitoContaCapital(value:ArrayCollection):void
		{
			_idsDebitoContaCapital = value;
		}

	}
}