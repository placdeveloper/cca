package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: antonio.genaro
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.SubscricaoRenVO", SubscricaoRenVO);
	public class SubscricaoRenVO extends BancoobVO {
		
		private var _idContaCapital:Number;
		private var _idInstituicao:Number;
		private var _numContaCapital:Number;
		private var _vlrSubs:Number;
		private var _vlrInteg:Number;
		private var _qtdParcelas:Number;
		private var _vlrParcelas:Number;
		private var _diaDebito:Number;
		private var _tipoInteg:Number;
		private var _numContaCorrente:Number;
		private var _dataHoraAtualizacao:IDateTime;
		private var _descNumMatriculaFunc:String;	
		private var _bolSubscricaoProposta:Number;
		private var _idTipoSubscricao:Number;
		private var _idPessoaLegado:Number;
		private var _idPessoa:Number;
		
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

		public function get bolSubscricaoProposta():Number
		{
			return _bolSubscricaoProposta;
		}

		public function set bolSubscricaoProposta(value:Number):void
		{
			_bolSubscricaoProposta = value;
		}

		public function get idTipoSubscricao():Number
		{
			return _idTipoSubscricao;
		}

		public function set idTipoSubscricao(value:Number):void
		{
			_idTipoSubscricao = value;
		}

		public function get descNumMatriculaFunc():String
		{
			return _descNumMatriculaFunc;
		}

		public function set descNumMatriculaFunc(value:String):void
		{
			_descNumMatriculaFunc = value;
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

		public function get diaDebito():Number
		{
			return _diaDebito;
		}

		public function set diaDebito(value:Number):void
		{
			_diaDebito = value;
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

		public function get vlrInteg():Number
		{
			return _vlrInteg;
		}

		public function set vlrInteg(value:Number):void
		{
			_vlrInteg = value;
		}

		public function get vlrSubs():Number
		{
			return _vlrSubs;
		}

		public function set vlrSubs(value:Number):void
		{
			_vlrSubs = value;
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