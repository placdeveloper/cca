package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.excecao.RelatorioException;
import br.com.bancoob.negocio.relatorios.ColecaoDataSource;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.retaguarda.comum.RecuperadorCooperativa;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.RelContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosRelCapRealDevVO;

@RemoteService
public class RelCapRealDevServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	RelContaCapitalDelegate relCcaDelegate = AtendimentoFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String[] minMax = obterMatricMinimaMaxima();
		
		retorno.getDados().put("dtAtualProd", obterDataAtualProduto());
		retorno.getDados().put("pacs", recuperarListaPACs());
		retorno.getDados().put("itens", preencherMotivo());
		retorno.getDados().put("min", minMax[0]);
		retorno.getDados().put("max", minMax[1]);
		
		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		final RelContaCapitalProxy vo = (RelContaCapitalProxy) dto.getDados().get("filtro");
		relCcaDelegate.validarCamposRelCapRealDev(vo);
		List<DadosRelCapRealDevVO> dados = relCcaDelegate.gerarRelCapRealDev(vo);		
		Map<String, Object> parametros = new HashMap<String, Object>();		
		setarParametros(parametros, vo);
		if(!vo.getbAtivos()){
			List<DadosRelCapRealDevVO> filtrados = new ArrayList<DadosRelCapRealDevVO>();
			for(DadosRelCapRealDevVO drcr: dados){
				if(drcr.getValorCalculado().compareTo(BigDecimal.ZERO) > 0){
					filtrados.add(drcr);
				}
			}
			gerarRelatorio(filtrados, parametros);
		}else
			gerarRelatorio(dados, parametros);
		return new RetornoDTO();
	}	
	
	private void setarParametros(Map<String, Object> parametros,
			RelContaCapitalProxy vo) throws BancoobException {
		String sNomeCoop = RecuperadorCooperativa.getInstance().obterSiglaCooperativa(obterCooperativa());
		String nomePac = "";
		String txtTipoRel = "";
		String txtNomeRelatorio = "";
		String txtMotivo = "";
		String txtPeriodo = "";
		String txtDataDev = "";
		String txtMotivoDev = "";
		String txtTipoSubRel = "";
		Integer tipoRel = null;
		List<ItemListaVO> motivos = obterLista(ContaCapitalConstantes.LST_MOTIVOS_DEVOLUCAO_CAPITAL);
		
		if (vo.getiNumPac() != -1) {
			nomePac = "Cooperativa/Pac: " + new DecimalFormat("00").format(Integer.valueOf(vo.getiNumPac())) + " - " + obterNomePac(vo.getiNumPac());
		}		
		switch (vo.getiTipo().intValue()) {
		case 0:
			txtTipoRel = "A Realizar";
			txtNomeRelatorio = "Relatório de Capital a Realizar";
			tipoRel = 0;
			break;
		case 1:
			if(vo.getiForma().intValue() == 0){
				txtMotivo = "Motivo de Devolução: TODOS";
			}else{
				for(ItemListaVO li: motivos){
					if(li.getCodigoItem().equals(vo.getiForma().toString())){
						txtMotivo = "Motivo de Devolução: " + li.getDescricao();
					}
				}
			}
			txtTipoRel = "A Devolver";
			txtNomeRelatorio = "Relatório de Capital a Devolver";
			txtPeriodo = "Solicitação de Devolução: de " + DataUtil.toStringBr(vo.getDtmDataInicial()) + " a " + DataUtil.toStringBr(vo.getDtmDataFinal());
			txtDataDev = "Sol. Devolução";
			txtMotivoDev = "Motivo Devolução";
			txtTipoSubRel = "A Devolver";
			tipoRel = 2;
			break;
		default:
			if(vo.getiForma().intValue() == 0){
				txtMotivo = "Motivo de Devolução: TODOS";
			}else{
				for(ItemListaVO li: motivos){
					if(li.getCodigoItem().equals(vo.getiForma().toString())){
						txtMotivo = "Motivo de Devolução: " + li.getDescricao();
					}
				}
			}
			txtTipoRel = "Devolvido";
			txtNomeRelatorio = "Relatório de Capital Devolvido";
			txtPeriodo = "Data de Devolução: de " + DataUtil.toStringBr(vo.getDtmDataInicial()) + " a " + DataUtil.toStringBr(vo.getDtmDataFinal());
			txtDataDev = "Data";
			txtMotivoDev = "Motivo Devolução";
			txtTipoSubRel = "Devolvido";
			tipoRel = 3;
			break;
		}		
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("TXT_NOME_RELATORIO", txtNomeRelatorio);
		parametros.put("TXT_PERIODO", txtPeriodo);
		parametros.put("TXT_ORDENACAO", (vo.getiOrdenacao().intValue() == 0 ? "Ordenação: Matrícula": "Ordenação: Nome Cliente"));
		parametros.put("TXT_MATRIC", "Matrículas: " + vo.getlMatriculaInicial() + " a " + vo.getlMatriculaFinal());
		parametros.put("DATA_PROCESSAMENTO", DataUtil.toStringBr(obterDataAtualMovimento()));
		parametros.put("NUM_COOPERATIVA", new DecimalFormat("0000").format(Integer.valueOf(obterCooperativa())));
		parametros.put("DESC_SIGLA_COOP", sNomeCoop);
		parametros.put("TXT_PAC", nomePac);
		parametros.put("TXT_MOTIVO", txtMotivo);
		parametros.put("TXT_TIPO_REL", txtTipoRel);		
		parametros.put("TXT_DATA_DEV", txtDataDev);
		parametros.put("TXT_MOTIVO_DEV", txtMotivoDev);
		parametros.put("TXT_TIPO_SUB_REL", txtTipoSubRel);
		parametros.put("URL_SUB", recuperarUrlRelatorio("CCARelCapRealDev_subreport.jasper"));
		parametros.put("COD_RELATORIO", "CCA-007");
		parametros.put("TIPO_REL", tipoRel);
		vo.setiTipo((long) (vo.getiTipo().longValue() == 1? 3 : 4));
		List<DadosRelCapRealDevVO> dadosSub = relCcaDelegate.gerarRelCapRealDev(vo);
		if(!vo.getbAtivos()){
			List<DadosRelCapRealDevVO> filtradosSub = new ArrayList<DadosRelCapRealDevVO>();
			for(DadosRelCapRealDevVO drSub: dadosSub){
				if(drSub.getValorCalculado().compareTo(BigDecimal.ZERO) > 0){
					filtradosSub.add(drSub);
				}
			}
			parametros.put("DS_RESUMO", new ColecaoDataSource(filtradosSub, 1, true));
		}
		else
			parametros.put("DS_RESUMO", new ColecaoDataSource(dadosSub, 1, true));
		
	}

	private void gerarRelatorio(List<DadosRelCapRealDevVO> dados,
			Map<String, Object> parametros) throws RelatorioException {

		RelatorioAtendimentoCCA<DadosRelCapRealDevVO> relatorio = 
				new RelatorioAtendimentoCCA<DadosRelCapRealDevVO>(dados, "CCARelCapRealDev.jasper", parametros);

		ContextoHttp.getInstance().adicionarContexto(
				"CCARelatorioCapRealDev", relatorio.gerarSincronamente());
	}	
	
	private List<ItemListaVO> preencherMotivo() throws BancoobException {
		List<ItemListaVO> lista = obterLista(ContaCapitalConstantes.LST_MOTIVOS_DEVOLUCAO_CAPITAL);
		return lista;
	}


	
}
