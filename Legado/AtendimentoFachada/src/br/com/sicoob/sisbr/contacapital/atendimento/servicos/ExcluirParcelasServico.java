package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ExcluirParcelasCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.ExcluirParcelasCCAVO;

@RemoteService
public class ExcluirParcelasServico extends AtendimentoFachada {

	private ExcluirParcelasCCADelegate delegate = AtendimentoFabricaDelegates.getInstance().criarExcluirParcelasCCADelegate();
	
	public RetornoDTO excluirParcelasCCA(RequisicaoReqDTO dto) throws AtendimentoCadastroException{

		RetornoDTO retorno = new RetornoDTO();
		List<ExcluirParcelasCCAVO> listaDados = (List<ExcluirParcelasCCAVO>) dto.getDados().get("listaExcluirParcelas");			
		Boolean salvarRetorno = delegate.excluirParcelasCCA(listaDados);
		retorno.getDados().put("salvarRetorno",salvarRetorno);
		return retorno;
		
	}
}
