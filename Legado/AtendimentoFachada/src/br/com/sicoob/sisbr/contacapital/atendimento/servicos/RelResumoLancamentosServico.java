package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelLancamentosDelegate;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelResumoLancamentosServico extends AtendimentoFachada {

	private RelLancamentosDelegate relLancDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarRelLancamentosDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException{
		
		DateTime dtProduto = obterDataAtualMovimento();	
		
		RetornoDTO retorno = new RetornoDTO();
		
		Date dataInicioMes = DataUtil.obterPrimeiroDiaMes(dtProduto);
		Date dataFimMes = DataUtil.obterUltimoDiaMes(dtProduto);
		
		retorno.getDados().put("dataInicioMes", 
				new DateTime(dataInicioMes.getTime()));
		retorno.getDados().put("dataFimMes", 
				new DateTime(dataFimMes.getTime()));
		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException{
		
		DateTime dataInicial = (DateTime) dto.getDados().get("dataInicial");
		DateTime dataFinal = (DateTime) dto.getDados().get("dataFinal");
		
		RetornoDTO retorno = new RetornoDTO();
		
		Object relatorio = relLancDelegate.gerarRelResumo(dataInicial, dataFinal);
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioResumoLancamentos", relatorio);
		
		return retorno;	
	}
	
}
