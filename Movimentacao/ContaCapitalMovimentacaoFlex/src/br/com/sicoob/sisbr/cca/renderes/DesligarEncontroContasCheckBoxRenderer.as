package br.com.sicoob.sisbr.cca.renderes
{
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.renderes.CheckBoxRenderer;
	
	public class DesligarEncontroContasCheckBoxRenderer extends CheckBoxRenderer {
		
		public function DesligarEncontroContasCheckBoxRenderer() {
			super();
		}
		
		public override function set data(value:Object):void {
			super.permitirHabilitar = true;
			this.enabled = true;
			super.data = value;
			dispatchEvent(new FlexEvent(FlexEvent.DATA_CHANGE));
		}
		
		public function checkBoxClick(event:MouseEvent):void {
			(event.currentTarget.parentDocument as DesligarEncontroContas).onClickEmprestimo();
		}
		
	}
}