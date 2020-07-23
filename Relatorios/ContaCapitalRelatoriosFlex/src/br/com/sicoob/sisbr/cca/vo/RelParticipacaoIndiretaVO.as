package br.com.sicoob.sisbr.cca.vo
{
	import br.com.bancoob.vo.BancoobVO;
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.relatorios.vo.RelParticipacaoIndiretaVO",RelParticipacaoIndiretaVO);

	public class RelParticipacaoIndiretaVO extends BancoobVO 
	{
		private var _periodo:Number;
		private var _periodoAnt:Number;
		private var _numCentral:Number;
		private var _numSingular:Number;		
		
		public function get periodo():Number
		{ return _periodo; }
		public function set periodo(vlr:Number):void
		{ _periodo = vlr; }		
		
		public function get periodoAnt():Number
		{ return _periodoAnt; }
		public function set periodoAnt(vlr:Number):void
		{ _periodoAnt = vlr; }	
		
		public function get numCentral():Number
		{ return _numCentral; }
		public function set numCentral(vlr:Number):void
		{ _numCentral = vlr; }	
		
		public function get numSingular():Number
		{ return _numSingular; }
		public function set numSingular(vlr:Number):void
		{ _numSingular = vlr; }	
		
	}
}