package br.com.bancoob.sisbr.negocio.dto
{
	import br.com.bancoob.dto.RequisicaoDTO;
	
	import flash.net.registerClassAlias;
	import br.com.bancoob.sisbr.negocio.vo.LancFolhaPJVO;
	
	registerClassAlias("br.com.bancoob.sisbr.negocio.dto.LancFolhaPJReqDTO",
					    br.com.bancoob.sisbr.negocio.dto.LancFolhaPJReqDTO);
	
	public class LancFolhaPJReqDTO extends RequisicaoDTO
	{
		private var _dados_lanc_folha_pj:LancFolhaPJVO;
		private var _inf_profissional:Boolean;
		
		/*Dados Funcionário*/
		public function get DadosLancFolhaPJ():LancFolhaPJVO {
			return _dados_lanc_folha_pj;
		}
		public function set DadosLancFolhaPJ(vlr:LancFolhaPJVO):void {
			_dados_lanc_folha_pj = vlr;
		}
		
        //Utilizado pela funcionalidade Informações Profissionais (classe Trabalha)
		public function get InfProfissional():Boolean {
			return _inf_profissional;
		}
		public function set InfProfissional(vlr:Boolean):void {
			_inf_profissional = vlr;
		}
	}
}