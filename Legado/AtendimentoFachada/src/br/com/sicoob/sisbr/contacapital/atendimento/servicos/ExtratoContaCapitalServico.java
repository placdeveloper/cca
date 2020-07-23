package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import br.com.sicoob.sisbr.cca.api.negocio.delegates.APIContaCapitalFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelAtendimentoComumDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.RelContaCapitalProxy;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosRelExtratoCcaProxy;

@RemoteService
public class ExtratoContaCapitalServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");


	RelAtendimentoComumDelegate relDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelAtendimentoComumDelegate();
	RelContaCapitalDelegate relCcaDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelContaCapitalDelegate();

	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Integer numPJ = (Integer) dto.getDados().get("numPessoaJuridica");
		String[] minMax = relDelegate.obterProcuraDep(numPJ);
		retorno.getDados().put("numPJ", numPJ);
		retorno.getDados().put("depIni", minMax[0]);
		retorno.getDados().put("depFin", minMax[1]);
		return retorno;
	}

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		String[] minMax = obterMatricMinimaMaxima();

		retorno.getDados().put("pacs", recuperarListaPACs());
		retorno.getDados().put("min", minMax[0]);
		retorno.getDados().put("max", minMax[1]);
		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {
		final RelContaCapitalProxy vo = (RelContaCapitalProxy) dto.getDados()
				.get("filtro");
		List<DadosRelExtratoCcaProxy> dados = relCcaDelegate.executarSpCcaExtrato(vo);
		
		if(dados == null || dados.isEmpty()){			
			throw new AtendimentoCadastroException("Não existem lançamentos para o relatório selecionado.");			
		}else{
			
			br.com.sicoob.sisbr.cca.api.negocio.delegates.ContaCapitalDelegate ccaRenDelegate = 
					APIContaCapitalFabricaDelegates.getInstance().criarContaCapitalDelegate();
			BigDecimal valorBloqueado = ccaRenDelegate
					.obterValorBloqueado(Integer.valueOf(obterCooperativa()), vo.getlMatriculaInicial().intValue());
			if (valorBloqueado != null && valorBloqueado.compareTo(BigDecimal.ZERO) > 0) {
				for (DadosRelExtratoCcaProxy obj : dados) {
					obj.setValorSaldoBloqInt(valorBloqueado);
				}
			}
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			setarParametros(parametros, vo);
			gerarRelatorio(dados, parametros);
			return new RetornoDTO();			
		}
		
	}

	private void setarParametros(Map<String, Object> parametros,
			RelContaCapitalProxy vo) throws BancoobException {
		String sNomeCoop = RecuperadorCooperativa.getInstance()
				.obterSiglaCooperativa(obterCooperativa());
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("TXT_PERIODO",
				"Período: " + DataUtil.toStringBr(vo.getDtmDataInicial()) + " a "
						+ DataUtil.toStringBr(vo.getDtmDataFinal()));
		parametros.put("NUM_COOPERATIVA", new DecimalFormat("0000")
				.format(Integer.valueOf(obterCooperativa())));
		parametros.put("DESC_SIGLA_COOP", sNomeCoop);
		parametros.put("MATR", vo.getbMatrFunc());
	}

	private void gerarRelatorio(List<DadosRelExtratoCcaProxy> dados,
			Map<String, Object> parametros) throws RelatorioException {

		RelatorioAtendimentoCCA<DadosRelExtratoCcaProxy> relatorio = new RelatorioAtendimentoCCA<DadosRelExtratoCcaProxy>(
				dados, "CCARelatorioExtratoCCA.jasper", parametros);

		ContextoHttp.getInstance()
				.adicionarContexto("CCARelatorioExtratoCCA",
						relatorio.gerarSincronamente());
	}
	
}
