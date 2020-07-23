package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelLancamentosDelegate;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelLancNaoContabilizadosServico extends AtendimentoFachada {

	private RelLancamentosDelegate relLancDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarRelLancamentosDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		
		DateTime dtProduto = obterDataAntProduto();
		retorno.getDados().put("dtProduto", dtProduto);
		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
		throws BancoobException{
		
		DateTime dataInicial = (DateTime) dto.getDados().get("dataInicial");
		DateTime dataFinal = (DateTime) dto.getDados().get("dataFinal");
		Integer tipoRel = Integer.valueOf(
				dto.getDados().get("tipoRel").toString());
		
		if(tipoRel == 1){
			gerarRelatorioAnalitico(dataInicial, dataFinal);
		}else {
			gerarRelatorioSintetico(dataInicial, dataFinal);
		}
		
		return new RetornoDTO();	
	}

	private void gerarRelatorioSintetico(DateTime dataInicial,
			DateTime dataFinal) throws BancoobException{
		Object relatorio = relLancDelegate.gerarRelLancNaoContabSint(dataInicial, dataFinal);
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioLancamentosNaoContab", relatorio);
	}

	private void gerarRelatorioAnalitico(DateTime dataInicial,
			DateTime dataFinal) throws BancoobException{
		Object relatorio = relLancDelegate.gerarRelLancNaoContabAnalit(dataInicial, dataFinal);
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioLancamentosNaoContab", relatorio);
	}
	
}
