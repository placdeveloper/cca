package br.com.sicoob.sisbr.cca.renderes
{
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.componentes.renderes.CheckBoxRenderer;
	
	public class DebitoIndeterminadoCheckBoxRenderer extends CheckBoxRenderer {
		
		public function DebitoIndeterminadoCheckBoxRenderer() {
			super();
		}
		
		public override function set data(value:Object):void {
			super.permitirHabilitar = true;
			
			var _data:Object = value;
			super.data = _data;
			habilitar = true;
			var habilitar:*;
			this.enabled = habilitar;
			dispatchEvent(new FlexEvent(FlexEvent.DATA_CHANGE));
		}
		
		public function checkBoxClick(event:MouseEvent):void {
			if(event.currentTarget.parent.parent is Grid) {
				
				event.currentTarget.parentDocument.btExcluir.enabled = false;
				event.currentTarget.parentDocument.btAlterar.enabled = false;
				
				var grid:Grid = event.currentTarget.parent.parent;
				for(var i:int = 0; i < grid.dataProvider.length; i++) {
					
					if(grid.dataProvider[i].selecionado) {
						event.currentTarget.parentDocument.btExcluir.enabled = true;
						event.currentTarget.parentDocument.btAlterar.enabled = true;
						break;
					}
				}
			}
		}
	}
}