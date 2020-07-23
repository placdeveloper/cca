package br.com.sicoob.sisbr.cca.replicacao.monitoracao
{
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.renderes.CheckBoxRenderer;
	
	public class MonitoracaoCheckBoxRenderer extends CheckBoxRenderer {
		
		public function MonitoracaoCheckBoxRenderer() {
			super();
		}
		
		public override function set data(value:Object):void {
			super.permitirHabilitar=true;
			
			var _data:Object = value;
			super.data = _data;
			habilitar = true;
			var habilitar:*;
			this.enabled = habilitar;
			dispatchEvent(new FlexEvent(FlexEvent.DATA_CHANGE));
		}
	}
}