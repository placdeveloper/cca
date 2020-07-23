package br.com.sicoob.sisbr.cca.comum.pesquisaempresa {
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;
	
	registerClassAlias("br.com.sicoob.sisbr.cca.comum.vo.PesquisaEmpresaVO", PesquisaEmpresaVO)
	public class PesquisaEmpresaVO extends BancoobVO {
		
		private var _numPessoaJuridica:Number;
		private var _descNomePessoa:String;
		private var _diaFolha:Number;
		private var _qtdeDiasGeraInf:Number;
		private var _numCGC_CPF:String;
		
		public function get numPessoaJuridica():Number
		{
			return _numPessoaJuridica;
		}

		public function set numPessoaJuridica(value:Number):void
		{
			_numPessoaJuridica = value;
		}

		public function get diaFolha():Number
		{
			return _diaFolha;
		}

		public function set diaFolha(value:Number):void
		{
			_diaFolha = value;
		}

		public function get qtdeDiasGeraInf():Number
		{
			return _qtdeDiasGeraInf;
		}

		public function set qtdeDiasGeraInf(value:Number):void
		{
			_qtdeDiasGeraInf = value;
		}

		public function get descNomePessoa():String
		{
			return _descNomePessoa;
		}

		public function set descNomePessoa(value:String):void
		{
			_descNomePessoa = value;
		}

		public function get numCGC_CPF():String
		{
			return _numCGC_CPF;
		}

		public function set numCGC_CPF(value:String):void
		{
			_numCGC_CPF = value;
		}
	}
}