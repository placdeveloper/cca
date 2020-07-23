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
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.infraestrutura.relatorios.RelatorioAtendimentoCCA;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelOperacoesFinanceirasDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.proxy.DadosRelOperacoesFinanceirasProxy;
import br.com.sicoob.sisbr.corporativo.produto.negocio.entidades.ProdutoLegado;
import br.com.sicoob.tipos.DateTime;

@RemoteService
public class RelOperacoesFinanceirasServico extends AtendimentoFachada {
	
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("contacapital.propriedades");

	private static final String KEY_DATA_ATUAL_PRODUTO = "keyDataAtualProduto";
	private static final String NOME_RELATORIO_SESSAO = "CCARelOperacoesFinanceiras";
	private static final String NOME_RELATORIO_ARQUIVO = "CCARelOperacoesFinanceiras.jasper";
	
	private RelOperacoesFinanceirasDelegate relOpFinDelegate = AtendimentoFabricaDelegates
			.getInstance().criarRelOperacoesFinanceirasDelegate();

	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		
		RetornoDTO retorno = new RetornoDTO();
		
		retorno.getDados().put(KEY_DATA_ATUAL_PRODUTO, obterDataAntProduto());
		
		return retorno;
	}

	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto)
			throws BancoobException {

		RetornoDTO retorno;
		DateTime dataAtualMovimento;
		List<DadosRelOperacoesFinanceirasProxy> dadosRelatorio;
		Map<String, Object> parametros;
		RelatorioAtendimentoCCA<DadosRelOperacoesFinanceirasProxy> relatorio;

		dataAtualMovimento = (DateTime) dto.getDados().get(
				KEY_DATA_ATUAL_PRODUTO);

		parametros = new HashMap<String, Object>();
		String caminhologosicoob = propriedades.getProperty("ccatendimento.caminhologosicoob");
		parametros.put("IMAGEM_SICOOB", recuperarImagemRelatorio(caminhologosicoob));
		parametros.put("DADOS_COOPERATIVA", descricaoCooperativa());
		parametros.put("DATA_ATUAL_PRODUTO", obterDataAtualProdutoCorporativo());

		dadosRelatorio = relOpFinDelegate.obterDadosRelatorio(dataAtualMovimento);

		relatorio = new RelatorioAtendimentoCCA<DadosRelOperacoesFinanceirasProxy>(
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
	
	private DateTime obterDataAtualProdutoCorporativo() throws BancoobException{
		
		ProdutoLegado produto = produtoDelegate.obter(
				ContaCapitalConstantes.PRODUTO_CORPORATIVO);
		
		if(produto != null && produto.getDataAtualProd() != null){
			return new DateTime(produto.getDataAtualProd().getTime());
		}else{
			throw new BancoobException("Erro ao obter data atual produto");
		}
		
	}
	
}
