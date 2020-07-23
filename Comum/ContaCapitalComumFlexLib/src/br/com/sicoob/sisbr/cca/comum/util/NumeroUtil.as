package br.com.sicoob.sisbr.cca.comum.util{
	
	/**
	 * Classe que contem métodos utilitários para numeros.
	 */	
	public class NumeroUtil{
		
		/**
		 * Corrige problema de arredondamento 
		 */		
		public static function ajustarArredondamento(valor: Number): Number {
			var correction:Number = Math.pow(10, 5);
			return Math.round(correction * valor) / correction;
		}
	}
}