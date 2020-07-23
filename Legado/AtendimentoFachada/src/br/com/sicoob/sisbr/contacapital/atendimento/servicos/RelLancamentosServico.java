package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.bancoob.comum.negocio.delegates.FabricaDelegates;
import br.com.bancoob.comum.negocio.delegates.TipoHistoricoDelegate;
import br.com.bancoob.comum.negocio.entidades.TipoHistorico;
import br.com.bancoob.comum.negocio.entidades.pk.TipoHistoricoPK;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.contacapital.comum.vo.TipoHistoricoVO;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.CapaLoteCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelLancamentosDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioLancamentosVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelLancamentosServico extends AtendimentoFachada {

	private RelLancamentosDelegate relLancDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarRelLancamentosDelegate();
	
	private CapaLoteCapitalDelegate clCcaDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarCapaLoteCapitalDelegate();	
	
	private TipoHistoricoDelegate tipoHistDelegate = 
		FabricaDelegates.getInstance().criarTipoHistoricoDelegate();

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		
		/*Integer codTipoOperacao = Integer.valueOf(
				dto.getDados().get("codTipoOperacao").toString());*/
		
		DateTime dtProduto = obterDataAtualMovimento();		
		String[] matriculas = obterMatricMinimaMaxima();
		
		Long[] lotes = clCcaDelegate.obterNrLoteCCAMinMax();
		
		List<ItemListaVO> listaGrupoHist = obterListaHist();
		
		RetornoDTO retorno = new RetornoDTO();
		
		retorno.getDados().put("listaGrupoHist", listaGrupoHist);
		retorno.getDados().put("dtProduto", dtProduto);
		retorno.getDados().put("loteInicial", lotes[0]);
		retorno.getDados().put("loteFinal", lotes[1]);
		retorno.getDados().put("matriculaInicial", Long.valueOf(matriculas[0]));
		retorno.getDados().put("matriculaFinal", Long.valueOf(matriculas[1]));
		
		return retorno;
	}

	public RetornoDTO obterListaHistorico(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<TipoHistorico> listaAux = listarPorProduto();
		
		List<TipoHistoricoVO> listaHist = new ArrayList<TipoHistoricoVO>();
		
		Iterator<TipoHistorico> it = listaAux.iterator();
		while (it.hasNext()) {
			
			TipoHistorico tipoHistorico = (TipoHistorico) it.next();
			
			TipoHistoricoVO vo = new TipoHistoricoVO();
			vo.setIdTipoHistorico(tipoHistorico.getId().getIdTipoHistorico());
			vo.setDescHistorico(tipoHistorico.getDescHistorico());			
			listaHist.add(vo);
		}
		
		retornoDTO.getDados().put("listaTipoHistorico", listaHist);
		
		return retornoDTO;
	}	

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RelatorioLancamentosVO vo = (RelatorioLancamentosVO) 
				dto.getDados().get("RelatorioLancamentosVO");
		
		if(vo.getTipo() == 1){
			emitirRelatorioPorLote(vo);
		}else{
			emitirRelatorioPorMatricula(vo);
		}
		
		RetornoDTO retornoDTO = new RetornoDTO();
		return retornoDTO;
	}
	
	private void emitirRelatorioPorMatricula(RelatorioLancamentosVO vo) 
			throws BancoobException {
		
		Object relatorio = relLancDelegate.gerarRelarioMatricula(vo);
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioLancamentos", relatorio);
		
	}

	private void emitirRelatorioPorLote(RelatorioLancamentosVO vo) 
			throws BancoobException {
		
		Object relatorio = relLancDelegate.gerarRelarioLote(vo);
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelatorioLancamentos", relatorio);
		
	}

	private List<ItemListaVO> obterListaHist() throws BancoobException {
		List<ItemListaVO> listaGrupoHist = obterLista(
				ContaCapitalConstantes.LST_GRUPO_HISTORICO_CCAPITAL);
		ItemListaVO itvo = new ItemListaVO();
		itvo.setCodigoItem("0");
		itvo.setDescricao("TODOS");
		listaGrupoHist.add(0, itvo);
		return listaGrupoHist;
	}

	private List<TipoHistorico> listarPorProduto() throws BancoobException {
		
		TipoHistoricoPK auxPk = new TipoHistoricoPK();
		auxPk.setIdProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL.longValue());
		
		TipoHistorico auxTh = new TipoHistorico();
		auxTh.setId(auxPk);
		
		ConsultaDto<TipoHistorico> dto = new ConsultaDto<TipoHistorico>();
		dto.setFiltro(auxTh);
		
		List<TipoHistorico> listaAux = tipoHistDelegate.listar(dto);
		return listaAux;
	}

	
}
