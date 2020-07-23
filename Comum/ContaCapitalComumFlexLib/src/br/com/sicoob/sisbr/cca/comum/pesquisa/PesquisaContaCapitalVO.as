package br.com.sicoob.sisbr.cca.comum.pesquisa {
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.comum.vo.PesquisaContaCapitalVO", PesquisaContaCapitalVO)
	public class PesquisaContaCapitalVO extends BancoobVO {
		
		private var _numContaCapital:Number;
		private var _idPessoa:Number;
		private var _idPessoaLegado:Number;
		private var _idInstituicao:Number;
		private var _cpfCnpj:String;
		private var _nome:String;
		private var _descSituacaoContaCapital:String;
		private var _idSituacaoContaCapital:Number;
		private var _idSituacaoCadastro:Number;
		private var _idContaCapital:Number;
		private var _codTipoPessoa:Number;
		
		public function get idContaCapital():Number
		{
			return _idContaCapital;
		}

		public function set idContaCapital(value:Number):void
		{
			_idContaCapital = value;
		}

		public function get numContaCapital():Number
		{
			return _numContaCapital;
		}

		public function set numContaCapital(value:Number):void
		{
			_numContaCapital = value;
		}

		public function get idPessoa():Number
		{
			return _idPessoa;
		}

		public function set idPessoa(value:Number):void
		{
			_idPessoa = value;
		}

		public function get idInstituicao():Number
		{
			return _idInstituicao;
		}

		public function set idInstituicao(value:Number):void
		{
			_idInstituicao = value;
		}

		public function get cpfCnpj():String
		{
			return _cpfCnpj;
		}

		public function set cpfCnpj(value:String):void
		{
			_cpfCnpj = value;
		}

		public function get nome():String
		{
			return _nome;
		}

		public function set nome(value:String):void
		{
			_nome = value;
		}

		public function get descSituacaoContaCapital():String
		{
			return _descSituacaoContaCapital;
		}

		public function set descSituacaoContaCapital(value:String):void
		{
			_descSituacaoContaCapital = value;
		}

		public function get idSituacaoContaCapital():Number
		{
			return _idSituacaoContaCapital;
		}

		public function set idSituacaoContaCapital(value:Number):void
		{
			_idSituacaoContaCapital = value;
		}

		public function get idSituacaoCadastro():Number
		{
			return _idSituacaoCadastro;
		}

		public function set idSituacaoCadastro(value:Number):void
		{
			_idSituacaoCadastro = value;
		}

		public function get idPessoaLegado():Number
		{
			return _idPessoaLegado;
		}

		public function set idPessoaLegado(value:Number):void
		{
			_idPessoaLegado = value;
		}

		public function get codTipoPessoa():Number
		{
			return _codTipoPessoa;
		}

		public function set codTipoPessoa(value:Number):void
		{
			_codTipoPessoa = value;
		}
	}
}