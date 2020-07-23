/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelValorParametroDelegate;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * Fachada RelValorParametroServico
 */
@RemoteService
public class RelValorParametroServico extends RelatoriosContaCapital {

	private RelValorParametroDelegate vlrParamDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelValorParametroDelegate();
	
	/**
	 * Método que recebe o objeto de requisição (dto) com os valores para gerar relatorio historico de valor parametro
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO gerarRelatorio(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		List<Integer> idsInstituicao = (List) dto.getDados().get("idsInstituicao");
		
		Integer idConfiguracao = Integer.valueOf(dto.getDados().get("idConfiguracao").toString());
		
		if(idsInstituicao != null && idConfiguracao != null) {
			ContextoHttp.getInstance().adicionarContexto("CCA_Relatorio_HistValorParametro", vlrParamDelegate.gerarRelatorio(idsInstituicao, idConfiguracao));	
		}
		
		return retorno;
	}
}