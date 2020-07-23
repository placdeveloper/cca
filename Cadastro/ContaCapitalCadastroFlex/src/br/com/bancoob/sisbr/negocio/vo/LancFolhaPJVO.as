package br.com.bancoob.sisbr.negocio.vo
{
	import br.com.bancoob.vo.BancoobVO;
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.bancoob.sisbr.negocio.vo.LancFolhaPJVO",
					    br.com.bancoob.sisbr.negocio.vo.LancFolhaPJVO);
	
	public class LancFolhaPJVO extends BancoobVO
	{
		private var _cod_tipo_dado_matricula:int;	
		private var _num_pessoa_juridica:int;
		
		// CodTipoDadoMatricula
		public function get CodTipoDadoMatricula():int
		{ return _cod_tipo_dado_matricula; }
		public function set CodTipoDadoMatricula(vlr:int):void
		{ _cod_tipo_dado_matricula = vlr; }
		
		// NumPessoaJuridica
		public function get NumPessoaJuridica():int
		{ return _num_pessoa_juridica; }
		public function set NumPessoaJuridica(vlr:int):void
		{ _num_pessoa_juridica = vlr; }
	}
}