package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.IntegralizacaoOutrosBancosVO", IntegralizacaoOutrosBancosVO);
	public class IntegralizacaoOutrosBancosVO extends BancoobVO {

		private var _id:Number;
		private var _numCliente:Number;
		private var _numMatricula:Number;
		private var _descNomePessoa:String;
		private var _numCpfCnpj:String;
		private var _numBancoFavorecido:Number;
		private var _numBanco:Number;
		private var _numAgencia:Number;
		private var _numDVAgencia:String;
		private var _numContaCorrente:String;
		private var _numDVContaCorrente:Number;
		private var _numAgenciaFavorecido:Number;
		private var _numContaFavorecido:String;
		private var _numDVAgenciaFavorecido:String;
		private var _descBanco:String;
		private var _valorIntegralizacao:Number;
		private var _valorIntegralizacaoStr:String;
		private var _contaPrincipal:Boolean;
		private var _selecionado:Boolean;
		private var _bolIntegralizadoCapital:Boolean;		
		private var _sequencialArquivo:Number;
		private var _sequencialDetalhe:Number;
		private var _numParcela:Number;
		private var _nomeArquivo:String;
		private var _strIntegralizadoCapital:String;
				
		public function get strIntegralizadoCapital():String
		{
			return _strIntegralizadoCapital;
		}

		public function set strIntegralizadoCapital(value:String):void
		{
			_strIntegralizadoCapital = value;
		}

		public function get nomeArquivo():String
		{
			return _nomeArquivo;
		}

		public function set nomeArquivo(value:String):void
		{
			_nomeArquivo = value;
		}

		public function get numParcela():Number
		{
			return _numParcela;
		}

		public function set numParcela(value:Number):void
		{
			_numParcela = value;
		}

		public function get sequencialDetalhe():Number
		{
			return _sequencialDetalhe;
		}

		public function set sequencialDetalhe(value:Number):void
		{
			_sequencialDetalhe = value;
		}

		public function get sequencialArquivo():Number
		{
			return _sequencialArquivo;
		}

		public function set sequencialArquivo(value:Number):void
		{
			_sequencialArquivo = value;
		}

		public function get bolIntegralizadoCapital():Boolean
		{
			return _bolIntegralizadoCapital;
		}

		public function set bolIntegralizadoCapital(value:Boolean):void
		{
			_bolIntegralizadoCapital = value;
		}

		public function get id():Number
		{
			return _id;
		}

		public function set id(value:Number):void
		{
			_id = value;
		}

		public function get contaPrincipal():Boolean
		{
			return _contaPrincipal;
		}

		public function set contaPrincipal(value:Boolean):void
		{
			_contaPrincipal = value;
		}

		public function get numDVAgencia():String
		{
			return _numDVAgencia;
		}

		public function set numDVAgencia(value:String):void
		{
			_numDVAgencia = value;
		}

		public function get numAgencia():Number
		{
			return _numAgencia;
		}

		public function set numAgencia(value:Number):void
		{
			_numAgencia = value;
		}

		public function get numBanco():Number
		{
			return _numBanco;
		}

		public function set numBanco(value:Number):void
		{
			_numBanco = value;
		}

		public function get numDVContaCorrente():Number
		{
			return _numDVContaCorrente;
		}

		public function set numDVContaCorrente(value:Number):void
		{
			_numDVContaCorrente = value;
		}

		public function get numContaCorrente():String
		{
			return _numContaCorrente;
		}

		public function set numContaCorrente(value:String):void
		{
			_numContaCorrente = value;
		}

		public function get numDVAgenciaFavorecido():String
		{
			return _numDVAgenciaFavorecido;
		}

		public function set numDVAgenciaFavorecido(value:String):void
		{
			_numDVAgenciaFavorecido = value;
		}

		public function get valorIntegralizacaoStr():String
		{
			return _valorIntegralizacaoStr;
		}

		public function set valorIntegralizacaoStr(value:String):void
		{
			_valorIntegralizacaoStr = value;
		}

		public function get selecionado():Boolean
		{
			return _selecionado;
		}

		public function set selecionado(value:Boolean):void
		{
			_selecionado = value;
		}

		public function get valorIntegralizacao():Number
		{
			return _valorIntegralizacao;
		}

		public function set valorIntegralizacao(value:Number):void
		{
			_valorIntegralizacao = value;
		}

		public function get descBanco():String
		{
			return _descBanco;
		}

		public function set descBanco(value:String):void
		{
			_descBanco = value;
		}

		public function get numContaFavorecido():String
		{
			return _numContaFavorecido;
		}

		public function set numContaFavorecido(value:String):void
		{
			_numContaFavorecido = value;
		}

		public function get numAgenciaFavorecido():Number
		{
			return _numAgenciaFavorecido;
		}

		public function set numAgenciaFavorecido(value:Number):void
		{
			_numAgenciaFavorecido = value;
		}

		public function get numBancoFavorecido():Number
		{
			return _numBancoFavorecido;
		}

		public function set numBancoFavorecido(value:Number):void
		{
			_numBancoFavorecido = value;
		}

		public function get numCpfCnpj():String
		{
			return _numCpfCnpj;
		}

		public function set numCpfCnpj(value:String):void
		{
			_numCpfCnpj = value;
		}

		public function get descNomePessoa():String
		{
			return _descNomePessoa;
		}

		public function set descNomePessoa(value:String):void
		{
			_descNomePessoa = value;
		}

		public function get numMatricula():Number
		{
			return _numMatricula;
		}

		public function set numMatricula(value:Number):void
		{
			_numMatricula = value;
		}

		public function get numCliente():Number
		{
			return _numCliente;
		}

		public function set numCliente(value:Number):void
		{
			_numCliente = value;
		}

	}
}