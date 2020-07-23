package br.com.sicoob.sisbr.cca.comum.pesquisaempresa
{
	import flash.events.Event;

	public class SelecionarEmpresaEvent extends Event {
		
		//Evento Disparado sempre que a busca por uma conta capital é retornada ou quando seleciona uma conta na Grid
		public static const ITEM_SELECIONADO:String = "itemSelecionado";
		
		//Evento Disparado sempre que a busca por uma conta é retornada sem resultados
		public static const REGISTRO_NAO_ENCONTRADO:String = "registroNaoEncontrado";
		
		public function SelecionarEmpresaEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
		}
	}
}