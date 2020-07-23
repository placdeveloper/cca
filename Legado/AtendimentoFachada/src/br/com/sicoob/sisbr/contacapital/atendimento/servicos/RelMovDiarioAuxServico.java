package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelLancamentosDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelMovDiarioAuxVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelMovDiarioAuxServico extends AtendimentoFachada {

	private RelLancamentosDelegate relLancDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarRelLancamentosDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
		throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		
		DateTime dtProduto = obterDataAtualProduto();
		retorno.getDados().put("dtProduto", dtProduto);
		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RelMovDiarioAuxVO vo = (RelMovDiarioAuxVO) dto.getDados().get("RelMovDiarioAuxVO");
		Object relatorio = relLancDelegate.gerarRelMovDiarioAux(vo);		
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioMovDiarioAux", relatorio);
		
		return new RetornoDTO();	
		
	}
	
}
