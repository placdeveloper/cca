package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: marco.nascimento
	 */
	
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.ConsultaDebitoIndeterminadoRenVO", ConsultaDebitoIndeterminadoRenVO);
	public class ConsultaDebitoIndeterminadoRenVO extends BancoobVO {
	
		private var _idContaCapital:Number;
		private var _idDebitoContaCapital:Number;
		private var _numContaCapital:Number;
		private var _numContaCorrente:Number;
		private var _diaDebito:Number;
		private var _nome:String;
		private var _idTipoPessoa:Number;
		private var _tipoPessoa:String;
		private var _idFormaDebito:Number;
		private var _formaDebito:String;
		private var _valor:Number;
		private var _dataPeriodoDeb:String;
		private var _cpfCnpj:String;
		private var _selecionado:Boolean;
		private var _idTipoValorDebito:Number;
		private var _idTipoIntegralizacao:Number;
		private var _nomeEmpresa:String;
		private var _numMatriculaFunc:String;
		

		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get nome():String
		{
			return _nome;
		}

		public function set nome(value:String):void
		{
			_nome = value;
		}

		public function get tipoPessoa():String
		{
			return _tipoPessoa;
		}

		public function set tipoPessoa(value:String):void
		{
			_tipoPessoa = value;
		}

		public function get formaDebito():String
		{
			return _formaDebito;
		}

		public function set formaDebito(value:String):void
		{
			_formaDebito = value;
		}

		public function get valor():Number
		{
			return _valor;
		}

		public function set valor(value:Number):void
		{
			_valor = value;
		}

		public function get dataPeriodoDeb():String
		{
			return _dataPeriodoDeb;
		}

		public function set dataPeriodoDeb(value:String):void
		{
			_dataPeriodoDeb = value;
		}

		public function get cpfCnpj():String
		{
			return _cpfCnpj;
		}

		public function set cpfCnpj(value:String):void
		{
			_cpfCnpj = value;
		}

		public function get selecionado():Boolean
		{
			return _selecionado;
		}

		public function set selecionado(value:Boolean):void
		{
			_selecionado = value;
		}

		public function get diaDebito():Number
		{
			return _diaDebito;
		}

		public function set diaDebito(value:Number):void
		{
			_diaDebito = value;
		}

		public function get idFormaDebito():Number
		{
			return _idFormaDebito;
		}

		public function set idFormaDebito(value:Number):void
		{
			_idFormaDebito = value;
		}

		public function get idTipoPessoa():Number
		{
			return _idTipoPessoa;
		}

		public function set idTipoPessoa(value:Number):void
		{
			_idTipoPessoa = value;
		}

		public function get idTipoValorDebito():Number
		{
			return _idTipoValorDebito;
		}

		public function set idTipoValorDebito(value:Number):void
		{
			_idTipoValorDebito = value;
		}

		public function get idDebitoContaCapital():Number
		{
			return _idDebitoContaCapital;
		}

		public function set idDebitoContaCapital(value:Number):void
		{
			_idDebitoContaCapital = value;
		}

		public function get numContaCorrente():Number
		{
			return _numContaCorrente;
		}

		public function set numContaCorrente(value:Number):void
		{
			_numContaCorrente = value;
		}

		public function get nomeEmpresa():String
		{
			return _nomeEmpresa;
		}

		public function set nomeEmpresa(value:String):void
		{
			_nomeEmpresa = value;
		}

		public function get numMatriculaFunc():String
		{
			return _numMatriculaFunc;
		}

		public function set numMatriculaFunc(value:String):void
		{
			_numMatriculaFunc = value;
		}

		public function get idTipoIntegralizacao():Number
		{
			return _idTipoIntegralizacao;
		}

		public function set idTipoIntegralizacao(value:Number):void
		{
			_idTipoIntegralizacao = value;
		}
	}
}