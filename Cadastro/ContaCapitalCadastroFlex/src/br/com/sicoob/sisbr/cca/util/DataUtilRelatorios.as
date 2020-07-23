package br.com.sicoob.sisbr.cca.util{
	
	import mx.controls.DateField;
	import mx.formatters.DateFormatter;
	
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.StringUtils;

	/**
	 * Classe que contem métodos utilitários para tratamento de datas
	 */	
	public class DataUtilRelatorios{
		
		/**
		 * Transforma uma data do formato UTC para o locale corrente
		 */
		public static function getData(aDataUTC: Date): Date {
			if (aDataUTC == null) {
				return null;
			}
			
    		return new Date(aDataUTC.fullYearUTC, aDataUTC.monthUTC, 
    			aDataUTC.dateUTC, aDataUTC.hoursUTC - 3, aDataUTC.minutesUTC, 
    			aDataUTC.secondsUTC, aDataUTC.millisecondsUTC);
		}

		/**
		 * Tranforma uma data do locale corrente para o formato UTC
		 */		
		public static function getDataUTC(aData: Date): Date {
			if (aData == null) {
				return null;
			}
			
		    var sDate:Date = new Date(Date.UTC(aData.fullYear, aData.month, aData.date, 
		    	aData.hours + 3, aData.minutes, aData.seconds, aData.milliseconds));
		    return sDate;
		}
		
		/**
		 * Cria um objeto do tipo data com a data atual, mas os secundos e milissegundos zerados
		 */
		public static function getDataHora(): Date {
			var dataHora: Date = new Date();
			dataHora.date			 = 1;
			dataHora.dateUTC		 = 1;
			dataHora.month			 = 0;
			dataHora.monthUTC		 = 0;
			dataHora.fullYear		 = 1970; 
			dataHora.fullYearUTC	 = 1970;
			dataHora.seconds 	  	 = 0;
			dataHora.secondsUTC   	 = 0;
			dataHora.milliseconds 	 = 0;
			dataHora.millisecondsUTC = 0;
			
			return dataHora;
		}
		
		public static function stringBrToDate(aData: String):Date{
			return DateField.stringToDate(aData, "DD/MM/YYYY");
		}
		
		/**
		 * Retorna uma string no formato DD/MM/YYYY
		 */
		public static function dateToString(aData: Date):String{
			var format:DateFormatter = new DateFormatter();
			format.formatString = "DD/MM/YYYY";
			return format.format(aData);
		}
		
		
		/**
		 * Retorna uma string no formato YYYY-MM-DD
		 */
		public static function dateToStringDB2(aData: Date):String{
			var format:DateFormatter = new DateFormatter();
			format.formatString = "YYYY-MM-DD";
			return format.format(aData);
		}
		
		/**
		 * Retorna uma string no formato DD/MM/YYYY HH24:MI:SS
		 */
		public static function dateTimeToString(aData: Date):String{
			var format:DateFormatter = new DateFormatter();
			format.formatString = "DD/MM/YYYY J:NN:SS";
			return format.format(aData);
		}
	 
	 	/**
	 	 * Efetua a soma de dias em uma data
	 	 */
		public static function somarDiasData(aDate: Date, aDias: int):Date{
			var millisecondsPerDay:int = 1000 * 60 * 60 * 24;
			var resultado:Date;
			return new Date(aDate.getTime() + (aDias * millisecondsPerDay));
		}
		
		/**
		* dateDiff(datePart:String, date1:Date, date2:Date):Number
		* 
		* Retorna a diferença entre duas datas
		* 
		*  Ex:
		*	var now:Date = new Date();	
		* 	var kaliBDay = new Date(2004,5,23,11,17);
		* 	trace("Diff between "+ kaliBDay + " and now " + DateFunction.dateDiff("y",now,kaliBDay) +" in years");
		*	
		* dateParts:
		* s: Seconds
		* n: Minutes
		* h: Hours
		* d: Days
		* m: Months
		* y: Years
		*/
		public static function dateDiff(aDatePart: String, aDate1: Date, aDate2: Date):Number{
			return getDatePartHashMap()[aDatePart.toLowerCase()](aDate1,aDate2);
		}
		
		private static function getDatePartHashMap():Object{
			var dpHashMap:Object = new Object();
			dpHashMap["s"] = getSeconds;
			dpHashMap["n"] = getMinutes;
			dpHashMap["h"] = getHours;
			dpHashMap["d"] = getDays;
			dpHashMap["m"] = getMonths;
			dpHashMap["y"] = getYears;
			return dpHashMap;
		}
		
		private static function compareDates(aDate1: Date, aDate2: Date):Number{
			return aDate1.getTime() - aDate2.getTime();
		}
		
		private static function getSeconds(aDate1: Date, aDate2: Date):Number{
			return Math.floor(compareDates(aDate1,aDate2)/1000);
		}
		
		private static function getMinutes(aDate1: Date, aDate2: Date):Number{
			return Math.floor(getSeconds(aDate1,aDate2)/60);
		}
		
		private static function getHours(aDate1: Date, aDate2: Date):Number{
			return Math.floor(getMinutes(aDate1,aDate2)/60);
		}
		
		private static function getDays(aDate1: Date, aDate2: Date):Number{
			return Math.floor(getHours(aDate1,aDate2)/24);
		}
		
		private static function getMonths(aDate1: Date, aDate2: Date):Number{
			var yearDiff:Number = getYears(aDate1,aDate2);
			var monthDiff:Number = aDate1.getMonth() - aDate2.getMonth();
			if(monthDiff < 0){
				monthDiff += 12;
			}
			if(aDate1.getDate()< aDate2.getDate()){
				monthDiff -=1;
			}
			return 12 *yearDiff + monthDiff;
		}
		
		private static function getYears(aDate1: Date, aDate2: Date):Number{
			return Math.floor(getDays(aDate1,aDate2)/365);
		}
		
		public static function toStringBr(aData: Date):String{
			return FormataData.formataData(aData);
		}
		
		public static function toStringTimeBr(aData: Date):String{
			return FormataData.formataDataHora(aData);
		}
		
		
		
		
		public static function isDataEquals(aDataUm:Date, aDataDois:Date):Boolean{
			if(aDataUm.getDate() == aDataDois.getDate()
					&& aDataUm.getMonth() == aDataDois.getMonth()
					&& aDataUm.getFullYear() == aDataDois.getFullYear()){
					return true;
				}else{
					return false;
				}
		}
		
		public static function isDataMaior(aDataInicio:Date, aDataFim:Date):Boolean{
			if(aDataInicio.getDate() > aDataFim.getDate()
					&& aDataInicio.getMonth() >= aDataFim.getMonth()
					&& aDataInicio.getFullYear() >= aDataFim.getFullYear()){
					return true;
				}else{
					return false;
				}
		}
		
		public static function isDataMaiorEquals(aDataInicio:Date, aDataFim:Date):Boolean{
			if(aDataInicio.getDate() >= aDataFim.getDate()
					&& aDataInicio.getMonth() >= aDataFim.getMonth()
					&& aDataInicio.getFullYear() >= aDataFim.getFullYear()){
					return true;
				}else{
					return false;
				}
		}
		
		/**
		 * Retorna verdade se dataFim > dataInicio
		 */
		public static function isDataHoraMaior(aDataInicio:Date, aDataFim:Date):Boolean{
			
			var horaInicio:String =
				StringUtils.completarCaracterEsquerda(new String(aDataInicio.getHours()), 2, "0") + 
				StringUtils.completarCaracterEsquerda(new String(aDataInicio.getMinutes()), 2, "0") + 
				StringUtils.completarCaracterEsquerda(new String(aDataInicio.getSeconds()), 2, "0");
				
			var horaFim:String = 
				StringUtils.completarCaracterEsquerda(new String(aDataFim.getHours()), 2, "0") +
				StringUtils.completarCaracterEsquerda(new String(aDataFim.getMinutes()), 2, "0") +
				StringUtils.completarCaracterEsquerda(new String(aDataFim.getSeconds()), 2, "0");
				
			var dataInicio:String =
				StringUtils.completarCaracterEsquerda(new String(aDataInicio.getFullYear()), 2, "0") + 
				StringUtils.completarCaracterEsquerda(new String(aDataInicio.getMonth()), 2, "0") +
				StringUtils.completarCaracterEsquerda(new String(aDataInicio.getDate()), 2, "0");
				
			var dataFim:String = 
				StringUtils.completarCaracterEsquerda(new String(aDataFim.getFullYear()), 2, "0") + 
				StringUtils.completarCaracterEsquerda(new String(aDataFim.getMonth()), 2, "0") +
				StringUtils.completarCaracterEsquerda(new String(aDataFim.getDate()), 2, "0");
			
			var dataHoraInicio:Number = new Number(dataInicio + horaInicio);
			var dataHoraFim:Number = new Number(dataFim + horaFim);
			
			return (dataHoraFim > dataHoraInicio);
		}
		
		public static function isHoraValida(aHora:Number, aMinuto:Number):Boolean{
			return (aHora >= 0 && aHora <= 23) && (aMinuto >= 0 && aMinuto <= 59); 
		}
		
		public static function isDataMenor(aDataUm:Date, aDataDois:Date):Boolean{
			if(aDataUm.getDate() < aDataDois.getDate()
				&& aDataUm.getMonth() <= aDataDois.getMonth()
				&& aDataUm.getFullYear() <= aDataDois.getFullYear()){
				return true;
			} else{
				return false;
			}
		}
		
		public static function isDataMenorEquals(aDataUm:Date, aDataDois:Date):Boolean{
			if(aDataUm.getDate() <= aDataDois.getDate()
				&& aDataUm.getMonth() <= aDataDois.getMonth()
				&& aDataUm.getFullYear() <= aDataDois.getFullYear()){
				return true;
			} else{
				return false;
			}
		}
	}
}