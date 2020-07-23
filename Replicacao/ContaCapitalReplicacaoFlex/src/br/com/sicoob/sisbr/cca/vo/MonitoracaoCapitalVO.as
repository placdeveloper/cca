package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: marco.nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.replicacao.vo.MonitoracaoCapitalVO", MonitoracaoCapitalVO);
	public class MonitoracaoCapitalVO extends BancoobVO {
	
		private var _tabela:String;
		private var _qtdAguardando:Number;
		private var _qtdInvalido:Number;
		private var _qtdReplicado:Number;
		private var _qtdErro:Number;
	
		public function get tabela():String
		{
			return _tabela;
		}

		public function set tabela(value:String):void
		{
			_tabela = value;
		}

		public function get qtdAguardando():Number
		{
			return _qtdAguardando;
		}

		public function set qtdAguardando(value:Number):void
		{
			_qtdAguardando = value;
		}

		public function get qtdInvalido():Number
		{
			return _qtdInvalido;
		}

		public function set qtdInvalido(value:Number):void
		{
			_qtdInvalido = value;
		}

		public function get qtdReplicado():Number
		{
			return _qtdReplicado;
		}

		public function set qtdReplicado(value:Number):void
		{
			_qtdReplicado = value;
		}

		public function get qtdErro():Number
		{
			return _qtdErro;
		}

		public function set qtdErro(value:Number):void
		{
			_qtdErro = value;
		}
	}
}