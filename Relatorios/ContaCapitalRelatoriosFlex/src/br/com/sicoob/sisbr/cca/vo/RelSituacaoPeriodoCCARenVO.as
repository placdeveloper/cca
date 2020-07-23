package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelSituacaoPeriodoCCARenVO", RelSituacaoPeriodoCCARenVO);
	public class RelSituacaoPeriodoCCARenVO extends BancoobVO { 
		
		private var _idInstituicao: Number;

		private var _dataPeriodoInicial: IDateTime;

		private var _dataPeriodoFinal: IDateTime;
		
		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}
		
		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get dataPeriodoInicial():IDateTime
		{
			return _dataPeriodoInicial;
		}

		public function set dataPeriodoInicial(value:IDateTime):void
		{
			_dataPeriodoInicial = value;
		}
		
		public function get dataPeriodoFinal():IDateTime
		{
			return _dataPeriodoFinal;
		}

		public function set dataPeriodoFinal(value:IDateTime):void
		{
			_dataPeriodoFinal = value;
		}		
	}
}
