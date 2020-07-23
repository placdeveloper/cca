package br.com.bancoob.sisbr.negocio.dto
{
	import br.com.bancoob.dto.RetornoDTO;
	import br.com.bancoob.sisbr.negocio.vo.OutrosDadosVO;
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.bancoob.sisbr.negocio.dto.OutrosDadosReqDTO", 
					br.com.bancoob.sisbr.negocio.dto.OutrosDadosReqDTO);

	public class OutrosDadosReqDTO extends RetornoDTO
	{
		private var _dados_conta_capital:OutrosDadosVO; 

		public function get dadosContaCapital():OutrosDadosVO { 
			return _dados_conta_capital; 
		} 
		public function set dadosContaCapital(vlr:OutrosDadosVO):void {
			_dados_conta_capital = vlr;
		}

	}
}