package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

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
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelResumoSaldoDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosRelResumoSaldoProxy;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelResumoSaldoServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	private RelResumoSaldoDelegate resumoSaldoDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelResumoSaldoDelegate();

	public static final String CODIGO_RELATORIO = "CCA-018";
	public static final String NOME_RELATORIO_SESSAO = "CCARelPosicaoDiariaCarteira";
	public static final String NOME_RELATORIO_ARQUIVO = "CCARelPosicaoDiariaCarteira.jasper";

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {

		RetornoDTO retorno;
		DateTime dataAtualProduto;
		List<DadosRelResumoSaldoProxy> dadosRelatorio;
		Map<String, Object> parametros;
		RelatorioAtendimentoCCA<DadosRelResumoSaldoProxy> relatorio;

		dataAtualProduto = obterDataAtualProduto();

		parametros = new HashMap<String, Object>();
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("DADOS_COOPERATIVA", descricaoCooperativa());
		parametros.put("DATA_ATUAL_PRODUTO", DataUtil.converterDateToString(dataAtualProduto, "dd/MM/yyyy"));

		dadosRelatorio = resumoSaldoDelegate.gerarRelResumoSaldo();

		relatorio = new RelatorioAtendimentoCCA<DadosRelResumoSaldoProxy>(
				dadosRelatorio, NOME_RELATORIO_ARQUIVO, parametros);

		ContextoHttp.getInstance().adicionarContexto(NOME_RELATORIO_SESSAO,
				relatorio.gerarSincronamente());

		retorno = new RetornoDTO();

		return retorno;
	}

	private String descricaoCooperativa() throws BancoobException {
		StringBuilder sb = new StringBuilder();

		sb.append(obterCooperativaFmt());
		sb.append(" - ");
		sb.append(obterSiglaCooperativa());

		return sb.toString();
	}
	
}
