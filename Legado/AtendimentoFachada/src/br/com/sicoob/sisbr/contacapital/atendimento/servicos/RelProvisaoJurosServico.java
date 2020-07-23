package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ProvisaoJurosCCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelProvisaoJurosVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelProvisaoJurosServico extends AtendimentoFachada {

	private ProvisaoJurosCCapitalDelegate provJurosDel = 
			AtendimentoFabricaDelegates.getInstance().criarProvisaoJurosCCapitalDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
		throws BancoobException{		
		
		List<PacVO> listaPac = obterListaPACs();
		PacVO pacVO = new PacVO();
		pacVO.setIdUnidade(-1);
		pacVO.setNomeUnidade("TODOS");
		listaPac.add(0, pacVO);
		
		String[] matriculas = obterMatricMinimaMaxima();
		
		DateTime dtProduto = obterDataAtualProduto();
		
		RetornoDTO retorno = new RetornoDTO();
		
		retorno.getDados().put("anoProduto", Integer.valueOf(DataUtil.getAno(dtProduto)-1));
		retorno.getDados().put("listaPac", listaPac);
		retorno.getDados().put("matriculaInicial", Long.valueOf(matriculas[0]));
		retorno.getDados().put("matriculaFinal", Long.valueOf(matriculas[1]));
		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RelProvisaoJurosVO vo = (RelProvisaoJurosVO) 
				dto.getDados().get("RelProvisaoJurosVO");
		
		Object relatorio = provJurosDel.gerarRelProvJuros(vo);		
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioProvisaoJuros", relatorio);
	
		return new RetornoDTO();
	}
	
}
