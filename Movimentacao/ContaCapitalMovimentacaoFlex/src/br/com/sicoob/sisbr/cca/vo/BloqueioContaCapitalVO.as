package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;

	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO", BloqueioContaCapitalVO);
	public class BloqueioContaCapitalVO extends BancoobVO {
		
		private var _idBloqueio:Number;
		private var _idContaCapital:Number;
		private var _idInstituicao:Number;
		private var _numContaCapital:Number;
		private var _nomePessoa:String;
		private var _cpfCnpj:String;
		private var _codTipoBloqueio:Number;
		private var _idTipoBloqueio:Number;
		private var _nomeTipoBloqueio:String;
		private var _numProtocolo:String;
		private var _numProcesso:String;
		private var _codSituacaoBloqueio:Number;
		private var _dataBloqueio:IDateTime;
		private var _dataDesbloqueio:IDateTime;
		private var _valorBloqueado:Number;
		private var _valorDesbloqueio:Number;
		private var _dataProtocolo:IDateTime;
		private var _ativo:Boolean;
		
		public function get idBloqueio():Number
		{
			return _idBloqueio;
		}

		public function set idBloqueio(value:Number):void
		{
			_idBloqueio = value;
		}

		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get nomePessoa():String
		{
			return _nomePessoa;
		}

		public function set nomePessoa(value:String):void
		{
			_nomePessoa = value;
		}

		public function get cpfCnpj():String
		{
			return _cpfCnpj;
		}

		public function set cpfCnpj(value:String):void
		{
			_cpfCnpj = value;
		}

		public function get codTipoBloqueio():Number
		{
			return _codTipoBloqueio;
		}

		public function set codTipoBloqueio(value:Number):void
		{
			_codTipoBloqueio = value;
		}
		
		public function get idTipoBloqueio():Number
		{
			return _idTipoBloqueio;
		}
		
		public function set idTipoBloqueio(value:Number):void
		{
			_idTipoBloqueio = value;
		}

		public function get nomeTipoBloqueio():String
		{
			return _nomeTipoBloqueio;
		}

		public function set nomeTipoBloqueio(value:String):void
		{
			_nomeTipoBloqueio = value;
		}

		public function get numProtocolo():String
		{
			return _numProtocolo;
		}

		public function set numProtocolo(value:String):void
		{
			_numProtocolo = value;
		}
		
		public function get numProcesso():String
		{
			return _numProcesso;
		}
		
		public function set numProcesso(value:String):void
		{
			_numProcesso = value;
		}

		public function get codSituacaoBloqueio():Number
		{
			return _codSituacaoBloqueio;
		}

		public function set codSituacaoBloqueio(value:Number):void
		{
			_codSituacaoBloqueio = value;
		}

		public function get dataBloqueio():IDateTime
		{
			return _dataBloqueio;
		}

		public function set dataBloqueio(value:IDateTime):void
		{
			_dataBloqueio = value;
		}
		
		public function get dataDesbloqueio():IDateTime
		{
			return _dataDesbloqueio;
		}
		
		public function set dataDesbloqueio(value:IDateTime):void
		{
			_dataDesbloqueio = value;
		}

		public function get valorBloqueado():Number
		{
			return _valorBloqueado;
		}

		public function set valorBloqueado(value:Number):void
		{
			_valorBloqueado = value;
		}
		
		public function get valorDesbloqueio():Number
		{
			return _valorDesbloqueio;
		}
		
		public function set valorDesbloqueio(value:Number):void
		{
			_valorDesbloqueio = value;
		}

		public function get dataProtocolo():IDateTime
		{
			return _dataProtocolo;
		}

		public function set dataProtocolo(value:IDateTime):void
		{
			_dataProtocolo = value;
		}

		public function get ativo():Boolean
		{
			return _ativo;
		}

		public function set ativo(value:Boolean):void
		{
			_ativo = value;
		}

	}
}