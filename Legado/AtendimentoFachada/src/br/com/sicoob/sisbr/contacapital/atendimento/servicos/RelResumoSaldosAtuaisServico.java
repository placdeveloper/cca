package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioResumoSaldosAtuaisVO;

@RemoteService
public class RelResumoSaldosAtuaisServico extends AtendimentoFachada {

	private RelContaCapitalDelegate relCcaDel = 
			AtendimentoFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
		throws BancoobException{
		
		List<PacVO> listaPac = obterListaPACs();
		PacVO pacVO = new PacVO();
		pacVO.setIdUnidade(-1);
		pacVO.setNomeUnidade("TODOS");
		listaPac.add(0, pacVO);
		
		String[] matriculas = obterMatricMinimaMaxima();
		
		RetornoDTO retorno = new RetornoDTO();
		
		String valor = obterParametro(900);
		
		retorno.getDados().put("valorPar", Integer.valueOf(valor));
		retorno.getDados().put("listaPac", listaPac);
		retorno.getDados().put("matriculaInicial", Long.valueOf(matriculas[0]));
		retorno.getDados().put("matriculaFinal", Long.valueOf(matriculas[1]));
		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RelatorioResumoSaldosAtuaisVO vo = (RelatorioResumoSaldosAtuaisVO) 
				dto.getDados().get("RelatorioResumoSaldosAtuaisVO");
		
		Object relatorio = relCcaDel.gerarRelResSaldosAtuais(vo);
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioResumoSaldosAtuais", relatorio);
			
		return new RetornoDTO();
	}
	
}
