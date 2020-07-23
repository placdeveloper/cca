package br.com.sicoob.sisbr.cca.comum.nivelinstituicao
{
	import flash.events.Event;

	public class NivelInstituicaoEvent extends Event {
		
		// Evento disparado na troca de valor das combos de invel instituicao
		public static const NIVEL_INSTITUICAO_SELECIONADO:String = "nivelInstituicaoSelecionado";
		
		public function NivelInstituicaoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
		}
	}
}