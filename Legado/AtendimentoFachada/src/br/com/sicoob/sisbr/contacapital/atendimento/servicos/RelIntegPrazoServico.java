package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.retaguarda.comum.RecuperadorCooperativa;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.RelContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosRelIntegPrazoVO;

@RemoteService
public class RelIntegPrazoServico extends AtendimentoFachada {

	RelContaCapitalDelegate relCcaDelegate = AtendimentoFabricaDelegates.getInstance().criarRelContaCapitalDelegate();
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");	
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String[] minMax = obterMatricMinimaMaxima();
		String[] empMinMax = obterEmpresaMinimaMaxima();

		retorno.getDados().put("pacs", recuperarListaPACs());
		retorno.getDados().put("itens", preencherFormaDebito());
		retorno.getDados().put("min", minMax[0]);
		retorno.getDados().put("max", minMax[1]);
		retorno.getDados().put("empMin", empMinMax[0]);
		retorno.getDados().put("empMax", empMinMax[1]);
		return retorno;
	}
	
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException{
			final RelContaCapitalProxy vo = (RelContaCapitalProxy) dto.getDados().get("filtro");
			relCcaDelegate.validarCamposDebIndeterminado(vo);
			List<DadosRelIntegPrazoVO> dados = relCcaDelegate.gerarRelDebIndeterminado(vo);
			Map<String, Object> parametros = new HashMap<String, Object>();
			setarParametros(parametros, vo);			
			gerarRelatorio(dados, parametros);
			return new RetornoDTO();
		}
		
	private void setarParametros(Map<String, Object> parametros,
			RelContaCapitalProxy vo) throws BancoobException {
		String sNomeCoop = RecuperadorCooperativa.getInstance()
				.obterSiglaCooperativa(obterCooperativa());
		String nomePac = "";
		String ordenacao = "";
		if(vo.getiOrdenacao().intValue() == 1){
			ordenacao = "CLIENTE";
		} else if(vo.getiOrdenacao().intValue() == 2){
			ordenacao = "MATRÍCULA";
		} else
			ordenacao = "NOME";
		if (vo.getiNumPac() != -1) {
			nomePac = "Cooperativa/Pac: "
					+ new DecimalFormat("00").format(Integer.valueOf(vo
							.getiNumPac())) + " - "
					+ obterNomePac(vo.getiNumPac());
		}
		parametros.put(
				"TXT_COMPLEMENTO",
				"Matrículas: " + vo.getlMatriculaInicial() + " a "
						+ vo.getlMatriculaFinal() + " - Ordenação: " + ordenacao);
		parametros.put("NUM_COOPERATIVA", new DecimalFormat("0000")
				.format(Integer.valueOf(obterCooperativa())));
		parametros.put("DESC_SIGLA_COOP", sNomeCoop);
		parametros.put("TXT_PAC", nomePac);
		parametros.put("DATA_PROCESSAMENTO",
				DataUtil.toStringBr(obterDataAtualProduto()));
		parametros.put("COD_RELATORIO", "CCA-043");
		parametros.put("BEMPRESA", vo.getbEmpresa());
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");  
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		
	}

	private void gerarRelatorio(List<DadosRelIntegPrazoVO> dados,
			Map<String, Object> parametros) throws RelatorioException {

		RelatorioAtendimentoCCA<DadosRelIntegPrazoVO> relatorio = new RelatorioAtendimentoCCA<DadosRelIntegPrazoVO>(
				dados, "CCARelDebIndeterminado.jasper", parametros);

		ContextoHttp.getInstance().adicionarContexto("CCARelDebIndeterminado",
				relatorio.gerarSincronamente());
	}

	private List<ItemListaVO> preencherFormaDebito() throws BancoobException {
		List<ItemListaVO> lista = obterLista(ContaCapitalConstantes.LST_FORMA_DEBITO_INTEGRALIZACAO);
		List<ItemListaVO> listaDebito = new ArrayList<ItemListaVO>();
		Iterator<ItemListaVO> it = lista.iterator();
		while(it.hasNext()){
			ItemListaVO vo = it.next();
			if(!vo.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_CAIXA.toString())
					&& !vo.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RATEIO.toString())
					&& !vo.getCodigoItem().equals(ContaCapitalConstantes.COD_MODO_LANCAMENTO_VIA_RESERVA.toString())){
				listaDebito.add(vo);
			}				
		}
		return listaDebito;
	}	

	
}
