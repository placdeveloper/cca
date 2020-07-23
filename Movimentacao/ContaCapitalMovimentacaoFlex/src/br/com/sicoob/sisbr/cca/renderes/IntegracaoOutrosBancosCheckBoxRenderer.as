package br.com.sicoob.sisbr.cca.renderes
{
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.renderes.CheckBoxRenderer;
	
	public class IntegracaoOutrosBancosCheckBoxRenderer extends CheckBoxRenderer
	{
		public function IntegracaoOutrosBancosCheckBoxRenderer()
		{
			super();
		}
		
		public override function set data(value:Object):void {
			super.permitirHabilitar=true;
			
			var _data:Object = value;
			super.data = _data;
			habilitar = true;
			var habilitar:*;
			data.strIntegralizadoCapital = "NÃO";
			if(data.bolIntegralizadoCapital){
				habilitar = false;		
				data.strIntegralizadoCapital = "SIM";
			}
			this.enabled = habilitar;
			dispatchEvent(new FlexEvent(FlexEvent.DATA_CHANGE));
		}
	}
}