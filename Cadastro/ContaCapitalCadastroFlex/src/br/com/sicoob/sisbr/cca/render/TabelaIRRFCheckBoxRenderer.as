package br.com.sicoob.sisbr.cca.render
{
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.componentes.renderes.CheckBoxRenderer;
	
	public class TabelaIRRFCheckBoxRenderer extends CheckBoxRenderer {
		
		public function TabelaIRRFCheckBoxRenderer() {
			super();
		}
		
		public override function set data(value:Object):void {
			super.permitirHabilitar = true;
			
			var _data:Object = value;
			super.data = _data;
			habilitar = true;
			var habilitar:*;
			this.enabled = habilitar;
			
			if(value != null && value.anoBase < new Date().fullYear) {
				this.enabled = false;
				this.visible = false;
				this.includeInLayout = false;
			}
			
			dispatchEvent(new FlexEvent(FlexEvent.DATA_CHANGE));
		}
		
		public function checkBoxClick(event:MouseEvent):void {
			if(event.currentTarget.parent.parent is Grid) {
				
				event.currentTarget.parentDocument.btExcluir.enabled = false;
				
				var grid:Grid = event.currentTarget.parent.parent;
				for(var i:int = 0; i < grid.dataProvider.length; i++) {
					
					if(grid.dataProvider[i].selecionado) {
						event.currentTarget.parentDocument.btExcluir.enabled = true;
						break;
					}
				}
			}
		}
	}
}