package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: Marco.Nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.CondicaoEstatutariaVO", CondicaoEstatutariaVO);
	public class CondicaoEstatutariaVO extends BancoobVO {
		
		private var _idConfiguracaoCapital:Number;
		private var _nomeAgrupadorConfiguracaoCapital:String;
		private var _nomeConfiguracaoCapital:String;
		private var _valorConfiguracao:String;
		private var _condicao:String;
		
		public function get valorConfiguracao():String
		{
			return _valorConfiguracao;
		}

		public function set valorConfiguracao(value:String):void
		{
			_valorConfiguracao = value;
		}

		public function get nomeAgrupadorConfiguracaoCapital():String
		{
			return _nomeAgrupadorConfiguracaoCapital;
		}

		public function set nomeAgrupadorConfiguracaoCapital(value:String):void
		{
			_nomeAgrupadorConfiguracaoCapital = value;
		}

		public function get idConfiguracaoCapital():Number
		{
			return _idConfiguracaoCapital;
		}

		public function set idConfiguracaoCapital(value:Number):void
		{
			_idConfiguracaoCapital = value;
		}

		public function get condicao():String
		{
			return _idConfiguracaoCapital + " - " + _nomeConfiguracaoCapital
		}

		public function set condicao(value:String):void
		{
			_condicao = value;
		}

		public function get nomeConfiguracaoCapital():String
		{
			return _nomeConfiguracaoCapital;
		}

		public function set nomeConfiguracaoCapital(value:String):void
		{
			_nomeConfiguracaoCapital = value;
		}
	}
}