package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: antonio.genaro
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DevolucaoRenVO", DevolucaoRenVO);
	public class DevolucaoRenVO extends BancoobVO {
		
		private var _idContaCapital:Number;
		private var _idInstituicao:Number;
		private var _numContaCapital:Number;
		private var _vlrDevolucao:Number;
		private var _vlrAVista:Number;
		private var _qtdParcelas:Number;
		private var _vlrParcelas:Number;
		private var _dtInicioParcelamento:String;
		private var _tipoInteg:Number;
		private var _numContaCorrente:Number;
		private var _dataHoraAtualizacao:IDateTime;
		private var _idPessoaLegado:Number;
		private var _idPessoa:Number;
		private var _idMotivoDevolucao:Number;
		
		//Capitação Remunerada
		private var _idModalidadeAplicacao:Number;
		private var _qtdAplicacao:Number;
		private var _vlrPorAplicacao:Number;

		public function get idModalidadeAplicacao():Number
		{
			return _idModalidadeAplicacao;
		}

		public function set idModalidadeAplicacao(value:Number):void
		{
			_idModalidadeAplicacao = value;
		}

		public function get idMotivoDevolucao():Number
		{
			return _idMotivoDevolucao;
		}

		public function set idMotivoDevolucao(value:Number):void
		{
			_idMotivoDevolucao = value;
		}

		public function get vlrPorAplicacao():Number
		{
			return _vlrPorAplicacao;
		}

		public function set vlrPorAplicacao(value:Number):void
		{
			_vlrPorAplicacao = value;
		}

		public function get qtdAplicacao():Number
		{
			return _qtdAplicacao;
		}

		public function set qtdAplicacao(value:Number):void
		{
			_qtdAplicacao = value;
		}

		public function get dtInicioParcelamento():String
		{
			return _dtInicioParcelamento;
		}

		public function set dtInicioParcelamento(value:String):void
		{
			_dtInicioParcelamento = value;
		}

		public function get idPessoa():Number
		{
			return _idPessoa;
		}

		public function set idPessoa(value:Number):void
		{
			_idPessoa = value;
		}

		public function get idPessoaLegado():Number
		{
			return _idPessoaLegado;
		}

		public function set idPessoaLegado(value:Number):void
		{
			_idPessoaLegado = value;
		}

		public function get dataHoraAtualizacao():IDateTime
		{
			return _dataHoraAtualizacao;
		}

		public function set dataHoraAtualizacao(value:IDateTime):void
		{
			_dataHoraAtualizacao = value;
		}

		public function get numContaCorrente():Number
		{
			return _numContaCorrente;
		}

		public function set numContaCorrente(value:Number):void
		{
			_numContaCorrente = value;
		}

		public function get tipoInteg():Number
		{
			return _tipoInteg;
		}

		public function set tipoInteg(value:Number):void
		{
			_tipoInteg = value;
		}

		public function get vlrParcelas():Number
		{
			return _vlrParcelas;
		}

		public function set vlrParcelas(value:Number):void
		{
			_vlrParcelas = value;
		}

		public function get qtdParcelas():Number
		{
			return _qtdParcelas;
		}

		public function set qtdParcelas(value:Number):void
		{
			_qtdParcelas = value;
		}

		public function get vlrAVista():Number
		{
			return _vlrAVista;
		}

		public function set vlrAVista(value:Number):void
		{
			_vlrAVista = value;
		}

		public function get vlrDevolucao():Number
		{
			return _vlrDevolucao;
		}

		public function set vlrDevolucao(value:Number):void
		{
			_vlrDevolucao = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

	}
}