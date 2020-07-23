package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.InfAcumuladaDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelInfoAcumuladasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.InfAcumuladaVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioSaldosMediosVO;

@RemoteService
public class RelSaldosMediosServico extends AtendimentoFachada {

	private InfAcumuladaDelegate infDel = 
			AtendimentoFabricaDelegates.getInstance().criarInfAcumuladaDelegate();
	
	private RelInfoAcumuladasDelegate relInfDel = 
			AtendimentoFabricaDelegates.getInstance().criarRelInfoAcumuladasDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
		throws BancoobException{
		
		List<PacVO> listaPac = obterListaPACs();
		PacVO pacVO = new PacVO();
		pacVO.setIdUnidade(-1);
		pacVO.setNomeUnidade("TODOS");
		listaPac.add(0, pacVO);
		
		String[] matriculas = obterMatricMinimaMaxima();
		
		RetornoDTO retorno = new RetornoDTO();
		
		retorno.getDados().put("listaPac", listaPac);
		retorno.getDados().put("listaBase", obterPeriodoPorTipoInformacao());
		retorno.getDados().put("matriculaInicial", Long.valueOf(matriculas[0]));
		retorno.getDados().put("matriculaFinal", Long.valueOf(matriculas[1]));
		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RelatorioSaldosMediosVO vo = (RelatorioSaldosMediosVO) 
				dto.getDados().get("RelatorioSaldosMediosVO");
		
		Object relatorio = relInfDel.gerarRelatorioSaldosMedios(vo);
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioSaldosMedios", relatorio);
		
		return new RetornoDTO();
	}
	
	private List<InfAcumuladaVO> obterPeriodoPorTipoInformacao() throws BancoobException{
		
		return infDel.obterPeriodoPorTipoInformacao(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, 
				ContaCapitalConstantes.COD_TIPO_INF_ACUMULADA_SALDO_MEDIO_REAL);
	}
	
}
