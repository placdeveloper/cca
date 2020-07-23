package 
{
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.ProcurarGeralV2;
	
	import mx.collections.ArrayCollection;
	import mx.utils.ObjectUtil;
	
	public class ContaCapitalConfiguracoes
	{
		private var central: ProcurarGeralV2 = new ProcurarGeralV2();
		private var singular: ProcurarGeralV2 = new ProcurarGeralV2();
		private static var contTabulacao:int=1;
		
		public function obterConfiguracoesCentralSingular(paramCentral:ProcurarGeralV2,paramSingular:ProcurarGeralV2,dados:Object):void{
			var numCentral:String = dados["numCentral"];
			var descCentral:String = dados["descCentral"];
			var numSingular:String = dados["numCooperativa"];
			var descSingular:String = dados["descCooperativa"];
			
			/**
			 *	Entra aqui nos casos da tela só ter o campo singular
			 *  A tela não tem central
			 **/
			
			if (paramCentral == null){
				
				singular = paramSingular;
				
				if (numSingular != null){					
					singular.txtValor.text = numSingular;
					singular.lblDescricao.text = descSingular;
					singular.txtValor.enabled = false;
					singular.btnProcurar.enabled = false;
				}else if (numCentral != null){
					//Força o Filtro Adicional 43 cod 6010
					var obj: Object = new Object();
					var arrayParametros: ArrayCollection = new ArrayCollection();
					obj.nome ="NumCoopPai";
					obj.valor=numCentral;  
					arrayParametros.addItem(obj);
					singular.filtroAdicional = 43;
					singular.parametros = arrayParametros;											
				}else{									
					singular.txtValor.text = '';
					singular.lblDescricao.text = '';		
					singular.txtValor.enabled = true;
					singular.btnProcurar.enabled = true;
				}								
				
			}
			/**
			 *	Entra aqui nos casos da tela ter a central e a singular 
			 *  Sendo que o campo singular vai depender do campo central escolhido 
			 **/			
			else{		
				
				central = paramCentral;
				singular = paramSingular;
				central.addEventListener(SelecaoEvent.OBJETO_SELECIONADO,resultConsultaCentral);
				
				//Trava Central e singular				
				if (numCentral != null && numSingular != null){
					
					central.txtValor.text = numCentral;
					central.lblDescricao.text = descCentral;				
					central.txtValor.enabled = false;
					central.btnProcurar.enabled = false;	
					singular.txtValor.text = numSingular;
					singular.lblDescricao.text = descSingular;				
					singular.txtValor.enabled = false;
					singular.btnProcurar.enabled = false;
					
					//Trava apenas a Central													
				}else if (numCentral != null){
					
					central.txtValor.text = numCentral;
					central.lblDescricao.text = descCentral;				
					central.txtValor.enabled = false;
					central.btnProcurar.enabled = false;
					
					//Libera a singular com o filtro adicional
					singular.txtValor.text = '';
					singular.lblDescricao.text = '';				
					singular.txtValor.enabled = true;
					singular.btnProcurar.enabled = true;
					setFiltroAdicional();
				}else{
					central.txtValor.text = '';
					central.lblDescricao.text = '';				
					singular.txtValor.text = '';
					singular.lblDescricao.text = '';		
					singular.txtValor.enabled = false;
					singular.btnProcurar.enabled = false;
				}
			}
			return;				
		}
		
		/**
		 * Libera a pesquisa por singulares caso uma central tenha sido escolhida
		 * */
		private function resultConsultaCentral(evt:SelecaoEvent):void{ 
			//Nenhuma Central Escolhida
			if (central.lblDescricao.text == ''){
				singular.txtValor.text = '';
				singular.lblDescricao.text = '';				
				singular.txtValor.enabled = false;
				singular.btnProcurar.enabled = false;	
				//Central Selecionada								
			}else if (central.btnProcurar.enabled){				
				singular.txtValor.text = '';
				singular.lblDescricao.text = '';				
				singular.txtValor.enabled = true;
				singular.btnProcurar.enabled = true;	
				
				//Passa o filtro da Central para dentro da consulta de singular
				setFiltroAdicional();
			}
			return;
		}
		
		/**
		 * Passa o filtro da Central para dentro da consulta de singular
		 * */
		public function setFiltroAdicional():void{
			var obj: Object = new Object();
			var arrayParametros: ArrayCollection = new ArrayCollection();
			obj.nome ="NumCoopPai";
			obj.valor=central.txtValor.text;  
			arrayParametros.addItem(obj);
			singular.filtroAdicional = 41;
			singular.parametros = arrayParametros;		
			return;
		}		
			
	}
}