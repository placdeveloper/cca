package br.com.sicoob.sisbr.cca.replicacao.Configuracao
{
	import br.com.bancoob.componentes.renderes.CheckBoxRenderer;
	
	public class PilotoCheckBoxRenderer extends CheckBoxRenderer
	{
		public function PilotoCheckBoxRenderer()
		{
			super();
		}
		
		public override function set data(value:Object):void {
			var _data:Object = value;
			super.data = _data;
			this.enabled = !_data.coopPiloto;
		}
	}
}