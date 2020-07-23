package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.retaguarda.comum.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.RelContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.RelatorioSituacaoCCAVO;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelSituacaoPeriodoCCAServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	private static final String KEY_DATA_INICIAL = "dataInicial";
	private static final String KEY_DATA_FINAL = "dataFinal";
	private static final String KEY_LISTA_PACS = "listaPacs";
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retornoDTO = new RetornoDTO();
		List<PacVO> lista = obterListaPACs();
		montaListaPacVO(lista);
		retornoDTO.getDados().put(KEY_LISTA_PACS,lista);
		List<String> listaOrdenacao = new ArrayList<String>();
		listaOrdenacao.add("MATRÍCULA");//POSIÇÃO 0
		listaOrdenacao.add("NOME");//POSIÇÃO 1
		retornoDTO.getDados().put("listaOrdenacao",listaOrdenacao);	
		
		List<String> listaSituacao = new ArrayList<String>();
		listaSituacao.add("EM ABERTO");//POSIÇÃO 0
		listaSituacao.add("DESLIGADO");//POSIÇÃO 1				
		listaSituacao.add("TODAS");//POSIÇÃO 2
		retornoDTO.getDados().put("listaSituacao",listaSituacao);		
		
		String[] minMax = obterMatricMinimaMaxima();
		retornoDTO.getDados().put("matriculaInicial", minMax[0]);
		retornoDTO.getDados().put("matriculaFinal",minMax[1]);
		
		DateTime dtAtualProduto = obterDataAtualProduto();
		
		retornoDTO.getDados().put(KEY_DATA_INICIAL, new DateTime(DataUtil
				.obterPrimeiroDiaMes(dtAtualProduto).getTime()));
		retornoDTO.getDados().put(KEY_DATA_FINAL, new DateTime(DataUtil.obterUltimoDiaMes(dtAtualProduto).getTime()));		
		
		return retornoDTO;
	}
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {

		RetornoDTO retornoDTO = new RetornoDTO();		
		RelContaCapitalProxy relContaCapital = (RelContaCapitalProxy)dto.getDados().get("relContaCapitalProxy");		
		List<RelatorioSituacaoCCAVO> lista = RelContaCapitalDelegate.getInstance().gerarDadosRelatorioSituacaoPeriodoCCA(relContaCapital);		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		
		String siglaCooperativa = obterSiglaCooperativa();
		String descricaoCoop = obterCooperativaFmt() 
				+ " - " + siglaCooperativa;
		parametros.put("DESCRICAO_COOP", descricaoCoop);
		parametros.put("NOME_COOPERATIVA", siglaCooperativa);
		parametros.put("CODIGO_RELATORIO", "CCA-035");
		String descricao = "Período: "+DataUtil.formataData(relContaCapital.getDtmDataInicial(), "dd/MM/yyyy")
				+" a "+DataUtil.formataData(relContaCapital.getDtmDataFinal(), "dd/MM/yyyy");
		
		parametros.put("DESCRICAO_PERIODO", descricao	);
		parametros.put("DATA_PROCESSAMENTO", new Date());
		
		if(relContaCapital.getiNumPac()!=null && relContaCapital.getiNumPac() != -1){
			relContaCapital.setDescricaoPac("Cooperativa/Pac: "+relContaCapital.getDescricaoPac());
			parametros.put("DESCRICAO_PAC", relContaCapital.getDescricaoPac());
		}
					
		RelatorioAtendimentoCCA<RelatorioSituacaoCCAVO> rel = new RelatorioAtendimentoCCA<RelatorioSituacaoCCAVO>(
				lista, "CCARelSituacaoPeriodoCCA.jasper", parametros);
							
		Object conteudo = rel.gerarSincronamente();
		ContextoHttp.getInstance().adicionarContexto("CCARelSituacaoPeriodoCCA", conteudo);
		
		return retornoDTO;
	}
	
	

	
}
