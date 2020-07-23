package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelParcelamentoCCADelegate;

@RemoteService
public class RelReciboDebCcoServico extends AtendimentoFachada {

	RelParcelamentoCCADelegate relParcDelegate = AtendimentoFabricaDelegates.getInstance().criarRelParcelamentoCCADelegate();
	ParcelamentoCCADelegate parDelegate = AtendimentoFabricaDelegates.getInstance().criarParcelamentoCCADelegate();
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		Integer matricula = (Integer) dto.getDados().get("numMatric");
		Integer numParcelamento = (Integer) dto.getDados().get("numParc");
		Boolean parcAberto = (Boolean) dto.getDados().get("parcAberto");
		Object relatorio = null;
		relatorio = gerarRelatorioReciboDebitoCCO(matricula.longValue(), numParcelamento, parcAberto);
		ContextoHttp.getInstance().adicionarContexto(
				"Relatorio",
				relatorio);				
		retornoDTO.getDados().put("Relatorio", "Relatorio");
				
		return retornoDTO;
	}	

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Integer matricula = (Integer) dto.getDados().get("numMatricP");
		Integer pcca = parDelegate.obterParcelamentoAtual(matricula.longValue(), ContaCapitalConstantes.COD_TIPO_PARCELAMENTO_INTEGRAL, 
				ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CONTA);
		retorno.getDados().put("numParcAtual", pcca != null? pcca : 0);
		return retorno;
	}
	
	private Object gerarRelatorioReciboDebitoCCO(Long numMatricula, Integer numParcelamento, Boolean parcAberto) throws BancoobException{		
		Object relatorio = 
				relParcDelegate.gerarRelatorioReciboDebitoCCO(numMatricula, numParcelamento, parcAberto);
        return relatorio;
	}


}
