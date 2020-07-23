package br.com.sicoob.sisbr.cca.vo
{
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.cadastro.vo.ItemListaVO",ItemListaVO);
	public class ItemListaVO extends BancoobVO
	{
		private var _codListaItem:String;
		private var _descListaItem:String;
		
		public function get codListaItem():String {
			return _codListaItem;
		}
		public function set codListaItem(vlr:String):void {
			_codListaItem = vlr;
		}
		
		public function get descListaItem():String {
			return _descListaItem;
		}
		public function set descListaItem(vlr:String):void {
			_descListaItem = vlr;
		}
		
	}
}


