package br.com.sicoob.sisbr.cca.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;

	/**
	 * @author: marco.nascimento
	 */
	registerClassAlias("br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO", DesligarContaCapitalRenVO);
	public class DesligarContaCapitalRenVO extends BancoobVO {
		
		private var _idContaCapital:Number;
		private var _idInstituicao:Number;
		private var _numContaCapital:Number;
		private var _idPessoaLegado:Number;
		private var _idPessoa:Number;
		
		private var _vlrSubs:Number;
		private var _vlrInteg:Number;
		private var _vlrAInteg:Number;
		private var _vlrDevol:Number;
		private var _vlrBloq:Number;
		private var _vlrEmprestimos:Number;
		private var _qtdCotas:int;
		private var _tipoOperacao:int;
		private var _dataDesligamento:IDateTime;
		
		public function get vlrEmprestimos():Number
		{
			return _vlrEmprestimos;
		}

		public function set vlrEmprestimos(value:Number):void
		{
			_vlrEmprestimos = value;
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

		public function get vlrSubs():Number
		{
			return _vlrSubs;
		}

		public function set vlrSubs(value:Number):void
		{
			_vlrSubs = value;
		}

		public function get vlrInteg():Number
		{
			return _vlrInteg;
		}

		public function set vlrInteg(value:Number):void
		{
			_vlrInteg = value;
		}

		public function get vlrAInteg():Number
		{
			return _vlrAInteg;
		}

		public function set vlrAInteg(value:Number):void
		{
			_vlrAInteg = value;
		}

		public function get vlrBloq():Number
		{
			return _vlrBloq;
		}

		public function set vlrBloq(value:Number):void
		{
			_vlrBloq = value;
		}

		public function get qtdCotas():int
		{
			return _qtdCotas;
		}

		public function set qtdCotas(value:int):void
		{
			_qtdCotas = value;
		}

		public function get tipoOperacao():int
		{
			return _tipoOperacao;
		}

		public function set tipoOperacao(value:int):void
		{
			_tipoOperacao = value;
		}

		public function get vlrDevol():Number
		{
			return _vlrDevol;
		}

		public function set vlrDevol(value:Number):void
		{
			_vlrDevol = value;
		}

		public function get dataDesligamento():IDateTime
		{
			return _dataDesligamento;
		}

		public function set dataDesligamento(value:IDateTime):void
		{
			_dataDesligamento = value;
		}
	}
}