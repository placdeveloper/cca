package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelLancamentosDelegate;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelConciliacaoContabilServico extends AtendimentoFachada {

	private RelLancamentosDelegate relLancamentosDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelLancamentosDelegate();
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		DateTime dataAtualProduto = obterDataAtualProduto();
		
		List<ItemListaVO> listaGrupo = obterLista(ContaCapitalConstantes.LST_GRUPO_HISTORICO_CCAPITAL);
		
		retorno.getDados().put("dataAtualProduto",dataAtualProduto);
		retorno.getDados().put("listaGrupo",listaGrupo);
		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		
		Date dataInicial = (Date) dto.getDados().get("dataInicial");
		Date dataFinal = (Date) dto.getDados().get("dataFinal");
		String grupo = (String) dto.getDados().get("grupo");
		String relatorio = (String) dto.getDados().get("relatorio");
		
		Object conteudoRelatorio = relLancamentosDelegate
				.gerarRelatorioConciliacaoContabil(dataInicial, dataFinal,
						grupo, "1".equals(relatorio));
		
		ContextoHttp.getInstance().adicionarContexto("CCARelatorioConciliacaoContabil", conteudoRelatorio);
		
		return null;
	}


	
}
