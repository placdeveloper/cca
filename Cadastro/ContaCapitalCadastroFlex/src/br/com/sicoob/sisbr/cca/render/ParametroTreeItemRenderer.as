package br.com.sicoob.sisbr.cca.render
{
	import flash.events.MouseEvent;
	
	import mx.controls.CheckBox;
	import mx.controls.Label;
	import mx.controls.treeClasses.TreeItemRenderer;

	public class ParametroTreeItemRenderer extends TreeItemRenderer{
		public var myCheckBox:CheckBox;
		public var myLabel:Label;
		public var itemXml:XML;
		public function ParametroTreeItemRenderer(){
			super();
			mouseEnabled = false;
		}
		override public function set data(value:Object):void{
			if(value != null){
				super.data = value;
				this.itemXml = XML(value);
				if(this.itemXml.@CHECKED== "1"){
					this.myCheckBox.selected = true;
				}else{
					this.myCheckBox.selected = false;
				}
				myLabel.text = this.itemXml.@VALORCONFIGURACAO;										
			}
		}
		
		override protected function createChildren():void{
			super.createChildren();
			myCheckBox = new CheckBox();
			myCheckBox.addEventListener(MouseEvent.CLICK, handleChkClick);
			addChild(myCheckBox);	
			
			super.createChildren();
			myLabel = new Label();
			myLabel.x = 550;
			myLabel.width = 100;	
			addChild(myLabel);			
		}
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			if(super.data)
			{
				if (super.icon != null)
				{
					myCheckBox.x = super.icon.x;
					myCheckBox.y = (unscaledHeight - myCheckBox.height) / 2;
					super.icon.x = myCheckBox.x + myCheckBox.width + 17;
					if (icon.x + icon.width > unscaledWidth)
						icon.setActualSize(0, unscaledHeight);
					super.label.x = super.icon.x + super.icon.width + 3;
					super.label.setActualSize(Math.max(unscaledWidth - super.label.x, 4), unscaledHeight);
				}
				else
				{
					myLabel.y = super.label.y;
					myLabel.height = super.label.height;
					myCheckBox.x = super.label.x;
					myCheckBox.y = (unscaledHeight - myCheckBox.height) / 2;
					super.label.x = myCheckBox.x + myCheckBox.width + 17;
					super.label.setActualSize(Math.max(unscaledWidth - super.label.x, 4), unscaledHeight);
				}
				if (myCheckBox.x + myCheckBox.width > unscaledWidth)
					myCheckBox.visible = false;
				
			}
			
		}
		
		private function handleChkClick(evt:MouseEvent):void{						
			
			if(this.myCheckBox.selected){
				this.itemXml.@CHECKED = "1";
			}else{
				this.itemXml.@CHECKED = "0";
			}

			var xList:XMLList = this.itemXml.descendants();
			for (var x:uint=0; x < xList.length(); x++){
				xList[x].@CHECKED = this.itemXml.@CHECKED;   
			}			
		}
	}
}