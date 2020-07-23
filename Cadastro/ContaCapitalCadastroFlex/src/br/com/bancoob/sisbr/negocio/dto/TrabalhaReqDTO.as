package br.com.bancoob.sisbr.negocio.dto
{
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.sisbr.negocio.vo.TrabalhaVO;
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.bancoob.sisbr.negocio.dto.TrabalhaReqDTO",
					    br.com.bancoob.sisbr.negocio.dto.TrabalhaReqDTO);
					    
	public class TrabalhaReqDTO extends RequisicaoDTO
	{
		private var _dados_trabalha:TrabalhaVO;
		
		/*Dados Funcionário*/
		public function get DadosTrabalha():TrabalhaVO {
			return _dados_trabalha;
		}
		public function set DadosTrabalha(vlr:TrabalhaVO):void {
			_dados_trabalha = vlr;
		}
	}
}