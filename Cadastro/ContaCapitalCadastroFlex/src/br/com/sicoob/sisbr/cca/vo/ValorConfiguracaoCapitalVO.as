package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.ValorConfiguracaoCapitalVO",ValorConfiguracaoCapitalVO);
	public class ValorConfiguracaoCapitalVO extends BancoobVO
	{
		private var _idValorConfiguracaoCapital:Number;
		private var _idConfiguracaoCapital:Number;
		private var _idInstituicao:Number;
		private var _valorConfiguracao:String;
		private var _idUsuario:String;
		private var _dataHoraAtualizacao:String;		
		private var _xmlString:String;	
		
		public function get xmlString():String
		{
			return _xmlString;
		}
		
		public function set xmlString(value:String):void
		{
			_xmlString = value;
		}		

		public function get valorConfiguracao():String
		{
			return _valorConfiguracao;
		}

		public function set valorConfiguracao(value:String):void
		{
			_valorConfiguracao = value;
		}

		public function get dataHoraAtualizacao():String
		{
			return _dataHoraAtualizacao;
		}

		public function set dataHoraAtualizacao(value:String):void
		{
			_dataHoraAtualizacao = value;
		}

		public function get idUsuario():String
		{
			return _idUsuario;
		}

		public function set idUsuario(value:String):void
		{
			_idUsuario = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get idConfiguracaoCapital():Number
		{
			return _idConfiguracaoCapital;
		}

		public function set idConfiguracaoCapital(value:Number):void
		{
			_idConfiguracaoCapital = value;
		}

		public function get idValorConfiguracaoCapital():Number
		{
			return _idValorConfiguracaoCapital;
		}

		public function set idValorConfiguracaoCapital(value:Number):void
		{
			_idValorConfiguracaoCapital = value;
		}

	}
}