package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;

@RemoteService
public class RelFichaMatriculaServico extends AtendimentoFachada {

	RelContaCapitalDelegate relCCADelegate = AtendimentoFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		// TODO Auto-generated method stub
		return null;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		Integer matricula = (Integer) dto.getDados().get("numMatric");
		Integer numPessoa = (Integer) dto.getDados().get("numPessoa");
		Integer iTipoDocumento = (Integer) dto.getDados().get("iTipoDocumento");
		Object relatorio = null;
		relatorio = gerarRelFicha(iTipoDocumento, matricula.longValue(), numPessoa.longValue());
		ContextoHttp.getInstance().adicionarContexto("RelFichaMatricula", relatorio);				
		retornoDTO.getDados().put("RelFichaMatricula", "RelFichaMatricula");
				
		return retornoDTO;
	}

	private Object gerarRelFicha(Integer iTipoDocumento, Long matricula,
			Long numPessoa) throws BancoobException {
		Object relatorio = 
				relCCADelegate.gerarRelFicha(iTipoDocumento, matricula, numPessoa);
        return relatorio;
	}


	
}
