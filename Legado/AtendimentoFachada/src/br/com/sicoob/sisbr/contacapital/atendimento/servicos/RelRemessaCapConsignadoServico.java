package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelRemessaCapConsignadoVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelRemessaCapConsignadoServico extends AtendimentoFachada {

	private RelContaCapitalDelegate relCcaDel = 
			AtendimentoFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
		throws BancoobException{
		
		String[] matriculas = obterMatricMinimaMaxima();

		List<PacVO> listaPac = obterListaPACs();
		PacVO pacVO = new PacVO();
		pacVO.setIdUnidade(-1);
		pacVO.setNomeUnidade("TODOS");
		listaPac.add(0, pacVO);
		
		DateTime dtProduto = obterDataAtualProduto();
		
		Integer mesProduto = DataUtil.getMes(dtProduto) + 1;
		Integer anoProduto = DataUtil.getAno(dtProduto);
		
		RetornoDTO retorno = new RetornoDTO();
		
		retorno.getDados().put("mesProduto", mesProduto);
		retorno.getDados().put("anoProduto", anoProduto);
		retorno.getDados().put("listaPac", listaPac);
		retorno.getDados().put("listaSituacao", obterListaSituacao());
		retorno.getDados().put("matriculaInicial", Long.valueOf(matriculas[0]));
		retorno.getDados().put("matriculaFinal", Long.valueOf(matriculas[1]));
		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
		throws BancoobException{
		
		RelRemessaCapConsignadoVO vo = (RelRemessaCapConsignadoVO) 
				dto.getDados().get("RelRemessaCapConsignadoVO");
		
		Object relatorio = relCcaDel.gerarRelRemessaCapConsignado(vo);
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelRemessaCapConsignado", relatorio);
		
		return new RetornoDTO();	
	}

	private List<ItemListaVO> obterListaSituacao() throws BancoobException {
		List<ItemListaVO> listaGrupoHist = obterLista(
				ContaCapitalConstantes.LST_SIT_RETORNO_FOLHA);
		ItemListaVO itvo = new ItemListaVO();
		itvo.setCodigoItem("-1");
		itvo.setDescricao("TODAS");
		listaGrupoHist.add(0, itvo);
		return listaGrupoHist;
	}
	
	public RetornoDTO verificarSeEmpresaPrepRemessa(RequisicaoReqDTO dto)throws BancoobException {	
		RelRemessaCapConsignadoVO vo = (RelRemessaCapConsignadoVO) dto.getDados().get("RelRemessaCapConsignadoVO");
		RetornoDTO retorno = new RetornoDTO();		
		retorno.getDados().put("bolEmpPreparaRemessa",  relCcaDel.verificarSeEmpresaPrepRemessa(vo));		
		return retorno;
	}
	
}
