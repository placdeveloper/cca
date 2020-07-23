package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import mx.events.FlexEvent;

	public class JanelaCopiaConfiguracao extends JanelaCopiaConfiguracaoView {
		
		private var texto:String;
		
		public function JanelaCopiaConfiguracao(texto:String) {
			super();
			this.texto = texto;
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected function init(event:FlexEvent):void {
			txtConcScript.text = this.texto;			
		}
		
	}
}