package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: Marco.Nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.TabelaIRRFVO", TabelaIRRFVO);
	public class TabelaIRRFVO extends BancoobVO {
		
		private var _anoBase:Number;
		private var _percAliquota:Number;
		private var _valorBaseInicial:Number;
		private var _valorBaseFinal:Number;
		private var _valorDeducao:Number;
		private var _selecionado:Boolean;
		
		public function get anoBase():Number
		{
			return _anoBase;
		}

		public function set anoBase(value:Number):void
		{
			_anoBase = value;
		}

		public function get percAliquota():Number
		{
			return _percAliquota;
		}

		public function set percAliquota(value:Number):void
		{
			_percAliquota = value;
		}

		public function get valorBaseInicial():Number
		{
			return _valorBaseInicial;
		}

		public function set valorBaseInicial(value:Number):void
		{
			_valorBaseInicial = value;
		}

		public function get valorBaseFinal():Number
		{
			return _valorBaseFinal;
		}

		public function set valorBaseFinal(value:Number):void
		{
			_valorBaseFinal = value;
		}

		public function get valorDeducao():Number
		{
			return _valorDeducao;
		}

		public function set valorDeducao(value:Number):void
		{
			_valorDeducao = value;
		}

		public function get selecionado():Boolean
		{
			return _selecionado;
		}

		public function set selecionado(value:Boolean):void
		{
			_selecionado = value;
		}

	}
}