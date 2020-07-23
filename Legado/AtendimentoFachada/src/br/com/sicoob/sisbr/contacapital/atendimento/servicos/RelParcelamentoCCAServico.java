package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelParcelamentoCCADelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioParcelamentoCCAVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelParcelamentoCCAServico extends AtendimentoFachada {

	private static final String KEY_DATA_INICIAL = "dataInicial";
	private static final String KEY_DATA_FINAL = "dataFinal";
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		List<PacVO> lista = obterListaPACs();
		montaListaPacVO(lista);
		retornoDTO.getDados().put("listaPacs",lista);		
		List<String> listaTipoParcelamento = new ArrayList<String>(3);

		List<ItemListaVO> listaSituacao = obterLista(880);
		retornoDTO.getDados().put("listaSituacao",listaSituacao);		
			
		List<String> listaOrdenacao = new ArrayList<String>();
		listaOrdenacao.add("MATRÍCULA");//POSIÇÃO 0
		listaOrdenacao.add("NOME CLIENTE");//POSIÇÃO 1
		listaOrdenacao.add("DATA VENCIMENTO");//POSIÇÃO 1
		retornoDTO.getDados().put("listaOrdenacao",listaOrdenacao);	
		
		listaTipoParcelamento.add(0,"INTEGRALIZAÇÃO");//POSIÇÃO 1
		listaTipoParcelamento.add(1,"DEVOLUÇÃO");//POSIÇÃO 2
		
	
		retornoDTO.getDados().put("listaTipoParcelamento",listaTipoParcelamento);		
		String[] minMax = obterMatricMinimaMaxima();
		retornoDTO.getDados().put("matriculaInicial", minMax[0]);
		retornoDTO.getDados().put("matriculaFinal",minMax[1]);
		
		retornoDTO.getDados().put("dataAtualProduto", new DateTime());
		
		int anoAtual = DataUtil.getAno(new Date());
	
		Date dataIn=converterData("01/01/"+anoAtual, "dd/MM/yyyy");
		Date dataFn=converterData("31/12/"+anoAtual, "dd/MM/yyyy");
				
		retornoDTO.getDados().put(KEY_DATA_INICIAL,new DateTime(dataIn.getTime()));
		retornoDTO.getDados().put(KEY_DATA_FINAL, new DateTime(dataFn.getTime()));	
		
		return retornoDTO;
	}

	protected static Date converterData(String date, String mascara) {
		SimpleDateFormat sdf = new SimpleDateFormat(mascara);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		RelatorioParcelamentoCCAVO relParcelamento = (RelatorioParcelamentoCCAVO)dto.getDados().get("relParcelamentoCCAVO");		
		Object relatorio = 
				RelParcelamentoCCADelegate.getInstance().gerarRelatorioParcelamentoCCA(relParcelamento);
		ContextoHttp.getInstance().adicionarContexto(
				"Relatorio",
				relatorio);				
		retornoDTO.getDados().put("Relatorio", "Relatorio");
		return null;
	}


	
}
