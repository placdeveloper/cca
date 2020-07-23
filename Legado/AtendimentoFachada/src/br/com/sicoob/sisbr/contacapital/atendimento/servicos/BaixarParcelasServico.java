package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.BaixarParcelasCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.BaixarParcelasCCAVO;

@RemoteService
public class BaixarParcelasServico extends AtendimentoFachada {
	private BaixarParcelasCCADelegate delegate = AtendimentoFabricaDelegates.getInstance().criarBaixarParcelasCCADelegate();
	
	public RetornoDTO baixarParcelasCCA(RequisicaoReqDTO dto) throws AtendimentoCadastroException{

		RetornoDTO retorno = new RetornoDTO();
		List<BaixarParcelasCCAVO> listaDados = (List<BaixarParcelasCCAVO>) dto.getDados().get("listaBaixarParcelas");			
		Boolean salvarRetorno = delegate.baixarParcelasCCA(listaDados);
		retorno.getDados().put("salvarRetorno",salvarRetorno);
		return retorno;
		
	}
}
