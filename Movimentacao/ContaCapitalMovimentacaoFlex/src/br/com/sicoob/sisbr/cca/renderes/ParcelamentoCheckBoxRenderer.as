package br.com.sicoob.sisbr.cca.renderes
{
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.renderes.CheckBoxRenderer;
	
	public class ParcelamentoCheckBoxRenderer extends CheckBoxRenderer
	{
		public function ParcelamentoCheckBoxRenderer()
		{
			super();
		}
		
		public override function set data(value:Object):void {
			super.permitirHabilitar=true;
			
			var _data:Object = value;
			super.data = _data;
			habilitar = true;
			var habilitar:*;
			if(data.idSituacaoParcelamento != "1"){
				habilitar = false;				
			}
			this.enabled = habilitar;
			dispatchEvent(new FlexEvent(FlexEvent.DATA_CHANGE));
		}
	}
}