package br.com.bancoob.sisbr.negocio.dto
{
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.sisbr.negocio.vo.PessoaVO;
	
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.bancoob.sisbr.negocio.dto.PessoaReqDTO",
					    br.com.bancoob.sisbr.negocio.dto.PessoaReqDTO);

	public class PessoaReqDTO extends RequisicaoDTO
	{
		private var _dados_pessoa:PessoaVO; 
		
		public function get DadosPessoa():PessoaVO { 
		      return _dados_pessoa; 
		} 
		public function set DadosPessoa(vlr:PessoaVO):void {
		      _dados_pessoa = vlr;
		}		
	}
}