package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: marco.nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.QuadroGeralAssociadoVO", QuadroGeralAssociadoVO);
	public class QuadroGeralAssociadoVO extends BancoobVO {
		
		/**
		 * Quantitativo Geral 
		 */
		private var _tipoPessoa:String;
		private var _qtdPessoasComDebito:Number;
		private var _qtdPessoasSemDebito:Number;
		private var _totalAssociados:Number;
		
		/**
		** Debitos via CCO por DIA FIXO
		*/
		private var _dia:Number;
		private var _qtdPorDiaFixo:Number;
		private var _valorTotalPorDiaFixo:Number;
		
		/**
		 * Debitos via CCO por INTERVALO
		 */
		private var _intervaloDias:Number;
		private var _qtdIntervalo:Number;
		private var _valorTotalIntervalo:Number;
		
		private var _formaCalculo:Number;
		private var _descFormaCalculo:String;
		private var _qtdDebitos:Number;
		
		
		public function get tipoPessoa():String
		{
			return _tipoPessoa;
		}

		public function set tipoPessoa(value:String):void
		{
			_tipoPessoa = value;
		}

		public function get qtdPessoasComDebito():Number
		{
			return _qtdPessoasComDebito;
		}

		public function set qtdPessoasComDebito(value:Number):void
		{
			_qtdPessoasComDebito = value;
		}

		public function get qtdPessoasSemDebito():Number
		{
			return _qtdPessoasSemDebito;
		}

		public function set qtdPessoasSemDebito(value:Number):void
		{
			_qtdPessoasSemDebito = value;
		}

		public function get totalAssociados():Number
		{
			return _totalAssociados;
		}

		public function set totalAssociados(value:Number):void
		{
			_totalAssociados = value;
		}

		public function get dia():Number
		{
			return _dia;
		}

		public function set dia(value:Number):void
		{
			_dia = value;
		}

		public function get qtdPorDiaFixo():Number
		{
			return _qtdPorDiaFixo;
		}

		public function set qtdPorDiaFixo(value:Number):void
		{
			_qtdPorDiaFixo = value;
		}

		public function get valorTotalPorDiaFixo():Number
		{
			return _valorTotalPorDiaFixo;
		}

		public function set valorTotalPorDiaFixo(value:Number):void
		{
			_valorTotalPorDiaFixo = value;
		}

		public function get intervaloDias():Number
		{
			return _intervaloDias;
		}
		
		public function set intervaloDias(value:Number):void
		{
			_intervaloDias = value;
		}

		public function get qtdIntervalo():Number
		{
			return _qtdIntervalo;
		}

		public function set qtdIntervalo(value:Number):void
		{
			_qtdIntervalo = value;
		}

		public function get valorTotalIntervalo():Number
		{
			return _valorTotalIntervalo;
		}

		public function set valorTotalIntervalo(value:Number):void
		{
			_valorTotalIntervalo = value;
		}

		public function get qtdDebitos():Number
		{
			return _qtdDebitos;
		}

		public function set qtdDebitos(value:Number):void
		{
			_qtdDebitos = value;
		}

		public function get descFormaCalculo():String
		{
			return _descFormaCalculo;
		}

		public function set descFormaCalculo(value:String):void
		{
			_descFormaCalculo = value;
		}

		/**
		 ** Debitos via FOLHA/BANCO
		 */
		public function get formaCalculo():Number
		{
			return _formaCalculo;
		}

		/**
		 * @private
		 */
		public function set formaCalculo(value:Number):void
		{
			_formaCalculo = value;
		}
	}
}