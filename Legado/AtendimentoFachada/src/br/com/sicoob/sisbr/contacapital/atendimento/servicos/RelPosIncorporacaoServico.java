package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;

public class RelPosIncorporacaoServico extends AtendimentoFachada {
	
	RelContaCapitalDelegate relCCADelegate = AtendimentoFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
				
		Integer tipoCooperativa = obterCooperativaTipo(new Integer(dto.getDados().get("numCooperativaLogado").toString()));		
		retorno.getDados().put("tipoCooperativa", tipoCooperativa);
		retorno.getDados().put("nomeCooperativa", obterNomePac(0));		

		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto) throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		Integer numCooperativaIncorporadora = new Integer(dto.getDados().get("numCooperativaIncorporadora").toString());
		Integer numCooperativaIncorporada = new Integer(dto.getDados().get("numCooperativaIncorporada").toString());
		
		Object relatorio = relCCADelegate.gerarRelPosIncorporacao(numCooperativaIncorporadora, numCooperativaIncorporada);		
		
		ContextoHttp.getInstance().adicionarContexto("RelPosIncorporacao", relatorio);				
		retornoDTO.getDados().put("RelPosIncorporacao", "RelPosIncorporacao");
				
		return retornoDTO;
	}	
}
