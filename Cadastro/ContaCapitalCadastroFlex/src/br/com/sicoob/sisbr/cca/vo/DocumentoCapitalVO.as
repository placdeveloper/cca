package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: Marco.Nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO", DocumentoCapitalVO);
	public class DocumentoCapitalVO extends BancoobVO {
		
		private var _idDocumento:Number;
		private var _idContaCapital:Number;
		private var _nome:String;
		private var _idUsuario:String;
		private var _dataHoraAtualizacao:String;
		
		
		public function get idDocumento():Number
		{
			return _idDocumento;
		}

		public function set idDocumento(value:Number):void
		{
			_idDocumento = value;
		}

		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

		public function get nome():String
		{
			return _nome;
		}

		public function set nome(value:String):void
		{
			_nome = value;
		}

		public function get idUsuario():String
		{
			return _idUsuario;
		}

		public function set idUsuario(value:String):void
		{
			_idUsuario = value;
		}

		public function get dataHoraAtualizacao():String
		{
			return _dataHoraAtualizacao;
		}

		public function set dataHoraAtualizacao(value:String):void
		{
			_dataHoraAtualizacao = value;
		}
	}
}