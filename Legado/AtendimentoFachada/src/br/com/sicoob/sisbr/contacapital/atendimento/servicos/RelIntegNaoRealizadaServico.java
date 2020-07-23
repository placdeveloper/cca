package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.excecao.RelatorioException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.retaguarda.comum.RecuperadorCooperativa;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.RelContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosFnIntegralizacaoVO;

@RemoteService
public class RelIntegNaoRealizadaServico extends AtendimentoFachada {

	RelContaCapitalDelegate relCcaDelegate = AtendimentoFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");	
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
		throws BancoobException{
		RetornoDTO retorno = new RetornoDTO();
		String[] minMax = obterMatricMinimaMaxima();
		
		retorno.getDados().put("pacs", recuperarListaPACs());
		retorno.getDados().put("dtAtualProd", obterDataAtualProduto());
		retorno.getDados().put("min", minMax[0]);
		retorno.getDados().put("max", minMax[1]);		
		return retorno;	
	}	
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
		throws BancoobException{
		final RelContaCapitalProxy vo = (RelContaCapitalProxy) dto.getDados().get("filtro");
		relCcaDelegate.validarCamposExtratoCca(vo);
		List<DadosFnIntegralizacaoVO> dados = relCcaDelegate.executarFnIntegralizacao(vo);
		List<DadosFnIntegralizacaoVO> filtrados = new ArrayList<DadosFnIntegralizacaoVO>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		setarParametros(parametros, vo);
		if(vo.getiNumPac() != -1){
			for(DadosFnIntegralizacaoVO df : dados){
				if(df.getNumPac().longValue() == vo.getiNumPac().longValue()){
					filtrados.add(df);
				}
			}
			gerarRelatorio(filtrados, parametros);
		} else
			gerarRelatorio(dados, parametros);
		return new RetornoDTO();
	}
	
	private void setarParametros(Map<String, Object> parametros,
			RelContaCapitalProxy vo) throws BancoobException {
		String sNomeCoop = RecuperadorCooperativa.getInstance()
				.obterSiglaCooperativa(obterCooperativa());
		String nomePac = "";
		if(vo.getiNumPac() != -1){
			nomePac = "Cooperativa/Pac: " + new DecimalFormat("00")
			.format(Integer.valueOf(vo.getiNumPac())) + " - " + obterNomePac(vo.getiNumPac());
		}
		parametros.put("TXT_COMPLEMENTO", DataUtil.toStringBr(vo.getDtmDataInicial()) + " a " + DataUtil.toStringBr(vo.getDtmDataFinal()));
		parametros.put("NUM_COOPERATIVA", new DecimalFormat("0000")
		.format(Integer.valueOf(obterCooperativa())));
		parametros.put("DESC_SIGLA_COOP", sNomeCoop);
		parametros.put("TXT_PAC", nomePac);
		parametros.put("DATA_PROCESSAMENTO", DataUtil.toStringBr(obterDataAtualProduto()));
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");  
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		
		if(vo.getbIntegPendente()){
			if(vo.getbAnalitico()){
				parametros.put("COD_RELATORIO", "CCA037");
			} else
				parametros.put("COD_RELATORIO", "CCA039");
		} else
			parametros.put("COD_RELATORIO", "CCA038");
	}

	private void gerarRelatorio(List<DadosFnIntegralizacaoVO> dados,
			Map<String, Object> parametros) throws RelatorioException {

		RelatorioAtendimentoCCA<DadosFnIntegralizacaoVO> relatorio = 
				new RelatorioAtendimentoCCA<DadosFnIntegralizacaoVO>(dados, "CCARelatorioIntegNaoRealizada.jasper", parametros);

		ContextoHttp.getInstance().adicionarContexto(
				"CCARelatorioIntegNaoRealizada", relatorio.gerarSincronamente());
	}
	

	
}
